package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.3 LED state [operation] (Characteristics UUID: 0x5113)
 */
@SuppressWarnings("WeakerAccess")
public class LedStateOperation extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: Rainbow (default)
     */
    public static final int START_UP_RAINBOW = 0x00;

    /**
     * 0x01: BLUE
     */
    public static final int START_UP_BLUE = 0x01;

    /**
     * 0x00: NONE (default)
     */
    public static final int ERROR_NONE = 0x00;

    /**
     * 0x01: RED
     */
    public static final int ERROR_RED = 0x01;

    /**
     * 0x00: NONE (default)
     */
    public static final int CONNECTION_NONE = 0x00;

    /**
     * 0x01: GREEN ON 1sec
     */
    public static final int CONNECTION_GREEN = 0x01;

    /**
     * @see Creator
     */
    public static final Creator<LedStateOperation> CREATOR = new Creator<LedStateOperation>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public LedStateOperation createFromParcel(Parcel in) {
            return new LedStateOperation(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LedStateOperation[] newArray(int size) {
            return new LedStateOperation[size];
        }

    };

    /**
     * Start up
     */
    private final int mStartUp;

    /**
     * Error
     */
    private final int mError;

    /**
     * Connection
     */
    private final int mConnection;


    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5113
     */
    public LedStateOperation(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mStartUp = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mConnection = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LedStateOperation(Parcel in) {
        mStartUp = in.readInt();
        mError = in.readInt();
        mConnection = in.readInt();
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
        dest.writeInt(mStartUp);
        dest.writeInt(mError);
        dest.writeInt(mConnection);
    }

    /**
     * @return Start up
     */
    public int getStartUp() {
        return mStartUp;
    }

    /**
     * @return Error
     */
    public int getError() {
        return mError;
    }

    /**
     * @return Connection
     */
    public int getConnection() {
        return mConnection;
    }

}
