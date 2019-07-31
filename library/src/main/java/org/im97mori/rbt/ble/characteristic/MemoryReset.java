package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_RESET_CHARACTERISTIC;

/**
 * 2.4.6 Memory reset (Characteristics UUID: 0x5116)
 */
public class MemoryReset extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x01: Sensing data area
     */
    public static final int MEMORY_RESET_SENSING_DATA_AREA = 0x01;

    /**
     * 0x02: Acceleration area
     */
    public static final int MEMORY_RESET_ACCELERATION_DATA_AREA = 0x02;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemoryReset> CREATOR = new ByteArrayCreater<MemoryReset>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryReset createFromParcel(Parcel in) {
            return new MemoryReset(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryReset[] newArray(int size) {
            return new MemoryReset[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryReset createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_RESET_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemoryReset(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory reset
     */
    private final int mMemoryReset;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5116
     */
    public MemoryReset(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryReset = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    }

    /**
     * Constructor from value
     *
     * @param memoryReset one of {@link #MEMORY_RESET_SENSING_DATA_AREA}
     *                    {@link #MEMORY_RESET_ACCELERATION_DATA_AREA}
     */
    public MemoryReset(int memoryReset) {
        mMemoryReset = memoryReset;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryReset(Parcel in) {
        mMemoryReset = in.readInt();
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
        dest.writeInt(mMemoryReset);
    }

    /**
     * @return Memory reset
     */
    public int getMemoryReset() {
        return mMemoryReset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[1];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mMemoryReset);
        return data;
    }

}
