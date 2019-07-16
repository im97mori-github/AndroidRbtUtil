package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.8.3 Peripheral preferred connection parameters (Characteristics UUID: 0x2A04)
 */
@SuppressWarnings("WeakerAccess")
public class PeripheralPreferredConnectionParameters extends AbstractCharacteristic implements Parcelable {

    /**
     * Unit: 1.25ms
     */
    public static final double MINIMUM_CONNECTION_INTERVAL_UNIT = 1.25d;

    /**
     * Unit: 1.25ms
     */
    public static final double MAXIMUM_CONNECTION_INTERVAL_UNIT = 1.25d;

    /**
     * Unit: 10ms
     */
    public static final double CONNECTION_SUPERVISION_TIMEOUT_MULTIPLIER_UNIT = 10d;

    /**
     * @see Creator
     */
    public static final Creator<PeripheralPreferredConnectionParameters> CREATOR = new Creator<PeripheralPreferredConnectionParameters>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public PeripheralPreferredConnectionParameters createFromParcel(Parcel in) {
            return new PeripheralPreferredConnectionParameters(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PeripheralPreferredConnectionParameters[] newArray(int size) {
            return new PeripheralPreferredConnectionParameters[size];
        }

    };

    /**
     * Minimum connection interval
     */
    private final int mMinimumConnectionInterval;

    /**
     * Maximum connection interval
     */
    private final int mMaximumConnectionInterval;

    /**
     * Slave Latency
     */
    private final int mSlaveLatency;

    /**
     * Connection Supervision Timeout Multiplier
     */
    private final int mConnectionSupervisionTimeoutMultiplier;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x2A04
     */
    public PeripheralPreferredConnectionParameters(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMinimumConnectionInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mMaximumConnectionInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2);
        mSlaveLatency = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 4);
        mConnectionSupervisionTimeoutMultiplier = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 6);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private PeripheralPreferredConnectionParameters(Parcel in) {
        mMinimumConnectionInterval = in.readInt();
        mMaximumConnectionInterval = in.readInt();
        mSlaveLatency = in.readInt();
        mConnectionSupervisionTimeoutMultiplier = in.readInt();
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
        dest.writeInt(mMinimumConnectionInterval);
        dest.writeInt(mMaximumConnectionInterval);
        dest.writeInt(mSlaveLatency);
        dest.writeInt(mConnectionSupervisionTimeoutMultiplier);
    }

    /**
     * @return Minimum connection interval
     */
    public int getMinimumConnectionInterval() {
        return mMinimumConnectionInterval;
    }

    /**
     * @return Minimum connection interval(millis)
     */
    public double getMinimumConnectionIntervalMillis() {
        return mMinimumConnectionInterval * MINIMUM_CONNECTION_INTERVAL_UNIT;
    }

    /**
     * @return Maximum connection interval
     */
    public int getMaximumConnectionInterval() {
        return mMaximumConnectionInterval;
    }

    /**
     * @return Maximum connection interval(millis)
     */
    public double getMaximumConnectionIntervalMillis() {
        return mMaximumConnectionInterval * MAXIMUM_CONNECTION_INTERVAL_UNIT;
    }

    /**
     * @return Slave Latency
     */
    public int getSlaveLatency() {
        return mSlaveLatency;
    }

    /**
     * @return Connection Supervision Timeout Multiplier
     */
    public int getConnectionSupervisionTimeoutMultiplier() {
        return mConnectionSupervisionTimeoutMultiplier;
    }

    /**
     * @return Connection Supervision Timeout Multiplier(millis)
     */
    public double getConnectionSupervisionTimeoutMultiplierMillis() {
        return mConnectionSupervisionTimeoutMultiplier * CONNECTION_SUPERVISION_TIMEOUT_MULTIPLIER_UNIT;
    }

}
