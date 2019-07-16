package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryChangeTest {

    @Test
    public void test001() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_NORMAL_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange modeChange = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_NORMAL_MODE, modeChange.getModeChange());
    }

    @Test
    public void test002() {
        byte[] data = new byte[1];
        data[ 0] = (byte) ((ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ModeChange modeChange = new ModeChange(bluetoothGattCharacteristic);
        assertEquals(ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE, modeChange.getModeChange());
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
}
