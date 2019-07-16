package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;

/**
 * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryDataHeader4 extends AbstractCharacteristic implements Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * @see Creator
     */
    public static final Creator<AccelerationMemoryDataHeader4> CREATOR = new Creator<AccelerationMemoryDataHeader4>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader4 createFromParcel(Parcel in) {
            return new AccelerationMemoryDataHeader4(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader4[] newArray(int size) {
            return new AccelerationMemoryDataHeader4[size];
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
    public AccelerationMemoryDataHeader4(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
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
    private AccelerationMemoryDataHeader4(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
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

}
