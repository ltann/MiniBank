import javax.swing.*;
import java.awt.*;

public class depositGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Please type deposit amount: ");
    JLabel title = new JLabel("Amount: ");
    JTextField amount = new JTextField();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    String[] currencyList = { "$", "妤�", "閳э拷" };
    JComboBox currencyType = new JComboBox(currencyList);
    public depositGUI() {
        GUI.depositGUIAL(this);
        currencyType.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        header.setFont(new Font("Black", Font.CENTER_BASELINE,25));
        header.setBounds(250,100,400,50);
        title.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        title.setBounds(100,250,100,50);
        amount.setBounds(200,250,450,50);
        currencyType.setBounds(675,250,100,50);
        currencyType.setSelectedIndex(0);
        confirm.setBounds(200,425,100,50);
        cancel.setBounds(550,425,100,50);
        j.add(header);
        j.add(title);
        j.add(amount);
        j.add(confirm);
        j.add(cancel);
        j.add(currencyType);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
}