package ClientEnd;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

class SendEvents implements MouseListener, MouseMotionListener, KeyListener {
    private Socket cSocket;
    private JPanel cPanel;
    private PrintWriter writer;
    private String width;
    private String height;
    private double h;
    private double w;

    SendEvents(Socket s, JPanel p, String width, String height) {
        cSocket = s;
        cPanel = p;
        this.width = width;
        this.height = height;
        h = Double.parseDouble(height.trim());
        w = Double.parseDouble(width.trim());

        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);

        try {
            writer = new PrintWriter(cSocket.getOutputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xScale = w / cPanel.getWidth();
        double yScale = h / cPanel.getHeight();
        writer.println(Commands.MOVE_MOUSE.getAbbrev());
        writer.println((int) (e.getX() * xScale));
        writer.println((int) (e.getY() * yScale));
        writer.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        writer.println(Commands.PRESS_MOUSE.getAbbrev());
        int button = e.getButton();
        int xButton = 16;
        if (button == MouseEvent.BUTTON3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        writer.println(Commands.RELEASE_MOUSE.getAbbrev());
        int button = e.getButton();
        int xButton = 16;
        if (button == MouseEvent.BUTTON3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        writer.println(Commands.PRESS_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        writer.println(Commands.RELEASE_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }
}

//         cSocket = s;
//         cPanel = p;
//         this.width = width;
//         this.height = height;
//         h = Double.valueOf(height.trim()).doubleValue();
//         w = Double.valueOf(width.trim()).doubleValue();
    
//         cPanel.addKeyListener(this);
//         cPanel.addMouseListener(this);
//         cPanel.addMouseMotionListener(this);

//         try{
//             writer = new PrintWriter(cSocket.getOutputStream());
//         }catch(IOException ioe){
//             ioe.printStackTrace();
//         }
//         public void mouseDragged(MouseEvent e){
            
//         }
//         public void mouseMoved(MouseEvent e){
//             double xScale = (double) w/cPanel.getWidth();
//             double yScale = (double) h/cPanel.getHeight();
//             writer.println(Commands.MOVE_MOUSE.getAbbrev());
//             writer.println((int)(e.getX()*xScale));
//             writer.println((int)(e.getY()*yScale));
//             writer.flush();
//         }
//         public void mouseClicked(MouseEvent e){

//         }

//         public void mousePressed(MouseEvent e){
//             writer.println(Commands.PRESS_MOUSE.getAbbrev());
//             int button = e.getButton();
//             int xButton = 16;
//             if(button == MouseEvent.BUTTON3){
//                 xButton = 4;
//             }
//             writer.println(xButton);
//             writer.flush(); 
//         }
//         public void mouseEntered(MotionEvent e){

//         }
//         public void mouseExited(MotionEvent e){

//         }
//         @Override
//         public void keyTyped(KeyEvent e){

//         }
//         @Override
//         public void keyPressed(KeyEvent e){
//             writer.println(Commands.PRESS_KEY.getAbbrev());
//             writer.println(e.getKeyCode());
//             writer.flush();
//         }
//         @Override
//         public void keyReleased(KeyEvent e){
//             writer.println(Commands.RELEASE_KEY.getAbbrev());
//             writer.println(e.getKeyCode());
//             writer.flush();
//         }

// }
// }
