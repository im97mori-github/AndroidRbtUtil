package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * 2.5.2 Time setting (Characteristics UUID: 0x5202)
 */
@SuppressWarnings("WeakerAccess")
public class TimeSetting extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<TimeSetting> CREATOR = new Creator<TimeSetting>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeSetting createFromParcel(Parcel in) {
            return new TimeSetting(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeSetting[] newArray(int size) {
            return new TimeSetting[size];
        }

    };

    /**
     * Time setting
     */
    private final BigInteger mTimeSetting;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5202
     */
    public TimeSetting(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTimeSetting = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 8);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private TimeSetting(Parcel in) {
        mTimeSetting = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mTimeSetting);
    }

    /**
     * @return Time setting
     */
    public BigInteger getTimeSetting() {
        return mTimeSetting;
    }

}
