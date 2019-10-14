package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
import org.im97mori.ble.BLECallbackDistributer;
import org.im97mori.ble.BLEConnection;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.ble.task.ReadCharacteristicTask;
import org.im97mori.ble.task.ReadDescriptorTask;
import org.im97mori.ble.task.WriteCharacteristicTask;
import org.im97mori.ble.task.WriteDescriptorTask;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.characteristic.AbstractRbtCharacteristic;
import org.im97mori.rbt.ble.characteristic.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.AdvertiseSetting;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor1;
import org.im97mori.rbt.ble.characteristic.AmbientLightSensor2;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor1;
import org.im97mori.rbt.ble.characteristic.BarometricPressureSensor2;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor1;
import org.im97mori.rbt.ble.characteristic.DiscomfortIndexSensor2;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor1;
import org.im97mori.rbt.ble.characteristic.Eco2Sensor2;
import org.im97mori.rbt.ble.characteristic.EtvocSensor1;
import org.im97mori.rbt.ble.characteristic.EtvocSensor2;
import org.im97mori.rbt.ble.characteristic.FlashMemoryStatus;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor1;
import org.im97mori.rbt.ble.characteristic.HeatStrokeSensor2;
import org.im97mori.rbt.ble.characteristic.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.MemoryReset;
import org.im97mori.rbt.ble.characteristic.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.ModeChange;
import org.im97mori.rbt.ble.characteristic.PgaAcceleration;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor1;
import org.im97mori.rbt.ble.characteristic.RelativeHumiditySensor2;
import org.im97mori.rbt.ble.characteristic.RequestAccelerationMemoryIndex;
import org.im97mori.rbt.ble.characteristic.RequestMemoryIndex;
import org.im97mori.rbt.ble.characteristic.SeismicIntensityAcceleration;
import org.im97mori.rbt.ble.characteristic.SiValueAcceleration;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor1;
import org.im97mori.rbt.ble.characteristic.SoundNoiseSensor2;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor1;
import org.im97mori.rbt.ble.characteristic.TemperatureSensor2;
import org.im97mori.rbt.ble.characteristic.TimeSetting;
import org.im97mori.rbt.ble.task.RbtRequestAccelerationMemoryIndexTask;
import org.im97mori.rbt.ble.task.RbtRequestMemoryIndexTask;
import org.im97mori.rbt.ble.task.RbtWriteCharacteristicTask;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.CharacteristicUUID.APPEARANCE_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.DEVICE_NAME_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.FIRMWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.HARDWARE_REVISION_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MANUFACTURER_NAME_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.MODEL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.CharacteristicUUID.SERIAL_NUMBER_STRING_CHARACTERISTIC;
import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR;
import static org.im97mori.ble.BLEConstants.ServiceUUID.DEVICE_INFORMATION_SERVICE;
import static org.im97mori.ble.BLEConstants.ServiceUUID.GENERIC_ACCESS_SERVICE;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ADVERTISE_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ECO2_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ECO2_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ERROR_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ETVOC_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ETVOC_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.FLASH_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.HEAT_STROKE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.HEAT_STROKE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.INSTALLATION_OFFSET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_ACCELERATION_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_TIME_COUNTER_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_EVENT_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_NORMAL_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_STATE_OPERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_INDEX_INFORMATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_RESET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STORAGE_INTERVAL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MODE_CHANGE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MOUNTING_ORIENTATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.PGA_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SI_VALUE_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SOUND_NOISE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SOUND_NOISE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TEMPERATURE_SENSOR_1_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TEMPERATURE_SENSOR_2_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TIME_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.VIBRATION_COUNT_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.ACCELERATION_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.CONTROL_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.EVENT_SETTING_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.INFORMATION_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.LATEST_DATA_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.MEMORY_DATA_SERVICE;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.TIME_SETTING_SERVICE;

