package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.1.2 Request memory index (Characteristics UUID: 0x5005)
 */
@SuppressWarnings("WeakerAccess")
public class RequestMemoryIndex extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: Sensing data
     */
    public static final int DATA_TYPE_SENSING_DATA = 0x00;

    /**
     * 0x01: Calculation data
     */
    public static final int DATA_TYPE_CALCULATION_DATA = 0x01;

    /**
     * 0x02: Sensing flag
     */
    public static final int DATA_TYPE_SENSING_FLAG = 0x02;

    /**
     * 0x03: Calculation flag
     */
    public static final int DATA_TYPE_CALCULATION_FLAG = 0x03;

    /**
     * @see Creator
     */
    public static final Creator<RequestMemoryIndex> CREATOR = new Creator<RequestMemoryIndex>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public RequestMemoryIndex createFromParcel(Parcel in) {
            return new RequestMemoryIndex(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RequestMemoryIndex[] newArray(int size) {
            return new RequestMemoryIndex[size];
        }

    };

    /**
     * Memory index (Start)
     */
    private final int mMemoryIndexStart;

    /**
     * Memory index (End)
     */
    private final int mMemoryIndexEnd;

    /**
     * Data type
     */
    private final int mDataType;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5005
     */
    public RequestMemoryIndex(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndexStart = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mMemoryIndexEnd = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 4);
        mDataType = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 8);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private RequestMemoryIndex(Parcel in) {
        mMemoryIndexStart = in.readInt();
        mMemoryIndexEnd = in.readInt();
        mDataType = in.readInt();
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
        dest.writeInt(mMemoryIndexStart);
        dest.writeInt(mMemoryIndexEnd);
        dest.writeInt(mDataType);
    }

    /**
     * @return Memory index (Start)
     */
    public int getMemoryIndexStart() {
        return mMemoryIndexStart;
    }

    /**
     * @return Memory index (End)
     */
    public int getMemoryIndexEnd() {
        return mMemoryIndexEnd;
    }


    /**
     * @return Data type
     */
    public int getDataType() {
        return mDataType;
    }

}
