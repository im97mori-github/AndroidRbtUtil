package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_INDEX_INFORMATION_CHARACTERISTIC;

/**
 * 2.1.1 Memory index information ( Characteristics UUID: 0x5004)
 */
public class MemoryIndexInformation extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemoryIndexInformation> CREATOR = new ByteArrayCreater<MemoryIndexInformation>() {


        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryIndexInformation createFromParcel(@NonNull Parcel in) {
            return new MemoryIndexInformation(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryIndexInformation[] newArray(int size) {
            return new MemoryIndexInformation[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemoryIndexInformation createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_INDEX_INFORMATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemoryIndexInformation(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory index (Latest)
     */
    private final int mMemoryIndexLatest;

    /**
     * Memory index (Last)
     */
    private final int mMemoryIndexLast;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5004
     */
    public MemoryIndexInformation(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndexLatest = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mMemoryIndexLast = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryIndexInformation(@NonNull Parcel in) {
        mMemoryIndexLatest = in.readInt();
        mMemoryIndexLast = in.readInt();
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
        dest.writeInt(mMemoryIndexLatest);
        dest.writeInt(mMemoryIndexLast);
    }

    /**
     * @return Memory index (Latest)
     */
    public int getMemoryIndexLatest() {
        return mMemoryIndexLatest;
    }

    /**
     * @return Memory index (Last)
     */
    public int getMemoryIndexLast() {
        return mMemoryIndexLast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(mMemoryIndexLatest);
        byteBuffer.putInt(mMemoryIndexLast);
        return data;
    }

}
