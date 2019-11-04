package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MemoryResetTest {

    @Test
    public void test001() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_SENSING_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset memoryReset = new MemoryReset(bluetoothGattCharacteristic);
        assertEquals(MemoryReset.MEMORY_RESET_SENSING_DATA_AREA, memoryReset.getMemoryReset());
    }

    @Test
    public void test002() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset memoryReset = new MemoryReset(bluetoothGattCharacteristic);
        assertEquals(MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA, memoryReset.getMemoryReset());
    }

    @Test
    public void test003() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset result1 = new MemoryReset(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryReset result2 = MemoryReset.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryReset(), result2.getMemoryReset());
    }

    @Test
    public void test004() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset result1 = new MemoryReset(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        int memoryReset = MemoryReset.MEMORY_RESET_SENSING_DATA_AREA;

        MemoryReset result1 = new MemoryReset(memoryReset);
        assertEquals(memoryReset, result1.getMemoryReset());
    }


    @Test
    public void test006() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset result1 = new MemoryReset(bluetoothGattCharacteristic);
        MemoryReset result2 = MemoryReset.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
