package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.task.ConnectTask;
import org.im97mori.rbt.RbtConstants;
import org.im97mori.rbt.ble.RbtBLEConnection;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor1;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor2;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor1;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor2;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor1;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor2;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor1;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor2;
import org.im97mori.rbt.ble.characteristic.EtvocSensor1;
import org.im97mori.rbt.ble.characteristic.EtvocSensor2;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor1;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor2;
import org.im97mori.rbt.ble.characteristic.PgaAcceleration;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor1;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor2;
import org.im97mori.rbt.ble.characteristic.SeismicIntensityAcceleration;
import org.im97mori.rbt.ble.characteristic.SiValueAcceleration;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor1;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor2;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor1;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor2;

import java.util.LinkedList;
import java.util.List;

public class EventSettingServiceSampleActivity extends BaseActivity implements View.OnClickListener, SampleCallback {

    private static class TestScanCallback extends ScanCallback {

        final EventSettingServiceSampleActivity mActivity;
        final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

        private TestScanCallback(EventSettingServiceSampleActivity activity) {
            mActivity = activity;
            RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
            mRbtAdvertisingDataParser = builder.build();
        }

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            parse(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {

            for (ScanResult result : results) {
                parse(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
        }

        private void parse(final ScanResult result) {
            ScanRecord record = result.getScanRecord();
            if (record != null) {
                byte[] data = record.getBytes();

                RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult = mRbtAdvertisingDataParser.parse(data);
                if (rbtAdvertisingDataParseResult != null && rbtAdvertisingDataParseResult.isRbt()) {

                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mActivity.mRbtBLEConnection = new RbtBLEConnection(mActivity, result.getDevice(), mActivity.mRbtBleCallbackSample);
                                mActivity.mBluetoothLeScanner.stopScan(EventSettingServiceSampleActivity.TestScanCallback.this);
                                mActivity.mTestScanCallback = null;

                                mActivity.mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private TestScanCallback mTestScanCallback;
    private RbtBLEConnection mRbtBLEConnection;

    private ListView mListView;
    private Button mConnectDisconnectButton;

    private RbtCallbackSample mRbtBleCallbackSample;
    private ArrayAdapter<Pair<String, String>> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRbtBleCallbackSample = new RbtCallbackSample(this);

        mAdapter = new ArrayAdapter<Pair<String, String>>(this, R.layout.list_child, new LinkedList<Pair<String, String>>()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View child = convertView;
                if (child == null) {
                    child = getLayoutInflater().inflate(R.layout.list_child, parent, false);
                }
                TextView textView = child.findViewById(R.id.time);
                textView.setText(getItem(position).first);
                textView = child.findViewById(R.id.body);
                textView.setText(getItem(position).second);
                return child;
            }
        };
        mListView = findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        mConnectDisconnectButton = findViewById(R.id.connectDisconnectButton);
        mConnectDisconnectButton.setOnClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gatt_sample;
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLayout();
    }

    private void updateLayout() {
        if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        } else if (mBluetoothLeScanner == null) {
            mConnectDisconnectButton.setVisibility(View.GONE);
        } else {
            mConnectDisconnectButton.setVisibility(View.VISIBLE);

            if (mRbtBLEConnection == null) {
                mConnectDisconnectButton.setText(R.string.connect);
            } else {
                if (mRbtBLEConnection.isConnected()) {
                    mConnectDisconnectButton.setText(R.string.disconnect);
                } else {
                    mConnectDisconnectButton.setText(R.string.connect);
                }
            }
        }
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothLeScanner != null && mTestScanCallback != null) {
            mBluetoothLeScanner.stopScan(mTestScanCallback);
            mTestScanCallback = null;
        }
        if (mRbtBLEConnection != null) {
            mRbtBLEConnection.quit();
            mRbtBLEConnection = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_setting_service, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setEnabled(mRbtBLEConnection != null && mRbtBLEConnection.isConnected());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.read_temperature_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readTemperatureSensor1();
        } else if (R.id.write_temperature_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeTemperatureSensor1(new TemperatureSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 3500
                    , 4000
                    , 1000
                    , 0
                    , 100
                    , 200
                    , 100
                    , 200
            ));
        } else if (R.id.read_temperature_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readTemperatureSensor2();
        } else if (R.id.write_temperature_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeTemperatureSensor2(new TemperatureSensor2(
                    3500
                    , 1000
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_relative_humidity_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readRelativeHumiditySensor1();
        } else if (R.id.write_relative_humidity_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeRelativeHumiditySensor1(new RelativeHumiditySensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 8500
                    , 9500
                    , 3500
                    , 1000
                    , 100
                    , 200
                    , 100
                    , 200
            ));
        } else if (R.id.read_relative_humidity_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readRelativeHumiditySensor2();
        } else if (R.id.write_relative_humidity_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeRelativeHumiditySensor2(new RelativeHumiditySensor2(
                    8500
                    , 3500
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_ambient_light_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readAmbientLightSensor1();
        } else if (R.id.write_ambient_light_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeAmbientLightSensor1(new AmbientLightSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 300
                    , 1000
                    , 100
                    , 10
                    , 100
                    , 200
                    , 100
                    , 200
            ));
        } else if (R.id.read_ambient_light_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readAmbientLightSensor2();
        } else if (R.id.write_ambient_light_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeAmbientLightSensor2(new AmbientLightSensor2(
                    300
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_barometric_pressure_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readBarometricPressure1();
        } else if (R.id.write_barometric_pressure_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeBarometricPressureSensor1(new BarometricPressureSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 10300
                    , 10500
                    , 9700
                    , 9500
                    , 100
                    , 200
                    , 100
                    , 200
            ));
        } else if (R.id.read_barometric_pressure_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readBarometricPressure2();
        } else if (R.id.write_barometric_pressure_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeBarometricPressureSensor2(new BarometricPressureSensor2(
                    10300
                    , 9700
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_sound_noise_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readSoundNoise1();
        } else if (R.id.write_sound_noise_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeSoundNoiseSensor1(new SoundNoiseSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 7000
                    , 9000
                    , 5000
                    , 4000
                    , 1000
                    , 2000
                    , 1000
                    , 2000
            ));
        } else if (R.id.read_sound_noise_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readSoundNoise2();
        } else if (R.id.write_sound_noise_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeSoundNoiseSensor2(new SoundNoiseSensor2(
                    7000
                    , 5000
                    , 1000
                    , 1000
                    , 1000
                    , 1000
                    , 1000
                    , 1000
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_etvoc_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readEtvocSensor1();
        } else if (R.id.write_etvoc_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeEtvocSensor1(new EtvocSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 250
                    , 450
                    , 100
                    , 50
                    , 50
                    , 100
                    , 50
                    , 100
            ));
        } else if (R.id.read_etvoc_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readEtvocSensor2();
        } else if (R.id.write_etvoc_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeEtvocSensor2(new EtvocSensor2(
                    250
                    , 100
                    , 50
                    , 50
                    , 50
                    , 50
                    , 50
                    , 50
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_eco2_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readEco2Sensor1();
        } else if (R.id.write_eco2_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeEco2Sensor1(new Eco2Sensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 1500
                    , 2500
                    , 1000
                    , 600
                    , 100
                    , 200
                    , 50
                    , 200
            ));
        } else if (R.id.read_eco2_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readEco2Sensor2();
        } else if (R.id.write_eco2_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeEco2Sensor2(new Eco2Sensor2(
                    1500
                    , 1000
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_discomfort_index_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readDiscomfortIndexSensor1();
        } else if (R.id.write_discomfort_index_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeDiscomfortIndexSensor1(new DiscomfortIndexSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 7500
                    , 8000
                    , 6000
                    , 5500
                    , 200
                    , 500
                    , 200
                    , 500
            ));
        } else if (R.id.read_discomfort_index_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readDiscomfortIndexSensor2();
        } else if (R.id.write_discomfort_index_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeDiscomfortIndexSensor2(new DiscomfortIndexSensor2(
                    7500
                    , 6000
                    , 200
                    , 200
                    , 200
                    , 200
                    , 200
                    , 200
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_heat_stroke_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.readHeatStroke1();
        } else if (R.id.write_heat_stroke_sensor_1 == item.getItemId()) {
            mRbtBLEConnection.writeHeatStrokeSensor1(new HeatStrokeSensor1(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 2800
                    , 3100
                    , 2500
                    , 2200
                    , 100
                    , 200
                    , 100
                    , 200
            ));
        } else if (R.id.read_heat_stroke_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.readHeatStroke2();
        } else if (R.id.write_heat_stroke_sensor_2 == item.getItemId()) {
            mRbtBLEConnection.writeHeatStrokeSensor2(new HeatStrokeSensor2(
                    2800
                    , 2500
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 100
                    , 8
                    , 8
                    , 8
                    , 8
            ));
        } else if (R.id.read_si_value_acceleration == item.getItemId()) {
            mRbtBLEConnection.readSiValueAcceleration();
        } else if (R.id.write_si_value_acceleration == item.getItemId()) {
            mRbtBLEConnection.writeSiValueAcceleration(new SiValueAcceleration(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 100
                    , 170
                    , 30
                    , 50
            ));
        } else if (R.id.read_pga_acceleration == item.getItemId()) {
            mRbtBLEConnection.readPgaAcceleration();
        } else if (R.id.write_pga_acceleration == item.getItemId()) {
            mRbtBLEConnection.writePgaAcceleration(new PgaAcceleration(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 500
                    , 1000
                    , 200
                    , 500
            ));
        } else if (R.id.read_seismic_intensity_acceleration == item.getItemId()) {
            mRbtBLEConnection.readSeismicIntensityAcceleration();
        } else if (R.id.write_seismic_intensity_acceleration == item.getItemId()) {
            mRbtBLEConnection.writeSeismicIntensityAcceleration(new SeismicIntensityAcceleration(
                    RbtConstants.EventEnableDisableSensor.DISABLE
                    , 3500
                    , 5000
                    , 500
                    , 1000
            ));
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (R.id.connectDisconnectButton == v.getId()) {
            if (mRbtBLEConnection == null) {
                if (mBluetoothLeScanner != null) {
                    if (mTestScanCallback == null) {
                        if (hasPermission()) {
                            if (mRbtBLEConnection != null) {
                                mRbtBLEConnection.quit();
                                mRbtBLEConnection = null;
                            }
                            mTestScanCallback = new TestScanCallback(this);
                            mBluetoothLeScanner.startScan(mTestScanCallback);
                        }
                    } else {
                        mBluetoothLeScanner.stopScan(mTestScanCallback);
                        mTestScanCallback = null;
                    }
                }
            } else {
                if (mRbtBLEConnection.isConnected()) {
                    mRbtBLEConnection.quit();
                } else {
                    mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                }
            }

            updateLayout();
        } else {
            super.onClick(v);
        }
    }

    @Override
    public void onCallbacked(final Pair<String, String> log) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.add(log);
                mListView.smoothScrollToPosition(mAdapter.getCount());

                updateLayout();
            }
        });
    }

}