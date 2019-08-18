package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Message;
import android.text.format.DateUtils;

import org.im97mori.ble.BLEConnection;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.ble.task.ReadCharacteristicTask;
import org.im97mori.ble.task.WriteCharacteristicTask;
import org.im97mori.ble.task.WriteDescriptorTask;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.characteristic.AbstractRbtCharacteristic;
import org.im97mori.rbt.ble.characteristic.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.AdvertiseSetting;
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
import org.im97mori.rbt.ble.characteristic.FlashMemoryStatus;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor1;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor2;
import org.im97mori.rbt.ble.characteristic.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.MemoryReset;
import org.im97mori.rbt.ble.characteristic.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.ModeChange;
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
import org.im97mori.rbt.ble.task.RbtWriteCharacteristicTask;
import org.im97mori.rbt.ble.task.RequestAccelerationMemoryIndexTask;
import org.im97mori.rbt.ble.task.RequestMemoryIndexTask;

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
import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR;
import static org.im97mori.ble.BLEConstants.ServiceUUID.DEVICE_INFORMATION_SERVICE;
import static org.im97mori.ble.BLEConstants.ServiceUUID.GENERIC_ACCESS_SERVICE;
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
import static org.im97mori.rbt.RbtConstants.ServiceUUID.ACCELERATION_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.CONTROL_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.EVENT_SETTING_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.INFORMATION_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.LATEST_DATA_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.MEMORY_DATA_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.TIME_SETTING_SERVICE;

/**
 * Rbt device controller for BLE
 */
@SuppressWarnings("WeakerAccess")
public class RbtBLEConnection extends BLEConnection {

    /**
     * {@link RbtCallback} instance
     */
    private final RbtCallback mRbtCallback;

    /**
     * @param context         {@link Context} instance
     * @param bluetoothDevice Rbt {@link BluetoothDevice}
     * @param rbtCallback     {@link RbtCallback} instance
     */
    public RbtBLEConnection(Context context, BluetoothDevice bluetoothDevice, RbtCallback rbtCallback) {
        super(context, bluetoothDevice, new RbtBLECallback(rbtCallback));
        mRbtCallback = rbtCallback;
    }

