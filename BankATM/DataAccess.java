import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAccess {
	private static MongoClientURI uri;
	private static MongoClient client;
	private static MongoDatabase db;
	 
	public DataAccess() {
		// Initialize connection with database
        uri  = new MongoClientURI("mongodb://bank:cs591b1@ds157857.mlab.com:57857/bank"); 
        client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());

	}
	
	/*-----------------------userInfo Collection/SystemApp.customers(part of it), SystemApp.customerAccount-----------------------
	 * Collection: customer (basic information)
	 * 				 (String) userName, 
	 * 				 (String) name, 
	 * 				 (String) address,
	 * 				 (String) cell,  
	 * 				 (String) password,
	 * 				 (boolean) collateral
	 * Operation: 1) add customer information
	 * 			  2) find specific customer information :
	 * 				 relative field : public static ArrayList<Customer> customers --in SystemApp.java
	 * 			  3) update specific customer information
	 * 			  4) delete specific customer information
	 * 			  5) find all customers information
	*/
	
	// 1) add customer information
	public static boolean dataAddCustomerBasic(CustomerBasicDB customerDB) {
		MongoCollection<Document> customerBasicInfo = db.getCollection("customerBasicInfo");
		if(dataFindCustomerBasic(customerDB.getUserName()) == null){
			customerBasicInfo.insertOne(new Document("userName", customerDB.getUserName())
				.append("psw", customerDB.getPsw())
			    .append("name", customerDB.getName())
			    .append("cell", customerDB.getCell())
			    .append("addr", customerDB.getAddr())
			    .append("collateral", customerDB.isColletral())
			    .append("loanNum", customerDB.getLoanNum())
			    .append("stockNum", customerDB.getStockNum())
			    .append("bondNum", customerDB.getBondNum())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific customer information
	public static CustomerBasicDB dataFindCustomerBasic(String userName) {
		MongoCollection<Document> customerBasicInfo = db.getCollection("customerBasicInfo");
		Document findQuery = new Document("userName", userName);
		MongoCursor<Document> cursor = customerBasicInfo.find(findQuery).iterator();
		CustomerBasicDB customer = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        customer = new CustomerBasicDB(userName,
		        		(String)doc.get("psw"), 
		        		(String)doc.get("name"), 
		        		(String)doc.get("cell"), 
		        		(String)doc.get("addr"), 
		        		(boolean)doc.get("collateral"), 
		        		(int)doc.get("loanNum"), 
		        		(int)doc.get("stockNum"), 
		        		(int)doc.get("bondNum")
		        		);
		    }
		} finally {
			cursor.close();
		}
		return customer;
	}
	
	// 3) update specific customer information
	public static boolean dataUpdateCustomerBasic(String userName, CustomerBasicDB customerDB) {
		MongoCollection<Document> customerBasicInfo = db.getCollection("customerBasicInfo");
		if(dataFindCustomerBasic(userName) != null) {
			Document updateQuery = new Document("userName", userName);
			Document updateCustomerBasic = new Document("userName", userName)
					.append("psw", customerDB.getPsw())
					.append("name", customerDB.getName())
					.append("cell", customerDB.getCell())
					.append("addr", customerDB.getAddr())
					.append("collateral", customerDB.isColletral())
					.append("loanNum", customerDB.getLoanNum())
					.append("stockNum", customerDB.getStockNum())
					.append("bondNum", customerDB.getBondNum())
					;
			customerBasicInfo.updateOne(updateQuery, new Document("$set",updateCustomerBasic));
			return true;
		}
		return false;
	}

	// 4) delete specific customer information
	public static boolean dataDeleteCustomerBasic(String userName) {
		MongoCollection<Document> customerBasicInfo = db.getCollection("customerBasicInfo");
		if(dataFindCustomerBasic(userName) != null) {
			Document deleteQuery = new Document("userName", userName);
			customerBasicInfo.deleteOne(deleteQuery);
			return true;
		}
		return false;
	}
	
	// 5) find all specific customer information
	public static List<CustomerBasicDB> dataFindAllCustomerBasic() {
		MongoCollection<Document> customerBasicInfo = db.getCollection("customerBasicInfo");
		MongoCursor<Document> cursor = customerBasicInfo.find().iterator();
		List<CustomerBasicDB> allCustomerBasic = new ArrayList<CustomerBasicDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        CustomerBasicDB cDB = dataFindCustomerBasic((String)doc.get("userName"));
		        allCustomerBasic.add(cDB);
		    }
		} finally {
			cursor.close();
		}
