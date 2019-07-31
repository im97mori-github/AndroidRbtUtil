package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ModeChangeTest {

    @Test
    public void test001() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_NORMAL_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_NORMAL_MODE, result.getModeChange());
    }

    @Test
    public void test002() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE, result.getModeChange());
    }

    @Test
    public void test003() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result1 = new ModeChange(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ModeChange result2 = ModeChange.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getModeChange(), result2.getModeChange());
    }

    @Test
    public void test004() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result1 = new ModeChange(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        int modeChange = ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE;

        ModeChange result1 = new ModeChange(modeChange);
        assertEquals(modeChange, result1.getModeChange());
    }

    @Test
    public void test006() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result1 = new ModeChange(bluetoothGattCharacteristic);
        ModeChange result2 = ModeChange.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
