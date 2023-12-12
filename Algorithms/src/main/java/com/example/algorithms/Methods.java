package com.example.algorithms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Methods {
    void openPage(Button registrationFirstPage, String pageName){
        registrationFirstPage.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(pageName));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
