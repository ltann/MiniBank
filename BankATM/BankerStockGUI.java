import java.awt.Font;
import javax.swing.*;

public class BankerStockGUI {
    JFrame j = new JFrame();
    JLabel header = new JLabel("Stocks");
    JTable stock;
    JScrollPane scroll;
    JButton apply = new JButton("Update");
    JButton cancel = new JButton("Cancel");
    String[] columnTitle = {"TICKER", "NAME", "PRICE", "PERCENTAGE"};

    public BankerStockGUI() {
    	GUI.BankerStockGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE, 25));
        header.setBounds(400,50,200,35);
        apply.setBounds(200,450,100,50);
        cancel.setBounds(550,450,100,50);
        stock = new JTable(SystemApp.getBankerStock(), columnTitle);
        scroll = new JScrollPane(stock);
        scroll.setBounds(100,100,700,300);

        j.add(header);
        j.add(apply);
        j.add(cancel);
        j.add(scroll);
        j.setSize(900,600);
        j.setLayout(null);
        j.setTitle("Trading System");
        j.setVisible(true);
    }
    public static void main(String[] args) {
        Object[][] data = {
            new Object[]{"APPR", "APPLE", 100.25, "15%"},
            new Object[]{"MICR", "MICROSOFT", 57.26, "-30%"}
        };
        new BankerStockGUI();
    }
}