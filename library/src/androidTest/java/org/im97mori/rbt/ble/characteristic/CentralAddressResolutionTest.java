package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CentralAddressResolutionTest {

    @Test
    public void test1() {
        byte[] data = new byte[1];
        data[0] = (byte) (CentralAddressResolution.CENTRAL_ADDRESS_RESOLUTION_SUPPORTED & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        CentralAddressResolution peripheralPreferredConnectionParameters = new CentralAddressResolution(bluetoothGattCharacteristic);
        assertEquals(CentralAddressResolution.CENTRAL_ADDRESS_RESOLUTION_SUPPORTED, peripheralPreferredConnectionParameters.getCentralAddressResolutionSupport());
    }

    @Test
    public void test2() {
        byte[] data = new byte[1];
        data[0] = (byte) (CentralAddressResolution.CENTRAL_ADDRESS_RESOLUTION_SUPPORTED & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        CentralAddressResolution result1 = new CentralAddressResolution(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        CentralAddressResolution result2 = CentralAddressResolution.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getCentralAddressResolutionSupport(), result2.getCentralAddressResolutionSupport());
    }

}
