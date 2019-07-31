package org.im97mori.rbt.ble.task;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;

import org.im97mori.ble.BLEConnection;
import org.im97mori.ble.TaskHandler;
import org.im97mori.ble.task.WriteCharacteristicTask;
import org.im97mori.rbt.ble.characteristic.AbstractRbtCharacteristic;
import org.im97mori.rbt.ble.characteristic.FlashMemoryStatus;

import java.util.UUID;

import static org.im97mori.ble.BLEConstants.ErrorCodes.UNKNOWN;
import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.FLASH_MEMORY_STATUS_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.ServiceUUID.INFORMATION_SERVICE;
import static org.im97mori.rbt.ble.characteristic.FlashMemoryStatus.FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING;
import static org.im97mori.rbt.ble.characteristic.FlashMemoryStatus.FLASH_MEMORY_STATUS_NONE;
import static org.im97mori.rbt.ble.characteristic.FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITE_SUCCESS;
import static org.im97mori.rbt.ble.characteristic.FlashMemoryStatus.FLASH_MEMORY_STATUS_WRITING;

/**
 * <p>
 * Rbt write characteristic task
 * <p>
 * Read {@link FlashMemoryStatus} after characteristic write for get result
 * </p>
 */
public class RbtWriteCharacteristicTask extends AbstractRbtTask {

    /**
     * task target {@link BLEConnection} instance
     */
    private final BLEConnection mBLEConnection;

    /**
     * task target {@link BluetoothGatt} instance
     */
    private final BluetoothGatt mBluetoothGatt;


    /**
     * task target {@link TaskHandler} instance
     */
    private final TaskHandler mTaskHandler;

    /**
     * task target service {@link UUID}
     */
    private final UUID mServiceUUID;

    /**
     * task target characteristic {@link UUID}
     */
    private final UUID mCharacteristicUUID;


    /**
     * task target data class
     */
    private final AbstractRbtCharacteristic mAbstractRbtCharacteristic;

    /**
     * {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_WRITING} for non memory reset write, {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING} for memory reset write
     */
    private final int mWaitTarget;

    /**
     * timeout(millis)
     */
    private final long mTimeout;

