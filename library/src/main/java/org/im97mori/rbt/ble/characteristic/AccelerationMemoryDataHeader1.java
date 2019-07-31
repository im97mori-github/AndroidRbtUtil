package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;

/**
 * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryDataHeader1 extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * 0x00: Vibration data
     */
    public static final int EARTHQUAKE_FLAG_VIBRATION_DATA = 0x00;

    /**
     * 0x01: Earthquakedata
     */
    public static final int EARTHQUAKE_FLAG_EARTHQUAKE_DATA = 0x01;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationMemoryDataHeader1> CREATOR = new ByteArrayCreater<AccelerationMemoryDataHeader1>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader1 createFromParcel(Parcel in) {
            return new AccelerationMemoryDataHeader1(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader1[] newArray(int size) {
            return new AccelerationMemoryDataHeader1[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader1 createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_MEMORY_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationMemoryDataHeader1(bluetoothGattCharacteristic);
        }

    };

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Storage totalpage
     */
    private final int mStorageTotalPage;

    /**
     * Earthquakes or vibration count
     */
    private final BigInteger mEarthQuakesOrVibrationCount;

    /**
     * Time counter
     */
    private final BigInteger mTimeCounter;

    /**
     * Earthquake flag
     */
    private final int mEarthQuakeFlag;

    /**
     * SI value calculation axis
     */
    private final int mSiValueCalculationAxis;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5034
     */
    public AccelerationMemoryDataHeader1(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mStorageTotalPage = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2);
        mEarthQuakesOrVibrationCount = createBigInteger(bluetoothGattCharacteristic.getValue(), 4, 4);
        mTimeCounter = createBigInteger(bluetoothGattCharacteristic.getValue(), 8, 8);
        mEarthQuakeFlag = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 16);
        mSiValueCalculationAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 17);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryDataHeader1(Parcel in) {
        mTotalTransferCount = in.readInt();
        mStorageTotalPage = in.readInt();
        mEarthQuakesOrVibrationCount = (BigInteger) in.readSerializable();
        mTimeCounter = (BigInteger) in.readSerializable();
        mEarthQuakeFlag = in.readInt();
        mSiValueCalculationAxis = in.readInt();
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
        dest.writeInt(mTotalTransferCount);
        dest.writeInt(mStorageTotalPage);
        dest.writeSerializable(mEarthQuakesOrVibrationCount);
        dest.writeSerializable(mTimeCounter);
        dest.writeInt(mEarthQuakeFlag);
        dest.writeInt(mSiValueCalculationAxis);
    }

    /**
     * @return Total transfer count
     */
    public int getTotalTransferCount() {
        return mTotalTransferCount;
    }

    /**
     * @return {@code true}:Total transfer count is data error, {@code false}:not data error
     */
    public boolean isTotalTransferCountDataError() {
        return (mTotalTransferCount & DATA_ERROR_BIT) != 0;
    }

    /**
     * @return Storage totalpage
     */
    public int getStorageTotalPage() {
        return mStorageTotalPage;
    }

    /**
     * @return Earthquakes or vibration count
     */
    public BigInteger getEarthQuakesOrVibrationCount() {
        return mEarthQuakesOrVibrationCount;
    }

    /**
     * @return Time counter
     */
    public BigInteger getTimeCounter() {
        return mTimeCounter;
    }

    /**
     * @return Earthquake flag
     */
    public int getEarthQuakeFlag() {
        return mEarthQuakeFlag;
    }

    /**
     * @return SI value calculation axis
     */
    public int getSiValueCalculationAxis() {
        return mSiValueCalculationAxis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mTotalTransferCount);
        byteBuffer.putShort((short) mStorageTotalPage);
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mEarthQuakesOrVibrationCount, 4));
        byteBuffer.put(createLittleEndianByteArrayFromBigInteger(mTimeCounter, 8));
        byteBuffer.put((byte) mEarthQuakeFlag);
        byteBuffer.put((byte) mSiValueCalculationAxis);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        return data;
    }

}
