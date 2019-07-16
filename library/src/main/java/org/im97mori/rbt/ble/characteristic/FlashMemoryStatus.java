package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.7.3 FLASH memory status (Characteristics UUID: 0x5403)
 */
@SuppressWarnings("WeakerAccess")
public class FlashMemoryStatus extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x00: NONE
     */
    public static final int FLASH_MEMORY_STATUS_NONE = 0x00;

    /**
     * 0x01: Writing
     */
    public static final int FLASH_MEMORY_STATUS_WRITING = 0x01;

    /**
     * 0x02: Write success
     */
    public static final int FLASH_MEMORY_STATUS_WRITE_SUCCESS = 0x02;

    /**
     * 0x03: Write failure
     */
    public static final int FLASH_MEMORY_STATUS_WRITE_FAILURE = 0x03;

    /**
     * 0x04: Flash memory erasing
     */
    public static final int FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING = 0x04;

    /**
     * @see Creator
     */
    public static final Creator<FlashMemoryStatus> CREATOR = new Creator<FlashMemoryStatus>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public FlashMemoryStatus createFromParcel(Parcel in) {
            return new FlashMemoryStatus(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public FlashMemoryStatus[] newArray(int size) {
            return new FlashMemoryStatus[size];
        }

    };

    /**
     * FLASH memory status
     */
    private final int mFlashMemoryStatus;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5403
     */
    public FlashMemoryStatus(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mFlashMemoryStatus = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private FlashMemoryStatus(Parcel in) {
        mFlashMemoryStatus = in.readInt();
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
        dest.writeInt(mFlashMemoryStatus);
    }

    /**
     * @return FLASH memory status
     */
    public int getFlashMemoryStatus() {
        return mFlashMemoryStatus;
    }

}
