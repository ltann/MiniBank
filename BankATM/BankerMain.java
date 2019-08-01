
import javax.swing.*;
import java.awt.*;

public class BankerMain{
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    JLabel label = new JLabel("Main Menu");
    Font font = new Font("Black",Font.CENTER_BASELINE,30);
    JButton report = new JButton("Report");
    JButton exit = new JButton("Exit");
    JButton search = new JButton("Search Customer");
    Dimension dimension = new Dimension(150,80);
    JButton customers = new JButton("Show all customers");

    public BankerMain(){
        GUI.BankerMainGUIAL(this);
        j.setLayout(new GridBagLayout());
        j.setTitle("ATM");
        
        label.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,150,0,0);
        j.add(label, c);
        
        report.setFont(new Font("Black",1,20));
        report.setPreferredSize(dimension);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        //c.insets = new Insets(0,0,0,100);
        j.add(report, c);
        customers.setPreferredSize(dimension);
        customers.setFont(new Font("Black",1,20));
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        //c.insets = new Insets(0,0,0,100);
        j.add(customers, c);

        search.setPreferredSize(dimension);
        search.setFont(new Font("Black",1,20));
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        //;c.insets = new Insets(0,100,0,0)
        j.add(search, c);


        exit.setPreferredSize(dimension);
        exit.setFont(new Font("Black",1,20));
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        //;c.insets = new Insets(0,100,0,0)
        j.add(exit, c);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setVisible(true);

    }

}
