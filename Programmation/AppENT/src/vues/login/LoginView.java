/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.login;

import java.awt.Button;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.ControllerUsers;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import vues.UserControl.homePage.UserControlMainStud;
import vues.mainView.MainView;

/**
 *
 * @author ozsevik1
 */
public class LoginView  {
    
  
    private ControllerUsers controller;
    //XML Encoder/Decoder
    
    @FXML
    TextField identifierField;
    
    @FXML
    PasswordField passwordField;
    
    @FXML
    Label errorLabel;
    
    public LoginView(ControllerUsers controller) {
        this.controller = controller;
    }
    
    @FXML
    public void actionConnexion(Event e) throws Exception {
        
       connexion();
       
    }
    
    public void connexion() throws IOException{
         boolean result = controller.checkLogin(identifierField.getText().toString(), passwordField.getText().toString());
        System.out.println(result);
        if(result) {
            Parent root;
            MainView mainView = new MainView(controller);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/mainView/MainView.fxml"));
            loader.setController(mainView);
            loader.setClassLoader(mainView.getClass().getClassLoader());
            root = loader.load();
           
            mainView.setBasicsSettings();
            
            Stage mainWindow =(Stage) this.errorLabel.getScene().getWindow(); 
            Scene s = new Scene(root, mainWindow.getWidth(), mainWindow.getHeight());
            mainWindow.setScene(s);
            mainWindow.setMinWidth(800);
            mainWindow.setMinHeight(650);
            mainWindow.show();
        } else {
            errorLabel.setVisible(true);
        }
    
    }

}
