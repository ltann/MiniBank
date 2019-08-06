import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;

public class SystemApp {
    public static ArrayList<ArrayList<String>> bankerAccount = new ArrayList<ArrayList<String>>();
//    public static ArrayList<ArrayList<String>> customerAccount = new ArrayList<ArrayList<String>>();
//    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    //public static ArrayList<Customer> customers = createCustomers();
    public static ArrayList<Banker> bankers = new ArrayList<Banker>();
    //public static int accountNumber = 1;
    //public static int currentCustomer;
   // public static int currentBanker;
    public static Customer currentCustomer;
    public static Account currentAccount;
    public static String report = "";
    public static double fee = 0;
    public static DataAccess database = new DataAccess();

//    public static ArrayList<Customer> createCustomers() {
//    	ArrayList<Customer> customer = new ArrayList<Customer>();
//    	if(database.dataFindAllCustomerBasic() != null) {
//    		List<CustomerBasicDB> allCustomerBasic = database.dataFindAllCustomerBasic();
//    		for(CustomerBasicDB cDB : allCustomerBasic) {
//    			//username, String password, String name, String cell, String address, boolean collateral
//    			Customer c = new Customer(cDB.getUserName(), cDB.getPsw(),
//    					cDB.getName(), cDB.getCell(), cDB.getAddr(), cDB.isColletral());
//    			List<AccountDB> accDB = database.dataFindCustomerAccount(cDB.getUserName());
//    			ArrayList<Account> acc = new ArrayList<Account>();
//    			for(AccountDB aDB : accDB) {
//    				Map<String, Double> currency = aDB.getCurrency();
//    				Currency[] cur = new Currency[3];
//    				for(Map.Entry<String, Double> entry : currency.entrySet()) {
//    					switch(entry.getKey()) {
//    						case "USD": 
//    							cur[0] = new Currency("USD", entry.getValue());
//    							break;
//    						case "RMB": 
//    							cur[1] = new Currency("RMB", entry.getValue());
//    							break;
//    						case "EUR": 
//    							cur[2] = new Currency("EUR", entry.getValue());
//    							break;
//    					}
//    				}
//    				Account a = new Account(aDB.getAccountNumber(), aDB.getType(), cur);
//    				acc.add(a);
//    			}
//    			c.setAcc(acc);
//    			c.setLn(new ArrayList<Loan>());
//	        	System.out.println(c.getLoginName());
//    			customer.add(c);
//    		}
//    	}
//    	return customer;
//    }
    
    public static void checkCurrentCustomer(String Username, String Password) {
    	CustomerBasicDB cb = database.dataFindCustomerBasic(Username);
		Customer cus = new Customer(Username, Password, cb.getName(), cb.getCell(), cb.getAddr(), cb.isColletral());
		List<AccountDB> accdb = database.dataFindCustomerAccount(Username);
		ArrayList<Account> acc = new ArrayList<Account>();
		for(int i = 0; i < accdb.size(); i++) {
			Map<String, Double> cur = accdb.get(i).getCurrency();
			Currency[] c = new Currency[3];
			c[0] = new Currency("USD", cur.get("USD"));
			c[1] = new Currency("RMB", cur.get("RMB"));
			c[2] = new Currency("EUR", cur.get("EUR"));
			Account a = new Account(accdb.get(i).getAccountNumber(), accdb.get(i).getType(), c);
			acc.add(a);
		}
		cus.setAcc(acc);
		currentCustomer = cus;
    }
    
    public static void checkCurrentAccount(int AccountNumber) {
    	AccountDB a = database.dataFindAccount(AccountNumber);
    	Map<String, Double> cur = a.getCurrency();
		Currency[] c = new Currency[3];
		c[0] = new Currency("USD", cur.get("USD"));
		c[1] = new Currency("RMB", cur.get("RMB"));
		c[2] = new Currency("EUR", cur.get("EUR"));
		currentAccount = new Account(a.getAccountNumber(),a.getType(),c);
    }
    
    public static boolean checkUser(String Username, String Password, int type) {
    	if(type == 0) {
    		if(Username.equals("admin") && Password.equals("admin")) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		CustomerBasicDB LoginInfo = database.dataFindCustomerBasic(Username);
        	if(LoginInfo == null) {
        		return false;
        	}
        	else {
        		if(LoginInfo.getPsw().equals(Password)) {
        			checkCurrentCustomer(Username, Password);
        			return true;
        		}
        		else {
        			return false;
        		}
        	} 
    	}
    	
    }

//    public static Banker getCurrentBanker() {
//        return bankers.get(0);
//    }
//
//    public static Customer getCurrentCustomer() {
//        return customers.get(currentCustomer);
//    }

