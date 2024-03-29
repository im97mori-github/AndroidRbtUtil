package org.im97mori.rbt.ble.characteristic.mds;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.ble.BLEConstants.BASE_UUID;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemorySensingFlagTest {

    @Test
    public void test_001() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x01) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x00) & 0xff); // Memory index
        data1[ 4] = (byte) 0x00; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
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
    public void test_002() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0xff) & 0xff); // Memory index
        data1[ 1] = (byte) ((0xff) & 0xff); // Memory index
        data1[ 2] = (byte) ((0xff) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x7f) & 0xff); // Memory index
        data1[ 4] = (byte) 0x00; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
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
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(2147483647, result.getMemoryIndex());
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
    public void test_003() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((MemorySensingFlag.DATA_ERROR_BIT) & 0xff); // Memory index
        data1[ 1] = (byte) ((MemorySensingFlag.DATA_ERROR_BIT >> 8) & 0xff); // Memory index
        data1[ 2] = (byte) ((MemorySensingFlag.DATA_ERROR_BIT >> 16) & 0xff); // Memory index
        data1[ 3] = (byte) ((MemorySensingFlag.DATA_ERROR_BIT >> 24) & 0xff); // Memory index
        data1[ 4] = (byte) 0x00; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
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
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result = new MemorySensingFlag(bluetoothGattCharacteristic);
        assertEquals(-2147483648, result.getMemoryIndex());
        assertTrue(result.isMemoryIndexDataError());

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
    public void test_004() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x10) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x20) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x30) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x40) & 0xff); // Memory index
        data1[ 4] = (byte) 0x50; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
        data1[ 6] = (byte) 0x60; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x70; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x80; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x90; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0xa0; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0xb0; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result1 = new MemorySensingFlag(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        MemorySensingFlag result2 = MemorySensingFlag.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getMemoryIndex(), result1.getMemoryIndex());
        assertEquals(result2.getTemperatureFlag(), result1.getTemperatureFlag());
        assertEquals(result2.getRelativeHumidityFlag(), result1.getRelativeHumidityFlag());
        assertEquals(result2.getAmbientLightFlag(), result1.getAmbientLightFlag());
        assertEquals(result2.getBarometricPressureFlag(), result1.getBarometricPressureFlag());
        assertEquals(result2.getSoundNoiseFlag(), result1.getSoundNoiseFlag());
        assertEquals(result2.getEtvocFlag(), result1.getEtvocFlag());
        assertEquals(result2.getEco2Flag(), result1.getEco2Flag());
    }

    @Test
    public void test_005() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x10) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x20) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x30) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x40) & 0xff); // Memory index
        data1[ 4] = (byte) 0x50; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
        data1[ 6] = (byte) 0x60; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x70; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x80; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x90; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0xa0; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0xb0; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result1 = new MemorySensingFlag(bluetoothGattCharacteristic);
        byte[] resultData = result1.getBytes();
        assertArrayEquals(data1, resultData);
    }

    @Test
    public void test_006() {
        //@formatter:off
        byte[] data1 = new byte[18];
        data1[ 0] = (byte) ((0x10) & 0xff); // Memory index
        data1[ 1] = (byte) ((0x20) & 0xff); // Memory index
        data1[ 2] = (byte) ((0x30) & 0xff); // Memory index
        data1[ 3] = (byte) ((0x40) & 0xff); // Memory index
        data1[ 4] = (byte) 0x50; // Temperature flag
        data1[ 5] = (byte) 0x00; // Temperature flag
        data1[ 6] = (byte) 0x60; // Relative humidity flag
        data1[ 7] = (byte) 0x00; // Relative humidity flag
        data1[ 8] = (byte) 0x70; // Ambient light flag
        data1[ 9] = (byte) 0x00; // Ambient light flag
        data1[10] = (byte) 0x80; // Barometric pressure flag
        data1[11] = (byte) 0x00; // Barometric pressure flag
        data1[12] = (byte) 0x90; // Sound noise flag
        data1[13] = (byte) 0x00; // Sound noise flag
        data1[14] = (byte) 0xa0; // eTVOC flag
        data1[15] = (byte) 0x00; // eTVOC flag
        data1[16] = (byte) 0xb0; // eCO2 flag
        data1[17] = (byte) 0x00; // eCO2 flag
        //@formatter:on

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data1);

        MemorySensingFlag result1 = new MemorySensingFlag(bluetoothGattCharacteristic);
        MemorySensingFlag result2 = MemorySensingFlag.CREATOR.createFromByteArray(data1);
        assertArrayEquals(result1.getBytes(), result2.getBytes());
    }
}
