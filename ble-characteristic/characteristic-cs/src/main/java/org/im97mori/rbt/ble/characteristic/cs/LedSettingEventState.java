package org.im97mori.rbt.ble.characteristic.cs;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_EVENT_STATE_CHARACTERISTIC;

/**
 * 2.4.2 LED setting [event state] (Characteristics UUID: 0x5112)
 */
@SuppressWarnings("WeakerAccess")
public class LedSettingEventState implements ByteArrayInterface, Parcelable {

    /**
     * Bit7: PGA event
     */
    public static final int DISPLAY_RULE_PGA_EVENT_BIT = 0b00000000_10000000;

    /**
     * Bit6: SI value event
     */
    public static final int DISPLAY_RULE_SI_VALUE_EVENT_BIT = 0b00000000_01000000;

    /**
     * Bit5: eTVOC event
     */
    public static final int DISPLAY_RULE_ETVOC_EVENT_BIT = 0b00000000_00100000;

    /**
     * Bit4: Sound noise event
     */
    public static final int DISPLAY_RULE_SOUND_NOISE_EVENT_BIT = 0b00000000_00010000;

    /**
     * Bit3: Barometric pressure event
     */
    public static final int DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT = 0b00000000_00001000;

    /**
     * Bit2: Ambient light event
     */
    public static final int DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT = 0b00000000_00000100;

    /**
     * Bit1: Relative humidity event
     */
    public static final int DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT = 0b00000000_00000010;

    /**
     * Bit0: Temperature event
     */
    public static final int DISPLAY_RULE_TEMPERATURE_EVENT_BIT = 0b00000000_00000001;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LedSettingEventState> CREATOR = new ByteArrayCreater<LedSettingEventState>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LedSettingEventState createFromParcel(@NonNull Parcel in) {
            return new LedSettingEventState(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LedSettingEventState[] newArray(int size) {
            return new LedSettingEventState[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LedSettingEventState createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LED_SETTING_EVENT_STATE_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LedSettingEventState(bluetoothGattCharacteristic);
        }

    };

    /**
     * Display rule (event state)
     */
    private final int mDisplayRule;

    /**
     * Intensity of LED (Red)
     */
    private final int mIntensityOfLedRed;

    /**
     * Intensity of LED (Green)
     */
    private final int mIntensityOfLedGreen;

    /**
     * Intensity of LED (Blue)
     */
    private final int mIntensityOfLedBlue;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5112
     */
    public LedSettingEventState(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mDisplayRule = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mIntensityOfLedRed = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        mIntensityOfLedGreen = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3);
        mIntensityOfLedBlue = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4);
    }

    /**
     * Constructor from values
     *
     * @param displayRule         combination of {@link #DISPLAY_RULE_PGA_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_SI_VALUE_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_ETVOC_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_SOUND_NOISE_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT}
     *                            {@link #DISPLAY_RULE_TEMPERATURE_EVENT_BIT}
     * @param intensityOfLedRed   Intensity of LED (Red)
     * @param intensityOfLedGreen Intensity of LED (Green)
     * @param intensityOfLedBlue  Intensity of LED (Blue)
     */
    public LedSettingEventState(int displayRule, int intensityOfLedRed, int intensityOfLedGreen, int intensityOfLedBlue) {
        mDisplayRule = displayRule;
        mIntensityOfLedRed = intensityOfLedRed;
        mIntensityOfLedGreen = intensityOfLedGreen;
        mIntensityOfLedBlue = intensityOfLedBlue;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LedSettingEventState(@NonNull Parcel in) {
        mDisplayRule = in.readInt();
        mIntensityOfLedRed = in.readInt();
        mIntensityOfLedGreen = in.readInt();
        mIntensityOfLedBlue = in.readInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(mDisplayRule);
        dest.writeInt(mIntensityOfLedRed);
        dest.writeInt(mIntensityOfLedGreen);
        dest.writeInt(mIntensityOfLedBlue);
    }

    /**
     * @return Display rule (event state)
     */
    public int getDisplayRule() {
        return mDisplayRule;
    }

    /**
     * check PGA event enabled
     *
     * @return {@code true}:PGA event bit is 1, {@code false}:bit is 0;
     */
    public boolean isPgaEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_PGA_EVENT_BIT) != 0;
    }

    /**
     * check SI value event enabled
     *
     * @return {@code true}:SI value event bit is 1, {@code false}:bit is 0;
     */
    public boolean isSiValueEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_SI_VALUE_EVENT_BIT) != 0;
    }

    /**
     * check eTVOC event enabled
     *
     * @return {@code true}:eTVOC event bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_ETVOC_EVENT_BIT) != 0;
    }

    /**
     * check Sound noise event enabled
     *
     * @return {@code true}:Sound noise event bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_SOUND_NOISE_EVENT_BIT) != 0;
    }

    /**
     * check Barometric pressure event enabled
     *
     * @return {@code true}:Barometric pressure event bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_BAROMETRIC_PRESSURE_EVENT_BIT) != 0;
    }

    /**
     * check Ambient light event enabled
     *
     * @return {@code true}:Barometric pressure event bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_AMBIENT_LIGHT_EVENT_BIT) != 0;
    }

    /**
     * check Relative humidity event enabled
     *
     * @return {@code true}:Relative humidity event bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_RELATIVE_HUMIDITY_EVENT_BIT) != 0;
    }

    /**
     * check Temperature event enabled
     *
     * @return {@code true}:Temperature event bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureEventEnabled() {
        return (mDisplayRule & DISPLAY_RULE_TEMPERATURE_EVENT_BIT) != 0;
    }

    /**
     * @return Intensity of LED (Red)
     */
    public int getIntensityOfLedRed() {
        return mIntensityOfLedRed;
    }

    /**
     * @return Intensity of LED (Green)
     */
    public int getIntensityOfLedGreen() {
        return mIntensityOfLedGreen;
    }

    /**
     * @return Intensity of LED (Blue)
     */
    public int getIntensityOfLedBlue() {
        return mIntensityOfLedBlue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[5];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mDisplayRule);
        byteBuffer.put((byte) mIntensityOfLedRed);
        byteBuffer.put((byte) mIntensityOfLedGreen);
        byteBuffer.put((byte) mIntensityOfLedBlue);
        return data;
    }

}
