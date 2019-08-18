package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import android.util.Pair;

import org.im97mori.ble.characteristic.Appearance;
import org.im97mori.ble.characteristic.CentralAddressResolution;
import org.im97mori.ble.characteristic.DeviceName;
import org.im97mori.ble.characteristic.FirmwareRevisionString;
import org.im97mori.ble.characteristic.HardwareRevisionString;
import org.im97mori.ble.characteristic.ManufacturerNameString;
import org.im97mori.ble.characteristic.ModelNumberString;
import org.im97mori.ble.characteristic.PeripheralPreferredConnectionParameters;
import org.im97mori.ble.characteristic.SerialNumberString;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.rbt.ble.RbtCallback;
import org.im97mori.rbt.ble.characteristic.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.AccelerationLoggerStatus;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryStatus;
import org.im97mori.rbt.ble.characteristic.AdvertiseSetting;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor1;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor2;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor1;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor2;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor1;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor2;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor1;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor2;
import org.im97mori.rbt.ble.characteristic.ErrorStatus;
import org.im97mori.rbt.ble.characteristic.EtvocSensor1;
import org.im97mori.rbt.ble.characteristic.EtvocSensor2;
import org.im97mori.rbt.ble.characteristic.FlashMemoryStatus;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor1;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor2;
import org.im97mori.rbt.ble.characteristic.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.LatestAccelerationStatus;
import org.im97mori.rbt.ble.characteristic.LatestCalculationData;
import org.im97mori.rbt.ble.characteristic.LatestCalculationFlag;
import org.im97mori.rbt.ble.characteristic.LatestSensingData;
import org.im97mori.rbt.ble.characteristic.LatestSensingFlag;
import org.im97mori.rbt.ble.characteristic.LatestTimeCounter;
import org.im97mori.rbt.ble.characteristic.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.MemoryIndexInformation;
import org.im97mori.rbt.ble.characteristic.MemoryReset;
import org.im97mori.rbt.ble.characteristic.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.MemoryStatus;
import org.im97mori.rbt.ble.characteristic.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.ModeChange;
import org.im97mori.rbt.ble.characteristic.MountingOrientation;
import org.im97mori.rbt.ble.characteristic.PgaAcceleration;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor1;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor2;
import org.im97mori.rbt.ble.characteristic.SeismicIntensityAcceleration;
import org.im97mori.rbt.ble.characteristic.SiValueAcceleration;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor1;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor2;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor1;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor2;
import org.im97mori.rbt.ble.characteristic.TimeSetting;
import org.im97mori.rbt.ble.characteristic.VibrationCount;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static org.im97mori.ble.BLEConstants.APPEARANCE_DESCRIPTION_MAP;
import static org.im97mori.ble.BLEConstants.APPEARANCE_VALUE_MAP;

public class RbtCallbackSample implements RbtCallback {