//		if(allCustomerBasic.isEmpty())
//			return null;
		return allCustomerBasic;
	}


	/*-----------------------AccountInfo Collection-----------------------
	 * Collection: account information (checking, saving) :
	 * 				 (String) userName, 
	 * 				 (String) accountNumber, 
	 * 				 (HashMap<String, Stock>) stock, 
	 * 				 (HashMap<String, Bonds>) bond, 
	 * 				 (Currency) balance
	 * Operation: 1) add account 
	 * 			  2) find specific account
	 * 			  3) update specific account 
	 * 			  4) delete specific account
	 * 			  5) find specific customer's accounts
	 * 			  6) find all accounts
	*/
	
	// 1) add account
	public static boolean dataAddAccount(AccountDB accountDB) {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		if(dataFindAccount(accountDB.getAccountNumber()) == null){
			AccountInfo.insertOne(new Document("userName", accountDB.getUserName())
				.append("accountNumber", accountDB.getAccountNumber())
			    .append("type", accountDB.getType())
			    .append("currency", accountDB.getCurrency())
			);
			return true;
		}
		return false;
	}
		
	// 2) find specific account
	public static AccountDB dataFindAccount(int accountNumber) {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		Document findQuery = new Document("accountNumber", accountNumber);
		MongoCursor<Document> cursor = AccountInfo.find(findQuery).iterator();
		AccountDB account = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        account = new AccountDB((String)doc.get("userName"),
		        		(int)doc.get("accountNumber"), 
			        	(int)doc.get("type"), 
			        	(Map<String, Double>)doc.get("currency")
		        		);
			}
		} finally {
			cursor.close();
		}
		return account;
	}
		
	// 3) update specific account
	public static boolean dataUpdateAccount(int accountNumber, AccountDB accountDB) {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		if(dataFindAccount(accountNumber) != null) {
			Document updateQuery = new Document("accountNumber", accountNumber);
			Document updateAccount = new Document("userName", accountDB.getUserName())
					.append("accountNumber", accountDB.getAccountNumber())
					.append("type", accountDB.getType())
					.append("currency", accountDB.getCurrency())
					;
			AccountInfo.updateOne(updateQuery, new Document("$set",updateAccount));
			return true;
		}
		return false;
	}

	// 4) delete specific account
	public static boolean dataDeleteAccount(int accountNumber) {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		if(dataFindAccount(accountNumber) != null) {
			Document deleteQuery = new Document("accountNumber", accountNumber);
			AccountInfo.deleteOne(deleteQuery);
			return true;
		}
		return false;
	}

	// 5) find specific customer's account
	public static List<AccountDB> dataFindCustomerAccount(String userName) {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		Document findQuery = new Document("userName", userName);
		MongoCursor<Document> cursor = AccountInfo.find(findQuery).iterator();
		List<AccountDB> allAccount = new ArrayList<AccountDB>();
		try {
			while(cursor.hasNext()) {
				Document doc = cursor.next();
			    AccountDB aDB = dataFindAccount((int)doc.get("accountNumber"));
			    allAccount.add(aDB);
			}
		} finally {
			cursor.close();
		}
//		if(allAccount.isEmpty())
//			return null;
		return allAccount;
	}
		
	// 6) find all account
	public static List<AccountDB> dataFindAllAccount() {
		MongoCollection<Document> AccountInfo = db.getCollection("AccountInfo");
		MongoCursor<Document> cursor = AccountInfo.find().iterator();
		List<AccountDB> allAccount = new ArrayList<AccountDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
			    AccountDB aDB = dataFindAccount((int)doc.get("accountNumber"));
			    allAccount.add(aDB);
			}
		} finally {
			cursor.close();
		}
