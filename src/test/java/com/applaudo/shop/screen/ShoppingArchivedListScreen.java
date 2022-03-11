package com.applaudo.shop.screen;

import com.applaudo.shop.core.BaseScreen;
import com.applaudo.shop.locator.ShoppingArchivedListLocator;
import com.google.common.truth.Truth;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class ShoppingArchivedListScreen extends BaseScreen {

    private ShoppingArchivedListLocator locator;

    public ShoppingArchivedListScreen(AppiumDriver driver) {
        super(driver);
        locator = new ShoppingArchivedListLocator();
        PageFactory.initElements(new AppiumFieldDecorator(driver), locator);
    }

    public void deleteArchiveList(String name) {
        WebElement shoppingList = waitForVisibility(locator.getShoppingList(name));
        int x = getCoordinateX(shoppingList);
        int y = getCoordinateY(shoppingList);
        tapCoordinates(x, y);
        tap(locator.deleteListOption);
        tap(locator.modalDeleteOption);
    }

    public int getCoordinateX(WebElement element) {
        int width = element.getSize().getWidth();
        double factor = 0.9176;
        return (int) (width * factor);
    }

    public int getCoordinateY(WebElement element) {
        int y = element.getLocation().y;
        int height = element.getSize().getHeight();
        double factor = 0.7862;
        return (int) ((y + height) * factor);
    }


    public void verifyShoppingListNotPresent(String name) {
        Truth.assertThat(isElementPresent(locator.getShoppingList(name))).isFalse();
    }
}
