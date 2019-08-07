import javax.swing.*;
import java.awt.*;

public class SecurityMainGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Main Menu");
    JButton balance = new JButton("Balance");
    JButton property = new JButton("Property");
    JButton stocks = new JButton("Stocks");
    JButton bonds = new JButton("Bonds");
    JButton transfer = new JButton("Transfer");
    JButton exit = new JButton("Exit");

    public SecurityMainGUI() {
        GUI.SecurityMainGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(360,25,300,50);
        balance.setBounds(0,150,250,80);
        balance.setFont(new Font("Black", Font.BOLD,20));
        property.setBounds(0,300,250,80);
        property.setFont(new Font("Black", Font.BOLD,20));
        transfer.setBounds(0,450,250,80);
        transfer.setFont(new Font("Black", Font.BOLD,20));
        stocks.setBounds(640,150,250,80);
        stocks.setFont(new Font("Black", Font.BOLD,20));
        bonds.setBounds(640,300,250,80);
        bonds.setFont(new Font("Black", Font.BOLD,20));
        exit.setBounds(640,450,250,80);
        exit.setFont(new Font("Black", Font.BOLD,20));
        
        j.add(header);
        j.add(balance);
        j.add(property);
        j.add(stocks);
        j.add(transfer);
        j.add(bonds);
        j.add(exit);
        j.setTitle("Trading System");
        j.setSize(900,600);
        j.setResizable(false);
        j.setLayout(null);
        j.setVisible(true);
    }
}