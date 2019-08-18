package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;

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

/**
 * Rbt BLE callback
 *
 * @see org.im97mori.ble.BLECallback
 */
public interface RbtCallback {

    /**
     * Connected callback
     *
     * @param bluetoothDevice Rbt device
     * @see BluetoothGatt#connect()
     * @see android.bluetooth.BluetoothGattCallback#onConnectionStateChange(BluetoothGatt, int, int)
     * @see android.bluetooth.BluetoothGattCallback#onServicesDiscovered(BluetoothGatt, int)
     * @see org.im97mori.ble.task.ConnectTask
     */
    void onRbtConnected(BluetoothDevice bluetoothDevice);

    /**
     * Connecte task failed callback
     *
     * @param bluetoothDevice Rbt device
     * @see BluetoothGatt#connect()
     * @see org.im97mori.ble.task.ConnectTask
     */
    void onRbtConnectFailed(BluetoothDevice bluetoothDevice);

    /**
     * Connect task timeout callback
     *
     * @param bluetoothDevice Rbt device
     * @see BluetoothGatt#connect()
     * @see org.im97mori.ble.task.ConnectTask
     */
    void onRbtConnectTimeout(BluetoothDevice bluetoothDevice);

    /**
     * Disconnected callback
     *
     * @param bluetoothDevice Rbt device
     * @see BluetoothGatt#disconnect()
     * @see BluetoothGatt#close()
     * @see android.bluetooth.BluetoothGattCallback#onConnectionStateChange(BluetoothGatt, int, int)
     * @see RbtBLEConnection#quit()
     * @see org.im97mori.ble.task.DisconnectTask
     */
    void onRbtDisonnected(BluetoothDevice bluetoothDevice);

    // Memory Data Service (Service UUID: 0x5000)

    /**
     * Read success callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param bluetoothDevice        Rbt device
     * @param memoryIndexInformation read result data
     */
    void onMemoryIndexInformationReadSuccess(BluetoothDevice bluetoothDevice, MemoryIndexInformation memoryIndexInformation);

