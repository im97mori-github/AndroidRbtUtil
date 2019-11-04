package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstallationOffsetTest {

    private static final int ALL_EVENT_FLAG_SENSOR =
            InstallationOffset.INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT
                    | InstallationOffset.INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT
                    | InstallationOffset.INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT
                    | InstallationOffset.INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT
                    | InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT;

    @Test
    public void test001() {
        byte[] data = new byte[13];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0xf0) & 0xff);
        data[2] = (byte) ((0xd8) & 0xff);
        data[3] = (byte) ((0xf0) & 0xff);
        data[4] = (byte) ((0xd8) & 0xff);
        data[5] = (byte) ((0x00) & 0xff);
        data[6] = (byte) ((0x00) & 0xff);
        data[7] = (byte) ((0xc0) & 0xff);
        data[8] = (byte) ((0xbd) & 0xff);
        data[9] = (byte) ((0xf0) & 0xff);
        data[10] = (byte) ((0xff) & 0xff);
        data[11] = (byte) ((0xf0) & 0xff);
        data[12] = (byte) ((0xd8) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(0, installationOffset.getInstallationOffsetEnableDisable());
        assertFalse(installationOffset.isSoundNoiseOffsetEnabled());
        assertFalse(installationOffset.isBarometricPressureOffsetEnabled());
        assertFalse(installationOffset.isAmbientLightOffsetEnabled());
        assertFalse(installationOffset.isRelativeHumidityOffsetEnabled());
        assertFalse(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(-10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(-100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(-10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(-100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(0, installationOffset.getAmbientLightInstallationGain());
        assertEquals(0.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(-1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(-1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(-10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(-100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test002() {
        byte[] data = new byte[13];
        data[0] = (byte) ((ALL_EVENT_FLAG_SENSOR) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(ALL_EVENT_FLAG_SENSOR, installationOffset.getInstallationOffsetEnableDisable());
        assertTrue(installationOffset.isSoundNoiseOffsetEnabled());
        assertTrue(installationOffset.isBarometricPressureOffsetEnabled());
        assertTrue(installationOffset.isAmbientLightOffsetEnabled());
        assertTrue(installationOffset.isRelativeHumidityOffsetEnabled());
        assertTrue(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test003() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(InstallationOffset.INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT, installationOffset.getInstallationOffsetEnableDisable());
        assertTrue(installationOffset.isSoundNoiseOffsetEnabled());
        assertFalse(installationOffset.isBarometricPressureOffsetEnabled());
        assertFalse(installationOffset.isAmbientLightOffsetEnabled());
        assertFalse(installationOffset.isRelativeHumidityOffsetEnabled());
        assertFalse(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test004() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(InstallationOffset.INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT, installationOffset.getInstallationOffsetEnableDisable());
        assertFalse(installationOffset.isSoundNoiseOffsetEnabled());
        assertTrue(installationOffset.isBarometricPressureOffsetEnabled());
        assertFalse(installationOffset.isAmbientLightOffsetEnabled());
        assertFalse(installationOffset.isRelativeHumidityOffsetEnabled());
        assertFalse(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test005() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(InstallationOffset.INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT, installationOffset.getInstallationOffsetEnableDisable());
        assertFalse(installationOffset.isSoundNoiseOffsetEnabled());
        assertFalse(installationOffset.isBarometricPressureOffsetEnabled());
        assertTrue(installationOffset.isAmbientLightOffsetEnabled());
        assertFalse(installationOffset.isRelativeHumidityOffsetEnabled());
        assertFalse(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test006() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(InstallationOffset.INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT, installationOffset.getInstallationOffsetEnableDisable());
        assertFalse(installationOffset.isSoundNoiseOffsetEnabled());
        assertFalse(installationOffset.isBarometricPressureOffsetEnabled());
        assertFalse(installationOffset.isAmbientLightOffsetEnabled());
        assertTrue(installationOffset.isRelativeHumidityOffsetEnabled());
        assertFalse(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test007() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0x10) & 0xff);
        data[4] = (byte) ((0x27) & 0xff);
        data[5] = (byte) ((0x10) & 0xff);
        data[6] = (byte) ((0x27) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x10) & 0xff);
        data[12] = (byte) ((0x27) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset installationOffset = new InstallationOffset(bluetoothGattCharacteristic);
        assertEquals(InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT, installationOffset.getInstallationOffsetEnableDisable());
        assertFalse(installationOffset.isSoundNoiseOffsetEnabled());
        assertFalse(installationOffset.isBarometricPressureOffsetEnabled());
        assertFalse(installationOffset.isAmbientLightOffsetEnabled());
        assertFalse(installationOffset.isRelativeHumidityOffsetEnabled());
        assertTrue(installationOffset.isTemperatureOffsetEnabled());
        assertEquals(10000, installationOffset.getTemperatureInstallationOffset());
        assertEquals(100.00d, installationOffset.getTemperatureInstallationOffsetDegC(), 0);
        assertEquals(10000, installationOffset.getRelativeHumidityInstallationOffset());
        assertEquals(100.00d, installationOffset.getRelativeHumidityInstallationOffsetRh(), 0);
        assertEquals(10000, installationOffset.getAmbientLightInstallationGain());
        assertEquals(10.000d, installationOffset.getAmbientLightInstallationGainWithUnit(), 0);
        assertEquals(1000000, installationOffset.getBarometricPressureInstallationOffset());
        assertEquals(1000.000d, installationOffset.getBarometricPressureInstallationOffsetHpa(), 0);
        assertEquals(10000, installationOffset.getSoundNoiseInstallationOffset());
        assertEquals(100.00, installationOffset.getSoundNoiseInstallationOffsetDb(), 0);
    }

    @Test
    public void test008() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0xf0) & 0xff);
        data[4] = (byte) ((0xd8) & 0xff);
        data[5] = (byte) ((0x09) & 0xff);
        data[6] = (byte) ((0x26) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x25) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset result1 = new InstallationOffset(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        InstallationOffset result2 = InstallationOffset.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getInstallationOffsetEnableDisable(), result2.getInstallationOffsetEnableDisable());
        assertEquals(result1.getTemperatureInstallationOffset(), result2.getTemperatureInstallationOffset());
        assertEquals(result1.getRelativeHumidityInstallationOffset(), result2.getRelativeHumidityInstallationOffset());
        assertEquals(result1.getAmbientLightInstallationGain(), result2.getAmbientLightInstallationGain());
        assertEquals(result1.getBarometricPressureInstallationOffset(), result2.getBarometricPressureInstallationOffset());
        assertEquals(result1.getSoundNoiseInstallationOffset(), result2.getSoundNoiseInstallationOffset());
    }

    @Test
    public void test009() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0xf0) & 0xff);
        data[4] = (byte) ((0xd8) & 0xff);
        data[5] = (byte) ((0x09) & 0xff);
        data[6] = (byte) ((0x26) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x25) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset result1 = new InstallationOffset(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test010() {
        int installationOffsetEnableDisable = InstallationOffset.INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT;
        int temperatureInstallationOffet = 0xd8f0;
        int relativeHumidityInstallationOffset = 0xd8f1;
        int ambientLightInstallationGain = 0x0000;
        int barometricPressureInstallationOffset = 0xfff0bdc0;
        int soundNoiseInstallationOffset = 0xd8f2;

        InstallationOffset result1 = new InstallationOffset(installationOffsetEnableDisable, temperatureInstallationOffet, relativeHumidityInstallationOffset, ambientLightInstallationGain, barometricPressureInstallationOffset, soundNoiseInstallationOffset);
        assertEquals(installationOffsetEnableDisable, result1.getInstallationOffsetEnableDisable());
        assertEquals(temperatureInstallationOffet, result1.getTemperatureInstallationOffset());
        assertEquals(relativeHumidityInstallationOffset, result1.getRelativeHumidityInstallationOffset());
        assertEquals(ambientLightInstallationGain, result1.getAmbientLightInstallationGain());
        assertEquals(barometricPressureInstallationOffset, result1.getBarometricPressureInstallationOffset());
        assertEquals(soundNoiseInstallationOffset, result1.getSoundNoiseInstallationOffset());
    }

    @Test
    public void test011() {
        byte[] data = new byte[13];
        data[0] = (byte) ((InstallationOffset.INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT) & 0xff);
        data[1] = (byte) ((0x10) & 0xff);
        data[2] = (byte) ((0x27) & 0xff);
        data[3] = (byte) ((0xf0) & 0xff);
        data[4] = (byte) ((0xd8) & 0xff);
        data[5] = (byte) ((0x09) & 0xff);
        data[6] = (byte) ((0x26) & 0xff);
        data[7] = (byte) ((0x40) & 0xff);
        data[8] = (byte) ((0x42) & 0xff);
        data[9] = (byte) ((0x0f) & 0xff);
        data[10] = (byte) ((0x00) & 0xff);
        data[11] = (byte) ((0x08) & 0xff);
        data[12] = (byte) ((0x25) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        InstallationOffset result1 = new InstallationOffset(bluetoothGattCharacteristic);
        InstallationOffset result2 = InstallationOffset.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
