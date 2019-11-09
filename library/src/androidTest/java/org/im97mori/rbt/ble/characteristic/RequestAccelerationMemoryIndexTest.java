package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestAccelerationMemoryIndexTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA) & 0xff);
        data[ 1] = (byte) ((0x01) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA, result.getAccelerationDataType());
        assertEquals(1, result.getRequestAccelerationMemoryIndex());
        assertEquals(0, result.getRequestPageStart());
        assertEquals(0, result.getRequestPageEnd());
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA, result.getAccelerationDataType());
        assertEquals(10, result.getRequestAccelerationMemoryIndex());
        assertEquals(511, result.getRequestPageStart());
        assertEquals(511, result.getRequestPageEnd());
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0x0f) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
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

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0x0f) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result1 = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        int accelerationDataType = RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA;
        int requestAcceelrationMemoryIndex = 0x0a;
        int requestPageStart = 0x0000;
        int requestPageEnd = 0x01ff;

        RequestAccelerationMemoryIndex resutl1 = new RequestAccelerationMemoryIndex(accelerationDataType, requestAcceelrationMemoryIndex, requestPageStart, requestPageEnd);
        assertEquals(accelerationDataType, resutl1.getAccelerationDataType());
        assertEquals(requestAcceelrationMemoryIndex, resutl1.getRequestAccelerationMemoryIndex());
        assertEquals(requestPageStart, resutl1.getRequestPageStart());
        assertEquals(requestPageEnd, resutl1.getRequestPageEnd());
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[6];
        data[ 0] = (byte) ((RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA) & 0xff);
        data[ 1] = (byte) ((0x0a) & 0xff);
        data[ 2] = (byte) ((0x0f) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0x01) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestAccelerationMemoryIndex result1 = new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        RequestAccelerationMemoryIndex result2 = RequestAccelerationMemoryIndex.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
