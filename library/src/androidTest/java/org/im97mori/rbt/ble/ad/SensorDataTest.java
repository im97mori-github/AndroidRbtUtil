package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.junit.Assert.assertEquals;

public class SensorDataTest {

    @Test
    public void test1() {
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

        SensorData result = new SensorData(data);
        assertEquals(DATA_TYPE_SENSOR_DATA, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(-4000, result.getTemperature());
        assertEquals(-40.00d, result.getTemperatureDegC(), 0);
        assertEquals(0, result.getRelativeHumidity());
        assertEquals(0d, result.getRelativeHumidityRh(), 0);
        assertEquals(0, result.getAmbientLight());
        assertEquals(0d, result.getAmbientLightLx(), 0);
        assertEquals(300000, result.getBarometricPressure());
        assertEquals(300.000d, result.getBarometricPressureHpa(), 0);
        assertEquals(3300, result.getSoundNoise());
        assertEquals(33.00d, result.getSoundNoiseDb(), 0);
        assertEquals(0, result.getEtvoc());
        assertEquals(0d, result.getEtvocPpb(), 0);
        assertEquals(400, result.getEco2());
        assertEquals(400d, result.getEco2Ppm(), 0);
    }

    @Test
    public void test2() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_SENSOR_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0xd4; // Temperature
        data[ 3] = (byte) 0x30; // Temperature
        data[ 4] = (byte) 0x10; // Relative humidity
        data[ 5] = (byte) 0x27; // Relative humidity
        data[ 6] = (byte) 0x30; // Ambient light
        data[ 7] = (byte) 0x75; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0xc8; // Barometric pressure
        data[10] = (byte) 0x10; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe0; // Sound noise
        data[13] = (byte) 0x2e; // Sound noise
        data[14] = (byte) 0xff; // eTVOC
        data[15] = (byte) 0x7f; // eTVOC
        data[16] = (byte) 0xff; // eCO2
        data[17] = (byte) 0x7f; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        SensorData result = new SensorData(data);
        assertEquals(DATA_TYPE_SENSOR_DATA, result.getDataType());
        assertEquals(255, result.getSequenceNumber());
        assertEquals(12500, result.getTemperature());
        assertEquals(125.00d, result.getTemperatureDegC(), 0);
        assertEquals(10000, result.getRelativeHumidity());
        assertEquals(100.00d, result.getRelativeHumidityRh(), 0);
        assertEquals(30000, result.getAmbientLight());
        assertEquals(30000d, result.getAmbientLightLx(), 0);
        assertEquals(1100000, result.getBarometricPressure());
        assertEquals(1100.000d, result.getBarometricPressureHpa(), 0);
        assertEquals(12000, result.getSoundNoise());
        assertEquals(120.00d, result.getSoundNoiseDb(), 0);
        assertEquals(32767, result.getEtvoc());
        assertEquals(32767d, result.getEtvocPpb(), 0);
        assertEquals(32767, result.getEco2());
        assertEquals(32767d, result.getEco2Ppm(), 0);
    }

    @Test
    public void test3() {
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
    public void test4() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_SENSOR_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0xd4; // Temperature
        data[ 3] = (byte) 0x30; // Temperature
        data[ 4] = (byte) 0x10; // Relative humidity
        data[ 5] = (byte) 0x27; // Relative humidity
        data[ 6] = (byte) 0x30; // Ambient light
        data[ 7] = (byte) 0x75; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0xc8; // Barometric pressure
        data[10] = (byte) 0x10; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe0; // Sound noise
        data[13] = (byte) 0x2e; // Sound noise
        data[14] = (byte) 0xff; // eTVOC
        data[15] = (byte) 0x7f; // eTVOC
        data[16] = (byte) 0xff; // eCO2
        data[17] = (byte) 0x7f; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

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
    public void test5() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_SENSOR_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0xd4; // Temperature
        data[ 3] = (byte) 0x30; // Temperature
        data[ 4] = (byte) 0x10; // Relative humidity
        data[ 5] = (byte) 0x27; // Relative humidity
        data[ 6] = (byte) 0x30; // Ambient light
        data[ 7] = (byte) 0x75; // Ambient light
        data[ 8] = (byte) 0xe0; // Barometric pressure
        data[ 9] = (byte) 0xc8; // Barometric pressure
        data[10] = (byte) 0x10; // Barometric pressure
        data[11] = (byte) 0x00; // Barometric pressure
        data[12] = (byte) 0xe0; // Sound noise
        data[13] = (byte) 0x2e; // Sound noise
        data[14] = (byte) 0xff; // eTVOC
        data[15] = (byte) 0x7f; // eTVOC
        data[16] = (byte) 0xff; // eCO2
        data[17] = (byte) 0x7f; // eCO2
        data[18] = (byte) 0xFF; // Reserve for Future Use
        //@formatter:on

        SensorData result1 = new SensorData(data);
        SensorData result2 = SensorData.CREATOR.createFromByteArray(data);

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
