import java.net.UnknownHostException;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.codecs.*;
import org.bson.codecs.configuration.*;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataAccess {
	private static MongoClientURI uri;
	private static MongoClient client;
	private static MongoDatabase db;
//	private static CodecProvider pojoCodecProvider;
//	private static CodecRegistry pojoCodecRegistry;
	 
	public DataAccess() {
		// Initialize connection with database
        uri  = new MongoClientURI("mongodb://bank:cs591b1@ds157857.mlab.com:57857/bank"); 
        client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());

   
//     pojoCodecProvider = PojoCodecProvider.builder().register(CustomerDB.class, Customer.class, 
//    		 Account.class, Loan.class, PersonInfo.class, Currency.class).build();
//     pojoCodecRegistry = fromRegistries(CodecRegistries.fromCodecs(new StringCodec(), 
//    		 new BsonArrayCodec(), new BsonBooleanCodec()
//    		 ), fromProviders(pojoCodecProvider));
//     MongoClient mongoClient = new MongoClient(new MongoClientURI(uriString));
//     MongoDatabase database = mongoClient.getDatabase("mydb");
//     
//     MongoCollection<DiagnoseDocument> collection = database.getCollection("soar", DiagnoseDocument.class).withCodecRegistry(pojoCodecRegistry);
//     collection.insertOne(diagnoseDocument);
	}
	
	/*-----------------------userInfo Collection/SystemApp.customers(part of it), SystemApp.customerAccount-----------------------
	 * Operation: 1) add customer information :
	 * 				 (String) userName, 
	 * 				 (String) name, 
	 * 				 (String) address,
	 * 				 (String) cell,  
	 * 				 (String) password,
	 * 				 (boolean) collateral
	 * 			  2) find specific customer information :
	 * 				 relative field : public static ArrayList<Customer> customers --in SystemApp.java
	 * 			  3) update specific customer information
	 * 			  4) find all customers information
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
		        		(boolean)doc.get("collateral")
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
					;
			customerBasicInfo.updateOne(updateQuery, new Document("$set",updateCustomerBasic));
			return true;
		}
		return false;
	}

	// 4) find all specific customer information
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
	 * Collection: basic account (checking, saving) :
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
	 * 			  7) find the number of all accounts
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
//			if(allAccount.isEmpty())
//				return null;
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
//			if(allAccount.isEmpty())
//				return null;
			return allAccount;
		}
		
		// 7) find the number of all accounts
		public static int dataFindAccountSize() {
			if(dataFindAllAccount() != null)
				return dataFindAllAccount().size();
			return 0;
		}
	
		/*-----------------------LoanInfo Collection-----------------------
		 * Collection: loan :
		 * 				 (String) userName, 
		 * 				 (int) loanID,
		 * 				 (double) interestRate, 
		 * 				 (Map<String, Double>) currency
		 * Operation: 1) add loan 
		 * 			  2) find specific customer's loan
		 * 			  3) update specific customer's loan
		 * 			  4) delete specific customer's loan
		 * 			  5) find all loans
		*/
		
	
	
	
	/*-----------------------SecurityAccountInfo Collection-----------------------
	 * Collection: security account (customer) :
	 * 				 (String) userName, 
	 * 				 (String) accountNumber, 
	 * 				 (HashMap<String, Stock>) stock, 
	 * 				 (HashMap<String, Bonds>) bond, 
	 * 				 (Currency) balance
	 * Operation: 1) add security account information
	 * 			  2) find specific security account information
	 * 			  3) update specific security account 
	 * 			  4) delete specific security account
	 * 			  5) find all security account information 
	*/
	
	// 1) add security account information
	public static boolean dataAddSecurityAccount(SecurityAccountDB sAccount) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(sAccount.getUserName()) == null){
			securityAccountInfo.insertOne(new Document("userName", sAccount.getUserName())
				.append("accountNumber", sAccount.getAccountNumber())
			    .append("stockInfo", sAccount.getStockInfo())
			    .append("stockPrice", sAccount.getStockPrice())
			    .append("stockPriceHistory", sAccount.getStockPriceHistory())
			    .append("stockNumShares", sAccount.getStockNumShares())
			    .append("bondInfo", sAccount.getBondInfo())
			    .append("bondValue", sAccount.getBondValue())
			    .append("bondInterest", sAccount.getBondInterest())
			    .append("avaliableFunds", sAccount.getAvaliableFunds())
			    .append("valueOfSA", sAccount.getValueOfSA())
			    .append("transactions", sAccount.getTransactions())
			    .append("profitMade", sAccount.getProfitMade())
			);
			return true;
		}
		return false;
	}
	
	// 2) find specific security account information
	public static SecurityAccountDB dataFindSecurityAccount(String userName) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		Document findQuery = new Document("userName", userName);
		MongoCursor<Document> cursor = securityAccountInfo.find(findQuery).iterator();
		SecurityAccountDB sAccount = null;
		try {
			if(cursor.hasNext()) {
		        Document doc = cursor.next();
		        sAccount = new SecurityAccountDB(
		        		(String)doc.get("userName"),
		        		(String)doc.get("accountNumber"),
		        		(ArrayList<Map<String, String>>)doc.get("stockInfo"),
		        		(ArrayList<Map<String, Double>>)doc.get("stockPrice"),
		        		(ArrayList<ArrayList<Double>>)doc.get("stockPriceHistory"),
		        		(ArrayList<Integer>)doc.get("stockNumShares"),
		        		(ArrayList<Map<String, String>>)doc.get("bondInfo"),
		        		(ArrayList<Map<String, Integer>>)doc.get("bondValue"),
		        		(ArrayList<Map<String, Double>>)doc.get("bondAmount"),
		        		(ArrayList<Double>)doc.get("bondInterest"),
		        		(double)doc.get("avaliableFunds"),
		        		(double)doc.get("valueOfSA"),
		        		(ArrayList<String>)doc.get("transactions"),
		        		(double)doc.get("profitMade")
		        		);

		        
		    }
		} finally {
			cursor.close();
		}
		return sAccount;
	}
	
	// 3) update specific security account
	public static boolean dataUpdateSecurityAccount(String userName, SecurityAccountDB newSecurityAccount) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(userName) != null) {
			Document updateQuery = new Document("userName", userName);
			Document updateSecurityAccount = new Document("userName", userName)
					.append("accountNumber", newSecurityAccount.getAccountNumber())
				    .append("stockInfo", newSecurityAccount.getStockInfo())
				    .append("stockPrice", newSecurityAccount.getStockPrice())
				    .append("stockPriceHistory", newSecurityAccount.getStockPriceHistory())
				    .append("stockNumShares", newSecurityAccount.getStockNumShares())
				    .append("bondInfo", newSecurityAccount.getBondInfo())
				    .append("bondValue", newSecurityAccount.getBondValue())
				    .append("bondDouble", newSecurityAccount.getBondAmount())
				    .append("bondInterest", newSecurityAccount.getBondInterest())
				    .append("avaliableFunds", newSecurityAccount.getAvaliableFunds())
				    .append("valueOfSA", newSecurityAccount.getValueOfSA())
				    .append("transactions", newSecurityAccount.getTransactions())
				    .append("profitMade", newSecurityAccount.getProfitMade())
					;
			securityAccountInfo.updateOne(updateQuery, new Document("$set",updateSecurityAccount));
			return true;
		}
		return false;
	}
	
	// 4) delete specific security account
	public static boolean dataDeleteSecurityAccount(String userName) {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		if(dataFindSecurityAccount(userName) != null) {
			Document deleteQuery = new Document("userName", userName);
			securityAccountInfo.deleteOne(deleteQuery);
			return true;
		}
		return false;
	}
	
	// 5) find all security account information 
	public static List<SecurityAccountDB> dataFindAllSecurityAccount() {
		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
		MongoCursor<Document> cursor = securityAccountInfo.find().iterator();
		List<SecurityAccountDB> allSecurityAccount = new ArrayList<SecurityAccountDB>();
		try {
			while(cursor.hasNext()) {
		        Document doc = cursor.next();
		        SecurityAccountDB sAccount = dataFindSecurityAccount((String)doc.get("userName"));
		        allSecurityAccount.add(sAccount);
		    }
		} finally {
			cursor.close();
		}
		if(allSecurityAccount.isEmpty())
			allSecurityAccount = null;
		return allSecurityAccount;
	}
	
	
	
	/*-----------------------Stocks Collection-----------------------
	 * Collection: price information of all stocks :
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
			
			List<SecurityAccountDB> allSecurityAccount = dataFindAllSecurityAccount();
			if(allSecurityAccount != null) {
				// for each security account
				for(SecurityAccountDB sAccount : allSecurityAccount) {
					// get security account info
					String userName = sAccount.getUserName();
//					SecurityAccountDB oldSAccount = dataFindSecurityAccount(userName);
					
					// get stock info of the security account
					ArrayList<customerStock> stockSA = sAccount.getStock();
					for (customerStock s : stockSA) {
						// if the security account contains the target stock (price changed)
						if(s.getTicker().equals(ticker)) {
							// update price per share of the target stock
							//ticker, String stockName, double pPS, int nS, ArrayList<Double> stockPriceHistory, double pBA
							customerStock newStock = new customerStock(s.getTicker(),
									s.getStockName(), s.getPriceBoughtAt(), s.getNumShares(),
									s.getSph(), s.getPriceBoughtAt());
							newStock.setPricePerShare(pricePerShare);
							// update the whole stock info of the security account
							stockSA.remove(s);
							stockSA.add(newStock);
							sAccount.setStock(stockSA);
							// update the security account info to the database
							dataUpdateSecurityAccount(userName, sAccount);
							break;
						}
					}
				}
			}
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
	 * Collection: bonds basic information :
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
	
}