    public static void setDefaultBanker() {
//        ArrayList<String> newUser = new ArrayList<String>();
//        newUser.add("admin");
//        newUser.add("admin");
//        bankerAccount.add(newUser);
//        customerAccount.add(newUser);
//        customers.add(new Customer("admin", "admin", "asdf", "qwe", "asdf", true));
    	bankers.add(new Banker("admin", "admin"));
        // database.dataAddAccount(new AccountDB());
    	CustomerBasicDB cus = new CustomerBasicDB("admin", "admin", "asdf", "qwe", "asdf", true);
    	database.dataAddCustomerBasic(cus);
        
    }

    public static boolean addUser(String username, String password, String name, String cell, String address, int type, boolean collateral) {
        if (type == 0) {
            return false;
        } else {
//            if (customerAccount.size() == 0) {
//                ArrayList<String> newUser = new ArrayList<String>();
//                newUser.add(username);
//                newUser.add(password);
//                customerAccount.add(newUser);
//                customers.add(new Customer(username, password, name, cell, address, collateral));
//                return true;
//            }
        	if(database.dataFindCustomerBasic(username) != null) {
        		return false;
        	}
        	else {
        		CustomerBasicDB cus = new CustomerBasicDB(username, password, name, cell, address, collateral);
            	database.dataAddCustomerBasic(cus);
            	return true;
        	}
//            for (int i = 0; i < customerAccount.size(); i++) {
//                if (username == customerAccount.get(i).get(0)) {
//                    return false;
//                } else {
//                    ArrayList<String> newUser = new ArrayList<String>();
//                    newUser.add(username);
//                    newUser.add(password);
//                    customerAccount.add(newUser);
//                    customers.add(new Customer(username, password, name, cell, address, collateral));
//                    return true;
//                }
//            }
        }
    }

    public static void addAccount(int type, Customer c) {
        //c.addAccount(type, accountNumber++);
    	// 需要更改，删除账户后会无法创建，建议存在Banker中
    	int AccountNumber = database.dataFindAccountSize() + 1;
    	Map<String, Double> cur = new HashMap<String, Double>();
    	cur.put("USD", -3.0);
    	cur.put("RMB", 0.0);
    	cur.put("EUR", 0.0);
        AccountDB account = new AccountDB(c.getLoginName(), AccountNumber, type, cur);
        database.dataAddAccount(account);
        checkCurrentCustomer(c.getLoginName(),c.getPassword());
    }

    public static boolean delAccount(int accountNumber, Customer c) {
        if (database.dataFindAccount(accountNumber).getUserName().equals(c.getLoginName())) {
        	database.dataDeleteAccount(accountNumber);
        	checkCurrentCustomer(c.getLoginName(),c.getPassword());
            return true;
        }
        return false;
    }

