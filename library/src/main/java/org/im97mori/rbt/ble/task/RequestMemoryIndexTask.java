package org.im97mori.rbt.ble.task;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;

import org.im97mori.ble.TaskHandler;
import org.im97mori.ble.task.WriteCharacteristicTask;
import org.im97mori.ble.task.WriteDescriptorTask;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.RbtCallback;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.RequestMemoryIndex;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR;
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
public class RequestMemoryIndexTask extends AbstractRbtTask {

    /**
     * start of data page
     */
    private static final int MINIMUM_DATA_PAGE = 1;

    /**
     * create notify Memory sensing data / Memory calculation data / Memory sensing flag / Memory calculation flag message
     *
     * @param characteristicUUID target characteristic UUID
     * @param values             {@link BluetoothGattCharacteristic#getValue()}
     * @return notify Memory sensing data / Memory calculation data / Memory sensing flag / Memory calculation flag {@link Message} instance
     */
    public static Message createBatchNotifyMessage(UUID characteristicUUID, byte[] values) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_VALUES, values);
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, characteristicUUID);
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
     * <p>
     * {@link RbtCallback} instance
     * <p>
     * this task dont use {@link org.im97mori.ble.BLECallback}(bypass to RbtCallback)
     * </p>
     */
    private final RbtCallback mRbtCallback;

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
     * @param bluetoothGatt      task target {@link BluetoothGatt} instance
     * @param taskHandler        task target {@link TaskHandler} instance
     * @param requestMemoryIndex task target data class
     * @param rbtCallback        {@link RbtCallback} instance
     */
    public RequestMemoryIndexTask(BluetoothGatt bluetoothGatt, TaskHandler taskHandler, RequestMemoryIndex requestMemoryIndex, RbtCallback rbtCallback) {
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mRequestMemoryIndex = requestMemoryIndex;
        mRbtCallback = rbtCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message createInitialMessage() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, REQUEST_MEMORY_INDEX_CHARACTERISTIC);
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
    public boolean doProcess(Message message) {
        Bundle bundle = message.getData();
        UUID characteristicUUID = (UUID) bundle.getSerializable(KEY_CHARACTERISTIC_UUID);
        int nextProgress = bundle.getInt(KEY_NEXT_PROGRESS);

        // timeout
        if (message.obj == this && PROGRESS_TIMEOUT == nextProgress) {
            mRbtCallback.onRequestMemoryIndexWriteTimeout(mBluetoothGatt.getDevice(), mTimeout);
            mCurrentProgress = nextProgress;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:check request
            if (REQUEST_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
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

                            mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                            nextProgress = PROGRESS_FINISHED;
                        }
                    } else {
                        // wrong request

                        mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
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
                                bluetoothGattDescriptor = bluetoothGattCharacteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR);
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
                            mTaskHandler.sendProcessingMessage(createTimeoutMessage(REQUEST_MEMORY_INDEX_CHARACTERISTIC, this), mTimeout);
                        } else {
                            if (bluetoothGattDescriptor == null) {
                                nextProgress = PROGRESS_FINISHED;
                                mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                            } else {
                                nextProgress = PROGRESS_BUSY;
                                mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), BUSY);
                            }
                        }
                    }

                    mCurrentProgress = nextProgress;
                }
            }
        } else if (PROGRESS_CHECK_REQUEST == mCurrentProgress) {
            if (mNotifyTargetUUID != null && mNotifyTargetUUID.equals(characteristicUUID)) {
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
                            if (!mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic)) {
                                nextProgress = PROGRESS_FINISHED;
                                mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                                // remove timeout message
                                mTaskHandler.removeCallbacksAndMessages(this);
                            }
                        }
                    }

                    if (!result) {
                        if (bluetoothGattCharacteristic == null) {
                            nextProgress = PROGRESS_FINISHED;
                            mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), BUSY);
                        }
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                } else if (PROGRESS_DESCRIPTOR_WRITE_ERROR == nextProgress) {
                    // current:check request, next:write descriptor error
                    mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS));
                    nextProgress = PROGRESS_FINISHED;
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == mCurrentProgress) {
            if (REQUEST_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write descriptor success, next:write characteristic success
                if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == nextProgress) {
                    mCurrentProgress = nextProgress;
                } else if (PROGRESS_CHARACTERISTIC_WRITE_ERROR == nextProgress) {
                    // current:write descriptor success, next:write characteristic error
                    mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS));
                    mCurrentProgress = PROGRESS_FINISHED;
                }
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == mCurrentProgress) {
            if (mNotifyTargetUUID != null && mNotifyTargetUUID.equals(characteristicUUID)) {
                // current:write characteristic success, next:batch notify
                if (PROGRESS_BATCH_NOTIFY == nextProgress) {

                    mCurrentTransferCount++;

                    // notify by UUID
                    if (MEMORY_SENSING_DATA_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtCallback.onMemorySensingDataNotified(mBluetoothGatt.getDevice(), MemorySensingData.CREATOR.createFromByteArray(bundle.getByteArray(KEY_VALUES)));
                    } else if (MEMORY_CALCULATION_DATA_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtCallback.onMemoryCalculationDataNotified(mBluetoothGatt.getDevice(), MemoryCalculationData.CREATOR.createFromByteArray(bundle.getByteArray(KEY_VALUES)));
                    } else if (MEMORY_SENSING_FLAG_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtCallback.onMemorySensingFlagNotified(mBluetoothGatt.getDevice(), MemorySensingFlag.CREATOR.createFromByteArray(bundle.getByteArray(KEY_VALUES)));
                    } else if (MEMORY_CALCULATION_FLAG_CHARACTERISTIC.equals(mNotifyTargetUUID)) {
                        mRbtCallback.onMemoryCalculationFlagNotified(mBluetoothGatt.getDevice(), MemoryCalculationFlag.CREATOR.createFromByteArray(bundle.getByteArray(KEY_VALUES)));
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
        mRbtCallback.onRequestMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), CANCEL);
    }

}
