package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeatStrokeSensor1Test extends AbstractEventEnableDisableTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x00) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x00) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x60) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xf0) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x60) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0xf0) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x60) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0xf0) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x60) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0xf0) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x00) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x00) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x00) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x00) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x00) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x00) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(0, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(-4000, result.getSimpleThresholdUpperLimit1());
        assertEquals(-40.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(-4000, result.getSimpleThresholdUpperLimit2());
        assertEquals(-40.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(-4000, result.getSimpleThresholdLowerLimit1());
        assertEquals(-40.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(-4000, result.getSimpleThresholdLowerLimit2());
        assertEquals(-40.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(0, result.getChangeThresholdRise1());
        assertEquals(0d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(0, result.getChangeThresholdRise2());
        assertEquals(0d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(0, result.getChangeThresholdDecline1());
        assertEquals(0d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(0, result.getChangeThresholdDecline2());
        assertEquals(0d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((ALL_EVENT_ENABLE_DISABLE_SENSOR_LSB) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((ALL_EVENT_ENABLE_DISABLE_SENSOR_MSB) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(ALL_EVENT_ENABLE_DISABLE_SENSOR, result.getEventEnableDisable());
        assertTrue(result.isSimpleThresholdUpperLimit1Enabled());
        assertTrue(result.isSimpleThresholdUpperLimit2Enabled());
        assertTrue(result.isSimpleThresholdLowerLimit1Enabled());
        assertTrue(result.isSimpleThresholdLowerLimit2Enabled());
        assertTrue(result.isChangeThresholdRise1Enabled());
        assertTrue(result.isChangeThresholdRise2Enabled());
        assertTrue(result.isChangeThresholdDecline1Enabled());
        assertTrue(result.isChangeThresholdDecline2Enabled());
        assertTrue(result.isAverageValueThresholdUpperEnabled());
        assertTrue(result.isAverageValueThresholdLowerEnabled());
        assertTrue(result.isPeakToPeakThresholdUpperEnabled());
        assertTrue(result.isPeakToPeakThresholdLowerEnabled());
        assertTrue(result.isIntervalDifferenceThresholdRiseEnabled());
        assertTrue(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertTrue(result.isBaseDifferenceThresholdUpperEnabled());
        assertTrue(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getEventEnableDisable());
        assertTrue(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertTrue(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertTrue(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertTrue(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test007() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertTrue(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test008() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertTrue(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test009() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertTrue(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test010() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertTrue(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test011() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertTrue(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test012() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertTrue(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test013() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertTrue(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test014() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertTrue(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test015() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertTrue(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test016() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertTrue(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test017() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertTrue(result.isBaseDifferenceThresholdUpperEnabled());
        assertFalse(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test018() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0xd4) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x30) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0xd4) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x30) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x10) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x27) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x10) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x27) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x10) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x27) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit1Enabled());
        assertFalse(result.isSimpleThresholdLowerLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertFalse(result.isChangeThresholdDecline1Enabled());
        assertFalse(result.isChangeThresholdDecline2Enabled());
        assertFalse(result.isAverageValueThresholdUpperEnabled());
        assertFalse(result.isAverageValueThresholdLowerEnabled());
        assertFalse(result.isPeakToPeakThresholdUpperEnabled());
        assertFalse(result.isPeakToPeakThresholdLowerEnabled());
        assertFalse(result.isIntervalDifferenceThresholdRiseEnabled());
        assertFalse(result.isIntervalDifferenceThresholdDeclineEnabled());
        assertFalse(result.isBaseDifferenceThresholdUpperEnabled());
        assertTrue(result.isBaseDifferenceThresholdLowerEnabled());
        assertEquals(12500, result.getSimpleThresholdUpperLimit1());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdUpperLimit2());
        assertEquals(125.00d, result.getSimpleThresholdUpperLimit2DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit1());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit1DegC(), 0);
        assertEquals(12500, result.getSimpleThresholdLowerLimit2());
        assertEquals(125.00d, result.getSimpleThresholdLowerLimit2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1DegC(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2DegC(), 0);
    }

    @Test
    public void test101() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x02) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x03) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x04) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x05) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x00) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x06) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x00) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x07) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x00) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result1 = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        HeatStrokeSensor1 result2 = HeatStrokeSensor1.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getEventEnableDisable(), result2.getEventEnableDisable());
        assertEquals(result1.getSimpleThresholdUpperLimit1(), result2.getSimpleThresholdUpperLimit1());
        assertEquals(result1.getSimpleThresholdUpperLimit2(), result2.getSimpleThresholdUpperLimit2());
        assertEquals(result1.getSimpleThresholdLowerLimit1(), result2.getSimpleThresholdLowerLimit1());
        assertEquals(result1.getSimpleThresholdLowerLimit2(), result2.getSimpleThresholdLowerLimit2());
        assertEquals(result1.getChangeThresholdRise1(), result2.getChangeThresholdRise1());
        assertEquals(result1.getChangeThresholdRise2(), result2.getChangeThresholdRise2());
        assertEquals(result1.getChangeThresholdDecline1(), result2.getChangeThresholdDecline1());
        assertEquals(result1.getChangeThresholdDecline2(), result2.getChangeThresholdDecline2());
    }

    @Test
    public void test102() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x02) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x03) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x04) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x05) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x00) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x06) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x00) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x07) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x00) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result1 = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test103() {
        int eventEnableDisable = RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER;
        int simpleThresholdUpperLimit1 = 0xf060;
        int simpleThresholdUpperLimit2 = 0xf061;
        int simpleThresholdLowerLimit1 = 0xf062;
        int simpleThresholdLowerLimit2 = 0xf063;
        int changeThesholdRise1 = 0x0000;
        int changeThesholdRise2 = 0x0001;
        int changeThesholdDecline1 = 0x0002;
        int changeThesholdDecline2 = 0x0003;

        HeatStrokeSensor1 result1 = new HeatStrokeSensor1(eventEnableDisable, simpleThresholdUpperLimit1, simpleThresholdUpperLimit2, simpleThresholdLowerLimit1, simpleThresholdLowerLimit2, changeThesholdRise1, changeThesholdRise2, changeThesholdDecline1, changeThesholdDecline2);
        assertEquals(eventEnableDisable, result1.getEventEnableDisable());
        assertEquals(simpleThresholdUpperLimit1, result1.getSimpleThresholdUpperLimit1());
        assertEquals(simpleThresholdUpperLimit2, result1.getSimpleThresholdUpperLimit2());
        assertEquals(simpleThresholdLowerLimit1, result1.getSimpleThresholdLowerLimit1());
        assertEquals(simpleThresholdLowerLimit2, result1.getSimpleThresholdLowerLimit2());
        assertEquals(changeThesholdRise1, result1.getChangeThresholdRise1());
        assertEquals(changeThesholdRise2, result1.getChangeThresholdRise2());
        assertEquals(changeThesholdDecline1, result1.getChangeThresholdDecline1());
        assertEquals(changeThesholdDecline2, result1.getChangeThresholdDecline2());
    }

    @Test
    public void test104() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x02) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x03) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 2
        data[10] = (byte) ((0x04) & 0xff); // Change threshold [rise] 1
        data[11] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[12] = (byte) ((0x05) & 0xff); // Change threshold [rise] 2
        data[13] = (byte) ((0x00) & 0xff); // Change threshold [rise] 2
        data[14] = (byte) ((0x06) & 0xff); // Change threshold [decline] 1
        data[15] = (byte) ((0x00) & 0xff); // Change threshold [decline] 1
        data[16] = (byte) ((0x07) & 0xff); // Change threshold [decline] 2
        data[17] = (byte) ((0x00) & 0xff); // Change threshold [decline] 2
        data[18] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        data[19] = (byte) ((0xff) & 0xff); // Reserve for Future Use
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        HeatStrokeSensor1 result1 = new HeatStrokeSensor1(bluetoothGattCharacteristic);
        HeatStrokeSensor1 result2 = HeatStrokeSensor1.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
