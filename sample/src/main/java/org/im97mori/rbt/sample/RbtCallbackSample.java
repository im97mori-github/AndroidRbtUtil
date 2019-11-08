package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.characteristic.dis.Appearance;
import org.im97mori.ble.characteristic.dis.CentralAddressResolution;
import org.im97mori.ble.characteristic.dis.DeviceName;
import org.im97mori.ble.characteristic.dis.PeripheralPreferredConnectionParameters;
import org.im97mori.ble.characteristic.gas.FirmwareRevisionString;
import org.im97mori.ble.characteristic.gas.HardwareRevisionString;
import org.im97mori.ble.characteristic.gas.ManufacturerNameString;
import org.im97mori.ble.characteristic.gas.ModelNumberString;
import org.im97mori.ble.characteristic.gas.SerialNumberString;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.rbt.ble.AbstractRbtBLECallback;
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
import org.im97mori.rbt.ble.characteristic.RequestAccelerationMemoryIndex;
import org.im97mori.rbt.ble.characteristic.RequestMemoryIndex;
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
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.im97mori.ble.BLEConstants.APPEARANCE_DESCRIPTION_MAP;
import static org.im97mori.ble.BLEConstants.APPEARANCE_VALUE_MAP;

@SuppressWarnings("unused")
public class RbtCallbackSample extends AbstractRbtBLECallback {

    private final SampleCallback mSampleCallback;
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
    public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {
        callback(memoryIndexInformation.getMemoryIndexLatest(), memoryIndexInformation.getMemoryIndexLast());
    }

    @Override
    public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {
        callback(requestMemoryIndex.getDataType()
                , requestMemoryIndex.getMemoryIndexStart()
                , requestMemoryIndex.getMemoryIndexEnd());
    }

    @Override
    public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {
        callback(memoryStatus.getStatus(), memoryStatus.getTimeCounter(), memoryStatus.getMemoryStorageInterval());
    }

