import java.awt.Font;

import javax.swing.*;

public class CreateStockGUI {
	JFrame j = new JFrame();
	JLabel header = new JLabel("Create Stock");
	JLabel tickerTitle = new JLabel("Ticker");
	JLabel nameTitle = new JLabel("Company Name");
	JLabel priceTitle = new JLabel("Price");
	
	JTextField ticker = new JTextField();
	JTextField name = new JTextField();
	JTextField price = new JTextField();
	
	JButton apply = new JButton("Apply");
	JButton cancel = new JButton("Cancel");
	
	public CreateStockGUI() {
		GUI.CreateStockGUIAL(this);
		header.setBounds(350, 25, 200, 50);
		header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
		tickerTitle.setBounds(200, 125, 100, 50);
		tickerTitle.setFont(new Font("Black", Font.CENTER_BASELINE,25));
		nameTitle.setBounds(125, 225, 250, 50);
		nameTitle.setFont(new Font("Black", Font.CENTER_BASELINE,25));
		priceTitle.setBounds(200, 325, 100, 50);
		priceTitle.setFont(new Font("Black", Font.CENTER_BASELINE,25));
		
		ticker.setBounds(330, 125, 400, 50);
		name.setBounds(330, 225, 400, 50);
		price.setBounds(330, 325, 400, 50);
		
		apply.setBounds(125, 425, 150, 50);
		apply.setFont(new Font("Black", Font.CENTER_BASELINE,25));
		cancel.setBounds(600, 425, 150, 50);
		cancel.setFont(new Font("Black", Font.CENTER_BASELINE,25));
		
		j.add(header);
		j.add(tickerTitle);
		j.add(nameTitle);
		j.add(priceTitle);
		j.add(ticker);
		j.add(name);
		j.add(price);
		j.add(apply);
		j.add(cancel);
		
		j.setSize(900, 600);
		j.setLayout(null);
		j.setResizable(false);
		j.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CreateStockGUI();
	}
}
