package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.TextStyle;
import java.util.Objects;

public class StartController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    int width = 800;
    int height = 600;


    @FXML
    TextField loginInput;
    @FXML
    TextField ageInput;
    @FXML
    public void changeScene(ActionEvent event, int width, int height, String file) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(file)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {
        try {
            String username = loginInput.getText();
            int age = Integer.parseInt(ageInput.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inside.fxml"));
            root = loader.load();
            InsideController insideCon = loader.getController();
            insideCon.createNewHero(username, age);
            insideCon.displayInfo();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, width, height);
            String css = Objects.requireNonNull(this.getClass().getResource("css/style.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
            System.out.println("Starting game");
        }catch (NumberFormatException e){
            System.out.println("wrong input");
        }catch (Exception e){
            System.out.println(e);
        }

    }


//    public void switchToHello(ActionEvent event) throws IOException {
//        int width = 640;
//        int height = 480;
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root, width, height);
//        stage.setScene(scene);
//        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
//        scene.getStylesheets().add(css);
//        stage.show();
//    }
}