    @Override
    public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {
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
    public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {
        callback(memoryCalculationData.getMemoryIndex()
                , memoryCalculationData.getDiscomfortIndexWithUnit()
                , memoryCalculationData.getHeatStrokeDegC()
                , memoryCalculationData.getVibrationInformation()
                , memoryCalculationData.getSiValueKine()
                , memoryCalculationData.getPgaGal()
                , memoryCalculationData.getSeismicIntensityWithUnit());
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {
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
    public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {
        callback(memoryCalculationFlag.getMemoryIndex()
                , memoryCalculationFlag.getDiscomfortIndexFlag()
                , memoryCalculationFlag.getHeatStrokeFlag()
                , memoryCalculationFlag.getSiValueFlag()
                , memoryCalculationFlag.getPgaFlag()
                , memoryCalculationFlag.getSeismicIntensityFlag());
    }

    @Override
    public void onLatestSensingDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData, @Nullable Bundle argument) {
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
    public void onLatestSensingDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData) {
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
    public void onLatestCalculationDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData, @Nullable Bundle argument) {
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
    public void onLatestCalculationDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData) {
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
    public void onLatestSensingFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag, @Nullable Bundle argument) {
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
    public void onLatestSensingFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestSensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestSensingFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag) {
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
    public void onLatestCalculationFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag, @Nullable Bundle argument) {
        callback(latestCalculationFlag.getSequenceNumber()
                , latestCalculationFlag.getDiscomfortIndexFlag()
                , latestCalculationFlag.getHeatStrokeFlag()
                , latestCalculationFlag.getSiValueFlag()
                , latestCalculationFlag.getPgaFlag()
                , latestCalculationFlag.getSeismicIntensityFlag()
        );
    }

    @Override
    public void onLatestCalculationFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestCalculationFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag) {
        callback(latestCalculationFlag.getSequenceNumber()
                , latestCalculationFlag.getDiscomfortIndexFlag()
                , latestCalculationFlag.getHeatStrokeFlag()
                , latestCalculationFlag.getSiValueFlag()
                , latestCalculationFlag.getPgaFlag()
                , latestCalculationFlag.getSeismicIntensityFlag()
        );
    }

    @Override
    public void onLatestAccelerationStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus, @Nullable Bundle argument) {
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
    public void onLatestAccelerationStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestAccelerationStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestAccelerationStatusNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus) {
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
    public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {
        callback(
                vibrationCount.getEarthquakeCount()
                , vibrationCount.getVibrationCount()
        );
    }

    @Override
    public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {
        callback(
                requestAccelerationMemoryIndex.getAccelerationDataType()
                , requestAccelerationMemoryIndex.getRequestAccelerationMemoryIndex()
                , requestAccelerationMemoryIndex.getRequestPageStart()
                , requestAccelerationMemoryIndex.getRequestPageEnd()
        );
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {
        callback(
                accelerationMemoryStatus.getStatus()
                , accelerationMemoryStatus.getTotalTransferCount()
        );
    }

    @Override
    public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
        callback(Arrays.toString(clientCharacteristicConfiguration.getProperties()));
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
        callback(ledSettingNormalState.getDisplayRule()
                , ledSettingNormalState.getIntensityOfLedRed()
                , ledSettingNormalState.getIntensityOfLedGreen()
                , ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
        callback(ledSettingNormalState.getDisplayRule()
                , ledSettingNormalState.getIntensityOfLedRed()
                , ledSettingNormalState.getIntensityOfLedGreen()
                , ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
        callback(ledSettingEventState.getDisplayRule()
                , ledSettingEventState.getIntensityOfLedRed()
                , ledSettingEventState.getIntensityOfLedGreen()
                , ledSettingEventState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
        callback(ledSettingEventState.getDisplayRule()
                , ledSettingEventState.getIntensityOfLedRed()
                , ledSettingEventState.getIntensityOfLedGreen()
                , ledSettingEventState.getIntensityOfLedBlue());
    }

    @Override
    public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
        callback(ledStateOperation.getStartUp()
                , ledStateOperation.getError()
                , ledStateOperation.getConnection());
    }

    @Override
    public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
        callback(ledStateOperation.getStartUp()
                , ledStateOperation.getError()
                , ledStateOperation.getConnection());
    }

    @Override
    public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
        callback(installationOffset.getInstallationOffsetEnableDisable()
                , installationOffset.getTemperatureInstallationOffsetDegC()
                , installationOffset.getRelativeHumidityInstallationOffsetRh()
                , installationOffset.getAmbientLightInstallationGainWithUnit()
                , installationOffset.getBarometricPressureInstallationOffsetHpa()
                , installationOffset.getSoundNoiseInstallationOffsetDb());
    }

    @Override
    public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
        callback(installationOffset.getInstallationOffsetEnableDisable()
                , installationOffset.getTemperatureInstallationOffsetDegC()
                , installationOffset.getRelativeHumidityInstallationOffsetRh()
                , installationOffset.getAmbientLightInstallationGainWithUnit()
                , installationOffset.getBarometricPressureInstallationOffsetHpa()
                , installationOffset.getSoundNoiseInstallationOffsetDb());
    }

