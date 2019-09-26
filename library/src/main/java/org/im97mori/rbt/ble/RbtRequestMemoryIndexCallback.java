package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationData;
import org.im97mori.rbt.ble.characteristic.MemoryCalculationFlag;
import org.im97mori.rbt.ble.characteristic.MemorySensingData;
import org.im97mori.rbt.ble.characteristic.MemorySensingFlag;
import org.im97mori.rbt.ble.characteristic.RequestMemoryIndex;

/**
 * callback interface for memory index
 */
public interface RbtRequestMemoryIndexCallback extends BLECallback {

    /**
     * Write success callback Request memory index  (Characteristics UUID: 0x5005)
     *
     * @param taskId                         task id
     * @param bluetoothDevice                Rbt device
     * @param requestMemoryIndex write data
     * @param argument                       callback argument
     */
    void onRequestMemoryIndexWriteWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestMemoryIndex requestMemoryIndex, @Nullable Bundle argument);

    /**
     * Write failed callback Request memory index (Characteristics UUID: 0x5005)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRequestMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Request memory index (Characteristics UUID: 0x5005)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRequestMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Memory sensing data (Characteristics UUID: 0x500A)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param memorySensingData notification data
     * @param argument          callback argument
     */
    void onMemorySensingDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingData memorySensingData, @Nullable Bundle argument);


    /**
     * Notification callback Memory calculation data (Characteristics UUID: 0x500B)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param memoryCalculationData notification data
     * @param argument              callback argument
     */
    void onMemoryCalculationDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationData memoryCalculationData, @Nullable Bundle argument);

    /**
     * Notification callback Memory sensing flag (Characteristics UUID: 0x500C)
     *
     * @param taskId            task id
     * @param bluetoothDevice   Rbt device
     * @param memorySensingFlag notification data
     * @param argument          callback argument
     */
    void onMemorySensingFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemorySensingFlag memorySensingFlag, @Nullable Bundle argument);

    /**
     * Notification callback Memory calculation flag (Characteristics UUID: 0x500D)
     *
     * @param taskId                task id
     * @param bluetoothDevice       Rbt device
     * @param memoryCalculationFlag notification data
     * @param argument              callback argument
     */
    void onMemoryCalculationFlagNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull MemoryCalculationFlag memoryCalculationFlag, @Nullable Bundle argument);

}
