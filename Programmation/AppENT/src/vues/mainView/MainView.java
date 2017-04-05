/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.mainView;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vues.UserControl.admin.UserControlAddUser;
import vues.UserControl.homePage.*;
import vues.UserControl.mark.UserControlNoteProf;
import vues.UserControl.schedule.UserControlSchedule;

import metier.utilisateur.Utilisateur;
import metier.utilisateur.Admin;
import metier.utilisateur.Etudiant;
import metier.utilisateur.Professeur;

import controller.*;
import javafx.scene.input.KeyCode;
import vues.UserControl.mark.UserControlNoteStud;
import vues.login.LoginView;

/**
 *
 * @author ozsevik1
 */
public class MainView {
    
    @FXML
    BorderPane monBorderPane;
    
    @FXML
    Button addUserButton;
    
    @FXML
    Button markButton;
    
    @FXML
    Label displayUserIdentity;
    
    private ControllerUsers controller;
    
    public MainView(ControllerUsers controller) {
        this.controller = controller;
    }
    
    @FXML
    public void clickOnAcceuil(Event e) throws Exception {
        
        if(this.controller.getUserConnected() instanceof Etudiant || this.controller.getUserConnected() instanceof Professeur ){
            monBorderPane.setCenter(new UserControlMainStud(this.controller));
        } else if(this.controller.getUserConnected() instanceof Admin){
            monBorderPane.setCenter(new UserControlMainAdmin(this.controller.usersProperty()));
        }
         
    }
    
      
    @FXML
    public void clickOnNotes(Event e) throws Exception {
     
        if(this.controller.getUserConnected() instanceof Professeur)
            monBorderPane.setCenter(new UserControlNoteProf(controller)); 
        
        else
             monBorderPane.setCenter(new UserControlNoteStud(controller)); 
     
    }
    
    @FXML
    public void clickOnDisconnect(Event e) throws Exception {
        
        Parent root;
        LoginView loginView = new LoginView(controller);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/login/LoginView.fxml"));
        loader.setController(loginView);
        loader.setClassLoader(loginView.getClass().getClassLoader());
        root = loader.load();
        
        Stage mainWindow = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene s=new Scene(root, mainWindow.getWidth(), mainWindow.getHeight());
        mainWindow.setScene(s);
        mainWindow.setMinWidth(800);
        mainWindow.setMinHeight(500);
        mainWindow.show();
        
        s.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                try {
                    loginView.connexion();
                } catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      
    }
    
        
    @FXML
    public void clickOnEmploiDuTemps(Event e) throws Exception {
        
        monBorderPane.setCenter(new UserControlSchedule(controller));
        
    }
    
    @FXML
    public void clickOnGererUtilisateur(Event e) throws Exception {
     
        if(this.controller.getUserConnected() instanceof Admin){
            monBorderPane.setCenter(new UserControlAddUser(controller)); 
        }
    }
    
    public void setBasicsSettings(){
        
        if(!(this.controller.getUserConnected() instanceof Admin)){
            addUserButton.setVisible(false);
            try {
                monBorderPane.setCenter(new UserControlMainStud(this.controller));
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            markButton.setVisible(false);
            try {
                monBorderPane.setCenter(new UserControlMainAdmin(controller.usersProperty()));
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        displayUserIdentity.textProperty().bind(this.controller.getUserConnected().identifiantProperty());
        
    }
    
}