    /**
     * Read failed callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryIndexInformationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryIndexInformationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write failed callback Request memory index (Characteristics UUID: 0x5005)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRequestMemoryIndexWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Request memory index (Characteristics UUID: 0x5005)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRequestMemoryIndexWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param bluetoothDevice Rbt device
     * @param memoryStatus    read result data
     */
    void onMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, MemoryStatus memoryStatus);

    /**
     * Read failed callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onMemorySensingDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemorySensingDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemorySensingDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param bluetoothDevice   Rbt device
     * @param memorySensingData notification data
     */
    void onMemorySensingDataNotified(BluetoothDevice bluetoothDevice, MemorySensingData memorySensingData);

    /**
     * Notification setting success callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param bluetoothDevice       Rbt device
     * @param memoryCalculationData notification data
     */
    void onMemoryCalculationDataNotified(BluetoothDevice bluetoothDevice, MemoryCalculationData memoryCalculationData);

    /**
     * Notification setting success callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onMemorySensingFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemorySensingFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemorySensingFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param bluetoothDevice   Rbt device
     * @param memorySensingFlag notification data
     */
    void onMemorySensingFlagNotified(BluetoothDevice bluetoothDevice, MemorySensingFlag memorySensingFlag);

    /**
     * Notification setting success callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param bluetoothDevice       Rbt device
     * @param memoryCalculationFlag notification data
     */
    void onMemoryCalculationFlagNotified(BluetoothDevice bluetoothDevice, MemoryCalculationFlag memoryCalculationFlag);

    // Latest Data Service (Service UUID: 0x5010)

    /**
     * Read success callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingData read result data
     */
    void onLatestSensingDataReadSuccess(BluetoothDevice bluetoothDevice, LatestSensingData latestSensingData);

    /**
     * Read failed callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestSensingDataReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestSensingDataReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onLatestSensingDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestSensingDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestSensingDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingData notification data
     */
    void onLatestSensingDataNotified(BluetoothDevice bluetoothDevice, LatestSensingData latestSensingData);

    /**
     * Read success callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationData read result data
     */
    void onLatestCalculationDataReadSuccess(BluetoothDevice bluetoothDevice, LatestCalculationData latestCalculationData);

    /**
     * Read failed callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestCalculationDataReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestCalculationDataReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onLatestCalculationDataClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestCalculationDataClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestCalculationDataClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationData notification data
     */
    void onLatestCalculationDataNotified(BluetoothDevice bluetoothDevice, LatestCalculationData latestCalculationData);

    /**
     * Read success callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingFlag read result data
     */
    void onLatestSensingFlagReadSuccess(BluetoothDevice bluetoothDevice, LatestSensingFlag latestSensingFlag);

    /**
     * Read failed callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestSensingFlagReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestSensingFlagReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onLatestSensingFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestSensingFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestSensingFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingFlag notification data
     */
    void onLatestSensingFlagNotified(BluetoothDevice bluetoothDevice, LatestSensingFlag latestSensingFlag);

    /**
     * Read success callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationFlag read result data
     */
    void onLatestCalculationFlagReadSuccess(BluetoothDevice bluetoothDevice, LatestCalculationFlag latestCalculationFlag);

    /**
     * Read failed callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestCalculationFlagReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestCalculationFlagReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationFlag notification data
     */
    void onLatestCalculationFlagNotified(BluetoothDevice bluetoothDevice, LatestCalculationFlag latestCalculationFlag);

    /**
     * Read success callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice          Rbt device
     * @param latestAccelerationStatus read result data
     */
    void onLatestAccelerationStatusReadSuccess(BluetoothDevice bluetoothDevice, LatestAccelerationStatus latestAccelerationStatus);

    /**
     * Read failed callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestAccelerationStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestAccelerationStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification setting success callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationSuccess(BluetoothDevice bluetoothDevice, ClientCharacteristicConfiguration clientCharacteristicConfiguration);

    /**
     * Notification setting failed callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Notification setting timeout callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice          Rbt device
     * @param latestAccelerationStatus notification data
     */
    void onLatestAccelerationStatusNotified(BluetoothDevice bluetoothDevice, LatestAccelerationStatus latestAccelerationStatus);

    // 2.3. Acceleration Service (Service UUID: 0x5030)

    /**
     * Read success callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param bluetoothDevice Rbt device
     * @param vibrationCount  read result data
     */
    void onVibrationCountReadSuccess(BluetoothDevice bluetoothDevice, VibrationCount vibrationCount);

    /**
     * Read failed callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onVibrationCountReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onVibrationCountReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write failed callback Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRequestAccelerationMemoryIndexWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRequestAccelerationMemoryIndexWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param bluetoothDevice          Rbt device
     * @param accelerationMemoryStatus read result data
     */
    void onAccelerationMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, AccelerationMemoryStatus accelerationMemoryStatus);

    /**
     * Read failed callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAccelerationMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAccelerationMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    // Control Service (Service UUID: 0x5110)

    /**
     * Read success callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice       Rbt device
     * @param ledSettingNormalState read result data
     */
    void onLedSettingNormalStateReadSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState);

    /**
     * Read failed callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedSettingNormalStateReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedSettingNormalStateReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice       Rbt device
     * @param ledSettingNormalState write data
     */
    void onLedSettingNormalStateWriteSuccess(BluetoothDevice bluetoothDevice, LedSettingNormalState ledSettingNormalState);

    /**
     * Write failed callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedSettingNormalStateWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedSettingNormalStateWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice      Rbt device
     * @param ledSettingEventState read result data
     */
    void onLedSettingEventStateReadSuccess(BluetoothDevice bluetoothDevice, LedSettingEventState ledSettingEventState);

    /**
     * Read failed callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedSettingEventStateReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedSettingEventStateReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice      Rbt device
     * @param ledSettingEventState write data
     */
    void onLedSettingEventStateWriteSuccess(BluetoothDevice bluetoothDevice, LedSettingEventState ledSettingEventState);

    /**
     * Write failed callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedSettingEventStateWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedSettingEventStateWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice   Rbt device
     * @param ledStateOperation read result data
     */
    void onLedStateOperationReadSuccess(BluetoothDevice bluetoothDevice, LedStateOperation ledStateOperation);

    /**
     * Read failed callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedStateOperationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedStateOperationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice   Rbt device
     * @param ledStateOperation write data
     */
    void onLedStateOperationWriteSuccess(BluetoothDevice bluetoothDevice, LedStateOperation ledStateOperation);

    /**
     * Write failed callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLedStateOperationWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLedStateOperationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice    Rbt device
     * @param installationOffset read result data
     */
    void onInstallationOffsetReadSuccess(BluetoothDevice bluetoothDevice, InstallationOffset installationOffset);

    /**
     * Read failed callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onInstallationOffsetReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onInstallationOffsetReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice    Rbt device
     * @param installationOffset write data
     */
    void onInstallationOffsetWriteSuccess(BluetoothDevice bluetoothDevice, InstallationOffset installationOffset);

    /**
     * Write failed callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onInstallationOffsetWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onInstallationOffsetWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice  Rbt device
     * @param advertiseSetting read result data
     */
    void onAdvertiseSettingReadSuccess(BluetoothDevice bluetoothDevice, AdvertiseSetting advertiseSetting);

    /**
     * Read failed callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAdvertiseSettingReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAdvertiseSettingReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice  Rbt device
     * @param advertiseSetting write data
     */
    void onAdvertiseSettingWriteSuccess(BluetoothDevice bluetoothDevice, AdvertiseSetting advertiseSetting);

    /**
     * Write failed callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAdvertiseSettingWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAdvertiseSettingWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param bluetoothDevice Rbt device
     * @param memoryReset     write data
     */
    void onMemoryResetWriteSuccess(BluetoothDevice bluetoothDevice, MemoryReset memoryReset);

    /**
     * Write failed callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryResetWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryResetWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param modeChange      read result data
     */
    void onModeChangeReadSuccess(BluetoothDevice bluetoothDevice, ModeChange modeChange);

    /**
     * Read failed callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onModeChangeReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onModeChangeReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param modeChange      write data
     */
    void onModeChangeWriteSuccess(BluetoothDevice bluetoothDevice, ModeChange modeChange);

    /**
     * Write failed callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onModeChangeWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onModeChangeWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param bluetoothDevice           Rbt device
     * @param accelerationLoggerControl write data
     */
    void onAccelerationLoggerControlWriteSuccess(BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl);

    /**
     * Write failed callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAccelerationLoggerControlWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAccelerationLoggerControlWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param bluetoothDevice          Rbt device
     * @param accelerationLoggerStatus read result data
     */
    void onAccelerationLoggerStatusReadSuccess(BluetoothDevice bluetoothDevice, AccelerationLoggerStatus accelerationLoggerStatus);

    /**
     * Read failed callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAccelerationLoggerStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAccelerationLoggerStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    // Time Setting Service (Service UUID: 0x5200)

    /**
     * Read success callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestTimeCounter read result data
     */
    void onLatestTimeCounterReadSuccess(BluetoothDevice bluetoothDevice, LatestTimeCounter latestTimeCounter);

    /**
     * Read failed callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onLatestTimeCounterReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onLatestTimeCounterReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param timeSetting     read result data
     */
    void onTimeSettingReadSuccess(BluetoothDevice bluetoothDevice, TimeSetting timeSetting);

    /**
     * Read failed callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTimeSettingReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTimeSettingReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param timeSetting     write data
     */
    void onTimeSettingWriteSuccess(BluetoothDevice bluetoothDevice, TimeSetting timeSetting);

    /**
     * Write failed callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTimeSettingWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTimeSettingWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice       Rbt device
     * @param memoryStorageInterval read result data
     */
    void onMemoryStorageIntervalReadSuccess(BluetoothDevice bluetoothDevice, MemoryStorageInterval memoryStorageInterval);

    /**
     * Read failed callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryStorageIntervalReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryStorageIntervalReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice       Rbt device
     * @param memoryStorageInterval write data
     */
    void onMemoryStorageIntervalWriteSuccess(BluetoothDevice bluetoothDevice, MemoryStorageInterval memoryStorageInterval);

    /**
     * Write failed callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryStorageIntervalWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMemoryStorageIntervalWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    // Event Setting Service (Service UUID: 0x5210)

    /**
     * Read success callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor1 read result data
     */
    void onTemperatureSensor1ReadSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor1 temperatureSensor1);

    /**
     * Read failed callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTemperatureSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTemperatureSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor1 write data
     */
    void onTemperatureSensor1WriteSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor1 temperatureSensor1);

    /**
     * Write failed callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTemperatureSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTemperatureSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor2 read result data
     */
    void onTemperatureSensor2ReadSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor2 temperatureSensor2);

    /**
     * Read failed callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTemperatureSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTemperatureSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor2 write data
     */
    void onTemperatureSensor2WriteSuccess(BluetoothDevice bluetoothDevice, TemperatureSensor2 temperatureSensor2);

    /**
     * Write failed callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onTemperatureSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onTemperatureSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor1 read result data
     */
    void onRelativeHumiditySensor1ReadSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor1 relativeHumiditySensor1);

    /**
     * Read failed callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRelativeHumiditySensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRelativeHumiditySensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor1 write data
     */
    void onRelativeHumiditySensor1WriteSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor1 relativeHumiditySensor1);

    /**
     * Write failed callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRelativeHumiditySensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRelativeHumiditySensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor2 read result data
     */
    void onRelativeHumiditySensor2ReadSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor2 relativeHumiditySensor2);

    /**
     * Read failed callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRelativeHumiditySensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRelativeHumiditySensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor2 write data
     */
    void onRelativeHumiditySensor2WriteSuccess(BluetoothDevice bluetoothDevice, RelativeHumiditySensor2 relativeHumiditySensor2);

    /**
     * Write failed callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onRelativeHumiditySensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onRelativeHumiditySensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor1 read result data
     */
    void onAmbientLightSensor1ReadSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor1 ambientLightSensor1);

    /**
     * Read failed callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAmbientLightSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAmbientLightSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor1 write data
     */
    void onAmbientLightSensor1WriteSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor1 ambientLightSensor1);

    /**
     * Write failed callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAmbientLightSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAmbientLightSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor2 read result data
     */
    void onAmbientLightSensor2ReadSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor2 ambientLightSensor2);

    /**
     * Read failed callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAmbientLightSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAmbientLightSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor2 write data
     */
    void onAmbientLightSensor2WriteSuccess(BluetoothDevice bluetoothDevice, AmbientLightSensor2 ambientLightSensor2);

    /**
     * Write failed callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAmbientLightSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAmbientLightSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor1 read result data
     */
    void onBarometricPressureSensor1ReadSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor1 barometricPressureSensor1);

    /**
     * Read failed callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onBarometricPressureSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onBarometricPressureSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor1 write data
     */
    void onBarometricPressureSensor1WriteSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor1 barometricPressureSensor1);

    /**
     * Write failed callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onBarometricPressureSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onBarometricPressureSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor2 read result data
     */
    void onBarometricPressureSensor2ReadSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor2 barometricPressureSensor2);

    /**
     * Read failed callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onBarometricPressureSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onBarometricPressureSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor2 write data
     */
    void onBarometricPressureSensor2WriteSuccess(BluetoothDevice bluetoothDevice, BarometricPressureSensor2 barometricPressureSensor2);

    /**
     * Write failed callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onBarometricPressureSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onBarometricPressureSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor1 read result data
     */
    void onSoundNoiseSensor1ReadSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor1 soundNoiseSensor1);

    /**
     * Read failed callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSoundNoiseSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSoundNoiseSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor1 write data
     */
    void onSoundNoiseSensor1WriteSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor1 soundNoiseSensor1);

    /**
     * Write failed callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSoundNoiseSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSoundNoiseSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor2 read result data
     */
    void onSoundNoiseSensor2ReadSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor2 soundNoiseSensor2);

    /**
     * Read failed callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSoundNoiseSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSoundNoiseSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor2 write data
     */
    void onSoundNoiseSensor2WriteSuccess(BluetoothDevice bluetoothDevice, SoundNoiseSensor2 soundNoiseSensor2);

    /**
     * Write failed callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSoundNoiseSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSoundNoiseSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param etvocSensor1    read result data
     */
    void onEtvocSensor1ReadSuccess(BluetoothDevice bluetoothDevice, EtvocSensor1 etvocSensor1);

    /**
     * Read failed callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEtvocSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEtvocSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param etvocSensor1    write data
     */
    void onEtvocSensor1WriteSuccess(BluetoothDevice bluetoothDevice, EtvocSensor1 etvocSensor1);

    /**
     * Write failed callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEtvocSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEtvocSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param etvocSensor2    read result data
     */
    void onEtvocSensor2ReadSuccess(BluetoothDevice bluetoothDevice, EtvocSensor2 etvocSensor2);

    /**
     * Read failed callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEtvocSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEtvocSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param etvocSensor2    write data
     */
    void onEtvocSensor2WriteSuccess(BluetoothDevice bluetoothDevice, EtvocSensor2 etvocSensor2);

    /**
     * Write failed callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEtvocSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEtvocSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor1     read result data
     */
    void onEco2Sensor1ReadSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor1 eco2Sensor1);

    /**
     * Read failed callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEco2Sensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEco2Sensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor1     write data
     */
    void onEco2Sensor1WriteSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor1 eco2Sensor1);

    /**
     * Write failed callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEco2Sensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEco2Sensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor2     read result data
     */
    void onEco2Sensor2ReadSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor2 eco2Sensor2);

    /**
     * Read failed callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEco2Sensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEco2Sensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor2     write data
     */
    void onEco2Sensor2WriteSuccess(BluetoothDevice bluetoothDevice, Eco2Sensor2 eco2Sensor2);

    /**
     * Write failed callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onEco2Sensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onEco2Sensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor1 read result data
     */
    void onDiscomfortIndexSensor1ReadSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor1 discomfortIndexSensor1);

    /**
     * Read failed callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onDiscomfortIndexSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onDiscomfortIndexSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor1 write data
     */
    void onDiscomfortIndexSensor1WriteSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor1 discomfortIndexSensor1);

    /**
     * Write failed callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onDiscomfortIndexSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onDiscomfortIndexSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor2 read result data
     */
    void onDiscomfortIndexSensor2ReadSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor2 discomfortIndexSensor2);

    /**
     * Read failed callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onDiscomfortIndexSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onDiscomfortIndexSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor2 write data
     */
    void onDiscomfortIndexSensor2WriteSuccess(BluetoothDevice bluetoothDevice, DiscomfortIndexSensor2 discomfortIndexSensor2);

    /**
     * Write failed callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onDiscomfortIndexSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onDiscomfortIndexSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor1 read result data
     */
    void onHeatStrokeSensor1ReadSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor1 heatStrokeSensor1);

    /**
     * Read failed callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onHeatStrokeSensor1ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onHeatStrokeSensor1ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor1 write data
     */
    void onHeatStrokeSensor1WriteSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor1 heatStrokeSensor1);

    /**
     * Write failed callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onHeatStrokeSensor1WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onHeatStrokeSensor1WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor2 read result data
     */
    void onHeatStrokeSensor2ReadSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor2 heatStrokeSensor2);

    /**
     * Read failed callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onHeatStrokeSensor2ReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onHeatStrokeSensor2ReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor2 write data
     */
    void onHeatStrokeSensor2WriteSuccess(BluetoothDevice bluetoothDevice, HeatStrokeSensor2 heatStrokeSensor2);

    /**
     * Write failed callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onHeatStrokeSensor2WriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onHeatStrokeSensor2WriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice     Rbt device
     * @param siValueAcceleration read result data
     */
    void onSiValueAccelerationReadSuccess(BluetoothDevice bluetoothDevice, SiValueAcceleration siValueAcceleration);

    /**
     * Read failed callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSiValueAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSiValueAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice     Rbt device
     * @param siValueAcceleration write data
     */
    void onSiValueAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, SiValueAcceleration siValueAcceleration);

    /**
     * Write failed callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSiValueAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSiValueAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param pgaAcceleration read result data
     */
    void onPgaAccelerationReadSuccess(BluetoothDevice bluetoothDevice, PgaAcceleration pgaAcceleration);

    /**
     * Read failed callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onPgaAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onPgaAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param pgaAcceleration write data
     */
    void onPgaAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, PgaAcceleration pgaAcceleration);

    /**
     * Write failed callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onPgaAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onPgaAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice              Rbt device
     * @param seismicIntensityAcceleration read result data
     */
    void onSeismicIntensityAccelerationReadSuccess(BluetoothDevice bluetoothDevice, SeismicIntensityAcceleration seismicIntensityAcceleration);

    /**
     * Read failed callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSeismicIntensityAccelerationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSeismicIntensityAccelerationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Write success callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice              Rbt device
     * @param seismicIntensityAcceleration write data
     */
    void onSeismicIntensityAccelerationWriteSuccess(BluetoothDevice bluetoothDevice, SeismicIntensityAcceleration seismicIntensityAcceleration);

    /**
     * Write failed callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSeismicIntensityAccelerationWriteFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Write timeout callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSeismicIntensityAccelerationWriteTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Error status (Characteristics UUID: 0x5401)
     *
     * @param bluetoothDevice Rbt device
     * @param errorStatus     read result data
     */
    void onErrorStatusReadSuccess(BluetoothDevice bluetoothDevice, ErrorStatus errorStatus);

    /**
     * Read failed callback Error status (Characteristics UUID: 0x5401)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onErrorStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Error status (Characteristics UUID: 0x5401)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onErrorStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param bluetoothDevice     Rbt device
     * @param mountingOrientation read result data
     */
    void onMountingOrientationReadSuccess(BluetoothDevice bluetoothDevice, MountingOrientation mountingOrientation);

    /**
     * Read failed callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMountingOrientationReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onMountingOrientationReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param bluetoothDevice   Rbt device
     * @param flashMemoryStatus read result data
     */
    void onFlashMemoryStatusReadSuccess(BluetoothDevice bluetoothDevice, FlashMemoryStatus flashMemoryStatus);

    /**
     * Read failed callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onFlashMemoryStatusReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onFlashMemoryStatusReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param bluetoothDevice Rbt device
     * @param deviceName      read result data
     */
    void onDeviceNameReadSuccess(BluetoothDevice bluetoothDevice, DeviceName deviceName);

    /**
     * Read failed callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onDeviceNameReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onDeviceNameReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param bluetoothDevice Rbt device
     * @param appearance      read result data
     */
    void onAppearanceReadSuccess(BluetoothDevice bluetoothDevice, Appearance appearance);

    /**
     * Read failed callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onAppearanceReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onAppearanceReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param bluetoothDevice                         Rbt device
     * @param peripheralPreferredConnectionParameters read result data
     */
    void onPeripheralPreferredConnectionParametersReadSuccess(BluetoothDevice bluetoothDevice, PeripheralPreferredConnectionParameters peripheralPreferredConnectionParameters);

    /**
     * Read failed callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onPeripheralPreferredConnectionParametersReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onPeripheralPreferredConnectionParametersReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param bluetoothDevice          Rbt device
     * @param centralAddressResolution read result data
     */
    void onCentralAddressResolutionReadSuccess(BluetoothDevice bluetoothDevice, CentralAddressResolution centralAddressResolution);

    /**
     * Read failed callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onCentralAddressResolutionReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onCentralAddressResolutionReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param bluetoothDevice   Rbt device
     * @param modelNumberString read result data
     */
    void onModelNumberStringReadSuccess(BluetoothDevice bluetoothDevice, ModelNumberString modelNumberString);

    /**
     * Read failed callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onModelNumberStringReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onModelNumberStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param bluetoothDevice    Rbt device
     * @param serialNumberString read result data
     */
    void onSerialNumberStringReadSuccess(BluetoothDevice bluetoothDevice, SerialNumberString serialNumberString);

    /**
     * Read failed callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onSerialNumberStringReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onSerialNumberStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param bluetoothDevice        Rbt device
     * @param firmwareRevisionString read result data
     */
    void onFirmwareRevisionStringReadSuccess(BluetoothDevice bluetoothDevice, FirmwareRevisionString firmwareRevisionString);

    /**
     * Read failed callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onFirmwareRevisionStringReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onFirmwareRevisionStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param bluetoothDevice        Rbt device
     * @param hardwareRevisionString read result data
     */
    void onHardwareRevisionStringReadSuccess(BluetoothDevice bluetoothDevice, HardwareRevisionString hardwareRevisionString);

    /**
     * Read failed callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onHardwareRevisionStringReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onHardwareRevisionStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Read success callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param bluetoothDevice        Rbt device
     * @param manufacturerNameString read result data
     */
    void onManufacturerNameStringReadSuccess(BluetoothDevice bluetoothDevice, ManufacturerNameString manufacturerNameString);

    /**
     * Read failed callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onManufacturerNameStringReadFailed(BluetoothDevice bluetoothDevice, int status);

    /**
     * Read timeout callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onManufacturerNameStringReadTimeout(BluetoothDevice bluetoothDevice, long timeout);

    /**
     * Notification callback Acceleration memory data [Header] (Characteristics UUID: 0x5034)
     *
     * @param bluetoothDevice              Rbt device
     * @param accelerationMemoryDataHeader notification data
     */
    void onAccelerationMemoryDataHeaderNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryDataHeader accelerationMemoryDataHeader);

    /**
     * Notification callback Acceleration memory data [Data] (Characteristics UUID: 0x5034)
     *
     * @param bluetoothDevice        Rbt device
     * @param accelerationMemoryData notification data
     */
    void onAccelerationMemoryDataNotified(BluetoothDevice bluetoothDevice, AccelerationMemoryData accelerationMemoryData);

}
