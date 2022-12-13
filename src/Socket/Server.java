/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

import affichage.MyFrame;
import affichage.TableInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itu
 */
public class Server extends Thread {
    Socket client ;
    static MyFrame myFrame ;
    int numLine ;
    
    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public static MyFrame getMyFrame() {
        return myFrame;
    }

    public static void setMyFrame() {
        myFrame = new MyFrame(new TableInfo(6,20));
    }

    public Server(Socket client) {
        setClient(client);
    }
    
    
    public void traitementsDonnees(){
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(client.getInputStream()));
            String message = reader.readLine();
            String[] messageSplited = message.split(",");
            myFrame.getTableInfo().addLine(messageSplited, numLine );
            numLine++;
            
            client.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        traitementsDonnees();
    }
    
    public static void main(String[] args)  {
        try {
            ServerSocket serverSocket = new ServerSocket(6969);
            setMyFrame();
            while (true) {                
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                server.start();
            }
                        
        } 
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
 