package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LatestCalculationFlagTest extends AbstractSensingFlagTest {

    @Test
    public void test_001() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) 0x00; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x00; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x00; // SI value flag
        data2[ 6] = (byte) 0x00; // PGA flag
        data2[ 7] = (byte) 0x00; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result = new LatestCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_002() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0xff; // Sequence number
        data2[ 1] = (byte) 0x00; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x00; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x00; // SI value flag
        data2[ 6] = (byte) 0x00; // PGA flag
        data2[ 7] = (byte) 0x00; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result = new LatestCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(255, result.getSequenceNumber());
        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_003() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0xff; // Sequence number
        data2[ 1] = (byte) 0x00; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x00; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x00; // SI value flag
        data2[ 6] = (byte) 0x00; // PGA flag
        data2[ 7] = (byte) 0x00; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result = new LatestCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(255, result.getSequenceNumber());
        assertEquals(0, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertFalse(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertFalse(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertFalse(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertFalse(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSiValueFlag());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSiValueSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSiValueChangeThresholdRise1Reached());
        assertFalse(result.isSiValueChangeThresholdRise2Reached());

        assertEquals(0, result.getPgaFlag());
        assertFalse(result.isPgaSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isPgaSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isPgaChangeThresholdRise1Reached());
        assertFalse(result.isPgaChangeThresholdRise2Reached());

        assertEquals(0, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_004() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x01; // Sequence number
        data2[ 1] = (byte) 0x02; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x03; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x04; // SI value flag
        data2[ 6] = (byte) 0x05; // PGA flag
        data2[ 7] = (byte) 0x06; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result1 = new LatestCalculationFlag(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestCalculationFlag result2 = LatestCalculationFlag.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getDiscomfortIndexFlag(), result1.getDiscomfortIndexFlag());
        assertEquals(result2.getHeatStrokeFlag(), result1.getHeatStrokeFlag());
        assertEquals(result2.getSiValueFlag(), result1.getSiValueFlag());
        assertEquals(result2.getPgaFlag(), result1.getPgaFlag());
        assertEquals(result2.getSeismicIntensityFlag(), result1.getSeismicIntensityFlag());
    }

    @Test
    public void test_005() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0xff; // Sequence number
        data2[ 1] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Discomfort index flag
        data2[ 2] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Discomfort index flag
        data2[ 3] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Heat stroke flag
        data2[ 4] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Heat stroke flag
        data2[ 5] = (byte) ALL_EVENT_FLAG_ACCELERATION; // SI value flag
        data2[ 6] = (byte) ALL_EVENT_FLAG_ACCELERATION; // PGA flag
        data2[ 7] = (byte) ALL_EVENT_FLAG_ACCELERATION; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result1 = new LatestCalculationFlag(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestCalculationFlag result2 = LatestCalculationFlag.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getSequenceNumber(), result1.getSequenceNumber());
        assertEquals(result2.getDiscomfortIndexFlag(), result1.getDiscomfortIndexFlag());
        assertEquals(result2.getHeatStrokeFlag(), result1.getHeatStrokeFlag());
        assertEquals(result2.getSiValueFlag(), result1.getSiValueFlag());
        assertEquals(result2.getPgaFlag(), result1.getPgaFlag());
        assertEquals(result2.getSeismicIntensityFlag(), result1.getSeismicIntensityFlag());
    }

    @Test
    public void test_006() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x01; // Sequence number
        data2[ 1] = (byte) 0x02; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x03; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x04; // SI value flag
        data2[ 6] = (byte) 0x05; // PGA flag
        data2[ 7] = (byte) 0x06; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result1 = new LatestCalculationFlag(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data2, resultData);
    }

    @Test
    public void test_007() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x01; // Sequence number
        data2[ 1] = (byte) 0x02; // Discomfort index flag
        data2[ 2] = (byte) 0x00; // Discomfort index flag
        data2[ 3] = (byte) 0x03; // Heat stroke flag
        data2[ 4] = (byte) 0x00; // Heat stroke flag
        data2[ 5] = (byte) 0x04; // SI value flag
        data2[ 6] = (byte) 0x05; // PGA flag
        data2[ 7] = (byte) 0x06; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        LatestCalculationFlag result1 = new LatestCalculationFlag(bluetoothGattCharacteristic);
        LatestCalculationFlag result2 = LatestCalculationFlag.CREATOR.createFromByteArray(data2);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
