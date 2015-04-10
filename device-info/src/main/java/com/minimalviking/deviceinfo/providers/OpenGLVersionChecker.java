package com.minimalviking.deviceinfo.providers;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

/**
 * Created by Jacek Olszewski on 09/04/15.
 */
public class OpenGLVersionChecker {
    public static String getMaxSupportedVersion(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        if (configurationInfo.reqGlEsVersion >= 0x30000) {
            return "3.0";
        }
        if (configurationInfo.reqGlEsVersion >= 0x20000) {
            return "2.0";
        }
        return "1.0";
    }
}
