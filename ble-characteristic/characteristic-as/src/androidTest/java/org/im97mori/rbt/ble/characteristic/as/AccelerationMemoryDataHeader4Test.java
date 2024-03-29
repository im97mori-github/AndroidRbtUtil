package org.im97mori.rbt.ble.characteristic.as;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccelerationMemoryDataHeader4Test {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0xe0) & 0xff);
        data[ 3] = (byte) ((0xb1) & 0xff);
        data[ 4] = (byte) ((0xe0) & 0xff);
        data[ 5] = (byte) ((0xb1) & 0xff);
        data[ 6] = (byte) ((0xe0) & 0xff);
        data[ 7] = (byte) ((0xb1) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        assertEquals(1, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(-20000, result.getAccelerationOffsetXAxis());
        assertEquals(-2000.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationOffsetYAxis());
        assertEquals(-2000.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationOffsetZAxis());
        assertEquals(-2000.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0x7f) & 0xff);
        data[ 2] = (byte) ((0x20) & 0xff);
        data[ 3] = (byte) ((0x4e) & 0xff);
        data[ 4] = (byte) ((0x20) & 0xff);
        data[ 5] = (byte) ((0x4e) & 0xff);
        data[ 6] = (byte) ((0x20) & 0xff);
        data[ 7] = (byte) ((0x4e) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        assertEquals(32767, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(20000, result.getAccelerationOffsetXAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(20000, result.getAccelerationOffsetYAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(20000, result.getAccelerationOffsetZAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader4.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader4.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x20) & 0xff);
        data[ 3] = (byte) ((0x4e) & 0xff);
        data[ 4] = (byte) ((0x20) & 0xff);
        data[ 5] = (byte) ((0x4e) & 0xff);
        data[ 6] = (byte) ((0x20) & 0xff);
        data[ 7] = (byte) ((0x4e) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        assertEquals(32768, result.getTotalTransferCount());
        assertTrue(result.isTotalTransferCountDataError());
        assertEquals(20000, result.getAccelerationOffsetXAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(20000, result.getAccelerationOffsetYAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(20000, result.getAccelerationOffsetZAxis());
        assertEquals(2000.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result1 = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryDataHeader4 result2 = AccelerationMemoryDataHeader4.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
        assertEquals(result1.getAccelerationOffsetXAxis(), result2.getAccelerationOffsetXAxis());
        assertEquals(result1.getAccelerationOffsetYAxis(), result2.getAccelerationOffsetYAxis());
        assertEquals(result1.getAccelerationOffsetZAxis(), result2.getAccelerationOffsetZAxis());
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result1 = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x04) & 0xff);
        data[ 6] = (byte) ((0x05) & 0xff);
        data[ 7] = (byte) ((0x06) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xff) & 0xff);
        data[12] = (byte) ((0xff) & 0xff);
        data[13] = (byte) ((0xff) & 0xff);
        data[14] = (byte) ((0xff) & 0xff);
        data[15] = (byte) ((0xff) & 0xff);
        data[16] = (byte) ((0xff) & 0xff);
        data[17] = (byte) ((0xff) & 0xff);
        data[18] = (byte) ((0xff) & 0xff);
        data[19] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader4 result1 = new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        AccelerationMemoryDataHeader4 result2 = AccelerationMemoryDataHeader4.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
