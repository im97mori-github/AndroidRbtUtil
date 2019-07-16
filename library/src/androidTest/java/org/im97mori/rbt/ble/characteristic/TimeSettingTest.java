package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.math.BigInteger;

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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result = new TimeSetting(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(1, new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getTimeSetting());
    }

    @Test
    public void test003() {
        byte[] data = new byte[8];
        data[0] = (byte) ((0xff) & 0xff);
        data[1] = (byte) ((0xff) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);
        data[5] = (byte) ((0xff) & 0xff);
        data[6] = (byte) ((0xff) & 0xff);
        data[7] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        TimeSetting result1 = new TimeSetting(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        TimeSetting result2 = TimeSetting.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTimeSetting(), result2.getTimeSetting());
    }
}
