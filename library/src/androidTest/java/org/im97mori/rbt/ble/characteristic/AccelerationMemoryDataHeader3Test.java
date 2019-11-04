package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.BLEConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccelerationMemoryDataHeader3Test {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[0] = (byte) ((0x01) & 0xff);
        data[1] = (byte) ((0x00) & 0xff);
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xe0) & 0xff);
        data[5] = (byte) ((0x93) & 0xff);
        data[6] = (byte) ((0x04) & 0xff);
        data[7] = (byte) ((0x00) & 0xff);
        data[8] = (byte) ((0xe4) & 0xff);
        data[9] = (byte) ((0x0c) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x00) & 0xff);
        data[12] = (byte) ((0x90) & 0xff);
        data[13] = (byte) ((0x01) & 0xff);
        data[14] = (byte) ((0x00) & 0xff);
        data[15] = (byte) ((0x00) & 0xff);
        data[16] = (byte) ((0x60) & 0xff);
        data[17] = (byte) ((0xf0) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        assertEquals(1, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
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
        assertEquals(0, result.getDiscomfortIndex());
        assertEquals(0.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(-4000, result.getHeatStroke());
        assertEquals(-40.00d, result.getHeatStrokeDegC(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[0] = (byte) ((0xff) & 0xff);
        data[1] = (byte) ((0x7f) & 0xff);
        data[2] = (byte) ((0x30) & 0xff);
        data[3] = (byte) ((0x75) & 0xff);
        data[4] = (byte) ((0xe0) & 0xff);
        data[5] = (byte) ((0xc8) & 0xff);
        data[6] = (byte) ((0x10) & 0xff);
        data[7] = (byte) ((0x00) & 0xff);
        data[8] = (byte) ((0xe0) & 0xff);
        data[9] = (byte) ((0x2e) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0x7f) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0x7f) & 0xff);
        data[14] = (byte) ((0x10) & 0xff);
        data[15] = (byte) ((0x27) & 0xff);
        data[16] = (byte) ((0xd4) & 0xff);
        data[17] = (byte) ((0x30) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        assertEquals(32767, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
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
        assertEquals(10000, result.getDiscomfortIndex());
        assertEquals(100.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(12500, result.getHeatStroke());
        assertEquals(125.00d, result.getHeatStrokeDegC(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[20];
        data[0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[2] = (byte) ((0x30) & 0xff);
        data[3] = (byte) ((0x75) & 0xff);
        data[4] = (byte) ((0xe0) & 0xff);
        data[5] = (byte) ((0xc8) & 0xff);
        data[6] = (byte) ((0x10) & 0xff);
        data[7] = (byte) ((0x00) & 0xff);
        data[8] = (byte) ((0xe0) & 0xff);
        data[9] = (byte) ((0x2e) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0x7f) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0x7f) & 0xff);
        data[14] = (byte) ((0x10) & 0xff);
        data[15] = (byte) ((0x27) & 0xff);
        data[16] = (byte) ((0xd4) & 0xff);
        data[17] = (byte) ((0x30) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        assertEquals(32768, result.getTotalTransferCount());
        assertTrue(result.isTotalTransferCountDataError());
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
        assertEquals(10000, result.getDiscomfortIndex());
        assertEquals(100.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(12500, result.getHeatStroke());
        assertEquals(125.00d, result.getHeatStrokeDegC(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0x07) & 0xff);
        data[ 9] = (byte) ((0x08) & 0xff);
        data[10] = (byte) ((0x09) & 0xff);
        data[11] = (byte) ((0x0a) & 0xff);
        data[12] = (byte) ((0x0b) & 0xff);
        data[13] = (byte) ((0x0c) & 0xff);
        data[14] = (byte) ((0x0d) & 0xff);
        data[15] = (byte) ((0x0e) & 0xff);
        data[16] = (byte) ((0x0f) & 0xff);
        data[17] = (byte) ((0x0a) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result1 = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryDataHeader3 result2 = AccelerationMemoryDataHeader3.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
        assertEquals(result1.getAmbientLight(), result2.getAmbientLight());
        assertEquals(result1.getBarometricPressure(), result2.getBarometricPressure());
        assertEquals(result1.getSoundNoise(), result2.getSoundNoise());
        assertEquals(result1.getEtvoc(), result2.getEtvoc());
        assertEquals(result1.getEco2(), result2.getEco2());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
    }

    @Test
    public void test005() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0x07) & 0xff);
        data[ 9] = (byte) ((0x08) & 0xff);
        data[10] = (byte) ((0x09) & 0xff);
        data[11] = (byte) ((0x0a) & 0xff);
        data[12] = (byte) ((0x0b) & 0xff);
        data[13] = (byte) ((0x0c) & 0xff);
        data[14] = (byte) ((0x0d) & 0xff);
        data[15] = (byte) ((0x0e) & 0xff);
        data[16] = (byte) ((0x0f) & 0xff);
        data[17] = (byte) ((0x0a) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result1 = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test006() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0x07) & 0xff);
        data[ 9] = (byte) ((0x08) & 0xff);
        data[10] = (byte) ((0x09) & 0xff);
        data[11] = (byte) ((0x0a) & 0xff);
        data[12] = (byte) ((0x0b) & 0xff);
        data[13] = (byte) ((0x0c) & 0xff);
        data[14] = (byte) ((0x0d) & 0xff);
        data[15] = (byte) ((0x0e) & 0xff);
        data[16] = (byte) ((0x0f) & 0xff);
        data[17] = (byte) ((0x0a) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader3 result1 = new AccelerationMemoryDataHeader3(bluetoothGattCharacteristic);
        AccelerationMemoryDataHeader3 result2 = AccelerationMemoryDataHeader3.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
