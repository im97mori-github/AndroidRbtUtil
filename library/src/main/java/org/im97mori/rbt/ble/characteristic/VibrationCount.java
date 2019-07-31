package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.VIBRATION_COUNT_CHARACTERISTIC;

/**
 * 2.3.1 Vibration count (Characteristics UUID: 0x5031)
 */
@SuppressWarnings("WeakerAccess")
public class VibrationCount extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<VibrationCount> CREATOR = new ByteArrayCreater<VibrationCount>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public VibrationCount createFromParcel(Parcel in) {
            return new VibrationCount(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VibrationCount[] newArray(int size) {
            return new VibrationCount[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VibrationCount createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(VIBRATION_COUNT_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new VibrationCount(bluetoothGattCharacteristic);
        }

    };

    /**
     * Earthquake count
     */
    private final BigInteger mEarthquakeCount;

    /**
     * Vibration count
     */
    private final BigInteger mVibrationCount;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5031
     */
    public VibrationCount(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mEarthquakeCount = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 4);
        mVibrationCount = createBigInteger(bluetoothGattCharacteristic.getValue(), 4, 4);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private VibrationCount(Parcel in) {
        mEarthquakeCount = (BigInteger) in.readSerializable();
        mVibrationCount = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mEarthquakeCount);
        dest.writeSerializable(mVibrationCount);
    }

    /**
     * @return Earthquake count
     */
    public BigInteger getEarthquakeCount() {
        return mEarthquakeCount;
    }

    /**
     * @return Vibration count
     */
    public BigInteger getVibrationCount() {
        return mVibrationCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mEarthquakeCount, 4));
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mVibrationCount, 4));
        return data;
    }

}
