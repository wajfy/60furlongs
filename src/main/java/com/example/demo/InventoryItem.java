package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public class InventoryItem {
    private String itemName;
    private String itemDescription;
    private Image itemImage;
    public InventoryItem(String itemName, String itemDescription, String itemImagePath){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/items/" + itemImagePath + ".png")));
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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }
}
