package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccelerationMemorydataData8Test {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0xe0) & 0xff);
        data[ 3] = (byte) ((0xb1) & 0xff);
        data[ 4] = (byte) ((0xe0) & 0xff);
        data[ 5] = (byte) ((0xb1) & 0xff);
        data[ 6] = (byte) ((0xe0) & 0xff);
        data[ 7] = (byte) ((0xb1) & 0xff);
        data[ 8] = (byte) ((0xe0) & 0xff);
        data[ 9] = (byte) ((0xb1) & 0xff);
        data[10] = (byte) ((0xe0) & 0xff);
        data[11] = (byte) ((0xb1) & 0xff);
        data[12] = (byte) ((0xe0) & 0xff);
        data[13] = (byte) ((0xb1) & 0xff);
        data[14] = (byte) ((0xe0) & 0xff);
        data[15] = (byte) ((0xb1) & 0xff);
        data[16] = (byte) ((0xe0) & 0xff);
        data[17] = (byte) ((0xb1) & 0xff);
        data[18] = (byte) ((0xe0) & 0xff);
        data[19] = (byte) ((0xb1) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData8 result = new AccelerationMemoryData8(bluetoothGattCharacteristic);
        assertEquals(1, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(-20000, result.getAccelerationXAxis1());
        assertEquals(-2000.0d, result.getAccelerationXAxis1Gal(), 0);
        assertEquals(-20000, result.getAccelerationYAxis1());
        assertEquals(-2000.0d, result.getAccelerationYAxis1Gal(), 0);
        assertEquals(-20000, result.getAccelerationZAxis1());
        assertEquals(-2000.0d, result.getAccelerationZAxis1Gal(), 0);
        assertEquals(-20000, result.getAccelerationXAxis2());
        assertEquals(-2000.0d, result.getAccelerationXAxis2Gal(), 0);
        assertEquals(-20000, result.getAccelerationYAxis2());
        assertEquals(-2000.0d, result.getAccelerationYAxis2Gal(), 0);
        assertEquals(-20000, result.getAccelerationZAxis2());
        assertEquals(-2000.0d, result.getAccelerationZAxis2Gal(), 0);
        assertEquals(-20000, result.getAccelerationXAxis3());
        assertEquals(-2000.0d, result.getAccelerationXAxis3Gal(), 0);
        assertEquals(-20000, result.getAccelerationYAxis3());
        assertEquals(-2000.0d, result.getAccelerationYAxis3Gal(), 0);
        assertEquals(-20000, result.getAccelerationZAxis3());
        assertEquals(-2000.0d, result.getAccelerationZAxis3Gal(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0x7f) & 0xff);
        data[ 2] = (byte) ((0x20) & 0xff);
        data[ 3] = (byte) ((0x4e) & 0xff);
        data[ 4] = (byte) ((0x20) & 0xff);
        data[ 5] = (byte) ((0x4e) & 0xff);
        data[ 6] = (byte) ((0x20) & 0xff);
        data[ 7] = (byte) ((0x4e) & 0xff);
        data[ 8] = (byte) ((0x20) & 0xff);
        data[ 9] = (byte) ((0x4e) & 0xff);
        data[10] = (byte) ((0x20) & 0xff);
        data[11] = (byte) ((0x4e) & 0xff);
        data[12] = (byte) ((0x20) & 0xff);
        data[13] = (byte) ((0x4e) & 0xff);
        data[14] = (byte) ((0x20) & 0xff);
        data[15] = (byte) ((0x4e) & 0xff);
        data[16] = (byte) ((0x20) & 0xff);
        data[17] = (byte) ((0x4e) & 0xff);
        data[18] = (byte) ((0x20) & 0xff);
        data[19] = (byte) ((0x4e) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData8 result = new AccelerationMemoryData8(bluetoothGattCharacteristic);
        assertEquals(32767, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(20000, result.getAccelerationXAxis1());
        assertEquals(2000.0d, result.getAccelerationXAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis1());
        assertEquals(2000.0d, result.getAccelerationYAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis1());
        assertEquals(2000.0d, result.getAccelerationZAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationXAxis2());
        assertEquals(2000.0d, result.getAccelerationXAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis2());
        assertEquals(2000.0d, result.getAccelerationYAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis2());
        assertEquals(2000.0d, result.getAccelerationZAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationXAxis3());
        assertEquals(2000.0d, result.getAccelerationXAxis3Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis3());
        assertEquals(2000.0d, result.getAccelerationYAxis3Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis3());
        assertEquals(2000.0d, result.getAccelerationZAxis3Gal(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[20];
        data[0] = (byte) ((AccelerationMemoryData8.DATA_ERROR_BIT) & 0xff);
        data[1] = (byte) ((AccelerationMemoryData8.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x20) & 0xff);
        data[ 3] = (byte) ((0x4e) & 0xff);
        data[ 4] = (byte) ((0x20) & 0xff);
        data[ 5] = (byte) ((0x4e) & 0xff);
        data[ 6] = (byte) ((0x20) & 0xff);
        data[ 7] = (byte) ((0x4e) & 0xff);
        data[ 8] = (byte) ((0x20) & 0xff);
        data[ 9] = (byte) ((0x4e) & 0xff);
        data[10] = (byte) ((0x20) & 0xff);
        data[11] = (byte) ((0x4e) & 0xff);
        data[12] = (byte) ((0x20) & 0xff);
        data[13] = (byte) ((0x4e) & 0xff);
        data[14] = (byte) ((0x20) & 0xff);
        data[15] = (byte) ((0x4e) & 0xff);
        data[16] = (byte) ((0x20) & 0xff);
        data[17] = (byte) ((0x4e) & 0xff);
        data[18] = (byte) ((0x20) & 0xff);
        data[19] = (byte) ((0x4e) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData8 result = new AccelerationMemoryData8(bluetoothGattCharacteristic);
        assertEquals(32768, result.getTotalTransferCount());
        assertTrue(result.isTotalTransferCountDataError());
        assertEquals(20000, result.getAccelerationXAxis1());
        assertEquals(2000.0d, result.getAccelerationXAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis1());
        assertEquals(2000.0d, result.getAccelerationYAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis1());
        assertEquals(2000.0d, result.getAccelerationZAxis1Gal(), 0);
        assertEquals(20000, result.getAccelerationXAxis2());
        assertEquals(2000.0d, result.getAccelerationXAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis2());
        assertEquals(2000.0d, result.getAccelerationYAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis2());
        assertEquals(2000.0d, result.getAccelerationZAxis2Gal(), 0);
        assertEquals(20000, result.getAccelerationXAxis3());
        assertEquals(2000.0d, result.getAccelerationXAxis3Gal(), 0);
        assertEquals(20000, result.getAccelerationYAxis3());
        assertEquals(2000.0d, result.getAccelerationYAxis3Gal(), 0);
        assertEquals(20000, result.getAccelerationZAxis3());
        assertEquals(2000.0d, result.getAccelerationZAxis3Gal(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[20];
        data[0] = (byte) ((AccelerationMemoryData8.DATA_ERROR_BIT) & 0xff);
        data[1] = (byte) ((AccelerationMemoryData8.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x20) & 0xff);
        data[ 3] = (byte) ((0x4e) & 0xff);
        data[ 4] = (byte) ((0x20) & 0xff);
        data[ 5] = (byte) ((0x4e) & 0xff);
        data[ 6] = (byte) ((0x20) & 0xff);
        data[ 7] = (byte) ((0x4e) & 0xff);
        data[ 8] = (byte) ((0x20) & 0xff);
        data[ 9] = (byte) ((0x4e) & 0xff);
        data[10] = (byte) ((0x20) & 0xff);
        data[11] = (byte) ((0x4e) & 0xff);
        data[12] = (byte) ((0x20) & 0xff);
        data[13] = (byte) ((0x4e) & 0xff);
        data[14] = (byte) ((0x20) & 0xff);
        data[15] = (byte) ((0x4e) & 0xff);
        data[16] = (byte) ((0x20) & 0xff);
        data[17] = (byte) ((0x4e) & 0xff);
        data[18] = (byte) ((0x20) & 0xff);
        data[19] = (byte) ((0x4e) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData8 result1 = new AccelerationMemoryData8(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryData8 result2 = AccelerationMemoryData8.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
        assertEquals(result1.getAccelerationXAxis1(), result2.getAccelerationXAxis1());
        assertEquals(result1.getAccelerationYAxis1(), result2.getAccelerationYAxis1());
        assertEquals(result1.getAccelerationZAxis1(), result2.getAccelerationZAxis1());
        assertEquals(result1.getAccelerationXAxis2(), result2.getAccelerationXAxis2());
        assertEquals(result1.getAccelerationYAxis2(), result2.getAccelerationYAxis2());
        assertEquals(result1.getAccelerationZAxis2(), result2.getAccelerationZAxis2());
        assertEquals(result1.getAccelerationXAxis3(), result2.getAccelerationXAxis3());
        assertEquals(result1.getAccelerationYAxis3(), result2.getAccelerationYAxis3());
        assertEquals(result1.getAccelerationZAxis3(), result2.getAccelerationZAxis3());
    }
}
