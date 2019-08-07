import java.awt.Font;
import javax.swing.*;

public class CreateAccountGUI {
    JFrame j = new JFrame();
    JButton check = new JButton("Checking");
    JButton save = new JButton("Saving");
    JButton security = new JButton("Security");
    JButton cancel = new JButton("cancel");
    JLabel header = new JLabel("Please choose your account type!");

    public CreateAccountGUI(Customer c) {
        GUI.CreateAccountGUIAL(this, c);
        j.setSize(900,600);
        check.setBounds(100, 250, 200, 50);
        check.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        save.setBounds(350, 250, 200, 50);
        save.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        security.setBounds(600, 250, 200, 50);
        security.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        cancel.setBounds(650, 450, 150, 50);
        cancel.setFont(new Font("Black", Font.CENTER_BASELINE, 20));
        header.setBounds(200, 100, 500, 50);
        header.setFont(new Font("Black", Font.CENTER_BASELINE, 30));

        j.add(check);
        j.add(save);
        j.add(security);
        j.add(cancel);
        j.add(header);
        j.setLayout(null);
        j.setResizable(false);
        j.setVisible(true);
    }
}