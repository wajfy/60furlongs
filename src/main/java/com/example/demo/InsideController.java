package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class InsideController {
    @FXML
    Label nameLabel;
    @FXML
    Label ageLabel;
    @FXML
    Label goldLabel;
    @FXML
    Label healthLabel;
    @FXML
    Label damageLabel;
    @FXML
    Label armorLabel;

    @FXML
    ImageView profilePicture;

    String[] pictures = {
            "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png", "10.png",
            "11.png", "12.png", "13.png", "14.png", "15.png", "16.png", "17.png", "18.png", "19.png", "20.png",
            "21.png", "22.png", "23.png", "24.png", "25.png", "26.png", "27.png", "28.png", "29.png", "30.png",
            "31.png", "32.png"
    };

    Hero hrdina;

    public int getRandomNumber(int min, int max){
        int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return randomInt;
    }
    public Image getRandomPicture(){
        int index = getRandomNumber(0, 31);
        String picture = "img/CharacterPic/" + this.pictures[index];
        Image profPic = new Image(Objects.requireNonNull(getClass().getResourceAsStream(picture)));
        return profPic;
    }
    public void createNewHero(String username, int age){
        Image pic = getRandomPicture();
        this.hrdina = new Hero(username, age, pic);
    }

    public void displayInfo(){
        nameLabel.setText(hrdina.getHeroName());
        ageLabel.setText("Age: " + hrdina.getHeroAge());
        goldLabel.setText("Gold: " + hrdina.getHeroGold());
        healthLabel.setText("Health: " + hrdina.getHeroHealth());
        damageLabel.setText("Defense: " + hrdina.getHeroDamage());
        armorLabel.setText("Armor: " + hrdina.getHeroArmor());
        profilePicture.setImage(hrdina.getHeroImage());
    }
}
