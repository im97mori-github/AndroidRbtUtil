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
import org.im97mori.rbt.ble.RbtRequestAccelerationMemoryIndexCallback;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData1;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData10;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData11;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData12;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData13;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData2;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData3;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData4;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData5;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData6;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData7;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData8;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryData9;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader1;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader2;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader3;
import org.im97mori.rbt.ble.characteristic.as.AccelerationMemoryDataHeader4;
import org.im97mori.rbt.ble.characteristic.as.RequestAccelerationMemoryIndex;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR;
import static org.im97mori.ble.BLEConstants.ErrorCodes.BUSY;
import static org.im97mori.ble.BLEConstants.ErrorCodes.CANCEL;
import static org.im97mori.ble.BLEConstants.ErrorCodes.UNKNOWN;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.ACCELERATION_SERVICE;

/**
 * Request 0x5032 to notify 0x5032 batch task
 */
public class RbtRequestAccelerationMemoryIndexTask extends AbstractRbtTask {

    /**
     * header data count
     */
    private static final int HEADER_TRANSFER_COUNT = 4;

    /**
     * non-header data count
     */
    private static final int DATA_TRANSFER_COUNT = 13;

    /**
     * header page number for normal mode
     */
    private static final int HEADER_PAGE = 0;

    /**
     * start of data page
     */
    private static final int MINIMUM_DATA_PAGE = 1;

    /**
     * maximum page number for normal mode
     */
    private static final int MAXIMUM_DATA_PAGE_NORMAL_MODE = 511;

    /**
     * maximum page number for acceleration logger mode
     */
    private static final int MAXIMUM_DATA_PAGE_ACCELERATION_LOGGER_MODE = 10240;

    /**
     * maximun page length per request for acceleration logger mode
     */
    private static final int MAXIMUM_DATA_PAGE_LENGTH_ACCELERATION_LOGGER_MODE = 1000;

    /**
     * create notify Acceleration memory data [Header] / Acceleration memory data [Data] message
     *
     * @param values {@link BluetoothGattCharacteristic#getValue()}
     * @return notify Acceleration memory data [Header] / Acceleration memory data [Data] {@link Message} instance
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
    private final RequestAccelerationMemoryIndex mRequestAccelerationMemoryIndex;

    /**
     * {@link RbtRequestAccelerationMemoryIndexCallback} instance
     */
    private final RbtRequestAccelerationMemoryIndexCallback mRbtRequestAccelerationMemoryIndexCallback;

    /**
     * {@code true}:request with header, {@code false}:request with no header
     */
    private boolean mHasHeader;

    /**
     * {@link AccelerationMemoryDataHeader} isntance
     */
    private AccelerationMemoryDataHeader mAccelerationMemoryDataHeader;

    /**
     * {@link AccelerationMemoryData} isntance
     */
    private AccelerationMemoryData mAccelerationMemoryData;

