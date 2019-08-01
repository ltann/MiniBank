import java.awt.*;
import javax.swing.*;

public class CreateUserGUI{
    JFrame j = new JFrame();
    JLabel loginName=new JLabel("Login Name:");
    JTextField nameField=new JTextField(20);
    JLabel password=new JLabel("Password:");
    JTextField passwordField=new JTextField(20);
    JLabel passwordAgain=new JLabel("Type in again:");
    JTextField passwordAgField=new JTextField(20);
    JButton next=new JButton("NEXT");
    JButton cancel=new JButton("CANCEL");
    JLabel label=new JLabel("Creating account");

    public CreateUserGUI(){
        GUI.CreateUserGUIAL(this);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(null);

        
        loginName.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        nameField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        password.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        passwordField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        passwordAgain.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        passwordAgField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
       
       
        label.setFont(new Font("Black",Font.CENTER_BASELINE,30));
        
        GridBagLayout gbaglayout=new GridBagLayout();
        GridBagConstraints constraints=new GridBagConstraints();
        j.setLayout(gbaglayout);
        constraints.insets=new Insets(-100, 200, 0, 0);
        constraints.fill=GridBagConstraints.BOTH;
        constraints.gridx=1;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.gridheight=1;
        j.add(label,constraints);
        constraints.insets=new Insets(50, 50, 0, 0);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        j.add(loginName,constraints);
        //constraints.insets=new Insets(100,200, 0, 0);
        constraints.gridx=2;
        constraints.gridy=1;
        constraints.gridwidth=2;
        constraints.gridheight=1;
        j.add(nameField,constraints);
        //constraints.insets=new Insets(200, 100, 0, 0);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        j.add(password,constraints);
        //constraints.insets=new Insets(200, 200, 0, 0);
        constraints.gridx=2;
        constraints.gridy=2;
        constraints.gridwidth=2;
        constraints.gridheight=1;
        j.add(passwordField,constraints);
        //constraints.insets=new Insets(300, 100, 0, 0);
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        j.add(passwordAgain,constraints);
        //constraints.insets=new Insets(300, 200, 0, 0);
        constraints.gridx=2;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.gridheight=1;
        j.add(passwordAgField,constraints);
        constraints.insets=new Insets(50, 50, 0, 0);
        constraints.gridx=1;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        j.add(next,constraints);
        constraints.insets=new Insets(50, 200, 0, -100);
        constraints.gridx=2;
        constraints.gridy=4;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        j.add(cancel,constraints);
        
        j.setVisible( true );
    }
}