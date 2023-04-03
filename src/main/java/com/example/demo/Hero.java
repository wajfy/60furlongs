package com.example.demo;

import javafx.scene.image.Image;

public class Hero {
    private String heroName;
    private int heroAge;
    private int heroGold;
    private int heroHealth;
    private int heroDamage;
    private int heroArmor;
    private Image heroImage;

    public Hero(String heroName, int heroAge, Image heroImage){
        this.heroName = heroName;
        this.heroAge = heroAge;
        this.heroGold = 50;
        this.heroHealth = 100;
        this.heroDamage = 20;
        this.heroArmor = 0;
        this.heroImage = heroImage;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getHeroAge() {
        return heroAge;
    }

    public int getHeroGold() {
        return heroGold;
    }

    public int getHeroHealth() {
        return heroHealth;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public int getHeroArmor() {
        return heroArmor;
    }

    public Image getHeroImage() {
        return heroImage;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setHeroAge(int heroAge) {
        this.heroAge = heroAge;
    }

    public void setHeroGold(int heroGold) {
        this.heroGold = heroGold;
    }

    public void setHeroHealth(int heroHealth) {
        this.heroHealth = heroHealth;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public void setHeroArmor(int heroArmor) {
        this.heroArmor = heroArmor;
    }

    public void setHeroImage(Image heroImage) {
        this.heroImage = heroImage;
    }
}