    /**
     * lastest page was notified
     */
    private int mLastestPage = -1;

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
     * @param bluetoothGatt                             task target {@link BluetoothGatt} instance
     * @param taskHandler                               task target {@link TaskHandler} instance
     * @param requestAccelerationMemoryIndex            task target data class
     * @param rbtRequestAccelerationMemoryIndexCallback {@link RbtRequestAccelerationMemoryIndexCallback} instance
     * @param argument                                  callback argument
     */
    public RbtRequestAccelerationMemoryIndexTask(@NonNull BluetoothGatt bluetoothGatt, @NonNull TaskHandler taskHandler, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @NonNull RbtRequestAccelerationMemoryIndexCallback rbtRequestAccelerationMemoryIndexCallback, @NonNull Bundle argument) {
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mRequestAccelerationMemoryIndex = requestAccelerationMemoryIndex;
        mRbtRequestAccelerationMemoryIndexCallback = rbtRequestAccelerationMemoryIndexCallback;
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
            mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteTimeout(getTaskId(), mBluetoothGatt.getDevice(), mTimeout, mArguemnt);
            mCurrentProgress = nextProgress;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:check request
            if (message.obj == this && PROGRESS_CHECK_REQUEST == nextProgress) {
                int dataType = mRequestAccelerationMemoryIndex.getAccelerationDataType();
                int start = mRequestAccelerationMemoryIndex.getRequestPageStart();
                int end = mRequestAccelerationMemoryIndex.getRequestPageEnd();

                // earthquake or vibration data
                if (RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_EARTHQUAKE_DATA == dataType || RequestAccelerationMemoryIndex.ACCELERATION_DATA_TYPE_VIBRATION_DATA == dataType) {
                    if (start >= HEADER_PAGE && end <= MAXIMUM_DATA_PAGE_NORMAL_MODE && start <= end) {

                        mTotalTransferCount = (end - start) * DATA_TRANSFER_COUNT;

                        // with header
                        if (start == HEADER_PAGE) {
                            mHasHeader = true;
                            mAccelerationMemoryDataHeader = new AccelerationMemoryDataHeader();
                            mTotalTransferCount += HEADER_TRANSFER_COUNT;
                        } else {
                            // no header
                            mTotalTransferCount += DATA_TRANSFER_COUNT;
                        }
                    } else {
                        // wrong request

                        mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        nextProgress = PROGRESS_FINISHED;
                    }
                } else {
                    // logger data

                    if (start >= MINIMUM_DATA_PAGE && end <= MAXIMUM_DATA_PAGE_ACCELERATION_LOGGER_MODE && end - start <= MAXIMUM_DATA_PAGE_LENGTH_ACCELERATION_LOGGER_MODE) {
                        mTotalTransferCount = (end - start + 1) * DATA_TRANSFER_COUNT;
                    } else {
                        // wrong request

                        mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        nextProgress = PROGRESS_FINISHED;
                    }
                }

                if (PROGRESS_CHECK_REQUEST == nextProgress) {
                    mTimeout = WriteDescriptorTask.TIMEOUT_MILLIS + WriteCharacteristicTask.TIMEOUT_MILLIS + DateUtils.SECOND_IN_MILLIS * mTotalTransferCount;

                    BluetoothGattDescriptor bluetoothGattDescriptor = null;
                    boolean result = false;
                    BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(ACCELERATION_SERVICE);
                    if (bluetoothGattService != null) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(ACCELERATION_MEMORY_DATA_CHARACTERISTIC);
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
                            mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), BUSY, mArguemnt);
                        }
                    }
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_CHECK_REQUEST == mCurrentProgress) {
            if (ACCELERATION_SERVICE.equals(serviceUUID) && ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:check request, next:write descriptor success
                if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == nextProgress) {

                    BluetoothGattCharacteristic bluetoothGattCharacteristic = null;
                    boolean result = false;
                    BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(ACCELERATION_SERVICE);
                    if (bluetoothGattService != null) {
                        bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC);
                        if (bluetoothGattCharacteristic != null) {
                            bluetoothGattCharacteristic.setValue(mRequestAccelerationMemoryIndex.getBytes());

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
                            mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), UNKNOWN, mArguemnt);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), BUSY, mArguemnt);
                        }
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                } else if (PROGRESS_DESCRIPTOR_WRITE_ERROR == nextProgress) {
                    // current:check request, next:write descriptor error
                    mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS), mArguemnt);
                    nextProgress = PROGRESS_FINISHED;
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == mCurrentProgress) {
            if (ACCELERATION_SERVICE.equals(serviceUUID) && REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write descriptor success, next:write characteristic success
                if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == nextProgress) {
                    mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteSuccess(getTaskId(), mBluetoothGatt.getDevice(), mRequestAccelerationMemoryIndex, mArguemnt);
                    mCurrentProgress = nextProgress;
                } else if (PROGRESS_CHARACTERISTIC_WRITE_ERROR == nextProgress) {
                    // current:write descriptor success, next:write characteristic error
                    mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS), mArguemnt);
                    mCurrentProgress = PROGRESS_FINISHED;
                }
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == mCurrentProgress) {
            byte[] values = bundle.getByteArray(KEY_VALUES);
            if (values != null) {
                // current:write characteristic success, next:batch notify
                if (PROGRESS_BATCH_NOTIFY == nextProgress) {
                    int totalTransferCount = ((values[0] | values[1] << 8) & 0b01111111);

                    mCurrentTransferCount++;

                    // header
                    if (mHasHeader && totalTransferCount <= HEADER_TRANSFER_COUNT) {

                        if (totalTransferCount == 1) {
                            mAccelerationMemoryDataHeader.setAccelerationMemoryDataHeader1(AccelerationMemoryDataHeader1.CREATOR.createFromByteArray(values));
                        } else if (totalTransferCount == 2) {
                            mAccelerationMemoryDataHeader.setAccelerationMemoryDataHeader2(AccelerationMemoryDataHeader2.CREATOR.createFromByteArray(values));
                        } else if (totalTransferCount == 3) {
                            mAccelerationMemoryDataHeader.setAccelerationMemoryDataHeader3(AccelerationMemoryDataHeader3.CREATOR.createFromByteArray(values));
                        } else if (totalTransferCount == 4) {
                            mAccelerationMemoryDataHeader.setAccelerationMemoryDataHeader4(AccelerationMemoryDataHeader4.CREATOR.createFromByteArray(values));
                        }

                        // notify header data
                        if (mCurrentTransferCount == HEADER_TRANSFER_COUNT) {
                            mRbtRequestAccelerationMemoryIndexCallback.onAccelerationMemoryDataHeaderNotified(getTaskId(), mBluetoothGatt.getDevice(), mAccelerationMemoryDataHeader, mArguemnt);

                        }

                        // only header request
                        if (mTotalTransferCount == mCurrentTransferCount) {
                            // finish
                            mCurrentProgress = PROGRESS_FINISHED;
                            // remove timeout message
                            mTaskHandler.removeCallbacksAndMessages(this);
                        }
                    } else {
                        // non-header

                        // request has header
                        if (mHasHeader) {
                            totalTransferCount -= HEADER_TRANSFER_COUNT;
                        }
                        totalTransferCount--;
                        int page = totalTransferCount / DATA_TRANSFER_COUNT;
                        int index = totalTransferCount % DATA_TRANSFER_COUNT;

                        // if page changed, notify data
                        if (mLastestPage != page) {
                            if (mLastestPage != -1) {
                                mRbtRequestAccelerationMemoryIndexCallback.onAccelerationMemoryDataNotified(getTaskId(), mBluetoothGatt.getDevice(), mAccelerationMemoryData, mArguemnt);
                            }
                            mAccelerationMemoryData = new AccelerationMemoryData();
                            mLastestPage = page;
                        }

                        if (index == 0) {
                            mAccelerationMemoryData.setAccelerationMemoryData1(AccelerationMemoryData1.CREATOR.createFromByteArray(values));
                        } else if (index == 1) {
                            mAccelerationMemoryData.setAccelerationMemoryData2(AccelerationMemoryData2.CREATOR.createFromByteArray(values));
                        } else if (index == 2) {
                            mAccelerationMemoryData.setAccelerationMemoryData3(AccelerationMemoryData3.CREATOR.createFromByteArray(values));
                        } else if (index == 3) {
                            mAccelerationMemoryData.setAccelerationMemoryData4(AccelerationMemoryData4.CREATOR.createFromByteArray(values));
                        } else if (index == 4) {
                            mAccelerationMemoryData.setAccelerationMemoryData5(AccelerationMemoryData5.CREATOR.createFromByteArray(values));
                        } else if (index == 5) {
                            mAccelerationMemoryData.setAccelerationMemoryData6(AccelerationMemoryData6.CREATOR.createFromByteArray(values));
                        } else if (index == 6) {
                            mAccelerationMemoryData.setAccelerationMemoryData7(AccelerationMemoryData7.CREATOR.createFromByteArray(values));
                        } else if (index == 7) {
                            mAccelerationMemoryData.setAccelerationMemoryData8(AccelerationMemoryData8.CREATOR.createFromByteArray(values));
                        } else if (index == 8) {
                            mAccelerationMemoryData.setAccelerationMemoryData9(AccelerationMemoryData9.CREATOR.createFromByteArray(values));
                        } else if (index == 9) {
                            mAccelerationMemoryData.setAccelerationMemoryData10(AccelerationMemoryData10.CREATOR.createFromByteArray(values));
                        } else if (index == 10) {
                            mAccelerationMemoryData.setAccelerationMemoryData11(AccelerationMemoryData11.CREATOR.createFromByteArray(values));
                        } else if (index == 11) {
                            mAccelerationMemoryData.setAccelerationMemoryData12(AccelerationMemoryData12.CREATOR.createFromByteArray(values));
                        } else if (index == 12) {
                            mAccelerationMemoryData.setAccelerationMemoryData13(AccelerationMemoryData13.CREATOR.createFromByteArray(values));
                        }

                        // all data notified
                        if (mTotalTransferCount == mCurrentTransferCount || mTotalTransferCount == totalTransferCount) {
                            // notiy last page data
                            mRbtRequestAccelerationMemoryIndexCallback.onAccelerationMemoryDataNotified(getTaskId(), mBluetoothGatt.getDevice(), mAccelerationMemoryData, mArguemnt);

                            mCurrentProgress = PROGRESS_FINISHED;
                            // remove timeout message
                            mTaskHandler.removeCallbacksAndMessages(this);
                        }
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
        mRbtRequestAccelerationMemoryIndexCallback.onRequestAccelerationMemoryIndexWriteFailed(getTaskId(), mBluetoothGatt.getDevice(), CANCEL, mArguemnt);
    }

}
