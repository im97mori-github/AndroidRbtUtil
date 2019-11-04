package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;

import org.im97mori.rbt.RbtConstants;
import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemorySensingFlagTemperatureTest extends AbstractSensingFlagTest {

    @Test
    public void test_temperature001() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) ALL_EVENT_FLAG_SENSOR_LSB; // Temperature flag
        data1[ 5] = (byte) ALL_EVENT_FLAG_SENSOR_MSB; // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(ALL_EVENT_FLAG_SENSOR, result.getTemperatureFlag());
        assertTrue(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isTemperatureChangeThresholdRise1Reached());
        assertTrue(result.isTemperatureChangeThresholdRise2Reached());
        assertTrue(result.isTemperatureChangeThresholdDecline1Reached());
        assertTrue(result.isTemperatureChangeThresholdDecline2Reached());
        assertTrue(result.isTemperatureAverageValueThresholdUpperReached());
        assertTrue(result.isTemperatureAverageValueThresholdLowerReached());
        assertTrue(result.isTemperaturePeakToPeakThresholdUpperReached());
        assertTrue(result.isTemperaturePeakToPeakThresholdLowerReached());
        assertTrue(result.isTemperatureIntervalDifferenceThresholdRiseReached());
        assertTrue(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
        assertTrue(result.isTemperatureBaseDifferenceThresholdUpperReached());
        assertTrue(result.isTemperatureBaseDifferenceThresholdLowerReached());

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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature002() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1, result.getTemperatureFlag());
        assertTrue(result.isTemperatureSimpleThresholdUpperLimit1Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature003() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertTrue(result.isTemperatureSimpleThresholdUpperLimit2Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature004() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertTrue(result.isTemperatureSimpleThresholdLowerLimit1Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature005() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertTrue(result.isTemperatureSimpleThresholdLowerLimit2Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature006() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertTrue(result.isTemperatureChangeThresholdRise1Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature007() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertTrue(result.isTemperatureChangeThresholdRise2Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature008() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertTrue(result.isTemperatureChangeThresholdDecline1Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature009() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2 >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertTrue(result.isTemperatureChangeThresholdDecline2Reached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature010() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertTrue(result.isTemperatureAverageValueThresholdUpperReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature011() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER, result.getTemperatureFlag());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdUpperLimit2Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit1Reached());
        assertFalse(result.isTemperatureSimpleThresholdLowerLimit2Reached());
        assertFalse(result.isTemperatureChangeThresholdRise1Reached());
        assertFalse(result.isTemperatureChangeThresholdRise2Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline1Reached());
        assertFalse(result.isTemperatureChangeThresholdDecline2Reached());
        assertFalse(result.isTemperatureAverageValueThresholdUpperReached());
        assertTrue(result.isTemperatureAverageValueThresholdLowerReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature012() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER, result.getTemperatureFlag());
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
        assertTrue(result.isTemperaturePeakToPeakThresholdUpperReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature013() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER, result.getTemperatureFlag());
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
        assertTrue(result.isTemperaturePeakToPeakThresholdLowerReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature014() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE, result.getTemperatureFlag());
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
        assertTrue(result.isTemperatureIntervalDifferenceThresholdRiseReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature015() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE, result.getTemperatureFlag());
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
        assertTrue(result.isTemperatureIntervalDifferenceThresholdDeclineReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature016() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER, result.getTemperatureFlag());
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
        assertTrue(result.isTemperatureBaseDifferenceThresholdUpperReached());
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

        assertEquals(0, result.getBarometricPressureFlag());
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
    public void test_temperature017() {
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) (RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER & 0xff); // Temperature flag
        data1[ 5] = (byte) ((RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER >> 8) & 0xff); // Temperature flag
        data1[ 6] = (byte) 0x00; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x00; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x00; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x00; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0x00; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0x00; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(1, result.getMemoryIndex());
        assertFalse(result.isMemoryIndexDataError());
        assertEquals(RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER, result.getTemperatureFlag());
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
        assertTrue(result.isTemperatureBaseDifferenceThresholdLowerReached());

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

        assertEquals(0, result.getBarometricPressureFlag());
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

}
