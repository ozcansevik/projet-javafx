/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.groupe;

import metier.groupe.IGroupe;
import metier.cours.Cours;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import metier.utilisateur.Etudiant;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Iterator;
import metier.cours.ICours;
import metier.utilisateur.IEtudiant;
import metier.utilisateur.Professeur;


public class Groupe implements IGroupe{

    private final IntegerProperty numero = new SimpleIntegerProperty();
        @Override
        public IntegerProperty numeroProperty() {
            return numero;
        }
    private final IntegerProperty annee = new SimpleIntegerProperty();
        @Override
        public IntegerProperty anneeProperty() {
            return annee;
        }

    private ObservableList<IEtudiant> etudiants = FXCollections.observableArrayList() ;

    private final ListProperty<IEtudiant> Etudiants = new SimpleListProperty<>(etudiants);
        @Override
        public ListProperty etudiantsProperty() {
            return Etudiants;
        }

    private ObservableList<ICours> listCours = FXCollections.observableArrayList();

    private final ListProperty<ICours> cours = new SimpleListProperty<>(listCours);
        @Override
        public ListProperty coursProperty() {
            return cours;
        }


    public Groupe(int numero, int annee) {
        this.setNumero(numero);
        this.setAnnee(annee);
    }

    public void addEtudiant(Etudiant etud){
        this.getEtudiants().add(etud);
    }

    public void afficherEtudiants(){

        for(Iterator<IEtudiant> i = this.getEtudiants().iterator(); i.hasNext(); ) {
            Etudiant item = (Etudiant)i.next();
            System.out.println(item.toString());
        }

    }

    public void addCours(ICours c){
        listCours.add(c);
    }

    public void removeCours(ICours c){
        listCours.remove(c);
    }

    public ObservableList<ICours> getCoursByProfAndDate(Professeur p, String date){
        ObservableList<ICours> c = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        for(Iterator<ICours> i = getCours().iterator(); i.hasNext();){
            Cours cours = (Cours)i.next();
            if(cours.getMatiere().getProfesseur().equals(p) && cours.getDateCours().compareTo(dateTime) >= 0 && cours.getDateCours().compareTo(dateTime.plusDays(6)) <= 0){
                c.add(cours);
            }
        }
        return c;
    }

    public ObservableList<ICours> getCoursByDateForWeek(String date){
        ObservableList<ICours> c = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        for(Iterator<ICours> i = getCours().iterator(); i.hasNext();){
            Cours cours = (Cours)i.next();
            if(cours.getDateCours().compareTo(dateTime) >= 0 && cours.getDateCours().compareTo(dateTime.plusDays(6)) <= 0){
                c.add(cours);
            }
        }
        return c;
    }

    public ObservableList<ICours> getCoursByDateForDay(String date){
        ObservableList<ICours> c = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        for(Iterator<ICours> i = getCours().iterator(); i.hasNext();){
            Cours cours = (Cours)i.next();
            if(cours.getDateCours().compareTo(dateTime) >= 0 && cours.getDateCours().compareTo(dateTime.plusDays(1)) <= 0){
                c.add(cours);
            }
        }
        return c;
    }

    @Override
    public String toString(){
        return "Groupe " + this.getNumero();
    }
}
