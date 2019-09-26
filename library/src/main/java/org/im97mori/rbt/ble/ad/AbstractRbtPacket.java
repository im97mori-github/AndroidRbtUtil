package org.im97mori.rbt.ble.ad;

import androidx.annotation.NonNull;

import org.im97mori.ble.ad.ManufacturerSpecificData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Rbt Advertising packet
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue", "unused"})
public abstract class AbstractRbtPacket {

    /**
     * 3. BLE Advertising packet data type
     */
    abstract public int getDataType();

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createSInt8(int offset, @NonNull byte[] data) {
        return createInt(data, offset, byte.class, false);
    }

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createUInt8(int offset, @NonNull byte[] data) {
        return createInt(data, offset, byte.class, true);
    }

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createSInt16(int offset, @NonNull byte[] data) {
        return createInt(data, offset, short.class, false);
    }

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createUInt16(int offset, @NonNull byte[] data) {
        return createInt(data, offset, short.class, true);
    }

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createSInt32(int offset, @NonNull byte[] data) {
        return createInt(data, offset, int.class, false);
    }

    /**
     * @see #createInt(byte[], int, Class, boolean)
     */
    protected int createUInt32(int offset, @NonNull byte[] data) {
        return createInt(data, offset, int.class, true);
    }

    /**
     * create int from byte array
     *
     * @param data       byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     * @param offset     data offset
     * @param clazz      result type. byte/short/int allowed
     * @param isUnsigned {@code true}:bit at 32 to (32 - length of clazz) are 0, {@code false}:no bit change
     * @return int type value
     */
    private int createInt(@NonNull byte[] data, int offset, @NonNull Class<? extends Number> clazz, boolean isUnsigned) {
        int result;
        int bitmask;
        ByteBuffer buffer = ByteBuffer.wrap(data, offset, data.length - offset).order(ByteOrder.LITTLE_ENDIAN);
        if (byte.class.equals(clazz)) {
            result = buffer.get();
            bitmask = 0x000000ff;
        } else if (short.class.equals(clazz)) {
            result = buffer.getShort();
            bitmask = 0x0000ffff;
        } else {
            result = buffer.getInt();
            bitmask = 0xffffffff;
        }

        if (isUnsigned) {
            result &= bitmask;
        }
        return result;
    }
}
