package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_TIME_COUNTER_CHARACTERISTIC;

/**
 * 2.5.1 Latest time counter (Characteristics UUID: 0x5201)
 */
@SuppressWarnings("WeakerAccess")
public class LatestTimeCounter extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LatestTimeCounter> CREATOR = new ByteArrayCreater<LatestTimeCounter>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestTimeCounter createFromParcel(@NonNull Parcel in) {
            return new LatestTimeCounter(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestTimeCounter[] newArray(int size) {
            return new LatestTimeCounter[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestTimeCounter createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LATEST_TIME_COUNTER_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LatestTimeCounter(bluetoothGattCharacteristic);
        }

    };

    /**
     * Time counter
     */
    private final BigInteger mTimeCounter;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5201
     */
    public LatestTimeCounter(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTimeCounter = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 8);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LatestTimeCounter(@NonNull Parcel in) {
        mTimeCounter = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mTimeCounter);
    }

    /**
     * @return Time counter
     */
    public BigInteger getTimeCounter() {
        return mTimeCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mTimeCounter, 8));
        return data;
    }

}
