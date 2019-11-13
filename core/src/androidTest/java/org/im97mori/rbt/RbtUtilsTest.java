package org.im97mori.rbt;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RbtUtilsTest {

    @Test
    public void test_createBigInteger001() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        data[ 6] = 0x07;
        data[ 7] = 0x08;

        byte[] reverse = new byte[8];
        reverse[ 0] = 0x08;
        reverse[ 1] = 0x07;
        reverse[ 2] = 0x06;
        reverse[ 3] = 0x05;
        reverse[ 4] = 0x04;
        reverse[ 5] = 0x03;
        reverse[ 6] = 0x02;
        reverse[ 7] = 0x01;
        //@formatter:on

        BigInteger result1 = RbtUtils.createBigInteger(data, 0, 8);
        assertEquals(new BigInteger(reverse), result1);
    }

    @Test
    public void test_createBigInteger002() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) 0xff;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        data[ 6] = 0x07;
        data[ 7] = 0x08;

        byte[] reverse = new byte[8];
        reverse[ 0] = 0x08;
        reverse[ 1] = 0x07;
        reverse[ 2] = 0x06;
        reverse[ 3] = 0x05;
        reverse[ 4] = 0x04;
        reverse[ 5] = 0x03;
        reverse[ 6] = 0x02;
        reverse[ 7] = (byte) 0xff;
        //@formatter:on

        BigInteger result1 = RbtUtils.createBigInteger(data, 0, 8);
        assertEquals(new BigInteger(1, reverse), result1);
    }

    @Test
    public void test_createBigInteger003() {
        //@formatter:off
        byte[] data = new byte[9];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        data[ 6] = 0x07;
        data[ 7] = 0x08;
        data[ 8] = 0x09;

        byte[] reverse = new byte[8];
        reverse[ 0] = 0x09;
        reverse[ 1] = 0x08;
        reverse[ 2] = 0x07;
        reverse[ 3] = 0x06;
        reverse[ 4] = 0x05;
        reverse[ 5] = 0x04;
        reverse[ 6] = 0x03;
        reverse[ 7] = 0x02;
        //@formatter:on

        BigInteger result1 = RbtUtils.createBigInteger(data, 1, 8);
        assertEquals(new BigInteger(reverse), result1);
    }

    @Test
    public void test_createBigInteger004() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;

        byte[] reverse = new byte[4];
        reverse[ 0] = 0x04;
        reverse[ 1] = 0x03;
        reverse[ 2] = 0x02;
        reverse[ 3] = 0x01;
        //@formatter:on

        BigInteger result1 = RbtUtils.createBigInteger(data, 0, 4);
        assertEquals(new BigInteger(reverse), result1);
    }

    @Test
    public void test_createLittleEndianByteArrayFromBigInteger001() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        data[ 6] = 0x07;
        data[ 7] = 0x08;

        byte[] reverse = new byte[8];
        reverse[ 0] = 0x08;
        reverse[ 1] = 0x07;
        reverse[ 2] = 0x06;
        reverse[ 3] = 0x05;
        reverse[ 4] = 0x04;
        reverse[ 5] = 0x03;
        reverse[ 6] = 0x02;
        reverse[ 7] = 0x01;
        //@formatter:on

        byte[] result1 = RbtUtils.createLittleEndianByteArrayFromBigInteger(new BigInteger(reverse), 8);
        assertArrayEquals(data, result1);
    }

    @Test
    public void test_createLittleEndianByteArrayFromBigInteger002() {
        //@formatter:off
        byte[] data = new byte[8];
        data[ 0] = (byte) 0xff;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;
        data[ 4] = 0x05;
        data[ 5] = 0x06;
        data[ 6] = 0x07;
        data[ 7] = 0x08;

        byte[] reverse = new byte[8];
        reverse[ 0] = 0x08;
        reverse[ 1] = 0x07;
        reverse[ 2] = 0x06;
        reverse[ 3] = 0x05;
        reverse[ 4] = 0x04;
        reverse[ 5] = 0x03;
        reverse[ 6] = 0x02;
        reverse[ 7] = (byte) 0xff;
        //@formatter:on

        byte[] result1 = RbtUtils.createLittleEndianByteArrayFromBigInteger(new BigInteger(1, reverse), 8);
        assertArrayEquals(data, result1);
    }

    @Test
    public void test_createLittleEndianByteArrayFromBigInteger003() {
        //@formatter:off
        byte[] data = new byte[4];
        data[ 0] = 0x01;
        data[ 1] = 0x02;
        data[ 2] = 0x03;
        data[ 3] = 0x04;

        byte[] reverse = new byte[4];
        reverse[ 0] = 0x04;
        reverse[ 1] = 0x03;
        reverse[ 2] = 0x02;
        reverse[ 3] = 0x01;
        //@formatter:on

        byte[] result1 = RbtUtils.createLittleEndianByteArrayFromBigInteger(new BigInteger(reverse), 4);
        assertArrayEquals(data, result1);
    }

}
