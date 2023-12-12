package com.example.algorithms;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameRegistrationPage;

    @FXML
    private TextField SurnameRegistrationPage;

    @FXML
    private Button logRegistrationPage;

    @FXML
    private TextField loginRegistrationPage;
    @FXML
    private Button BackToHelloView;

    @FXML
    private TextField passwordRegistrationPage;

    @FXML
    void initialize() {
        logRegistrationPage.setOnAction(actionEvent -> {

        String login = loginRegistrationPage.getText();
         String  password =  passwordRegistrationPage.getText();
         String name = NameRegistrationPage.getText();
         String surname = SurnameRegistrationPage.getText();
         try{
             User newUser = new User(name, surname, login, password);
             File newFile = new File("C:\\Users\\Admin\\IdeaProjects\\Algorithms\\src\\main\\resources\\com\\example\\algorithms\\Users\\"+login+".txt");
             if(newFile.exists()){
                 System.out.println("This file already exist!");
                 return;
             }
             newFile.createNewFile();
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newFile));
             out.writeObject(newUser);
             out.close();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

         try {
             ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\Algorithms\\src\\main\\resources\\com\\example\\algorithms\\Users\\nurma192.txt"));
             User user = (User)in.readObject();
             System.out.println(user.toString());;

            in.close();

         } catch (IOException e) {
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }



        });

        Methods methods = new Methods();
        BackToHelloView.setOnAction(event -> {
            methods.openPage(BackToHelloView, "hello-view.fxml");
        });


    }


}

