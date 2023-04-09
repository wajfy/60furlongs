package com.example.demo;

public class ItemHeal extends InventoryItem{
    int healHP;
    public ItemHeal(String itemName, String itemDescription, String itemImagePath, int itemValue,ItemType itemType, int healHP) {
        super(itemName, itemDescription, itemImagePath, itemValue, itemType);
        this.healHP = healHP;
    }

    public int getHealHP() {
        return healHP;
    }

    public void setHealHP(int healHP) {
        this.healHP = healHP;
    }
}
