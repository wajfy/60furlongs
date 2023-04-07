package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public class Hero {
    private String heroName;
    private int heroAge;
    private int heroGold;
    private int heroHealth;
    private int heroMaxHealth;
    private int heroEnergy;
    private int heroMaxEnergy;
    private int heroHeight;
    private int heroSpeed;
    private int heroIntelligence;
    private int heroCharisma;
    private int heroDamage;
    private int heroArmor;
    private Image heroImage;

    public Hero(String heroName, int heroAge, Image heroImage){
        this.heroName = heroName;
        this.heroAge = heroAge;
        if(this.heroName.equals("xoxoxo")){
            this.heroGold = 999999;
        }else{
            this.heroGold = InsideController.getRandomNumber(0, 100);
        }
        this.heroHealth = InsideController.getRandomNumber(30, 60);
        this.heroMaxHealth = 100;
        this.heroEnergy = InsideController.getRandomNumber(70, 100);
        this.heroMaxEnergy = 100;
        this.heroDamage = InsideController.getRandomNumber(5, 20);
        this.heroArmor = InsideController.getRandomNumber(0, 10);
        this.heroSpeed = InsideController.getRandomNumber(1, 5);
        this.heroIntelligence = InsideController.getRandomNumber(1, 5);
        this.heroCharisma = InsideController.getRandomNumber(1, 5);
        this.heroHeight = InsideController.getRandomNumber(150, 200);

        if(this.heroName.equals("xoxoxo")){
            this.heroImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/test.png")));
        }else{
            this.heroImage = heroImage;
        }
        System.out.println("Created hero");
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

    public int getHeroMaxHealth() {
        return heroMaxHealth;
    }

    public int getHeroEnergy() {
        return heroEnergy;
    }

    public int getHeroMaxEnergy() {
        return heroMaxEnergy;
    }

    public int getHeroHeight() {
        return heroHeight;
    }

    public int getHeroSpeed() {
        return heroSpeed;
    }

    public int getHeroIntelligence() {
        return heroIntelligence;
    }

    public int getHeroCharisma() {
        return heroCharisma;
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

    public void setHeroMaxHealth(int heroMaxHealth) {
        this.heroMaxHealth = heroMaxHealth;
    }

    public void setHeroEnergy(int heroEnergy) {
        this.heroEnergy = heroEnergy;
    }

    public void setHeroMaxEnergy(int heroMaxEnergy) {
        this.heroMaxEnergy = heroMaxEnergy;
    }

    public void setHeroHeight(int heroHeight) {
        this.heroHeight = heroHeight;
    }

    public void setHeroSpeed(int heroSpeed) {
        this.heroSpeed = heroSpeed;
    }

    public void setHeroIntelligence(int heroIntelligence) {
        this.heroIntelligence = heroIntelligence;
    }

    public void setHeroCharisma(int heroCharisma) {
        this.heroCharisma = heroCharisma;
    }
}
