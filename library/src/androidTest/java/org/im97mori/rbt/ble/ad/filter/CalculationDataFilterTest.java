package org.im97mori.rbt.ble.ad.filter;

import org.im97mori.rbt.ble.ad.CalculationData;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_VIBRATION_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_X_AXIS;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_Y_AXIS;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ACCELERATION_Z_AXIS;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_DISCOMFORT_INDEX;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_HEAT_STROKE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_PGA;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SEISMIC_INTENSITY;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SEQUENCE_NUMBER;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SI_VALUE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_VIBRATION_INFORMATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_GREATER_THAN;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_EQUAL;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_LESSER_THAN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CalculationDataFilterTest {

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
        actualData[ 7] = DATA_TYPE_SENSOR_DATA;
        actualData[ 8] = (byte) 0x00; // Sequence number
        actualData[ 9] = (byte) 0x60; // Temperature
        actualData[10] = (byte) 0xf0; // Temperature
        actualData[11] = (byte) 0x00; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        actualData[13] = (byte) 0x00; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        actualData[15] = (byte) 0xe0; // Barometric pressure
        actualData[16] = (byte) 0x93; // Barometric pressure
        actualData[17] = (byte) 0x04; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        actualData[19] = (byte) 0xe4; // Sound noise
        actualData[20] = (byte) 0x0c; // Sound noise
        actualData[21] = (byte) 0x00; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        actualData[23] = (byte) 0x90; // eCO2
        actualData[24] = (byte) 0x01; // eCO2
        actualData[25] = (byte) 0xFF; // Reserve for Future Use
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(null);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_002() {
        //@formatter:off
        byte[] expectData = new byte[19];
        expectData[ 0] = DATA_TYPE_CALCULATION_DATA;
        expectData[ 1] = (byte) 0x00; // Sequence number
        expectData[ 2] = (byte) 0x00; // Discomfort index
        expectData[ 3] = (byte) 0x00; // Discomfort index
        expectData[ 4] = (byte) 0x60; // Heat stroke
        expectData[ 5] = (byte) 0xf0; // Heat stroke
        expectData[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        expectData[ 7] = (byte) 0x00; // SI value
        expectData[ 8] = (byte) 0x00; // SI value
        expectData[ 9] = (byte) 0x00; // PGA
        expectData[10] = (byte) 0x00; // PGA
        expectData[11] = (byte) 0x00; // Seismic intensity
        expectData[12] = (byte) 0x00; // Seismic intensity
        expectData[13] = (byte) 0xe0; // Acceleration (X-axis)
        expectData[14] = (byte) 0xb1; // Acceleration (X-axis)
        expectData[15] = (byte) 0xe0; // Acceleration (Y-axis)
        expectData[16] = (byte) 0xb1; // Acceleration (Y-axis)
        expectData[17] = (byte) 0xe0; // Acceleration (Z-axis)
        expectData[18] = (byte) 0xb1; // Acceleration (Z-axis)

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = (byte) 0x01; // AD 2
        actualData[ 8] = (byte) 0x00; // Sequence numberuence number
        actualData[ 9] = (byte) 0x60; // Temperature
        actualData[10] = (byte) 0xf0; // Temperature
        actualData[11] = (byte) 0x00; // Relative humidity
        actualData[12] = (byte) 0x00; // Relative humidity
        actualData[13] = (byte) 0x00; // Ambient light
        actualData[14] = (byte) 0x00; // Ambient light
        actualData[15] = (byte) 0xe0; // Barometric pressure
        actualData[16] = (byte) 0x93; // Barometric pressure
        actualData[17] = (byte) 0x04; // Barometric pressure
        actualData[18] = (byte) 0x00; // Barometric pressure
        actualData[19] = (byte) 0xe4; // Sound noise
        actualData[20] = (byte) 0x0c; // Sound noise
        actualData[21] = (byte) 0x00; // eTVOC
        actualData[22] = (byte) 0x00; // eTVOC
        actualData[23] = (byte) 0x90; // eCO2
        actualData[24] = (byte) 0x01; // eCO2
        actualData[25] = (byte) 0xFF; // Reserve for Future Use
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(CalculationData.CREATOR.createFromByteArray(expectData));

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_003() {
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
        CalculationDataFilter filter = new CalculationDataFilter(null);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_004() {
        //@formatter:off
        byte[] expectData = new byte[19];
        expectData[ 0] = DATA_TYPE_CALCULATION_DATA;
        expectData[ 1] = (byte) 0xff; // Sequence number
        expectData[ 2] = (byte) 0x10; // Discomfort index
        expectData[ 3] = (byte) 0x27; // Discomfort index
        expectData[ 4] = (byte) 0xd4; // Heat stroke
        expectData[ 5] = (byte) 0x30; // Heat stroke
        expectData[ 6] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        expectData[ 7] = (byte) 0xff; // SI value
        expectData[ 8] = (byte) 0xff; // SI value
        expectData[ 9] = (byte) 0xff; // PGA
        expectData[10] = (byte) 0xff; // PGA
        expectData[11] = (byte) 0xff; // Seismic intensity
        expectData[12] = (byte) 0xff; // Seismic intensity
        expectData[13] = (byte) 0x20; // Acceleration (X-axis)
        expectData[14] = (byte) 0x4e; // Acceleration (X-axis)
        expectData[15] = (byte) 0x20; // Acceleration (Y-axis)
        expectData[16] = (byte) 0x4e; // Acceleration (Y-axis)
        expectData[17] = (byte) 0x20; // Acceleration (Z-axis)
        expectData[18] = (byte) 0x4e; // Acceleration (Z-axis)

        byte[] actualData = new byte[31];
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
        //@formatter:on

        System.arraycopy(expectData, 0, actualData, 7, expectData.length);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(CalculationData.CREATOR.createFromByteArray(expectData));

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void sequenceNumberTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void discomfortIndexTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[ 9] = (byte) 0x01; // Discomfort index
        actualData[10] = (byte) 0x00; // Discomfort index
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void heatStrokeTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[11] = (byte) 0x01; // Heat stroke
        actualData[12] = (byte) 0x00; // Heat stroke
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_HEAT_STROKE, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[13] = VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_NONE_BIT);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[13] = VIBRATION_INFORMATION_DURING_VIBRATION_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_DURING_VIBRATION_BIT);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void vibrationInformationTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[13] = VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_VIBRATION_INFORMATION, TYPE_EQUAL, VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:off

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void siValueTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void siValueTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[14] = (byte) 0x01; // SI value
        actualData[15] = (byte) 0x00; // SI value
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SI_VALUE, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x00; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void pgaTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void pgaTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[16] = (byte) 0x01; // PGA
        actualData[17] = (byte) 0x00; // PGA
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_PGA, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void seismicIntensityTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[18] = (byte) 0x01; // Seismic intensity
        actualData[19] = (byte) 0x00; // Seismic intensity
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationXAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[20] = (byte) 0x01; // Acceleration (X-axis)
        actualData[21] = (byte) 0x00; // Acceleration (X-axis)]
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationYAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[22] = (byte) 0x01; // Acceleration (Y-axis)
        actualData[23] = (byte) 0x00; // Acceleration (Y-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_THAN, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 0);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_GREATER_EQUAL, 2);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 1);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_THAN, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 0);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 1);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void accelerationZAxisTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x16; // AD 2
        actualData[ 4] = (byte) 0xff; // AD 2
        actualData[ 5] = (byte) 0xd5; // AD 2
        actualData[ 6] = (byte) 0x02; // AD 2
        actualData[ 7] = DATA_TYPE_CALCULATION_DATA;
        actualData[26] = (byte) 0x04; // AD 3
        actualData[27] = (byte) 0x08; // AD 3
        actualData[28] = (byte) 'R'; // AD 3
        actualData[29] = (byte) 'b'; // AD 3
        actualData[30] = (byte) 't'; // AD 3
        actualData[24] = (byte) 0x01; // Acceleration (Z-axis)
        actualData[25] = (byte) 0x00; // Acceleration (Z-axis)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        CalculationDataFilter filter = new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, TYPE_LESSER_EQUAL, 2);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

}
