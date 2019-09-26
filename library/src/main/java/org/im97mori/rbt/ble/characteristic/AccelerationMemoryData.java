package org.im97mori.rbt.ble.characteristic;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034)
 *
 * @see AccelerationMemoryData1
 * @see AccelerationMemoryData2
 * @see AccelerationMemoryData3
 * @see AccelerationMemoryData4
 * @see AccelerationMemoryData5
 * @see AccelerationMemoryData6
 * @see AccelerationMemoryData7
 * @see AccelerationMemoryData8
 * @see AccelerationMemoryData9
 * @see AccelerationMemoryData10
 * @see AccelerationMemoryData11
 * @see AccelerationMemoryData12
 * @see AccelerationMemoryData13
 */
public class AccelerationMemoryData implements Parcelable {

    /**
     * @see android.os.Parcelable.Creator
     */
    public static final Creator<AccelerationMemoryData> CREATOR = new Creator<AccelerationMemoryData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryData createFromParcel(@NonNull Parcel in) {
            return new AccelerationMemoryData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryData[] newArray(int size) {
            return new AccelerationMemoryData[size];
        }

    };

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 1 / 13
     */
    private AccelerationMemoryData1 mAccelerationMemoryData1;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 2 / 13
     */
    private AccelerationMemoryData2 mAccelerationMemoryData2;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 3 / 13
     */
    private AccelerationMemoryData3 mAccelerationMemoryData3;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 4 / 13
     */
    private AccelerationMemoryData4 mAccelerationMemoryData4;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 5 / 13
     */
    private AccelerationMemoryData5 mAccelerationMemoryData5;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 6 / 13
     */
    private AccelerationMemoryData6 mAccelerationMemoryData6;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 7 / 13
     */
    private AccelerationMemoryData7 mAccelerationMemoryData7;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 8 / 13
     */
    private AccelerationMemoryData8 mAccelerationMemoryData8;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 9 / 13
     */
    private AccelerationMemoryData9 mAccelerationMemoryData9;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 10 / 13
     */
    private AccelerationMemoryData10 mAccelerationMemoryData10;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 11 / 13
     */
    private AccelerationMemoryData11 mAccelerationMemoryData11;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 12 / 13
     */
    private AccelerationMemoryData12 mAccelerationMemoryData12;

    /**
     * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 13 / 13
     */
    private AccelerationMemoryData13 mAccelerationMemoryData13;

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 1 / 13
     */
    @Nullable
    public AccelerationMemoryData1 getAccelerationMemoryData1() {
        return mAccelerationMemoryData1;
    }

