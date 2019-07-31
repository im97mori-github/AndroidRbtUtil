package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_STATUS_CHARACTERISTIC;

/**
 * 2.4.9 Acceleration logger status (Characteristics UUID: 0x5119)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationLoggerStatus extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Waiting
     */
    public static final int LOGGER_STATUS_WAITING = 0x00;

    /**
     * 0x01: Running
     */
    public static final int LOGGER_STATUS_RUNNING = 0x01;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationLoggerStatus> CREATOR = new ByteArrayCreater<AccelerationLoggerStatus>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationLoggerStatus createFromParcel(Parcel in) {
            return new AccelerationLoggerStatus(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationLoggerStatus[] newArray(int size) {
            return new AccelerationLoggerStatus[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationLoggerStatus createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationLoggerStatus(bluetoothGattCharacteristic);
        }

    };

    /**
     * Logger status
     */
    private final int mLoggerStatus;

    /**
     * Running page
     */
    private final int mRunningPage;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5119
     */
    public AccelerationLoggerStatus(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mLoggerStatus = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRunningPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationLoggerStatus(Parcel in) {
        mLoggerStatus = in.readInt();
        mRunningPage = in.readInt();
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
        dest.writeInt(mLoggerStatus);
        dest.writeInt(mRunningPage);
    }

    /**
     * @return Logger status
     */
    public int getLoggerStatus() {
        return mLoggerStatus;
    }

    /**
     * @return Running page
     */
    public int getRunningPage() {
        return mRunningPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[3];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mLoggerStatus);
        byteBuffer.putShort((short) mRunningPage);
        return data;
    }

}
