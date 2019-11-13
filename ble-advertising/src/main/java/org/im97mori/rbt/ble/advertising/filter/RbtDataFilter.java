package org.im97mori.rbt.ble.advertising.filter;

import androidx.annotation.NonNull;

import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.rbt.ble.advertising.CalculationData;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.advertising.SensorData;
import org.im97mori.rbt.ble.advertising.SensorDataAndCalculationData;
import org.im97mori.rbt.ble.advertising.SensorFlagAndCalculationFlag;
import org.im97mori.rbt.ble.advertising.SerialNumber;

/**
 * filter interface for Rbt's Advertising data
 */
@SuppressWarnings("WeakerAccess")
public abstract class RbtDataFilter<T2 extends Comparable<T2>> implements AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> {

    /**
     * non-partial comparison
     */
    public static final int TARGET_NONE = 0;

    /**
     * check target : Sequence Number
     *
     * @see SensorData#getSequenceNumber()
     * @see CalculationData#getSequenceNumber()
     * @see SensorDataAndCalculationData#getSequenceNumber()
     * @see SensorFlagAndCalculationFlag#getSequenceNumber()
     */
    public static final int TARGET_SEQUENCE_NUMBER = TARGET_NONE + 1;

    /**
     * check target : Temperature / Temperature flag
     *
     * @see SensorData#getTemperature()
     * @see SensorDataAndCalculationData#getTemperature()
     * @see SensorFlagAndCalculationFlag#getTemperatureFlag()
     */
    public static final int TARGET_TEMPERATURE = TARGET_SEQUENCE_NUMBER + 1;

    /**
     * check target : Relative humidity / Relative humidity flag
     *
     * @see SensorData#getRelativeHumidity()
     * @see SensorDataAndCalculationData#getRelativeHumidity()
     * @see SensorFlagAndCalculationFlag#getRelativeHumidityFlag()
     */
    public static final int TARGET_RELATIVE_HUMIDITY = TARGET_TEMPERATURE + 1;

    /**
     * check target : Ambient light / Ambient light flag
     *
     * @see SensorData#getAmbientLight()
     * @see SensorDataAndCalculationData#getAmbientLight()
     * @see SensorFlagAndCalculationFlag#getAmbientLightFlag()
     */
    public static final int TARGET_AMBIENT_LIGHT = TARGET_RELATIVE_HUMIDITY + 1;

    /**
     * check target : Barometric pressure / Barometric pressure flag
     *
     * @see SensorData#getBarometricPressure()
     * @see SensorDataAndCalculationData#getBarometricPressure()
     * @see SensorFlagAndCalculationFlag#getBarometricPressureFlag()
     */
    public static final int TARGET_BAROMETRIC_PRESSURE = TARGET_AMBIENT_LIGHT + 1;

    /**
     * check target : Sound noise / Sound noise flag
     *
     * @see SensorData#getSoundNoise()
     * @see SensorDataAndCalculationData#getSoundNoise()
     * @see SensorFlagAndCalculationFlag#getSoundNoiseFlag()
     */
    public static final int TARGET_SOUND_NOISE = TARGET_BAROMETRIC_PRESSURE + 1;

    /**
     * check target : eTVOC / eTVOC flag
     *
     * @see SensorData#getEtvoc()
     * @see SensorDataAndCalculationData#getEtvoc()
     * @see SensorFlagAndCalculationFlag#getEtvocFlag()
     */
    public static final int TARGET_ETVOC = TARGET_SOUND_NOISE + 1;

    /**
     * check target : eCO2 / eCO2 flag
     *
     * @see SensorData#getEco2()
     * @see SensorDataAndCalculationData#getEco2()
     * @see SensorFlagAndCalculationFlag#getEco2Flag()
     */
    public static final int TARGET_ECO2 = TARGET_ETVOC + 1;

    /**
     * check target : Discomfort index / Discomfort index flag
     *
     * @see CalculationData#getDiscomfortIndex()
     * @see SensorDataAndCalculationData#getDiscomfortIndex()
     * @see SensorFlagAndCalculationFlag#getDiscomfortIndexFlag()
     */
    public static final int TARGET_DISCOMFORT_INDEX = TARGET_ECO2 + 1;

