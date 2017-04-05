/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


public abstract class VueUtiles {

    public static void chargerVue(Node vue, String path){
        FXMLLoader loader = new FXMLLoader(vue.getClass().getResource(path));
        loader.setController(vue);
        loader.setRoot(vue); //reçoit tout ce qu'il y a dans le fichier fxml
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(VueUtiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
