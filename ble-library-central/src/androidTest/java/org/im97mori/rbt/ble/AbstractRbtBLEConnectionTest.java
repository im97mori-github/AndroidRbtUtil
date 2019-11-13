package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.core.app.ApplicationProvider;

import org.im97mori.ble.characteristic.dis.FirmwareRevisionString;
import org.im97mori.ble.characteristic.dis.HardwareRevisionString;
import org.im97mori.ble.characteristic.dis.ManufacturerNameString;
import org.im97mori.ble.characteristic.dis.ModelNumberString;
import org.im97mori.ble.characteristic.dis.SerialNumberString;
import org.im97mori.ble.characteristic.gas.Appearance;
import org.im97mori.ble.characteristic.gas.CentralAddressResolution;
import org.im97mori.ble.characteristic.gas.DeviceName;
import org.im97mori.ble.characteristic.gas.PeripheralPreferredConnectionParameters;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.ble.task.AbstractBLETask;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryStatus;
import org.im97mori.rbt.ble.characteristic.as.RequestAccelerationMemoryIndex;
import org.im97mori.rbt.ble.characteristic.as.VibrationCount;
import org.im97mori.rbt.ble.characteristic.cs.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.cs.AccelerationLoggerStatus;
import org.im97mori.rbt.ble.characteristic.cs.AdvertiseSetting;
import org.im97mori.rbt.ble.characteristic.cs.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.cs.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.cs.MemoryReset;
import org.im97mori.rbt.ble.characteristic.cs.ModeChange;
import org.im97mori.rbt.ble.characteristic.ess.AmbientLightSensor1;
import org.im97mori.rbt.ble.characteristic.ess.AmbientLightSensor2;
import org.im97mori.rbt.ble.characteristic.ess.BarometricPressureSensor1;
import org.im97mori.rbt.ble.characteristic.ess.BarometricPressureSensor2;
import org.im97mori.rbt.ble.characteristic.ess.DiscomfortIndexSensor1;
import org.im97mori.rbt.ble.characteristic.ess.DiscomfortIndexSensor2;
import org.im97mori.rbt.ble.characteristic.ess.Eco2Sensor1;
import org.im97mori.rbt.ble.characteristic.ess.Eco2Sensor2;
import org.im97mori.rbt.ble.characteristic.ess.EtvocSensor1;
import org.im97mori.rbt.ble.characteristic.ess.EtvocSensor2;
import org.im97mori.rbt.ble.characteristic.ess.HeatStrokeSensor1;
import org.im97mori.rbt.ble.characteristic.ess.HeatStrokeSensor2;
import org.im97mori.rbt.ble.characteristic.ess.PgaAcceleration;
import org.im97mori.rbt.ble.characteristic.ess.RelativeHumiditySensor1;
import org.im97mori.rbt.ble.characteristic.ess.RelativeHumiditySensor2;
import org.im97mori.rbt.ble.characteristic.ess.SeismicIntensityAcceleration;
import org.im97mori.rbt.ble.characteristic.ess.SiValueAcceleration;
import org.im97mori.rbt.ble.characteristic.ess.SoundNoiseSensor1;
import org.im97mori.rbt.ble.characteristic.ess.SoundNoiseSensor2;
import org.im97mori.rbt.ble.characteristic.ess.TemperatureSensor1;
import org.im97mori.rbt.ble.characteristic.ess.TemperatureSensor2;
import org.im97mori.rbt.ble.characteristic.is.ErrorStatus;
import org.im97mori.rbt.ble.characteristic.is.FlashMemoryStatus;
import org.im97mori.rbt.ble.characteristic.is.MountingOrientation;
import org.im97mori.rbt.ble.characteristic.lds.LatestAccelerationStatus;
import org.im97mori.rbt.ble.characteristic.lds.LatestCalculationData;
import org.im97mori.rbt.ble.characteristic.lds.LatestCalculationFlag;
import org.im97mori.rbt.ble.characteristic.lds.LatestSensingData;
import org.im97mori.rbt.ble.characteristic.lds.LatestSensingFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemoryIndexInformation;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemoryStatus;
import org.im97mori.rbt.ble.characteristic.mds.RequestMemoryIndex;
import org.im97mori.rbt.ble.characteristic.tss.LatestTimeCounter;
import org.im97mori.rbt.ble.characteristic.tss.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.tss.TimeSetting;
import org.junit.BeforeClass;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractRbtBLEConnectionTest {

    private static final long SLEEP_DURATION = 50;
    protected static final BluetoothDevice MOCK_DEVICE = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");

    protected static final class MockBLEConnection extends RbtBLEConnection {
        private MockBLEConnection() {
            super(ApplicationProvider.getApplicationContext(), MOCK_DEVICE);
            this.start();
        }
    }

    protected static class BaseBLECallback extends AbstractRbtBLECallback {

        AtomicBoolean result = new AtomicBoolean(false);

        @Override
        public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData) {

        }

        @Override
        public void onLatestCalculationDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData) {

        }

        @Override
        public void onLatestSensingFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestSensingFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag) {

        }

        @Override
        public void onLatestCalculationFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestCalculationFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag) {

        }

        @Override
        public void onLatestAccelerationStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestAccelerationStatusNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus) {

        }

        @Override
        public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {

        }

        @Override
        public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, TimeSetting timeSetting, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onTemperatureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRelativeHumiditySensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAmbientLightSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onBarometricPressureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSoundNoiseSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEtvocSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onEco2Sensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscomfortIndexSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onHeatStrokeSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSiValueAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onPgaAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSeismicIntensityAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onErrorStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ErrorStatus errorStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onErrorStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onErrorStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMountingOrientationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MountingOrientation mountingOrientation, @Nullable Bundle argument) {

        }

        @Override
        public void onMountingOrientationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onMountingOrientationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onFlashMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FlashMemoryStatus flashMemoryStatus, @Nullable Bundle argument) {

        }

        @Override
        public void onFlashMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onFlashMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onDeviceNameReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DeviceName deviceName, @Nullable Bundle argument) {

        }

        @Override
        public void onDeviceNameReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDeviceNameReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAppearanceReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Appearance appearance, @Nullable Bundle argument) {

        }

        @Override
        public void onAppearanceReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAppearanceReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onPeripheralPreferredConnectionParametersReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PeripheralPreferredConnectionParameters peripheralPreferredConnectionParameters, @Nullable Bundle argument) {

        }

        @Override
        public void onPeripheralPreferredConnectionParametersReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onPeripheralPreferredConnectionParametersReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onCentralAddressResolutionReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull CentralAddressResolution centralAddressResolution, @Nullable Bundle argument) {

        }

        @Override
        public void onCentralAddressResolutionReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onCentralAddressResolutionReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onModelNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModelNumberString modelNumberString, @Nullable Bundle argument) {

        }

        @Override
        public void onModelNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onModelNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSerialNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SerialNumberString serialNumberString, @Nullable Bundle argument) {

        }

        @Override
        public void onSerialNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSerialNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onFirmwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FirmwareRevisionString firmwareRevisionString, @Nullable Bundle argument) {

        }

        @Override
        public void onFirmwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onFirmwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onHardwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HardwareRevisionString hardwareRevisionString, @Nullable Bundle argument) {

        }

        @Override
        public void onHardwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onHardwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onManufacturerNameStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ManufacturerNameString manufacturerNameString, @Nullable Bundle argument) {

        }

        @Override
        public void onManufacturerNameStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onManufacturerNameStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBLEConnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {

        }

        @Override
        public void onBLEConnectFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onBLEConnectTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {

        }

        @Override
        public void onBLEDisconnected(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscoverServiceSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull List<BluetoothGattService> serviceList, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscoverServiceFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onDiscoverServiceTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMtuSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int mtu, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMtuFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMtuTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onReadPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, @Nullable Bundle argument) {

        }

        @Override
        public void onReadPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onReadPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onSetPreferredPhySuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int txPhy, int rxPhy, int phyOptions, @Nullable Bundle argument) {

        }

        @Override
        public void onSetPreferredPhyFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onSetPreferredPhyTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onReadRemoteRssiSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int rssi, @Nullable Bundle argument) {

        }

        @Override
        public void onReadRemoteRssiFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onReadRemoteRssiTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onBeginReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {

        }

        @Override
        public void onBeginReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onExecuteReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {

        }

        @Override
        public void onExecuteReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onExecuteReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAbortReliableWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @Nullable Bundle argument) {

        }

        @Override
        public void onAbortReliableWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onAbortReliableWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {

        }

        @Override
        public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {

        }

        @Override
        public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {

        }

        @Override
        public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {

        }

        @Override
        public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {

        }
    }

    protected static abstract class MockBLETask extends AbstractBLETask {

        AtomicBoolean isProccesing = new AtomicBoolean(true);

        @Override
        @NonNull
        public Message createInitialMessage() {
            return new Message();
        }

        @Override
        public boolean isBusy() {
            return false;
        }

        @Override
        public void cancel() {

        }
    }

    protected static MockBLEConnection MOCK_BLE_CONNECTION = new MockBLEConnection();

    protected void check(BaseBLECallback firstCallback, BaseBLECallback secondCallback, MockBLETask task, boolean secondResult) {

        try {
            MOCK_BLE_CONNECTION.attach(firstCallback);
            MOCK_BLE_CONNECTION.attach(secondCallback);

            MOCK_BLE_CONNECTION.addTask(task);

            while (task.isProccesing.get()) {
                try {
                    Thread.sleep(SLEEP_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            assertTrue(firstCallback.result.get());
            assertEquals(secondResult, secondCallback.result.get());
        } finally {
            MOCK_BLE_CONNECTION.detach(firstCallback);
            MOCK_BLE_CONNECTION.detach(secondCallback);
        }
    }

    @BeforeClass
    public static void setup() {
        RbtLogUtils.verbose();
    }
}