    /**
     * Read Memory index information (Characteristics UUID: 0x5004)
     */
    public void readMemoryIndexInformation() {
        createReadCharacteristicTask(MEMORY_DATA_SERVICE, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Memory status (Characteristics UUID: 0x5006)
     */
    public void readMemoryStatus() {
        createReadCharacteristicTask(MEMORY_DATA_SERVICE, MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest sensing data (Characteristics UUID: 0x5012)
     */
    public void readLatestSensingData() {
        createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_SENSING_DATA_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest calculation data ( Characteristics UUID: 0x5013)
     */
    public void readLatestCalculationData() {
        createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_DATA_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest sensing flag (Characteristics UUID: 0x5014)
     */
    public void readLatestSensingFlag() {
        createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_SENSING_FLAG_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest calculation flag ( Characteristics UUID: 0x5015)
     */
    public void readLatestCalculationFlag() {
        createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_FLAG_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest acceleration status (Characteristics UUID: 0x5016)
     */
    public void readLatestAccelerationStatus() {
        createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_ACCELERATION_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Vibration count (Characteristics UUID: 0x5031)
     */
    public void readVibrationCount() {
        createReadCharacteristicTask(ACCELERATION_SERVICE, VIBRATION_COUNT_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Acceleration memory status (Characteristics UUID: 0x5033)
     */
    public void readAccelerationMemoryStatus() {
        createReadCharacteristicTask(ACCELERATION_SERVICE, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read LED setting [normal state] (Characteristics UUID: 0x5111)
     */
    public void readLedSettingNormalState() {
        createReadCharacteristicTask(CONTROL_SERVICE, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read LED setting [event state] (Characteristics UUID: 0x5112)
     */
    public void readLedSettingEventState() {
        createReadCharacteristicTask(CONTROL_SERVICE, LED_SETTING_EVENT_STATE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read LED state [operation] (Characteristics UUID: 0x5113)
     */
    public void readLedStateOperation() {
        createReadCharacteristicTask(CONTROL_SERVICE, LED_STATE_OPERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Installation offset (Characteristics UUID: 0x5114)
     */
    public void readInstallationOffset() {
        createReadCharacteristicTask(CONTROL_SERVICE, INSTALLATION_OFFSET_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Advertise setting (Characteristics UUID: 0x5115)
     */
    public void readAdvertisingSetting() {
        createReadCharacteristicTask(CONTROL_SERVICE, ADVERTISE_SETTING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Mode change (Characteristics UUID: 0x5117)
     */
    public void readModeChange() {
        createReadCharacteristicTask(CONTROL_SERVICE, MODE_CHANGE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Acceleration logger status (Characteristics UUID: 0x5119)
     */
    public void readAccelerationLoggerStatus() {
        createReadCharacteristicTask(CONTROL_SERVICE, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Latest time counter (Characteristics UUID: 0x5201)
     */
    public void readLatestTimeCounter() {
        createReadCharacteristicTask(TIME_SETTING_SERVICE, LATEST_TIME_COUNTER_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Time setting (Characteristics UUID: 0x5202)
     */
    public void readTimeSetting() {
        createReadCharacteristicTask(TIME_SETTING_SERVICE, TIME_SETTING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Memory storage interval (Characteristics UUID: 0x5203)
     */
    public void readMemoryStorageInterval() {
        createReadCharacteristicTask(TIME_SETTING_SERVICE, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Temperature [Sensor 1] (Characteristics UUID: 0x5211)
     */
    public void readTemperatureSensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Temperature [Sensor 2] (Characteristics UUID: 0x5212)
     */
    public void readTemperatureSensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Relative humidity [Sensor 1] (Characteristics UUID: 0x5213)
     */
    public void readRelativeHumiditySensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Relative humidity [Sensor 2] (Characteristics UUID: 0x5214)
     */
    public void readRelativeHumiditySensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Ambient light [Sensor 1] (Characteristics UUID: 0x5215)
     */
    public void readAmbientLightSensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Ambient light [Sensor 2] (Characteristics UUID: 0x5216)
     */
    public void readAmbientLightSensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Barometric pressure [Sensor 1] (Characteristics UUID: 0x5217)
     */
    public void readBarometricPressure1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Barometric pressure [Sensor 2] (Characteristics UUID: 0x5218)
     */
    public void readBarometricPressure2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Sound noise [Sensor 1] (Characteristics UUID: 0x5219)
     */
    public void readSoundNoise1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Sound noise [Sensor 2] (Characteristics UUID: 0x521A)
     */
    public void readSoundNoise2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern eTVOC [Sensor 1] (Characteristics UUID: 0x521B)
     */
    public void readEtvocSensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern eTVOC  [Sensor 2] (Characteristics UUID: 0x521C)
     */
    public void readEtvocSensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern eCO2 [Sensor 1] (Characteristics UUID: 0x521D)
     */
    public void readEco2Sensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern eCO2 [Sensor 2] (Characteristics UUID: 0x521E)
     */
    public void readEco2Sensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Discomfort index [Sensor 1] (Characteristics UUID: 0x521F)
     */
    public void readDiscomfortIndexSensor1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Discomfort index [Sensor 2] (Characteristics UUID: 0x5220)
     */
    public void readDiscomfortIndexSensor2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Heat stroke [Sensor 1] (Characteristics UUID: 0x5221)
     */
    public void readHeatStroke1() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Heat stroke [Sensor 2] (Characteristics UUID: 0x5222)
     */
    public void readHeatStroke2() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern SI value [Acceleration] (Characteristics UUID: 0x5226)
     */
    public void readSiValueAcceleration() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, SI_VALUE_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern PGA [Acceleration] (Characteristics UUID: 0x5227)
     */
    public void readPgaAcceleration() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, PGA_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Event pattern Seismic intensity [Acceleration] (Characteristics UUID: 0x5228)
     */
    public void readSeismicIntensityAcceleration() {
        createReadCharacteristicTask(EVENT_SETTING_SERVICE, SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Error status (Characteristics UUID: 0x5401)
     */
    public void readErrorStatus() {
        createReadCharacteristicTask(INFORMATION_SERVICE, ERROR_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Mounting orientation (Characteristics UUID: 0x5402)
     */
    public void readMountingOrientation() {
        createReadCharacteristicTask(INFORMATION_SERVICE, MOUNTING_ORIENTATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read FLASH memory status (Characteristics UUID: 0x5403)
     */
    public void readFlashMemoryStatus() {
        createReadCharacteristicTask(INFORMATION_SERVICE, FLASH_MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Device name (Characteristics UUID: 0x2A00)
     */
    public void readDeviceName() {
        createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, DEVICE_NAME_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Appearance (Characteristics UUID: 0x2A01)
     */
    public void readAppearance() {
        createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, APPEARANCE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Peripheral preferred c onnection parameters (Characteristics UUID: 0x2A04)
     */
    public void readPeripheralPreferredConnectionParameters() {
        createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Central address resolution (Characteristics UUID: 0x2AA6)
     */
    public void readCentralAddressResolution() {
        createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Model number string (Characteristics UUID: 0x2A24)
     */
    public void readModelNumberString() {
        createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, MODEL_NUMBER_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Serial number string (Characteristics UUID: 0x2A25)
     */
    public void readSerialNumberString() {
        createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, SERIAL_NUMBER_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Firmware revision string (Characteristics UUID: 0x2A26)
     */
    public void readFirmwareRevisionString() {
        createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, FIRMWARE_REVISION_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Hardware revision string (Characteristics UUID: 0x2A27)
     */
    public void readHardwareRevisionString() {
        createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, HARDWARE_REVISION_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Read Manufacturer name string (Characteristics UUID: 0x2A28)
     */
    public void readManufacturerNameString() {
        createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, MANUFACTURER_NAME_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Request memory index (Characteristics UUID: 0x5005)
     *
     * @param data {@link RequestMemoryIndex} data
     */
    public void writeRequestMemoryIndex(RequestMemoryIndex data) {
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RequestMemoryIndexTask task = new RequestMemoryIndexTask(bluetoothGatt, mTaskHandler, data, mRbtCallback);
            Message message = RequestMemoryIndexTask.createWriteRequestMemoryIndexMessage(task);
            mTaskHandler.addTask(task, message);
        }
    }

    /**
     * Write Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param data {@link RequestAccelerationMemoryIndex} data
     */
    public void writeRequestAccelerationMemoryIndex(RequestAccelerationMemoryIndex data) {
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RequestAccelerationMemoryIndexTask task = new RequestAccelerationMemoryIndexTask(bluetoothGatt, mTaskHandler, data, mRbtCallback);
            Message message = RequestAccelerationMemoryIndexTask.createWriteRequestAccelerationMemoryIndexMessage(task);
            mTaskHandler.addTask(task, message);
        }
    }

    /**
     * Write LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param data {@link LedSettingNormalState} data
     */
    public void writeLedSettingNormalState(LedSettingNormalState data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param data {@link LedSettingEventState} data
     */
    public void writeLedSettingEventState(LedSettingEventState data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_SETTING_EVENT_STATE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param data {@link LedStateOperation} data
     */
    public void writeLedStateOperation(LedStateOperation data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_STATE_OPERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Installation offset (Characteristics UUID: 0x5114)
     *
     * @param data {@link InstallationOffset} data
     */
    public void writeInstallationOffset(InstallationOffset data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, INSTALLATION_OFFSET_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param data {@link AdvertiseSetting} data
     */
    public void writeAdvertisingSetting(AdvertiseSetting data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, ADVERTISE_SETTING_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Memory reset (Characteristics UUID: 0x5116)
     *
     * @param data {@link MemoryReset} data
     */
    public void writeMemoryReset(MemoryReset data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, MEMORY_RESET_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, DateUtils.MINUTE_IN_MILLIS * 3);
    }

    /**
     * Write Mode change (Characteristics UUID: 0x5117)
     *
     * @param data {@link ModeChange} data
     */
    public void writeModeChange(ModeChange data) {
        createRbtWriteCharacteristicTask(CONTROL_SERVICE, MODE_CHANGE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, DateUtils.MINUTE_IN_MILLIS * 3);
    }

    /**
     * Write Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param data {@link AccelerationLoggerControl} data
     */
    public void writeAccelerationLoggerControl(AccelerationLoggerControl data) {
        createWriteCharacteristicTask(CONTROL_SERVICE, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, data, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Time setting (Characteristics UUID: 0x5202)
     *
     * @param data {@link TimeSetting} data
     */
    public void writeTimeSetting(TimeSetting data) {
        createWriteCharacteristicTask(TIME_SETTING_SERVICE, TIME_SETTING_CHARACTERISTIC, data, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param data {@link MemoryStorageInterval} data
     */
    public void writeMemoryStorageInterval(MemoryStorageInterval data) {
        createRbtWriteCharacteristicTask(TIME_SETTING_SERVICE, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Temperature [Sensor 1] (Characteristics UUID: 0x5211)
     *
     * @param data {@link TemperatureSensor1} data
     */
    public void writeTemperatureSensor1(TemperatureSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Temperature [Sensor 2] (Characteristics UUID: 0x5212)
     *
     * @param data {@link TemperatureSensor2} data
     */
    public void writeTemperatureSensor2(TemperatureSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Relative humidity [Sensor 1] (Characteristics UUID: 0x5213)
     *
     * @param data {@link RelativeHumiditySensor1} data
     */
    public void writeRelativeHumiditySensor1(RelativeHumiditySensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Relative humidity [Sensor 2] (Characteristics UUID: 0x5214)
     *
     * @param data {@link RelativeHumiditySensor2} data
     */
    public void writeRelativeHumiditySensor2(RelativeHumiditySensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Ambient light [Sensor 1] (Characteristics UUID: 0x5215)
     *
     * @param data {@link AmbientLightSensor1} data
     */
    public void writeAmbientLightSensor1(AmbientLightSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Ambient light [Sensor 2] (Characteristics UUID: 0x5216)
     *
     * @param data {@link AmbientLightSensor2} data
     */
    public void writeAmbientLightSensor2(AmbientLightSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Barometric pressure [Sensor 1] (Characteristics UUID: 0x5217)
     *
     * @param data {@link BarometricPressureSensor1} data
     */
    public void writeBarometricPressureSensor1(BarometricPressureSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Barometric pressure [Sensor 2] (Characteristics UUID: 0x5218)
     *
     * @param data {@link BarometricPressureSensor2} data
     */
    public void writeBarometricPressureSensor2(BarometricPressureSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Sound noise [Sensor 1] (Characteristics UUID: 0x5219)
     *
     * @param data {@link SoundNoiseSensor1} data
     */
    public void writeSoundNoiseSensor1(SoundNoiseSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Sound noise [Sensor 2] (Characteristics UUID: 0x521A)
     *
     * @param data {@link SoundNoiseSensor2} data
     */
    public void writeSoundNoiseSensor2(SoundNoiseSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern eTVOC [Sensor 1] (Characteristics UUID: 0x521B)
     *
     * @param data {@link EtvocSensor1} data
     */
    public void writeEtvocSensor1(EtvocSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern eTVOC  [Sensor 2] (Characteristics UUID: 0x521C)
     *
     * @param data {@link EtvocSensor2} data
     */
    public void writeEtvocSensor2(EtvocSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern eCO2 [Sensor 1] (Characteristics UUID: 0x521D)
     *
     * @param data {@link Eco2Sensor1} data
     */
    public void writeEco2Sensor1(Eco2Sensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern eCO2 [Sensor 2] (Characteristics UUID: 0x521E)
     *
     * @param data {@link Eco2Sensor2} data
     */
    public void writeEco2Sensor2(Eco2Sensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Discomfort index [Sensor 1] (Characteristics UUID: 0x521F)
     *
     * @param data {@link DiscomfortIndexSensor1} data
     */
    public void writeDiscomfortIndexSensor1(DiscomfortIndexSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Discomfort index [Sensor 2] (Characteristics UUID: 0x5220)
     *
     * @param data {@link DiscomfortIndexSensor2} data
     */
    public void writeDiscomfortIndexSensor2(DiscomfortIndexSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Heat stroke [Sensor 1] (Characteristics UUID: 0x5221)
     *
     * @param data {@link HeatStrokeSensor1} data
     */
    public void writeHeatStrokeSensor1(HeatStrokeSensor1 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Heat stroke [Sensor 2] (Characteristics UUID: 0x5222)
     *
     * @param data {@link HeatStrokeSensor2} data
     */
    public void writeHeatStrokeSensor2(HeatStrokeSensor2 data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern SI value [Acceleration] (Characteristics UUID: 0x5226)
     *
     * @param data {@link SiValueAcceleration} data
     */
    public void writeSiValueAcceleration(SiValueAcceleration data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SI_VALUE_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern PGA [Acceleration] (Characteristics UUID: 0x5227)
     *
     * @param data {@link PgaAcceleration} data
     */
    public void writePgaAcceleration(PgaAcceleration data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, PGA_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Write Event pattern Seismic intensity [Acceleration] (Characteristics UUID: 0x5228)
     *
     * @param data {@link SeismicIntensityAcceleration} data
     */
    public void writeSeismicIntensityAcceleration(SeismicIntensityAcceleration data) {
        createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingMemorySensingData(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingMemoryCalculationData(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingMemorySensingFlag(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingMemoryCalculationFlag(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingLatestSensingData(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingLatestCalculationData(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingLatestSensingFlag(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingLatestCalculationFlag(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingLatestAccelerationSetting(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_ACCELERATION_STATUS_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Notification setting Acceleration memory data [Header] / Acceleration memory data [Data] (Characteristics UUID: 0x5034)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     */
    public void notificationSettingAccelerationMemoryData(ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        createWriteDescriptorTask(ACCELERATION_SERVICE, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS);
    }

    /**
     * Create Rbt write characteristic task
     *
     * @param serviceUUID               service {@link UUID}
     * @param characteristicUUID        characteristic {@link UUID}
     * @param abstractRbtCharacteristic write data
     * @param waitTarget                {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_WRITING} for non memory reset write, {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING} for memory reset write
     * @param timeout                   timeout(millis)
     * @see RbtWriteCharacteristicTask
     */
    public void createRbtWriteCharacteristicTask(UUID serviceUUID, UUID characteristicUUID, AbstractRbtCharacteristic abstractRbtCharacteristic, int waitTarget, long timeout) {
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RbtWriteCharacteristicTask task = new RbtWriteCharacteristicTask(this, bluetoothGatt, mTaskHandler, serviceUUID, characteristicUUID, abstractRbtCharacteristic, waitTarget, timeout);
            Message message = WriteCharacteristicTask.createWriteCharacteristicMessage(serviceUUID, characteristicUUID, task);
            mTaskHandler.addTask(task, message);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        // gatt instance is not matched
        if (gatt != mBluetoothGatt) {
            return;
        }
        try {
            UUID uuid = characteristic.getUuid();

            // notification status check
            if (mNotificationSet.contains(uuid)) {
                if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(uuid)
                        || MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(uuid)
                        || MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(uuid)
                        || MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(uuid)) {
                    mTaskHandler.sendProcessingMessage(RequestMemoryIndexTask.createBatchNotifyMessage(uuid, characteristic.getValue()));
                } else if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(uuid)) {
                    mTaskHandler.sendProcessingMessage(RequestAccelerationMemoryIndexTask.createBatchNotifyMessage(characteristic.getValue()));
                } else {
                    super.onCharacteristicChanged(gatt, characteristic);
                }
            }
        } catch (Exception e) {
            RbtLogUtils.stackLog(e);
        }
    }
}
