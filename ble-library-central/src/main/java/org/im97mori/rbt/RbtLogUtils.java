package org.im97mori.rbt;

import android.util.Log;

import androidx.annotation.NonNull;

import org.im97mori.ble.BLELogUtils;
import org.im97mori.rbt.ble.central.BuildConfig;
import org.im97mori.stacklog.LogUtils;

/**
 * Output log when {@link BuildConfig#DEBUG} == {@code true}
 */
@SuppressWarnings({"unused"})
public class RbtLogUtils {

    /**
     * verbose mode
     */
    private static boolean VERBOSE = false;

    /**
     * enable verbose mode
     */
    public static void verbose() {
        VERBOSE = true;
        BLELogUtils.verbose();
    }

    /**
     * disable verbose mode
     */
    public static void mute() {
        VERBOSE = false;
        BLELogUtils.mute();
    }

    /**
     * @see LogUtils#stackLog(Object...)
     */
    public static void stackLog(@NonNull Object... args) {
        if (BuildConfig.DEBUG || VERBOSE) {
            LogUtils.stackLogWithOffset(1, args);
        }
    }

    /**
     * @see LogUtils#stackLog(Object...)
     */
    public static void stackLog(@NonNull Throwable t) {
        if (BuildConfig.DEBUG || VERBOSE) {
            LogUtils.stackLogWithOffset(1, Log.getStackTraceString(t));
        }
    }

}
