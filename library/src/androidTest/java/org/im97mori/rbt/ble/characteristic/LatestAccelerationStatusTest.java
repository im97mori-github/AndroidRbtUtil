package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.OutputRange.SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.SI_VALUE_CALCULATION_AXIS_XZ_AXIS_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.SI_VALUE_CALCULATION_AXIS_YZ_AXIS_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_VIBRATION_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LatestAccelerationStatusTest {

    @Test
    public void test001() {
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((VIBRATION_INFORMATION_NONE_BIT) & 0xff);
        data[ 2] = (byte) ((0x30) & 0xff);
        data[ 3] = (byte) ((0xf8) & 0xff);
        data[ 4] = (byte) ((0x30) & 0xff);
        data[ 5] = (byte) ((0xf8) & 0xff);
        data[ 6] = (byte) ((0x30) & 0xff);
        data[ 7] = (byte) ((0xf8) & 0xff);
        data[ 8] = (byte) ((SI_VALUE_CALCULATION_AXIS_YZ_AXIS_BIT) & 0xff);
        data[ 9] = (byte) ((0x30) & 0xff);
        data[10] = (byte) ((0xf8) & 0xff);
        data[11] = (byte) ((0x30) & 0xff);
        data[12] = (byte) ((0xf8) & 0xff);
        data[13] = (byte) ((0x30) & 0xff);
        data[14] = (byte) ((0xf8) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestAccelerationStatus result = new LatestAccelerationStatus(bluetoothGattCharacteristic);
        assertEquals(0, result.getSequenceNumber());
        assertEquals(VIBRATION_INFORMATION_NONE_BIT, result.getVibrationInformation());
        assertEquals(-2000, result.getMaximumAccelerationXAxis());
        assertEquals(-200.0d, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(-2000, result.getMaximumAccelerationYAxis());
        assertEquals(-200.0d, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(-2000, result.getMaximumAccelerationZAxis());
        assertEquals(-200.0d, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(SI_VALUE_CALCULATION_AXIS_YZ_AXIS_BIT, result.getSiValueCalculationAxis());
        assertEquals(-2000, result.getAccelerationOffsetXAxis());
        assertEquals(-200.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(-2000, result.getAccelerationOffsetYAxis());
        assertEquals(-200.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(-2000, result.getAccelerationOffsetZAxis());
        assertEquals(-200.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((VIBRATION_INFORMATION_DURING_VIBRATION_BIT) & 0xff);
        data[ 2] = (byte) ((0xd0) & 0xff);
        data[ 3] = (byte) ((0x07) & 0xff);
        data[ 4] = (byte) ((0xd0) & 0xff);
        data[ 5] = (byte) ((0x07) & 0xff);
        data[ 6] = (byte) ((0xd0) & 0xff);
        data[ 7] = (byte) ((0x07) & 0xff);
        data[ 8] = (byte) ((SI_VALUE_CALCULATION_AXIS_XZ_AXIS_BIT) & 0xff);
        data[ 9] = (byte) ((0xd0) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0xd0) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0xd0) & 0xff);
        data[14] = (byte) ((0x07) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestAccelerationStatus result = new LatestAccelerationStatus(bluetoothGattCharacteristic);
        assertEquals(255, result.getSequenceNumber());
        assertEquals(VIBRATION_INFORMATION_DURING_VIBRATION_BIT, result.getVibrationInformation());
        assertEquals(2000, result.getMaximumAccelerationXAxis());
        assertEquals(200.0d, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(2000, result.getMaximumAccelerationYAxis());
        assertEquals(200.0d, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(2000, result.getMaximumAccelerationZAxis());
        assertEquals(200.0d, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(SI_VALUE_CALCULATION_AXIS_XZ_AXIS_BIT, result.getSiValueCalculationAxis());
        assertEquals(2000, result.getAccelerationOffsetXAxis());
        assertEquals(200.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(2000, result.getAccelerationOffsetYAxis());
        assertEquals(200.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(2000, result.getAccelerationOffsetZAxis());
        assertEquals(200.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT) & 0xff);
        data[ 2] = (byte) ((0xd0) & 0xff);
        data[ 3] = (byte) ((0x07) & 0xff);
        data[ 4] = (byte) ((0xd0) & 0xff);
        data[ 5] = (byte) ((0x07) & 0xff);
        data[ 6] = (byte) ((0xd0) & 0xff);
        data[ 7] = (byte) ((0x07) & 0xff);
        data[ 8] = (byte) ((SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT) & 0xff);
        data[ 9] = (byte) ((0xd0) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0xd0) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0xd0) & 0xff);
        data[14] = (byte) ((0x07) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestAccelerationStatus result = new LatestAccelerationStatus(bluetoothGattCharacteristic);
        assertEquals(255, result.getSequenceNumber());
        assertEquals(VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT, result.getVibrationInformation());
        assertEquals(2000, result.getMaximumAccelerationXAxis());
        assertEquals(200.0d, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(2000, result.getMaximumAccelerationYAxis());
        assertEquals(200.0d, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(2000, result.getMaximumAccelerationZAxis());
        assertEquals(200.0d, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT, result.getSiValueCalculationAxis());
        assertEquals(2000, result.getAccelerationOffsetXAxis());
        assertEquals(200.0d, result.getAccelerationOffsetXAxisGal(), 0);
        assertEquals(2000, result.getAccelerationOffsetYAxis());
        assertEquals(200.0d, result.getAccelerationOffsetYAxisGal(), 0);
        assertEquals(2000, result.getAccelerationOffsetZAxis());
        assertEquals(200.0d, result.getAccelerationOffsetZAxisGal(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x07) & 0xff);
        data[ 4] = (byte) ((0x02) & 0xff);
        data[ 5] = (byte) ((0x07) & 0xff);
        data[ 6] = (byte) ((0x03) & 0xff);
        data[ 7] = (byte) ((0x07) & 0xff);
        data[ 8] = (byte) ((SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT) & 0xff);
        data[ 9] = (byte) ((0x04) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0x05) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0x06) & 0xff);
        data[14] = (byte) ((0x07) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestAccelerationStatus result1 = new LatestAccelerationStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestAccelerationStatus result2 = LatestAccelerationStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getMaximumAccelerationXAxis(), result2.getMaximumAccelerationXAxis());
        assertEquals(result1.getMaximumAccelerationYAxis(), result2.getMaximumAccelerationYAxis());
        assertEquals(result1.getMaximumAccelerationZAxis(), result2.getMaximumAccelerationZAxis());
        assertEquals(result1.getSiValueCalculationAxis(), result2.getSiValueCalculationAxis());
        assertEquals(result1.getAccelerationOffsetXAxis(), result2.getAccelerationOffsetXAxis());
        assertEquals(result1.getAccelerationOffsetYAxis(), result2.getAccelerationOffsetYAxis());
        assertEquals(result1.getAccelerationOffsetZAxis(), result2.getAccelerationOffsetZAxis());
    }

    @Test
    public void test005() {
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x07) & 0xff);
        data[ 4] = (byte) ((0x02) & 0xff);
        data[ 5] = (byte) ((0x07) & 0xff);
        data[ 6] = (byte) ((0x03) & 0xff);
        data[ 7] = (byte) ((0x07) & 0xff);
        data[ 8] = (byte) ((SI_VALUE_CALCULATION_AXIS_XY_AXIS_BIT) & 0xff);
        data[ 9] = (byte) ((0x04) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0x05) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0x06) & 0xff);
        data[14] = (byte) ((0x07) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestAccelerationStatus result1 = new LatestAccelerationStatus(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }
}
