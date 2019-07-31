package org.im97mori.rbt.ble.characteristic;

import android.os.Parcel;
import android.os.Parcelable;

public class AccelerationMemoryDataHeader implements Parcelable {


    public static final Creator<AccelerationMemoryDataHeader> CREATOR = new Creator<AccelerationMemoryDataHeader>() {
        @Override
        public AccelerationMemoryDataHeader createFromParcel(Parcel in) {
            return new AccelerationMemoryDataHeader(in);
        }

        @Override
        public AccelerationMemoryDataHeader[] newArray(int size) {
            return new AccelerationMemoryDataHeader[size];
        }
    };


    private AccelerationMemoryDataHeader1 mAccelerationMemoryDataHeader1;
    private AccelerationMemoryDataHeader2 mAccelerationMemoryDataHeader2;
    private AccelerationMemoryDataHeader3 mAccelerationMemoryDataHeader3;
    private AccelerationMemoryDataHeader4 mAccelerationMemoryDataHeader4;

    public AccelerationMemoryDataHeader() {
    }

    private AccelerationMemoryDataHeader(Parcel in) {
        mAccelerationMemoryDataHeader1 = in.readParcelable(AccelerationMemoryDataHeader1.class.getClassLoader());
        mAccelerationMemoryDataHeader2 = in.readParcelable(AccelerationMemoryDataHeader2.class.getClassLoader());
        mAccelerationMemoryDataHeader3 = in.readParcelable(AccelerationMemoryDataHeader3.class.getClassLoader());
        mAccelerationMemoryDataHeader4 = in.readParcelable(AccelerationMemoryDataHeader4.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mAccelerationMemoryDataHeader1, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader2, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader3, flags);
        dest.writeParcelable(mAccelerationMemoryDataHeader4, flags);
    }


    public AccelerationMemoryDataHeader1 getAccelerationMemoryDataHeader1() {
        return mAccelerationMemoryDataHeader1;
    }

    public void setAccelerationMemoryDataHeader1(AccelerationMemoryDataHeader1 mAccelerationMemoryDataHeader1) {
        this.mAccelerationMemoryDataHeader1 = mAccelerationMemoryDataHeader1;
    }

    public AccelerationMemoryDataHeader2 getAccelerationMemoryDataHeader2() {
        return mAccelerationMemoryDataHeader2;
    }

    public void setAccelerationMemoryDataHeader2(AccelerationMemoryDataHeader2 mAccelerationMemoryDataHeader2) {
        this.mAccelerationMemoryDataHeader2 = mAccelerationMemoryDataHeader2;
    }

    public AccelerationMemoryDataHeader3 getAccelerationMemoryDataHeader3() {
        return mAccelerationMemoryDataHeader3;
    }

    public void setAccelerationMemoryDataHeader3(AccelerationMemoryDataHeader3 mAccelerationMemoryDataHeader3) {
        this.mAccelerationMemoryDataHeader3 = mAccelerationMemoryDataHeader3;
    }

    public AccelerationMemoryDataHeader4 getAccelerationMemoryDataHeader4() {
        return mAccelerationMemoryDataHeader4;
    }

    public void setAccelerationMemoryDataHeader4(AccelerationMemoryDataHeader4 mAccelerationMemoryDataHeader4) {
        this.mAccelerationMemoryDataHeader4 = mAccelerationMemoryDataHeader4;
    }
    
}
