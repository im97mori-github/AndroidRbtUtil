package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STATUS_CHARACTERISTIC;

/**
 * 2.1.3 Memory status (Characteristics UUID: 0x5006)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryStatus extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Waiting
     */
    public static final int STATUS_WAITING = 0x00;

    /**
     * 0x01: Ready to transfer
     */
    public static final int STATUS_READY_TO_TRANSFER = 0x01;

    /**
     * 0x02: Transferring
     */
    public static final int STATUS_TRANSFERRING = 0x02;

    /**
     * 0x03: Error
     */
    public static final int STATUS_ERROR = 0x03;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemoryStatus> CREATOR = new ByteArrayCreater<MemoryStatus>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryStatus createFromParcel(Parcel in) {
            return new MemoryStatus(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryStatus[] newArray(int size) {
            return new MemoryStatus[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryStatus createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_STATUS_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemoryStatus(bluetoothGattCharacteristic);
        }

    };

    /**
     * Status
     */
    private final int mStatus;

    /**
     * Time counter
     */
    private final BigInteger mTimeCounter;

    /**
     * Memory storage interval
     */
    private final int mMemoryStorageInterval;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5006
     */
    public MemoryStatus(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mStatus = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mTimeCounter = createBigInteger(bluetoothGattCharacteristic.getValue(), 1, 8);
        mMemoryStorageInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 9);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryStatus(Parcel in) {
        mStatus = in.readInt();
        mTimeCounter = (BigInteger) in.readSerializable();
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStatus);
        dest.writeSerializable(mTimeCounter);
        dest.writeInt(mMemoryStorageInterval);
    }

    /**
     * @return Status
     */
    public int getStatus() {
        return mStatus;
    }

    /**
     * @return Time counter
     */
    public BigInteger getTimeCounter() {
        return mTimeCounter;
    }

    /**
     * @return Memory storage interval
     */
    public int getMemoryStorageInterval() {
        return mMemoryStorageInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[11];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mStatus);
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mTimeCounter, 8));
        byteBuffer.putShort((short) mMemoryStorageInterval);
        return data;
    }

}
