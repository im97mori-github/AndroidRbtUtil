package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class VibrationCountTest {

    @Test
    public void test001() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result = new VibrationCount(bluetoothGattCharacteristic);
        assertEquals(BigInteger.ZERO, result.getEarthquakeCount());
        assertEquals(BigInteger.ZERO, result.getVibrationCount());
    }

    @Test
    public void test002() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result = new VibrationCount(bluetoothGattCharacteristic);
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getEarthquakeCount());
        assertEquals(new BigInteger(new byte[]{0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff}), result.getVibrationCount());
    }

    @Test
    public void test003() {
        byte[] data = new byte[8];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0xff) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        VibrationCount result1 = new VibrationCount(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        VibrationCount result2 = VibrationCount.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getEarthquakeCount(), result2.getEarthquakeCount());
        assertEquals(result1.getVibrationCount(), result2.getVibrationCount());
    }
}
