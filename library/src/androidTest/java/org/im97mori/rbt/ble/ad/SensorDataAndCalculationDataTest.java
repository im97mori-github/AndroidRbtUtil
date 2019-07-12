package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.junit.Assert.assertEquals;

public class SensorDataAndCalculationDataTest {

    @Test
    public void test1() {
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

        SensorDataAndCalculationData result1 = new SensorDataAndCalculationData(data1, data2);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorDataAndCalculationData result2 = SensorDataAndCalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA, result1.getDataType());
        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getTemperature(), result1.getTemperature());
        assertEquals(result2.getTemperatureDegC(), result1.getTemperatureDegC(), 0);
        assertEquals(result2.getRelativeHumidity(), result1.getRelativeHumidity());
        assertEquals(result2.getRelativeHumidityRh(), result1.getRelativeHumidityRh(), 0);
        assertEquals(result2.getAmbientLight(), result1.getAmbientLight());
        assertEquals(result2.getAmbientLightLx(), result1.getAmbientLightLx(), 0);
        assertEquals(result2.getBarometricPressure(), result1.getBarometricPressure());
        assertEquals(result2.getBarometricPressureHpa(), result1.getBarometricPressureHpa(), 0);
        assertEquals(result2.getSoundNoise(), result1.getSoundNoise());
        assertEquals(result2.getSoundNoiseDb(), result1.getSoundNoiseDb(), 0);
        assertEquals(result2.getEtvoc(), result1.getEtvoc());
        assertEquals(result2.getEtvocPpb(), result1.getEtvocPpb(), 0);
        assertEquals(result2.getEco2(), result1.getEco2());
        assertEquals(result2.getEco2Ppm(), result1.getEco2Ppm(), 0);

        assertEquals(result2.getDiscomfortIndex(), result1.getDiscomfortIndex());
        assertEquals(result2.getDiscomfortIndexWithUnit(), result1.getDiscomfortIndexWithUnit(), 0);
        assertEquals(result2.getHeatStroke(), result1.getHeatStroke());
        assertEquals(result2.getHeatStrokeDegC(), result1.getHeatStrokeDegC(), 0);
        assertEquals(result2.getVibrationInformation(), result1.getVibrationInformation());
        assertEquals(result2.getSiValue(), result1.getSiValue());
        assertEquals(result2.getSiValueKine(), result1.getSiValueKine(), 0);
        assertEquals(result2.getPga(), result1.getPga());
        assertEquals(result2.getPgaGal(), result1.getPgaGal(), 0);
        assertEquals(result2.getSeismicIntensity(), result1.getSeismicIntensity());
        assertEquals(result2.getSeismicIntensityWithUnit(), result1.getSeismicIntensityWithUnit(), 0);
        assertEquals(result2.getAccelerationXAxis(), result1.getAccelerationXAxis());
        assertEquals(result2.getAccelerationXAxisGal(), result1.getAccelerationXAxisGal(), 0);
        assertEquals(result2.getAccelerationYAxis(), result1.getAccelerationYAxis());
        assertEquals(result2.getAccelerationYAxisGal(), result1.getAccelerationYAxisGal(), 0);
        assertEquals(result2.getAccelerationZAxis(), result1.getAccelerationZAxis());
        assertEquals(result2.getAccelerationZAxisGal(), result1.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test2() {
        byte[] data1 = new byte[19];
        data1[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data1[ 1] = (byte) 0xff; // Sequence number
        data1[ 2] = (byte) 0xd4; // Temperature
        data1[ 3] = (byte) 0x30; // Temperature
        data1[ 4] = (byte) 0x10; // Relative humidity
        data1[ 5] = (byte) 0x27; // Relative humidity
        data1[ 6] = (byte) 0x30; // Ambient light
        data1[ 7] = (byte) 0x75; // Ambient light
        data1[ 8] = (byte) 0xe0; // Barometric pressure
        data1[ 9] = (byte) 0xc8; // Barometric pressure
        data1[10] = (byte) 0x10; // Barometric pressure
        data1[11] = (byte) 0x00; // Barometric pressure
        data1[12] = (byte) 0xe0; // Sound noise
        data1[13] = (byte) 0x2e; // Sound noise
        data1[14] = (byte) 0xff; // eTVOC
        data1[15] = (byte) 0x7f; // eTVOC
        data1[16] = (byte) 0xff; // eCO2
        data1[17] = (byte) 0x7f; // eCO2
        data1[18] = (byte) 0xFF; // Reserve for Future Use

        byte[] data2 = new byte[19];
        data2[ 0] = DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
        data2[ 1] = (byte) 0xff; // Sequence number
        data2[ 2] = (byte) 0x10; // Discomfort index
        data2[ 3] = (byte) 0x27; // Discomfort index
        data2[ 4] = (byte) 0xd4; // Heat stroke
        data2[ 5] = (byte) 0x30; // Heat stroke
        data2[ 6] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data2[ 7] = (byte) 0xff; // SI value
        data2[ 8] = (byte) 0xff; // SI value
        data2[ 9] = (byte) 0xff; // PGA
        data2[10] = (byte) 0xff; // PGA
        data2[11] = (byte) 0xff; // Seismic intensity
        data2[12] = (byte) 0xff; // Seismic intensity
        data2[13] = (byte) 0x20; // Acceleration (X-axis)
        data2[14] = (byte) 0x4e; // Acceleration (X-axis)
        data2[15] = (byte) 0x20; // Acceleration (Y-axis)
        data2[16] = (byte) 0x4e; // Acceleration (Y-axis)
        data2[17] = (byte) 0x20; // Acceleration (Z-axis)
        data2[18] = (byte) 0x4e; // Acceleration (Z-axis)

        SensorDataAndCalculationData result1 = new SensorDataAndCalculationData(data1, data2);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorDataAndCalculationData result2 = SensorDataAndCalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA, result1.getDataType());
        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getTemperature(), result1.getTemperature());
        assertEquals(result2.getTemperatureDegC(), result1.getTemperatureDegC(), 0);
        assertEquals(result2.getRelativeHumidity(), result1.getRelativeHumidity());
        assertEquals(result2.getRelativeHumidityRh(), result1.getRelativeHumidityRh(), 0);
        assertEquals(result2.getAmbientLight(), result1.getAmbientLight());
        assertEquals(result2.getAmbientLightLx(), result1.getAmbientLightLx(), 0);
        assertEquals(result2.getBarometricPressure(), result1.getBarometricPressure());
        assertEquals(result2.getBarometricPressureHpa(), result1.getBarometricPressureHpa(), 0);
        assertEquals(result2.getSoundNoise(), result1.getSoundNoise());
        assertEquals(result2.getSoundNoiseDb(), result1.getSoundNoiseDb(), 0);
        assertEquals(result2.getEtvoc(), result1.getEtvoc());
        assertEquals(result2.getEtvocPpb(), result1.getEtvocPpb(), 0);
        assertEquals(result2.getEco2(), result1.getEco2());
        assertEquals(result2.getEco2Ppm(), result1.getEco2Ppm(), 0);

        assertEquals(result2.getDiscomfortIndex(), result1.getDiscomfortIndex());
        assertEquals(result2.getDiscomfortIndexWithUnit(), result1.getDiscomfortIndexWithUnit(), 0);
        assertEquals(result2.getHeatStroke(), result1.getHeatStroke());
        assertEquals(result2.getHeatStrokeDegC(), result1.getHeatStrokeDegC(), 0);
        assertEquals(result2.getVibrationInformation(), result1.getVibrationInformation());
        assertEquals(result2.getSiValue(), result1.getSiValue());
        assertEquals(result2.getSiValueKine(), result1.getSiValueKine(), 0);
        assertEquals(result2.getPga(), result1.getPga());
        assertEquals(result2.getPgaGal(), result1.getPgaGal(), 0);
        assertEquals(result2.getSeismicIntensity(), result1.getSeismicIntensity());
        assertEquals(result2.getSeismicIntensityWithUnit(), result1.getSeismicIntensityWithUnit(), 0);
        assertEquals(result2.getAccelerationXAxis(), result1.getAccelerationXAxis());
        assertEquals(result2.getAccelerationXAxisGal(), result1.getAccelerationXAxisGal(), 0);
        assertEquals(result2.getAccelerationYAxis(), result1.getAccelerationYAxis());
        assertEquals(result2.getAccelerationYAxisGal(), result1.getAccelerationYAxisGal(), 0);
        assertEquals(result2.getAccelerationZAxis(), result1.getAccelerationZAxis());
        assertEquals(result2.getAccelerationZAxisGal(), result1.getAccelerationZAxisGal(), 0);
    }
}
