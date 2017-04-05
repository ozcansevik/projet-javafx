/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import metier.groupe.IGroupe;
import metier.utilisateur.IProfesseur;
import metier.utilisateur.IUtilisateur;


public class XMLProfesseur extends XMLUtilisateur implements IProfesseur{

    public XMLProfesseur(IUtilisateur model){
        super(model);
        groupes = new SimpleListProperty<IGroupe>();
    }

    private final ListProperty<IGroupe> groupes;
        @Override public ListProperty<IGroupe> groupesProperty() { return ((IProfesseur)getModel()).groupesProperty();}
        @Override public ObservableList<IGroupe> getGroupes() { return IProfesseur.super.getGroupes();}
        @Override public void setGroupes(ObservableList<IGroupe> value) { IProfesseur.super.setGroupes(value);}
}
