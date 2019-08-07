import javax.swing.*;
import java.awt.*;

public class withdrawGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Please type withdraw amount: (Checking account will charge 3 USD)");
    JLabel title = new JLabel("Amount: ");
    JTextField amount = new JTextField();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    JLabel subheader = new JLabel("Quick Money: ");
    JButton d_20 = new JButton("$20");
    JButton d_50 = new JButton("$50");
    JButton d_100 = new JButton("$100");
    JButton d_200 = new JButton("$200");
    JButton d_500 = new JButton("$500");
    String[] currencyList = { "$", "¥", "€" };
    JComboBox currencyType = new JComboBox(currencyList);

    public withdrawGUI() {
        GUI.withdrawGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        header.setBounds(100,75,700,50);
        title.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        title.setBounds(150,175,100,50);
        amount.setBounds(250,175,400,50);
        confirm.setBounds(200,275,100,50);
        cancel.setBounds(550,275,100,50);
        subheader.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        subheader.setBounds(50,350,200,50);
        d_20.setBounds(50,425,100,50);
        d_50.setBounds(225,425,100,50);
        d_100.setBounds(400,425,100,50);
        d_200.setBounds(575,425,100,50);
        d_500.setBounds(750,425,100,50);
        currencyType.setBounds(675,175,100,50);
        currencyType.setSelectedIndex(0);

        j.add(header);
        j.add(title);
        j.add(amount);
        j.add(confirm);
        j.add(cancel);
        j.add(subheader);
        j.add(d_20);
        j.add(d_50);
        j.add(d_100);
        j.add(d_200);
        j.add(d_500);
        j.add(currencyType);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }

}