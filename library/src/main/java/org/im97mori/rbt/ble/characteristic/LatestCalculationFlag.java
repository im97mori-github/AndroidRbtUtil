package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.rbt.RbtConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_FLAG_CHARACTERISTIC;

/**
 * 2.2.4 Latest calculation flag (Characteristics UUID: 0x5015)
 */
public class LatestCalculationFlag extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LatestCalculationFlag> CREATOR = new ByteArrayCreater<LatestCalculationFlag>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationFlag createFromParcel(@NonNull Parcel in) {
            return new LatestCalculationFlag(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationFlag[] newArray(int size) {
            return new LatestCalculationFlag[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationFlag createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LATEST_CALCULATION_FLAG_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LatestCalculationFlag(bluetoothGattCharacteristic);
        }

    };

    /**
     * Sequence number
     */
    private final int mSequenceNumber;

    /**
     * Discomfort index flag
     */
    private final int mDiscomfortIndexFlag;

    /**
     * Heat stroke flag
     */
    private final int mHeatStrokeFlag;

    /**
     * SI value flag
     */
    private final int mSiValueFlag;

    /**
     * PGA flag
     */
    private final int mPgaFlag;

    /**
     * Seismic intensity flag
     */
    private final int mSeismicIntensityFlag;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5015
     */
    public LatestCalculationFlag(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mSequenceNumber = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mDiscomfortIndexFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1);
        mHeatStrokeFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 3);
        mSiValueFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 5);
        mPgaFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 6);
        mSeismicIntensityFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 7);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LatestCalculationFlag(@NonNull Parcel in) {
        mSequenceNumber = in.readInt();
        mDiscomfortIndexFlag = in.readInt();
        mHeatStrokeFlag = in.readInt();
        mSiValueFlag = in.readInt();
        mPgaFlag = in.readInt();
        mSeismicIntensityFlag = in.readInt();
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
        dest.writeInt(mSequenceNumber);
        dest.writeInt(mDiscomfortIndexFlag);
        dest.writeInt(mHeatStrokeFlag);
        dest.writeInt(mSiValueFlag);
        dest.writeInt(mPgaFlag);
        dest.writeInt(mSeismicIntensityFlag);
    }

    /**
     * @return Sequence number
     */
    public int getSequenceNumber() {
        return mSequenceNumber;
    }

    /**
     * @return Discomfort index flag
     */
    public int getDiscomfortIndexFlag() {
        return mDiscomfortIndexFlag;
    }

    /**
     * check Discomfort index Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Discomfort index Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Discomfort index Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Discomfort index Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Discomfort index Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Change threshold [rise] 1 reached
     *
     * @return {@code true}:Discomfort index Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Change threshold [rise] 2 reached
     *
     * @return {@code true}:Discomfort index Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Change threshold [decline] 1 reached
     *
     * @return {@code true}:Discomfort index Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Change threshold [decline] 2 reached
     *
     * @return {@code true}:Discomfort index Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Average value threshold [upper] reached
     *
     * @return {@code true}:Discomfort index Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Average value threshold [lower] reached
     *
     * @return {@code true}:Discomfort index Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Discomfort index Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexPeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Discomfort index Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexPeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Interval difference threshold [rise] reached
     *
     * @return {@code true}:Discomfort index Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Interval difference threshold [decline] reached
     *
     * @return {@code true}:Discomfort index Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Base difference threshold [upper] reached
     *
     * @return {@code true}:Discomfort index Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mDiscomfortIndexFlag);
    }

    /**
     * check Discomfort index Base difference threshold [lower] reached
     *
     * @return {@code true}:Discomfort index Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isDiscomfortIndexBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mDiscomfortIndexFlag);
    }

    /**
     * @return Heat stroke flag
     */
    public int getHeatStrokeFlag() {
        return mHeatStrokeFlag;
    }

    /**
     * check Heat stroke Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Heat stroke Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Heat stroke Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Heat stroke Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Heat stroke Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Change threshold [rise] 1 reached
     *
     * @return {@code true}:Heat stroke Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Change threshold [rise] 2 reached
     *
     * @return {@code true}:Heat stroke Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Change threshold [decline] 1 reached
     *
     * @return {@code true}:Heat stroke Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Change threshold [decline] 2 reached
     *
     * @return {@code true}:Heat stroke Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Average value threshold [upper] reached
     *
     * @return {@code true}:Heat stroke Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Average value threshold [lower] reached
     *
     * @return {@code true}:Heat stroke Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Heat stroke Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokePeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Heat stroke Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokePeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Interval difference threshold [rise] reached
     *
     * @return {@code true}:Heat stroke Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Interval difference threshold [decline] reached
     *
     * @return {@code true}:Heat stroke Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Base difference threshold [upper] reached
     *
     * @return {@code true}:Heat stroke Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mHeatStrokeFlag);
    }

    /**
     * check Heat stroke Base difference threshold [lower] reached
     *
     * @return {@code true}:Discomfort index Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isHeatStrokeBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mHeatStrokeFlag);
    }

    /**
     * @return SI value flag
     */
    public int getSiValueFlag() {
        return mSiValueFlag;
    }

    /**
     * check SI value Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:SI value Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSiValueSimpleThresholdUpperLimit1Reached() {
        return isAccelerationSimpleThresholdUpperLimit1Reached(mSiValueFlag);
    }

    /**
     * check SI value Simple threshold [ upper limit] 2 reached
     *
     * @return {@code true}:SI value Simple threshold [ upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSiValueSimpleThresholdUpperLimit2Reached() {
        return isAccelerationSimpleThresholdUpperLimit2Reached(mSiValueFlag);
    }

    /**
     * check SI value Change threshold [rise] 1 reached
     *
     * @return {@code true}:SI value Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSiValueChangeThresholdRise1Reached() {
        return isAccelerationChangeThresholdRise1Reached(mSiValueFlag);
    }

    /**
     * check SI value Change threshold [rise] 2 reached
     *
     * @return {@code true}:SI value Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSiValueChangeThresholdRise2Reached() {
        return isAccelerationChangeThresholdRise2Reached(mSiValueFlag);
    }

    /**
     * @return PGA flag
     */
    public int getPgaFlag() {
        return mPgaFlag;
    }

    /**
     * check PGA Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:PGA Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isPgaSimpleThresholdUpperLimit1Reached() {
        return isAccelerationSimpleThresholdUpperLimit1Reached(mPgaFlag);
    }

    /**
     * check PGA Simple threshold [ upper limit] 2 reached
     *
     * @return {@code true}:PGA Simple threshold [ upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isPgaSimpleThresholdUpperLimit2Reached() {
        return isAccelerationSimpleThresholdUpperLimit2Reached(mPgaFlag);
    }

    /**
     * check PGA Change threshold [rise] 1 reached
     *
     * @return {@code true}:PGA Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isPgaChangeThresholdRise1Reached() {
        return isAccelerationChangeThresholdRise1Reached(mPgaFlag);
    }

    /**
     * check PGA Change threshold [rise] 2 reached
     *
     * @return {@code true}:PGA Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isPgaChangeThresholdRise2Reached() {
        return isAccelerationChangeThresholdRise2Reached(mPgaFlag);
    }

    /**
     * @return Seismic intensity flag
     */
    public int getSeismicIntensityFlag() {
        return mSeismicIntensityFlag;
    }

    /**
     * check Seismic intensity Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Seismic intensity Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSeismicIntensitySimpleThresholdUpperLimit1Reached() {
        return isAccelerationSimpleThresholdUpperLimit1Reached(mSeismicIntensityFlag);
    }

    /**
     * check Seismic intensity Simple threshold [ upper limit] 2 reached
     *
     * @return {@code true}:Seismic intensity Simple threshold [ upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSeismicIntensitySimpleThresholdUpperLimit2Reached() {
        return isAccelerationSimpleThresholdUpperLimit2Reached(mSeismicIntensityFlag);
    }

    /**
     * check Seismic intensity Change threshold [rise] 1 reached
     *
     * @return {@code true}:Seismic intensity Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSeismicIntensityChangeThresholdRise1Reached() {
        return isAccelerationChangeThresholdRise1Reached(mSeismicIntensityFlag);
    }

    /**
     * check Seismic intensity Change threshold [rise] 2 reached
     *
     * @return {@code true}:Seismic intensity Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSeismicIntensityChangeThresholdRise2Reached() {
        return isAccelerationChangeThresholdRise2Reached(mSeismicIntensityFlag);
    }

    /**
     * check Simple threshold [upper limit] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorSimpleThresholdUpperLimit1Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [upper limit] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorSimpleThresholdUpperLimit2Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2) != 0;
    }

    /**
     * check Simple threshold [lower limit] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorSimpleThresholdLowerLimit1Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [lower limit] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorSimpleThresholdLowerLimit2Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2) != 0;
    }

    /**
     * check Change threshold [rise] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorChangeThresholdRise1Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1) != 0;
    }

    /**
     * check Change threshold [rise] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorChangeThresholdRise2Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2) != 0;
    }

    /**
     * check Change threshold [decline] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorChangeThresholdDecline1Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1) != 0;
    }

    /**
     * check Change threshold [decline] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorChangeThresholdDecline2Reached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2) != 0;
    }

    /**
     * check Average value threshold [upper] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorAverageValueThresholdUpperReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Average value threshold [lower] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorAverageValueThresholdLowerReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER) != 0;
    }

    /**
     * check Peak to Peak threshold [upper] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorPeakToPeakThresholdUpperReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Peak to Peak threshold [lower] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorPeakToPeakThresholdLowerReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER) != 0;
    }

    /**
     * check Interval difference threshold [rise] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorIntervalDifferenceThresholdRiseReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE) != 0;
    }

    /**
     * check Interval difference threshold [decline] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorIntervalDifferenceThresholdDeclineReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE) != 0;
    }

    /**
     * check Base difference threshold [upper] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorBaseDifferenceThresholdUpperReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Base difference threshold [lower] reached
     *
     * @param flag check target flag
     * @return {@code true}:target Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorBaseDifferenceThresholdLowerReached(int flag) {
        return (flag & RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) != 0;
    }

    /**
     * check Simple threshold [upper limit] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isAccelerationSimpleThresholdUpperLimit1Reached(int flag) {
        return (flag & RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [upper limit] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Simple threshold [ upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isAccelerationSimpleThresholdUpperLimit2Reached(int flag) {
        return (flag & RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2) != 0;
    }

    /**
     * check Change threshold [rise] 1 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    private boolean isAccelerationChangeThresholdRise1Reached(int flag) {
        return (flag & RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1) != 0;
    }

    /**
     * check Change threshold [rise] 2 reached
     *
     * @param flag check target flag
     * @return {@code true}:target Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    private boolean isAccelerationChangeThresholdRise2Reached(int flag) {
        return (flag & RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2) != 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mSequenceNumber);
        byteBuffer.putShort((short) mDiscomfortIndexFlag);
        byteBuffer.putShort((short) mHeatStrokeFlag);
        byteBuffer.put((byte) mSiValueFlag);
        byteBuffer.put((byte) mPgaFlag);
        byteBuffer.put((byte) mSeismicIntensityFlag);
        return data;
    }

}
