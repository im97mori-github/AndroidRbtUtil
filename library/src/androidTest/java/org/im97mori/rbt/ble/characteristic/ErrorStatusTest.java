package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;

import org.im97mori.ble.ad.AdvertisingDataConstants;
import org.junit.Test;

import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_COMMUNICATION_ERROR;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_FROZEN_OUTPUT;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_INITIALIZATION_ERROR;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_REBOOT_WITH_WATCHDOG;
import static org.im97mori.rbt.ble.characteristic.ErrorStatus.ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ErrorStatusTest {

    private static final int ALL_FLAG_SENSOR =
            ERROR_STATUS_INITIALIZATION_ERROR
                    | ERROR_STATUS_FROZEN_OUTPUT
                    | ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE
                    | ERROR_STATUS_COMMUNICATION_ERROR;

    private static final int ALL_FLAG_CPU =
            ERROR_STATUS_REBOOT_WITH_WATCHDOG
                    | ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR
                    | ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR;

    @Test
    public void test001() {
        byte[] data = new byte[11];
        data[0] = (byte) (ALL_FLAG_SENSOR);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getTemperatureSensorError());
        assertTrue(errorStatus.isTemperatureSensorInitializationError());
        assertTrue(errorStatus.isTemperatureSensorFrozenOutputError());
        assertTrue(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test002() {
        byte[] data = new byte[11];
        data[0] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getTemperatureSensorError());
        assertTrue(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test003() {
        byte[] data = new byte[11];
        data[0] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertTrue(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test004() {
        byte[] data = new byte[11];
        data[0] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertTrue(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test005() {
        byte[] data = new byte[11];
        data[0] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test101() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (ALL_FLAG_SENSOR);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getRelativeHumiditySensorError());
        assertTrue(errorStatus.isRelativeHumiditySensorInitializationError());
        assertTrue(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertTrue(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test102() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getRelativeHumiditySensorError());
        assertTrue(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test103() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertTrue(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test104() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertTrue(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test105() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test201() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (ALL_FLAG_SENSOR);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getAmbientLightSensorError());
        assertTrue(errorStatus.isAmbientLightSensorInitializationError());
        assertTrue(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertTrue(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test202() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getAmbientLightSensorError());
        assertTrue(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test203() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertTrue(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test204() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertTrue(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test205() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test301() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (ALL_FLAG_SENSOR);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getBarometricPressureSensorError());
        assertTrue(errorStatus.isBarometricPressureSensorInitializationError());
        assertTrue(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertTrue(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test302() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getBarometricPressureSensorError());
        assertTrue(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test303() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertTrue(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test304() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertTrue(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test305() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test401() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (ALL_FLAG_SENSOR);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getSoundNoiseSensorError());
        assertTrue(errorStatus.isSoundNoiseSensorInitializationError());
        assertTrue(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertTrue(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test402() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getSoundNoiseSensorError());
        assertTrue(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test403() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertTrue(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test404() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertTrue(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test405() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test501() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (ALL_FLAG_SENSOR);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getAccelerationSensorError());
        assertTrue(errorStatus.isAccelerationSensorInitializationError());
        assertTrue(errorStatus.isAccelerationSensorFrozenOutputError());
        assertTrue(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test502() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getAccelerationSensorError());
        assertTrue(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test503() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertTrue(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test504() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertTrue(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test505() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test601() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (ALL_FLAG_SENSOR);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getEtvocSensorError());
        assertTrue(errorStatus.isEtvocSensorInitializationError());
        assertTrue(errorStatus.isEtvocSensorFrozenOutputError());
        assertTrue(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test602() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getEtvocSensorError());
        assertTrue(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test603() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertTrue(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test604() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertTrue(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test605() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[7] = (byte) (0x00);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test701() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (ALL_FLAG_SENSOR);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(ALL_FLAG_SENSOR, errorStatus.getEco2SensorError());
        assertTrue(errorStatus.isEco2SensorInitializationError());
        assertTrue(errorStatus.isEco2SensorFrozenOutputError());
        assertTrue(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test702() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(ERROR_STATUS_INITIALIZATION_ERROR, errorStatus.getEco2SensorError());
        assertTrue(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test703() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(ERROR_STATUS_FROZEN_OUTPUT, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertTrue(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test704() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertTrue(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test705() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[8] = (byte) (0x00);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(ERROR_STATUS_COMMUNICATION_ERROR, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertTrue(errorStatus.isEco2SensorCommunicationError());

        assertEquals(0, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test801() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (ALL_FLAG_CPU);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(ALL_FLAG_CPU, errorStatus.getCpuError());
        assertTrue(errorStatus.isCpuRebootWithWatchdogError());
        assertTrue(errorStatus.isCpuFlashMemmoryEraseError());
        assertTrue(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test802() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (ERROR_STATUS_REBOOT_WITH_WATCHDOG);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(ERROR_STATUS_REBOOT_WITH_WATCHDOG, errorStatus.getCpuError());
        assertTrue(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test803() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertTrue(errorStatus.isCpuFlashMemmoryEraseError());
        assertFalse(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test804() {
        byte[] data = new byte[11];
        data[0] = (byte) (0x00);
        data[1] = (byte) (0x00);
        data[2] = (byte) (0x00);
        data[3] = (byte) (0x00);
        data[4] = (byte) (0x00);
        data[5] = (byte) (0x00);
        data[6] = (byte) (0x00);
        data[7] = (byte) (0x00);
        data[8] = (byte) (ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus errorStatus = new ErrorStatus(bluetoothGattCharacteristic);

        assertEquals(0, errorStatus.getTemperatureSensorError());
        assertFalse(errorStatus.isTemperatureSensorInitializationError());
        assertFalse(errorStatus.isTemperatureSensorFrozenOutputError());
        assertFalse(errorStatus.isTemperatureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isTemperatureSensorCommunicationError());

        assertEquals(0, errorStatus.getRelativeHumiditySensorError());
        assertFalse(errorStatus.isRelativeHumiditySensorInitializationError());
        assertFalse(errorStatus.isRelativeHumiditySensorFrozenOutputError());
        assertFalse(errorStatus.isRelativeHumiditySensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isRelativeHumiditySensorCommunicationError());

        assertEquals(0, errorStatus.getAmbientLightSensorError());
        assertFalse(errorStatus.isAmbientLightSensorInitializationError());
        assertFalse(errorStatus.isAmbientLightSensorFrozenOutputError());
        assertFalse(errorStatus.isAmbientLightSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAmbientLightSensorCommunicationError());

        assertEquals(0, errorStatus.getBarometricPressureSensorError());
        assertFalse(errorStatus.isBarometricPressureSensorInitializationError());
        assertFalse(errorStatus.isBarometricPressureSensorFrozenOutputError());
        assertFalse(errorStatus.isBarometricPressureSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isBarometricPressureSensorCommunicationError());

        assertEquals(0, errorStatus.getSoundNoiseSensorError());
        assertFalse(errorStatus.isSoundNoiseSensorInitializationError());
        assertFalse(errorStatus.isSoundNoiseSensorFrozenOutputError());
        assertFalse(errorStatus.isSoundNoiseSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isSoundNoiseSensorCommunicationError());

        assertEquals(0, errorStatus.getAccelerationSensorError());
        assertFalse(errorStatus.isAccelerationSensorInitializationError());
        assertFalse(errorStatus.isAccelerationSensorFrozenOutputError());
        assertFalse(errorStatus.isAccelerationSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isAccelerationSensorCommunicationError());

        assertEquals(0, errorStatus.getEtvocSensorError());
        assertFalse(errorStatus.isEtvocSensorInitializationError());
        assertFalse(errorStatus.isEtvocSensorFrozenOutputError());
        assertFalse(errorStatus.isEtvocSensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEtvocSensorCommunicationError());

        assertEquals(0, errorStatus.getEco2SensorError());
        assertFalse(errorStatus.isEco2SensorInitializationError());
        assertFalse(errorStatus.isEco2SensorFrozenOutputError());
        assertFalse(errorStatus.isEco2SensorSensingDataIsOutOfRangeError());
        assertFalse(errorStatus.isEco2SensorCommunicationError());

        assertEquals(ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR, errorStatus.getCpuError());
        assertFalse(errorStatus.isCpuRebootWithWatchdogError());
        assertFalse(errorStatus.isCpuFlashMemmoryEraseError());
        assertTrue(errorStatus.isCpuFlashMemoryInitializationError());
    }

    @Test
    public void test901() {
        byte[] data = new byte[11];
        data[0] = (byte) (ALL_FLAG_SENSOR);
        data[1] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[2] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[3] = (byte) (ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE);
        data[4] = (byte) (ERROR_STATUS_COMMUNICATION_ERROR);
        data[5] = (byte) (ALL_FLAG_SENSOR);
        data[6] = (byte) (ERROR_STATUS_INITIALIZATION_ERROR);
        data[7] = (byte) (ERROR_STATUS_FROZEN_OUTPUT);
        data[8] = (byte) (ALL_FLAG_CPU);
        data[9] = (byte) (0x00);
        data[10] = (byte) (0x00);

        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(AdvertisingDataConstants.BASE_UUID, 0, 0);
        bluetoothGattCharacteristic.setValue(data);

        ErrorStatus result1 = new ErrorStatus(bluetoothGattCharacteristic);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ErrorStatus result2 = ErrorStatus.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getTemperatureSensorError(), result2.getTemperatureSensorError());
        assertEquals(result1.getRelativeHumiditySensorError(), result2.getRelativeHumiditySensorError());
        assertEquals(result1.getAmbientLightSensorError(), result2.getAmbientLightSensorError());
        assertEquals(result1.getBarometricPressureSensorError(), result2.getBarometricPressureSensorError());
        assertEquals(result1.getSoundNoiseSensorError(), result2.getSoundNoiseSensorError());
        assertEquals(result1.getAccelerationSensorError(), result2.getAccelerationSensorError());
        assertEquals(result1.getEtvocSensorError(), result2.getEtvocSensorError());
        assertEquals(result1.getEco2SensorError(), result2.getEco2SensorError());
        assertEquals(result1.getCpuError(), result2.getCpuError());
    }

}
