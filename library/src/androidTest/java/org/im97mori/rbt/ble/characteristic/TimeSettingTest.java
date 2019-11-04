package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import java.math.BigInteger;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TimeSettingTest {

    @Test
    public void test001() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0x01) & 0xff);
        data[1] = (byte) ((0x00) & 0xff);
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);
        data[5] = (byte) ((0x00) & 0xff);
        data[6] = (byte) ((0x00) & 0xff);
        data[7] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result = new TimeSetting(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(1, new byte[]{1}), result.getTimeSetting());
    }

    @Test
    public void test002() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0xff) & 0xff);
        data[1] = (byte) ((0xff) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);
        data[5] = (byte) ((0xff) & 0xff);
        data[6] = (byte) ((0xff) & 0xff);
        data[7] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result = new TimeSetting(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(1, new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getTimeSetting());
    }

    @Test
    public void test003() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0x01) & 0xff);
        data[1] = (byte) ((0x02) & 0xff);
        data[2] = (byte) ((0x03) & 0xff);
        data[3] = (byte) ((0x04) & 0xff);
        data[4] = (byte) ((0x05) & 0xff);
        data[5] = (byte) ((0x06) & 0xff);
        data[6] = (byte) ((0x07) & 0xff);
        data[7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result1 = new TimeSetting(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        TimeSetting result2 = TimeSetting.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTimeSetting(), result2.getTimeSetting());
    }

    @Test
    public void test004() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0x01) & 0xff);
        data[1] = (byte) ((0x02) & 0xff);
        data[2] = (byte) ((0x03) & 0xff);
        data[3] = (byte) ((0x04) & 0xff);
        data[4] = (byte) ((0x05) & 0xff);
        data[5] = (byte) ((0x06) & 0xff);
        data[6] = (byte) ((0x07) & 0xff);
        data[7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result1 = new TimeSetting(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        BigInteger timeSetting = BigInteger.valueOf(1);

        TimeSetting result1 = new TimeSetting(timeSetting);
        assertEquals(timeSetting, result1.getTimeSetting());
    }

    @Test
    public void test006() {
        byte[] data = new byte[8];
        data[0] = 1;
        BigInteger timeSetting = new BigInteger(new byte[]{1});

        TimeSetting result1 = new TimeSetting(timeSetting);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test007() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0x01) & 0xff);
        data[1] = (byte) ((0x02) & 0xff);
        data[2] = (byte) ((0x03) & 0xff);
        data[3] = (byte) ((0x04) & 0xff);
        data[4] = (byte) ((0x05) & 0xff);
        data[5] = (byte) ((0x06) & 0xff);
        data[6] = (byte) ((0x07) & 0xff);
        data[7] = (byte) ((0x08) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result1 = new TimeSetting(bluetoothGattCharacteristic);
        TimeSetting result2 = TimeSetting.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

    @Test
    public void test008() {
        byte[] data = new byte[8];
        data[0] = (byte) 0xff;
        BigInteger bigInteger = new BigInteger(data);

        for (int i = 0; i < data.length / 2; i++) {
            byte tmp = data[i];
            data[i] = data[data.length - i - 1];
            data[data.length - i - 1] = tmp;
        }

        TimeSetting result1 = new TimeSetting(bigInteger);
        assertArrayEquals(data, result1.getBytes());
    }

    @Test
    public void test009() {
        byte[] data = new byte[8];
        data[0] = (byte) 0xff;
        data[1] = (byte) 0xfe;
        data[2] = (byte) 0xfd;
        data[3] = (byte) 0xfc;
        data[4] = (byte) 0xfb;
        data[5] = (byte) 0xfa;
        data[6] = (byte) 0xf0;
        data[7] = (byte) 0xef;
        BigInteger bigInteger = new BigInteger(data);

        for (int i = 0; i < data.length / 2; i++) {
            byte tmp = data[i];
            data[i] = data[data.length - i - 1];
            data[data.length - i - 1] = tmp;
        }

        TimeSetting result1 = new TimeSetting(bigInteger);
        assertArrayEquals(data, result1.getBytes());
    }
}
