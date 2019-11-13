package org.im97mori.rbt.ble.characteristic.lds;

import android.bluetooth.BluetoothGattCharacteristic;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR_LSB;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR_MSB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LatestCalculationFlagDiscomfortIndexTest {

    @Test
    public void test_discomfortIndex001() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Discomfort index flag
        data2[ 2] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Discomfort index flag
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
        assertEquals(ALL_EVENT_FLAG_SENSOR, result.getDiscomfortIndexFlag());
        assertTrue(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertTrue(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertTrue(result.isDiscomfortIndexAverageValueThresholdLowerReached());
        assertTrue(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
        assertTrue(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
        assertTrue(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
        assertTrue(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

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
    public void test_discomfortIndex002() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getDiscomfortIndexFlag());
        assertTrue(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
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
    public void test_discomfortIndex003() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
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
    public void test_discomfortIndex004() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
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
    public void test_discomfortIndex005() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
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
    public void test_discomfortIndex006() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdRise1Reached());
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
    public void test_discomfortIndex007() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdRise2Reached());
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
    public void test_discomfortIndex008() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdDecline1Reached());
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
    public void test_discomfortIndex009() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertTrue(result.isDiscomfortIndexChangeThresholdDecline2Reached());
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
    public void test_discomfortIndex010() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertTrue(result.isDiscomfortIndexAverageValueThresholdUpperReached());
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
    public void test_discomfortIndex011() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getDiscomfortIndexFlag());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isDiscomfortIndexSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdRise2Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline1Reached());
        assertFalse(result.isDiscomfortIndexChangeThresholdDecline2Reached());
        assertFalse(result.isDiscomfortIndexAverageValueThresholdUpperReached());
        assertTrue(result.isDiscomfortIndexAverageValueThresholdLowerReached());
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
    public void test_discomfortIndex012() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexPeakToPeakThresholdUpperReached());
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
    public void test_discomfortIndex013() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexPeakToPeakThresholdLowerReached());
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
    public void test_discomfortIndex014() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexIntervalDifferenceThresholdRiseReached());
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
    public void test_discomfortIndex015() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexIntervalDifferenceThresholdDeclineReached());
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
    public void test_discomfortIndex016() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexBaseDifferenceThresholdUpperReached());
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
    public void test_discomfortIndex017() {
        //@formatter:off
        byte[] data2 = new byte[8];
        data2[ 0] = (byte) 0x00; // Sequence number
        data2[ 1] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Discomfort index flag
        data2[ 2] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Discomfort index flag
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
        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getDiscomfortIndexFlag());
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
        assertTrue(result.isDiscomfortIndexBaseDifferenceThresholdLowerReached());

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

}
