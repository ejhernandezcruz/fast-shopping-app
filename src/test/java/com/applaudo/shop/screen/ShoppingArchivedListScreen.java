package com.applaudo.shop.screen;

import com.applaudo.shop.core.BaseScreen;
import com.applaudo.shop.locator.ShoppingArchivedListLocator;
import com.google.common.truth.Truth;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;


public class ShoppingArchivedListScreen extends BaseScreen {

    private ShoppingArchivedListLocator locator;

    public ShoppingArchivedListScreen(AppiumDriver driver) {
        super(driver);
        locator = new ShoppingArchivedListLocator();
        PageFactory.initElements(new AppiumFieldDecorator(driver), locator);
    }

    public void deleteArchiveList(String name) {
        waitForVisibility(locator.getShoppingList(name));
        tapCoordinates(991, 592);
        tap(locator.deleteListOption);
        tap(locator.modalDeleteOption);
    }


    public void verifyShoppingListNotPresent(String name) {
        Truth.assertThat(isElementPresent(locator.getShoppingList(name))).isFalse();
    }
}
