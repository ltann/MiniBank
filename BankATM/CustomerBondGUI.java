import javax.swing.*;
import java.awt.*;

public class CustomerBondGUI {
	JFrame j = new JFrame();
	JLabel header = new JLabel("Bonds");
	JLabel amountTitle = new JLabel("Amount:");
	JTable t;
	JScrollPane scroll;
	String[] columnTitle = {"ID", "Interest", "Maturity", "Type"};
	JTextField amount = new JTextField();
	JButton buy = new JButton("Buy");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    
	public CustomerBondGUI() {
		GUI.CustomerBondGUIAL(this);
		header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(400,25,200,50);
        t = new JTable(SystemApp.getBondsData(), columnTitle);
        scroll = new JScrollPane(t);
        scroll.setBounds(100,100,700,250);
        
        amountTitle.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        amountTitle.setBounds(150,375,100,50);
        amount.setBounds(250, 375, 350, 50);
        buy.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        buy.setBounds(650, 375, 100, 50);
        ret.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        ret.setBounds(150, 450, 150, 50);
        exit.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        exit.setBounds(575, 450, 150, 50);
        
        j.add(header);
        j.add(scroll);
        j.add(amountTitle);
        j.add(amount);
        j.add(buy);
        j.add(ret);
        j.add(exit);
        j.setSize(900, 600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
	}
}
