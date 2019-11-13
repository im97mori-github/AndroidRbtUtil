package org.im97mori.rbt.ble.advertising.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.ble.advertising.filter.OrFilter;
import org.im97mori.rbt.RbtConstants;
import org.im97mori.rbt.ble.advertising.CalculationData;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.advertising.SensorData;
import org.im97mori.rbt.ble.advertising.SensorDataAndCalculationData;
import org.im97mori.rbt.ble.advertising.SensorFlagAndCalculationFlag;
import org.im97mori.rbt.ble.advertising.SerialNumber;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_ACCELERATION_X_AXIS;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_ACCELERATION_Y_AXIS;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_ACCELERATION_Z_AXIS;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_AMBIENT_LIGHT;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_BAROMETRIC_PRESSURE;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_DISCOMFORT_INDEX;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_ECO2;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_ETVOC;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_HEAT_STROKE;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_PGA;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_RELATIVE_HUMIDITY;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_SEISMIC_INTENSITY;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_SEQUENCE_NUMBER;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_SI_VALUE;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_SOUND_NOISE;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TARGET_TEMPERATURE;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION;
import static org.im97mori.rbt.ble.advertising.filter.RbtDataFilter.TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR;

/**
 * Builder for {@link FilteredRbtLeScanCallback} or {@link FilteredRbtScanCallback} with filter function
 *
 * @param <T> instance of {@link FilteredRbtLeScanCallback} or {@link FilteredRbtScanCallback}
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractRbtFilterBuilder<T> {

    /**
     * filter list
     */
    protected final List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> mFilterList = new LinkedList<>();

    /**
     * {@link RbtAdvertisingDataParser} instance for parse
     */
    protected RbtAdvertisingDataParser mRbtAdvertisingDataParser;

    /**
     * @param filter filter
     * @return myself
     */
    @NonNull
    public AbstractRbtFilterBuilder<T> addFilter(@NonNull RbtDataFilter<?> filter) {
        mFilterList.add(filter);
        return this;
    }

    /**
     * @see #addFilters(List)
     */
    @NonNull
    public AbstractRbtFilterBuilder<T> addFilters(@NonNull RbtDataFilter<?>... filters) {
        return addFilters(Arrays.asList(filters));
    }

    /**
     * @param filterList filter list
     * @return myself
     */
    @NonNull
    public AbstractRbtFilterBuilder<T> addFilters(@NonNull List<RbtDataFilter<?>> filterList) {
        mFilterList.addAll(filterList);
        return this;
    }

    /**
     * clear current filter list
     *
     * @return myself
     */
    @NonNull
    public AbstractRbtFilterBuilder<T> clearFilter() {
        mFilterList.clear();
        return this;
    }

    /**
     * <p>
     * set original parser
     * if not set, {@link org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser.Builder} with {@code true} will use
     * </p>
     *
     * @param parser {@link org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser} instance for parse
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> setRbtAdvertisingDataParser(RbtAdvertisingDataParser parser) {
        mRbtAdvertisingDataParser = parser;
        return this;
    }

    /**
     * @see #addSensorDataFilter(SensorData)
     */
    public AbstractRbtFilterBuilder<T> addSensorDataFilter(@NonNull byte[] data) {
        return addSensorDataFilter(new SensorData(data));
    }

    /**
     * <p>
     * add Sensor data filter
     * complete comparison
     * </p>
     *
     * @param expect {@link SensorData}
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> addSensorDataFilter(@NonNull SensorData expect) {
        mFilterList.add(new SensorDataFilter(expect));
        return this;
    }

    /**
     * @see #addCalculationDataFilter(CalculationData)
     */
    public AbstractRbtFilterBuilder<T> addCalculationDataFilter(@NonNull byte[] data) {
        return addCalculationDataFilter(new CalculationData(data));
    }

    /**
     * <p>
     * add Calculation data filter
     * complete comparison
     * </p>
     *
     * @param expect {@link CalculationData}
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> addCalculationDataFilter(@NonNull CalculationData expect) {
        mFilterList.add(new CalculationDataFilter(expect));
        return this;
    }

    /**
     * @param data serialized Sensor data & Calculation data (Scan rsp) data array
     * @see #addSensorDataAndCalculationDataFilter(SensorDataAndCalculationData)
     */
    public AbstractRbtFilterBuilder<T> addSensorDataAndCalculationDataFilter(@NonNull byte[] data) {
        return addSensorDataAndCalculationDataFilter(SensorDataAndCalculationData.CREATOR.createFromByteArray(data));
    }

    /**
     * @param data1 Sensor data (Scan rsp) data array
     * @param data2 Calculation data (Scan rsp) data array
     * @see #addSensorDataAndCalculationDataFilter(SensorDataAndCalculationData)
     */
    public AbstractRbtFilterBuilder<T> addSensorDataAndCalculationDataFilter(@NonNull byte[] data1, byte[] data2) {
        return addSensorDataAndCalculationDataFilter(new SensorDataAndCalculationData(data1, data2));
    }

    /**
     * <p>
     * add Sensor data & Calculation data (Scan rsp) filter
     * complete comparison
     * </p>
     *
     * @param expect {@link SensorDataAndCalculationData} instance
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> addSensorDataAndCalculationDataFilter(@NonNull SensorDataAndCalculationData expect) {
        mFilterList.add(new SensorDataAndCalculationDataFilter(expect));
        return this;
    }

    /**
     * @param data serialized Sensor flag & Calculation flag (Scan rsp) data array
     * @see #addSensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag)
     */
    public AbstractRbtFilterBuilder<T> addSensorFlagAndCalculationFlagFilter(@NonNull byte[] data) {
        return addSensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag.CREATOR.createFromByteArray(data));
    }

    /**
     * @param data1 Sensor flag (Scan rsp) data array
     * @param data2 Calculation flag (Scan rsp) data array
     * @see #addSensorFlagAndCalculationFlagFilter(SensorFlagAndCalculationFlag)
     */
    public AbstractRbtFilterBuilder<T> addSensorFlagAndCalculationFlagFilter(@NonNull byte[] data1, byte[] data2) {
        return addSensorFlagAndCalculationFlagFilter(new SensorFlagAndCalculationFlag(data1, data2));
    }

    /**
     * <p>
     * add Sensor flag & Calculation flag (Scan rsp) filter
     * complete comparison
     * </p>
     *
     * @param expect {@link SensorFlagAndCalculationFlag} instance
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> addSensorFlagAndCalculationFlagFilter(@NonNull SensorFlagAndCalculationFlag expect) {
        mFilterList.add(new SensorFlagAndCalculationFlagFilter(expect));
        return this;
    }

    /**
     * @param data Serial number data array
     * @see #addSerialNumberFilter(SerialNumber)
     */
    public AbstractRbtFilterBuilder<T> addSerialNumberFilter(@NonNull byte[] data) {
        return addSerialNumberFilter(new SerialNumber(data));
    }

    /**
     * @see #addSerialNumberFilter(SerialNumber)
     */
    public AbstractRbtFilterBuilder<T> addSerialNumberFilter(@NonNull SerialNumber expect) {
        return addSerialNumberFilter(expect, null);
    }

    /**
     * @see #addSerialNumberFilter(SerialNumber)
     */
    public AbstractRbtFilterBuilder<T> addSerialNumberFilter(@NonNull Pattern pattern) {
        return addSerialNumberFilter(null, pattern);
    }

    /**
     * <p>
     * add Serial number filter
     * complete comparison
     * </p>
     *
     * @param expect  {@link SerialNumber} instance
     * @param pattern {@link Pattern} instance
     * @return myself
     */
    public AbstractRbtFilterBuilder<T> addSerialNumberFilter(@Nullable SerialNumber expect, @Nullable Pattern pattern) {
        mFilterList.add(new SerialNumberFilter(expect, pattern));
        return this;
    }

    /**
     * <p>
     * add Sequence Number filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getSequenceNumber()
     * @see CalculationData#getSequenceNumber()
     * @see SensorDataAndCalculationData#getSequenceNumber()
     * @see SensorFlagAndCalculationFlag#getSequenceNumber()
     */
    public AbstractRbtFilterBuilder<T> addSequenceNumberFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_SEQUENCE_NUMBER, type, value), new CalculationDataFilter(TARGET_SEQUENCE_NUMBER, type, value), new SensorDataAndCalculationDataFilter(TARGET_SEQUENCE_NUMBER, type, value), new SensorFlagAndCalculationFlagFilter(TARGET_SEQUENCE_NUMBER, type, value)));
        return this;
    }

    /**
     * <p>
     * add Temperature filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getTemperature()
     * @see SensorDataAndCalculationData#getTemperature()
     */
    public AbstractRbtFilterBuilder<T> addTemperatureFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_TEMPERATURE, type, value), new SensorDataAndCalculationDataFilter(TARGET_TEMPERATURE, type, value)));
        return this;
    }

    /**
     * <p>
     * add Relative humidity filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getRelativeHumidity()
     * @see SensorDataAndCalculationData#getRelativeHumidity()
     */
    public AbstractRbtFilterBuilder<T> addRelativeHumidityFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_RELATIVE_HUMIDITY, type, value), new SensorDataAndCalculationDataFilter(TARGET_RELATIVE_HUMIDITY, type, value)));
        return this;
    }

    /**
     * <p>
     * add Ambient light filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getAmbientLight()
     * @see SensorDataAndCalculationData#getAmbientLight()
     */
    public AbstractRbtFilterBuilder<T> addAmbientLightFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_AMBIENT_LIGHT, type, value), new SensorDataAndCalculationDataFilter(TARGET_AMBIENT_LIGHT, type, value)));
        return this;
    }

    /**
     * <p>
     * add Barometric pressure filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getBarometricPressure()
     * @see SensorDataAndCalculationData#getBarometricPressure()
     */
    public AbstractRbtFilterBuilder<T> addBarometricPressureFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_BAROMETRIC_PRESSURE, type, value), new SensorDataAndCalculationDataFilter(TARGET_BAROMETRIC_PRESSURE, type, value)));
        return this;
    }

    /**
     * <p>
     * add Sound noise filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getSoundNoise()
     * @see SensorDataAndCalculationData#getSoundNoise()
     */
    public AbstractRbtFilterBuilder<T> addSoundNoiseFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_SOUND_NOISE, type, value), new SensorDataAndCalculationDataFilter(TARGET_SOUND_NOISE, type, value)));
        return this;
    }

    /**
     * <p>
     * add eTVOC filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getEtvoc()
     * @see SensorDataAndCalculationData#getEtvoc()
     */
    public AbstractRbtFilterBuilder<T> addEtvocFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_ETVOC, type, value), new SensorDataAndCalculationDataFilter(TARGET_ETVOC, type, value)));
        return this;
    }

    /**
     * <p>
     * add eCO2 filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SensorData#getEco2()
     * @see SensorDataAndCalculationData#getEco2()
     */
    public AbstractRbtFilterBuilder<T> addEco2Filter(int type, int value) {
        mFilterList.add(new OrFilter<>(new SensorDataFilter(TARGET_ECO2, type, value), new SensorDataAndCalculationDataFilter(TARGET_ECO2, type, value)));
        return this;
    }

    /**
     * <p>
     * add Discomfort index filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getDiscomfortIndex()
     * @see SensorDataAndCalculationData#getDiscomfortIndex()
     */
    public AbstractRbtFilterBuilder<T> addDiscomfortIndexFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_DISCOMFORT_INDEX, type, value), new SensorDataAndCalculationDataFilter(TARGET_DISCOMFORT_INDEX, type, value)));
        return this;
    }

    /**
     * <p>
     * add Heat stroke filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getHeatStroke()
     * @see SensorDataAndCalculationData#getHeatStroke()
     */
    public AbstractRbtFilterBuilder<T> addHeatStrokeFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_HEAT_STROKE, type, value), new SensorDataAndCalculationDataFilter(TARGET_HEAT_STROKE, type, value)));
        return this;
    }

    /**
     * <p>
     * add SI value filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getSiValue()
     * @see SensorDataAndCalculationData#getSiValue()
     */
    public AbstractRbtFilterBuilder<T> addSiValueFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_SI_VALUE, type, value), new SensorDataAndCalculationDataFilter(TARGET_SI_VALUE, type, value)));
        return this;
    }

    /**
     * <p>
     * add PGA filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getPga()
     * @see SensorDataAndCalculationData#getPga()
     */
    public AbstractRbtFilterBuilder<T> addPgaFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_PGA, type, value), new SensorDataAndCalculationDataFilter(TARGET_PGA, type, value)));
        return this;
    }

    /**
     * <p>
     * add Seismic intensity filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getSeismicIntensity()
     * @see SensorDataAndCalculationData#getSeismicIntensity()
     */
    public AbstractRbtFilterBuilder<T> addSeismicIntensityFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_SEISMIC_INTENSITY, type, value), new SensorDataAndCalculationDataFilter(TARGET_SEISMIC_INTENSITY, type, value)));
        return this;
    }

    /**
     * <p>
     * add Acceleration (X-axis) filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getAccelerationXAxis()
     * @see SensorDataAndCalculationData#getAccelerationXAxis()
     */
    public AbstractRbtFilterBuilder<T> addAccelerationXAxisFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_ACCELERATION_X_AXIS, type, value), new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_X_AXIS, type, value)));
        return this;
    }

    /**
     * <p>
     * add Acceleration (Y-axis) filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getAccelerationYAxis()
     * @see SensorDataAndCalculationData#getAccelerationYAxis()
     */
    public AbstractRbtFilterBuilder<T> addAccelerationYAxisFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, type, value), new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Y_AXIS, type, value)));
        return this;
    }

    /**
     * <p>
     * add Acceleration (Z-axis) filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see CalculationData#getAccelerationZAxis()
     * @see SensorDataAndCalculationData#getAccelerationZAxis()
     */
    public AbstractRbtFilterBuilder<T> addAccelerationZAxisFilter(int type, int value) {
        mFilterList.add(new OrFilter<>(new CalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, type, value), new SensorDataAndCalculationDataFilter(TARGET_ACCELERATION_Z_AXIS, type, value)));
        return this;
    }

    /**
     * <p>
     * add Memory index (Latest) filter
     * partial comparison
     * </p>
     *
     * @param type  check target
     * @param value check value
     * @return myself
     * @see SerialNumber#getMemoryIndex()
     */
    public AbstractRbtFilterBuilder<T> addMemoryIndexFilter(int type, Long value) {
        mFilterList.add(new SerialNumberFilter(type, value));
        return this;
    }

    /**
     * <p>
     * add Temperature flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getTemperatureFlag()
     */
    public AbstractRbtFilterBuilder<T> addTemperatureFlagFilter(int type) {
        return addFlagFilter(TARGET_TEMPERATURE, type);
    }

    /**
     * <p>
     * add Relative humidity flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getRelativeHumidityFlag()
     */
    public AbstractRbtFilterBuilder<T> addRelativeHumidityFlagFilter(int type) {
        return addFlagFilter(TARGET_RELATIVE_HUMIDITY, type);
    }

    /**
     * <p>
     * add Ambient light flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getAmbientLightFlag()
     */
    public AbstractRbtFilterBuilder<T> addAmbientLightFlagFilter(int type) {
        return addFlagFilter(TARGET_AMBIENT_LIGHT, type);
    }

    /**
     * <p>
     * add Barometric pressure flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getBarometricPressureFlag()
     */
    public AbstractRbtFilterBuilder<T> addBarometricPressureFlagFilter(int type) {
        return addFlagFilter(TARGET_BAROMETRIC_PRESSURE, type);
    }

    /**
     * <p>
     * add Sound noise flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getSoundNoiseFlag()
     */
    public AbstractRbtFilterBuilder<T> addSoundNoiseFlagFilter(int type) {
        return addFlagFilter(TARGET_SOUND_NOISE, type);
    }

    /**
     * <p>
     * add eTVOC flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getEtvocFlag()
     */
    public AbstractRbtFilterBuilder<T> addEtvocFlagFilter(int type) {
        return addFlagFilter(TARGET_ETVOC, type);
    }

    /**
     * <p>
     * add eCO2 flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getEco2Flag()
     */
    public AbstractRbtFilterBuilder<T> addEco2FlagFilter(int type) {
        return addFlagFilter(TARGET_ECO2, type);
    }

    /**
     * <p>
     * add Discomfort index flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getDiscomfortIndexFlag()
     */
    public AbstractRbtFilterBuilder<T> addDiscomfortIndexFlagFilter(int type) {
        return addFlagFilter(TARGET_DISCOMFORT_INDEX, type);
    }

    /**
     * <p>
     * add Heat stroke flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getHeatStrokeFlag()
     */
    public AbstractRbtFilterBuilder<T> addHeatStrokeFlagFilter(int type) {
        return addFlagFilter(TARGET_HEAT_STROKE, type);
    }

    /**
     * <p>
     * add SI value flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getSiValueFlag()
     */
    public AbstractRbtFilterBuilder<T> addSiValueFlagFilter(int type) {
        return addFlagFilter(TARGET_SI_VALUE, type);
    }

    /**
     * <p>
     * add PGA flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getPgaFlag()
     */
    public AbstractRbtFilterBuilder<T> addPgaFlagFilter(int type) {
        return addFlagFilter(TARGET_PGA, type);
    }

    /**
     * <p>
     * add Seismic intensity flag filter
     * partial comparison
     * </p>
     *
     * @param type check target
     * @return myself
     * @see SensorFlagAndCalculationFlag#getSeismicIntensityFlag()
     */
    public AbstractRbtFilterBuilder<T> addSeismicIntensityFlagFilter(int type) {
        return addFlagFilter(TARGET_SEISMIC_INTENSITY, type);
    }

    /**
     * flag value selector
     *
     * @param target check target
     * @param type   check type
     * @return myself
     * @see org.im97mori.rbt.RbtConstants.EventFlagSensor
     * @see org.im97mori.rbt.RbtConstants.EventFlagAcceleration
     */
    private AbstractRbtFilterBuilder<T> addFlagFilter(int target, int type) {
        int value = 0;
        if (TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1;
        } else if (TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2;
        } else if (TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_1_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1;
        } else if (TYPE_SIMPLE_THRESHOLD_LOWER_LIMIT_2_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2;
        } else if (TYPE_CHANGE_THRESHOLD_RISE_1_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1;
        } else if (TYPE_CHANGE_THRESHOLD_RISE_2_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2;
        } else if (TYPE_CHANGE_THRESHOLD_DECLINE_1_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1;
        } else if (TYPE_CHANGE_THRESHOLD_DECLINE_2_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2;
        } else if (TYPE_AVERAGE_VALUE_THRESHOLD_UPPER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER;
        } else if (TYPE_AVERAGE_VALUE_THRESHOLD_LOWER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER;
        } else if (TYPE_PEAK_TO_PEAK_THRESHOLD_UPPER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER;
        } else if (TYPE_PEAK_TO_PEAK_THRESHOLD_LOWER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER;
        } else if (TYPE_INTERVAL_DIFFERENCE_THRESHOLD_RISE_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE;
        } else if (TYPE_INTERVAL_DIFFERENCE_THRESHOLD_DECLINE_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE;
        } else if (TYPE_BASE_DIFFERENCE_THRESHOLD_UPPER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER;
        } else if (TYPE_BASE_DIFFERENCE_THRESHOLD_LOWER_SENSOR == type) {
            value = RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER;
        } else if (TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_1_ACCELERATION == type) {
            value = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1;
        } else if (TYPE_SIMPLE_THRESHOLD_UPPER_LIMIT_2_ACCELERATION == type) {
            value = RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2;
        } else if (TYPE_CHANGE_THRESHOLD_RISE_1_ACCELERATION == type) {
            value = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1;
        } else if (TYPE_CHANGE_THRESHOLD_RISE_2_ACCELERATION == type) {
            value = RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2;
        }
        mFilterList.add(new SensorFlagAndCalculationFlagFilter(target, type, value));
        return this;
    }

    /**
     * @return {@link FilteredRbtLeScanCallback} or {@link FilteredRbtLeScanCallback} instance
     */
    public abstract T build();

}
