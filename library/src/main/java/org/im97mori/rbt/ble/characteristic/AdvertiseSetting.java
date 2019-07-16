package org.im97mori.rbt.ble.characteristic;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2.4.5 Advertise setting (Characteristics UUID: 0x5115)
 */
@SuppressWarnings("WeakerAccess")
public class AdvertiseSetting extends AbstractCharacteristic implements Parcelable {

    /**
     * EventThreshold: 0.625ms
     */
    public static final double ADVERTISING_INTERVAL_UNIT = 0.625d;

    /**
     * 0x01: Sensor data (default)
     */
    public static final int ADVERTISING_MODE_SENSOR_DATA = 0x01;

    /**
     * 0x02: Calculation data
     */
    public static final int ADVERTISING_MODE_CALCULATION_DATA = 0x02;

    /**
     * 0x03: Sensor data & Calculation data (Scan rsp)
     */
    public static final int ADVERTISING_MODE_SENSOR_DATA_AND_CALCULATION_DATA = 0x03;

    /**
     * 0x04: Sensor flag & Calculation flag (Scan rsp)
     */
    public static final int ADVERTISING_MODE_SENSOR_FLAG_AND_CALCULATION_FLAG = 0x04;

    /**
     * 0x05: Serial number
     */
    public static final int ADVERTISING_MODE_SERIAL_NUMBER = 0x05;

    /**
     * @see Creator
     */
    public static final Creator<AdvertiseSetting> CREATOR = new Creator<AdvertiseSetting>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public AdvertiseSetting createFromParcel(Parcel in) {
            return new AdvertiseSetting(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AdvertiseSetting[] newArray(int size) {
            return new AdvertiseSetting[size];
        }

    };

    /**
     * Advertising interval
     */
    private final int mAdvertisingInterval;

    /**
     * Advertising mode
     */
    private final int mAdvertisingMode;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5115
     */
    public AdvertiseSetting(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mAdvertisingInterval = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0);
        mAdvertisingMode = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private AdvertiseSetting(Parcel in) {
        mAdvertisingInterval = in.readInt();
        mAdvertisingMode = in.readInt();
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
        dest.writeInt(mAdvertisingInterval);
        dest.writeInt(mAdvertisingMode);
    }

    /**
     * @return Advertising interval
     */
    public int getAdvertisingInterval() {
        return mAdvertisingInterval;
    }

    /**
     * @return Advertising interval(millis)
     */
    public double getAdvertisingIntervalMillis() {
        return mAdvertisingInterval * ADVERTISING_INTERVAL_UNIT;
    }

    /**
     * @return Advertising mode
     */
    public int getAdvertisingMode() {
        return mAdvertisingMode;
    }

}
