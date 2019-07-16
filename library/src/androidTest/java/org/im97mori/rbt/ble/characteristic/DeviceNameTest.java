package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class DeviceNameTest {

    @Test
    public void test1() {
        String name = "Rbt-Sensor";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(name.getBytes(StandardCharsets.UTF_8));

        DeviceName deviceName = new DeviceName(bluetoothGattCharacteristic);
        assertEquals(name, deviceName.getName());
    }

    @Test
    public void test2() {
        String name = "Rbt-Sensor";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(name.getBytes(StandardCharsets.UTF_8));

        DeviceName result1 = new DeviceName(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        DeviceName result2 = DeviceName.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getName(), result2.getName());
    }
}
