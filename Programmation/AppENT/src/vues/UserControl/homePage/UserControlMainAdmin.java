/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.homePage;

import java.io.IOException;
import javafx.beans.property.ListProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import metier.utilisateur.Utilisateur;
import utilities.VueUtiles;


public class UserControlMainAdmin extends ListView<Utilisateur>{

    public UserControlMainAdmin(ListProperty users) throws IOException{

       VueUtiles.chargerVue(this, "/vues/UserControl/homePage/UserControlMainAdmin.fxml");

       this.itemsProperty().bind(users);
    }
}
