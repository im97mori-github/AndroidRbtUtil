package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallbackDistributer;
import org.im97mori.ble.BLEConstants;
import org.im97mori.rbt.ble.characteristic.cs.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.cs.AccelerationLoggerStatus;
import org.im97mori.rbt.ble.characteristic.cs.AdvertiseSetting;
import org.im97mori.rbt.ble.characteristic.cs.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.cs.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.cs.MemoryReset;
import org.im97mori.rbt.ble.characteristic.cs.ModeChange;
import org.junit.Test;

import java.util.UUID;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_LOGGER_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ADVERTISE_SETTING_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.INSTALLATION_OFFSET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_EVENT_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_SETTING_NORMAL_STATE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.LED_STATE_OPERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_RESET_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MODE_CHANGE_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.CONTROL_SERVICE;

public class RbtBLEConnectionControlServiceTest extends AbstractRbtBLEConnectionTest {

    private static final UUID SERVICE_UUID = CONTROL_SERVICE;

    @Test
    public void readLedSettingNormalStateSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingNormalStateSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedSettingNormalStateFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingNormalStateFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedSettingNormalStateTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingNormalStateTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedSettingEventStateSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingEventStateSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedSettingEventStateFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingEventStateFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedSettingEventStateTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedSettingEventStateTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedStateOperationSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedStateOperationSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedStateOperationFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedStateOperationFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readLedStateOperationTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readLedStateOperationTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readInstallationOffsetSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, new byte[13], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readInstallationOffsetSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, new byte[13], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readInstallationOffsetFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readInstallationOffsetFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readInstallationOffsetTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readInstallationOffsetTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAdvertisingSettingSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAdvertisingSettingSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAdvertisingSettingFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAdvertisingSettingFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAdvertisingSettingTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAdvertisingSettingTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readModeChangeSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readModeChangeSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readModeChangeFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readModeChangeFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readModeChangeTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readModeChangeTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationLoggerStatusSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationLoggerStatusSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationLoggerStatus accelerationLoggerStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationLoggerStatusFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationLoggerStatusFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readAccelerationLoggerStatusTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readAccelerationLoggerStatusTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingNormalStateSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingNormalStateSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingNormalState ledSettingNormalState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingNormalStateFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingNormalStateFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingNormalStateTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingNormalStateTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingNormalStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_NORMAL_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingEventStateSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingEventStateSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedSettingEventState ledSettingEventState, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, new byte[5], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingEventStateFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingEventStateFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedSettingEventStateTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedSettingEventStateTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedSettingEventStateWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_SETTING_EVENT_STATE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedStateOperationSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedStateOperationSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull LedStateOperation ledStateOperation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedStateOperationFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedStateOperationFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeLedStateOperationTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeLedStateOperationTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onLedStateOperationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, LED_STATE_OPERATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeInstallationOffsetSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, new byte[13], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeInstallationOffsetSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull InstallationOffset installationOffset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, new byte[13], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeInstallationOffsetFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeInstallationOffsetFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeInstallationOffsetTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeInstallationOffsetTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onInstallationOffsetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, INSTALLATION_OFFSET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAdvertisingSettingSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAdvertisingSettingSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AdvertiseSetting advertiseSetting, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, new byte[3], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAdvertisingSettingFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAdvertisingSettingFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAdvertisingSettingTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAdvertisingSettingTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAdvertiseSettingWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ADVERTISE_SETTING_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryResetSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryResetSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryReset memoryReset, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryResetFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryResetFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeMemoryResetTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeMemoryResetTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryResetWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_RESET_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeModeChangeSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeModeChangeSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ModeChange modeChange, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, new byte[1], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeModeChangeFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeModeChangeFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeModeChangeTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeModeChangeTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onModeChangeWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MODE_CHANGE_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAccelerationLoggerControlSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, new byte[7], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAccelerationLoggerControlSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, AccelerationLoggerControl accelerationLoggerControl, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, new byte[7], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAccelerationLoggerControlFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAccelerationLoggerControlFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeAccelerationLoggerControlTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeAccelerationLoggerControlTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onAccelerationLoggerControlWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, ACCELERATION_LOGGER_CONTROL_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }
}
