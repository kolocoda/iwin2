package com.ugarsoft.chyma.iwin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.ugarsoft.chyma.iwin.models.Announcement;
import com.ugarsoft.chyma.iwin.models.AppUser;
import com.ugarsoft.chyma.iwin.models.Appliance;
import com.ugarsoft.chyma.iwin.models.Bill;
import com.ugarsoft.chyma.iwin.models.FAQ;
import com.ugarsoft.chyma.iwin.models.PowerNews;
import com.ugarsoft.chyma.iwin.models.UserRole;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Chyma
 * <p/>
 * on 5/7/2016.
 */
public class Pref {
    private static Logger logger = Logger.getLogger(Pref.class);

    public static void saveToSharedPreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void saveToSharedPreference(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static void saveToSharedPreference(Context context, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public static void saveToSharedPreference(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static String getStringValue(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static boolean getBooleanValue(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static long getLongValue(Context context, String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static int getIntegerValue(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.IWIN_SHARED_PREFERENCES_NAME.name(), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static AppUser getUser(Context context) {
        String serialized = getStringValue(context, Constant.APP_USER.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        AppUser AppUser = gson.fromJson(serialized, AppUser.class);
        return AppUser;
    }

    public static void saveUser(Context context, AppUser AppUser) {
        String serialized = gson.toJson(AppUser);
        saveToSharedPreference(context, Constant.APP_USER.name(), serialized);
    }

    public static PowerNews getNewsContent(Context context) {
        String serialized = getStringValue(context, Constant.POWER_NEWS.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        PowerNews PowerNews = gson.fromJson(serialized, PowerNews.class);
        return PowerNews;
    }

    public static void saveNewsContent(Context context, PowerNews PowerNews) {
        String serialized = gson.toJson(PowerNews);
        saveToSharedPreference(context, Constant.POWER_NEWS.name(), serialized);
    }

    public static Announcement getAnnouncementContent(Context context) {
        String serialized = getStringValue(context, Constant.ANNOUNCEMENT.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        Announcement Announcement = gson.fromJson(serialized, Announcement.class);
        return Announcement;
    }

    public static void saveAnnouncementContent(Context context, Announcement announcement) {
        String serialized = gson.toJson(announcement);
        saveToSharedPreference(context, Constant.ANNOUNCEMENT.name(), serialized);
    }

    public static Appliance getCurrentAppliance(Context context) {
        String serialized = getStringValue(context, Constant.APPLIANCE.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        Appliance Appliance = gson.fromJson(serialized, Appliance.class);
        return Appliance;
    }

    public static void saveCurrentAppliance(Context context, Appliance Appliance) {
        String serialized = gson.toJson(Appliance);
        saveToSharedPreference(context, Constant.APPLIANCE.name(), serialized);
    }

    public static FAQ getFAQFromSharedPrefs(Context context) {
        String serialized = getStringValue(context, Constant.FAQ.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        FAQ faq = gson.fromJson(serialized, FAQ.class);
        return faq;
    }

    public static void saveFAQToSharedPref(Context context, FAQ faq) {
        String serialized = gson.toJson(faq);
        saveToSharedPreference(context, Constant.FAQ.name(), serialized);
    }

    public static Bill getBillFromSharedPrefs(Context context) {
        String serialized = getStringValue(context, Constant.BILL.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }
        Bill bill = gson.fromJson(serialized, Bill.class);
        return bill;
    }

    public static void saveBillToSharedPref(Context context, Bill bill) {
        String serialized = gson.toJson(bill);
        saveToSharedPreference(context, Constant.BILL.name(), serialized);
    }



    public static List<UserRole> getUserRoles(Context context) {
        String serialized = getStringValue(context, Constant.PORTAL_USER_ROLE.name(), "");
        if (serialized.isEmpty()) {
            return null;
        }

        return  gson.fromJson(serialized, List.class);
    }

    public static void saveUserRoles(Context context, List<UserRole> portalUserJMCRoles) {
        String serialized = gson.toJson(portalUserJMCRoles);
        saveToSharedPreference(context, Constant.PORTAL_USER_ROLE.name(), serialized);
    }

    private static final Gson gson = new Gson();
}
