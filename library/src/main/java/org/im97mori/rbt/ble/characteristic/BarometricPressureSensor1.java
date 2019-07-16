package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.rbt.RbtConstants;

import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1;
import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2;

/**
 * 2.6.1 Event pattern Barometric pressure (Characteristics UUID: 0x5217)
 */
@SuppressWarnings("WeakerAccess")
public class BarometricPressureSensor1 extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<BarometricPressureSensor1> CREATOR = new Creator<BarometricPressureSensor1>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public BarometricPressureSensor1 createFromParcel(Parcel in) {
            return new BarometricPressureSensor1(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BarometricPressureSensor1[] newArray(int size) {
            return new BarometricPressureSensor1[size];
        }

    };

    /**
     * Event enable/disable
     */
    private final int mEventEnableDisable;

    /**
     * Simple threshold [upper limit] 1
     */
    private final int mSimpleThresholdUpperLimit1;

    /**
     * Simple threshold [upper limit] 2
     */
    private final int mSimpleThresholdUpperLimit2;

    /**
     * Simple threshold [lower limit] 1
     */
    private final int mSimpleThresholdLowerLimit1;

    /**
     * Simple threshold [lower limit] 2
     */
    private final int mSimpleThresholdLowerLimit2;

    /**
     * Change threshold [rise] 1
     */
    private final int mChangeThresholdRise1;

    /**
     * Change threshold [rise] 2
     */
    private final int mChangeThresholdRise2;

    /**
     * Change threshold [decline] 1
     */
    private final int mChangeThresholdDecline1;

