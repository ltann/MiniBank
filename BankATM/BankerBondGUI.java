import javax.swing.*;
import java.awt.*;

public class BankerBondGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Bonds");
    JLabel period = new JLabel("Period");
    JLabel interest = new JLabel("Interest Rate");
    JLabel newRate = new JLabel("New Rate: ");
    JComboBox bondPeriod;
    JTextField curPrice = new JTextField();
    JTextField newPrice = new JTextField();
    JButton search = new JButton();
    JButton apply = new JButton("Update");
    JButton cancel = new JButton("Cancel");
    String[] bondList;

    public BankerBondGUI() {
    	GUI.BankerBondGUIAL(this);
    	bondPeriod = new JComboBox(SystemApp.getBondTypeList());
        header.setFont(new Font("Black", Font.CENTER_BASELINE, 30));
        header.setBounds(400,50,200,50);
        period.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        period.setBounds(150,150,200,50);
        interest.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        interest.setBounds(550,150,200,50);
        newRate.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        newRate.setBounds(375,300,150,50);
        curPrice.setBounds(500,225,250,50);
        newPrice.setBounds(500,300,250,50);
        bondPeriod.setBounds(100,225,300,50);
        search.setBounds(410,225,50,50);
        apply.setBounds(200,450,100,50);
        cancel.setBounds(550,450,100,50);

        j.add(header);
        j.add(period);
        j.add(interest);
        j.add(newRate);
        j.add(curPrice);
        j.add(newPrice);
        j.add(bondPeriod);
        j.add(search);
        j.add(apply);
        j.add(cancel);
        j.setSize(900,600);
        j.setTitle("Trading System");
        j.setResizable(false);
        j.setLayout(null);
        j.setVisible(true);
    }
    public static void main(String[] args) {
        new BankerBondGUI();
    }
}