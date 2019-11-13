package org.im97mori.rbt.ble.characteristic.cs;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.INSTALLATION_OFFSET_CHARACTERISTIC;

/**
 * 2.4.4 Installation offset (Characteristics UUID: 0x5114)
 */
@SuppressWarnings("WeakerAccess")
public class InstallationOffset implements ByteArrayInterface, Parcelable {

    /**
     * Bit4: Sound noise offset enable
     */
    public static final int INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT = 0b00010000;

    /**
     * Bit3: Barometric pressure offset enable
     */
    public static final int INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT = 0b00001000;

    /**
     * Bit2: Ambient light gain enable
     */
    public static final int INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT = 0b00000100;

    /**
     * Bit1: Relative humidity offset enable
     */
    public static final int INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT = 0b00000010;

    /**
     * Bit0: Temperature offset enable
     */
    public static final int INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT = 0b00000001;

    /**
     * EventThreshold: 0.01 degC
     */
    public static final double TEMPERATURE_INSTALLATION_OFFSET_UNIT = 0.01d;

    /**
     * EventThreshold: 0.01 %RH
     */
    public static final double RELATIVE_HUMIDITY_INSTALLATION_OFFSET_UNIT = 0.01d;

    /**
     * EventThreshold: 0.001
     */
    public static final double AMBIENT_LIGHT_INSTALLATION_GAIN_UNIT = 0.001d;

    /**
     * EventThreshold: 0.001 hPa
     */
    public static final double BAROMETRIC_PRESSURE_INSTALLATION_OFFSET_UNIT = 0.001d;