    /**
     * @param bleConnection             task target {@link BLEConnection} instance
     * @param bluetoothGatt             task target {@link BluetoothGatt} instance
     * @param taskHandler               task target service {@link UUID}
     * @param serviceUUID               task target characteristic {@link UUID}
     * @param characteristicUUID        task target {@link TaskHandler} instance
     * @param abstractRbtCharacteristic task target data class
     * @param waitTarget                {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_WRITING} for non memory reset write, {@link FlashMemoryStatus#FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING} for memory reset write
     * @param timeout                   timeout(millis)
     */
    public RbtWriteCharacteristicTask(BLEConnection bleConnection, BluetoothGatt bluetoothGatt, TaskHandler taskHandler, UUID serviceUUID, UUID characteristicUUID, AbstractRbtCharacteristic abstractRbtCharacteristic, int waitTarget, long timeout) {
        mBLEConnection = bleConnection;
        mBluetoothGatt = bluetoothGatt;
        mTaskHandler = taskHandler;
        mServiceUUID = serviceUUID;
        mCharacteristicUUID = characteristicUUID;
        mAbstractRbtCharacteristic = abstractRbtCharacteristic;
        mWaitTarget = waitTarget;
        mTimeout = timeout;
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
            mBLEConnection.getBLECallback().onCharacteristicWriteTimeout(mBluetoothGatt.getDevice(), mCharacteristicUUID, mTimeout);
            mCurrentProgress = nextProgress;
        } else if (PROGRESS_INIT == mCurrentProgress) {
            // current:init, next:write start
            if (message.obj == this && PROGRESS_CHARACTERISTIC_WRITE_START == nextProgress) {

                boolean result = false;
                BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(mServiceUUID);
                if (bluetoothGattService != null) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(mCharacteristicUUID);
                    if (bluetoothGattCharacteristic != null) {
                        bluetoothGattCharacteristic.setValue(mAbstractRbtCharacteristic.getBytes());

                        // write characteristic
                        result = mBluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
                    }
                }

                if (result) {
                    // set timeout message
                    mTaskHandler.sendProcessingMessage(createTimeoutMessage(mCharacteristicUUID, this), mTimeout);
                } else {
                    nextProgress = PROGRESS_FINISHED;
                    mBLEConnection.getBLECallback().onCharacteristicWriteFailed(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, UNKNOWN);
                }
                mCurrentProgress = nextProgress;
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_START == mCurrentProgress) {
            if (mCharacteristicUUID.equals(characteristicUUID)) {
                // current:write start, next:write success
                if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == nextProgress) {
                    BluetoothGattService bluetoothGattService = mBluetoothGatt.getService(INFORMATION_SERVICE);
                    if (bluetoothGattService != null) {
                        BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattService.getCharacteristic(FLASH_MEMORY_STATUS_CHARACTERISTIC);
                        if (bluetoothGattCharacteristic != null) {
                            // read FLASH memory status characteristic
                            if (!mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic)) {
                                nextProgress = PROGRESS_FINISHED;

                                mBLEConnection.getBLECallback().onCharacteristicWriteFailed(mBLEConnection.getBluetoothDevice(), mCharacteristicUUID, UNKNOWN);
                                // remove timeout message
                                mTaskHandler.removeCallbacksAndMessages(this);
                            }
                        }
                    }
                    mCurrentProgress = nextProgress;
                } else if (PROGRESS_CHARACTERISTIC_WRITE_ERROR == nextProgress) {
                    mCurrentProgress = PROGRESS_FINISHED;

                    mBLEConnection.getBLECallback().onCharacteristicWriteFailed(mBluetoothGatt.getDevice(), mCharacteristicUUID, bundle.getInt(KEY_STATUS));
                    // remove timeout message
                    mTaskHandler.removeCallbacksAndMessages(this);
                }
            }
        } else if (PROGRESS_CHARACTERISTIC_WRITE_SUCCESS == mCurrentProgress) {
            if (FLASH_MEMORY_STATUS_CHARACTERISTIC.equals(characteristicUUID)) {
                // current:write success, next:read success
                if (PROGRESS_CHARACTERISTIC_READ_SUCCESS == nextProgress) {
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(FLASH_MEMORY_STATUS_CHARACTERISTIC, 0, 0);
                    bluetoothGattCharacteristic.setValue(bundle.getByteArray(KEY_VALUES));
                    FlashMemoryStatus data = new FlashMemoryStatus(bluetoothGattCharacteristic);
                    int flashMemoryStatus = data.getFlashMemoryStatus();

                    // check waiting target
                    if (mWaitTarget == flashMemoryStatus) {
                        // retry read

                        mCurrentProgress = PROGRESS_CHARACTERISTIC_WRITE_START;
                        mTaskHandler.sendProcessingMessage(WriteCharacteristicTask.createWriteCharacteristicFinishedMessage(mCharacteristicUUID, null), DateUtils.SECOND_IN_MILLIS * 5);
                    } else if ((mWaitTarget == FLASH_MEMORY_STATUS_WRITING && FLASH_MEMORY_STATUS_WRITE_SUCCESS == flashMemoryStatus)
                            || (mWaitTarget == FLASH_MEMORY_STATUS_FLASH_MEMORY_ERASING && FLASH_MEMORY_STATUS_NONE == flashMemoryStatus)) {
                        // write success

                        mBLEConnection.getBLECallback().onCharacteristicWriteSuccess(mBluetoothGatt.getDevice(), mCharacteristicUUID, mAbstractRbtCharacteristic.getBytes());

                        mCurrentProgress = PROGRESS_FINISHED;
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    } else {
                        // write failed

                        mBLEConnection.getBLECallback().onCharacteristicWriteFailed(mBluetoothGatt.getDevice(), mCharacteristicUUID, UNKNOWN);

                        mCurrentProgress = PROGRESS_FINISHED;
                        // remove timeout message
                        mTaskHandler.removeCallbacksAndMessages(this);
                    }
                } else if (PROGRESS_CHARACTERISTIC_READ_ERROR == nextProgress) {
                    mBLEConnection.getBLECallback().onCharacteristicWriteFailed(mBluetoothGatt.getDevice(), mCharacteristicUUID, bundle.getInt(KEY_STATUS));
                    mCurrentProgress = PROGRESS_FINISHED;
                    // remove timeout message
                    mTaskHandler.removeCallbacksAndMessages(this);
                }
            }
        }

        return PROGRESS_FINISHED == mCurrentProgress || PROGRESS_TIMEOUT == mCurrentProgress;
    }

}
