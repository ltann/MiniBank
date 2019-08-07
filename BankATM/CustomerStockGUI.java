import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class CustomerStockGUI {
    JFrame j = new JFrame();
    ChartPanel panel;
    JLabel header = new JLabel("Stock");
    JList stockName = new JList();
    DefaultListModel listModel = new DefaultListModel();
    JScrollPane scrollPane;
    JFreeChart stockGraph;
    JTextField amount = new JTextField();
    JButton check = new JButton("View");
    JButton buy = new JButton("Buy");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    JButton view = new JButton("View");
    ArrayList<DefaultCategoryDataset> data;
    String[] nameList;

    public CustomerStockGUI(int index) {
        this.data = SystemApp.getStockData();
        this.nameList = SystemApp.getStockNameList();
    	GUI.CustomerStockGUIAL(this);
        stockGraph = ChartFactory.createLineChart(
            "K-Line Graph",
            "Time",
            "Price/share",
            data.get(index),
            PlotOrientation.VERTICAL,
            true,
            true,
            false);
        
        CategoryPlot mPlot = (CategoryPlot)stockGraph.getPlot();
        panel = new ChartPanel(stockGraph);
        panel.setBounds(350, 75, 480, 320);
        panel.setBackground(null);
        
        header.setFont(new Font("Black", Font.CENTER_BASELINE,30));
        header.setBounds(400,5,200,50);
        //stockName = new JList(nameList);
        //listModel.setSize(10);
        for(int i = 0; i < nameList.length; i++) {
        	listModel.addElement(nameList[i]);
        }
        stockName.setModel(listModel);
        stockName.setSelectedIndex(index);
        stockName.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        stockName.setBounds(0, 75, 300, 375);
        stockName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(stockName);
        view.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        view.setBounds(100, 475, 100, 50);
        amount.setBounds(400, 420, 300, 50);
        buy.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        buy.setBounds(725, 420, 100, 50);
        ret.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        ret.setBounds(400, 490, 150, 50);
        exit.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        exit.setBounds(650, 490, 150, 50);
        
        j.add(header);
        j.add(stockName);
        j.add(panel);
        j.add(view);
        j.add(amount);
        j.add(buy);
        j.add(ret);
        j.add(exit);
        j.setLayout(null);
        j.setResizable(false);
        j.setSize(900,600);
        j.setVisible(true);
    }
//    public static void main(String[] args) {
//    	ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
//      DefaultCategoryDataset first = new DefaultCategoryDataset();
//      DefaultCategoryDataset second = new DefaultCategoryDataset();
//      first.addValue(1, "First", "2013");
//      first.addValue(3, "First", "2014");
//      first.addValue(2, "First", "2015");
//      first.addValue(6, "First", "2016");
//      first.addValue(5, "First", "2017");
//      first.addValue(12, "First", "2018");
//      second.addValue(14, "Second", "2013");
//      second.addValue(13, "Second", "2014");
//      second.addValue(12, "Second", "2015");
//      second.addValue(9, "Second", "2016");
//      second.addValue(5, "Second", "2017");
//      second.addValue(7, "Second", "2018");
//      data.add(first);
//      data.add(second);
//      String[] nameList = {"First", "Second"};
//  		new CustomerStockGUI(data, nameList, 0);
//    }
}