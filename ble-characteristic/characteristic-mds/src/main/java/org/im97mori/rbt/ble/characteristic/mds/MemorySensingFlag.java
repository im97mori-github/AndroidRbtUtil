package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;
import org.im97mori.rbt.RbtConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_FLAG_CHARACTERISTIC;

/**
 * 2.1.6 Memory s ensing flag ( Characteristics UUID: 0x500C)
 */
@SuppressWarnings("WeakerAccess")
public class MemorySensingFlag implements ByteArrayInterface, Parcelable {

    /**
     * Memory index data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000_00000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemorySensingFlag> CREATOR = new ByteArrayCreater<MemorySensingFlag>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingFlag createFromParcel(@NonNull Parcel in) {
            return new MemorySensingFlag(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingFlag[] newArray(int size) {
            return new MemorySensingFlag[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingFlag createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_SENSING_FLAG_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemorySensingFlag(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory index
     */
    private final int mMemoryIndex;

    /**
     * Temperature flag
     */
    private final int mTemperatureFlag;

    /**
     * Relative humidity flag
     */
    private final int mRelativeHumidityFlag;

    /**
     * Ambient light flag
     */
    private final int mAmbientLightFlag;

    /**
     * Barometric pressure flag
     */
    private final int mBarometricPressureFlag;

    /**
     * Sound noise flag
     */
    private final int mSoundNoiseFlag;

    /**
     * eTVOC flag
     */
    private final int mEtvocFlag;

