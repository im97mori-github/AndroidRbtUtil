package org.im97mori.rbt.ble.advertising.filter;

import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.advertising.SerialNumber;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_EQUAL;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_GREATER_EQUAL;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_GREATER_THAN;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_LESSER_EQUAL;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_LESSER_THAN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SerialNumberFilterTest {

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
        SerialNumberFilter filter = new SerialNumberFilter(null, null);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_002() {
        //@formatter:off
        byte[] expectData = new byte[15];
        expectData[ 0] = DATA_TYPE_SERIAL_NUMBER;
        expectData[ 1] = (byte) 0x30;
        expectData[ 2] = (byte) 0x30;
        expectData[ 3] = (byte) 0x30;
        expectData[ 4] = (byte) 0x30;
        expectData[ 5] = (byte) 0x4d;
        expectData[ 6] = (byte) 0x59;
        expectData[ 7] = (byte) 0x30;
        expectData[ 8] = (byte) 0x30;
        expectData[ 9] = (byte) 0x30;
        expectData[10] = (byte) 0x30;
        expectData[11] = (byte) 0x01;
        expectData[12] = (byte) 0x00;
        expectData[13] = (byte) 0x00;
        expectData[14] = (byte) 0x00;

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
        SerialNumberFilter filter = new SerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(expectData), null);

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
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(null, null);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_004() {
        //@formatter:off
        byte[] expectData = new byte[15];
        expectData[ 0] = DATA_TYPE_SERIAL_NUMBER;
        expectData[ 1] = (byte) 0x30;
        expectData[ 2] = (byte) 0x30;
        expectData[ 3] = (byte) 0x30;
        expectData[ 4] = (byte) 0x30;
        expectData[ 5] = (byte) 0x4d;
        expectData[ 6] = (byte) 0x59;
        expectData[ 7] = (byte) 0x30;
        expectData[ 8] = (byte) 0x30;
        expectData[ 9] = (byte) 0x30;
        expectData[10] = (byte) 0x30;
        expectData[11] = (byte) 0x01;
        expectData[12] = (byte) 0x00;
        expectData[13] = (byte) 0x00;
        expectData[14] = (byte) 0x00;

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        //@formatter:on

        System.arraycopy(expectData, 0, actualData, 11, expectData.length);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(expectData), null);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(null, Pattern.compile("0000MY0000"));

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(null, Pattern.compile("\\d{4}MY\\d{4}"));

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(null, Pattern.compile("\\d{5}MY\\d{4}"));

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_008() {
        //@formatter:off
        byte[] expectData = new byte[15];
        expectData[ 0] = DATA_TYPE_SERIAL_NUMBER;
        expectData[ 1] = (byte) 0x30;
        expectData[ 2] = (byte) 0x30;
        expectData[ 3] = (byte) 0x30;
        expectData[ 4] = (byte) 0x30;
        expectData[ 5] = (byte) 0x4d;
        expectData[ 6] = (byte) 0x59;
        expectData[ 7] = (byte) 0x30;
        expectData[ 8] = (byte) 0x30;
        expectData[ 9] = (byte) 0x30;
        expectData[10] = (byte) 0x30;
        expectData[11] = (byte) 0x01;
        expectData[12] = (byte) 0x00;
        expectData[13] = (byte) 0x00;
        expectData[14] = (byte) 0x00;

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[22] = (byte) 0x01;
        actualData[23] = (byte) 0x00;
        actualData[24] = (byte) 0x00;
        actualData[25] = (byte) 0x00;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(expectData), Pattern.compile("0000MY0000"));

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void test_009() {
        //@formatter:off
        byte[] expectData = new byte[15];
        expectData[ 0] = DATA_TYPE_SERIAL_NUMBER;
        expectData[ 1] = (byte) 0x31;
        expectData[ 2] = (byte) 0x30;
        expectData[ 3] = (byte) 0x30;
        expectData[ 4] = (byte) 0x30;
        expectData[ 5] = (byte) 0x4d;
        expectData[ 6] = (byte) 0x59;
        expectData[ 7] = (byte) 0x30;
        expectData[ 8] = (byte) 0x30;
        expectData[ 9] = (byte) 0x30;
        expectData[10] = (byte) 0x30;
        expectData[11] = (byte) 0x01;
        expectData[12] = (byte) 0x00;
        expectData[13] = (byte) 0x00;
        expectData[14] = (byte) 0x00;

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[22] = (byte) 0x01;
        actualData[23] = (byte) 0x00;
        actualData[24] = (byte) 0x00;
        actualData[25] = (byte) 0x00;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(expectData), Pattern.compile("0000MY0000"));

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void test_010() {
        //@formatter:off
        byte[] expectData = new byte[15];
        expectData[ 0] = DATA_TYPE_SERIAL_NUMBER;
        expectData[ 1] = (byte) 0x30;
        expectData[ 2] = (byte) 0x30;
        expectData[ 3] = (byte) 0x30;
        expectData[ 4] = (byte) 0x30;
        expectData[ 5] = (byte) 0x4d;
        expectData[ 6] = (byte) 0x59;
        expectData[ 7] = (byte) 0x30;
        expectData[ 8] = (byte) 0x30;
        expectData[ 9] = (byte) 0x30;
        expectData[10] = (byte) 0x30;
        expectData[11] = (byte) 0x02;
        expectData[12] = (byte) 0x00;
        expectData[13] = (byte) 0x00;
        expectData[14] = (byte) 0x00;

        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[12] = (byte) 0x30;
        actualData[13] = (byte) 0x30;
        actualData[14] = (byte) 0x30;
        actualData[15] = (byte) 0x30;
        actualData[16] = (byte) 0x4d;
        actualData[17] = (byte) 0x59;
        actualData[18] = (byte) 0x30;
        actualData[19] = (byte) 0x30;
        actualData[20] = (byte) 0x30;
        actualData[21] = (byte) 0x30;
        actualData[22] = (byte) 0x01;
        actualData[23] = (byte) 0x00;
        actualData[24] = (byte) 0x00;
        actualData[25] = (byte) 0x00;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(expectData), Pattern.compile("0000MY0000"));

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_001() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_EQUAL, 1L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_002() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_EQUAL, 2L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_003() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_THAN, 0L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_004() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_THAN, 1L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_005() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_THAN, 2L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_006() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_EQUAL, 0L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_007() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_EQUAL, 1L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_008() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_GREATER_EQUAL, 2L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_009() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_THAN, 0L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_010() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_THAN, 1L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_011() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_THAN, 2L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_012() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_EQUAL, 0L);

        assertNotNull(result);
        assertFalse(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_013() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_EQUAL, 1L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }

    @Test
    public void memoryIndexTest_014() {
        //@formatter:off
        byte[] actualData = new byte[31];
        actualData[ 0] = (byte) 0x02; // AD 1
        actualData[ 1] = (byte) 0x01; // AD 1
        actualData[ 2] = (byte) 0x06; // AD 1
        actualData[ 3] = (byte) 0x03; // AD 2
        actualData[ 4] = (byte) 0x02; // AD 2
        actualData[ 5] = (byte) 0x0a; // AD 2
        actualData[ 6] = (byte) 0x18; // AD 2
        actualData[ 7] = (byte) 0x12; // AD 3
        actualData[ 8] = (byte) 0xff; // AD 3
        actualData[ 9] = (byte) 0xd5; // AD 3
        actualData[10] = (byte) 0x02; // AD 3
        actualData[11] = DATA_TYPE_SERIAL_NUMBER;
        actualData[26] = (byte) 0x04; // AD 4
        actualData[27] = (byte) 0x08; // AD 4
        actualData[28] = (byte) 'R'; // AD 4
        actualData[29] = (byte) 'b'; // AD 4
        actualData[30] = (byte) 't'; // AD 4
        actualData[22] = (byte) 0x01; // Memory index (Latest)
        actualData[23] = (byte) 0x00; // Memory index (Latest)
        actualData[24] = (byte) 0x00; // Memory index (Latest)
        actualData[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(actualData);
        SerialNumberFilter filter = new SerialNumberFilter(TYPE_LESSER_EQUAL, 2L);

        assertNotNull(result);
        assertTrue(filter.isMatched(result));
    }
}