    private SampleCallback mSampleCallback;
    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.US);

    RbtCallbackSample(SampleCallback sampleCallback) {
        mSampleCallback = sampleCallback;
    }

    private void callback(Object... logs) {
        int index = 0;
        StackTraceElement[] stackTraceElementArray = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElementArray.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArray[i];
            if (RbtCallbackSample.class.getName().equals(stackTraceElement.getClassName())
                    && "callback".equals(stackTraceElement.getMethodName())) {
                index = i + 1;
                break;
            }
        }

        String now;
        synchronized (format) {
            now = format.format(new Date());
            format.notifyAll();
        }
        if (logs.length == 0) {
            mSampleCallback.onCallbacked(Pair.create(now, stackTraceElementArray[index].getMethodName()));
        } else {
            mSampleCallback.onCallbacked(Pair.create(now, stackTraceElementArray[index].getMethodName() + '\n' + TextUtils.join("\n", logs)));
        }
    }

    @Override
    public void onRbtConnected(BluetoothDevice bluetoothDevice) {
        callback();
    }

    @Override
    public void onRbtConnectFailed(BluetoothDevice bluetoothDevice) {
        callback();
    }

    @Override
    public void onRbtConnectTimeout(BluetoothDevice bluetoothDevice) {
        callback();
    }

    @Override
    public void onRbtDisonnected(BluetoothDevice bluetoothDevice) {
        callback();
    }

    @Override
    public void onMemoryIndexInformationReadSuccess(BluetoothDevice bluetoothDevice, MemoryIndexInformation memoryIndexInformation) {
        callback(memoryIndexInformation.getMemoryIndexLatest(), memoryIndexInformation.getMemoryIndexLast());
    }

    @Override
    public void onMemoryIndexInformationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryIndexInformationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRequestMemoryIndexWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRequestMemoryIndexWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, MemoryStatus memoryStatus) {
        callback(memoryStatus.getStatus(), memoryStatus.getTimeCounter(), memoryStatus.getMemoryStorageInterval());
    }

    @Override
    public void onMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingDataNotified(BluetoothDevice bluetoothDevice, MemorySensingData memorySensingData) {
        callback(memorySensingData.getMemoryIndex()
                , memorySensingData.getTemperatureDegC()
                , memorySensingData.getRelativeHumidityRh()
                , memorySensingData.getAmbientLightLx()
                , memorySensingData.getBarometricPressureHpa()
                , memorySensingData.getSoundNoiseDb()
                , memorySensingData.getEtvocPpb()
                , memorySensingData.getEco2Ppm());
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationDataNotified(BluetoothDevice bluetoothDevice, MemoryCalculationData memoryCalculationData) {
        callback(memoryCalculationData.getMemoryIndex()
                , memoryCalculationData.getDiscomfortIndexWithUnit()
                , memoryCalculationData.getHeatStrokeDegC()
                , memoryCalculationData.getVibrationInformation()
                , memoryCalculationData.getSiValueKine()
                , memoryCalculationData.getPgaGal()
                , memoryCalculationData.getSeismicIntensityWithUnit());
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingFlagNotified(BluetoothDevice bluetoothDevice, MemorySensingFlag memorySensingFlag) {
        callback(memorySensingFlag.getMemoryIndex()
                , memorySensingFlag.getTemperatureFlag()
                , memorySensingFlag.getRelativeHumidityFlag()
                , memorySensingFlag.getAmbientLightFlag()
                , memorySensingFlag.getBarometricPressureFlag()
                , memorySensingFlag.getSoundNoiseFlag()
                , memorySensingFlag.getEtvocFlag()
                , memorySensingFlag.getEco2Flag());
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationFlagNotified(BluetoothDevice bluetoothDevice, MemoryCalculationFlag memoryCalculationFlag) {
        callback(memoryCalculationFlag.getMemoryIndex()
                , memoryCalculationFlag.getDiscomfortIndexFlag()
                , memoryCalculationFlag.getHeatStrokeFlag()
                , memoryCalculationFlag.getSiValueFlag()
                , memoryCalculationFlag.getPgaFlag()
                , memoryCalculationFlag.getSeismicIntensityFlag());
    }

    @Override
    public void onLatestSensingDataReadSuccess(BluetoothDevice bluetoothDevice, LatestSensingData latestSensingData) {
        callback(latestSensingData.getSequenceNumber()
                , latestSensingData.getTemperatureDegC()
                , latestSensingData.getRelativeHumidityRh()
                , latestSensingData.getAmbientLightLx()
                , latestSensingData.getBarometricPressureHpa()
                , latestSensingData.getSoundNoiseDb()
                , latestSensingData.getEtvocPpb()
                , latestSensingData.getEco2Ppm()
        );
    }

    @Override
    public void onLatestSensingDataReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestSensingDataReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingDataNotified(BluetoothDevice bluetoothDevice, LatestSensingData latestSensingData) {
        callback(latestSensingData.getSequenceNumber()
                , latestSensingData.getTemperatureDegC()
                , latestSensingData.getRelativeHumidityRh()
                , latestSensingData.getAmbientLightLx()
                , latestSensingData.getBarometricPressureHpa()
                , latestSensingData.getSoundNoiseDb()
                , latestSensingData.getEtvocPpb()
                , latestSensingData.getEco2Ppm()
        );
    }

    @Override
    public void onLatestCalculationDataReadSuccess(BluetoothDevice bluetoothDevice, LatestCalculationData latestCalculationData) {
        callback(latestCalculationData.getSequenceNumber()
                , latestCalculationData.getDiscomfortIndexWithUnit()
                , latestCalculationData.getHeatStrokeDegC()
                , latestCalculationData.getVibrationInformation()
                , latestCalculationData.getSiValueKine()
                , latestCalculationData.getPgaGal()
                , latestCalculationData.getSeismicIntensityWithUnit()
                , latestCalculationData.getAccelerationXAxisGal()
                , latestCalculationData.getAccelerationYAxisGal()
                , latestCalculationData.getAccelerationZAxisGal()
        );
    }

    @Override
    public void onLatestCalculationDataReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestCalculationDataReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationDataNotified(BluetoothDevice bluetoothDevice, LatestCalculationData latestCalculationData) {
        callback(latestCalculationData.getSequenceNumber()
                , latestCalculationData.getDiscomfortIndexWithUnit()
                , latestCalculationData.getHeatStrokeDegC()
                , latestCalculationData.getVibrationInformation()
                , latestCalculationData.getSiValueKine()
                , latestCalculationData.getPgaGal()
                , latestCalculationData.getSeismicIntensityWithUnit()
                , latestCalculationData.getAccelerationXAxisGal()
                , latestCalculationData.getAccelerationYAxisGal()
                , latestCalculationData.getAccelerationZAxisGal()
        );
    }

    @Override
    public void onLatestSensingFlagReadSuccess(BluetoothDevice bluetoothDevice, LatestSensingFlag latestSensingFlag) {
        callback(latestSensingFlag.getSequenceNumber()
                , latestSensingFlag.getTemperatureFlag()
                , latestSensingFlag.getRelativeHumidityFlag()
                , latestSensingFlag.getAmbientLightFlag()
                , latestSensingFlag.getBarometricPressureFlag()
                , latestSensingFlag.getSoundNoiseFlag()
                , latestSensingFlag.getEtvocFlag()
                , latestSensingFlag.getEco2Flag()
        );
    }

    @Override
    public void onLatestSensingFlagReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestSensingFlagReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingFlagNotified(BluetoothDevice bluetoothDevice, LatestSensingFlag latestSensingFlag) {
        callback(latestSensingFlag.getSequenceNumber()
                , latestSensingFlag.getTemperatureFlag()
                , latestSensingFlag.getRelativeHumidityFlag()
                , latestSensingFlag.getAmbientLightFlag()
                , latestSensingFlag.getBarometricPressureFlag()
                , latestSensingFlag.getSoundNoiseFlag()
                , latestSensingFlag.getEtvocFlag()
                , latestSensingFlag.getEco2Flag()
        );
    }

    @Override
    public void onLatestCalculationFlagReadSuccess(BluetoothDevice bluetoothDevice, LatestCalculationFlag latestCalculationFlag) {
        callback(latestCalculationFlag.getSequenceNumber()
                , latestCalculationFlag.getDiscomfortIndexFlag()
                , latestCalculationFlag.getHeatStrokeFlag()
                , latestCalculationFlag.getSiValueFlag()
                , latestCalculationFlag.getPgaFlag()
                , latestCalculationFlag.getSeismicIntensityFlag()
        );
    }

    @Override
    public void onLatestCalculationFlagReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestCalculationFlagReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationFlagNotified(BluetoothDevice bluetoothDevice, LatestCalculationFlag latestCalculationFlag) {
        callback(latestCalculationFlag.getSequenceNumber()
                , latestCalculationFlag.getDiscomfortIndexFlag()
                , latestCalculationFlag.getHeatStrokeFlag()
                , latestCalculationFlag.getSiValueFlag()
                , latestCalculationFlag.getPgaFlag()
                , latestCalculationFlag.getSeismicIntensityFlag()
        );
    }

    @Override
    public void onLatestAccelerationStatusReadSuccess(BluetoothDevice bluetoothDevice, LatestAccelerationStatus latestAccelerationStatus) {
        callback(latestAccelerationStatus.getSequenceNumber()
                , latestAccelerationStatus.getVibrationInformation()
                , latestAccelerationStatus.getMaximumAccelerationXAxisGal()
                , latestAccelerationStatus.getMaximumAccelerationYAxisGal()
                , latestAccelerationStatus.getMaximumAccelerationZAxisGal()
                , latestAccelerationStatus.getSiValueCalculationAxis()
                , latestAccelerationStatus.getAccelerationOffsetXAxisGal()
                , latestAccelerationStatus.getAccelerationOffsetYAxisGal()
                , latestAccelerationStatus.getAccelerationOffsetZAxisGal()
        );
    }

    @Override
    public void onLatestAccelerationStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestAccelerationStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getConfiguration()));
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestAccelerationStatusNotified(BluetoothDevice bluetoothDevice, LatestAccelerationStatus latestAccelerationStatus) {
        callback(latestAccelerationStatus.getSequenceNumber()
                , latestAccelerationStatus.getVibrationInformation()
                , latestAccelerationStatus.getMaximumAccelerationXAxisGal()
                , latestAccelerationStatus.getMaximumAccelerationYAxisGal()
                , latestAccelerationStatus.getMaximumAccelerationZAxisGal()
                , latestAccelerationStatus.getSiValueCalculationAxis()
                , latestAccelerationStatus.getAccelerationOffsetXAxisGal()
                , latestAccelerationStatus.getAccelerationOffsetYAxisGal()
                , latestAccelerationStatus.getAccelerationOffsetZAxisGal()
        );
    }

    @Override
    public void onVibrationCountReadSuccess(BluetoothDevice bluetoothDevice, VibrationCount vibrationCount) {
        callback(
                vibrationCount.getEarthquakeCount()
                , vibrationCount.getVibrationCount()
        );
    }

    @Override
    public void onVibrationCountReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onVibrationCountReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, AccelerationMemoryStatus accelerationMemoryStatus) {
        callback(
                accelerationMemoryStatus.getStatus()
                , accelerationMemoryStatus.getTotalTransferCount()
        );
    }

    @Override
    public void onAccelerationMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAccelerationMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedSettingNormalStateReadSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState) {
        callback(ledSettingNormalState.getDisplayRule()
                , ledSettingNormalState.getIntensityOfLedRed()
                , ledSettingNormalState.getIntensityOfLedGreen()
                , ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingNormalStateReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedSettingNormalStateReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedSettingNormalStateWriteSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState) {
        callback(ledSettingNormalState.getDisplayRule()
                , ledSettingNormalState.getIntensityOfLedRed()
                , ledSettingNormalState.getIntensityOfLedGreen()
                , ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingNormalStateWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedSettingNormalStateWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedSettingEventStateReadSuccess(BluetoothDevice bluetoothDevice, LedSettingEventState ledSettingEventState) {
        callback(ledSettingEventState.getDisplayRule()
                , ledSettingEventState.getIntensityOfLedRed()
                , ledSettingEventState.getIntensityOfLedGreen()
                , ledSettingEventState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingEventStateReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedSettingEventStateReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedSettingEventStateWriteSuccess(BluetoothDevice bluetoothDevice, LedSettingEventState ledSettingEventState) {
        callback(ledSettingEventState.getDisplayRule()
                , ledSettingEventState.getIntensityOfLedRed()
                , ledSettingEventState.getIntensityOfLedGreen()
                , ledSettingEventState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingEventStateWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedSettingEventStateWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedStateOperationReadSuccess(BluetoothDevice bluetoothDevice, LedStateOperation ledStateOperation) {
        callback(ledStateOperation.getStartUp()
                , ledStateOperation.getError()
                , ledStateOperation.getConnection());
    }

    @Override
    public void onLedStateOperationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedStateOperationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLedStateOperationWriteSuccess(BluetoothDevice bluetoothDevice, LedStateOperation ledStateOperation) {
        callback(ledStateOperation.getStartUp()
                , ledStateOperation.getError()
                , ledStateOperation.getConnection());
    }

    @Override
    public void onLedStateOperationWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLedStateOperationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onInstallationOffsetReadSuccess(BluetoothDevice bluetoothDevice, InstallationOffset installationOffset) {
        callback(installationOffset.getInstallationOffsetEnableDisable()
                , installationOffset.getTemperatureInstallationOffsetDegC()
                , installationOffset.getRelativeHumidityInstallationOffsetRh()
                , installationOffset.getAmbientLightInstallationGainWithUnit()
                , installationOffset.getBarometricPressureInstallationOffsetHpa()
                , installationOffset.getSoundNoiseInstallationOffsetDb());
    }

    @Override
    public void onInstallationOffsetReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onInstallationOffsetReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onInstallationOffsetWriteSuccess(BluetoothDevice bluetoothDevice, InstallationOffset installationOffset) {
        callback(installationOffset.getInstallationOffsetEnableDisable()
                , installationOffset.getTemperatureInstallationOffsetDegC()
                , installationOffset.getRelativeHumidityInstallationOffsetRh()
                , installationOffset.getAmbientLightInstallationGainWithUnit()
                , installationOffset.getBarometricPressureInstallationOffsetHpa()
                , installationOffset.getSoundNoiseInstallationOffsetDb());
    }

    @Override
    public void onInstallationOffsetWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onInstallationOffsetWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAdvertiseSettingReadSuccess(BluetoothDevice bluetoothDevice, AdvertiseSetting advertiseSetting) {
        callback(advertiseSetting.getAdvertisingIntervalMillis()
                , advertiseSetting.getAdvertisingMode());
    }

    @Override
    public void onAdvertiseSettingReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAdvertiseSettingReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAdvertiseSettingWriteSuccess(BluetoothDevice bluetoothDevice, AdvertiseSetting advertiseSetting) {
        callback(advertiseSetting.getAdvertisingIntervalMillis()
                , advertiseSetting.getAdvertisingMode());
    }

    @Override
    public void onAdvertiseSettingWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAdvertiseSettingWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryResetWriteSuccess(BluetoothDevice bluetoothDevice, MemoryReset memoryReset) {
        callback(memoryReset.getMemoryReset());
    }

    @Override
    public void onMemoryResetWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryResetWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onModeChangeReadSuccess(BluetoothDevice bluetoothDevice, ModeChange modeChange) {
        callback(modeChange.getModeChange());
    }

    @Override
    public void onModeChangeReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onModeChangeReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onModeChangeWriteSuccess(BluetoothDevice bluetoothDevice, ModeChange modeChange) {
        callback(modeChange.getModeChange());
    }

    @Override
    public void onModeChangeWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onModeChangeWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAccelerationLoggerControlWriteSuccess(BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl) {
        callback(accelerationLoggerControl.getLoggerCondition()
                , accelerationLoggerControl.getRangeOfDetection()
                , accelerationLoggerControl.getOdrSetting()
                , accelerationLoggerControl.getStartPage()
                , accelerationLoggerControl.getEndPage());
    }

    @Override
    public void onAccelerationLoggerControlWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAccelerationLoggerControlWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAccelerationLoggerStatusReadSuccess(BluetoothDevice bluetoothDevice, AccelerationLoggerStatus accelerationLoggerStatus) {
        callback(accelerationLoggerStatus.getLoggerStatus()
                , accelerationLoggerStatus.getRunningPage());
    }

    @Override
    public void onAccelerationLoggerStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAccelerationLoggerStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onLatestTimeCounterReadSuccess(BluetoothDevice bluetoothDevice, LatestTimeCounter latestTimeCounter) {
        callback(latestTimeCounter.getTimeCounter());
    }

    @Override
    public void onLatestTimeCounterReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onLatestTimeCounterReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTimeSettingReadSuccess(BluetoothDevice bluetoothDevice, TimeSetting timeSetting) {
        callback(timeSetting.getTimeSetting());
    }

    @Override
    public void onTimeSettingReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTimeSettingReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTimeSettingWriteSuccess(BluetoothDevice bluetoothDevice, TimeSetting timeSetting) {
        callback(timeSetting.getTimeSetting());
    }

    @Override
    public void onTimeSettingWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTimeSettingWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryStorageIntervalReadSuccess(BluetoothDevice bluetoothDevice, MemoryStorageInterval memoryStorageInterval) {
        callback(memoryStorageInterval.getMemoryStorageIntervalSec());
    }

    @Override
    public void onMemoryStorageIntervalReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryStorageIntervalReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMemoryStorageIntervalWriteSuccess(BluetoothDevice bluetoothDevice, MemoryStorageInterval memoryStorageInterval) {
        callback(memoryStorageInterval.getMemoryStorageIntervalSec());
    }

    @Override
    public void onMemoryStorageIntervalWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMemoryStorageIntervalWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor1ReadSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor1 temperatureSensor1) {
        callback(temperatureSensor1.getEventEnableDisable()
                , temperatureSensor1.getSimpleThresholdUpperLimit1DegC()
                , temperatureSensor1.getSimpleThresholdUpperLimit2DegC()
                , temperatureSensor1.getSimpleThresholdLowerLimit1DegC()
                , temperatureSensor1.getSimpleThresholdLowerLimit2DegC()
                , temperatureSensor1.getChangeThresholdRise1DegC()
                , temperatureSensor1.getChangeThresholdRise2DegC()
                , temperatureSensor1.getChangeThresholdDecline1DegC()
                , temperatureSensor1.getChangeThresholdDecline2DegC()
        );
    }

    @Override
    public void onTemperatureSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor1WriteSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor1 temperatureSensor1) {
        callback(temperatureSensor1.getEventEnableDisable()
                , temperatureSensor1.getSimpleThresholdUpperLimit1DegC()
                , temperatureSensor1.getSimpleThresholdUpperLimit2DegC()
                , temperatureSensor1.getSimpleThresholdLowerLimit1DegC()
                , temperatureSensor1.getSimpleThresholdLowerLimit2DegC()
                , temperatureSensor1.getChangeThresholdRise1DegC()
                , temperatureSensor1.getChangeThresholdRise2DegC()
                , temperatureSensor1.getChangeThresholdDecline1DegC()
                , temperatureSensor1.getChangeThresholdDecline2DegC()
        );
    }

    @Override
    public void onTemperatureSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor2ReadSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor2 temperatureSensor2) {
        callback(temperatureSensor2.getAverageValueThresholdUpperDegC()
                , temperatureSensor2.getAverageValueThresholdLowerDegC()
                , temperatureSensor2.getPeakToPeakThresholdUpperDegC()
                , temperatureSensor2.getPeakToPeakThresholdLowerDegC()
                , temperatureSensor2.getIntervalDifferenceThresholdUpperDegC()
                , temperatureSensor2.getIntervalDifferenceThresholdLowerDegC()
                , temperatureSensor2.getBaseDifferenceThresholdUpperDegC()
                , temperatureSensor2.getBaseDifferenceThresholdLowerDegC()
                , temperatureSensor2.getAverageValueCount()
                , temperatureSensor2.getPeakToPeakCount()
                , temperatureSensor2.getIntervalDifferenceCount()
                , temperatureSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onTemperatureSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor2WriteSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor2 temperatureSensor2) {
        callback(temperatureSensor2.getAverageValueThresholdUpperDegC()
                , temperatureSensor2.getAverageValueThresholdLowerDegC()
                , temperatureSensor2.getPeakToPeakThresholdUpperDegC()
                , temperatureSensor2.getPeakToPeakThresholdLowerDegC()
                , temperatureSensor2.getIntervalDifferenceThresholdUpperDegC()
                , temperatureSensor2.getIntervalDifferenceThresholdLowerDegC()
                , temperatureSensor2.getBaseDifferenceThresholdUpperDegC()
                , temperatureSensor2.getBaseDifferenceThresholdLowerDegC()
                , temperatureSensor2.getAverageValueCount()
                , temperatureSensor2.getPeakToPeakCount()
                , temperatureSensor2.getIntervalDifferenceCount()
                , temperatureSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onTemperatureSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor1ReadSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor1 relativeHumiditySensor1) {
        callback(relativeHumiditySensor1.getEventEnableDisable()
                , relativeHumiditySensor1.getSimpleThresholdUpperLimit1Rh()
                , relativeHumiditySensor1.getSimpleThresholdUpperLimit2Rh()
                , relativeHumiditySensor1.getSimpleThresholdLowerLimit1Rh()
                , relativeHumiditySensor1.getSimpleThresholdLowerLimit2Rh()
                , relativeHumiditySensor1.getChangeThresholdRise1Rh()
                , relativeHumiditySensor1.getChangeThresholdRise2Rh()
                , relativeHumiditySensor1.getChangeThresholdDecline1Rh()
                , relativeHumiditySensor1.getChangeThresholdDecline2Rh()
        );
    }

    @Override
    public void onRelativeHumiditySensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor1WriteSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor1 relativeHumiditySensor1) {
        callback(relativeHumiditySensor1.getEventEnableDisable()
                , relativeHumiditySensor1.getSimpleThresholdUpperLimit1Rh()
                , relativeHumiditySensor1.getSimpleThresholdUpperLimit2Rh()
                , relativeHumiditySensor1.getSimpleThresholdLowerLimit1Rh()
                , relativeHumiditySensor1.getSimpleThresholdLowerLimit2Rh()
                , relativeHumiditySensor1.getChangeThresholdRise1Rh()
                , relativeHumiditySensor1.getChangeThresholdRise2Rh()
                , relativeHumiditySensor1.getChangeThresholdDecline1Rh()
                , relativeHumiditySensor1.getChangeThresholdDecline2Rh()
        );
    }

    @Override
    public void onRelativeHumiditySensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor2ReadSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor2 relativeHumiditySensor2) {
        callback(relativeHumiditySensor2.getAverageValueThresholdUpperRh()
                , relativeHumiditySensor2.getAverageValueThresholdLowerRh()
                , relativeHumiditySensor2.getPeakToPeakThresholdUpperRh()
                , relativeHumiditySensor2.getPeakToPeakThresholdLowerRh()
                , relativeHumiditySensor2.getIntervalDifferenceThresholdUpperRh()
                , relativeHumiditySensor2.getIntervalDifferenceThresholdLowerRh()
                , relativeHumiditySensor2.getBaseDifferenceThresholdUpperRh()
                , relativeHumiditySensor2.getBaseDifferenceThresholdLowerRh()
                , relativeHumiditySensor2.getAverageValueCount()
                , relativeHumiditySensor2.getPeakToPeakCount()
                , relativeHumiditySensor2.getIntervalDifferenceCount()
                , relativeHumiditySensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onRelativeHumiditySensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor2WriteSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor2 relativeHumiditySensor2) {
        callback(relativeHumiditySensor2.getAverageValueThresholdUpperRh()
                , relativeHumiditySensor2.getAverageValueThresholdLowerRh()
                , relativeHumiditySensor2.getPeakToPeakThresholdUpperRh()
                , relativeHumiditySensor2.getPeakToPeakThresholdLowerRh()
                , relativeHumiditySensor2.getIntervalDifferenceThresholdUpperRh()
                , relativeHumiditySensor2.getIntervalDifferenceThresholdLowerRh()
                , relativeHumiditySensor2.getBaseDifferenceThresholdUpperRh()
                , relativeHumiditySensor2.getBaseDifferenceThresholdLowerRh()
                , relativeHumiditySensor2.getAverageValueCount()
                , relativeHumiditySensor2.getPeakToPeakCount()
                , relativeHumiditySensor2.getIntervalDifferenceCount()
                , relativeHumiditySensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onRelativeHumiditySensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor1ReadSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor1 ambientLightSensor1) {
        callback(ambientLightSensor1.getEventEnableDisable()
                , ambientLightSensor1.getSimpleThresholdUpperLimit1Lx()
                , ambientLightSensor1.getSimpleThresholdUpperLimit2Lx()
                , ambientLightSensor1.getSimpleThresholdLowerLimit1Lx()
                , ambientLightSensor1.getSimpleThresholdLowerLimit2Lx()
                , ambientLightSensor1.getChangeThresholdRise1Lx()
                , ambientLightSensor1.getChangeThresholdRise2Lx()
                , ambientLightSensor1.getChangeThresholdDecline1Lx()
                , ambientLightSensor1.getChangeThresholdDecline2Lx()
        );
    }

    @Override
    public void onAmbientLightSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor1WriteSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor1 ambientLightSensor1) {
        callback(ambientLightSensor1.getEventEnableDisable()
                , ambientLightSensor1.getSimpleThresholdUpperLimit1Lx()
                , ambientLightSensor1.getSimpleThresholdUpperLimit2Lx()
                , ambientLightSensor1.getSimpleThresholdLowerLimit1Lx()
                , ambientLightSensor1.getSimpleThresholdLowerLimit2Lx()
                , ambientLightSensor1.getChangeThresholdRise1Lx()
                , ambientLightSensor1.getChangeThresholdRise2Lx()
                , ambientLightSensor1.getChangeThresholdDecline1Lx()
                , ambientLightSensor1.getChangeThresholdDecline2Lx()
        );
    }

    @Override
    public void onAmbientLightSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor2ReadSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor2 ambientLightSensor2) {
        callback(ambientLightSensor2.getAverageValueThresholdUpperLx()
                , ambientLightSensor2.getAverageValueThresholdLowerLx()
                , ambientLightSensor2.getPeakToPeakThresholdUpperLx()
                , ambientLightSensor2.getPeakToPeakThresholdLowerLx()
                , ambientLightSensor2.getIntervalDifferenceThresholdUpperLx()
                , ambientLightSensor2.getIntervalDifferenceThresholdLowerLx()
                , ambientLightSensor2.getBaseDifferenceThresholdUpperLx()
                , ambientLightSensor2.getBaseDifferenceThresholdLowerLx()
                , ambientLightSensor2.getAverageValueCount()
                , ambientLightSensor2.getPeakToPeakCount()
                , ambientLightSensor2.getIntervalDifferenceCount()
                , ambientLightSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onAmbientLightSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor2WriteSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor2 ambientLightSensor2) {
        callback(ambientLightSensor2.getAverageValueThresholdUpperLx()
                , ambientLightSensor2.getAverageValueThresholdLowerLx()
                , ambientLightSensor2.getPeakToPeakThresholdUpperLx()
                , ambientLightSensor2.getPeakToPeakThresholdLowerLx()
                , ambientLightSensor2.getIntervalDifferenceThresholdUpperLx()
                , ambientLightSensor2.getIntervalDifferenceThresholdLowerLx()
                , ambientLightSensor2.getBaseDifferenceThresholdUpperLx()
                , ambientLightSensor2.getBaseDifferenceThresholdLowerLx()
                , ambientLightSensor2.getAverageValueCount()
                , ambientLightSensor2.getPeakToPeakCount()
                , ambientLightSensor2.getIntervalDifferenceCount()
                , ambientLightSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onAmbientLightSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor1ReadSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor1 barometricPressureSensor1) {
        callback(barometricPressureSensor1.getEventEnableDisable()
                , barometricPressureSensor1.getSimpleThresholdUpperLimit1Hpa()
                , barometricPressureSensor1.getSimpleThresholdUpperLimit2Hpa()
                , barometricPressureSensor1.getSimpleThresholdLowerLimit1Hpa()
                , barometricPressureSensor1.getSimpleThresholdLowerLimit2Hpa()
                , barometricPressureSensor1.getChangeThresholdRise1Hpa()
                , barometricPressureSensor1.getChangeThresholdRise2Hpa()
                , barometricPressureSensor1.getChangeThresholdDecline1Hpa()
                , barometricPressureSensor1.getChangeThresholdDecline2Hpa()
        );
    }

    @Override
    public void onBarometricPressureSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor1WriteSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor1 barometricPressureSensor1) {
        callback(barometricPressureSensor1.getEventEnableDisable()
                , barometricPressureSensor1.getSimpleThresholdUpperLimit1Hpa()
                , barometricPressureSensor1.getSimpleThresholdUpperLimit2Hpa()
                , barometricPressureSensor1.getSimpleThresholdLowerLimit1Hpa()
                , barometricPressureSensor1.getSimpleThresholdLowerLimit2Hpa()
                , barometricPressureSensor1.getChangeThresholdRise1Hpa()
                , barometricPressureSensor1.getChangeThresholdRise2Hpa()
                , barometricPressureSensor1.getChangeThresholdDecline1Hpa()
                , barometricPressureSensor1.getChangeThresholdDecline2Hpa()
        );
    }

    @Override
    public void onBarometricPressureSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor2ReadSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor2 barometricPressureSensor2) {
        callback(barometricPressureSensor2.getAverageValueThresholdUpperHpa()
                , barometricPressureSensor2.getAverageValueThresholdLowerHpa()
                , barometricPressureSensor2.getPeakToPeakThresholdUpperHpa()
                , barometricPressureSensor2.getPeakToPeakThresholdLowerHpa()
                , barometricPressureSensor2.getIntervalDifferenceThresholdUpperHpa()
                , barometricPressureSensor2.getIntervalDifferenceThresholdLowerHpa()
                , barometricPressureSensor2.getBaseDifferenceThresholdUpperHpa()
                , barometricPressureSensor2.getBaseDifferenceThresholdLowerHpa()
                , barometricPressureSensor2.getAverageValueCount()
                , barometricPressureSensor2.getPeakToPeakCount()
                , barometricPressureSensor2.getIntervalDifferenceCount()
                , barometricPressureSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onBarometricPressureSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor2WriteSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor2 barometricPressureSensor2) {
        callback(barometricPressureSensor2.getAverageValueThresholdUpperHpa()
                , barometricPressureSensor2.getAverageValueThresholdLowerHpa()
                , barometricPressureSensor2.getPeakToPeakThresholdUpperHpa()
                , barometricPressureSensor2.getPeakToPeakThresholdLowerHpa()
                , barometricPressureSensor2.getIntervalDifferenceThresholdUpperHpa()
                , barometricPressureSensor2.getIntervalDifferenceThresholdLowerHpa()
                , barometricPressureSensor2.getBaseDifferenceThresholdUpperHpa()
                , barometricPressureSensor2.getBaseDifferenceThresholdLowerHpa()
                , barometricPressureSensor2.getAverageValueCount()
                , barometricPressureSensor2.getPeakToPeakCount()
                , barometricPressureSensor2.getIntervalDifferenceCount()
                , barometricPressureSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onBarometricPressureSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor1ReadSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor1 soundNoiseSensor1) {
        callback(soundNoiseSensor1.getEventEnableDisable()
                , soundNoiseSensor1.getSimpleThresholdUpperLimit1Db()
                , soundNoiseSensor1.getSimpleThresholdUpperLimit2Db()
                , soundNoiseSensor1.getSimpleThresholdLowerLimit1Db()
                , soundNoiseSensor1.getSimpleThresholdLowerLimit2Db()
                , soundNoiseSensor1.getChangeThresholdRise1Db()
                , soundNoiseSensor1.getChangeThresholdRise2Db()
                , soundNoiseSensor1.getChangeThresholdDecline1Db()
                , soundNoiseSensor1.getChangeThresholdDecline2Db()
        );
    }

    @Override
    public void onSoundNoiseSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor1WriteSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor1 soundNoiseSensor1) {
        callback(soundNoiseSensor1.getEventEnableDisable()
                , soundNoiseSensor1.getSimpleThresholdUpperLimit1Db()
                , soundNoiseSensor1.getSimpleThresholdUpperLimit2Db()
                , soundNoiseSensor1.getSimpleThresholdLowerLimit1Db()
                , soundNoiseSensor1.getSimpleThresholdLowerLimit2Db()
                , soundNoiseSensor1.getChangeThresholdRise1Db()
                , soundNoiseSensor1.getChangeThresholdRise2Db()
                , soundNoiseSensor1.getChangeThresholdDecline1Db()
                , soundNoiseSensor1.getChangeThresholdDecline2Db()
        );
    }

    @Override
    public void onSoundNoiseSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor2ReadSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor2 soundNoiseSensor2) {
        callback(soundNoiseSensor2.getAverageValueThresholdUpperDb()
                , soundNoiseSensor2.getAverageValueThresholdLowerDb()
                , soundNoiseSensor2.getPeakToPeakThresholdUpperDb()
                , soundNoiseSensor2.getPeakToPeakThresholdLowerDb()
                , soundNoiseSensor2.getIntervalDifferenceThresholdUpperDb()
                , soundNoiseSensor2.getIntervalDifferenceThresholdLowerDb()
                , soundNoiseSensor2.getBaseDifferenceThresholdUpperDb()
                , soundNoiseSensor2.getBaseDifferenceThresholdLowerDb()
                , soundNoiseSensor2.getAverageValueCount()
                , soundNoiseSensor2.getPeakToPeakCount()
                , soundNoiseSensor2.getIntervalDifferenceCount()
                , soundNoiseSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onSoundNoiseSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor2WriteSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor2 soundNoiseSensor2) {
        callback(soundNoiseSensor2.getAverageValueThresholdUpperDb()
                , soundNoiseSensor2.getAverageValueThresholdLowerDb()
                , soundNoiseSensor2.getPeakToPeakThresholdUpperDb()
                , soundNoiseSensor2.getPeakToPeakThresholdLowerDb()
                , soundNoiseSensor2.getIntervalDifferenceThresholdUpperDb()
                , soundNoiseSensor2.getIntervalDifferenceThresholdLowerDb()
                , soundNoiseSensor2.getBaseDifferenceThresholdUpperDb()
                , soundNoiseSensor2.getBaseDifferenceThresholdLowerDb()
                , soundNoiseSensor2.getAverageValueCount()
                , soundNoiseSensor2.getPeakToPeakCount()
                , soundNoiseSensor2.getIntervalDifferenceCount()
                , soundNoiseSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onSoundNoiseSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor1ReadSuccess(BluetoothDevice bluetoothDevice, EtvocSensor1 etvocSensor1) {
        callback(etvocSensor1.getEventEnableDisable()
                , etvocSensor1.getSimpleThresholdUpperLimit1Ppb()
                , etvocSensor1.getSimpleThresholdUpperLimit2Ppb()
                , etvocSensor1.getSimpleThresholdLowerLimit1Ppb()
                , etvocSensor1.getSimpleThresholdLowerLimit2Ppb()
                , etvocSensor1.getChangeThresholdRise1Ppb()
                , etvocSensor1.getChangeThresholdRise2Ppb()
                , etvocSensor1.getChangeThresholdDecline1Ppb()
                , etvocSensor1.getChangeThresholdDecline2Ppb()
        );
    }

    @Override
    public void onEtvocSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEtvocSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor1WriteSuccess(BluetoothDevice bluetoothDevice, EtvocSensor1 etvocSensor1) {
        callback(etvocSensor1.getEventEnableDisable()
                , etvocSensor1.getSimpleThresholdUpperLimit1Ppb()
                , etvocSensor1.getSimpleThresholdUpperLimit2Ppb()
                , etvocSensor1.getSimpleThresholdLowerLimit1Ppb()
                , etvocSensor1.getSimpleThresholdLowerLimit2Ppb()
                , etvocSensor1.getChangeThresholdRise1Ppb()
                , etvocSensor1.getChangeThresholdRise2Ppb()
                , etvocSensor1.getChangeThresholdDecline1Ppb()
                , etvocSensor1.getChangeThresholdDecline2Ppb()
        );
    }

    @Override
    public void onEtvocSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEtvocSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor2ReadSuccess(BluetoothDevice bluetoothDevice, EtvocSensor2 etvocSensor2) {
        callback(etvocSensor2.getAverageValueThresholdUpperPpb()
                , etvocSensor2.getAverageValueThresholdLowerPpb()
                , etvocSensor2.getPeakToPeakThresholdUpperPpb()
                , etvocSensor2.getPeakToPeakThresholdLowerPpb()
                , etvocSensor2.getIntervalDifferenceThresholdUpperPpb()
                , etvocSensor2.getIntervalDifferenceThresholdLowerPpb()
                , etvocSensor2.getBaseDifferenceThresholdUpperPpb()
                , etvocSensor2.getBaseDifferenceThresholdLowerPpb()
                , etvocSensor2.getAverageValueCount()
                , etvocSensor2.getPeakToPeakCount()
                , etvocSensor2.getIntervalDifferenceCount()
                , etvocSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onEtvocSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEtvocSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor2WriteSuccess(BluetoothDevice bluetoothDevice, EtvocSensor2 etvocSensor2) {
        callback(etvocSensor2.getAverageValueThresholdUpperPpb()
                , etvocSensor2.getAverageValueThresholdLowerPpb()
                , etvocSensor2.getPeakToPeakThresholdUpperPpb()
                , etvocSensor2.getPeakToPeakThresholdLowerPpb()
                , etvocSensor2.getIntervalDifferenceThresholdUpperPpb()
                , etvocSensor2.getIntervalDifferenceThresholdLowerPpb()
                , etvocSensor2.getBaseDifferenceThresholdUpperPpb()
                , etvocSensor2.getBaseDifferenceThresholdLowerPpb()
                , etvocSensor2.getAverageValueCount()
                , etvocSensor2.getPeakToPeakCount()
                , etvocSensor2.getIntervalDifferenceCount()
                , etvocSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onEtvocSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEtvocSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor1ReadSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor1 eco2Sensor1) {
        callback(eco2Sensor1.getEventEnableDisable()
                , eco2Sensor1.getSimpleThresholdUpperLimit1Ppm()
                , eco2Sensor1.getSimpleThresholdUpperLimit2Ppm()
                , eco2Sensor1.getSimpleThresholdLowerLimit1Ppm()
                , eco2Sensor1.getSimpleThresholdLowerLimit2Ppm()
                , eco2Sensor1.getChangeThresholdRise1Ppm()
                , eco2Sensor1.getChangeThresholdRise2Ppm()
                , eco2Sensor1.getChangeThresholdDecline1Ppm()
                , eco2Sensor1.getChangeThresholdDecline2Ppm()
        );
    }

    @Override
    public void onEco2Sensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEco2Sensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor1WriteSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor1 eco2Sensor1) {
        callback(eco2Sensor1.getEventEnableDisable()
                , eco2Sensor1.getSimpleThresholdUpperLimit1Ppm()
                , eco2Sensor1.getSimpleThresholdUpperLimit2Ppm()
                , eco2Sensor1.getSimpleThresholdLowerLimit1Ppm()
                , eco2Sensor1.getSimpleThresholdLowerLimit2Ppm()
                , eco2Sensor1.getChangeThresholdRise1Ppm()
                , eco2Sensor1.getChangeThresholdRise2Ppm()
                , eco2Sensor1.getChangeThresholdDecline1Ppm()
                , eco2Sensor1.getChangeThresholdDecline2Ppm()
        );
    }

    @Override
    public void onEco2Sensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEco2Sensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor2ReadSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor2 eco2Sensor2) {
        callback(eco2Sensor2.getAverageValueThresholdUpperPpm()
                , eco2Sensor2.getAverageValueThresholdLowerPpm()
                , eco2Sensor2.getPeakToPeakThresholdUpperPpm()
                , eco2Sensor2.getPeakToPeakThresholdLowerPpm()
                , eco2Sensor2.getIntervalDifferenceThresholdUpperPpm()
                , eco2Sensor2.getIntervalDifferenceThresholdLowerPpm()
                , eco2Sensor2.getBaseDifferenceThresholdUpperPpm()
                , eco2Sensor2.getBaseDifferenceThresholdLowerPpm()
                , eco2Sensor2.getAverageValueCount()
                , eco2Sensor2.getPeakToPeakCount()
                , eco2Sensor2.getIntervalDifferenceCount()
                , eco2Sensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onEco2Sensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEco2Sensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor2WriteSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor2 eco2Sensor2) {
        callback(eco2Sensor2.getAverageValueThresholdUpperPpm()
                , eco2Sensor2.getAverageValueThresholdLowerPpm()
                , eco2Sensor2.getPeakToPeakThresholdUpperPpm()
                , eco2Sensor2.getPeakToPeakThresholdLowerPpm()
                , eco2Sensor2.getIntervalDifferenceThresholdUpperPpm()
                , eco2Sensor2.getIntervalDifferenceThresholdLowerPpm()
                , eco2Sensor2.getBaseDifferenceThresholdUpperPpm()
                , eco2Sensor2.getBaseDifferenceThresholdLowerPpm()
                , eco2Sensor2.getAverageValueCount()
                , eco2Sensor2.getPeakToPeakCount()
                , eco2Sensor2.getIntervalDifferenceCount()
                , eco2Sensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onEco2Sensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onEco2Sensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor1ReadSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor1 discomfortIndexSensor1) {
        callback(discomfortIndexSensor1.getEventEnableDisable()
                , discomfortIndexSensor1.getSimpleThresholdUpperLimit1WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdUpperLimit2WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdLowerLimit1WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdLowerLimit2WithUnit()
                , discomfortIndexSensor1.getChangeThresholdRise1WithUnit()
                , discomfortIndexSensor1.getChangeThresholdRise2WithUnit()
                , discomfortIndexSensor1.getChangeThresholdDecline1WithUnit()
                , discomfortIndexSensor1.getChangeThresholdDecline2WithUnit()
        );
    }

    @Override
    public void onDiscomfortIndexSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor1WriteSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor1 discomfortIndexSensor1) {
        callback(discomfortIndexSensor1.getEventEnableDisable()
                , discomfortIndexSensor1.getSimpleThresholdUpperLimit1WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdUpperLimit2WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdLowerLimit1WithUnit()
                , discomfortIndexSensor1.getSimpleThresholdLowerLimit2WithUnit()
                , discomfortIndexSensor1.getChangeThresholdRise1WithUnit()
                , discomfortIndexSensor1.getChangeThresholdRise2WithUnit()
                , discomfortIndexSensor1.getChangeThresholdDecline1WithUnit()
                , discomfortIndexSensor1.getChangeThresholdDecline2WithUnit()
        );
    }

    @Override
    public void onDiscomfortIndexSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor2ReadSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor2 discomfortIndexSensor2) {
        callback(discomfortIndexSensor2.getAverageValueThresholdUpperWithUnit()
                , discomfortIndexSensor2.getAverageValueThresholdLowerWithUnit()
                , discomfortIndexSensor2.getPeakToPeakThresholdUpperWithUnit()
                , discomfortIndexSensor2.getPeakToPeakThresholdLowerWithUnit()
                , discomfortIndexSensor2.getIntervalDifferenceThresholdUpperWithUnit()
                , discomfortIndexSensor2.getIntervalDifferenceThresholdLowerWithUnit()
                , discomfortIndexSensor2.getBaseDifferenceThresholdUpperWithUnit()
                , discomfortIndexSensor2.getBaseDifferenceThresholdLowerWithUnit()
                , discomfortIndexSensor2.getAverageValueCount()
                , discomfortIndexSensor2.getPeakToPeakCount()
                , discomfortIndexSensor2.getIntervalDifferenceCount()
                , discomfortIndexSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onDiscomfortIndexSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor2WriteSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor2 discomfortIndexSensor2) {
        callback(discomfortIndexSensor2.getAverageValueThresholdUpperWithUnit()
                , discomfortIndexSensor2.getAverageValueThresholdLowerWithUnit()
                , discomfortIndexSensor2.getPeakToPeakThresholdUpperWithUnit()
                , discomfortIndexSensor2.getPeakToPeakThresholdLowerWithUnit()
                , discomfortIndexSensor2.getIntervalDifferenceThresholdUpperWithUnit()
                , discomfortIndexSensor2.getIntervalDifferenceThresholdLowerWithUnit()
                , discomfortIndexSensor2.getBaseDifferenceThresholdUpperWithUnit()
                , discomfortIndexSensor2.getBaseDifferenceThresholdLowerWithUnit()
                , discomfortIndexSensor2.getAverageValueCount()
                , discomfortIndexSensor2.getPeakToPeakCount()
                , discomfortIndexSensor2.getIntervalDifferenceCount()
                , discomfortIndexSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onDiscomfortIndexSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor1ReadSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor1 heatStrokeSensor1) {
        callback(heatStrokeSensor1.getEventEnableDisable()
                , heatStrokeSensor1.getSimpleThresholdUpperLimit1DegC()
                , heatStrokeSensor1.getSimpleThresholdUpperLimit2DegC()
                , heatStrokeSensor1.getSimpleThresholdLowerLimit1DegC()
                , heatStrokeSensor1.getSimpleThresholdLowerLimit2DegC()
                , heatStrokeSensor1.getChangeThresholdRise1DegC()
                , heatStrokeSensor1.getChangeThresholdRise2DegC()
                , heatStrokeSensor1.getChangeThresholdDecline1DegC()
                , heatStrokeSensor1.getChangeThresholdDecline2DegC()
        );
    }

    @Override
    public void onHeatStrokeSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor1WriteSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor1 heatStrokeSensor1) {
        callback(heatStrokeSensor1.getEventEnableDisable()
                , heatStrokeSensor1.getSimpleThresholdUpperLimit1DegC()
                , heatStrokeSensor1.getSimpleThresholdUpperLimit2DegC()
                , heatStrokeSensor1.getSimpleThresholdLowerLimit1DegC()
                , heatStrokeSensor1.getSimpleThresholdLowerLimit2DegC()
                , heatStrokeSensor1.getChangeThresholdRise1DegC()
                , heatStrokeSensor1.getChangeThresholdRise2DegC()
                , heatStrokeSensor1.getChangeThresholdDecline1DegC()
                , heatStrokeSensor1.getChangeThresholdDecline2DegC()
        );
    }

    @Override
    public void onHeatStrokeSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor2ReadSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor2 heatStrokeSensor2) {
        callback(heatStrokeSensor2.getAverageValueThresholdUpperDegC()
                , heatStrokeSensor2.getAverageValueThresholdLowerDegC()
                , heatStrokeSensor2.getPeakToPeakThresholdUpperDegC()
                , heatStrokeSensor2.getPeakToPeakThresholdLowerDegC()
                , heatStrokeSensor2.getIntervalDifferenceThresholdUpperDegC()
                , heatStrokeSensor2.getIntervalDifferenceThresholdLowerDegC()
                , heatStrokeSensor2.getBaseDifferenceThresholdUpperDegC()
                , heatStrokeSensor2.getBaseDifferenceThresholdLowerDegC()
                , heatStrokeSensor2.getAverageValueCount()
                , heatStrokeSensor2.getPeakToPeakCount()
                , heatStrokeSensor2.getIntervalDifferenceCount()
                , heatStrokeSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onHeatStrokeSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor2WriteSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor2 heatStrokeSensor2) {
        callback(heatStrokeSensor2.getAverageValueThresholdUpperDegC()
                , heatStrokeSensor2.getAverageValueThresholdLowerDegC()
                , heatStrokeSensor2.getPeakToPeakThresholdUpperDegC()
                , heatStrokeSensor2.getPeakToPeakThresholdLowerDegC()
                , heatStrokeSensor2.getIntervalDifferenceThresholdUpperDegC()
                , heatStrokeSensor2.getIntervalDifferenceThresholdLowerDegC()
                , heatStrokeSensor2.getBaseDifferenceThresholdUpperDegC()
                , heatStrokeSensor2.getBaseDifferenceThresholdLowerDegC()
                , heatStrokeSensor2.getAverageValueCount()
                , heatStrokeSensor2.getPeakToPeakCount()
                , heatStrokeSensor2.getIntervalDifferenceCount()
                , heatStrokeSensor2.getBaseDifferenceCount()
        );
    }

    @Override
    public void onHeatStrokeSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSiValueAccelerationReadSuccess(BluetoothDevice bluetoothDevice, SiValueAcceleration siValueAcceleration) {
        callback(siValueAcceleration.getEventEnableDisable()
                , siValueAcceleration.getSimpleThresholdUpperLimit1Kine()
                , siValueAcceleration.getSimpleThresholdUpperLimit2Kine()
                , siValueAcceleration.getChangeThresholdRise1Kine()
                , siValueAcceleration.getChangeThresholdRise2Kine()
        );
    }

    @Override
    public void onSiValueAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSiValueAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSiValueAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, SiValueAcceleration siValueAcceleration) {
        callback(siValueAcceleration.getEventEnableDisable()
                , siValueAcceleration.getSimpleThresholdUpperLimit1Kine()
                , siValueAcceleration.getSimpleThresholdUpperLimit2Kine()
                , siValueAcceleration.getChangeThresholdRise1Kine()
                , siValueAcceleration.getChangeThresholdRise2Kine()
        );
    }

    @Override
    public void onSiValueAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSiValueAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onPgaAccelerationReadSuccess(BluetoothDevice bluetoothDevice, PgaAcceleration pgaAcceleration) {
        callback(pgaAcceleration.getEventEnableDisable()
                , pgaAcceleration.getSimpleThresholdUpperLimit1Gal()
                , pgaAcceleration.getSimpleThresholdUpperLimit2Gal()
                , pgaAcceleration.getChangeThresholdRise1Gal()
                , pgaAcceleration.getChangeThresholdRise2Gal()
        );
    }

    @Override
    public void onPgaAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onPgaAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onPgaAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, PgaAcceleration pgaAcceleration) {
        callback(pgaAcceleration.getEventEnableDisable()
                , pgaAcceleration.getSimpleThresholdUpperLimit1Gal()
                , pgaAcceleration.getSimpleThresholdUpperLimit2Gal()
                , pgaAcceleration.getChangeThresholdRise1Gal()
                , pgaAcceleration.getChangeThresholdRise2Gal()
        );
    }

    @Override
    public void onPgaAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onPgaAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSeismicIntensityAccelerationReadSuccess(BluetoothDevice bluetoothDevice, SeismicIntensityAcceleration seismicIntensityAcceleration) {
        callback(seismicIntensityAcceleration.getEventEnableDisable()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit1WithUnit()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit2WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise1WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise2WithUnit()
        );
    }

    @Override
    public void onSeismicIntensityAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSeismicIntensityAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSeismicIntensityAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, SeismicIntensityAcceleration seismicIntensityAcceleration) {
        callback(seismicIntensityAcceleration.getEventEnableDisable()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit1WithUnit()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit2WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise1WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise2WithUnit()
        );
    }

    @Override
    public void onSeismicIntensityAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSeismicIntensityAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onErrorStatusReadSuccess(BluetoothDevice bluetoothDevice, ErrorStatus errorStatus) {
        callback(errorStatus.getTemperatureSensorError()
                , errorStatus.getRelativeHumiditySensorError()
                , errorStatus.getAmbientLightSensorError()
                , errorStatus.getBarometricPressureSensorError()
                , errorStatus.getSoundNoiseSensorError()
                , errorStatus.getAccelerationSensorError()
                , errorStatus.getEtvocSensorError()
                , errorStatus.getCpuError()
        );
    }

    @Override
    public void onErrorStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onErrorStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onMountingOrientationReadSuccess(BluetoothDevice bluetoothDevice, MountingOrientation mountingOrientation) {
        callback(mountingOrientation.getMountingOrientation());
    }

    @Override
    public void onMountingOrientationReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onMountingOrientationReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onFlashMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, FlashMemoryStatus flashMemoryStatus) {
        callback(flashMemoryStatus.getFlashMemoryStatus());
    }

    @Override
    public void onFlashMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onFlashMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onDeviceNameReadSuccess(BluetoothDevice bluetoothDevice, DeviceName deviceName) {
        callback(deviceName.getName());
    }

    @Override
    public void onDeviceNameReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onDeviceNameReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAppearanceReadSuccess(BluetoothDevice bluetoothDevice, Appearance appearance) {
        callback(APPEARANCE_VALUE_MAP.get(appearance.getCategory()) + '(' + APPEARANCE_DESCRIPTION_MAP.get(appearance.getCategory()) + ')');
    }

    @Override
    public void onAppearanceReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onAppearanceReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadSuccess(BluetoothDevice bluetoothDevice, PeripheralPreferredConnectionParameters peripheralPreferredConnectionParameters) {
        callback(peripheralPreferredConnectionParameters.getMinimumConnectionIntervalMillis(), peripheralPreferredConnectionParameters.getMaximumConnectionIntervalMillis(), peripheralPreferredConnectionParameters.getSlaveLatency(), peripheralPreferredConnectionParameters.getConnectionSupervisionTimeoutMultiplierMillis());
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onCentralAddressResolutionReadSuccess(BluetoothDevice bluetoothDevice, CentralAddressResolution centralAddressResolution) {
        callback(centralAddressResolution.getCentralAddressResolutionSupport());
    }

    @Override
    public void onCentralAddressResolutionReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onCentralAddressResolutionReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onModelNumberStringReadSuccess(BluetoothDevice bluetoothDevice, ModelNumberString modelNumberString) {
        callback(modelNumberString.getModelNumber());
    }

    @Override
    public void onModelNumberStringReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onModelNumberStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onSerialNumberStringReadSuccess(BluetoothDevice bluetoothDevice, SerialNumberString serialNumberString) {
        callback(serialNumberString.getSerialNumber());
    }

    @Override
    public void onSerialNumberStringReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onSerialNumberStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onFirmwareRevisionStringReadSuccess(BluetoothDevice bluetoothDevice, FirmwareRevisionString firmwareRevisionString) {
        callback(firmwareRevisionString.getFirmwareRevision());
    }

    @Override
    public void onFirmwareRevisionStringReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onFirmwareRevisionStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onHardwareRevisionStringReadSuccess(BluetoothDevice bluetoothDevice, HardwareRevisionString hardwareRevisionString) {
        callback(hardwareRevisionString.getHardwareRevision());
    }

    @Override
    public void onHardwareRevisionStringReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onHardwareRevisionStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onManufacturerNameStringReadSuccess(BluetoothDevice bluetoothDevice, ManufacturerNameString manufacturerNameString) {
        callback(manufacturerNameString.getManufactureName());
    }

    @Override
    public void onManufacturerNameStringReadFailed(BluetoothDevice bluetoothDevice, int status) {
        callback(status);
    }

    @Override
    public void onManufacturerNameStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryDataHeaderNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryDataHeader accelerationMemoryDataHeader) {
        if (accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1() != null) {
            callback(
                    accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getTotalTransferCount()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getStorageTotalPage()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getEarthQuakesOrVibrationCount()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getTimeCounter()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getEarthQuakeFlag()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader1().getSiValueCalculationAxis()
            );
        }
        if (accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2() != null) {
            callback(
                    accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getTotalTransferCount()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getPageNumber()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getSiValueKine()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getPgaGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getSeismicIntensityWithUnit()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getMaximuAccelerationXAxisGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getMaximuAccelerationYAxisGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getMaximuAccelerationZAxisGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getTemparatureDegC()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader2().getRelativeHumidityRh()
            );
        }
        if (accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3() != null) {
            callback(
                    accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getTotalTransferCount()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getAmbientLightLx()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getBarometricPressureHpa()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getSoundNoiseDb()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getEtvocPpb()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getEco2Ppm()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getDiscomfortIndexWithUnit()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader3().getHeatStrokeDegC()
            );
        }
        if (accelerationMemoryDataHeader.getAccelerationMemoryDataHeader4() != null) {
            callback(
                    accelerationMemoryDataHeader.getAccelerationMemoryDataHeader4().getTotalTransferCount()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader4().getAccelerationOffsetXAxisGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader4().getAccelerationOffsetYAxisGal()
                    , accelerationMemoryDataHeader.getAccelerationMemoryDataHeader4().getAccelerationOffsetZAxisGal()
            );
        }
    }

    @Override
    public void onAccelerationMemoryDataNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryData accelerationMemoryData) {
        if (accelerationMemoryData.getAccelerationMemoryData1() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData1().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData1().getPageNumber()
                    , accelerationMemoryData.getAccelerationMemoryData1().getSiValueKine()
                    , accelerationMemoryData.getAccelerationMemoryData1().getPgaGal()
                    , accelerationMemoryData.getAccelerationMemoryData1().getSeismicIntensityWithUnit()
                    , accelerationMemoryData.getAccelerationMemoryData1().getMaximumAccelerationXAxisGal()
                    , accelerationMemoryData.getAccelerationMemoryData1().getMaximumAccelerationYAxisGal()
                    , accelerationMemoryData.getAccelerationMemoryData1().getMaximumAccelerationZAxisGal()
                    , accelerationMemoryData.getAccelerationMemoryData1().getTemperatureDegC()
                    , accelerationMemoryData.getAccelerationMemoryData1().getRelativeHumidityRh()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData2() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData2().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData2().getAmbientLightLx()
                    , accelerationMemoryData.getAccelerationMemoryData2().getBarometricPressureHpa()
                    , accelerationMemoryData.getAccelerationMemoryData2().getSoundNoiseDb()
                    , accelerationMemoryData.getAccelerationMemoryData2().getEtvocPpb()
                    , accelerationMemoryData.getAccelerationMemoryData2().getEco2Ppm()
                    , accelerationMemoryData.getAccelerationMemoryData2().getDiscomfortIndexWithUnit()
                    , accelerationMemoryData.getAccelerationMemoryData2().getHeatStrokeDegC()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData3() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData3().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData3().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData4() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData4().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData4().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData5() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData5().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData5().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData6() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData6().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData6().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData7() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData7().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData7().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData8() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData8().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData8().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData9() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData9().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData9().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData10() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData10().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData10().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData11() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData11().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData11().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData12() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData12().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationZAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationXAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationYAxis3Gal()
                    , accelerationMemoryData.getAccelerationMemoryData12().getAccelerationZAxis3Gal()
            );
        }
        if (accelerationMemoryData.getAccelerationMemoryData13() != null) {
            callback(
                    accelerationMemoryData.getAccelerationMemoryData13().getTotalTransferCount()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationXAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationYAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationZAxis1Gal()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationXAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationYAxis2Gal()
                    , accelerationMemoryData.getAccelerationMemoryData13().getAccelerationZAxis2Gal()
            );
        }
    }
}
