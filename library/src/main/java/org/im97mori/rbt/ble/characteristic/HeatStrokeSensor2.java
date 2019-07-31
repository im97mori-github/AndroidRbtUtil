package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.HEAT_STROKE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_HEAT_STROKE_UNIT;

/**
 * 2.6.2 Event pattern Heat stroke 2 (Characteristics UUID: 0x5222)
 */
@SuppressWarnings("WeakerAccess")
public class HeatStrokeSensor2 extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<HeatStrokeSensor2> CREATOR = new ByteArrayCreater<HeatStrokeSensor2>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public HeatStrokeSensor2 createFromParcel(Parcel in) {
            return new HeatStrokeSensor2(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HeatStrokeSensor2[] newArray(int size) {
            return new HeatStrokeSensor2[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HeatStrokeSensor2 createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(HEAT_STROKE_SENSOR_2_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new HeatStrokeSensor2(bluetoothGattCharacteristic);
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
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5222
     */
    public HeatStrokeSensor2(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
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
     * Constructor from value
     *
     * @param averageValueThresholdUpper       Average value threshold [upper]
     * @param averageValueThresholdLower       Average value threshold [lower]
     * @param peakToPeakThresholdUpper         Peak to Peak threshold [upper]
     * @param peakToPeakThresholdLower         Peak to Peak threshold [lower]
     * @param intervalDifferenceThresholdUpper Interval difference threshold [upper]
     * @param intervalDifferenceThresholdLower Interval difference threshold [lower]
     * @param baseDifferenceThresholdUpper     Base difference threshold [lower]
     * @param baseDifferenceThresholdLower     Base difference threshold [lower]
     * @param averageValueCount                Average value count
     * @param peakToPeakCount                  Peak to Peak count
     * @param intervalDifferenceCount          Interval difference count
     * @param baseDifferenceCount              Base difference count
     */
    public HeatStrokeSensor2(int averageValueThresholdUpper, int averageValueThresholdLower, int peakToPeakThresholdUpper, int peakToPeakThresholdLower, int intervalDifferenceThresholdUpper, int intervalDifferenceThresholdLower, int baseDifferenceThresholdUpper, int baseDifferenceThresholdLower, int averageValueCount, int peakToPeakCount, int intervalDifferenceCount, int baseDifferenceCount) {
        mAverageValueThresholdUpper = averageValueThresholdUpper;
        mAverageValueThresholdLower = averageValueThresholdLower;
        mPeakToPeakThresholdUpper = peakToPeakThresholdUpper;
        mPeakToPeakThresholdLower = peakToPeakThresholdLower;
        mIntervalDifferenceThresholdUpper = intervalDifferenceThresholdUpper;
        mIntervalDifferenceThresholdLower = intervalDifferenceThresholdLower;
        mBaseDifferenceThresholdUpper = baseDifferenceThresholdUpper;
        mBaseDifferenceThresholdLower = baseDifferenceThresholdLower;
        mAverageValueCount = averageValueCount;
        mPeakToPeakCount = peakToPeakCount;
        mIntervalDifferenceCount = intervalDifferenceCount;
        mBaseDifferenceCount = baseDifferenceCount;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private HeatStrokeSensor2(Parcel in) {
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
     * @return Average value threshold [upper](degC)
     */
    public double getAverageValueThresholdUpperDegC() {
        return mAverageValueThresholdUpper * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Average value threshold [lower]
     */
    public int getAverageValueThresholdLower() {
        return mAverageValueThresholdLower;
    }

    /**
     * @return Average value threshold [lower](degC)
     */
    public double getAverageValueThresholdLowerDegC() {
        return mAverageValueThresholdLower * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Peak to Peak threshold [upper]
     */
    public int getPeakToPeakThresholdUpper() {
        return mPeakToPeakThresholdUpper;
    }

    /**
     * @return Peak to Peak threshold [upper](degC)
     */
    public double getPeakToPeakThresholdUpperDegC() {
        return mPeakToPeakThresholdUpper * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Peak to Peak threshold [lower]
     */
    public int getPeakToPeakThresholdLower() {
        return mPeakToPeakThresholdLower;
    }

    /**
     * @return Peak to Peak threshold [lower](degC)
     */
    public double getPeakToPeakThresholdLowerDegC() {
        return mPeakToPeakThresholdLower * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Interval difference threshold [upper]
     */
    public int getIntervalDifferenceThresholdUpper() {
        return mIntervalDifferenceThresholdUpper;
    }

    /**
     * @return Interval difference threshold [upper](degC)
     */
    public double getIntervalDifferenceThresholdUpperDegC() {
        return mIntervalDifferenceThresholdUpper * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Interval difference threshold [lower]
     */
    public int getIntervalDifferenceThresholdLower() {
        return mIntervalDifferenceThresholdLower;
    }

    /**
     * @return Interval difference threshold [lower](degC)
     */
    public double getIntervalDifferenceThresholdLowerDegC() {
        return mIntervalDifferenceThresholdLower * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Base difference threshold [upper]
     */
    public int getBaseDifferenceThresholdUpper() {
        return mBaseDifferenceThresholdUpper;
    }

    /**
     * @return Base difference threshold [upper](degC)
     */
    public double getBaseDifferenceThresholdUpperDegC() {
        return mBaseDifferenceThresholdUpper * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
    }

    /**
     * @return Base difference threshold [lower]
     */
    public int getBaseDifferenceThresholdLower() {
        return mBaseDifferenceThresholdLower;
    }

    /**
     * @return Base difference threshold [lower](degC)
     */
    public double getBaseDifferenceThresholdLowerDegC() {
        return mBaseDifferenceThresholdLower * EVENT_THRESHOLD_HEAT_STROKE_UNIT;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mAverageValueThresholdUpper);
        byteBuffer.putShort((short) mAverageValueThresholdLower);
        byteBuffer.putShort((short) mPeakToPeakThresholdUpper);
        byteBuffer.putShort((short) mPeakToPeakThresholdLower);
        byteBuffer.putShort((short) mIntervalDifferenceThresholdUpper);
        byteBuffer.putShort((short) mIntervalDifferenceThresholdLower);
        byteBuffer.putShort((short) mBaseDifferenceThresholdUpper);
        byteBuffer.putShort((short) mBaseDifferenceThresholdLower);
        byteBuffer.put((byte) mAverageValueCount);
        byteBuffer.put((byte) mPeakToPeakCount);
        byteBuffer.put((byte) mIntervalDifferenceCount);
        byteBuffer.put((byte) mBaseDifferenceCount);
        return data;
    }

}
