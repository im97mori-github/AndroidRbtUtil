package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryStorageIntervalTest {

    @Test
    public void test1() {
        byte[] data = new byte[2];
        data[0] = (byte) (0x01 & 0xff);
        data[1] = (byte) (0x00 & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval memoryStorageInterval = new MemoryStorageInterval(bluetoothGattCharacteristic);
        assertEquals(1, memoryStorageInterval.getMemoryStorageInterval());
        assertEquals(1d, memoryStorageInterval.getMemoryStorageIntervalSec(), 0);
    }

    @Test
    public void test2() {
        byte[] data = new byte[2];
        data[0] = (byte) (0x10 & 0xff);
        data[1] = (byte) (0x0e & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval memoryStorageInterval = new MemoryStorageInterval(bluetoothGattCharacteristic);
        assertEquals(3600, memoryStorageInterval.getMemoryStorageInterval());
        assertEquals(3600d, memoryStorageInterval.getMemoryStorageIntervalSec(), 0);
    }

    @Test
    public void test7() {
        byte[] data = new byte[2];
        data[0] = (byte) (0x10 & 0xff);
        data[1] = (byte) (0x0e & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryStorageInterval result1 = new MemoryStorageInterval(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryStorageInterval result2 = MemoryStorageInterval.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryStorageInterval(), result2.getMemoryStorageInterval());
    }
}
