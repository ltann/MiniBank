
import javax.swing.*;
import java.awt.*;

public class BankerMain{
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    JLabel header = new JLabel("Main Menu");
    JButton report = new JButton("Report");
    JButton exit = new JButton("Exit");
    JButton search = new JButton("Search Customer");
    JButton customers = new JButton("Show all customers");
    JButton stock = new JButton("Stocks");
    JButton bond = new JButton("Bonds");

    public BankerMain(){
        GUI.BankerMainGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(360,25,300,50);
        report.setBounds(0,150,250,80);
        report.setFont(new Font("Black", Font.BOLD,20));
        search.setBounds(0,300,250,80);
        search.setFont(new Font("Black", Font.BOLD,20));
        customers.setBounds(0,450,250,80);
        customers.setFont(new Font("Black", Font.BOLD,20));
        stock.setBounds(640,150,250,80);
        stock.setFont(new Font("Black", Font.BOLD,20));
        bond.setBounds(640,300,250,80);
        bond.setFont(new Font("Black", Font.BOLD,20));
        exit.setBounds(640,450,250,80);
        exit.setFont(new Font("Black", Font.BOLD,20));

        j.add(header);
        j.add(report);
        j.add(search);
        j.add(customers);
        j.add(stock);
        j.add(bond);
        j.add(exit);
        j.setLayout(null);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setVisible(true);
    }
    public static void main(String[] args) {
        new BankerMain();
    }
}
