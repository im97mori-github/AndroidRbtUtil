package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_VIBRATION_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryCalculationDataTest {

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data[ 4] = (byte) 0x00; // Discomfort index
        data[ 5] = (byte) 0x00; // Discomfort index
        data[ 6] = (byte) 0x60; // Heat stroke
        data[ 7] = (byte) 0xf0; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 9] = (byte) 0x00; // SI value
        data[10] =(byte) 0x00; // SI value
        data[11] =(byte) 0x00; // PGA
        data[12] =(byte) 0x00; // PGA
        data[13] =(byte) 0x00; // Seismic intensity
        data[14] =(byte) 0x00; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result = new MemoryCalculationData(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
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
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0xff) & 0xff); // Memory index
        data[ 1] = (byte) ((0xff) & 0xff); // Memory index
        data[ 2] = (byte) ((0xff) & 0xff); // Memory index
        data[ 3] = (byte) ((0x7f) & 0xff); // Memory index
        data[ 4] = (byte) 0x10; // Discomfort index
        data[ 5] = (byte) 0x27; // Discomfort index
        data[ 6] = (byte) 0xd4; // Heat stroke
        data[ 7] = (byte) 0x30; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_DURING_VIBRATION_BIT; // Vibration information// Vibration information
        data[ 9] = (byte) 0xff; // SI value
        data[10] =(byte) 0xff; // SI value
        data[11] =(byte) 0xff; // PGA
        data[12] =(byte) 0xff; // PGA
        data[13] =(byte) 0xff; // Seismic intensity
        data[14] =(byte) 0xff; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result = new MemoryCalculationData(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(10000, result.getDiscomfortIndex());
        assertEquals(100.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(12500, result.getHeatStroke());
        assertEquals(125.00d, result.getHeatStrokeDegC(), 0);
        assertEquals(VIBRATION_INFORMATION_DURING_VIBRATION_BIT, result.getVibrationInformation());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0x10; // Discomfort index
        data[ 5] = (byte) 0x27; // Discomfort index
        data[ 6] = (byte) 0xd4; // Heat stroke
        data[ 7] = (byte) 0x30; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information// Vibration information
        data[ 9] = (byte) 0xff; // SI value
        data[10] =(byte) 0xff; // SI value
        data[11] =(byte) 0xff; // PGA
        data[12] =(byte) 0xff; // PGA
        data[13] =(byte) 0xff; // Seismic intensity
        data[14] =(byte) 0xff; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result = new MemoryCalculationData(bluetoothGattCharacteristic);
        assertEquals(-2147483648, result.getMemoryIndex());
        assertTrue(result.isMemoryIndexDataError());
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
    }

    @Test
    public void test004() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0x10; // Discomfort index
        data[ 5] = (byte) 0x27; // Discomfort index
        data[ 6] = (byte) 0xd4; // Heat stroke
        data[ 7] = (byte) 0x30; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information// Vibration information
        data[ 9] = (byte) 0x1f; // SI value
        data[10] =(byte) 0xff; // SI value
        data[11] =(byte) 0x2f; // PGA
        data[12] =(byte) 0xff; // PGA
        data[13] =(byte) 0x3f; // Seismic intensity
        data[14] =(byte) 0xff; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result1 = new MemoryCalculationData(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemoryCalculationData result2 = MemoryCalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getMemoryIndex(), result2.getMemoryIndex());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0x10; // Discomfort index
        data[ 5] = (byte) 0x27; // Discomfort index
        data[ 6] = (byte) 0xd4; // Heat stroke
        data[ 7] = (byte) 0x30; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information// Vibration information
        data[ 9] = (byte) 0x1f; // SI value
        data[10] =(byte) 0xff; // SI value
        data[11] =(byte) 0x2f; // PGA
        data[12] =(byte) 0xff; // PGA
        data[13] =(byte) 0x3f; // Seismic intensity
        data[14] =(byte) 0xff; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result1 = new MemoryCalculationData(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[15];
        data[ 0] = (byte) ((0x00) & 0xff); // Memory index
        data[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data[ 3] = (byte) ((0x80) & 0xff); // Memory index
        data[ 4] = (byte) 0x10; // Discomfort index
        data[ 5] = (byte) 0x27; // Discomfort index
        data[ 6] = (byte) 0xd4; // Heat stroke
        data[ 7] = (byte) 0x30; // Heat stroke
        data[ 8] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information// Vibration information
        data[ 9] = (byte) 0x1f; // SI value
        data[10] =(byte) 0xff; // SI value
        data[11] =(byte) 0x2f; // PGA
        data[12] =(byte) 0xff; // PGA
        data[13] =(byte) 0x3f; // Seismic intensity
        data[14] =(byte) 0xff; // Seismic intensity
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        MemoryCalculationData result1 = new MemoryCalculationData(bluetoothGattCharacteristic);
        MemoryCalculationData result2 = MemoryCalculationData.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
