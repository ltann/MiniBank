import javax.swing.*;
import java.awt.*;

public class CustomerMainGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Main Menu");
    JButton balance = new JButton("Balance");
    JButton withdraw = new JButton("Withdraw");
    JButton deposit = new JButton("Deposit");
    JButton transfer = new JButton("Transfer");
    JButton loan = new JButton("Loan");
    JButton exit = new JButton("Exit");
    public CustomerMainGUI() {
        GUI.customerMainGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(360,25,300,50);
        balance.setBounds(0,150,250,80);
        balance.setFont(new Font("Black", Font.BOLD,20));
        withdraw.setBounds(0,300,250,80);
        withdraw.setFont(new Font("Black", Font.BOLD,20));
        deposit.setBounds(0,450,250,80);
        deposit.setFont(new Font("Black", Font.BOLD,20));
        transfer.setBounds(640,150,250,80);
        transfer.setFont(new Font("Black", Font.BOLD,20));
        loan.setBounds(640,300,250,80);
        loan.setFont(new Font("Black", Font.BOLD,20));
        exit.setBounds(640,450,250,80);
        exit.setFont(new Font("Black", Font.BOLD,20));
        
        j.add(header);
        j.add(balance);
        j.add(withdraw);
        j.add(deposit);
        j.add(transfer);
        j.add(loan);
        j.add(exit);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setResizable(false);
        j.setLayout(null);
        j.setVisible(true);
    }
}