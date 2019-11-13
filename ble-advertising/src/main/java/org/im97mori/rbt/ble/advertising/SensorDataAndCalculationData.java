package org.im97mori.rbt.ble.advertising;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.advertising.ManufacturerSpecificData;
import org.im97mori.rbt.RbtConstants;

import java.util.Arrays;

import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_AMBIENT_LIGHT_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ECO2_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ETVOC_UNIT;

/**
 * 3.3 Sensor data & Calculation data (Scan rsp)
 */
public class SensorDataAndCalculationData implements RbtPacket, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<SensorDataAndCalculationData> CREATOR = new ByteArrayCreater<SensorDataAndCalculationData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SensorDataAndCalculationData createFromParcel(@NonNull Parcel in) {
            return new SensorDataAndCalculationData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SensorDataAndCalculationData[] newArray(int size) {
            return new SensorDataAndCalculationData[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        @Override
        public SensorDataAndCalculationData createFromByteArray(@NonNull byte[] values) {
            byte[] data1 = Arrays.copyOfRange(values, 0, 19);
            byte[] data2 = Arrays.copyOfRange(values, 19, 39);
            return new SensorDataAndCalculationData(data1, data2);
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
     * Constructor for SensorDataAndCalculationData
     *
     * @param data1 1st byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     * @param data2 2nd byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     */
    public SensorDataAndCalculationData(@NonNull byte[] data1, @NonNull byte[] data2) {
        mSequenceNumber = BLEUtils.createUInt8(data1, 1);
        mTemperature = BLEUtils.createSInt16(data1, 2);
        mRelativeHumidity = BLEUtils.createSInt16(data1, 4);
        mAmbientLight = BLEUtils.createSInt16(data1, 6);
        mBarometricPressure = BLEUtils.createSInt32(data1, 8);
        mSoundNoise = BLEUtils.createSInt16(data1, 12);
        mEtvoc = BLEUtils.createSInt16(data1, 14);
        mEco2 = BLEUtils.createSInt16(data1, 16);

        mDiscomfortIndex = BLEUtils.createSInt16(data2, 2);
        mHeatStroke = BLEUtils.createSInt16(data2, 4);
        mVibrationInformation = BLEUtils.createUInt8(data2, 6);
        mSiValue = BLEUtils.createUInt16(data2, 7);
        mPga = BLEUtils.createUInt16(data2, 9);
        mSeismicIntensity = BLEUtils.createUInt16(data2, 11);
        mAccelerationXAxis = BLEUtils.createSInt16(data2, 13);
        mAccelerationYAxis = BLEUtils.createSInt16(data2, 15);
        mAccelerationZAxis = BLEUtils.createSInt16(data2, 17);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private SensorDataAndCalculationData(@NonNull Parcel in) {
        mSequenceNumber = in.readInt();
        mTemperature = in.readInt();
        mRelativeHumidity = in.readInt();
        mAmbientLight = in.readInt();
        mBarometricPressure = in.readInt();
        mSoundNoise = in.readInt();
        mEtvoc = in.readInt();
        mEco2 = in.readInt();

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
        dest.writeInt(mTemperature);
        dest.writeInt(mRelativeHumidity);
        dest.writeInt(mAmbientLight);
        dest.writeInt(mBarometricPressure);
        dest.writeInt(mSoundNoise);
        dest.writeInt(mEtvoc);
        dest.writeInt(mEco2);

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
     * {@inheritDoc}
     */
    @Override
    public int getDataType() {
        return RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
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
        return mDiscomfortIndex * RbtConstants.OutputRange.OUTPUT_RANGE_DISCOMFORT_INDEX_UNIT;
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
        return mHeatStroke * RbtConstants.OutputRange.OUTPUT_RANGE_HEAT_STROKE_UNIT;
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
        return mSiValue * RbtConstants.OutputRange.OUTPUT_RANGE_SI_VALUE_UNIT;
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
        return mPga * RbtConstants.OutputRange.OUTPUT_RANGE_PGA_UNIT;
    }

    /**
     * @return Seismic intensity
     */
    public int getSeismicIntensity() {
        return mSeismicIntensity;
    }

    /**
     * @return Seismic intensity(unit 0.001)
     */
    public double getSeismicIntensityWithUnit() {
        return mSeismicIntensity * RbtConstants.OutputRange.OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT;
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
        return mAccelerationXAxis * RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;
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
        return mAccelerationYAxis * RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;
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
        return mAccelerationZAxis * RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;
    }

}
