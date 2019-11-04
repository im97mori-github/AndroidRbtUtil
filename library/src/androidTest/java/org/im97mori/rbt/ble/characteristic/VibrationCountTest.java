package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import java.math.BigInteger;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VibrationCountTest {

    @Test
    public void test001() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result = new VibrationCount(bluetoothGattCharacteristic);
        assertEquals(BigInteger.ZERO, result.getEarthquakeCount());
        assertEquals(BigInteger.ZERO, result.getVibrationCount());
    }

    @Test
    public void test002() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result = new VibrationCount(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getEarthquakeCount());
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getVibrationCount());
    }

    @Test
    public void test003() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x02) & 0xff);
        data[ 2] = (byte) ((0x03) & 0xff);
        data[ 3] = (byte) ((0x04) & 0xff);
        data[ 4] = (byte) ((0x05) & 0xff);
        data[ 5] = (byte) ((0x06) & 0xff);
        data[ 6] = (byte) ((0x07) & 0xff);
        data[ 7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        VibrationCount result2 = VibrationCount.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getEarthquakeCount(), result2.getEarthquakeCount());
        assertEquals(result1.getVibrationCount(), result2.getVibrationCount());
    }

    @Test
    public void test004() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x02) & 0xff);
        data[ 2] = (byte) ((0x03) & 0xff);
        data[ 3] = (byte) ((0x04) & 0xff);
        data[ 4] = (byte) ((0x05) & 0xff);
        data[ 5] = (byte) ((0x06) & 0xff);
        data[ 6] = (byte) ((0x07) & 0xff);
        data[ 7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x02) & 0xff);
        data[ 2] = (byte) ((0x03) & 0xff);
        data[ 3] = (byte) ((0x04) & 0xff);
        data[ 4] = (byte) ((0x05) & 0xff);
        data[ 5] = (byte) ((0x06) & 0xff);
        data[ 6] = (byte) ((0x07) & 0xff);
        data[ 7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        VibrationCount result2 = VibrationCount.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test006() {
        byte[] data = new byte[8];
        data[ 3] = (byte) ((0x04) & 0xff);
        data[ 7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        VibrationCount result2 = VibrationCount.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
    @Test
    public void test007() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 4] = (byte) ((0x05) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        VibrationCount result2 = VibrationCount.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}
