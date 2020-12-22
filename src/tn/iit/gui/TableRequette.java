package tn.iit.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Dimension;
import java.awt.GridLayout;


public class TableRequette extends JPanel {
    

    public TableRequette(Object[][] dataa,String[] columnNamees) {
        super(new GridLayout(1,0));

       

Object[][] data = dataa;
String[] columnNames = columnNamees;

	
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(data,columnNames) );
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        
    }

   
    
    public static void createAndShowGUI(Object[][] data,String[] columnNames) {
        //Create and set up the window.
        JFrame frame = new JFrame("Resultat de la Requette");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        TableRequette newContentPane = new TableRequette(data,columnNames);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
}

