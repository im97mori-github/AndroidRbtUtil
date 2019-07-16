package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RelativeHumiditySensor1Test extends AbstractEventEnableDisableTest {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x00) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x00) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x00) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(0, result.getSimpleThresholdUpperLimit1());
        assertEquals(0d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(0, result.getSimpleThresholdUpperLimit2());
        assertEquals(0d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(0, result.getSimpleThresholdLowerLimit1());
        assertEquals(0d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(0, result.getSimpleThresholdLowerLimit2());
        assertEquals(0d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(0, result.getChangeThresholdRise1());
        assertEquals(0d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(0, result.getChangeThresholdRise2());
        assertEquals(0d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(0, result.getChangeThresholdDecline1());
        assertEquals(0d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(0, result.getChangeThresholdDecline2());
        assertEquals(0d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((ALL_EVENT_ENABLE_DISABLE_SENSOR_LSB) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((ALL_EVENT_ENABLE_DISABLE_SENSOR_MSB) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test005() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test006() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test007() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test008() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test009() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test010() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test011() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test012() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test013() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test014() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test015() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test016() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test017() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test018() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Event enable/disable
        data[ 2] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 1
        data[ 4] = (byte) ((0x10) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x27) & 0xff); // Simple threshold [upper limit] 2
        data[ 6] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 1
        data[ 7] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 1
        data[ 8] = (byte) ((0x10) & 0xff); // Simple threshold [lower limit] 2
        data[ 9] = (byte) ((0x27) & 0xff); // Simple threshold [lower limit] 2
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
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
        assertEquals(10000, result.getSimpleThresholdUpperLimit1());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdUpperLimit2());
        assertEquals(100.00d, result.getSimpleThresholdUpperLimit2Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit1());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit1Rh(), 0);
        assertEquals(10000, result.getSimpleThresholdLowerLimit2());
        assertEquals(100.00d, result.getSimpleThresholdLowerLimit2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(100.00d, result.getChangeThresholdRise1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(100.00d, result.getChangeThresholdRise2Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline1());
        assertEquals(100.00d, result.getChangeThresholdDecline1Rh(), 0);
        assertEquals(10000, result.getChangeThresholdDecline2());
        assertEquals(100.00d, result.getChangeThresholdDecline2Rh(), 0);
    }

    @Test
    public void test101() {
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        RelativeHumiditySensor1 result1 = new RelativeHumiditySensor1(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        RelativeHumiditySensor1 result2 = RelativeHumiditySensor1.CREATOR.createFromParcel(parcel);

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
}
