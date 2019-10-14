package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SensorFlagAndCalculationFlagTest extends AbstractSensorFlagAndCalculationFlagTest {

    @Test
    public void test_001() {
        byte[] data1 = new byte[19];
        data1[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[1] = (byte) 0x00; // Sequence number
        data1[2] = (byte) 0x00; // Temperature flag
        data1[3] = (byte) 0x00; // Temperature flag
        data1[4] = (byte) 0x00; // Relative humidity flag
        data1[5] = (byte) 0x00; // Relative humidity flag
        data1[6] = (byte) 0x00; // Ambient light flag
        data1[7] = (byte) 0x00; // Ambient light flag
        data1[8] = (byte) 0x00; // Barometric pressure flag
        data1[9] = (byte) 0x00; // Barometric pressure flag
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
        data2[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[1] = (byte) 0x00; // Sequence number
        data2[2] = (byte) 0x00; // Discomfort index flag
        data2[3] = (byte) 0x00; // Discomfort index flag
        data2[4] = (byte) 0x00; // Heat stroke flag
        data2[5] = (byte) 0x00; // Heat stroke flag
        data2[6] = (byte) 0x00; // SI value flag
        data2[7] = (byte) 0x00; // PGA flag
        data2[8] = (byte) 0x00; // Seismic intensity flag
        data2[9] = (byte) 0xff; // Reserve for Future Use
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

        assertEquals(0, result.getHeatStrokeFlag());
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
    public void test_002() {
        byte[] data1 = new byte[19];
        data1[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[1] = (byte) 0xff; // Sequence number
        data1[2] = (byte) 0x00; // Temperature flag
        data1[3] = (byte) 0x00; // Temperature flag
        data1[4] = (byte) 0x00; // Relative humidity flag
        data1[5] = (byte) 0x00; // Relative humidity flag
        data1[6] = (byte) 0x00; // Ambient light flag
        data1[7] = (byte) 0x00; // Ambient light flag
        data1[8] = (byte) 0x00; // Barometric pressure flag
        data1[9] = (byte) 0x00; // Barometric pressure flag
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
        data2[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[1] = (byte) 0x00; // Sequence number
        data2[2] = (byte) 0x00; // Discomfort index flag
        data2[3] = (byte) 0x00; // Discomfort index flag
        data2[4] = (byte) 0x00; // Heat stroke flag
        data2[5] = (byte) 0x00; // Heat stroke flag
        data2[6] = (byte) 0x00; // SI value flag
        data2[7] = (byte) 0x00; // PGA flag
        data2[8] = (byte) 0x00; // Seismic intensity flag
        data2[9] = (byte) 0xff; // Reserve for Future Use
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

        SensorFlagAndCalculationFlag result = new SensorFlagAndCalculationFlag(data1, data2);
        assertEquals(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG, result.getDataType());
        assertEquals(255, result.getSequenceNumber());
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

        assertEquals(0, result.getHeatStrokeFlag());
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
    public void test_003() {
        byte[] data1 = new byte[19];
        data1[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[1] = (byte) 0x00; // Sequence number
        data1[2] = (byte) 0x00; // Temperature flag
        data1[3] = (byte) 0x00; // Temperature flag
        data1[4] = (byte) 0x00; // Relative humidity flag
        data1[5] = (byte) 0x00; // Relative humidity flag
        data1[6] = (byte) 0x00; // Ambient light flag
        data1[7] = (byte) 0x00; // Ambient light flag
        data1[8] = (byte) 0x00; // Barometric pressure flag
        data1[9] = (byte) 0x00; // Barometric pressure flag
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
        data2[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[1] = (byte) 0xff; // Sequence number
        data2[2] = (byte) 0x00; // Discomfort index flag
        data2[3] = (byte) 0x00; // Discomfort index flag
        data2[4] = (byte) 0x00; // Heat stroke flag
        data2[5] = (byte) 0x00; // Heat stroke flag
        data2[6] = (byte) 0x00; // SI value flag
        data2[7] = (byte) 0x00; // PGA flag
        data2[8] = (byte) 0x00; // Seismic intensity flag
        data2[9] = (byte) 0xff; // Reserve for Future Use
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

        assertEquals(0, result.getHeatStrokeFlag());
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
    public void test_004() {
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

        SensorFlagAndCalculationFlag result1 = new SensorFlagAndCalculationFlag(data1, data2);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorFlagAndCalculationFlag result2 = SensorFlagAndCalculationFlag.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getDataType(), result1.getDataType());
        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getTemperatureFlag(), result1.getTemperatureFlag());
        assertEquals(result2.getRelativeHumidityFlag(), result1.getRelativeHumidityFlag());
        assertEquals(result2.getAmbientLightFlag(), result1.getAmbientLightFlag());
        assertEquals(result2.getBarometricPressureFlag(), result1.getBarometricPressureFlag());
        assertEquals(result2.getSoundNoiseFlag(), result1.getSoundNoiseFlag());
        assertEquals(result2.getEtvocFlag(), result1.getEtvocFlag());
        assertEquals(result2.getEco2Flag(), result1.getEco2Flag());
        assertEquals(result2.getDiscomfortIndexFlag(), result1.getDiscomfortIndexFlag());
        assertEquals(result2.getHeatStrokeFlag(), result1.getHeatStrokeFlag());
        assertEquals(result2.getSiValueFlag(), result1.getSiValueFlag());
        assertEquals(result2.getPgaFlag(), result1.getPgaFlag());
        assertEquals(result2.getSeismicIntensityFlag(), result1.getSeismicIntensityFlag());
    }

    @Test
    public void test_005() {
        byte[] data1 = new byte[19];
        data1[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[1] = (byte) 0xff; // Sequence number
        data1[2] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Temperature flag
        data1[3] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Temperature flag
        data1[4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Relative humidity flag
        data1[5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Relative humidity flag
        data1[6] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Ambient light flag
        data1[7] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Ambient light flag
        data1[8] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Barometric pressure flag
        data1[9] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Barometric pressure flag
        data1[10] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Sound noise flag
        data1[11] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Sound noise flag
        data1[12] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // eTVOC flag
        data1[13] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // eTVOC flag
        data1[14] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // eCO2 flag
        data1[15] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // eCO2 flag
        data1[16] = (byte) 0xFF; // Reserve for Future Use
        data1[17] = (byte) 0xFF; // Reserve for Future Use
        data1[18] = (byte) 0xFF; // Reserve for Future Use

        byte[] data2 = new byte[27];
        data2[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[1] = (byte) 0xff; // Sequence number
        data2[2] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Discomfort index flag
        data2[3] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Discomfort index flag
        data2[4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Heat stroke flag
        data2[5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Heat stroke flag
        data2[6] = (byte) ALL_EVENT_FLAG_ACCELERATION; // SI value flag
        data2[7] = (byte) ALL_EVENT_FLAG_ACCELERATION; // PGA flag
        data2[8] = (byte) ALL_EVENT_FLAG_ACCELERATION; // Seismic intensity flag
        data2[9] = (byte) 0xff; // Reserve for Future Use
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

        SensorFlagAndCalculationFlag result1 = new SensorFlagAndCalculationFlag(data1, data2);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorFlagAndCalculationFlag result2 = SensorFlagAndCalculationFlag.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getDataType(), result1.getDataType());
        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getTemperatureFlag(), result1.getTemperatureFlag());
        assertEquals(result2.getRelativeHumidityFlag(), result1.getRelativeHumidityFlag());
        assertEquals(result2.getAmbientLightFlag(), result1.getAmbientLightFlag());
        assertEquals(result2.getBarometricPressureFlag(), result1.getBarometricPressureFlag());
        assertEquals(result2.getSoundNoiseFlag(), result1.getSoundNoiseFlag());
        assertEquals(result2.getEtvocFlag(), result1.getEtvocFlag());
        assertEquals(result2.getEco2Flag(), result1.getEco2Flag());
        assertEquals(result2.getDiscomfortIndexFlag(), result1.getDiscomfortIndexFlag());
        assertEquals(result2.getHeatStrokeFlag(), result1.getHeatStrokeFlag());
        assertEquals(result2.getSiValueFlag(), result1.getSiValueFlag());
        assertEquals(result2.getPgaFlag(), result1.getPgaFlag());
        assertEquals(result2.getSeismicIntensityFlag(), result1.getSeismicIntensityFlag());
    }

    @Test
    public void test_006() {
        byte[] data1 = new byte[19];
        data1[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data1[1] = (byte) 0xff; // Sequence number
        data1[2] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Temperature flag
        data1[3] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Temperature flag
        data1[4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Relative humidity flag
        data1[5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Relative humidity flag
        data1[6] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Ambient light flag
        data1[7] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Ambient light flag
        data1[8] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Barometric pressure flag
        data1[9] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Barometric pressure flag
        data1[10] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Sound noise flag
        data1[11] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Sound noise flag
        data1[12] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // eTVOC flag
        data1[13] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // eTVOC flag
        data1[14] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // eCO2 flag
        data1[15] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // eCO2 flag
        data1[16] = (byte) 0xFF; // Reserve for Future Use
        data1[17] = (byte) 0xFF; // Reserve for Future Use
        data1[18] = (byte) 0xFF; // Reserve for Future Use

        byte[] data2 = new byte[27];
        data2[0] = DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
        data2[1] = (byte) 0xff; // Sequence number
        data2[2] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Discomfort index flag
        data2[3] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Discomfort index flag
        data2[4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Heat stroke flag
        data2[5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Heat stroke flag
        data2[6] = (byte) ALL_EVENT_FLAG_ACCELERATION; // SI value flag
        data2[7] = (byte) ALL_EVENT_FLAG_ACCELERATION; // PGA flag
        data2[8] = (byte) ALL_EVENT_FLAG_ACCELERATION; // Seismic intensity flag
        data2[9] = (byte) 0xff; // Reserve for Future Use
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

        byte[] data = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data, 0, data1.length);
        System.arraycopy(data2, 0, data, data1.length, data2.length);

        SensorFlagAndCalculationFlag result1 = new SensorFlagAndCalculationFlag(data1, data2);
        SensorFlagAndCalculationFlag result2 = SensorFlagAndCalculationFlag.CREATOR.createFromByteArray(data);

        assertEquals(result2.getDataType(), result1.getDataType());
        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getTemperatureFlag(), result1.getTemperatureFlag());
        assertEquals(result2.getRelativeHumidityFlag(), result1.getRelativeHumidityFlag());
        assertEquals(result2.getAmbientLightFlag(), result1.getAmbientLightFlag());
        assertEquals(result2.getBarometricPressureFlag(), result1.getBarometricPressureFlag());
        assertEquals(result2.getSoundNoiseFlag(), result1.getSoundNoiseFlag());
        assertEquals(result2.getEtvocFlag(), result1.getEtvocFlag());
        assertEquals(result2.getEco2Flag(), result1.getEco2Flag());
        assertEquals(result2.getDiscomfortIndexFlag(), result1.getDiscomfortIndexFlag());
        assertEquals(result2.getHeatStrokeFlag(), result1.getHeatStrokeFlag());
        assertEquals(result2.getSiValueFlag(), result1.getSiValueFlag());
        assertEquals(result2.getPgaFlag(), result1.getPgaFlag());
        assertEquals(result2.getSeismicIntensityFlag(), result1.getSeismicIntensityFlag());
    }

}
