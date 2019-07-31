package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestMemoryIndexTest {

    @Test
    public void test001() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_SENSING_DATA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result = new RequestMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(0, result.getMemoryIndexStart());
        assertEquals(0, result.getMemoryIndexEnd());
        assertEquals(RequestMemoryIndex.DATA_TYPE_SENSING_DATA, result.getDataType());
    }

    @Test
    public void test002() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_CALCULATION_DATA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result = new RequestMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndexStart());
        assertEquals(2147483647, result.getMemoryIndexEnd());
        assertEquals(RequestMemoryIndex.DATA_TYPE_CALCULATION_DATA, result.getDataType());
    }

    @Test
    public void test003() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_SENSING_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result = new RequestMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(2147483392, result.getMemoryIndexStart());
        assertEquals(2147483647, result.getMemoryIndexEnd());
        assertEquals(RequestMemoryIndex.DATA_TYPE_SENSING_FLAG, result.getDataType());
    }

    @Test
    public void test004() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result = new RequestMemoryIndex(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndexStart());
        assertEquals(2147483392, result.getMemoryIndexEnd());
        assertEquals(RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG, result.getDataType());
    }

    @Test
    public void test005() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x2f) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result1 = new RequestMemoryIndex(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RequestMemoryIndex result2 = RequestMemoryIndex.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryIndexStart(), result2.getMemoryIndexStart());
        assertEquals(result1.getMemoryIndexEnd(), result2.getMemoryIndexEnd());
        assertEquals(result1.getDataType(), result2.getDataType());
    }

    @Test
    public void test006() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x2f) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result1 = new RequestMemoryIndex(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test007() {
        int memoryIndexStart = 0x00000001;
        int memoryIndexEnd = 0x7fffffff;
        int dataType = RequestMemoryIndex.DATA_TYPE_CALCULATION_DATA;

        RequestMemoryIndex result1 = new RequestMemoryIndex(memoryIndexStart, memoryIndexEnd, dataType);

        assertEquals(memoryIndexStart, result1.getMemoryIndexStart());
        assertEquals(memoryIndexEnd, result1.getMemoryIndexEnd());
        assertEquals(dataType, result1.getDataType());
    }

    @Test
    public void test008() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x1f) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x7f) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x2f) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0x7f) & 0xff);
        data[ 8] = (byte) ((RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RequestMemoryIndex result1 = new RequestMemoryIndex(bluetoothGattCharacteristic);
        RequestMemoryIndex result2 = RequestMemoryIndex.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
