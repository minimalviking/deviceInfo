package com.minimalviking.deviceinfo;

import com.minimalviking.deviceinfo.DeviceInfoConfigBuilder.DeviceInfoConfig;
import com.minimalviking.deviceinfo.providers.AppVersionProvider;
import com.minimalviking.deviceinfo.providers.ConnectivityInfo;
import com.minimalviking.deviceinfo.providers.Devices;
import com.minimalviking.deviceinfo.providers.OpenGLVersionChecker;
import com.minimalviking.deviceinfo.providers.RootCheck;

import static android.os.Build.VERSION.RELEASE;
import static android.os.Build.VERSION.SDK_INT;

/**
 * Created by Jacek Olszewski on 08/04/15.
 */
public class DeviceInfoGenerator {
    private final DeviceInfoConfig config;
    private       boolean          showModel;
    private       boolean          showAndroidVersion;
    private       boolean          showApiLevel;
    private       boolean          showNetwork;
    private       boolean          showIsRooted;
    private       boolean          showOpenglVersion;
    private       boolean          showVersionName;
    private       boolean          showVersionCode;

    public DeviceInfoGenerator(DeviceInfoConfig config) {
        this.config = config;
    }

    public DeviceInfoGenerator showMeEverything() {
        showModel = true;
        showAndroidVersion = true;
        showApiLevel = true;
        showNetwork = true;
        showIsRooted = true;
        showOpenglVersion = true;
        showVersionCode = true;
        showVersionName = true;

        return this;
    }

    public DeviceInfoGenerator model() {
        showModel = true;
        return this;
    }

    public DeviceInfoGenerator androidVersion() {
        showAndroidVersion = true;
        return this;
    }

    public DeviceInfoGenerator apiLevel() {
        showApiLevel = true;
        return this;
    }


    public DeviceInfoGenerator network() {
        showNetwork = true;
        return this;
    }

    public DeviceInfoGenerator isRooted() {
        showIsRooted = true;
        return this;
    }

    public DeviceInfoGenerator checkOpenGlVersion() {
        showOpenglVersion = true;
        return this;
    }

    public DeviceInfoGenerator versionName() {
        showVersionName = true;
        return this;
    }

    public DeviceInfoGenerator versionCode() {
        showVersionCode = true;
        return this;
    }

    public String print() {

        String result = config.getAppendAtStart() == null ? "" : config.getAppendAtStart();
        if (showVersionName) {
            if (config.includeFieldNames()) {
                result += "Version name: ";
            }
            result += AppVersionProvider.getVersionName(config.getContext());
            result = addSeprator(result);
        }

        if (showVersionCode) {
            if (config.includeFieldNames()) {
                result += "Version code: ";
            }
            result += AppVersionProvider.getVersionCode(config.getContext());
            result = addSeprator(result);
        }

        if (showModel) {
            if (config.includeFieldNames()) {
                result += "Model: ";
            }
            result += Devices.getDeviceName();
            result = addSeprator(result);
        }

        if (showAndroidVersion) {
            if (config.includeFieldNames()) {
                result += "Android version: ";
            }
            result += RELEASE;
            result = addSeprator(result);
        }

        if (showApiLevel) {
            if (config.includeFieldNames()) {
                result += "API level: ";
            }
            result += SDK_INT;
            result = addSeprator(result);
        }

        if (showNetwork) {
            if (config.includeFieldNames()) {
                result += "Network: ";
            }
            result += ConnectivityInfo.getConnectionInfo(config.getContext());
            result = addSeprator(result);
        }

        if (showIsRooted) {
            if (config.includeFieldNames()) {
                result += "Rooted: ";
            }
            result += RootCheck.isDeviceRooted();
            result = addSeprator(result);
        }

        if (showOpenglVersion) {
            if (config.includeFieldNames()) {
                result += "OpenGL version: ";
            }
            result += OpenGLVersionChecker.getMaxSupportedVersion(config.getContext());
            result = addSeprator(result);
        }

        result = removeLastSeparator(result);
        result = config.getAppendAtEnd() == null ? result : result + config.getAppendAtEnd();

        return result;
    }

    private String removeLastSeparator(String removeFrom) {
        int index = removeFrom.lastIndexOf(config.getSeparator());
        if (index == -1) {
            return removeFrom;
        }
        return removeFrom.substring(0, index);
    }

    private String addSeprator(String addTo) {
        return addTo + config.getSeparator();
    }

    @Override
    public String toString() {
        return print();
    }
}