package org.im97mori.rbt.ble.characteristic.tss;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;
import org.im97mori.rbt.RbtUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TIME_SETTING_CHARACTERISTIC;

/**
 * 2.5.2 Time setting (Characteristics UUID: 0x5202)
 */
public class TimeSetting implements ByteArrayInterface, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<TimeSetting> CREATOR = new ByteArrayCreater<TimeSetting>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public TimeSetting createFromParcel(@NonNull Parcel in) {
            return new TimeSetting(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public TimeSetting[] newArray(int size) {
            return new TimeSetting[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public TimeSetting createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(TIME_SETTING_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new TimeSetting(bluetoothGattCharacteristic);
        }

    };

    /**
     * Time setting
     */
    private final BigInteger mTimeSetting;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5202
     */
    public TimeSetting(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTimeSetting = RbtUtils.createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 8);
    }

    /**
     * Constructor from value
     *
     * @param timeSetting Time setting
     */
    public TimeSetting(@NonNull BigInteger timeSetting) {
        mTimeSetting = timeSetting;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private TimeSetting(@NonNull Parcel in) {
        mTimeSetting = (BigInteger) in.readSerializable();
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
        dest.writeSerializable(mTimeSetting);
    }

    /**
     * @return Time setting
     */
    public BigInteger getTimeSetting() {
        return mTimeSetting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(RbtUtils.createLittleEndianByteArrayFromBigInteger(mTimeSetting, 8));
        return data;
    }

}
