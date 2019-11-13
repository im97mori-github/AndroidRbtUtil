package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallbackDistributer;
import org.im97mori.ble.BLEConstants;
import org.im97mori.rbt.ble.characteristic.tss.LatestTimeCounter;
import org.im97mori.rbt.ble.characteristic.tss.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.tss.TimeSetting;
import org.junit.Test;

import java.util.UUID;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LATEST_TIME_COUNTER_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STORAGE_INTERVAL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.TIME_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.TIME_SETTING_SERVICE;

public class RbtBLEConnectionTimeSettingServiceTest extends AbstractRbtBLEConnectionTest {

    private static final UUID SERVICE_UUID = TIME_SETTING_SERVICE;

    @Test
    public void readLatestTimeCounterSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLatestTimeCounterSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LatestTimeCounter latestTimeCounter, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLatestTimeCounterFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLatestTimeCounterFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLatestTimeCounterTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLatestTimeCounterTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLatestTimeCounterReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LATEST_TIME_COUNTER_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readTimeSettingSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readTimeSettingSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readTimeSettingFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readTimeSettingFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readTimeSettingTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readTimeSettingTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStorageIntervalSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStorageIntervalSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStorageIntervalFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStorageIntervalFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStorageIntervalTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStorageIntervalTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeTimeSettingSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeTimeSettingSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull TimeSetting timeSetting, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeTimeSettingFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeTimeSettingFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeTimeSettingTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeTimeSettingTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onTimeSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, TIME_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryStorageIntervalSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryStorageIntervalSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStorageInterval memoryStorageInterval, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryStorageIntervalFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryStorageIntervalFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryStorageIntervalTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryStorageIntervalTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStorageIntervalWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STORAGE_INTERVAL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }
}
