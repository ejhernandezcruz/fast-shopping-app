package com.applaudo.shop.tests;

import com.applaudo.shop.core.BaseTest;
import com.applaudo.shop.entity.Item;
import com.applaudo.shop.entity.ShoppingList;
import com.applaudo.shop.screen.ShoppingArchivedListScreen;
import com.applaudo.shop.screen.ShoppingListScreen;
import com.applaudo.shop.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class ShoppingListTests extends BaseTest {

    private ShoppingListScreen mainScreen;

    @DataProvider(name = "shoppingListDP")
    public Object[][] shoppingListDataProvider() {
        return JsonUtil.jsonToDataProvider("shopping.json", ShoppingList.class);
    }

    @BeforeClass
    public void initialize() {
        this.mainScreen = new ShoppingListScreen(driver);
    }

    @Test(testName = "Create shopping list", dataProvider = "shoppingListDP", priority = 1)
    public void createShoppingListTest(ShoppingList shoppingList) {
        mainScreen.accessToActiveList()
                .addList(shoppingList.getShoppingListName());
    }

    @Test(testName = "Add items to shopping list", dataProvider = "shoppingListDP", priority = 2)
    public void addItemsTest(ShoppingList shoppingList) {
        mainScreen.addItemsToList(shoppingList);
    }

    @Test(testName = "Edit all the items and add the quantity", dataProvider = "shoppingListDP", priority = 3)
    public void editItemsTest(ShoppingList shoppingList) {
        shoppingList.getItems()
                .forEach(item -> mainScreen.setItemQuantity(item.getName(), item.getQuantity()));
    }

    @Test(testName = "Remove 2 item and make sure that the items do not appear", dataProvider = "shoppingListDP", priority = 4)
    public void removeItemsTest(ShoppingList shoppingList) {
        int size = shoppingList.getItems().size();
        List<Item> myLastItems = shoppingList.getItems().subList(size - 2, size);
        myLastItems.stream()
                .map(item -> String.format("%s(%s)", item.getName(), item.getQuantity()))
                .forEach(itemName -> {
                    mainScreen.removeItem(itemName);
                    mainScreen.verifyItemWasDeleted(itemName);
                });
    }

    @Test(testName = "Check all items and proceed to archive the list", priority = 5)
    public void archiveAllItemsTest() {
        mainScreen.archiveAllItems();
    }

    @Test(testName = "Delete archived list", dataProvider = "shoppingListDP", priority = 6)
    public void deleteArchivedList(ShoppingList shoppingList) {
        ShoppingArchivedListScreen archiveScreen = mainScreen.accessToActiveList().accessToArchivedList();
        archiveScreen.deleteArchiveList(shoppingList.getShoppingListName());
        archiveScreen.verifyShoppingListNotPresent(shoppingList.getShoppingListName());
    }
}
