package org.im97mori.rbt.ble.task;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import org.im97mori.ble.TaskHandler;
import org.im97mori.ble.task.WriteCharacteristicTask;
import org.im97mori.ble.task.WriteDescriptorTask;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.RbtRequestMemoryIndexCallback;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.mds.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.mds.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.mds.RequestMemoryIndex;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR;
import static org.im97mori.ble.BLEConstants.ErrorCodes.BUSY;
import static org.im97mori.ble.BLEConstants.ErrorCodes.CANCEL;
import static org.im97mori.ble.BLEConstants.ErrorCodes.UNKNOWN;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_CALCULATION_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.MEMORY_SENSING_FLAG_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.REQUEST_MEMORY_INDEX_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.MEMORY_DATA_SERVICE;

/**
 * Request 0x5005 to notify 0x500a / 0x500b / 0x500c / 0x500d batch task
 */
public class RbtRequestMemoryIndexTask extends AbstractRbtTask {

    /**
     * start of data page
     */
    private static final int MINIMUM_DATA_PAGE = 1;

    /**
     * create notify Memory sensing data / Memory calculation data / Memory sensing flag / Memory calculation flag message
     *
     * @param values {@link BluetoothGattCharacteristic#getValue()}
     * @return notify Memory sensing data / Memory calculation data / Memory sensing flag / Memory calculation flag {@link Message} instance
     */
    public static Message createBatchNotifyMessage(@NonNull byte[] values) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_VALUES, values);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_BATCH_NOTIFY);
        Message message = new Message();
        message.setData(bundle);
        return message;
    }

    /**
     * task target {@link BluetoothGatt} instance
     */
    private final BluetoothGatt mBluetoothGatt;

    /**
     * task target {@link TaskHandler} instance
     */
    private final TaskHandler mTaskHandler;

    /**
     * task target data class
     */
    private final RequestMemoryIndex mRequestMemoryIndex;

    /**
     * {@link RbtRequestMemoryIndexCallback} instance
     */
    private final RbtRequestMemoryIndexCallback mRbtRequestMemoryIndexCallback;

    /**
     * notify target characteristic UUID
     */
    private UUID mNotifyTargetUUID;

    /**
     * request's total transfer count
     */
    private int mTotalTransferCount;

    /**
     * notified count
     */
    private int mCurrentTransferCount;

    /**
     * timeout(millis)
     */
    private long mTimeout;

    /**
     * callback argument
     */
    private final Bundle mArguemnt;

    /**
     * @param bluetoothGatt                 task target {@link BluetoothGatt} instance
     * @param taskHandler                   task target {@link TaskHandler} instance
     * @param requestMemoryIndex            task target data class
     * @param rbtRequestMemoryIndexCallback {@link RbtRequestMemoryIndexCallback} instance
     * @param argument                      callback argument
     */
    public RbtRequestMemoryIndexTask(@NonNull BluetoothGatt bluetoothGatt, @NonNull TaskHandler taskHandler, @NonNull RequestMemoryIndex requestMemoryIndex, @NonNull RbtRequestMemoryIndexCallback rbtRequestMemoryIndexCallback, @NonNull Bundle argument) {
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mRequestMemoryIndex = requestMemoryIndex;
        mRbtRequestMemoryIndexCallback = rbtRequestMemoryIndexCallback;
        mArguemnt = argument;
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Message createInitialMessage() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_CHECK_REQUEST);
        Message message = new Message();
        message.setData(bundle);
        message.obj = this;
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doProcess(@NonNull Message message) {
        Bundle bundle = message.getData();
        UUID serviceUUID = (UUID) bundle.getSerializable(KEY_SERVICE_UUID);
        UUID characteristicUUID = (UUID) bundle.getSerializable(KEY_CHARACTERISTIC_UUID);
        int nextProgress = bundle.getInt(KEY_NEXT_PROGRESS);

        // timeout
        if (message.obj == this && PROGRESS_TIMEOUT == nextProgress) {
            mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteTimeout(getTaskId(), mBluetoothGatt.getDevice(), mTimeout, mArguemnt);
            mCurrentProgress = nextProgress;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:check request
            if (message.obj == this && PROGRESS_CHECK_REQUEST == nextProgress) {
                int dataType = mRequestMemoryIndex.getDataType();
                int start = mRequestMemoryIndex.getMemoryIndexStart();
                int end = mRequestMemoryIndex.getMemoryIndexEnd();

                if (start >= MINIMUM_DATA_PAGE) {
                    mTotalTransferCount = end - start + 1;

                    // check target characteristic UUID
                    if (RequestMemoryIndex.DATA_TYPE_SENSING_DATA == dataType) {
                        mNotifyTargetUUID = MEMORY_SENSING_DATA_CHARACTERISTIC;
                    } else if (RequestMemoryIndex.DATA_TYPE_CALCULATION_DATA == dataType) {
                        mNotifyTargetUUID = MEMORY_CALCULATION_DATA_CHARACTERISTIC;
                    } else if (RequestMemoryIndex.DATA_TYPE_SENSING_FLAG == dataType) {
                        mNotifyTargetUUID = MEMORY_SENSING_FLAG_CHARACTERISTIC;
                    } else if (RequestMemoryIndex.DATA_TYPE_CALCULATION_FLAG == dataType) {
                        mNotifyTargetUUID = MEMORY_CALCULATION_FLAG_CHARACTERISTIC;
                    } else {
                        // wrong request

                        mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        nextProgress = PROGRESS_FINISHED;
                    }
                } else {
                    // wrong request

                    mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                    nextProgress = PROGRESS_FINISHED;
                }

                if (PROGRESS_CHECK_REQUEST == nextProgress) {
                    mTimeout = WriteDescriptorTask.TIMEOUT_MILLIS + WriteCharacteristicTask.TIMEOUT_MILLIS + DateUtils.SECOND_IN_MILLIS * mTotalTransferCount;

                    BluetoothGattDescriptor bluetoothGattDescriptor = null;
                    boolean result = false;
                    BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(MEMORY_DATA_SERVICE);
                    if (bluetoothGattService != null) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(mNotifyTargetUUID);
                        if (bluetoothGattCharacteristic != null) {
                            bluetoothGattDescriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR);
                            if (bluetoothGattDescriptor != null) {
                                bluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);

                                // write descriptor
                                try {
                                    result = mBluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
                                } catch (Exception e) {
                                    RbtLogUtils.stackLog(e);
                                }
                            }
                        }
                    }

                    if (result) {
                        // set timeout message
                        mTaskHandler.sendProcessingMessage(createTimeoutMessage(this), mTimeout);
                    } else {
                        if (bluetoothGattDescriptor == null) {
                            nextProgress = PROGRESS_FINISHED;
                            mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), BUSY, mArguemnt);
                        }
                    }
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_CHECK_REQUEST == mCurrentProgress) {
            if (MEMORY_DATA_SERVICE.equals(serviceUUID) && mNotifyTargetUUID != null && mNotifyTargetUUID.equals(characteristicUUID)) {
                // current:check request, next:write descriptor success
                if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == nextProgress) {

                    BluetoothGattCharacteristic bluetoothGattCharacteristic = null;
                    boolean result = false;
                    BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(MEMORY_DATA_SERVICE);
                    if (bluetoothGattService != null) {
                        bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(REQUEST_MEMORY_INDEX_CHARACTERISTIC);
                        if (bluetoothGattCharacteristic != null) {
                            bluetoothGattCharacteristic.setValue(mRequestMemoryIndex.getBytes());

                            // write characteristic
                            try {
                                result = mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
                            } catch (Exception e) {
                                RbtLogUtils.stackLog(e);
                            }
                        }
                    }

                    if (!result) {
                        if (bluetoothGattCharacteristic == null) {
                            nextProgress = PROGRESS_FINISHED;
                            mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), BUSY, mArguemnt);
                        }
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                } else if (PROGRESS_DESCRIPTOR_WRITE_ERROR == nextProgress) {
                    // current:check request, next:write descriptor error
                    mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS), mArguemnt);
                    nextProgress = PROGRESS_FINISHED;
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == mCurrentProgress) {
            if (MEMORY_DATA_SERVICE.equals(serviceUUID) && REQUEST_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write descriptor success, next:write characteristic success
                if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == nextProgress) {
                    mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteWriteSuccess(getTaskId(), mBluetoothGatt.getDevice(), mRequestMemoryIndex, mArguemnt);
                    mCurrentProgress = nextProgress;
                } else if (PROGRESS_CHARACTERISTIC_WRITE_ERROR == nextProgress) {
                    // current:write descriptor success, next:write characteristic error
                    mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS), mArguemnt);
                    mCurrentProgress = PROGRESS_FINISHED;
                }
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == mCurrentProgress) {
            byte[] value = bundle.getByteArray(KEY_VALUES);
            if (value != null) {
                // current:write characteristic success, next:batch notify
                if (PROGRESS_BATCH_NOTIFY == nextProgress) {

                    mCurrentTransferCount++;

                    // notify by UUID

                    if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtRequestMemoryIndexCallback.onMemorySensingDataNotified(getTaskId(), mBluetoothGatt.getDevice(), MemorySensingData.CREATOR.createFromByteArray(value), mArguemnt);
                    } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtRequestMemoryIndexCallback.onMemoryCalculationDataNotified(getTaskId(), mBluetoothGatt.getDevice(), MemoryCalculationData.CREATOR.createFromByteArray(value), mArguemnt);
                    } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtRequestMemoryIndexCallback.onMemorySensingFlagNotified(getTaskId(), mBluetoothGatt.getDevice(), MemorySensingFlag.CREATOR.createFromByteArray(value), mArguemnt);
                    } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtRequestMemoryIndexCallback.onMemoryCalculationFlagNotified(getTaskId(), mBluetoothGatt.getDevice(), MemoryCalculationFlag.CREATOR.createFromByteArray(value), mArguemnt);
                    }

                    // all data notified
                    if (mTotalTransferCount == mCurrentTransferCount) {
                        mCurrentProgress = PROGRESS_FINISHED;
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                }
            }
        }


        return PROGRESS_FINISHED == mCurrentProgress || PROGRESS_BUSY == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBusy() {
        return PROGRESS_BUSY == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancel() {
        mTaskHandler.removeCallbacksAndMessages(this);
        mCurrentProgress = PROGRESS_FINISHED;
        mRbtRequestMemoryIndexCallback.onRequestMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), CANCEL, mArguemnt);
    }

}
