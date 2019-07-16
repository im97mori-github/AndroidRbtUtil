package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_ACCELERATION_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_PGA_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_RELATIVE_HUMIDITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SEISMIC_INTENSITY_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_SI_VALUE_UNIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.OUTPUT_RANGE_TEMPERATURE_UNIT;

/**
 * 2.3.4 Acceleration memory data [Header] (Characteristics UUID: 0x5034)
 */
@SuppressWarnings("WeakerAccess")
public class AccelerationMemoryDataHeader2 extends AbstractCharacteristic implements Parcelable {

    /**
     * Total transfer count data error
     */
    public static final int DATA_ERROR_BIT = 0b10000000_00000000;

    /**
     * 0x0000: Fixed value
     */
    public static final int PAGE_NUMBER_FIXED_VALUE = 0x0000;

    /**
     * @see Creator
     */
    public static final Creator<AccelerationMemoryDataHeader2> CREATOR = new Creator<AccelerationMemoryDataHeader2>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader2 createFromParcel(Parcel in) {
            return new AccelerationMemoryDataHeader2(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AccelerationMemoryDataHeader2[] newArray(int size) {
            return new AccelerationMemoryDataHeader2[size];
        }
    };

    /**
     * Total transfer count
     */
    private final int mTotalTransferCount;

    /**
     * Page number
     */
    private final int mPageNumber;

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
     * Maximum acceleration (X-axis)
     */
    private final int mMaximuAccelerationXAxis;

    /**
     * Maximum acceleration (Y-axis)
     */
    private final int mMaximuAccelerationYAxis;

    /**
     * Maximum acceleration (Z-axis)
     */
    private final int mMaximuAccelerationZAxis;

    /**
     * Temperature
     */
    private final int mTemparature;

    /**
     * Relative humidity
     */
    private final int mRelativeHumidity;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5034
     */
    public AccelerationMemoryDataHeader2(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTotalTransferCount = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mPageNumber = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2);
        mSiValue = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 4);
        mPga = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 6);
        mSeismicIntensity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 8);
        mMaximuAccelerationXAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 10);
        mMaximuAccelerationYAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 12);
        mMaximuAccelerationZAxis = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 14);
        mTemparature = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 16);
        mRelativeHumidity = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, 18);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AccelerationMemoryDataHeader2(Parcel in) {
        mTotalTransferCount = in.readInt();
        mPageNumber = in.readInt();
        mSiValue = in.readInt();
        mPga = in.readInt();
        mSeismicIntensity = in.readInt();
        mMaximuAccelerationXAxis = in.readInt();
        mMaximuAccelerationYAxis = in.readInt();
        mMaximuAccelerationZAxis = in.readInt();
        mTemparature = in.readInt();
        mRelativeHumidity = in.readInt();
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
        dest.writeInt(mPageNumber);
        dest.writeInt(mSiValue);
        dest.writeInt(mPga);
        dest.writeInt(mSeismicIntensity);
        dest.writeInt(mMaximuAccelerationXAxis);
        dest.writeInt(mMaximuAccelerationYAxis);
        dest.writeInt(mMaximuAccelerationZAxis);
        dest.writeInt(mTemparature);
        dest.writeInt(mRelativeHumidity);
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
     * @return Page number
     */
    public int getPageNumber() {
        return mPageNumber;
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
     * @return Maximum acceleration (X-axis)
     */
    public int getMaximuAccelerationXAxis() {
        return mMaximuAccelerationXAxis;
    }

    /**
     * @return Maximum acceleration (X-axis)(gal)
     */
    public double getMaximuAccelerationXAxisGal() {
        return mMaximuAccelerationXAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Maximum acceleration (Y-axis)
     */
    public int getMaximuAccelerationYAxis() {
        return mMaximuAccelerationYAxis;
    }

    /**
     * @return Maximum acceleration (Y-axis)(gal)
     */
    public double getMaximuAccelerationYAxisGal() {
        return mMaximuAccelerationYAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Maximum acceleration (Z-axis)
     */
    public int getMaximuAccelerationZAxis() {
        return mMaximuAccelerationZAxis;
    }

    /**
     * @return Maximum acceleration (Z-axis)(gal)
     */
    public double getMaximuAccelerationZAxisGal() {
        return mMaximuAccelerationZAxis * OUTPUT_RANGE_ACCELERATION_UNIT;
    }

    /**
     * @return Temperature
     */
    public int getTemparature() {
        return mTemparature;
    }

    /**
     * @return Temperature(degC)
     */
    public double getTemparatureDegC() {
        return mTemparature * OUTPUT_RANGE_TEMPERATURE_UNIT;
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

}
