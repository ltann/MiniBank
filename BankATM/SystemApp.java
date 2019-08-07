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
    public static int accountNumber = 1;
    //public static int currentCustomer;
   // public static int currentBanker;
    public static Customer currentCustomer;
    public static Account currentAccount;
    public static SecurityAccount currentSecurity;
    public static String report = "";
    public static double fee = 0;
    public static DataAccess database = new DataAccess();

    public static ArrayList<Customer> createCustomers() {
    	ArrayList<Customer> customer = new ArrayList<Customer>();
    	if(database.dataFindAllCustomerBasic() != null) {
    		List<CustomerBasicDB> allCustomerBasic = database.dataFindAllCustomerBasic();
    		for(CustomerBasicDB cDB : allCustomerBasic) {
    			//username, String password, String name, String cell, String address, boolean collateral
    			Customer c = new Customer(cDB.getUserName(), cDB.getPsw(),
    					cDB.getName(), cDB.getCell(), cDB.getAddr(), cDB.isColletral());
    			List<AccountDB> accDB = database.dataFindCustomerAccount(cDB.getUserName());
    			ArrayList<Account> acc = new ArrayList<Account>();
    			for(AccountDB aDB : accDB) {
    				Map<String, Double> currency = aDB.getCurrency();
    				Currency[] cur = new Currency[3];
    				for(Map.Entry<String, Double> entry : currency.entrySet()) {
    					switch(entry.getKey()) {
    						case "USD": 
    							cur[0] = new Currency("USD", entry.getValue());
    							break;
    						case "RMB": 
    							cur[1] = new Currency("RMB", entry.getValue());
    							break;
    						case "EUR": 
    							cur[2] = new Currency("EUR", entry.getValue());
    							break;
    					}
    				}
    				Account a = new Account(aDB.getAccountNumber(), aDB.getType(), cur);
    				acc.add(a);
    			}
    			c.setAcc(acc);
    			c.setLn(new ArrayList<Loan>());
	        	System.out.println(c.getLoginName());
    			customer.add(c);
    		}
    	}
    	return customer;
    }
    
    public static void updateReport() {
    	BankDB b = database.dataFindBank();
    	ArrayList<String> rep = b.getReport();
    	rep.add(report);
    	b.setReport(rep);
    	database.dataUpdateBank(b);
    }
    
    public static void updateFee() {
    	BankDB b = database.dataFindBank();
    	ArrayList<Double> rep = new ArrayList<Double>();
    	rep.add(fee);
    	b.setProfit(rep);
    	database.dataUpdateBank(b);
    }