    public static void withdraw(int curIndex, double fee, double money, Account a) {
    	String key = "";
    	switch(curIndex) {
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
    	AccountDB acc = SystemApp.database.dataFindAccount(a.getAccountNumber());
    	Map<String, Double> cur = acc.getCurrency();
    	cur.put(key,(cur.get(key)-money-fee));
    	acc.setCurrency(cur);
        SystemApp.database.dataUpdateAccount(a.getAccountNumber(), acc);
    }
    
    public static String transaction(int sendAccountNumber, int accountNumber, double amount, Customer c, int currencyIndex) {
    	String str = "";
    	String curType = "";
    	switch (currencyIndex) {
        case 0:
            curType = "USD";
            break;
        case 1:
        	curType = "RMB";
            break;
        case 2:
        	curType = "EUR";
            break;
        default:
            break;
    }
    	if (database.dataFindAccount(sendAccountNumber).getUserName().equals(c.getLoginName())) {
    		if(database.dataFindAccount(accountNumber) != null) {
    			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    			AccountDB acc = database.dataFindAccount(sendAccountNumber);
        		Map<String, Double> cur = acc.getCurrency();
        		int type = acc.getType();
        		AccountDB rec = database.dataFindAccount(accountNumber);
        		Map<String, Double> recCur = rec.getCurrency();
        		if(type == 1 || type == 3) {
        			if(database.dataFindAccount(accountNumber) != null) {
            			if(cur.get(curType) >= (amount+3)) {
            				// Withdraw
                    		cur.put(curType, cur.get(curType) - (amount+3));
                    		acc.setCurrency(cur);
                    		fee += 3;
                    		database.dataUpdateAccount(sendAccountNumber, acc);
                    		
                    		// Deposit
                    		recCur.put(curType, recCur.get(curType) + amount);
                    		rec.setCurrency(recCur);
                    		database.dataUpdateAccount(accountNumber, rec);
                    		
                    		// Report
                    		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
                    	}
            		}
        		}
        		else {
        			if(database.dataFindAccount(accountNumber) != null) {
            			if(cur.get(curType) >= amount) {
            				// Withdraw
                    		cur.put(curType, cur.get(curType) - amount);
                    		acc.setCurrency(cur);
                    		database.dataUpdateAccount(sendAccountNumber, acc);
                    		
                    		// Deposit
                    		recCur.put(curType, recCur.get(curType) + amount);
                    		rec.setCurrency(recCur);
                    		database.dataUpdateAccount(accountNumber, rec);
                    		
                    		// Report
                    		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
                    	}
            		}
        		}
    		}        
        }
    	checkCurrentCustomer(c.getLoginName(),c.getPassword());
    	return str;
    }

    public static String transferReport(Customer sender, int sendAccountNumber, int receiveAccountNumber, double amount, int currencyIndex) {
        String str = "";
        str += sender.getPerInfomation().getName();
        str += "'s account: ";
        str += sendAccountNumber;
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
        str += " account:  ";
        str += receiveAccountNumber;
        str += "\r\n";
        report += str;
        return str;
    }


//    public static int[] checkAccountNumber(int accountNumber) {
//        int[] a;
//        for (int i = 0; i < customers.size(); i++) {
//            for (int j = 0; j < customers.get(i).getAcc().size(); j++) {
//                if (customers.get(i).getAcc().get(j).getAccountNumber() == accountNumber) {
//                    a = new int[]{i, j};
//                    return a;
//                }
//            }
//        }
//        a = new int[]{-1};
//        return a;
//    }

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
                info += "¥ ";
                break;
            case 2:
                info += "€ ";
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
                info += "¥  ";
                break;
            case 2:
                info += "€  ";
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
            case "¥":
                info += "RMB";
                break;
            case "€":
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
        List<CustomerBasicDB> customers = database.dataFindAllCustomerBasic();
        for (int i = 0; i <= customers.size()-1; i++) {
            answer += "Name : ";
            answer += customers.get(i).getName();
            answer += "  Cellphone :  ";
            answer += customers.get(i).getCell();
            answer += "  Address :  ";
            answer += customers.get(i).getAddr();
            answer += "\nAccount :\n";
            List<AccountDB> acc = database.dataFindCustomerAccount(customers.get(i).getUserName());
            for (int j = 0; j <= acc.size() - 1; j++) {
                answer += getAccountInfo(acc.get(j));
            }
            answer += "\nLoan :\n";
            
//            for (int k = 0; k <= customers.get(i).getLoanNumber() - 1; k++) {
//                answer += customers.get(i).getLn().get(k).getInterest();
//                answer += " ";
//                answer += customers.get(i).getLn().get(k).getCurrency().getName();
//                answer += " ";
//                answer += customers.get(i).getLn().get(k).getCurrency().getSymbol();
//                answer += customers.get(i).getLn().get(k).getCurrency().getBalance();
//                answer += " ";
//            }
//            answer += "\n";
        }
        return answer;
    }

