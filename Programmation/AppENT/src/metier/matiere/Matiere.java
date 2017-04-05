/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import java.util.Objects;
import metier.utilisateur.Professeur;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class Matiere implements IMatiere{

    private final StringProperty nom = new SimpleStringProperty();
        @Override
        public StringProperty nomProperty() {
            return nom;
        }

    private final FloatProperty coefficient = new SimpleFloatProperty();
        @Override
        public FloatProperty coefficientProperty() {
            return coefficient;
        }

    private final ObjectProperty<Professeur> professeur = new SimpleObjectProperty<>();
        @Override
        public ObjectProperty professeurProperty() {
            return professeur;
        }

    private String color;

    Matiere(String nom, float coefficient, Professeur prof, String color) {

        this.setNom(nom);
        this.setCoefficient(coefficient);
        this.setProfesseur(prof);
        this.setColor(color);
    }

    private void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return this.getNom() + " : " + this.getProfesseur();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matiere other = (Matiere) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.coefficient, other.coefficient)) {
            return false;
        }
        if (!Objects.equals(this.professeur, other.professeur)) {
            return false;
        }
        return true;
    }


}
