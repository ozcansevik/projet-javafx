/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.schedule;


import controller.ControllerUsers;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import metier.cours.Cours;
import metier.cours.ICours;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import metier.matiere.*;
import metier.utilisateur.Admin;
import metier.utilisateur.Etudiant;
import metier.utilisateur.Professeur;
import utilities.VueUtiles;
import vues.UserControl.admin.UserControlCaseEdit;


public class UserControlSchedule extends GridPane {

    @FXML
    GridPane maGrid;

    @FXML
    ComboBox groupSelected;

    @FXML
    Text debutSemaine;

    @FXML
    Text finSemaine;

    @FXML
    Text TextMod;

    @FXML
    Button buttonEdit;

    @FXML
    Button buttonDelete;

    private ControllerUsers controller;

    private List<UserControlCaseEdit> listCaseEdit = new ArrayList<>();

    private ObservableList<CommandMatiere> commandMatiere = FXCollections.observableArrayList();

    private ListProperty<CommandMatiere> commandMatiereProperty = new SimpleListProperty<>(commandMatiere);

    private String dateD = "2017-01-23 00:00", dateF = "2017-01-28 00:00";

    private StringProperty mod = new SimpleStringProperty("Mod choisit : Aucun");

    public UserControlSchedule(ControllerUsers controller) throws IOException{

       this.controller = controller;

       this.setStyle("-fx-background-color:#C0C0C0;");

       commandMatiere.add(new CommandAlgorithme());
       commandMatiere.add(new CommandExpression());
       commandMatiere.add(new CommandPoo());

       VueUtiles.chargerVue(this, "/vues/UserControl/schedule/UserControlSchedule.fxml");


       if(!(controller.getUserConnected() instanceof Admin)){
           buttonEdit.setVisible(false);
           buttonDelete.setVisible(false);
           groupSelected.setVisible(false);
           TextMod.setVisible(false);

       }

       TextMod.textProperty().bind(mod);

       groupSelected.itemsProperty().bind(controller.getControllerGroupes().groupeProperty());
       groupSelected.getSelectionModel().selectFirst();

       groupSelected.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Groupe>() {

            @Override
            public void changed(ObservableValue<? extends Groupe> arg0, Groupe arg1, Groupe arg2) {
                cancelEditionMod();
            }
       });

       afficherDate();

       this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

