package org.im97mori.rbt.ble.ad;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ad.ManufacturerSpecificData;
import org.im97mori.rbt.RbtConstants;

/**
 * 3.2 Calculation data
 */
public class CalculationData extends AbstractRbtPacket implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<CalculationData> CREATOR = new ByteArrayCreater<CalculationData>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public CalculationData createFromParcel(@NonNull Parcel in) {
            return new CalculationData(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public CalculationData[] newArray(int size) {
            return new CalculationData[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        @Override
        public CalculationData createFromByteArray(@NonNull byte[] values) {
            return new CalculationData(values);
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
     * Constructor for CalculationData
     *
     * @param data byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     */
    public CalculationData(@NonNull byte[] data) {
        mSequenceNumber = createUInt8(1, data);
        mDiscomfortIndex = createSInt16(2, data);
        mHeatStroke = createSInt16(4, data);
        mVibrationInformation = createUInt8(6, data);
        mSiValue = createUInt16(7, data);
        mPga = createUInt16(9, data);
        mSeismicIntensity = createUInt16(11, data);
        mAccelerationXAxis = createSInt16(13, data);
        mAccelerationYAxis = createSInt16(15, data);
        mAccelerationZAxis = createSInt16(17, data);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private CalculationData(@NonNull Parcel in) {
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
     * {@inheritDoc}
     */
    @Override
    public int getDataType() {
        return RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
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
