package com.minimalviking.deviceinfo.providers;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

/**
 * Created by Jacek Olszewski on 09/04/15.
 */
public class OpenGLVersionChecker {
    public static int OPENGL_1 = 1;
    public static int OPENGL_2 = 2;
    public static int OPENGL_3 = 3;

    public static int getMaxSupportedVersion(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        if (configurationInfo.reqGlEsVersion >= 0x30000) {
            return OPENGL_3;
        }
        if (configurationInfo.reqGlEsVersion >= 0x20000) {
            return OPENGL_2;
        }
        return OPENGL_1;
    }
}
