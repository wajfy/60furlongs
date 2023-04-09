package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public class InventoryItem {
    private String itemName;
    private String itemDescription;
    private Image itemImage;
    private int itemValue;
    private ItemType itemType;
    public InventoryItem(String itemName, String itemDescription, String itemImagePath, int itemValue, ItemType itemType){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/items/" + itemImagePath + ".png")));
        this.itemValue = itemValue;
        this.itemType = itemType;
        System.out.println("Created item " + itemName);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Image getItemImage() {
        return itemImage;
    }

    public int getItemValue() {
        return itemValue;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
