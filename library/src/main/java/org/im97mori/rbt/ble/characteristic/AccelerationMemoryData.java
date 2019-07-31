package org.im97mori.rbt.ble.characteristic;

import android.os.Parcel;
import android.os.Parcelable;

public class AccelerationMemoryData implements Parcelable {


    public static final Creator<AccelerationMemoryData> CREATOR = new Creator<AccelerationMemoryData>() {
        @Override
        public AccelerationMemoryData createFromParcel(Parcel in) {
            return new AccelerationMemoryData(in);
        }

        @Override
        public AccelerationMemoryData[] newArray(int size) {
            return new AccelerationMemoryData[size];
        }
    };


    private AccelerationMemoryData1 mAccelerationMemoryData1;
    private AccelerationMemoryData2 mAccelerationMemoryData2;
    private AccelerationMemoryData3 mAccelerationMemoryData3;
    private AccelerationMemoryData4 mAccelerationMemoryData4;
    private AccelerationMemoryData5 mAccelerationMemoryData5;
    private AccelerationMemoryData6 mAccelerationMemoryData6;
    private AccelerationMemoryData7 mAccelerationMemoryData7;
    private AccelerationMemoryData8 mAccelerationMemoryData8;
    private AccelerationMemoryData9 mAccelerationMemoryData9;
    private AccelerationMemoryData10 mAccelerationMemoryData10;

    public AccelerationMemoryData1 getAccelerationMemoryData1() {
        return mAccelerationMemoryData1;
    }

    public void setAccelerationMemoryData1(AccelerationMemoryData1 mAccelerationMemoryData1) {
        this.mAccelerationMemoryData1 = mAccelerationMemoryData1;
    }

    public AccelerationMemoryData2 getAccelerationMemoryData2() {
        return mAccelerationMemoryData2;
    }

    public void setAccelerationMemoryData2(AccelerationMemoryData2 mAccelerationMemoryData2) {
        this.mAccelerationMemoryData2 = mAccelerationMemoryData2;
    }

    public AccelerationMemoryData3 getAccelerationMemoryData3() {
        return mAccelerationMemoryData3;
    }

    public void setAccelerationMemoryData3(AccelerationMemoryData3 mAccelerationMemoryData3) {
        this.mAccelerationMemoryData3 = mAccelerationMemoryData3;
    }

    public AccelerationMemoryData4 getAccelerationMemoryData4() {
        return mAccelerationMemoryData4;
    }

    public void setAccelerationMemoryData4(AccelerationMemoryData4 mAccelerationMemoryData4) {
        this.mAccelerationMemoryData4 = mAccelerationMemoryData4;
    }

    public AccelerationMemoryData5 getAccelerationMemoryData5() {
        return mAccelerationMemoryData5;
    }

    public void setAccelerationMemoryData5(AccelerationMemoryData5 mAccelerationMemoryData5) {
        this.mAccelerationMemoryData5 = mAccelerationMemoryData5;
    }

    public AccelerationMemoryData6 getAccelerationMemoryData6() {
        return mAccelerationMemoryData6;
    }

    public void setAccelerationMemoryData6(AccelerationMemoryData6 mAccelerationMemoryData6) {
        this.mAccelerationMemoryData6 = mAccelerationMemoryData6;
    }

    public AccelerationMemoryData7 getAccelerationMemoryData7() {
        return mAccelerationMemoryData7;
    }

    public void setAccelerationMemoryData7(AccelerationMemoryData7 mAccelerationMemoryData7) {
        this.mAccelerationMemoryData7 = mAccelerationMemoryData7;
    }

    public AccelerationMemoryData8 getAccelerationMemoryData8() {
        return mAccelerationMemoryData8;
    }

    public void setAccelerationMemoryData8(AccelerationMemoryData8 mAccelerationMemoryData8) {
        this.mAccelerationMemoryData8 = mAccelerationMemoryData8;
    }

    public AccelerationMemoryData9 getAccelerationMemoryData9() {
        return mAccelerationMemoryData9;
    }

    public void setAccelerationMemoryData9(AccelerationMemoryData9 mAccelerationMemoryData9) {
        this.mAccelerationMemoryData9 = mAccelerationMemoryData9;
    }

    public AccelerationMemoryData10 getAccelerationMemoryData10() {
        return mAccelerationMemoryData10;
    }

    public void setAccelerationMemoryData10(AccelerationMemoryData10 mAccelerationMemoryData10) {
        this.mAccelerationMemoryData10 = mAccelerationMemoryData10;
    }

    public AccelerationMemoryData11 getAccelerationMemoryData11() {
        return mAccelerationMemoryData11;
    }

    public void setAccelerationMemoryData11(AccelerationMemoryData11 mAccelerationMemoryData11) {
        this.mAccelerationMemoryData11 = mAccelerationMemoryData11;
    }

    public AccelerationMemoryData12 getAccelerationMemoryData12() {
        return mAccelerationMemoryData12;
    }

    public void setAccelerationMemoryData12(AccelerationMemoryData12 mAccelerationMemoryData12) {
        this.mAccelerationMemoryData12 = mAccelerationMemoryData12;
    }

    public AccelerationMemoryData13 getAccelerationMemoryData13() {
        return mAccelerationMemoryData13;
    }

    public void setAccelerationMemoryData13(AccelerationMemoryData13 mAccelerationMemoryData13) {
        this.mAccelerationMemoryData13 = mAccelerationMemoryData13;
    }

    private AccelerationMemoryData11 mAccelerationMemoryData11;
    private AccelerationMemoryData12 mAccelerationMemoryData12;
    private AccelerationMemoryData13 mAccelerationMemoryData13;


    public AccelerationMemoryData() {
    }

    private AccelerationMemoryData(Parcel in) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    
}
