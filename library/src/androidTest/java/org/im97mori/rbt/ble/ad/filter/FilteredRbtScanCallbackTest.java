package org.im97mori.rbt.ble.ad.filter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Parcel;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilteredRbtScanCallbackTest {

    @Test
    public void test_001() {
        int callbackType = 1;
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");

        //@formatter:off
        byte[] scanRecord = new byte[31];
        scanRecord[ 0] = (byte) 0x02; // AD 1
        scanRecord[ 1] = (byte) 0x01; // AD 1
        scanRecord[ 2] = (byte) 0x06; // AD 1
        scanRecord[ 3] = (byte) 0x16; // AD 2
        scanRecord[ 4] = (byte) 0xff; // AD 2
        scanRecord[ 5] = (byte) 0xd5; // AD 2
        scanRecord[ 6] = (byte) 0x02; // AD 2
        scanRecord[ 7] = (byte) 0x01; // AD 2
        scanRecord[ 8] = (byte) 0x00; // Sequence number
        scanRecord[ 9] = (byte) 0x60; // Temperature
        scanRecord[10] = (byte) 0xf0; // Temperature
        scanRecord[11] = (byte) 0x00; // Relative humidity
        scanRecord[12] = (byte) 0x00; // Relative humidity
        scanRecord[13] = (byte) 0x00; // Ambient light
        scanRecord[14] = (byte) 0x00; // Ambient light
        scanRecord[15] = (byte) 0xe0; // Barometric pressure
        scanRecord[16] = (byte) 0x93; // Barometric pressure
        scanRecord[17] = (byte) 0x04; // Barometric pressure
        scanRecord[18] = (byte) 0x00; // Barometric pressure
        scanRecord[19] = (byte) 0xe4; // Sound noise
        scanRecord[20] = (byte) 0x0c; // Sound noise
        scanRecord[21] = (byte) 0x00; // eTVOC
        scanRecord[22] = (byte) 0x00; // eTVOC
        scanRecord[23] = (byte) 0x90; // eCO2
        scanRecord[24] = (byte) 0x01; // eCO2
        scanRecord[25] = (byte) 0xFF; // Reserve for Future Use
        scanRecord[26] = (byte) 0x04; // AD 3
        scanRecord[27] = (byte) 0x08; // AD 3
        scanRecord[28] = (byte) 'R'; // AD 3
        scanRecord[29] = (byte) 'b'; // AD 3
        scanRecord[30] = (byte) 't'; // AD 3
        //@formatter:on

        Parcel parcel = Parcel.obtain();
        parcel.writeInt(1);
        bluetoothDevice.writeToParcel(parcel, 0);
        parcel.writeInt(1);
        parcel.writeByteArray(scanRecord);
        parcel.writeInt(1);
        parcel.writeLong(2);
        parcel.writeInt(3);
        parcel.writeInt(4);
        parcel.writeInt(5);
        parcel.writeInt(6);
        parcel.writeInt(7);
        parcel.writeInt(7);
        parcel.setDataPosition(0);
        ScanResult scanResult = ScanResult.CREATOR.createFromParcel(parcel);

        final List<Integer> list1 = new LinkedList<>();
        final List<ScanResult> list2 = new LinkedList<>();
        final List<List<ScanResult>> list3 = new LinkedList<>();

        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                list1.add(callbackType);
                list2.add(result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                list3.add(results);
            }
        };
        FilteredRbtScanCallback callback = new FilteredRbtScanCallback.Builder().setScanCallback(scanCallback).build();
        callback.onScanResult(callbackType, scanResult);

        assertEquals(1, list1.size());
        assertEquals(1, list2.size());
        assertEquals(0, list3.size());
        assertEquals(callbackType, list1.get(0).intValue());
        assertEquals(scanResult, list2.get(0));
    }

    @Test
    public void test_002() {
        int callbackType = 1;
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");

        //@formatter:off
        byte[] scanRecord = new byte[31];
        scanRecord[ 0] = (byte) 0x02; // AD 1
        scanRecord[ 1] = (byte) 0x01; // AD 1
        scanRecord[ 2] = (byte) 0x06; // AD 1
        scanRecord[ 3] = (byte) 0x16; // AD 2
        scanRecord[ 4] = (byte) 0xff; // AD 2
        scanRecord[ 5] = (byte) 0xd5; // AD 2
        scanRecord[ 6] = (byte) 0x02; // AD 2
        scanRecord[ 7] = (byte) 0x01; // AD 2
        scanRecord[ 8] = (byte) 0x00; // Sequence number
        scanRecord[ 9] = (byte) 0x60; // Temperature
        scanRecord[10] = (byte) 0xf0; // Temperature
        scanRecord[11] = (byte) 0x00; // Relative humidity
        scanRecord[12] = (byte) 0x00; // Relative humidity
        scanRecord[13] = (byte) 0x00; // Ambient light
        scanRecord[14] = (byte) 0x00; // Ambient light
        scanRecord[15] = (byte) 0xe0; // Barometric pressure
        scanRecord[16] = (byte) 0x93; // Barometric pressure
        scanRecord[17] = (byte) 0x04; // Barometric pressure
        scanRecord[18] = (byte) 0x00; // Barometric pressure
        scanRecord[19] = (byte) 0xe4; // Sound noise
        scanRecord[20] = (byte) 0x0c; // Sound noise
        scanRecord[21] = (byte) 0x00; // eTVOC
        scanRecord[22] = (byte) 0x00; // eTVOC
        scanRecord[23] = (byte) 0x90; // eCO2
        scanRecord[24] = (byte) 0x01; // eCO2
        scanRecord[25] = (byte) 0xFF; // Reserve for Future Use
        scanRecord[26] = (byte) 0x04; // AD 3
        scanRecord[27] = (byte) 0x08; // AD 3
        scanRecord[28] = (byte) 'R'; // AD 3
        scanRecord[29] = (byte) 'b'; // AD 3
        scanRecord[30] = (byte) 't'; // AD 3
        //@formatter:on

        Parcel parcel = Parcel.obtain();
        parcel.writeInt(1);
        bluetoothDevice.writeToParcel(parcel, 0);
        parcel.writeInt(1);
        parcel.writeByteArray(scanRecord);
        parcel.writeInt(1);
        parcel.writeLong(2);
        parcel.writeInt(3);
        parcel.writeInt(4);
        parcel.writeInt(5);
        parcel.writeInt(6);
        parcel.writeInt(7);
        parcel.writeInt(7);
        parcel.setDataPosition(0);
        ScanResult scanResult = ScanResult.CREATOR.createFromParcel(parcel);

        final List<Integer> list1 = new LinkedList<>();
        final List<ScanResult> list2 = new LinkedList<>();
        final List<List<ScanResult>> list3 = new LinkedList<>();

        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                list1.add(callbackType);
                list2.add(result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                list3.add(results);
            }
        };
        FilteredRbtScanCallback callback = new FilteredRbtScanCallback.Builder().setScanCallback(scanCallback).addFilter(new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        }).build();
        callback.onScanResult(callbackType, scanResult);

        assertEquals(0, list1.size());
        assertEquals(0, list2.size());
        assertEquals(0, list3.size());
    }

    @Test
    public void test_003() {
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");

        //@formatter:off
        byte[] scanRecord = new byte[31];
        scanRecord[ 0] = (byte) 0x02; // AD 1
        scanRecord[ 1] = (byte) 0x01; // AD 1
        scanRecord[ 2] = (byte) 0x06; // AD 1
        scanRecord[ 3] = (byte) 0x16; // AD 2
        scanRecord[ 4] = (byte) 0xff; // AD 2
        scanRecord[ 5] = (byte) 0xd5; // AD 2
        scanRecord[ 6] = (byte) 0x02; // AD 2
        scanRecord[ 7] = (byte) 0x01; // AD 2
        scanRecord[ 8] = (byte) 0x00; // Sequence number
        scanRecord[ 9] = (byte) 0x60; // Temperature
        scanRecord[10] = (byte) 0xf0; // Temperature
        scanRecord[11] = (byte) 0x00; // Relative humidity
        scanRecord[12] = (byte) 0x00; // Relative humidity
        scanRecord[13] = (byte) 0x00; // Ambient light
        scanRecord[14] = (byte) 0x00; // Ambient light
        scanRecord[15] = (byte) 0xe0; // Barometric pressure
        scanRecord[16] = (byte) 0x93; // Barometric pressure
        scanRecord[17] = (byte) 0x04; // Barometric pressure
        scanRecord[18] = (byte) 0x00; // Barometric pressure
        scanRecord[19] = (byte) 0xe4; // Sound noise
        scanRecord[20] = (byte) 0x0c; // Sound noise
        scanRecord[21] = (byte) 0x00; // eTVOC
        scanRecord[22] = (byte) 0x00; // eTVOC
        scanRecord[23] = (byte) 0x90; // eCO2
        scanRecord[24] = (byte) 0x01; // eCO2
        scanRecord[25] = (byte) 0xFF; // Reserve for Future Use
        scanRecord[26] = (byte) 0x04; // AD 3
        scanRecord[27] = (byte) 0x08; // AD 3
        scanRecord[28] = (byte) 'R'; // AD 3
        scanRecord[29] = (byte) 'b'; // AD 3
        scanRecord[30] = (byte) 't'; // AD 3
        //@formatter:on

        Parcel parcel = Parcel.obtain();
        parcel.writeInt(1);
        bluetoothDevice.writeToParcel(parcel, 0);
        parcel.writeInt(1);
        parcel.writeByteArray(scanRecord);
        parcel.writeInt(1);
        parcel.writeLong(2);
        parcel.writeInt(3);
        parcel.writeInt(4);
        parcel.writeInt(5);
        parcel.writeInt(6);
        parcel.writeInt(7);
        parcel.writeInt(7);
        parcel.setDataPosition(0);
        ScanResult scanResult = ScanResult.CREATOR.createFromParcel(parcel);

        final List<Integer> list1 = new LinkedList<>();
        final List<ScanResult> list2 = new LinkedList<>();
        final List<List<ScanResult>> list3 = new LinkedList<>();

        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                list1.add(callbackType);
                list2.add(result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                list3.add(results);
            }
        };
        FilteredRbtScanCallback callback = new FilteredRbtScanCallback.Builder().setScanCallback(scanCallback).build();
        callback.onBatchScanResults(Collections.singletonList(scanResult));

        assertEquals(0, list1.size());
        assertEquals(0, list2.size());
        assertEquals(1, list3.size());
        assertEquals(1, list3.get(0).size());
        assertEquals(scanResult, list3.get(0).get(0));
    }

    @Test
    public void test_004() {
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");

        //@formatter:off
        byte[] scanRecord = new byte[31];
        scanRecord[ 0] = (byte) 0x02; // AD 1
        scanRecord[ 1] = (byte) 0x01; // AD 1
        scanRecord[ 2] = (byte) 0x06; // AD 1
        scanRecord[ 3] = (byte) 0x16; // AD 2
        scanRecord[ 4] = (byte) 0xff; // AD 2
        scanRecord[ 5] = (byte) 0xd5; // AD 2
        scanRecord[ 6] = (byte) 0x02; // AD 2
        scanRecord[ 7] = (byte) 0x01; // AD 2
        scanRecord[ 8] = (byte) 0x00; // Sequence number
        scanRecord[ 9] = (byte) 0x60; // Temperature
        scanRecord[10] = (byte) 0xf0; // Temperature
        scanRecord[11] = (byte) 0x00; // Relative humidity
        scanRecord[12] = (byte) 0x00; // Relative humidity
        scanRecord[13] = (byte) 0x00; // Ambient light
        scanRecord[14] = (byte) 0x00; // Ambient light
        scanRecord[15] = (byte) 0xe0; // Barometric pressure
        scanRecord[16] = (byte) 0x93; // Barometric pressure
        scanRecord[17] = (byte) 0x04; // Barometric pressure
        scanRecord[18] = (byte) 0x00; // Barometric pressure
        scanRecord[19] = (byte) 0xe4; // Sound noise
        scanRecord[20] = (byte) 0x0c; // Sound noise
        scanRecord[21] = (byte) 0x00; // eTVOC
        scanRecord[22] = (byte) 0x00; // eTVOC
        scanRecord[23] = (byte) 0x90; // eCO2
        scanRecord[24] = (byte) 0x01; // eCO2
        scanRecord[25] = (byte) 0xFF; // Reserve for Future Use
        scanRecord[26] = (byte) 0x04; // AD 3
        scanRecord[27] = (byte) 0x08; // AD 3
        scanRecord[28] = (byte) 'R'; // AD 3
        scanRecord[29] = (byte) 'b'; // AD 3
        scanRecord[30] = (byte) 't'; // AD 3
        //@formatter:on

        Parcel parcel = Parcel.obtain();
        parcel.writeInt(1);
        bluetoothDevice.writeToParcel(parcel, 0);
        parcel.writeInt(1);
        parcel.writeByteArray(scanRecord);
        parcel.writeInt(1);
        parcel.writeLong(2);
        parcel.writeInt(3);
        parcel.writeInt(4);
        parcel.writeInt(5);
        parcel.writeInt(6);
        parcel.writeInt(7);
        parcel.writeInt(7);
        parcel.setDataPosition(0);
        ScanResult scanResult = ScanResult.CREATOR.createFromParcel(parcel);

        final List<Integer> list1 = new LinkedList<>();
        final List<ScanResult> list2 = new LinkedList<>();
        final List<List<ScanResult>> list3 = new LinkedList<>();

        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                list1.add(callbackType);
                list2.add(result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                list3.add(results);
            }
        };
        FilteredRbtScanCallback callback = new FilteredRbtScanCallback.Builder().setScanCallback(scanCallback).addFilter(new RbtDataFilter<Integer>() {
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        }).build();
        callback.onBatchScanResults(Collections.singletonList(scanResult));

        assertEquals(0, list1.size());
        assertEquals(0, list2.size());
        assertEquals(0, list3.size());
    }

}
