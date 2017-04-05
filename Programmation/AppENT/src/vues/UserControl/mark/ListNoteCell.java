/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues.UserControl.mark;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListCell;
import metier.note.Note;


public class ListNoteCell extends ListCell<Note>
{
    @Override
    public void updateItem(Note n, boolean empty)
    {
        super.updateItem(n,empty);
        if(n != null)
        {
                ListNoteCellData data = new ListNoteCellData();
                data.setInfo(n);
                setGraphic(data.getGrid());

        }
    }
}
