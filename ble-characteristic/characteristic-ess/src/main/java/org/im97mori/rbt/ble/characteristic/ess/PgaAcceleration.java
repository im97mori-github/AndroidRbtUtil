package org.im97mori.rbt.ble.characteristic.ess;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;
import org.im97mori.rbt.RbtConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.PGA_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_PGA_UNIT;

/**
 * 2.6.3 Event pattern PGA (Characteristics UUID: 0x5227)
 */
@SuppressWarnings("WeakerAccess")
public class PgaAcceleration implements ByteArrayInterface, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<PgaAcceleration> CREATOR = new ByteArrayCreater<PgaAcceleration>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PgaAcceleration createFromParcel(@NonNull Parcel in) {
            return new PgaAcceleration(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PgaAcceleration[] newArray(int size) {
            return new PgaAcceleration[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public PgaAcceleration createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(PGA_ACCELERATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new PgaAcceleration(bluetoothGattCharacteristic);
        }

    };

    /**
     * Event enable/disable
     */
    private final int mEventEnableDisable;

    /**
     * Simple threshold [upper limit] 1
     */
    private final int mSimpleThresholdUpperLimit1;

    /**
     * Simple threshold [upper limit] 2
     */
    private final int mSimpleThresholdUpperLimit2;

    /**
     * Change threshold [rise] 1
     */
    private final int mChangeThresholdRise1;

    /**
     * Change threshold [rise] 2
     */
    private final int mChangeThresholdRise2;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5227
     */
    public PgaAcceleration(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mEventEnableDisable = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mSimpleThresholdUpperLimit1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1);
        mSimpleThresholdUpperLimit2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 3);
        mChangeThresholdRise1 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 5);
        mChangeThresholdRise2 = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 7);
    }

    /**
     * Constructor from value
     *
     * @param eventEnableDisable         Event enable/disable
     * @param simpleThresholdUpperLimit1 Simple threshold [upper limit] 1
     * @param simpleThresholdUpperLimit2 Simple threshold [upper limit] 2
     * @param changeThresholdRise1       Change threshold [rise] 1
     * @param changeThresholdRise2       Change threshold [rise] 2
     */
    public PgaAcceleration(int eventEnableDisable, int simpleThresholdUpperLimit1, int simpleThresholdUpperLimit2, int changeThresholdRise1, int changeThresholdRise2) {
        mEventEnableDisable = eventEnableDisable;
        mSimpleThresholdUpperLimit1 = simpleThresholdUpperLimit1;
        mSimpleThresholdUpperLimit2 = simpleThresholdUpperLimit2;
        mChangeThresholdRise1 = changeThresholdRise1;
        mChangeThresholdRise2 = changeThresholdRise2;
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private PgaAcceleration(@NonNull Parcel in) {
        mEventEnableDisable = in.readInt();
        mSimpleThresholdUpperLimit1 = in.readInt();
        mSimpleThresholdUpperLimit2 = in.readInt();
        mChangeThresholdRise1 = in.readInt();
        mChangeThresholdRise2 = in.readInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(mEventEnableDisable);
        dest.writeInt(mSimpleThresholdUpperLimit1);
        dest.writeInt(mSimpleThresholdUpperLimit2);
        dest.writeInt(mChangeThresholdRise1);
        dest.writeInt(mChangeThresholdRise2);
    }

    /**
     * @return Event enable/disable
     */
    public int getEventEnableDisable() {
        return mEventEnableDisable;
    }

    /**
     * check Simple threshold [upper limit] 1 enabled
     *
     * @return {@code true}:Simple threshold [upper limit] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdUpperLimit1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1) != 0;
    }

    /**
     * check Simple threshold [upper limit] 2 enabled
     *
     * @return {@code true}:Simple threshold [upper limit] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isSimpleThresholdUpperLimit2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2) != 0;
    }

    /**
     * check Change threshold [rise] 1 enabled
     *
     * @return {@code true}:Change threshold [rise] 1 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdRise1Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_1) != 0;
    }

    /**
     * check Change threshold [rise] 2 enabled
     *
     * @return {@code true}:Change threshold [rise] 2 bit is 1, {@code false}:bit is 0;
     */
    public boolean isChangeThresholdRise2Enabled() {
        return (mEventEnableDisable & RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2) != 0;
    }

    /**
     * @return Simple threshold [upper limit] 1
     */
    public int getSimpleThresholdUpperLimit1() {
        return mSimpleThresholdUpperLimit1;
    }

    /**
     * @return Simple threshold [upper limit] 1(gal)
     */
    public double getSimpleThresholdUpperLimit1Gal() {
        return mSimpleThresholdUpperLimit1 * EVENT_THRESHOLD_PGA_UNIT;
    }

    /**
     * @return Simple threshold [upper limit] 2
     */
    public int getSimpleThresholdUpperLimit2() {
        return mSimpleThresholdUpperLimit2;
    }

    /**
     * @return Simple threshold [upper limit] 2(gal)
     */
    public double getSimpleThresholdUpperLimit2Gal() {
        return mSimpleThresholdUpperLimit2 * EVENT_THRESHOLD_PGA_UNIT;
    }

    /**
     * @return Change threshold [rise] 1
     */
    public int getChangeThresholdRise1() {
        return mChangeThresholdRise1;
    }

    /**
     * @return Change threshold [rise] 1(gal)
     */
    public double getChangeThresholdRise1Gal() {
        return mChangeThresholdRise1 * EVENT_THRESHOLD_PGA_UNIT;
    }

    /**
     * @return Change threshold [rise] 2
     */
    public int getChangeThresholdRise2() {
        return mChangeThresholdRise2;
    }

    /**
     * @return Change threshold [rise] 2(gal)
     */
    public double getChangeThresholdRise2Gal() {
        return mChangeThresholdRise2 * EVENT_THRESHOLD_PGA_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[9];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mEventEnableDisable);
        byteBuffer.putShort((short) mSimpleThresholdUpperLimit1);
        byteBuffer.putShort((short) mSimpleThresholdUpperLimit2);
        byteBuffer.putShort((short) mChangeThresholdRise1);
        byteBuffer.putShort((short) mChangeThresholdRise2);
        return data;
    }

}