/**
 * Rbt device controller for BLE
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class RbtBLEConnection extends BLEConnection {

    /**
     * Callback wrapper for distribute
     */
    private static final class DistributionRbtCallback extends BLECallbackDistributer implements RbtRequestAccelerationMemoryIndexCallback, RbtRequestMemoryIndexCallback {

        /**
         * @param subscriberInterface {@link org.im97mori.ble.BLECallbackDistributer.SubscriberInterface}
         */
        public DistributionRbtCallback(SubscriberInterface subscriberInterface) {
            super(subscriberInterface);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteSuccess(taskId, bluetoothDevice, requestAccelerationMemoryIndex, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteSuccess(taskId, bluetoothDevice, requestAccelerationMemoryIndex, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteFailed(taskId, bluetoothDevice, status, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteFailed(taskId, bluetoothDevice, status, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteTimeout(taskId, bluetoothDevice, timeout, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestAccelerationMemoryIndexWriteTimeout(taskId, bluetoothDevice, timeout, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onAccelerationMemoryDataHeaderNotified(taskId, bluetoothDevice, accelerationMemoryDataHeader, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onAccelerationMemoryDataHeaderNotified(taskId, bluetoothDevice, accelerationMemoryDataHeader, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onAccelerationMemoryDataNotified(taskId, bluetoothDevice, accelerationMemoryData, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onAccelerationMemoryDataNotified(taskId, bluetoothDevice, accelerationMemoryData, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteWriteSuccess(taskId, bluetoothDevice, requestMemoryIndex, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteWriteSuccess(taskId, bluetoothDevice, requestMemoryIndex, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteFailed(taskId, bluetoothDevice, status, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteFailed(taskId, bluetoothDevice, status, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteTimeout(taskId, bluetoothDevice, timeout, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onRequestMemoryIndexWriteTimeout(taskId, bluetoothDevice, timeout, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onMemorySensingDataNotified(taskId, bluetoothDevice, memorySensingData, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onMemorySensingDataNotified(taskId, bluetoothDevice, memorySensingData, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onMemoryCalculationDataNotified(taskId, bluetoothDevice, memoryCalculationData, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onMemoryCalculationDataNotified(taskId, bluetoothDevice, memoryCalculationData, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onMemorySensingFlagNotified(taskId, bluetoothDevice, memorySensingFlag, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onMemorySensingFlagNotified(taskId, bluetoothDevice, memorySensingFlag, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @SuppressWarnings("NullableProblems") @NonNull Bundle argument) {
            Integer callbackId;
            if (argument.containsKey(KEY_CALLBACK_ID)) {
                callbackId = argument.getInt(KEY_CALLBACK_ID);
            } else {
                callbackId = null;
            }
            Bundle originalArgument = argument.getBundle(KEY_WRAPPED_ARGUMENT);

            for (BLECallback bleCallback : mSubscriberInterface.getSubscriberCallbackSet()) {
                if (bleCallback instanceof AbstractRbtBLECallback) {
                    try {
                        if (callbackId == null) {
                            ((AbstractRbtBLECallback) bleCallback).onMemoryCalculationFlagNotified(taskId, bluetoothDevice, memoryCalculationFlag, originalArgument);
                        } else if (bleCallback.hashCode() == callbackId) {
                            ((AbstractRbtBLECallback) bleCallback).onMemoryCalculationFlagNotified(taskId, bluetoothDevice, memoryCalculationFlag, originalArgument);
                            break;
                        }
                    } catch (Exception e) {
                        RbtLogUtils.stackLog(e);
                    }
                }
            }
        }

    }

    /**
     * {@link DistributionRbtCallback} instance
     */
    private DistributionRbtCallback mDistributionRbtCallback;

    /**
     * @param context         {@link Context} instance
     * @param bluetoothDevice Rbt {@link BluetoothDevice}
     */
    public RbtBLEConnection(Context context, BluetoothDevice bluetoothDevice) {
        super(context, bluetoothDevice, null);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public synchronized BLECallback getBLECallback() {
        if (mDistributionRbtCallback == null) {
            mDistributionRbtCallback = new DistributionRbtCallback(this);
        }
        return mDistributionRbtCallback;
    }

    /**
     * @see #readMemoryIndexInformation(Bundle, RbtCallback)
     */
    public Integer readMemoryIndexInformation() {
        return readMemoryIndexInformation(null, null);
    }

    /**
     * Read Memory index information (Characteristics UUID: 0x5004)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readMemoryIndexInformation(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(MEMORY_DATA_SERVICE, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readMemoryStatus(Bundle, RbtCallback)
     */
    public Integer readMemoryStatus() {
        return readMemoryStatus(null, null);
    }

    /**
     * Read Memory status (Characteristics UUID: 0x5006)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readMemoryStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(MEMORY_DATA_SERVICE, MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestSensingData(Bundle, RbtCallback)
     */
    public Integer readLatestSensingData() {
        return readLatestSensingData(null, null);
    }

    /**
     * Read Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestSensingData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_SENSING_DATA_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestCalculationData(Bundle, RbtCallback)
     */
    public Integer readLatestCalculationData() {
        return readLatestCalculationData(null, null);
    }

    /**
     * Read Latest calculation data ( Characteristics UUID: 0x5013)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestCalculationData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_DATA_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestSensingFlag(Bundle, RbtCallback)
     */
    public Integer readLatestSensingFlag() {
        return readLatestSensingFlag(null, null);
    }

    /**
     * Read Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestSensingFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_SENSING_FLAG_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestCalculationFlag(Bundle, RbtCallback)
     */
    public Integer readLatestCalculationFlag() {
        return readLatestCalculationFlag(null, null);
    }

    /**
     * Read Latest calculation flag ( Characteristics UUID: 0x5015)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestCalculationFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_FLAG_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestAccelerationStatus(Bundle, RbtCallback)
     */
    public Integer readLatestAccelerationStatus() {
        return readLatestAccelerationStatus(null, null);
    }

    /**
     * Read Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestAccelerationStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(LATEST_DATA_SERVICE, LATEST_ACCELERATION_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readVibrationCount(Bundle, RbtCallback)
     */
    public Integer readVibrationCount() {
        return readVibrationCount(null, null);
    }

    /**
     * Read Vibration count (Characteristics UUID: 0x5031)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readVibrationCount(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(ACCELERATION_SERVICE, VIBRATION_COUNT_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAccelerationMemoryStatus(Bundle, RbtCallback)
     */
    public Integer readAccelerationMemoryStatus() {
        return readAccelerationMemoryStatus(null, null);
    }

    /**
     * Read Acceleration memory status (Characteristics UUID: 0x5033)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAccelerationMemoryStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(ACCELERATION_SERVICE, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLedSettingNormalState(Bundle, RbtCallback)
     */
    public Integer readLedSettingNormalState() {
        return readLedSettingNormalState(null, null);
    }

    /**
     * Read LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLedSettingNormalState(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLedSettingEventState(Bundle, RbtCallback)
     */
    public Integer readLedSettingEventState() {
        return readLedSettingEventState(null, null);
    }

    /**
     * Read LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLedSettingEventState(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, LED_SETTING_EVENT_STATE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLedStateOperation(Bundle, RbtCallback)
     */
    public Integer readLedStateOperation() {
        return readLedStateOperation(null, null);
    }

    /**
     * Read LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLedStateOperation(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, LED_STATE_OPERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readInstallationOffset(Bundle, RbtCallback)
     */
    public Integer readInstallationOffset() {
        return readInstallationOffset(null, null);
    }

    /**
     * Read Installation offset (Characteristics UUID: 0x5114)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readInstallationOffset(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, INSTALLATION_OFFSET_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAdvertisingSetting(Bundle, RbtCallback)
     */
    public Integer readAdvertisingSetting() {
        return readAdvertisingSetting(null, null);
    }

    /**
     * Read Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAdvertisingSetting(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, ADVERTISE_SETTING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readModeChange(Bundle, RbtCallback)
     */
    public Integer readModeChange() {
        return readModeChange(null, null);
    }

    /**
     * Read Mode change (Characteristics UUID: 0x5117)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readModeChange(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, MODE_CHANGE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAccelerationLoggerStatus(Bundle, RbtCallback)
     */
    public Integer readAccelerationLoggerStatus() {
        return readAccelerationLoggerStatus(null, null);
    }

    /**
     * Read Acceleration logger status (Characteristics UUID: 0x5119)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAccelerationLoggerStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(CONTROL_SERVICE, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readLatestTimeCounter()
     */
    public Integer readLatestTimeCounter() {
        return readLatestTimeCounter(null, null);
    }

    /**
     * Read Latest time counter (Characteristics UUID: 0x5201)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readLatestTimeCounter(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(TIME_SETTING_SERVICE, LATEST_TIME_COUNTER_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readTimeSetting
     */
    public Integer readTimeSetting() {
        return readTimeSetting(null, null);
    }

    /**
     * Read Time setting (Characteristics UUID: 0x5202)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readTimeSetting(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(TIME_SETTING_SERVICE, TIME_SETTING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readMemoryStorageInterval(Bundle, RbtCallback)
     */
    public Integer readMemoryStorageInterval() {
        return readMemoryStorageInterval(null, null);
    }

    /**
     * Read Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readMemoryStorageInterval(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(TIME_SETTING_SERVICE, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readTemperatureSensor1(Bundle, RbtCallback)
     */
    public Integer readTemperatureSensor1() {
        return readTemperatureSensor1(null, null);
    }

    /**
     * Read Event pattern Temperature [Sensor 1] (Characteristics UUID: 0x5211)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readTemperatureSensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readTemperatureSensor2(Bundle, RbtCallback)
     */
    public Integer readTemperatureSensor2() {
        return readTemperatureSensor2(null, null);
    }

    /**
     * Read Event pattern Temperature [Sensor 2] (Characteristics UUID: 0x5212)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readTemperatureSensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readRelativeHumiditySensor1(Bundle, RbtCallback)
     */
    public Integer readRelativeHumiditySensor1() {
        return readRelativeHumiditySensor1(null, null);
    }

    /**
     * Read Event pattern Relative humidity [Sensor 1] (Characteristics UUID: 0x5213)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readRelativeHumiditySensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readRelativeHumiditySensor2(Bundle, RbtCallback)
     */
    public Integer readRelativeHumiditySensor2() {
        return readRelativeHumiditySensor2(null, null);
    }

    /**
     * Read Event pattern Relative humidity [Sensor 2] (Characteristics UUID: 0x5214)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readRelativeHumiditySensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAmbientLightSensor1(Bundle, RbtCallback)
     */
    public Integer readAmbientLightSensor1() {
        return readAmbientLightSensor1(null, null);
    }

    /**
     * Read Event pattern Ambient light [Sensor 1] (Characteristics UUID: 0x5215)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAmbientLightSensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAmbientLightSensor2(Bundle, RbtCallback)
     */
    public Integer readAmbientLightSensor2() {
        return readAmbientLightSensor2(null, null);
    }

    /**
     * Read Event pattern Ambient light [Sensor 2] (Characteristics UUID: 0x5216)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAmbientLightSensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readBarometricPressure1(Bundle, RbtCallback)
     */
    public Integer readBarometricPressure1() {
        return readBarometricPressure1(null, null);
    }

    /**
     * Read Event pattern Barometric pressure [Sensor 1] (Characteristics UUID: 0x5217)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readBarometricPressure1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readBarometricPressure2(Bundle, RbtCallback)
     */
    public Integer readBarometricPressure2() {
        return readBarometricPressure2(null, null);
    }

    /**
     * Read Event pattern Barometric pressure [Sensor 2] (Characteristics UUID: 0x5218)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readBarometricPressure2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readSoundNoise1(Bundle, RbtCallback)
     */
    public Integer readSoundNoise1() {
        return readSoundNoise1(null, null);
    }

    /**
     * Read Event pattern Sound noise [Sensor 1] (Characteristics UUID: 0x5219)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readSoundNoise1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readSoundNoise2(Bundle, RbtCallback)
     */
    public Integer readSoundNoise2() {
        return readSoundNoise2(null, null);
    }

    /**
     * Read Event pattern Sound noise [Sensor 2] (Characteristics UUID: 0x521A)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readSoundNoise2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readEtvocSensor1(Bundle, RbtCallback)
     */
    public Integer readEtvocSensor1() {
        return readEtvocSensor1(null, null);
    }

    /**
     * Read Event pattern eTVOC [Sensor 1] (Characteristics UUID: 0x521B)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readEtvocSensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readEtvocSensor2(Bundle, RbtCallback)
     */
    public Integer readEtvocSensor2() {
        return readEtvocSensor2(null, null);
    }

    /**
     * Read Event pattern eTVOC  [Sensor 2] (Characteristics UUID: 0x521C)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readEtvocSensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readEco2Sensor1(Bundle, RbtCallback)
     */
    public Integer readEco2Sensor1() {
        return readEco2Sensor1(null, null);
    }

    /**
     * Read Event pattern eCO2 [Sensor 1] (Characteristics UUID: 0x521D)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readEco2Sensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readEco2Sensor2(Bundle, RbtCallback)
     */
    public Integer readEco2Sensor2() {
        return readEco2Sensor2(null, null);
    }

    /**
     * Read Event pattern eCO2 [Sensor 2] (Characteristics UUID: 0x521E)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readEco2Sensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readDiscomfortIndexSensor1(Bundle, RbtCallback)
     */
    public Integer readDiscomfortIndexSensor1() {
        return readDiscomfortIndexSensor1(null, null);
    }

    /**
     * Read Event pattern Discomfort index [Sensor 1] (Characteristics UUID: 0x521F)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readDiscomfortIndexSensor1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readDiscomfortIndexSensor2(Bundle, RbtCallback)
     */
    public Integer readDiscomfortIndexSensor2() {
        return readDiscomfortIndexSensor2(null, null);
    }

    /**
     * Read Event pattern Discomfort index [Sensor 2] (Characteristics UUID: 0x5220)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readDiscomfortIndexSensor2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readHeatStroke1(Bundle, RbtCallback)
     */
    public Integer readHeatStroke1() {
        return readHeatStroke1(null, null);
    }

    /**
     * Read Event pattern Heat stroke [Sensor 1] (Characteristics UUID: 0x5221)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readHeatStroke1(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_1_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readHeatStroke2(Bundle, RbtCallback)
     */
    public Integer readHeatStroke2() {
        return readHeatStroke2(null, null);
    }

    /**
     * Read Event pattern Heat stroke [Sensor 2] (Characteristics UUID: 0x5222)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readHeatStroke2(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_2_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readSiValueAcceleration(Bundle, RbtCallback)
     */
    public Integer readSiValueAcceleration() {
        return readSiValueAcceleration(null, null);
    }

    /**
     * Read Event pattern SI value [Acceleration] (Characteristics UUID: 0x5226)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readSiValueAcceleration(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, SI_VALUE_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readPgaAcceleration(Bundle, RbtCallback)
     */
    public Integer readPgaAcceleration() {
        return readPgaAcceleration(null, null);
    }

    /**
     * Read Event pattern PGA [Acceleration] (Characteristics UUID: 0x5227)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readPgaAcceleration(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, PGA_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readSeismicIntensityAcceleration(Bundle, RbtCallback)
     */
    public Integer readSeismicIntensityAcceleration() {
        return readSeismicIntensityAcceleration(null, null);
    }

    /**
     * Read Event pattern Seismic intensity [Acceleration] (Characteristics UUID: 0x5228)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readSeismicIntensityAcceleration(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(EVENT_SETTING_SERVICE, SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readErrorStatus(Bundle, RbtCallback)
     */
    public Integer readErrorStatus() {
        return readErrorStatus(null, null);
    }

    /**
     * Read Error status (Characteristics UUID: 0x5401)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readErrorStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(INFORMATION_SERVICE, ERROR_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readMountingOrientation(Bundle, RbtCallback)
     */
    public Integer readMountingOrientation() {
        return readMountingOrientation(null, null);
    }

    /**
     * Read Mounting orientation (Characteristics UUID: 0x5402)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readMountingOrientation(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(INFORMATION_SERVICE, MOUNTING_ORIENTATION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readFlashMemoryStatus(Bundle, RbtCallback)
     */
    public Integer readFlashMemoryStatus() {
        return readFlashMemoryStatus(null, null);
    }

    /**
     * Read FLASH memory status (Characteristics UUID: 0x5403)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readFlashMemoryStatus(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(INFORMATION_SERVICE, FLASH_MEMORY_STATUS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readDeviceName(Bundle, RbtCallback)
     */
    public Integer readDeviceName() {
        return readDeviceName(null, null);
    }

    /**
     * Read Device name (Characteristics UUID: 0x2A00)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readDeviceName(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, DEVICE_NAME_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readAppearance(Bundle, RbtCallback)
     */
    public Integer readAppearance() {
        return readAppearance(null, null);
    }

    /**
     * Read Appearance (Characteristics UUID: 0x2A01)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readAppearance(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, APPEARANCE_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readPeripheralPreferredConnectionParameters(Bundle, RbtCallback)
     */
    public Integer readPeripheralPreferredConnectionParameters() {
        return readPeripheralPreferredConnectionParameters(null, null);
    }

    /**
     * Read Peripheral preferred c onnection parameters (Characteristics UUID: 0x2A04)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readPeripheralPreferredConnectionParameters(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, PERIPHERAL_PREFERRED_CONNECTION_PARAMATERS_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readCentralAddressResolution(Bundle, RbtCallback)
     */
    public Integer readCentralAddressResolution() {
        return readCentralAddressResolution(null, null);
    }

    /**
     * Read Central address resolution (Characteristics UUID: 0x2AA6)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readCentralAddressResolution(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(GENERIC_ACCESS_SERVICE, CENTRAL_ADDRESS_RESOLUTION_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readModelNumberString(Bundle, RbtCallback)
     */
    public Integer readModelNumberString() {
        return readModelNumberString(null, null);
    }

    /**
     * Read Model number string (Characteristics UUID: 0x2A24)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readModelNumberString(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, MODEL_NUMBER_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readSerialNumberString(Bundle, RbtCallback)
     */
    public Integer readSerialNumberString() {
        return readSerialNumberString(null, null);
    }

    /**
     * Read Serial number string (Characteristics UUID: 0x2A25)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readSerialNumberString(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, SERIAL_NUMBER_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readFirmwareRevisionString(Bundle, RbtCallback)
     */
    public Integer readFirmwareRevisionString() {
        return readFirmwareRevisionString(null, null);
    }

    /**
     * Read Firmware revision string (Characteristics UUID: 0x2A26)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readFirmwareRevisionString(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, FIRMWARE_REVISION_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readHardwareRevisionString(Bundle, RbtCallback)
     */
    public Integer readHardwareRevisionString() {
        return readHardwareRevisionString(null, null);
    }

    /**
     * Read Hardware revision string (Characteristics UUID: 0x2A27)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readHardwareRevisionString(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, HARDWARE_REVISION_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readManufacturerNameString(Bundle, RbtCallback)
     */
    public Integer readManufacturerNameString() {
        return readManufacturerNameString(null, null);
    }

    /**
     * Read Manufacturer name string (Characteristics UUID: 0x2A28)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readManufacturerNameString(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadCharacteristicTask(DEVICE_INFORMATION_SERVICE, MANUFACTURER_NAME_STRING_CHARACTERISTIC, ReadCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeRequestMemoryIndex(RequestMemoryIndex, Bundle, AbstractRbtBLECallback)
     */
    public Integer writeRequestMemoryIndex(@NonNull RequestMemoryIndex data) {
        return writeRequestMemoryIndex(data, null, null);
    }

    /**
     * Write Request memory index (Characteristics UUID: 0x5005)
     *
     * @param data        {@link RequestMemoryIndex} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeRequestMemoryIndex(@NonNull RequestMemoryIndex data, @Nullable Bundle argument, @Nullable AbstractRbtBLECallback rbtCallback) {
        Integer taskId = null;
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RbtRequestMemoryIndexTask task = new RbtRequestMemoryIndexTask(bluetoothGatt, mTaskHandler, data, mDistributionRbtCallback, BLECallbackDistributer.wrapArgument(argument, rbtCallback));
            mTaskHandler.addTask(task);
            taskId = task.getTaskId();
        }
        return taskId;
    }

    /**
     * @see #writeRequestAccelerationMemoryIndex(RequestAccelerationMemoryIndex, Bundle, AbstractRbtBLECallback)
     */
    public Integer writeRequestAccelerationMemoryIndex(@NonNull RequestAccelerationMemoryIndex data) {
        return writeRequestAccelerationMemoryIndex(data, null, null);
    }

    /**
     * Write Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param data        {@link RequestAccelerationMemoryIndex} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeRequestAccelerationMemoryIndex(@NonNull RequestAccelerationMemoryIndex data, @Nullable Bundle argument, @Nullable AbstractRbtBLECallback rbtCallback) {
        Integer taskId = null;
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RbtRequestAccelerationMemoryIndexTask task = new RbtRequestAccelerationMemoryIndexTask(bluetoothGatt, mTaskHandler, data, mDistributionRbtCallback, BLECallbackDistributer.wrapArgument(argument, rbtCallback));
            mTaskHandler.addTask(task);
            taskId = task.getTaskId();
        }
        return taskId;
    }

    /**
     * @see #writeLedSettingNormalState(LedSettingNormalState, Bundle, RbtCallback)
     */
    public Integer writeLedSettingNormalState(@NonNull LedSettingNormalState data) {
        return writeLedSettingNormalState(data, null, null);
    }

    /**
     * Write LED setting [normal state] (Characteristics UUID: 0x5111)
     *
     * @param data        {@link LedSettingNormalState} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeLedSettingNormalState(@NonNull LedSettingNormalState data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeLedSettingEventState(LedSettingEventState, Bundle, RbtCallback)
     */
    public Integer writeLedSettingEventState(@NonNull LedSettingEventState data) {
        return writeLedSettingEventState(data, null, null);
    }

    /**
     * Write LED setting [event state] (Characteristics UUID: 0x5112)
     *
     * @param data        {@link LedSettingEventState} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeLedSettingEventState(@NonNull LedSettingEventState data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_SETTING_EVENT_STATE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeLedStateOperation(LedStateOperation, Bundle, RbtCallback)
     */
    public Integer writeLedStateOperation(@NonNull LedStateOperation data) {
        return writeLedStateOperation(data, null, null);
    }

    /**
     * Write LED state [operation] (Characteristics UUID: 0x5113)
     *
     * @param data        {@link LedStateOperation} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeLedStateOperation(@NonNull LedStateOperation data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, LED_STATE_OPERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeInstallationOffset(InstallationOffset, Bundle, RbtCallback)
     */
    public Integer writeInstallationOffset(@NonNull InstallationOffset data) {
        return writeInstallationOffset(data, null, null);
    }

    /**
     * Write Installation offset (Characteristics UUID: 0x5114)
     *
     * @param data        {@link InstallationOffset} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeInstallationOffset(@NonNull InstallationOffset data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, INSTALLATION_OFFSET_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeAdvertisingSetting(AdvertiseSetting, Bundle, RbtCallback)
     */
    public Integer writeAdvertisingSetting(@NonNull AdvertiseSetting data) {
        return writeAdvertisingSetting(data, null, null);
    }

    /**
     * Write Advertise setting (Characteristics UUID: 0x5115)
     *
     * @param data        {@link AdvertiseSetting} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeAdvertisingSetting(@NonNull AdvertiseSetting data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, ADVERTISE_SETTING_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeMemoryReset(MemoryReset, Bundle, RbtCallback)
     */
    public Integer writeMemoryReset(@NonNull MemoryReset data) {
        return writeMemoryReset(data, null, null);
    }

    /**
     * Write Memory reset (Characteristics UUID: 0x5116)
     *
     * @param data        {@link MemoryReset} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeMemoryReset(@NonNull MemoryReset data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, MEMORY_RESET_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, DateUtils.MINUTE_IN_MILLIS * 3, argument, rbtCallback);
    }

    /**
     * @see #writeModeChange(ModeChange, Bundle, RbtCallback)
     */
    public Integer writeModeChange(@NonNull ModeChange data) {
        return writeModeChange(data, null, null);
    }

    /**
     * Write Mode change (Characteristics UUID: 0x5117)
     *
     * @param data        {@link ModeChange} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeModeChange(@NonNull ModeChange data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(CONTROL_SERVICE, MODE_CHANGE_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING, DateUtils.MINUTE_IN_MILLIS * 3, argument, rbtCallback);
    }

    /**
     * @see #writeAccelerationLoggerControl(AccelerationLoggerControl, Bundle, RbtCallback)
     */
    public Integer writeAccelerationLoggerControl(@NonNull AccelerationLoggerControl data) {
        return writeAccelerationLoggerControl(data, null, null);
    }

    /**
     * Write Acceleration logger control (Characteristics UUID: 0x5118)
     *
     * @param data        {@link AccelerationLoggerControl} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeAccelerationLoggerControl(@NonNull AccelerationLoggerControl data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteCharacteristicTask(CONTROL_SERVICE, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, data, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeTimeSetting(TimeSetting, Bundle, RbtCallback)
     */
    public Integer writeTimeSetting(@NonNull TimeSetting data) {
        return writeTimeSetting(data, null, null);
    }

    /**
     * Write Time setting (Characteristics UUID: 0x5202)
     *
     * @param data        {@link TimeSetting} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeTimeSetting(@NonNull TimeSetting data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteCharacteristicTask(TIME_SETTING_SERVICE, TIME_SETTING_CHARACTERISTIC, data, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeMemoryStorageInterval(MemoryStorageInterval, Bundle, RbtCallback)
     */
    public Integer writeMemoryStorageInterval(@NonNull MemoryStorageInterval data) {
        return writeMemoryStorageInterval(data, null, null);
    }

    /**
     * Write Memory storage interval (Characteristics UUID: 0x5203)
     *
     * @param data        {@link MemoryStorageInterval} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeMemoryStorageInterval(@NonNull MemoryStorageInterval data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(TIME_SETTING_SERVICE, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, DateUtils.MINUTE_IN_MILLIS * 3, argument, rbtCallback);
    }

    /**
     * @see #writeTemperatureSensor1(TemperatureSensor1, Bundle, RbtCallback)
     */
    public Integer writeTemperatureSensor1(@NonNull TemperatureSensor1 data) {
        return writeTemperatureSensor1(data, null, null);
    }

    /**
     * Write Event pattern Temperature [Sensor 1] (Characteristics UUID: 0x5211)
     *
     * @param data        {@link TemperatureSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeTemperatureSensor1(@NonNull TemperatureSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeTemperatureSensor2(TemperatureSensor2, Bundle, RbtCallback)
     */
    public Integer writeTemperatureSensor2(@NonNull TemperatureSensor2 data) {
        return writeTemperatureSensor2(data, null, null);
    }

    /**
     * Write Event pattern Temperature [Sensor 2] (Characteristics UUID: 0x5212)
     *
     * @param data        {@link TemperatureSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeTemperatureSensor2(@NonNull TemperatureSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, TEMPERATURE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeRelativeHumiditySensor1(RelativeHumiditySensor1, Bundle, RbtCallback)
     */
    public Integer writeRelativeHumiditySensor1(@NonNull RelativeHumiditySensor1 data) {
        return writeRelativeHumiditySensor1(data, null, null);
    }

    /**
     * Write Event pattern Relative humidity [Sensor 1] (Characteristics UUID: 0x5213)
     *
     * @param data        {@link RelativeHumiditySensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeRelativeHumiditySensor1(@NonNull RelativeHumiditySensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeRelativeHumiditySensor2(RelativeHumiditySensor2, Bundle, RbtCallback)
     */
    public Integer writeRelativeHumiditySensor2(@NonNull RelativeHumiditySensor2 data) {
        return writeRelativeHumiditySensor2(data, null, null);
    }

    /**
     * Write Event pattern Relative humidity [Sensor 2] (Characteristics UUID: 0x5214)
     *
     * @param data        {@link RelativeHumiditySensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeRelativeHumiditySensor2(@NonNull RelativeHumiditySensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, RELATIVE_HUMIDITY_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeAmbientLightSensor1(AmbientLightSensor1, Bundle, RbtCallback)
     */
    public Integer writeAmbientLightSensor1(@NonNull AmbientLightSensor1 data) {
        return writeAmbientLightSensor1(data, null, null);
    }

    /**
     * Write Event pattern Ambient light [Sensor 1] (Characteristics UUID: 0x5215)
     *
     * @param data        {@link AmbientLightSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeAmbientLightSensor1(@NonNull AmbientLightSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeAmbientLightSensor2(AmbientLightSensor2, Bundle, RbtCallback)
     */
    public Integer writeAmbientLightSensor2(@NonNull AmbientLightSensor2 data) {
        return writeAmbientLightSensor2(data, null, null);
    }

    /**
     * Write Event pattern Ambient light [Sensor 2] (Characteristics UUID: 0x5216)
     *
     * @param data        {@link AmbientLightSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeAmbientLightSensor2(@NonNull AmbientLightSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, AMBIENT_LIGHT_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeBarometricPressureSensor1(BarometricPressureSensor1, Bundle, RbtCallback)
     */
    public Integer writeBarometricPressureSensor1(@NonNull BarometricPressureSensor1 data) {
        return writeBarometricPressureSensor1(data, null, null);
    }

    /**
     * Write Event pattern Barometric pressure [Sensor 1] (Characteristics UUID: 0x5217)
     *
     * @param data        {@link BarometricPressureSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeBarometricPressureSensor1(@NonNull BarometricPressureSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeBarometricPressureSensor2(BarometricPressureSensor2, Bundle, RbtCallback)
     */
    public Integer writeBarometricPressureSensor2(@NonNull BarometricPressureSensor2 data) {
        return writeBarometricPressureSensor2(data, null, null);
    }

    /**
     * Write Event pattern Barometric pressure [Sensor 2] (Characteristics UUID: 0x5218)
     *
     * @param data        {@link BarometricPressureSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeBarometricPressureSensor2(@NonNull BarometricPressureSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, BAROMETRIC_PRESSURE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeSoundNoiseSensor1(SoundNoiseSensor1, Bundle, RbtCallback)
     */
    public Integer writeSoundNoiseSensor1(@NonNull SoundNoiseSensor1 data) {
        return writeSoundNoiseSensor1(data, null, null);
    }

    /**
     * Write Event pattern Sound noise [Sensor 1] (Characteristics UUID: 0x5219)
     *
     * @param data        {@link SoundNoiseSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeSoundNoiseSensor1(@NonNull SoundNoiseSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeSoundNoiseSensor2(SoundNoiseSensor2, Bundle, RbtCallback)
     */
    public Integer writeSoundNoiseSensor2(@NonNull SoundNoiseSensor2 data) {
        return writeSoundNoiseSensor2(data, null, null);
    }

    /**
     * Write Event pattern Sound noise [Sensor 2] (Characteristics UUID: 0x521A)
     *
     * @param data        {@link SoundNoiseSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeSoundNoiseSensor2(@NonNull SoundNoiseSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SOUND_NOISE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeEtvocSensor1(EtvocSensor1, Bundle, RbtCallback)
     */
    public Integer writeEtvocSensor1(@NonNull EtvocSensor1 data) {
        return writeEtvocSensor1(data, null, null);
    }

    /**
     * Write Event pattern eTVOC [Sensor 1] (Characteristics UUID: 0x521B)
     *
     * @param data        {@link EtvocSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeEtvocSensor1(@NonNull EtvocSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeEtvocSensor2(EtvocSensor2, Bundle, RbtCallback)
     */
    public Integer writeEtvocSensor2(@NonNull EtvocSensor2 data) {
        return writeEtvocSensor2(data, null, null);
    }

    /**
     * Write Event pattern eTVOC  [Sensor 2] (Characteristics UUID: 0x521C)
     *
     * @param data        {@link EtvocSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeEtvocSensor2(@NonNull EtvocSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ETVOC_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeEco2Sensor1(Eco2Sensor1, Bundle, RbtCallback)
     */
    public Integer writeEco2Sensor1(@NonNull Eco2Sensor1 data) {
        return writeEco2Sensor1(data, null, null);
    }

    /**
     * Write Event pattern eCO2 [Sensor 1] (Characteristics UUID: 0x521D)
     *
     * @param data        {@link Eco2Sensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeEco2Sensor1(@NonNull Eco2Sensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeEco2Sensor2(Eco2Sensor2, Bundle, RbtCallback)
     */
    public Integer writeEco2Sensor2(@NonNull Eco2Sensor2 data) {
        return writeEco2Sensor2(data, null, null);
    }

    /**
     * Write Event pattern eCO2 [Sensor 2] (Characteristics UUID: 0x521E)
     *
     * @param data        {@link Eco2Sensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeEco2Sensor2(@NonNull Eco2Sensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, ECO2_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeDiscomfortIndexSensor1(DiscomfortIndexSensor1, Bundle, RbtCallback)
     */
    public Integer writeDiscomfortIndexSensor1(@NonNull DiscomfortIndexSensor1 data) {
        return writeDiscomfortIndexSensor1(data, null, null);
    }

    /**
     * Write Event pattern Discomfort index [Sensor 1] (Characteristics UUID: 0x521F)
     *
     * @param data        {@link DiscomfortIndexSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeDiscomfortIndexSensor1(@NonNull DiscomfortIndexSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeDiscomfortIndexSensor2(DiscomfortIndexSensor2, Bundle, RbtCallback)
     */
    public Integer writeDiscomfortIndexSensor2(@NonNull DiscomfortIndexSensor2 data) {
        return writeDiscomfortIndexSensor2(data, null, null);
    }

    /**
     * Write Event pattern Discomfort index [Sensor 2] (Characteristics UUID: 0x5220)
     *
     * @param data        {@link DiscomfortIndexSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeDiscomfortIndexSensor2(@NonNull DiscomfortIndexSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, DISCOMFORT_INDEX_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeHeatStrokeSensor1(HeatStrokeSensor1, Bundle, RbtCallback)
     */
    public Integer writeHeatStrokeSensor1(@NonNull HeatStrokeSensor1 data) {
        return writeHeatStrokeSensor1(data, null, null);
    }

    /**
     * Write Event pattern Heat stroke [Sensor 1] (Characteristics UUID: 0x5221)
     *
     * @param data        {@link HeatStrokeSensor1} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeHeatStrokeSensor1(@NonNull HeatStrokeSensor1 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_1_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeHeatStrokeSensor2(HeatStrokeSensor2, Bundle, RbtCallback)
     */
    public Integer writeHeatStrokeSensor2(@NonNull HeatStrokeSensor2 data) {
        return writeHeatStrokeSensor2(data, null, null);
    }

    /**
     * Write Event pattern Heat stroke [Sensor 2] (Characteristics UUID: 0x5222)
     *
     * @param data        {@link HeatStrokeSensor2} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeHeatStrokeSensor2(@NonNull HeatStrokeSensor2 data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, HEAT_STROKE_SENSOR_2_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeSiValueAcceleration(SiValueAcceleration, Bundle, RbtCallback)
     */
    public Integer writeSiValueAcceleration(@NonNull SiValueAcceleration data) {
        return writeSiValueAcceleration(data, null, null);
    }

    /**
     * Write Event pattern SI value [Acceleration] (Characteristics UUID: 0x5226)
     *
     * @param data        {@link SiValueAcceleration} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeSiValueAcceleration(@NonNull SiValueAcceleration data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SI_VALUE_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writePgaAcceleration(PgaAcceleration, Bundle, RbtCallback)
     */
    public Integer writePgaAcceleration(@NonNull PgaAcceleration data) {
        return writePgaAcceleration(data, null, null);
    }

    /**
     * Write Event pattern PGA [Acceleration] (Characteristics UUID: 0x5227)
     *
     * @param data        {@link PgaAcceleration} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writePgaAcceleration(@NonNull PgaAcceleration data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, PGA_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeSeismicIntensityAcceleration(SeismicIntensityAcceleration, Bundle, RbtCallback)
     */
    public Integer writeSeismicIntensityAcceleration(@NonNull SeismicIntensityAcceleration data) {
        return writeSeismicIntensityAcceleration(data, null, null);
    }

    /**
     * Write Event pattern Seismic intensity [Acceleration] (Characteristics UUID: 0x5228)
     *
     * @param data        {@link SeismicIntensityAcceleration} data
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeSeismicIntensityAcceleration(@NonNull SeismicIntensityAcceleration data, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createRbtWriteCharacteristicTask(EVENT_SETTING_SERVICE, SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC, data, FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING, WriteCharacteristicTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingMemorySensingData(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingMemorySensingData() {
        return readNotificationSettingMemorySensingData(null, null);
    }

    /**
     * Read notification setting Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingMemorySensingData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingMemoryCalculationData(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingMemoryCalculationData() {
        return readNotificationSettingMemoryCalculationData(null, null);
    }

    /**
     * Read notification setting Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingMemoryCalculationData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingMemorySensingFlag(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingMemorySensingFlag() {
        return readNotificationSettingMemorySensingFlag(null, null);
    }

    /**
     * Read notification setting Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingMemorySensingFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingMemoryCalculationFlag(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingMemoryCalculationFlag() {
        return readNotificationSettingMemoryCalculationFlag(null, null);
    }

    /**
     * Read notification setting Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingMemoryCalculationFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingLatestSensingData(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingLatestSensingData() {
        return readNotificationSettingLatestSensingData(null, null);
    }

    /**
     * Read notification setting Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingLatestSensingData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingLatestCalculationData(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingLatestCalculationData() {
        return readNotificationSettingLatestCalculationData(null, null);
    }

    /**
     * Read notification setting Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingLatestCalculationData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingLatestSensingFlag(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingLatestSensingFlag() {
        return readNotificationSettingLatestSensingFlag(null, null);
    }

    /**
     * Read notification setting Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingLatestSensingFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingLatestCalculationFlag(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingLatestCalculationFlag() {
        return readNotificationSettingLatestCalculationFlag(null, null);
    }

    /**
     * Read notification setting Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingLatestCalculationFlag(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingLatestAccelerationSetting(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingLatestAccelerationSetting() {
        return readNotificationSettingLatestAccelerationSetting(null, null);
    }

    /**
     * Read notification setting Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingLatestAccelerationSetting(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(LATEST_DATA_SERVICE, LATEST_ACCELERATION_STATUS_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #readNotificationSettingAccelerationMemoryData(Bundle, RbtCallback)
     */
    public Integer readNotificationSettingAccelerationMemoryData() {
        return readNotificationSettingAccelerationMemoryData(null, null);
    }

    /**
     * Read notification setting Acceleration memory data [Header] / Acceleration memory data [Data] (Characteristics UUID: 0x5034)
     *
     * @param argument    callback argument
     * @param rbtCallback {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer readNotificationSettingAccelerationMemoryData(@Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createReadDescriptorTask(ACCELERATION_SERVICE, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, ReadDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingMemorySensingData(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingMemorySensingData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingMemorySensingData(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingMemorySensingData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingMemoryCalculationData(ClientCharacteristicConfiguration)
     */
    public Integer writeNotificationSettingMemoryCalculationData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingMemoryCalculationData(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingMemoryCalculationData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingMemorySensingFlag(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingMemorySensingFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingMemorySensingFlag(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingMemorySensingFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingMemoryCalculationFlag(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingMemoryCalculationFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingMemoryCalculationFlag(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingMemoryCalculationFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(MEMORY_DATA_SERVICE, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingLatestSensingData(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingLatestSensingData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingLatestSensingData(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Latest sensing data (Characteristics UUID: 0x5012)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingLatestSensingData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingLatestCalculationData(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingLatestCalculationData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingLatestCalculationData(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Latest calculation data (Characteristics UUID: 0x5013)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingLatestCalculationData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingLatestSensingFlag(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingLatestSensingFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingLatestSensingFlag(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Latest sensing flag (Characteristics UUID: 0x5014)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingLatestSensingFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingLatestCalculationFlag(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingLatestCalculationFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingLatestCalculationFlag(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Latest calculation flag (Characteristics UUID: 0x5015)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingLatestCalculationFlag(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingLatestAccelerationSetting(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingLatestAccelerationSetting(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingLatestAccelerationSetting(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Latest acceleration status (Characteristics UUID: 0x5016)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingLatestAccelerationSetting(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(LATEST_DATA_SERVICE, LATEST_ACCELERATION_STATUS_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * @see #writeNotificationSettingAccelerationMemoryData(ClientCharacteristicConfiguration, Bundle, RbtCallback)
     */
    public Integer writeNotificationSettingAccelerationMemoryData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration) {
        return writeNotificationSettingAccelerationMemoryData(clientCharacteristicConfiguration, null, null);
    }

    /**
     * Write notification setting Acceleration memory data [Header] / Acceleration memory data [Data] (Characteristics UUID: 0x5034)
     *
     * @param clientCharacteristicConfiguration {@link ClientCharacteristicConfiguration} data
     * @param argument                          callback argument
     * @param rbtCallback                       {@code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     */
    public Integer writeNotificationSettingAccelerationMemoryData(@NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        return createWriteDescriptorTask(ACCELERATION_SERVICE, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR, clientCharacteristicConfiguration, WriteDescriptorTask.TIMEOUT_MILLIS, argument, rbtCallback);
    }

    /**
     * Create Rbt write characteristic task
     *
     * @param serviceUUID               service {@link UUID}
     * @param characteristicUUID        characteristic {@link UUID}
     * @param abstractRbtCharacteristic write data
     * @param waitTarget                {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_WRITING} for non memory reset write, {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING} for memory reset write
     * @param timeout                   timeout(millis)
     * @param argument                  callback argument
     * @param rbtCallback               @code null}:task result is communicated to all attached callbacks, {@code non-null}:the task result is communicated to the specified callback
     * @return task id. if {@code null} returned, task was not registed
     * @see RbtWriteCharacteristicTask
     */
    public Integer createRbtWriteCharacteristicTask(UUID serviceUUID, UUID characteristicUUID, AbstractRbtCharacteristic abstractRbtCharacteristic, int waitTarget, long timeout, @Nullable Bundle argument, @Nullable RbtCallback rbtCallback) {
        Integer taskId = null;
        BluetoothGatt bluetoothGatt = mBluetoothGatt;
        if (bluetoothGatt != null) {
            RbtWriteCharacteristicTask task = new RbtWriteCharacteristicTask(this, bluetoothGatt, mTaskHandler, serviceUUID, characteristicUUID, abstractRbtCharacteristic, waitTarget, timeout, BLECallbackDistributer.wrapArgument(argument, rbtCallback));
            mTaskHandler.addTask(task);
            taskId = task.getTaskId();
        }
        return taskId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        // gatt instance is not matched
        if (gatt != mBluetoothGatt) {
            return;
        }
        try {
            UUID serviceUUID = characteristic.getService().getUuid();
            UUID characteristicUUID = characteristic.getUuid();

            // notification status check
            if (mNotificationSet.contains(characteristicUUID)) {
                if (MEMORY_DATA_SERVICE.equals(serviceUUID)
                        && (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(characteristicUUID)
                        || MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(characteristicUUID)
                        || MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(characteristicUUID)
                        || MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(characteristicUUID))) {
                    mTaskHandler.sendProcessingMessage(RbtRequestMemoryIndexTask.createBatchNotifyMessage(characteristic.getValue()));
                } else if (ACCELERATION_SERVICE.equals(serviceUUID) && ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
                    mTaskHandler.sendProcessingMessage(RbtRequestAccelerationMemoryIndexTask.createBatchNotifyMessage(characteristic.getValue()));
                } else {
                    super.onCharacteristicChanged(gatt, characteristic);
                }
            }
        } catch (Exception e) {
            RbtLogUtils.stackLog(e);
        }
    }

}
