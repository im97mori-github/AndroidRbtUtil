package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import java.math.BigInteger;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LatestTimeCounterTest {

    @Test
    public void test1() {
        byte[] data = new byte[8];
        data[0] = (byte) (0x01 & 0xff);
        data[1] = (byte) (0x00 & 0xff);
        data[2] = (byte) (0x00 & 0xff);
        data[3] = (byte) (0x00 & 0xff);
        data[4] = (byte) (0x00 & 0xff);
        data[5] = (byte) (0x00 & 0xff);
        data[6] = (byte) (0x00 & 0xff);
        data[7] = (byte) (0x00 & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter latestTimeCounter = new LatestTimeCounter(bluetoothGattCharacteristic);
        assertEquals(BigInteger.ONE, latestTimeCounter.getTimeCounter());
    }

    @Test
    public void test2() {
        byte[] data = new byte[8];
        data[0] = (byte) (0xff & 0xff);
        data[1] = (byte) (0xff & 0xff);
        data[2] = (byte) (0xff & 0xff);
        data[3] = (byte) (0xff & 0xff);
        data[4] = (byte) (0xff & 0xff);
        data[5] = (byte) (0xff & 0xff);
        data[6] = (byte) (0xff & 0xff);
        data[7] = (byte) (0xff & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter latestTimeCounter = new LatestTimeCounter(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), latestTimeCounter.getTimeCounter());
    }

    @Test
    public void test3() {
        byte[] data = new byte[8];
        data[0] = (byte) (0x01 & 0xff);
        data[1] = (byte) (0x02 & 0xff);
        data[2] = (byte) (0x03 & 0xff);
        data[3] = (byte) (0x04 & 0xff);
        data[4] = (byte) (0x05 & 0xff);
        data[5] = (byte) (0x06 & 0xff);
        data[6] = (byte) (0x07 & 0xff);
        data[7] = (byte) (0x08 & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestTimeCounter result2 = LatestTimeCounter.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTimeCounter(), result2.getTimeCounter());
    }

    @Test
    public void test4() {
        byte[] data = new byte[8];
        data[0] = (byte) (0x01 & 0xff);
        data[1] = (byte) (0x02 & 0xff);
        data[2] = (byte) (0x03 & 0xff);
        data[3] = (byte) (0x04 & 0xff);
        data[4] = (byte) (0x05 & 0xff);
        data[5] = (byte) (0x06 & 0xff);
        data[6] = (byte) (0x07 & 0xff);
        data[7] = (byte) (0x08 & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test5() {
        byte[] data = new byte[8];
        data[0] = (byte) (0x01 & 0xff);
        data[1] = (byte) (0x02 & 0xff);
        data[2] = (byte) (0x03 & 0xff);
        data[3] = (byte) (0x04 & 0xff);
        data[4] = (byte) (0x05 & 0xff);
        data[5] = (byte) (0x06 & 0xff);
        data[6] = (byte) (0x07 & 0xff);
        data[7] = (byte) (0x08 & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        LatestTimeCounter result2 = LatestTimeCounter.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test6() {
        byte[] data = new byte[8];
        data[0] = (byte) (0x08 & 0xff);
        BigInteger bigInteger = new BigInteger(data);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test7() {
        byte[] data = new byte[8];
        data[7] = (byte) (0x08 & 0xff);
        BigInteger bigInteger = new BigInteger(data);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        assertArrayEquals(data, result1.getBytes());
    }
}
