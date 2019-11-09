package org.im97mori.rbt.ble.ad;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RbtAdvertisingDataParserTest {

    @Test
    public void builderTest0001() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[ 0];
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNull(result);
    }

    @Test
    public void builderTest0002() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNotNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0003() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNotNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0004() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNotNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0005() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNotNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0006() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNotNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0102() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_DATA).include(DATA_TYPE_SENSOR_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNotNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0103() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_CALCULATION_DATA).include(DATA_TYPE_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNotNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0104() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA).include(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNotNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0105() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG).include(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNotNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0106() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SERIAL_NUMBER).include(DATA_TYPE_SERIAL_NUMBER).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNotNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0202() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0203() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0204() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0205() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0206() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.exclude(DATA_TYPE_SERIAL_NUMBER).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0302() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0303() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0304() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0305() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0306() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0402() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.include(DATA_TYPE_SENSOR_DATA).exclude(DATA_TYPE_SENSOR_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0403() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.include(DATA_TYPE_CALCULATION_DATA).exclude(DATA_TYPE_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0404() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.include(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA).exclude(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0405() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.include(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG).exclude(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG).build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0406() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.include(DATA_TYPE_SERIAL_NUMBER).exclude(DATA_TYPE_SERIAL_NUMBER).build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0502() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_SENSOR_DATA);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNotNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0503() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_CALCULATION_DATA);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNotNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0504() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNotNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0505() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNotNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0506() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_SERIAL_NUMBER);
        RbtAdvertisingDataParser parser = builder.build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNotNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0602() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.excludeAll().includeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNotNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0603() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.excludeAll().includeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNotNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0604() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.excludeAll().includeAll().build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNotNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0605() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.excludeAll().includeAll().build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNotNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0606() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
        RbtAdvertisingDataParser parser = builder.excludeAll().includeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNotNull(result.getSerialNumber());
        assertTrue(result.isRbt());
    }

    @Test
    public void builderTest0702() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.includeAll().excludeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x01; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0703() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.includeAll().excludeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x02; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Discomfort index
        data[10] = (byte) 0x00; // Discomfort index
        data[11] = (byte) 0x60; // Heat stroke
        data[12] = (byte) 0xf0; // Heat stroke
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[14] = (byte) 0x00; // SI value
        data[15] = (byte) 0x00; // SI value
        data[16] = (byte) 0x00; // PGA
        data[17] = (byte) 0x00; // PGA
        data[18] = (byte) 0x00; // Seismic intensity
        data[19] = (byte) 0x00; // Seismic intensity
        data[20] = (byte) 0xe0; // Acceleration (X-axis)
        data[21] = (byte) 0xb1; // Acceleration (X-axis)
        data[22] = (byte) 0xe0; // Acceleration (Y-axis)
        data[23] = (byte) 0xb1; // Acceleration (Y-axis)
        data[24] = (byte) 0xe0; // Acceleration (Z-axis)
        data[25] = (byte) 0xb1; // Acceleration (Z-axis)
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0704() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.includeAll().excludeAll().build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x03; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x60; // Temperature
        data[10] = (byte) 0xf0; // Temperature
        data[11] = (byte) 0x00; // Relative humidity
        data[12] = (byte) 0x00; // Relative humidity
        data[13] = (byte) 0x00; // Ambient light
        data[14] = (byte) 0x00; // Ambient light
        data[15] = (byte) 0xe0; // Barometric pressure
        data[16] = (byte) 0x93; // Barometric pressure
        data[17] = (byte) 0x04; // Barometric pressure
        data[18] = (byte) 0x00; // Barometric pressure
        data[19] = (byte) 0xe4; // Sound noise
        data[20] = (byte) 0x0c; // Sound noise
        data[21] = (byte) 0x00; // eTVOC
        data[22] = (byte) 0x00; // eTVOC
        data[23] = (byte) 0x90; // eCO2
        data[24] = (byte) 0x01; // eCO2
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x03; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index
        data[38] = (byte) 0x00; // Discomfort index
        data[39] = (byte) 0x60; // Heat stroke
        data[40] = (byte) 0xf0; // Heat stroke
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[42] = (byte) 0x00; // SI value
        data[43] = (byte) 0x00; // SI value
        data[44] = (byte) 0x00; // PGA
        data[45] = (byte) 0x00; // PGA
        data[46] = (byte) 0x00; // Seismic intensity
        data[47] = (byte) 0x00; // Seismic intensity
        data[48] = (byte) 0xe0; // Acceleration (X-axis)
        data[49] = (byte) 0xb1; // Acceleration (X-axis)
        data[50] = (byte) 0xe0; // Acceleration (Y-axis)
        data[51] = (byte) 0xb1; // Acceleration (Y-axis)
        data[52] = (byte) 0xe0; // Acceleration (Z-axis)
        data[53] = (byte) 0xb1; // Acceleration (Z-axis)
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0705() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.includeAll().excludeAll().build();

        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = (byte) 0x04; // AD 2
        data[ 8] = (byte) 0x00; // Sequence number
        data[ 9] = (byte) 0x00; // Temperature flag
        data[10] = (byte) 0x00; // Temperature flag
        data[11] = (byte) 0x00; // Relative humidity flag
        data[12] = (byte) 0x00; // Relative humidity flag
        data[13] = (byte) 0x00; // Ambient light flag
        data[14] = (byte) 0x00; // Ambient light flag
        data[15] = (byte) 0x00; // Barometric pressure flag
        data[16] = (byte) 0x00; // Barometric pressure flag
        data[17] = (byte) 0x00; // Sound noise flag
        data[18] = (byte) 0x00; // Sound noise flag
        data[19] = (byte) 0x00; // eTVOC flag
        data[20] = (byte) 0x00; // eTVOC flag
        data[21] = (byte) 0x00; // eCO2 flag
        data[22] = (byte) 0x00; // eCO2 flag
        data[23] = (byte) 0xFF; // Reserve for Future Use
        data[24] = (byte) 0xFF; // Reserve for Future Use
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = (byte) 0x04; // AD 4
        data[36] = (byte) 0x00; // Sequence number
        data[37] = (byte) 0x00; // Discomfort index flag
        data[38] = (byte) 0x00; // Discomfort index flag
        data[39] = (byte) 0x00; // Heat stroke flag
        data[40] = (byte) 0x00; // Heat stroke flag
        data[41] = (byte) 0x00; // SI value flag
        data[42] = (byte) 0x00; // PGA flag
        data[43] = (byte) 0x00; // Seismic intensity flag
        data[44] = (byte) 0xff; // Reserve for Future Use
        data[45] = (byte) 0xff; // Reserve for Future Use
        data[46] = (byte) 0xff; // Reserve for Future Use
        data[47] = (byte) 0xff; // Reserve for Future Use
        data[48] = (byte) 0xff; // Reserve for Future Use
        data[49] = (byte) 0xff; // Reserve for Future Use
        data[50] = (byte) 0xff; // Reserve for Future Use
        data[51] = (byte) 0xff; // Reserve for Future Use
        data[52] = (byte) 0xff; // Reserve for Future Use
        data[53] = (byte) 0xff; // Reserve for Future Use
        data[54] = (byte) 0xff; // Reserve for Future Use
        data[55] = (byte) 0xff; // Reserve for Future Use
        data[56] = (byte) 0xff; // Reserve for Future Use
        data[57] = (byte) 0xff; // Reserve for Future Use
        data[58] = (byte) 0xff; // Reserve for Future Use
        data[59] = (byte) 0xff; // Reserve for Future Use
        data[60] = (byte) 0xff; // Reserve for Future Use
        data[61] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

    @Test
    public void builderTest0706() {
        RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(false);
        RbtAdvertisingDataParser parser = builder.includeAll().excludeAll().build();

        //@formatter:off
        byte[] data = new byte[31];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x03; // AD 2
        data[ 4] = (byte) 0x02; // AD 2
        data[ 5] = (byte) 0x0a; // AD 2
        data[ 6] = (byte) 0x18; // AD 2
        data[ 7] = (byte) 0x12; // AD 3
        data[ 8] = (byte) 0xff; // AD 3
        data[ 9] = (byte) 0xd5; // AD 3
        data[10] = (byte) 0x02; // AD 3
        data[11] = (byte) 0x05; // AD 3
        data[12] = (byte) 0x00; // Serial number
        data[13] = (byte) 0x60; // Serial number
        data[14] = (byte) 0xf0; // Serial number
        data[15] = (byte) 0x00; // Serial number
        data[16] = (byte) 0x00; // Serial number
        data[17] = (byte) 0x00; // Serial number
        data[18] = (byte) 0x00; // Serial number
        data[19] = (byte) 0xe0; // Serial number
        data[20] = (byte) 0x93; // Serial number
        data[21] = (byte) 0x04; // Serial number
        data[22] = (byte) 0x00; // Memory index (Latest)
        data[23] = (byte) 0xe4; // Memory index (Latest)
        data[24] = (byte) 0x0c; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        //@formatter:on

        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result = parser.parse(data);

        assertNotNull(result);
        assertNull(result.getSensorData());
        assertNull(result.getCalculationData());
        assertNull(result.getSensorDataAndCalculationData());
        assertNull(result.getSensorFlagAndCalculationFlag());
        assertNull(result.getSerialNumber());
        assertFalse(result.isRbt());
    }

}