//		if(allAccount.isEmpty())
//			return null;
		return allAccount;
	}
	
		
		
	/*-----------------------LoanInfo Collection-----------------------
	 * Collection: loan 
	 * 				 (String) userName, 
	 * 				 (int) loanID,
	 * 				 (int) type,
	 * 				 (double) interestRate, 
	 * 				 (double) amount,
	 * 				 (int) boughtAt
	 * Operation: 1) add loan 
	 * 			  2) find specific customer's loan (by userName)
	 * 			  3) find specific loan (by loanID)
	 * 			  4) delete specific customer's loan
	 * 			  5) find all loans
	 */
		
	// 1) add loan 
	public static boolean dataAddLoan(LoanDB loanDB, AccountDB accountDB) {
		MongoCollection<Document> LoanInfo = db.getCollection("LoanInfo");
		if(dataFindLoan(loanDB.getLoanID(), accountDB.getUserName()) == null){
			LoanInfo.insertOne(new Document("userName", loanDB.getUserName())
				.append("loanID", loanDB.getLoanID())
			    .append("type", loanDB.getType())
			    .append("interestRate", loanDB.getInterest())
			    .append("amount", loanDB.getAmount())
			    .append("boughtAt", loanDB.getBoughtAt())
			);
			Map<String, Double> currency = accountDB.getCurrency();
			double oldAmount = currency.get("USD");
			currency.put("USD", oldAmount + loanDB.getAmount() - 3);
			accountDB.setCurrency(currency);
			dataUpdateAccount(accountDB.getAccountNumber(), accountDB);
			return true;
		}
		return false;
	}

	// 2) find specific customer's loan (by userName)
	public static List<LoanDB> dataFindLoan(String userName) {
		MongoCollection<Document> LoanInfo = db.getCollection("LoanInfo");
		Document findQuery = new Document("userName", userName);
		MongoCursor<Document> cursor = LoanInfo.find(findQuery).iterator();
		List<LoanDB> customerLoanDB = new ArrayList<LoanDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        //LoanDB(int loanID, double interest, double amount, String type, int boughtAt, String userName) 
		        LoanDB loanDB = new LoanDB(
		        		(int)doc.get("loanID"),  
			        	(double)doc.get("interestRate"),  
			        	(double)doc.get("amount"),
			        	(int)doc.get("type"),
			        	(int)doc.get("boughtAt"),
		        		(String)doc.get("userName")
		        		);
		        customerLoanDB.add(loanDB);
			}
		} finally {
			cursor.close();
		}
		if(customerLoanDB.isEmpty())
			return null;
		return customerLoanDB;
	}
	
	// 3) find specific loan (by loanID)
	public static LoanDB dataFindLoan(int loanID, String userName) {
		List<LoanDB> customerLoanDB = dataFindLoan(userName);
		if(customerLoanDB !=  null) {
			for(LoanDB loanDB : customerLoanDB) {
				if(loanDB.getLoanID() == loanID) {
					return loanDB;
				}
			}
		}
		return null;
	}
	
	// 4) update specific loan (by loanID)
	public static boolean dataUpdateLoan(int loanID, String userName, LoanDB loanDB) {
		MongoCollection<Document> LoanInfo = db.getCollection("LoanInfo");
		if(dataFindLoan(loanID, userName) != null) {
			Document updateQuery = new Document("userName", userName).append("loanID", loanID);
			Document updateLoan = new Document("loanID", loanID)
					.append("interestRate", loanDB.getInterest())
				    .append("amount", loanDB.getAmount())
				    .append("type", loanDB.getType())
				    .append("boughtAt", loanDB.getBoughtAt())
				    .append("userName", userName)
					;
			LoanInfo.updateOne(updateQuery, new Document("$set",updateLoan));
			return true;
		}
		return false;
	}
	
	// 5) delete specific loan
	public static boolean dataDeleteLoan(int loanID, AccountDB accountDB) {
		MongoCollection<Document> LoanInfo = db.getCollection("LoanInfo");
		LoanDB loanDB = dataFindLoan(loanID, accountDB.getUserName());
		if(loanDB != null) {
			Document deleteQuery = new Document("userName", accountDB.getUserName())
					.append("loanID", loanID);
			LoanInfo.deleteOne(deleteQuery);

			Map<String, Double> currency = accountDB.getCurrency();
			double oldAmount = currency.get("USD");
			currency.put("USD", oldAmount - loanDB.getAmount());
			accountDB.setCurrency(currency);
			dataUpdateAccount(accountDB.getAccountNumber(), accountDB);
			return true;
		}
		return false;
	}
	
	
	// 5) find all loans
	public static List<LoanDB> dataFindAllLoan() {
		MongoCollection<Document> LoanInfo = db.getCollection("LoanInfo");
		MongoCursor<Document> cursor = LoanInfo.find().iterator();
		List<LoanDB> allLoan = new ArrayList<LoanDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
			    LoanDB lDB = dataFindLoan((int)doc.get("loanID"), (String)doc.get("userName"));
			    allLoan.add(lDB);
			}
		} finally {
			cursor.close();
		}
