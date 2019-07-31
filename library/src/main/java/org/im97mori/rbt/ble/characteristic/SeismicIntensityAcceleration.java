package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.rbt.RbtConstants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC;
import static org.im97mori.rbt.RbtConstants.EventThreshold.EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT;

/**
 * 2.6.3 Event pattern Seismic intensity (Characteristics UUID: 0x5228)
 */
@SuppressWarnings("WeakerAccess")
public class SeismicIntensityAcceleration extends AbstractRbtCharacteristic implements Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<SeismicIntensityAcceleration> CREATOR = new ByteArrayCreater<SeismicIntensityAcceleration>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public SeismicIntensityAcceleration createFromParcel(Parcel in) {
            return new SeismicIntensityAcceleration(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SeismicIntensityAcceleration[] newArray(int size) {
            return new SeismicIntensityAcceleration[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SeismicIntensityAcceleration createFromByteArray(byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(SEISMIC_INTENSITY_ACCELERATION_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new SeismicIntensityAcceleration(bluetoothGattCharacteristic);
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
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5228
     */
    public SeismicIntensityAcceleration(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
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
    public SeismicIntensityAcceleration(int eventEnableDisable, int simpleThresholdUpperLimit1, int simpleThresholdUpperLimit2, int changeThresholdRise1, int changeThresholdRise2) {
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
    private SeismicIntensityAcceleration(Parcel in) {
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
    public void writeToParcel(Parcel dest, int flags) {
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
     * @return Simple threshold [upper limit] 1(Unit 0.001)
     */
    public double getSimpleThresholdUpperLimit1WithUnit() {
        return mSimpleThresholdUpperLimit1 * EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT;
    }

    /**
     * @return Simple threshold [upper limit] 2
     */
    public int getSimpleThresholdUpperLimit2() {
        return mSimpleThresholdUpperLimit2;
    }

    /**
     * @return Simple threshold [upper limit] 2(Unit 0.001)
     */
    public double getSimpleThresholdUpperLimit2WithUnit() {
        return mSimpleThresholdUpperLimit2 * EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT;
    }

    /**
     * @return Change threshold [rise] 1
     */
    public int getChangeThresholdRise1() {
        return mChangeThresholdRise1;
    }

    /**
     * @return Change threshold [rise] 1(Unit 0.001)
     */
    public double getChangeThresholdRise1WithUnit() {
        return mChangeThresholdRise1 * EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT;
    }

    /**
     * @return Change threshold [rise] 2
     */
    public int getChangeThresholdRise2() {
        return mChangeThresholdRise2;
    }

    /**
     * @return Change threshold [rise] 2(Unit 0.001)
     */
    public double getChangeThresholdRise2WithUnit() {
        return mChangeThresholdRise2 * EVENT_THRESHOLD_SEISMIC_INTENSITY_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
