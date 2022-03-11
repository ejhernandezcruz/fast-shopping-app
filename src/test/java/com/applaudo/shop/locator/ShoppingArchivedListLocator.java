package com.applaudo.shop.locator;

;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ShoppingArchivedListLocator {

    @AndroidFindBy(accessibility = "Delete")
    public WebElement deleteListOption;

    @AndroidFindBy(accessibility = "DELETE")
    public WebElement modalDeleteOption;

    public By getShoppingList(String name) {
        String expression = String.format("//android.widget.Button[contains(@content-desc,'%s')]", name);
        return By.xpath(expression);
    }
}
