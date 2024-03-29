package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import java.math.BigInteger;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MemoryStatusTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_WAITING) & 0xff);
        data[ 1] = (byte) ((0x01) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x00) & 0xff);
        data[ 9] = (byte) ((0x01) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result = new MemoryStatus(bluetoothGattCharacteristic);
        assertEquals(MemoryStatus.STATUS_WAITING, result.getStatus());
        assertEquals(new BigInteger("1"), result.getTimeCounter());
        assertEquals(1, result.getMemoryStorageInterval());
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_READY_TO_TRANSFER) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result = new MemoryStatus(bluetoothGattCharacteristic);
        assertEquals(MemoryStatus.STATUS_READY_TO_TRANSFER, result.getStatus());
        assertEquals(new BigInteger("0FFFFFFFFFFFFFFFF", 16), result.getTimeCounter());
        assertEquals(3600, result.getMemoryStorageInterval());
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_TRANSFERRING) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result = new MemoryStatus(bluetoothGattCharacteristic);
        assertEquals(MemoryStatus.STATUS_TRANSFERRING, result.getStatus());
        assertEquals(new BigInteger("0FFFFFFFFFFFFFF00", 16), result.getTimeCounter());
        assertEquals(3600, result.getMemoryStorageInterval());
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);
        data[ 8] = (byte) ((0x00) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result = new MemoryStatus(bluetoothGattCharacteristic);
        assertEquals(MemoryStatus.STATUS_ERROR, result.getStatus());
        assertEquals(new BigInteger("000FFFFFFFFFFFFff", 16), result.getTimeCounter());
        assertEquals(3600, result.getMemoryStorageInterval());
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x1f) & 0xff);
        data[ 3] = (byte) ((0x2f) & 0xff);
        data[ 4] = (byte) ((0x3f) & 0xff);
        data[ 5] = (byte) ((0x4f) & 0xff);
        data[ 6] = (byte) ((0x5f) & 0xff);
        data[ 7] = (byte) ((0x6f) & 0xff);
        data[ 8] = (byte) ((0x7f) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result1 = new MemoryStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryStatus result2 = MemoryStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getStatus(), result2.getStatus());
        assertEquals(result1.getTimeCounter(), result2.getTimeCounter());
        assertEquals(result1.getMemoryStorageInterval(), result2.getMemoryStorageInterval());
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x1f) & 0xff);
        data[ 3] = (byte) ((0x2f) & 0xff);
        data[ 4] = (byte) ((0x3f) & 0xff);
        data[ 5] = (byte) ((0x4f) & 0xff);
        data[ 6] = (byte) ((0x5f) & 0xff);
        data[ 7] = (byte) ((0x6f) & 0xff);
        data[ 8] = (byte) ((0x7f) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result1 = new MemoryStatus(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test007() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x1f) & 0xff);
        data[ 3] = (byte) ((0x2f) & 0xff);
        data[ 4] = (byte) ((0x3f) & 0xff);
        data[ 5] = (byte) ((0x4f) & 0xff);
        data[ 6] = (byte) ((0x5f) & 0xff);
        data[ 7] = (byte) ((0x6f) & 0xff);
        data[ 8] = (byte) ((0x7f) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result1 = new MemoryStatus(bluetoothGattCharacteristic);
        MemoryStatus result2 = MemoryStatus.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test008() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x7f) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result1 = new MemoryStatus(bluetoothGattCharacteristic);
        MemoryStatus result2 = MemoryStatus.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test009() {
        //@formatter:off
        byte[] data = new byte[11];
        data[ 0] = (byte) ((MemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 9] = (byte) ((0x10) & 0xff);
        data[10] = (byte) ((0x0e) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStatus result1 = new MemoryStatus(bluetoothGattCharacteristic);
        MemoryStatus result2 = MemoryStatus.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
