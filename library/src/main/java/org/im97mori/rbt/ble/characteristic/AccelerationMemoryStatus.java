package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_STATUS_CHARACTERISTIC;

/**
 * 2.3.3 Acceleration memory status (Characteristics UUID: 0x5033)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryStatus extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Waiting
     */
    public static final int STATUS_WATING = 0x00;

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
    public static final ByteArrayCreater<AccelerationMemoryStatus> CREATOR = new ByteArrayCreater<AccelerationMemoryStatus>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryStatus createFromParcel(Parcel in) {
            return new AccelerationMemoryStatus(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryStatus[] newArray(int size) {
            return new AccelerationMemoryStatus[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryStatus createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationMemoryStatus(bluetoothGattCharacteristic);
        }

    };

    /**
     * Status
     */
    private final int mStatus;

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5033
     */
    public AccelerationMemoryStatus(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mStatus = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryStatus(Parcel in) {
        mStatus = in.readInt();
        mTotalTransferCount = in.readInt();
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
        dest.writeInt(mTotalTransferCount);
    }

    /**
     * @return Status
     */
    public int getStatus() {
        return mStatus;
    }

    /**
     * @return Total transfer count
     */
    public int getTotalTransferCount() {
        return mTotalTransferCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[3];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mStatus);
        byteBuffer.putShort((short) mTotalTransferCount);
        return data;
    }

}
