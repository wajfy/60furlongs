package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Inventory {
    //inv
    private int inventorySize;
    private int itemsCount;
    InventoryItem[] inventory;
    InventoryItem[] loadout;
    private TilePane invTilePane;

    //loadout
    private TilePane loadoutPane;
    private int loadoutSize = 2;
    private Image emptySwordIMG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/items/weapons/emptySword.png")));
    private Image emptyArmorIMG = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/items/armors/emptyArmor.png")));
    StackPane infoPane;
    public Inventory(int inventorySize, TilePane invTilePane, TilePane loadoutPane, StackPane infoPane){
        //building inv
        this.infoPane = infoPane;
        this.infoPane.getStyleClass().add("infoPane");
        this.itemsCount = 0;
        this.inventorySize = inventorySize;
        this.inventory = new InventoryItem[this.inventorySize];
        this.loadout = new InventoryItem[this.loadoutSize];
        this.invTilePane = invTilePane;
        this.loadoutPane = loadoutPane;

        System.out.println("Building inventory");
        for (int i = 0; i < 6; i++){
            System.out.println("Building inventory Slot " + i);
            StackPane slot = new StackPane();
            slot.getStyleClass().add("invSlot");
            slot.setId("invSlot" + i);
            slot.setPrefHeight(50);
            slot.setPrefWidth(50);
            invTilePane.getChildren().add(slot);
        }
        System.out.println("Building inventory finished");
        invTilePane.setOrientation(Orientation.HORIZONTAL);

        //building loadout
        System.out.println("Building loadout panel");
        for (int i = 0; i < this.loadoutSize; i++){
            ImageView loadoutImg;
            if(i == 0){
                loadoutImg = new ImageView(emptySwordIMG);
            }else{
                loadoutImg = new ImageView(emptyArmorIMG);
            }
            System.out.println("Building loadout Slot " + i);
            StackPane slot = new StackPane();
            slot.getStyleClass().add("loadSlot");
            slot.setId("loadSlot" + i);
            slot.setPrefHeight(50);
            slot.setPrefWidth(50);

            loadoutImg.setFitWidth(40);
            loadoutImg.setFitHeight(40);
            slot.getChildren().add(loadoutImg);

            loadoutPane.getChildren().add(slot);
        }
        System.out.println("Building loadout panel finished");
        loadoutPane.setOrientation(Orientation.HORIZONTAL);
    }
    public void addItem(InventoryItem item, int slot) {
        slot = itemsCount;
        if (itemsCount < this.inventorySize) {
            StackPane newSlot = new StackPane();
            newSlot.setPrefHeight(50);
            newSlot.setPrefWidth(50);
            newSlot.setId("newSlot" + slot);
            newSlot.getStyleClass().add("newSlot");

            ImageView slotImg = new ImageView(item.getItemImage());
            slotImg.setFitHeight(40);
            slotImg.setFitWidth(40);
            newSlot.getChildren().add(slotImg);

            newSlot.setOnMouseEntered(event -> {
                showItemInfo(item);
            });
            newSlot.setOnMouseExited(event -> {

                removeItemInfo(item);
            });
            if(item.getItemType() == ItemType.WEAPON){
                newSlot.setOnMouseClicked(event -> {
                    equipWeapon();
                });
            } else if (item.getItemType() == ItemType.ARMOR) {
                newSlot.setOnMouseClicked(event -> {
                    equipArmor();
                });
            }else if (item.getItemType() == ItemType.HEAL) {
                newSlot.setOnMouseClicked(event -> {
                    useHealItem();
                });
            }
            itemsCount++;
            inventory[slot] = item;
            this.invTilePane.getChildren().set(slot, newSlot);
            showInventoryArray();
        }else{
            System.out.println("Cannot add more items, inventory is full");
        }
    }
    public void equipWeapon(){
        System.out.println("equiping weapon");
    }
    public void equipArmor(){
        System.out.println("equiping armor");
    }
    public void useHealItem(){
        System.out.println("using healing item");
    }
    public void showItemInfo(InventoryItem item) {
        this.infoPane.setVisible(true);
        Label itemInfoLabel = new Label();
        String itemInfoText =
                "Name: " + item.getItemName() + "\n" +
                "Description: " + item.getItemDescription() + "\n" +
                "Type: " + item.getItemType() + "\n" +
                "Value: " + item.getItemValue();

        System.out.println("Name: " + item.getItemName());
        System.out.println("Description: " + item.getItemDescription());
        System.out.println("Type: " + item.getItemType());
        System.out.println("Value: " + item.getItemValue());
        if(item.getItemType() == ItemType.WEAPON) {
            try {
                Method Mdamage = item.getClass().getMethod("getWeaponDamage", null);
                try {
                    int damage = (int) Mdamage.invoke(item);
                    System.out.println("Damage: " + damage);
                    itemInfoText += "\nDamage: " + damage;
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        } else if (item.getItemType() == ItemType.HEAL) {
            try {
                Method MHP = item.getClass().getMethod("getHealHP", null);
                try {
                    int HP = (int) MHP.invoke(item);
                    System.out.println("HP: " + HP);
                    itemInfoText += "\nHP: " + HP;
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }else if (item.getItemType() == ItemType.ARMOR) {
            try {
                Method Mdefense = item.getClass().getMethod("getArmorDefense", null);
                try {
                    int defense = (int) Mdefense.invoke(item);
                    System.out.println("Defense: " + defense);
                    itemInfoText += "\nDefense: " + defense;
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
        itemInfoLabel.setText(itemInfoText);
        itemInfoLabel.setWrapText(true);
        infoPane.getChildren().add(itemInfoLabel);
    }
    public void removeItemInfo(InventoryItem item){
        System.out.println("Mouse exited from: " + item.getItemName());
        this.infoPane.getChildren().clear();
        this.infoPane.setVisible(false);
    }
    public void showInventoryArray(){
        for (int i = 0; i < inventory.length; i++){
            if(inventory[i] != null){
                System.out.println(inventory[i].getItemName());
            }else{
                System.out.println("empty slot");
            }
        }
    }

}
