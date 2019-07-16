package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestAccelerationMemoryIndexTest {

    @Test
    public void test001() {
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA) & 0xff);
        data[ 1] = (byte) ((0x01) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA, result.getAccelerationDataType());
        assertEquals(1, result.getRequestAccelerationMemoryIndex());
        assertEquals(0, result.getRequestPageStart());
        assertEquals(0, result.getRequestPageEnd());
    }

    @Test
    public void test002() {
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA, result.getAccelerationDataType());
        assertEquals(10, result.getRequestAccelerationMemoryIndex());
        assertEquals(511, result.getRequestPageStart());
        assertEquals(511, result.getRequestPageEnd());
    }

    @Test
    public void test003() {
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0x0f) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result1 = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RequestAccelerationMemoryIndex result2 = RequestAccelerationMemoryIndex.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getAccelerationDataType(), result2.getAccelerationDataType());
        assertEquals(result1.getRequestAccelerationMemoryIndex(), result2.getRequestAccelerationMemoryIndex());
        assertEquals(result1.getRequestPageStart(), result2.getRequestPageStart());
        assertEquals(result1.getRequestPageEnd(), result2.getRequestPageEnd());
    }
}
