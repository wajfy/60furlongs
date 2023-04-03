package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Start extends Application {

    @Override

    public void start(Stage stage) throws IOException {
        int width = 640;
        int height = 480;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Scene scene = new Scene(root, width, height);
        String css = Objects.requireNonNull(this.getClass().getResource("css/style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("60Furlongs");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}