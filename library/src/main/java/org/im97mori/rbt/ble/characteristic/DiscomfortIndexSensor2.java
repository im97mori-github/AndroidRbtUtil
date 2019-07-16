package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;

/**
 * 2.6.2 Event pattern Discomfort index (Characteristics UUID: 0x5220)
 */
@SuppressWarnings("WeakerAccess")
public class DiscomfortIndexSensor2 extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<DiscomfortIndexSensor2> CREATOR = new Creator<DiscomfortIndexSensor2>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public DiscomfortIndexSensor2 createFromParcel(Parcel in) {
            return new DiscomfortIndexSensor2(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public DiscomfortIndexSensor2[] newArray(int size) {
            return new DiscomfortIndexSensor2[size];
        }

    };

    /**
     * Average value threshold [upper]
     */
    private final int mAverageValueThresholdUpper;

    /**
     * Average value threshold [lower]
     */
    private final int mAverageValueThresholdLower;

    /**
     * Peak to Peak threshold [upper]
     */
    private final int mPeakToPeakThresholdUpper;

    /**
     * Peak to Peak threshold [lower]
     */
    private final int mPeakToPeakThresholdLower;

    /**
     * Interval difference threshold [upper]
     */
    private final int mIntervalDifferenceThresholdUpper;

    /**
     * Interval difference threshold [lower]
     */
    private final int mIntervalDifferenceThresholdLower;

    /**
     * Base difference threshold [upper]
     */
    private final int mBaseDifferenceThresholdUpper;

    /**
     * Base difference threshold [lower]
     */
    private final int mBaseDifferenceThresholdLower;

    /**
     * Average value count
     */
    private final int mAverageValueCount;

    /**
     * Peak to Peak count
     */
    private final int mPeakToPeakCount;

    /**
     * Interval difference count
     */
    private final int mIntervalDifferenceCount;

    /**
     * Base difference count
     */
    private final int mBaseDifferenceCount;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5220
     */
    public DiscomfortIndexSensor2(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mAverageValueThresholdUpper = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 0);
        mAverageValueThresholdLower = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 2);
        mPeakToPeakThresholdUpper = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mPeakToPeakThresholdLower = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
        mIntervalDifferenceThresholdUpper = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 8);
        mIntervalDifferenceThresholdLower = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 10);
        mBaseDifferenceThresholdUpper = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mBaseDifferenceThresholdLower = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mAverageValueCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 16);
        mPeakToPeakCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 17);
        mIntervalDifferenceCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 18);
        mBaseDifferenceCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 19);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private DiscomfortIndexSensor2(Parcel in) {
        mAverageValueThresholdUpper = in.readInt();
        mAverageValueThresholdLower = in.readInt();
        mPeakToPeakThresholdUpper = in.readInt();
        mPeakToPeakThresholdLower = in.readInt();
        mIntervalDifferenceThresholdUpper = in.readInt();
        mIntervalDifferenceThresholdLower = in.readInt();
        mBaseDifferenceThresholdUpper = in.readInt();
        mBaseDifferenceThresholdLower = in.readInt();
        mAverageValueCount = in.readInt();
        mPeakToPeakCount = in.readInt();
        mIntervalDifferenceCount = in.readInt();
        mBaseDifferenceCount = in.readInt();
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
        dest.writeInt(mAverageValueThresholdUpper);
        dest.writeInt(mAverageValueThresholdLower);
        dest.writeInt(mPeakToPeakThresholdUpper);
        dest.writeInt(mPeakToPeakThresholdLower);
        dest.writeInt(mIntervalDifferenceThresholdUpper);
        dest.writeInt(mIntervalDifferenceThresholdLower);
        dest.writeInt(mBaseDifferenceThresholdUpper);
        dest.writeInt(mBaseDifferenceThresholdLower);
        dest.writeInt(mAverageValueCount);
        dest.writeInt(mPeakToPeakCount);
        dest.writeInt(mIntervalDifferenceCount);
        dest.writeInt(mBaseDifferenceCount);
    }

    /**
     * @return Average value threshold [upper]
     */
    public int getAverageValueThresholdUpper() {
        return mAverageValueThresholdUpper;
    }

    /**
     * @return Average value threshold [upper](Unit 0.01)
     */
    public double getAverageValueThresholdUpperWithUnit() {
        return mAverageValueThresholdUpper * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Average value threshold [lower]
     */
    public int getAverageValueThresholdLower() {
        return mAverageValueThresholdLower;
    }

    /**
     * @return Average value threshold [lower](Unit 0.01)
     */
    public double getAverageValueThresholdLowerWithUnit() {
        return mAverageValueThresholdLower * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Peak to Peak threshold [upper]
     */
    public int getPeakToPeakThresholdUpper() {
        return mPeakToPeakThresholdUpper;
    }

    /**
     * @return Peak to Peak threshold [upper](Unit 0.01)
     */
    public double getPeakToPeakThresholdUpperWithUnit() {
        return mPeakToPeakThresholdUpper * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Peak to Peak threshold [lower]
     */
    public int getPeakToPeakThresholdLower() {
        return mPeakToPeakThresholdLower;
    }

    /**
     * @return Peak to Peak threshold [lower](Unit 0.01)
     */
    public double getPeakToPeakThresholdLowerWithUnit() {
        return mPeakToPeakThresholdLower * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Interval difference threshold [upper]
     */
    public int getIntervalDifferenceThresholdUpper() {
        return mIntervalDifferenceThresholdUpper;
    }

    /**
     * @return Interval difference threshold [upper](Unit 0.01)
     */
    public double getIntervalDifferenceThresholdUpperWithUnit() {
        return mIntervalDifferenceThresholdUpper * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Interval difference threshold [lower]
     */
    public int getIntervalDifferenceThresholdLower() {
        return mIntervalDifferenceThresholdLower;
    }

    /**
     * @return Interval difference threshold [lower](Unit 0.01)
     */
    public double getIntervalDifferenceThresholdLowerWithUnit() {
        return mIntervalDifferenceThresholdLower * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Base difference threshold [upper]
     */
    public int getBaseDifferenceThresholdUpper() {
        return mBaseDifferenceThresholdUpper;
    }

    /**
     * @return Base difference threshold [upper](Unit 0.01)
     */
    public double getBaseDifferenceThresholdUpperWithUnit() {
        return mBaseDifferenceThresholdUpper * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Base difference threshold [lower]
     */
    public int getBaseDifferenceThresholdLower() {
        return mBaseDifferenceThresholdLower;
    }

    /**
     * @return Base difference threshold [lower](Unit 0.01)
     */
    public double getBaseDifferenceThresholdLowerWithUnit() {
        return mBaseDifferenceThresholdLower * EVENT_THRESHOLD_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Average value count
     */
    public int getAverageValueCount() {
        return mAverageValueCount;
    }

    /**
     * @return Peak to Peak count
     */
    public int getPeakToPeakCount() {
        return mPeakToPeakCount;
    }

    /**
     * @return Interval difference count
     */
    public int getIntervalDifferenceCount() {
        return mIntervalDifferenceCount;
    }

    /**
     * @return Base difference count
     */
    public int getBaseDifferenceCount() {
        return mBaseDifferenceCount;
    }

}