    public static String getAccountInfo(AccountDB acc) {
        String info = "";
        info += acc.getAccountNumber();
        info += " ";
        if(acc.getType()==0){
            info += "Default";
        }
        else if(acc.getType()==1){
            info += "Checking";
        }
        else if(acc.getType()==2){
            info += "Saving";
        }
        else if(acc.getType()==3){
            info += "Security";
        }
        info += "  Account\n";
        
        if(acc.getCurrency().get("USD") != 0){     
            info += "USD: ";
            info += "$";
            info += acc.getCurrency().get("USD");
            info += " ";
        }
        if(acc.getCurrency().get("RMB") != 0){     
            info += "RMB: ";
            info += "¥";
            info += acc.getCurrency().get("RMB");
            info += " ";
        }
        if(acc.getCurrency().get("EUR") != 0){     
            info += "EUR: ";
            info += "€";
            info += acc.getCurrency().get("EUR");
            info += " ";
        }
        info += "\n";
        return info;
    }
    
//    public static void loan(int numMonth, double amount, int currency) {
//        Customer c = customers.get(currentCustomer);
//        double interest;
//        if (numMonth < 5) {
//            interest = 0.02;
//        } else if (numMonth < 12) {
//            interest = 0.01;
//        } else {
//            interest = 0.007;
//        }
//        Loan loan = new Loan(interest, currency);
//        loan.getCurrency().deposit(amount);
//        c.addLoan(loan);
//        c.getAcc().get(0).getC()[currency].deposit(amount);
//    }
//    
//
//    public static boolean payLoan(Loan loan){
//        boolean payable = true;
//        Customer c = customers.get(currentCustomer);
//        char currencyType;
//        if(loan.getCurrencyType() == 1){
//            currencyType = '$';
//        }
//        else if(loan.getCurrencyType() == 2){
//            currencyType = '¥';
//        }
//        else{
//            currencyType = '€';
//        }
//
//        payable = c.hasEnoughMoney(loan.getCurrency().getBalance(), loan.getCurrencyType());
//        if(!payable){
//            System.out.println("You do not have enough money in this currency of type " + currencyType);
//        }
//        else{
//            ListIterator<Account> i = c.getAcc().listIterator();
//            while(i.hasNext()){
//                for(Currency currency: i.next().getC()){
//                    if(currencyType == currency.getSymbol()){
//                        if(currency.getBalance() >= loan.getCurrency().getBalance()){
//                            currency.withdraw(loan.getCurrency().getBalance());
//                            loan.getCurrency().withdraw(loan.getCurrency().getBalance());
//                        }
//                        else{
//                            currency.withdraw(currency.getBalance());
//                            loan.getCurrency().withdraw(currency.getBalance());
//                        }
//                    }
//                }
//                if(loan.getCurrency().getBalance() == 0){
//                    c.removeLoan(loan);
//                    break;
//                }
//            }
//        }
//        return payable;
//    }

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
        SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        //SecurityAccount sa = c.getSecurityAccount();
        //SystemApp.bankers.get(0).addProfits();
        //purchasable = sa.purchaseBond(b, amount);
        if(amount > sa.getAvaliableFunds()){
            purchasable = false;
            //System.out.println("You can't buy this bond at price " + amount + ". You only have "  + " USD left in security acount");
        }
        if(purchasable){
        	double availFunds = sa.getAvaliableFunds() - amount;
        	sa.setAvaliableFunds(availFunds);
            database.dataUpdateSecurityAccount(c.getLoginName(), sa);
        }
        return purchasable;
    }

    //Customer sells a bond

    public static boolean sellBond(Customer c, int index){
        boolean sellable = true;
        SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        ArrayList<customerBond> bond = sa.getBond();
        customerBond b = bond.get(index);
        if(b.isMatured()){
            sa.setAvaliableFunds(sa.getAvaliableFunds() + b.getAmount() + b.getInterest());
            sa.setValueOfSA(sa.getValueOfSA() + b.getInterest());
            sa.setProfitMade(sa.getProfitMade() + b.getInterest());
        }else{
        	sa.setAvaliableFunds(sa.getAvaliableFunds() + b.getAmount());
        	sa.setProfitMade(sa.getProfitMade());
        }
        bond.remove(index);
        sa.setBond(bond);
        database.dataUpdateSecurityAccount(c.getLoginName(), sa);
        return sellable;
    }

    //Customer purchase a stock
    public static boolean purchaseStock(Customer c, int index, int numOfShare) {
        boolean purchasable = true;
        SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        ArrayList<customerStock> cs = sa.getStock();
        List<StocksDB> s = database.dataFindAllStocks();
        StocksDB stock = s.get(index);
        double shareValue = stock.getPriceHistory().get(stock.getPriceHistory().size()-1) * numOfShare;
        if (shareValue > sa.getAvaliableFunds()) {
            //System.out.println("You're available funds are: " + availableFunds + " you cannot purchase this stock share value at: " + shareValue);
            purchasable = false;
        } else {
            sa.setAvaliableFunds(sa.getAvaliableFunds()- shareValue);
            cs.add(new customerStock(stock.getTicker(), stock.getCompanyName(), stock.getPriceHistory().get(stock.getPriceHistory().size()-1), numOfShare, stock.getPriceHistory()));
            sa.setStock(cs);
        }

        if(purchasable){
           database.dataUpdateSecurityAccount(c.getLoginName(), sa);
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
//        Banker banker = SystemApp.bankers.get(0);
//        banker.updateExisitngStocks();
//
//        Iterator<Customer> i = customers.listIterator();//UPDATING EACH CUSTOMER'S BONDS BY A DAY.
//        while(i.hasNext()){
//            Customer c = i.next();
//            ArrayList<customerBond> customersBonds = c.getSecurityAccount().getBonds();
//            ListIterator<customerBond> j = customersBonds.listIterator();
//            while(j.hasNext()){
//                j.next().updateDaysMatured();
//            }
//        }
    	Banker banker = SystemApp.bankers.get(0);
    	banker.updateExisitngStocks();
    	List<SecurityAccountDB> acc = database.dataFindAllSecurityAccount();
    	Iterator<SecurityAccountDB> i = acc.listIterator();//UPDATING EACH CUSTOMER'S BONDS BY A DAY.
    	while(i.hasNext()){
    		SecurityAccountDB c = i.next();
    		ArrayList<customerBond> customersBonds = c.getBond();
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
    	if(sa == null) {
    		System.out.println("---------------------");
    	}
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
    	Object[][] bonds = new Object[b.size()][4];
    	for(int i = 0; i < b.size(); i++) {
    		bonds[i][0] = b.get(i).getBondID();
    		bonds[i][1] = b.get(i).getInterest();
    		bonds[i][2] = b.get(i).getMaturity();
    		bonds[i][3] = b.get(i).getBondType();
    	}
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



}

    
