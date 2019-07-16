package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.7.2 Mounting orientation (Characteristics UUID: 0x5402)
 */
@SuppressWarnings("WeakerAccess")
public class MountingOrientation extends AbstractCharacteristic implements Parcelable {

    /**
     * 0x01: Position 1
     */
    public static final int MOUNTING_ORIENTATION_POSITION_1 = 0x01;

    /**
     * 0x02: Position 2
     */
    public static final int MOUNTING_ORIENTATION_POSITION_2 = 0x02;

    /**
     * 0x03: Position 3
     */
    public static final int MOUNTING_ORIENTATION_POSITION_3 = 0x03;

    /**
     * 0x04: Position 4
     */
    public static final int MOUNTING_ORIENTATION_POSITION_4 = 0x04;

    /**
     * 0x05: Position 5
     */
    public static final int MOUNTING_ORIENTATION_POSITION_5 = 0x05;

    /**
     * 0x06: Position 6
     */
    public static final int MOUNTING_ORIENTATION_POSITION_6 = 0x06;

    /**
     * @see Creator
     */
    public static final Creator<MountingOrientation> CREATOR = new Creator<MountingOrientation>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MountingOrientation createFromParcel(Parcel in) {
            return new MountingOrientation(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MountingOrientation[] newArray(int size) {
            return new MountingOrientation[size];
        }

    };

    /**
     * Mounting orientation
     */
    private final int mountingOrientation;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5402
     */
    public MountingOrientation(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mountingOrientation = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MountingOrientation(Parcel in) {
        mountingOrientation = in.readInt();
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
        dest.writeInt(mountingOrientation);
    }

    /**
     * @return Mounting orientation
     */
    public int getMountingOrientation() {
        return mountingOrientation;
    }

}
