# deviceInfo
Utility library for creating concise device info for support/debug purposes

# Basic example
```
    DeviceInfoConfig config = DeviceInfoConfigBuilder.defaultConfig(getApplicationContext());
    DeviceInfoGenerator deviceInfo = new DeviceInfoGenerator(config).showMeEverything();
    Log.d(TAG, deviceInfo.print());
```
And this outputs:
```
    Device info:
    Model: Samsung Galaxy Note4
    Version: 4.4.4
    API level: 19
    Network: Wifi
    Rooted: false
```
# Customised usage
```
DeviceInfoConfig config = new DeviceInfoConfigBuilder(context)
    .appendAtStart("Do not modify line below\n")
    .appendAtEnd("\nPlease write below this line\n")
    .separator(", ")
    .includeFieldNames(false).build();

String deviceInfo = new DeviceInfoGenerator(config)
    .model().androidVersion().apiLevel().network().isRooted().print();
```
Example above generates device data presented in CSV-friendly format (handy when importing into spreadsheets):
```
Do not modify line below
Motorola MOTO G, 5.0.2, 21, Mobile - slow, true
Please write below this line
```

You can then use this to prepopulate a support email, remember to add a new line character at the end to position the caret in a new line.
```
Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
String uriText = "mailto:" + Uri.encode("support@example.com") +
    "?subject=" + Uri.encode("Help me") +
    "&body=" + Uri.encode(deviceInfo);
    
emailIntent.setData(Uri.parse(uriText));
startActivity(Intent.createChooser(emailIntent, "Send email"));
```