package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ETVOC_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_HEAT_STROKE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SOUND_NOISE_UNIT;

/**
 * 2.3.5 Acceleration memory data [Data] (Characteristics UUID: 0x5034)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryData2 extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<AccelerationMemoryData2> CREATOR = new ByteArrayCreater<AccelerationMemoryData2>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryData2 createFromParcel(Parcel in) {
            return new AccelerationMemoryData2(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryData2[] newArray(int size) {
            return new AccelerationMemoryData2[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryData2 createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ACCELERATION_MEMORY_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new AccelerationMemoryData2(bluetoothGattCharacteristic);
        }

    };

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Ambient light
     */
    private final int mAmbientLight;

    /**
     * Barometric pressure
     */
    private final int mBarometricPressure;

    /**
     * Sound noise
     */
    private final int mSoundNoise;

    /**
     * eTVOC
     */
    private final int mEtvoc;

    /**
     * eCO2
     */
    private final int mEco2;

    /**
     * Discomfort index
     */
    private final int mDiscomfortIndex;

    /**
     * Heat stroke
     */
    private final int mHeatStroke;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5034
     */
    public AccelerationMemoryData2(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mAmbientLight = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 2);
        mBarometricPressure = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT32, 4);
        mSoundNoise = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 8);
        mEtvoc = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 10);
        mEco2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mDiscomfortIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mHeatStroke = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryData2(Parcel in) {
        mTotalTransferCount = in.readInt();
        mAmbientLight = in.readInt();
        mBarometricPressure = in.readInt();
        mSoundNoise = in.readInt();
        mEtvoc = in.readInt();
        mEco2 = in.readInt();
        mDiscomfortIndex = in.readInt();
        mHeatStroke = in.readInt();
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
        dest.writeInt(mAmbientLight);
        dest.writeInt(mBarometricPressure);
        dest.writeInt(mSoundNoise);
        dest.writeInt(mEtvoc);
        dest.writeInt(mEco2);
        dest.writeInt(mDiscomfortIndex);
        dest.writeInt(mHeatStroke);
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
     * @return Ambient light
     */
    public int getAmbientLight() {
        return mAmbientLight;
    }

    /**
     * @return Ambient light(lx)
     */
    public double getAmbientLightLx() {
        return mAmbientLight * OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
    }

    /**
     * @return Barometric pressure
     */
    public int getBarometricPressure() {
        return mBarometricPressure;
    }

    /**
     * @return Barometric pressure(hPa)
     */
    public double getBarometricPressureHpa() {
        return mBarometricPressure * OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT;
    }

    /**
     * @return Sound noise
     */
    public int getSoundNoise() {
        return mSoundNoise;
    }

    /**
     * @return Sound noise(dB)
     */
    public double getSoundNoiseDb() {
        return mSoundNoise * OUTPUT_RANGE_SOUND_NOISE_UNIT;
    }

    /**
     * @return eTVOC
     */
    public int getEtvoc() {
        return mEtvoc;
    }

    /**
     * @return eTVOC(ppb)
     */
    public double getEtvocPpb() {
        return mEtvoc * OUTPUT_RANGE_ETVOC_UNIT;
    }

    /**
     * @return eCO2
     */
    public int getEco2() {
        return mEco2;
    }

    /**
     * @return eCO2(ppm)
     */
    public double getEco2Ppm() {
        return mEco2 * OUTPUT_RANGE_ECO2_UNIT;
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
     * {@inheritDoc}
     */
    @Override
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort((short) mTotalTransferCount);
        byteBuffer.putShort((short) mAmbientLight);
        byteBuffer.putInt(mBarometricPressure);
        byteBuffer.putShort((short) mSoundNoise);
        byteBuffer.putShort((short) mEtvoc);
        byteBuffer.putShort((short) mEco2);
        byteBuffer.putShort((short) mDiscomfortIndex);
        byteBuffer.putShort((short) getHeatStroke());
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        return data;
    }

}
