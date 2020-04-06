package com.ugarsoft.chyma.iwin.utils;

import android.util.Log;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class Logger {
    private static final boolean DEBUG = true;
    private final String TAG;

    private Logger(String tag) {
        TAG = tag;
    }

    public static <T> Logger getLogger(Class<T> _class) {
        return new Logger(_class.getSimpleName().toUpperCase());
    }

    public void i(String message) {
        if (DEBUG) {
            Log.i(TAG, message);
        }
    }

    public void e(String message) {
        if (DEBUG) {
            Log.e(TAG, message);
        }
    }

    public void e(String message, Throwable e) {
        if (DEBUG) {
            Log.e(TAG, message, e);
        }
    }

    public void d(String message) {
        if (DEBUG) {
            Log.d(TAG, message);
        }
    }

}
