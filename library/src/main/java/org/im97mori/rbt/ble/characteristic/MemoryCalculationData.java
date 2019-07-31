package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_HEAT_STROKE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_PGA_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SI_VALUE_UNIT;

/**
 * 2.1.5 Memory calculation data (Characteristics UUID: 0x500B)
 */
@SuppressWarnings("WeakerAccess")
public class MemoryCalculationData extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * Memory index data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000_00000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemoryCalculationData> CREATOR = new ByteArrayCreater<MemoryCalculationData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryCalculationData createFromParcel(Parcel in) {
            return new MemoryCalculationData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryCalculationData[] newArray(int size) {
            return new MemoryCalculationData[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MemoryCalculationData createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_CALCULATION_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemoryCalculationData(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory index
     */
    private final int mMemoryIndex;

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
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5118
     */
    public MemoryCalculationData(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mDiscomfortIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mHeatStroke = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
        mVibrationInformation = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 8);
        mSiValue = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 9);
        mPga = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 11);
        mSeismicIntensity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 13);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemoryCalculationData(Parcel in) {
        mMemoryIndex = in.readInt();
        mDiscomfortIndex = in.readInt();
        mHeatStroke = in.readInt();
        mVibrationInformation = in.readInt();
        mSiValue = in.readInt();
        mPga = in.readInt();
        mSeismicIntensity = in.readInt();
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
        dest.writeInt(mMemoryIndex);
        dest.writeInt(mDiscomfortIndex);
        dest.writeInt(mHeatStroke);
        dest.writeInt(mVibrationInformation);
        dest.writeInt(mSiValue);
        dest.writeInt(mPga);
        dest.writeInt(mSeismicIntensity);
    }

    /**
     * @return Memory index
     */
    public int getMemoryIndex() {
        return mMemoryIndex;
    }

    /**
     * @return {@code true}:Memory index is data error, {@code false}:not data error
     */
    public boolean isMemoryIndexDataError() {
        return (mMemoryIndex & DATA_ERROR_BIT) != 0;
    }

    /**
     * @return Discomfort index
     */
    public int getDiscomfortIndex() {
        return mDiscomfortIndex;
    }

    /**
     * @return Discomfort index(Unit 0.01)
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
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[15];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(mMemoryIndex);
        byteBuffer.putShort((short) mDiscomfortIndex);
        byteBuffer.putShort((short) mHeatStroke);
        byteBuffer.put((byte) mVibrationInformation);
        byteBuffer.putShort((short) mSiValue);
        byteBuffer.putShort((short) mPga);
        byteBuffer.putShort((short) mSeismicIntensity);
        return data;
    }

}
