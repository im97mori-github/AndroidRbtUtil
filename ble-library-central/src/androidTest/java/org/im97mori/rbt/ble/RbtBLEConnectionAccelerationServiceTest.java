package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallbackDistributer;
import org.im97mori.ble.BLEConstants;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryStatus;
import org.im97mori.rbt.ble.characteristic.as.RequestAccelerationMemoryIndex;
import org.im97mori.rbt.ble.characteristic.as.VibrationCount;
import org.junit.Test;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.VIBRATION_COUNT_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.ACCELERATION_SERVICE;

public class RbtBLEConnectionAccelerationServiceTest extends AbstractRbtBLEConnectionTest {

    private static final UUID SERVICE_UUID = ACCELERATION_SERVICE;

    @Test
    public void readVibrationCountSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readVibrationCountSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull VibrationCount vibrationCount, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readVibrationCountFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readVibrationCountFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readVibrationCountTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readVibrationCountTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onVibrationCountReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, VIBRATION_COUNT_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationMemoryStatusSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationMemoryStatusSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryStatus accelerationMemoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationMemoryStatusFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationMemoryStatusFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationMemoryStatusTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationMemoryStatusTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteSuccess(getTaskId(), MOCK_DEVICE, RequestAccelerationMemoryIndex.CREATOR.createFromByteArray(new byte[6]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteSuccess(getTaskId(), MOCK_DEVICE, RequestAccelerationMemoryIndex.CREATOR.createFromByteArray(new byte[6]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), MOCK_DEVICE, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), MOCK_DEVICE, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteTimeout(getTaskId(), MOCK_DEVICE, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestAccelerationMemoryIndexTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestAccelerationMemoryIndexWriteTimeout(getTaskId(), MOCK_DEVICE, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyAccelerationMemoryDataHeaderTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onAccelerationMemoryDataHeaderNotified(getTaskId(), MOCK_DEVICE, new AccelerationMemoryDataHeader(), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyAccelerationMemoryDataHeaderTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onAccelerationMemoryDataHeaderNotified(getTaskId(), MOCK_DEVICE, new AccelerationMemoryDataHeader(), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyAccelerationMemoryDataTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onAccelerationMemoryDataNotified(getTaskId(), MOCK_DEVICE, new AccelerationMemoryData(), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyAccelerationMemoryDataTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestAccelerationMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onAccelerationMemoryDataNotified(getTaskId(), MOCK_DEVICE, new AccelerationMemoryData(), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {
            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingAccelerationMemoryDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {
            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingAccelerationMemoryDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationMemoryDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }
}
