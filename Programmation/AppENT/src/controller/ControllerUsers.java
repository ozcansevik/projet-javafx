/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.groupe.Groupe;
import metier.groupe.IGroupe;
import metier.matiere.FabriqueMatiere;
import metier.matiere.Matiere;
import metier.note.FabriqueNote;
import metier.note.INote;

import metier.utilisateur.Utilisateur;
import metier.utilisateur.Admin;
import metier.utilisateur.Etudiant;
import metier.utilisateur.FabriqueEtudiant;
import metier.utilisateur.FabriqueProfesseur;
import metier.utilisateur.IUtilisateur;
import metier.utilisateur.Professeur;
import persistance.XMLLoadDataManager;
import persistance.XMLSaveDataManager;


public class ControllerUsers {

    private Utilisateur connected;

    private ControllerGroupes controllerGroupes = new ControllerGroupes();

    private ObservableList<IUtilisateur> listUsers = FXCollections.observableArrayList();
    private final ListProperty<IUtilisateur> users = new SimpleListProperty<IUtilisateur>(listUsers);

    public ObservableList getUsers() {
        return users.get();
    }

    public void setUsers(ObservableList value) {
        users.set(value);
    }

    public ListProperty usersProperty() {
        return users;
    }



    public ControllerUsers() {
        // Initialisation à la main en attendant a base de données.
        try{
            this.addEtudiant("Reyes","Adam");
            this.addEtudiant("Sevik", "Ozcan");
            this.addEtudiant("Reyes","Adam");
            ObservableList<IGroupe> gProf = FXCollections.observableArrayList();
            gProf.add((Groupe)getControllerGroupes().getGroupe().get(1));
            gProf.add((Groupe)getControllerGroupes().getGroupe().get(0));
            this.addProfesseur("Bouhours", "Cedric", gProf);
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
        listUsers.add(new Admin("Root", "root"));
        loadAll();
    }

    public Utilisateur getUserConnected(){
        return this.connected;
    }

    public ControllerGroupes getControllerGroupes(){
        return controllerGroupes;
    }

    public boolean checkLogin(String identifiant, String mdp){
        for(IUtilisateur user : listUsers){
            if(((Utilisateur)user).compareLogin(identifiant, mdp))
            {
                this.connected = (Utilisateur)user;
                return true;
            }
        }

        return false;
    }

    public void addEtudiant(String nom, String prenom) throws IOException{

        Etudiant e = FabriqueEtudiant.create(nom, prenom);
        Integer i = new Integer(1);

        while(checkLogin(e.getIdentifiant(), e.getMdp())){
            e.modifyIdentifiant(i);
            e.modifyMdp(i);
            i = i+1;
        }

        controllerGroupes.addEtudiant(e);

       //NOTES POUR TESTS
       Professeur p = FabriqueProfesseur.createForTest("Bouhours", "Cedric");
       Matiere m = (Matiere) FabriqueMatiere.createAlgorithme(p);

       /*Professeur p2 = FabriqueProfesseur.createForTest("Paul", "Jean");
       Matiere m2 = (Matiere) FabriqueMatiere.createExpression(p2);

       Professeur p3 = FabriqueProfesseur.createForTest("Petit", "Jean");
       Matiere m3 = (Matiere) FabriqueMatiere.createAlgorithme(p3); */

       e.getNotes().add(FabriqueNote.create(10, 1,p,m));
      /* e.getNotes().add(FabriqueNote.create(14, 3,p3,m3));
       e.getNotes().add(FabriqueNote.create(11, 1,p3,m3));
       e.getNotes().add(FabriqueNote.create(12, 2,p2,m2));
       e.getNotes().add(FabriqueNote.create(8, 2,p2,m2));*/
       e.getNotes().add(FabriqueNote.create(14, 1,p,m));
       e.getNotes().add(FabriqueNote.create(15, 1,p,m));
       e.getNotes().add(FabriqueNote.create(17, 1,p,m));
       e.getNotes().add(FabriqueNote.create(11, 1,p,m));
       e.getNotes().add(FabriqueNote.create(10, 1,p,m));

       Collections.sort(e.getNotes(), new Comparator<INote>() {
       @Override
        public int compare(INote o1, INote o2) {

            return o1.getMatiere().toString().compareTo(o2.getMatiere().toString());

        }
        });

       e.calculerMoyenne();
       //FIN NOTES POUR TESTS

       listUsers.add(e);

    }

    public void addProfesseur(String nom, String prenom, ObservableList<IGroupe> groupe)throws IOException{

        Professeur p = FabriqueProfesseur.create(nom, prenom, groupe);
        Integer i = new Integer(1);

        while(checkLogin(p.getIdentifiant(), p.getMdp())){
            p.modifyIdentifiant(i);
            p.modifyMdp(i);
            i = i+1;
        }

        listUsers.add(p);
    }


    public ListProperty getAllProfesseurPropertyByGroup(Groupe g){
        ObservableList<Professeur> listProf = FXCollections.observableArrayList();
        ListProperty<Professeur> profProperty = new SimpleListProperty<>(listProf);

        for(Iterator<Utilisateur> i = this.getUsers().iterator(); i.hasNext();){
            Utilisateur u = i.next();
            if(u instanceof Professeur && ((Professeur)u).getGroupes().contains(g)){
                listProf.add((Professeur)u);
            }
        }

        return profProperty;
    }

    public void saveAll(){
        XMLSaveDataManager.saveUtilisateurs(listUsers);
    }

    public void loadAll(){
        loadUtilisateurs();
    }

    public void loadUtilisateurs(){
        List<IUtilisateur> result = XMLLoadDataManager.loadUtilisateur();
        if (result != null) {
            //getUsers().clear();
            getUsers().addAll(result);
        }
    }
}