    /**
     * EventThreshold: 0.01 dB
     */
    public static final double SOUND_NOISE_INSTALLATION_OFFSET_UNIT = 0.01d;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<InstallationOffset> CREATOR = new ByteArrayCreater<InstallationOffset>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public InstallationOffset createFromParcel(@NonNull Parcel in) {
            return new InstallationOffset(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public InstallationOffset[] newArray(int size) {
            return new InstallationOffset[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public InstallationOffset createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(INSTALLATION_OFFSET_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new InstallationOffset(bluetoothGattCharacteristic);
        }

    };

    /**
     * Installation offset enable/disable
     */
    private final int mInstallationOffsetEnableDisable;

    /**
     * Temperature installation offset
     */
    private final int mTemperatureInstallationOffset;

    /**
     * Relative humidity installation offset
     */
    private final int mRelativeHumidityInstallationOffset;

    /**
     * Ambient light installation gain
     */
    private final int mAmbientLightInstallationGain;

    /**
     * Barometric pressure installation offset
     */
    private final int mBarometricPressureInstallationOffset;

    /**
     * Sound noise installation offset
     */
    private final int mSoundNoiseInstallationOffset;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5114
     */
    public InstallationOffset(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mInstallationOffsetEnableDisable = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mTemperatureInstallationOffset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 1);
        mRelativeHumidityInstallationOffset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 3);
        mAmbientLightInstallationGain = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 5);
        mBarometricPressureInstallationOffset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT32, 7);
        mSoundNoiseInstallationOffset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 11);
    }

    /**
     * Constructor from values
     *
     * @param installationOffsetEnableDisable      combination of {@link #INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT}
     *                                             {@link #INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT}
     *                                             {@link #INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT}
     *                                             {@link #INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT}
     *                                             {@link #INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT}
     * @param temperatureInstallationOffset        Temperature installation offset
     * @param relativeHumidityInstallationOffset   Relative humidity installation offset
     * @param ambientLightInstallationGain         Barometric pressure installation offset
     * @param barometricPressureInstallationOffset Barometric pressure installation offset
     * @param soundnoiseInstallationOffset         Sound noise installation offset
     */
    public InstallationOffset(int installationOffsetEnableDisable, int temperatureInstallationOffset, int relativeHumidityInstallationOffset, int ambientLightInstallationGain, int barometricPressureInstallationOffset, int soundnoiseInstallationOffset) {
        mInstallationOffsetEnableDisable = installationOffsetEnableDisable;
        mTemperatureInstallationOffset = temperatureInstallationOffset;
        mRelativeHumidityInstallationOffset = relativeHumidityInstallationOffset;
        mAmbientLightInstallationGain = ambientLightInstallationGain;
        mBarometricPressureInstallationOffset = barometricPressureInstallationOffset;
        mSoundNoiseInstallationOffset = soundnoiseInstallationOffset;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private InstallationOffset(@NonNull Parcel in) {
        mInstallationOffsetEnableDisable = in.readInt();
        mTemperatureInstallationOffset = in.readInt();
        mRelativeHumidityInstallationOffset = in.readInt();
        mAmbientLightInstallationGain = in.readInt();
        mBarometricPressureInstallationOffset = in.readInt();
        mSoundNoiseInstallationOffset = in.readInt();
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
        dest.writeInt(mInstallationOffsetEnableDisable);
        dest.writeInt(mTemperatureInstallationOffset);
        dest.writeInt(mRelativeHumidityInstallationOffset);
        dest.writeInt(mAmbientLightInstallationGain);
        dest.writeInt(mBarometricPressureInstallationOffset);
        dest.writeInt(mSoundNoiseInstallationOffset);
    }

    /**
     * @return Installation offset enable/disable
     */
    public int getInstallationOffsetEnableDisable() {
        return mInstallationOffsetEnableDisable;
    }

    /**
     * check Sound noise offset enable
     *
     * @return {@code true}:Sound noise offset enable bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseOffsetEnabled() {
        return (mInstallationOffsetEnableDisable & INSTALLATION_OFFSET_SOUND_NOISE_OFFSET_BIT) != 0;
    }

    /**
     * check Barometric pressure offset enable
     *
     * @return {@code true}:Barometric pressure offset enable bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureOffsetEnabled() {
        return (mInstallationOffsetEnableDisable & INSTALLATION_OFFSET_BAROMETRIC_PRESSURE_OFFSET_BIT) != 0;
    }

    /**
     * check Ambient light gain enable
     *
     * @return {@code true}:Ambient light gain enable bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightOffsetEnabled() {
        return (mInstallationOffsetEnableDisable & INSTALLATION_OFFSET_AMBIENT_LIGHT_GAIN_BIT) != 0;
    }

    /**
     * check Relative humidity offset enable
     *
     * @return {@code true}:Relative humidity offset enable bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityOffsetEnabled() {
        return (mInstallationOffsetEnableDisable & INSTALLATION_OFFSET_RELATIVE_HUMIDITY_OFFSET_BIT) != 0;
    }

    /**
     * check Temperature offset enable
     *
     * @return {@code true}:Temperature offset enable bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureOffsetEnabled() {
        return (mInstallationOffsetEnableDisable & INSTALLATION_OFFSET_TEMPERATURE_OFFSET_BIT) != 0;
    }

    /**
     * @return Temperature installation offset
     */
    public int getTemperatureInstallationOffset() {
        return mTemperatureInstallationOffset;
    }

    /**
     * @return Temperature installation offset(degC)
     */
    public double getTemperatureInstallationOffsetDegC() {
        return mTemperatureInstallationOffset * TEMPERATURE_INSTALLATION_OFFSET_UNIT;
    }

    /**
     * @return Relative humidity installation offset
     */
    public int getRelativeHumidityInstallationOffset() {
        return mRelativeHumidityInstallationOffset;
    }

    /**
     * @return Relative humidity installation offset(%RH)
     */
    public double getRelativeHumidityInstallationOffsetRh() {
        return mRelativeHumidityInstallationOffset * RELATIVE_HUMIDITY_INSTALLATION_OFFSET_UNIT;
    }

    /**
     * @return Ambient light installation gain
     */
    public int getAmbientLightInstallationGain() {
        return mAmbientLightInstallationGain;
    }

    /**
     * @return Ambient light installation gain(unit 0.001)
     */
    public double getAmbientLightInstallationGainWithUnit() {
        return mAmbientLightInstallationGain * AMBIENT_LIGHT_INSTALLATION_GAIN_UNIT;
    }

    /**
     * @return Barometric pressure installation offset
     */
    public int getBarometricPressureInstallationOffset() {
        return mBarometricPressureInstallationOffset;
    }

    /**
     * @return Barometric pressure installation offset(hPa)
     */
    public double getBarometricPressureInstallationOffsetHpa() {
        return mBarometricPressureInstallationOffset * BAROMETRIC_PRESSURE_INSTALLATION_OFFSET_UNIT;
    }

    /**
     * @return Sound noise installation offset
     */
    public int getSoundNoiseInstallationOffset() {
        return mSoundNoiseInstallationOffset;
    }

    /**
     * @return Sound noise installation offset(dB)
     */
    public double getSoundNoiseInstallationOffsetDb() {
        return mSoundNoiseInstallationOffset * SOUND_NOISE_INSTALLATION_OFFSET_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[13];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mInstallationOffsetEnableDisable);
        byteBuffer.putShort((short) mTemperatureInstallationOffset);
        byteBuffer.putShort((short) mRelativeHumidityInstallationOffset);
        byteBuffer.putShort((short) mAmbientLightInstallationGain);
        byteBuffer.putInt(mBarometricPressureInstallationOffset);
        byteBuffer.putShort((short) mSoundNoiseInstallationOffset);
        return data;
    }

}
