/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.admin;

import controller.ControllerUsers;
import java.io.IOException;
import java.util.Date;
import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import metier.cours.Cours;
import metier.cours.FabriqueCours;
import metier.groupe.Groupe;
import metier.cours.ICours;
import metier.matiere.CommandMatiere;
import metier.utilisateur.Professeur;
import utilities.VueUtiles;

/**
 *
 * @author ozsevik1
 */
public class UserControlCaseEdit extends VBox {
    
    @FXML
    ComboBox matiere;

    @FXML
    public ComboBox professeur;
    
    private int positionX;
    private int positionY;
    
    private String date;
    
    public Cours lastCours;

    public UserControlCaseEdit(Cours c, ControllerUsers controller, ListProperty<CommandMatiere> commandMatiereProperty, Groupe groupSelected, int i, int j, String date) throws IOException{
       
       VueUtiles.chargerVue(this, "/vues/UserControl/admin/UserControlCaseEdit.fxml");
       
       positionX = i;
       positionY = j;
       this.date = date;
       this.lastCours = c;
       
       matiere.itemsProperty().bind(commandMatiereProperty);
       matiere.getSelectionModel().selectFirst();
       
       professeur.itemsProperty().bind(controller.getAllProfesseurPropertyByGroup(groupSelected));
       professeur.getSelectionModel().selectFirst();
       
       //this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    
    public int getPositionX(){
        return positionX;
    }
    
    public int getPositionY(){
        return positionY;
    }
    
    public ICours createCours(){
        return FabriqueCours.create((CommandMatiere)matiere.getValue(), (Professeur)professeur.getValue(), getPositionX(), getPositionY(), date);
    }
}
