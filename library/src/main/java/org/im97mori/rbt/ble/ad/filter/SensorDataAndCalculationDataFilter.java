package org.im97mori.rbt.ble.ad.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorDataAndCalculationData;

@SuppressWarnings("WeakerAccess")
public class SensorDataAndCalculationDataFilter extends RbtDataFilter<Integer> {

    /**
     * expected {@link SensorDataAndCalculationData} in Rbt's Advertising data
     */
    private SensorDataAndCalculationData mExpect;

    /**
     * Constructor for complete comparison
     *
     * @param expect expected {@link SensorDataAndCalculationData} in Rbt's Advertising data
     */
    public SensorDataAndCalculationDataFilter(@Nullable SensorDataAndCalculationData expect) {
        mExpect = expect;
    }

    /**
     * Constructor for partial comparison
     *
     * @param target check target
     * @param type   check type
     * @param value  check value
     */
    public SensorDataAndCalculationDataFilter(int target, int type, int value) {
        super(target, type, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched(@NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
        boolean result = false;
        SensorDataAndCalculationData actual = parseResult.getSensorDataAndCalculationData();
        if (mExpect == null) {
            if (actual == null && mTarget == TARGET_NONE) {
                result = true;
            } else if (actual != null && mTarget != TARGET_NONE) {
                if (TARGET_SEQUENCE_NUMBER == mTarget) {
                    result = check(actual.getSequenceNumber());
                } else if (TARGET_TEMPERATURE == mTarget) {
                    result = check(actual.getTemperature());
                } else if (TARGET_RELATIVE_HUMIDITY == mTarget) {
                    result = check(actual.getRelativeHumidity());
                } else if (TARGET_AMBIENT_LIGHT == mTarget) {
                    result = check(actual.getAmbientLight());
                } else if (TARGET_BAROMETRIC_PRESSURE == mTarget) {
                    result = check(actual.getBarometricPressure());
                } else if (TARGET_SOUND_NOISE == mTarget) {
                    result = check(actual.getSoundNoise());
                } else if (TARGET_ETVOC == mTarget) {
                    result = check(actual.getEtvoc());
                } else if (TARGET_ECO2 == mTarget) {
                    result = check(actual.getEco2());
                } else if (TARGET_DISCOMFORT_INDEX == mTarget) {
                    result = check(actual.getDiscomfortIndex());
                } else if (TARGET_HEAT_STROKE == mTarget) {
                    result = check(actual.getHeatStroke());
                } else if (TARGET_VIBRATION_INFORMATION == mTarget) {
                    result = check(actual.getVibrationInformation());
                } else if (TARGET_SI_VALUE == mTarget) {
                    result = check(actual.getSiValue());
                } else if (TARGET_PGA == mTarget) {
                    result = check(actual.getPga());
                } else if (TARGET_SEISMIC_INTENSITY == mTarget) {
                    result = check(actual.getSeismicIntensity());
                } else if (TARGET_ACCELERATION_X_AXIS == mTarget) {
                    result = check(actual.getAccelerationXAxis());
                } else if (TARGET_ACCELERATION_Y_AXIS == mTarget) {
                    result = check(actual.getAccelerationYAxis());
                } else if (TARGET_ACCELERATION_Z_AXIS == mTarget) {
                    result = check(actual.getAccelerationZAxis());
                }
            }
        } else if (actual != null) {
            if (mExpect.getDataType() == actual.getDataType()
                    && mExpect.getSequenceNumber() == actual.getSequenceNumber()
                    && mExpect.getDataType() == actual.getDataType()
                    && mExpect.getSequenceNumber() == actual.getSequenceNumber()
                    && mExpect.getTemperature() == actual.getTemperature()
                    && mExpect.getRelativeHumidity() == actual.getRelativeHumidity()
                    && mExpect.getAmbientLight() == actual.getAmbientLight()
                    && mExpect.getBarometricPressure() == actual.getBarometricPressure()
                    && mExpect.getSoundNoise() == actual.getSoundNoise()
                    && mExpect.getEtvoc() == actual.getEtvoc()
                    && mExpect.getEco2() == actual.getEco2()
                    && mExpect.getDiscomfortIndex() == actual.getDiscomfortIndex()
                    && mExpect.getHeatStroke() == actual.getHeatStroke()
                    && mExpect.getVibrationInformation() == actual.getVibrationInformation()
                    && mExpect.getSiValue() == actual.getSiValue()
                    && mExpect.getPga() == actual.getPga()
                    && mExpect.getSeismicIntensity() == actual.getSeismicIntensity()
                    && mExpect.getAccelerationXAxis() == actual.getAccelerationXAxis()
                    && mExpect.getAccelerationYAxis() == actual.getAccelerationYAxis()
                    && mExpect.getAccelerationZAxis() == actual.getAccelerationZAxis()) {
                result = true;
            }
        }
        return result;
    }

}
