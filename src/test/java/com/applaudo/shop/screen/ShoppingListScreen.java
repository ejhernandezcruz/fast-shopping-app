package com.applaudo.shop.screen;

import com.applaudo.shop.core.BaseScreen;
import com.applaudo.shop.entity.ShoppingList;
import com.applaudo.shop.locator.ShoppingListLocator;
import com.google.common.truth.Truth;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingListScreen extends BaseScreen {

    private final ShoppingListLocator locator;

    public ShoppingListScreen(AppiumDriver driver) {
        super(driver);
        locator = new ShoppingListLocator();
        PageFactory.initElements(new AppiumFieldDecorator(driver), locator);
    }

    public ShoppingActiveListScreen accessToActiveList() {
        tap(locator.emptyListComponent);
        return new ShoppingActiveListScreen(getDriver());
    }

    public void addItemsToList(ShoppingList shoppingList) {
        waitForVisibility(locator.getShoppingList(shoppingList.getShoppingListName()));
        shoppingList.getItems().forEach(item -> addItem(item.getName()));
    }

    public void addItem(String item) {
        tap(locator.addItemButton);
        sendKeysUsingKeyboard(item);
        tap(locator.addModalButton);
    }

    public void editItem(String item, String text) {
        tapItem(item);
        WebElement textBox = waitForVisibility(locator.getTextBoxItem(item));
        sendKeysUsingKeyboard(textBox, text);
        hideKeyboard();
        scroll();
        tap(locator.saveItemButton);
        tap(locator.collapseItemElement);
    }

    public void tapItem(String item) {
        WebElement itemElement = waitForVisibility(locator.getItem(item));
        tap(itemElement);
    }

    public void setItemQuantity(String item, int quantity) {
        String text = String.format("(%d)", quantity);
        editItem(item, text);
    }

    public void removeItem(String itemName) {
        WebElement item = waitForVisibility(locator.getItem(itemName));
        tap(item);
        tap(locator.deleteItemButtom);
    }

    public void verifyItemWasDeleted(String itemName) {
        Truth.assertThat(isElementPresent(locator.getItem(itemName))).isFalse();
    }

    public void archiveAllItems() {
        List<WebElement> checkboxes = locator.checkboxes;
        checkboxes.forEach(element -> {
            tap(element);
            verifyCheckboxSelection(element);
        });
        tap(locator.archiveButton);
    }

    public void verifyCheckboxSelection(WebElement checkbox) {
        Truth.assertThat(waitCheckboxSelection(checkbox)).isTrue();
    }


}
