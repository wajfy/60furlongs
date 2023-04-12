package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
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
    Hero hero;
    Label hpLabel;
    public Inventory(int inventorySize, TilePane invTilePane, TilePane loadoutPane, StackPane infoPane, Hero hero, Label hpLabel){
        this.hpLabel = hpLabel;
        this.hero = hero;
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
    public void addItem(InventoryItem item) {
        if (itemsCount < this.inventorySize) {
            boolean placed = false;
            int index = 0;
            while(!placed){
                if(inventory[index] != null){
                    System.out.println("This slot is occupied");
                }else{
                    System.out.println("This slot is not occupied");
                    inventory[index] = item;
                    placed = true;
                }
                index++;
            }
            itemsCount++;
            refreshInventory();
            showInventoryArray();
        }else{
            System.out.println("Cannot add more items, inventory is full");
        }
    }
    public void removeItem(int index){
        System.out.println("removing item: " + inventory[index].getItemName() + " on index: " + index);
        inventory[index] = null;
        itemsCount--;
        refreshInventory();
    }
    public void refreshInventory(){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] != null){
                InventoryItem item = inventory[i];
                StackPane newSlot = new StackPane();
                newSlot.setPrefHeight(50);
                newSlot.setPrefWidth(50);
                newSlot.setId("newSlot" + i);
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
                        removeItem(invTilePane.getChildren().indexOf(newSlot));
                    });
                } else if (item.getItemType() == ItemType.ARMOR) {
                    newSlot.setOnMouseClicked(event -> {
                        equipArmor();
                        removeItem(invTilePane.getChildren().indexOf(newSlot));
                    });
                }else if (item.getItemType() == ItemType.HEAL) {
                    newSlot.setOnMouseClicked(event -> {
                        useHealItem(item);
                        removeItem(invTilePane.getChildren().indexOf(newSlot));
                    });
                }
                this.invTilePane.getChildren().set(i, newSlot);
            }else{
                StackPane slot = new StackPane();
                slot.getStyleClass().add("invSlot");
                slot.setId("invSlot" + i);
                slot.setPrefHeight(50);
                slot.setPrefWidth(50);
                invTilePane.getChildren().set(i, slot);
            }
        }
    }
    public void equipWeapon(){
        System.out.println("equiping weapon");
    }
    public void equipArmor(){
        System.out.println("equiping armor");
    }
    public void useHealItem(InventoryItem item) {
        if(this.hero.getHeroHealth() != this.hero.getHeroMaxHealth()){
            System.out.println("using healing item");
            this.hero.setHeroHealth(this.hero.getHeroHealth() + getItemHP(item));
            if(this.hero.getHeroHealth() > this.hero.getHeroMaxHealth()){
                this.hero.setHeroHealth(this.hero.getHeroMaxHealth());
            }
            hpLabel.setText(this.hero.getHeroHealth() + "/" + this.hero.getHeroMaxHealth());
        }else{
            System.out.println("You have full hp");
        }

    }
    public int getItemHP(InventoryItem item){
        int HP = 0;
        if (item.getItemType() == ItemType.HEAL) {
            try {
                Method MHP = item.getClass().getMethod("getHealHP", null);
                try {
                    HP = (int) MHP.invoke(item);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
        return HP;
    }
    public int getItemDefense(InventoryItem item){
        int defense = 0;
        if (item.getItemType() == ItemType.ARMOR) {
            try {
                Method Mdefense = item.getClass().getMethod("getArmorDefense", null);
                try {
                    defense = (int) Mdefense.invoke(item);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
        return defense;
    }
    public int getItemDamage(InventoryItem item){
        int damage = 0;
        if(item.getItemType() == ItemType.WEAPON) {
            try {
                Method Mdamage = item.getClass().getMethod("getWeaponDamage", null);
                try {
                    damage = (int) Mdamage.invoke(item);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.toString());
            }
        }
        return damage;
    }
    public void showItemInfo(InventoryItem item) {
        this.infoPane.setVisible(true);
        Label itemInfoLabel = new Label();
        String itemInfoText =
                "Name: " + item.getItemName() + "\n" +
                "Description: " + item.getItemDescription() + "\n" +
                "Type: " + item.getItemType() + "\n" +
                "Value: " + item.getItemValue();
                itemInfoText += "\nDamage: " + getItemDamage(item);

                itemInfoText += "\nHP: " + getItemHP(item);

                itemInfoText += "\nDefense: " + getItemDefense(item);

        itemInfoLabel.setText(itemInfoText);
        itemInfoLabel.setWrapText(true);
        infoPane.getChildren().add(itemInfoLabel);
    }
    public void removeItemInfo(InventoryItem item){
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
