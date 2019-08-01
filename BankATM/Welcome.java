import java.awt.*;
import javax.swing.*;

public class Welcome extends JFrame{
    JPanel space=new JPanel();
    JPanel button=new JPanel();
    JPanel label=new JPanel();
    JButton jbBanker = new JButton( "Banker" );
    JButton jbCustomer = new JButton( "Customer" );
    JLabel jlWelcome=new JLabel("Welcome to CS591 ATM system");

    public Welcome(){
        GUI.WelcomeGUIAL(this);
        setTitle("ATM");
        setSize(900,600);
        setLayout(new BorderLayout(10,150));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension preferredSize=new Dimension(200, 80); 
        jbBanker.setPreferredSize(preferredSize);
        jbBanker.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        jbCustomer.setPreferredSize(preferredSize);
        jbCustomer.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        jlWelcome.setFont(new Font("Black",Font.CENTER_BASELINE,30));
        label.add(jlWelcome);


        button.add(jbBanker);
        button.add(jbCustomer);
        button.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        
        add(space,BorderLayout.NORTH);
        add(label,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
       
        
        setVisible( true );
    }
}