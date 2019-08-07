import javax.swing.*;
import java.awt.*;

public class LoanGUI {
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Black",Font.CENTER_BASELINE,30);
    Dimension dimension = new Dimension(100,80);
    JLabel welcome = new JLabel("Welcome, " + "name");
    JLabel header = new JLabel("Loan");
    JLabel l1 = new JLabel("1.   0.7% / day    3 month");
    JLabel l2 = new JLabel("2.   1% / day    2 month");
    JLabel l3 = new JLabel("3.   2% / day   1 month");
    JLabel amountTitle = new JLabel("Amount:");
    //String[] currencyList = { "$", "¥", "€" };
    String[] currencyList = { "$"};
    String[] timeList = {"3 mon","2 mon","1 mon"};
    JComboBox currencyCB = new JComboBox(currencyList);
    JComboBox timeCB = new JComboBox(timeList);
    JTextField amount = new JTextField();
    JButton history = new JButton("History");
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    public LoanGUI(){
        GUI.LoanGUIAL(this);
        j.setLayout(null);
        
        welcome.setFont(font);
        welcome.setBounds(50, 50, 250, 50);
        header.setFont(font);
        header.setBounds(375, 50, 100, 50);
        l1.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        l1.setBounds(150, 150, 300, 50);
        l2.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        l2.setBounds(150, 210, 300, 50);
        l3.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        l3.setBounds(150, 270, 300, 50);
        amountTitle.setFont(font);
        amountTitle.setBounds(100, 375, 150, 50);
        amount.setFont(font);        
        amount.setBounds(275,375,300,50);
        currencyCB.setFont(font);
        currencyCB.setBounds(600,375,200,50);
        currencyCB.setSelectedIndex(0);
        timeCB.setFont(font);
        timeCB.setBounds(600,275,200,50);
        timeCB.setSelectedIndex(0);
        history.setBounds(650, 50, 150, 50);
        history.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        confirm.setBounds(200, 475, 150, 50);
        confirm.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        cancel.setBounds(550, 475, 150, 50);
        cancel.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        
        j.add(welcome);
        j.add(header);
        j.add(l1);
        j.add(l2);
        j.add(l3);
        j.add(amountTitle);
        j.add(amount);
        j.add(currencyCB);
        j.add(timeCB);
        j.add(history);
        j.add(confirm);
        j.add(cancel);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setResizable(false);
        j.setVisible(true);
    }

}
