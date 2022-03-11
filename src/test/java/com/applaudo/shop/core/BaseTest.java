package com.applaudo.shop.core;

import com.applaudo.shop.listener.ExecutionListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static com.applaudo.shop.core.Kobiton.*;

@Listeners({ExecutionListener.class})
public class BaseTest {


    protected AppiumDriver driver;

    private AndroidDriver kobiton() throws MalformedURLException {
        String kobitonServerUrl = Kobiton.getKobitonServer();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Kobiton.getAutomationName());
        capabilities.setCapability(SESSION_NAME, Kobiton.getSessionName());
        capabilities.setCapability(SESSION_DESCRIPTION, Kobiton.getSessionDescription());
        capabilities.setCapability(DEVICE_ORIENTATION, Kobiton.getDeviceOrientation());
        capabilities.setCapability(CAPTURE_SCREENSHOTS, Kobiton.getCaptureScreenShot());
        capabilities.setCapability(MobileCapabilityType.APP, Kobiton.getApp());
        capabilities.setCapability(DEVICE_GROUP, Kobiton.getDeviceGroup());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Kobiton.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Kobiton.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Kobiton.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, Kobiton.getAcceptSSLCerts());
        return new AndroidDriver(new URL(kobitonServerUrl), capabilities);
    }

    private AndroidDriver local() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getPlatformName());
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, Config.getPlatformVersion());
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, Config.getAutomationName());
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getDeviceName());
        caps.setCapability(MobileCapabilityType.APP, Config.getAppPath(Config.getApp()));
        return new AndroidDriver(new URL(Config.getAppiumServer()), caps);
    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        driver = Config.isRemote() ? kobiton() : local();
    }

    @AfterTest
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }
}
