package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TIME_SETTING_CHARACTERISTIC;

/**
 * 2.5.2 Time setting (Characteristics UUID: 0x5202)
 */
public class TimeSetting extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<TimeSetting> CREATOR = new ByteArrayCreater<TimeSetting>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeSetting createFromParcel(Parcel in) {
            return new TimeSetting(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeSetting[] newArray(int size) {
            return new TimeSetting[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public TimeSetting createFromByteArray(byte[] values) {
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
    public TimeSetting(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTimeSetting = createBigInteger(bluetoothGattCharacteristic.getValue(), 0, 8);
    }

    /**
     * Constructor from value
     *
     * @param timeSetting Time setting
     */
    public TimeSetting(BigInteger timeSetting) {
        mTimeSetting = timeSetting;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private TimeSetting(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
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
    public byte[] getBytes() {
        byte[] data = new byte[8];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mTimeSetting, 8));
        return data;
    }

}
