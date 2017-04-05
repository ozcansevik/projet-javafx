/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class Utilisateur implements IUtilisateur{

    private final StringProperty nom = new SimpleStringProperty();
        @Override
        public StringProperty nomProperty() {return nom;}

    private final StringProperty prenom = new SimpleStringProperty();
        @Override
        public StringProperty prenomProperty() {return prenom;}

    private final StringProperty identifiant = new SimpleStringProperty();
        @Override
        public StringProperty identifiantProperty() {return identifiant;}

    private final StringProperty mdp = new SimpleStringProperty();
        @Override
        public StringProperty mdpProperty() {return mdp;}

    Utilisateur(String nom, String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setIdentifiant(prenom.toLowerCase().substring(0, 2).concat(nom.toLowerCase()).concat("1"));
        this.setMdp(nom.toUpperCase().concat("1"));
    }

    @Override
    public String toString(){
        return this.getNom() + " " + this.getPrenom();
    }

    public boolean compareLogin(String identifiant, String mdp){
        return (this.getIdentifiant().equals(identifiant) && this.getMdp().equals(mdp));
    }

    public void modifyIdentifiant(Integer num){
        this.setIdentifiant(this.getIdentifiant().substring(0, this.getIdentifiant().length()-1).concat(num.toString()));
    }

    public void modifyMdp(Integer num){
        this.setMdp(this.getMdp().substring(0, this.getMdp().length()-1).concat(num.toString()));
    }

}
