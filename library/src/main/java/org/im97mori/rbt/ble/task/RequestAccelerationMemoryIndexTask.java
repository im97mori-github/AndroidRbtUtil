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
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData1;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData10;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData11;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData12;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData13;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData2;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData3;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData4;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData5;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData6;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData7;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData8;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData9;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader1;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader2;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader3;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader4;
import org.im97mori.rbt.ble.characteristic.RequestAccelerationMemoryIndex;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.DescriptorUUID.CLIENT_CHARACTERISTIC_CONFIGRATION_DESCRIPTOR;
import static org.im97mori.ble.BLEConstants.ErrorCodes.BUSY;
import static org.im97mori.ble.BLEConstants.ErrorCodes.CANCEL;
import static org.im97mori.ble.BLEConstants.ErrorCodes.UNKNOWN;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ACCELERATION_MEMORY_DATA_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.ACCELERATION_SERVICE;

/**
 * Request 0x5032 to notify 0x5032 batch task
 */
@SuppressWarnings({"JavadocReference"})
public class RequestAccelerationMemoryIndexTask extends AbstractRbtTask {

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
     * create write Request acceleration memory index message
     *
     * @param obj instance for {@link android.os.Handler#removeCallbacksAndMessages(Object)}
     * @return write Request acceleration memory index {@link Message} instance
     */
    public static Message createWriteRequestAccelerationMemoryIndexMessage(Object obj) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC);
        bundle.putInt(KEY_NEXT_PROGRESS, PROGRESS_CHECK_REQUEST);
        Message message = new Message();
        message.setData(bundle);
        message.obj = obj;
        return message;
    }

    /**
     * create notify Acceleration memory data [Header] / Acceleration memory data [Data] message
     *
     * @param values {@link BluetoothGattCharacteristic#getValue()}
     * @return notify Acceleration memory data [Header] / Acceleration memory data [Data] {@link Message} instance
     */
    public static Message createBatchNotifyMessage(byte[] values) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_VALUES, values);
        bundle.putSerializable(KEY_CHARACTERISTIC_UUID, ACCELERATION_MEMORY_DATA_CHARACTERISTIC);
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
     * <p>
     * {@link RbtCallback} instance
     * <p>
     * this task dont use {@link org.im97mori.ble.BLECallback}(bypass to RbtCallback)
     * </p>
     */
    private final RbtCallback mRbtCallback;

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
     * @param bluetoothGatt                  task target {@link BluetoothGatt} instance
     * @param taskHandler                    task target {@link TaskHandler} instance
     * @param requestAccelerationMemoryIndex task target data class
     * @param rbtCallback                    {@link RbtCallback} instance
     */
    public RequestAccelerationMemoryIndexTask(BluetoothGatt bluetoothGatt, TaskHandler taskHandler, RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, RbtCallback rbtCallback) {
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mRequestAccelerationMemoryIndex = requestAccelerationMemoryIndex;
        mRbtCallback = rbtCallback;
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
            mRbtCallback.onRequestAccelerationMemoryIndexWriteTimeout(mBluetoothGatt.getDevice(), mTimeout);
            mCurrentProgress = nextProgress;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:check request
            if (REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
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

                            mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                            nextProgress = PROGRESS_FINISHED;
                        }
                    } else {
                        // logger data

                        if (start >= MINIMUM_DATA_PAGE && end <= MAXIMUM_DATA_PAGE_ACCELERATION_LOGGER_MODE && end - start <= MAXIMUM_DATA_PAGE_LENGTH_ACCELERATION_LOGGER_MODE) {
                            mTotalTransferCount = (end - start + 1) * DATA_TRANSFER_COUNT;
                        } else {
                            // wrong request

                            mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
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
                            mTaskHandler.sendProcessingMessage(createTimeoutMessage(REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC, this), mTimeout);
                        } else {
                            if (bluetoothGattDescriptor == null) {
                                nextProgress = PROGRESS_FINISHED;
                                mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                            } else {
                                nextProgress = PROGRESS_BUSY;
                                mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), BUSY);
                            }
                        }
                    }

                    mCurrentProgress = nextProgress;
                }
            }
        } else if (PROGRESS_CHECK_REQUEST == mCurrentProgress) {
            if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
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
                            mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), UNKNOWN);
                        } else {
                            nextProgress = PROGRESS_BUSY;
                            mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), BUSY);
                        }
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                } else if (PROGRESS_DESCRIPTOR_WRITE_ERROR == nextProgress) {
                    // current:check request, next:write descriptor error
                    mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS));
                    nextProgress = PROGRESS_FINISHED;
                }

                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_DESCRIPTOR_WRITE_SUCCESS == mCurrentProgress) {
            if (REQUEST_ACCELERATION_MEMORY_INDEX_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write descriptor success, next:write characteristic success
                if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == nextProgress) {
                    mCurrentProgress = nextProgress;
                } else if (PROGRESS_CHARACTERISTIC_WRITE_ERROR == nextProgress) {
                    // current:write descriptor success, next:write characteristic error
                    mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), bundle.getInt(KEY_STATUS));
                    mCurrentProgress = PROGRESS_FINISHED;
                }
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == mCurrentProgress) {
            if (ACCELERATION_MEMORY_DATA_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write characteristic success, next:batch notify
                if (PROGRESS_BATCH_NOTIFY == nextProgress) {
                    byte[] values = bundle.getByteArray(KEY_VALUES);
                    @SuppressWarnings("ConstantConditions") int totalTransferCount = ((values[0] | values[1] << 8) & 0b01111111);

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
                            mRbtCallback.onAccelerationMemoryDataHeaderNotified(mBluetoothGatt.getDevice(), mAccelerationMemoryDataHeader);

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
                                mRbtCallback.onAccelerationMemoryDataNotified(mBluetoothGatt.getDevice(), mAccelerationMemoryData);
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
                            mRbtCallback.onAccelerationMemoryDataNotified(mBluetoothGatt.getDevice(), mAccelerationMemoryData);

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
        mRbtCallback.onRequestAccelerationMemoryIndexWriteFailed(mBluetoothGatt.getDevice(), CANCEL);
    }

}
