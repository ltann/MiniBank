import java.awt.Font;
import javax.swing.*;

public class BankerStockGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Stocks");
    JTable stock;
    JScrollPane scroll;
    //JButton apply = new JButton("Update");
    JButton cancel = new JButton("Cancel");
    JButton createStock = new JButton("Create Stock");
    String[] columnTitle = {"TICKER", "NAME", "PRICE"};

    public BankerStockGUI() {
    	GUI.BankerStockGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE, 35));
        header.setBounds(400,25,200,50);
        createStock.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        createStock.setBounds(150,450,250,50);
        cancel.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        cancel.setBounds(550,450,150,50);
        //createStock.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        //createStock.setBounds(600,25,200,50);
        stock = new JTable(SystemApp.getBankerStock(), columnTitle);
        scroll = new JScrollPane(stock);
        scroll.setBounds(100,100,700,300);

        j.add(header);
        //j.add(apply);
        j.add(cancel);
        j.add(scroll);
        j.add(createStock);
        j.setSize(900,600);
        j.setLayout(null);
        j.setResizable(false);
        j.setTitle("Trading System");
        j.setVisible(true);
    }
    public static void main(String[] args) {
        
        new BankerStockGUI();
    }
}