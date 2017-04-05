/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.cours;

import metier.cours.Cours;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import metier.matiere.CommandMatiere;
import metier.utilisateur.Professeur;


public abstract class FabriqueCours {

    public static ICours create(CommandMatiere com, Professeur prof, int i, int j, String date){
        Cours c;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        dateTime = dateTime.plusDays(i-1);

        switch(j){
            case 1 : dateTime = dateTime.plusHours(8); break;
            case 2 : dateTime = dateTime.plusHours(10); break;
            case 3 : dateTime = dateTime.plusHours(12).plusMinutes(30); break;
            case 4 : dateTime = dateTime.plusHours(13).plusMinutes(30); break;
            case 5 : dateTime = dateTime.plusHours(15).plusMinutes(30); break;
            case 6 : dateTime = dateTime.plusHours(17).plusMinutes(30); break;
            case 7 : dateTime = dateTime.plusHours(19).plusMinutes(30); break;
        }

        c = new Cours(dateTime, com.execute(prof));

        return c;
    }
}
