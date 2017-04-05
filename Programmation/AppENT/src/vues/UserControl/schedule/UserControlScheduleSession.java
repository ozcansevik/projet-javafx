/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.schedule;

import controller.ControllerUsers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import metier.groupe.Groupe;
import metier.cours.Cours;
import metier.matiere.Matiere;
import metier.matiere.CommandMatiere;
import metier.utilisateur.*;
import utilities.VueUtiles;
import vues.UserControl.admin.UserControlCaseEdit;
import java.util.List;


public class UserControlScheduleSession extends GridPane{

    @FXML
    Label matiereLabel;

    @FXML
    Label profOrGroupLabel;

    public Cours cours;

    public UserControlScheduleSession(Cours cours, Utilisateur connected) throws IOException {
        VueUtiles.chargerVue(this, "/vues/UserControl/schedule/UserControlScheduleSession.fxml");

        setTextCours(cours, connected);
    }

    public void resetText(){
        this.cours = null;
        matiereLabel.setText("");
        profOrGroupLabel.setText("");
        this.setStyle("-fx-background-color:#C0C0C0;");
    }

    public void setTextCours(Cours c, Utilisateur connected){
        if(c != null){
            this.cours = c;
            matiereLabel.setText(c.getMatiere().getNom());
            this.setStyle("-fx-background-color:" + ((Matiere)c.getMatiere()).getColor()+";");
            if(connected instanceof Etudiant){
                profOrGroupLabel.setText(((Professeur)c.getMatiere().getProfesseur()).getNom());
            } else if(connected instanceof Professeur){

                profOrGroupLabel.setText(((Professeur)connected).findGroupByCours(c).toString());
            } else if(connected instanceof Admin){
                profOrGroupLabel.setText(((Professeur)c.getMatiere().getProfesseur()).getNom());
            }
        } else {
            resetText();
        }
    }
}
