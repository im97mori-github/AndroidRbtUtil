package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.7 Mode change (Characteristics UUID: 0x5117)
 */
@SuppressWarnings("WeakerAccess")
public class ModeChange extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: Normal mode (default)
     */
    public static final int MODE_CHANGE_NORMAL_MODE = 0x00;

    /**
     * 0x01: Acceleration logger mode
     */
    public static final int MODE_CHANGE_ACCELERATION_LOGGER_MODE = 0x01;

    /**
     * @see Creator
     */
    public static final Creator<ModeChange> CREATOR = new Creator<ModeChange>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public ModeChange createFromParcel(Parcel in) {
            return new ModeChange(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ModeChange[] newArray(int size) {
            return new ModeChange[size];
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
    public ModeChange(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mModeChange = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ModeChange(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
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

}
