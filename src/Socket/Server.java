/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author itu
 */
public class Server {
    static String[] columns = {"os","address ip","address mac","RAM"};
    static int colonsFilled = 0;
    
    public static JFrame parkInfo (String[][]rowData){
        JFrame jframe = new JFrame();
        JTable jtable = new JTable(rowData, columns);
        
        jframe.add(jtable);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        return jframe ;
    }
    
//    public static void instanciate2DArray(String[][] arrays){
//        arrays = new String[20][];
//        for(int i = 0 ; i < arrays.length ; i++){
//            arrays[i] = new String[columns.length];
//        }
//    }
//    
    public static void addTo2dArray(String[][]arrays, String[]toAdd){
        arrays[colonsFilled] = toAdd;
        colonsFilled ++;
    }
    
    public static void main(String[] args)  {
        try {
            ServerSocket serverSocket = new ServerSocket(6969);
            Socket client = serverSocket.accept();
            BufferedReader reader = new BufferedReader (new InputStreamReader(client.getInputStream()));
            String message = reader.readLine();
            
            String[] messageSplited = message.split(",");
            String[][] rowDatas = new String[5][columns.length];
            addTo2dArray(rowDatas, messageSplited);
            
            parkInfo(rowDatas);
            
            serverSocket.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
 