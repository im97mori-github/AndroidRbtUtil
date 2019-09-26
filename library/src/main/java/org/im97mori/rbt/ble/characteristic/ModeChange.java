package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MODE_CHANGE_CHARACTERISTIC;

/**
 * 2.4.7 Mode change (Characteristics UUID: 0x5117)
 */
@SuppressWarnings("WeakerAccess")
public class ModeChange extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Normal mode (default)
     */
    public static final int MODE_CHANGE_NORMAL_MODE = 0x00;

    /**
     * 0x01: Acceleration logger mode
     */
    public static final int MODE_CHANGE_ACCELERATION_LOGGER_MODE = 0x01;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<ModeChange> CREATOR = new ByteArrayCreater<ModeChange>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ModeChange createFromParcel(@NonNull Parcel in) {
            return new ModeChange(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ModeChange[] newArray(int size) {
            return new ModeChange[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ModeChange createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MODE_CHANGE_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new ModeChange(bluetoothGattCharacteristic);
        }

    };

    /**
     * Mode change
     */
    private final int mModeChange;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5117
     */
    public ModeChange(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mModeChange = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from value
     *
     * @param modeChange one of {@link #MODE_CHANGE_NORMAL_MODE}
     *                   {@link #MODE_CHANGE_ACCELERATION_LOGGER_MODE}
     */
    public ModeChange(int modeChange) {
        mModeChange = modeChange;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ModeChange(@NonNull Parcel in) {
        mModeChange = in.readInt();
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
        dest.writeInt(mModeChange);
    }

    /**
     * Getter for Mode change
     *
     * @return Mode change
     */
    public int getModeChange() {
        return mModeChange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[1];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mModeChange);
        return data;
    }

}
