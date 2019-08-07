import javax.swing.*;
import java.awt.*;

public class Transfer {
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Black",Font.CENTER_BASELINE,30);
    Dimension dimension = new Dimension(100,50);
    JLabel label0;
    JLabel label1 = new JLabel("Transfer ");
    JLabel label2 = new JLabel("Name");
    JLabel label3 = new JLabel("Amount");
    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField();
    JLabel label4 = new JLabel("Account  Number");
    JLabel label5 = new JLabel("Currency");
    JLabel hint = new JLabel("(Checking account will charge 3 USD)");
    JTextField tf3 = new JTextField();
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    String[] currencyList = { "$", "¥", "€" };
    JComboBox currencyCB = new JComboBox(currencyList);

    public Transfer(int type){
        GUI.TransferGUIAL(this, type);
        j.setLayout(new GridBagLayout());
        label0 = new JLabel("Welcome, " + SystemApp.currentCustomer.getPerInfomation().getName());
        label0.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,50,0,0);
        j.add(label0, c);
        
        label1.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);
        j.add(label1, c);
        
        hint.setFont(new Font("Black",Font.CENTER_BASELINE,15));
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        j.add(hint, c);

        label2.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,100,0,0);
        j.add(label2, c);

        tf1.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0,0,0,50);
        j.add(tf1, c);

        
        label3.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0,100,0,0);
        j.add(label3, c);
       
        tf2.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0,0,0,50);
        j.add(tf2, c);

        
        label4.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0,100,0,0);
        j.add(label4, c);
        
        tf3.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(0,0,0,50);
        j.add(tf3, c);

        label5.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0,100,0,0);
        j.add(label5, c);

        currencyCB.setFont(font);
        currencyCB.setSelectedIndex(0);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(0,0,0,100);
        j.add(currencyCB, c);

        confirm.setPreferredSize(dimension);
        confirm.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0,100,0,100);
        j.add(confirm, c);


        
        cancel.setPreferredSize(dimension);
        cancel.setFont(font);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 6;
        c.insets = new Insets(0,0,0,50);
        j.add(cancel, c);
        j.setSize(900,600);
        j.setResizable(false);
        j.setVisible(true);
    }
}