//    public static void saveReport() {
//    	BankDB b = database.dataFindBank();
//    	ArrayList<String> rep = b.getReport();
//    	rep.get(rep.size()-1);
//    }
    
    public static void checkCurrentCustomer(String Username, String Password) {
    	CustomerBasicDB cb = database.dataFindCustomerBasic(Username);
		Customer cus = new Customer(Username, Password, cb.getName(), cb.getCell(), cb.getAddr(), cb.isColletral());
		List<AccountDB> accdb = database.dataFindCustomerAccount(Username);
		SecurityAccountDB Saccdb = database.dataFindSecurityAccount(Username);
		ArrayList<Account> acc = new ArrayList<Account>();
		ArrayList<SecurityAccount> Sacc = new ArrayList<SecurityAccount>();
		for(int i = 0; i < accdb.size(); i++) {
			Map<String, Double> cur = accdb.get(i).getCurrency();
			Currency[] c = new Currency[3];
			c[0] = new Currency("USD", cur.get("USD"));
			c[1] = new Currency("RMB", cur.get("RMB"));
			c[2] = new Currency("EUR", cur.get("EUR"));
			Account a = new Account(accdb.get(i).getAccountNumber(), accdb.get(i).getType(), c);
			acc.add(a);
		}
		if(Saccdb != null) {
			SecurityAccount sa = new SecurityAccount(3, Integer.parseInt(Saccdb.getAccountNumber()), Saccdb.getStock(), Saccdb.getBond(), Saccdb.getProfitMade());
			cus.setSacc(sa);
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
    
    public static void checkCurrentSecurity(String username) {
    	SecurityAccountDB sa = database.dataFindSecurityAccount(username);
    	
    }
    
    public static boolean checkUser(String Username, String Password, int type) {
    	System.out.println(Username + " " + Password);
    	
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
        			System.out.println(Password);
        			return true;
        		}
        		else {
        			return false;
        		}
        	} 
    	}
    	
    }

    public static void setDefaultBanker() {
    	if(database.dataFindBankerBasic("admin") == null) {
    		BankerBasicDB bb = new BankerBasicDB("admin", "admin");
        	database.dataAddBankerBasic(bb);
        	ArrayList<String> rep = new ArrayList<String>();
        	rep.add(report);
        	ArrayList<Double> pro = new ArrayList<Double>();
        	pro.add(fee);
        	BankDB bank = new BankDB(rep, pro, accountNumber);
        	database.dataAddBank(bank);
    	}
    	BankDB b = database.dataFindBank();
    	accountNumber = b.getAccountNum();
    	if(b.getReport().size() != 0) {
    		report = b.getReport().get(b.getReport().size()-1);
    	}
    	if(b.getProfit().size() != 0) {
    		fee = b.getProfit().get(b.getProfit().size()-1);
    	}
//    	fee = b.getProfit().get(b.getProfit().size()-1);
//    	if(database.dataFindBank() == null) {
//    		ArrayList<String> rep = new ArrayList<String>();
//    		ArrayList<Double> pro = new ArrayList<Double>();
//    		rep.add("");
//    		pro.add(0.0);
//    		BankDB b = new BankDB(rep, pro, accountNumber);
//    	}
//    	else {
//    		BankDB b = database.dataFindBank();
//        	fee = b.getProfit().get(b.getProfit().size()-1);
//        	accountNumber = b.getAccountNum();
//        	report = b.getReport().get(b.getReport().size()-1);
//    	}
    	
        bankers.add(new Banker("admin","admin"));
    	CustomerBasicDB cus = new CustomerBasicDB("admin", "admin", "asdf", "qwe", "asdf", true, 0, 0, 0);
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
        		CustomerBasicDB cus = new CustomerBasicDB(username, password, name, cell, address, collateral, 0, 0, 0);
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
        if(type == 3) {
        	ArrayList<customerStock> stock = new ArrayList<customerStock>();
        	ArrayList<customerBond> bond = new ArrayList<customerBond>();
        	ArrayList<String> transaction = new ArrayList<String>();
        	ArrayList<Double> valueOfSA = new ArrayList<Double>();
        	valueOfSA.add(-3.0);
        	SecurityAccountDB sacc = new SecurityAccountDB(currentCustomer.getLoginName(), String.valueOf(accountNumber), stock, bond, -3.0, valueOfSA, transaction, 0.0);
        	fee += 3;
        	SystemApp.updateFee();
        	database.dataAddSecurityAccount(sacc);
        	BankDB b = database.dataFindBank();
        	b.setAccountNum(accountNumber++);
        	database.dataUpdateBank(b);
        	checkCurrentCustomer(c.getLoginName(),c.getPassword());
        }
        else {
        	Map<String, Double> cur = new HashMap<String, Double>();
        	cur.put("USD", -3.0);
        	cur.put("RMB", 0.0);
        	cur.put("EUR", 0.0);
            AccountDB account = new AccountDB(c.getLoginName(), accountNumber++, type, cur);
            database.dataAddAccount(account);
            fee += 3;
        	SystemApp.updateFee();
            BankDB b = database.dataFindBank();
        	b.setAccountNum(accountNumber);
        	database.dataUpdateBank(b);
            checkCurrentCustomer(c.getLoginName(),c.getPassword());
        }
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
    
    public static String transaction(int type, int sendAccountNumber, int accountNumber, double amount, Customer c, int currencyIndex) {
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
		if(database.dataFindAccount(sendAccountNumber) != null) {//Checking or Saving
			AccountDB acc = database.dataFindAccount(sendAccountNumber);
    		Map<String, Double> cur = acc.getCurrency();
    		int t = acc.getType();
    		if(database.dataFindAccount(accountNumber) != null) { // To Checking or Saving
    			AccountDB rec = database.dataFindAccount(accountNumber);
        		Map<String, Double> recCur = rec.getCurrency();
        		if(t == 1) {// Checking
        			if(cur.get(curType) >= (amount+3)) {
        				// Withdraw
                		cur.put(curType, cur.get(curType) - (amount+3));
                		acc.setCurrency(cur);
                		fee += 3;
                		SystemApp.updateFee();
                		database.dataUpdateAccount(sendAccountNumber, acc);
                		
            			// Deposit
                		recCur.put(curType, recCur.get(curType) + amount);
                		rec.setCurrency(recCur);
                		database.dataUpdateAccount(accountNumber, rec);

                		// Report
                		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
                	}
        		}
        		else { // Saving
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
    		else { // To Security
    			SecurityAccountDB rec = database.dataFindSecurityAccount(accountNumber);
    			if(t == 1) {// Checking
        			if(cur.get(curType) >= (amount+3)) {
        				// Withdraw
                		cur.put(curType, cur.get(curType) - (amount+3));
                		acc.setCurrency(cur);
                		fee += 3;
                		SystemApp.updateFee();
                		database.dataUpdateAccount(sendAccountNumber, acc);
                		
            			// Deposit
                		rec.setAvaliableFunds(rec.getAvaliableFunds() + amount);
                		ArrayList<Double> vos = rec.getValueOfSA();
                		double value = vos.get(vos.size()-1) + amount;
                		vos.remove(vos.size()-1);
                		vos.add(value);
                		rec.setValueOfSA(vos);
                		database.dataUpdateSecurityAccount(accountNumber, rec);
                		
                		// Report
                		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
                	}
        		}
        		else { // Saving
        			if(cur.get(curType) >= amount) {
        				// Withdraw
                		cur.put(curType, cur.get(curType) - amount);
                		acc.setCurrency(cur);
                		database.dataUpdateAccount(sendAccountNumber, acc);
                		
                		// Deposit
                		rec.setAvaliableFunds(rec.getAvaliableFunds() + amount);
                		ArrayList<Double> vos = rec.getValueOfSA();
                		double value = vos.get(vos.size()-1) + amount;
                		vos.remove(vos.size()-1);
                		vos.add(value);
                		rec.setValueOfSA(vos);
                		database.dataUpdateSecurityAccount(accountNumber, rec);
                		
                		// Report
                		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
                	}
        		}
    		}
    		
		}
		else if(database.dataFindSecurityAccount(sendAccountNumber) != null) { // Security
			SecurityAccountDB sa = database.dataFindSecurityAccount(sendAccountNumber);
//			AccountDB acc = database.dataFindAccount(sendAccountNumber);
			if(database.dataFindAccount(accountNumber) != null) { // To Checking or Saving
				AccountDB rec = database.dataFindAccount(accountNumber);
				if(sa.getAvaliableFunds() >= amount) {
					// Withdraw
	        		sa.setAvaliableFunds(sa.getAvaliableFunds() - amount);
	        		database.dataUpdateSecurityAccount(accountNumber, sa);
	        		
	        		// Deposit
            		int t = rec.getType();
            		Map<String, Double> recCur = rec.getCurrency();
            		recCur.put("USD", recCur.get("USD") + amount);
            		rec.setCurrency(recCur);
            		database.dataUpdateAccount(accountNumber, rec);
	        		
	        		// Report
	        		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
	        	}
			}
			else { // To Security
				SecurityAccountDB recsa = database.dataFindSecurityAccount(accountNumber);
				if(sa.getAvaliableFunds() >= amount) {
					// Withdraw
	        		sa.setAvaliableFunds(sa.getAvaliableFunds() - amount);
	        		database.dataUpdateSecurityAccount(accountNumber, sa);
	        		
	        		// Deposit
	        		recsa.setAvaliableFunds(sa.getAvaliableFunds() + amount);
	    			database.dataUpdateSecurityAccount(accountNumber, recsa);
	        		
	        		// Report
	        		str = transferReport(c, sendAccountNumber, accountNumber, amount, currencyIndex);
	        	}
			}	
    	}
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
//        updateReport();
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
//        updateReport();
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
        report += info;
//        updateReport();
        return info;
    }

    public static void stockReport(String ticker, String companyName, double price, int numOfShare, double amount, int type) {
    	String info = "";
    	info += currentCustomer.getPerInfomation().getName();
    	if(type == 0) { // Buy
    		info += ": Buy\n";
    	}
    	else {
    		info += ": Sell\n";
    	}
    	info += "Ticker: ";
    	info += ticker;
    	info += "\n";
    	info += "Company Name: ";
    	info += companyName;
    	info += "\n";
    	info += "Price: ";
    	info += price;
    	info += "\n";
    	info += "Number Of Share: ";
    	info += numOfShare;
    	info += "\n";
    	info += "Amount: ";
    	info += amount;
    	info += "\n";
    	
    	report += info;
    }
    
    public static void bondReport(int maturity, String bondType, double interest, double amount, int type) {
    	String info = "";
    	if(type == 0) { // Buy
    		info += ": Buy\n";
    	}
    	else {
    		info += ": Sell\n";
    	}
    	info += currentCustomer.getPerInfomation().getName();
    	info += ":\n";
    	info += "Period: ";
    	info += maturity + " " + bondType;
    	info += "\n";
    	info += "Interest Rate: ";
    	info += interest + " / per day";
    	info += "\n";
    	info += "Amount: ";
    	info += amount;
    	
    	report += info;
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
            SecurityAccountDB sa = database.dataFindSecurityAccount(customers.get(i).getUserName());
            if(sa != null) {
            	answer += sa.getAccountNumber();
                answer += " Security\n";
                answer += "USD: ";
                answer += "$";
                answer += sa.getAvaliableFunds();
            }
            
            answer += "\nLoan :\n\n";
            
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
    
    public static void loan(int index, double amount) {
        AccountDB acc = database.dataFindAccount(currentAccount.getAccountNumber());
        //Map<String, Double> cur = acc.getCurrency();
        
        int loanNumber = 0;
        if(database.dataFindCustomerBasic(currentCustomer.getLoginName()).getBondNum() != 0) {
        	loanNumber = database.dataFindCustomerBasic(currentCustomer.getLoginName()).getBondNum();
        }
        double interest = 0;
        int BoughtAt = database.dataFindBank().getReport().size();
        switch(index) {
        case 0:
        	interest = 0.007;
        	break;
        case 1:
        	interest = 0.01;
        	break;
        case 2:
        	interest = 0.02;
        	break;
        }
        LoanDB l = new LoanDB(++loanNumber, interest, amount, index, BoughtAt, currentCustomer.getLoginName());
//        cur.put("USD", cur.get("USD")+amount);
//        acc.setCurrency(cur);
//        database.dataUpdateAccount(currentAccount.getAccountNumber(), acc);
        database.dataAddLoan(l, acc);
        
    }
    
    public static Object[][] getUserLoan() {
    	Object[][] data = null;
    	List<LoanDB> loan = database.dataFindLoan(currentCustomer.getLoginName());
    	if(loan == null) {
    		return data;
    	}
    	data = new Object[loan.size()][5];
    	for(int i = 0; i < loan.size(); i++) {
    		data[i][0] = loan.get(i).getLoanID();
    		data[i][1] = loan.get(i).getInterest();
    		data[i][2] = loan.get(i).getAmount();
    		data[i][3] = loan.get(i).getType();
    		data[i][4] = loan.get(i).getBoughtAt();
    	}
    	return data;
    }

    // Update Loan
//    public static void updateLoan() {
//    	List<LoanDB> loans = database.dataFindAllLoan();
//    	for(LoanDB loan: loans) {
//    		double amount = loan.getAmount();
//    		amount += (loan.getInterest() + 1) * loan.getAmount();
//    		loan.setAmount(amount);
//    		database.dataUpdate;
//    	}
//    	
//    }
    
    public static boolean payLoan(int index){
        boolean payable = false;
        char currencyType = '$';
        List<LoanDB> loans = database.dataFindLoan(currentCustomer.getLoginName());
        LoanDB loan = loans.get(index);
        AccountDB acc = database.dataFindAccount(currentAccount.getAccountNumber());
//        Map<String, Double> cur = acc.getCurrency();
//        if(cur.get("USD") < loan.getAmount()){
//            System.out.println("You do not have enough money payback the loan! ");
//            return payable;
//        }
//        else{
//            double balance = cur.get("USD");
//            balance -= loan.getAmount();
//            cur.put("USD", balance);
//            acc.setCurrency(cur);
//            database.dataUpdateAccount(acc.getAccountNumber(), acc);
            database.dataDeleteLoan(loan.getLoanID(), acc);
            payable = true;
//        }
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
    public static boolean purchaseBond(Customer c, int ID, double amount) {
        boolean purchasable = false;
        SecurityAccountDB sa = database.dataFindSecurityAccount(currentCustomer.getLoginName());
        ArrayList<customerBond> cb;
        if(sa.getBond() != null) {
        	cb = sa.getBond();
        }
        else {
        	cb = new ArrayList<customerBond>();
        }
        String bondID = "";
        int BondID = 0;
        int daysMatured = 0;
        switch(ID) {
        case 0:
        	bondID = "a";
        	daysMatured = 7;
        	break;
        case 1:
        	bondID = "b";
        	daysMatured = 30;
        	break;
        case 2:
        	bondID = "c";
        	daysMatured = 90;
        	break;
        }
        System.out.println(ID);
        BondsDB b = database.dataFindBonds(bondID);
        if(amount <= sa.getAvaliableFunds()){
            purchasable = true;
            //System.out.println("You can't buy this bond at price " + amount + ". You only have "  + " USD left in security acount");
        }
        if(purchasable){
        	double availFunds = sa.getAvaliableFunds() - amount;
        	sa.setAvaliableFunds(availFunds);
        	if(database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerBondID().size() == 0) {
            	BondID = 1;
            }
            else {
            	BondID = database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerBondID().get(database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerBondID().size()-1);
            }
        	customerBond cusBond = new customerBond(b.getBondID(), b.getBondType(), b.getMaturity(), amount, b.getInterest(), daysMatured, ++BondID);
        	cb.add(cusBond);
        	sa.setBond(cb);
        	database.dataUpdateSecurityAccount(currentCustomer.getLoginName(), sa);
        	bondReport(b.getMaturity(), b.getBondType(), b.getInterest(), amount, 0);
        }
        return purchasable;
    }

    //Customer sells a bond

    public static boolean sellBond(Customer c, int index){
        boolean sellable = true;
        SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        ArrayList<customerBond> bond = sa.getBond();
        ArrayList<Double> vos = sa.getValueOfSA();
        customerBond b = bond.get(index);
        if(b.isMatured()){
            sa.setAvaliableFunds(sa.getAvaliableFunds() + b.getAmount() + b.getInterest());
            int days = 0;
            if(b.getMaturity() == 1) { // 1 month or 1 week
            	if(b.getBondType() == "week") {
            		days = 7;
            	}
            	else {
            		days = 30;
            	}
            }
            else { //3 month
            	days = 90;
            }
            double value = vos.get(vos.size()-1) + (b.getInterest() * days);
            vos.remove(vos.size()-1);
            vos.add(value);
            sa.setValueOfSA(vos);
            sa.setProfitMade(sa.getProfitMade() + b.getInterest());
        }else{
        	sa.setAvaliableFunds(sa.getAvaliableFunds() + b.getAmount());
        	sa.setProfitMade(sa.getProfitMade());
        }
        bond.remove(index);
        sa.setBond(bond);
        database.dataUpdateSecurityAccount(c.getLoginName(), sa);
        bondReport(b.getMaturity(), b.getBondType(), b.getInterest(), b.getAmount(), 1);
        return sellable;
    }

    //Customer purchase a stock
    public static boolean purchaseStock(Customer c, int index, int numOfShare) {
        boolean purchasable = false;
        SecurityAccountDB sa = database.dataFindSecurityAccount(currentCustomer.getLoginName());
        ArrayList<customerStock> cs;
        List<StocksDB> s = database.dataFindAllStocks();
        StocksDB stock = s.get(index);
        int stockID = 0;
        if(sa.getStock() != null) {
        	cs = sa.getStock();
        }
        else {
        	cs = new ArrayList<customerStock>();
        }
        double shareValue = stock.getPriceHistory().get(stock.getPriceHistory().size()-1) * numOfShare;
        if (shareValue > sa.getAvaliableFunds()) {
            //System.out.println("You're available funds are: " + availableFunds + " you cannot purchase this stock share value at: " + shareValue);
            purchasable = false;
        } else {
            sa.setAvaliableFunds(sa.getAvaliableFunds()- shareValue);
            sa.setProfitMade(sa.getProfitMade() - shareValue);
            if(database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerStockID().size() == 0) {
            	stockID = 1;
            }
            else {
            	stockID = database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerStockID().get(database.dataFindSecurityAccount(currentCustomer.getLoginName()).getCustomerStockID().size()-1);
            }
            final int BoughtAt = stock.getPriceHistory().size();
            customerStock custock = new customerStock(stock.getTicker(), stock.getCompanyName(), stock.getPriceHistory().get(BoughtAt-1), numOfShare, stock.getPriceHistory(), ++stockID);
            cs.add(custock);
            sa.setStock(cs);
            purchasable = true;
            stockReport(stock.getTicker(), stock.getCompanyName(), stock.getPriceHistory().get(BoughtAt-1), numOfShare, shareValue, 0);
        }
        if(purchasable){
           database.dataUpdateSecurityAccount(c.getLoginName(), sa);
        }
        
        return purchasable;
    }

    //Customer sell a stock
    public static boolean sellStock(Customer c, int index) {
        boolean sellable = true;
        SecurityAccountDB sa = database.dataFindSecurityAccount(c.getLoginName());
        ArrayList<Double> vos = sa.getValueOfSA();
        ArrayList<customerStock> stock = sa.getStock();
        customerStock s = stock.get(index);
        StocksDB st = database.dataFindStocks(s.getTicker());
        //SystemApp.bankers.get(0).addProfits();
        //sellable = sa.sellStock(s, database.dataFindStocks(s.getTicker()) ,numOfShare); //second parameter needs to be set as the currentPriceStock
        double shareValue = st.getPriceHistory().get(st.getPriceHistory().size()-1) * s.getNumShares();
        double profits = shareValue - (s.getNumShares() * s.getPriceBoughtAt());
        sa.setAvaliableFunds(sa.getAvaliableFunds()+shareValue);
        double value = vos.get(vos.size()-1) + profits;
        vos.remove(vos.size()-1);
        vos.add(value);
        sa.setValueOfSA(vos);
        stock.remove(s);
        //Transactions.add("Sold " + numOfShares + " of " + customerStock.getStockName() + " Share(s) at " + currentStockPrice);
        sa.setProfitMade(sa.getProfitMade() + shareValue);
        sa.setStock(stock);
        stockReport(st.getTicker(), st.getCompanyName(), st.getPriceHistory().get(st.getPriceHistory().size()-1), s.getNumShares(), shareValue, 1);
        if(sellable){
            database.dataUpdateSecurityAccount(c.getLoginName(), sa);
        }
        return sellable;
    }


    //Manager side functions:

    // for change interest rate button change bond interest rate
    public static void changeBondInterestRate(Bonds b, double newInterestRate) {
        b.changeInterest(newInterestRate);
    }

    //refresh stock value, update bonds maturity, increase day, update Loan
    public static void update() {
    	updateBankDB();
    	updateReport();
    	report = "";
    	Banker banker = SystemApp.bankers.get(0);
    	banker.updateExisitngStocks();
    	
    	List<SecurityAccountDB> acc = database.dataFindAllSecurityAccount();
    	for(int i = 0; i < acc.size(); i++) {
    		SecurityAccountDB c = acc.get(i);
    		if(c.getBond() == null) {
    			continue;
    		}
    		ArrayList<customerBond> customersBonds = c.getBond();
    		for(int j = 0; j < customersBonds.size(); j++) {
    			System.out.println(customersBonds.get(j).getDaysMatured());
    			customerBond cb = customersBonds.get(j);
    			cb.setDaysMatured(customersBonds.get(j).getDaysMatured()-1);
    			customersBonds.set(j, cb);
    			System.out.println(customersBonds.get(j).getDaysMatured());
    		}
    		c.setBond(customersBonds);
    		//Update ValueOfSA
    		ArrayList<Double> vos = c.getValueOfSA();
    		double value = vos.get(vos.size()-1);
    		vos.add(value);
    		c.setValueOfSA(vos);
    		
    		database.dataUpdateSecurityAccount(acc.get(i).getUserName(), acc.get(i));  		
    	}
    	List<LoanDB> l = database.dataFindAllLoan();
    	for (int i = 0; i < l.size(); i++) {
    		LoanDB ld =  l.get(i);
    		ld.setAmount(ld.getAmount() + ld.getInterest() * ld.getAmount());
    		database.dataUpdateLoan(l.get(i).getLoanID(), l.get(i).getUserName(), ld);
    	}
    	
    	// Update BankDB
    }
    
    public static void updateBankDB() {
    	BankDB b = database.dataFindBank();
    	b.setAccountNum(accountNumber);
    	ArrayList<String> rep = b.getReport();
    	rep.add(report);
    	b.setReport(rep);
    	ArrayList<Double> pro = b.getProfit();
    	pro.add(fee);
    	b.setProfit(pro);
    	database.dataUpdateBank(b);
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
    	SecurityAccountDB sa = database.dataFindSecurityAccount(currentCustomer.getLoginName());
    	Object[][] data = null;
    	if(sa != null) {
    		List<customerStock> stock = sa.getStock();
        	if(stock == null) {
        		return data;
        	}
        	data = new Object[sa.getStock().size()][7];
        	for(int i = 0; i < sa.getStock().size(); i++) {
        		data[i][0] = sa.getStock().get(i).getTicker();
        		data[i][1] = sa.getStock().get(i).getStockName();
        		data[i][2] = String.format("%.2f",sa.getStock().get(i).getPriceBoughtAt());
        		ArrayList<Double> history = database.dataFindStocks(sa.getStock().get(i).getTicker()).getPriceHistory();
        		data[i][3] = String.format("%.2f",history.get(history.size()-1));
        		data[i][4] = sa.getStock().get(i).getNumShares();
        		data[i][5] = String.format("%.2f",(((Double.parseDouble((String)data[i][3]) - Double.parseDouble((String)data[i][2])) / Double.parseDouble((String)data[i][2]) * 100))) + "%";
        		data[i][6] = String.format("%.2f",(Double.parseDouble((String)data[i][3]) * (int)data[i][4]));
        	}
    	}
    	
        return data;
    }
    
    public static Object[][] getUserBonds(Customer c) {
    	SecurityAccountDB sa = database.dataFindSecurityAccount(currentCustomer.getLoginName());
    	Object[][] bonds = null;
    	if(sa.getBond() != null) {
    		List<customerBond> bond = sa.getBond();
	    	bonds = new Object[sa.getBond().size()][6];
	        for(int i = 0; i < sa.getBond().size(); i++) {
	    		bonds[i][0] = sa.getBond().get(i).getCustomerBondID();
	    		bonds[i][1] = sa.getBond().get(i).getMaturity();
	    		bonds[i][2] = sa.getBond().get(i).getBondType();
	    		bonds[i][3] = sa.getBond().get(i).getAmount();
	    		bonds[i][4] = sa.getBond().get(i).getInterest();
	    		bonds[i][5] = sa.getBond().get(i).getDaysMatured();
	    	}
    	}
        return bonds;
    }

    public static DefaultCategoryDataset getAccountValueData(Customer c) {
    	SecurityAccountDB sa = database.dataFindSecurityAccount(currentCustomer.getLoginName());
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        ArrayList<Double> valueHistory = sa.getValueOfSA();
        int days = valueHistory.size();
        if(days >= 10) {
        	days = 10;
        }
        for(int i = 0; i < days; i++) {
        	data.addValue(valueHistory.get(valueHistory.size() - days + i), "Account Value", String.valueOf(valueHistory.size()- days + i));
        }
        return data;
    }

    public static ArrayList<DefaultCategoryDataset> getStockData() {
        ArrayList<DefaultCategoryDataset> data = new ArrayList<DefaultCategoryDataset>();
        List<StocksDB> stock = database.dataFindAllStocks();
        for(int i = 0; i < stock.size(); i++) {
        	DefaultCategoryDataset s = new DefaultCategoryDataset();
        	int days = stock.get(i).getPriceHistory().size();
            if(days >= 10) {
            	days = 10;
            }
        	for(int j = 0; j < days; j++) {
            	s.addValue(stock.get(i).getPriceHistory().get(stock.get(i).getPriceHistory().size() - days + j), stock.get(i).getCompanyName(), String.valueOf(stock.get(i).getPriceHistory().size() - days + j));
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
    	//System.out.println(s.get(0).getPriceHistory().size());
    	Object[][] stocks = new Object[s.size()][3];
    	for(int i = 0; i < s.size(); i++) {
    		stocks[i][0] = s.get(i).getTicker();
    		stocks[i][1] = s.get(i).getCompanyName();
    		if(s.get(i).getPriceHistory().size() == 0) {
    			continue;
    		}
    		
        	stocks[i][2] = s.get(i).getPriceHistory().get(s.get(i).getPriceHistory().size()-1);
    		
    	}
//        Object[][] stocks = {
//                new String[]{"1 month", "100", "2019-8-1", "2019-9-1", "15%"},
//                new String[]{"3 month", "10000", "2019-8-3", "2019-11-3", "30%"}
//        };
        return stocks;
    }



}

    
