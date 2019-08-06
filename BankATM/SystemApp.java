import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
    public static DataAccess database = new DataAccess();

    public static boolean checkUser(String Username, String Password, int type) {
        if (type == 0) {
            //banker account
            for (int i = 0; i < bankerAccount.size(); i++) {
                if (Username.equals(bankerAccount.get(i).get(0))) {
                    if (Password.equals(bankerAccount.get(i).get(1))) {
                        currentBanker = i;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            //customer account
            for (int i = 0; i < customerAccount.size(); i++) {
                if (Username.equals(customerAccount.get(i).get(0))) {
                    if (Password.equals(customerAccount.get(i).get(1))) {
                        currentCustomer = i;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }

    public static Banker getCurrentBanker() {
        return bankers.get(currentBanker);
    }

    public static Customer getCurrentCustomer() {
        return customers.get(currentCustomer);
    }

    public static void setDefaultBanker() {
        ArrayList<String> newUser = new ArrayList<String>();
        newUser.add("admin");
        newUser.add("admin");
        bankerAccount.add(newUser);
        customerAccount.add(newUser);
        customers.add(new Customer("admin", "admin", "asdf", "qwe", "asdf", true));
        bankers.add(new Banker("admin", "admin"));
    }

    public static boolean addUser(String username, String password, String name, String cell, String address, int type, boolean collateral) {
        if (type == 0) {
            return false;
        } else {
            if (customerAccount.size() == 0) {
                ArrayList<String> newUser = new ArrayList<String>();
                newUser.add(username);
                newUser.add(password);
                customerAccount.add(newUser);
                customers.add(new Customer(username, password, name, cell, address, collateral));
                return true;
            }
            for (int i = 0; i < customerAccount.size(); i++) {
                if (username == customerAccount.get(i).get(0)) {
                    return false;
                } else {
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

    public static void addAccount(int type, Customer c) {
        c.addAccount(type, accountNumber++);
    }

    public static boolean delAccount(int accountNumber, Customer c) {
        if (c.delAccount(accountNumber)) {
            return true;
        }
        return false;
    }

    public static String transaction(int sendAccountNumber, int accountNumber, double amount, Customer c, int currencyIndex) {
        int sender = -1;
        String str = "";
        for (int i = 0; i < c.getAcc().size(); i++) {
            if (c.getAcc().get(i).getAccountNumber() == sendAccountNumber) {
                sender = i;
                break;
            }
        }
        int[] receiver = checkAccountNumber(accountNumber);
        if (receiver[0] != -1 && sender != -1) {
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
            if (c.getAcc().get(sender).getType() == 0) {
                if (c.getAcc().get(sender).getC()[currencyIndex].getBalance() >= (amount + fee)) {
                    c.getAcc().get(sender).getC()[currencyIndex].withdraw(amount + fee);
                    SystemApp.fee += 3;
                    customers.get(receiver[0]).getAcc().get(receiver[1]).getC()[0].deposit(amount);
                    str = transferReport(customers.get(currentCustomer), sender, customers.get(receiver[0]), receiver[0], amount, currencyIndex);
                }
            } else {
                if (c.getAcc().get(sender).getC()[currencyIndex].getBalance() >= (amount)) {
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
        switch (currencyIndex) {
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


    public static int[] checkAccountNumber(int accountNumber) {
        int[] a;
        for (int i = 0; i < customers.size(); i++) {
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
        switch (currency) {
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
        switch (currency) {
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
        switch (currency) {
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
        info += currency + " " + amount;
        info += "\r\n";

        info += "Time Last: ";
        info += "" + month + "th";
        return info;
    }

    public static String getInfoForBanker() {
        String answer = "";
        for (int i = 0; i <= customers.size() - 1; i++) {
            answer += "Name : ";
            answer += customers.get(i).getPerInfomation().getName();
            answer += "  Cellphone :  ";
            answer += customers.get(i).getPerInfomation().getCell();
            answer += "  Address :  ";
            answer += customers.get(i).getPerInfomation().getAddress();
            answer += "\nAccount :\n";
            for (int j = 0; j <= customers.get(i).getAccountNumber() - 1; j++) {
                answer += customers.get(i).getAcc().get(j).getAccountInfo();
            }
            answer += "\nLoan :\n";
            for (int k = 0; k <= customers.get(i).getLoanNumber() - 1; k++) {
                answer += customers.get(i).getLn().get(k).getInterest();
                answer += " ";
                answer += customers.get(i).getLn().get(k).getCurrency().getName();
                answer += " ";
                answer += customers.get(i).getLn().get(k).getCurrency().getSymbol();
                answer += customers.get(i).getLn().get(k).getCurrency().getBalance();
                answer += " ";
            }
            answer += "\n";
        }
        return answer;
    }

    public static void loan(int numMonth, double amount, int currency) {
        Customer c = customers.get(currentCustomer);
        double interest;
        if (numMonth < 5) {
            interest = 0.02;
        } else if (numMonth < 12) {
            interest = 0.01;
        } else {
            interest = 0.007;
        }
        Loan loan = new Loan(interest, currency);
        loan.getCurrency().deposit(amount);
        c.addLoan(loan);
        c.getAcc().get(0).getC()[currency].deposit(amount);
    }

    public static boolean payLoan(Loan loan){
        boolean payable = true;
        Customer c = customers.get(currentCustomer);
        if(c.hasEnoughMoney(loan.getCurrency().getBalance())){
            bankers.get(currentBanker).
        }
        return payable;
    }

    public static boolean checkBalance(Customer c) {
        double balance = 0;
        for (int i = 0; i < c.getAcc().size(); i++) {
            balance += c.getAcc().get(i).getC()[0].getBalance();
            balance += c.getAcc().get(i).getC()[1].getBalance() / 7;
            balance += c.getAcc().get(i).getC()[2].getBalance() / 0.9;
        }
        if (balance >= 5000) {
            return true;
        }
        return false;
    }


 ///////////////////////////////////////////////////////////////////////////////////
 // STOCKS AND BONDS FUNCTIONS
    
    // for change interest rate button change bond interest rate
    public static void changeBondInterestrate(Bonds b, double newInterestRate) {
      b.changeInterest(newInterestRate);
    }

    public static boolean updateBond(String bondID, double newInt) {
        if(SystemApp.bankers.get(0).setNewInterestRate(bondID, newInt)) {
        	return true;
        }
        return false;
        
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////
    // STOCKS AND BONDS FUNCTIONS

    //Customer Side Functions:

    //Customer purchase a bond
    public static boolean purchaseBond(Customer c, Bonds b, double amount) {
        boolean purchasable = true;
        SecurityAccount sa = c.getSecurityAccount();
        SystemApp.bankers.get(0).addProfits();
        purchasable = sa.purchaseBond(b, amount);
        if(purchasable){
            //database.dataUpdateSecurityAccount(c.getLoginName(), SecurityAccountDB newSecurityAccount);
        }
        return purchasable;
    }

    //Customer sells a bond

    public static boolean sellBond(Customer c, customerBond b){
        boolean sellable = true;
        SecurityAccount sa = c.getSecurityAccount();
        SystemApp.bankers.get(0).addProfits();
        sellable = sa.sellBond(b);
        if(sellable){
            //database.dataUpdateSecurityAccount(c.getLoginName(), SecurityAccountDB newSecurityAccount);
        }
        return sellable;
    }

    //Customer purchase a stock
    public static boolean purchaseStock(Customer c, Stocks s, int numOfShare) {
        boolean purchasable = true;
        SecurityAccount sa = c.getSecurityAccount();
        SystemApp.bankers.get(0).addProfits();
        purchasable = sa.purchaseStock(s,numOfShare);
        if(purchasable){
            //database.dataUpdateSecurityAccount(c.getLoginName(), SecurityAccountDB_SecurityAccountDB );
        }
        return purchasable;
    }


    //Customer sell a stock
    public static boolean sellStock(Customer c, customerStock s, int numOfShare) {
        boolean sellable = true;
        SecurityAccount sa = c.getSecurityAccount();
        SystemApp.bankers.get(0).addProfits();
        //sellable = sa.sellStock(s, database.dataFindStocks(s.getTicker()) ,numOfShare); //second parameter needs to be set as the currentPriceStock
        if(sellable){
            //database.dataUpdateSecurityAccount(c.getLoginName(), SecurityAccountDB newSecurityAccount);
        }
        return sellable;
    }

    //Customer's available funds in SA
    public static double getAvailableFunds(Customer c){
        return c.getSecurityAccount().getAvailableFunds();
    }

    //Customer's SA value
    public static double getSAValue(Customer c){
        return c.getSecurityAccount().getValueOfSA();
    }

    //Customer's transactions in SA
    public static ArrayList<String> getSecurityTransactions(Customer c){
        return c.getSecurityAccount().getTransactions();
    }

    //Customer's profit made from SA.
    public static double getProfitMadeInSecurity(Customer c){
        return c.getSecurityAccount().getProfitMade();
    }

    //Customer's unrealized profits/Loss from SA
    public static double getUnrealized(Stocks s, customerStock cst){
        return cst.getUnrealizedProfitOrLoss(s);
    }

    //Manager side functions:

    // for change interest rate button change bond interest rate
    public static void changeBondInterestRate(Bonds b, double newInterestRate) {
        b.changeInterest(newInterestRate);
    }

    //refresh stock value, update bonds maturity, increase day, update Loan
    public static void update() {
        //function provided by the database
        Banker banker = SystemApp.bankers.get(0);
        banker.updateExisitngStocks();

        Iterator<Customer> i = customers.listIterator();//UPDATING EACH CUSTOMER'S BONDS BY A DAY.
        while(i.hasNext()){
            Customer c = i.next();
            ArrayList<customerBond> customersBonds = c.getSecurityAccount().getBonds();
            ListIterator<customerBond> j = customersBonds.listIterator();
            while(j.hasNext()){
                j.next().updateDaysMatured();
            }
        }
    }


    //Create a new Stock from Manager end
    public static void ManagerCreateNewStock(Banker b, String ticker, String CompanyName, double newPrice) {
        //stock s should have a stock name, stock ticker, and stock price
        b.createNewStock(ticker, CompanyName, newPrice);
    }

    public static void init() {
    	if(database.dataFindAllBonds() == null) {
    		bankers.get(0).initBonds();
    		System.out.println("Init Bonds!");
    	}
    	if(database.dataFindAllStocks() == null) {
    		bankers.get(0).createInitialStocks();
    		System.out.println("Init Stocks!");
    	}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Object[][] getUserStock(Customer c) {
    	SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
    	Object[][] data = new Object[sa.getStock().size()][7];
    	for(int i = 0; i < sa.getStock().size(); i++) {
    		data[i][0] = sa.getStock().get(i).getTicker();
    		data[i][1] = sa.getStock().get(i).getStockName();
    		data[i][2] = sa.getStock().get(i).getPriceBoughtAt();
    		data[i][3] = sa.getStock().get(i).getPricePerShare();
    		data[i][4] = sa.getStock().get(i).getNumShares();
    		data[i][5] = String.valueOf((sa.getStock().get(i).getPricePerShare() - sa.getStock().get(i).getPriceBoughtAt()) / sa.getStock().get(i).getPriceBoughtAt() * 100) + "%";
    		data[i][6] = String.valueOf(sa.getStock().get(i).getPricePerShare() * sa.getStock().get(i).getNumShares());
    	}
    	
//        Object[][] data = {
//                new Object[]{"APPR", "APPLE", 100.25, 110, 20, "15%", 110 * 20},
//                new Object[]{"MICR", "MICROSOFT", 57.26, 48.6, 50, "-30%", 48.6 * 50}
//        };
        return data;
    }
    
    public static Object[][] getUserBonds(Customer c) {
    	SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        Object[][] bonds = new Object[sa.getBond().size()][6];
        for(int i = 0; i < sa.getBond().size(); i++) {
    		bonds[i][0] = sa.getBond().get(i).getBondID();
    		bonds[i][1] = sa.getBond().get(i).getBondType();
    		bonds[i][2] = sa.getBond().get(i).getMaturity();
    		bonds[i][3] = sa.getBond().get(i).getAmount();
    		bonds[i][4] = sa.getBond().get(i).getInterest();
    		bonds[i][5] = sa.getBond().get(i).getDaysMatured();
    	}
        return bonds;
    }

    public static DefaultCategoryDataset getAccountValueData(Customer c) {
//    	SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        DefaultCategoryDataset first = new DefaultCategoryDataset();
//        for(int i = 0; i < sa.getValueOfSA())
//        first.addValue(1, "First", "2013");
//        first.addValue(3, "First", "2014");
//        first.addValue(2, "First", "2015");
//        first.addValue(6, "First", "2016");
//        first.addValue(5, "First", "2017");
//        first.addValue(12, "First", "2018");
        return first;
    }

    public static ArrayList<DefaultCategoryDataset> getStockData() {
        ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
        List<StocksDB> stock = database.dataFindAllStocks();
        for(int i = 0; i < stock.size(); i++) {
        	DefaultCategoryDataset s = new DefaultCategoryDataset();
        	for(int j = 0; j < stock.get(i).getPriceHistory().size(); j++) {
            	s.addValue(stock.get(i).getPriceHistory().get(j), stock.get(i).getCompanyName(), String.valueOf(j));
        	}
        	data.add(s);
        }
        
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
        return data;
    }

    public static String[] getStockNameList() {
        List<StocksDB> stock = database.dataFindAllStocks();
        String[] stockName = new String[stock.size()];
        for (int i = 0; i < stock.size(); i++) {
        	stockName[i] = stock.get(i).getCompanyName();
        }
        
        return stockName;
    }


    public static Object[][] getBondsData() {
    	List<BondsDB> b = database.dataFindAllBonds();
    	Object[][] bonds = new Object[b.size()][5];
    	for(int i = 0; i < b.size(); i++) {
    		bonds[i][0] = b.get(i).getBondID();
    		bonds[i][1] = b.get(i).getAmount();
    		bonds[i][2] = b.get(i).getInterest();
    		bonds[i][3] = b.get(i).getMaturity();
    		bonds[i][4] = b.get(i).getBondType();
    	}
//        Object[][] bonds = {
//                new String[]{"1 month", "100", "15%"},
//                new String[]{"3 month", "10000", "30%"}
//        };
        return bonds;
    }
    
    public static String[] getBondTypeList() {
        List<BondsDB> bond = database.dataFindAllBonds();
        String[] bondType = new String[bond.size()];
        for (int i = 0; i < bond.size(); i++) {
        	bondType[i] = bond.get(i).getBondID() + ". " + bond.get(i).getMaturity() + " " + bond.get(i).getBondType();
        }
        
        return bondType;
    }

    public static double SearchBondInterest(String ID) {
        BondsDB bond = database.dataFindBonds(ID);
        double interest = bond.getInterest();
        return interest;
    }
    
    public static Object[][] getBankerStock() {
    	List<StocksDB> s = database.dataFindAllStocks();
    	Object[][] stocks = new Object[s.size()][3];
    	for(int i = 0; i < s.size(); i++) {
    		stocks[i][0] = s.get(i).getTicker();
    		stocks[i][1] = s.get(i).getCompanyName();
    		stocks[i][2] = s.get(i).getPriceHistory().get(s.get(i).getPriceHistory().size()-1);
    	}
//        Object[][] stocks = {
//                new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
//                new String[]{"3 month", "10000", "2019-8-3", "2019-11-3", "30%"}
//        };
        return stocks;
    }

//    public static void updateStock() {
//
//    }
//
//    public static void updateBond() {
//
//    }

}

    