    /**
     * check target : Heat stroke / Heat stroke flag
     *
     * @see CalculationData#getHeatStroke()
     * @see SensorDataAndCalculationData#getHeatStroke()
     * @see SensorFlagAndCalculationFlag#getHeatStrokeFlag()
     */
    public static final int TARGET_HEAT_STROKE = TARGET_DISCOMFORT_INDEX + 1;

    /**
     * check target : Vibration information
     *
     * @see CalculationData#getVibrationInformation()
     * @see SensorDataAndCalculationData#getVibrationInformation()
     */
    public static final int TARGET_VIBRATION_INFORMATION = TARGET_HEAT_STROKE + 1;

    /**
     * check target : SI value / SI value flag
     *
     * @see CalculationData#getSiValue()
     * @see SensorDataAndCalculationData#getSiValue()
     * @see SensorFlagAndCalculationFlag#getSiValueFlag()
     */
    public static final int TARGET_SI_VALUE = TARGET_VIBRATION_INFORMATION + 1;

    /**
     * check target : PGA / PGA flag
     *
     * @see CalculationData#getPga()
     * @see SensorDataAndCalculationData#getPga()
     * @see SensorFlagAndCalculationFlag#getPgaFlag()
     */
    public static final int TARGET_PGA = TARGET_SI_VALUE + 1;

    /**
     * check target : Seismic intensity / Seismic intensity flag
     *
     * @see CalculationData#getSeismicIntensity()
     * @see SensorDataAndCalculationData#getSeismicIntensity()
     * @see SensorFlagAndCalculationFlag#getSeismicIntensityFlag()
     */
    public static final int TARGET_SEISMIC_INTENSITY = TARGET_PGA + 1;

    /**
     * check target : Acceleration (X-axis)
     *
     * @see CalculationData#getAccelerationXAxis()
     * @see SensorDataAndCalculationData#getAccelerationXAxis()
     */
    public static final int TARGET_ACCELERATION_X_AXIS = TARGET_SEISMIC_INTENSITY + 1;

    /**
     * check target : Acceleration (Y-axis)
     *
     * @see CalculationData#getAccelerationYAxis()
     * @see SensorDataAndCalculationData#getAccelerationYAxis()
     */
    public static final int TARGET_ACCELERATION_Y_AXIS = TARGET_ACCELERATION_X_AXIS + 1;

    /**
     * check target : Acceleration (Z-axis)
     *
     * @see CalculationData#getAccelerationZAxis()
     * @see SensorDataAndCalculationData#getAccelerationZAxis()
     */
    public static final int TARGET_ACCELERATION_Z_AXIS = TARGET_ACCELERATION_Y_AXIS + 1;

    /**
     * check target : Memory index (Latest)
     *
     * @see SerialNumber#getMemoryIndex()
     */
    public static final int TARGET_MEMORY_INDEX = TARGET_ACCELERATION_Z_AXIS + 1;

    /**
     * check type : =
     */
    public static final int TYPE_EQUAL = 0;

    /**
     * check type : %gt;
     */
    public static final int TYPE_GREATER_THAN = TYPE_EQUAL + 1;

    /**
     * check type : %gt;=
     */
    public static final int TYPE_GREATER_EQUAL = TYPE_GREATER_THAN + 1;

    /**
     * check type : %lt;
     */
    public static final int TYPE_LESSER_THAN = TYPE_GREATER_EQUAL + 1;

    /**
     * check type : %lt;=
     */
    public static final int TYPE_LESSER_EQUAL = TYPE_LESSER_THAN + 1;

    /**
     * check type : bit of Simple threshold [upper limit] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#SIMPLE_THRESHOLD_UPPER_LIMIT_1
     */
    public static final int TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR = TYPE_LESSER_EQUAL + 1;

    /**
     * check type : bit of Simple threshold [upper limit] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#SIMPLE_THRESHOLD_UPPER_LIMIT_2
     */
    public static final int TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR + 1;

    /**
     * check type : bit of Simple threshold [lower limit] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#SIMPLE_THRESHOLD_LOWER_LIMIT_1
     */
    public static final int TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR + 1;

    /**
     * check type : bit of Simple threshold [lower limit] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#SIMPLE_THRESHOLD_LOWER_LIMIT_2
     */
    public static final int TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR + 1;

    /**
     * check type : bit of Change threshold [rise] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#CHANGE_THRESHOLD_RISE_1
     */
    public static final int TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR = TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR + 1;

