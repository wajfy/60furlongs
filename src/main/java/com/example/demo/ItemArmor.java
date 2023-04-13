package com.example.demo;

public class ItemArmor extends InventoryItem{
    private int armorDefense;
    public ItemArmor(String itemName, String itemDescription, String itemImagePath, int itemValue,ItemType itemType, int armorDefense) {
        super(itemName, itemDescription, itemImagePath, itemValue, itemType);
        this.armorDefense = armorDefense;
    }

    public int getArmorDefense() {
        return armorDefense;
    }

    public void setArmorDefense(int armorDefense) {
        this.armorDefense = armorDefense;
    }

    @Override
    public int getItemStat() {
        return this.armorDefense;
    }

    @Override
    public void setItemStat(int value){
        this.armorDefense = value;
    }
}
