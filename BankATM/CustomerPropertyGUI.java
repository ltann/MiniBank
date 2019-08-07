import javax.swing.*;
import java.awt.Font;

public class CustomerPropertyGUI {
	JFrame j = new JFrame();
	JLabel header = new JLabel("Property");
	JTable t;
	JScrollPane scroll;
    String[] stockTitle = {"TICKER", "NAME", "BUY PRICE", "NOW PRICE", "SHARE", "PERCENTAGE", "TOTAL VALUE"};
    String[] bondTitle = {"ID", "Maturity", "Type", "Amount", "Interest", "Days Matured"};
    
    JButton stock = new JButton("Stocks");
    JButton bond = new JButton("Bonds");
    JButton value = new JButton("Account Value");
    JButton sell = new JButton("Sell");
    JButton cancel = new JButton("Cancel");
    int index;
    
    public CustomerPropertyGUI(int index) {
    	GUI.CustomerPropertyGUIAL(this);
    	this.index = index;
    	header.setFont(new Font("Black", Font.CENTER_BASELINE, 30));
        header.setBounds(400,25,200,50);
        
        stock.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        stock.setBounds(25,150,150,50);
        bond.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        bond.setBounds(25,300,150,50);
        value.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        value.setBounds(650,25,200,50);
        
        if(index == 0) { //Stock
        	Object[][] data = SystemApp.getUserStock(SystemApp.currentCustomer);
        	if(data != null) {
        		t = new JTable(data, stockTitle);
        	}
        }
        else {
        	Object[][] data = SystemApp.getUserBonds(SystemApp.currentCustomer);
        	if(data != null) {
        		t = new JTable(data, bondTitle);
        	}
        }
        scroll = new JScrollPane(t);
        scroll.setBounds(200,100,650,300);
        
        sell.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        sell.setBounds(150, 450, 150, 50);
        cancel.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        cancel.setBounds(575, 450, 150, 50);
        
        j.add(header);
        j.add(stock);
        j.add(bond);
        j.add(value);
        j.add(scroll);
        j.add(sell);
        j.add(cancel);
        j.setSize(900, 600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
//    public static void main(String[] args) {
//        Object[][] data = {
//            new Object[]{"APPR", "APPLE", 100.25, 110, 20, "15%", 110*20},
//            new Object[]{"MICR", "MICROSOFT", 57.26, 48.6, 50, "-30%", 48.6*50}
//        };
//        Customer c = new Customer();
//        Account a = new Account();
//        new CustomerPropertyGUI(data, 0, c, a, "name");
//    }
}
