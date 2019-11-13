package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MemoryIndexInformationTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryIndexInformation result = new MemoryIndexInformation(bluetoothGattCharacteristic);
        assertEquals(0, result.getMemoryIndexLatest());
        assertEquals(0, result.getMemoryIndexLast());
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryIndexInformation result = new MemoryIndexInformation(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndexLatest());
        assertEquals(2147483647, result.getMemoryIndexLast());
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x2f) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryIndexInformation result1 = new MemoryIndexInformation(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryIndexInformation result2 = MemoryIndexInformation.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryIndexLatest(), result2.getMemoryIndexLatest());
        assertEquals(result1.getMemoryIndexLast(), result2.getMemoryIndexLast());
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x2f) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryIndexInformation result1 = new MemoryIndexInformation(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x2f) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryIndexInformation result1 = new MemoryIndexInformation(bluetoothGattCharacteristic);
        MemoryIndexInformation result2 = MemoryIndexInformation.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
