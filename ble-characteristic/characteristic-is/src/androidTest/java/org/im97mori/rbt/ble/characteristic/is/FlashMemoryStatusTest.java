package org.im97mori.rbt.ble.characteristic.is;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FlashMemoryStatusTest {

    @Test
    public void test1() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_NONE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus flashMemoryStatus = new FlashMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(FlashMemoryStatus.FLASH_MEMORY_STATUS_NONE, flashMemoryStatus.getFlashMemoryStatus());
    }

    @Test
    public void test2() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus flashMemoryStatus = new FlashMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, flashMemoryStatus.getFlashMemoryStatus());
    }

    @Test
    public void test3() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITE_SUCCESS;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus flashMemoryStatus = new FlashMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITE_SUCCESS, flashMemoryStatus.getFlashMemoryStatus());
    }

    @Test
    public void test4() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITE_FAILURE;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus flashMemoryStatus = new FlashMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITE_FAILURE, flashMemoryStatus.getFlashMemoryStatus());
    }

    @Test
    public void test5() {
        byte[] data = new byte[1];
        data[0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus flashMemoryStatus = new FlashMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, flashMemoryStatus.getFlashMemoryStatus());
    }

    @Test
    public void test6() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus result1 = new FlashMemoryStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        FlashMemoryStatus result2 = FlashMemoryStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getFlashMemoryStatus(), result2.getFlashMemoryStatus());
    }

    @Test
    public void test7() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus result1 = new FlashMemoryStatus(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test8() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING;
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        FlashMemoryStatus result1 = new FlashMemoryStatus(bluetoothGattCharacteristic);
        FlashMemoryStatus result2 = FlashMemoryStatus.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
