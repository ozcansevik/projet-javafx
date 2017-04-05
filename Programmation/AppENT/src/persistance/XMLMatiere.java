/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.Serializable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import metier.matiere.IMatiere;
import metier.utilisateur.IProfesseur;


public class XMLMatiere implements IMatiere, Serializable{

    public XMLMatiere(IMatiere model){
        this.model = model;
        nom = new SimpleStringProperty(model.getNom());
        coefficient = new SimpleFloatProperty(model.getCoefficient());
        professeur = new SimpleObjectProperty<IProfesseur>(model.getProfesseur());
    }

    private transient IMatiere model;

    public IMatiere getModel(){
        return model;
    }

    private final StringProperty nom;
        @Override public StringProperty nomProperty() { return model.nomProperty();}
        @Override public String getNom() { return IMatiere.super.getNom();}
        @Override public void setNom(String value) { IMatiere.super.setNom(value);}

    private final FloatProperty coefficient;
        @Override public FloatProperty coefficientProperty() { return model.coefficientProperty();}
        @Override public float getCoefficient() { return IMatiere.super.getCoefficient();}
        @Override public void setCoefficient(float value) { IMatiere.super.setCoefficient(value);}

    private final ObjectProperty<IProfesseur> professeur;
        @Override public ObjectProperty<IProfesseur> professeurProperty() { return model.professeurProperty();}
        @Override public IProfesseur getProfesseur() { return IMatiere.super.getProfesseur();}
        @Override public void setProfesseur(IProfesseur value) { IMatiere.super.setProfesseur(value);}

}
