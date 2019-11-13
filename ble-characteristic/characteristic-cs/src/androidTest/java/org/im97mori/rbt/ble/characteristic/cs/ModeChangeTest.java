package org.im97mori.rbt.ble.characteristic.cs;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ModeChangeTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_NORMAL_MODE) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_NORMAL_MODE, result.getModeChange());
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE, result.getModeChange());
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
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
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
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
        //@formatter:off
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange result1 = new ModeChange(bluetoothGattCharacteristic);
        ModeChange result2 = ModeChange.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
