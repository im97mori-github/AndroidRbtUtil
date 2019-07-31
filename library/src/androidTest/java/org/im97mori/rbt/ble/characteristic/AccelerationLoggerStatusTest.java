package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AccelerationLoggerStatusTest {

    @Test
    public void test001() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationLoggerStatus.LOGGER_STATUS_WAITING) & 0xff);
        data[ 1] = (byte) ((0x01) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationLoggerStatus accelerationLoggerStatus = new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationLoggerStatus.LOGGER_STATUS_WAITING, accelerationLoggerStatus.getLoggerStatus());
        assertEquals(1, accelerationLoggerStatus.getRunningPage());
    }

    @Test
    public void test002() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationLoggerStatus.LOGGER_STATUS_RUNNING) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x28) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationLoggerStatus accelerationLoggerStatus = new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        assertEquals(AccelerationLoggerStatus.LOGGER_STATUS_RUNNING, accelerationLoggerStatus.getLoggerStatus());
        assertEquals(10240, accelerationLoggerStatus.getRunningPage());
    }

    @Test
    public void test003() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationLoggerStatus.LOGGER_STATUS_RUNNING) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x28) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationLoggerStatus result1 = new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationLoggerStatus result2 = AccelerationLoggerStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getLoggerStatus(), result2.getLoggerStatus());
        assertEquals(result1.getRunningPage(), result2.getRunningPage());
    }

    @Test
    public void test004() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationLoggerStatus.LOGGER_STATUS_RUNNING) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x28) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationLoggerStatus result1 = new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        byte[] data = new byte[3];
        data[ 0] = (byte) ((AccelerationLoggerStatus.LOGGER_STATUS_RUNNING) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x28) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationLoggerStatus result1 = new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        AccelerationLoggerStatus result2 = AccelerationLoggerStatus.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}
