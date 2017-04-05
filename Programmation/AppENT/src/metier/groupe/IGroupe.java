/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.groupe;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import metier.cours.ICours;
import metier.utilisateur.IEtudiant;


public interface IGroupe {

    default public int getNumero() { return numeroProperty().get();}
    default public void setNumero(int value) { numeroProperty().set(value);}

    default public int getAnnee() { return anneeProperty().get();}
    default public void setAnnee(int value) { anneeProperty().set(value);}

    default public ObservableList<IEtudiant> getEtudiants() { return etudiantsProperty().get();}
    default public void setEtudiants(ObservableList<IEtudiant> value) { etudiantsProperty().set(value);}

    default public ObservableList<ICours> getCours() { return coursProperty().get();}
    default public void setCours(ObservableList<ICours> value) { coursProperty().set(value);}

    public IntegerProperty numeroProperty();
    public IntegerProperty anneeProperty();
    public ListProperty<IEtudiant> etudiantsProperty();
    public ListProperty<ICours> coursProperty();
}
