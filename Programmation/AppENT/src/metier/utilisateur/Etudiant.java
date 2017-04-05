/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import java.util.Collections;
import java.util.Comparator;
import javafx.beans.property.IntegerProperty;
import metier.utilisateur.Utilisateur;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.note.Note;
import metier.groupe.Groupe;
import metier.note.INote;


public class Etudiant extends Utilisateur implements IEtudiant{

    private ObservableList<INote> listNotes = FXCollections.observableArrayList();

    private final ListProperty<INote> notes = new SimpleListProperty<>(listNotes);
        @Override
        public ListProperty<INote> notesProperty() {return notes;}


    private final ObjectProperty<Groupe> groupe = new SimpleObjectProperty<>();
        @Override
        public ObjectProperty groupeProperty() {
            return groupe;
        }

     private final IntegerProperty moyGen = new SimpleIntegerProperty();
        @Override
        public IntegerProperty moyGenProperty() {
            return moyGen;
        }

    Etudiant(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public String toString(){
        return this.getNom() + " " + this.getPrenom() + " -> Groupe " + this.getGroupe().getNumero();
    }

    public void calculerMoyenne(){
        int sommeCoeff=0;
        this.setMoyGen(0);

        for(int i=0; i<this.getNotes().size();i++){
            this.setMoyGen((this.getMoyGen())+((this.getNotes().get(i).getCoefficient())*(this.getNotes().get(i).getNote())));
            sommeCoeff = sommeCoeff + this.getNotes().get(i).getCoefficient();
        }
        this.setMoyGen((this.getMoyGen())/sommeCoeff);
    }
}
