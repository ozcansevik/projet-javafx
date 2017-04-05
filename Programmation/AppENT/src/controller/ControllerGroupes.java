/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.cours.Cours;
import metier.cours.FabriqueCours;
import metier.cours.ICours;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import metier.matiere.CommandAlgorithme;
import metier.matiere.CommandExpression;
import metier.utilisateur.Etudiant;
import metier.utilisateur.FabriqueProfesseur;


public class ControllerGroupes {

    private ObservableList<IGroupe> listGroup = FXCollections.observableArrayList();

    private final ListProperty<IGroupe> groupe = new SimpleListProperty<>(listGroup);

        public ObservableList getGroupe() {
            return groupe.get();
        }

        private void setGroupe(ObservableList value) {
            groupe.set(value);
        }

        public ListProperty groupeProperty() {
            return groupe;
        }

    public ControllerGroupes() {
        // Inititialisation à la main, tant qu'il n'y a pas de base de données.
        for(int i=0; i < 8; i++)
        {
            if(i==0){
                Groupe g = new Groupe(i+1,1);
            g.addCours(FabriqueCours.create(new CommandAlgorithme(), FabriqueProfesseur.createForTest("Bouhours", "Cedric"), 1 , 1, "2017-01-23 00:00"));
            g.addCours(FabriqueCours.create(new CommandExpression(), FabriqueProfesseur.createForTest("Bouhours", "Cedric"), 1 , 1, "2017-01-24 00:00"));
            listGroup.add(g);
            } else if(i==1){
                 Groupe g = new Groupe(i+1,1);
                g.addCours(FabriqueCours.create(new CommandAlgorithme(), FabriqueProfesseur.createForTest("Bouhours", "Cedric"), 2 , 2, "2017-01-23 00:00"));
                listGroup.add(g);
            } else {
                listGroup.add(new Groupe(i+1, 1));
            }
        }
    }

    private Groupe findGroupForStud(){
        int nbEleveMin = 30;
        Groupe g = new Groupe(0, 0);

        for(int i = 0; i < listGroup.size(); i++){
            if(listGroup.get(i).getEtudiants().size() < nbEleveMin){
                g = (Groupe)listGroup.get(i);
                nbEleveMin = g.getEtudiants().size();
            }
        }

        return g;
    }

    public Groupe findGroupByName(String n){
        for(int i = 0; i < listGroup.size(); i++){
            if(listGroup.get(i).toString().equals(n))
                return (Groupe)listGroup.get(i);
        }
        return null;
    }

    public void addEtudiant(Etudiant e){
        Groupe g = findGroupForStud();
        g.addEtudiant(e);
        e.setGroupe(g);
    }

    public void addCoursToGroup(Groupe g, Cours c){
        ((Groupe)getGroupe().get(getGroupe().indexOf(g))).addCours(c);
    }

    public ObservableList<ICours> getCoursOfGroupByDate(Groupe g, String date){
        return g.getCoursByDateForDay(date);
    }
}
