package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.5.3 Memory storage interval (Characteristics UUID: 0x5203)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryStorageInterval extends AbstractCharacteristic implements Parcelable {

    /**
     * Unit: 1s
     */
    public static final double MEMORY_STORAGE_INTERVAL_UNIT = 1d;

    /**
     * @see Creator
     */
    public static final Creator<MemoryStorageInterval> CREATOR = new Creator<MemoryStorageInterval>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryStorageInterval createFromParcel(Parcel in) {
            return new MemoryStorageInterval(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryStorageInterval[] newArray(int size) {
            return new MemoryStorageInterval[size];
        }

    };

    /**
     * Memory storage interval
     */
    private final int memoryStorageInterval;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5203
     */
    public MemoryStorageInterval(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        memoryStorageInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryStorageInterval(Parcel in) {
        memoryStorageInterval = in.readInt();
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
        dest.writeInt(memoryStorageInterval);
    }

    /**
     * @return Memory storage interval
     */
    public int getMemoryStorageInterval() {
        return memoryStorageInterval;
    }

    /**
     * @return Memory storage interval(Sec)
     */
    public double getMemoryStorageIntervalSec() {
        return memoryStorageInterval * MEMORY_STORAGE_INTERVAL_UNIT;
    }

}