    /**
     * Change threshold [decline] 2
     */
    private final int mChangeThresholdDecline2;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5217
     */
    public BarometricPressureSensor1(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mEventEnableDisable = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mSimpleThresholdUpperLimit1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 2);
        mSimpleThresholdUpperLimit2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mSimpleThresholdLowerLimit1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
        mSimpleThresholdLowerLimit2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 8);
        mChangeThresholdRise1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 10);
        mChangeThresholdRise2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mChangeThresholdDecline1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mChangeThresholdDecline2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private BarometricPressureSensor1(Parcel in) {
        mEventEnableDisable = in.readInt();
        mSimpleThresholdUpperLimit1 = in.readInt();
        mSimpleThresholdUpperLimit2 = in.readInt();
        mSimpleThresholdLowerLimit1 = in.readInt();
        mSimpleThresholdLowerLimit2 = in.readInt();
        mChangeThresholdRise1 = in.readInt();
        mChangeThresholdRise2 = in.readInt();
        mChangeThresholdDecline1 = in.readInt();
        mChangeThresholdDecline2 = in.readInt();
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
        dest.writeInt(mEventEnableDisable);
        dest.writeInt(mSimpleThresholdUpperLimit1);
        dest.writeInt(mSimpleThresholdUpperLimit2);
        dest.writeInt(mSimpleThresholdLowerLimit1);
        dest.writeInt(mSimpleThresholdLowerLimit2);
        dest.writeInt(mChangeThresholdRise1);
        dest.writeInt(mChangeThresholdRise2);
        dest.writeInt(mChangeThresholdDecline1);
        dest.writeInt(mChangeThresholdDecline2);
    }

    /**
     * @return Event enable/disable
     */
    public int getEventEnableDisable() {
        return mEventEnableDisable;
    }

    /**
     * check Simple threshold [upper limit] 1 enabled
     *
     * @return {@code true}:Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdUpperLimit1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [upper limit] 2 enabled
     *
     * @return {@code true}:Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdUpperLimit2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2) != 0;
    }

    /**
     * check Simple threshold [lower limit] 1 enabled
     *
     * @return {@code true}:Simple threshold [lower limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdLowerLimit1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [lower limit] 2 enabled
     *
     * @return {@code true}:Simple threshold [lower limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdLowerLimit2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2) != 0;
    }

    /**
     * check Change threshold [rise] 1 enabled
     *
     * @return {@code true}:Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdRise1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1) != 0;
    }

    /**
     * check Change threshold [rise] 2 enabled
     *
     * @return {@code true}:Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdRise2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2) != 0;
    }

    /**
     * check Change threshold [decline] 1 enabled
     *
     * @return {@code true}:Change threshold [decline] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdDecline1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1) != 0;
    }

    /**
     * check Change threshold [decline] 2 enabled
     *
     * @return {@code true}:Change threshold [decline] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdDecline2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2) != 0;
    }

    /**
     * check Average value threshold [upper] enabled
     *
     * @return {@code true}:Average value threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAverageValueThresholdUpperEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Average value threshold [lower] enabled
     *
     * @return {@code true}:Average value threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isAverageValueThresholdLowerEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER) != 0;
    }

    /**
     * check Peak to Peak threshold [upper] enabled
     *
     * @return {@code true}:Peak to Peak threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isPeakToPeakThresholdUpperEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Peak to Peak threshold [lower] enabled
     *
     * @return {@code true}:Peak to Peak threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isPeakToPeakThresholdLowerEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER) != 0;
    }

    /**
     * check Interval difference threshold [rise] enabled
     *
     * @return {@code true}:Interval difference threshold [rise] bit is 1, {@code false}:bit is 0;
     */
    public boolean isIntervalDifferenceThresholdRiseEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE) != 0;
    }

    /**
     * check Interval difference threshold [decline] enabled
     *
     * @return {@code true}:Interval difference threshold [decline] bit is 1, {@code false}:bit is 0;
     */
    public boolean isIntervalDifferenceThresholdDeclineEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE) != 0;
    }

    /**
     * check Base difference threshold [upper] enabled
     *
     * @return {@code true}:Base difference threshold [upper] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBaseDifferenceThresholdUpperEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER) != 0;
    }

    /**
     * check Base difference threshold [lower] enabled
     *
     * @return {@code true}:Base difference threshold [lower] bit is 1, {@code false}:bit is 0;
     */
    public boolean isBaseDifferenceThresholdLowerEnabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER) != 0;
    }

    /**
     * @return Simple threshold [upper limit] 1
     */
    public int getSimpleThresholdUpperLimit1() {
        return mSimpleThresholdUpperLimit1;
    }

    /**
     * @return Simple threshold [upper limit] 1(hPa)
     */
    public double getSimpleThresholdUpperLimit1Hpa() {
        return mSimpleThresholdUpperLimit1 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1;
    }

    /**
     * @return Simple threshold [upper limit] 2
     */
    public int getSimpleThresholdUpperLimit2() {
        return mSimpleThresholdUpperLimit2;
    }

    /**
     * @return Simple threshold [upper limit] 2(hPa)
     */
    public double getSimpleThresholdUpperLimit2Hpa() {
        return mSimpleThresholdUpperLimit2 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1;
    }

    /**
     * @return Simple threshold [lower limit] 1
     */
    public int getSimpleThresholdLowerLimit1() {
        return mSimpleThresholdLowerLimit1;
    }

    /**
     * @return Simple threshold [lower limit] 1(hPa)
     */
    public double getSimpleThresholdLowerLimit1Hpa() {
        return mSimpleThresholdLowerLimit1 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1;
    }

    /**
     * @return Simple threshold [lower limit] 2
     */
    public int getSimpleThresholdLowerLimit2() {
        return mSimpleThresholdLowerLimit2;
    }

    /**
     * @return Simple threshold [lower limit] 2(hPa)
     */
    public double getSimpleThresholdLowerLimit2Hpa() {
        return mSimpleThresholdLowerLimit2 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_1;
    }

    /**
     * @return Change threshold [rise] 1
     */
    public int getChangeThresholdRise1() {
        return mChangeThresholdRise1;
    }

    /**
     * @return Change threshold [rise] 1(hPa)
     */
    public double getChangeThresholdRise1Hpa() {
        return mChangeThresholdRise1 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2;
    }

    /**
     * @return Change threshold [rise] 2
     */
    public int getChangeThresholdRise2() {
        return mChangeThresholdRise2;
    }

    /**
     * @return Change threshold [rise] 2(hPa)
     */
    public double getChangeThresholdRise2Hpa() {
        return mChangeThresholdRise2 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2;
    }

    /**
     * @return Change threshold [decline] 1
     */
    public int getChangeThresholdDecline1() {
        return mChangeThresholdDecline1;
    }

    /**
     * @return Change threshold [decline] 1(hPa)
     */
    public double getChangeThresholdDecline1Hpa() {
        return mChangeThresholdDecline1 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2;
    }

    /**
     * @return Change threshold [decline] 2
     */
    public int getChangeThresholdDecline2() {
        return mChangeThresholdDecline2;
    }

    /**
     * @return Change threshold [decline] 2(hPa)
     */
    public double getChangeThresholdDecline2Hpa() {
        return mChangeThresholdDecline2 * EVENT_THRESHOLD_BAROMETRIC_PRESSURE_UNIT_2;
    }

}
