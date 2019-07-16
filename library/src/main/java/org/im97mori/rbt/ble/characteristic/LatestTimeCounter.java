package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * 2.5.1 Latest time counter (Characteristics UUID: 0x5201)
 */
@SuppressWarnings("WeakerAccess")
public class LatestTimeCounter extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<LatestTimeCounter> CREATOR = new Creator<LatestTimeCounter>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public LatestTimeCounter createFromParcel(Parcel in) {
            return new LatestTimeCounter(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LatestTimeCounter[] newArray(int size) {
            return new LatestTimeCounter[size];
        }

    };

    /**
     * Time counter
     */
    private final BigInteger mTimeCounter;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5201
     */
    public LatestTimeCounter(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTimeCounter = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 8);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LatestTimeCounter(Parcel in) {
        mTimeCounter = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mTimeCounter);
    }

    /**
     * @return Time counter
     */
    public BigInteger getTimeCounter() {
        return mTimeCounter;
    }

}
