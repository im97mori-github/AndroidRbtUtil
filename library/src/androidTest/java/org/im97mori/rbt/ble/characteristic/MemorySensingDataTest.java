package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemorySensingDataTest {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[0] = (byte) ((0x01) & 0xff); // Memory index
        data[1] = (byte) ((0x00) & 0xff); // Memory index
        data[2] = (byte) ((0x00) & 0xff); // Memory index
        data[3] = (byte) ((0x00) & 0xff); // Memory index
        data[4] = (byte) 0x60; // Temperature
        data[5] = (byte) 0xf0; // Temperature
        data[6] = (byte) 0x00; // Relative humidity
        data[7] = (byte) 0x00; // Relative humidity
        data[8] = (byte) 0x00; // Ambient light
        data[9] = (byte) 0x00; // Ambient light
        data[10] = (byte) 0xe0; // Barometric pressure
        data[11] = (byte) 0x93; // Barometric pressure
        data[12] = (byte) 0x04; // Barometric pressure
        data[13] = (byte) 0x00; // Barometric pressure
        data[14] = (byte) 0xe4; // Sound noise
        data[15] = (byte) 0x0c; // Sound noise
        data[16] = (byte) 0x00; // eTVOC
        data[17] = (byte) 0x00; // eTVOC
        data[18] = (byte) 0x90; // eCO2
        data[19] = (byte) 0x01; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemorySensingData result = new MemorySensingData(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff); // Memory index
        data[ 1] = (byte) ((0xff) & 0xff); // Memory index
        data[ 2] = (byte) ((0xff) & 0xff); // Memory index
        data[ 3] = (byte) ((0x7f) & 0xff); // Memory index
        data[ 4] = (byte) 0xd4; // Temperature
        data[ 5] = (byte) 0x30; // Temperature
        data[ 6] = (byte) 0x10; // Relative humidity
        data[ 7] = (byte) 0x27; // Relative humidity
        data[ 8] = (byte) 0x30; // Ambient light
        data[ 9] = (byte) 0x75; // Ambient light
        data[10] = (byte) 0xe0; // Barometric pressure
        data[11] = (byte) 0xc8; // Barometric pressure
        data[12] = (byte) 0x10; // Barometric pressure
        data[13] = (byte) 0x00; // Barometric pressure
        data[14] = (byte) 0xe0; // Sound noise
        data[15] = (byte) 0x2e; // Sound noise
        data[16] = (byte) 0xff; // eTVOC
        data[17] = (byte) 0x7f; // eTVOC
        data[18] = (byte) 0xff; // eCO2
        data[19] = (byte) 0x7f; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemorySensingData result = new MemorySensingData(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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
    public void test003() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0xd4; // Temperature
        data[ 5] = (byte) 0x30; // Temperature
        data[ 6] = (byte) 0x10; // Relative humidity
        data[ 7] = (byte) 0x27; // Relative humidity
        data[ 8] = (byte) 0x30; // Ambient light
        data[ 9] = (byte) 0x75; // Ambient light
        data[10] = (byte) 0xe0; // Barometric pressure
        data[11] = (byte) 0xc8; // Barometric pressure
        data[12] = (byte) 0x10; // Barometric pressure
        data[13] = (byte) 0x00; // Barometric pressure
        data[14] = (byte) 0xe0; // Sound noise
        data[15] = (byte) 0x2e; // Sound noise
        data[16] = (byte) 0xff; // eTVOC
        data[17] = (byte) 0x7f; // eTVOC
        data[18] = (byte) 0xff; // eCO2
        data[19] = (byte) 0x7f; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemorySensingData result = new MemorySensingData(bluetoothGattCharacteristic);
        assertEquals(-2147483648, result.getMemoryIndex());
        assertTrue(result.isMemoryIndexDataError());
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
    public void test004() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0xd4; // Temperature
        data[ 5] = (byte) 0x30; // Temperature
        data[ 6] = (byte) 0x10; // Relative humidity
        data[ 7] = (byte) 0x27; // Relative humidity
        data[ 8] = (byte) 0x30; // Ambient light
        data[ 9] = (byte) 0x75; // Ambient light
        data[10] = (byte) 0xe0; // Barometric pressure
        data[11] = (byte) 0xc8; // Barometric pressure
        data[12] = (byte) 0x10; // Barometric pressure
        data[13] = (byte) 0x00; // Barometric pressure
        data[14] = (byte) 0xe0; // Sound noise
        data[15] = (byte) 0x2e; // Sound noise
        data[16] = (byte) 0xff; // eTVOC
        data[17] = (byte) 0x7f; // eTVOC
        data[18] = (byte) 0xff; // eCO2
        data[19] = (byte) 0x7f; // eCO2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemorySensingData result1 = new MemorySensingData(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemorySensingData result2 = MemorySensingData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryIndex(), result2.getMemoryIndex());
        assertEquals(result1.getTemperature(), result2.getTemperature());
        assertEquals(result1.getRelativeHumidity(), result2.getRelativeHumidity());
        assertEquals(result1.getAmbientLight(), result2.getAmbientLight());
        assertEquals(result1.getBarometricPressure(), result2.getBarometricPressure());
        assertEquals(result1.getSoundNoise(), result2.getSoundNoise());
        assertEquals(result1.getEtvoc(), result2.getEtvoc());
        assertEquals(result1.getEco2(), result2.getEco2());
    }
}
