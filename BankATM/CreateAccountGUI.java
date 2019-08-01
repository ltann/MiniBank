import java.awt.Font;

import javax.swing.*;

public class CreateAccountGUI {
    JFrame j = new JFrame();
    JButton check = new JButton("Checking Account");
    JButton save = new JButton("Saving Account");
    JButton cancel = new JButton("cancel");
    JLabel header = new JLabel("Please choose your account type!");

    public CreateAccountGUI(Customer c) {
        GUI.CreateAccountGUIAL(this, c);
        j.setSize(900,600);
        check.setBounds(125, 250, 250, 50);
        check.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        save.setBounds(525, 250, 250, 50);
        save.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        cancel.setBounds(650, 450, 150, 50);
        cancel.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        header.setBounds(200, 100, 500, 50);
        header.setFont(new Font("Black", Font.CENTER_BASELINE, 30));

        j.add(check);
        j.add(save);
        j.add(cancel);
        j.add(header);
        j.setLayout(null);
        j.setVisible(true);
    }
}