package org.im97mori.rbt.ble.descriptor;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ClientCharacteristicConfigrationTest {

    @Test
    public void test001() {
        BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(AdvertisingDataConstants.BASE_UUID, 0);
        bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);

        ClientCharacteristicConfigration result = new ClientCharacteristicConfigration(bluetoothGattDescriptor);
        assertArrayEquals(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, result.getConfiguration());
    }

    @Test
    public void test002() {
        BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(AdvertisingDataConstants.BASE_UUID, 0);
        bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);

        ClientCharacteristicConfigration result = new ClientCharacteristicConfigration(bluetoothGattDescriptor);
        assertArrayEquals(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE, result.getConfiguration());
    }

    @Test
    public void test003() {
        BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(AdvertisingDataConstants.BASE_UUID, 0);
        bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);

        ClientCharacteristicConfigration result = new ClientCharacteristicConfigration(bluetoothGattDescriptor);
        assertArrayEquals(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE, result.getConfiguration());
    }

    @Test
    public void test101() {
        BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(AdvertisingDataConstants.BASE_UUID, 0);
        bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);

        ClientCharacteristicConfigration result1 = new ClientCharacteristicConfigration(bluetoothGattDescriptor);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ClientCharacteristicConfigration result2 = ClientCharacteristicConfigration.CREATOR.createFromParcel(parcel);

        assertArrayEquals(result1.getConfiguration(), result2.getConfiguration());
    }
}
