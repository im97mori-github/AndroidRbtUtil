package org.im97mori.rbt.ble.ad.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorFlagAndCalculationFlag;

@SuppressWarnings("WeakerAccess")
public class SensorFlagAndCalculationFlagFilter extends RbtDataFilter<Integer> {

    /**
     * expected {@link SensorFlagAndCalculationFlag} in Rbt's Advertising data
     */
    private SensorFlagAndCalculationFlag mExpect;

    /**
     * Constructor for complete comparison
     *
     * @param expect expected {@link SensorFlagAndCalculationFlag} in Rbt's Advertising data
     */
    public SensorFlagAndCalculationFlagFilter(@Nullable SensorFlagAndCalculationFlag expect) {
        mExpect = expect;
    }

    /**
     * Constructor for partial comparison
     *
     * @param target check target
     * @param type   check type
     * @param value  check value
     */
    public SensorFlagAndCalculationFlagFilter(int target, int type, int value) {
        super(target, type, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched(@NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
        boolean result = false;
        SensorFlagAndCalculationFlag actual = parseResult.getSensorFlagAndCalculationFlag();
        if (mExpect == null) {
            if (actual == null && mTarget == TARGET_NONE) {
                result = true;
            } else if (actual != null && mTarget != TARGET_NONE) {
                if (TARGET_SEQUENCE_NUMBER == mTarget) {
                    result = check(actual.getSequenceNumber());
                } else if (TARGET_TEMPERATURE == mTarget) {
                    result = check(actual.getTemperatureFlag());
                } else if (TARGET_RELATIVE_HUMIDITY == mTarget) {
                    result = check(actual.getRelativeHumidityFlag());
                } else if (TARGET_AMBIENT_LIGHT == mTarget) {
                    result = check(actual.getAmbientLightFlag());
                } else if (TARGET_BAROMETRIC_PRESSURE == mTarget) {
                    result = check(actual.getBarometricPressureFlag());
                } else if (TARGET_SOUND_NOISE == mTarget) {
                    result = check(actual.getSoundNoiseFlag());
                } else if (TARGET_ETVOC == mTarget) {
                    result = check(actual.getEtvocFlag());
                } else if (TARGET_ECO2 == mTarget) {
                    result = check(actual.getEco2Flag());
                } else if (TARGET_DISCOMFORT_INDEX == mTarget) {
                    result = check(actual.getDiscomfortIndexFlag());
                } else if (TARGET_HEAT_STROKE == mTarget) {
                    result = check(actual.getHeatStrokeFlag());
                } else if (TARGET_SI_VALUE == mTarget) {
                    result = check(actual.getSiValueFlag());
                } else if (TARGET_PGA == mTarget) {
                    result = check(actual.getPgaFlag());
                } else if (TARGET_SEISMIC_INTENSITY == mTarget) {
                    result = check(actual.getSeismicIntensityFlag());
                }
            }
        } else if (actual != null) {
            if (mExpect.getDataType() == actual.getDataType()
                    && mExpect.getSequenceNumber() == actual.getSequenceNumber()
                    && mExpect.getTemperatureFlag() == actual.getTemperatureFlag()
                    && mExpect.getRelativeHumidityFlag() == actual.getRelativeHumidityFlag()
                    && mExpect.getAmbientLightFlag() == actual.getAmbientLightFlag()
                    && mExpect.getBarometricPressureFlag() == actual.getBarometricPressureFlag()
                    && mExpect.getSoundNoiseFlag() == actual.getSoundNoiseFlag()
                    && mExpect.getEtvocFlag() == actual.getEtvocFlag()
                    && mExpect.getEco2Flag() == actual.getEco2Flag()
                    && mExpect.getDiscomfortIndexFlag() == actual.getDiscomfortIndexFlag()
                    && mExpect.getHeatStrokeFlag() == actual.getHeatStrokeFlag()
                    && mExpect.getSiValueFlag() == actual.getSiValueFlag()
                    && mExpect.getPgaFlag() == actual.getPgaFlag()
                    && mExpect.getSeismicIntensityFlag() == actual.getSeismicIntensityFlag()) {
                result = true;
            }
        }
        return result;
    }

}
