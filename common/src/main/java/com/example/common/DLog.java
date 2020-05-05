package com.example.common;

import android.util.Log;

public final class DLog {
    private static final String TAG = "DEMO_";

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
