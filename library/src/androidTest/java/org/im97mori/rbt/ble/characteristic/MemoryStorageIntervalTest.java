package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MemoryStorageIntervalTest {

    @Test
    public void test1() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) (0x01 & 0xff);
        data[ 1] = (byte) (0x00 & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval memoryStorageInterval = new MemoryStorageInterval(bluetoothGattCharacteristic);
        assertEquals(1, memoryStorageInterval.getMemoryStorageInterval());
        assertEquals(1d, memoryStorageInterval.getMemoryStorageIntervalSec(), 0);
    }

    @Test
    public void test2() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) (0x10 & 0xff);
        data[ 1] = (byte) (0x0e & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval memoryStorageInterval = new MemoryStorageInterval(bluetoothGattCharacteristic);
        assertEquals(3600, memoryStorageInterval.getMemoryStorageInterval());
        assertEquals(3600d, memoryStorageInterval.getMemoryStorageIntervalSec(), 0);
    }

    @Test
    public void test3() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) (0x10 & 0xff);
        data[ 1] = (byte) (0x0e & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval result1 = new MemoryStorageInterval(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryStorageInterval result2 = MemoryStorageInterval.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryStorageInterval(), result2.getMemoryStorageInterval());
    }

    @Test
    public void test4() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) (0x10 & 0xff);
        data[ 1] = (byte) (0x0e & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval result1 = new MemoryStorageInterval(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test5() {
        int memoryStorageInterval = 0x01;

        MemoryStorageInterval result1 = new MemoryStorageInterval(memoryStorageInterval);
        assertEquals(memoryStorageInterval, result1.getMemoryStorageInterval());
    }

    @Test
    public void test6() {
        //@formatter:off
        byte[] data = new byte[2];
        data[ 0] = (byte) (0x10 & 0xff);
        data[ 1] = (byte) (0x0e & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval result1 = new MemoryStorageInterval(bluetoothGattCharacteristic);
        MemoryStorageInterval result2 = MemoryStorageInterval.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
