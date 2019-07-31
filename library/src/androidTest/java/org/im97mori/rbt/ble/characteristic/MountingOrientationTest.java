package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MountingOrientationTest {

    @Test
    public void test1() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_1;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_1, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test2() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_2;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_2, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test3() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_3;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_3, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test4() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_4;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_4, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test5() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_5;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_5, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test6() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_6;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation mountingOrientation = new MountingOrientation(bluetoothGattCharacteristic);
        assertEquals(MountingOrientation.MOUNTING_ORIENTATION_POSITION_6, mountingOrientation.getMountingOrientation());
    }

    @Test
    public void test7() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_6;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation result1 = new MountingOrientation(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MountingOrientation result2 = MountingOrientation.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMountingOrientation(), result2.getMountingOrientation());
    }

    @Test
    public void test8() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_6;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation result1 = new MountingOrientation(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test9() {
        byte[] data = new byte[1];
        data[0] = MountingOrientation.MOUNTING_ORIENTATION_POSITION_6;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MountingOrientation result1 = new MountingOrientation(bluetoothGattCharacteristic);
        MountingOrientation result2 = MountingOrientation.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }

}
