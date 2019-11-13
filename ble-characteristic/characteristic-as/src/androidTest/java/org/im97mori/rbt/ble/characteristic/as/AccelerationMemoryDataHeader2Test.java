package org.im97mori.rbt.ble.characteristic.as;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccelerationMemoryDataHeader2Test {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x00) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x00) & 0xff);
        data[ 9] = (byte) ((0x00) & 0xff);
        data[10] = (byte) ((0xe0) & 0xff);
        data[11] = (byte) ((0xb1) & 0xff);
        data[12] = (byte) ((0xe0) & 0xff);
        data[13] = (byte) ((0xb1) & 0xff);
        data[14] = (byte) ((0xe0) & 0xff);
        data[15] = (byte) ((0xb1) & 0xff);
        data[16] = (byte) ((0x60) & 0xff);
        data[17] = (byte) ((0xf0) & 0xff);
        data[18] = (byte) ((0x00) & 0xff);
        data[19] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        assertEquals(1, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE, result.getPageNumber());
        assertEquals(0, result.getSiValue());
        assertEquals(0d, result.getSiValueKine(), 0);
        assertEquals(0, result.getPga());
        assertEquals(0d, result.getPgaGal(), 0);
        assertEquals(0, result.getSeismicIntensity());
        assertEquals(0d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(-20000, result.getMaximuAccelerationXAxis());
        assertEquals(-2000.0d, result.getMaximuAccelerationXAxisGal(), 0);
        assertEquals(-20000, result.getMaximuAccelerationYAxis());
        assertEquals(-2000.0d, result.getMaximuAccelerationYAxisGal(), 0);
        assertEquals(-20000, result.getMaximuAccelerationZAxis());
        assertEquals(-2000.0d, result.getMaximuAccelerationZAxisGal(), 0);
        assertEquals(-4000, result.getTemparature());
        assertEquals(-40.00d, result.getTemparatureDegC(), 0);
        assertEquals(0, result.getRelativeHumidity());
        assertEquals(0d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0x7f) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0x20) & 0xff);
        data[11] = (byte) ((0x4e) & 0xff);
        data[12] = (byte) ((0x20) & 0xff);
        data[13] = (byte) ((0x4e) & 0xff);
        data[14] = (byte) ((0x20) & 0xff);
        data[15] = (byte) ((0x4e) & 0xff);
        data[16] = (byte) ((0xd4) & 0xff);
        data[17] = (byte) ((0x30) & 0xff);
        data[18] = (byte) ((0x10) & 0xff);
        data[19] = (byte) ((0x27) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        assertEquals(32767, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE, result.getPageNumber());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(20000, result.getMaximuAccelerationXAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationXAxisGal(), 0);
        assertEquals(20000, result.getMaximuAccelerationYAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationYAxisGal(), 0);
        assertEquals(20000, result.getMaximuAccelerationZAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationZAxisGal(), 0);
        assertEquals(12500, result.getTemparature());
        assertEquals(125.00d, result.getTemparatureDegC(), 0);
        assertEquals(10000, result.getRelativeHumidity());
        assertEquals(100.00d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader2.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader2.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        data[ 5] = (byte) ((0xff) & 0xff);
        data[ 6] = (byte) ((0xff) & 0xff);
        data[ 7] = (byte) ((0xff) & 0xff);
        data[ 8] = (byte) ((0xff) & 0xff);
        data[ 9] = (byte) ((0xff) & 0xff);
        data[10] = (byte) ((0x20) & 0xff);
        data[11] = (byte) ((0x4e) & 0xff);
        data[12] = (byte) ((0x20) & 0xff);
        data[13] = (byte) ((0x4e) & 0xff);
        data[14] = (byte) ((0x20) & 0xff);
        data[15] = (byte) ((0x4e) & 0xff);
        data[16] = (byte) ((0xd4) & 0xff);
        data[17] = (byte) ((0x30) & 0xff);
        data[18] = (byte) ((0x10) & 0xff);
        data[19] = (byte) ((0x27) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        assertEquals(32768, result.getTotalTransferCount());
        assertTrue(result.isTotalTransferCountDataError());
        assertEquals(AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE, result.getPageNumber());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(20000, result.getMaximuAccelerationXAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationXAxisGal(), 0);
        assertEquals(20000, result.getMaximuAccelerationYAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationYAxisGal(), 0);
        assertEquals(20000, result.getMaximuAccelerationZAxis());
        assertEquals(2000.0d, result.getMaximuAccelerationZAxisGal(), 0);
        assertEquals(12500, result.getTemparature());
        assertEquals(125.00d, result.getTemparatureDegC(), 0);
        assertEquals(10000, result.getRelativeHumidity());
        assertEquals(100.00d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0x01) & 0xff);
        data[ 5] = (byte) ((0x02) & 0xff);
        data[ 6] = (byte) ((0x03) & 0xff);
        data[ 7] = (byte) ((0x04) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x06) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x09) & 0xff);
        data[13] = (byte) ((0x0a) & 0xff);
        data[14] = (byte) ((0x0b) & 0xff);
        data[15] = (byte) ((0x0c) & 0xff);
        data[16] = (byte) ((0x0d) & 0xff);
        data[17] = (byte) ((0x0e) & 0xff);
        data[18] = (byte) ((0x0f) & 0xff);
        data[19] = (byte) ((0x10) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result1 = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryDataHeader2 result2 = AccelerationMemoryDataHeader2.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
        assertEquals(result1.getPageNumber(), result2.getPageNumber());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getMaximuAccelerationXAxis(), result2.getMaximuAccelerationXAxis());
        assertEquals(result1.getMaximuAccelerationYAxis(), result2.getMaximuAccelerationYAxis());
        assertEquals(result1.getMaximuAccelerationZAxis(), result2.getMaximuAccelerationZAxis());
        assertEquals(result1.getTemparature(), result2.getTemparature());
        assertEquals(result1.getRelativeHumidity(), result2.getRelativeHumidity());
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0x01) & 0xff);
        data[ 5] = (byte) ((0x02) & 0xff);
        data[ 6] = (byte) ((0x03) & 0xff);
        data[ 7] = (byte) ((0x04) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x06) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x09) & 0xff);
        data[13] = (byte) ((0x0a) & 0xff);
        data[14] = (byte) ((0x0b) & 0xff);
        data[15] = (byte) ((0x0c) & 0xff);
        data[16] = (byte) ((0x0d) & 0xff);
        data[17] = (byte) ((0x0e) & 0xff);
        data[18] = (byte) ((0x0f) & 0xff);
        data[19] = (byte) ((0x10) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result1 = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[20];
        data[ 0] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT) & 0xff);
        data[ 1] = (byte) ((AccelerationMemoryDataHeader1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE) & 0xff);
        data[ 3] = (byte) ((AccelerationMemoryDataHeader2.PAGE_NUMBER_FIXED_VALUE >> 8) & 0xff);
        data[ 4] = (byte) ((0x01) & 0xff);
        data[ 5] = (byte) ((0x02) & 0xff);
        data[ 6] = (byte) ((0x03) & 0xff);
        data[ 7] = (byte) ((0x04) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x06) & 0xff);
        data[10] = (byte) ((0x07) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x09) & 0xff);
        data[13] = (byte) ((0x0a) & 0xff);
        data[14] = (byte) ((0x0b) & 0xff);
        data[15] = (byte) ((0x0c) & 0xff);
        data[16] = (byte) ((0x0d) & 0xff);
        data[17] = (byte) ((0x0e) & 0xff);
        data[18] = (byte) ((0x0f) & 0xff);
        data[19] = (byte) ((0x10) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryDataHeader2 result1 = new AccelerationMemoryDataHeader2(bluetoothGattCharacteristic);
        AccelerationMemoryDataHeader2 result2 = AccelerationMemoryDataHeader2.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
