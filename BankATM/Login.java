import java.awt.*;
import javax.swing.*;

public class Login{
    JFrame j = new JFrame();
    JLabel loginName=new JLabel("Login Name:");
    JTextField nameField=new JTextField(20);
    JLabel password=new JLabel("Password:");
    JPasswordField passwordField=new JPasswordField(20);
    JButton jbLogin=new JButton("Login");
    JButton jbCancel=new JButton("Cancel");
    JButton jbCreate=new JButton("New user?");
    JLabel title=new JLabel("Please enter your account number and the password");
    int type;
    public Login(int type){
        GUI.LoginGUIAL(this);
        this.type = type;
        if(type == 0) {
            jbCreate.setVisible(false);
        }
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setLayout(null);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Dimension preferredSize=new Dimension(150, 50);
        //jbLogin.setPreferredSize(preferredSize);
        //jbCancel.setPreferredSize(preferredSize);
        

        
        
        jbCreate.setFont(new Font("Black",Font.ITALIC,15));
        jbCreate.setForeground(Color.BLUE);
        jbCreate.setOpaque(true);
        jbCreate.setBorder(null);

        
        title.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        title.setBounds(175, 70, 700, 100);
        
        loginName.setBounds(250,200,100,30);
        nameField.setBounds(350,200,250,30);
        password.setBounds(250, 250,100,30);
        passwordField.setBounds(350, 250,250,30);
        jbLogin.setBounds(250,400,150,50);
        jbCancel.setBounds(450,400,150,50);
        jbCreate.setBounds(235,320,100,30);

        j.add(title);
        j.add(loginName);
        j.add(nameField);
        j.add(password);
        j.add(passwordField);
        j.add(jbCreate);
        j.add(jbLogin);
        j.add(jbCancel);

        j.setVisible( true );
    }
}