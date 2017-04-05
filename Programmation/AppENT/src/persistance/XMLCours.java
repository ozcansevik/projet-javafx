/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.io.Serializable;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import metier.cours.ICours;
import metier.matiere.IMatiere;


public class XMLCours implements ICours, Serializable{

    public XMLCours(ICours model){
        this.model = model;
        dateCours = new SimpleObjectProperty<>(model.getDateCours());
        matiere = new SimpleObjectProperty<>(new XMLMatiere(model.getMatiere()));
    }

    private transient ICours model;

    public ICours getModel(){
        return model;
    }

    private ObjectProperty<LocalDateTime> dateCours;
        @Override public ObjectProperty<LocalDateTime> dateCoursProperty() { return model.dateCoursProperty();}
        @Override public LocalDateTime getDateCours() { return ICours.super.getDateCours();}
        @Override public void setDateCours(LocalDateTime value) { ICours.super.setDateCours(value);}

    private ObjectProperty<IMatiere> matiere;
        @Override public ObjectProperty<IMatiere> matiereProperty() { return model.matiereProperty();}
        @Override public IMatiere getMatiere() { return ICours.super.getMatiere();}
        @Override public void setMatiere(IMatiere value) { ICours.super.setMatiere(value);}

}
