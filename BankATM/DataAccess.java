///*
// * Copyright (c) 2017 ObjectLabs Corporation
// * Distributed under the MIT license - http://opensource.org/licenses/MIT
// *
// * Written with mongo-3.4.2.jar
// * Documentation: http://api.mongodb.org/java/
// * A Java class connecting to a MongoDB database given a MongoDB Connection URI.
// */
//import java.net.UnknownHostException;
//
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.mongodb.ServerAddress;
//
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoCollection;
//
//import org.bson.Document;
//import java.util.Arrays;
//import java.util.HashMap;
//
//import com.mongodb.Block;
//
//import com.mongodb.client.MongoCursor;
//import static com.mongodb.client.model.Filters.*;
//import com.mongodb.client.result.DeleteResult;
//import static com.mongodb.client.model.Updates.*;
//import com.mongodb.client.result.UpdateResult;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class DataAccess {
//	private static MongoClientURI uri;
//	private static MongoClient client;
//	private static MongoDatabase db;
//
//	public DataAccess() {
//		// Initialize connection with database
//        uri  = new MongoClientURI("mongodb://bank:cs591b1@ds157857.mlab.com:57857/bank");
//        client = new MongoClient(uri);
//        db = client.getDatabase(uri.getDatabase());
//	}
//
//	//userInfo
//	//accountInfo
//	//loanInfo
//
//
//	/*-----------------------SecurityAccountInfo Collection-----------------------
//	 * Collection: security account (customer) :
//	 * 				 (String) userName,
//	 * 				 (String) accountNumber,
//	 * 				 (HashMap<String, Stock>) stock,
//	 * 				 (HashMap<String, Bonds>) bond,
//	 * 				 (Currency) balance
//	 * Operation: 1) add security account information
//	 * 			  2) find specific security account information
//	 * 			  3) update specific security account
//	 * 			  4) delete specific security account
//	 * 			  5) find all security account information
//	*/
//
//	// 1) add security account information
//	public static boolean dataAddSecurityAccount(SecurityAccountDB sAccount) {
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//		if(dataFindSecurityAccount(sAccount.getUserName()) == null){
//			securityAccountInfo.insertOne(new Document("userName", sAccount.getUserName())
//				.append("accountNumber", sAccount.getAccountNumber())
//			    .append("stock", sAccount.getStock())
//			    .append("bond", sAccount.getBond())
//			    .append("balance", sAccount.getBalance())
//			);
//			return true;
//		}
//		return false;
//	}
//
//	// 2) find specific security account information
//	public static SecurityAccountDB dataFindSecurityAccount(String userName) {
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//		Document findQuery = new Document("userName", userName);
//		MongoCursor<Document> cursor = securityAccountInfo.find(findQuery).iterator();
//		SecurityAccountDB sAccount = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        sAccount = new SecurityAccountDB(
//		        		(String)doc.get("userName"),
//		        		(String)doc.get("accountNumber"),
//		        		(HashMap<String, Stocks>)doc.get("stock"),
//		        		(HashMap<String, Bonds>)doc.get("bond"),
//		        		(Currency)doc.get("balance")
//		        		);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return sAccount;
//	}
//
//	// 3) update specific security account
//	public static boolean dataUpdateSecurityAccount(String userName, SecurityAccountDB newSecurityAccount) {
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//		if(dataFindSecurityAccount(userName) != null) {
//			Document updateQuery = new Document("userName", userName);
//			Document updateSecurityAccount = new Document("userName", userName)
//					.append("accountNumber", newSecurityAccount.getAccountNumber())
//					.append("stock", newSecurityAccount.getStock())
//					.append("bond", newSecurityAccount.getBond())
//					.append("balance", newSecurityAccount.getBalance())
//					;
//			securityAccountInfo.updateOne(updateQuery, updateSecurityAccount);
//			return true;
//		}
//		return false;
//	}
//
//	// 4) delete specific security account
//	public static boolean dataDeleteSecurityAccount(String userName) {
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//		if(dataFindSecurityAccount(userName) != null) {
//			Document deleteQuery = new Document("userName", userName);
//			securityAccountInfo.deleteOne(deleteQuery);
//			return true;
//		}
//		return false;
//	}
//
//	// 5) find all security account information
//	public static List<SecurityAccountDB> dataFindAllSecurityAccount() {
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//		MongoCursor<Document> cursor = securityAccountInfo.find().iterator();
//		List<SecurityAccountDB> allSecurityAccount = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        SecurityAccountDB sAccount = dataFindSecurityAccount((String)doc.get("userName"));
//		        allSecurityAccount.add(sAccount);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return allSecurityAccount;
//	}
//
//
//
//	/*-----------------------Stocks Collection-----------------------
//	 * Collection: price information of all stocks :
//	 * 				 (String) ticker,
//	 * 				 (ArrayList<StockPriceHistory>) priceHistory
//	 * Operation: 1) add stock information
//	 * 			  2) find specific stock information
//	 * 			  3) update specific stock in Stocks and SecurityAccount.stock
//	 * 			  4) find all stock information
//	 * 			  5) update all stocks in Stocks and SecurityAccount.stock
//	*/
//
//	// 1) add stock information
//	public static boolean dataAddStocks(StocksDB stock) {
//		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
//		if(dataFindBonds(stock.getTicker()) == null){
//			stocksInfo.insertOne(new Document("ticker", stock.getTicker())
//				.append("priceHistory", stock.getPriceHistory())
//			);
//			return true;
//		}
//		return false;
//	}
//
//	// 2) find specific stock information
//	public static StocksDB dataFindStocks(String ticker) {
//		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
//		Document findQuery = new Document("ticker", ticker);
//		MongoCursor<Document> cursor = stocksInfo.find(findQuery).iterator();
//		StocksDB stock = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        stock = new StocksDB(
//		        		(String)doc.get("ticker"),
//		        		(ArrayList<StockPriceHistory>)doc.get("priceHistory")
//		        		);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return stock;
//	}
//
//	// 3) update specific stock in Stocks and SecurityAccount.stock
//	public static boolean dataUpdateStocks(StocksDB stock) {
//		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
//		MongoCollection<Document> securityAccountInfo = db.getCollection("securityAccountInfo");
//
//		String ticker = stock.getTicker();
//		if(dataFindStocks(ticker) != null) {
//			Document updateQuery = new Document("ticker", ticker);
//			Document updateStocks = new Document("ticker", ticker)
//					.append("priceHistory", stock.getPriceHistory())
//					;
//			stocksInfo.updateOne(updateQuery, updateStocks);
//			ArrayList<StockPriceHistory> priceHistory = stock.getPriceHistory();
//			double pricePerShare = priceHistory.get(priceHistory.size() - 1).getPrice();
//
//			List<SecurityAccountDB> allSecurityAccount = dataFindAllSecurityAccount();
//			// for each security account
//			for(SecurityAccountDB sAccount : allSecurityAccount) {
//				// get security account info
//				String userName = sAccount.getUserName();
//				SecurityAccountDB oldSAccount = dataFindSecurityAccount(userName);
//
//				// get stock info of the security account
//				HashMap<String, Stocks> stockSA = sAccount.getStock();
//				for (Map.Entry<String, Stocks> s : stockSA.entrySet()) {
//					// if the security account contains the target stock (price changed)
//					if(s.getKey().equals(ticker)) {
//						// update price per share of the target stock
//						Stocks newStock = s.getValue();
//						newStock.setPricePerShare(pricePerShare);
//						// update the whole stock info of the security account
//						stockSA.put(s.getKey(), newStock);
//						sAccount.setStock(stockSA);
//						// update the security account info to the database
//						dataUpdateSecurityAccount(userName, sAccount);
//						break;
//					}
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//
//	// 4) find all stock information
//	public static List<StocksDB> dataFindAllStocks() {
//		MongoCollection<Document> stocksInfo = db.getCollection("stocksInfo");
//		MongoCursor<Document> cursor = stocksInfo.find().iterator();
//		List<StocksDB> allStocks = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        StocksDB stock = dataFindStocks((String)doc.get("ticker"));
//		        allStocks.add(stock);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return allStocks;
//	}
//
//	// 5) update all stocks in Stocks and SecurityAccount.stock
//	public static void dataUpdateAllStocks(List<StocksDB> stock) {
//		for(StocksDB s : stock) {
//			dataUpdateStocks(s);
//		}
//	}
//
//
//
//	/*-----------------------Bonds Collection-----------------------
//	 * Collection: bonds basic information :
//	 * 				 (String) bondID,
//	 * 				 (String) bondType,
//	 * 				 (int) maturity,
//	 * 				 (int) amount,
//	 * 				 (double) interest
//	 * Operation: 1) add bond information
//	 * 			  2) find specific bond information
//	 * 			  3) update specific bond
//	 * 			  4) find all bond information
//	*/
//
//	// 1) add bond information
//	public static boolean dataAddBonds(BondsDB bond) {
//		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
//		if(dataFindBonds(bond.getBondID()) == null){
//			bondsInfo.insertOne(new Document("bondID", bond.getBondID())
//				.append("amount", bond.getAmount())
//			    .append("interest", bond.getInterest())
//			    .append("maturity", bond.getMaturity())
//			    .append("bondType", bond.getBondType())
//			);
//			return true;
//		}
//		return false;
//	}
//
//	// 2) find specific bond information
//	public static BondsDB dataFindBonds(String bondID) {
//		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
//		Document findQuery = new Document("bondID", bondID);
//		MongoCursor<Document> cursor = bondsInfo.find(findQuery).iterator();
//		BondsDB bond = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        bond = new BondsDB(
//		        		(String)doc.get("bondID"),
//		        		(int)doc.get("amount"),
//		        		(double)doc.get("interest"),
//		        		(int)doc.get("maturity"),
//		        		(String)doc.get("bondType")
//		        		);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return bond;
//	}
//
//	// 3) update specific bond
//	public static boolean dataUpdateBonds(String bondID, BondsDB bond) {
//		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
//		if(dataFindBonds(bondID) != null) {
//			Document updateQuery = new Document("bondID", bondID);
//			Document updateBonds = new Document("bondID", bond.getBondID())
//					.append("amount", bond.getAmount())
//					.append("interest", bond.getInterest())
//					.append("maturity", bond.getMaturity())
//					.append("bondType", bond.getBondType())
//					;
//			bondsInfo.updateOne(updateQuery, updateBonds);
//			return true;
//		}
//		return false;
//	}
//
//	// 4) find all bond information
//	public static List<BondsDB> dataFindAllBonds() {
//		MongoCollection<Document> bondsInfo = db.getCollection("bondsInfo");
//		MongoCursor<Document> cursor = bondsInfo.find().iterator();
//		List<BondsDB> allBonds = null;
//		try {
//			if(cursor.hasNext()) {
//		        Document doc = cursor.next();
//		        BondsDB bond = dataFindBonds((String)doc.get("bondID"));
//		        allBonds.add(bond);
//		    }
//		} finally {
//			cursor.close();
//		}
//		return allBonds;
//	}
//
//}