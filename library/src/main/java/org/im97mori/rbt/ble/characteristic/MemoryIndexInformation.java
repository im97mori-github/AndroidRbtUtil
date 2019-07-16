package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.1.1 Memory index information ( Characteristics UUID: 0x5004)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryIndexInformation extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<MemoryIndexInformation> CREATOR = new Creator<MemoryIndexInformation>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryIndexInformation createFromParcel(Parcel in) {
            return new MemoryIndexInformation(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryIndexInformation[] newArray(int size) {
            return new MemoryIndexInformation[size];
        }

    };

    /**
     * Memory index (Latest)
     */
    private final int mMemoryIndexLatest;

    /**
     * Memory index (Last)
     */
    private final int mMemoryIndexLast;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5004
     */
    public MemoryIndexInformation(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndexLatest = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mMemoryIndexLast = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryIndexInformation(Parcel in) {
        mMemoryIndexLatest = in.readInt();
        mMemoryIndexLast = in.readInt();
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
        dest.writeInt(mMemoryIndexLatest);
        dest.writeInt(mMemoryIndexLast);
    }

    /**
     * @return Memory index (Latest)
     */
    public int getMemoryIndexLatest() {
        return mMemoryIndexLatest;
    }

    /**
     * @return Memory index (Last)
     */
    public int getMemoryIndexLast() {
        return mMemoryIndexLast;
    }

}
