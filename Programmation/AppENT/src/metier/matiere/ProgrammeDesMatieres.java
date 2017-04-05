/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.matiere;

import java.util.Iterator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ProgrammeDesMatieres {

    private static ObservableList<IMatiere> listMatiere = FXCollections.observableArrayList();

    private static final ListProperty<IMatiere> matieres = new SimpleListProperty<>(listMatiere);

        public static ObservableList getMatieres() {
            return matieres.get();
        }

        public static void setMatieres(ObservableList value) {
            matieres.set(value);
        }

        public static ListProperty matieresProperty() {
            return matieres;
        }

    public void addMatiere(Matiere m){
        listMatiere.add(m);
    }

    public boolean containsMatiere(Matiere m){
        return listMatiere.contains(m);
    }

    public IMatiere getMatiere(Matiere m){
        return listMatiere.get(listMatiere.indexOf(m));
    }

    // Pour testMatiere
    public static void afficherListMatiere(){

        for(Iterator<Matiere> i = getMatieres().iterator(); i.hasNext(); ) {
            Matiere item = i.next();
            System.out.println(item.toString());
        }
    }
}
