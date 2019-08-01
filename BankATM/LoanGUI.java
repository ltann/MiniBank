import javax.swing.*;
import java.awt.*;

public class LoanGUI {
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Black",Font.CENTER_BASELINE,30);
    Dimension dimension = new Dimension(100,80);
    JLabel label0 = new JLabel("Welcome, " + "name");
    JLabel label1 = new JLabel("Loan");
    JLabel label2 = new JLabel("1.   0.7% / month    > 12 month");
    JLabel label3 = new JLabel("2.   1% / month    6 - 12 month");
    JLabel label4 = new JLabel("3.   2% / month    1 - 5 month");
    JLabel label5 = new JLabel("Amount");
    String[] currencyList = { "$", "¥", "€" };
    String[] timeList = {"1 mon","2 mon","3 mon","4 mon","5 mon","6 mon","7 mon","8 mon","9 mon","10 mon","11 mon","12 mon"};
    JComboBox currencyCB = new JComboBox(currencyList);
    JComboBox timeCB = new JComboBox(timeList);
    JTextField tf1 = new JTextField();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    public LoanGUI(String name, Customer customer, Account a, String str, int accountNumber){
        GUI.LoanGUIAL(this, a, customer, str, accountNumber);
        j.setLayout(new GridBagLayout());
        
        label0.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,100,0,0);
        j.add(label0, c);

        
        label1.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,100,0,0);
        j.add(label1, c);

        
        label2.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,100,0,0);
        j.add(label2, c);

        
        label3.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,100,0,0);
        j.add(label3, c);

        
        label4.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0,100,0,0);
        j.add(label4, c);

       
        label5.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0,100,0,0);
        j.add(label5, c);

        
        tf1.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(0,-100,0,0);
        j.add(tf1, c);


        
        
        currencyCB.setFont(font);
        currencyCB.setSelectedIndex(0);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 4;
        c.insets = new Insets(0,50,0,50);
        j.add(currencyCB, c);

        timeCB.setFont(font);
        timeCB.setSelectedIndex(0);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 4;
        c.insets = new Insets(0,0,0,0);
        j.add(timeCB, c);


        confirm.setPreferredSize(dimension);
        confirm.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0,100,0,50);
        j.add(confirm, c);


        
        cancel.setPreferredSize(dimension);
        cancel.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        c.insets = new Insets(0,0,0,0);
        j.add(cancel, c);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setVisible(true);
    }
}
