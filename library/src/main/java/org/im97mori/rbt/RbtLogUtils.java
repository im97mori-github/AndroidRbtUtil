package org.im97mori.rbt;

import android.util.Log;

import org.im97mori.stacklog.LogUtils;

/**
 * Output log when {@link BuildConfig#DEBUG} == {@code true}
 */
@SuppressWarnings({"JavadocReference", "unused"})
public class RbtLogUtils {

    /**
     * @see LogUtils#stackLog(Object...)
     */
    public static void stackLog(Object... args) {
        if (BuildConfig.DEBUG) {
            LogUtils.stackLogWithOffset(1, args);
        }
    }

    /**
     * @see LogUtils#stackLog(Object...)
     */
    public static void stackLog(Throwable t) {
        if (BuildConfig.DEBUG) {
            LogUtils.stackLogWithOffset(1, Log.getStackTraceString(t));
        }
    }

}
