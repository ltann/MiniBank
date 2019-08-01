import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class search {
    JLabel title = new JLabel("Search a specific customer");
    JLabel question = new JLabel("Enter customer name: ");
    JButton search = new JButton("SEARCH");
    JButton cancel = new JButton("CANCEL");
    JFrame j = new JFrame();
    JTextField searchName = new JTextField(50);

    public search() {
        GUI.searchGUIAL(this);
        j.setTitle("ATM");
        j.setSize(900, 600);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(null);

        title.setFont(new Font("Black", Font.ITALIC, 30));
        question.setFont(new Font("Black", Font.ITALIC, 20));

        title.setBounds(250, 50, 400, 50);
        question.setBounds(175, 200, 300, 50);
        searchName.setBounds(400, 200, 200, 50);
        search.setBounds(250, 400, 150, 50);
        cancel.setBounds(450, 400, 150, 50);

        j.add(title);
        j.add(question);
        j.add(searchName);
        j.add(search);
        j.add(cancel);
        j.setVisible(true);
    }
}