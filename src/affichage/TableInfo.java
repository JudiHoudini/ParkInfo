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
public class TableInfo extends JTable {
    int columns ;
    int lines ;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
    
    public TableInfo(int columns, int lines){
        super(lines, columns);
        setValueAt("OS", 0, 0);
        setValueAt("OSVERSION", 0, 1);
        setValueAt("IP", 0, 2);
        setValueAt("Adress Mac", 0, 3);
        setValueAt("RAM", 0, 4);
        setValueAt("user", 0, 5);
        setValueAt("CPU", 0, 6);
        setValueAt("Free Disk ", 0, 7);
    }
    
    public void addLine(String[]linesContent , int numLine){
        for (int i = 0; i < linesContent.length; i++) {
            setValueAt(linesContent[i],numLine , i);
        }
        repaint();
    }
    
}
