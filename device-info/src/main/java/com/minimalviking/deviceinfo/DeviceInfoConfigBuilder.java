package com.minimalviking.deviceinfo;

import android.content.Context;

/**
 * Created by Jacek Olszewski on 08/04/15.
 */
public class DeviceInfoConfigBuilder {
    private Context context;
    private String  appendAtStart;
    private String  appendAtEnd;
    private String  separator;
    private boolean includeFieldNames;

    public DeviceInfoConfigBuilder(Context context) {
        this.context = context;
    }

    public static DeviceInfoConfig defaultConfig(Context context) {
        return new DeviceInfoConfigBuilder(context).includeFieldNames(true).separator("\n").appendAtStart("Device info:\n").build();
    }

    public DeviceInfoConfigBuilder appendAtStart(String appendAtStart) {
        this.appendAtStart = appendAtStart;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public DeviceInfoConfigBuilder appendAtEnd(String appendAtEnd) {
        this.appendAtEnd = appendAtEnd;
        return this;
    }

    public DeviceInfoConfigBuilder separator(String separator) {
        if (separator != null) this.separator = separator;
        return this;
    }

    public DeviceInfoConfigBuilder includeFieldNames(boolean includeFieldNames) {
        this.includeFieldNames = includeFieldNames;
        return this;
    }


    public DeviceInfoConfig build() {
        if (separator == null) {
            separator = "";
        }
        return new DeviceInfoConfig(context, appendAtStart, appendAtEnd, separator, includeFieldNames);
    }


    public class DeviceInfoConfig {
        public Context getContext() {
            return context;
        }

        public String getAppendAtStart() {
            return appendAtStart;
        }

        public String getAppendAtEnd() {
            return appendAtEnd;
        }

        public boolean includeFieldNames() {
            return includeFieldNames;
        }

        public String getSeparator() {
            return separator;
        }

        private Context context;
        private String  appendAtStart;
        private String  appendAtEnd;
        private boolean includeFieldNames;
        private String  separator;

        public DeviceInfoConfig(Context context, String appendAtStart, String appendAtEnd, String separator, boolean includeFieldNames) {
            this.context = context;
            this.appendAtStart = appendAtStart;
            this.appendAtEnd = appendAtEnd;
            this.separator = separator;
            this.includeFieldNames = includeFieldNames;
        }
    }


}
