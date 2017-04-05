/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import metier.groupe.IGroupe;


public interface IProfesseur {

    default public ObservableList<IGroupe> getGroupes() {return groupesProperty().get();}
    default public void setGroupes(ObservableList<IGroupe> value) { groupesProperty().set(value);}

    public ListProperty<IGroupe> groupesProperty();
}
