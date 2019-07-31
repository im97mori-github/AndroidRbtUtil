package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AccelerationMemoryStatusTest {

    @Test
    public void test001() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_WATING) & 0xff);
        data[ 1] = (byte) ((0x01) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationMemoryStatus.STATUS_WATING, result.getStatus());
        assertEquals(1, result.getTotalTransferCount());
    }

    @Test
    public void test002() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_READY_TO_TRANSFER) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0x7f) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationMemoryStatus.STATUS_READY_TO_TRANSFER, result.getStatus());
        assertEquals(32767, result.getTotalTransferCount());
    }

    @Test
    public void test003() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_TRANSFERRING) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0x7f) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationMemoryStatus.STATUS_TRANSFERRING, result.getStatus());
        assertEquals(32767, result.getTotalTransferCount());
    }

    @Test
    public void test004() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0x7f) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationMemoryStatus.STATUS_ERROR, result.getStatus());
        assertEquals(32767, result.getTotalTransferCount());
    }

    @Test
    public void test005() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0x7f) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result1 = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryStatus result2 = AccelerationMemoryStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getStatus(), result2.getStatus());
        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
    }

    @Test
    public void test006() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationMemoryStatus.STATUS_ERROR) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0x7f) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryStatus result1 = new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }
}
