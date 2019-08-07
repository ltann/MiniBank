import javax.swing.*;
import java.awt.*;

 public class CustomerMain {
     JFrame j = new JFrame();
     GridBagConstraints c = new GridBagConstraints();
     Font font = new Font("Black",Font.CENTER_BASELINE,25);
     Dimension dimension = new Dimension(100,50);
     JLabel label;
     JButton create = new JButton("Create Account");
     DefaultListModel listModel = new DefaultListModel();
     JList sourceList = new JList();
     
     JButton apply = new JButton("APPLY");
     JButton close = new JButton("Close Account");
     JButton exit = new JButton("EXIT");

     public CustomerMain(String name, Customer customer){
         GUI.CustomerMainAL(this, customer, name);
         j.setLayout(new GridBagLayout());
         label = new JLabel("Welcome, " + name);
         label.setFont(font);
         c.weightx = 0.5;
         c.weighty = 0.5;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 0;
         c.insets = new Insets(0,100,0,0);
         j.add(label, c);


         create.setPreferredSize(new Dimension(150,50));
         create.setFont(font);
         c.weightx = 0.5;
         c.weighty = 0.5;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 2;
         c.gridy = 0;
         c.insets = new Insets(0,0,0,0);
         j.add(create, c);

         listModel.setSize(6);
         for(int i = 0; i < customer.getAcc().size(); i++){
             if(customer.getAcc().get(i).getType() == 1){
                 listModel.addElement("Checking Account: " + customer.getAcc().get(i).getAccountNumber());
             }else if(customer.getAcc().get(i).getType() == 2){
                 listModel.addElement("Saving Account: " + customer.getAcc().get(i).getAccountNumber());
             } 
             else{
                 listModel.addElement("Default Account: " + customer.getAcc().get(i).getAccountNumber());
             }
         }
        	 if(customer.getSacc() != null){
                 listModel.addElement("Security Account: " + customer.getSacc().getAccountNumber());
              }
         
         sourceList.setModel(listModel);
         sourceList.setFont(font);
         sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         JScrollPane sourceListScroller = new JScrollPane(sourceList);
         sourceListScroller.setPreferredSize(new Dimension(120, 300));
         sourceListScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         c.weightx = 1;
         c.weighty = 1;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridwidth = 3;
         c.gridx = 0;
         c.gridy = 1;
         c.insets = new Insets(0,0,0,0);
         j.add(sourceListScroller, c);


         close.setPreferredSize(dimension);
         close.setFont(font);
         c.weightx = 0.5;
         c.weighty = 0.5;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridwidth = 1;
         c.gridx = 0;
         c.gridy = 2;
         c.insets = new Insets(0,0,0,100);
         j.add(close, c);

         apply.setPreferredSize(dimension);
         apply.setFont(font);
         c.weightx = 0.5;
         c.weighty = 0.5;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridwidth = 1;
         c.gridx = 1;
         c.gridy = 2;
         c.insets = new Insets(0,0,0,100);
         j.add(apply, c);


         exit.setPreferredSize(dimension);
         exit.setFont(font);
         c.weightx = 0.5;
         c.weighty = 0.5;
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 2;
         c.gridy = 2;
         c.insets = new Insets(0,50,0,50);
         j.add(exit, c);
         j.setTitle("ATM");
         j.setSize(900,600);
         j.setVisible(true);
     }
 }
