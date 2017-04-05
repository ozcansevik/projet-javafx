/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import metier.utilisateur.IProfesseur;


public interface IMatiere {

    default public String getNom() { return nomProperty().get();}
    default public void setNom(String value) { nomProperty().set(value);}

    default public float getCoefficient() { return coefficientProperty().get();}
    default public void setCoefficient(float value) { coefficientProperty().set(value);}

    default public IProfesseur getProfesseur() { return professeurProperty().get();}
    default public void setProfesseur(IProfesseur value) { professeurProperty().set(value);}

    public StringProperty nomProperty();
    public FloatProperty coefficientProperty();
    public ObjectProperty<IProfesseur> professeurProperty();
}
