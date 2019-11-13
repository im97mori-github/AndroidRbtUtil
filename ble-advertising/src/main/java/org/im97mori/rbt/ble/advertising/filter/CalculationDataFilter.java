package org.im97mori.rbt.ble.advertising.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.rbt.ble.advertising.CalculationData;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;

/**
 * filter for {@link CalculationData}
 */
@SuppressWarnings("WeakerAccess")
public class CalculationDataFilter extends RbtDataFilter<Integer> {

    /**
     * expected {@link CalculationData} in Rbt's Advertising data
     */
    private CalculationData mExpect;

    /**
     * Constructor for complete comparison
     *
     * @param expect expected {@link CalculationData} in Rbt's Advertising data
     */
    public CalculationDataFilter(@Nullable CalculationData expect) {
        mExpect = expect;
    }

    /**
     * Constructor for partial comparison
     *
     * @param target check target
     * @param type   check type
     * @param value  check value
     */
    public CalculationDataFilter(int target, int type, int value) {
        super(target, type, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched(@NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
        boolean result = false;
        CalculationData actual = parseResult.getCalculationData();
        if (mExpect == null) {
            if (actual == null && mTarget == TARGET_NONE) {
                result = true;
            } else if (actual != null && mTarget != TARGET_NONE) {
                if (TARGET_SEQUENCE_NUMBER == mTarget) {
                    result = check(actual.getSequenceNumber());
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
