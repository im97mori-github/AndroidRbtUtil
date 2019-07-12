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
    public void test2() {
        byte[] data = new byte[19];
        data[0] = DATA_TYPE_CALCULATION_DATA;
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
}
