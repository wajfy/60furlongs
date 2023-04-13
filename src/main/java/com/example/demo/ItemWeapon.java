package com.example.demo;

public class ItemWeapon extends InventoryItem{
    int weaponDamage;
    public ItemWeapon(String itemName, String itemDescription, String itemImagePath, int itemValue,ItemType itemType, int weaponDamage) {
        super(itemName, itemDescription, itemImagePath, itemValue, itemType);
        this.weaponDamage = weaponDamage;
    }

    @Override
    public int getItemStat() {
        return this.weaponDamage;
    }

    @Override
    public void setItemStat(int value){
        this.weaponDamage = value;
    }
}
