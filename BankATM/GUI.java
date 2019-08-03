import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GUI {
    public static void customerMainGUIAL(CustomerMainGUI c, Customer cus, Account a, String str) {
        c.balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new balanceGUI(a, str, cus);
            }
        });
        
        c.withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new withdrawGUI(a, cus, str);
            }
        });
        
        c.deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new depositGUI(a, cus, str);
            }
        });
        
        c.transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new Transfer(str, cus, a);
            }
        });
        
        c.loan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new LoanGUI(str, cus, a, str, a.getAccountNumber());
            }
        });
        
        c.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(c.j);
            }
        });
    }

    public static void SecurityMainGUIAL(SecurityMainGUI s, Customer cus, Account a) {
        s.balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s.j.setVisible(false);
                new balanceGUI(a, cus.getPerInfomation().getName(), cus);
            }
        });
        
         s.property.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 s.j.setVisible(false);
                 new CustomerPropertyGUI(0, cus, a);
             }
         });
        
         s.stocks.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 s.j.dispose();
                 new CustomerStockGUI(0, cus, a);
             }
         });
        s.bonds.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  s.j.setVisible(false);
                  new CustomerBondGUI(cus, a);
              }
          });
        s.transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s.j.setVisible(false);
                new Transfer(cus.getPerInfomation().getName(), cus, a);
            }
        });
        
        
        
        s.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(s.j);
            }
        });
    }

    public static void depositGUIAL(depositGUI d, Account a, Customer c, String str) {
        d.amount.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                d.amount.setText("");
            }
            public void focusLost(FocusEvent e) {
                if(d.amount.getText().equals("")) {
                   d.amount.setText("Please type your deposit amount here! (0-50000)");
                }
            }
        });
        d.confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double money = Double.parseDouble(d.amount.getText());
                if(money >= 0 && money <= 50000) {
                    a.getC()[d.currencyType.getSelectedIndex()].deposit(money);
                    d.j.setVisible(false);
                    d.j.dispose();
                    new SucceedInfoGUI(true, a, SystemApp.depositReport(a, money, d.currencyType.getSelectedIndex()),c);
                }
                else {
                    d.amount.setText("Please type your deposit amount! (0-50000)");
                }
            }
        });
        d.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.j.dispose();
                new CustomerMainGUI(a, str, c);
            }
        });
    }

    public static void withdrawGUIAL(withdrawGUI wd, Account a, Customer c, String str) {
        wd.amount.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                wd.amount.setText("");
            }
            public void focusLost(FocusEvent e) {
                if(wd.amount.getText().equals("")) {
                    wd.amount.setText("Please type your withdraw amount!");
                }
            }
        });
        wd.confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double money = Double.parseDouble(wd.amount.getText());
                if(money >= 0 && money <= 50000) {
                    if(a.getType() == 1) {
                        double fee = 0;
                        switch (wd.currencyType.getSelectedIndex()) {
                            case 0:
                                fee = 3;
                                break;
                            case 1:
                                fee = 21;
                                break;
                            case 2:
                                fee = 2.7;
                                break;
                            default:
                                fee = 3;
                                break;
                        }
                        if(a.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= (money+fee)) {
                            a.getC()[wd.currencyType.getSelectedIndex()].withdraw(money+fee);
                            SystemApp.fee += 3;
                            wd.j.setVisible(false);
                            wd.j.dispose();

                            new SucceedInfoGUI(true, a, SystemApp.depositReport(a, money, wd.currencyType.getSelectedIndex()), c);
                        }
                        else {
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(false, a, SystemApp.depositReport(a, money, wd.currencyType.getSelectedIndex()), c);
                        }
                    }
                    else{
                        if(a.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= (money)) {
                            a.getC()[wd.currencyType.getSelectedIndex()].withdraw(money);
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(true, a, SystemApp.depositReport(a, money, wd.currencyType.getSelectedIndex()), c);
                        }
                        else {
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(false, a, SystemApp.depositReport(a, money, wd.currencyType.getSelectedIndex()), c);
                        }
                    }
                       
                }
                else {
                    wd.amount.setText("Please type your withdraw amount here!");
                }
            }
        });
        wd.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wd.j.dispose();
                new CustomerMainGUI(a, str, c);
            }
        });
        wd.d_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a.getType() == 1) {
                    if(a.getC()[0].getBalance() >= 23) {
                        a.getC()[0].withdraw(23);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 20, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 20, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                else {
                    if(a.getC()[0].getBalance() >= 20) {
                        a.getC()[0].withdraw(20);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 20, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 20, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                
            }
        });
        wd.d_50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a.getType() == 1) {
                    if(a.getC()[0].getBalance() >= 53) {
                        a.getC()[0].withdraw(53);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 50, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 50, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                else {
                    if(a.getC()[0].getBalance() >= 50) {
                        a.getC()[0].withdraw(50);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 50, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 50, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                
            }
        });
        wd.d_100.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a.getType() == 1) {
                    if(a.getC()[0].getBalance() >= 103) {
                        a.getC()[0].withdraw(103);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 100, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 100, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                else {
                    if(a.getC()[0].getBalance() >= 100) {
                        a.getC()[0].withdraw(100);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 100, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 100, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                    
            }
        });
        wd.d_200.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a.getType() == 1) {
                    if(a.getC()[0].getBalance() >= 203) {
                        a.getC()[0].withdraw(203);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 200, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 200, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                else {
                    if(a.getC()[0].getBalance() >= 200) {
                        a.getC()[0].withdraw(200);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 200, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 200, wd.currencyType.getSelectedIndex()), c);
                    }
                }
            }
        });
        wd.d_500.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a.getType() == 1) {
                    if(a.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= 503) {
                        a.getC()[0].withdraw(503);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 500, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 500, wd.currencyType.getSelectedIndex()), c);
                    }
                }
                else {
                    if(a.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= 500) {
                        a.getC()[0].withdraw(500);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, a, SystemApp.depositReport(a, 500, wd.currencyType.getSelectedIndex()), c);
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, a, SystemApp.depositReport(a, 500, wd.currencyType.getSelectedIndex()), c);
                    }
                }
            }
        });
    }

    public static void balanceGUIAL(balanceGUI b, Account a, String str, Customer c) {
        b.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	b.j.setVisible(false);
                b.j.dispose();
            	if (a.getType() == 3) {
            		b.j.dispose();
            		new SecurityMainGUI(c, a);
            	}
            	else {
            		b.j.dispose();
                    new CustomerMainGUI(a, str, c);
            	}
                
            }
        });
        b.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(b.j);
            }
        });
    }

    public static void TransferGUIAL(Transfer tf, String str, Customer cus, Account a){
        tf.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.j.setVisible(false);
                tf.j.dispose();
                if(a.getType() == 3) {
                	new SecurityMainGUI(cus,a);
                }
                else {
                	new CustomerMainGUI(a, str, cus);
                }
               
            }
        });
        tf.confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double money = Double.parseDouble(tf.tf2.getText());
                int accountNumber = Integer.parseInt(tf.tf3.getText());
                if (tf.tf1.getText() == "") {
                    tf.tf1.setText("Please type name!");
                }
                else if (tf.tf2.getText() == "" || money < 0 || money > 50000) {
                    tf.tf2.setText("Please type amount! (0-50000)");
                }
                else if (tf.tf3.getText() == "" || accountNumber < 0 || accountNumber >= 10000) {
                    tf.tf3.setText("Please type account number!");
                }
                else {
                    String report = SystemApp.transaction(a.getAccountNumber(),accountNumber, money, cus, tf.currencyCB.getSelectedIndex());
                    if(report != "") {
                        tf.j.setVisible(false);
                        tf.j.dispose();
                        new SucceedInfoGUI(true, a, report, cus);
                    }
                    else {
                        tf.j.setVisible(false);
                        tf.j.dispose();
                        new SucceedInfoGUI(false, a, report, cus);
                    }
                }
            } 
        });
    }

    public static void SucceedInfoGUIAL(SucceedInfoGUI si, Account a, Customer c, String name) {
        si.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                si.j.setVisible(false);
                si.j.dispose();
                new CustomerMainGUI(a, name, c);
            }
        });
        si.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //si.j.setVisible(false);
                new exit(si.j);
            }
        });
    }
    
    public static void LoanGUIAL(LoanGUI l, Account a, Customer cus, String str, int accountNumber) {
        l.confirm.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //l.tf1.setText("Please type amount! (0-50000)");
                //System.out.println(l.tf1.getText());
                double money = Double.parseDouble(l.tf1.getText());
                if (l.tf1.getText() == "" || money < 0 || money > 50000) {
                    l.tf1.setText("Please type amount! (0-50000)");
                } else if(!cus.isCollateral()){
                    l.tf1.setText("Can't loan because you don't have collateral");
                }  else {
                    SystemApp.loan(l.timeCB.getSelectedIndex(), Double.parseDouble(l.tf1.getText()), l.currencyCB.getSelectedIndex()); 
                    l.j.setVisible(false);
                    l.j.dispose();
                    new SucceedInfoGUI(true, a, SystemApp.loanReport(Double.parseDouble(l.tf1.getText()), l.currencyList[l.currencyCB.getSelectedIndex()], l.timeList[l.timeCB.getSelectedIndex()]), cus);
                    
                }
            }
        });

        l.cancel.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                l.j.dispose();
                new CustomerMainGUI(a, str, cus);
            }
        });
        
    }

    public static void WelcomeGUIAL(Welcome w) {
        w.jbBanker.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Banker");
                w.dispose();
                new Login(0);
            }
        });
        
        w.jbCustomer.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Customer");
                w.dispose();
                new Login(1);
            }
        });
    }
   
    public static void CreateUserGUIAL(CreateUserGUI ca) {
        ca.next.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=ca.nameField.getText();
                System.out.println("name "+name);
                String password=ca.passwordField.getText();
                String again=ca.passwordAgField.getText();
                if(!password.equals(again)){
                    ca.passwordAgField.setText("Not the same password!");
                }
                else{
                    System.out.println("password "+password);
                    System.out.println("next!");
                    ca.j.dispose();
                    new CreateInfo(name, password);
                }
                
            }
        });
        
        ca.cancel.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                //dispose();
                new exit(ca.j);
                System.out.println("Cancel");
            }
        });
    }
   
    public static void CreateInfoGUIAL(CreateInfo ci) {
    ci.next.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String name=ci.nameField.getText();
            String cell=ci.cellField.getText();
            String address=ci.addressField.getText();
            boolean res = false;
            System.out.println("Name: "+name);
            System.out.println("Cell: "+cell);
            System.out.println("Address: "+address);
            if(cell.length()!=10){
                ci.cellField.setText("Wrong format");
            }
            else{
                if(ci.checkBox.isSelected()){
                    res = SystemApp.addUser(ci.username, ci.password, name, cell, address, 1,true);
                }else{
                    res = SystemApp.addUser(ci.username, ci.password, name, cell, address, 1,false);
                }
                if(res) {
                    JOptionPane.showMessageDialog(ci.j, "Succeed!");
                }
                else {
                    JOptionPane.showMessageDialog(ci.j, "Failed!");
                }
                ci.j.dispose();
                new Login(1);
            }
        }
    });
    ci.cancel.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            new exit(ci.j);
            System.out.println("Cancel");
        }
    });
   }
   
    public static void LoginGUIAL(Login lo) {
    lo.jbLogin.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String name=lo.nameField.getText();
            // System.out.println("name "+name);
            String password=String.valueOf(lo.passwordField.getPassword());
            // System.out.println("password "+password);
            // System.out.println("Login");
            if(SystemApp.checkUser(name, password, lo.type)) {
                lo.j.dispose();
                if(lo.type == 0) {
                    new BankerMain();
                }
                else {
                    new CustomerMain(name, SystemApp.customers.get(SystemApp.currentCustomer));
                }
            }
            else {
                lo.title.setText("Wrong username or password!");
            }      
        }
    });
    
    lo.jbCancel.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            new exit(lo.j);
            System.out.println("Cancel");
        }
    });
    
    lo.jbCreate.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            lo.j.dispose();
            new CreateUserGUI();
            System.out.println("New user");
        }
    });
   }
   
    public static void ExitGUI(exit ex) {
    ex.confirm.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ex.j.dispose();
            new Welcome();
            ex.dispose();
        }
    });
    
    ex.cancel.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            ex.dispose();
        }
    });
   }
   
    public static void BankerMainGUIAL(BankerMain bm) {
    bm.report.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            bm.j.dispose();
            new ReportGUI(SystemApp.report);
        }
    });
    bm.customers.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            bm.j.dispose();
            new BankerCustomerGUI(SystemApp.getInfoForBanker());
        }
    });
    bm.search.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            bm.j.dispose();
            new search();
        }
    });
    bm.stock.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            bm.j.dispose();
            new BankerStockGUI();
        }
    });
    bm.bond.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            bm.j.dispose();
            new BankerBondGUI();
        }
    });
    bm.exit.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            new exit(bm.j);
        }
    });
   }

    public static void searchGUIAL(search s) {
        s.search.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int key=0;
            String name = s.searchName.getText();
            String str = "";
            for (int i = 0; i <= SystemApp.customers.size() - 1; i++) {
                if (name.equals(SystemApp.customers.get(i).getPerInfomation().getName())) {
                    str += "Name : ";
                    str += SystemApp.customers.get(i).getPerInfomation().getName();
                    str += "   Cellphone : ";
                    str += SystemApp.customers.get(i).getPerInfomation().getCell();
                    str += "   Address : ";
                    str += SystemApp.customers.get(i).getPerInfomation().getAddress();
                    str += "\nAccount :\n";
                    for (int j = 0; j <= SystemApp.customers.get(i).getAccountNumber() - 1; j++) {
                        str += SystemApp.customers.get(i).getAcc().get(j).getAccountInfo();
                    }
                    str += "\nLoan :\n";
                    for (int k = 0; k <= SystemApp.customers.get(i).getLoanNumber() - 1; k++) {
                        str += SystemApp.customers.get(i).getLn().get(k).getInterest();
                        str += " ";
                        str += SystemApp.customers.get(i).getLn().get(k).getCurrency().getName();
                        str += " ";
                        str += SystemApp.customers.get(i).getLn().get(k).getCurrency().getSymbol();
                        str += SystemApp.customers.get(i).getLn().get(k).getCurrency().getBalance();
                        str += " ";
                    }
                    str += "\n";
                    key=1;
                    break;
                }
            }
            if(key==1){
                s.j.dispose();
                new ReportGUI(str);
            }
            else{
                s.title.setText("There is no such customer");
            }
        }
    });
    s.cancel.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            s.j.dispose();
            new BankerMain();
        }
    });
   }

    public static void BankerCustomerGUIAL(BankerCustomerGUI bc) {
    bc.ret.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bc.j.setVisible(false);
            bc.j.dispose();
            new BankerMain();
        }
    });
    bc.exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new exit(bc.j);
        }
    });
   }

    public static void ReportGUIAL(ReportGUI r) {
    r.ret.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            r.j.dispose();
            new BankerMain();
        }
    });
    r.exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new exit(r.j);
        }
    });
   }

    public static void CustomerMainAL(CustomerMain cmain, Customer c, String name){
    cmain.apply.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            String val = (String)cmain.sourceList.getSelectedValue();
            if(val == null){
                JOptionPane.showMessageDialog(null,"You need to create account first!");
            }else{
                String[] split = val.split(": ");
                int accountNum = Integer.parseInt(split[1]);
                for(int i = 0; i < c.getAcc().size(); i++){
                    if(c.getAcc().get(i).getAccountNumber() == accountNum){
                        cmain.j.dispose();
                        if(c.getAcc().get(i).getType() == 3) {
                            new SecurityMainGUI(c,c.getAcc().get(i));
                            break;
                        }
                        else {
                            new CustomerMainGUI(c.getAcc().get(i),name,c);
                            break;
                        }
                    }
                }
            }
        }
    });
    cmain.exit.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            new exit(cmain.j);
        }
    });
    cmain.create.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            cmain.j.dispose();
            new CreateAccountGUI(c);
        }
    });
    cmain.close.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(cmain.sourceList.getSelectedValue() != null) {
                if(c.getLoanNumber() > 0) {
                    JOptionPane.showMessageDialog(cmain.j, "You have laon, can't delete account!");
                }
                else {
                    int confirm = JOptionPane.showConfirmDialog(cmain.j,"Are you sure to delete this account?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        SystemApp.delAccount(c.getAcc().get(cmain.sourceList.getSelectedIndex()-6).getAccountNumber(), c);
                        cmain.j.dispose();
                        new CustomerMain(name, c);
                    }
                }
            }
        }
    });
}

    public static void CreateAccountGUIAL(CreateAccountGUI ca, Customer c) {
        ca.check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.addAccount(1, c);
                c.getAcc().get(c.getAccountNumber()-1).getC()[0].withdraw(3);
                SystemApp.fee += 3;
                ca.j.dispose();
                System.out.println(c.getAcc().size());
                new CustomerMain(c.getPerInfomation().getName(), c);
            }
        });
        ca.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.addAccount(2, c);
                c.getAcc().get(c.getAccountNumber()-1).getC()[0].withdraw(3);
                SystemApp.fee += 3;
                ca.j.dispose();
                new CustomerMain(c.getPerInfomation().getName(), c);
            }
        });
        ca.security.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.checkBalance(c)) {
                    SystemApp.addAccount(3, c);
                    c.getAcc().get(c.getAccountNumber()-1).getC()[0].withdraw(3);
                    SystemApp.fee += 3;
                    ca.j.dispose();
                    new CustomerMain(c.getPerInfomation().getName(), c);
                }
                else {
                    JOptionPane.showMessageDialog(ca.j, "Your balance is less than $5000, Can't create security account!");
                }
            }
        });
        ca.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ca.j.setVisible(false);
                ca.j.dispose();
                new CustomerMain(c.getPerInfomation().getName(), c);
            }
        });
    }

    public static void CustomerStockGUIAL(CustomerStockGUI cs, Customer c, Account a) {
    	cs.view.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cs.j.dispose();
    			new CustomerStockGUI(cs.stockName.getSelectedIndex(), c, a);
    		}
    	});
    	cs.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cs.j.dispose();
                new SecurityMainGUI(c, a);
            }
        });
    	cs.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new exit(cs.j);
            }
        });
    }
    
    public static void CustomerBondGUIAL(CustomerBondGUI cb, Customer c, Account a) {
    	cb.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb.j.dispose();
                new SecurityMainGUI(c, a);
            }
        });
    	cb.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new exit(cb.j);
            }
        });
    }
    
    public static void CustomerPropertyGUIAL(CustomerPropertyGUI cp, Customer c, Account a) {
    	cp.value.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cp.j.dispose();
    			new AccountValueGUI(c, a);
    		}
    	});
    	cp.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cp.j.setVisible(false);
                cp.j.dispose();
                new SecurityMainGUI(c, a);
            }
        });
    	cp.stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cp.j.dispose();
            	new CustomerPropertyGUI(0, c, a);
            }
        });
    	cp.bond.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cp.j.dispose();
            	new CustomerPropertyGUI(1, c, a);
            }
        });
    	cp.sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//---------------------------------------------------------
            }
        });
    }
    
    public static void AccountValueGUIAL(AccountValueGUI avg, Customer c, Account a) {
    	avg.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	avg.j.setVisible(false);
                avg.j.dispose();
            	new CustomerPropertyGUI(0, c, a);
            }
        });
        avg.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(avg.j);
            }
        });
    }

    public static void BankerStockGUIAL(BankerStockGUI bs) {
    	bs.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bs.j.dispose();
                new BankerMain();
            }
        });
    	bs.apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.updateStock();
            }
        });
    }
    
    public static void BankerBondGUIAL(BankerBondGUI bb) {
    	bb.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bb.j.dispose();
                new BankerMain();
            }
        });
    	bb.apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.updateBond();
            }
        });
    }

    public static void main(String[] args) {
        SystemApp.setDefaultBanker();
         String str = "Eric";
         Customer c = new Customer(str, "123456", str, "1111111111", "abc", true);
         SystemApp.addUser(str, "123456", str, "1111111111", "abc", 1, true);
         SystemApp.addAccount(3, c);
         //new CustomerMainGUI(c.getAcc().get(0), str, c);
        // new Welcome();
//        String[] nameList = {"First", "Second"};
//        ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
//        DefaultCategoryDataset first = new DefaultCategoryDataset();
//        DefaultCategoryDataset second = new DefaultCategoryDataset();
//        first.addValue(1, "First", "2013");
//        first.addValue(3, "First", "2014");
//        first.addValue(2, "First", "2015");
//        first.addValue(6, "First", "2016");
//        first.addValue(5, "First", "2017");
//        first.addValue(12, "First", "2018");
//        second.addValue(14, "Second", "2013");
//        second.addValue(13, "Second", "2014");
//        second.addValue(12, "Second", "2015");
//        second.addValue(9, "Second", "2016");
//        second.addValue(5, "Second", "2017");
//        second.addValue(7, "Second", "2018");
//        data.add(first);
//        data.add(second);
//    	new CustomerStockGUI(data, nameList, 0);
        
       // new SecurityMainGUI(c, c.getAcc().get(0));
         new Welcome();
    }
}