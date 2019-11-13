package org.im97mori.rbt.ble.characteristic.lds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LatestCalculationDataTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[18];
        data[ 0] = (byte) 0x00; // Sequence number
        data[ 1] = (byte) 0x00; // Discomfort index
        data[ 2] = (byte) 0x00; // Discomfort index
        data[ 3] = (byte) 0x60; // Heat stroke
        data[ 4] = (byte) 0xf0; // Heat stroke
        data[ 5] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 6] = (byte) 0x00; // SI value
        data[ 7] = (byte) 0x00; // SI value
        data[ 8] = (byte) 0x00; // PGA
        data[ 9] = (byte) 0x00; // PGA
        data[10] = (byte) 0x00; // Seismic intensity
        data[11] = (byte) 0x00; // Seismic intensity
        data[12] = (byte) 0xe0; // Acceleration (X-axis)
        data[13] = (byte) 0xb1; // Acceleration (X-axis)
        data[14] = (byte) 0xe0; // Acceleration (Y-axis)
        data[15] = (byte) 0xb1; // Acceleration (Y-axis)
        data[16] = (byte) 0xe0; // Acceleration (Z-axis)
        data[17] = (byte) 0xb1; // Acceleration (Z-axis)
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestCalculationData result = new LatestCalculationData(bluetoothGattCharacteristic);
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getDiscomfortIndex());
        assertEquals(0d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(-4000, result.getHeatStroke());
        assertEquals(-40.00d, result.getHeatStrokeDegC(), 0);
        assertEquals(VIBRATION_INFORMATION_NONE_BIT, result.getVibrationInformation());
        assertEquals(0, result.getSiValue());
        assertEquals(0.0d, result.getSiValueKine(), 0);
        assertEquals(0, result.getPga());
        assertEquals(0.0d, result.getPgaGal(), 0);
        assertEquals(0, result.getSeismicIntensity());
        assertEquals(0.00d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(-20000, result.getAccelerationXAxis());
        assertEquals(-2000.0d, result.getAccelerationXAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationYAxis());
        assertEquals(-2000.0d, result.getAccelerationYAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationZAxis());
        assertEquals(-2000.0d, result.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[18];
        data[ 0] = (byte) 0xff; // Sequence number
        data[ 1] = (byte) 0x10; // Discomfort index
        data[ 2] = (byte) 0x27; // Discomfort index
        data[ 3] = (byte) 0xd4; // Heat stroke
        data[ 4] = (byte) 0x30; // Heat stroke
        data[ 5] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 6] = (byte) 0xff; // SI value
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0xff; // PGA
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0xff; // Seismic intensity
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0x20; // Acceleration (X-axis)
        data[13] = (byte) 0x4e; // Acceleration (X-axis)
        data[14] = (byte) 0x20; // Acceleration (Y-axis)
        data[15] = (byte) 0x4e; // Acceleration (Y-axis)
        data[16] = (byte) 0x20; // Acceleration (Z-axis)
        data[17] = (byte) 0x4e; // Acceleration (Z-axis)
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestCalculationData result = new LatestCalculationData(bluetoothGattCharacteristic);
        assertEquals(255, result.getSequenceNumber());
        assertEquals(10000, result.getDiscomfortIndex());
        assertEquals(100.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(12500, result.getHeatStroke());
        assertEquals(125.00d, result.getHeatStrokeDegC(), 0);
        assertEquals(VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT, result.getVibrationInformation());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(20000, result.getAccelerationXAxis());
        assertEquals(2000.0d, result.getAccelerationXAxisGal(), 0);
        assertEquals(20000, result.getAccelerationYAxis());
        assertEquals(2000.0d, result.getAccelerationYAxisGal(), 0);
        assertEquals(20000, result.getAccelerationZAxis());
        assertEquals(2000.0d, result.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[18];
        data[ 0] = (byte) 0xff; // Sequence number
        data[ 1] = (byte) 0x10; // Discomfort index
        data[ 2] = (byte) 0x27; // Discomfort index
        data[ 3] = (byte) 0xd4; // Heat stroke
        data[ 4] = (byte) 0x30; // Heat stroke
        data[ 5] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 6] = (byte) 0x1f; // SI value
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0x2f; // PGA
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0x3f; // Seismic intensity
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0x21; // Acceleration (X-axis)
        data[13] = (byte) 0x42; // Acceleration (X-axis)
        data[14] = (byte) 0x23; // Acceleration (Y-axis)
        data[15] = (byte) 0x44; // Acceleration (Y-axis)
        data[16] = (byte) 0x25; // Acceleration (Z-axis)
        data[17] = (byte) 0x46; // Acceleration (Z-axis)
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestCalculationData result1 = new LatestCalculationData(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LatestCalculationData result2 = LatestCalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getAccelerationXAxis(), result2.getAccelerationXAxis());
        assertEquals(result1.getAccelerationYAxis(), result2.getAccelerationYAxis());
        assertEquals(result1.getAccelerationZAxis(), result2.getAccelerationZAxis());
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[18];
        data[ 0] = (byte) 0xff; // Sequence number
        data[ 1] = (byte) 0x10; // Discomfort index
        data[ 2] = (byte) 0x27; // Discomfort index
        data[ 3] = (byte) 0xd4; // Heat stroke
        data[ 4] = (byte) 0x30; // Heat stroke
        data[ 5] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 6] = (byte) 0x1f; // SI value
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0x2f; // PGA
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0x3f; // Seismic intensity
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0x21; // Acceleration (X-axis)
        data[13] = (byte) 0x42; // Acceleration (X-axis)
        data[14] = (byte) 0x23; // Acceleration (Y-axis)
        data[15] = (byte) 0x44; // Acceleration (Y-axis)
        data[16] = (byte) 0x25; // Acceleration (Z-axis)
        data[17] = (byte) 0x46; // Acceleration (Z-axis)
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestCalculationData result1 = new LatestCalculationData(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[18];
        data[ 0] = (byte) 0xff; // Sequence number
        data[ 1] = (byte) 0x10; // Discomfort index
        data[ 2] = (byte) 0x27; // Discomfort index
        data[ 3] = (byte) 0xd4; // Heat stroke
        data[ 4] = (byte) 0x30; // Heat stroke
        data[ 5] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 6] = (byte) 0x1f; // SI value
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0x2f; // PGA
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0x3f; // Seismic intensity
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0x21; // Acceleration (X-axis)
        data[13] = (byte) 0x42; // Acceleration (X-axis)
        data[14] = (byte) 0x23; // Acceleration (Y-axis)
        data[15] = (byte) 0x44; // Acceleration (Y-axis)
        data[16] = (byte) 0x25; // Acceleration (Z-axis)
        data[17] = (byte) 0x46; // Acceleration (Z-axis)
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LatestCalculationData result1 = new LatestCalculationData(bluetoothGattCharacteristic);
        LatestCalculationData result2 = LatestCalculationData.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