    @Override
    public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
        callback(advertiseSetting.getAdvertisingIntervalMillis()
                , advertiseSetting.getAdvertisingMode());
    }

    @Override
    public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
        callback(advertiseSetting.getAdvertisingIntervalMillis()
                , advertiseSetting.getAdvertisingMode());
    }

    @Override
    public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {
        callback(memoryReset.getMemoryReset());
    }

    @Override
    public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
        callback(modeChange.getModeChange());
    }

    @Override
    public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
        callback(modeChange.getModeChange());
    }

    @Override
    public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {
        callback(accelerationLoggerControl.getLoggerCondition()
                , accelerationLoggerControl.getRangeOfDetection()
                , accelerationLoggerControl.getOdrSetting()
                , accelerationLoggerControl.getStartPage()
                , accelerationLoggerControl.getEndPage());
    }

    @Override
    public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {
        callback(accelerationLoggerStatus.getLoggerStatus()
                , accelerationLoggerStatus.getRunningPage());
    }

    @Override
    public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {
        callback(latestTimeCounter.getTimeCounter());
    }

    @Override
    public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
        callback(timeSetting.getTimeSetting());
    }

    @Override
    public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, TimeSetting timeSetting, @Nullable Bundle argument) {
        callback(timeSetting.getTimeSetting());
    }

    @Override
    public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
        callback(memoryStorageInterval.getMemoryStorageIntervalSec());
    }

    @Override
    public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
        callback(memoryStorageInterval.getMemoryStorageIntervalSec());
    }

    @Override
    public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument) {
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
    public void onTemperatureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument) {
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
    public void onTemperatureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument) {
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
    public void onTemperatureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onTemperatureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument) {
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
    public void onTemperatureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onTemperatureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument) {
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
    public void onRelativeHumiditySensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument) {
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
    public void onRelativeHumiditySensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument) {
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
    public void onRelativeHumiditySensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRelativeHumiditySensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument) {
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
    public void onRelativeHumiditySensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRelativeHumiditySensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument) {
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
    public void onAmbientLightSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument) {
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
    public void onAmbientLightSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument) {
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
    public void onAmbientLightSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAmbientLightSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument) {
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
    public void onAmbientLightSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAmbientLightSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument) {
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
    public void onBarometricPressureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument) {
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
    public void onBarometricPressureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument) {
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
    public void onBarometricPressureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onBarometricPressureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument) {
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
    public void onBarometricPressureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onBarometricPressureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument) {
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
    public void onSoundNoiseSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument) {
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
    public void onSoundNoiseSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument) {
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
    public void onSoundNoiseSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSoundNoiseSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument) {
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
    public void onSoundNoiseSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSoundNoiseSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument) {
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
    public void onEtvocSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEtvocSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument) {
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
    public void onEtvocSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEtvocSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument) {
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
    public void onEtvocSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEtvocSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEtvocSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument) {
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
    public void onEtvocSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEtvocSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument) {
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
    public void onEco2Sensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEco2Sensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument) {
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
    public void onEco2Sensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEco2Sensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument) {
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
    public void onEco2Sensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEco2Sensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onEco2Sensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument) {
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
    public void onEco2Sensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onEco2Sensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument) {
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
    public void onDiscomfortIndexSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument) {
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
    public void onDiscomfortIndexSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument) {
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
    public void onDiscomfortIndexSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onDiscomfortIndexSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument) {
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
    public void onDiscomfortIndexSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscomfortIndexSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument) {
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
    public void onHeatStrokeSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument) {
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
    public void onHeatStrokeSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument) {
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
    public void onHeatStrokeSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onHeatStrokeSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument) {
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
    public void onHeatStrokeSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onHeatStrokeSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSiValueAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument) {
        callback(siValueAcceleration.getEventEnableDisable()
                , siValueAcceleration.getSimpleThresholdUpperLimit1Kine()
                , siValueAcceleration.getSimpleThresholdUpperLimit2Kine()
                , siValueAcceleration.getChangeThresholdRise1Kine()
                , siValueAcceleration.getChangeThresholdRise2Kine()
        );
    }

    @Override
    public void onSiValueAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSiValueAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSiValueAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument) {
        callback(siValueAcceleration.getEventEnableDisable()
                , siValueAcceleration.getSimpleThresholdUpperLimit1Kine()
                , siValueAcceleration.getSimpleThresholdUpperLimit2Kine()
                , siValueAcceleration.getChangeThresholdRise1Kine()
                , siValueAcceleration.getChangeThresholdRise2Kine()
        );
    }

    @Override
    public void onSiValueAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSiValueAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onPgaAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument) {
        callback(pgaAcceleration.getEventEnableDisable()
                , pgaAcceleration.getSimpleThresholdUpperLimit1Gal()
                , pgaAcceleration.getSimpleThresholdUpperLimit2Gal()
                , pgaAcceleration.getChangeThresholdRise1Gal()
                , pgaAcceleration.getChangeThresholdRise2Gal()
        );
    }

    @Override
    public void onPgaAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onPgaAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onPgaAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument) {
        callback(pgaAcceleration.getEventEnableDisable()
                , pgaAcceleration.getSimpleThresholdUpperLimit1Gal()
                , pgaAcceleration.getSimpleThresholdUpperLimit2Gal()
                , pgaAcceleration.getChangeThresholdRise1Gal()
                , pgaAcceleration.getChangeThresholdRise2Gal()
        );
    }

    @Override
    public void onPgaAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onPgaAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSeismicIntensityAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument) {
        callback(seismicIntensityAcceleration.getEventEnableDisable()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit1WithUnit()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit2WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise1WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise2WithUnit()
        );
    }

    @Override
    public void onSeismicIntensityAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSeismicIntensityAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSeismicIntensityAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument) {
        callback(seismicIntensityAcceleration.getEventEnableDisable()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit1WithUnit()
                , seismicIntensityAcceleration.getSimpleThresholdUpperLimit2WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise1WithUnit()
                , seismicIntensityAcceleration.getChangeThresholdRise2WithUnit()
        );
    }

    @Override
    public void onSeismicIntensityAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSeismicIntensityAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onErrorStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ErrorStatus errorStatus, @Nullable Bundle argument) {
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
    public void onErrorStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onErrorStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onMountingOrientationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MountingOrientation mountingOrientation, @Nullable Bundle argument) {
        callback(mountingOrientation.getMountingOrientation());
    }

    @Override
    public void onMountingOrientationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onMountingOrientationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onFlashMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FlashMemoryStatus flashMemoryStatus, @Nullable Bundle argument) {
        callback(flashMemoryStatus.getFlashMemoryStatus());
    }

    @Override
    public void onFlashMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onFlashMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onDeviceNameReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DeviceName deviceName, @Nullable Bundle argument) {
        callback(deviceName.getName());
    }

    @Override
    public void onDeviceNameReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDeviceNameReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAppearanceReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Appearance appearance, @Nullable Bundle argument) {
        callback(APPEARANCE_VALUE_MAP.get(appearance.getCategory()) + '(' + APPEARANCE_DESCRIPTION_MAP.get(appearance.getCategory()) + ')');
    }

    @Override
    public void onAppearanceReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onAppearanceReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PeripheralPreferredConnectionParameters peripheralPreferredConnectionParameters, @Nullable Bundle argument) {
        callback(peripheralPreferredConnectionParameters.getMinimumConnectionIntervalMillis(), peripheralPreferredConnectionParameters.getMaximumConnectionIntervalMillis(), peripheralPreferredConnectionParameters.getSlaveLatency(), peripheralPreferredConnectionParameters.getConnectionSupervisionTimeoutMultiplierMillis());
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onPeripheralPreferredConnectionParametersReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onCentralAddressResolutionReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull CentralAddressResolution centralAddressResolution, @Nullable Bundle argument) {
        callback(centralAddressResolution.getCentralAddressResolutionSupport());
    }

    @Override
    public void onCentralAddressResolutionReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onCentralAddressResolutionReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onModelNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModelNumberString modelNumberString, @Nullable Bundle argument) {
        callback(modelNumberString.getModelNumber());
    }

    @Override
    public void onModelNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onModelNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSerialNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SerialNumberString serialNumberString, @Nullable Bundle argument) {
        callback(serialNumberString.getSerialNumber());
    }

    @Override
    public void onSerialNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSerialNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onFirmwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FirmwareRevisionString firmwareRevisionString, @Nullable Bundle argument) {
        callback(firmwareRevisionString.getFirmwareRevision());
    }

    @Override
    public void onFirmwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onFirmwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onHardwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HardwareRevisionString hardwareRevisionString, @Nullable Bundle argument) {
        callback(hardwareRevisionString.getHardwareRevision());
    }

    @Override
    public void onHardwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onHardwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onManufacturerNameStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ManufacturerNameString manufacturerNameString, @Nullable Bundle argument) {
        callback(manufacturerNameString.getManufactureName());
    }

    @Override
    public void onManufacturerNameStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onManufacturerNameStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {
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
    public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {
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

    @Override
    public void onBLEConnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onBLEConnectFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onBLEConnectTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback();
    }

    @Override
    public void onBLEDisconnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscoverServiceSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull List<BluetoothGattService> serviceList, @Nullable Bundle argument) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < serviceList.size(); i++) {
            BluetoothGattService service = serviceList.get(i);
            List<BluetoothGattCharacteristic> characteristicList = service.getCharacteristics();
            if (characteristicList.isEmpty()) {
                writeServiceList(service.getUuid(), 0, null, 0, null, sb);
            } else {
                for (int j = 0; j < characteristicList.size(); j++) {
                    BluetoothGattCharacteristic characteristic = characteristicList.get(j);
                    List<BluetoothGattDescriptor> descriptorList = characteristic.getDescriptors();
                    if (descriptorList.isEmpty()) {
                        writeServiceList(service.getUuid(), j, characteristic.getUuid(), 0, null, sb);
                    } else {
                        for (int k = 0; k < descriptorList.size(); k++) {
                            BluetoothGattDescriptor descriptor = descriptorList.get(k);
                            writeServiceList(service.getUuid(), j, characteristic.getUuid(), k, descriptor.getUuid(), sb);
                        }
                    }
                }
            }
        }
        callback(sb);
    }

    private void writeServiceList(@NonNull UUID serviceUUID, int characteristicLineNumber, @Nullable UUID characteristicUUID, int descriptorLineNumber, @Nullable UUID descriptorUUID, @NonNull StringBuilder sb) {
        if (characteristicLineNumber == 0) {
            sb.append(serviceUUID.toString().substring(4, 8));
        } else {
            sb.append(serviceUUID.toString().substring(4, 8));
        }
        sb.append('\t');

        if (characteristicUUID != null) {
            if (descriptorLineNumber == 0) {
                sb.append(characteristicUUID.toString().substring(4, 8));
            } else {
                sb.append(characteristicUUID.toString().substring(4, 8));
            }
            sb.append('\t');

            if (descriptorUUID != null) {
                sb.append(descriptorUUID.toString().substring(4, 8));
            }
        }
        sb.append('\n');
    }

    @Override
    public void onDiscoverServiceFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onDiscoverServiceTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onRequestMtuSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int mtu, @Nullable Bundle argument) {
        callback(mtu);
    }

    @Override
    public void onRequestMtuFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onRequestMtuTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onReadPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, @Nullable Bundle argument) {
        callback(txPhy, rxPhy);
    }

    @Override
    public void onReadPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onReadPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onSetPreferredPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, int phyOptions, @Nullable Bundle argument) {
        callback(txPhy, rxPhy, phyOptions);
    }

    @Override
    public void onSetPreferredPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onSetPreferredPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onReadRemoteRssiSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int rssi, @Nullable Bundle argument) {
        callback(rssi);
    }

    @Override
    public void onReadRemoteRssiFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status);
    }

    @Override
    public void onReadRemoteRssiTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout);
    }

    @Override
    public void onBeginReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onBeginReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onExecuteReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onExecuteReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onExecuteReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

    @Override
    public void onAbortReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {
        callback(argument);
    }

    @Override
    public void onAbortReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
        callback(status, argument);
    }

    @Override
    public void onAbortReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
        callback(timeout, argument);
    }

}
