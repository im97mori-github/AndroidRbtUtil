package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.1 LED setting [normal state] (Characteristics UUID: 0x5111)
 */
@SuppressWarnings("WeakerAccess")
public class LedSettingNormalState extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x0000: Normally OFF
     */
    public static final int DISPLAY_RULE_NORMALLY_OFF = 0x0000;

    /**
     * 0x0001: Normally ON
     */
    public static final int DISPLAY_RULE_NORMALLY_ON = 0x0001;

    /**
     * 0x0002: Temperature value scales
     */
    public static final int DISPLAY_RULE_TEMPERATURE_VALUE_SCALES = 0x0002;

    /**
     * 0x0003: Relative humidity value scales
     */
    public static final int DISPLAY_RULE_RELATIVE_HUMIDITY_VALUE_SCALES = 0x0003;

    /**
     * 0x0004: Ambient light value scales
     */
    public static final int DISPLAY_RULE_AMBIENT_LIGHT_VALUE_SCALES = 0x0004;

    /**
     * 0x0005: Barometric pressure value scales
     */
    public static final int DISPLAY_RULE_BAROMETRIC_PRESSURE_VALUE_SCALES = 0x0005;

    /**
     * 0x0006: Sound noise value scales
     */
    public static final int DISPLAY_RULE_SOUND_NOISE_VALUE_SCALES = 0x0006;

    /**
     * 0x0007: eTVOC value scales
     */
    public static final int DISPLAY_RULE_ETVOC_VALUE_SCALES = 0x0007;

    /**
     * 0x0008: SI value scales
     */
    public static final int DISPLAY_RULE_SI_VALUE_SCALES = 0x0008;

    /**
     * 0x0009: PGA value scales
     */
    public static final int DISPLAY_RULE_PGA_VALUE_SCALES = 0x0009;

    /**
     * @see Creator
     */
    public static final Creator<LedSettingNormalState> CREATOR = new Creator<LedSettingNormalState>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public LedSettingNormalState createFromParcel(Parcel in) {
            return new LedSettingNormalState(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LedSettingNormalState[] newArray(int size) {
            return new LedSettingNormalState[size];
        }

    };

    /**
     * Display rule (normal state)
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
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5111
     */
    public LedSettingNormalState(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mDisplayRule = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mIntensityOfLedRed = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        mIntensityOfLedGreen = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3);
        mIntensityOfLedBlue = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LedSettingNormalState(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDisplayRule);
        dest.writeInt(mIntensityOfLedRed);
        dest.writeInt(mIntensityOfLedGreen);
        dest.writeInt(mIntensityOfLedBlue);
    }

    /**
     * @return Display rule (normal state)
     */
    public int getDisplayRule() {
        return mDisplayRule;
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

}
