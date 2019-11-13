# OMRON 2JCIE-BU01 Data Parser
オムロン社の環境センサ[2JCIE-BU01](https://www.fa.omron.co.jp/products/family/3724/)をAndroid上から利用する為のライブラリです。
バージョン0.4.0ではBLEのみをサポートしています(USB接続は未対応)。

## Prerequire
minSdkVersion 18

## Download
project/build.gradle

    repositories {
        google()
        jcenter()
        maven { url "https://github.com/im97mori-github/maven/raw/master" }
    }

project/module/build.gradle

    dependencies {
        implementation 'org.im97mori:rbt-ble-central:0.4.0'
    }

## How to use
### Advertising data
5種類(Sensor data / Calculation data / Sensor data & Calculation data / Sensor flag & Calculation flag / Serial Number)のAdvertising dataに対応しています。
[LeScanCallback](https://developer.android.com/reference/android/bluetooth/BluetoothAdapter.LeScanCallback.html#onLeScan(android.bluetooth.BluetoothDevice,%20int,%20byte[])) や [ScanCallback](https://developer.android.com/reference/android/bluetooth/le/ScanCallback#onScanResult(int,%2520android.bluetooth.le.ScanResult))
から取得したバイト配列を解析し、対応したデータクラスを生成します。

    @Override
    public void onScanResult(int callbackType, ScanResult result) {
        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult = parser.parse(record.getBytes());
    }

バイト配列内に5種類のデータのいずれかが含まれている場合、RbtAdvertisingDataParseResultから対応したデータクラスを取得することが可能です。

        // Sensor dataが含まれている場合
        SensorData sensorData = rbtAdvertisingDataParseResult.getSensorData();
        if (sensorData != null) {
            System.out.println(sensorData.getSequenceNumber());
            System.out.println(sensorData.getTemperatureDegC());
            System.out.println(sensorData.getRelativeHumidityRh());
            System.out.println(sensorData.getAmbientLightLx());
            System.out.println(sensorData.getBarometricPressureHpa());
            System.out.println(sensorData.getSoundNoiseDb());
            System.out.println(sensorData.getEtvocPpb());
            System.out.println(sensorData.getEco2Ppm());
        }

FilteredRbtLeScanCallbackおよびFilteredRbtScanCallbackを使用することでAdvertising dataが特定の条件に一致する場合のみコールバックを行うが可能です

ex) 受信した3.1. Sensor dataもしくは 3.3 Sensor data & Calculation dataの温度が30度を超えてるかつ湿度が70%を超えている場合のみコールバックする

    BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner().startScan(new FilteredRbtScanCallback.Builder()
            .addTemperatureFilter(TYPE_GREATER_THAN, 3000)
            .addRelativeHumidityFilter(TYPE_GREATER_THAN, 7000)
            .build());


### BLE GATT Services
2JCIE-BU01に定義されている全てのServiceおよびCharacteristicに対応しています。

#### 2JCIE-BU01への接続
RbtBLEConnectionクラスを使用して接続を行います。

    public class MainActivity2 extends Activity implements RbtCallback {
    
        private RbtBLEConnection rbtBLEConnection;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    
            Intent intent = getIntent();
            BluetoothDevice bluetoothDevice = intent.getParcelableExtra("device");
            
            rbtBLEConnection = new RbtBLEConnection(this, bluetoothDevice, this);
            rbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
        }

        @Override
        public void onRbtConnected(BluetoothDevice bluetoothDevice) {
            // 接続完了
        }
    
        @Override
        public void onRbtConnectTimeout(BluetoothDevice bluetoothDevice) {
            /// 接続失敗 or タイムアウト
        }

#### 2JCIE-BU01から切断
RbtBLEConnectionインスタンスはペリフェラルへのリクエスト/レスポンスを処理する専用の[Handler](https://developer.android.com/reference/android/os/Handler)を保持しているためかならず終了する必要があります。

    @Override
    protected void onDestroy() {
        rbtBLEConnection.quit();
        super.onDestroy();
    }

この処理を行わない場合、Handlerスレッドが解放さないためメモリリークを引き起こします。

### Characteristicの読み込み / 書き込み
RbtBLEConnectionクラスにはそれぞれのCharacteristic(およびDescriptor)の読み込み/書き込みを行うメソッドが定義されています。
またRbtCallbackインターフェースには対応した処理結果メソッドが定義されています。

#### 読み込み
例 Control Service(0x5110)内のLED setting normal state(0x5111)の場合

    // 読み込みタスクをキューイング
    @Override
    public void onClick(View v) {
        rbtBLEConnection.readLedSettingNormalState();
    }
    
    @Override
    public void onLedSettingNormalStateReadSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState) {
        // 読み込み成功
        System.out.println(ledSettingNormalState.getDisplayRule());
        System.out.println(ledSettingNormalState.getIntensityOfLedRed());
        System.out.println(ledSettingNormalState.getIntensityOfLedGreen());
        System.out.println(ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingNormalStateReadFailed(BluetoothDevice bluetoothDevice, int status) {
        // 読み込み失敗
    }

    @Override
    public void onLedSettingNormalStateReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        // タイムアウト
    }

#### 書き込み
2JCIE-BU01は一部のCharacteristic書き込み完了後、FLASH memory status(0x5403)による書き込み成否の判断を行う必要がありますが
この処理は個々のCharacteristic書き込み処理タスク内で自動的に行われるため別途読み込み処理を行う必要はありません。

例1 LED setting normal state(Flash memory statusチェックが必要)の書き込みタスク内処理
1. LED setting normal state書き込み
1. FLASH memory status読み込み
1. FLASH memory status結果がWrite success (0x02)の場合は成功としてタスク終了およびコールバックインターフェース呼び出し
1. FLASH memory status結果がWrite failure (0x03)の場合は失敗としてタスク終了およびコールバックインターフェース呼び出し
1. FLASH memory status結果がWriting (0x01)の場合は再度FLASH memory status読み込み

```
    @Override
    public void onClick(View v) {
        rbtBLEConnection.writeLedSettingNormalState(
                new LedSettingNormalState(
                        LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON
                        , 255
                        , 0
                        , 255
                )
        );
    }
    
    @Override
    public void onLedSettingNormalStateWriteSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState) {
        // 書き込み成功
    }

    @Override
    public void onLedSettingNormalStateWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        // 書き込み失敗
    }

    @Override
    public void onLedSettingNormalStateWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        // タイムアウト
    }
```

例2 Time setting(Flash memory statusチェックが不要)の書き込みタスク内処理
1. Time setting書き込み後タスク終了、成功 / 失敗コールバックインターフェース呼び出し

Request memory index(0x5005)およびRequest acceleration memory index(0x5032)については書き込み直後にnotificationが発生します。
発生するnotificationの順番によりデータ構造が異なるため、上記のCharacteristic書き込みの場合は後続のnotificationの受信とセットとなる処理になっています。

例 Request acceleration memory indexの書き込みタスク内処理
1. Request acceleration memory indexのCCCDにnotificationを書き込み
1. Request acceleration memory index書き込み
1. Acceleration memory data(0x5034)からのnotificationを受信しHeader(20byte * 4packet)もしくはData(20byte * 13packet * リクエストしたページ数)として解析完了毎にコールバックインターフェース呼び出し

```
    @Override
    public void onClick(View v) {
        rbtBLEConnection.writeRequestAccelerationMemoryIndex(new RequestAccelerationMemoryIndex(
                RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA
                , 0x01
                , 0x0000
                , 0x0001
        ));
    }
    
    @Override
    public void onAccelerationMemoryDataHeaderNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryDataHeader accelerationMemoryDataHeader) {
        // Headerがnotifyされた
    }

    @Override
    public void onAccelerationMemoryDataNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryData accelerationMemoryData) {
        // Dataがnotifyされた
    }
    
    @Override
    public void onRequestAccelerationMemoryIndexWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        // 書き込み失敗
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        // タイムアウト
    }
```