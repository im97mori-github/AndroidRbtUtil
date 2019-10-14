package org.im97mori.rbt.ble.ad.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorData;

@SuppressWarnings("WeakerAccess")
public class SensorDataFilter extends RbtDataFilter {

    /**
     * expected {@link SensorData} in Rbt's Advertising data
     */
    private SensorData mExpect;

    /**
     * Constructor for complete comparison
     *
     * @param expect expected {@link SensorData} in Rbt's Advertising data
     */
    public SensorDataFilter(@Nullable SensorData expect) {
        mExpect = expect;
    }

    /**
     * Constructor for partial comparison
     *
     * @param target check target
     * @param type   check type
     * @param value  check value
     */
    public SensorDataFilter(int target, int type, int value) {
        super(target, type, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched(@NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult) {
        boolean result = false;

        SensorData actual = rbtAdvertisingDataParseResult.getSensorData();
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
                }
            }
        } else if (actual != null) {
            if (mExpect.getDataType() == actual.getDataType()
                    && mExpect.getSequenceNumber() == actual.getSequenceNumber()
                    && mExpect.getTemperature() == actual.getTemperature()
                    && mExpect.getRelativeHumidity() == actual.getRelativeHumidity()
                    && mExpect.getAmbientLight() == actual.getAmbientLight()
                    && mExpect.getBarometricPressure() == actual.getBarometricPressure()
                    && mExpect.getSoundNoise() == actual.getSoundNoise()
                    && mExpect.getEtvoc() == actual.getEtvoc()
                    && mExpect.getEco2() == actual.getEco2()) {
                result = true;
            }
        }
        return result;
    }

}
