import java.awt.Font;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AccountValueGUI {
	JFrame j = new JFrame();
    ChartPanel panel;
    JFreeChart valueGraph;
    JLabel header = new JLabel("Account Value");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    DefaultCategoryDataset data;
    
    public AccountValueGUI() {
    	this.data = SystemApp.getAccountValueData(SystemApp.currentCustomer);
    	GUI.AccountValueGUIAL(this);
    	valueGraph = ChartFactory.createLineChart(
                "",
                "Time",
                "Value",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
            
        CategoryPlot mPlot = (CategoryPlot)valueGraph.getPlot();
        panel = new ChartPanel(valueGraph);
        panel.setBounds(50, 100, 775, 350);
        
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(325,25,250,50);
        ret.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        ret.setBounds(125, 475, 150, 50);
        exit.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        exit.setBounds(625, 475, 150, 50);
        
        j.add(header);
        j.add(panel);
        j.add(ret);
        j.add(exit);
        j.setSize(900, 600);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
//    public static void main(String[] args) {
//    	
//        new AccountValueGUI(first);
//    }
}
