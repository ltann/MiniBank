import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReportGUI {
    JFrame j = new JFrame("Banker Report");
    JTextArea area = new JTextArea("Report text");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    JLabel header = new JLabel("Daily Report");
    JLabel profit = new JLabel();
    JScrollPane sp = new JScrollPane(area);
    public ReportGUI(String str) {
        GUI.ReportGUIAL(this);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        header.setBounds(400,25,200,50);
        
        area.setEditable(false);
        area.setText(str);
        ret.setBounds(100,450,150,50);
        exit.setBounds(650,450,150,50);
        profit.setText("Profit: " + SystemApp.fee);
        profit.setBounds(650,50,150,50);
        profit.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBounds(100,100,700,300);
        j.setSize(900,600);
        //j.add(area);
        j.add(ret);
        j.add(exit);
        j.add(header);
        j.add(profit);
        j.add(sp);
        j.setResizable(false);
        j.setLayout(null);
        j.setVisible(true);
    }
}