/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.cours;

import metier.matiere.Matiere;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import metier.matiere.IMatiere;


public class Cours implements ICours{

    private final ObjectProperty<LocalDateTime> dateCours = new SimpleObjectProperty<>();
        @Override
        public ObjectProperty dateCoursProperty() {
            return dateCours;
        }

    private final ObjectProperty<IMatiere> matiere = new SimpleObjectProperty<>();
        @Override
        public ObjectProperty matiereProperty() {
            return matiere;
        }

    Cours(LocalDateTime dateC, IMatiere matiere) {
        this.setDateCours(dateC);
        this.setMatiere(matiere);
    }

    @Override
    public boolean equals(Object o){

        if(o == this) return true;
        if(!(o instanceof Cours)) return false;

        return ((Cours)o).getDateCours().equals(this.getDateCours())
                && ((Cours)o).getMatiere().equals(this.getMatiere());
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = dateCours.get().format(formatter);

        return date.substring(11) + " vous avez un cours : " + getMatiere().getNom();
    }

}