    /**
     * check type : bit of Change threshold [rise] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#CHANGE_THRESHOLD_RISE_2
     */
    public static final int TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR = TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR + 1;

    /**
     * check type : bit of Change threshold [decline] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#CHANGE_THRESHOLD_DECLINE_1
     */
    public static final int TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR = TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR + 1;

    /**
     * check type : bit of Change threshold [decline] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#CHANGE_THRESHOLD_DECLINE_2
     */
    public static final int TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR = TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR + 1;

    /**
     * check type : bit of Average value threshold [upper]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#AVERAGE_VALUE_THRESHOLD_UPPER
     */
    public static final int TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR = TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR + 1;

    /**
     * check type : bit of Average value threshold [lower]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#AVERAGE_VALUE_THRESHOLD_LOWER
     */
    public static final int TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR = TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR + 1;

    /**
     * check type : bit of Peak to Peak threshold [upper]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#PEAK_TO_PEAK_THRESHOLD_UPPER
     */
    public static final int TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR = TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR + 1;

    /**
     * check type : bit of Peak to Peak threshold [lower]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#PEAK_TO_PEAK_THRESHOLD_LOWER
     */
    public static final int TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR = TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR + 1;

    /**
     * check type : bit of Interval differencethreshold [rise]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#INTERVAL_DIFFERENCE_THRESHOLD_RISE
     */
    public static final int TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR = TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR + 1;

    /**
     * check type : bit of Interval differencethreshold [decline]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#INTERVAL_DIFFERENCE_THRESHOLD_DECLINE
     */
    public static final int TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR + 1;

    /**
     * check type : bit of Base difference threshold [upper]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#BASE_DIFFERENCE_THRESHOLD_UPPER
     */
    public static final int TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR = TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR + 1;

    /**
     * check type : bit of Base difference threshold [lower]
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor#BASE_DIFFERENCE_THRESHOLD_LOWER
     */
    public static final int TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR = TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR + 1;

    /**
     * check type : bit of Simple threshold [upper limit] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagAcceleration#SIMPLE_THRESHOLD_UPPER_LIMIT_1
     */
    public static final int TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION = TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR + 1;

    /**
     * check type : bit of Simple threshold [upper limit] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagAcceleration#SIMPLE_THRESHOLD_UPPER_LIMIT_2
     */
    public static final int TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION + 1;

    /**
     * check type : bit of Change threshold [rise] 1
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagAcceleration#CHANGE_THRESHOLD_RISE_1
     */
    public static final int TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION = TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION + 1;

    /**
     * check type : bit of Change threshold [rise] 2
     *
     * @see org.im97mori.rbt.RbtConstants.EventFlagAcceleration#CHANGE_THRESHOLD_RISE_2
     */
    public static final int TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION = TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION + 1;

    /**
     * check target
     */
    protected int mTarget = TARGET_NONE;

    /**
     * check type
     */
    protected int mType;

    /**
     * check value
     */
    protected T2 mValue;

    /**
     * Constructor for complete comparison
     */
    protected RbtDataFilter() {
    }

    /**
     * Constructor for partial comparison
     *
     * @param target check target
     * @param type   check type
     * @param value  check value
     */
    protected RbtDataFilter(int target, int type, @NonNull T2 value) {
        mTarget = target;
        mType = type;
        mValue = value;
    }

    /**
     * partial check
     *
     * @param actual comparison value
     * @return {@code true}:condition has been met, {@code false}:not met
     */
    protected boolean check(T2 actual) {
        boolean result = false;
        if (TYPE_EQUAL == mType) {
            if (actual.compareTo(mValue) == 0) {
                result = true;
            }
        } else if (TYPE_GREATER_THAN == mType) {
            if (actual.compareTo(mValue) > 0) {
                result = true;
            }
        } else if (TYPE_GREATER_EQUAL == mType) {
            if (actual.compareTo(mValue) >= 0) {
                result = true;
            }
        } else if (TYPE_LESSER_THAN == mType) {
            if (actual.compareTo(mValue) < 0) {
                result = true;
            }
        } else if (TYPE_LESSER_EQUAL == mType) {
            if (actual.compareTo(mValue) <= 0) {
                result = true;
            }
        } else {
            if (actual instanceof Number && mValue instanceof Number && (((Number) actual).longValue() & ((Number) mValue).longValue()) != 0) {
                // all of bit check
                result = true;
            }
        }
        return result;
    }

}
