package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_SERIAL_NUMBER;
import static org.junit.Assert.assertEquals;

public class SerialNumberTest {

    @Test
    public void test1() {
        byte[] data = new byte[15];
        data[0] = DATA_TYPE_SERIAL_NUMBER;
        data[1] = (byte) 0x30;
        data[2] = (byte) 0x30;
        data[3] = (byte) 0x30;
        data[4] = (byte) 0x30;
        data[5] = (byte) 0x4d;
        data[6] = (byte) 0x59;
        data[7] = (byte) 0x30;
        data[8] = (byte) 0x30;
        data[9] = (byte) 0x30;
        data[10] = (byte) 0x30;
        data[11] = (byte) 0x01;
        data[12] = (byte) 0x00;
        data[13] = (byte) 0x00;
        data[14] = (byte) 0x00;

        SerialNumber result1 = new SerialNumber(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SerialNumber result2 = SerialNumber.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getDataType(), result1.getDataType());
        assertEquals(result2.getSerialNumber(), result1.getSerialNumber());
        assertEquals(result2.getMemoryIndex(), result1.getMemoryIndex());
    }

    @Test
    public void test2() {
        byte[] data = new byte[15];
        data[0] = DATA_TYPE_SERIAL_NUMBER;
        data[1] = (byte) 0x33;
        data[2] = (byte) 0x39;
        data[3] = (byte) 0x5A;
        data[4] = (byte) 0x39;
        data[5] = (byte) 0x4d;
        data[6] = (byte) 0x59;
        data[7] = (byte) 0x39;
        data[8] = (byte) 0x39;
        data[9] = (byte) 0x39;
        data[10] = (byte) 0x39;
        data[11] = (byte) 0xff;
        data[12] = (byte) 0xff;
        data[13] = (byte) 0xff;
        data[14] = (byte) 0x7f;

        SerialNumber result1 = new SerialNumber(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        SerialNumber result2 = SerialNumber.CREATOR.createFromParcel(parcel);

        assertEquals(result2.getDataType(), result1.getDataType());
        assertEquals(result2.getSerialNumber(), result1.getSerialNumber());
        assertEquals(result2.getMemoryIndex(), result1.getMemoryIndex());
    }
}
