package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR_LSB;
import static org.im97mori.rbt.RbtConstants.EventEnableDisableSensor.ALL_EVENT_FLAG_SENSOR_MSB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemorySensingFlagBarometricPressureTest {

    @Test
    public void test_barometricPressure001() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Barometric pressure flag
        data1[11] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(ALL_EVENT_FLAG_SENSOR, result.getBarometricPressureFlag());
        assertTrue(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isBarometricPressureChangeThresholdRise1Reached());
        assertTrue(result.isBarometricPressureChangeThresholdRise2Reached());
        assertTrue(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertTrue(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertTrue(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertTrue(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertTrue(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertTrue(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertTrue(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertTrue(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure002() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getBarometricPressureFlag());
        assertTrue(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure003() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure004() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure005() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure006() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure007() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertTrue(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure008() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertTrue(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure009() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertTrue(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure010() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertTrue(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure011() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertTrue(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure012() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertTrue(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure013() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertTrue(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure014() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertTrue(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure015() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure016() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

    @Test
    public void test_barometricPressure017() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 5] = (byte) ((0x00) & 0xff); // Temperature flag
        data1[ 6] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 7] = (byte) ((0x00) & 0xff); // Relative humidity flag
        data1[ 8] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[ 9] = (byte) ((0x00) & 0xff); // Ambient light flag
        data1[10] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Barometric pressure flag
        data1[11] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(0, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertFalse(result.isTemperatureAverageValueThresholdLowerReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertFalse(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertFalse(result.isTemperatureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getRelativeHumidityFlag());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdUpperLimit2Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit1Reached());
        assertFalse(result.isRelativeHumiditySimpleThresholdLowerLimit2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdRise2Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline1Reached());
        assertFalse(result.isRelativeHumidityChangeThresholdDecline2Reached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdUpperReached());
        assertFalse(result.isRelativeHumidityAverageValueThresholdLowerReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdUpperReached());
        assertFalse(result.isRelativeHumidityPeakToPeakThresholdLowerReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isRelativeHumidityIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdUpperReached());
        assertFalse(result.isRelativeHumidityBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getAmbientLightFlag());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isAmbientLightSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise1Reached());
        assertFalse(result.isAmbientLightChangeThresholdRise2Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline1Reached());
        assertFalse(result.isAmbientLightChangeThresholdDecline2Reached());
        assertFalse(result.isAmbientLightAverageValueThresholdUpperReached());
        assertFalse(result.isAmbientLightAverageValueThresholdLowerReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdUpperReached());
        assertFalse(result.isAmbientLightPeakToPeakThresholdLowerReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isAmbientLightIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdUpperReached());
        assertFalse(result.isAmbientLightBaseDifferenceThresholdLowerReached());

        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getBarometricPressureFlag());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isBarometricPressureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdRise2Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline1Reached());
        assertFalse(result.isBarometricPressureChangeThresholdDecline2Reached());
        assertFalse(result.isBarometricPressureAverageValueThresholdUpperReached());
        assertFalse(result.isBarometricPressureAverageValueThresholdLowerReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdUpperReached());
        assertFalse(result.isBarometricPressurePeakToPeakThresholdLowerReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isBarometricPressureIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isBarometricPressureBaseDifferenceThresholdUpperReached());
        assertTrue(result.isBarometricPressureBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getSoundNoiseFlag());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isSoundNoiseSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdRise2Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline1Reached());
        assertFalse(result.isSoundNoiseChangeThresholdDecline2Reached());
        assertFalse(result.isSoundNoiseAverageValueThresholdUpperReached());
        assertFalse(result.isSoundNoiseAverageValueThresholdLowerReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdUpperReached());
        assertFalse(result.isSoundNoisePeakToPeakThresholdLowerReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isSoundNoiseIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdUpperReached());
        assertFalse(result.isSoundNoiseBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEtvocFlag());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEtvocSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEtvocChangeThresholdRise1Reached());
        assertFalse(result.isEtvocChangeThresholdRise2Reached());
        assertFalse(result.isEtvocChangeThresholdDecline1Reached());
        assertFalse(result.isEtvocChangeThresholdDecline2Reached());
        assertFalse(result.isEtvocAverageValueThresholdUpperReached());
        assertFalse(result.isEtvocAverageValueThresholdLowerReached());
        assertFalse(result.isEtvocPeakToPeakThresholdUpperReached());
        assertFalse(result.isEtvocPeakToPeakThresholdLowerReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEtvocIntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdUpperReached());
        assertFalse(result.isEtvocBaseDifferenceThresholdLowerReached());

        assertEquals(0, result.getEco2Flag());
        assertFalse(result.isEco2SimpleThresholdUpperLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdUpperLimit2Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit1Reached());
        assertFalse(result.isEco2SimpleThresholdLowerLimit2Reached());
        assertFalse(result.isEco2ChangeThresholdRise1Reached());
        assertFalse(result.isEco2ChangeThresholdRise2Reached());
        assertFalse(result.isEco2ChangeThresholdDecline1Reached());
        assertFalse(result.isEco2ChangeThresholdDecline2Reached());
        assertFalse(result.isEco2AverageValueThresholdUpperReached());
        assertFalse(result.isEco2AverageValueThresholdLowerReached());
        assertFalse(result.isEco2PeakToPeakThresholdUpperReached());
        assertFalse(result.isEco2PeakToPeakThresholdLowerReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdRiseReached());
        assertFalse(result.isEco2IntervalDifferenceThresholdDeclineReached());
        assertFalse(result.isEco2BaseDifferenceThresholdUpperReached());
        assertFalse(result.isEco2BaseDifferenceThresholdLowerReached());
    }

}