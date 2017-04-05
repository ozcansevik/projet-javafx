/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.mark;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import metier.note.Note;


public class ListNoteCellData{

    @FXML
    private GridPane gridPane;

    @FXML
    private Label textMatiere;

    @FXML
    private Label textNote;

    @FXML
    private Label textCoeff;


    public ListNoteCellData()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vues/UserControl/mark/ListNoteCellData.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void setInfo(Note n)
    {
      textMatiere.textProperty().setValue(n.getMatiere().toString());
      textNote.textProperty().setValue(Integer.toString(n.getNote()));
      textCoeff.textProperty().setValue(Integer.toString(n.getCoefficient()));
    }

    public GridPane getGrid()
    {
        return gridPane;
    }

}
