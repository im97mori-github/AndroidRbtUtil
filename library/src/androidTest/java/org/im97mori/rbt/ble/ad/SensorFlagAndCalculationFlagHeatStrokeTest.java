package org.im97mori.rbt.ble.ad;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SensorFlagAndCalculationFlagHeatStrokeTest extends AbstractSensorFlagAndCalculationFlagTest {

    @Test
    public void test_heatStroke001() {
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
        data2[ 4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Heat stroke flag
        data2[ 5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(ALL_EVENT_FLAG_SENSOR, result.getHeatStrokeFlag());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertTrue(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertTrue(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke002() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getHeatStrokeFlag());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke003() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke004() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke005() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke006() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke007() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke008() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke009() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke010() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertTrue(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke011() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertTrue(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke012() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke013() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke014() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke015() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke016() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_heatStroke017() {
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
        data2[ 4] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 5] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

}
