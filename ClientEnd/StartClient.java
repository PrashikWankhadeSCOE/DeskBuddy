package ClientEnd;
import java.net.Socket;

import javax.swing.JOptionPane;

public class StartClient {
    //static int portNo = 2010;

    /*public static void main(String[] args) {
        String ipAddress = JOptionPane.showInputDialog("Enter IP Address:");
        new StartClient().initialize(ipAddress);

    } */
    public static void connectionClient(){
        new StartClient();
    }
    StartClient(){
        String ipAddress = JOptionPane.showInputDialog("Enter IP Address:");
        //if(ipAddress!=null && ipAddress.isEmpty()){
            initialize(ipAddress);
        //}

    }
    public static void initialize(String ipAddress){
        System.out.println("Server");
        try {
            Socket sc = new Socket(ipAddress,2002);
            System.out.println("Connecting to the server....");
            Authentication frame1 = new Authentication(sc);
            frame1.setSize(300,80);
            frame1.setLocation(500,300);
            frame1.setVisible(true);
            System.out.println("Goes to authentication");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
