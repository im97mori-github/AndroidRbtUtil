package org.im97mori.rbt.ble.characteristic.lds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ETVOC_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SOUND_NOISE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_TEMPERATURE_UNIT;

/**
 * 2.2.1 Latest sensing data (Characteristics UUID: 0x5012)
 */
@SuppressWarnings("WeakerAccess")
public class LatestSensingData implements ByteArrayInterface, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<LatestSensingData> CREATOR = new ByteArrayCreater<LatestSensingData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestSensingData createFromParcel(@NonNull Parcel in) {
            return new LatestSensingData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestSensingData[] newArray(int size) {
            return new LatestSensingData[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public LatestSensingData createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(LATEST_SENSING_DATA_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new LatestSensingData(bluetoothGattCharacteristic);
        }

    };

    /**
     * Sequence number
     */
    private final int mSequenceNumber;

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
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5012
     */
    public LatestSensingData(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mSequenceNumber = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mTemperature = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 1);
        mRelativeHumidity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 3);
        mAmbientLight = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 5);
        mBarometricPressure = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT32, 7);
        mSoundNoise = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 11);
        mEtvoc = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 13);
        mEco2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 15);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private LatestSensingData(@NonNull Parcel in) {
        mSequenceNumber = in.readInt();
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
        dest.writeInt(mSequenceNumber);
        dest.writeInt(mTemperature);
        dest.writeInt(mRelativeHumidity);
        dest.writeInt(mAmbientLight);
        dest.writeInt(mBarometricPressure);
        dest.writeInt(mSoundNoise);
        dest.writeInt(mEtvoc);
        dest.writeInt(mEco2);
    }

    /**
     * @return Sequence number
     */
    public int getSequenceNumber() {
        return mSequenceNumber;
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
        byte[] data = new byte[17];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mSequenceNumber);
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
