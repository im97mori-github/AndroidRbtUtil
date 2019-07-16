package org.im97mori.rbt.ble.descriptor;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Core Specification v5.1 Vol 3 Part G 3.3.3.3
 */
@SuppressWarnings("WeakerAccess")
public class ClientCharacteristicConfigration implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<ClientCharacteristicConfigration> CREATOR = new Creator<ClientCharacteristicConfigration>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public ClientCharacteristicConfigration createFromParcel(Parcel in) {
            return new ClientCharacteristicConfigration(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ClientCharacteristicConfigration[] newArray(int size) {
            return new ClientCharacteristicConfigration[size];
        }

    };

    /**
     * <p>
     * Configuration
     *
     * @see BluetoothGattDescriptor#ENABLE_NOTIFICATION_VALUE
     * @see BluetoothGattDescriptor#ENABLE_INDICATION_VALUE
     * @see BluetoothGattDescriptor#DISABLE_NOTIFICATION_VALUE
     * </p>
     */
    private final byte[] mConfiguration;

    /**
     * Constructor from {@link BluetoothGattDescriptor}
     *
     * @param bluetoothGattDescriptor Characteristics UUID: 0x2902
     */
    public ClientCharacteristicConfigration(BluetoothGattDescriptor bluetoothGattDescriptor) {
        mConfiguration = bluetoothGattDescriptor.getValue();
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ClientCharacteristicConfigration(Parcel in) {
        mConfiguration = in.createByteArray();
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
        dest.writeByteArray(mConfiguration);
    }

    /**
     * @return Configuration
     */
    public byte[] getConfiguration() {
        return mConfiguration;
    }

}