    /**
     * eCO2 flag
     */
    private final int mEco2Flag;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x500C
     */
    public MemorySensingFlag(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mTemperatureFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 4);
        mRelativeHumidityFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 6);
        mAmbientLightFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 8);
        mBarometricPressureFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 10);
        mSoundNoiseFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 12);
        mEtvocFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 14);
        mEco2Flag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 16);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemorySensingFlag(@NonNull Parcel in) {
        mMemoryIndex = in.readInt();
        mTemperatureFlag = in.readInt();
        mRelativeHumidityFlag = in.readInt();
        mAmbientLightFlag = in.readInt();
        mBarometricPressureFlag = in.readInt();
        mSoundNoiseFlag = in.readInt();
        mEtvocFlag = in.readInt();
        mEco2Flag = in.readInt();
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
        dest.writeInt(mMemoryIndex);
        dest.writeInt(mTemperatureFlag);
        dest.writeInt(mRelativeHumidityFlag);
        dest.writeInt(mAmbientLightFlag);
        dest.writeInt(mBarometricPressureFlag);
        dest.writeInt(mSoundNoiseFlag);
        dest.writeInt(mEtvocFlag);
        dest.writeInt(mEco2Flag);
    }

    /**
     * @return Memory index
     */
    public int getMemoryIndex() {
        return mMemoryIndex;
    }

    /**
     * @return {@code true}:Memory index is data error, {@code false}:not data error
     */
    public boolean isMemoryIndexDataError() {
        return (mMemoryIndex & DATA_ERROR_BIT) != 0;
    }

    /**
     * @return Temperature flag
     */
    public int getTemperatureFlag() {
        return mTemperatureFlag;
    }

    /**
     * check Temperature Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Temperature Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Temperature Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Temperature Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Temperature Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Change threshold [rise] 1 reached
     *
     * @return {@code true}:Temperature Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Change threshold [rise] 2 reached
     *
     * @return {@code true}:Temperature Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Change threshold [decline] 1 reached
     *
     * @return {@code true}:Temperature Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Change threshold [decline] 2 reached
     *
     * @return {@code true}:Temperature Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mTemperatureFlag);
    }

    /**
     * check Temperature Average value threshold [upper] reached
     *
     * @return {@code true}:Temperature Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mTemperatureFlag);
    }

    /**
     * check Temperature Average value threshold [lower] reached
     *
     * @return {@code true}:Temperature Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mTemperatureFlag);
    }

    /**
     * check Temperature Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Temperature Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperaturePeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mTemperatureFlag);
    }

    /**
     * check Temperature Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Temperature Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperaturePeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mTemperatureFlag);
    }

    /**
     * check Temperature Interval difference threshold [rise] reached
     *
     * @return {@code true}:Temperature Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mTemperatureFlag);
    }

    /**
     * check Temperature Interval difference threshold [decline] reached
     *
     * @return {@code true}:Temperature Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mTemperatureFlag);
    }

    /**
     * check Temperature Base difference threshold [upper] reached
     *
     * @return {@code true}:Temperature Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mTemperatureFlag);
    }

    /**
     * check Temperature Base difference threshold [lower] reached
     *
     * @return {@code true}:Temperature Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mTemperatureFlag);
    }

    /**
     * @return Relative humidity flag
     */
    public int getRelativeHumidityFlag() {
        return mRelativeHumidityFlag;
    }

    /**
     * check Relative humidity Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Relative humidity Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Relative humidity Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Relative humidity Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Relative humidity Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Change threshold [rise] 1 reached
     *
     * @return {@code true}:Relative humidity Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Change threshold [rise] 2 reached
     *
     * @return {@code true}:Relative humidity Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Change threshold [decline] 1 reached
     *
     * @return {@code true}:Relative humidity Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Change threshold [decline] 2 reached
     *
     * @return {@code true}:Relative humidity Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Average value threshold [upper] reached
     *
     * @return {@code true}:Relative humidity Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Average value threshold [lower] reached
     *
     * @return {@code true}:Relative humidity Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Relative humidity Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityPeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Relative humidity Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityPeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Interval difference threshold [rise] reached
     *
     * @return {@code true}:Relative humidity Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Interval difference threshold [decline] reached
     *
     * @return {@code true}:Relative humidity Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Base difference threshold [upper] reached
     *
     * @return {@code true}:Relative humidity Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mRelativeHumidityFlag);
    }

    /**
     * check Relative humidity Base difference threshold [lower] reached
     *
     * @return {@code true}:Relative humidity Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumidityBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mRelativeHumidityFlag);
    }

    /**
     * @return Ambient light flag
     */
    public int getAmbientLightFlag() {
        return mAmbientLightFlag;
    }

    /**
     * check Ambient light Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Ambient light Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Ambient light Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Ambient light Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Ambient light Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Change threshold [rise] 1 reached
     *
     * @return {@code true}:Ambient light Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Change threshold [rise] 2 reached
     *
     * @return {@code true}:Ambient light Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Change threshold [decline] 1 reached
     *
     * @return {@code true}:Ambient light Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Change threshold [decline] 2 reached
     *
     * @return {@code true}:Ambient light Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Average value threshold [upper] reached
     *
     * @return {@code true}:Ambient light Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Average value threshold [lower] reached
     *
     * @return {@code true}:Ambient light Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Ambient light Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightPeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Ambient light Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightPeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Interval difference threshold [rise] reached
     *
     * @return {@code true}:Ambient light Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Interval difference threshold [decline] reached
     *
     * @return {@code true}:Ambient light Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Base difference threshold [upper] reached
     *
     * @return {@code true}:Ambient light Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mAmbientLightFlag);
    }

    /**
     * check Ambient light Base difference threshold [lower] reached
     *
     * @return {@code true}:Ambient light Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mAmbientLightFlag);
    }

    /**
     * @return Barometric pressure flag
     */
    public int getBarometricPressureFlag() {
        return mBarometricPressureFlag;
    }

    /**
     * check Barometric pressure Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Barometric pressure Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Barometric pressure Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Barometric pressure Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Barometric pressure Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Change threshold [rise] 1 reached
     *
     * @return {@code true}:Barometric pressure Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Change threshold [rise] 2 reached
     *
     * @return {@code true}:Barometric pressure Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Change threshold [decline] 1 reached
     *
     * @return {@code true}:Barometric pressure Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Change threshold [decline] 2 reached
     *
     * @return {@code true}:Barometric pressure Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Average value threshold [upper] reached
     *
     * @return {@code true}:Barometric pressure Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Average value threshold [lower] reached
     *
     * @return {@code true}:Barometric pressure Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Barometric pressure Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressurePeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Barometric pressure Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressurePeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Interval difference threshold [rise] reached
     *
     * @return {@code true}:Barometric pressure Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Interval difference threshold [decline] reached
     *
     * @return {@code true}:Barometric pressure Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Base difference threshold [upper] reached
     *
     * @return {@code true}:Barometric pressure Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mBarometricPressureFlag);
    }

    /**
     * check Barometric pressure Base difference threshold [lower] reached
     *
     * @return {@code true}:Barometric pressure Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mBarometricPressureFlag);
    }

    /**
     * @return Sound noise flag
     */
    public int getSoundNoiseFlag() {
        return mSoundNoiseFlag;
    }

    /**
     * check Sound noise Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:Sound noise Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:Sound noise Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:Sound noise Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:Sound noise Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Change threshold [rise] 1 reached
     *
     * @return {@code true}:Sound noise Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Change threshold [rise] 2 reached
     *
     * @return {@code true}:Sound noise Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Change threshold [decline] 1 reached
     *
     * @return {@code true}:Sound noise Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Change threshold [decline] 2 reached
     *
     * @return {@code true}:Sound noise Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Average value threshold [upper] reached
     *
     * @return {@code true}:Sound noise Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Average value threshold [lower] reached
     *
     * @return {@code true}:Sound noise Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:Sound noise Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoisePeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:Sound noise Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoisePeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Interval difference threshold [rise] reached
     *
     * @return {@code true}:Sound noise Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Interval difference threshold [decline] reached
     *
     * @return {@code true}:Sound noise Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Base difference threshold [upper] reached
     *
     * @return {@code true}:Sound noise Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mSoundNoiseFlag);
    }

    /**
     * check Sound noise Base difference threshold [lower] reached
     *
     * @return {@code true}:Sound noise Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mSoundNoiseFlag);
    }

    /**
     * @return eTVOC flag
     */
    public int getEtvocFlag() {
        return mEtvocFlag;
    }

    /**
     * check eTVOC Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:eTVOC Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:eTVOC Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:eTVOC Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:eTVOC Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Change threshold [rise] 1 reached
     *
     * @return {@code true}:eTVOC Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Change threshold [rise] 2 reached
     *
     * @return {@code true}:eTVOC Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Change threshold [decline] 1 reached
     *
     * @return {@code true}:eTVOC Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Change threshold [decline] 2 reached
     *
     * @return {@code true}:eTVOC Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mEtvocFlag);
    }

    /**
     * check eTVOC Average value threshold [upper] reached
     *
     * @return {@code true}:eTVOC Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocAverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mEtvocFlag);
    }

    /**
     * check eTVOC Average value threshold [lower] reached
     *
     * @return {@code true}:eTVOC Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocAverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mEtvocFlag);
    }

    /**
     * check eTVOC Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:eTVOC Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocPeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mEtvocFlag);
    }

    /**
     * check eTVOC Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:eTVOC Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocPeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mEtvocFlag);
    }

    /**
     * check eTVOC Interval difference threshold [rise] reached
     *
     * @return {@code true}:eTVOC Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocIntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mEtvocFlag);
    }

    /**
     * check eTVOC Interval difference threshold [decline] reached
     *
     * @return {@code true}:eTVOC Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocIntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mEtvocFlag);
    }

    /**
     * check eTVOC Base difference threshold [upper] reached
     *
     * @return {@code true}:eTVOC Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocBaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mEtvocFlag);
    }

    /**
     * check eTVOC Base difference threshold [lower] reached
     *
     * @return {@code true}:eTVOC Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocBaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mEtvocFlag);
    }

    /**
     * @return eCO2 flag
     */
    public int getEco2Flag() {
        return mEco2Flag;
    }

    /**
     * check eCO2 Simple threshold [upper limit] 1 reached
     *
     * @return {@code true}:eCO2 Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SimpleThresholdUpperLimit1Reached() {
        return isSensorSimpleThresholdUpperLimit1Reached(mEco2Flag);
    }

    /**
     * check eCO2 Simple threshold [upper limit] 2 reached
     *
     * @return {@code true}:eCO2 Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SimpleThresholdUpperLimit2Reached() {
        return isSensorSimpleThresholdUpperLimit2Reached(mEco2Flag);
    }

    /**
     * check eCO2 Simple threshold [lower limit] 1 reached
     *
     * @return {@code true}:eCO2 Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SimpleThresholdLowerLimit1Reached() {
        return isSensorSimpleThresholdLowerLimit1Reached(mEco2Flag);
    }

    /**
     * check eCO2 Simple threshold [lower limit] 2 reached
     *
     * @return {@code true}:eCO2 Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SimpleThresholdLowerLimit2Reached() {
        return isSensorSimpleThresholdLowerLimit2Reached(mEco2Flag);
    }

    /**
     * check eCO2 Change threshold [rise] 1 reached
     *
     * @return {@code true}:eCO2 Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2ChangeThresholdRise1Reached() {
        return isSensorChangeThresholdRise1Reached(mEco2Flag);
    }

    /**
     * check eCO2 Change threshold [rise] 2 reached
     *
     * @return {@code true}:eCO2 Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2ChangeThresholdRise2Reached() {
        return isSensorChangeThresholdRise2Reached(mEco2Flag);
    }

    /**
     * check eCO2 Change threshold [decline] 1 reached
     *
     * @return {@code true}:eCO2 Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2ChangeThresholdDecline1Reached() {
        return isSensorChangeThresholdDecline1Reached(mEco2Flag);
    }

    /**
     * check eCO2 Change threshold [decline] 2 reached
     *
     * @return {@code true}:eCO2 Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2ChangeThresholdDecline2Reached() {
        return isSensorChangeThresholdDecline2Reached(mEco2Flag);
    }

    /**
     * check eCO2 Average value threshold [upper] reached
     *
     * @return {@code true}:eCO2 Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2AverageValueThresholdUpperReached() {
        return isSensorAverageValueThresholdUpperReached(mEco2Flag);
    }

    /**
     * check eCO2 Average value threshold [lower] reached
     *
     * @return {@code true}:eCO2 Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2AverageValueThresholdLowerReached() {
        return isSensorAverageValueThresholdLowerReached(mEco2Flag);
    }

    /**
     * check eCO2 Peak to Peak threshold [upper] reached
     *
     * @return {@code true}:eCO2 Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2PeakToPeakThresholdUpperReached() {
        return isSensorPeakToPeakThresholdUpperReached(mEco2Flag);
    }

    /**
     * check eCO2 Peak to Peak threshold [lower] reached
     *
     * @return {@code true}:eCO2 Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2PeakToPeakThresholdLowerReached() {
        return isSensorPeakToPeakThresholdLowerReached(mEco2Flag);
    }

    /**
     * check eCO2 Interval difference threshold [rise] reached
     *
     * @return {@code true}:eCO2 Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2IntervalDifferenceThresholdRiseReached() {
        return isSensorIntervalDifferenceThresholdRiseReached(mEco2Flag);
    }

    /**
     * check eCO2 Interval difference threshold [decline] reached
     *
     * @return {@code true}:eCO2 Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2IntervalDifferenceThresholdDeclineReached() {
        return isSensorIntervalDifferenceThresholdDeclineReached(mEco2Flag);
    }

    /**
     * check eCO2 Base difference threshold [upper] reached
     *
     * @return {@code true}:eCO2 Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2BaseDifferenceThresholdUpperReached() {
        return isSensorBaseDifferenceThresholdUpperReached(mEco2Flag);
    }

    /**
     * check eCO2 Base difference threshold [lower] reached
     *
     * @return {@code true}:eCO2 Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2BaseDifferenceThresholdLowerReached() {
        return isSensorBaseDifferenceThresholdLowerReached(mEco2Flag);
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
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[18];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(mMemoryIndex);
        byteBuffer.putShort((short) mTemperatureFlag);
        byteBuffer.putShort((short) mRelativeHumidityFlag);
        byteBuffer.putShort((short) mAmbientLightFlag);
        byteBuffer.putShort((short) mBarometricPressureFlag);
        byteBuffer.putShort((short) mSoundNoiseFlag);
        byteBuffer.putShort((short) mEtvocFlag);
        byteBuffer.putShort((short) mEco2Flag);
        return data;
    }

}
