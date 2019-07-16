package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.3.2 Request acceleration memory index (Characteristics UUID: 0x5032)
 */
@SuppressWarnings("WeakerAccess")
public class RequestAccelerationMemoryIndex extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: Earthquake data
     */
    public static final int ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA = 0x00;

    /**
     * 0x01: Vibration data
     */
    public static final int ACCELERATION_DATA_TYPE_VIBRATION_DATA = 0x01;

    /**
     * @see Creator
     */
    public static final Creator<RequestAccelerationMemoryIndex> CREATOR = new Creator<RequestAccelerationMemoryIndex>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public RequestAccelerationMemoryIndex createFromParcel(Parcel in) {
            return new RequestAccelerationMemoryIndex(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RequestAccelerationMemoryIndex[] newArray(int size) {
            return new RequestAccelerationMemoryIndex[size];
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
    public RequestAccelerationMemoryIndex(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mAccelerationDataType = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRequestAccelerationMemoryIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mRequestPageStart = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2);
        mRequestPageEnd = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private RequestAccelerationMemoryIndex(Parcel in) {
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

}
