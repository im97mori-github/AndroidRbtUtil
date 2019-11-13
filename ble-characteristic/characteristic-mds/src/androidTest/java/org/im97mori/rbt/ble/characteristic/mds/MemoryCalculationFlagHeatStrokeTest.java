package org.im97mori.rbt.ble.characteristic.mds;

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

public class MemoryCalculationFlagHeatStrokeTest {

    @Test
    public void test_heatStroke001() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Heat stroke flag
        data2[ 7] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(ALL_EVENT_FLAG_SENSOR, result.getHeatStrokeFlag());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertTrue(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertTrue(result.isHeatStrokeAverageValueThresholdLowerReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdUpperReached());
        assertTrue(result.isHeatStrokePeakToPeakThresholdLowerReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

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
    public void test_heatStroke002() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getHeatStrokeFlag());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
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
    public void test_heatStroke003() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
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
    public void test_heatStroke004() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
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
    public void test_heatStroke005() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
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
    public void test_heatStroke006() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise1Reached());
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
    public void test_heatStroke007() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdRise2Reached());
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
    public void test_heatStroke008() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline1Reached());
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
    public void test_heatStroke009() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertTrue(result.isHeatStrokeChangeThresholdDecline2Reached());
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
    public void test_heatStroke010() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertTrue(result.isHeatStrokeAverageValueThresholdUpperReached());
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
    public void test_heatStroke011() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getHeatStrokeFlag());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isHeatStrokeSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdRise2Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline1Reached());
        assertFalse(result.isHeatStrokeChangeThresholdDecline2Reached());
        assertFalse(result.isHeatStrokeAverageValueThresholdUpperReached());
        assertTrue(result.isHeatStrokeAverageValueThresholdLowerReached());
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
    public void test_heatStroke012() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokePeakToPeakThresholdUpperReached());
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
    public void test_heatStroke013() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokePeakToPeakThresholdLowerReached());
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
    public void test_heatStroke014() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdRiseReached());
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
    public void test_heatStroke015() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokeIntervalDifferenceThresholdDeclineReached());
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
    public void test_heatStroke016() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdUpperReached());
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
    public void test_heatStroke017() {
        //@formatter:off
        byte[] data2 = new byte[11];
        data2[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data2[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data2[ 4] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 5] = (byte) ((0x00) & 0xff); // Discomfort index flag
        data2[ 6] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Heat stroke flag
        data2[ 7] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Heat stroke flag
        data2[ 8] = (byte) 0x00; // SI value flag
        data2[ 9] = (byte) 0x00; // PGA flag
        data2[10] = (byte) 0x00; // Seismic intensity flag
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

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getHeatStrokeFlag());
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
        assertTrue(result.isHeatStrokeBaseDifferenceThresholdLowerReached());

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
