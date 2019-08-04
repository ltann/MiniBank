import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.category.DefaultCategoryDataset;

public class SystemApp {
    public static ArrayList<ArrayList<String>> bankerAccount = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> customerAccount = new ArrayList<ArrayList<String>>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Banker> bankers = new ArrayList<Banker>();
    public static int accountNumber = 1;
    public static int currentCustomer;
    public static int currentBanker;
    public static String report = "";
    public static double fee = 0;

    public static boolean checkUser(String Username, String Password, int type){
        if(type == 0){
            //banker account
            for(int i = 0; i < bankerAccount.size(); i++) {
                if(Username.equals(bankerAccount.get(i).get(0))) {
                    if(Password.equals(bankerAccount.get(i).get(1))) {
                        currentBanker = i;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }else{
            //customer account
            for(int i = 0; i < customerAccount.size(); i++) {
                if(Username.equals(customerAccount.get(i).get(0))) {
                    if(Password.equals(customerAccount.get(i).get(1))) {
                        currentCustomer = i;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }

        return false;
    }
    public static void setDefaultBanker(){
        ArrayList<String> newUser = new ArrayList<String>();
        newUser.add("admin");
        newUser.add("admin");
        bankerAccount.add(newUser);
        customerAccount.add(newUser);
        customers.add(new Customer("admin","admin","asdf","qwe","asdf",true));
    }

    public static boolean addUser(String username, String password, String name, String cell, String address, int type, boolean collateral){
        if(type == 0) {
            return false;
        }
        else {
            if(customerAccount.size() == 0){
                ArrayList<String> newUser = new ArrayList<String>();
                newUser.add(username);
                newUser.add(password);
                customerAccount.add(newUser);
                customers.add(new Customer(username, password, name, cell, address, collateral));
                return true;
            }
            for(int i = 0; i < customerAccount.size(); i++) {
                if(username == customerAccount.get(i).get(0)) {
                    return false;
                }
                else {
                    ArrayList<String> newUser = new ArrayList<String>();
                    newUser.add(username);
                    newUser.add(password);
                    customerAccount.add(newUser);
                    customers.add(new Customer(username, password, name, cell, address, collateral));
                    return true;
                }
            }
        }
        return false;  
    }

    public static void addAccount(int type, Customer c){
        c.addAccount(type, accountNumber++);
    }

    public static boolean delAccount(int accountNumber, Customer c) {
        if(c.delAccount(accountNumber)) {
            return true;
        }
        return false;
    }

    public static String transaction(int sendAccountNumber,int accountNumber, double amount, Customer c, int currencyIndex){
        int sender = -1;
        String str = "";
        for(int i = 0; i < c.getAcc().size(); i++) {
            if(c.getAcc().get(i).getAccountNumber() == sendAccountNumber) {
                sender = i;
                break;
            }
        }
        int[] receiver = checkAccountNumber(accountNumber);
        if(receiver[0] != -1 && sender != -1) {
            double fee = 0;
            switch (currencyIndex) {
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
            if(c.getAcc().get(sender).getType() == 0) {
                if(c.getAcc().get(sender).getC()[currencyIndex].getBalance() >= (amount+fee)) {
                    c.getAcc().get(sender).getC()[currencyIndex].withdraw(amount+fee);
                    SystemApp.fee += 3;
                    customers.get(receiver[0]).getAcc().get(receiver[1]).getC()[0].deposit(amount);
                    str = transferReport(customers.get(currentCustomer), sender, customers.get(receiver[0]), receiver[0], amount, currencyIndex);
                }
            }
            else {
                if(c.getAcc().get(sender).getC()[currencyIndex].getBalance() >= (amount)) {
                    c.getAcc().get(sender).getC()[currencyIndex].withdraw(amount);
                    customers.get(receiver[0]).getAcc().get(receiver[1]).getC()[0].deposit(amount);
                    str = transferReport(customers.get(currentCustomer), sender, customers.get(receiver[0]), receiver[0], amount, currencyIndex);
                }
            }
            
        }
            return str;   
    }
    public static String transferReport(Customer sender, int accountNumber, Customer receiver, int receiveIndex, double amount, int currencyIndex) {
        String str = "";
        str += sender.getPerInfomation().getName();
        str += "'s account: ";
        str += accountNumber;
        str += "   send ";
        str += amount;
        switch(currencyIndex) {
            case 0:
                str += " USD to ";
                break;
            case 1:
                str += " RMB to ";
                break;
            case 2:
                str += " EUR to ";
                break;
            default:
                break;
        }
        str += receiver.getPerInfomation().getName();
        str += " 's account:  ";
        str += receiver.getAcc().get(receiveIndex).getAccountNumber();
        str += "\r\n";
        report += str;
        return str;
    }


    public static int[] checkAccountNumber (int accountNumber) {
        int[] a;
        for(int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < customers.get(i).getAcc().size(); j++) {
                if (customers.get(i).getAcc().get(j).getAccountNumber() == accountNumber) {
                    a = new int[]{i, j};
                    return a;
                }
            }
        }
        a = new int[]{-1};
        return a;
    }

    public static String depositReport(Account a, double deposit, int currency) {
        String info = "";
        info += "Account Number: ";
        info += a.getAccountNumber();
        info += "\r\n";
        info += "Amount: ";
        switch(currency) {
            case 0:
                info += "$ ";
                break;
            case 1:
                info += "妤� ";
                break;
            case 2:
                info += "閳э拷 ";
                break;
        }
        info += deposit;
        info += "\r\n";
        info += "Current Balance: ";
        switch(currency) {
            case 0:
                info += "$ ";
                break;
            case 1:
                info += "妤� ";
                break;
            case 2:
                info += "閳э拷 ";
                break;
        }
        info += a.getC()[currency].getBalance();
        info += "\r\n";
        info += "Transaction Time: ";
        info += LocalDateTime.now().toString();
        info += "\r\n";
        report += info;
        return info;
    }


    public static String loanReport(double amount, String currency, String month) {
        String info = "";
        info += "Currency Type: ";
        switch(currency) {
            case "$":
                info += "USD";
                break;
            case "妤�":
                info += "RMB";
                break;
            case "閳э拷":
                info += "EUR";
                break;
            default:
                break;
        }
        info += "\r\n";
        info += "Loan Amount: ";
        info += currency + " " + amount ;
        info += "\r\n";
    
        info += "Time Last: ";
        info += "" + month + "th";
        return info;
    }

    public static String getInfoForBanker(){
        String answer="";
        for(int i=0;i<=customers.size()-1;i++){
            answer+="Name : ";
            answer+=customers.get(i).getPerInfomation().getName();
            answer+="  Cellphone :  ";
            answer+=customers.get(i).getPerInfomation().getCell();
            answer+="  Address :  ";
            answer+=customers.get(i).getPerInfomation().getAddress();
            answer+="\nAccount :\n";
            for(int j=0;j<=customers.get(i).getAccountNumber()-1;j++){
                answer+=customers.get(i).getAcc().get(j).getAccountInfo();
            }
            answer+="\nLoan :\n";
            for(int k=0;k<=customers.get(i).getLoanNumber()-1;k++){
                answer+=customers.get(i).getLn().get(k).getInterest();
                answer+=" ";
                answer+=customers.get(i).getLn().get(k).getCurrency().getName();
                answer+=" ";
                answer+=customers.get(i).getLn().get(k).getCurrency().getSymbol();
                answer+=customers.get(i).getLn().get(k).getCurrency().getBalance();
                answer+=" ";
            }
            answer+="\n";
        }
        return answer;
    }
    
    public static void loan(int numMonth, double amount, int currency){
        double interest = 0;
        if(numMonth < 5){
            interest = 0.02;
        } else if(numMonth < 12){
            interest = 0.01;
        }else{
            interest = 0.007;
        }
        Loan loan = new Loan(interest,currency);
        loan.getCurrency().deposit(amount);
        customers.get(currentCustomer).addLoan(loan);
        customers.get(currentCustomer).getAcc().get(0).getC()[currency].deposit(amount);
    }

    public static boolean checkBalance(Customer c) {
        double balance = 0;
        for(int i = 0; i < c.getAcc().size(); i++) {
            balance += c.getAcc().get(i).getC()[0].getBalance();
            balance += c.getAcc().get(i).getC()[1].getBalance()/7;
            balance += c.getAcc().get(i).getC()[2].getBalance()/0.9;
        }
        if(balance >= 5000) {
            return true;
        }
        return false;
    }

    public static Object[][] getAllData() {
    	Object[][] data = {
                new Object[]{"APPR", "APPLE", 100.25, 110, 20, "15%", 110*20},
                new Object[]{"MICR", "MICROSOFT", 57.26, 48.6, 50, "-30%", 48.6*50}
            };
    	return data;
    }
    
    public static DefaultCategoryDataset getAccountValueData() {
    	DefaultCategoryDataset first = new DefaultCategoryDataset();
		first.addValue(1, "First", "2013");
	    first.addValue(3, "First", "2014");
	    first.addValue(2, "First", "2015");
	    first.addValue(6, "First", "2016");
	    first.addValue(5, "First", "2017");
	    first.addValue(12, "First", "2018");
	    return first;
    }
    
    public static ArrayList<DefaultCategoryDataset> getStockData() {
    	ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
		DefaultCategoryDataset first = new DefaultCategoryDataset();
		DefaultCategoryDataset second = new DefaultCategoryDataset();
		first.addValue(1, "First", "2013");
		first.addValue(3, "First", "2014");
		first.addValue(2, "First", "2015");
		first.addValue(6, "First", "2016");
		first.addValue(5, "First", "2017");
		first.addValue(12, "First", "2018");
		second.addValue(14, "Second", "2013");
		second.addValue(13, "Second", "2014");
		second.addValue(12, "Second", "2015");
		second.addValue(9, "Second", "2016");
		second.addValue(5, "Second", "2017");
		second.addValue(7, "Second", "2018");
		data.add(first);
		data.add(second);
		return data;
    }
    
    public static String[] getStockNameList() {
    	String[] stockName = {"Apple", "Microsoft"};
    	return stockName;
    }
    
    public static Object[][] getUserBonds() {
    	Object[][] bonds = {
            new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
            new String[]{"3 month", "10000", "2019-8-3", "2019-11-3",  "30%"}
        };
    	return bonds;
    }
    
    public static Object[][] getBondsData() {
    	Object[][] bonds = {
                new String[]{"1 month", "100", "15%"},
                new String[]{"3 month", "10000", "30%"}
            };
        	return bonds;
    }
    
    public static Object[][] getBankerStock() {
    	Object[][] stocks = {
                new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
                new String[]{"3 month", "10000", "2019-8-3", "2019-11-3",  "30%"}
        };
    	return stocks;
    }
    
    public static void updateStock() {
    	
    }
    
    public static void updateBond(String bondID, double newInt) {
    	SystemApp.bankers.get(0).setNewInterestRate(bondID, newInt);
    }

 ///////////////////////////////////////////////////////////////////////////////////
 // STOCKS AND BONDS FUNCTIONS
    
    // for change interest rate button change bond interest rate
    public static void changeBondInterestrate(Bonds b, double newInterestRate) {
      b.changeInterest(newInterestRate);
    }
    //purchase a new bond for a customer
    
    
    public static void addNewBond(Customer c, Bonds b) {
      Bonds addBond = b;
      Iterator iter = c.getAcc().iterator();
      while(iter.hasNext()) {
        SecurityAccount findSA = (SecurityAccount) iter.next();
        if(findSA.getType() == 3) {
          // TODO: (ADD bonds to SA) --> should update available balance
          //                         --> add bonds to linkedList of Customer
          // findSA.addBonds(b);
        }
      }
    }
    
    
    //refresh stock button
    public static void updateAllStocks() {
      //function provided by the database
      SystemApp.bankers.get(0).updateExisitngStocks();
    }
    
    
    //Customer purchase a stock
    public static void purchaseANewStock(Customer c, Stocks s, int numOfShare) {
      Stocks addStock = s;
      Iterator iter = c.getAcc().iterator();
      while(iter.hasNext()) {
        SecurityAccount findSA = (SecurityAccount) iter.next();
        if(findSA.getType()==3) {
          //TODO: (Add stocks to SA) --> should update available balance
          //                         --> should add Stock to linkedList
          // findSA.addStocks(s, numOfShare)
          SystemApp.bankers.get(0).addProfits();
        }
      }
    }
    
    
    //Customer sell a stock
    public static void sellANewStock(Customer c, Stocks s, int numOfShare) {
      Stocks sellStock = s;
      Iterator iter = c.getAcc().iterator();
      while(iter.hasNext()) {
        SecurityAccount findSA = (SecurityAccount) iter.next();
        if(findSA.getType() == 3) {
        //TODO: (Sell stocks to SA) --> should update available balance
          //                         --> should update value of SA
          //                         --> should remove Stock to linkedList
          // findSA.sellStocks(s, numOfShare)
          SystemApp.bankers.get(0).addProfits();
        }
      }
    }
    
    
    
    //Create a new Stock from Manager end
    public static void ManagerCreateNewStock(Banker b, String ticker, String CompanyName, int newPrice) {
      //stock s should have a stock name, stock ticker, and stock price
      b.createNewStock(ticker, CompanyName, newPrice);
    }
    

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static Object[][] getAllData() {
	Object[][] data = {
            new Object[]{"APPR", "APPLE", 100.25, 110, 20, "15%", 110*20},
            new Object[]{"MICR", "MICROSOFT", 57.26, 48.6, 50, "-30%", 48.6*50}
        };
	return data;
}

public static DefaultCategoryDataset getAccountValueData() {
	DefaultCategoryDataset first = new DefaultCategoryDataset();
	first.addValue(1, "First", "2013");
    first.addValue(3, "First", "2014");
    first.addValue(2, "First", "2015");
    first.addValue(6, "First", "2016");
    first.addValue(5, "First", "2017");
    first.addValue(12, "First", "2018");
    return first;
}

public static ArrayList<DefaultCategoryDataset> getStockData() {
	ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
	DefaultCategoryDataset first = new DefaultCategoryDataset();
	DefaultCategoryDataset second = new DefaultCategoryDataset();
	first.addValue(1, "First", "2013");
	first.addValue(3, "First", "2014");
	first.addValue(2, "First", "2015");
	first.addValue(6, "First", "2016");
	first.addValue(5, "First", "2017");
	first.addValue(12, "First", "2018");
	second.addValue(14, "Second", "2013");
	second.addValue(13, "Second", "2014");
	second.addValue(12, "Second", "2015");
	second.addValue(9, "Second", "2016");
	second.addValue(5, "Second", "2017");
	second.addValue(7, "Second", "2018");
	data.add(first);
	data.add(second);
	return data;
}

public static String[] getStockNameList() {
	String[] stockName = {"Apple", "Microsoft"};
	return stockName;
}

public static Object[][] getUserBonds() {
	Object[][] bonds = {
        new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
        new String[]{"3 month", "10000", "2019-8-3", "2019-11-3",  "30%"}
    };
	return bonds;
}

public static Object[][] getBondsData() {
	Object[][] bonds = {
            new String[]{"1 month", "100", "15%"},
            new String[]{"3 month", "10000", "30%"}
        };
    	return bonds;
}

public static Object[][] getBankerStock() {
	Object[][] stocks = {
            new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
            new String[]{"3 month", "10000", "2019-8-3", "2019-11-3",  "30%"}
    };
	return stocks;
}

public static void updateStock() {
	
}

public static void updateBond() {
	
}
}

    