    /**
     * Constructor
     */
    public AccelerationMemoryData() {
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryData(@NonNull Parcel in) {
        mAccelerationMemoryData1 = in.readParcelable(AccelerationMemoryData1.class.getClassLoader());
        mAccelerationMemoryData2 = in.readParcelable(AccelerationMemoryData2.class.getClassLoader());
        mAccelerationMemoryData3 = in.readParcelable(AccelerationMemoryData3.class.getClassLoader());
        mAccelerationMemoryData4 = in.readParcelable(AccelerationMemoryData4.class.getClassLoader());
        mAccelerationMemoryData5 = in.readParcelable(AccelerationMemoryData5.class.getClassLoader());
        mAccelerationMemoryData6 = in.readParcelable(AccelerationMemoryData6.class.getClassLoader());
        mAccelerationMemoryData7 = in.readParcelable(AccelerationMemoryData7.class.getClassLoader());
        mAccelerationMemoryData8 = in.readParcelable(AccelerationMemoryData8.class.getClassLoader());
        mAccelerationMemoryData9 = in.readParcelable(AccelerationMemoryData9.class.getClassLoader());
        mAccelerationMemoryData10 = in.readParcelable(AccelerationMemoryData10.class.getClassLoader());
        mAccelerationMemoryData11 = in.readParcelable(AccelerationMemoryData11.class.getClassLoader());
        mAccelerationMemoryData12 = in.readParcelable(AccelerationMemoryData12.class.getClassLoader());
        mAccelerationMemoryData13 = in.readParcelable(AccelerationMemoryData13.class.getClassLoader());
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
        dest.writeParcelable(mAccelerationMemoryData1, flags);
        dest.writeParcelable(mAccelerationMemoryData2, flags);
        dest.writeParcelable(mAccelerationMemoryData3, flags);
        dest.writeParcelable(mAccelerationMemoryData4, flags);
        dest.writeParcelable(mAccelerationMemoryData5, flags);
        dest.writeParcelable(mAccelerationMemoryData6, flags);
        dest.writeParcelable(mAccelerationMemoryData7, flags);
        dest.writeParcelable(mAccelerationMemoryData8, flags);
        dest.writeParcelable(mAccelerationMemoryData9, flags);
        dest.writeParcelable(mAccelerationMemoryData10, flags);
        dest.writeParcelable(mAccelerationMemoryData11, flags);
        dest.writeParcelable(mAccelerationMemoryData12, flags);
        dest.writeParcelable(mAccelerationMemoryData13, flags);
    }

    /**
     * @param accelerationMemoryData1 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 1 / 13
     */
    public void setAccelerationMemoryData1(@NonNull AccelerationMemoryData1 accelerationMemoryData1) {
        this.mAccelerationMemoryData1 = accelerationMemoryData1;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 2 / 13
     */
    @Nullable
    public AccelerationMemoryData2 getAccelerationMemoryData2() {
        return mAccelerationMemoryData2;
    }

    /**
     * @param accelerationMemoryData2 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 2 / 13
     */
    public void setAccelerationMemoryData2(@NonNull AccelerationMemoryData2 accelerationMemoryData2) {
        this.mAccelerationMemoryData2 = accelerationMemoryData2;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 3 / 13
     */
    @Nullable
    public AccelerationMemoryData3 getAccelerationMemoryData3() {
        return mAccelerationMemoryData3;
    }

    /**
     * @param accelerationMemoryData3 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 3 / 13
     */
    public void setAccelerationMemoryData3(AccelerationMemoryData3 accelerationMemoryData3) {
        this.mAccelerationMemoryData3 = accelerationMemoryData3;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 4 / 13
     */
    @Nullable
    public AccelerationMemoryData4 getAccelerationMemoryData4() {
        return mAccelerationMemoryData4;
    }

    /**
     * @param accelerationMemoryData4 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 4 / 13
     */
    public void setAccelerationMemoryData4(@NonNull AccelerationMemoryData4 accelerationMemoryData4) {
        this.mAccelerationMemoryData4 = accelerationMemoryData4;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 5 / 13
     */
    @Nullable
    public AccelerationMemoryData5 getAccelerationMemoryData5() {
        return mAccelerationMemoryData5;
    }

    /**
     * @param accelerationMemoryData5 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 5 / 13
     */
    public void setAccelerationMemoryData5(AccelerationMemoryData5 accelerationMemoryData5) {
        this.mAccelerationMemoryData5 = accelerationMemoryData5;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 6 / 13
     */
    @Nullable
    public AccelerationMemoryData6 getAccelerationMemoryData6() {
        return mAccelerationMemoryData6;
    }

    /**
     * @param accelerationMemoryData6 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 6 / 13
     */
    public void setAccelerationMemoryData6(@NonNull AccelerationMemoryData6 accelerationMemoryData6) {
        this.mAccelerationMemoryData6 = accelerationMemoryData6;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 7 / 13
     */
    @Nullable
    public AccelerationMemoryData7 getAccelerationMemoryData7() {
        return mAccelerationMemoryData7;
    }

    /**
     * @param accelerationMemoryData7 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 7 / 13
     */
    public void setAccelerationMemoryData7(@NonNull AccelerationMemoryData7 accelerationMemoryData7) {
        this.mAccelerationMemoryData7 = accelerationMemoryData7;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 8 / 13
     */
    @Nullable
    public AccelerationMemoryData8 getAccelerationMemoryData8() {
        return mAccelerationMemoryData8;
    }

    /**
     * @param accelerationMemoryData8 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 8 / 13
     */
    public void setAccelerationMemoryData8(@NonNull AccelerationMemoryData8 accelerationMemoryData8) {
        this.mAccelerationMemoryData8 = accelerationMemoryData8;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 9 / 13
     */
    @Nullable
    public AccelerationMemoryData9 getAccelerationMemoryData9() {
        return mAccelerationMemoryData9;
    }

    /**
     * @param accelerationMemoryData9 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 9 / 13
     */
    public void setAccelerationMemoryData9(@NonNull AccelerationMemoryData9 accelerationMemoryData9) {
        this.mAccelerationMemoryData9 = accelerationMemoryData9;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 10 / 13
     */
    @Nullable
    public AccelerationMemoryData10 getAccelerationMemoryData10() {
        return mAccelerationMemoryData10;
    }

    /**
     * @param accelerationMemoryData10 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 10 / 13
     */
    public void setAccelerationMemoryData10(@NonNull AccelerationMemoryData10 accelerationMemoryData10) {
        this.mAccelerationMemoryData10 = accelerationMemoryData10;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 11 / 13
     */
    @Nullable
    public AccelerationMemoryData11 getAccelerationMemoryData11() {
        return mAccelerationMemoryData11;
    }

    /**
     * @param accelerationMemoryData11 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 11 / 13
     */
    public void setAccelerationMemoryData11(@NonNull AccelerationMemoryData11 accelerationMemoryData11) {
        this.mAccelerationMemoryData11 = accelerationMemoryData11;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 12 / 13
     */
    @Nullable
    public AccelerationMemoryData12 getAccelerationMemoryData12() {
        return mAccelerationMemoryData12;
    }

    /**
     * @param accelerationMemoryData12 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 12 / 13
     */
    public void setAccelerationMemoryData12(@NonNull AccelerationMemoryData12 accelerationMemoryData12) {
        this.mAccelerationMemoryData12 = accelerationMemoryData12;
    }

    /**
     * @return 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 13 / 13
     */
    @Nullable
    public AccelerationMemoryData13 getAccelerationMemoryData13() {
        return mAccelerationMemoryData13;
    }

    /**
     * @param accelerationMemoryData13 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 13 / 13
     */
    public void setAccelerationMemoryData13(@NonNull AccelerationMemoryData13 accelerationMemoryData13) {
        this.mAccelerationMemoryData13 = accelerationMemoryData13;
    }

}
