package org.im97mori.rbt.ble.characteristic.lds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_HEAT_STROKE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_PGA_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SI_VALUE_UNIT;

/**
 * 2.2.2 Latest calculation data (Characteristics UUID: 0x5013)
 */
@SuppressWarnings("WeakerAccess")
public class LatestCalculationData implements ByteArrayInterface, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LatestCalculationData> CREATOR = new ByteArrayCreater<LatestCalculationData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationData createFromParcel(@NonNull Parcel in) {
            return new LatestCalculationData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationData[] newArray(int size) {
            return new LatestCalculationData[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestCalculationData createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LATEST_CALCULATION_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LatestCalculationData(bluetoothGattCharacteristic);
        }

    };

    /**
     * Sequence number
     */
    private final int mSequenceNumber;

    /**
     * Discomfort index
     */
    private final int mDiscomfortIndex;

    /**
     * Heat stroke
     */
    private final int mHeatStroke;

    /**
     * Vibration information
     */
    private final int mVibrationInformation;

    /**
     * SI value
     */
    private final int mSiValue;

    /**
     * PGA
     */
    private final int mPga;

    /**
     * Seismic intensity
     */
    private final int mSeismicIntensity;

    /**
     * Acceleration (X-axis)
     */
    private final int mAccelerationXAxis;

    /**
     * Acceleration (Y-axis)
     */
    private final int mAccelerationYAxis;

    /**
     * Acceleration (Z-axis)
     */
    private final int mAccelerationZAxis;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5013
     */
    public LatestCalculationData(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mSequenceNumber = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mDiscomfortIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 1);
        mHeatStroke = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 3);
        mVibrationInformation = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 5);
        mSiValue = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 6);
        mPga = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 8);
        mSeismicIntensity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 10);
        mAccelerationXAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mAccelerationYAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mAccelerationZAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LatestCalculationData(@NonNull Parcel in) {
        mSequenceNumber = in.readInt();
        mDiscomfortIndex = in.readInt();
        mHeatStroke = in.readInt();
        mVibrationInformation = in.readInt();
        mSiValue = in.readInt();
        mPga = in.readInt();
        mSeismicIntensity = in.readInt();
        mAccelerationXAxis = in.readInt();
        mAccelerationYAxis = in.readInt();
        mAccelerationZAxis = in.readInt();
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
        dest.writeInt(mSequenceNumber);
        dest.writeInt(mDiscomfortIndex);
        dest.writeInt(mHeatStroke);
        dest.writeInt(mVibrationInformation);
        dest.writeInt(mSiValue);
        dest.writeInt(mPga);
        dest.writeInt(mSeismicIntensity);
        dest.writeInt(mAccelerationXAxis);
        dest.writeInt(mAccelerationYAxis);
        dest.writeInt(mAccelerationZAxis);
    }

    /**
     * @return Sequence number
     */
    public int getSequenceNumber() {
        return mSequenceNumber;
    }

    /**
     * @return Discomfort index
     */
    public int getDiscomfortIndex() {
        return mDiscomfortIndex;
    }

    /**
     * @return Discomfort index(unit 0.01)
     */
    public double getDiscomfortIndexWithUnit() {
        return mDiscomfortIndex * OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT;
    }

    /**
     * @return Heat stroke
     */
    public int getHeatStroke() {
        return mHeatStroke;
    }

    /**
     * @return Heat stroke(degC)
     */
    public double getHeatStrokeDegC() {
        return mHeatStroke * OUTPUT_RANGE_HEAT_STROKE_UNIT;
    }

    /**
     * @return Vibration information
     */
    public int getVibrationInformation() {
        return mVibrationInformation;
    }

    /**
     * @return SI value
     */
    public int getSiValue() {
        return mSiValue;
    }

    /**
     * @return SI value(kine)
     */
    public double getSiValueKine() {
        return mSiValue * OUTPUT_RANGE_SI_VALUE_UNIT;
    }

    /**
     * @return PGA
     */
    public int getPga() {
        return mPga;
    }

    /**
     * @return PGA(gal)
     */
    public double getPgaGal() {
        return mPga * OUTPUT_RANGE_PGA_UNIT;
    }

    /**
     * @return Seismic intensity
     */
    public int getSeismicIntensity() {
        return mSeismicIntensity;
    }

    /**
     * @return Seismic intensity(Unit 0.001)
     */
    public double getSeismicIntensityWithUnit() {
        return mSeismicIntensity * OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT;
    }

    /**
     * @return Acceleration (X-axis)
     */
    public int getAccelerationXAxis() {
        return mAccelerationXAxis;
    }

    /**
     * @return Acceleration (X-axis)(gal)
     */
    public double getAccelerationXAxisGal() {
        return mAccelerationXAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Y-axis)
     */
    public int getAccelerationYAxis() {
        return mAccelerationYAxis;
    }

    /**
     * @return Acceleration (Y-axis)(gal)
     */
    public double getAccelerationYAxisGal() {
        return mAccelerationYAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Acceleration (Z-axis)
     */
    public int getAccelerationZAxis() {
        return mAccelerationZAxis;
    }

    /**
     * @return Acceleration (Z-axis)(gal)
     */
    public double getAccelerationZAxisGal() {
        return mAccelerationZAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[18];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mSequenceNumber);
        byteBuffer.putShort((short) mDiscomfortIndex);
        byteBuffer.putShort((short) mHeatStroke);
        byteBuffer.put((byte) mVibrationInformation);
        byteBuffer.putShort((short) mSiValue);
        byteBuffer.putShort((short) mPga);
        byteBuffer.putShort((short) mSeismicIntensity);
        byteBuffer.putShort((short) mAccelerationXAxis);
        byteBuffer.putShort((short) mAccelerationYAxis);
        byteBuffer.putShort((short) mAccelerationZAxis);
        return data;
    }

}
