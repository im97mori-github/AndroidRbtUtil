package org.im97mori.rbt.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.BLECallback;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryData;
import org.im97mori.rbt.ble.characteristic.AccelerationMemoryDataHeader;
import org.im97mori.rbt.ble.characteristic.RequestAccelerationMemoryIndex;

/**
 * callback interface for acceleration memory index
 */
public interface RbtRequestAccelerationMemoryIndexCallback extends BLECallback {

    /**
     * Write success callback Request acceleration memory index  (Characteristics UUID: 0x5032)
     *
     * @param taskId                         task id
     * @param bluetoothDevice                Rbt device
     * @param requestAccelerationMemoryIndex write data
     * @param argument                       callback argument
     */
    void onRequestAccelerationMemoryIndexWriteSuccess(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull RequestAccelerationMemoryIndex requestAccelerationMemoryIndex, @Nullable Bundle argument);

    /**
     * Write failed callback Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param status          error status
     * @param argument        callback argument
     */
    void onRequestAccelerationMemoryIndexWriteFailed(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, int status, @Nullable Bundle argument);

    /**
     * Write timeout callback Request acceleration memory index (Characteristics UUID: 0x5032)
     *
     * @param taskId          task id
     * @param bluetoothDevice Rbt device
     * @param timeout         timeout(millis)
     * @param argument        callback argument
     */
    void onRequestAccelerationMemoryIndexWriteTimeout(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, long timeout, @Nullable Bundle argument);

    /**
     * Notification callback Acceleration memory data [Header] (Characteristics UUID: 0x5034)
     *
     * @param taskId                       task id
     * @param bluetoothDevice              Rbt device
     * @param accelerationMemoryDataHeader notification data
     * @param argument                     callback argument
     */
    void onAccelerationMemoryDataHeaderNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryDataHeader accelerationMemoryDataHeader, @Nullable Bundle argument);

    /**
     * Notification callback Acceleration memory data [Data] (Characteristics UUID: 0x5034)
     *
     * @param taskId                 task id
     * @param bluetoothDevice        Rbt device
     * @param accelerationMemoryData notification data
     * @param argument               callback argument
     */
    void onAccelerationMemoryDataNotified(@NonNull Integer taskId, @NonNull BluetoothDevice bluetoothDevice, @NonNull AccelerationMemoryData accelerationMemoryData, @Nullable Bundle argument);
}
