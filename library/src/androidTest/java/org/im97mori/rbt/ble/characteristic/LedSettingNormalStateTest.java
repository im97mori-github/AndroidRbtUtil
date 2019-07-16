package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LedSettingNormalStateTest {

    @Test
    public void test1() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF >> 8) & 0xff;
        data[2] = (byte) (0x00) & 0xff;
        data[3] = (byte) (0x00) & 0xff;
        data[4] = (byte) (0x00) & 0xff;

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF, ledSettingNormalState.getDisplayRule());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test2() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_NORMALLY_ON, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test3() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_TEMPERATURE_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test4() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test5() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0x00) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test6() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0x00) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test7() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0x00 & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test8() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_ETVOC_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test9() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_SI_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test10() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0xff) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        LedSettingNormalState ledSettingNormalState = new LedSettingNormalState(bluetoothGattCharacteristic);
        assertEquals(LedSettingNormalState.DISPLAY_RULE_PGA_VALUE_SCALES, ledSettingNormalState.getDisplayRule());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedRed());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedGreen());
        assertEquals(0xff & 0xff, ledSettingNormalState.getIntensityOfLedBlue());
    }

    @Test
    public void test11() {
        byte[] data = new byte[5];
        data[0] = (byte) LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES & 0xff;
        data[1] = (byte) (LedSettingNormalState.DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES >> 8) & 0xff;
        data[2] = (byte) ((0x00) & 0xff);
        data[3] = (byte) ((0xff) & 0xff);
        data[4] = (byte) ((0xff) & 0xff);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
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
}
