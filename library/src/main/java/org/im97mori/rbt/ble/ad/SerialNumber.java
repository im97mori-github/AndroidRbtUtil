package org.im97mori.rbt.ble.ad;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.BLEUtils;
import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.advertising.ManufacturerSpecificData;
import org.im97mori.rbt.RbtConstants;

/**
 * 3.5 Serial number
 */
public class SerialNumber implements RbtPacket, Parcelable {

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<SerialNumber> CREATOR = new ByteArrayCreater<SerialNumber>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SerialNumber createFromParcel(@NonNull Parcel in) {
            return new SerialNumber(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public SerialNumber[] newArray(int size) {
            return new SerialNumber[size];
        }

        /**
         * {@inheritDoc}
         */
        @NonNull
        @Override
        public SerialNumber createFromByteArray(@NonNull byte[] values) {
            return new SerialNumber(values);
        }

    };

    /**
     * Serial number
     */
    private final String mSerialNumber;

    /**
     * Memory index (Latest)
     */
    private final long mMemoryIndex;

    /**
     * Constructor for SerialNumber
     *
     * @param data byte array from {@link ManufacturerSpecificData#getManufacturerSpecificData()}
     */
    public SerialNumber(@NonNull byte[] data) {
        mSerialNumber = new String(data, 1, 10);
        mMemoryIndex = BLEUtils.createUInt32(data, 11);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private SerialNumber(@NonNull Parcel in) {
        mSerialNumber = in.readString();
        mMemoryIndex = in.readLong();
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
        dest.writeString(mSerialNumber);
        dest.writeLong(mMemoryIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDataType() {
        return RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
    }

    /**
     * @return Serial number
     */
    public String getSerialNumber() {
        return mSerialNumber;
    }

    /**
     * @return Memory index (Latest)
     */
    public long getMemoryIndex() {
        return mMemoryIndex;
    }

}
