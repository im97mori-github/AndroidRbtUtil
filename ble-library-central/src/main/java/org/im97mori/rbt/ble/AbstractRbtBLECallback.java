package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
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
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryStatus;
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
import org.im97mori.rbt.ble.characteristic.mds.MemoryIndexInformation;
import org.im97mori.rbt.ble.characteristic.mds.MemoryStatus;
import org.im97mori.rbt.ble.characteristic.tss.LatestTimeCounter;
import org.im97mori.rbt.ble.characteristic.tss.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.tss.TimeSetting;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.CharacteristicUUID.APPEARANCE_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.DEVICE_NAME_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.FIRMWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.HARDWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MANUFACTURER_NAME_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MODEL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.SERIAL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
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
public abstract class AbstractRbtBLECallback implements RbtCallback, RbtRequestAccelerationMemoryIndexCallback, RbtRequestMemoryIndexCallback {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryIndexInformationReadSuccess(taskId, bluetoothDevice, MemoryIndexInformation.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStatusReadSuccess(taskId, bluetoothDevice, MemoryStatus.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataReadSuccess(taskId, bluetoothDevice, LatestSensingData.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataReadSuccess(taskId, bluetoothDevice, LatestCalculationData.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagReadSuccess(taskId, bluetoothDevice, LatestSensingFlag.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagReadSuccess(taskId, bluetoothDevice, LatestCalculationFlag.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusReadSuccess(taskId, bluetoothDevice, LatestAccelerationStatus.CREATOR.createFromByteArray(values), argument);
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            onVibrationCountReadSuccess(taskId, bluetoothDevice, VibrationCount.CREATOR.createFromByteArray(values), argument);
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryStatusReadSuccess(taskId, bluetoothDevice, AccelerationMemoryStatus.CREATOR.createFromByteArray(values), argument);
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateReadSuccess(taskId, bluetoothDevice, LedSettingNormalState.CREATOR.createFromByteArray(values), argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateReadSuccess(taskId, bluetoothDevice, LedSettingEventState.CREATOR.createFromByteArray(values), argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationReadSuccess(taskId, bluetoothDevice, LedStateOperation.CREATOR.createFromByteArray(values), argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetReadSuccess(taskId, bluetoothDevice, InstallationOffset.CREATOR.createFromByteArray(values), argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingReadSuccess(taskId, bluetoothDevice, AdvertiseSetting.CREATOR.createFromByteArray(values), argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeReadSuccess(taskId, bluetoothDevice, ModeChange.CREATOR.createFromByteArray(values), argument);
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerStatusReadSuccess(taskId, bluetoothDevice, AccelerationLoggerStatus.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestTimeCounterReadSuccess(taskId, bluetoothDevice, LatestTimeCounter.CREATOR.createFromByteArray(values), argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingReadSuccess(taskId, bluetoothDevice, TimeSetting.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalReadSuccess(taskId, bluetoothDevice, MemoryStorageInterval.CREATOR.createFromByteArray(values), argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1ReadSuccess(taskId, bluetoothDevice, TemperatureSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2ReadSuccess(taskId, bluetoothDevice, TemperatureSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1ReadSuccess(taskId, bluetoothDevice, RelativeHumiditySensor1.CREATOR.createFromByteArray(values), argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2ReadSuccess(taskId, bluetoothDevice, RelativeHumiditySensor2.CREATOR.createFromByteArray(values), argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1ReadSuccess(taskId, bluetoothDevice, AmbientLightSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2ReadSuccess(taskId, bluetoothDevice, AmbientLightSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1ReadSuccess(taskId, bluetoothDevice, BarometricPressureSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2ReadSuccess(taskId, bluetoothDevice, BarometricPressureSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1ReadSuccess(taskId, bluetoothDevice, SoundNoiseSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2ReadSuccess(taskId, bluetoothDevice, SoundNoiseSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1ReadSuccess(taskId, bluetoothDevice, EtvocSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2ReadSuccess(taskId, bluetoothDevice, EtvocSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1ReadSuccess(taskId, bluetoothDevice, Eco2Sensor1.CREATOR.createFromByteArray(values), argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2ReadSuccess(taskId, bluetoothDevice, Eco2Sensor2.CREATOR.createFromByteArray(values), argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1ReadSuccess(taskId, bluetoothDevice, DiscomfortIndexSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2ReadSuccess(taskId, bluetoothDevice, DiscomfortIndexSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1ReadSuccess(taskId, bluetoothDevice, HeatStrokeSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2ReadSuccess(taskId, bluetoothDevice, HeatStrokeSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationReadSuccess(taskId, bluetoothDevice, SiValueAcceleration.CREATOR.createFromByteArray(values), argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationReadSuccess(taskId, bluetoothDevice, PgaAcceleration.CREATOR.createFromByteArray(values), argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationReadSuccess(taskId, bluetoothDevice, SeismicIntensityAcceleration.CREATOR.createFromByteArray(values), argument);
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onErrorStatusReadSuccess(taskId, bluetoothDevice, ErrorStatus.CREATOR.createFromByteArray(values), argument);
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMountingOrientationReadSuccess(taskId, bluetoothDevice, MountingOrientation.CREATOR.createFromByteArray(values), argument);
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onFlashMemoryStatusReadSuccess(taskId, bluetoothDevice, FlashMemoryStatus.CREATOR.createFromByteArray(values), argument);
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            onDeviceNameReadSuccess(taskId, bluetoothDevice, DeviceName.CREATOR.createFromByteArray(values), argument);
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            onAppearanceReadSuccess(taskId, bluetoothDevice, Appearance.CREATOR.createFromByteArray(values), argument);
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS_CHARACTERISTIC.equals(characteristicUUID)) {
            onPeripheralPreferredConnectionParametersReadSuccess(taskId, bluetoothDevice, PeripheralPreferredConnectionParameters.CREATOR.createFromByteArray(values), argument);
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            onCentralAddressResolutionReadSuccess(taskId, bluetoothDevice, CentralAddressResolution.CREATOR.createFromByteArray(values), argument);
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onModelNumberStringReadSuccess(taskId, bluetoothDevice, ModelNumberString.CREATOR.createFromByteArray(values), argument);
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onSerialNumberStringReadSuccess(taskId, bluetoothDevice, SerialNumberString.CREATOR.createFromByteArray(values), argument);
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onFirmwareRevisionStringReadSuccess(taskId, bluetoothDevice, FirmwareRevisionString.CREATOR.createFromByteArray(values), argument);
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onHardwareRevisionStringReadSuccess(taskId, bluetoothDevice, HardwareRevisionString.CREATOR.createFromByteArray(values), argument);
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onManufacturerNameStringReadSuccess(taskId, bluetoothDevice, ManufacturerNameString.CREATOR.createFromByteArray(values), argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, int status, @Nullable Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryIndexInformationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            onVibrationCountReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestTimeCounterReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2ReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onErrorStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMountingOrientationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onFlashMemoryStatusReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            onDeviceNameReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            onAppearanceReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS_CHARACTERISTIC.equals(characteristicUUID)) {
            onPeripheralPreferredConnectionParametersReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            onCentralAddressResolutionReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onModelNumberStringReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onSerialNumberStringReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onFirmwareRevisionStringReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onHardwareRevisionStringReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onManufacturerNameStringReadFailed(taskId, bluetoothDevice, status, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, long timeout, @Nullable Bundle argument) {
        if (MEMORY_INDEX_INFORMATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryIndexInformationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (VIBRATION_COUNT_CHARACTERISTIC.equals(characteristicUUID)) {
            onVibrationCountReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ACCELERATION_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ACCELERATION_LOGGER_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_TIME_COUNTER_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestTimeCounterReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2ReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ERROR_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onErrorStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MOUNTING_ORIENTATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onMountingOrientationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onFlashMemoryStatusReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (DEVICE_NAME_CHARACTERISTIC.equals(characteristicUUID)) {
            onDeviceNameReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (APPEARANCE_CHARACTERISTIC.equals(characteristicUUID)) {
            onAppearanceReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS_CHARACTERISTIC.equals(characteristicUUID)) {
            onPeripheralPreferredConnectionParametersReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC.equals(characteristicUUID)) {
            onCentralAddressResolutionReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MODEL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onModelNumberStringReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SERIAL_NUMBER_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onSerialNumberStringReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (FIRMWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onFirmwareRevisionStringReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (HARDWARE_REVISION_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onHardwareRevisionStringReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MANUFACTURER_NAME_STRING_CHARACTERISTIC.equals(characteristicUUID)) {
            onManufacturerNameStringReadTimeout(taskId, bluetoothDevice, timeout, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateWriteSuccess(taskId, bluetoothDevice, LedSettingNormalState.CREATOR.createFromByteArray(values), argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateWriteSuccess(taskId, bluetoothDevice, LedSettingEventState.CREATOR.createFromByteArray(values), argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationWriteSuccess(taskId, bluetoothDevice, LedStateOperation.CREATOR.createFromByteArray(values), argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetWriteSuccess(taskId, bluetoothDevice, InstallationOffset.CREATOR.createFromByteArray(values), argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingWriteSuccess(taskId, bluetoothDevice, AdvertiseSetting.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryResetWriteSuccess(taskId, bluetoothDevice, MemoryReset.CREATOR.createFromByteArray(values), argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeWriteSuccess(taskId, bluetoothDevice, ModeChange.CREATOR.createFromByteArray(values), argument);
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerControlWriteSuccess(taskId, bluetoothDevice, AccelerationLoggerControl.CREATOR.createFromByteArray(values), argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingWriteSuccess(taskId, bluetoothDevice, TimeSetting.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalWriteSuccess(taskId, bluetoothDevice, MemoryStorageInterval.CREATOR.createFromByteArray(values), argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1WriteSuccess(taskId, bluetoothDevice, TemperatureSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2WriteSuccess(taskId, bluetoothDevice, TemperatureSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1WriteSuccess(taskId, bluetoothDevice, RelativeHumiditySensor1.CREATOR.createFromByteArray(values), argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2WriteSuccess(taskId, bluetoothDevice, RelativeHumiditySensor2.CREATOR.createFromByteArray(values), argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1WriteSuccess(taskId, bluetoothDevice, AmbientLightSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2WriteSuccess(taskId, bluetoothDevice, AmbientLightSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1WriteSuccess(taskId, bluetoothDevice, BarometricPressureSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2WriteSuccess(taskId, bluetoothDevice, BarometricPressureSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1WriteSuccess(taskId, bluetoothDevice, SoundNoiseSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2WriteSuccess(taskId, bluetoothDevice, SoundNoiseSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1WriteSuccess(taskId, bluetoothDevice, EtvocSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2WriteSuccess(taskId, bluetoothDevice, EtvocSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1WriteSuccess(taskId, bluetoothDevice, Eco2Sensor1.CREATOR.createFromByteArray(values), argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2WriteSuccess(taskId, bluetoothDevice, Eco2Sensor2.CREATOR.createFromByteArray(values), argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1WriteSuccess(taskId, bluetoothDevice, DiscomfortIndexSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2WriteSuccess(taskId, bluetoothDevice, DiscomfortIndexSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1WriteSuccess(taskId, bluetoothDevice, HeatStrokeSensor1.CREATOR.createFromByteArray(values), argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2WriteSuccess(taskId, bluetoothDevice, HeatStrokeSensor2.CREATOR.createFromByteArray(values), argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationWriteSuccess(taskId, bluetoothDevice, SiValueAcceleration.CREATOR.createFromByteArray(values), argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationWriteSuccess(taskId, bluetoothDevice, PgaAcceleration.CREATOR.createFromByteArray(values), argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationWriteSuccess(taskId, bluetoothDevice, SeismicIntensityAcceleration.CREATOR.createFromByteArray(values), argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, int status, @Nullable Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryResetWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerControlWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2WriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationWriteFailed(taskId, bluetoothDevice, status, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, long timeout, @Nullable Bundle argument) {
        if (LED_SETTING_NORMAL_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingNormalStateWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LED_SETTING_EVENT_STATE_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedSettingEventStateWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LED_STATE_OPERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onLedStateOperationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (INSTALLATION_OFFSET_CHARACTERISTIC.equals(characteristicUUID)) {
            onInstallationOffsetWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ADVERTISE_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onAdvertiseSettingWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_RESET_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryResetWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MODE_CHANGE_CHARACTERISTIC.equals(characteristicUUID)) {
            onModeChangeWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationLoggerControlWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TIME_SETTING_CHARACTERISTIC.equals(characteristicUUID)) {
            onTimeSettingWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_STORAGE_INTERVAL_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryStorageIntervalWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TEMPERATURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (TEMPERATURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onTemperatureSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onRelativeHumiditySensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onAmbientLightSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onBarometricPressureSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SOUND_NOISE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SOUND_NOISE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onSoundNoiseSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ETVOC_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ETVOC_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEtvocSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ECO2_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ECO2_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onEco2Sensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onDiscomfortIndexSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (HEAT_STROKE_SENSOR_1_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor1WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (HEAT_STROKE_SENSOR_2_CHARACTERISTIC.equals(characteristicUUID)) {
            onHeatStrokeSensor2WriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SI_VALUE_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSiValueAccelerationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (PGA_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onPgaAccelerationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC.equals(characteristicUUID)) {
            onSeismicIntensityAccelerationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, int status, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(taskId, bluetoothDevice, status, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, long timeout, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(taskId, bluetoothDevice, timeout, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, @NonNull byte[] values, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(taskId, bluetoothDevice, ClientCharacteristicConfiguration.CREATOR.createFromByteArray(values), argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, int status, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(taskId, bluetoothDevice, status, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDescriptorWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull UUID descriptorUUID, long timeout, @Nullable Bundle argument) {
        if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(taskId, bluetoothDevice, timeout, argument);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicNotified(@NonNull BluetoothDevice bluetoothDevice, @NonNull UUID serviceUUID, @NonNull UUID characteristicUUID, @NonNull byte[] values) {
        if (LATEST_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingDataNotified(bluetoothDevice, LatestSensingData.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationDataNotified(bluetoothDevice, LatestCalculationData.CREATOR.createFromByteArray(values));
        } else if (LATEST_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestSensingFlagNotified(bluetoothDevice, LatestSensingFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestCalculationFlagNotified(bluetoothDevice, LatestCalculationFlag.CREATOR.createFromByteArray(values));
        } else if (LATEST_ACCELERATION_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
            onLatestAccelerationStatusNotified(bluetoothDevice, LatestAccelerationStatus.CREATOR.createFromByteArray(values));
        }
    }
}
