import javax.swing.*;
import java.awt.*;

public class SucceedInfoGUI {
    JFrame j = new JFrame("Banker Report");
    JTextArea area = new JTextArea("Report text");
    JButton ret = new JButton("Return");
    JButton exit = new JButton("Exit");
    JLabel header = new JLabel("Succeed!");

    public SucceedInfoGUI(boolean b, String str) {
        GUI.SucceedInfoGUIAL(this);
        if(!b) {
            header.setText("Failed!");
        }
        else {
            
        }
        j.setSize(900,600);
        header.setFont(new Font("Black", Font.CENTER_BASELINE,20));
        header.setBounds(400,25,200,50);
        area.setBounds(100,100,700,300);
        area.setEditable(false);
        area.setText(str);
        ret.setBounds(100,450,150,50);
        exit.setBounds(650,450,150,50);
        j.add(area);
        j.add(ret);
        j.add(exit);
        j.add(header);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
}