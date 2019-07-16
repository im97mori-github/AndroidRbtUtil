package org.im97mori.rbt;

import java.util.UUID;

/**
 * OMRON 2JCIE-BU01 Constants
 */
@SuppressWarnings("unused")
public class RbtConstants {

    /**
     * @see org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes#DATA_TYPE_SHORTENED_LOCAL_NAME
     */
    public static final String SHORTENED_NAME_RBT = "Rbt";

    /**
     * OMRON 2JCIE-BU01 BASE UUID
     */
    public static final UUID BASE_UUID_RBT = UUID.fromString("AB700000-0A3A-11E8-BA89-0ED5F89F718B");

    /**
     * OMRON 2JCIE-BU01 BLE GATT Servies UUID
     */
    public static final class ServiceUUID {

        /**
         * 2.1. Memory Data Service (Service UUID: 0x5000)
         */
        public static final UUID MEMORY_DATA_SERVICE = UUID.fromString("ab705000-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.2. Latest Data Service (Service UUID: 0x5010)
         */
        public static final UUID LATEST_DATA_SERVICE = UUID.fromString("ab705010-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.3. Acceleration Service (Service UUID: 0x5030)
         */
        public static final UUID ACCELERATION_SERVICE = UUID.fromString("ab705030-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4. Control Service (Service UUID: 0x5110)
         */
        public static final UUID CONTROL_SERVICE = UUID.fromString("ab705110-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.5. Time Setting Service (Service UUID: 0x5200)
         */
        public static final UUID TIME_SETTING_SERVICE = UUID.fromString("ab705200-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6. Event Setting Service (Service UUID: 0x5210)
         */
        public static final UUID EVENT_SETTING_SERVICE = UUID.fromString("ab705210-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.7. Information Service ( Service UUID: 0x5400)
         */
        public static final UUID INFORMATION_SERVICE = UUID.fromString("ab705400-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.8. Generic Access Service (Service UUID: 0x1800)
         */
        public static final UUID GENERIC_ACCESS_SERVICE = UUID.fromString("00001800-0000-1000-8000-00805f9b34fb");

        /**
         * 2.9. Device Information Service (Service UUID: 0x180A)
         */
        public static final UUID DEVICE_INFORMATION_SERVICE = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
    }

    /**
     * OMRON 2JCIE-BU01 BLE GATT Characteristics UUID
     */
    public static final class CharacteristicUUID {

        // 2.1. Memory Data Service (Service UUID: 0x5000)

        /**
         * 2.1.1 Memory index information (Characteristics UUID: 0x5004)
         */
        public static final UUID MEMORY_INDEX_INFORMATION_CHARACTERISTIC = UUID.fromString("ab705004-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.2 Request memory index (Characteristics UUID: 0x5005)
         */
        public static final UUID REQUEST_MEMORY_INDEX_CHARACTERISTIC = UUID.fromString("ab705005-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.3 Memory status (Characteristics UUID: 0x5006)
         */
        public static final UUID MEMORY_STATUS_CHARACTERISTIC = UUID.fromString("ab705006-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.4 Memory sensing data (Characteristics UUID: 0x500A)
         */
        public static final UUID MEMORY_SENSING_DATA_CHARACTERISTIC = UUID.fromString("ab70500a-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.5 Memory c alculation data (Characteristics UUID: 0x500B)
         */
        public static final UUID MEMORY_CALCULATION_DATA_CHARACTERISTIC = UUID.fromString("ab70500b-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.6 Memory sensing flag (Characteristics UUID: 0x500C)
         */
        public static final UUID MEMORY_SENSING_FLAG_CHARACTERISTIC = UUID.fromString("ab70500c-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.1.7 Memory calculation flag (Characteristics UUID: 0x500D)
         */
        public static final UUID MEMORY_CALCULATION_FLAG_CHARACTERISTIC = UUID.fromString("ab70500d-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.2. Latest Data Service (Service UUID: 0x5010)

        /**
         * 2.2.1 Latest sensing data (Characteristics UUID: 0x5012)
         */
        public static final UUID LATEST_SENSING_DATA_CHARACTERISTIC = UUID.fromString("ab705012-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.2.2 Latest calculation data ( Characteristics UUID: 0x5013)
         */
        public static final UUID LATEST_CALCULATION_DATA_CHARACTERISTIC = UUID.fromString("ab705013-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.2.3 Latest sensing flag (Characteristics UUID: 0x5014)
         */
        public static final UUID LATEST_SENSING_FLAG_CHARACTERISTIC = UUID.fromString("ab705014-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.2.4 Latest calculation flag ( Characteristics UUID: 0x5015)
         */
        public static final UUID LATEST_CALCULATION_FLAG_CHARACTERISTIC = UUID.fromString("ab705015-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.2.5 Latest acceleration status (Characteristics UUID: 0x5016)
         */
        public static final UUID LATEST_ACCELERATION_STATUS_CHARACTERISTIC = UUID.fromString("ab705016-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.3. Acceleration Service (Service UUID: 0x5030)

        /**
         * 2.3.1 Vibration count (Characteristics UUID: 0x5031)
         */
        public static final UUID VIBRATION_COUNT_CHARACTERISTIC = UUID.fromString("ab705031-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.3.2 Request acceleration memory index (Characteristics UUID: 0x5032)
         */
        public static final UUID REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC = UUID.fromString("ab705032-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.3.3 Acceleration memory status (Characteristics UUID: 0x5033)
         */
        public static final UUID ACCELERATION_MEMORY_STATUS_CHARACTERISTIC = UUID.fromString("ab705033-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034)
         * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034)
         */
        public static final UUID ACCELERATION_MEMORY_DATA_CHARACTERISTIC = UUID.fromString("ab705034-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.4. Control Service (Service UUID: 0x5110)

        /**
         * 2.4.1 LED setting [normal state] (Characteristics UUID: 0x5111)
         */
        public static final UUID LED_SETTING_NORMAL_STATE_CHARACTERISTIC = UUID.fromString("ab705111-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.2 LED setting [event state] (Characteristics UUID: 0x5112)
         */
        public static final UUID LED_SETTING_EVENT_STATE_CHARACTERISTIC = UUID.fromString("ab705112-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.3 LED state [ operation] (Characteristics UUID: 0x5113)
         */
        public static final UUID LED_STATE_OPERATION_CHARACTERISTIC = UUID.fromString("ab705113-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.4 Installation offset (Characteristics UUID: 0x5114)
         */
        public static final UUID INSTALLATION_OFFSET_CHARACTERISTIC = UUID.fromString("ab705114-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.5 Advertise setting (Characteristics UUID: 0x5115)
         */
        public static final UUID ADVERTISE_SETTING_CHARACTERISTIC = UUID.fromString("ab705115-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.6 Memory reset (Characteristics UUID: 0x5116)
         */
        public static final UUID MEMORY_RESET_CHARACTERISTIC = UUID.fromString("ab705116-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.7 Mode change (Characteristics UUID: 0x5117)
         */
        public static final UUID MODE_CHANGE_CHARACTERISTIC = UUID.fromString("ab705117-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.8 Acceleration logger control (Characteristics UUID: 0x5118)
         */
        public static final UUID ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC = UUID.fromString("ab705118-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.4.9 Acceleration logger status (Characteristics UUID: 0x5119)
         */
        public static final UUID ACCELERATION_LOGGER_STATUS_CHARACTERISTIC = UUID.fromString("ab705119-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.5. Time Setting Service (Service UUID: 0x5200)

        /**
         * 2.5.1 Latest time counter (Characteristics UUID: 0x5201)
         */
        public static final UUID LATEST_TIME_COUNTER_CHARACTERISTIC = UUID.fromString("ab705201-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.5.2 Time setting (Characteristics UUID: 0x5202)
         */
        public static final UUID TIME_SETTING_CHARACTERISTIC = UUID.fromString("ab705202-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.5.3 Memory storage interval (Characteristics UUID: 0x5203)
         */
        public static final UUID MEMORY_STORAGE_INTERVAL_CHARACTERISTIC = UUID.fromString("ab705203-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.6. Event Setting Service (Service UUID: 0x5210)

        /**
         * 2.6.1 Event pattern Temperature [Sensor 1] (Characteristics UUID: 0x5211)
         */
        public static final UUID TEMPERATURE_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705211-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Temperature [Sensor 2] (Characteristics UUID: 0x5212)
         */
        public static final UUID TEMPERATURE_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705212-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Relative humidity [Sensor 1] (Characteristics UUID: 0x5213)
         */
        public static final UUID RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705213-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Relative humidity [Sensor 2] (Characteristics UUID: 0x5214)
         */
        public static final UUID RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705214-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Ambient light [Sensor 1] (Characteristics UUID: 0x5215)
         */
        public static final UUID AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705215-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Ambient light [Sensor 2] (Characteristics UUID: 0x5216)
         */
        public static final UUID AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705216-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Barometric pressure [Sensor 1] (Characteristics UUID: 0x5217)
         */
        public static final UUID BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705217-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Barometric pressure [Sensor 2] (Characteristics UUID: 0x5218)
         */
        public static final UUID BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705218-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Sound noise [Sensor 1] (Characteristics UUID: 0x5219)
         */
        public static final UUID SOUND_NOISE_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705219-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Sound noise [Sensor 2] (Characteristics UUID: 0x521A)
         */
        public static final UUID SOUND_NOISE_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab70521a-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern eTVOC [Sensor 1] (Characteristics UUID: 0x521B)
         */
        public static final UUID ETVOC_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab70521b-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern eTVOC  [Sensor 2] (Characteristics UUID: 0x521C)
         */
        public static final UUID ETVOC_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab70521c-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern eCO2 [Sensor 1] (Characteristics UUID: 0x521D)
         */
        public static final UUID ECO2_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab70521d-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern eCO2 [Sensor 2] (Characteristics UUID: 0x521E)
         */
        public static final UUID ECO2_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab70521e-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Discomfort index [Sensor 1] (Characteristics UUID: 0x521F)
         */
        public static final UUID DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab70521f-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Discomfort index [Sensor 2] (Characteristics UUID: 0x5220)
         */
        public static final UUID DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705220-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.1 Event pattern Heat stroke [Sensor 1] (Characteristics UUID: 0x5221)
         */
        public static final UUID HEAT_STROKE_SENSOR_1_CHARACTERISTIC = UUID.fromString("ab705221-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.2 Event pattern Heat stroke [Sensor 2] (Characteristics UUID: 0x5222)
         */
        public static final UUID HEAT_STROKE_SENSOR_2_CHARACTERISTIC = UUID.fromString("ab705222-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.3 Event pattern SI value [Acceleration] (Characteristics UUID: 0x5226)
         */
        public static final UUID SI_VALUE_ACCELERATION_CHARACTERISTIC = UUID.fromString("ab705226-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.3 Event pattern PGA [Acceleration] (Characteristics UUID: 0x5227)
         */
        public static final UUID PGA_ACCELERATION_CHARACTERISTIC = UUID.fromString("ab705227-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.6.3 Event pattern Seismic intensity [Acceleration] (Characteristics UUID: 0x5228)
         */
        public static final UUID SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC = UUID.fromString("ab705228-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.7. Information Service (Service UUID: 0x5400)

        /**
         * 2.7.1 Error status (Characteristics UUID: 0x5401)
         */
        public static final UUID ERROR_STATUS_CHARACTERISTIC = UUID.fromString("ab705401-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.7.2 Mounting orientation (Characteristics UUID: 0x5402)
         */
        public static final UUID MOUNTING_ORIENTATION_CHARACTERISTIC = UUID.fromString("ab705402-0a3a-11e8-ba89-0ed5f89f718b");

        /**
         * 2.7.3 FLASH memory status (Characteristics UUID: 0x5403)
         */
        public static final UUID FLASH_MEMORY_STATUS_CHARACTERISTIC = UUID.fromString("ab705403-0a3a-11e8-ba89-0ed5f89f718b");

        // 2.8. Generic Access Service (Service UUID: 0x1800)

        /**
         * 2.8.1 Device name (Characteristics UUID: 0x2A00)
         */
        public static final UUID DEVICE_NAME_CHARACTERISTIC = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb");

        /**
         * 2.8.2 Appearance (Characteristics UUID: 0x2A01)
         */
        public static final UUID APPEARANCE_CHARACTERISTIC = UUID.fromString("00002a01-0000-1000-8000-00805f9b34fb");

        /**
         * 2.8.3 Peripheral preferred c onnection parameters (Characteristics UUID: 0x2A04)
         */
        public static final UUID PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC = UUID.fromString("00002a04-0000-1000-8000-00805f9b34fb");

        /**
         * 2.8.4 Central address resolution (Characteristics UUID: 0x2AA6)
         */
        public static final UUID CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC = UUID.fromString("00002aa6-0000-1000-8000-00805f9b34fb");

        // 2.9. Device Information Service (Service UUID: 0x180A)

        /**
         * 2.9.1 Model number string (Characteristics UUID: 0x2A24)
         */
        public static final UUID MODEL_NUMBER_STRING_CHARACTERISTIC = UUID.fromString("00002a24-0000-1000-8000-00805f9b34fb");

        /**
         * 2.9.2 Serial number string (Characteristics UUID: 0x2A25)
         */
        public static final UUID SERIAL_NUMBER_STRING_CHARACTERISTIC = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");

        /**
         * 2.9.3 Firmware revision string (Characteristics UUID: 0x2A26)
         */
        public static final UUID FIRMWARE_REVISION_STRING_CHARACTERISTIC = UUID.fromString("00002a26-0000-1000-8000-00805f9b34fb");

        /**
         * 2.9.4 Hardware revision string (Characteristics UUID: 0x2A27)
         */
        public static final UUID HARDWARE_REVISION_STRING_CHARACTERISTIC = UUID.fromString("00002a27-0000-1000-8000-00805f9b34fb");

        /**
         * 2.9.5 Manufacturer name string (Characteristics UUID: 0x2A28)
         */
        public static final UUID MANUFACTURER_NAME_STRING_CHARACTERISTIC = UUID.fromString("00002a29-0000-1000-8000-00805f9b34fb");

    }

    /**
     * OMRON 2JCIE-BU01 BLE GATT Descriptors UUID
     */
    public static final class DescriptorUUID {

        /**
         * Core Specification v5.1 Vol 3 Part G 3.3.3.3
         */
        public static final UUID CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    }

    /**
     * 3. BLE Advertising packet
     */
    public static final class RbtAdvertisingDataType {

        /**
         * 3.1. Sensor data
         */
        public static final int DATA_TYPE_SENSOR_DATA = 0x01;

        /**
         * 3.2. Calculation data
         */
        public static final int DATA_TYPE_CALCULATION_DATA = 0x02;

        /**
         * 3,3. Sensor data & Calculation data (Scan rsp)
         */
        public static final int DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA = 0x03;

        /**
         * 3.4. Sensor flag & Calculation flag (Scan rsp)
         */
        public static final int DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG = 0x04;

        /**
         * 3.5. Serial number
         */
        public static final int DATA_TYPE_SERIAL_NUMBER = 0x05;

    }

    /**
     * 5.1. Output range
     */
    public static final class OutputRange {

        /**
         * Temperature Unit 0.01 degC
         */
        public static final double OUTPUT_RANGE_TEMPERATURE_UNIT = 0.01d;

        /**
         * Relative humidity Unit 0.01 %RH
         */
        public static final double OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT = 0.01d;

        /**
         * Ambient light Unit 1 lx
         */
        public static final double OUTPUT_RANGE_AMBIENT_LIGHT_UNIT = 1d;

        /**
         * Barometric pressure Unit 0.001 hPa
         */
        public static final double OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT = 0.001d;

        /**
         * Sound noise Unit 0.01 dB
         */
        public static final double OUTPUT_RANGE_SOUND_NOISE_UNIT = 0.01d;

        /**
         * eTVOC Unit 1 ppb
         */
        public static final double OUTPUT_RANGE_ETVOC_UNIT = 1d;

        /**
         * eCO2 Unit 1 ppm
         */
        public static final double OUTPUT_RANGE_ECO2_UNIT = 1d;

        /**
         * Discomfort index Unit 0.01
         */
        public static final double OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT = 0.01d;

        /**
         * Heat stroke Unit 0.01 degC
         */
        public static final double OUTPUT_RANGE_HEAT_STROKE_UNIT = 0.01d;

        /**
         * Acceleration Unit 0.1 gal
         */
        public static final double OUTPUT_RANGE_ACCELERATION_UNIT = 0.1d;

        /**
         * SI value Unit 0.1 kine
         */
        public static final double OUTPUT_RANGE_SI_VALUE_UNIT = 0.1d;

        /**
         * PGA Unit 0.1 gal
         */
        public static final double OUTPUT_RANGE_PGA_UNIT = 0.1d;

        /**
         * Seismic intensity Unit 0.001
         */
        public static final double OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT = 0.001d;

        /**
         * SI value calculation axis:YZ-axis
         */
        public static final int SI_VALUE_CALCULATION_AXIS_YZ_AXIS_BIT = 0x00;

        /**
         * SI value calculation axis:XZ-axis
         */
        public static final int SI_VALUE_CALCULATION_AXIS_XZ_AXIS_BIT = 0x01;

        /**
         * SI value calculation axis:XY-axis
         */
        public static final int SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT = 0x02;

        /**
         * Vibration information:NONE
         */
        public static final int VIBRATION_INFORMATION_NONE_BIT = 0x00;

        /**
         * Vibration information:during vibration (Earthquake judgment in progress)
         */
        public static final int VIBRATION_INFORMATION_DURING_VIBRATION_BIT = 0x01;

        /**
         * Vibration information:during earthquake
         */
        public static final int VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT = 0x02;

    }

    /**
     * 5.2. Event enable/disable [Sensor]
     */
    public static final class EventEnableDisableSensor {

        /**
         * Simple threshold [upper limit] 1
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_1 = 0b00000000_00000001;

        /**
         * Simple threshold [upper limit] 2
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_2 = 0b00000000_00000010;

        /**
         * Simple threshold [lower limit] 1
         */
        public static final int SIMPLE_THRESHOLD_LOWER_LIMIT_1 = 0b00000000_00000100;

        /**
         * Simple threshold [lower limit] 2
         */
        public static final int SIMPLE_THRESHOLD_LOWER_LIMIT_2 = 0b00000000_00001000;

        /**
         * Change threshold [rise] 1
         */
        public static final int CHANGE_THRESHOLD_RISE_1 = 0b00000000_00010000;

        /**
         * Change threshold [rise] 2
         */
        public static final int CHANGE_THRESHOLD_RISE_2 = 0b00000000_00100000;

        /**
         * Change threshold [decline] 1
         */
        public static final int CHANGE_THRESHOLD_DECLINE_1 = 0b00000000_01000000;

        /**
         * Change threshold [decline] 2
         */
        public static final int CHANGE_THRESHOLD_DECLINE_2 = 0b00000000_10000000;

        /**
         * Average value threshold [upper]
         */
        public static final int AVERAGE_VALUE_THRESHOLD_UPPER = 0b00000001_00000000;

        /**
         * Average value threshold [lower]
         */
        public static final int AVERAGE_VALUE_THRESHOLD_LOWER = 0b00000010_00000000;

        /**
         * Peak to Peak threshold [upper]
         */
        public static final int PEAK_TO_PEAK_THRESHOLD_UPPER = 0b00000100_00000000;

        /**
         * Peak to Peak threshold [lower]
         */
        public static final int PEAK_TO_PEAK_THRESHOLD_LOWER = 0b00001000_00000000;

        /**
         * Interval difference threshold [rise]
         */
        public static final int INTERVAL_DIFFERENCE_THRESHOLD_RISE = 0b00010000_00000000;

        /**
         * Interval difference threshold [decline]
         */
        public static final int INTERVAL_DIFFERENCE_THRESHOLD_DECLINE = 0b00100000_00000000;

        /**
         * Base difference threshold [upper]
         */
        public static final int BASE_DIFFERENCE_THRESHOLD_UPPER = 0b01000000_00000000;

        /**
         * Base difference threshold [lower]
         */
        public static final int BASE_DIFFERENCE_THRESHOLD_LOWER = 0b10000000_00000000;

    }

    /**
     * 5.2. Event enable/disable [Acceleration]
     */
    public static final class EventEnableDisableAcceleration {

        /**
         * Simple threshold [upper limit] 1
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_1 = 0b00000001;

        /**
         * Simple threshold [ upper limit] 2
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_2 = 0b00000010;

        /**
         * Change threshold [rise] 1
         */
        public static final int CHANGE_THRESHOLD_RISE_1 = 0b00001000;

        /**
         * Change threshold [rise] 2
         */
        public static final int CHANGE_THRESHOLD_RISE_2 = 0b00010000;

    }

    /**
     * 5.3. Event flag [Sensor]
     */
    public static final class EventFlagSensor {


        /**
         * Simple threshold [upper limit] 1
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_1 = 0b00000000_00000001;

        /**
         * Simple threshold [upper limit] 2
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_2 = 0b00000000_00000010;

        /**
         * Simple threshold [lower limit] 1
         */
        public static final int SIMPLE_THRESHOLD_LOWER_LIMIT_1 = 0b00000000_00000100;

        /**
         * Simple threshold [lower limit] 2
         */
        public static final int SIMPLE_THRESHOLD_LOWER_LIMIT_2 = 0b00000000_00001000;

        /**
         * Change threshold [rise] 1
         */
        public static final int CHANGE_THRESHOLD_RISE_1 = 0b00000000_00010000;

        /**
         * Change threshold [rise] 2
         */
        public static final int CHANGE_THRESHOLD_RISE_2 = 0b00000000_00100000;

        /**
         * Change threshold [decline] 1
         */
        public static final int CHANGE_THRESHOLD_DECLINE_1 = 0b00000000_01000000;

        /**
         * Change threshold [decline] 2
         */
        public static final int CHANGE_THRESHOLD_DECLINE_2 = 0b00000000_10000000;

        /**
         * Average value threshold [upper]
         */
        public static final int AVERAGE_VALUE_THRESHOLD_UPPER = 0b00000001_00000000;

        /**
         * Average value threshold [lower]
         */
        public static final int AVERAGE_VALUE_THRESHOLD_LOWER = 0b00000010_00000000;

        /**
         * Peak to Peak threshold [upper]
         */
        public static final int PEAK_TO_PEAK_THRESHOLD_UPPER = 0b00000100_00000000;

        /**
         * Peak to Peak threshold [lower]
         */
        public static final int PEAK_TO_PEAK_THRESHOLD_LOWER = 0b00001000_00000000;

        /**
         * Interval differencethreshold [rise]
         */
        public static final int INTERVAL_DIFFERENCE_THRESHOLD_RISE = 0b00010000_00000000;

        /**
         * Interval differencethreshold [decline]
         */
        public static final int INTERVAL_DIFFERENCE_THRESHOLD_DECLINE = 0b00100000_00000000;

        /**
         * Base difference threshold [upper]
         */
        public static final int BASE_DIFFERENCE_THRESHOLD_UPPER = 0b01000000_00000000;

        /**
         * Base difference threshold [lower]
         */
        public static final int BASE_DIFFERENCE_THRESHOLD_LOWER = 0b10000000_00000000;

    }

    /**
     * 5.3. Event flag [Acceleration]
     */
    public static final class EventFlagAcceleration {

        /**
         * Simple threshold [upper limit] 1
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_1 = 0b00000001;

        /**
         * Simple threshold [ upper limit] 2
         */
        public static final int SIMPLE_THRESHOLD_UPPER_LIMIT_2 = 0b00000010;

        /**
         * Change threshold [rise] 1
         */
        public static final int CHANGE_THRESHOLD_RISE_1 = 0b00001000;

        /**
         * Change threshold [rise] 2
         */
        public static final int CHANGE_THRESHOLD_RISE_2 = 0b00010000;

    }

    /**
     * 5.4. Event threshold
     */
    public static final class EventThreshold {

        /**
         * Temperature Unit 0.01 degC
         */
        public static final double EVENT_THRESHOLD_TEMPERATURE_UNIT = 0.01d;

        /**
         * Relative humidity Unit 0.01 %RH
         */
        public static final double EVENT_THRESHOLD_RELATIVE_HUMIDITY_UNIT = 0.01d;

        /**
         * Ambient light Unit 1 lx
         */
        public static final double EVENT_THRESHOLD_AMBIENT_LIGHT_UNIT = 1d;

        /**
         * <p>
         * Barometric pressure Unit1 0.1 hPa
         * <p>
         * Simple threshold [upper limit] 1
         * Simple threshold [upper limit] 2
         * Simple threshold [lower limit] 1
         * Simple threshold [lower limit] 2
         * Average value threshold [upper]
         * Average value threshold [lower]
         * </p>
         */
        public static final double EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1 = 0.1d;

        /**
         * <p>
         * Barometric pressure Unit2 0.1 hPa
         * <p>
         * Change threshold [rise] 1
         * Change threshold [rise] 2
         * Change threshold [decline] 1
         * Change threshold [decline] 2
         * Peak to Peak threshold [upper]
         * Peak to Peak threshold [lower]
         * Interval difference threshold [rise]
         * Interval difference threshold [decline]
         * Base difference threshold [upper]
         * Base difference threshold [lower]
         * </p>
         */
        public static final double EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2 = 0.001d;

        /**
         * Sound noise Unit 0.01 dB
         */
        public static final double EVENT_THRESHOLD_SOUND_NOISE_UNIT = 0.01d;

        /**
         * eTVOC Unit 1 ppb
         */
        public static final double EVENT_THRESHOLD_ETVOC_UNIT = 1d;

        /**
         * eCO2 Unit 1 ppm
         */
        public static final double EVENT_THRESHOLD_ECO2_UNIT = 1d;

        /**
         * Discomfort index Unit 0.01
         */
        public static final double EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT = 0.01d;

        /**
         * Heat stroke Unit 0.01 degC
         */
        public static final double EVENT_THRESHOLD_HEAT_STROKE_UNIT = 0.01d;

        /**
         * SI value Unit 0.1 kine
         */
        public static final double EVENT_THRESHOLD_SI_VALUE_UNIT = 0.1d;

        /**
         * PGA Unit 0.1 gal
         */
        public static final double EVENT_THRESHOLD_PGA_UNIT = 0.1d;

        /**
         * Seismic intensity Unit 0.001
         */
        public static final double EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT = 0.001d;

    }

}
