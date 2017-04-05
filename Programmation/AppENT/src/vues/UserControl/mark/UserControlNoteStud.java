/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.mark;


import controller.ControllerUsers;
import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import metier.note.Note;
import metier.utilisateur.Etudiant;
import utilities.VueUtiles;



public class UserControlNoteStud extends GridPane{

    @FXML
    ListView listNote;

    @FXML
    Label  textMoy;

    private ControllerUsers controller ;
    private Etudiant etudiantConnecte;


    public UserControlNoteStud(ControllerUsers controllerUsers) throws IOException{


       controller=controllerUsers;

       VueUtiles.chargerVue(this, "/vues/UserControl/mark/UserControlNoteStud.fxml");

       etudiantConnecte=(Etudiant)controller.getUserConnected();

       listNote.itemsProperty().bind(etudiantConnecte.notesProperty());

       listNote.setCellFactory(new Callback<ListView<Note>, javafx.scene.control.ListCell<Note>>()
        {
            @Override
            public ListCell<Note> call(ListView<Note> listView)
            {
                return new ListNoteCell();
            }
        });

       textMoy.textProperty().bind(Bindings.convert((etudiantConnecte.moyGenProperty())));


    }

}
