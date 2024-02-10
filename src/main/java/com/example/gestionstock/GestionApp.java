package com.example.gestionstock;

import com.db.utils.ProduitManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // db stuff



        // jvfx init
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        FXMLLoader fxmlLoader = new FXMLLoader(GestionApp.class.getResource("Builder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(GestionApp.class.getResource("style.css").toExternalForm());
        Image icon = new Image("C:/Users/Yassir/Projects/Scala/GestionStock/src/logo-jfx.png");
        stage.getIcons().add(icon);
        stage.setTitle("ISYY Inventory Management");
        stage.setWidth(1000);
        stage.setHeight(650);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}