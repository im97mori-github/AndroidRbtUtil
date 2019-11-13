package org.im97mori.rbt.ble.characteristic.cs;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LedSettingEventStateTest {

    static final int ALL_EVENT_FLAG_SENSOR =
            LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_SI_VALUE_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT
                    | LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT;

    @Test
    public void test001() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((0x00) & 0xff);
        data[ 1] = (byte) ((0x00) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(0, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test002() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((ALL_EVENT_FLAG_SENSOR) & 0xff);
        data[ 1] = (byte) ((ALL_EVENT_FLAG_SENSOR >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(ALL_EVENT_FLAG_SENSOR, ledSettingEventState.getDisplayRule());
        assertTrue(ledSettingEventState.isPgaEventEnabled());
        assertTrue(ledSettingEventState.isSiValueEventEnabled());
        assertTrue(ledSettingEventState.isEtvocEventEnabled());
        assertTrue(ledSettingEventState.isSoundNoiseEventEnabled());
        assertTrue(ledSettingEventState.isBarometricPressureEventEnabled());
        assertTrue(ledSettingEventState.isAmbientLightEventEnabled());
        assertTrue(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertTrue(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test003() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertTrue(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test005() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0x00) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertTrue(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test006() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x00) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertTrue(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test007() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0x00) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertTrue(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test008() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertTrue(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test009() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertTrue(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertFalse(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test010() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0xff) & 0xff);
        data[ 3] = (byte) ((0xff) & 0xff);
        data[ 4] = (byte) ((0xff) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState ledSettingEventState = new LedSettingEventState(bluetoothGattCharacteristic);
        assertEquals(LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT, ledSettingEventState.getDisplayRule());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isSiValueEventEnabled());
        assertFalse(ledSettingEventState.isPgaEventEnabled());
        assertFalse(ledSettingEventState.isEtvocEventEnabled());
        assertFalse(ledSettingEventState.isSoundNoiseEventEnabled());
        assertFalse(ledSettingEventState.isBarometricPressureEventEnabled());
        assertFalse(ledSettingEventState.isAmbientLightEventEnabled());
        assertFalse(ledSettingEventState.isRelativeHumidityEventEnabled());
        assertTrue(ledSettingEventState.isTemperatureEventEnabled());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingEventState.getIntensityOfLedBlue());
    }

    @Test
    public void test011() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState result1 = new LedSettingEventState(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LedSettingEventState result2 = LedSettingEventState.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDisplayRule(), result2.getDisplayRule());
        assertEquals(result1.getIntensityOfLedRed(), result2.getIntensityOfLedRed());
        assertEquals(result1.getIntensityOfLedGreen(), result2.getIntensityOfLedGreen());
        assertEquals(result1.getIntensityOfLedBlue(), result2.getIntensityOfLedBlue());
    }

    @Test
    public void test012() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState result1 = new LedSettingEventState(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test013() {
        int displayRule = LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT;
        int intensityOfLedRed = 0x00;
        int intensityOfLedGreen = 0x01;
        int intensityOfLedBlue = 0x02;

        LedSettingEventState result1 = new LedSettingEventState(displayRule, intensityOfLedRed, intensityOfLedGreen, intensityOfLedBlue);
        assertEquals(displayRule, result1.getDisplayRule());
        assertEquals(intensityOfLedRed, result1.getIntensityOfLedRed());
        assertEquals(intensityOfLedGreen, result1.getIntensityOfLedGreen());
        assertEquals(intensityOfLedBlue, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test014() {
        //@formatter:off
        byte[] data = new byte[5];
        data[ 0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[ 1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[ 2] = (byte) ((0x01) & 0xff);
        data[ 3] = (byte) ((0x02) & 0xff);
        data[ 4] = (byte) ((0x03) & 0xff);

        //@formatter:on
        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingEventState result1 = new LedSettingEventState(bluetoothGattCharacteristic);
        LedSettingEventState result2 = LedSettingEventState.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
