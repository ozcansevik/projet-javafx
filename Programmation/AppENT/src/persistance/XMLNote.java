/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import metier.matiere.IMatiere;
import metier.note.INote;


public class XMLNote implements INote, Serializable{

    public XMLNote(INote model){
        this.model = model;
        note = new SimpleIntegerProperty(model.getNote());
        matiere = new SimpleObjectProperty<IMatiere>(new XMLMatiere(model.getMatiere()));
        coefficient = new SimpleIntegerProperty(model.getCoefficient());
    }

    private transient INote model;

    public INote getModel(){
        return model;
    }

    private final IntegerProperty note;
        @Override public IntegerProperty noteProperty() { return model.noteProperty();}
        @Override public int getNote(){ return INote.super.getNote();}
        @Override public void setNote(int value) { INote.super.setNote(value);}

    private final ObjectProperty<IMatiere> matiere;
        @Override public ObjectProperty<IMatiere> matiereProperty() { return model.matiereProperty();}
        @Override public IMatiere getMatiere(){ return INote.super.getMatiere();}
        @Override public void setMatiere(IMatiere value) { INote.super.setMatiere(value);}

    private final IntegerProperty coefficient;
        @Override public IntegerProperty coefficientProperty() { return model.coefficientProperty();}
        @Override public int getCoefficient(){ return INote.super.getCoefficient();}
        @Override public void setCoefficient(int value) { INote.super.setCoefficient(value);}
}
