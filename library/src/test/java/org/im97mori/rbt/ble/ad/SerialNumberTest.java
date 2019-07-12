package org.im97mori.rbt.ble.ad;

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

        SerialNumber result = new SerialNumber(data);
        assertEquals(DATA_TYPE_SERIAL_NUMBER, result.getDataType());
        assertEquals("0000MY0000", result.getSerialNumber());
        assertEquals(1, result.getMemoryIndex());
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

        SerialNumber result = new SerialNumber(data);
        assertEquals(DATA_TYPE_SERIAL_NUMBER, result.getDataType());
        assertEquals("39Z9MY9999", result.getSerialNumber());
        assertEquals(2147483647, result.getMemoryIndex());
    }
}
