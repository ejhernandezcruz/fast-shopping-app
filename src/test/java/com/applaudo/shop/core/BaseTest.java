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

@Listeners({ExecutionListener.class})
public class BaseTest {

    protected AppiumDriver driver;

    private AndroidDriver kobiton() throws MalformedURLException {
        String kobitonServerUrl = "https://ejhernandezcruz:#######@api.kobiton.com/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("sessionName", "Automation test session");
        capabilities.setCapability("sessionDescription", "");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("captureScreenshots", true);
        capabilities.setCapability("app", "kobiton-store:318296");
        capabilities.setCapability("deviceGroup", "KOBITON");
        capabilities.setCapability("deviceName", "Galaxy J2 Prime");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, true);
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
