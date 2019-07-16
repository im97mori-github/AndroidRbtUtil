package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BarometricPressureSensor2Test extends AbstractEventEnableDisableTest {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xb8) & 0xff); // Average value threshold [upper]
        data[ 1] = (byte) ((0x0b) & 0xff); // Average value threshold [upper]
        data[ 2] = (byte) ((0xb8) & 0xff); // Average value threshold [lower]
        data[ 3] = (byte) ((0x0b) & 0xff); // Average value threshold [lower]
        data[ 4] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [upper]
        data[ 5] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [upper]
        data[ 6] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [lower]
        data[ 7] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [lower]
        data[ 8] = (byte) ((0x00) & 0xff); // Interval difference threshold [upper]
        data[ 9] = (byte) ((0x00) & 0xff); // Interval difference threshold [upper]
        data[10] = (byte) ((0x00) & 0xff); // Interval difference threshold [lower]
        data[11] = (byte) ((0x00) & 0xff); // Interval difference threshold [lower]
        data[12] = (byte) ((0x00) & 0xff); // Base difference threshold [upper]
        data[13] = (byte) ((0x00) & 0xff); // Base difference threshold [upper]
        data[14] = (byte) ((0x00) & 0xff); // Base difference threshold [lower]
        data[15] = (byte) ((0x00) & 0xff); // Base difference threshold [lower]
        data[16] = (byte) ((0x01) & 0xff); // Average value count
        data[17] = (byte) ((0x01) & 0xff); // Peak to Peak count
        data[18] = (byte) ((0x01) & 0xff); // Interval difference count
        data[19] = (byte) ((0x01) & 0xff); // Base differencecount

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        BarometricPressureSensor2 result = new BarometricPressureSensor2(bluetoothGattCharacteristic);
        assertEquals(3000, result.getAverageValueThresholdUpper());
        assertEquals(300.0d, result.getAverageValueThresholdUpperHpa(), 0);
        assertEquals(3000, result.getAverageValueThresholdLower());
        assertEquals(300.0d, result.getAverageValueThresholdLowerHpa(), 0);
        assertEquals(0, result.getPeakToPeakThresholdUpper());
        assertEquals(0d, result.getPeakToPeakThresholdUpperHpa(), 0);
        assertEquals(0, result.getPeakToPeakThresholdLower());
        assertEquals(0d, result.getPeakToPeakThresholdLowerHpa(), 0);
        assertEquals(0, result.getIntervalDifferenceThresholdUpper());
        assertEquals(0d, result.getIntervalDifferenceThresholdUpperHpa(), 0);
        assertEquals(0, result.getIntervalDifferenceThresholdLower());
        assertEquals(0d, result.getIntervalDifferenceThresholdLowerHpa(), 0);
        assertEquals(0, result.getBaseDifferenceThresholdUpper());
        assertEquals(0d, result.getBaseDifferenceThresholdUpperHpa(), 0);
        assertEquals(0, result.getBaseDifferenceThresholdLower());
        assertEquals(0d, result.getBaseDifferenceThresholdLowerHpa(), 0);
        assertEquals(1, result.getAverageValueCount());
        assertEquals(1, result.getPeakToPeakCount());
        assertEquals(1, result.getIntervalDifferenceCount());
        assertEquals(1, result.getBaseDifferenceCount());
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xf8) & 0xff); // Average value threshold [upper]
        data[ 1] = (byte) ((0x2a) & 0xff); // Average value threshold [upper]
        data[ 2] = (byte) ((0xf8) & 0xff); // Average value threshold [lower]
        data[ 3] = (byte) ((0x2a) & 0xff); // Average value threshold [lower]
        data[ 4] = (byte) ((0x10) & 0xff); // Peak to Peak threshold [upper]
        data[ 5] = (byte) ((0x27) & 0xff); // Peak to Peak threshold [upper]
        data[ 6] = (byte) ((0x10) & 0xff); // Peak to Peak threshold [lower]
        data[ 7] = (byte) ((0x27) & 0xff); // Peak to Peak threshold [lower]
        data[ 8] = (byte) ((0x10) & 0xff); // Interval difference threshold [upper]
        data[ 9] = (byte) ((0x27) & 0xff); // Interval difference threshold [upper]
        data[10] = (byte) ((0x10) & 0xff); // Interval difference threshold [lower]
        data[11] = (byte) ((0x27) & 0xff); // Interval difference threshold [lower]
        data[12] = (byte) ((0x10) & 0xff); // Base difference threshold [upper]
        data[13] = (byte) ((0x27) & 0xff); // Base difference threshold [upper]
        data[14] = (byte) ((0x10) & 0xff); // Base difference threshold [lower]
        data[15] = (byte) ((0x27) & 0xff); // Base difference threshold [lower]
        data[16] = (byte) ((0x08) & 0xff); // Average value count
        data[17] = (byte) ((0x08) & 0xff); // Peak to Peak count
        data[18] = (byte) ((0x08) & 0xff); // Interval difference count
        data[19] = (byte) ((0x08) & 0xff); // Base differencecount

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        BarometricPressureSensor2 result = new BarometricPressureSensor2(bluetoothGattCharacteristic);
        assertEquals(11000, result.getAverageValueThresholdUpper());
        assertEquals(1100.0d, result.getAverageValueThresholdUpperHpa(), 0);
        assertEquals(11000, result.getAverageValueThresholdLower());
        assertEquals(1100.0d, result.getAverageValueThresholdLowerHpa(), 0);
        assertEquals(10000, result.getPeakToPeakThresholdUpper());
        assertEquals(10.000d, result.getPeakToPeakThresholdUpperHpa(), 0);
        assertEquals(10000, result.getPeakToPeakThresholdLower());
        assertEquals(10.000d, result.getPeakToPeakThresholdLowerHpa(), 0);
        assertEquals(10000, result.getIntervalDifferenceThresholdUpper());
        assertEquals(10.000d, result.getIntervalDifferenceThresholdUpperHpa(), 0);
        assertEquals(10000, result.getIntervalDifferenceThresholdLower());
        assertEquals(10.000d, result.getIntervalDifferenceThresholdLowerHpa(), 0);
        assertEquals(10000, result.getBaseDifferenceThresholdUpper());
        assertEquals(10.000d, result.getBaseDifferenceThresholdUpperHpa(), 0);
        assertEquals(10000, result.getBaseDifferenceThresholdLower());
        assertEquals(10.000d, result.getBaseDifferenceThresholdLowerHpa(), 0);
        assertEquals(8, result.getAverageValueCount());
        assertEquals(8, result.getPeakToPeakCount());
        assertEquals(8, result.getIntervalDifferenceCount());
        assertEquals(8, result.getBaseDifferenceCount());
    }

    @Test
    public void test101() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff); // Average value threshold [upper]
        data[ 1] = (byte) ((0x00) & 0xff); // Average value threshold [upper]
        data[ 2] = (byte) ((0x02) & 0xff); // Average value threshold [lower]
        data[ 3] = (byte) ((0x00) & 0xff); // Average value threshold [lower]
        data[ 4] = (byte) ((0x03) & 0xff); // Peak to Peak threshold [upper]
        data[ 5] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [upper]
        data[ 6] = (byte) ((0x04) & 0xff); // Peak to Peak threshold [lower]
        data[ 7] = (byte) ((0x00) & 0xff); // Peak to Peak threshold [lower]
        data[ 8] = (byte) ((0x05) & 0xff); // Interval difference threshold [upper]
        data[ 9] = (byte) ((0x00) & 0xff); // Interval difference threshold [upper]
        data[10] = (byte) ((0x06) & 0xff); // Interval difference threshold [lower]
        data[11] = (byte) ((0x00) & 0xff); // Interval difference threshold [lower]
        data[12] = (byte) ((0x07) & 0xff); // Base difference threshold [upper]
        data[13] = (byte) ((0x00) & 0xff); // Base difference threshold [upper]
        data[14] = (byte) ((0x08) & 0xff); // Base difference threshold [lower]
        data[15] = (byte) ((0x00) & 0xff); // Base difference threshold [lower]
        data[16] = (byte) ((0x01) & 0xff); // Average value count
        data[17] = (byte) ((0x02) & 0xff); // Peak to Peak count
        data[18] = (byte) ((0x03) & 0xff); // Interval difference count
        data[19] = (byte) ((0x04) & 0xff); // Base differencecount

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        BarometricPressureSensor2 result1 = new BarometricPressureSensor2(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        BarometricPressureSensor2 result2 = BarometricPressureSensor2.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getAverageValueThresholdUpper(), result2.getAverageValueThresholdUpper());
        assertEquals(result1.getAverageValueThresholdLower(), result2.getAverageValueThresholdLower());
        assertEquals(result1.getPeakToPeakThresholdUpper(), result2.getPeakToPeakThresholdUpper());
        assertEquals(result1.getPeakToPeakThresholdLower(), result2.getPeakToPeakThresholdLower());
        assertEquals(result1.getIntervalDifferenceThresholdUpper(), result2.getIntervalDifferenceThresholdUpper());
        assertEquals(result1.getIntervalDifferenceThresholdLower(), result2.getIntervalDifferenceThresholdLower());
        assertEquals(result1.getBaseDifferenceThresholdUpper(), result2.getBaseDifferenceThresholdUpper());
        assertEquals(result1.getBaseDifferenceThresholdLower(), result2.getBaseDifferenceThresholdLower());
        assertEquals(result1.getAverageValueCount(), result2.getAverageValueCount());
        assertEquals(result1.getPeakToPeakCount(), result2.getPeakToPeakCount());
        assertEquals(result1.getIntervalDifferenceCount(), result2.getIntervalDifferenceCount());
        assertEquals(result1.getBaseDifferenceCount(), result2.getBaseDifferenceCount());
    }
}
