/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.homePage;

import controller.ControllerUsers;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import metier.cours.Cours;
import metier.cours.ICours;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import metier.note.INote;
import metier.note.Note;
import metier.utilisateur.Etudiant;
import metier.utilisateur.Professeur;
import metier.utilisateur.Utilisateur;
import utilities.VueUtiles;
import vues.UserControl.mark.ListNoteCell;

/**
 *
 * @author ozsevik1
 */
public class UserControlMainStud extends GridPane{
    
    @FXML
    Label dateLabel;
    
    @FXML
    Label markLabel;
    
    @FXML
    ListView listViewTodaySession;
    
    @FXML
    ListView listViewLastMark;
    
    private ControllerUsers controller;
    
    private String currentDate;
    
    private ObservableList<ICours> cours = FXCollections.observableArrayList();
    
    private ListProperty<ICours> coursAffiche = new SimpleListProperty<ICours>(cours);
    
     private ObservableList<INote> notes = FXCollections.observableArrayList();
    
      private ListProperty<INote> dernieresNote = new SimpleListProperty<INote>(notes);
    
    public UserControlMainStud(ControllerUsers controller) throws IOException{
        
        this.controller = controller;
        
        VueUtiles.chargerVue(this, "/vues/UserControl/homePage/UserControlMainStud.fxml");
        
        listViewTodaySession.itemsProperty().bind(coursAffiche);
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        currentDate = dateFormat.format(cal.getTime());
        currentDate = currentDate.substring(0, 11).concat("00:00"); // On enlève l'heure
        
        dateLabel.setText("Emploi du temps d'aujourd'hui (" + currentDate.substring(0, 10) + "):");
        
        setSettingsByUserConnected(controller.getUserConnected());
    }
    
    private void setSettingsByUserConnected(Utilisateur user){
        
        if(user instanceof Etudiant){
            Groupe g = controller.getControllerGroupes().findGroupByName(((Groupe)((Etudiant)user).getGroupe()).toString());
            cours.addAll(controller.getControllerGroupes().getCoursOfGroupByDate(g, currentDate));
            
            if(((Etudiant)controller.getUserConnected()).getNotes().size() > 5){
                int taille=((Etudiant)controller.getUserConnected()).getNotes().size();
                ArrayList<INote> lastMark = new ArrayList<INote>(((Etudiant)controller.getUserConnected()).getNotes().subList(taille-5, taille));
                notes.setAll(lastMark);
                listViewLastMark.itemsProperty().bind(dernieresNote);
            }else{
                listViewLastMark.itemsProperty().bind(((Etudiant)controller.getUserConnected()).notesProperty());
            }
            listViewLastMark.setCellFactory(new Callback<ListView<Note>, javafx.scene.control.ListCell<Note>>(){
                @Override
                public ListCell<Note> call(ListView<Note> listView)
                {
                    return new ListNoteCell();
                }
            });
        } else if(user instanceof Professeur){
            listViewLastMark.setVisible(false);
            markLabel.setVisible(false);
            ObservableList<IGroupe> groupProf = ((Professeur)user).getGroupes();
            
            for(Iterator<IGroupe> i = groupProf.iterator(); i.hasNext();){
                Groupe g = (Groupe) i.next();
                cours.addAll(controller.getControllerGroupes().getCoursOfGroupByDate(g, currentDate));
            }
            
            Collections.sort(cours, new Comparator<ICours>() {
                @Override
                 public int compare(ICours o1, ICours o2) {

                     return o1.getDateCours().toString().compareTo(o2.getDateCours().toString());

                }
            });
        }
    }
    
}
