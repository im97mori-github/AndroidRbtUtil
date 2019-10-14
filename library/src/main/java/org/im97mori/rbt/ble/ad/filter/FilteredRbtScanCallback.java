package org.im97mori.rbt.ble.ad.filter;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.im97mori.ble.ad.filter.AdvertisingDataFilter;
import org.im97mori.ble.ad.filter.AndFilter;
import org.im97mori.rbt.RbtLogUtils;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link ScanCallback} with filter function
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressWarnings({"unused"})
public class FilteredRbtScanCallback extends ScanCallback {

    /**
     * Builder for {@link FilteredRbtScanCallback}
     */
    public static class Builder extends AbstractRbtFilterBuilder<FilteredRbtScanCallback> {

        /**
         * {@link ScanCallback} instance, used at the same time {@link #onFilteredScanResult(int, ScanResult, RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)} or {@link #onFilteredBatchScanResults(List, List)}
         */
        protected ScanCallback mScanCallback;

        /**
         * @param scanCallback {@link ScanCallback} instance, used at the same time {@link #onFilteredScanResult(int, ScanResult, RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)} or {@link #onFilteredBatchScanResults(List, List)}
         * @return myself
         */
        public Builder setScanCallback(ScanCallback scanCallback) {
            mScanCallback = scanCallback;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public FilteredRbtScanCallback build() {
            return new FilteredRbtScanCallback(mFilterList, mRbtAdvertisingDataParser, mScanCallback);
        }

    }

    /**
     * {@link AndFilter} from {@link #FilteredRbtScanCallback(List, RbtAdvertisingDataParser, ScanCallback)} 1st parameter
     */
    private final AndFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> mAndFilter;

    /**
     * {@link RbtAdvertisingDataParser} instance
     */
    private final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

    /**
     * {@link ScanCallback} instance, used at the same time {@link ScanCallback} instance, used at the same time {@link #onFilteredScanResult(int, ScanResult, RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)} or {@link #onFilteredBatchScanResults(List, List)}
     */
    private final ScanCallback mScanCallback;

    /**
     * @param filterList   used for {@link AndFilter}
     * @param parser       {@link RbtAdvertisingDataParser} instance
     * @param scanCallback {@link ScanCallback} instance, used at the same time {@link ScanCallback} instance, used at the same time {@link #onFilteredScanResult(int, ScanResult, RbtAdvertisingDataParser.RbtAdvertisingDataParseResult)} or {@link #onFilteredBatchScanResults(List, List)}
     */
    protected FilteredRbtScanCallback(@NonNull List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> filterList, @Nullable RbtAdvertisingDataParser parser, @Nullable ScanCallback scanCallback) {
        mAndFilter = new AndFilter<>(filterList);
        if (parser == null) {
            mRbtAdvertisingDataParser = new RbtAdvertisingDataParser.Builder(true).build();
        } else {
            mRbtAdvertisingDataParser = parser;
        }
        mScanCallback = scanCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onScanResult(int callbackType, ScanResult result) {
        ScanRecord scanRecord = result.getScanRecord();
        if (scanRecord != null) {
            RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = mRbtAdvertisingDataParser.parse(scanRecord.getBytes());
            if (parseResult != null && parseResult.isRbt() && mAndFilter.isMatched(parseResult)) {
                onFilteredScanResult(callbackType, result, parseResult);
                if (mScanCallback != null) {
                    mScanCallback.onScanResult(callbackType, result);
                }
            } else {
                RbtLogUtils.stackLog("not matched", result.getDevice(), scanRecord);
            }
        }
    }

    /**
     * Filtered {@link #onScanResult(int, ScanResult)}
     *
     * @param callbackType {@link #onScanResult(int, ScanResult)} 1st parameter
     * @param result       {@link #onScanResult(int, ScanResult)} 2nd parameter
     * @param parseResult  {@link  org.im97mori.ble.ad.AdvertisingDataParser.AdvertisingDataParseResult} instance, created from result
     */
    public void onFilteredScanResult(int callbackType, @NonNull ScanResult result, @NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onBatchScanResults(@NonNull List<ScanResult> results) {
        List<ScanResult> filteredResults = new LinkedList<>();
        List<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> parseResults = new LinkedList<>();
        for (ScanResult scanResult : results) {
            ScanRecord scanRecord = scanResult.getScanRecord();
            if (scanRecord != null) {
                RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult = mRbtAdvertisingDataParser.parse(scanRecord.getBytes());
                if (parseResult != null && parseResult.isRbt() && mAndFilter.isMatched(parseResult)) {
                    filteredResults.add(scanResult);
                    parseResults.add(parseResult);
                } else {
                    RbtLogUtils.stackLog("not matched", scanResult.getDevice(), scanRecord);
                }
            }
        }
        if (!filteredResults.isEmpty()) {
            onFilteredBatchScanResults(filteredResults, parseResults);
            if (mScanCallback != null) {
                mScanCallback.onBatchScanResults(results);
            }
        }
    }

    /**
     * Filtered {@link #onBatchScanResults(List)}
     *
     * @param results      {@link #onBatchScanResults(List)} 1st paramerter
     * @param parseResults List of {@link org.im97mori.ble.ad.AdvertisingDataParser.AdvertisingDataParseResult} instance, created from results
     */
    public void onFilteredBatchScanResults(@NonNull List<ScanResult> results, @NonNull List<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> parseResults) {
    }

}
