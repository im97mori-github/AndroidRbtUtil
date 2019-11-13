package org.im97mori.rbt.ble.advertising.filter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.ble.advertising.filter.AndFilter;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingLogUtils;

import java.util.Arrays;
import java.util.List;

/**
 * {@link android.bluetooth.BluetoothAdapter.LeScanCallback} with filter function
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class FilteredRbtLeScanCallback implements BluetoothAdapter.LeScanCallback {

    /**
     * Builder for {@link FilteredRbtLeScanCallback}
     */
    public static class Builder extends AbstractRbtFilterBuilder<FilteredRbtLeScanCallback> {

        /**
         * {@link android.bluetooth.BluetoothAdapter.LeScanCallback} instance, used at the same time {@link #onFilteredLeScan(BluetoothDevice, int, byte[], RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)}
         */
        private BluetoothAdapter.LeScanCallback mLeScanCallback;


        /**
         * @param leScanCallback {@link android.bluetooth.BluetoothAdapter.LeScanCallback} instance, used at the same time {@link #onFilteredLeScan(BluetoothDevice, int, byte[], RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)}
         * @return myself
         */
        public Builder setScanCallback(BluetoothAdapter.LeScanCallback leScanCallback) {
            mLeScanCallback = leScanCallback;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public FilteredRbtLeScanCallback build() {
            return new FilteredRbtLeScanCallback(mFilterList, mRbtAdvertisingDataParser, mLeScanCallback);
        }

    }

    /**
     * {@link AndFilter} from {@link #FilteredRbtLeScanCallback(List, RbtAdvertisingDataParser, BluetoothAdapter.LeScanCallback)}1st parameter
     */
    private final AndFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> mAndFilter;

    /**
     * {@link RbtAdvertisingDataParser} instance
     */
    private final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

    /**
     * {@link android.bluetooth.BluetoothAdapter.LeScanCallback} instance, used at the same time {@link #onFilteredLeScan(BluetoothDevice, int, byte[], RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)}
     */
    private final BluetoothAdapter.LeScanCallback mLeScanCallback;

    /**
     * @param filterList     used for {@link AndFilter}
     * @param parser         {@link RbtAdvertisingDataParser} instance
     * @param leScanCallback {@link android.bluetooth.BluetoothAdapter.LeScanCallback} instance, used at the same time {@link #onFilteredLeScan(BluetoothDevice, int, byte[], RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)}
     */
    protected FilteredRbtLeScanCallback(@NonNull List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> filterList, @Nullable RbtAdvertisingDataParser parser, @Nullable BluetoothAdapter.LeScanCallback leScanCallback) {
        mAndFilter = new AndFilter<>(filterList);
        if (parser == null) {
            mRbtAdvertisingDataParser = new RbtAdvertisingDataParser.Builder(true).build();
        } else {
            mRbtAdvertisingDataParser = parser;
        }
        mLeScanCallback = leScanCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = mRbtAdvertisingDataParser.parse(scanRecord);
        if (parseResult != null && parseResult.isRbt() && mAndFilter.isMatched(parseResult)) {
            onFilteredLeScan(device, rssi, scanRecord, parseResult);
            if (mLeScanCallback != null) {
                mLeScanCallback.onLeScan(device, rssi, scanRecord);
            }
        } else {
            RbtAdvertisingLogUtils.stackLog("not matched", device, Arrays.toString(scanRecord));
        }
    }

    /**
     * Filtered {@link #onLeScan(BluetoothDevice, int, byte[])}
     *
     * @param device     {@link #onLeScan(BluetoothDevice, int, byte[])} 1st parameter
     * @param rssi       {@link #onLeScan(BluetoothDevice, int, byte[])} 2nd parameter
     * @param scanRecord {@link #onLeScan(BluetoothDevice, int, byte[])} 3rd parameter
     * @param result     {@link org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser.RbtAdvertisingDataParseResult} instance, created from scanRecord byte array
     */
    @SuppressWarnings("EmptyMethod")
    public void onFilteredLeScan(@NonNull BluetoothDevice device, int rssi, @NonNull byte[] scanRecord, @NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult result) {
    }

}
