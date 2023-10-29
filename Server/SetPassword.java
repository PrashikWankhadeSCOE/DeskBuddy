package Server;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetPassword extends JFrame implements ActionListener{
    static int port = 2002;
    JButton submit;
    JPanel panel;
    JTextField text1,text2;
    String value1,value2;
    JLabel label,label1,label2;

    SetPassword(){
        label1 = new JLabel();
        label1.setText("Set PassWord");
        text1 = new JTextField(15);
        label = new JLabel();
        this.setLayout(new BorderLayout());
        submit = new JButton("Submit !!");
        panel = new JPanel(new GridLayout(2, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label);
        panel.add(submit);
        add(panel,BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("SET PASSWORD");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        value1 = text1.getText();
        dispose();
        new InitConnection(port,value1);

    }
    public String getValue1(){
        return value1;
    }
    /* 
    public static void main(String args[]){
        SetPassword frame1 = new SetPassword();
        frame1.setSize(300, 80);
        frame1.setLocation(500,300);
        frame1.setVisible(true);
    }
    */


}
