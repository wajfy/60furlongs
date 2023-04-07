package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class Inventory {
    private int inventorySize;
    InventoryItem[] inventory;
    @FXML
    private TilePane invTilePane;
    public Inventory(int inventorySize, TilePane invTilePane){
        this.inventory = new InventoryItem[inventorySize];
        this.invTilePane = invTilePane;
        System.out.println("Building inventory");
        for (int i = 0; i < 6; i++){
            System.out.println("Building inv Slot " + i);
            StackPane slot = new StackPane();
            slot.getStyleClass().add("invSlot");
            slot.setId("invSlot" + i);
            slot.setPrefHeight(50);
            slot.setPrefWidth(50);
            invTilePane.getChildren().add(slot);
        }
        System.out.println("Building inventory finished");
        invTilePane.setOrientation(Orientation.HORIZONTAL);
    }
    public void addItem(InventoryItem item, int slot){
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
            System.out.println("Mouse enterd on: " + item.getItemName());
        });
        newSlot.setOnMouseExited(event -> {
            System.out.println("Mouse exited from: " + item.getItemName());
        });

        inventory[slot] = item;
        this.invTilePane.getChildren().set(slot, newSlot);
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
