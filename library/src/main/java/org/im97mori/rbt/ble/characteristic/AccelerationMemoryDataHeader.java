package org.im97mori.rbt.ble.characteristic;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034)
 *
 * @see AccelerationMemoryDataHeader1
 * @see AccelerationMemoryDataHeader2
 * @see AccelerationMemoryDataHeader3
 * @see AccelerationMemoryDataHeader4
 */
public class AccelerationMemoryDataHeader implements Parcelable {

    /**
     * @see android.os.Parcelable.Creator
     */
    public static final Creator<AccelerationMemoryDataHeader> CREATOR = new Creator<AccelerationMemoryDataHeader>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryDataHeader createFromParcel(@NonNull Parcel in) {
            return new AccelerationMemoryDataHeader(in);
        }

        @Override
        @NonNull
        public AccelerationMemoryDataHeader[] newArray(int size) {
            return new AccelerationMemoryDataHeader[size];
        }
    };

    /**
     * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 1 / 4
     */
    private AccelerationMemoryDataHeader1 mAccelerationMemoryDataHeader1;

    /**
     * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 2 / 4
     */
    private AccelerationMemoryDataHeader2 mAccelerationMemoryDataHeader2;

    /**
     * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 3 / 4
     */
    private AccelerationMemoryDataHeader3 mAccelerationMemoryDataHeader3;

    /**
     * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 4 / 4
     */
    private AccelerationMemoryDataHeader4 mAccelerationMemoryDataHeader4;

    /**
     * Constructor
     */
    public AccelerationMemoryDataHeader() {
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryDataHeader(@NonNull Parcel in) {
        mAccelerationMemoryDataHeader1 = in.readParcelable(AccelerationMemoryDataHeader1.class.getClassLoader());
        mAccelerationMemoryDataHeader2 = in.readParcelable(AccelerationMemoryDataHeader2.class.getClassLoader());
        mAccelerationMemoryDataHeader3 = in.readParcelable(AccelerationMemoryDataHeader3.class.getClassLoader());
        mAccelerationMemoryDataHeader4 = in.readParcelable(AccelerationMemoryDataHeader4.class.getClassLoader());
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
        dest.writeParcelable(mAccelerationMemoryDataHeader1, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader2, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader3, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader4, flags);
    }

    /**
     * @return 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 1 / 4
     */
    @Nullable
    public AccelerationMemoryDataHeader1 getAccelerationMemoryDataHeader1() {
        return mAccelerationMemoryDataHeader1;
    }

    /**
     * @param accelerationMemoryDataHeader1 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 1 / 4
     */
    public void setAccelerationMemoryDataHeader1(@NonNull AccelerationMemoryDataHeader1 accelerationMemoryDataHeader1) {
        this.mAccelerationMemoryDataHeader1 = accelerationMemoryDataHeader1;
    }

    /**
     * @return 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 2 / 4
     */
    @Nullable
    public AccelerationMemoryDataHeader2 getAccelerationMemoryDataHeader2() {
        return mAccelerationMemoryDataHeader2;
    }

    /**
     * @param accelerationMemoryDataHeader2 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 2 / 4
     */
    public void setAccelerationMemoryDataHeader2(@NonNull AccelerationMemoryDataHeader2 accelerationMemoryDataHeader2) {
        this.mAccelerationMemoryDataHeader2 = accelerationMemoryDataHeader2;
    }

    /**
     * @return 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 3 / 4
     */
    @Nullable
    public AccelerationMemoryDataHeader3 getAccelerationMemoryDataHeader3() {
        return mAccelerationMemoryDataHeader3;
    }

    /**
     * @param accelerationMemoryDataHeader3 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 3 / 4
     */
    public void setAccelerationMemoryDataHeader3(@NonNull AccelerationMemoryDataHeader3 accelerationMemoryDataHeader3) {
        this.mAccelerationMemoryDataHeader3 = accelerationMemoryDataHeader3;
    }

    /**
     * @return 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 4 / 4
     */
    @Nullable
    public AccelerationMemoryDataHeader4 getAccelerationMemoryDataHeader4() {
        return mAccelerationMemoryDataHeader4;
    }

    /**
     * @param accelerationMemoryDataHeader4 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 4 / 4
     */
    public void setAccelerationMemoryDataHeader4(@NonNull AccelerationMemoryDataHeader4 accelerationMemoryDataHeader4) {
        this.mAccelerationMemoryDataHeader4 = accelerationMemoryDataHeader4;
    }

}
