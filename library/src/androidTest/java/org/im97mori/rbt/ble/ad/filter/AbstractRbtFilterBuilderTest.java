package org.im97mori.rbt.ble.ad.filter;

import androidx.annotation.NonNull;

import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.ble.advertising.filter.OrFilter;
import org.im97mori.rbt.RbtConstants;
import org.im97mori.rbt.ble.ad.CalculationData;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorData;
import org.im97mori.rbt.ble.ad.SensorDataAndCalculationData;
import org.im97mori.rbt.ble.ad.SensorFlagAndCalculationFlag;
import org.im97mori.rbt.ble.ad.SerialNumber;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_AMBIENT_LIGHT;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_BAROMETRIC_PRESSURE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_DISCOMFORT_INDEX;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ECO2;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_ETVOC;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_HEAT_STROKE;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_MEMORY_INDEX;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_PGA;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_RELATIVE_HUMIDITY;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TARGET_SEISMIC_INTENSITY;
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
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;
import static org.im97mori.rbt.ble.ad.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AbstractRbtFilterBuilderTest {

    private static class MockFilterBuilder extends AbstractRbtFilterBuilder<List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>>> {

        @Override
        @NonNull
        public List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> build() {
            return mFilterList;
        }
    }

    @Test
    public void addFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtDataFilter<Integer> filter = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addFilter(filter).build();
        assertEquals(1, result.size());
        assertEquals(filter, result.get(0));
    }

    @Test
    public void addFilter2Test_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtDataFilter<Integer> filter1 = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        RbtDataFilter<Integer> filter2 = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addFilters(filter1, filter2).build();
        assertEquals(2, result.size());
        assertEquals(filter1, result.get(0));
        assertEquals(filter2, result.get(1));
    }

    @Test
    public void addFilter2Test_002() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtDataFilter<Integer> filter1 = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        RbtDataFilter<Integer> filter2 = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addFilters(Arrays.<RbtDataFilter<?>>asList(filter1, filter2)).build();
        assertEquals(2, result.size());
        assertEquals(filter1, result.get(0));
        assertEquals(filter2, result.get(1));
    }

    @Test
    public void clearFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtDataFilter<Integer> filter = new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        };
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addFilter(filter).clearFilter().build();
        assertTrue(result.isEmpty());
    }

    @Test
    public void setAdvertisingDataParserTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        builder.setRbtAdvertisingDataParser(parser);
        assertEquals(parser, builder.mRbtAdvertisingDataParser);
    }

    @Test
    public void setAdvertisingDataParserTest_002() {
        MockFilterBuilder builder = new MockFilterBuilder();
        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();
        builder.setRbtAdvertisingDataParser(parser).setRbtAdvertisingDataParser(null);
        assertNull(builder.mRbtAdvertisingDataParser);
    }

    @Test
    public void addSensorDataFilterTest_001() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_SENSOR_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x60; // Temperature
        data[ 3] = (byte) 0xf0; // Temperature
        data[ 4] = (byte) 0x00; // Relative humidity
        data[ 5] = (byte) 0x00; // Relative humidity
        data[ 6] = (byte) 0x00; // Ambient light
        data[ 7] = (byte) 0x00; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0x93; // Barometric pressure
        data[10] = (byte) 0x04; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe4; // Sound noise
        data[13] = (byte) 0x0c; // Sound noise
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x00; // eTVOC
        data[16] = (byte) 0x90; // eCO2
        data[17] = (byte) 0x01; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorDataFilter(data).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorDataFilter);
    }

    @Test
    public void addSensorDataFilterTest_002() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_SENSOR_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x60; // Temperature
        data[ 3] = (byte) 0xf0; // Temperature
        data[ 4] = (byte) 0x00; // Relative humidity
        data[ 5] = (byte) 0x00; // Relative humidity
        data[ 6] = (byte) 0x00; // Ambient light
        data[ 7] = (byte) 0x00; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0x93; // Barometric pressure
        data[10] = (byte) 0x04; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe4; // Sound noise
        data[13] = (byte) 0x0c; // Sound noise
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x00; // eTVOC
        data[16] = (byte) 0x90; // eCO2
        data[17] = (byte) 0x01; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorDataFilter(SensorData.CREATOR.createFromByteArray(data)).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorDataFilter);
    }

    @Test
    public void addCalculationDataFilterTest_001() {
        //@formatter:off
        byte[] data = new byte[27];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x00; // Discomfort index
        data[ 3] = (byte) 0x00; // Discomfort index
        data[ 4] = (byte) 0x60; // Heat stroke
        data[ 5] = (byte) 0xf0; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 7] = (byte) 0x00; // SI value
        data[ 8] = (byte) 0x00; // SI value
        data[ 9] = (byte) 0x00; // PGA
        data[10] = (byte) 0x00; // PGA
        data[11] = (byte) 0x00; // Seismic intensity
        data[12] = (byte) 0x00; // Seismic intensity
        data[13] = (byte) 0xe0; // Acceleration (X-axis)
        data[14] = (byte) 0xb1; // Acceleration (X-axis)
        data[15] = (byte) 0xe0; // Acceleration (Y-axis)
        data[16] = (byte) 0xb1; // Acceleration (Y-axis)
        data[17] = (byte) 0xe0; // Acceleration (Z-axis)
        data[18] = (byte) 0xb1; // Acceleration (Z-axis)
        data[19] = (byte) 0xff; // Reserve for Future Use
        data[20] = (byte) 0xff; // Reserve for Future Use
        data[21] = (byte) 0xff; // Reserve for Future Use
        data[22] = (byte) 0xff; // Reserve for Future Use
        data[23] = (byte) 0xff; // Reserve for Future Use
        data[24] = (byte) 0xff; // Reserve for Future Use
        data[25] = (byte) 0xff; // Reserve for Future Use
        data[26] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addCalculationDataFilter(data).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof CalculationDataFilter);
    }

    @Test
    public void addCalculationDataFilterTest_002() {
        //@formatter:off
        byte[] data = new byte[27];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x00; // Discomfort index
        data[ 3] = (byte) 0x00; // Discomfort index
        data[ 4] = (byte) 0x60; // Heat stroke
        data[ 5] = (byte) 0xf0; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 7] = (byte) 0x00; // SI value
        data[ 8] = (byte) 0x00; // SI value
        data[ 9] = (byte) 0x00; // PGA
        data[10] = (byte) 0x00; // PGA
        data[11] = (byte) 0x00; // Seismic intensity
        data[12] = (byte) 0x00; // Seismic intensity
        data[13] = (byte) 0xe0; // Acceleration (X-axis)
        data[14] = (byte) 0xb1; // Acceleration (X-axis)
        data[15] = (byte) 0xe0; // Acceleration (Y-axis)
        data[16] = (byte) 0xb1; // Acceleration (Y-axis)
        data[17] = (byte) 0xe0; // Acceleration (Z-axis)
        data[18] = (byte) 0xb1; // Acceleration (Z-axis)
        data[19] = (byte) 0xff; // Reserve for Future Use
        data[20] = (byte) 0xff; // Reserve for Future Use
        data[21] = (byte) 0xff; // Reserve for Future Use
        data[22] = (byte) 0xff; // Reserve for Future Use
        data[23] = (byte) 0xff; // Reserve for Future Use
        data[24] = (byte) 0xff; // Reserve for Future Use
        data[25] = (byte) 0xff; // Reserve for Future Use
        data[26] = (byte) 0xff; // Reserve for Future Use
        //@formatter:off

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addCalculationDataFilter(CalculationData.CREATOR.createFromByteArray(data)).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof CalculationDataFilter);
    }

    @Test
    public void addSensorDataAndCalculationDataFilterTest_001() {
        //@formatter:off
        byte[] data = new byte[38];
        data[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x60; // Temperature
        data[ 3] = (byte) 0xf0; // Temperature
        data[ 4] = (byte) 0x00; // Relative humidity
        data[ 5] = (byte) 0x00; // Relative humidity
        data[ 6] = (byte) 0x00; // Ambient light
        data[ 7] = (byte) 0x00; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0x93; // Barometric pressure
        data[10] = (byte) 0x04; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe4; // Sound noise
        data[13] = (byte) 0x0c; // Sound noise
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x00; // eTVOC
        data[16] = (byte) 0x90; // eCO2
        data[17] = (byte) 0x01; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        data[19] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data[20] = (byte) 0xff; // Sequence number
        data[21] = (byte) 0x10; // Discomfort index
        data[22] = (byte) 0x27; // Discomfort index
        data[23] = (byte) 0xd4; // Heat stroke
        data[24] = (byte) 0x30; // Heat stroke
        data[25] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[26] = (byte) 0xff; // SI value
        data[27] = (byte) 0xff; // SI value
        data[28] = (byte) 0xff; // PGA
        data[29] = (byte) 0xff; // PGA
        data[30] = (byte) 0xff; // Seismic intensity
        data[31] = (byte) 0xff; // Seismic intensity
        data[32] = (byte) 0x20; // Acceleration (X-axis)
        data[33] = (byte) 0x4e; // Acceleration (X-axis)
        data[34] = (byte) 0x20; // Acceleration (Y-axis)
        data[35] = (byte) 0x4e; // Acceleration (Y-axis)
        data[36] = (byte) 0x20; // Acceleration (Z-axis)
        data[37] = (byte) 0x4e; // Acceleration (Z-axis)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorDataAndCalculationDataFilter(data).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorDataAndCalculationDataFilter);
    }

    @Test
    public void addSensorDataAndCalculationDataFilterTest_002() {
        //@formatter:off
        byte[] data1 = new byte[19];
        data1[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data1[ 1] = (byte) 0x00; // Sequence number
        data1[ 2] = (byte) 0x60; // Temperature
        data1[ 3] = (byte) 0xf0; // Temperature
        data1[ 4] = (byte) 0x00; // Relative humidity
        data1[ 5] = (byte) 0x00; // Relative humidity
        data1[ 6] = (byte) 0x00; // Ambient light
        data1[ 7] = (byte) 0x00; // Ambient light
        data1[ 8] = (byte) 0xe0; // Barometric pressure
        data1[ 9] = (byte) 0x93; // Barometric pressure
        data1[10] = (byte) 0x04; // Barometric pressure
        data1[11] = (byte) 0x00; // Barometric pressure
        data1[12] = (byte) 0xe4; // Sound noise
        data1[13] = (byte) 0x0c; // Sound noise
        data1[14] = (byte) 0x00; // eTVOC
        data1[15] = (byte) 0x00; // eTVOC
        data1[16] = (byte) 0x90; // eCO2
        data1[17] = (byte) 0x01; // eCO2
        data1[18] = (byte) 0xFF; // Reserve for Future Use

        byte[] data2 = new byte[19];
        data2[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data2[ 1] = (byte) 0x00; // Sequence number
        data2[ 2] = (byte) 0x00; // Discomfort index
        data2[ 3] = (byte) 0x00; // Discomfort index
        data2[ 4] = (byte) 0x60; // Heat stroke
        data2[ 5] = (byte) 0xf0; // Heat stroke
        data2[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data2[ 7] = (byte) 0x00; // SI value
        data2[ 8] = (byte) 0x00; // SI value
        data2[ 9] = (byte) 0x00; // PGA
        data2[10] = (byte) 0x00; // PGA
        data2[11] = (byte) 0x00; // Seismic intensity
        data2[12] = (byte) 0x00; // Seismic intensity
        data2[13] = (byte) 0xe0; // Acceleration (X-axis)
        data2[14] = (byte) 0xb1; // Acceleration (X-axis)
        data2[15] = (byte) 0xe0; // Acceleration (Y-axis)
        data2[16] = (byte) 0xb1; // Acceleration (Y-axis)
        data2[17] = (byte) 0xe0; // Acceleration (Z-axis)
        data2[18] = (byte) 0xb1; // Acceleration (Z-axis)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorDataAndCalculationDataFilter(data1, data2).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorDataAndCalculationDataFilter);
    }

    @Test
    public void addSensorDataAndCalculationDataFilterTest_003() {
        //@formatter:off
        byte[] data = new byte[38];
        data[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x60; // Temperature
        data[ 3] = (byte) 0xf0; // Temperature
        data[ 4] = (byte) 0x00; // Relative humidity
        data[ 5] = (byte) 0x00; // Relative humidity
        data[ 6] = (byte) 0x00; // Ambient light
        data[ 7] = (byte) 0x00; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0x93; // Barometric pressure
        data[10] = (byte) 0x04; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe4; // Sound noise
        data[13] = (byte) 0x0c; // Sound noise
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x00; // eTVOC
        data[16] = (byte) 0x90; // eCO2
        data[17] = (byte) 0x01; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        data[19] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data[20] = (byte) 0xff; // Sequence number
        data[21] = (byte) 0x10; // Discomfort index
        data[22] = (byte) 0x27; // Discomfort index
        data[23] = (byte) 0xd4; // Heat stroke
        data[24] = (byte) 0x30; // Heat stroke
        data[25] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[26] = (byte) 0xff; // SI value
        data[27] = (byte) 0xff; // SI value
        data[28] = (byte) 0xff; // PGA
        data[29] = (byte) 0xff; // PGA
        data[30] = (byte) 0xff; // Seismic intensity
        data[31] = (byte) 0xff; // Seismic intensity
        data[32] = (byte) 0x20; // Acceleration (X-axis)
        data[33] = (byte) 0x4e; // Acceleration (X-axis)
        data[34] = (byte) 0x20; // Acceleration (Y-axis)
        data[35] = (byte) 0x4e; // Acceleration (Y-axis)
        data[36] = (byte) 0x20; // Acceleration (Z-axis)
        data[37] = (byte) 0x4e; // Acceleration (Z-axis)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorDataAndCalculationDataFilter(SensorDataAndCalculationData.CREATOR.createFromByteArray(data)).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorDataAndCalculationDataFilter);
    }

    @Test
    public void addSensorFlagAndCalculationFlagFilterTest_001() {
        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorFlagAndCalculationFlagFilter(data).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorFlagAndCalculationFlagFilter);
    }

    @Test
    public void addSensorFlagAndCalculationFlagFilterTest_002() {
        //@formatter:off
        byte[] data1 = new byte[19];
        data1[ 0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[ 1] = (byte) 0x00; // Sequence number
        data1[ 2] = (byte) 0x00; // Temperature flag
        data1[ 3] = (byte) 0x00; // Temperature flag
        data1[ 4] = (byte) 0x00; // Relative humidity flag
        data1[ 5] = (byte) 0x00; // Relative humidity flag
        data1[ 6] = (byte) 0x00; // Ambient light flag
        data1[ 7] = (byte) 0x00; // Ambient light flag
        data1[ 8] = (byte) 0x00; // Barometric pressure flag
        data1[ 9] = (byte) 0x00; // Barometric pressure flag
        data1[10] = (byte) 0x00; // Sound noise flag
        data1[11] = (byte) 0x00; // Sound noise flag
        data1[12] = (byte) 0x00; // eTVOC flag
        data1[13] = (byte) 0x00; // eTVOC flag
        data1[14] = (byte) 0x00; // eCO2 flag
        data1[15] = (byte) 0x00; // eCO2 flag
        data1[16] = (byte) 0xFF; // Reserve for Future Use
        data1[17] = (byte) 0xFF; // Reserve for Future Use
        data1[18] = (byte) 0xFF; // Reserve for Future Use

        byte[] data2 = new byte[27];
        data2[ 0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[ 1] = (byte) 0x00; // Sequence number
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x00; // Discomfort index flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x00; // Heat stroke flag
        data2[ 6] = (byte) 0x00; // SI value flag
        data2[ 7] = (byte) 0x00; // PGA flag
        data2[ 8] = (byte) 0x00; // Seismic intensity flag
        data2[ 9] = (byte) 0xff; // Reserve for Future Use
        data2[10] = (byte) 0xff; // Reserve for Future Use
        data2[11] = (byte) 0xff; // Reserve for Future Use
        data2[12] = (byte) 0xff; // Reserve for Future Use
        data2[13] = (byte) 0xff; // Reserve for Future Use
        data2[14] = (byte) 0xff; // Reserve for Future Use
        data2[15] = (byte) 0xff; // Reserve for Future Use
        data2[16] = (byte) 0xff; // Reserve for Future Use
        data2[17] = (byte) 0xff; // Reserve for Future Use
        data2[18] = (byte) 0xff; // Reserve for Future Use
        data2[19] = (byte) 0xff; // Reserve for Future Use
        data2[20] = (byte) 0xff; // Reserve for Future Use
        data2[21] = (byte) 0xff; // Reserve for Future Use
        data2[22] = (byte) 0xff; // Reserve for Future Use
        data2[23] = (byte) 0xff; // Reserve for Future Use
        data2[24] = (byte) 0xff; // Reserve for Future Use
        data2[25] = (byte) 0xff; // Reserve for Future Use
        data2[26] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorFlagAndCalculationFlagFilter(data1, data2).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorFlagAndCalculationFlagFilter);
    }

    @Test
    public void addSensorFlagAndCalculationFlagFilterTest_003() {
        //@formatter:off
        byte[] data = new byte[62];
        data[ 0] = (byte) 0x02; // AD 1
        data[ 1] = (byte) 0x01; // AD 1
        data[ 2] = (byte) 0x06; // AD 1
        data[ 3] = (byte) 0x16; // AD 2
        data[ 4] = (byte) 0xff; // AD 2
        data[ 5] = (byte) 0xd5; // AD 2
        data[ 6] = (byte) 0x02; // AD 2
        data[ 7] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        data[31] = (byte) 0x1e; // AD 4
        data[32] = (byte) 0xff; // AD 4
        data[33] = (byte) 0xd5; // AD 4
        data[34] = (byte) 0x02; // AD 4
        data[35] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag.CREATOR.createFromByteArray(data)).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorFlagAndCalculationFlagFilter);
    }

    @Test
    public void addSerialNumberFilterTest_001() {
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
        data[11] = DATA_TYPE_SERIAL_NUMBER;
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        data[22] = (byte) 0x01; // Memory index (Latest)
        data[23] = (byte) 0x00; // Memory index (Latest)
        data[24] = (byte) 0x00; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSerialNumberFilter(data).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
    }

    @Test
    public void addSerialNumberFilterTest_002() {
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
        data[11] = DATA_TYPE_SERIAL_NUMBER;
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        data[22] = (byte) 0x01; // Memory index (Latest)
        data[23] = (byte) 0x00; // Memory index (Latest)
        data[24] = (byte) 0x00; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(data)).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
    }

    @Test
    public void addSerialNumberFilterTest_003() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSerialNumberFilter(Pattern.compile("")).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
    }

    @Test
    public void addSerialNumberFilterTest_004() {
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
        data[11] = DATA_TYPE_SERIAL_NUMBER;
        data[26] = (byte) 0x04; // AD 4
        data[27] = (byte) 0x08; // AD 4
        data[28] = (byte) 'R'; // AD 4
        data[29] = (byte) 'b'; // AD 4
        data[30] = (byte) 't'; // AD 4
        data[22] = (byte) 0x01; // Memory index (Latest)
        data[23] = (byte) 0x00; // Memory index (Latest)
        data[24] = (byte) 0x00; // Memory index (Latest)
        data[25] = (byte) 0x00; // Memory index (Latest)
        //@formatter:on

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSerialNumberFilter(SerialNumber.CREATOR.createFromByteArray(data), null).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
    }

    @Test
    public void addSerialNumberFilterTest_005() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSerialNumberFilter(null, null).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
    }

    private byte[] getSensorDataByteArray() {
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
        data[25] = (byte) 0xFF; // Reserve for Future Use
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on
        return data;
    }

    private byte[] getCalculationDataByteArray() {
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
        data[13] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[26] = (byte) 0x04; // AD 3
        data[27] = (byte) 0x08; // AD 3
        data[28] = (byte) 'R'; // AD 3
        data[29] = (byte) 'b'; // AD 3
        data[30] = (byte) 't'; // AD 3
        //@formatter:on
        return data;
    }

    private byte[] getSensorDataAndCalculationDataByteArray() {
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
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on
        return data;
    }

    private byte[] getSensorFlagAndCalculationFlagByteArray() {
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
        data[41] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[54] = (byte) 0xFF; // Reserve for Future Use
        data[55] = (byte) 0xFF; // Reserve for Future Use
        data[56] = (byte) 0xFF; // Reserve for Future Use
        data[57] = (byte) 0xFF; // Reserve for Future Use
        data[58] = (byte) 0xFF; // Reserve for Future Use
        data[59] = (byte) 0xFF; // Reserve for Future Use
        data[60] = (byte) 0xFF; // Reserve for Future Use
        data[61] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on
        return data;
    }

    @Test
    public void addSequenceNumberFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSequenceNumberFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        //@formatter:off
        data[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getCalculationDataByteArray();
        //@formatter:off
        data[ 8] = (byte) 0x01; // Sequence number
        //@formatter:on
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        //@formatter:off
        data[ 8] = (byte) 0x01; // Sequence number
        data[36] = (byte) 0x01; // Sequence number
        //@formatter:on
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorFlagAndCalculationFlagByteArray();
        //@formatter:off
        data[ 8] = (byte) 0x01; // Sequence number
        data[36] = (byte) 0x01; // Sequence number
        //@formatter:on
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addTemperatureFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        //@formatter:off
        data[ 9] = (byte) 0x01; // Temperature
        //@formatter:on
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        //@formatter:off
        data[ 9] = (byte) 0x01; // Temperature
        //@formatter:on
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addRelativeHumidityFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[11] = (byte) 0x01; // Relative humidity
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[11] = (byte) 0x01; // Relative humidity
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addAmbientLightFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[13] = (byte) 0x01; // Ambient light
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[13] = (byte) 0x01; // Ambient light
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addBarometricPressureFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[15] = (byte) 0x01; // Barometric pressure
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[15] = (byte) 0x01; // Barometric pressure
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addSoundNoiseFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[19] = (byte) 0x01; // Sound noise
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[19] = (byte) 0x01; // Sound noise
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addEtvocFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[21] = (byte) 0x01; // etvoc
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[21] = (byte) 0x01; // etvoc
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addEco2FilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2Filter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getSensorDataByteArray();
        data[23] = (byte) 0x01; // eco2
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[23] = (byte) 0x01; // eco2
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addDiscomfortIndexFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        //@formatter:off
        data[ 9] = (byte) 0x01; // Discomfort index
        //@formatter:on
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[37] = (byte) 0x01; // Discomfort index
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addHeatStrokeFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[11] = (byte) 0x01; // Heat stroke
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[39] = (byte) 0x01; // Heat stroke
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addSiValueFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSiValueFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[14] = (byte) 0x01; // SI value
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[42] = (byte) 0x01; // SI value
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addPgaFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addPgaFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[16] = (byte) 0x01; // PGA
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[44] = (byte) 0x01; // PGA
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }


    @Test
    public void addSeismicIntensityFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSeismicIntensityFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[18] = (byte) 0x01; // Seismic intensity
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[46] = (byte) 0x01; // Seismic intensity
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addAccelerationXAxisFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAccelerationXAxisFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[20] = (byte) 0x01; // Acceleration (X-axis)
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[48] = (byte) 0x01; // Acceleration (X-axis)
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addAccelerationYAxisFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAccelerationYAxisFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[22] = (byte) 0x01; // Acceleration (Y-axis)
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[50] = (byte) 0x01; // Acceleration (Y-axis)
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addAccelerationZAxisFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAccelerationZAxisFilter(TYPE_EQUAL, 1).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof OrFilter);
        OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> orFilter = (OrFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>) result.get(0);

        RbtAdvertisingDataParser parser = new RbtAdvertisingDataParser.Builder(true).build();

        byte[] data = getCalculationDataByteArray();
        data[24] = (byte) 0x01; // Acceleration (Z-axis)
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));

        data = getSensorDataAndCalculationDataByteArray();
        data[52] = (byte) 0x01; // Acceleration (Z-axis)
        parseResult = parser.parse(data);
        assertNotNull(parseResult);
        assertTrue(orFilter.isMatched(parseResult));
    }

    @Test
    public void addMemoryIndexFilterTest_001() {
        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addMemoryIndexFilter(TYPE_EQUAL, 1L).build();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SerialNumberFilter);
        assertEquals(((RbtDataFilter) result.get(0)).mTarget, TARGET_MEMORY_INDEX);
        assertEquals(((RbtDataFilter) result.get(0)).mType, TYPE_EQUAL);
        assertEquals(((RbtDataFilter) result.get(0)).mValue, 1L);
    }

    private void addFlagFilterTest(List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result, int target, int type, int value) {
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof SensorFlagAndCalculationFlagFilter);
        assertEquals(((RbtDataFilter) result.get(0)).mTarget, target);
        assertEquals(((RbtDataFilter) result.get(0)).mType, type);
        assertEquals(((RbtDataFilter) result.get(0)).mValue, value);
    }

    @Test
    public void addTemperatureFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addTemperatureFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addTemperatureFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addTemperatureFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addTemperatureFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addTemperatureFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addTemperatureFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addTemperatureFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addTemperatureFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addTemperatureFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addTemperatureFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addTemperatureFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addTemperatureFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addTemperatureFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addTemperatureFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addTemperatureFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addTemperatureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_TEMPERATURE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addRelativeHumidityFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addRelativeHumidityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_RELATIVE_HUMIDITY
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addAmbientLightFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addAmbientLightFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addAmbientLightFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addAmbientLightFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addAmbientLightFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addAmbientLightFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addAmbientLightFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addAmbientLightFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addAmbientLightFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addAmbientLightFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addAmbientLightFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addAmbientLightFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_AMBIENT_LIGHT
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addBarometricPressureFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addBarometricPressureFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_BAROMETRIC_PRESSURE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addSoundNoiseFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSoundNoiseFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SOUND_NOISE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addEtvocFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addEtvocFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addEtvocFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addEtvocFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addEtvocFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addEtvocFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addEtvocFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addEtvocFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addEtvocFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addEtvocFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addEtvocFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addEtvocFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addEtvocFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addEtvocFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addEtvocFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addEtvocFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEtvocFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ETVOC
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addEco2FlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addEco2FlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addEco2FlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addEco2FlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addEco2FlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addEco2FlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addEco2FlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addEco2FlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addEco2FlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addEco2FlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addEco2FlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addEco2FlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addEco2FlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addEco2FlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addEco2FlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addEco2FlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addEco2FlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_ECO2
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addDiscomfortIndexFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addDiscomfortIndexFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_DISCOMFORT_INDEX
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_003() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_004() {
        int type = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_005() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_006() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_007() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_008() {
        int type = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_009() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_010() {
        int type = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_011() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_012() {
        int type = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_013() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_014() {
        int type = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_015() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER);
    }

    @Test
    public void addHeatStrokeFlagFilterTest_016() {
        int type = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addHeatStrokeFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_HEAT_STROKE
                , type
                , RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER);
    }

    @Test
    public void addSiValueFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSiValueFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SI_VALUE
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addSiValueFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSiValueFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SI_VALUE
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addSiValueFlagFilterTest_003() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSiValueFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SI_VALUE
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addSiValueFlagFilterTest_004() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSiValueFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SI_VALUE
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addPgaFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addPgaFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_PGA
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addPgaFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addPgaFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_PGA
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addPgaFlagFilterTest_003() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addPgaFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_PGA
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addPgaFlagFilterTest_004() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addPgaFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_PGA
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);
    }

    @Test
    public void addSeismicIntensityFlagFilterTest_001() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSeismicIntensityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SEISMIC_INTENSITY
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1);
    }

    @Test
    public void addSeismicIntensityFlagFilterTest_002() {
        int type = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSeismicIntensityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SEISMIC_INTENSITY
                , type
                , RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2);
    }

    @Test
    public void addSeismicIntensityFlagFilterTest_003() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSeismicIntensityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SEISMIC_INTENSITY
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1);
    }

    @Test
    public void addSeismicIntensityFlagFilterTest_004() {
        int type = TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION;

        MockFilterBuilder builder = new MockFilterBuilder();
        List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> result = builder.addSeismicIntensityFlagFilter(type).build();

        addFlagFilterTest(result
                , TARGET_SEISMIC_INTENSITY
                , type
                , RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2);
    }

}
