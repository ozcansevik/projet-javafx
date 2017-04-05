/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.admin;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import metier.utilisateur.Utilisateur;

import controller.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import utilities.VueUtiles;


/**
 *
 * @author ozcan
 */
public class UserControlAddUser extends GridPane{
    
    
    @FXML
    ListView listeGroupe;
    
    @FXML
    ComboBox userSelected;
    
    @FXML
    ComboBox groupSelected;
    
    @FXML
    TextField nameField;
    
    @FXML
    TextField surnameField;
    
    @FXML
    Button addGroupButton;
    
    @FXML
    Button validateButton;
    
    @FXML
    Label groupLabel;
    
    @FXML
    Label errorLabel;
    
    private ControllerUsers controller;
    
    private ObservableList<IGroupe> listGroupProf = FXCollections.observableArrayList() ;
    
    private final ListProperty<IGroupe> groupProf = new SimpleListProperty<>(listGroupProf);

        public ObservableList getGroupProf() {
            return groupProf.get();
        }

        public void setGroupProf(ObservableList value) {
            groupProf.set(value);
        }

        public ListProperty groupProfProperty() {
            return groupProf;
        }
    
    

    public UserControlAddUser(ControllerUsers controller) throws IOException {
       VueUtiles.chargerVue(this, "/vues/UserControl/admin/UserControlAddUser.fxml");
       
       setVisibilityGroup(false);
       
       this.controller = controller;
       userSelected.getItems().addAll("Etudiant", "Professeur");
       userSelected.getSelectionModel().selectFirst();
       
       groupSelected.itemsProperty().bind(controller.getControllerGroupes().groupeProperty());
       groupSelected.getSelectionModel().selectFirst();
       
       listeGroupe.itemsProperty().bind(this.groupProfProperty());
       
       userSelected.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if(userSelected.getValue().toString().equals("Etudiant")){
                    setVisibilityGroup(false);
                } else if(userSelected.getValue().toString().equals("Professeur")){
                    setVisibilityGroup(true);
                }
            }
        });
       
       errorLabel.setTextFill(Color.web("#0076a3"));
       errorLabel.setText("");
    }
    
    private void setVisibilityGroup(boolean b){
        addGroupButton.setVisible(b);
        groupSelected.setVisible(b);
        listeGroupe.setVisible(b);
        groupLabel.setVisible(b);
    }
    
    private void reinitializeField(){
        listGroupProf.clear();
        nameField.clear();
        surnameField.clear();
        errorLabel.setText("");
    }
    
    @FXML
    public void clickOnAddGroup(Event e) throws Exception {
     
        listGroupProf.add(controller.getControllerGroupes().findGroupByName(groupSelected.getValue().toString()));
        
    }
    
    @FXML
    public void clickOnValidate(Event e) throws Exception {
     
        if(userSelected.getValue().toString().equals("Etudiant")){
            try{
                controller.addEtudiant(nameField.getText(), surnameField.getText());
                reinitializeField();
            } catch(IOException ex){
                errorLabel.setText("Erreur : Le nom ou le prenom doivent être renseignés !");
            }
        } else if(userSelected.getValue().toString().equals("Professeur")){
            try {
                controller.addProfesseur(nameField.getText(), surnameField.getText(), listGroupProf);
                reinitializeField();
            } catch(IOException ex){
                errorLabel.setText("Erreur : Le nom, le prenom ou le groupe doivent être renseignés !");
            }
        }
        
        
    }
    
    
    
    
}
