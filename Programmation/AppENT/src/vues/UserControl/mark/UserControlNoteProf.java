/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.mark;

import controller.ControllerUsers;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import metier.note.FabriqueNote;
import metier.groupe.Groupe;
import metier.note.Note;
import metier.matiere.FabriqueMatiere;
import metier.matiere.IMatiere;
import metier.matiere.Matiere;
import metier.note.INote;
import metier.utilisateur.Etudiant;
import metier.utilisateur.Professeur;
import utilities.VueUtiles;


public class UserControlNoteProf extends GridPane {

    @FXML
    private GridPane maGrid;

    @FXML
    private ListView listEleveDuProf;

    @FXML
    private ListView listNoteEleveSelectionne;

    @FXML
    private ComboBox listMatiereDuProf;

     @FXML
    private Button bouttonAjouter;

    @FXML
    private Button bouttonSupprimer;

    @FXML
    private Button bouttonAjouterNote;

    @FXML
    private TextField textNote;

    @FXML
    private TextField textCoefficient;

    @FXML
    private VBox vboxMatiereCoeff;

    @FXML
    private HBox hboxTextButton;


    private Etudiant etudiantSelectionne;
    private Note noteSelectionnee;
    private Professeur profConnecte;


    private ObservableList<IMatiere> listMatieresDuProf = FXCollections.observableArrayList() ;

    private final ListProperty<IMatiere> matieresDuProf = new SimpleListProperty<>(listMatieresDuProf);

        public ObservableList getGroupes() {
            return matieresDuProf.get();
        }

        private void setGroupes(ObservableList value) {
            matieresDuProf.set(value);
        }

        public ListProperty groupesProperty() {
            return matieresDuProf;
        }

    private ObservableList<INote> listNotesEtudiantByMatiereProf = FXCollections.observableArrayList() ;


    private final ListProperty<INote> notesEtudiantByMatiereProf = new SimpleListProperty<>(listNotesEtudiantByMatiereProf);

        public ObservableList<INote> getListNoteEtudiantByMatiereProf() {
            return notesEtudiantByMatiereProf.get();
        }

        public void setListNoteEtudiantByMatiereProf(ObservableList<INote> value) {
            notesEtudiantByMatiereProf.set(value);
        }

        public ListProperty<INote> listNoteEtudiantByMatiereProfProperty() {
            return notesEtudiantByMatiereProf;
        }



    private ControllerUsers controller ;

    public UserControlNoteProf(ControllerUsers controllerUsers) throws IOException{

        controller=controllerUsers;
        VueUtiles.chargerVue(this, "/vues/UserControl/mark/UserControlNoteProf.fxml");

        bouttonSupprimer.setVisible(false);
        changerVisibilite(false);


       profConnecte = (Professeur) controller.getUserConnected();
       listEleveDuProf.itemsProperty().bind(profConnecte.getAllEtudiants());

       matieresDuProf.setValue(FabriqueMatiere.getProgramme().matieresProperty().filtered(new Predicate<Matiere>(){
                    @Override
                    public boolean test(Matiere m) {

                            if(m.getProfesseur().equals(profConnecte)) {
                                return true;
                            }
                            return false;
                    }

       }));

      listMatiereDuProf.itemsProperty().bind(matieresDuProf);

       listEleveDuProf.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Etudiant>() {

            @Override
            public void changed(ObservableValue<? extends Etudiant> observable, Etudiant oldValue, Etudiant newValue) {

                etudiantSelectionne=newValue;

                listNoteEtudiantByMatiereProfProperty().setValue(etudiantSelectionne.notesProperty().filtered(new Predicate<INote>(){
                    @Override
                    public boolean test(INote n) {
                        for(int i=0;i<matieresDuProf.size();i++){
                            if (n.getMatiere().equals(matieresDuProf.get(i)))
                                return true;
                        }
                        return false;
                    }
                }));


              listNoteEleveSelectionne.itemsProperty().bind(listNoteEtudiantByMatiereProfProperty());
       }});

       listNoteEleveSelectionne.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {
           @Override
            public void changed(ObservableValue<? extends Note> observable, Note oldValue, Note newValue) {
                noteSelectionnee=newValue;
                bouttonSupprimer.setVisible(true);
                changerVisibilite(false);
            }});

    }

    void changerVisibilite(boolean b){
       vboxMatiereCoeff.setVisible(b);
       hboxTextButton.setVisible(b);

    }

    @FXML
    public void actionAjouter(Event e) throws Exception {
           changerVisibilite(true);
    }


    @FXML
    public void actionSupprimer(Event e) throws Exception {

          etudiantSelectionne.getNotes().remove(noteSelectionnee);
    }

    @FXML
    public void actionAjouterNote(Event e) throws Exception {
        if(listMatiereDuProf.getValue() == null){
            return;
        }
        Note n = FabriqueNote.create(Integer.parseInt(textNote.getText()),Integer.parseInt(textCoefficient.getText()),profConnecte,(Matiere) listMatiereDuProf.getValue());
        etudiantSelectionne.getNotes().add(n);

        etudiantSelectionne.calculerMoyenne();

        Collections.sort(etudiantSelectionne.getNotes(), new Comparator<INote>() {
            @Override
            public int compare(INote o1, INote o2) {

                return o1.getMatiere().toString().compareTo(o2.getMatiere().toString());

            }
        });


    }

}
