package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;

import java.math.BigInteger;

/**
 * Rbt characteristic
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractCharacteristic {

    /**
     * Create BigInteger instance
     *
     * @param data   {@link BluetoothGattCharacteristic#getValue()}
     * @param offset offset for time counter 8bytes data
     * @param length second parameter length of {@link BigInteger#BigInteger(int, byte[])}
     * @return Unsigned 64bit(8bytes) integer value
     */
    protected static BigInteger createBigInteger(byte[] data, int offset, int length) {
        // little to big endian
        byte[] reversed = new byte[length];
        for (int i = 0; i < length; i++) {
            reversed[i] = data[length - i - 1 + offset];
        }
        return new BigInteger(1, reversed);
    }

}
