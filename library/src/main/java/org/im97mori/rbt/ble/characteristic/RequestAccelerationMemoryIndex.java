package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC;

/**
 * 2.3.2 Request acceleration memory index (Characteristics UUID: 0x5032)
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RequestAccelerationMemoryIndex extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Vibration data
     */
    public static final int ACCELERATION_DATA_TYPE_VIBRATION_DATA = 0x00;

    /**
     * 0x01: Earthquake data
     */
    public static final int ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA = 0x01;

    /**
     * 0x02: Logger data
     */
    public static final int ACCELERATION_DATA_TYPE_LOGGER_DATA = 0x02;


    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<RequestAccelerationMemoryIndex> CREATOR = new ByteArrayCreater<RequestAccelerationMemoryIndex>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestAccelerationMemoryIndex createFromParcel(@NonNull Parcel in) {
            return new RequestAccelerationMemoryIndex(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestAccelerationMemoryIndex[] newArray(int size) {
            return new RequestAccelerationMemoryIndex[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public RequestAccelerationMemoryIndex createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new RequestAccelerationMemoryIndex(bluetoothGattCharacteristic);
        }

    };

    /**
     * Acceleration data type
     */
    private final int mAccelerationDataType;

    /**
     * Request acceleration memory index
     */
    private final int mRequestAccelerationMemoryIndex;

    /**
     * Request page (Start)
     */
    private final int mRequestPageStart;

    /**
     * Request page (End)
     */
    private final int mRequestPageEnd;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5032
     */
    public RequestAccelerationMemoryIndex(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mAccelerationDataType = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRequestAccelerationMemoryIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mRequestPageStart = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2);
        mRequestPageEnd = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 4);
    }

    /**
     * Constructor from value
     *
     * @param accelerationDataType           Acceleration data type
     * @param requestAccelerationMemoryIndex Request acceleration memory index
     * @param requestPageStart               Request page (Start)
     * @param requestPageEnd                 Request page (End)
     */
    public RequestAccelerationMemoryIndex(int accelerationDataType, int requestAccelerationMemoryIndex, int requestPageStart, int requestPageEnd) {
        mAccelerationDataType = accelerationDataType;
        mRequestAccelerationMemoryIndex = requestAccelerationMemoryIndex;
        mRequestPageStart = requestPageStart;
        mRequestPageEnd = requestPageEnd;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private RequestAccelerationMemoryIndex(@NonNull Parcel in) {
        mAccelerationDataType = in.readInt();
        mRequestAccelerationMemoryIndex = in.readInt();
        mRequestPageStart = in.readInt();
        mRequestPageEnd = in.readInt();
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
        dest.writeInt(mAccelerationDataType);
        dest.writeInt(mRequestAccelerationMemoryIndex);
        dest.writeInt(mRequestPageStart);
        dest.writeInt(mRequestPageEnd);
    }

    /**
     * @return Acceleration data type
     */
    public int getAccelerationDataType() {
        return mAccelerationDataType;
    }

    /**
     * @return Request acceleration memory index
     */
    public int getRequestAccelerationMemoryIndex() {
        return mRequestAccelerationMemoryIndex;
    }

    /**
     * @return Request page (Start)
     */
    public int getRequestPageStart() {
        return mRequestPageStart;
    }

    /**
     * @return Request page (End)
     */
    public int getRequestPageEnd() {
        return mRequestPageEnd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[6];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mAccelerationDataType);
        byteBuffer.put((byte) mRequestAccelerationMemoryIndex);
        byteBuffer.putShort((short) mRequestPageStart);
        byteBuffer.putShort((short) mRequestPageEnd);
        return data;
    }

}
