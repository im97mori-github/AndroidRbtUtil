package org.im97mori.rbt.ble.advertising;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.advertising.ManufacturerSpecificData;
import org.im97mori.rbt.RbtConstants;

import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ETVOC_UNIT;

/**
 * 3.1 Sensor data
 */
public class SensorData implements RbtPacket, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<SensorData> CREATOR = new ByteArrayCreater<SensorData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SensorData createFromParcel(@NonNull Parcel in) {
            return new SensorData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SensorData[] newArray(int size) {
            return new SensorData[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        @Override
        public SensorData createFromByteArray(@NonNull byte[] values) {
            return new SensorData(values);
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
     * Constructor for SensorData
     *
     * @param data byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     */
    public SensorData(@NonNull byte[] data) {
        mSequenceNumber = BLEUtils.createUInt8(data, 1);
        mTemperature = BLEUtils.createSInt16(data, 2);
        mRelativeHumidity = BLEUtils.createSInt16(data, 4);
        mAmbientLight = BLEUtils.createSInt16(data, 6);
        mBarometricPressure = BLEUtils.createSInt32(data, 8);
        mSoundNoise = BLEUtils.createSInt16(data, 12);
        mEtvoc = BLEUtils.createSInt16(data, 14);
        mEco2 = BLEUtils.createSInt16(data, 16);
        BLEUtils.createUInt16(data, 16);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private SensorData(@NonNull Parcel in) {
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
     * {@inheritDoc}
     */
    @Override
    public int getDataType() {
        return RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
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
        return mTemperature * RbtConstants.OutputRange.OUTPUT_RANGE_TEMPERATURE_UNIT;
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
        return mRelativeHumidity * RbtConstants.OutputRange.OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT;
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
        return mBarometricPressure * RbtConstants.OutputRange.OUTPUT_RANGE_BAROMETRIC_PRESSURE_UNIT;
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
        return mSoundNoise * RbtConstants.OutputRange.OUTPUT_RANGE_SOUND_NOISE_UNIT;
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

}
