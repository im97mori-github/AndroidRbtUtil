package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LedSettingNormalStateTest {

    @Test
    public void test001() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF >> 8) & 0xff;
        data[2] = (byte) (0x00) & 0xff;
        data[3] = (byte) (0x00) & 0xff;
        data[4] = (byte) (0x00) & 0xff;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF, result1.getDisplayRule());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test002() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test003() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test004() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test005() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test006() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test007() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0x00 & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test008() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test009() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test010() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES, result1.getDisplayRule());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test011() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        LedSettingNormalState result2 = LedSettingNormalState.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDisplayRule(), result2.getDisplayRule());
        assertEquals(result1.getIntensityOfLedRed(), result2.getIntensityOfLedRed());
        assertEquals(result1.getIntensityOfLedGreen(), result2.getIntensityOfLedGreen());
        assertEquals(result1.getIntensityOfLedBlue(), result2.getIntensityOfLedBlue());
    }

    @Test
    public void test012() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x01) & 0xff);
        data[3] = (byte) ((0x02) & 0xff);
        data[4] = (byte) ((0x03) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data, resultData);
    }

    @Test
    public void test013() {
        int displayRule = LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON;
        int intensityOfLedRed = 0x00;
        int intensityOfLedGreen = 0x01;
        int intensityOfLedBlue = 0x02;

        LedSettingNormalState result1 = new LedSettingNormalState(displayRule, intensityOfLedRed, intensityOfLedGreen, intensityOfLedBlue);
        assertEquals(displayRule, result1.getDisplayRule());
        assertEquals(intensityOfLedRed, result1.getIntensityOfLedRed());
        assertEquals(intensityOfLedGreen, result1.getIntensityOfLedGreen());
        assertEquals(intensityOfLedBlue, result1.getIntensityOfLedBlue());
    }

    @Test
    public void test014() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x01) & 0xff);
        data[3] = (byte) ((0x02) & 0xff);
        data[4] = (byte) ((0x03) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState result1 = new LedSettingNormalState(bluetoothGattCharacteristic);
        LedSettingNormalState result2 = LedSettingNormalState.CREATOR.createFromByteArray(data);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
