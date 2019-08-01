import javax.swing.*;
import java.awt.*;

public class BankerCustomerGUI {
    JFrame j = new JFrame("Banker Report");
    JLabel header = new JLabel("Customers Information");
    JTextArea area = new JTextArea("Information field");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    JScrollPane sp = new JScrollPane(area);

    public BankerCustomerGUI(String str) {
        GUI.BankerCustomerGUIAL(this);
        ret.setBounds(100,450,150,50);
        exit.setBounds(650,450,150,50);
        area.setText(str);
        area.setEditable(false);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,25));
        header.setBounds(325,25,300,50);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBounds(100,100,700,300);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.add(header);
        j.add(sp);
        j.add(ret);
        j.add(exit);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
}