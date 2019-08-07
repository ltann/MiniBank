import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GUI {
    public static void customerMainGUIAL(CustomerMainGUI c) {
        c.balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new balanceGUI(SystemApp.currentAccount.getType());
            }
        });
        
        c.withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new withdrawGUI();
            }
        });
        
        c.deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new depositGUI();
            }
        });
        
        c.transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new Transfer(SystemApp.currentAccount.getType());
            }
        });
        
        c.loan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.j.setVisible(false);
                new LoanGUI();
            }
        });
        
        c.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(c.j);
            }
        });
    }

    public static void SecurityMainGUIAL(SecurityMainGUI s) {
        s.balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s.j.setVisible(false);
                new balanceGUI(3);
            }
        });
        
         s.property.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 s.j.setVisible(false);
                 new CustomerPropertyGUI(0);
             }
         });
        
         s.stocks.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 s.j.dispose();
                 new CustomerStockGUI(0);
             }
         });
        s.bonds.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  s.j.setVisible(false);
                  new CustomerBondGUI();
              }
          });
        s.transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s.j.setVisible(false);
                new Transfer(3);
            }
        });
        
        
        
        s.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(s.j);
            }
        });
    }

    public static void depositGUIAL(depositGUI d) {
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
                	SystemApp.currentAccount.getC()[d.currencyType.getSelectedIndex()].deposit(money);
                	String key = "";
                	switch(d.currencyType.getSelectedIndex()) {
                	case 0: 
                		key = "USD";
                		break;
                	case 1: 
                		key = "RMB";
                		break;
                	case 2: 
                		key = "EUR";
                		break;
                	}
                	AccountDB acc = SystemApp.database.dataFindAccount(SystemApp.currentAccount.getAccountNumber());
                	Map<String, Double> cur = acc.getCurrency();
                	cur.put(key,(cur.get(key)+money));
                	acc.setCurrency(cur);
                    SystemApp.database.dataUpdateAccount(SystemApp.currentAccount.getAccountNumber(), acc);
                    //SystemApp.checkCurrentAccount(a.getAccountNumber());
                    d.j.setVisible(false);
                    d.j.dispose();
                    new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, money, d.currencyType.getSelectedIndex()));
                }
                else {
                    d.amount.setText("Please type your deposit amount! (0-50000)");
                }
            }
        });
        d.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.j.dispose();
                new CustomerMainGUI();
            }
        });
    }

    public static void withdrawGUIAL(withdrawGUI wd) {
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
                    if(SystemApp.currentAccount.getType() == 1) {
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
                        if(SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= (money+fee)) {
                        	SystemApp.withdraw(wd.currencyType.getSelectedIndex(), fee, money, SystemApp.currentAccount);
                        	SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].withdraw(money+fee);
                            SystemApp.fee += 3;
                            SystemApp.updateFee();
                            wd.j.setVisible(false);
                            wd.j.dispose();

                            new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, money, wd.currencyType.getSelectedIndex()));
                        }
                        else {
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, money, wd.currencyType.getSelectedIndex()));
                        }
                    }
                    else{
                        if(SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= (money)) {
                        	SystemApp.withdraw(wd.currencyType.getSelectedIndex(), 0, money, SystemApp.currentAccount);
                        	SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].withdraw(money);
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, money, wd.currencyType.getSelectedIndex()));
                        }
                        else {
                            wd.j.setVisible(false);
                            wd.j.dispose();
                            new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, money, wd.currencyType.getSelectedIndex()));
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
                new CustomerMainGUI();
            }
        });
        wd.d_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.currentAccount.getType() == 1) {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 23) {
                    	SystemApp.currentAccount.getC()[0].withdraw(23);
                    	SystemApp.fee += 3;
                    	SystemApp.updateFee();
                        SystemApp.withdraw(0, 3, 20, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 20, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 20, wd.currencyType.getSelectedIndex()));
                    }
                }
                else {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 20) {
                    	SystemApp.currentAccount.getC()[0].withdraw(20);
                        SystemApp.withdraw(0, 0, 20, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 20, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 20, wd.currencyType.getSelectedIndex()));
                    }
                }
                
            }
        });
        wd.d_50.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.currentAccount.getType() == 1) {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 53) {
                    	SystemApp.currentAccount.getC()[0].withdraw(53);
                    	SystemApp.fee += 3;
                    	SystemApp.updateFee();
                        SystemApp.withdraw(0, 3, 50, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 50, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 50, wd.currencyType.getSelectedIndex()));
                    }
                }
                else {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 50) {
                    	SystemApp.currentAccount.getC()[0].withdraw(50);
                        SystemApp.withdraw(0, 0, 50, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 50, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 50, wd.currencyType.getSelectedIndex()));
                    }
                }
                
            }
        });
        wd.d_100.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.currentAccount.getType() == 1) {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 103) {
                    	SystemApp.currentAccount.getC()[0].withdraw(103);
                    	SystemApp.fee += 3;
                    	SystemApp.updateFee();
                        SystemApp.withdraw(0, 3, 100, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 100, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 100, wd.currencyType.getSelectedIndex()));
                    }
                }
                else {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 100) {
                    	SystemApp.currentAccount.getC()[0].withdraw(100);
                        SystemApp.withdraw(0, 0, 100, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 100, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 100, wd.currencyType.getSelectedIndex()));
                    }
                }
                    
            }
        });
        wd.d_200.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.currentAccount.getType() == 1) {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 203) {
                    	SystemApp.currentAccount.getC()[0].withdraw(203);
                    	SystemApp.fee += 3;
                    	SystemApp.updateFee();
                        SystemApp.withdraw(0, 3, 200, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 200, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 200, wd.currencyType.getSelectedIndex()));
                    }
                }
                else {
                    if(SystemApp.currentAccount.getC()[0].getBalance() >= 200) {
                    	SystemApp.currentAccount.getC()[0].withdraw(200);
                        SystemApp.withdraw(0, 0, 200, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 200, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 200, wd.currencyType.getSelectedIndex()));
                    }
                }
            }
        });
        wd.d_500.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.currentAccount.getType() == 1) {
                    if(SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= 503) {
                    	SystemApp.currentAccount.getC()[0].withdraw(503);
                    	SystemApp.fee += 3;
                    	SystemApp.updateFee();
                        SystemApp.withdraw(0, 3, 500, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 500, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 500, wd.currencyType.getSelectedIndex()));
                    }
                }
                else {
                    if(SystemApp.currentAccount.getC()[wd.currencyType.getSelectedIndex()].getBalance() >= 500) {
                    	SystemApp.currentAccount.getC()[0].withdraw(500);
                        SystemApp.withdraw(0, 0, 500, SystemApp.currentAccount);
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(true, SystemApp.depositReport(SystemApp.currentAccount, 500, wd.currencyType.getSelectedIndex()));
                    }
                    else{
                        wd.j.setVisible(false);
                        wd.j.dispose();
                        new SucceedInfoGUI(false, SystemApp.depositReport(SystemApp.currentAccount, 500, wd.currencyType.getSelectedIndex()));
                    }
                }
            }
        });
    }

    public static void balanceGUIAL(balanceGUI b, int type) {
        b.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	b.j.setVisible(false);
                b.j.dispose();
            	if (type == 3) {
            		b.j.dispose();
            		new SecurityMainGUI();
            	}
            	else {
            		b.j.dispose();
                    new CustomerMainGUI();
            	}
                
            }
        });
        b.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new exit(b.j);
            }
        });
    }

    public static void TransferGUIAL(Transfer tf, int type){
        tf.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.j.setVisible(false);
                tf.j.dispose();
                if(type == 3) {
                	new SecurityMainGUI();
                }
                else {
                	new CustomerMainGUI();
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
                	String report = "";
                	if(type == 3) {
                        report = SystemApp.transaction(type, SystemApp.currentSecurity.getAccountNumber(),accountNumber, money, SystemApp.currentCustomer, tf.currencyCB.getSelectedIndex());
                	}
                	else {
                        report = SystemApp.transaction(type, SystemApp.currentAccount.getAccountNumber(),accountNumber, money, SystemApp.currentCustomer, tf.currencyCB.getSelectedIndex());

                	}
                    if(report != "") {
                        tf.j.setVisible(false);
                        tf.j.dispose();
                        new SucceedInfoGUI(true, report);
                    }
                    else {
                        tf.j.setVisible(false);
                        tf.j.dispose();
                        new SucceedInfoGUI(false, report);
                    }
                }
            } 
        });
    }

    public static void SucceedInfoGUIAL(SucceedInfoGUI si) {
        si.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                si.j.setVisible(false);
                si.j.dispose();
                if(SystemApp.currentAccount.getType() == 3) {
                	new SecurityMainGUI();
                }
                else {
                    new CustomerMainGUI();
                }
            }
        });
        si.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //si.j.setVisible(false);
                new exit(si.j);
            }
        });
    }
    
    public static void LoanGUIAL(LoanGUI l) {
        l.confirm.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //l.tf1.setText("Please type amount! (0-50000)");
                //System.out.println(l.tf1.getText());
                double money = Double.parseDouble(l.amount.getText());
                if (l.amount.getText() == "" || money < 0 || money > 50000) {
                    l.amount.setText("Please type amount! (0-50000)");
                } else if(!SystemApp.currentCustomer.isCollateral()){
                    l.amount.setText("Can't loan because you don't have collateral");
                }  else {
                	SystemApp.loan(l.timeCB.getSelectedIndex(), Double.parseDouble(l.amount.getText()));
					l.j.setVisible(false);
                    l.j.dispose();
                    new SucceedInfoGUI(true, SystemApp.loanReport(Double.parseDouble(l.amount.getText()), l.currencyList[l.currencyCB.getSelectedIndex()], l.timeList[l.timeCB.getSelectedIndex()]));
                    
                }
            }
        });

        l.cancel.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                l.j.dispose();
                new CustomerMainGUI();
            }
        });
        
        l.history.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		l.j.dispose();
        		new LoanHistoryGUI();
        	}
        });
    }

    public static void LoanHistoryGUIAL(LoanHistoryGUI lh) {
    	lh.pay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int confirm = JOptionPane.showConfirmDialog(lh.j, "Are you sure to payback your loan?");
        		if (confirm == JOptionPane.YES_OPTION) {
        			SystemApp.payLoan(lh.t.getSelectedRow());
        			JOptionPane.showMessageDialog(lh.j, "Succeed!");
                    lh.j.dispose();
                    new LoanHistoryGUI();
                }
        	}
        });
    	
    	lh.ret.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lh.j.dispose();
        		new LoanGUI();
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
                	//System.out.println(SystemApp.currentCustomer.getLoginName());
                    new CustomerMain(name, SystemApp.currentCustomer);
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
    bm.refresh.addActionListener(new ActionListener(){
    
        @Override
        public void actionPerformed(ActionEvent e) {
            SystemApp.update();
            JOptionPane.showMessageDialog(bm.j, "Succeed!");
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
            ArrayList<Customer> customers = SystemApp.createCustomers();
            for (int i = 0; i <= customers.size() - 1; i++) {
                if (name.equals(customers.get(i).getPerInfomation().getName())) {
                    str += "Name : ";
                    str += customers.get(i).getPerInfomation().getName();
                    str += "   Cellphone : ";
                    str += customers.get(i).getPerInfomation().getCell();
                    str += "   Address : ";
                    str += customers.get(i).getPerInfomation().getAddress();
                    str += "\nAccount :\n";
                    for (int j = 0; j <= customers.get(i).getAccountNumber() - 1; j++) {
                        str += customers.get(i).getAcc().get(j).getAccountInfo();
                    }
                    SecurityAccountDB sa = SystemApp.database.dataFindSecurityAccount(customers.get(i).getLoginName());
                    if(sa != null) {
                    	str += sa.getAccountNumber();
                        str += " Security\n";
                        str += "USD: ";
                        str += "$";
                        str += sa.getAvaliableFunds();
                    }
                    str += "\nLoan :\n";
//                    for (int k = 0; k <= customers.get(i).getLoanNumber() - 1; k++) {
//                        str += customers.get(i).getLn().get(k).getInterest();
//                        str += " ";
//                        str += customers.get(i).getLn().get(k).getCurrency().getName();
//                        str += " ";
//                        str += customers.get(i).getLn().get(k).getCurrency().getSymbol();
//                        str += customers.get(i).getLn().get(k).getCurrency().getBalance();
//                        str += " ";
//                    }
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
                if(c.getSacc() != null && c.getSacc().getAccountNumber() == accountNum) {
                	SystemApp.currentSecurity = c.getSacc();
                	cmain.j.dispose();
                    new SecurityMainGUI();
                }
                else {
                	for(int i = 0; i < c.getAcc().size(); i++){
                        if(c.getAcc().get(i).getAccountNumber() == accountNum){
                            cmain.j.dispose();
                            SystemApp.currentAccount = c.getAcc().get(i);
                            new CustomerMainGUI();
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
            new CreateAccountGUI(SystemApp.currentCustomer);
        }
    });
    cmain.close.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(cmain.sourceList.getSelectedValue() != null) {
                if(c.getLoanNumber() > 0) {
                    JOptionPane.showMessageDialog(cmain.j, "You have loan, can't delete account!");
                }
                else {
                    int confirm = JOptionPane.showConfirmDialog(cmain.j,"Are you sure to delete this account?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        SystemApp.delAccount(c.getAcc().get(cmain.sourceList.getSelectedIndex()-6).getAccountNumber(), c);
                        cmain.j.dispose();
                        new CustomerMain(name, SystemApp.currentCustomer);
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
                ca.j.dispose();
                System.out.println(c.getAcc().size());
                new CustomerMain(c.getPerInfomation().getName(), SystemApp.currentCustomer);
            }
        });
        ca.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.addAccount(2, c);
                ca.j.dispose();
                new CustomerMain(c.getPerInfomation().getName(), SystemApp.currentCustomer);
            }
        });
        ca.security.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.checkBalance(c)) {
                    SystemApp.addAccount(3, c);
                    ca.j.dispose();
                    new CustomerMain(c.getPerInfomation().getName(), SystemApp.currentCustomer);
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
                new CustomerMain(c.getPerInfomation().getName(), SystemApp.currentCustomer);
            }
        });
    }

    public static void CustomerStockGUIAL(CustomerStockGUI cs) {
    	cs.view.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cs.j.dispose();
    			new CustomerStockGUI(cs.stockName.getSelectedIndex());
    		}
    	});
    	cs.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cs.j.dispose();
                new SecurityMainGUI();
            }
        });
    	cs.buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.purchaseStock(SystemApp.currentCustomer, cs.stockName.getSelectedIndex(), Integer.parseInt(cs.amount.getText()))) {
                	JOptionPane.showMessageDialog(cs.j, "Succeed!");
                }
                else {
                	JOptionPane.showMessageDialog(cs.j, "Failed!");
                }
            }
        });
    	cs.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new exit(cs.j);
            }
        });
    }
    
    public static void CustomerBondGUIAL(CustomerBondGUI cb) {
    	cb.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cb.j.dispose();
                new SecurityMainGUI();
            }
        });
    	cb.exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new exit(cb.j);
            }
        });
    	cb.buy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SystemApp.purchaseBond(SystemApp.currentCustomer, cb.t.getSelectedRow(), Double.parseDouble(cb.amount.getText()))) {
        			JOptionPane.showMessageDialog(cb.j, "Succeed!");
                }
                else {
        			JOptionPane.showMessageDialog(cb.j, "Failed!");
                }
                cb.j.dispose();
                new CustomerBondGUI();
            }
        });
    }
    
    public static void CustomerPropertyGUIAL(CustomerPropertyGUI cp) {
    	cp.value.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			cp.j.dispose();
    			System.out.println(SystemApp.currentSecurity.getValueOfSA());
    			new AccountValueGUI();
    		}
    	});
    	cp.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cp.j.setVisible(false);
                cp.j.dispose();
                new SecurityMainGUI();
            }
        });
    	cp.stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cp.j.dispose();
            	new CustomerPropertyGUI(0);
            }
        });
    	cp.bond.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cp.j.dispose();
            	new CustomerPropertyGUI(1);
            }
        });
    	cp.sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(cp.index == 0) { // Sell stocks
            		if(SystemApp.sellStock(SystemApp.currentCustomer, cp.t.getSelectedRow())) {
            			JOptionPane.showMessageDialog(cp.j, "Succeed!");
            		}
            		else {
            			JOptionPane.showMessageDialog(cp.j, "Failed!");
            		}
            	}
            	else { // Sell Bonds
            		if(SystemApp.sellBond(SystemApp.currentCustomer, cp.t.getSelectedRow())) {
            			JOptionPane.showMessageDialog(cp.j, "Succeed!");
            		}
            		else {
            			JOptionPane.showMessageDialog(cp.j, "Failed!");
            		}
            	}
            	cp.j.dispose();
            	new CustomerPropertyGUI(0);
            }
        });
    }
    
    public static void AccountValueGUIAL(AccountValueGUI avg) {
    	avg.ret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	avg.j.setVisible(false);
                avg.j.dispose();
            	new CustomerPropertyGUI(0);
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
//    	bs.apply.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                SystemApp.updateStock();
//            }
//        });
    	bs.createStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	bs.j.dispose();
                new CreateStockGUI();
            }
        });
    }
    
    public static void CreateStockGUIAL(CreateStockGUI cs) {
    	cs.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cs.j.dispose();
                new BankerStockGUI();
            }
        });
    	cs.apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SystemApp.ManagerCreateNewStock(SystemApp.bankers.get(0), cs.ticker.getText(), cs.name.getText(), Double.parseDouble(cs.price.getText()));
                JOptionPane.showMessageDialog(cs.j, "Succeed!");
                cs.j.dispose();
                new BankerStockGUI();
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
                SystemApp.updateBond((String)bb.bondPeriod.getSelectedItem(), Double.parseDouble(bb.newPrice.getText()));
            }
        });
    	bb.search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		String str = String.valueOf(bb.bondPeriod.getSelectedItem().toString().charAt(0));
            	bb.curPrice.setText(String.valueOf(SystemApp.SearchBondInterest(str)));
            }
        });
    	bb.apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		String str = String.valueOf(bb.bondPeriod.getSelectedItem().toString().charAt(0));
            	double interest = Double.parseDouble(bb.newPrice.getText());
            	//System.out.println(String.valueOf(bb.bondPeriod.getSelectedItem().toString().charAt(0)));
            	if(SystemApp.updateBond(str, interest)) {
            		JOptionPane.showMessageDialog(bb.j, "Succeed!");
            		bb.j.dispose();
            		new BankerMain();
            	}
            	else {
            		JOptionPane.showMessageDialog(bb.j, "Failed!");
            	}
            }
        });
    }

    public static void main(String[] args) {
    	SystemApp.setDefaultBanker();
    	SystemApp.init();
//         String str = "Eric";
//         Customer c = new Customer(str, "123456", str, "1111111111", "abc", true);
//         SystemApp.addUser(str, "123456", str, "1111111111", "abc", 1, true);
//         SystemApp.addAccount(3, c);
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