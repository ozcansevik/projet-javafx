/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.Serializable;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import metier.cours.ICours;
import metier.groupe.IGroupe;
import metier.utilisateur.IEtudiant;


public class XMLGroupe implements IGroupe, Serializable{

    public XMLGroupe(IGroupe model){
        this.model = model;
        numero = new SimpleIntegerProperty(model.getNumero());
        annee = new SimpleIntegerProperty(model.getAnnee());
        etudiants = new SimpleListProperty<>(model.getEtudiants());
        cours = new SimpleListProperty<ICours>(model.getCours());
    }

    private transient IGroupe model;

    public IGroupe getModel(){
        return model;
    }

    private final IntegerProperty numero;
        @Override public IntegerProperty numeroProperty() { return model.numeroProperty();}
        @Override public int getNumero() { return IGroupe.super.getNumero();}
        @Override public void setNumero(int value) { IGroupe.super.setNumero(value);}

    private final IntegerProperty annee;
        @Override public IntegerProperty anneeProperty() { return model.anneeProperty();}
        @Override public int getAnnee() { return IGroupe.super.getAnnee();}
        @Override public void setAnnee(int value) { IGroupe.super.setAnnee(value);}

    private final ListProperty<IEtudiant> etudiants;
        @Override public ListProperty<IEtudiant> etudiantsProperty() { return model.etudiantsProperty();}
        @Override public ObservableList<IEtudiant> getEtudiants() { return IGroupe.super.getEtudiants();}
        @Override public void setEtudiants(ObservableList<IEtudiant> value) { IGroupe.super.setEtudiants(value);}

    private final ListProperty<ICours> cours;
        @Override public ListProperty<ICours> coursProperty() { return model.coursProperty();}
        @Override public ObservableList<ICours> getCours() { return IGroupe.super.getCours();}
        @Override public void setCours(ObservableList<ICours> value) { IGroupe.super.setCours(value);}
}
