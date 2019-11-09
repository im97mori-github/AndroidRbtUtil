package org.im97mori.rbt.ble.ad.filter;

import org.im97mori.rbt.RbtConstants;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorFlagAndCalculationFlag;
import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_AMBIENT_LIGHT;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_BAROMETRIC_PRESSURE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_DISCOMFORT_INDEX;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ECO2;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ETVOC;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_HEAT_STROKE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_PGA;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_RELATIVE_HUMIDITY;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SEISMIC_INTENSITY;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SEQUENCE_NUMBER;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SI_VALUE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SOUND_NOISE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_TEMPERATURE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_THAN;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_THAN;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SensorFlagAndCalculationFlagFilterTest {

    @Test
    public void test_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = (byte) 0x02; // AD 2
        actualData[ 8] = (byte) 0x00; // Sequence number
        actualData[ 9] = (byte) 0x00; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        actualData[11] = (byte) 0x60; // Heat stroke
        actualData[12] = (byte) 0xf0; // Heat stroke
        actualData[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        actualData[14] = (byte) 0x00; // SI value
        actualData[15] = (byte) 0x00; // SI value
        actualData[16] = (byte) 0x00; // PGA
        actualData[17] = (byte) 0x00; // PGA
        actualData[18] = (byte) 0x00; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        actualData[20] = (byte) 0xe0; // Acceleration (X-axis)
        actualData[21] = (byte) 0xb1; // Acceleration (X-axis)
        actualData[22] = (byte) 0xe0; // Acceleration (Y-axis)
        actualData[23] = (byte) 0xb1; // Acceleration (Y-axis)
        actualData[24] = (byte) 0xe0; // Acceleration (Z-axis)
        actualData[25] = (byte) 0xb1; // Acceleration (Z-axis)
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(null);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_002() {
        //@formatter:off
        byte[] expectData = new byte[62];
        expectData[ 0] = (byte) 0x02; // AD 1
        expectData[ 1] = (byte) 0x01; // AD 1
        expectData[ 2] = (byte) 0x06; // AD 1
        expectData[ 3] = (byte) 0x16; // AD 2
        expectData[ 4] = (byte) 0xff; // AD 2
        expectData[ 5] = (byte) 0xd5; // AD 2
        expectData[ 6] = (byte) 0x02; // AD 2
        expectData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        expectData[26] = (byte) 0x04; // AD 3
        expectData[27] = (byte) 0x08; // AD 3
        expectData[28] = (byte) 'R'; // AD 3
        expectData[29] = (byte) 'b'; // AD 3
        expectData[30] = (byte) 't'; // AD 3
        expectData[31] = (byte) 0x1e; // AD 4
        expectData[32] = (byte) 0xff; // AD 4
        expectData[33] = (byte) 0xd5; // AD 4
        expectData[34] = (byte) 0x02; // AD 4
        expectData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = (byte) 0x02; // AD 2
        actualData[ 8] = (byte) 0x00; // Sequence number
        actualData[ 9] = (byte) 0x00; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        actualData[11] = (byte) 0x60; // Heat stroke
        actualData[12] = (byte) 0xf0; // Heat stroke
        actualData[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        actualData[14] = (byte) 0x00; // SI value
        actualData[15] = (byte) 0x00; // SI value
        actualData[16] = (byte) 0x00; // PGA
        actualData[17] = (byte) 0x00; // PGA
        actualData[18] = (byte) 0x00; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        actualData[20] = (byte) 0xe0; // Acceleration (X-axis)
        actualData[21] = (byte) 0xb1; // Acceleration (X-axis)
        actualData[22] = (byte) 0xe0; // Acceleration (Y-axis)
        actualData[23] = (byte) 0xb1; // Acceleration (Y-axis)
        actualData[24] = (byte) 0xe0; // Acceleration (Z-axis)
        actualData[25] = (byte) 0xb1; // Acceleration (Z-axis)
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag.CREATOR.createFromByteArray(expectData));

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(null);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_004() {
        //@formatter:off
        byte[] expectData = new byte[46];
        expectData[ 0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        expectData[ 1] = (byte) 0xff; // Sequence number
        expectData[ 2] = (byte) 0x01; // Temperature flag
        expectData[ 3] = (byte) 0x02; // Temperature flag
        expectData[ 4] = (byte) 0x03; // Relative humidity flag
        expectData[ 5] = (byte) 0x04; // Relative humidity flag
        expectData[ 6] = (byte) 0x05; // Ambient light flag
        expectData[ 7] = (byte) 0x06; // Ambient light flag
        expectData[ 8] = (byte) 0x07; // Barometric pressure flag
        expectData[ 9] = (byte) 0x08; // Barometric pressure flag
        expectData[10] = (byte) 0x09; // Sound noise flag
        expectData[11] = (byte) 0x0A; // Sound noise flag
        expectData[12] = (byte) 0x0B; // eTVOC flag
        expectData[13] = (byte) 0x0C; // eTVOC flag
        expectData[14] = (byte) 0x0D; // eCO2 flag
        expectData[15] = (byte) 0x0E; // eCO2 flag
        expectData[16] = (byte) 0xFF; // Reserve for Future Use
        expectData[17] = (byte) 0xFF; // Reserve for Future Use
        expectData[18] = (byte) 0xFF; // Reserve for Future Use
        expectData[19] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        expectData[20] = (byte) 0xff; // Sequence number
        expectData[21] = (byte) 0x10; // Discomfort index flag
        expectData[22] = (byte) 0x11; // Discomfort index flag
        expectData[23] = (byte) 0x12; // Heat stroke flag
        expectData[24] = (byte) 0x13; // Heat stroke flag
        expectData[25] = (byte) 0x14; // SI value flag
        expectData[26] = (byte) 0x15; // PGA flag
        expectData[27] = (byte) 0x16; // Seismic intensity flag
        expectData[28] = (byte) 0xff; // Reserve for Future Use
        expectData[29] = (byte) 0xff; // Reserve for Future Use
        expectData[30] = (byte) 0xff; // Reserve for Future Use
        expectData[31] = (byte) 0xff; // Reserve for Future Use
        expectData[32] = (byte) 0xff; // Reserve for Future Use
        expectData[33] = (byte) 0xff; // Reserve for Future Use
        expectData[34] = (byte) 0xff; // Reserve for Future Use
        expectData[35] = (byte) 0xff; // Reserve for Future Use
        expectData[36] = (byte) 0xff; // Reserve for Future Use
        expectData[37] = (byte) 0xff; // Reserve for Future Use
        expectData[38] = (byte) 0xff; // Reserve for Future Use
        expectData[39] = (byte) 0xff; // Reserve for Future Use
        expectData[40] = (byte) 0xff; // Reserve for Future Use
        expectData[41] = (byte) 0xff; // Reserve for Future Use
        expectData[42] = (byte) 0xff; // Reserve for Future Use
        expectData[43] = (byte) 0xff; // Reserve for Future Use
        expectData[44] = (byte) 0xff; // Reserve for Future Use
        expectData[45] = (byte) 0xff; // Reserve for Future Use

        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        //@formatter:on

        System.arraycopy(expectData, 0, actualData, 7, 19);
        System.arraycopy(expectData, 19, actualData, 35, 27);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag.CREATOR.createFromByteArray(expectData));

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void temperatureTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[ 9] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Temperature flag
        actualData[10] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_TEMPERATURE, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void relativeHumidityTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[11] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Relative humidity flag
        actualData[12] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Relative humidity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_RELATIVE_HUMIDITY, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void ambientLightTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[13] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Ambient light flag
        actualData[14] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Ambient light flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_AMBIENT_LIGHT, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void barometricPressureTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[15] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        actualData[16] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void soundNoiseTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[17] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Sound noise flag
        actualData[18] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Sound noise flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SOUND_NOISE, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void etvocTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[19] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // eTVOC flag
        actualData[20] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // eTVOC flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ETVOC, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void eco2Test_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[21] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // eCO2 flag
        actualData[22] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // eCO2 flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_ECO2, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[37] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        actualData[38] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_DISCOMFORT_INDEX, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR, RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR, RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR, RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_015() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_016() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[39] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        actualData[40] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_HEAT_STROKE, TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR, RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[41] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1; // SI value flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SI_VALUE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[41] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2; // SI value flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SI_VALUE, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[41] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1; // SI value flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SI_VALUE, TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[41] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2; // SI value flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SI_VALUE, TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[42] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1; // PGA flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_PGA, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[42] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2; // PGA flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_PGA, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[42] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1; // PGA flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_PGA, TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[42] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2; // PGA flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_PGA, TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[43] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1; // Seismic intensity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEISMIC_INTENSITY, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[43] = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2; // Seismic intensity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEISMIC_INTENSITY, TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION, RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[43] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1; // Seismic intensity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEISMIC_INTENSITY, TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        actualData[43] = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2; // Seismic intensity flag
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorFlagAndCalculationFlagFilter filter = new SensorFlagAndCalculationFlagFilter(TARGET_SEISMIC_INTENSITY, TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION, RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

}
