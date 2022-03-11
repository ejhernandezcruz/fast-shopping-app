package com.applaudo.shop.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class BaseScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Config.getTimeout());
        this.wait.ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class));
    }

    protected AppiumDriver getDriver() {
        return driver;
    }

    protected void waitForVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.warn("Timeout waiting for ".concat(element.toString()), e);
            Assert.fail(e.getMessage());
        }
    }

    protected WebElement waitForVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.warn("Timeout waiting for ".concat(locator.toString()), e);
            Assert.fail(e.getMessage());
            return null;
        }
    }

    protected Boolean waitCheckboxSelection(WebElement element) {
        try {
            return wait.until(ExpectedConditions.attributeToBe(element, "checked", "true"));
        } catch (TimeoutException e) {
            log.warn("Timeout waiting for ".concat(element.toString()), e);
            Assert.fail(e.getMessage());
            return false;
        }
    }

    protected void tap(WebElement element) {
        waitForVisibility(element);
        element.click();
    }


    public void tapCoordinates(int x, int y) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    protected void sendKeysUsingKeyboard(String text) {
        driver.getKeyboard().sendKeys(text);
    }

    protected void sendKeysUsingKeyboard(WebElement element, String text) {
        tap(element);
        driver.getKeyboard().sendKeys(text);
    }

    protected void hideKeyboard() {
        driver.hideKeyboard();
    }


    protected void scroll() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.2);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);

        new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release()
                .perform();
    }

    protected boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }


}
