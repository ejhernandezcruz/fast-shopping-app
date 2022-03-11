package com.applaudo.shop.entity;


import lombok.Data;

import java.util.List;

@Data
public class ShoppingList {

    private String shoppingListName;
    private List<Item> items;

}
