import java.awt.*;
import javax.swing.*;

public class exit extends JFrame{
    JLabel question=new JLabel("Do you want to exit?");
    JButton confirm=new JButton("CONFIRM");
    JButton cancel=new JButton("CANCEL");
    JFrame j;

    public exit(JFrame x){
        this.j = x;
        GUI.ExitGUI(this);
        setTitle("ATM");
        setSize(480, 320);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        question.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        question.setBounds(130, 50, 300, 100);

        confirm.setBounds(100,200,100,35);
        cancel.setBounds(280,200,100,35);
        
        add(question);
        add(confirm);
        add(cancel);
        
        setVisible( true );
    }
}