/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import metier.note.INote;
import metier.note.Note;


public interface IEtudiant {

    default public ObservableList<INote> getNotes() { return notesProperty().get();}
    default public void setNotes(ObservableList<INote> value) {notesProperty().set(value);}

    default public IGroupe getGroupe() {return groupeProperty().get();}
    default public void setGroupe(IGroupe value) { groupeProperty().set(value);}

    default public int getMoyGen(){ return moyGenProperty().get();}
    default public void setMoyGen(int value){ moyGenProperty().set(value);}

    public ListProperty<INote> notesProperty();
    public ObjectProperty<IGroupe> groupeProperty();
    public IntegerProperty moyGenProperty();
}
