package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_STATE_OPERATION_CHARACTERISTIC;

/**
 * 2.4.3 LED state [operation] (Characteristics UUID: 0x5113)
 */
@SuppressWarnings("WeakerAccess")
public class LedStateOperation extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * 0x00: Rainbow (default)
     */
    public static final int START_UP_RAINBOW = 0x00;

    /**
     * 0x01: BLUE
     */
    public static final int START_UP_BLUE = 0x01;

    /**
     * 0x00: NONE (default)
     */
    public static final int ERROR_NONE = 0x00;

    /**
     * 0x01: RED
     */
    public static final int ERROR_RED = 0x01;

    /**
     * 0x00: NONE (default)
     */
    public static final int CONNECTION_NONE = 0x00;

    /**
     * 0x01: GREEN ON 1sec
     */
    public static final int CONNECTION_GREEN = 0x01;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LedStateOperation> CREATOR = new ByteArrayCreater<LedStateOperation>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public LedStateOperation createFromParcel(Parcel in) {
            return new LedStateOperation(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LedStateOperation[] newArray(int size) {
            return new LedStateOperation[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LedStateOperation createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LED_STATE_OPERATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LedStateOperation(bluetoothGattCharacteristic);
        }

    };

    /**
     * Start up
     */
    private final int mStartUp;

    /**
     * Error
     */
    private final int mError;

    /**
     * Connection
     */
    private final int mConnection;


    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5113
     */
    public LedStateOperation(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mStartUp = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mConnection = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
    }

    /**
     * Constructor from values
     *
     * @param startUp    one of {@link #START_UP_RAINBOW}
     *                   {@link #START_UP_BLUE}
     * @param error      one of {@link #ERROR_NONE}
     *                   {@link #ERROR_RED}
     * @param connection one of {@link #CONNECTION_NONE}
     *                   {@link #CONNECTION_GREEN}
     */
    public LedStateOperation(int startUp, int error, int connection) {
        mStartUp = startUp;
        mError = error;
        mConnection = connection;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LedStateOperation(Parcel in) {
        mStartUp = in.readInt();
        mError = in.readInt();
        mConnection = in.readInt();
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
        dest.writeInt(mStartUp);
        dest.writeInt(mError);
        dest.writeInt(mConnection);
    }

    /**
     * @return Start up
     */
    public int getStartUp() {
        return mStartUp;
    }

    /**
     * @return Error
     */
    public int getError() {
        return mError;
    }

    /**
     * @return Connection
     */
    public int getConnection() {
        return mConnection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[3];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mStartUp);
        byteBuffer.put((byte) mError);
        byteBuffer.put((byte) mConnection);
        return data;
    }

}