       loadCours(true);
    }

    private void loadCours(boolean isFirstLoad){

        int i,j;
        ObservableList<ICours> cours = FXCollections.observableArrayList();
        // On prends la liste du cours sélectionner, ou celle du groupe de l'etudiant, ou celle des groupes du prof
        if(controller.getUserConnected() instanceof Admin){
            cours = ((Groupe)groupSelected.getValue()).getCoursByDateForWeek(dateD);
        }else if(controller.getUserConnected() instanceof Etudiant){
            cours = ((Groupe)((Etudiant)controller.getUserConnected()).getGroupe()).getCoursByDateForWeek(dateD);
        }else if(controller.getUserConnected() instanceof Professeur){
            cours = FXCollections.observableArrayList();
            for(Iterator<IGroupe> it = ((Professeur)controller.getUserConnected()).getGroupes().iterator(); it.hasNext();){
                Groupe g = (Groupe)it.next();
                cours.addAll(g.getCoursByProfAndDate((Professeur)controller.getUserConnected(), dateD));
            }
        }

        //Selon sa date et son heure, on attache le cours à un affichage.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateD, formatter);

        for(i=1; i<7 ;i++){ //colonnes
           for(j=1; j<8 ;j++){ //lignes
               switch(j){
                    case 1 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(8)), isFirstLoad); break;
                    case 2 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(10)), isFirstLoad); break;
                    case 3 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(12).plusMinutes(30)), isFirstLoad); break;
                    case 4 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(13).plusMinutes(30)), isFirstLoad); break;
                    case 5 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(15).plusMinutes(30)), isFirstLoad); break;
                    case 6 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(17).plusMinutes(30)), isFirstLoad); break;
                    case 7 : addScheduleSession(i, j, getCoursByDate(cours, dateTime.plusDays(i-1).plusHours(19).plusMinutes(30)), isFirstLoad); break;
                }
           }
       }
    }

    private void addScheduleSession(int i, int j, Cours c, boolean isFirstLoad){
        if(!isFirstLoad){
            Node n = getNodeByColumnAndRow(i, j);
            ((UserControlScheduleSession)n).setTextCours(c, controller.getUserConnected());
        } else {
            try {
                UserControlScheduleSession u = new UserControlScheduleSession(c, controller.getUserConnected());
                if(controller.getUserConnected() instanceof Admin){
                    setEventOnMousePressed(u, i, j);
                }
                maGrid.add(u,i,j);
            } catch (IOException ex) {
                Logger.getLogger(UserControlSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void clickOnEdit(Event e) throws Exception{
        if(!(controller.getUserConnected() instanceof Admin)){
            return;
        }
        if(!mod.get().substring(14).equals("Edition")){
            mod.set("Mod choisit : Edition");
        } else {
            UserControlScheduleSession uSc;
            for(Iterator<UserControlCaseEdit> i = this.listCaseEdit.iterator(); i.hasNext();){
                UserControlCaseEdit u = i.next();
                if(u.professeur.getValue() != null){
                    controller.getControllerGroupes().findGroupByName(((Groupe)groupSelected.getValue()).toString()).removeCours(u.lastCours);
                    Cours c = (Cours)u.createCours();
                    controller.getControllerGroupes().addCoursToGroup((Groupe)groupSelected.getValue(), c);
                    uSc = new UserControlScheduleSession(c, controller.getUserConnected());
                } else {
                    uSc = new UserControlScheduleSession(u.lastCours, controller.getUserConnected());
                }
                setEventOnMousePressed(uSc, u.getPositionX(), u.getPositionY());
                maGrid.add(uSc, u.getPositionX(), u.getPositionY());
                maGrid.getChildren().remove(u);
            }
            listCaseEdit.clear();
        }
    }

    @FXML
    public void clickOnDelete(Event e) throws Exception{
        if(!mod.get().substring(14).equals("Suppression")){
            mod.set("Mod choisit : Suppression");
            cancelEditionMod();
        }
    }

    @FXML
    public void clickOnPreviousDate(Event e) throws Exception {

        dateD = previousDate(dateD);
        dateF = previousDate(dateF);
        afficherDate();
        cancelEditionMod();
    }

    private String previousDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        dateTime = dateTime.minusDays(7);
        return dateTime.format(formatter);
    }

    @FXML
    public void clickOnNextDate(Event e) throws Exception {
        dateD = nextDate(dateD);
        dateF = nextDate(dateF);
        afficherDate();
        cancelEditionMod();
    }

    private String nextDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        dateTime = dateTime.plusDays(7);
        return dateTime.format(formatter);
    }

    private void afficherDate(){
       debutSemaine.setText(dateD.substring(8,10) + "/" + dateD.substring(5,7)+ "/" + dateD.substring(0,4));
       finSemaine.setText(dateF.substring(8,10) + "/" + dateF.substring(5,7)+ "/" + dateF.substring(0,4));
    }

    private void cancelEditionMod(){
        for(Iterator<UserControlCaseEdit> i = this.listCaseEdit.iterator(); i.hasNext();){
            UserControlCaseEdit u = i.next();
            try {
                UserControlScheduleSession uSc = new UserControlScheduleSession(null, controller.getUserConnected());
                setEventOnMousePressed(uSc, u.getPositionX(), u.getPositionY());
                maGrid.add(uSc, u.getPositionX(), u.getPositionY());
                maGrid.getChildren().remove(u);
            } catch (IOException ex) {
                Logger.getLogger(UserControlSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        listCaseEdit.clear();
        loadCours(false);
    }

    private Cours getCoursByDate(ObservableList<ICours> cours, LocalDateTime date){
        for(Iterator<ICours> i = cours.iterator(); i.hasNext();){
            Cours c = (Cours)i.next();
            if(c.getDateCours().equals(date)){
                return c;
            }
        }
        return null;
    }

    public Node getNodeByColumnAndRow (int column, int row) {
        Node result = null;
        ObservableList<Node> childrens = maGrid.getChildren();

        for (Node node : childrens) {
            if(maGrid.getRowIndex(node) == row && maGrid.getColumnIndex(node) == column && node instanceof UserControlScheduleSession) {
                result = node;
                break;
            }
        }

        return result;
    }

    private void setEventOnMousePressed(UserControlScheduleSession u, int i, int j){
        u.setOnMousePressed(ev -> {
            if(mod.get().substring(14).equals("Edition")){
                try {
                    UserControlCaseEdit uC = new UserControlCaseEdit(u.cours, controller, commandMatiereProperty, (Groupe)groupSelected.getValue(), i, j, dateD);
                    uC.setAlignment(Pos.CENTER);
                    maGrid.add(uC, i, j);
                    listCaseEdit.add(uC);
                    maGrid.getChildren().remove(u);
                } catch (IOException ex) {
                    Logger.getLogger(UserControlSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(mod.get().substring(14).equals("Suppression")){
                controller.getControllerGroupes().findGroupByName(((Groupe)groupSelected.getValue()).toString()).removeCours(u.cours);
                u.resetText();
            }
        });
    }
}
