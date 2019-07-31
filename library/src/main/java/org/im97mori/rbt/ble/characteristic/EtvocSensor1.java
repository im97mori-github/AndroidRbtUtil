package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.rbt.RbtConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ETVOC_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_ETVOC_UNIT;

/**
 * 2.6.1 Event pattern eTVOC 1 (Characteristics UUID: 0x521B)
 */
@SuppressWarnings("WeakerAccess")
public class EtvocSensor1 extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<EtvocSensor1> CREATOR = new ByteArrayCreater<EtvocSensor1>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public EtvocSensor1 createFromParcel(Parcel in) {
            return new EtvocSensor1(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EtvocSensor1[] newArray(int size) {
            return new EtvocSensor1[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EtvocSensor1 createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ETVOC_SENSOR_1_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new EtvocSensor1(bluetoothGattCharacteristic);
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
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x521B
     */
    public EtvocSensor1(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
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
     * Constructor from value
     *
     * @param eventEnableDisable         Event enable/disable
     * @param simpleThresholdUpperLimit1 Simple threshold [upper limit] 1
     * @param simpleThresholdUpperLimit2 Simple threshold [upper limit] 2
     * @param simpleThresholdLowerLimit1 Simple threshold [lower limit] 1
     * @param simpleThresholdLowerLimit2 Simple threshold [lower limit] 2
     * @param changeThresholdRise1       Change threshold [rise] 1
     * @param changeThresholdRise2       Change threshold [rise] 2
     * @param changeThresholdDecline1    Change threshold [decline] 1
     * @param changeThresholdDecline2    Change threshold [decline] 1
     */
    public EtvocSensor1(int eventEnableDisable, int simpleThresholdUpperLimit1, int simpleThresholdUpperLimit2, int simpleThresholdLowerLimit1, int simpleThresholdLowerLimit2, int changeThresholdRise1, int changeThresholdRise2, int changeThresholdDecline1, int changeThresholdDecline2) {
        mEventEnableDisable = eventEnableDisable;
        mSimpleThresholdUpperLimit1 = simpleThresholdUpperLimit1;
        mSimpleThresholdUpperLimit2 = simpleThresholdUpperLimit2;
        mSimpleThresholdLowerLimit1 = simpleThresholdLowerLimit1;
        mSimpleThresholdLowerLimit2 = simpleThresholdLowerLimit2;
        mChangeThresholdRise1 = changeThresholdRise1;
        mChangeThresholdRise2 = changeThresholdRise2;
        mChangeThresholdDecline1 = changeThresholdDecline1;
        mChangeThresholdDecline2 = changeThresholdDecline2;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private EtvocSensor1(Parcel in) {
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
     * @return Simple threshold [upper limit] 1(ppb)
     */
    public double getSimpleThresholdUpperLimit1Ppb() {
        return mSimpleThresholdUpperLimit1 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Simple threshold [upper limit] 2
     */
    public int getSimpleThresholdUpperLimit2() {
        return mSimpleThresholdUpperLimit2;
    }

    /**
     * @return Simple threshold [upper limit] 2(ppb)
     */
    public double getSimpleThresholdUpperLimit2Ppb() {
        return mSimpleThresholdUpperLimit2 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Simple threshold [lower limit] 1
     */
    public int getSimpleThresholdLowerLimit1() {
        return mSimpleThresholdLowerLimit1;
    }

    /**
     * @return Simple threshold [lower limit] 1(ppb)
     */
    public double getSimpleThresholdLowerLimit1Ppb() {
        return mSimpleThresholdLowerLimit1 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Simple threshold [lower limit] 2
     */
    public int getSimpleThresholdLowerLimit2() {
        return mSimpleThresholdLowerLimit2;
    }

    /**
     * @return Simple threshold [lower limit] 2(ppb)
     */
    public double getSimpleThresholdLowerLimit2Ppb() {
        return mSimpleThresholdLowerLimit2 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Change threshold [rise] 1
     */
    public int getChangeThresholdRise1() {
        return mChangeThresholdRise1;
    }

    /**
     * @return Change threshold [rise] 1(ppb)
     */
    public double getChangeThresholdRise1Ppb() {
        return mChangeThresholdRise1 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Change threshold [rise] 2
     */
    public int getChangeThresholdRise2() {
        return mChangeThresholdRise2;
    }

    /**
     * @return Change threshold [rise] 2(ppb)
     */
    public double getChangeThresholdRise2Ppb() {
        return mChangeThresholdRise2 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Change threshold [decline] 1
     */
    public int getChangeThresholdDecline1() {
        return mChangeThresholdDecline1;
    }

    /**
     * @return Change threshold [decline] 1(ppb)
     */
    public double getChangeThresholdDecline1Ppb() {
        return mChangeThresholdDecline1 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * @return Change threshold [decline] 2
     */
    public int getChangeThresholdDecline2() {
        return mChangeThresholdDecline2;
    }

    /**
     * @return Change threshold [decline] 2(ppb)
     */
    public double getChangeThresholdDecline2Ppb() {
        return mChangeThresholdDecline2 * EVENT_THRESHOLD_ETVOC_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mEventEnableDisable);
        byteBuffer.putShort((short) mSimpleThresholdUpperLimit1);
        byteBuffer.putShort((short) mSimpleThresholdUpperLimit2);
        byteBuffer.putShort((short) mSimpleThresholdLowerLimit1);
        byteBuffer.putShort((short) mSimpleThresholdLowerLimit2);
        byteBuffer.putShort((short) mChangeThresholdRise1);
        byteBuffer.putShort((short) mChangeThresholdRise2);
        byteBuffer.putShort((short) mChangeThresholdDecline1);
        byteBuffer.putShort((short) mChangeThresholdDecline2);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        return data;
    }

}
