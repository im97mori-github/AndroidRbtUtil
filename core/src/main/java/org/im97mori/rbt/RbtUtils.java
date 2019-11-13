package org.im97mori.rbt;

import android.bluetooth.BluetoothGattCharacteristic;

import androidx.annotation.NonNull;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Rbt Core Utilities
 */
@SuppressWarnings("WeakerAccess")
public class RbtUtils {

    /**
     * Create {@link BigInteger} instance
     *
     * @param data   {@link BluetoothGattCharacteristic#getValue()}
     * @param offset offset for time counter 8bytes data
     * @param length second parameter length of {@link BigInteger#BigInteger(int, byte[])}
     * @return Unsigned 64bit(8bytes) integer value
     */
    public static BigInteger createBigInteger(@NonNull byte[] data, int offset, int length) {
        // little to big endian
        byte[] reversed = new byte[length];
        for (int i = 0; i < length; i++) {
            reversed[i] = data[length - i - 1 + offset];
        }
        return new BigInteger(1, reversed);
    }

    /**
     * Create little-endian byte array from {@link BigInteger}
     *
     * @param bigInteger original BigInteger instance
     * @param length     byte array length
     * @return little-endian byte array
     */
    public static byte[] createLittleEndianByteArrayFromBigInteger(@NonNull BigInteger bigInteger, int length) {
        byte[] original = bigInteger.toByteArray();
        byte[] data = new byte[length];
        if (bigInteger.signum() == -1) {
            Arrays.fill(data, (byte) -1);
        }
        for (int i = 0; i < original.length; i++) {
            data[i] = original[original.length - i - 1];
        }
        return data;
    }
}
