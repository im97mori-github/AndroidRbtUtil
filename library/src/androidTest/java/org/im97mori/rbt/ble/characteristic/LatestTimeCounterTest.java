package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.math.BigInteger;

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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter latestTimeCounter = new LatestTimeCounter(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), latestTimeCounter.getTimeCounter());
    }

    @Test
    public void test3() {
        byte[] data = new byte[8];
        data[0] = (byte) (0xff & 0xff);
        data[1] = (byte) (0xff & 0xff);
        data[2] = (byte) (0xff & 0xff);
        data[3] = (byte) (0xff & 0xff);
        data[4] = (byte) (0xff & 0xff);
        data[5] = (byte) (0xff & 0xff);
        data[6] = (byte) (0xff & 0xff);
        data[7] = (byte) (0xff & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestTimeCounter result1 = new LatestTimeCounter(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestTimeCounter result2 = LatestTimeCounter.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTimeCounter(), result2.getTimeCounter());
    }
}
