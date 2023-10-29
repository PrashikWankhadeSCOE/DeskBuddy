package ClientEnd;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

class Authentication extends JFrame implements ActionListener{

    private Socket cSocket = null;
    DataOutputStream passchk = null;
    DataInputStream verification = null;
    String verify = "";
    JButton submit;
    JPanel panel;
    JLabel label,label1;
    String width = "",height = "";
    JTextField text1;

    Authentication(Socket cSocket){
        this.cSocket=cSocket;
        label1 = new JLabel();
        label1.setText("Enter Password:");
        text1 = new JTextField(20);
        label = new JLabel();
        label.setText("");
        this.setLayout(new BorderLayout());
        submit=new JButton("Submit");
        panel = new JPanel(new GridLayout(2,1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label);
        panel.add(submit);
        add(panel,BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Login Form");
    }

    public void actionPerformed(ActionEvent e) {
        String value1 = text1.getText();
        System.out.println("in action performed");
        
        try{
            passchk = new DataOutputStream(cSocket.getOutputStream());
            passchk.writeUTF(value1);
            System.out.println("try action performed");
            verification = new DataInputStream(cSocket.getInputStream());
            verify = verification.readUTF();
        }catch(Exception ie){
            ie.printStackTrace();
        }   
         if(verify.equals("true")){
             try{
                width = verification.readUTF();
                height = verification.readUTF();
                System.out.println("in if of valid");
             }catch(Exception ie){
                ie.printStackTrace();
             }
             CreateFrame abc = new CreateFrame(cSocket,width,height);
             dispose();
        }
        else{
            System.out.println("Please enter vaild password");
            JOptionPane.showMessageDialog(this,"Password is incorrect","Error",JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
}