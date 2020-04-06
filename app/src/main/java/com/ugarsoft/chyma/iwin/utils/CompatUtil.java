package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import java.text.SimpleDateFormat;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class CompatUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");

    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }

    public static boolean isLandScape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean hasLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static long dateStringToLong(String dateString) {
        try {
            return dateFormat.parse(dateString).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
