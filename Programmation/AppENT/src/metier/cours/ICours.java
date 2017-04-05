/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.cours;

import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import metier.matiere.IMatiere;


public interface ICours {

    default public LocalDateTime getDateCours() { return dateCoursProperty().get();}
    default public void setDateCours(LocalDateTime value) { dateCoursProperty().set(value);}

    default public IMatiere getMatiere() { return matiereProperty().get();}
    default public void setMatiere(IMatiere value) { matiereProperty().set(value);}

    public ObjectProperty<LocalDateTime> dateCoursProperty();
    public ObjectProperty<IMatiere> matiereProperty();
}
