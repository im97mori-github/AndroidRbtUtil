package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.8.1 Device mName (Characteristics UUID: 0x2A00)
 */
@SuppressWarnings("WeakerAccess")
public class DeviceName extends AbstractCharacteristic implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<DeviceName> CREATOR = new Creator<DeviceName>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public DeviceName createFromParcel(Parcel in) {
            return new DeviceName(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public DeviceName[] newArray(int size) {
            return new DeviceName[size];
        }

    };

    /**
     * Name
     */
    private final String mName;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A00
     */
    public DeviceName(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mName = bluetoothGattCharacteristic.getStringValue(0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private DeviceName(Parcel in) {
        mName = in.readString();
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
        dest.writeString(mName);
    }

    /**
     * @return Name
     */
    public String getName() {
        return mName;
    }

}
