package org.im97mori.rbt.ble.ad;

import android.os.Parcel;

import org.junit.Test;

import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT;
import static org.im97mori.rbt.RbtConstants.OutputRange.VIBRATION_INFORMATION_NONE_BIT;
import static org.im97mori.rbt.RbtConstants.RbtAdvertisingDataType.DATA_TYPE_CALCULATION_DATA;
import static org.junit.Assert.assertEquals;

public class CalculationDataTest {

    @Test
    public void test1() {
        //@formatter:off
        byte[] data = new byte[27];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x00; // Discomfort index
        data[ 3] = (byte) 0x00; // Discomfort index
        data[ 4] = (byte) 0x60; // Heat stroke
        data[ 5] = (byte) 0xf0; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 7] = (byte) 0x00; // SI value
        data[ 8] = (byte) 0x00; // SI value
        data[ 9] = (byte) 0x00; // PGA
        data[10] = (byte) 0x00; // PGA
        data[11] = (byte) 0x00; // Seismic intensity
        data[12] = (byte) 0x00; // Seismic intensity
        data[13] = (byte) 0xe0; // Acceleration (X-axis)
        data[14] = (byte) 0xb1; // Acceleration (X-axis)
        data[15] = (byte) 0xe0; // Acceleration (Y-axis)
        data[16] = (byte) 0xb1; // Acceleration (Y-axis)
        data[17] = (byte) 0xe0; // Acceleration (Z-axis)
        data[18] = (byte) 0xb1; // Acceleration (Z-axis)
        data[19] = (byte) 0xff; // Reserve for Future Use
        data[20] = (byte) 0xff; // Reserve for Future Use
        data[21] = (byte) 0xff; // Reserve for Future Use
        data[22] = (byte) 0xff; // Reserve for Future Use
        data[23] = (byte) 0xff; // Reserve for Future Use
        data[24] = (byte) 0xff; // Reserve for Future Use
        data[25] = (byte) 0xff; // Reserve for Future Use
        data[26] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        CalculationData result = new CalculationData(data);
        assertEquals(DATA_TYPE_CALCULATION_DATA, result.getDataType());
        assertEquals(0, result.getSequenceNumber());
        assertEquals(0, result.getDiscomfortIndex());
        assertEquals(0d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(-4000, result.getHeatStroke());
        assertEquals(-40.00d, result.getHeatStrokeDegC(), 0);
        assertEquals(VIBRATION_INFORMATION_NONE_BIT, result.getVibrationInformation());
        assertEquals(0, result.getSiValue());
        assertEquals(0.0d, result.getSiValueKine(), 0);
        assertEquals(0, result.getPga());
        assertEquals(0.0d, result.getPgaGal(), 0);
        assertEquals(0, result.getSeismicIntensity());
        assertEquals(0.00d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(-20000, result.getAccelerationXAxis());
        assertEquals(-2000.0d, result.getAccelerationXAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationYAxis());
        assertEquals(-2000.0d, result.getAccelerationYAxisGal(), 0);
        assertEquals(-20000, result.getAccelerationZAxis());
        assertEquals(-2000.0d, result.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test2() {
        //@formatter:off
        byte[] data = new byte[27];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0x10; // Discomfort index
        data[ 3] = (byte) 0x27; // Discomfort index
        data[ 4] = (byte) 0xd4; // Heat stroke
        data[ 5] = (byte) 0x30; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0xff; // SI value
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0xff; // PGA
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0xff; // Seismic intensity
        data[13] = (byte) 0x20; // Acceleration (X-axis)
        data[14] = (byte) 0x4e; // Acceleration (X-axis)
        data[15] = (byte) 0x20; // Acceleration (Y-axis)
        data[16] = (byte) 0x4e; // Acceleration (Y-axis)
        data[17] = (byte) 0x20; // Acceleration (Z-axis)
        data[18] = (byte) 0x4e; // Acceleration (Z-axis)
        data[19] = (byte) 0xff; // Reserve for Future Use
        data[20] = (byte) 0xff; // Reserve for Future Use
        data[21] = (byte) 0xff; // Reserve for Future Use
        data[22] = (byte) 0xff; // Reserve for Future Use
        data[23] = (byte) 0xff; // Reserve for Future Use
        data[24] = (byte) 0xff; // Reserve for Future Use
        data[25] = (byte) 0xff; // Reserve for Future Use
        data[26] = (byte) 0xff; // Reserve for Future Use
        //@formatter:on

        CalculationData result = new CalculationData(data);
        assertEquals(DATA_TYPE_CALCULATION_DATA, result.getDataType());
        assertEquals(255, result.getSequenceNumber());
        assertEquals(10000, result.getDiscomfortIndex());
        assertEquals(100.00d, result.getDiscomfortIndexWithUnit(), 0);
        assertEquals(12500, result.getHeatStroke());
        assertEquals(125.00d, result.getHeatStrokeDegC(), 0);
        assertEquals(VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT, result.getVibrationInformation());
        assertEquals(65535, result.getSiValue());
        assertEquals(6553.5d, result.getSiValueKine(), 0);
        assertEquals(65535, result.getPga());
        assertEquals(6553.5d, result.getPgaGal(), 0);
        assertEquals(65535, result.getSeismicIntensity());
        assertEquals(65.535d, result.getSeismicIntensityWithUnit(), 0);
        assertEquals(20000, result.getAccelerationXAxis());
        assertEquals(2000.0d, result.getAccelerationXAxisGal(), 0);
        assertEquals(20000, result.getAccelerationYAxis());
        assertEquals(2000.0d, result.getAccelerationYAxisGal(), 0);
        assertEquals(20000, result.getAccelerationZAxis());
        assertEquals(2000.0d, result.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test3() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0x00; // Sequence number
        data[ 2] = (byte) 0x00; // Discomfort index
        data[ 3] = (byte) 0x00; // Discomfort index
        data[ 4] = (byte) 0x60; // Heat stroke
        data[ 5] = (byte) 0xf0; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_NONE_BIT; // Vibration information
        data[ 7] = (byte) 0x00; // SI value
        data[ 8] = (byte) 0x00; // SI value
        data[ 9] = (byte) 0x00; // PGA
        data[10] = (byte) 0x00; // PGA
        data[11] = (byte) 0x00; // Seismic intensity
        data[12] = (byte) 0x00; // Seismic intensity
        data[13] = (byte) 0xe0; // Acceleration (X-axis)
        data[14] = (byte) 0xb1; // Acceleration (X-axis)
        data[15] = (byte) 0xe0; // Acceleration (Y-axis)
        data[16] = (byte) 0xb1; // Acceleration (Y-axis)
        data[17] = (byte) 0xe0; // Acceleration (Z-axis)
        data[18] = (byte) 0xb1; // Acceleration (Z-axis)
        //@formatter:on

        CalculationData result1 = new CalculationData(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        CalculationData result2 = CalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getDiscomfortIndexWithUnit(), result2.getDiscomfortIndexWithUnit(), 0);
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
        assertEquals(result1.getHeatStrokeDegC(), result2.getHeatStrokeDegC(), 0);
        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getSiValueKine(), result2.getSiValueKine(), 0);
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getPgaGal(), result2.getPgaGal(), 0);
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getSeismicIntensityWithUnit(), result2.getSeismicIntensityWithUnit(), 0);
        assertEquals(result1.getAccelerationXAxis(), result2.getAccelerationXAxis());
        assertEquals(result1.getAccelerationXAxisGal(), result2.getAccelerationXAxisGal(), 0);
        assertEquals(result1.getAccelerationYAxis(), result2.getAccelerationYAxis());
        assertEquals(result1.getAccelerationYAxisGal(), result2.getAccelerationYAxisGal(), 0);
        assertEquals(result1.getAccelerationZAxis(), result2.getAccelerationZAxis());
        assertEquals(result1.getAccelerationZAxisGal(), result2.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test4() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0x10; // Discomfort index
        data[ 3] = (byte) 0x27; // Discomfort index
        data[ 4] = (byte) 0xd4; // Heat stroke
        data[ 5] = (byte) 0x30; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0xff; // SI value
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0xff; // PGA
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0xff; // Seismic intensity
        data[13] = (byte) 0x20; // Acceleration (X-axis)
        data[14] = (byte) 0x4e; // Acceleration (X-axis)
        data[15] = (byte) 0x20; // Acceleration (Y-axis)
        data[16] = (byte) 0x4e; // Acceleration (Y-axis)
        data[17] = (byte) 0x20; // Acceleration (Z-axis)
        data[18] = (byte) 0x4e; // Acceleration (Z-axis)
        //@formatter:on

        CalculationData result1 = new CalculationData(data);
        Parcel parcel = Parcel.obtain();
        result1.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        CalculationData result2 = CalculationData.CREATOR.createFromParcel(parcel);

        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getDiscomfortIndexWithUnit(), result2.getDiscomfortIndexWithUnit(), 0);
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
        assertEquals(result1.getHeatStrokeDegC(), result2.getHeatStrokeDegC(), 0);
        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getSiValueKine(), result2.getSiValueKine(), 0);
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getPgaGal(), result2.getPgaGal(), 0);
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getSeismicIntensityWithUnit(), result2.getSeismicIntensityWithUnit(), 0);
        assertEquals(result1.getAccelerationXAxis(), result2.getAccelerationXAxis());
        assertEquals(result1.getAccelerationXAxisGal(), result2.getAccelerationXAxisGal(), 0);
        assertEquals(result1.getAccelerationYAxis(), result2.getAccelerationYAxis());
        assertEquals(result1.getAccelerationYAxisGal(), result2.getAccelerationYAxisGal(), 0);
        assertEquals(result1.getAccelerationZAxis(), result2.getAccelerationZAxis());
        assertEquals(result1.getAccelerationZAxisGal(), result2.getAccelerationZAxisGal(), 0);
    }

    @Test
    public void test5() {
        //@formatter:off
        byte[] data = new byte[19];
        data[ 0] = DATA_TYPE_CALCULATION_DATA;
        data[ 1] = (byte) 0xff; // Sequence number
        data[ 2] = (byte) 0x10; // Discomfort index
        data[ 3] = (byte) 0x27; // Discomfort index
        data[ 4] = (byte) 0xd4; // Heat stroke
        data[ 5] = (byte) 0x30; // Heat stroke
        data[ 6] = (byte) VIBRATION_INFORMATION_DURING_EARTH_QUAKE_BIT; // Vibration information
        data[ 7] = (byte) 0xff; // SI value
        data[ 8] = (byte) 0xff; // SI value
        data[ 9] = (byte) 0xff; // PGA
        data[10] = (byte) 0xff; // PGA
        data[11] = (byte) 0xff; // Seismic intensity
        data[12] = (byte) 0xff; // Seismic intensity
        data[13] = (byte) 0x20; // Acceleration (X-axis)
        data[14] = (byte) 0x4e; // Acceleration (X-axis)
        data[15] = (byte) 0x20; // Acceleration (Y-axis)
        data[16] = (byte) 0x4e; // Acceleration (Y-axis)
        data[17] = (byte) 0x20; // Acceleration (Z-axis)
        data[18] = (byte) 0x4e; // Acceleration (Z-axis)
        //@formatter:on

        CalculationData result1 = new CalculationData(data);
        CalculationData result2 = CalculationData.CREATOR.createFromByteArray(data);

        assertEquals(result1.getDataType(), result2.getDataType());
        assertEquals(result1.getSequenceNumber(), result2.getSequenceNumber());
        assertEquals(result1.getDiscomfortIndex(), result2.getDiscomfortIndex());
        assertEquals(result1.getDiscomfortIndexWithUnit(), result2.getDiscomfortIndexWithUnit(), 0);
        assertEquals(result1.getHeatStroke(), result2.getHeatStroke());
        assertEquals(result1.getHeatStrokeDegC(), result2.getHeatStrokeDegC(), 0);
        assertEquals(result1.getVibrationInformation(), result2.getVibrationInformation());
        assertEquals(result1.getSiValue(), result2.getSiValue());
        assertEquals(result1.getSiValueKine(), result2.getSiValueKine(), 0);
        assertEquals(result1.getPga(), result2.getPga());
        assertEquals(result1.getPgaGal(), result2.getPgaGal(), 0);
        assertEquals(result1.getSeismicIntensity(), result2.getSeismicIntensity());
        assertEquals(result1.getSeismicIntensityWithUnit(), result2.getSeismicIntensityWithUnit(), 0);
        assertEquals(result1.getAccelerationXAxis(), result2.getAccelerationXAxis());
        assertEquals(result1.getAccelerationXAxisGal(), result2.getAccelerationXAxisGal(), 0);
        assertEquals(result1.getAccelerationYAxis(), result2.getAccelerationYAxis());
        assertEquals(result1.getAccelerationYAxisGal(), result2.getAccelerationYAxisGal(), 0);
        assertEquals(result1.getAccelerationZAxis(), result2.getAccelerationZAxis());
        assertEquals(result1.getAccelerationZAxisGal(), result2.getAccelerationZAxisGal(), 0);
    }
}
