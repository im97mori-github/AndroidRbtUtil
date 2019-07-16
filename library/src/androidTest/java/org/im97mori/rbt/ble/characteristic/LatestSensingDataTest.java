package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LatestSensingDataTest {

    @Test
    public void test001() {
        byte[] data = new byte[17];
        data[0] = (byte) ((0x00) & 0xff); // Sequence number
        data[1] = (byte) 0x60; // Temperature
        data[2] = (byte) 0xf0; // Temperature
        data[3] = (byte) 0x00; // Relative humidity
        data[4] = (byte) 0x00; // Relative humidity
        data[5] = (byte) 0x00; // Ambient light
        data[6] = (byte) 0x00; // Ambient light
        data[7] = (byte) 0xe0; // Barometric pressure
        data[8] = (byte) 0x93; // Barometric pressure
        data[9] = (byte) 0x04; // Barometric pressure
        data[10] = (byte) 0x00; // Barometric pressure
        data[11] = (byte) 0xe4; // Sound noise
        data[12] = (byte) 0x0c; // Sound noise
        data[13] = (byte) 0x00; // eTVOC
        data[14] = (byte) 0x00; // eTVOC
        data[15] = (byte) 0x90; // eCO2
        data[16] = (byte) 0x01; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestSensingData latestSensingData = new LatestSensingData(bluetoothGattCharacteristic);
        assertEquals(0, latestSensingData.getSequenceNumber());
        assertEquals(-4000, latestSensingData.getTemperature());
        assertEquals(-40.00d, latestSensingData.getTemperatureDegC(), 0);
        assertEquals(0, latestSensingData.getRelativeHumidity());
        assertEquals(0d, latestSensingData.getRelativeHumidityRh(), 0);
        assertEquals(0, latestSensingData.getAmbientLight());
        assertEquals(0d, latestSensingData.getAmbientLightLx(), 0);
        assertEquals(300000, latestSensingData.getBarometricPressure());
        assertEquals(300.000d, latestSensingData.getBarometricPressureHpa(), 0);
        assertEquals(3300, latestSensingData.getSoundNoise());
        assertEquals(33.00d, latestSensingData.getSoundNoiseDb(), 0);
        assertEquals(0, latestSensingData.getEtvoc());
        assertEquals(0d, latestSensingData.getEtvocPpb(), 0);
        assertEquals(400, latestSensingData.getEco2());
        assertEquals(400d, latestSensingData.getEco2Ppm(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[17];
        data[0] = (byte) 0xff; // Sequence number
        data[1] = (byte) 0xd4; // Temperature
        data[2] = (byte) 0x30; // Temperature
        data[3] = (byte) 0x10; // Relative humidity
        data[4] = (byte) 0x27; // Relative humidity
        data[5] = (byte) 0x30; // Ambient light
        data[6] = (byte) 0x75; // Ambient light
        data[7] = (byte) 0xe0; // Barometric pressure
        data[8] = (byte) 0xc8; // Barometric pressure
        data[9] = (byte) 0x10; // Barometric pressure
        data[10] = (byte) 0x00; // Barometric pressure
        data[11] = (byte) 0xe0; // Sound noise
        data[12] = (byte) 0x2e; // Sound noise
        data[13] = (byte) 0xff; // eTVOC
        data[14] = (byte) 0x7f; // eTVOC
        data[15] = (byte) 0xff; // eCO2
        data[16] = (byte) 0x7f; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestSensingData latestSensingData = new LatestSensingData(bluetoothGattCharacteristic);
        assertEquals(255, latestSensingData.getSequenceNumber());
        assertEquals(12500, latestSensingData.getTemperature());
        assertEquals(125.00d, latestSensingData.getTemperatureDegC(), 0);
        assertEquals(10000, latestSensingData.getRelativeHumidity());
        assertEquals(100.00d, latestSensingData.getRelativeHumidityRh(), 0);
        assertEquals(30000, latestSensingData.getAmbientLight());
        assertEquals(30000d, latestSensingData.getAmbientLightLx(), 0);
        assertEquals(1100000, latestSensingData.getBarometricPressure());
        assertEquals(1100.000d, latestSensingData.getBarometricPressureHpa(), 0);
        assertEquals(12000, latestSensingData.getSoundNoise());
        assertEquals(120.00d, latestSensingData.getSoundNoiseDb(), 0);
        assertEquals(32767, latestSensingData.getEtvoc());
        assertEquals(32767d, latestSensingData.getEtvocPpb(), 0);
        assertEquals(32767, latestSensingData.getEco2());
        assertEquals(32767d, latestSensingData.getEco2Ppm(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[17];
        data[0] = (byte) 0xff; // Sequence number
        data[1] = (byte) 0xd4; // Temperature
        data[2] = (byte) 0x30; // Temperature
        data[3] = (byte) 0x10; // Relative humidity
        data[4] = (byte) 0x27; // Relative humidity
        data[5] = (byte) 0x30; // Ambient light
        data[6] = (byte) 0x75; // Ambient light
        data[7] = (byte) 0xe0; // Barometric pressure
        data[8] = (byte) 0xc8; // Barometric pressure
        data[9] = (byte) 0x10; // Barometric pressure
        data[10] = (byte) 0x00; // Barometric pressure
        data[11] = (byte) 0xe0; // Sound noise
        data[12] = (byte) 0x2e; // Sound noise
        data[13] = (byte) 0xff; // eTVOC
        data[14] = (byte) 0x7f; // eTVOC
        data[15] = (byte) 0xff; // eCO2
        data[16] = (byte) 0x7f; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestSensingData result1 = new LatestSensingData(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestSensingData result2 = LatestSensingData.CREATOR.createFromParcel(parcel);

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
