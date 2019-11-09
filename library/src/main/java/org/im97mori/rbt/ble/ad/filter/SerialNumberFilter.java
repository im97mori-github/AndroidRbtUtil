package org.im97mori.rbt.ble.ad.filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SerialNumber;

import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class SerialNumberFilter extends RbtDataFilter<Long> {

    /**
     * expected {@link SerialNumber} in Rbt's Advertising data
     */
    private SerialNumber mExpect;

    /**
     * Serial number's {@link Pattern}
     */
    private Pattern mPattern;

    /**
     * Constructor for complete comparison
     *
     * @param expect  expected {@link SerialNumber} in Rbt's Advertising data
     * @param pattern Serial number's {@link Pattern}
     */
    public SerialNumberFilter(@Nullable SerialNumber expect, @Nullable Pattern pattern) {
        mExpect = expect;
        mPattern = pattern;
    }

    /**
     * Constructor for partial comparison
     *
     * @param type  check type
     * @param value check value
     * @see #TARGET_MEMORY_INDEX
     */
    public SerialNumberFilter(int type, long value) {
        super(TARGET_MEMORY_INDEX, type, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatched(@NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
        boolean result = false;
        SerialNumber actual = parseResult.getSerialNumber();
        if (mExpect == null && mPattern == null) {
            if (actual == null) {
                result = true;
            } else if (TARGET_MEMORY_INDEX == mTarget) {
                result = check(actual.getMemoryIndex());
            }
        } else {
            if (mExpect != null && mPattern != null && actual != null) {
                if (mExpect.getDataType() == actual.getDataType()
                        && mExpect.getSerialNumber().equals(actual.getSerialNumber())
                        && mExpect.getMemoryIndex() == actual.getMemoryIndex()) {
                    result = mPattern.matcher(actual.getSerialNumber()).matches();
                }
            } else if (mExpect != null && actual != null) {
                if (mExpect.getDataType() == actual.getDataType()
                        && mExpect.getSerialNumber().equals(actual.getSerialNumber())
                        && mExpect.getMemoryIndex() == actual.getMemoryIndex()) {
                    result = true;
                }
            } else if (mPattern != null && actual != null) {
                result = mPattern.matcher(actual.getSerialNumber()).matches();
            }
        }
        return result;
    }

}
