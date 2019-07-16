package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * 2.3.1 Vibration count (Characteristics UUID: 0x5031)
 */
@SuppressWarnings("WeakerAccess")
public class VibrationCount extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<VibrationCount> CREATOR = new Creator<VibrationCount>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public VibrationCount createFromParcel(Parcel in) {
            return new VibrationCount(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VibrationCount[] newArray(int size) {
            return new VibrationCount[size];
        }

    };

    /**
     * Earthquake count
     */
    private final BigInteger mEarthquakeCount;

    /**
     * Vibration count
     */
    private final BigInteger mVibrationCount;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5031
     */
    public VibrationCount(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mEarthquakeCount = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 4);
        mVibrationCount = createBigInteger(bluetoothGattCharacteristic.getValue(), 4, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private VibrationCount(Parcel in) {
        mEarthquakeCount = (BigInteger) in.readSerializable();
        mVibrationCount = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mEarthquakeCount);
        dest.writeSerializable(mVibrationCount);
    }

    /**
     * @return Earthquake count
     */
    public BigInteger getEarthquakeCount() {
        return mEarthquakeCount;
    }

    /**
     * @return Vibration count
     */
    public BigInteger getVibrationCount() {
        return mVibrationCount;
    }

}
