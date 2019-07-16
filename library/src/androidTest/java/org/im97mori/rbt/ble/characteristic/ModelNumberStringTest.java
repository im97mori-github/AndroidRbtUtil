package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ModelNumberStringTest {

    @Test
    public void test1() {
        String modelNumber = "2JCIE-BU01";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(modelNumber.getBytes(StandardCharsets.UTF_8));

        ModelNumberString modelNumberString = new ModelNumberString(bluetoothGattCharacteristic);
        assertEquals(modelNumber, modelNumberString.getModelNumber());
    }

    @Test
    public void test2() {
        String modelNumber = "2JCIE-BU01";

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(modelNumber.getBytes(StandardCharsets.UTF_8));

        ModelNumberString result1 = new ModelNumberString(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ModelNumberString result2 = ModelNumberString.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getModelNumber(), result2.getModelNumber());
    }
}
