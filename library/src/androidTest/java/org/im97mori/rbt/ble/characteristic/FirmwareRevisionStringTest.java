package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class FirmwareRevisionStringTest {

    @Test
    public void test1() {
        String firmwareRevision = "00.00";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(firmwareRevision.getBytes(StandardCharsets.UTF_8));

        FirmwareRevisionString firmwareRevisionString = new FirmwareRevisionString(bluetoothGattCharacteristic);
        assertEquals(firmwareRevision, firmwareRevisionString.getFirmwareRevision());
    }

    @Test
    public void test2() {
        String firmwareRevision = "99.99";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(firmwareRevision.getBytes(StandardCharsets.UTF_8));

        FirmwareRevisionString firmwareRevisionString = new FirmwareRevisionString(bluetoothGattCharacteristic);
        assertEquals(firmwareRevision, firmwareRevisionString.getFirmwareRevision());
    }

    @Test
    public void test3() {
        String firmwareRevision = "00.00";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(firmwareRevision.getBytes(StandardCharsets.UTF_8));

        FirmwareRevisionString result1 = new FirmwareRevisionString(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        FirmwareRevisionString result2 = FirmwareRevisionString.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getFirmwareRevision(), result1.getFirmwareRevision());
    }

    @Test
    public void test4() {
        String firmwareRevision = "99.99";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(firmwareRevision.getBytes(StandardCharsets.UTF_8));

        FirmwareRevisionString result1 = new FirmwareRevisionString(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        FirmwareRevisionString result2 = FirmwareRevisionString.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getFirmwareRevision(), result2.getFirmwareRevision());
    }
}
