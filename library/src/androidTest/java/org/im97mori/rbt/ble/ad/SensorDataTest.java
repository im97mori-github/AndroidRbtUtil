package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.junit.Assert.assertEquals;

public class SensorDataTest {

    @Test
    public void test1() {
        byte[] data = new byte[19];
        data[0] = DATA_TYPE_SENSOR_DATA;
        data[1] = (byte) 0x00; // Sequence number
        data[2] = (byte) 0x60; // Temperature
        data[3] = (byte) 0xf0; // Temperature
        data[4] = (byte) 0x00; // Relative humidity
        data[5] = (byte) 0x00; // Relative humidity
        data[6] = (byte) 0x00; // Ambient light
        data[7] = (byte) 0x00; // Ambient light
        data[8] = (byte) 0xe0; // Barometric pressure
        data[9] = (byte) 0x93; // Barometric pressure
        data[10] = (byte) 0x04; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe4; // Sound noise
        data[13] = (byte) 0x0c; // Sound noise
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x00; // eTVOC
        data[16] = (byte) 0x90; // eCO2
        data[17] = (byte) 0x01; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use

        SensorData result1 = new SensorData(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorData result2 = SensorData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getTemperature(), result2.getTemperature());
        assertEquals(result1.getTemperatureDegC(), result2.getTemperatureDegC(), 0);
        assertEquals(result1.getRelativeHumidity(), result2.getRelativeHumidity());
        assertEquals(result1.getRelativeHumidityRh(), result2.getRelativeHumidityRh(), 0);
        assertEquals(result1.getAmbientLight(), result2.getAmbientLight());
        assertEquals(result1.getAmbientLightLx(), result2.getAmbientLightLx(), 0);
        assertEquals(result1.getBarometricPressure(), result2.getBarometricPressure());
        assertEquals(result1.getBarometricPressureHpa(), result2.getBarometricPressureHpa(), 0);
        assertEquals(result1.getSoundNoise(), result2.getSoundNoise());
        assertEquals(result1.getSoundNoiseDb(), result2.getSoundNoiseDb(), 0);
        assertEquals(result1.getEtvoc(), result2.getEtvoc());
        assertEquals(result1.getEtvocPpb(), result2.getEtvocPpb(), 0);
        assertEquals(result1.getEco2(), result2.getEco2());
        assertEquals(result1.getEco2Ppm(), result2.getEco2Ppm(), 0);
    }

    @Test
    public void test2() {
        byte[] data = new byte[19];
        data[0] = DATA_TYPE_SENSOR_DATA;
        data[1] = (byte) 0xff; // Sequence number
        data[2] = (byte) 0xd4; // Temperature
        data[3] = (byte) 0x30; // Temperature
        data[4] = (byte) 0x10; // Relative humidity
        data[5] = (byte) 0x27; // Relative humidity
        data[6] = (byte) 0x30; // Ambient light
        data[7] = (byte) 0x75; // Ambient light
        data[8] = (byte) 0xe0; // Barometric pressure
        data[9] = (byte) 0xc8; // Barometric pressure
        data[10] = (byte) 0x10; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe0; // Sound noise
        data[13] = (byte) 0x2e; // Sound noise
        data[14] = (byte) 0xff; // eTVOC
        data[15] = (byte) 0x7f; // eTVOC
        data[16] = (byte) 0xff; // eCO2
        data[17] = (byte) 0x7f; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use

        SensorData result1 = new SensorData(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SensorData result2 = SensorData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getTemperature(), result2.getTemperature());
        assertEquals(result1.getTemperatureDegC(), result2.getTemperatureDegC(), 0);
        assertEquals(result1.getRelativeHumidity(), result2.getRelativeHumidity());
        assertEquals(result1.getRelativeHumidityRh(), result2.getRelativeHumidityRh(), 0);
        assertEquals(result1.getAmbientLight(), result2.getAmbientLight());
        assertEquals(result1.getAmbientLightLx(), result2.getAmbientLightLx(), 0);
        assertEquals(result1.getBarometricPressure(), result2.getBarometricPressure());
        assertEquals(result1.getBarometricPressureHpa(), result2.getBarometricPressureHpa(), 0);
        assertEquals(result1.getSoundNoise(), result2.getSoundNoise());
        assertEquals(result1.getSoundNoiseDb(), result2.getSoundNoiseDb(), 0);
        assertEquals(result1.getEtvoc(), result2.getEtvoc());
        assertEquals(result1.getEtvocPpb(), result2.getEtvocPpb(), 0);
        assertEquals(result1.getEco2(), result2.getEco2());
        assertEquals(result1.getEco2Ppm(), result2.getEco2Ppm(), 0);
    }
}
