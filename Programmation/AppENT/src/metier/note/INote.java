/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.note;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import metier.matiere.IMatiere;


public interface INote {

    default public int getNote() { return noteProperty().get();}
    default public void setNote(int value) { noteProperty().set(value);}

    default public IMatiere getMatiere() { return matiereProperty().get();}
    default public void setMatiere(IMatiere value) { matiereProperty().set(value);}

    default public int getCoefficient() { return coefficientProperty().get();}
    default public void setCoefficient(int value) { coefficientProperty().set(value);}

    public IntegerProperty noteProperty();
    public ObjectProperty<IMatiere> matiereProperty();
    public IntegerProperty coefficientProperty();
}
