package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

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

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.CharacteristicUUID.APPEARANCE_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.DEVICE_NAME_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.FIRMWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.HARDWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MANUFACTURER_NAME_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MODEL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.SERIAL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ADVERTISE_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ECO2_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ECO2_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ERROR_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ETVOC_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ETVOC_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.FLASH_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.HEAT_STROKE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.HEAT_STROKE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.INSTALLATION_OFFSET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_ACCELERATION_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_TIME_COUNTER_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_EVENT_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_NORMAL_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_STATE_OPERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_INDEX_INFORMATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_RESET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STORAGE_INTERVAL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MODE_CHANGE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MOUNTING_ORIENTATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.PGA_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SI_VALUE_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SOUND_NOISE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SOUND_NOISE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TEMPERATURE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TEMPERATURE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TIME_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.VIBRATION_COUNT_CHARACTERISTIC;

/**
 * {@link BLECallback} to {@link RbtCallback} by UUID
 */
class RbtBLECallback implements BLECallback {

    /**
     * {@link RbtCallback} instance
     */
    private final RbtCallback mRbtCallback;

    /**
     * @param rbtCallback {@link RbtCallback} instance
     */
    RbtBLECallback(RbtCallback rbtCallback) {
        mRbtCallback = rbtCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBLEConnected(long taskId, BluetoothDevice bluetoothDevice, Bundle argument) {
        mRbtCallback.onRbtConnected(bluetoothDevice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBLEConnectFailed(long taskId, BluetoothDevice bluetoothDevice, int status, Bundle argument) {
        mRbtCallback.onRbtConnectFailed(bluetoothDevice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBLEConnectTimeout(long taskId, BluetoothDevice bluetoothDevice, Bundle argument) {
        mRbtCallback.onRbtConnectTimeout(bluetoothDevice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBLEDisonnected(long taskId, BluetoothDevice bluetoothDevice, int status, Bundle argument) {
        mRbtCallback.onRbtDisonnected(bluetoothDevice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadSuccess(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, byte[] values, Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryIndexInformationReadSuccess(bluetoothDevice, MemoryIndexInformation.CREATOR.createFromByteArray(values));
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStatusReadSuccess(bluetoothDevice, MemoryStatus.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataReadSuccess(bluetoothDevice, LatestSensingData.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataReadSuccess(bluetoothDevice, LatestCalculationData.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagReadSuccess(bluetoothDevice, LatestSensingFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagReadSuccess(bluetoothDevice, LatestCalculationFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusReadSuccess(bluetoothDevice, LatestAccelerationStatus.CREATOR.createFromByteArray(values));
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onVibrationCountReadSuccess(bluetoothDevice, VibrationCount.CREATOR.createFromByteArray(values));
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationMemoryStatusReadSuccess(bluetoothDevice, AccelerationMemoryStatus.CREATOR.createFromByteArray(values));
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateReadSuccess(bluetoothDevice, LedSettingNormalState.CREATOR.createFromByteArray(values));
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateReadSuccess(bluetoothDevice, LedSettingEventState.CREATOR.createFromByteArray(values));
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationReadSuccess(bluetoothDevice, LedStateOperation.CREATOR.createFromByteArray(values));
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetReadSuccess(bluetoothDevice, InstallationOffset.CREATOR.createFromByteArray(values));
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingReadSuccess(bluetoothDevice, AdvertiseSetting.CREATOR.createFromByteArray(values));
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeReadSuccess(bluetoothDevice, ModeChange.CREATOR.createFromByteArray(values));
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerStatusReadSuccess(bluetoothDevice, AccelerationLoggerStatus.CREATOR.createFromByteArray(values));
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestTimeCounterReadSuccess(bluetoothDevice, LatestTimeCounter.CREATOR.createFromByteArray(values));
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingReadSuccess(bluetoothDevice, TimeSetting.CREATOR.createFromByteArray(values));
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalReadSuccess(bluetoothDevice, MemoryStorageInterval.CREATOR.createFromByteArray(values));
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1ReadSuccess(bluetoothDevice, TemperatureSensor1.CREATOR.createFromByteArray(values));
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2ReadSuccess(bluetoothDevice, TemperatureSensor2.CREATOR.createFromByteArray(values));
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1ReadSuccess(bluetoothDevice, RelativeHumiditySensor1.CREATOR.createFromByteArray(values));
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2ReadSuccess(bluetoothDevice, RelativeHumiditySensor2.CREATOR.createFromByteArray(values));
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1ReadSuccess(bluetoothDevice, AmbientLightSensor1.CREATOR.createFromByteArray(values));
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2ReadSuccess(bluetoothDevice, AmbientLightSensor2.CREATOR.createFromByteArray(values));
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1ReadSuccess(bluetoothDevice, BarometricPressureSensor1.CREATOR.createFromByteArray(values));
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2ReadSuccess(bluetoothDevice, BarometricPressureSensor2.CREATOR.createFromByteArray(values));
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1ReadSuccess(bluetoothDevice, SoundNoiseSensor1.CREATOR.createFromByteArray(values));
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2ReadSuccess(bluetoothDevice, SoundNoiseSensor2.CREATOR.createFromByteArray(values));
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1ReadSuccess(bluetoothDevice, EtvocSensor1.CREATOR.createFromByteArray(values));
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2ReadSuccess(bluetoothDevice, EtvocSensor2.CREATOR.createFromByteArray(values));
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1ReadSuccess(bluetoothDevice, Eco2Sensor1.CREATOR.createFromByteArray(values));
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2ReadSuccess(bluetoothDevice, Eco2Sensor2.CREATOR.createFromByteArray(values));
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1ReadSuccess(bluetoothDevice, DiscomfortIndexSensor1.CREATOR.createFromByteArray(values));
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2ReadSuccess(bluetoothDevice, DiscomfortIndexSensor2.CREATOR.createFromByteArray(values));
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1ReadSuccess(bluetoothDevice, HeatStrokeSensor1.CREATOR.createFromByteArray(values));
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2ReadSuccess(bluetoothDevice, HeatStrokeSensor2.CREATOR.createFromByteArray(values));
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationReadSuccess(bluetoothDevice, SiValueAcceleration.CREATOR.createFromByteArray(values));
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationReadSuccess(bluetoothDevice, PgaAcceleration.CREATOR.createFromByteArray(values));
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationReadSuccess(bluetoothDevice, SeismicIntensityAcceleration.CREATOR.createFromByteArray(values));
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onErrorStatusReadSuccess(bluetoothDevice, ErrorStatus.CREATOR.createFromByteArray(values));
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMountingOrientationReadSuccess(bluetoothDevice, MountingOrientation.CREATOR.createFromByteArray(values));
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFlashMemoryStatusReadSuccess(bluetoothDevice, FlashMemoryStatus.CREATOR.createFromByteArray(values));
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDeviceNameReadSuccess(bluetoothDevice, DeviceName.CREATOR.createFromByteArray(values));
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAppearanceReadSuccess(bluetoothDevice, Appearance.CREATOR.createFromByteArray(values));
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPeripheralPreferredConnectionParametersReadSuccess(bluetoothDevice, PeripheralPreferredConnectionParameters.CREATOR.createFromByteArray(values));
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onCentralAddressResolutionReadSuccess(bluetoothDevice, CentralAddressResolution.CREATOR.createFromByteArray(values));
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModelNumberStringReadSuccess(bluetoothDevice, ModelNumberString.CREATOR.createFromByteArray(values));
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSerialNumberStringReadSuccess(bluetoothDevice, SerialNumberString.CREATOR.createFromByteArray(values));
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFirmwareRevisionStringReadSuccess(bluetoothDevice, FirmwareRevisionString.CREATOR.createFromByteArray(values));
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHardwareRevisionStringReadSuccess(bluetoothDevice, HardwareRevisionString.CREATOR.createFromByteArray(values));
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onManufacturerNameStringReadSuccess(bluetoothDevice, ManufacturerNameString.CREATOR.createFromByteArray(values));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadFailed(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, int status, Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryIndexInformationReadFailed(bluetoothDevice, status);
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStatusReadFailed(bluetoothDevice, status);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataReadFailed(bluetoothDevice, status);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataReadFailed(bluetoothDevice, status);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagReadFailed(bluetoothDevice, status);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagReadFailed(bluetoothDevice, status);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusReadFailed(bluetoothDevice, status);
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onVibrationCountReadFailed(bluetoothDevice, status);
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationMemoryStatusReadFailed(bluetoothDevice, status);
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateReadFailed(bluetoothDevice, status);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateReadFailed(bluetoothDevice, status);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationReadFailed(bluetoothDevice, status);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetReadFailed(bluetoothDevice, status);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingReadFailed(bluetoothDevice, status);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeReadFailed(bluetoothDevice, status);
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerStatusReadFailed(bluetoothDevice, status);
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestTimeCounterReadFailed(bluetoothDevice, status);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingReadFailed(bluetoothDevice, status);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalReadFailed(bluetoothDevice, status);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1ReadFailed(bluetoothDevice, status);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2ReadFailed(bluetoothDevice, status);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1ReadFailed(bluetoothDevice, status);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2ReadFailed(bluetoothDevice, status);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1ReadFailed(bluetoothDevice, status);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2ReadFailed(bluetoothDevice, status);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1ReadFailed(bluetoothDevice, status);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2ReadFailed(bluetoothDevice, status);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1ReadFailed(bluetoothDevice, status);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2ReadFailed(bluetoothDevice, status);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1ReadFailed(bluetoothDevice, status);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2ReadFailed(bluetoothDevice, status);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1ReadFailed(bluetoothDevice, status);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2ReadFailed(bluetoothDevice, status);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1ReadFailed(bluetoothDevice, status);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2ReadFailed(bluetoothDevice, status);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1ReadFailed(bluetoothDevice, status);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2ReadFailed(bluetoothDevice, status);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationReadFailed(bluetoothDevice, status);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationReadFailed(bluetoothDevice, status);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationReadFailed(bluetoothDevice, status);
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onErrorStatusReadFailed(bluetoothDevice, status);
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMountingOrientationReadFailed(bluetoothDevice, status);
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFlashMemoryStatusReadFailed(bluetoothDevice, status);
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDeviceNameReadFailed(bluetoothDevice, status);
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAppearanceReadFailed(bluetoothDevice, status);
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPeripheralPreferredConnectionParametersReadFailed(bluetoothDevice, status);
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onCentralAddressResolutionReadFailed(bluetoothDevice, status);
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModelNumberStringReadFailed(bluetoothDevice, status);
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSerialNumberStringReadFailed(bluetoothDevice, status);
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFirmwareRevisionStringReadFailed(bluetoothDevice, status);
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHardwareRevisionStringReadFailed(bluetoothDevice, status);
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onManufacturerNameStringReadFailed(bluetoothDevice, status);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadTimeout(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, long timeout, Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryIndexInformationReadTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStatusReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusReadTimeout(bluetoothDevice, timeout);
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onVibrationCountReadTimeout(bluetoothDevice, timeout);
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationMemoryStatusReadTimeout(bluetoothDevice, timeout);
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateReadTimeout(bluetoothDevice, timeout);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateReadTimeout(bluetoothDevice, timeout);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationReadTimeout(bluetoothDevice, timeout);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetReadTimeout(bluetoothDevice, timeout);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingReadTimeout(bluetoothDevice, timeout);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeReadTimeout(bluetoothDevice, timeout);
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerStatusReadTimeout(bluetoothDevice, timeout);
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestTimeCounterReadTimeout(bluetoothDevice, timeout);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingReadTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalReadTimeout(bluetoothDevice, timeout);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1ReadTimeout(bluetoothDevice, timeout);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2ReadTimeout(bluetoothDevice, timeout);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationReadTimeout(bluetoothDevice, timeout);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationReadTimeout(bluetoothDevice, timeout);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationReadTimeout(bluetoothDevice, timeout);
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onErrorStatusReadTimeout(bluetoothDevice, timeout);
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMountingOrientationReadTimeout(bluetoothDevice, timeout);
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFlashMemoryStatusReadTimeout(bluetoothDevice, timeout);
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDeviceNameReadTimeout(bluetoothDevice, timeout);
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAppearanceReadTimeout(bluetoothDevice, timeout);
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPeripheralPreferredConnectionParametersReadTimeout(bluetoothDevice, timeout);
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onCentralAddressResolutionReadTimeout(bluetoothDevice, timeout);
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModelNumberStringReadTimeout(bluetoothDevice, timeout);
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSerialNumberStringReadTimeout(bluetoothDevice, timeout);
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onFirmwareRevisionStringReadTimeout(bluetoothDevice, timeout);
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHardwareRevisionStringReadTimeout(bluetoothDevice, timeout);
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onManufacturerNameStringReadTimeout(bluetoothDevice, timeout);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteSuccess(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, byte[] values, Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateWriteSuccess(bluetoothDevice, LedSettingNormalState.CREATOR.createFromByteArray(values));
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateWriteSuccess(bluetoothDevice, LedSettingEventState.CREATOR.createFromByteArray(values));
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationWriteSuccess(bluetoothDevice, LedStateOperation.CREATOR.createFromByteArray(values));
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetWriteSuccess(bluetoothDevice, InstallationOffset.CREATOR.createFromByteArray(values));
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingWriteSuccess(bluetoothDevice, AdvertiseSetting.CREATOR.createFromByteArray(values));
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryResetWriteSuccess(bluetoothDevice, MemoryReset.CREATOR.createFromByteArray(values));
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeWriteSuccess(bluetoothDevice, ModeChange.CREATOR.createFromByteArray(values));
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerControlWriteSuccess(bluetoothDevice, AccelerationLoggerControl.CREATOR.createFromByteArray(values));
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingWriteSuccess(bluetoothDevice, TimeSetting.CREATOR.createFromByteArray(values));
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalWriteSuccess(bluetoothDevice, MemoryStorageInterval.CREATOR.createFromByteArray(values));
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1WriteSuccess(bluetoothDevice, TemperatureSensor1.CREATOR.createFromByteArray(values));
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2WriteSuccess(bluetoothDevice, TemperatureSensor2.CREATOR.createFromByteArray(values));
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1WriteSuccess(bluetoothDevice, RelativeHumiditySensor1.CREATOR.createFromByteArray(values));
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2WriteSuccess(bluetoothDevice, RelativeHumiditySensor2.CREATOR.createFromByteArray(values));
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1WriteSuccess(bluetoothDevice, AmbientLightSensor1.CREATOR.createFromByteArray(values));
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2WriteSuccess(bluetoothDevice, AmbientLightSensor2.CREATOR.createFromByteArray(values));
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1WriteSuccess(bluetoothDevice, BarometricPressureSensor1.CREATOR.createFromByteArray(values));
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2WriteSuccess(bluetoothDevice, BarometricPressureSensor2.CREATOR.createFromByteArray(values));
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1WriteSuccess(bluetoothDevice, SoundNoiseSensor1.CREATOR.createFromByteArray(values));
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2WriteSuccess(bluetoothDevice, SoundNoiseSensor2.CREATOR.createFromByteArray(values));
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1WriteSuccess(bluetoothDevice, EtvocSensor1.CREATOR.createFromByteArray(values));
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2WriteSuccess(bluetoothDevice, EtvocSensor2.CREATOR.createFromByteArray(values));
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1WriteSuccess(bluetoothDevice, Eco2Sensor1.CREATOR.createFromByteArray(values));
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2WriteSuccess(bluetoothDevice, Eco2Sensor2.CREATOR.createFromByteArray(values));
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1WriteSuccess(bluetoothDevice, DiscomfortIndexSensor1.CREATOR.createFromByteArray(values));
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2WriteSuccess(bluetoothDevice, DiscomfortIndexSensor2.CREATOR.createFromByteArray(values));
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1WriteSuccess(bluetoothDevice, HeatStrokeSensor1.CREATOR.createFromByteArray(values));
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2WriteSuccess(bluetoothDevice, HeatStrokeSensor2.CREATOR.createFromByteArray(values));
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationWriteSuccess(bluetoothDevice, SiValueAcceleration.CREATOR.createFromByteArray(values));
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationWriteSuccess(bluetoothDevice, PgaAcceleration.CREATOR.createFromByteArray(values));
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationWriteSuccess(bluetoothDevice, SeismicIntensityAcceleration.CREATOR.createFromByteArray(values));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteFailed(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, int status, Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateWriteFailed(bluetoothDevice, status);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateWriteFailed(bluetoothDevice, status);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationWriteFailed(bluetoothDevice, status);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetWriteFailed(bluetoothDevice, status);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingWriteFailed(bluetoothDevice, status);
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryResetWriteFailed(bluetoothDevice, status);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeWriteFailed(bluetoothDevice, status);
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerControlWriteFailed(bluetoothDevice, status);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingWriteFailed(bluetoothDevice, status);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalWriteFailed(bluetoothDevice, status);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1WriteFailed(bluetoothDevice, status);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2WriteFailed(bluetoothDevice, status);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1WriteFailed(bluetoothDevice, status);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2WriteFailed(bluetoothDevice, status);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1WriteFailed(bluetoothDevice, status);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2WriteFailed(bluetoothDevice, status);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1WriteFailed(bluetoothDevice, status);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2WriteFailed(bluetoothDevice, status);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1WriteFailed(bluetoothDevice, status);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2WriteFailed(bluetoothDevice, status);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1WriteFailed(bluetoothDevice, status);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2WriteFailed(bluetoothDevice, status);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1WriteFailed(bluetoothDevice, status);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2WriteFailed(bluetoothDevice, status);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1WriteFailed(bluetoothDevice, status);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2WriteFailed(bluetoothDevice, status);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1WriteFailed(bluetoothDevice, status);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2WriteFailed(bluetoothDevice, status);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationWriteFailed(bluetoothDevice, status);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationWriteFailed(bluetoothDevice, status);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationWriteFailed(bluetoothDevice, status);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteTimeout(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, long timeout, Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingNormalStateWriteTimeout(bluetoothDevice, timeout);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedSettingEventStateWriteTimeout(bluetoothDevice, timeout);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLedStateOperationWriteTimeout(bluetoothDevice, timeout);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onInstallationOffsetWriteTimeout(bluetoothDevice, timeout);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAdvertiseSettingWriteTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryResetWriteTimeout(bluetoothDevice, timeout);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onModeChangeWriteTimeout(bluetoothDevice, timeout);
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAccelerationLoggerControlWriteTimeout(bluetoothDevice, timeout);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTimeSettingWriteTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryStorageIntervalWriteTimeout(bluetoothDevice, timeout);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onTemperatureSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onRelativeHumiditySensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onAmbientLightSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onBarometricPressureSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSoundNoiseSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEtvocSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onEco2Sensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onDiscomfortIndexSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor1WriteTimeout(bluetoothDevice, timeout);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onHeatStrokeSensor2WriteTimeout(bluetoothDevice, timeout);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSiValueAccelerationWriteTimeout(bluetoothDevice, timeout);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onPgaAccelerationWriteTimeout(bluetoothDevice, timeout);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onSeismicIntensityAccelerationWriteTimeout(bluetoothDevice, timeout);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadSuccess(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, byte[] values, Bundle argument) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadFailed(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, int status, Bundle argument) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadTimeout(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, long timeout, Bundle argument) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteSuccess(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, byte[] values, Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingDataClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationDataClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingFlagClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationFlagClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusClientCharactericsticConfigurationSuccess(bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteFailed(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, int status, Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingDataClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationDataClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingFlagClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationFlagClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusClientCharactericsticConfigurationFailed(bluetoothDevice, status);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteTimeout(long taskId, BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, long timeout, Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingDataClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationDataClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingFlagClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationFlagClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusClientCharactericsticConfigurationTimeout(bluetoothDevice, timeout);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicNotified(BluetoothDevice bluetoothDevice, UUID serviceUUID, UUID characteristicUUID, byte[] values) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingDataNotified(bluetoothDevice, MemorySensingData.CREATOR.createFromByteArray(values));
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationDataNotified(bluetoothDevice, MemoryCalculationData.CREATOR.createFromByteArray(values));
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemorySensingFlagNotified(bluetoothDevice, MemorySensingFlag.CREATOR.createFromByteArray(values));
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onMemoryCalculationFlagNotified(bluetoothDevice, MemoryCalculationFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingDataNotified(bluetoothDevice, LatestSensingData.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationDataNotified(bluetoothDevice, LatestCalculationData.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestSensingFlagNotified(bluetoothDevice, LatestSensingFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestCalculationFlagNotified(bluetoothDevice, LatestCalculationFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            mRbtCallback.onLatestAccelerationStatusNotified(bluetoothDevice, LatestAccelerationStatus.CREATOR.createFromByteArray(values));
        }
    }

}
