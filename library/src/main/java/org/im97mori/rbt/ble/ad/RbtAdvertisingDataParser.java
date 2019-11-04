package org.im97mori.rbt.ble.ad;

import android.bluetooth.le.ScanRecord;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.advertising.AdvertisingDataParser;
import org.im97mori.ble.advertising.ManufacturerSpecificData;
import org.im97mori.rbt.RbtLogUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.im97mori.ble.BLEConstants.ServiceUUID.DEVICE_INFORMATION_SERVICE;
import static org.im97mori.ble.advertising.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_FLAGS;
import static org.im97mori.ble.advertising.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_INCOMPLETE_LIST_OF_16_BIT_SERVICE_UUIDS;
import static org.im97mori.ble.advertising.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_MANUFACTURER_SPECIFIC_DATA;
import static org.im97mori.ble.advertising.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_SHORTENED_LOCAL_NAME;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.im97mori.rbt.RbtConstants.SHORTENED_NAME_RBT;

/**
 * Parser for OMRON 2JCIE-BU01 Advertising Data
 */
@SuppressWarnings("WeakerAccess")
public class RbtAdvertisingDataParser {

    /**
     * Builder for {@link RbtAdvertisingDataParser}
     */
    public static class Builder {

        /**
         * <p>
         * All Advertising Data Type set
         *
         * @see org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType
         * </p>
         */
        private static final Set<Integer> ALL_DATA_TYPE_SET;

        static {
            Set<Integer> set = new HashSet<>();
            set.add(DATA_TYPE_SENSOR_DATA);
            set.add(DATA_TYPE_CALCULATION_DATA);
            set.add(DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA);
            set.add(DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG);
            set.add(DATA_TYPE_SERIAL_NUMBER);

            ALL_DATA_TYPE_SET = Collections.synchronizedSet(Collections.synchronizedSet(set));
        }

        /**
         * parse target Advertising Data Type set
         */
        private final Set<Integer> mTypeSet = new HashSet<>();

        /**
         * constructor
         *
         * @param isIncludeAll {@code true}:include all data type, {@code false}:exclude all data type
         */
        public Builder(boolean isIncludeAll) {
            if (isIncludeAll) {
                mTypeSet.addAll(ALL_DATA_TYPE_SET);
            }
        }

        /**
         * include All Advertising Data Type
         *
         * @return myself
         */
        @NonNull
        public Builder includeAll() {
            mTypeSet.addAll(ALL_DATA_TYPE_SET);
            return this;
        }

        /**
         * include Advertising Data Type
         *
         * @param advertisingDataTypes one of {@link org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType}
         * @return myself
         */
        @NonNull
        public Builder include(int advertisingDataTypes) {
            if (ALL_DATA_TYPE_SET.contains(advertisingDataTypes)) {
                mTypeSet.add(advertisingDataTypes);
            }
            return this;
        }

        /**
         * exclude All Advertising Data Type
         *
         * @return myself
         */
        @NonNull
        public Builder excludeAll() {
            mTypeSet.clear();
            return this;
        }

        /**
         * exclude Advertising Data Type
         *
         * @param advertisingDataTypes one of {@link org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType}
         * @return myself
         */
        @NonNull
        public Builder exclude(int advertisingDataTypes) {
            mTypeSet.remove(advertisingDataTypes);
            return this;
        }

        /**
         * build {@link RbtAdvertisingDataParser}instance
         *
         * @return {@link RbtAdvertisingDataParser}
         */
        @NonNull
        public RbtAdvertisingDataParser build() {
            return new RbtAdvertisingDataParser(mTypeSet);
        }
    }

    /**
     * parse result for Rbt Advertising Data
     */
    public static class RbtAdvertisingDataParseResult {

        /**
         * 3.1 Sensor data
         */
        private SensorData mSensorData;

        /**
         * 3.2 Calculation data
         */
        private CalculationData mCalculationData;

        /**
         * 3.3 Sensor data & Calculation data (Scan rsp)
         */
        private SensorDataAndCalculationData mSensorDataAndCalculationData;

        /**
         * 3.4 ensor flag & Calculation flag (Scan rsp)
         */
        private SensorFlagAndCalculationFlag mSensorFlagAndCalculationFlag;

        /**
         * 3.5 Serial number
         */
        private SerialNumber mSerialNumber;

        /**
         * @return 3.1 Sensor data
         */
        public SensorData getSensorData() {
            return mSensorData;
        }

        /**
         * @return 3.2 Calculation data
         */
        public CalculationData getCalculationData() {
            return mCalculationData;
        }

        /**
         * @return 3.3 Sensor data & Calculation data (Scan rsp)
         */
        @Nullable
        public SensorDataAndCalculationData getSensorDataAndCalculationData() {
            return mSensorDataAndCalculationData;
        }

        /**
         * @return 3.4 Sensor flag & Calculation flag (Scan rsp)
         */
        @Nullable
        public SensorFlagAndCalculationFlag getSensorFlagAndCalculationFlag() {
            return mSensorFlagAndCalculationFlag;
        }

        /**
         * @return 3.5 Serial number
         */
        @Nullable
        public SerialNumber getSerialNumber() {
            return mSerialNumber;
        }

        /**
         * check Rbt or non-Rbt device
         *
         * @return {@code true}:Rbt device, {@code false}:non-Rbt device
         */
        public boolean isRbt() {
            return mSensorData != null || mCalculationData != null || mSensorDataAndCalculationData != null || mSensorFlagAndCalculationFlag != null || mSerialNumber != null;
        }

    }

    /**
     * parse target data type set
     */
    private final Set<Integer> mTargetDataTypeSet;

