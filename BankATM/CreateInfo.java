

import java.awt.*;
import javax.swing.*;

public class CreateInfo{
    JFrame j = new JFrame();
    JLabel name=new JLabel("Name:");
    JLabel cell=new JLabel("Cellphone:");
    JLabel address=new JLabel("Address:");
    JLabel title=new JLabel("Set personal information");
    JCheckBox checkBox = new JCheckBox("Do you have Collateral?");
    
    JTextField nameField=new JTextField(20);
    JTextField cellField=new JTextField(20);
    JTextField addressField=new JTextField(20);

    JButton next=new JButton("CONFIRM");
    JButton cancel=new JButton("CANCEL");
    String username;
    String password;
    
    public CreateInfo(String username, String password){
        this.username = username;
        this.password = password;
        GUI.CreateInfoGUIAL(this);
        j.setTitle("ATM");
        j.setSize(900,600);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gbaglayout=new GridBagLayout();
        GridBagConstraints constraints=new GridBagConstraints();
        j.setLayout(gbaglayout);

        title.setFont(new Font("Black",Font.CENTER_BASELINE,25));
        name.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        cell.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        address.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        nameField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        cellField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        addressField.setFont(new Font("Black",Font.CENTER_BASELINE,20));
        checkBox.setFont(new Font("Black",Font.CENTER_BASELINE,15));
        next.setFont(new Font("Black",Font.CENTER_BASELINE,15));
        cancel.setFont(new Font("Black",Font.CENTER_BASELINE,15));

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(0,0,0,0);
        j.add(title, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0,100,0,0);
        j.add(name, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0,0,0,50);
        j.add(nameField, constraints);



        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,100,0,0);
        j.add(cell, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,0,50);
        j.add(cellField, constraints);



        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(0,100,0,0);
        j.add(address, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0,0,0,50);
        j.add(addressField, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(0,100,0,0);
        j.add(checkBox, constraints);


        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(0,100,0,100);
        j.add(next, constraints);




        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.insets = new Insets(0,0,0,100);
        j.add(cancel, constraints);
        j.setSize(900,600);
        j.setVisible(true);

    }

}