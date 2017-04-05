/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import metier.note.INote;
import metier.utilisateur.IEtudiant;
import metier.utilisateur.IUtilisateur;
import javafx.collections.ObservableList;
import metier.groupe.IGroupe;


public class XMLEtudiant extends XMLUtilisateur implements IEtudiant{

    public XMLEtudiant(IUtilisateur model){
        super(model);
        notes = new SimpleListProperty<INote>(((IEtudiant)model).getNotes());
        groupe = new SimpleObjectProperty<>(new XMLGroupe(((IEtudiant)model).getGroupe()));
        moyGen = new SimpleIntegerProperty(((IEtudiant)model).getMoyGen());
    }

    private final ListProperty<INote> notes;
        @Override public ListProperty<INote> notesProperty() { return ((IEtudiant)getModel()).notesProperty();}
        @Override public ObservableList<INote> getNotes() { return IEtudiant.super.getNotes();}
        @Override public void setNotes(ObservableList<INote> value) { IEtudiant.super.setNotes(value);}

    private final ObjectProperty<IGroupe> groupe;
        @Override public ObjectProperty<IGroupe> groupeProperty() { return ((IEtudiant)getModel()).groupeProperty();}
        @Override public IGroupe getGroupe() { return IEtudiant.super.getGroupe();}
        @Override public void setGroupe(IGroupe value) { IEtudiant.super.setGroupe(value);}

    private final IntegerProperty moyGen;
        @Override public IntegerProperty moyGenProperty() { return ((IEtudiant)getModel()).moyGenProperty();}
        @Override public int getMoyGen() { return IEtudiant.super.getMoyGen();}
        @Override public void setMoyGen(int value) { IEtudiant.super.setMoyGen(value);}
}
