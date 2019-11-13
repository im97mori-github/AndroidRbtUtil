package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableAcceleration.ALL_EVENT_FLAG_ACCELERATION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryCalculationFlagSeismicIntensityTest {

    @Test
    public void test_seismicIntensity001() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) ((0x00) & 0xff); // SI value flag
        data2[ 9] = (byte) ((0x00) & 0xff); // PGA flag
        data2[10] = (byte) ALL_EVENT_FLAG_ACCELERATION; // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        MemoryCalculationFlag result = new MemoryCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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

        assertEquals(ALL_EVENT_FLAG_ACCELERATION, result.getSeismicIntensityFlag());
        assertTrue(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertTrue(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertTrue(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertTrue(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_seismicIntensity002() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) ((0x00) & 0xff); // SI value flag
        data2[ 9] = (byte) ((0x00) & 0xff); // PGA flag
        data2[10] = (byte) (RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        MemoryCalculationFlag result = new MemoryCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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

        assertEquals(RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getSeismicIntensityFlag());
        assertTrue(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_seismicIntensity003() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) ((0x00) & 0xff); // SI value flag
        data2[ 9] = (byte) ((0x00) & 0xff); // PGA flag
        data2[10] = (byte) (RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        MemoryCalculationFlag result = new MemoryCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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

        assertEquals(RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertTrue(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_seismicIntensity004() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) ((0x00) & 0xff); // SI value flag
        data2[ 9] = (byte) ((0x00) & 0xff); // PGA flag
        data2[10] = (byte) (RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1 & 0xff); // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        MemoryCalculationFlag result = new MemoryCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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

        assertEquals(RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertTrue(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

    @Test
    public void test_seismicIntensity005() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((0x00) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) ((0x00) & 0xff); // SI value flag
        data2[ 9] = (byte) ((0x00) & 0xff); // PGA flag
        data2[10] = (byte) (RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2 & 0xff); // Seismic intensity flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data2);

        MemoryCalculationFlag result = new MemoryCalculationFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());

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

        assertEquals(RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2, result.getSeismicIntensityFlag());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSeismicIntensitySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSeismicIntensityChangeThresholdRise1Reached());
        assertTrue(result.isSeismicIntensityChangeThresholdRise2Reached());
    }

}
