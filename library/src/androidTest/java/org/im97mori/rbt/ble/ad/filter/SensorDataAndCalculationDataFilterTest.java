package org.im97mori.rbt.ble.ad.filter;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorDataAndCalculationData;
import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_VIBRATION_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_X_AXIS;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_Y_AXIS;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_Z_AXIS;
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
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_VIBRATION_INFORMATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_THAN;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_THAN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SensorDataAndCalculationDataFilterTest {

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
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(null);

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
        expectData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        expectData[26] = (byte) 0x04; // AD 3
        expectData[27] = (byte) 0x08; // AD 3
        expectData[28] = (byte) 'R'; // AD 3
        expectData[29] = (byte) 'b'; // AD 3
        expectData[30] = (byte) 't'; // AD 3
        expectData[31] = (byte) 0x1e; // AD 4
        expectData[32] = (byte) 0xff; // AD 4
        expectData[33] = (byte) 0xd5; // AD 4
        expectData[34] = (byte) 0x02; // AD 4
        expectData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;

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
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(SensorDataAndCalculationData.CREATOR.createFromByteArray(expectData));

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(null);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_004() {
        //@formatter:off
        byte[] expectData = new byte[38];
        expectData[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        expectData[ 1] = (byte) 0x00; // Sequence number
        expectData[ 2] = (byte) 0x60; // Temperature
        expectData[ 3] = (byte) 0xf0; // Temperature
        expectData[ 4] = (byte) 0x00; // Relative humidity
        expectData[ 5] = (byte) 0x00; // Relative humidity
        expectData[ 6] = (byte) 0x00; // Ambient light
        expectData[ 7] = (byte) 0x00; // Ambient light
        expectData[ 8] = (byte) 0xe0; // Barometric pressure
        expectData[ 9] = (byte) 0x93; // Barometric pressure
        expectData[10] = (byte) 0x04; // Barometric pressure
        expectData[11] = (byte) 0x00; // Barometric pressure
        expectData[12] = (byte) 0xe4; // Sound noise
        expectData[13] = (byte) 0x0c; // Sound noise
        expectData[14] = (byte) 0x00; // eTVOC
        expectData[15] = (byte) 0x00; // eTVOC
        expectData[16] = (byte) 0x90; // eCO2
        expectData[17] = (byte) 0x01; // eCO2
        expectData[18] = (byte) 0xFF; // Reserve for Future Use
        expectData[19] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        expectData[20] = (byte) 0xff; // Sequence number
        expectData[21] = (byte) 0x10; // Discomfort index
        expectData[22] = (byte) 0x27; // Discomfort index
        expectData[23] = (byte) 0xd4; // Heat stroke
        expectData[24] = (byte) 0x30; // Heat stroke
        expectData[25] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        expectData[26] = (byte) 0xff; // SI value
        expectData[27] = (byte) 0xff; // SI value
        expectData[28] = (byte) 0xff; // PGA
        expectData[29] = (byte) 0xff; // PGA
        expectData[30] = (byte) 0xff; // Seismic intensity
        expectData[31] = (byte) 0xff; // Seismic intensity
        expectData[32] = (byte) 0x20; // Acceleration (X-axis)
        expectData[33] = (byte) 0x4e; // Acceleration (X-axis)
        expectData[34] = (byte) 0x20; // Acceleration (Y-axis)
        expectData[35] = (byte) 0x4e; // Acceleration (Y-axis)
        expectData[36] = (byte) 0x20; // Acceleration (Z-axis)
        expectData[37] = (byte) 0x4e; // Acceleration (Z-axis)

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
        System.arraycopy(expectData, 19, actualData, 35, 19);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(SensorDataAndCalculationData.CREATOR.createFromByteArray(expectData));

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 8] = (byte) 0x01; // Sequence number
        actualData[36] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[ 9] = (byte) 0x01; // Temperature
        actualData[10] = (byte) 0x00; // Temperature
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[11] = (byte) 0x01; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[13] = (byte) 0x01; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[15] = (byte) 0x01; // Barometric pressure
        actualData[16] = (byte) 0x00; // Barometric pressure
        actualData[17] = (byte) 0x00; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[19] = (byte) 0x01; // Sound noise
        actualData[20] = (byte) 0x00; // Sound noise
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[21] = (byte) 0x01; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ETVOC, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[23] = (byte) 0x01; // eCO2
        actualData[24] = (byte) 0x00; // eCO2
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ECO2, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[37] = (byte) 0x01; // Discomfort index
        actualData[38] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[39] = (byte) 0x01; // Heat stroke
        actualData[40] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[41] = VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_NONE_BIT);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[41] = VIBRATION_INFORMATION_DURING_VIBRATION_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_DURING_VIBRATION_BIT);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[41] = VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[42] = (byte) 0x01; // SI value
        actualData[43] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[44] = (byte) 0x01; // PGA
        actualData[45] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 2);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_EQUAL, 1);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 0);

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[46] = (byte) 0x01; // Seismic intensity
        actualData[47] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[48] = (byte) 0x01; // Acceleration (X-axis)
        actualData[49] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[50] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[51] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[62];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[31] = (byte) 0x1e; // AD 4
        actualData[32] = (byte) 0xff; // AD 4
        actualData[33] = (byte) 0xd5; // AD 4
        actualData[34] = (byte) 0x02; // AD 4
        actualData[35] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        actualData[52] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[53] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SensorDataAndCalculationDataFilter filter = new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }


}
