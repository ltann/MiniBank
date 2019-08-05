import javax.swing.*;
import java.awt.*;

public class LoanGUI {
    JFrame j = new JFrame();
    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Black",Font.CENTER_BASELINE,30);
    Dimension dimension = new Dimension(100,80);
    JLabel welcome = new JLabel("Welcome, " + "name");
    JLabel header = new JLabel("Loan");
    JLabel l1 = new JLabel("1.   0.7% / month    > 12 month");
    JLabel l2 = new JLabel("2.   1% / month    6 - 12 month");
    JLabel l3 = new JLabel("3.   2% / month    1 - 5 month");
    JLabel amountTitle = new JLabel("Amount:");
    String[] currencyList = { "$", "¥", "€" };
    String[] timeList = {"1 mon","2 mon","3 mon","4 mon","5 mon","6 mon","7 mon","8 mon","9 mon","10 mon","11 mon","12 mon"};
    JComboBox currencyCB = new JComboBox(currencyList);
    JComboBox timeCB = new JComboBox(timeList);
    JTextField amount = new JTextField();
    JButton history = new JButton("History");
    JButton confirm = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    public LoanGUI(Customer customer, Account a){
        GUI.LoanGUIAL(this, a, customer);
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
        j.setVisible(true);
    }
    public static void main(String[] args) {
    	String str = "Eric";
        Customer c = new Customer(str, "123456", str, "1111111111", "abc", true);
        SystemApp.addUser(str, "123456", str, "1111111111", "abc", 1, true);
        SystemApp.addAccount(3, c);
    	new LoanGUI(c, c.getAcc().get(0));
    }
}
