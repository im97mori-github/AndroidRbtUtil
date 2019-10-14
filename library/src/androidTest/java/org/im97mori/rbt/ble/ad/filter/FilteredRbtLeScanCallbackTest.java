package org.im97mori.rbt.ble.ad.filter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilteredRbtLeScanCallbackTest {

    @Test
    public void test_001() {
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");
        int rssi = 1;
        byte[] scanRecord = new byte[31];
        scanRecord[0] = (byte) 0x02; // AD 1
        scanRecord[1] = (byte) 0x01; // AD 1
        scanRecord[2] = (byte) 0x06; // AD 1
        scanRecord[3] = (byte) 0x16; // AD 2
        scanRecord[4] = (byte) 0xff; // AD 2
        scanRecord[5] = (byte) 0xd5; // AD 2
        scanRecord[6] = (byte) 0x02; // AD 2
        scanRecord[7] = (byte) 0x01; // AD 2
        scanRecord[8] = (byte) 0x00; // Sequence number
        scanRecord[9] = (byte) 0x60; // Temperature
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

        final List<BluetoothDevice> list1 = new LinkedList<>();
        final List<Integer> list2 = new LinkedList<>();
        final List<byte[]> list3 = new LinkedList<>();

        BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                list1.add(device);
                list2.add(rssi);
                list3.add(scanRecord);
            }
        };
        FilteredRbtLeScanCallback callback = new FilteredRbtLeScanCallback.Builder().setScanCallback(leScanCallback).build();
        callback.onLeScan(bluetoothDevice, rssi, scanRecord);

        assertEquals(1, list1.size());
        assertEquals(1, list2.size());
        assertEquals(1, list3.size());
        assertEquals(bluetoothDevice, list1.get(0));
        assertEquals(rssi, list2.get(0).intValue());
        assertArrayEquals(scanRecord, list3.get(0));
    }

    @Test
    public void test_002() {
        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("00:11:22:33:AA:BB");
        int rssi = 1;
        byte[] scanRecord = new byte[31];
        scanRecord[0] = (byte) 0x02; // AD 1
        scanRecord[1] = (byte) 0x01; // AD 1
        scanRecord[2] = (byte) 0x06; // AD 1
        scanRecord[3] = (byte) 0x16; // AD 2
        scanRecord[4] = (byte) 0xff; // AD 2
        scanRecord[5] = (byte) 0xd5; // AD 2
        scanRecord[6] = (byte) 0x02; // AD 2
        scanRecord[7] = (byte) 0x01; // AD 2
        scanRecord[8] = (byte) 0x00; // Sequence number
        scanRecord[9] = (byte) 0x60; // Temperature
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

        final List<BluetoothDevice> list1 = new LinkedList<>();
        final List<Integer> list2 = new LinkedList<>();
        final List<byte[]> list3 = new LinkedList<>();

        BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                list1.add(device);
                list2.add(rssi);
                list3.add(scanRecord);
            }
        };
        FilteredRbtLeScanCallback callback = new FilteredRbtLeScanCallback.Builder().setScanCallback(leScanCallback).addFilter(new RbtDataFilter(){
            @Override
            public boolean isMatched(RbtAdvertisingDataParser.RbtAdvertisingDataParseResult advertisingDataParseResult) {
                return false;
            }
        }).build();
        callback.onLeScan(bluetoothDevice, rssi, scanRecord);

        assertTrue(list1.isEmpty());
        assertTrue(list2.isEmpty());
        assertTrue(list3.isEmpty());
    }

}
