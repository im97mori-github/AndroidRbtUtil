package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PgaAccelerationTest extends AbstractEventEnableDisableTest {

    @Test
    public void test001() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((0x00) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x00) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x00) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(0, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertEquals(0, result.getSimpleThresholdUpperLimit1());
        assertEquals(0d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(0, result.getSimpleThresholdUpperLimit2());
        assertEquals(0d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(0, result.getChangeThresholdRise1());
        assertEquals(0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(0, result.getChangeThresholdRise2());
        assertEquals(0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((ALL_EVENT_ENABLE_DISABLE_ACCELERATION) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x10) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x27) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(ALL_EVENT_ENABLE_DISABLE_ACCELERATION, result.getEventEnableDisable());
        assertTrue(result.isSimpleThresholdUpperLimit1Enabled());
        assertTrue(result.isSimpleThresholdUpperLimit2Enabled());
        assertTrue(result.isChangeThresholdRise1Enabled());
        assertTrue(result.isChangeThresholdRise2Enabled());
        assertEquals(65535, result.getSimpleThresholdUpperLimit1());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(65535, result.getSimpleThresholdUpperLimit2());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(1000.0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(1000.0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x10) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x27) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getEventEnableDisable());
        assertTrue(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertEquals(65535, result.getSimpleThresholdUpperLimit1());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(65535, result.getSimpleThresholdUpperLimit2());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(1000.0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(1000.0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x10) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x27) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertTrue(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertEquals(65535, result.getSimpleThresholdUpperLimit1());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(65535, result.getSimpleThresholdUpperLimit2());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(1000.0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(1000.0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test005() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_1) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x10) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x27) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_1, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertTrue(result.isChangeThresholdRise1Enabled());
        assertFalse(result.isChangeThresholdRise2Enabled());
        assertEquals(65535, result.getSimpleThresholdUpperLimit1());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(65535, result.getSimpleThresholdUpperLimit2());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(1000.0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(1000.0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test006() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0xff) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x10) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x27) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x10) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x27) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result = new PgaAcceleration(bluetoothGattCharacteristic);
        assertEquals(RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2, result.getEventEnableDisable());
        assertFalse(result.isSimpleThresholdUpperLimit1Enabled());
        assertFalse(result.isSimpleThresholdUpperLimit2Enabled());
        assertFalse(result.isChangeThresholdRise1Enabled());
        assertTrue(result.isChangeThresholdRise2Enabled());
        assertEquals(65535, result.getSimpleThresholdUpperLimit1());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit1Gal(), 0);
        assertEquals(65535, result.getSimpleThresholdUpperLimit2());
        assertEquals(6553.5d, result.getSimpleThresholdUpperLimit2Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise1());
        assertEquals(1000.0d, result.getChangeThresholdRise1Gal(), 0);
        assertEquals(10000, result.getChangeThresholdRise2());
        assertEquals(1000.0d, result.getChangeThresholdRise2Gal(), 0);
    }

    @Test
    public void test101() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x02) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x03) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x04) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x00) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result1 = new PgaAcceleration(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        PgaAcceleration result2 = PgaAcceleration.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getEventEnableDisable(), result2.getEventEnableDisable());
        assertEquals(result1.getSimpleThresholdUpperLimit1(), result2.getSimpleThresholdUpperLimit1());
        assertEquals(result1.getSimpleThresholdUpperLimit2(), result2.getSimpleThresholdUpperLimit2());
        assertEquals(result1.getChangeThresholdRise1(), result2.getChangeThresholdRise1());
        assertEquals(result1.getChangeThresholdRise2(), result2.getChangeThresholdRise2());
    }

    @Test
    public void test102() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x02) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x03) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x04) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x00) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result1 = new PgaAcceleration(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test103() {
        int eventEnableDisable = RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2;
        int simpleThresholdUpperLimit1 = 0x0000;
        int simpleThresholdUpperLimit2 = 0x0001;
        int changeThresholdRise1 = 0x0002;
        int changeThresholdRise2 = 0x0003;

        PgaAcceleration result1 = new PgaAcceleration(eventEnableDisable, simpleThresholdUpperLimit1, simpleThresholdUpperLimit2, changeThresholdRise1, changeThresholdRise2);
        assertEquals(eventEnableDisable, result1.getEventEnableDisable());
        assertEquals(simpleThresholdUpperLimit1, result1.getSimpleThresholdUpperLimit1());
        assertEquals(simpleThresholdUpperLimit2, result1.getSimpleThresholdUpperLimit2());
        assertEquals(changeThresholdRise1, result1.getChangeThresholdRise1());
        assertEquals(changeThresholdRise2, result1.getChangeThresholdRise2());
    }

    @Test
    public void test104() {
        byte[] data = new byte[9];
        data[ 0] = (byte) ((RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2) & 0xff); // Event enable/disable
        data[ 1] = (byte) ((0x01) & 0xff); // Simple threshold [upper limit] 1
        data[ 2] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 1
        data[ 3] = (byte) ((0x02) & 0xff); // Simple threshold [upper limit] 2
        data[ 4] = (byte) ((0x00) & 0xff); // Simple threshold [upper limit] 2
        data[ 5] = (byte) ((0x03) & 0xff); // Change threshold [rise] 1
        data[ 6] = (byte) ((0x00) & 0xff); // Change threshold [rise] 1
        data[ 7] = (byte) ((0x04) & 0xff); // Change threshold[rise]2
        data[ 8] = (byte) ((0x00) & 0xff); // Change threshold[rise]2

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        PgaAcceleration result1 = new PgaAcceleration(bluetoothGattCharacteristic);
        PgaAcceleration result2 = PgaAcceleration.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
