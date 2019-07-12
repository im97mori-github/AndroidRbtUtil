package org.im97mori.rbt.ble.ad;

import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ad.ManufacturerSpecificData;
import org.im97mori.rbt.RbtConstants;

import static org.im97mori.rbt.RbtConstants.OutputRange.AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.ETVOC_UNIT;

/**
 * 3.1 Sensor data
 */
@SuppressWarnings("WeakerAccess")
public class SensorData extends AbstractRbtPacket implements Parcelable {

    /**
     * @see Creator
     */
    public static final Creator<SensorData> CREATOR = new Creator<SensorData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public SensorData createFromParcel(Parcel in) {
            return new SensorData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SensorData[] newArray(int size) {
            return new SensorData[size];
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
    public SensorData(byte[] data) {
        mSequenceNumber = createUInt8(1, data);
        mTemperature = createSInt16(2, data);
        mRelativeHumidity = createSInt16(4, data);
        mAmbientLight = createSInt16(6, data);
        mBarometricPressure = createSInt32(8, data);
        mSoundNoise = createSInt16(12, data);
        mEtvoc = createSInt16(14, data);
        mEco2 = createSInt16(16, data);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    public SensorData(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
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
        return mTemperature * RbtConstants.OutputRange.TEMPERATURE_UNIT;
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
        return mRelativeHumidity * RbtConstants.OutputRange.RELATIVE_HUMIDITY_UNIT;
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
        return mAmbientLight * AMBIENT_LIGHT_UNIT;
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
        return mBarometricPressure * RbtConstants.OutputRange.BAROMETRIC_PRESSURE_UNIT;
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
        return mSoundNoise * RbtConstants.OutputRange.SOUND_NOISE_UNIT;
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
        return mEtvoc * ETVOC_UNIT;
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
        return mEco2 * ECO2_UNIT;
    }

}
