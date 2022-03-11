package com.applaudo.shop.locator;


import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingListLocator {

    @AndroidFindBy(accessibility = "No list selected")
    public WebElement emptyListComponent;

    @AndroidFindBy(xpath = "(//android.widget.Button)[last()]")
    public WebElement addItemButton;

    @AndroidFindBy(accessibility = "ADD")
    public WebElement addModalButton;

    @AndroidFindBy(accessibility = "SAVE")
    public WebElement saveItemButton;

    @AndroidFindBy(accessibility = "REMOVE")
    public WebElement deleteItemButtom;

    @AndroidFindBy(accessibility = "ARCHIVE")
    public WebElement archiveButton;

    @AndroidFindBy(className = "android.widget.CheckBox")
    public List<WebElement> checkboxes;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='EDIT']/parent::android.view.View/android.view.View[1]")
    public WebElement collapseItemElement;

    public By getShoppingList(String name) {
        String shoppingListLocator = String.format("//android.view.View[@content-desc='%s']", name);
        return By.xpath(shoppingListLocator);
    }

    public By getItem(String item) {
        return MobileBy.AccessibilityId(item);
    }

    public By getTextBoxItem(String item) {
        String expression = String.format("//android.view.View[contains(@text,\"%s\")]", item);
        return By.xpath(expression);
    }

}
