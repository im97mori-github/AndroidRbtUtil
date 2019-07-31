package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.le.AdvertiseSettings;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AdvertiseSettingTest {

    @Test
    public void test001() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0xa0) & 0xff);
        data[1] = (byte) ((0x00) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SENSOR_DATA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting advertiseSetting = new AdvertiseSetting(bluetoothGattCharacteristic);
        assertEquals(160, advertiseSetting.getAdvertisingInterval());
        assertEquals(100d, advertiseSetting.getAdvertisingIntervalMillis(), 0);
        assertEquals(AdvertiseSetting.ADVERTISING_MODE_SENSOR_DATA, advertiseSetting.getAdvertisingMode());
    }

    @Test
    public void test002() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_CALCULATION_DATA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting advertiseSetting = new AdvertiseSetting(bluetoothGattCharacteristic);
        assertEquals(16384, advertiseSetting.getAdvertisingInterval());
        assertEquals(10240d, advertiseSetting.getAdvertisingIntervalMillis(), 0);
        assertEquals(AdvertiseSetting.ADVERTISING_MODE_CALCULATION_DATA, advertiseSetting.getAdvertisingMode());
    }

    @Test
    public void test003() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SENSOR_DATA_AND_CALCULATION_DATA) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting advertiseSetting = new AdvertiseSetting(bluetoothGattCharacteristic);
        assertEquals(16384, advertiseSetting.getAdvertisingInterval());
        assertEquals(10240d, advertiseSetting.getAdvertisingIntervalMillis(), 0);
        assertEquals(AdvertiseSetting.ADVERTISING_MODE_SENSOR_DATA_AND_CALCULATION_DATA, advertiseSetting.getAdvertisingMode());
    }

    @Test
    public void test004() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SENSOR_FLAG_AND_CALCULATION_FLAG) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting advertiseSetting = new AdvertiseSetting(bluetoothGattCharacteristic);
        assertEquals(16384, advertiseSetting.getAdvertisingInterval());
        assertEquals(10240d, advertiseSetting.getAdvertisingIntervalMillis(), 0);
        assertEquals(AdvertiseSetting.ADVERTISING_MODE_SENSOR_FLAG_AND_CALCULATION_FLAG, advertiseSetting.getAdvertisingMode());
    }

    @Test
    public void test005() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SERIAL_NUMBER) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting advertiseSetting = new AdvertiseSetting(bluetoothGattCharacteristic);
        assertEquals(16384, advertiseSetting.getAdvertisingInterval());
        assertEquals(10240d, advertiseSetting.getAdvertisingIntervalMillis(), 0);
        assertEquals(AdvertiseSetting.ADVERTISING_MODE_SERIAL_NUMBER, advertiseSetting.getAdvertisingMode());
    }

    @Test
    public void test006() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SERIAL_NUMBER) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting result1 = new AdvertiseSetting(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AdvertiseSetting result2 = AdvertiseSetting.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getAdvertisingInterval(), result2.getAdvertisingInterval());
        assertEquals(result1.getAdvertisingMode(), result2.getAdvertisingMode());
    }

    @Test
    public void test007() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SERIAL_NUMBER) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting result1 = new AdvertiseSetting(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test008() {
        int advertisingInterval = 0x00a0;
        int advertisingMode = AdvertiseSetting.ADVERTISING_MODE_CALCULATION_DATA;

        AdvertiseSetting result1 = new AdvertiseSetting(advertisingInterval, advertisingMode);
        assertEquals(advertisingInterval, result1.getAdvertisingInterval());
        assertEquals(advertisingMode, result1.getAdvertisingMode());
    }

    @Test
    public void test009() {
        byte[] data = new byte[3];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x40) & 0xff);
        data[2] = (byte) ((AdvertiseSetting.ADVERTISING_MODE_SERIAL_NUMBER) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AdvertiseSetting result1 = new AdvertiseSetting(bluetoothGattCharacteristic);
        AdvertiseSetting result2 = AdvertiseSetting.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
