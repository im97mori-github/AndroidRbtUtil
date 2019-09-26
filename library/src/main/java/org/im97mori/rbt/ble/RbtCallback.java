package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
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
import org.im97mori.rbt.ble.characteristic.MemoryIndexInformation;
import org.im97mori.rbt.ble.characteristic.MemoryReset;
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
 */
@SuppressWarnings("unused")
public interface RbtCallback extends BLECallback {

    // Memory Data Service (Service UUID: 0x5000)

    /**
     * Read success callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param memoryIndexInformation read result data
     * @param argument               callback argument
     */
    void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument);

    /**
     * Read failed callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Memory index information (Characteristics UUID: 0x5004)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param memoryStatus    read result data
     * @param argument        callback argument
     */
    void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument);

    /**
     * Read failed callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Memory status (Characteristics UUID: 0x5006)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    // Latest Data Service (Service UUID: 0x5010)

    /**
     * Read success callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param latestSensingData read result data
     * @param argument          callback argument
     */
    void onLatestSensingDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData, @Nullable Bundle argument);

    /**
     * Read failed callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingData notification data
     */
    void onLatestSensingDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingData latestSensingData);

    /**
     * Read success callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationData read result data
     * @param argument              callback argument
     */
    void onLatestCalculationDataReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData, @Nullable Bundle argument);

    /**
     * Read failed callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationDataReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationDataReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationData notification data
     */
    void onLatestCalculationDataNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationData latestCalculationData);

    /**
     * Read success callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param latestSensingFlag read result data
     * @param argument          callback argument
     */
    void onLatestSensingFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag, @Nullable Bundle argument);

    /**
     * Read failed callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestSensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Latest calculation data (Characteristics UUID: 0x5014)
     *
     * @param bluetoothDevice   Rbt device
     * @param latestSensingFlag notification data
     */
    void onLatestSensingFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestSensingFlag latestSensingFlag);

    /**
     * Read success callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationFlag read result data
     * @param argument              callback argument
     */
    void onLatestCalculationFlagReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag, @Nullable Bundle argument);

    /**
     * Read failed callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationFlagReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationFlagReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param bluetoothDevice       Rbt device
     * @param latestCalculationFlag notification data
     */
    void onLatestCalculationFlagNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestCalculationFlag latestCalculationFlag);

    /**
     * Read success callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId                   task id
     * @param bluetoothDevice          Rbt device
     * @param latestAccelerationStatus read result data
     * @param argument                 callback argument
     */
    void onLatestAccelerationStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus, @Nullable Bundle argument);

    /**
     * Read failed callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestAccelerationStatusClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param bluetoothDevice          Rbt device
     * @param latestAccelerationStatus notification data
     */
    void onLatestAccelerationStatusNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull LatestAccelerationStatus latestAccelerationStatus);

    // 2.3. Acceleration Service (Service UUID: 0x5030)

    /**
     * Read success callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param vibrationCount  read result data
     * @param argument        callback argument
     */
    void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument);

    /**
     * Read failed callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Vibration count (Characteristics UUID: 0x5031)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param taskId                   task id
     * @param bluetoothDevice          Rbt device
     * @param accelerationMemoryStatus read result data
     * @param argument                 callback argument
     */
    void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument);

    /**
     * Read failed callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting read success callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting read failed callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting read timeout callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification setting write success callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId                            task id
     * @param bluetoothDevice                   Rbt device
     * @param clientCharacteristicConfiguration notification setting data
     * @param argument                          callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument);

    /**
     * Notification setting write failed callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Notification setting write timeout callback Acceleration memory data (Characteristics UUID: 0x5034)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    // Control Service (Service UUID: 0x5110)

    /**
     * Read success callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param ledSettingNormalState read result data
     * @param argument              callback argument
     */
    void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument);

    /**
     * Read failed callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param ledSettingNormalState write data
     * @param argument              callback argument
     */
    void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument);

    /**
     * Write failed callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId               task id
     * @param bluetoothDevice      Rbt device
     * @param ledSettingEventState read result data
     * @param argument             callback argument
     */
    void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument);

    /**
     * Read failed callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId               task id
     * @param bluetoothDevice      Rbt device
     * @param ledSettingEventState write data
     * @param argument             callback argument
     */
    void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument);

    /**
     * Write failed callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param ledStateOperation read result data
     * @param argument          callback argument
     */
    void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument);

    /**
     * Read failed callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param ledStateOperation write data
     * @param argument          callback argument
     */
    void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument);

    /**
     * Write failed callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param installationOffset read result data
     * @param argument           callback argument
     */
    void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument);

    /**
     * Read failed callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param installationOffset write data
     * @param argument           callback argument
     */
    void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument);

    /**
     * Write failed callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Installation offset (Characteristics UUID: 0x5114)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId           task id
     * @param bluetoothDevice  Rbt device
     * @param advertiseSetting read result data
     * @param argument         callback argument
     */
    void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument);

    /**
     * Read failed callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId           task id
     * @param bluetoothDevice  Rbt device
     * @param advertiseSetting write data
     * @param argument         callback argument
     */
    void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument);

    /**
     * Write failed callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param memoryReset     write data
     * @param argument        callback argument
     */
    void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument);

    /**
     * Write failed callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Memory reset (Characteristics UUID: 0x5116)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param modeChange      read result data
     * @param argument        callback argument
     */
    void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument);

    /**
     * Read failed callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param modeChange      write data
     * @param argument        callback argument
     */
    void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument);

    /**
     * Write failed callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Mode change (Characteristics UUID: 0x5117)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param taskId                    task id
     * @param bluetoothDevice           Rbt device
     * @param accelerationLoggerControl write data
     * @param argument                  callback argument
     */
    void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument);

    /**
     * Write failed callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param taskId                   task id
     * @param bluetoothDevice          Rbt device
     * @param accelerationLoggerStatus read result data
     * @param argument                 callback argument
     */
    void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument);

    /**
     * Read failed callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    // Time Setting Service (Service UUID: 0x5200)

    /**
     * Read success callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param latestTimeCounter read result data
     * @param argument          callback argument
     */
    void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument);

    /**
     * Read failed callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeSetting     read result data
     * @param argument        callback argument
     */
    void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument);

    /**
     * Read failed callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeSetting     write data
     * @param argument        callback argument
     */
    void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, TimeSetting timeSetting, @Nullable Bundle argument);

    /**
     * Write failed callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Time setting (Characteristics UUID: 0x5202)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param memoryStorageInterval read result data
     * @param argument              callback argument
     */
    void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument);

    /**
     * Read failed callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param memoryStorageInterval write data
     * @param argument              callback argument
     */
    void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument);

    /**
     * Write failed callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    // Event Setting Service (Service UUID: 0x5210)

    /**
     * Read success callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor1 read result data
     * @param argument           callback argument
     */
    void onTemperatureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTemperatureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTemperatureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor1 write data
     * @param argument           callback argument
     */
    void onTemperatureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor1 temperatureSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTemperatureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Temperature 1 (Characteristics UUID: 0x5211)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTemperatureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor2 read result data
     * @param argument           callback argument
     */
    void onTemperatureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTemperatureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTemperatureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param temperatureSensor2 write data
     * @param argument           callback argument
     */
    void onTemperatureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TemperatureSensor2 temperatureSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onTemperatureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Temperature 2 (Characteristics UUID: 0x5212)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onTemperatureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId                  task id
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor1 read result data
     * @param argument                callback argument
     */
    void onRelativeHumiditySensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId                  task id
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor1 write data
     * @param argument                callback argument
     */
    void onRelativeHumiditySensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor1 relativeHumiditySensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Relative humidity 1 (Characteristics UUID: 0x5213)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId                  task id
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor2 read result data
     * @param argument                callback argument
     */
    void onRelativeHumiditySensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId                  task id
     * @param bluetoothDevice         Rbt device
     * @param relativeHumiditySensor2 write data
     * @param argument                callback argument
     */
    void onRelativeHumiditySensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RelativeHumiditySensor2 relativeHumiditySensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Relative humidity 2 (Characteristics UUID: 0x5214)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRelativeHumiditySensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor1 read result data
     * @param argument            callback argument
     */
    void onAmbientLightSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAmbientLightSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAmbientLightSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor1 write data
     * @param argument            callback argument
     */
    void onAmbientLightSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor1 ambientLightSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAmbientLightSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Ambient light 1 (Characteristics UUID: 0x5215)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAmbientLightSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor2 read result data
     * @param argument            callback argument
     */
    void onAmbientLightSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAmbientLightSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAmbientLightSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param ambientLightSensor2 write data
     * @param argument            callback argument
     */
    void onAmbientLightSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AmbientLightSensor2 ambientLightSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAmbientLightSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Ambient light 2 (Characteristics UUID: 0x5216)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAmbientLightSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId                    task id
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor1 read result data
     * @param argument                  callback argument
     */
    void onBarometricPressureSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onBarometricPressureSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onBarometricPressureSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId                    task id
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor1 write data
     * @param argument                  callback argument
     */
    void onBarometricPressureSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor1 barometricPressureSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onBarometricPressureSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Barometric pressure 1 (Characteristics UUID: 0x5217)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onBarometricPressureSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param taskId                    task id
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor2 read result data
     * @param argument                  callback argument
     */
    void onBarometricPressureSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onBarometricPressureSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onBarometricPressureSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param taskId                    task id
     * @param bluetoothDevice           Rbt device
     * @param barometricPressureSensor2 write data
     * @param argument                  callback argument
     */
    void onBarometricPressureSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull BarometricPressureSensor2 barometricPressureSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onBarometricPressureSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Barometric pressure 2 (Characteristics UUID: 0x5218)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     */
    void onBarometricPressureSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor1 read result data
     * @param argument          callback argument
     */
    void onSoundNoiseSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSoundNoiseSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSoundNoiseSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor1 write data
     * @param argument          callback argument
     */
    void onSoundNoiseSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor1 soundNoiseSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSoundNoiseSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Sound noise 1 (Characteristics UUID: 0x5219)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSoundNoiseSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor2 read result data
     * @param argument          callback argument
     */
    void onSoundNoiseSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSoundNoiseSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSoundNoiseSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param soundNoiseSensor2 write data
     * @param argument          callback argument
     */
    void onSoundNoiseSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SoundNoiseSensor2 soundNoiseSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSoundNoiseSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Sound noise 2 (Characteristics UUID: 0x521A)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSoundNoiseSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param etvocSensor1    read result data
     * @param argument        callback argument
     */
    void onEtvocSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEtvocSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEtvocSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param etvocSensor1    write data
     * @param argument        callback argument
     */
    void onEtvocSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor1 etvocSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEtvocSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEtvocSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param etvocSensor2    read result data
     * @param argument        callback argument
     */
    void onEtvocSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEtvocSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEtvocSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param etvocSensor2    write data
     * @param argument        callback argument
     */
    void onEtvocSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull EtvocSensor2 etvocSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEtvocSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern eTVOC 2 (Characteristics UUID: 0x521C)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEtvocSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor1     read result data
     * @param argument        callback argument
     */
    void onEco2Sensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEco2Sensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEco2Sensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor1     write data
     * @param argument        callback argument
     */
    void onEco2Sensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor1 eco2Sensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEco2Sensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern eCO2 1 (Characteristics UUID: 0x521D)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEco2Sensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor2     read result data
     * @param argument        callback argument
     */
    void onEco2Sensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEco2Sensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEco2Sensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param eco2Sensor2     write data
     * @param argument        callback argument
     */
    void onEco2Sensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Eco2Sensor2 eco2Sensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onEco2Sensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern eCO2 2 (Characteristics UUID: 0x521E)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onEco2Sensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor1 read result data
     * @param argument               callback argument
     */
    void onDiscomfortIndexSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor1 write data
     * @param argument               callback argument
     */
    void onDiscomfortIndexSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor1 discomfortIndexSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Discomfort index 1 (Characteristics UUID: 0x521F)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor2 read result data
     * @param argument               callback argument
     */
    void onDiscomfortIndexSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param discomfortIndexSensor2 write data
     * @param argument               callback argument
     */
    void onDiscomfortIndexSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DiscomfortIndexSensor2 discomfortIndexSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Discomfort index 2 (Characteristics UUID: 0x5220)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onDiscomfortIndexSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor1 read result data
     * @param argument          callback argument
     */
    void onHeatStrokeSensor1ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onHeatStrokeSensor1ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onHeatStrokeSensor1ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor1 write data
     * @param argument          callback argument
     */
    void onHeatStrokeSensor1WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor1 heatStrokeSensor1, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onHeatStrokeSensor1WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Heat stroke 1 (Characteristics UUID: 0x5221)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onHeatStrokeSensor1WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor2 read result data
     * @param argument          callback argument
     */
    void onHeatStrokeSensor2ReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onHeatStrokeSensor2ReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onHeatStrokeSensor2ReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param heatStrokeSensor2 write data
     * @param argument          callback argument
     */
    void onHeatStrokeSensor2WriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HeatStrokeSensor2 heatStrokeSensor2, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onHeatStrokeSensor2WriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onHeatStrokeSensor2WriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param siValueAcceleration read result data
     * @param argument            callback argument
     */
    void onSiValueAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSiValueAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSiValueAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param siValueAcceleration write data
     * @param argument            callback argument
     */
    void onSiValueAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SiValueAcceleration siValueAcceleration, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSiValueAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern SI value (Characteristics UUID: 0x5226)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSiValueAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param pgaAcceleration read result data
     * @param argument        callback argument
     */
    void onPgaAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onPgaAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onPgaAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param pgaAcceleration write data
     * @param argument        callback argument
     */
    void onPgaAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PgaAcceleration pgaAcceleration, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onPgaAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern PGA (Characteristics UUID: 0x5227)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onPgaAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId                       task id
     * @param bluetoothDevice              Rbt device
     * @param seismicIntensityAcceleration read result data
     * @param argument                     callback argument
     */
    void onSeismicIntensityAccelerationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument);

    /**
     * Read failed callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSeismicIntensityAccelerationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSeismicIntensityAccelerationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Write success callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId                       task id
     * @param bluetoothDevice              Rbt device
     * @param seismicIntensityAcceleration write data
     * @param argument                     callback argument
     */
    void onSeismicIntensityAccelerationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SeismicIntensityAcceleration seismicIntensityAcceleration, @Nullable Bundle argument);

    /**
     * Write failed callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSeismicIntensityAccelerationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Event pattern Seismic intensity (Characteristics UUID: 0x5228)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSeismicIntensityAccelerationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Error status (Characteristics UUID: 0x5401)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param errorStatus     read result data
     * @param argument        callback argument
     */
    void onErrorStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ErrorStatus errorStatus, @Nullable Bundle argument);

    /**
     * Read failed callback Error status (Characteristics UUID: 0x5401)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onErrorStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Error status (Characteristics UUID: 0x5401)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onErrorStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param taskId              task id
     * @param bluetoothDevice     Rbt device
     * @param mountingOrientation read result data
     * @param argument            callback argument
     */
    void onMountingOrientationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MountingOrientation mountingOrientation, @Nullable Bundle argument);

    /**
     * Read failed callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onMountingOrientationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onMountingOrientationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param flashMemoryStatus read result data
     * @param argument          callback argument
     */
    void onFlashMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FlashMemoryStatus flashMemoryStatus, @Nullable Bundle argument);

    /**
     * Read failed callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onFlashMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onFlashMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param deviceName      read result data
     * @param argument        callback argument
     */
    void onDeviceNameReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull DeviceName deviceName, @Nullable Bundle argument);

    /**
     * Read failed callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onDeviceNameReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Device Name (Characteristics UUID: 0x2A00)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onDeviceNameReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param appearance      read result data
     * @param argument        callback argument
     */
    void onAppearanceReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull Appearance appearance, @Nullable Bundle argument);

    /**
     * Read failed callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onAppearanceReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Appearance (Characteristics UUID: 0x2A01)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onAppearanceReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param taskId                                  task id
     * @param bluetoothDevice                         Rbt device
     * @param peripheralPreferredConnectionParameters read result data
     * @param argument                                callback argument
     */
    void onPeripheralPreferredConnectionParametersReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull PeripheralPreferredConnectionParameters peripheralPreferredConnectionParameters, @Nullable Bundle argument);

    /**
     * Read failed callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onPeripheralPreferredConnectionParametersReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onPeripheralPreferredConnectionParametersReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param taskId                   task id
     * @param bluetoothDevice          Rbt device
     * @param centralAddressResolution read result data
     * @param argument                 callback argument
     */
    void onCentralAddressResolutionReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull CentralAddressResolution centralAddressResolution, @Nullable Bundle argument);

    /**
     * Read failed callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onCentralAddressResolutionReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onCentralAddressResolutionReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param modelNumberString read result data
     * @param argument          callback argument
     */
    void onModelNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModelNumberString modelNumberString, @Nullable Bundle argument);

    /**
     * Read failed callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onModelNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Model number string (Characteristics UUID: 0x2A24)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onModelNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param taskId             task id
     * @param bluetoothDevice    Rbt device
     * @param serialNumberString read result data
     * @param argument           callback argument
     */
    void onSerialNumberStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull SerialNumberString serialNumberString, @Nullable Bundle argument);

    /**
     * Read failed callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onSerialNumberStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onSerialNumberStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param firmwareRevisionString read result data
     * @param argument               callback argument
     */
    void onFirmwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull FirmwareRevisionString firmwareRevisionString, @Nullable Bundle argument);

    /**
     * Read failed callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onFirmwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onFirmwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param hardwareRevisionString read result data
     * @param argument               callback argument
     */
    void onHardwareRevisionStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull HardwareRevisionString hardwareRevisionString, @Nullable Bundle argument);

    /**
     * Read failed callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onHardwareRevisionStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onHardwareRevisionStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Read success callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param manufacturerNameString read result data
     * @param argument               callback argument
     */
    void onManufacturerNameStringReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ManufacturerNameString manufacturerNameString, @Nullable Bundle argument);

    /**
     * Read failed callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onManufacturerNameStringReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Read timeout callback Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onManufacturerNameStringReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

}
