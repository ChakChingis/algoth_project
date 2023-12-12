package com.example.algorithms;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label invalid;

    @FXML
    private Button logInFirstPage;

    @FXML
    private TextField loginLogFirstPage;

    @FXML
    private TextField passwordFirstPage;

    @FXML
    private Button registrationFirstPage;

    @FXML
    void initialize() {
        Methods methods = new Methods();
          registrationFirstPage.setOnAction(event -> {
              methods.openPage(registrationFirstPage, "app.fxml");
          });

        logInFirstPage.setOnAction(actionEvent -> {
            String login = loginLogFirstPage.getText();
            String password = passwordFirstPage.getText();

            try {
                String filePath = "C:\\Users\\Admin\\IdeaProjects\\Algorithms\\src\\main\\resources\\com\\example\\algorithms\\Users\\" + login + ".txt";

                File userFile = new File(filePath);

                if (userFile.exists()) {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile));
                    User existingUser = (User) in.readObject();
                    in.close();
                    if(existingUser.getPassword().isBlank() || existingUser.getLogin().isBlank()){
                        System.out.println("Something wrong ");
                    }
                     else if (existingUser.getPassword().equals(password)) {

                        methods.openPage(logInFirstPage,"mainPage.fxml");

                    } else {

                        System.out.println("Incorrect password. Access denied.");
                    }
                } else {

                    System.out.println("User not found. Access denied.");
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }


}
