/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.utilisateur;

import java.util.Iterator;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.cours.Cours;
import metier.groupe.Groupe;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;


public class Professeur extends Utilisateur implements IProfesseur{

    private ObservableList<Groupe> listGroupes = FXCollections.observableArrayList() ;

    private final ListProperty<Groupe> groupes = new SimpleListProperty<>(listGroupes);
        @Override
        public ListProperty groupesProperty() {return groupes;}

    Professeur(String nom, String prenom, ObservableList<IGroupe> groupes) {
        super(nom, prenom);
        if(groupes != null)
            getGroupes().addAll(groupes);
    }

    public Groupe findGroupByCours(Cours cours){

        for(Iterator<IGroupe> i = this.getGroupes().iterator(); i.hasNext();){
            Groupe g = (Groupe)i.next();
            if(g.getCours().contains(cours)){
                return g;
            }
        }
        return null;
    }

    public ListProperty<IEtudiant> getAllEtudiants(){
        List<IEtudiant> etudiantsDuGroupe ;
        ObservableList<IEtudiant> etudiantsDuProf = FXCollections.observableArrayList();
        ListProperty<IEtudiant> listEtudiant = new SimpleListProperty<>(etudiantsDuProf);

        //partie créant tous kes etudiants de tous les groupes assoiés à un prof
         for(int i=0 ; i<getGroupes().size(); i++){
             etudiantsDuGroupe = ((Groupe)getGroupes().get(i)).getEtudiants();

             for(int j=0 ; j<etudiantsDuGroupe.size(); j++)
                etudiantsDuProf.add(etudiantsDuGroupe.get(j));

         }

         return listEtudiant;
    }

    @Override
    public boolean equals(Object o){

        if(o == this) return true;
        if(!(o instanceof Professeur)) return false;

        return ((Professeur)o).getIdentifiant().equals(this.getIdentifiant())
                && ((Professeur)o).getMdp().equals(this.getMdp());
    }
}
