package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.8.4 Central address resolution (Characteristics UUID: 0x2AA6)
 */
@SuppressWarnings("WeakerAccess")
public class CentralAddressResolution extends AbstractCharacteristic implements Parcelable {

    /**
     * 1: Address resolution is supported in this device
     */
    public static final int CENTRAL_ADDRESS_RESOLUTION_SUPPORTED = 1;

    /**
     * @see Creator
     */
    public static final Creator<CentralAddressResolution> CREATOR = new Creator<CentralAddressResolution>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public CentralAddressResolution createFromParcel(Parcel in) {
            return new CentralAddressResolution(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public CentralAddressResolution[] newArray(int size) {
            return new CentralAddressResolution[size];
        }

    };

    /**
     * Central address resolution support
     */
    private final int mCentralAddressResolutionSupport;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2AA6
     */
    public CentralAddressResolution(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mCentralAddressResolutionSupport = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private CentralAddressResolution(Parcel in) {
        mCentralAddressResolutionSupport = in.readInt();
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
        dest.writeInt(mCentralAddressResolutionSupport);
    }

    /**
     * @return Central address resolution support
     */
    public int getCentralAddressResolutionSupport() {
        return mCentralAddressResolutionSupport;
    }

}
