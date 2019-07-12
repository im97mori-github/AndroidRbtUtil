package org.im97mori.rbt.ble.ad;

import android.bluetooth.le.ScanRecord;
import android.util.Log;

import org.im97mori.ble.ad.AdvertisingDataParser;
import org.im97mori.ble.ad.ManufacturerSpecificData;
import org.im97mori.stacklog.LogUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_FLAGS;
import static org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_INCOMPLETE_LIST_OF_16_BIT_SERVICE_UUIDS;
import static org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_MANUFACTURER_SPECIFIC_DATA;
import static org.im97mori.ble.ad.AdvertisingDataConstants.AdvertisingDataTypes.DATA_TYPE_SHORTENED_LOCAL_NAME;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_DATA_AND_CALCULATION_DATA;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SENSOR_FLAG_AND_CALCULATION_FLAG;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.im97mori.rbt.RbtConstants.SHORTENED_NAME_RBT;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.DEVICE_INFORMATION_SERVICE;

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
        public Builder exclude(int advertisingDataTypes) {
            mTypeSet.remove(advertisingDataTypes);
            return this;
        }

        /**
         * build {@link RbtAdvertisingDataParser}instance
         *
         * @return {@link RbtAdvertisingDataParser}
         */
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
        public SensorDataAndCalculationData getSensorDataAndCalculationData() {
            return mSensorDataAndCalculationData;
        }

        /**
         * @return 3.4 ensor flag & Calculation flag (Scan rsp)
         */
        public SensorFlagAndCalculationFlag getSensorFlagAndCalculationFlag() {
            return mSensorFlagAndCalculationFlag;
        }

        /**
         * @return 3.5 Serial number
         */
        public SerialNumber getSerialNumber() {
            return mSerialNumber;
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

    private RbtAdvertisingDataParser(Set<Integer> targetDataTypeSet) {
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
    public RbtAdvertisingDataParseResult parse(byte[] data) {
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
    public RbtAdvertisingDataParseResult parse(byte[] data, int offset, int totalLength) {
        RbtAdvertisingDataParseResult result = null;

        try {
            AdvertisingDataParser.AdvertisingDataParseResult advertisingDataParseResult = mAdvertisingDataParser.parse(data, offset, totalLength);

            // Rbt is flag=0x06 and shortened name=Rbt
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
            LogUtils.stackLog(Log.getStackTraceString(e));
        }
        return result;
    }
}
