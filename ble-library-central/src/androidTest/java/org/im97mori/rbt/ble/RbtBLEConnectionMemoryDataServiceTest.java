package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallbackDistributer;
import org.im97mori.ble.BLEConstants;
import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemoryIndexInformation;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemoryStatus;
import org.im97mori.rbt.ble.characteristic.mds.RequestMemoryIndex;
import org.junit.Test;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_INDEX_INFORMATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.MEMORY_DATA_SERVICE;

public class RbtBLEConnectionMemoryDataServiceTest extends AbstractRbtBLEConnectionTest {

    private static final UUID SERVICE_UUID = MEMORY_DATA_SERVICE;

    @Test
    public void readMemoryIndexInformationSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryIndexInformationSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryIndexInformation memoryIndexInformation, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, new byte[8], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryIndexInformationFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryIndexInformationFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryIndexInformationTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryIndexInformationTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryIndexInformationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_INDEX_INFORMATION_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStatusSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, new byte[11], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStatusSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryStatus memoryStatus, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, new byte[11], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStatusFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStatusFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readMemoryStatusTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readMemoryStatusTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryStatusReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onCharacteristicReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_STATUS_CHARACTERISTIC, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }


    @Test
    public void writeRequestMemoryIndexSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteWriteSuccess(getTaskId(), MOCK_DEVICE, RequestMemoryIndex.CREATOR.createFromByteArray(new byte[9]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestMemoryIndexSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteWriteSuccess(getTaskId(), MOCK_DEVICE, RequestMemoryIndex.CREATOR.createFromByteArray(new byte[9]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeRequestMemoryIndexFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteFailed(getTaskId(), MOCK_DEVICE, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestMemoryIndexFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteFailed(getTaskId(), MOCK_DEVICE, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeRequestMemoryIndexTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteTimeout(getTaskId(), MOCK_DEVICE, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeRequestMemoryIndexTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onRequestMemoryIndexWriteTimeout(getTaskId(), MOCK_DEVICE, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyMemorySensingDataTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemorySensingDataNotified(getTaskId(), MOCK_DEVICE, MemorySensingData.CREATOR.createFromByteArray(new byte[20]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyMemorySensingDataTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemorySensingDataNotified(getTaskId(), MOCK_DEVICE, MemorySensingData.CREATOR.createFromByteArray(new byte[20]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyMemoryCalculationDataTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemoryCalculationDataNotified(getTaskId(), MOCK_DEVICE, MemoryCalculationData.CREATOR.createFromByteArray(new byte[15]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyMemoryCalculationDataTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemoryCalculationDataNotified(getTaskId(), MOCK_DEVICE, MemoryCalculationData.CREATOR.createFromByteArray(new byte[15]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyMemorySensingFlagTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemorySensingFlagNotified(getTaskId(), MOCK_DEVICE, MemorySensingFlag.CREATOR.createFromByteArray(new byte[18]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyMemorySensingFlagTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemorySensingFlagNotified(getTaskId(), MOCK_DEVICE, MemorySensingFlag.CREATOR.createFromByteArray(new byte[18]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void notifyMemoryCalculationFlagTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemoryCalculationFlagNotified(getTaskId(), MOCK_DEVICE, MemoryCalculationFlag.CREATOR.createFromByteArray(new byte[11]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void notifyMemoryCalculationFlagTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                ((RbtRequestMemoryIndexCallback) MOCK_BLE_CONNECTION.getBLECallback()).onMemoryCalculationFlagNotified(getTaskId(), MOCK_DEVICE, MemoryCalculationFlag.CREATOR.createFromByteArray(new byte[11]), argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {


            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {


            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemorySensingFlagTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void readNotificationSettingMemoryCalculationFlagTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationReadTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorReadTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {


            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {


            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationDataTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationDataClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_DATA_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemorySensingFlagTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemorySensingFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_SENSING_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagSuccessTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagSuccessTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull ClientCharacteristicConfiguration clientCharacteristicConfiguration, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteSuccess(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, new byte[2], argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagFailedTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagFailedTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteFailed(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, BLEConstants.ErrorCodes.UNKNOWN, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagTimeoutTest_001() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }
        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, null);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, true);
    }

    @Test
    public void writeNotificationSettingMemoryCalculationFlagTimeoutTest_002() {
        BaseBLECallback firstCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };
        BaseBLECallback secondCallback = new BaseBLECallback() {

            @Override
            public void onMemoryCalculationFlagClientCharactericsticConfigurationWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument) {
                result.set(true);
            }

        };

        final Bundle argument = BLECallbackDistributer.wrapArgument(null, firstCallback);
        MockBLETask task = new MockBLETask() {
            @Override
            public boolean doProcess(@NonNull Message message) {
                MOCK_BLE_CONNECTION.getBLECallback().onDescriptorWriteTimeout(getTaskId(), MOCK_DEVICE, SERVICE_UUID, MEMORY_CALCULATION_FLAG_CHARACTERISTIC, CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR, 0, argument);
                isProccesing.set(false);
                return true;
            }
        };

        check(firstCallback, secondCallback, task, false);
    }

}
