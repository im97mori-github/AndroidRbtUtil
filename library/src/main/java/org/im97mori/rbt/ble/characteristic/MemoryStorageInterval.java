package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STORAGE_INTERVAL_CHARACTERISTIC;

/**
 * 2.5.3 Memory storage interval (Characteristics UUID: 0x5203)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryStorageInterval extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * Unit: 1s
     */
    public static final double MEMORY_STORAGE_INTERVAL_UNIT = 1d;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemoryStorageInterval> CREATOR = new ByteArrayCreater<MemoryStorageInterval>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryStorageInterval createFromParcel(@NonNull Parcel in) {
            return new MemoryStorageInterval(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryStorageInterval[] newArray(int size) {
            return new MemoryStorageInterval[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryStorageInterval createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemoryStorageInterval(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory storage interval
     */
    private final int mMemoryStorageInterval;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5203
     */
    public MemoryStorageInterval(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryStorageInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
    }

    /**
     * Constructor from value
     *
     * @param memoryStorageInterval Memory storage interval
     */
    public MemoryStorageInterval(int memoryStorageInterval) {
        mMemoryStorageInterval = memoryStorageInterval;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryStorageInterval(@NonNull Parcel in) {
        mMemoryStorageInterval = in.readInt();
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(mMemoryStorageInterval);
    }

    /**
     * @return Memory storage interval
     */
    public int getMemoryStorageInterval() {
        return mMemoryStorageInterval;
    }

    /**
     * @return Memory storage interval(Sec)
     */
    public double getMemoryStorageIntervalSec() {
        return mMemoryStorageInterval * MEMORY_STORAGE_INTERVAL_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[2];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mMemoryStorageInterval);
        return data;
    }

}
