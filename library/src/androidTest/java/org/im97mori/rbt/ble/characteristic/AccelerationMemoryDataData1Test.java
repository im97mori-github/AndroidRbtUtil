package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccelerationMemoryDataData1Test {

    @Test
    public void test001() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        assertEquals(1, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(1, result.getPageNumber());
        assertEquals(0, result.getSiValue());
        assertEquals(0d, result.getSiValueKine(), 0);
        assertEquals(0, result.getPga());
        assertEquals(0d, result.getPgaGal(), 0);
        assertEquals(0, result.getSeismicIntensity());
        assertEquals(0d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(-20000, result.getMaximumAccelerationXAxis());
        assertEquals(-2000.0d, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(-20000, result.getMaximumAccelerationYAxis());
        assertEquals(-2000.0d, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(-20000, result.getMaximumAccelerationZAxis());
        assertEquals(-2000.0, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(-4000, result.getTemperature());
        assertEquals(-40.00d, result.getTemperatureDegC(), 0);
        assertEquals(0, result.getRelativeHumidity());
        assertEquals(0d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0xff) & 0xff);
        data[ 1] = (byte) ((0x7f) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x01) & 0xff);
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

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        assertEquals(32767, result.getTotalTransferCount());
        assertFalse(result.isTotalTransferCountDataError());
        assertEquals(511, result.getPageNumber());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(20000, result.getMaximumAccelerationXAxis());
        assertEquals(2000.0d, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(20000, result.getMaximumAccelerationYAxis());
        assertEquals(2000.0d, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(20000, result.getMaximumAccelerationZAxis());
        assertEquals(2000.0, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(12500, result.getTemperature());
        assertEquals(125.00d, result.getTemperatureDegC(), 0);
        assertEquals(10000, result.getRelativeHumidity());
        assertEquals(100.00d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[20];
        data[0] = (byte) ((AccelerationMemoryData1.DATA_ERROR_BIT) & 0xff);
        data[1] = (byte) ((AccelerationMemoryData1.DATA_ERROR_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x28) & 0xff);
        data[ 4] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[ 5] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[ 6] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[ 7] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[ 8] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[ 9] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[10] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[11] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[12] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[13] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[14] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE) & 0xff);
        data[15] = (byte) ((AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE >> 8) & 0xff);
        data[16] = (byte) ((0xd4) & 0xff);
        data[17] = (byte) ((0x30) & 0xff);
        data[18] = (byte) ((0x10) & 0xff);
        data[19] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        assertEquals(32768, result.getTotalTransferCount());
        assertTrue(result.isTotalTransferCountDataError());
        assertEquals(10240, result.getPageNumber());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getSiValue());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getSiValueKine(), 0);
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getPga());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getPgaGal(), 0);
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getSeismicIntensity());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationXAxis());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationXAxisGal(), 0);
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationYAxis());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationYAxisGal(), 0);
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationZAxis());
        assertEquals(AccelerationMemoryData1.ACCELERATION_LOGGER_MODE_FIXED_VALUE, result.getMaximumAccelerationZAxisGal(), 0);
        assertEquals(12500, result.getTemperature());
        assertEquals(125.00d, result.getTemperatureDegC(), 0);
        assertEquals(10000, result.getRelativeHumidity());
        assertEquals(100.00d, result.getRelativeHumidityRh(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x02) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x04) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x00) & 0xff);
        data[10] = (byte) ((0x06) & 0xff);
        data[11] = (byte) ((0x00) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0x00) & 0xff);
        data[14] = (byte) ((0x08) & 0xff);
        data[15] = (byte) ((0x00) & 0xff);
        data[16] = (byte) ((0x09) & 0xff);
        data[17] = (byte) ((0x00) & 0xff);
        data[18] = (byte) ((0x0a) & 0xff);
        data[19] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result1 = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        AccelerationMemoryData1 result2 = AccelerationMemoryData1.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTotalTransferCount(), result2.getTotalTransferCount());
        assertEquals(result1.getPageNumber(), result2.getPageNumber());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getMaximumAccelerationXAxis(), result2.getMaximumAccelerationXAxis());
        assertEquals(result1.getMaximumAccelerationYAxis(), result2.getMaximumAccelerationYAxis());
        assertEquals(result1.getMaximumAccelerationZAxis(), result2.getMaximumAccelerationZAxis());
        assertEquals(result1.getTemperature(), result2.getTemperature());
        assertEquals(result1.getRelativeHumidity(), result2.getRelativeHumidity());
    }

    @Test
    public void test005() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x02) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x04) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x00) & 0xff);
        data[10] = (byte) ((0x06) & 0xff);
        data[11] = (byte) ((0x00) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0x00) & 0xff);
        data[14] = (byte) ((0x08) & 0xff);
        data[15] = (byte) ((0x00) & 0xff);
        data[16] = (byte) ((0x09) & 0xff);
        data[17] = (byte) ((0x00) & 0xff);
        data[18] = (byte) ((0x0a) & 0xff);
        data[19] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        byte[] resultData = result.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test006() {
        byte[] data = new byte[20];
        data[ 0] = (byte) ((0x01) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x02) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        data[ 5] = (byte) ((0x00) & 0xff);
        data[ 6] = (byte) ((0x04) & 0xff);
        data[ 7] = (byte) ((0x00) & 0xff);
        data[ 8] = (byte) ((0x05) & 0xff);
        data[ 9] = (byte) ((0x00) & 0xff);
        data[10] = (byte) ((0x06) & 0xff);
        data[11] = (byte) ((0x00) & 0xff);
        data[12] = (byte) ((0x07) & 0xff);
        data[13] = (byte) ((0x00) & 0xff);
        data[14] = (byte) ((0x08) & 0xff);
        data[15] = (byte) ((0x00) & 0xff);
        data[16] = (byte) ((0x09) & 0xff);
        data[17] = (byte) ((0x00) & 0xff);
        data[18] = (byte) ((0x0a) & 0xff);
        data[19] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        AccelerationMemoryData1 result1 = new AccelerationMemoryData1(bluetoothGattCharacteristic);
        AccelerationMemoryData1 result2 = AccelerationMemoryData1.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
