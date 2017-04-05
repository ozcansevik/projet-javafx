/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.note;

import java.util.Collections;
import java.util.Objects;
import metier.matiere.Matiere;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Note implements INote{

    private final IntegerProperty note = new SimpleIntegerProperty();
        @Override
        public IntegerProperty noteProperty() {return note;}

    private final ObjectProperty<Matiere> matiere = new SimpleObjectProperty<>();
        @Override
        public ObjectProperty matiereProperty() {return matiere;}

    private final IntegerProperty coefficient = new SimpleIntegerProperty();
        @Override
        public IntegerProperty coefficientProperty() {
            return coefficient;
        }

    Note(int note, Matiere matiere,int coefficient) {

        this.setNote(note);
        this.setMatiere(matiere);
        this.setCoefficient(coefficient);

    }

    @Override
    public String toString() {
        return "Note : " + getNote() + " Coefficient : " + getCoefficient() + " Matiere : " + getMatiere();
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
        final Note other = (Note) obj;
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.matiere, other.matiere)) {
            return false;
        }
        if (!Objects.equals(this.coefficient, other.coefficient)) {
            return false;
        }
        return true;
    }



}
