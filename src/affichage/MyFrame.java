/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package affichage;

import javax.swing.JTable;

/**
 *
 * @author itu
 */
public class MyFrame extends JTable{
    String [] columns ;
    String [][] rowsData ;

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String[][] getRowsData() {
        return rowsData;
    }

    public void setRowsData(String[][] rowsData) {
        this.rowsData = rowsData;
    }

    
}
