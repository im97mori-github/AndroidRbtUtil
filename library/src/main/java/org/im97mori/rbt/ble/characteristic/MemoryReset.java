package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.6 Memory reset (Characteristics UUID: 0x5116)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryReset extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x01: Sensing data area
     */
    public static final int MEMORY_RESET_SENSING_DATA_AREA = 0x01;

    /**
     * 0x02: Acceleration area
     */
    public static final int MEMORY_RESET_ACCELERATION_DATA_AREA = 0x02;

    /**
     * @see Creator
     */
    public static final Creator<MemoryReset> CREATOR = new Creator<MemoryReset>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryReset createFromParcel(Parcel in) {
            return new MemoryReset(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryReset[] newArray(int size) {
            return new MemoryReset[size];
        }

    };

    /**
     * Memory reset
     */
    private final int mMemoryReset;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5116
     */
    public MemoryReset(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryReset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryReset(Parcel in) {
        mMemoryReset = in.readInt();
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
        dest.writeInt(mMemoryReset);
    }

    /**
     * @return Memory reset
     */
    public int getMemoryReset() {
        return mMemoryReset;
    }

}
