package com.applaudo.shop.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Kobiton {

    public static final String SESSION_NAME = "sessionName";
    public static final String SESSION_DESCRIPTION = "sessionDescription";
    public static final String DEVICE_ORIENTATION = "deviceOrientation";
    public static final String CAPTURE_SCREENSHOTS = "captureScreenshots";
    public static final String DEVICE_GROUP = "deviceGroup";

    public static final String KOBITON_PROPERTIES = "kobiton.properties";
    private static final Properties PROPS = new Properties();



    public static void loadConfig() throws IOException {
        String path = Config.getResourcesPath().concat(KOBITON_PROPERTIES);
        PROPS.load(new FileInputStream(path));
    }

    public static String getKobitonServer() {
        return PROPS.getProperty("kobitonServerUrl");
    }

    public static String getAutomationName() {
        return PROPS.getProperty("automationName");
    }

    public static boolean getAcceptSSLCerts() {
        return Boolean.parseBoolean(PROPS.getProperty("acceptSslCerts"));
    }

    public static String getSessionName() {
        return PROPS.getProperty("sessionName");
    }

    public static String getSessionDescription() {
        return PROPS.getProperty("sessionDescription");
    }

    public static String getDeviceOrientation() {
        return PROPS.getProperty("deviceOrientation");
    }

    public static boolean getCaptureScreenShot() {
        return Boolean.parseBoolean(PROPS.getProperty("captureScreenshots"));
    }

    public static String getDeviceGroup() {
        return PROPS.getProperty("deviceGroup");
    }

    public static String getDeviceName() {
        return PROPS.getProperty("deviceName");
    }

    public static String getPlatformVersion() {
        return PROPS.getProperty("platformVersion");
    }

    public static String getPlatformName() {
        return PROPS.getProperty("platformName");
    }

    public static String getApp() {
        return PROPS.getProperty("app");
    }
}
