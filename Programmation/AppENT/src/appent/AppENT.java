
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appent;


import controller.ControllerUsers;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import vues.login.LoginView;


public class AppENT extends Application {

    private ControllerUsers controller = new ControllerUsers();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root;
        LoginView loginView = new LoginView(controller);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/login/LoginView.fxml"));
        loader.setController(loginView);
        loader.setClassLoader(loginView.getClass().getClassLoader());
        root = loader.load();

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("AppENT");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    loginView.connexion();
                } catch (IOException ex) {
                    Logger.getLogger(AppENT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        primaryStage.getIcons().add(new Image("/resources/icone.png"));

    }

    @Override
    public void stop(){
        controller.saveAll();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
