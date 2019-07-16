package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.8 Acceleration l ogger control (Characteristics UUID: 0x5118)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationLoggerControl extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: Log stop
     */
    public static final int LOG_STOP = 0x00;

    /**
     * 0x01: Log start
     */
    public static final int LOG_START = 0x01;

    /**
     * 0x00: ±2000 gal (fixed value)
     */
    public static final int RANGE_OF_DETECTION_FIXED_VALUE = 0x00;

    /**
     * 0x00: 1 Hz
     */
    public static final int ODR_1_HZ = 0x00;

    /**
     * 0x01: 10 Hz
     */
    public static final int ODR_10_HZ = 0x01;

    /**
     * 0x02: 25 Hz
     */
    public static final int ODR_25_HZ = 0x02;

    /**
     * 0x03: 100 Hz
     */
    public static final int ODR_100_HZ = 0x03;

    /**
     * 0x04: 200 Hz
     */
    public static final int ODR_200_HZ = 0x04;

    /**
     * 0x05: 400 Hz
     */
    public static final int ODR_400_HZ = 0x05;

    /**
     * @see Creator
     */
    public static final Creator<AccelerationLoggerControl> CREATOR = new Creator<AccelerationLoggerControl>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationLoggerControl createFromParcel(Parcel in) {
            return new AccelerationLoggerControl(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationLoggerControl[] newArray(int size) {
            return new AccelerationLoggerControl[size];
        }

    };

    /**
     * Logger condition
     */
    private final int mLoggerCondition;

    /**
     * Range of detection
     */
    private final int mRangeOfDetection;

    /**
     * ODR setting
     */
    private final int mOdrSetting;

    /**
     * Start page
     */
    private final int mStartPage;

    /**
     * End page
     */
    private final int mEndPage;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5118
     */
    public AccelerationLoggerControl(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mLoggerCondition = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRangeOfDetection = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mOdrSetting = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        mStartPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 3);
        mEndPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 5);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationLoggerControl(Parcel in) {
        mLoggerCondition = in.readInt();
        mRangeOfDetection = in.readInt();
        mOdrSetting = in.readInt();
        mStartPage = in.readInt();
        mEndPage = in.readInt();
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
        dest.writeInt(mLoggerCondition);
        dest.writeInt(mRangeOfDetection);
        dest.writeInt(mOdrSetting);
        dest.writeInt(mStartPage);
        dest.writeInt(mEndPage);
    }

    /**
     * @return Logger condition
     */
    public int getLoggerCondition() {
        return mLoggerCondition;
    }

    /**
     * @return Range of detection
     */
    public int getRangeOfDetection() {
        return mRangeOfDetection;
    }

    /**
     * @return ODR setting
     */
    public int getOdrSetting() {
        return mOdrSetting;
    }

    /**
     * @return Start page
     */
    public int getStartPage() {
        return mStartPage;
    }

    /**
     * @return End page
     */
    public int getEndPage() {
        return mEndPage;
    }

}
