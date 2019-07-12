package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.junit.Assert.assertEquals;

public class SensorFlagAndCalculationFlagTest extends AbstractSensorFlagAndCalculationFlagTest {

    @Test
    public void test_001() {
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
    public void test_002() {
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

}
