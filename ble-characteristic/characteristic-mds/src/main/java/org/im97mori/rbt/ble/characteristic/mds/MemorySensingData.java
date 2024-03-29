package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ETVOC_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SOUND_NOISE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_TEMPERATURE_UNIT;

/**
 * 2.1.4 Memory ssensing data (Characteristics UUID: 0x500A)
 */
@SuppressWarnings("WeakerAccess")
public class MemorySensingData implements ByteArrayInterface, Parcelable {

    /**
     * Memory index data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000_00000000_00000000;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<MemorySensingData> CREATOR = new ByteArrayCreater<MemorySensingData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingData createFromParcel(@NonNull Parcel in) {
            return new MemorySensingData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingData[] newArray(int size) {
            return new MemorySensingData[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public MemorySensingData createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(MEMORY_SENSING_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new MemorySensingData(bluetoothGattCharacteristic);
        }

    };

    /**
     * Memory index
     */
    private final int mMemoryIndex;

    /**
     * Temperature
     */
    private final int mTemperature;

    /**
     * Relative humidity
     */
    private final int mRelativeHumidity;

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
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x500A
     */
    public MemorySensingData(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mMemoryIndex = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT32, 0);
        mTemperature = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 4);
        mRelativeHumidity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 6);
        mAmbientLight = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 8);
        mBarometricPressure = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT32, 10);
        mSoundNoise = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mEtvoc = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
        mEco2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 18);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private MemorySensingData(@NonNull Parcel in) {
        mMemoryIndex = in.readInt();
        mTemperature = in.readInt();
        mRelativeHumidity = in.readInt();
        mAmbientLight = in.readInt();
        mBarometricPressure = in.readInt();
        mSoundNoise = in.readInt();
        mEtvoc = in.readInt();
        mEco2 = in.readInt();
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
        dest.writeInt(mMemoryIndex);
        dest.writeInt(mTemperature);
        dest.writeInt(mRelativeHumidity);
        dest.writeInt(mAmbientLight);
        dest.writeInt(mBarometricPressure);
        dest.writeInt(mSoundNoise);
        dest.writeInt(mEtvoc);
        dest.writeInt(mEco2);
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
     * @return Temperature
     */
    public int getTemperature() {
        return mTemperature;
    }

    /**
     * @return Temperature(degC)
     */
    public double getTemperatureDegC() {
        return mTemperature * OUTPUT_RANGE_TEMPERATURE_UNIT;
    }

    /**
     * @return Relative humidity
     */
    public int getRelativeHumidity() {
        return mRelativeHumidity;
    }

    /**
     * @return Relative humidity(%RH)
     */
    public double getRelativeHumidityRh() {
        return mRelativeHumidity * OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT;
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
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[20];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(mMemoryIndex);
        byteBuffer.putShort((short) mTemperature);
        byteBuffer.putShort((short) mRelativeHumidity);
        byteBuffer.putShort((short) mAmbientLight);
        byteBuffer.putInt(mBarometricPressure);
        byteBuffer.putShort((short) mSoundNoise);
        byteBuffer.putShort((short) mEtvoc);
        byteBuffer.putShort((short) mEco2);
        return data;
    }

}
