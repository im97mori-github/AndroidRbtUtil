package org.im97mori.rbt.ble.characteristic.cs;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC;

/**
 * 2.4.8 Acceleration logger control (Characteristics UUID: 0x5118)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationLoggerControl implements ByteArrayInterface, Parcelable {

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
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationLoggerControl> CREATOR = new ByteArrayCreater<AccelerationLoggerControl>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationLoggerControl createFromParcel(@NonNull Parcel in) {
            return new AccelerationLoggerControl(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationLoggerControl[] newArray(int size) {
            return new AccelerationLoggerControl[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationLoggerControl createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationLoggerControl(bluetoothGattCharacteristic);
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
    public AccelerationLoggerControl(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mLoggerCondition = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRangeOfDetection = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mOdrSetting = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        mStartPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 3);
        mEndPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 5);
    }

    /**
     * Constructor from value
     *
     * @param loggerCondition  one of {@link #LOG_STOP}
     *                         {@link #LOG_START}
     * @param rangeOfDetection {@link #RANGE_OF_DETECTION_FIXED_VALUE}(fixed value)
     * @param odrSetting       one of {@link #ODR_1_HZ}
     *                         {@link #ODR_10_HZ}
     *                         {@link #ODR_25_HZ}
     *                         {@link #ODR_100_HZ}
     *                         {@link #ODR_200_HZ}
     *                         {@link #ODR_400_HZ}
     * @param startPage        Start page
     * @param endPage          End page
     */
    public AccelerationLoggerControl(int loggerCondition, int rangeOfDetection, int odrSetting, int startPage, int endPage) {
        mLoggerCondition = loggerCondition;
        mRangeOfDetection = rangeOfDetection;
        mOdrSetting = odrSetting;
        mStartPage = startPage;
        mEndPage = endPage;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationLoggerControl(@NonNull Parcel in) {
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[7];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mLoggerCondition);
        byteBuffer.put((byte) mRangeOfDetection);
        byteBuffer.put((byte) mOdrSetting);
        byteBuffer.putShort((short) mStartPage);
        byteBuffer.putShort((short) mEndPage);
        return data;
    }

}
