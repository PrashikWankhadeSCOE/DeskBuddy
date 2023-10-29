package Server;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

public class SendScreen extends Thread {
    Socket socket = null;
    Robot robot = null;
    Rectangle rectangle = null;
    boolean continueLoop = true;
    OutputStream oos = null;

    public SendScreen(Socket socket,Robot robot, Rectangle rectangle){
        this.socket = socket;
        this.robot = robot;
        this.rectangle = rectangle;
        start();

    }
    
    public void stopSending(){
        continueLoop = false;
    }

    
    public void run(){
        try{
            oos=socket.getOutputStream();

        }catch(IOException ie){
            ie.printStackTrace();
        }   
        
        while(continueLoop){
            
            BufferedImage image= robot.createScreenCapture(rectangle);
        
            try{
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image,"png",baos);
                byte[] imageData = baos.toByteArray();
                oos.write(imageData);
                oos.flush();
            }catch(IOException ie){
                ie.printStackTrace();
                stopSending();
            }
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
