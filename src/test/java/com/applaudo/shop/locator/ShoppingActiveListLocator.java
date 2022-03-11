package com.applaudo.shop.locator;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ShoppingActiveListLocator {

    @AndroidFindBy(accessibility = "NEW LIST")
    public WebElement createListButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@text,'Write shopping list')]")
    public WebElement listNameText;

    @AndroidFindBy(accessibility = "ADD")
    public WebElement addButton;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Archived')]")
    public WebElement archiveTab;


}