    /**
     * {@link AdvertisingDataParser} instance for target Rbt Data type
     */
    private final AdvertisingDataParser mAdvertisingDataParser;

    /**
     * constructor from builder
     */
    private RbtAdvertisingDataParser(@NonNull Set<Integer> targetDataTypeSet) {
        mTargetDataTypeSet = targetDataTypeSet;

        AdvertisingDataParser.Builder builder = new AdvertisingDataParser.Builder(false);
        builder.include(DATA_TYPE_FLAGS).include(DATA_TYPE_SHORTENED_LOCAL_NAME).include(DATA_TYPE_MANUFACTURER_SPECIFIC_DATA);
        // only serial number has Incomplete List of 16-bit Service Class UUIDs
        if (mTargetDataTypeSet.contains(DATA_TYPE_SERIAL_NUMBER)) {
            builder.include(DATA_TYPE_INCOMPLETE_LIST_OF_16_BIT_SERVICE_UUIDS);
        }
        mAdvertisingDataParser = builder.build();
    }

    /**
     * @see #parse(byte[], int, int)
     */
    @Nullable
    public RbtAdvertisingDataParseResult parse(@NonNull byte[] data) {
        return parse(data, 0, data.length);
    }

    /**
     * parse Advertising Data
     *
     * @param data        {@link ScanRecord#getBytes()}
     * @param offset      data offset
     * @param totalLength data length
     * @return {@link RbtAdvertisingDataParseResult} instance, null:non-Rbt Advertising data
     */
    @Nullable
    public RbtAdvertisingDataParseResult parse(@NonNull byte[] data, int offset, int totalLength) {
        RbtAdvertisingDataParseResult result = null;

        try {
            AdvertisingDataParser.AdvertisingDataParseResult advertisingDataParseResult = mAdvertisingDataParser.parse(data, offset, totalLength);
            result = parse(advertisingDataParseResult);
        } catch (Exception e) {
            RbtLogUtils.stackLog(e);
        }
        return result;
    }

    /**
     * parse Advertising Data
     *
     * @param advertisingDataParseResult {@link org.im97mori.ble.advertising.AdvertisingDataParser.AdvertisingDataParseResult} instance for parse
     * @return {@link RbtAdvertisingDataParseResult} instance, null:non-Rbt Advertising data
     */
    @Nullable
    public RbtAdvertisingDataParseResult parse(@NonNull AdvertisingDataParser.AdvertisingDataParseResult advertisingDataParseResult) {
        RbtAdvertisingDataParseResult result = null;

        try {
            // Rbt device has flag=0x06 and shortened name=Rbt
            if (advertisingDataParseResult.getFlags() != null
                    && advertisingDataParseResult.getFlags().isLeGeneralDiscoverableMode() && advertisingDataParseResult.getFlags().isBrEdrNotSupported()
                    && advertisingDataParseResult.getShortenedLocalName() != null
                    && SHORTENED_NAME_RBT.equals(advertisingDataParseResult.getShortenedLocalName().getShortenedLocalName())) {

                int size = advertisingDataParseResult.getManufacturerSpecificDataList().size();

                // Rbt must have 1 or more Manufacturer Specific Data
                if (size > 0) {
                    result = new RbtAdvertisingDataParseResult();

                    ManufacturerSpecificData manufacturerSpecificData = advertisingDataParseResult.getManufacturerSpecificDataList().get(0);
                    byte[] specificData = manufacturerSpecificData.getManufacturerSpecificData();
                    int dataType = specificData[0] & 0xff;

                    // ignore non target data type
                    if (mTargetDataTypeSet.contains(dataType)) {

                        // 3.1 Sensor data
                        // Sensor data has 1  Manufacturer Specific Data
                        if (DATA_TYPE_SENSOR_DATA == dataType && size == 1) {
                            result.mSensorData = new SensorData(specificData);
                        } else if (DATA_TYPE_CALCULATION_DATA == dataType && size == 1) {
                            // 3.2 Calculation data
                            // Calculation data has 1 Manufacturer Specific Data

                            result.mCalculationData = new CalculationData(specificData);
                        } else if (DATA_TYPE_SERIAL_NUMBER == dataType
                                && advertisingDataParseResult.getIncompleteListOf16BitServiceUUIDs() != null
                                && advertisingDataParseResult.getIncompleteListOf16BitServiceUUIDs().getUuidList().size() == 1
                                && DEVICE_INFORMATION_SERVICE.equals(advertisingDataParseResult.getIncompleteListOf16BitServiceUUIDs().getUuidList().get(0))
                                && size == 1) {
                            // 3.5 Serial number
                            // Serial number has 1  Manufacturer Specific Data
                            // Serial number must have Incomplete List of 16-bit Service Class UUIDs

                            result.mSerialNumber = new SerialNumber(specificData);
                        } else if (DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA == dataType
                                && size == 2) {
                            // 3.3 Sensor data & Calculation data (Scan rsp)
                            // Sensor data & Calculation data has 2 Manufacturer Specific Data

                            result.mSensorDataAndCalculationData = new SensorDataAndCalculationData(specificData, advertisingDataParseResult.getManufacturerSpecificDataList().get(1).getManufacturerSpecificData());
                        } else if (DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG == dataType
                                && size == 2) {
                            // 3.4 Sensor flag & Calculation flag (Scan rsp)
                            // Sensor flag & Calculation flag has 2 Manufacturer Specific Data

                            result.mSensorFlagAndCalculationFlag = new SensorFlagAndCalculationFlag(specificData, advertisingDataParseResult.getManufacturerSpecificDataList().get(1).getManufacturerSpecificData());
                        }
                    }
                }
            }
        } catch (Exception e) {
            RbtLogUtils.stackLog(e);
        }

        return result;
    }
}
