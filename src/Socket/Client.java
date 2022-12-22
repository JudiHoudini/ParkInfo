import com.sun.management.OperatingSystemMXBean;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MXBean;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author zdelavine
 */
public class Client  {
    /**
     * @param args the command line arguments
     */
    
    public static String getAddressMac() throws UnknownHostException, SocketException{
        InetAddress localIp = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localIp);
               
        byte[] macAdress = ni.getHardwareAddress();
                
        String[] hexadecimal = new String[macAdress.length];
        for (int i = 0; i < hexadecimal.length; i++) {
            hexadecimal[i] = String.format("%02X", macAdress[i]);
        }
        String realMacAdress = String.join("-", hexadecimal);
        return realMacAdress ;
    }
    
    public static long getRam(){
        OperatingSystemMXBean mXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return mXBean.getTotalMemorySize();
    }
    
    public static double getFreeDiskMemory(){
        return new File("/").getFreeSpace()*9.31e-10;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here  
        try {
            OperatingSystemMXBean mXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            //creating the client socket 
            Socket client = new Socket("localhost",6969);
            DataOutputStream send = new DataOutputStream(client.getOutputStream());
            //os , ip , adresse mac , ram  , 
            String os = System.getProperty("os.name");
            String ip = client.getInetAddress().getHostAddress();
            String addressMac = getAddressMac();
            String ram = String.valueOf(getRam());
            String user = System.getProperty("user.name");
            String CPU = String.valueOf(mXBean.getSystemLoadAverage() / mXBean.getAvailableProcessors());
            String freeSpace = String.valueOf(getFreeDiskMemory());
            String osVersion = System.getProperty("os.version");
            
            Vector<String> properties = new Vector<>();
            properties.add(os);
            properties.add(osVersion);
            properties.add(ip);
            properties.add(addressMac);
            properties.add(ram + "bytes");
            properties.add(user);
            properties.add(CPU);
            properties.add(freeSpace);
            
            String toSend = "";
            for (int i = 0; i < 8; i++) {
                toSend += properties.get(i) + ",";
            }
            
            send.writeUTF(toSend);
            send.flush();
            send.close();
            client.close();
        
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
