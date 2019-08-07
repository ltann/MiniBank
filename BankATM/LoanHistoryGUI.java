import javax.swing.*;
import java.awt.*;
public class LoanHistoryGUI {
	JFrame j = new JFrame();
	JLabel header = new JLabel("Loan History");
	JTable t;
	JScrollPane scroll;
	String[] columnTitle = {"ID", "Interest Rate", "Amount", "Type", "Bought Time"};
	JButton pay = new JButton("Payback");
    JButton ret = new JButton("Return");
	    
	public LoanHistoryGUI() {
		GUI.LoanHistoryGUIAL(this);
		header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(360,25,200,50);
        if(SystemApp.getUserLoan() != null) {
        	t = new JTable(SystemApp.getUserLoan(), columnTitle);
        }
        scroll = new JScrollPane(t);
        scroll.setBounds(100,100,690,300);


        pay.setBounds(150, 450, 150, 50);
        pay.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        ret.setBounds(575, 450, 150, 50);
        ret.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        
        j.add(header);
        j.add(scroll);
        j.add(pay);
        j.add(ret);
        j.setSize(900, 600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
	}


}
