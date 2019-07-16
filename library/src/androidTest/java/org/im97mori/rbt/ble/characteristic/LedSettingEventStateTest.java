package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

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
        byte[] data = new byte[5];
        data[0] = (byte) ((0x00) & 0xff);
        data[1] = (byte) ((0x00) & 0xff);
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((ALL_EVENT_FLAG_SENSOR) & 0xff);
        data[1] = (byte) ((ALL_EVENT_FLAG_SENSOR >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_PGA_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_ETVOC_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_TEMPERATURE_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
    public void test012() {
        byte[] data = new byte[5];
        data[0] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) & 0xff);
        data[1] = (byte) ((LedSettingEventState.DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT >> 8) & 0xff);
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
}
