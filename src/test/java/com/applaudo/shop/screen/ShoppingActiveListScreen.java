package com.applaudo.shop.screen;

import com.applaudo.shop.core.BaseScreen;
import com.applaudo.shop.locator.ShoppingActiveListLocator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ShoppingActiveListScreen extends BaseScreen {

    ShoppingActiveListLocator locator;

    public ShoppingActiveListScreen(AppiumDriver driver) {
        super(driver);
        locator = new ShoppingActiveListLocator();
        PageFactory.initElements(new AppiumFieldDecorator(driver), locator);
    }

    public void addList(String shoppingListName) {
        tap(locator.createListButton);
        sendKeysUsingKeyboard(locator.listNameText, shoppingListName);
        tap(locator.addButton);
    }

    public ShoppingArchivedListScreen accessToArchivedList() {
        tap(locator.archiveTab);
        return new ShoppingArchivedListScreen(getDriver());
    }
}
