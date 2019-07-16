package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryResetTest {

    @Test
    public void test001() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_SENSING_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset memoryReset = new MemoryReset(bluetoothGattCharacteristic);
        assertEquals(MemoryReset.MEMORY_RESET_SENSING_DATA_AREA, memoryReset.getMemoryReset());
    }

    @Test
    public void test002() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset memoryReset = new MemoryReset(bluetoothGattCharacteristic);
        assertEquals(MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA, memoryReset.getMemoryReset());
    }

    @Test
    public void test003() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryReset result1 = new MemoryReset(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryReset result2 = MemoryReset.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryReset(), result2.getMemoryReset());
    }
}
