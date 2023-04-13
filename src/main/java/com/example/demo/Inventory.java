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
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

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
    Label damageLabel;
    Label defenseLabel;
    public Inventory(int inventorySize, TilePane invTilePane, TilePane loadoutPane, StackPane infoPane, Hero hero, Label hpLabel, Label damageLabel, Label defenseLabel){
        this.hpLabel = hpLabel;
        this.damageLabel = damageLabel;
        this.defenseLabel = defenseLabel;
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
                        equipWeapon(item, invTilePane.getChildren().indexOf(newSlot));
                    });
                } else if (item.getItemType() == ItemType.ARMOR) {
                    newSlot.setOnMouseClicked(event -> {
                        equipArmor(item, invTilePane.getChildren().indexOf(newSlot));
                    });
                }else if (item.getItemType() == ItemType.HEAL) {
                    newSlot.setOnMouseClicked(event -> {
                        useHealItem(item, invTilePane.getChildren().indexOf(newSlot));
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
        refreshLoadout();
    }
    public void refreshLoadout(){
        for (int i = 0; i < loadout.length; i++){
            if(loadout[i] != null){
                //System.out.println("Loadout slot is not empty");
                InventoryItem item = loadout[i];
                StackPane newSlot = new StackPane();
                newSlot.setPrefHeight(50);
                newSlot.setPrefWidth(50);
                newSlot.setId("newLoadSlot" + i);
                newSlot.getStyleClass().add("newLoadSlot");

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
                        unEquipWeapon(item);
                    });
                } else if (item.getItemType() == ItemType.ARMOR) {
                    newSlot.setOnMouseClicked(event -> {
                        unEquipArmor(item);
                    });
                }
                this.loadoutPane.getChildren().set(i, newSlot);
            }else{
                //System.out.println("loadout slot is empty");
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

                loadoutPane.getChildren().set(i, slot);
            }
        }
    }
    public void equipWeapon(InventoryItem item, int slot){
        if(loadout[0] == null){
            System.out.println("equiping weapon");
            loadout[0] = item;
            hero.setHeroDamage(hero.getHeroDamage() + item.getItemStat());
            damageLabel.setText(Integer.toString(hero.getHeroDamage()));
            removeItem(slot);
        }
    }
    public void equipArmor(InventoryItem item, int slot){
        if(loadout[1] == null){
            System.out.println("equiping armor");
            loadout[1] = item;
            hero.setHeroDefense(hero.getHeroDefense() + item.getItemStat());
            defenseLabel.setText(Integer.toString(hero.getHeroDefense()));
            removeItem(slot);
        }
    }
    public void unEquipWeapon(InventoryItem item){
        if(inventorySize > itemsCount){
            System.out.println("unequiping weapon");
            loadout[0] = null;
            addItem(item);
            hero.setHeroDamage(hero.getHeroDamage() - item.getItemStat());
            damageLabel.setText(Integer.toString(hero.getHeroDamage()));
        }else{
            System.out.println("Cannot unequip weapon, inventory is full");
        }
    }
    public void unEquipArmor(InventoryItem item){
        if(inventorySize > itemsCount){
            System.out.println("unequiping armor");
            loadout[1] = null;
            addItem(item);
            hero.setHeroDefense(hero.getHeroDefense() - item.getItemStat());
            defenseLabel.setText(Integer.toString(hero.getHeroDefense()));
        }else{
            System.out.println("Cannot unequip armor, inventory is full");
        }
    }
    public void useHealItem(InventoryItem item, int slot) {
        if(this.hero.getHeroHealth() != this.hero.getHeroMaxHealth()){
            System.out.println("using healing item");
            this.hero.setHeroHealth(this.hero.getHeroHealth() + getItemHP(item));
            if(this.hero.getHeroHealth() > this.hero.getHeroMaxHealth()){
                this.hero.setHeroHealth(this.hero.getHeroMaxHealth());
            }
            hpLabel.setText(this.hero.getHeroHealth() + "/" + this.hero.getHeroMaxHealth());
            removeItem(slot);
        }else{
            System.out.println("You have full hp");
        }

    }
    public int getItemHP(InventoryItem item){
        return item.getItemStat();
    }
    public int getItemDefense(InventoryItem item){
        return item.getItemStat();
    }
    public int getItemDamage(InventoryItem item){
        return item.getItemStat();
    }
    public void showItemInfo(InventoryItem item) {
        this.infoPane.setVisible(true);
        Label itemInfoLabel = new Label();
        String itemInfoText =
                "Name: " + item.getItemName() + "\n" +
                "Description: " + item.getItemDescription() + "\n" +
                "Type: " + item.getItemType() + "\n" +
                "Value: " + item.getItemValue();
        if(item.getItemType() == ItemType.WEAPON){
            itemInfoText += "\nDamage: " + getItemDamage(item);
        } else if (item.getItemType() == ItemType.HEAL) {
            itemInfoText += "\nHP: " + getItemHP(item);
        } else if (item.getItemType() == ItemType.ARMOR) {
            itemInfoText += "\nDefense: " + getItemDefense(item);
        }

        itemInfoLabel.setText(itemInfoText);
        itemInfoLabel.setWrapText(true);
        infoPane.getChildren().add(itemInfoLabel);
    }
    public void removeItemInfo(InventoryItem item){
        this.infoPane.getChildren().clear();
        this.infoPane.setVisible(false);
    }
    public void showInventoryArray(){
        System.out.println("----INV----");
        for (int i = 0; i < inventory.length; i++){
            if(inventory[i] != null){
                System.out.println(inventory[i].getItemName());
            }else{
                System.out.println("empty slot");
            }
        }
        System.out.println("----LOAD----");
        for(int i = 0; i < loadout.length; i++){
            if(loadout[i] != null){
                System.out.println(loadout[i].getItemName());
            }else{
                System.out.println("empty loadout slot");
            }
        }
    }

}
