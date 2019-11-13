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
 * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034) 4 / 4
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryDataHeader4 implements ByteArrayInterface, Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationMemoryDataHeader4> CREATOR = new ByteArrayCreater<AccelerationMemoryDataHeader4>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryDataHeader4 createFromParcel(@NonNull Parcel in) {
            return new AccelerationMemoryDataHeader4(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryDataHeader4[] newArray(int size) {
            return new AccelerationMemoryDataHeader4[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public AccelerationMemoryDataHeader4 createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_MEMORY_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationMemoryDataHeader4(bluetoothGattCharacteristic);
        }

    };

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Acceleration offset (X-axis)
     */
    private final int mAccelerationOffsetXAxis;

    /**
     * Acceleration offset (Y-axis)
     */
    private final int mAccelerationOffsetYAxis;

    /**
     * Acceleration offset (Z-axis)
     */
    private final int mAccelerationOffsetZAxis;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5034
     */
    public AccelerationMemoryDataHeader4(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mAccelerationOffsetXAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 2);
        mAccelerationOffsetYAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mAccelerationOffsetZAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryDataHeader4(@NonNull Parcel in) {
        mTotalTransferCount = in.readInt();
        mAccelerationOffsetXAxis = in.readInt();
        mAccelerationOffsetYAxis = in.readInt();
        mAccelerationOffsetZAxis = in.readInt();
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
        dest.writeInt(mAccelerationOffsetXAxis);
        dest.writeInt(mAccelerationOffsetYAxis);
        dest.writeInt(mAccelerationOffsetZAxis);
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
     * @return Acceleration offset (X-axis)
     */
    public int getAccelerationOffsetXAxis() {
        return mAccelerationOffsetXAxis;
    }

    /**
     * @return Acceleration offset (X-axis)(gal)
     */
    public double getAccelerationOffsetXAxisGal() {
        return mAccelerationOffsetXAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration offset (Y-axis)
     */
    public int getAccelerationOffsetYAxis() {
        return mAccelerationOffsetYAxis;
    }

    /**
     * @return Acceleration offset (Y-axis)(gal)
     */
    public double getAccelerationOffsetYAxisGal() {
        return mAccelerationOffsetYAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration offset (Z-axis)
     */
    public int getAccelerationOffsetZAxis() {
        return mAccelerationOffsetZAxis;
    }

    /**
     * @return Acceleration offset (Z-axis)(gal)
     */
    public double getAccelerationOffsetZAxisGal() {
        return mAccelerationOffsetZAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
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
        byteBuffer.putShort((short) mAccelerationOffsetXAxis);
        byteBuffer.putShort((short) mAccelerationOffsetYAxis);
        byteBuffer.putShort((short) mAccelerationOffsetZAxis);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        return data;
    }

}
