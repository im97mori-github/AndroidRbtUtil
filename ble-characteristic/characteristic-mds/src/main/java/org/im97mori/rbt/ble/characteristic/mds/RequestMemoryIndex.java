package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.REQUEST_MEMORY_INDEX_CHARACTERISTIC;

/**
 * 2.1.2 Request memory index (Characteristics UUID: 0x5005)
 */
@SuppressWarnings("WeakerAccess")
public class RequestMemoryIndex implements ByteArrayInterface, Parcelable {

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
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<RequestMemoryIndex> CREATOR = new ByteArrayCreater<RequestMemoryIndex>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestMemoryIndex createFromParcel(@NonNull Parcel in) {
            return new RequestMemoryIndex(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestMemoryIndex[] newArray(int size) {
            return new RequestMemoryIndex[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestMemoryIndex createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(REQUEST_MEMORY_INDEX_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new RequestMemoryIndex(bluetoothGattCharacteristic);
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
    public RequestMemoryIndex(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndexStart = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mMemoryIndexEnd = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 4);
        mDataType = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 8);
    }

    /**
     * Constructor from value
     *
     * @param memoryIndexStart Memory index (Start)
     * @param memoryIndexEnd   Memory index (End)
     * @param dataType         one of {@link #DATA_TYPE_SENSING_DATA}
     *                         {@link #DATA_TYPE_CALCULATION_DATA}
     *                         {@link #DATA_TYPE_SENSING_FLAG}
     *                         {@link #DATA_TYPE_CALCULATION_FLAG}
     */
    public RequestMemoryIndex(int memoryIndexStart, int memoryIndexEnd, int dataType) {
        mMemoryIndexStart = memoryIndexStart;
        mMemoryIndexEnd = memoryIndexEnd;
        mDataType = dataType;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private RequestMemoryIndex(@NonNull Parcel in) {
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[9];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(mMemoryIndexStart);
        byteBuffer.putInt(mMemoryIndexEnd);
        byteBuffer.put((byte) mDataType);
        return data;
    }

}
