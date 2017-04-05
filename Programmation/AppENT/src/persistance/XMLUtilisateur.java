/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import metier.utilisateur.IUtilisateur;


public class XMLUtilisateur implements IUtilisateur, Serializable{

    public XMLUtilisateur(IUtilisateur model){
        this.model = model;
        nom = new SimpleStringProperty(model.getNom());
        prenom = new SimpleStringProperty(model.getPrenom());
        identifiant = new SimpleStringProperty(model.getIdentifiant());
        mdp = new SimpleStringProperty(model.getMdp());
    }

    private transient IUtilisateur model;

    public IUtilisateur getModel() {
        return model;
    }

    private final StringProperty nom;
        @Override public StringProperty nomProperty() { return model.nomProperty();}
        @Override public String getNom() {return IUtilisateur.super.getNom();}
        @Override public void setNom(String value) { IUtilisateur.super.setNom(value);}

    private final StringProperty prenom;
        @Override public StringProperty prenomProperty() { return model.prenomProperty();}
        @Override public String getPrenom() {return IUtilisateur.super.getPrenom();}
        @Override public void setPrenom(String value) { IUtilisateur.super.setPrenom(value);}

    private final StringProperty identifiant;
        @Override public StringProperty identifiantProperty() { return model.identifiantProperty();}
        @Override public String getIdentifiant() {return IUtilisateur.super.getIdentifiant();}
        @Override public void setIdentifiant(String value) { IUtilisateur.super.setIdentifiant(value);}

    private final StringProperty mdp;
        @Override public StringProperty mdpProperty() { return model.mdpProperty();}
        @Override public String getMdp() {return IUtilisateur.super.getMdp();}
        @Override public void setMdp(String value) { IUtilisateur.super.setMdp(value);}
}
