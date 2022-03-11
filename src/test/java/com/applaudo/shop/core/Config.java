package com.applaudo.shop.core;

import net.sf.cglib.core.ProcessSwitchCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static final String BASE_PATH = System.getProperty("user.dir");
    public static final String CONFIG_FILE = "config.properties";
    public static final String DATA_FOLDER = "data";
    public static final String APPS_FOLDER = "apps";
    private static final Properties PROPS = new Properties();

    public static void loadConfig() throws IOException {
        String path = getResourcesPath().concat(CONFIG_FILE);
        PROPS.load(new FileInputStream(path));
    }

    public static String getResourcesPath() {
        return BASE_PATH.concat(File.separator)
                .concat("src")
                .concat(File.separator)
                .concat("test")
                .concat(File.separator)
                .concat("resources")
                .concat(File.separator);

    }

    public static String getDataPath(String dataFile) {
        return getResourcesPath()
                .concat(DATA_FOLDER)
                .concat(File.separator)
                .concat(dataFile);
    }

    public static String getAppPath(String app) {
        return getResourcesPath()
                .concat(APPS_FOLDER)
                .concat(File.separator)
                .concat(app);
    }

    public static boolean isRemote() {
        return Boolean.parseBoolean(PROPS.getProperty("remote"));
    }

    public static String getAppiumServer() {
        return PROPS.getProperty("appiumServer");
    }

    public static int getTimeout() {
        return Integer.parseInt(PROPS.getProperty("timeout"));
    }

    public static String getPlatformName() {
        return PROPS.getProperty("platformName");
    }

    public static String getPlatformVersion() {
        return PROPS.getProperty("platformVersion");
    }

    public static String getAutomationName() {
        return PROPS.getProperty("automationName");
    }

    public static String getDeviceName() {
        return PROPS.getProperty("deviceName");
    }

    public static String getApp() {
        return PROPS.getProperty("app");
    }
}
