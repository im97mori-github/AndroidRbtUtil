package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.BLEConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class EtvocSensor2Test extends AbstractEventEnableDisableTest {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x00) & 0xff); // Average value threshold [upper]
        data[ 1] = (byte) ((0x00) & 0xff); // Average value threshold [upper]
        data[ 2] = (byte) ((0x00) & 0xff); // Average value threshold [lower]
        data[ 3] = (byte) ((0x00) & 0xff); // Average value threshold [lower]
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        EtvocSensor2 result = new EtvocSensor2(bluetoothGattCharacteristic);
        assertEquals(0, result.getAverageValueThresholdUpper());
        assertEquals(0d, result.getAverageValueThresholdUpperPpb(), 0);
        assertEquals(0, result.getAverageValueThresholdLower());
        assertEquals(0d, result.getAverageValueThresholdLowerPpb(), 0);
        assertEquals(0, result.getPeakToPeakThresholdUpper());
        assertEquals(0d, result.getPeakToPeakThresholdUpperPpb(), 0);
        assertEquals(0, result.getPeakToPeakThresholdLower());
        assertEquals(0d, result.getPeakToPeakThresholdLowerPpb(), 0);
        assertEquals(0, result.getIntervalDifferenceThresholdUpper());
        assertEquals(0d, result.getIntervalDifferenceThresholdUpperPpb(), 0);
        assertEquals(0, result.getIntervalDifferenceThresholdLower());
        assertEquals(0d, result.getIntervalDifferenceThresholdLowerPpb(), 0);
        assertEquals(0, result.getBaseDifferenceThresholdUpper());
        assertEquals(0d, result.getBaseDifferenceThresholdUpperPpb(), 0);
        assertEquals(0, result.getBaseDifferenceThresholdLower());
        assertEquals(0d, result.getBaseDifferenceThresholdLowerPpb(), 0);
        assertEquals(1, result.getAverageValueCount());
        assertEquals(1, result.getPeakToPeakCount());
        assertEquals(1, result.getIntervalDifferenceCount());
        assertEquals(1, result.getBaseDifferenceCount());
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff); // Average value threshold [upper]
        data[ 1] = (byte) ((0x7f) & 0xff); // Average value threshold [upper]
        data[ 2] = (byte) ((0xff) & 0xff); // Average value threshold [lower]
        data[ 3] = (byte) ((0x7f) & 0xff); // Average value threshold [lower]
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        EtvocSensor2 result = new EtvocSensor2(bluetoothGattCharacteristic);
        assertEquals(32767, result.getAverageValueThresholdUpper());
        assertEquals(32767d, result.getAverageValueThresholdUpperPpb(), 0);
        assertEquals(32767, result.getAverageValueThresholdLower());
        assertEquals(32767d, result.getAverageValueThresholdLowerPpb(), 0);
        assertEquals(10000, result.getPeakToPeakThresholdUpper());
        assertEquals(10000d, result.getPeakToPeakThresholdUpperPpb(), 0);
        assertEquals(10000, result.getPeakToPeakThresholdLower());
        assertEquals(10000d, result.getPeakToPeakThresholdLowerPpb(), 0);
        assertEquals(10000, result.getIntervalDifferenceThresholdUpper());
        assertEquals(10000d, result.getIntervalDifferenceThresholdUpperPpb(), 0);
        assertEquals(10000, result.getIntervalDifferenceThresholdLower());
        assertEquals(10000d, result.getIntervalDifferenceThresholdLowerPpb(), 0);
        assertEquals(10000, result.getBaseDifferenceThresholdUpper());
        assertEquals(10000d, result.getBaseDifferenceThresholdUpperPpb(), 0);
        assertEquals(10000, result.getBaseDifferenceThresholdLower());
        assertEquals(10000d, result.getBaseDifferenceThresholdLowerPpb(), 0);
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        EtvocSensor2 result1 = new EtvocSensor2(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        EtvocSensor2 result2 = EtvocSensor2.CREATOR.createFromParcel(parcel);

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

    @Test
    public void test102() {
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        EtvocSensor2 result1 = new EtvocSensor2(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test103() {
        int averageValueThresholdUpper = 0xf060;
        int averageValueThresholdLower = 0xf061;
        int peakToPeakThresholdUpper = 0x0000;
        int peakToPeakThresholdLower = 0x0001;
        int intervalDifferenceThresholdUpper = 0x0002;
        int intervalDifferenceThresholdLower = 0x0003;
        int baseDifferenceThresholdUpper = 0x0004;
        int baseDifferenceThresholdLower = 0x0005;
        int averageValueCount = 0x01;
        int peakToPeakCount = 0x02;
        int interavalDifferenceCount = 0x03;
        int baseDifferenceCount = 0x04;

        EtvocSensor2 result1 = new EtvocSensor2(averageValueThresholdUpper, averageValueThresholdLower, peakToPeakThresholdUpper, peakToPeakThresholdLower, intervalDifferenceThresholdUpper, intervalDifferenceThresholdLower, baseDifferenceThresholdUpper, baseDifferenceThresholdLower, averageValueCount, peakToPeakCount, interavalDifferenceCount, baseDifferenceCount);
        assertEquals(averageValueThresholdUpper, result1.getAverageValueThresholdUpper());
        assertEquals(averageValueThresholdLower, result1.getAverageValueThresholdLower());
        assertEquals(peakToPeakThresholdUpper, result1.getPeakToPeakThresholdUpper());
        assertEquals(peakToPeakThresholdLower, result1.getPeakToPeakThresholdLower());
        assertEquals(intervalDifferenceThresholdUpper, result1.getIntervalDifferenceThresholdUpper());
        assertEquals(intervalDifferenceThresholdLower, result1.getIntervalDifferenceThresholdLower());
        assertEquals(baseDifferenceThresholdUpper, result1.getBaseDifferenceThresholdUpper());
        assertEquals(baseDifferenceThresholdLower, result1.getBaseDifferenceThresholdLower());
        assertEquals(averageValueCount, result1.getAverageValueCount());
        assertEquals(peakToPeakCount, result1.getPeakToPeakCount());
        assertEquals(interavalDifferenceCount, result1.getIntervalDifferenceCount());
        assertEquals(baseDifferenceCount, result1.getBaseDifferenceCount());
    }

    @Test
    public void test104() {
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        EtvocSensor2 result1 = new EtvocSensor2(bluetoothGattCharacteristic);
        EtvocSensor2 result2 = EtvocSensor2.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
