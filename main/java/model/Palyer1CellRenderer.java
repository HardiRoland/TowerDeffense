package model;

import java.awt.Component;
import java.awt.Color;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import view.GameWindow;

public class Palyer1CellRenderer extends DefaultListCellRenderer {
    
    /**
     * Beszínezi Player2 azon egységeit a listában amik a paraméterben megkapott mezőn vannak.
     * 
     * @param list Megadott lista.
     * @param value Mi az értéke az adott elemnek.
     * @param index Mi a sorszáma a listában.
     * @param isSelected Ki van-e választva.
     * @param cellHasFocus Fókuszban van-e.
     */
    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
        Component c = super.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );
        if ( GameWindow.getPlayer1UnitsOnClickedTile().contains(index)) {
            c.setBackground(Color.green);
        }
        return c;
    }
}