//		if(allAccount.isEmpty())
//			return null;
		return allLoan;
	}
	
	
	
	
	/*-----------------------SecurityAccountInfo Collection-----------------------
	 * Collection: security account (customer) :
	 * 				 (String) userName, 
	 * 				 (String) accountNumber, 
	 * 				 (HashMap<String, Stock>) stock, 
	 * 				 (HashMap<String, Bonds>) bond, 
	 * 				 (Currency) balance
	 * Operation: 1) add security account information
	 * 			  2) find specific security account information (by userName)
	 * 			  3) find specific security account information (by accountNum)
	 * 			  4) update specific security account (by userName)
	 * 			  5) update specific security account (by accountNum)
	 * 			  6) delete specific security account
	 * 			  7) find all security account information 
	*/
	
	// 1) add security account information
	public static boolean dataAddSecurityAccount(SecurityAccountDB sAccountDB) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(sAccountDB.getUserName()) == null){
			securityAccountInfo.insertOne(new Document("userName", sAccountDB.getUserName())
				.append("accountNumber", sAccountDB.getAccountNumber())
			    .append("customerStockID", sAccountDB.getCustomerStockID())
			    .append("stockInfo", sAccountDB.getStockInfo())
			    .append("stockPrice", sAccountDB.getStockPrice())
			    .append("stockPriceHistory", sAccountDB.getStockPriceHistory())
			    .append("stockNumShares", sAccountDB.getStockNumShares())
			    .append("customerBondID", sAccountDB.getCustomerBondID())
			    .append("bondInfo", sAccountDB.getBondInfo())
			    .append("bondValue", sAccountDB.getBondValue())
			    .append("bondInterest", sAccountDB.getBondInterest())
			    .append("avaliableFunds", sAccountDB.getAvaliableFunds())
			    .append("valueOfSA", sAccountDB.getValueOfSA())
			    .append("transactions", sAccountDB.getTransactions())
			    .append("profitMade", sAccountDB.getProfitMade())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific security account information (by userName)
	public static SecurityAccountDB dataFindSecurityAccount(String userName) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		Document findQuery = new Document("userName", userName);
		MongoCursor<Document> cursor = securityAccountInfo.find(findQuery).iterator();
		SecurityAccountDB sAccountDB = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        sAccountDB = new SecurityAccountDB(
		        		(String)doc.get("userName"),
		        		(String)doc.get("accountNumber"),
		        		(ArrayList<Integer>)doc.get("customerStockID"),
		        		(ArrayList<Map<String, String>>)doc.get("stockInfo"),
		        		(ArrayList<Map<String, Double>>)doc.get("stockPrice"),
		        		(ArrayList<ArrayList<Double>>)doc.get("stockPriceHistory"),
		        		(ArrayList<Integer>)doc.get("stockNumShares"),
		        		(ArrayList<Integer>)doc.get("customerBondID"),
		        		(ArrayList<Map<String, String>>)doc.get("bondInfo"),
		        		(ArrayList<Map<String, Integer>>)doc.get("bondValue"),
		        		(ArrayList<Map<String, Double>>)doc.get("bondAmount"),
		        		(ArrayList<Double>)doc.get("bondInterest"),
		        		(double)doc.get("avaliableFunds"),
		        		(ArrayList<Double>)doc.get("valueOfSA"),
		        		(ArrayList<String>)doc.get("transactions"),
		        		(double)doc.get("profitMade")
		        		);

		        
		    }
		} finally {
			cursor.close();
		}
		return sAccountDB;
	}
	
	// 3) find specific security account information (by accountNum)
	public static SecurityAccountDB dataFindSecurityAccount(int accountNum) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		Document findQuery = new Document("accountNumber", String.valueOf(accountNum));
		MongoCursor<Document> cursor = securityAccountInfo.find(findQuery).iterator();
		SecurityAccountDB sAccountDB = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        sAccountDB = new SecurityAccountDB(
		        		(String)doc.get("userName"),
		        		(String)doc.get("accountNumber"),
		        		(ArrayList<Integer>)doc.get("customerStockID"),
		        		(ArrayList<Map<String, String>>)doc.get("stockInfo"),
		        		(ArrayList<Map<String, Double>>)doc.get("stockPrice"),
		        		(ArrayList<ArrayList<Double>>)doc.get("stockPriceHistory"),
		        		(ArrayList<Integer>)doc.get("stockNumShares"),
		        		(ArrayList<Integer>)doc.get("customerBondID"),
		        		(ArrayList<Map<String, String>>)doc.get("bondInfo"),
		        		(ArrayList<Map<String, Integer>>)doc.get("bondValue"),
		        		(ArrayList<Map<String, Double>>)doc.get("bondAmount"),
		        		(ArrayList<Double>)doc.get("bondInterest"),
		        		(double)doc.get("avaliableFunds"),
		        		(ArrayList<Double>)doc.get("valueOfSA"),
		        		(ArrayList<String>)doc.get("transactions"),
		        		(double)doc.get("profitMade")
		        		);

		        
		    }
		} finally {
			cursor.close();
		}
		return sAccountDB;
	}
	
	// 4) update specific security account (by userName)
	public static boolean dataUpdateSecurityAccount(String userName, SecurityAccountDB newSecurityAccountDB) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(userName) != null) {
			Document updateQuery = new Document("userName", userName);
			Document updateSecurityAccount = new Document("userName", userName)
					.append("accountNumber", newSecurityAccountDB.getAccountNumber())
				    .append("customerStockID", newSecurityAccountDB.getCustomerStockID())
				    .append("stockInfo", newSecurityAccountDB.getStockInfo())
				    .append("stockPrice", newSecurityAccountDB.getStockPrice())
				    .append("stockPriceHistory", newSecurityAccountDB.getStockPriceHistory())
				    .append("stockNumShares", newSecurityAccountDB.getStockNumShares())
				    .append("customerBondID", newSecurityAccountDB.getCustomerBondID())
				    .append("bondInfo", newSecurityAccountDB.getBondInfo())
				    .append("bondValue", newSecurityAccountDB.getBondValue())
				    .append("bondAmount", newSecurityAccountDB.getBondAmount())
				    .append("bondInterest", newSecurityAccountDB.getBondInterest())
				    .append("avaliableFunds", newSecurityAccountDB.getAvaliableFunds())
				    .append("valueOfSA", newSecurityAccountDB.getValueOfSA())
				    .append("transactions", newSecurityAccountDB.getTransactions())
				    .append("profitMade", newSecurityAccountDB.getProfitMade())
					;
			securityAccountInfo.updateOne(updateQuery, new Document("$set",updateSecurityAccount));
			return true;
		}
		return false;
	}

	// 5) update specific security account (by accountNum)
	public static boolean dataUpdateSecurityAccount(int accountNum, SecurityAccountDB newSecurityAccountDB) {
		SecurityAccountDB sAccountDB= dataFindSecurityAccount(accountNum);
		if(sAccountDB != null) {
			String userName = sAccountDB.getUserName();
			dataUpdateSecurityAccount(userName, newSecurityAccountDB);
			return true;
		}
		return false;
	}
	
	// 6) delete specific security account
	public static boolean dataDeleteSecurityAccount(String userName) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(userName) != null) {
			Document deleteQuery = new Document("userName", userName);
			securityAccountInfo.deleteOne(deleteQuery);
			return true;
		}
		return false;
	}
	
	// 7) find all security account information 
	public static List<SecurityAccountDB> dataFindAllSecurityAccount() {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		MongoCursor<Document> cursor = securityAccountInfo.find().iterator();
		List<SecurityAccountDB> allSecurityAccount = new ArrayList<SecurityAccountDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        SecurityAccountDB sAccountDB = dataFindSecurityAccount((String)doc.get("userName"));
		        allSecurityAccount.add(sAccountDB);
		    }
		} finally {
			cursor.close();
		}
		if(allSecurityAccount.isEmpty())
			allSecurityAccount = null;
		return allSecurityAccount;
	}
	
	
	
	/*-----------------------Stocks Collection-----------------------
	 * Collection: price information of all stocks (banker view)
	 * 				 (String) ticker, 
	 * 				 (ArrayList<StockPriceHistory>) priceHistory
	 * Operation: 1) add stock information 
	 * 			  2) find specific stock information
	 * 			  3) update specific stock in Stocks and SecurityAccount.stock
	 * 			  4) find all stock information 
	 * 			  5) update all stocks in Stocks and SecurityAccount.stock 
	*/

	// 1) add stock information 
	public static boolean dataAddStocks(StocksDB stock) {
		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
		if(dataFindStocks(stock.getTicker()) == null){
			stocksInfo.insertOne(new Document("ticker", stock.getTicker())
				.append("companyName", stock.getCompanyName())
				.append("priceHistory", stock.getPriceHistory())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific stock information
	public static StocksDB dataFindStocks(String ticker) {
		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
		Document findQuery = new Document("ticker", ticker);
		MongoCursor<Document> cursor = stocksInfo.find(findQuery).iterator();
		StocksDB stock = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        stock = new StocksDB(
		        		(String)doc.get("ticker"),
		        		(String)doc.get("companyName"),
		        		(ArrayList<Double>)doc.get("priceHistory")
		        		);
		    }
		} finally {
			cursor.close();
		}
		return stock;
	}
	
	// 3) update specific stock in Stocks and SecurityAccount.stock
	public static boolean dataUpdateStocks(StocksDB stock) {
		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
		
		String ticker = stock.getTicker();
		if(dataFindStocks(ticker) != null) {
			Document updateQuery = new Document("ticker", ticker);
			Document updateStocks = new Document("ticker", ticker)
					.append("companyName", stock.getCompanyName())
					.append("priceHistory", stock.getPriceHistory())
					;
			stocksInfo.updateOne(updateQuery, new Document("$set",updateStocks));
			ArrayList<Double> priceHistory = stock.getPriceHistory();
			double pricePerShare = priceHistory.get(priceHistory.size() - 1);
			
//			List<SecurityAccountDB> allSecurityAccount = dataFindAllSecurityAccount();
//			if(allSecurityAccount != null) {
//				// for each security account
//				for(SecurityAccountDB sAccount : allSecurityAccount) {
//					// get security account info
//					String userName = sAccount.getUserName();
////					SecurityAccountDB oldSAccount = dataFindSecurityAccount(userName);
//					
//					// get stock info of the security account
//					ArrayList<customerStock> stockSA = sAccount.getStock();
//					if(stockSA != null) {
//						for (customerStock s : stockSA) {
//							// if the security account contains the target stock (price changed)
//							if(s.getTicker().equals(ticker)) {
//								// update price per share of the target stock
//								//ticker, String stockName, double pPS, int nS, ArrayList<Double> stockPriceHistory, double pBA
//								customerStock newStock = new customerStock(s.getTicker(),
//										s.getStockName(), s.getPriceBoughtAt(), s.getNumShares(),
//										s.getSph(), s.getCustomerStockID());
//								newStock.setPricePerShare(pricePerShare);
//								// update the whole stock info of the security account
//								stockSA.remove(s);
//								stockSA.add(newStock);
//								sAccount.setStock(stockSA);
//								// update the security account info to the database
//								dataUpdateSecurityAccount(userName, sAccount);
//								break;
//							}
//						}
//					}
//				}
//			}
			return true;
		}
		return false;
	}
	
	// 4) find all stock information 
	public static List<StocksDB> dataFindAllStocks() {
		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
		MongoCursor<Document> cursor = stocksInfo.find().iterator();
		List<StocksDB> allStocks = new ArrayList<StocksDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        StocksDB stock = dataFindStocks((String)doc.get("ticker"));
		        allStocks.add(stock);
		    }
		} finally {
			cursor.close();
		}
		if(allStocks.isEmpty())
			return null;
		return allStocks;
	}
	
	// 5) update all stocks in Stocks and SecurityAccount.stock
	public static void dataUpdateAllStocks(List<StocksDB> stock) {
		for(StocksDB s : stock) {
			dataUpdateStocks(s);
		}
	}
	
	
	/*-----------------------Bonds Collection-----------------------
	 * Collection: bonds (basic information, banker view) 
	 * 				 (String) bondID, 
	 * 				 (String) bondType,
	 * 				 (int) maturity, 
	 * 				 (int) amount,
	 * 				 (double) interest
	 * Operation: 1) add bond information 
	 * 			  2) find specific bond information
	 * 			  3) update specific bond 
	 * 			  4) find all bond information 
	*/
	
	// 1) add bond information
	public static boolean dataAddBonds(BondsDB bond) {
		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
		if(dataFindBonds(bond.getBondID()) == null){
			bondsInfo.insertOne(new Document("bondID", bond.getBondID())
			    .append("interest", bond.getInterest())
			    .append("maturity", bond.getMaturity())
			    .append("bondType", bond.getBondType())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific bond information
	public static BondsDB dataFindBonds(String bondID) {
		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
		Document findQuery = new Document("bondID", bondID);
		MongoCursor<Document> cursor = bondsInfo.find(findQuery).iterator();
		BondsDB bond = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        bond = new BondsDB(
		        		(String)doc.get("bondID"),
		        		(double)doc.get("interest"),
		        		(int)doc.get("maturity"),
		        		(String)doc.get("bondType")
		        		);
		    }
		} finally {
			cursor.close();
		}
		return bond;
	}
	
	// 3) update specific bond
	public static boolean dataUpdateBonds(String bondID, BondsDB bond) {
		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
		if(dataFindBonds(bondID) != null) {
			Document updateQuery = new Document("bondID", bondID);
			Document updateBonds = new Document("bondID", bond.getBondID())
					.append("interest", bond.getInterest())
					.append("maturity", bond.getMaturity())
					.append("bondType", bond.getBondType())
					;
			bondsInfo.updateOne(updateQuery, new Document("$set",updateBonds));
			return true;
		}
		return false;
	}
	
	// 4) find all bond information 
	public static List<BondsDB> dataFindAllBonds() {
		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
		MongoCursor<Document> cursor = bondsInfo.find().iterator();
		List<BondsDB> allBonds = new ArrayList<BondsDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        BondsDB bond = dataFindBonds((String)doc.get("bondID"));
		        allBonds.add(bond);
		    }
		} finally {
			cursor.close();
		}
		if(allBonds.isEmpty())
			return null;
		return allBonds;
	}
	
	
	/*-----------------------bankerInfo Collection-----------------------
	 * Collection: banker (basic information)
	 * 				 (String) bankerName,
	 * 				 (String) psw
	 * Operation: 1) add banker information 
	 * 			  2) find specific banker information 
	*/
	
	// 1) add customer information
	public static boolean dataAddBankerBasic(BankerBasicDB bankerBasicDB) {
		MongoCollection<Document> bankerBasicInfo = db.getCollection("bankerBasicInfo");
		if(dataFindBankerBasic(bankerBasicDB.getBankerName()) == null){
			bankerBasicInfo.insertOne(new Document("bankerName", bankerBasicDB.getBankerName())
				.append("psw", bankerBasicDB.getPsw())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific customer information
	public static BankerBasicDB dataFindBankerBasic(String bankerName) {
		MongoCollection<Document> bankerBasicInfo = db.getCollection("bankerBasicInfo");
		Document findQuery = new Document("bankerName", bankerName);
		MongoCursor<Document> cursor = bankerBasicInfo.find(findQuery).iterator();
		BankerBasicDB bankerBasicDB = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        bankerBasicDB = new BankerBasicDB(bankerName,
		        		(String)doc.get("psw")
		        		);
		    }
		} finally {
			cursor.close();
		}
		return bankerBasicDB;
	}
	
	
	
	/*-----------------------BankInfo Collection-----------------------
	 * Collection: bank info
	 * 				 (ArrayList<String>) dailyReport,
	 * 				 (ArrayList<Double>) dailyProfit,
	 * 				 (int) accountNum
	 * Operation: 1) add bank
	 * 			  3) fund bank
	 * 			  2) update bank info
	*/
	
	// 1) add banker 
	public static void dataAddBank(BankDB bankDB) {
		MongoCollection<Document> bankInfo = db.getCollection("bankInfo");
		bankInfo.insertOne(new Document("dailyReport", bankDB.getReport())
			    .append("dailyProfit", bankDB.getProfit())
			    .append("accountNum", bankDB.getAccountNum())
		);
	}
	
	// 2) find bank info
	public static BankDB dataFindBank() {
		MongoCollection<Document> bankInfo = db.getCollection("bankInfo");
		Document bankDoc = bankInfo.find().first();
		BankDB bankDB = new BankDB((ArrayList<String>)bankDoc.get("dailyReport"),
		        		(ArrayList<Double>)bankDoc.get("dailyProfit"),
		        		(int)bankDoc.get("accountNum")
		        		);
		return bankDB;
	}
	// 3) update bank info
	public static void dataUpdateBank(BankDB bankDB) {
		MongoCollection<Document> bankInfo = db.getCollection("bankInfo");
		Document updateQuery = bankInfo.find().first();
		Document updateBank = new Document("dailyReport", bankDB.getReport())
					.append("dailyProfit", bankDB.getProfit())
					.append("accountNum", bankDB.getAccountNum())
					;
		bankInfo.updateOne(updateQuery, new Document("$set",updateBank));
	}
}