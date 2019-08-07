import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class balanceGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Account Information");
    JLabel accNum = new JLabel("Account Number");
    JLabel number;
    JLabel accType = new JLabel("Account Type");
    JLabel type;
    JLabel accName = new JLabel("Name");
    JLabel name;
    JLabel accBal = new JLabel("Account Balance");
    JTextArea balance;
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    private String curr(int type) {
        String str = "";
        if(type == 3) {
        	str += "USD";
            str += ": ";
            str += "$";
            str += String.format("%.2f",SystemApp.database.dataFindSecurityAccount(SystemApp.currentCustomer.getLoginName()).getAvaliableFunds());
            str += "\n";
        }
        else {
        	AccountDB a = SystemApp.database.dataFindAccount(SystemApp.currentAccount.getAccountNumber());
        	Map<String, Double> cur = a.getCurrency();
        	str += "USD";
            str += ": ";
            str += "$";
            str += String.format("%.2f",cur.get("USD"));
            str += "\n";
            str += "RMB";
            str += ": ";
            str += "¥";
            str += String.format("%.2f",cur.get("RMB"));
            str += "\n";
            str += "EUR";
            str += ": ";
            str += "€";
            str += String.format("%.2f",cur.get("EUR"));
            str += "\n";
        }
        
        return str;
    }
    public balanceGUI(int t) {
        GUI.balanceGUIAL(this, t);
        if(t == 3) {
        	name = new JLabel(SystemApp.currentCustomer.getPerInfomation().getName());
            number = new JLabel("" + SystemApp.currentSecurity.getAccountNumber());
        }
        else {
        	name = new JLabel(SystemApp.currentCustomer.getPerInfomation().getName());
            number = new JLabel("" + SystemApp.currentAccount.getAccountNumber());
        }
        balance = new JTextArea(curr(t));
        if(t == 1) {
            type = new JLabel("Checking");
        }
        else if(t == 2){
            type = new JLabel("Saving");
        }
        else if(t == 3) {
            type = new JLabel("Security");
        }
        else {
            type = new JLabel("Default");
        }
        
        ret.setBounds(100,450,150,50);
        exit.setBounds(650,450,150,50);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(325,50,300,50);
        accNum.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        accNum.setBounds(200,125,200,25);
        accType.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        accType.setBounds(200,175,200,25);
        accName.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        accName.setBounds(200,225,200,25);
        accBal.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        accBal.setBounds(200,275,200,25);
        number.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        number.setBounds(550,125,200,25);
        type.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        type.setBounds(550,175,200,25);
        name.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        name.setBounds(550,225,200,25);
        balance.setFont(new Font("Black", Font.CENTER_BASELINE,15));
        balance.setOpaque(true);
        balance.setBorder(null);
        balance.setBackground(null);
        balance.setEditable(false);
        balance.setBounds(550,275,200,100);

        j.add(header);
        j.add(accNum);
        j.add(accType);
        j.add(accName);
        j.add(balance);
        j.add(number);
        j.add(type);
        j.add(name);
        j.add(accBal);
        j.add(ret);
        j.add(exit);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
}