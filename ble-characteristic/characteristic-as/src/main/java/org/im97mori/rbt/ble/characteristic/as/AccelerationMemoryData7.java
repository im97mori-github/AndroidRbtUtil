package org.im97mori.rbt.ble.characteristic.as;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;

/**
 * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034) 7 / 13
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryData7 implements ByteArrayInterface, Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationMemoryData7> CREATOR = new ByteArrayCreater<AccelerationMemoryData7>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryData7 createFromParcel(@NonNull Parcel in) {
            return new AccelerationMemoryData7(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryData7[] newArray(int size) {
            return new AccelerationMemoryData7[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryData7 createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_MEMORY_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationMemoryData7(bluetoothGattCharacteristic);
        }

    };

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Acceleration (X -axis), 2-3 Byte
     */
    private final int mAccelerationXAxis1;

    /**
     * Acceleration (Y -axis), 4-5 Byte
     */
    private final int mAccelerationYAxis1;

    /**
     * Acceleration (Z -axis), 6-7 Byte
     */
    private final int mAccelerationZAxis1;

    /**
     * Acceleration (X -axis), 8-9 Byte
     */
    private final int mAccelerationXAxis2;

    /**
     * Acceleration (Y -axis), 10-11 Byte
     */
    private final int mAccelerationYAxis2;

    /**
     * Acceleration (Z -axis), 12-13 Byte
     */
    private final int mAccelerationZAxis2;

    /**
     * Acceleration (X -axis), 14-15 Byte
     */
    private final int mAccelerationXAxis3;

    /**
     * Acceleration (Y -axis), 16-17 Byte
     */
    private final int mAccelerationYAxis3;

    /**
     * Acceleration (Z -axis), 18-19 Byte
     */
    private final int mAccelerationZAxis3;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5034
     */
    public AccelerationMemoryData7(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mAccelerationXAxis1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 2);
        mAccelerationYAxis1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mAccelerationZAxis1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
        mAccelerationXAxis2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 8);
        mAccelerationYAxis2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 10);
        mAccelerationZAxis2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mAccelerationXAxis3 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mAccelerationYAxis3 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
        mAccelerationZAxis3 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 18);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryData7(@NonNull Parcel in) {
        mTotalTransferCount = in.readInt();
        mAccelerationXAxis1 = in.readInt();
        mAccelerationYAxis1 = in.readInt();
        mAccelerationZAxis1 = in.readInt();
        mAccelerationXAxis2 = in.readInt();
        mAccelerationYAxis2 = in.readInt();
        mAccelerationZAxis2 = in.readInt();
        mAccelerationXAxis3 = in.readInt();
        mAccelerationYAxis3 = in.readInt();
        mAccelerationZAxis3 = in.readInt();
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
        dest.writeInt(mTotalTransferCount);
        dest.writeInt(mAccelerationXAxis1);
        dest.writeInt(mAccelerationYAxis1);
        dest.writeInt(mAccelerationZAxis1);
        dest.writeInt(mAccelerationXAxis2);
        dest.writeInt(mAccelerationYAxis2);
        dest.writeInt(mAccelerationZAxis2);
        dest.writeInt(mAccelerationXAxis3);
        dest.writeInt(mAccelerationYAxis3);
        dest.writeInt(mAccelerationZAxis3);
    }

    /**
     * @return Total transfer count
     */
    public int getTotalTransferCount() {
        return mTotalTransferCount;
    }

    /**
     * @return {@code true}:Total transfer count is data error, {@code false}:not data error
     */
    public boolean isTotalTransferCountDataError() {
        return (mTotalTransferCount & DATA_ERROR_BIT) != 0;
    }

    /**
     * @return Acceleration (X -axis), 2-3 Byte
     */
    public int getAccelerationXAxis1() {
        return mAccelerationXAxis1;
    }

    /**
     * @return Acceleration (X -axis), 2-3 Byte(gal)
     */
    public double getAccelerationXAxis1Gal() {
        return mAccelerationXAxis1 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Y -axis), 4-5 Byte
     */
    public int getAccelerationYAxis1() {
        return mAccelerationYAxis1;
    }

    /**
     * @return Acceleration (Y -axis), 4-5 Byte(gal)
     */
    public double getAccelerationYAxis1Gal() {
        return mAccelerationYAxis1 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (X -axis), 8-9 Byte
     */
    public int getAccelerationZAxis1() {
        return mAccelerationZAxis1;
    }

    /**
     * @return Acceleration (Z -axis), 8-9 Byte(gal)
     */
    public double getAccelerationZAxis1Gal() {
        return mAccelerationZAxis1 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (X -axis), 8-9 Byte
     */
    public int getAccelerationXAxis2() {
        return mAccelerationXAxis2;
    }

    /**
     * @return Acceleration (X -axis), 8-9 Byte(gal)
     */
    public double getAccelerationXAxis2Gal() {
        return mAccelerationXAxis2 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Y -axis), 10-11 Byte
     */
    public int getAccelerationYAxis2() {
        return mAccelerationYAxis2;
    }

    /**
     * @return Acceleration (Y -axis), 10-11 Byte(gal)
     */
    public double getAccelerationYAxis2Gal() {
        return mAccelerationYAxis2 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Z -axis), 12-13 Byte
     */
    public int getAccelerationZAxis2() {
        return mAccelerationZAxis2;
    }

    /**
     * @return Acceleration (Z -axis), 12-13 Byte(gal)
     */
    public double getAccelerationZAxis2Gal() {
        return mAccelerationZAxis2 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (X -axis), 14-15 Byte
     */
    public int getAccelerationXAxis3() {
        return mAccelerationXAxis3;
    }

    /**
     * @return Acceleration (X -axis), 14-15 Byte(gal)
     */
    public double getAccelerationXAxis3Gal() {
        return mAccelerationXAxis3 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Y -axis), 16-17 Byte
     */
    public int getAccelerationYAxis3() {
        return mAccelerationYAxis3;
    }

    /**
     * @return Acceleration (Y -axis), 16-17 Byte(gal)
     */
    public double getAccelerationYAxis3Gal() {
        return mAccelerationYAxis3 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Z -axis), 18-19 Byte
     */
    public int getAccelerationZAxis3() {
        return mAccelerationZAxis3;
    }

    /**
     * @return Acceleration (Z -axis), 18-19 Byte(gal)
     */
    public double getAccelerationZAxis3Gal() {
        return mAccelerationZAxis3 * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mTotalTransferCount);
        byteBuffer.putShort((short) mAccelerationXAxis1);
        byteBuffer.putShort((short) mAccelerationYAxis1);
        byteBuffer.putShort((short) mAccelerationZAxis1);
        byteBuffer.putShort((short) mAccelerationXAxis2);
        byteBuffer.putShort((short) mAccelerationYAxis2);
        byteBuffer.putShort((short) mAccelerationZAxis2);
        byteBuffer.putShort((short) mAccelerationXAxis3);
        byteBuffer.putShort((short) mAccelerationYAxis3);
        byteBuffer.putShort((short) mAccelerationZAxis3);
        return data;
    }

}
