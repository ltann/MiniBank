import java.util.*;

public class SecurityAccountDB {
	private String userName; 
	private String accountNumber; 

//	private LinkedList<customerStocks> stock; 
	private ArrayList<Integer> customerStockID;
	private ArrayList<Map<String, String>> stockInfo;
	private ArrayList<Map<String, Double>> stockPrice;
	private ArrayList<ArrayList<Double>> stockPriceHistory;
	private ArrayList<Integer> stockNumShares;

//	private LinkedList<customerBond> bond;
	private ArrayList<Integer> customerBondID;
	private ArrayList<Map<String, String>> bondInfo;
	private ArrayList<Map<String, Integer>> bondValue;
	private ArrayList<Map<String, Double>> bondAmount;
	private ArrayList<Double> bondInterest;
	
	private double avaliableFunds;
	private ArrayList<Double> valueOfSA;
	
	private ArrayList<String> transactions;
	private double profitMade;

	public SecurityAccountDB(String userName, String accountNumber, ArrayList<customerStock> stock,
			ArrayList<customerBond> bond, double avaliableFunds, ArrayList<Double> valueOfSA, 
			ArrayList<String> transactions, double profitMade) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		
		// this.stock = stock;
		setStock(stock);
		
		// this.bond = bond;
		setBond(bond);
			
		this.avaliableFunds = avaliableFunds;
		this.valueOfSA = valueOfSA;
		this.transactions = transactions;
		this.profitMade = profitMade;
	}

	
	
	
	public SecurityAccountDB(String userName, String accountNumber, 
			ArrayList<Integer> customerStockID, 
			ArrayList<Map<String, String>> stockInfo,
			ArrayList<Map<String, Double>> stockPrice, 
			ArrayList<ArrayList<Double>> stockPriceHistory,
			ArrayList<Integer> stockNumShares, 
			ArrayList<Integer> customerBondID, 
			ArrayList<Map<String, String>> bondInfo,
			ArrayList<Map<String, Integer>> bondValue, 
			ArrayList<Map<String, Double>> bondAmount, 
			ArrayList<Double> bondInterest, 
			double avaliableFunds,
			ArrayList<Double> valueOfSA, 
			ArrayList<String> transactions, 
			double profitMade) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.customerStockID = customerStockID;
		this.stockInfo = stockInfo;
		this.stockPrice = stockPrice;
		this.stockPriceHistory = stockPriceHistory;
		this.stockNumShares = stockNumShares;
		this.customerBondID = customerBondID;
		this.bondInfo = bondInfo;
		this.bondValue = bondValue;
		this.bondAmount = bondAmount;
		this.bondInterest = bondInterest;
		this.avaliableFunds = avaliableFunds;
		this.valueOfSA = valueOfSA;
		this.transactions = transactions;
		this.profitMade = profitMade;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public ArrayList<customerStock> getStock() {
		ArrayList<customerStock> stock = null;
		if(!stockInfo.isEmpty()) {
			stock = new ArrayList<customerStock>();
			for(int i = 0; i < stockInfo.size(); i ++) {
				stock.add(new customerStock(stockInfo.get(i).get("ticker"), 
						stockInfo.get(i).get("stockName"), 
						stockPrice.get(i).get("pricePerShare"), 
						stockNumShares.get(i), 
						stockPriceHistory.get(i),
						customerStockID.get(i)
						));
			}
		}
		return stock;
	}

	public void setStock(ArrayList<customerStock> stock) {
		customerStockID = new ArrayList<Integer>();
		stockInfo = new ArrayList<Map<String, String>>();
		stockPrice = new ArrayList<Map<String, Double>>();
		stockPriceHistory = new ArrayList<ArrayList<Double>>();
		stockNumShares = new ArrayList<Integer>();	
		for(customerStock s : stock) {
			customerStockID.add(s.getCustomerStockID());
			Map<String, String> sInfo = new HashMap<String, String>();
			sInfo.put("ticker", s.getTicker());
			sInfo.put("stockName", s.getStockName());
			stockInfo.add(sInfo);
			
			Map<String, Double> sPrice = new HashMap<String, Double>();
			sPrice.put("pricePerShare", s.getPricePerShare());
//			sPrice.put("feePerTrade", s.getFeePerTrade());
			stockPrice.add(sPrice);
			
			stockPriceHistory.add(s.getSph());
			stockNumShares.add(s.getNumShares());
		}
	}

	public ArrayList<customerBond> getBond() {
		ArrayList<customerBond> bond = null;
		System.out.println("bondInfo size: " + bondInfo.size());
		if(!bondInfo.isEmpty()) {
			System.out.println("get bond");
			bond = new ArrayList<customerBond>();
			for(int i = 0; i < bondInfo.size(); i ++) {
				bond.add(new customerBond(bondInfo.get(i).get("bondID"),
						bondInfo.get(i).get("bondType"),
						bondValue.get(i).get("maturity"),
						bondAmount.get(i).get("amount"), 
						bondInterest.get(i),
						bondValue.get(i).get("daysMatured"),
						customerBondID.get(i))
						);

				System.out.println("bond add ");
			}

			System.out.println("bond size: " + bond.size());
		}	
		return bond;
	}

	public void setBond(ArrayList<customerBond> bond) {
		customerBondID = new ArrayList<Integer>();
		bondInfo = new ArrayList<Map<String, String>>();
		bondValue = new ArrayList<Map<String, Integer>>();
		bondAmount = new ArrayList<Map<String, Double>>();
		bondInterest = new ArrayList<Double>();
		for(customerBond b : bond) {
			Map<String, String> bInfo = new HashMap<String, String>();
			bInfo.put("bondID", b.getBondID());
			bInfo.put("bondType", b.getBondType());
			customerBondID.add(b.getCustomerBondID());
			bondInfo.add(bInfo);
			Map<String, Integer> bValue = new HashMap<String, Integer>();
			Map<String, Double> bDouble = new HashMap<String, Double>();
//			bValue.put("customerBondID", b.getCustomerBondID());
			bDouble.put("amount", b.getAmount());
			bValue.put("daysMatured", b.getDaysMatured());
			bValue.put("maturity", b.getMaturity());
			bondValue.add(bValue);
			bondAmount.add(bDouble);
			bondInterest.add(b.getInterest());
		}
	}

	public double getAvaliableFunds() {
		return avaliableFunds;
	}

	public void setAvaliableFunds(double avaliableFunds) {
		this.avaliableFunds = avaliableFunds;
	}

	public ArrayList<Double> getValueOfSA() {
		return valueOfSA;
	}

	public void setValueOfSA(ArrayList<Double> valueOfSA) {
		this.valueOfSA = valueOfSA;
	}

	public ArrayList<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<String> transactions) {
		this.transactions = transactions;
	}

	public double getProfitMade() {
		return profitMade;
	}

	public void setProfitMade(double profitMade) {
		this.profitMade = profitMade;
	}

	public ArrayList<Map<String, String>> getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(ArrayList<Map<String, String>> stockInfo) {
		this.stockInfo = stockInfo;
	}

	public ArrayList<Map<String, Double>> getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(ArrayList<Map<String, Double>> stockPrice) {
		this.stockPrice = stockPrice;
	}

	public ArrayList<ArrayList<Double>> getStockPriceHistory() {
		return stockPriceHistory;
	}

	public void setStockPriceHistory(ArrayList<ArrayList<Double>> stockPriceHistory) {
		this.stockPriceHistory = stockPriceHistory;
	}

	public ArrayList<Integer> getStockNumShares() {
		return stockNumShares;
	}

	public void setStockNumShares(ArrayList<Integer> stockNumShares) {
		this.stockNumShares = stockNumShares;
	}

	public ArrayList<Map<String, String>> getBondInfo() {
		return bondInfo;
	}

	public void setBondInfo(ArrayList<Map<String, String>> bondInfo) {
		this.bondInfo = bondInfo;
	}

	public ArrayList<Map<String, Integer>> getBondValue() {
		return bondValue;
	}

	public void setBondValue(ArrayList<Map<String, Integer>> bondValue) {
		this.bondValue = bondValue;
	}

	public ArrayList<Double> getBondInterest() {
		return bondInterest;
	}

	public void setBondInterest(ArrayList<Double> bondInterest) {
		this.bondInterest = bondInterest;
	}
	
	public ArrayList<Map<String, Double>> getBondAmount() {
		return bondAmount;
	}
	public void setBondAmount(ArrayList<Map<String, Double>> bondAmount) {
		this.bondAmount = bondAmount;
	}
	
	public ArrayList<Integer> getCustomerBondID() {
		return customerBondID;
	}
	
	public void setCustomerBondID(ArrayList<Integer> customerBondID) {
		this.customerBondID = customerBondID;
	}

	public ArrayList<Integer> getCustomerStockID() {
		return customerStockID;
	}
	
	public void setCustomerStockID(ArrayList<Integer> customerStockID) {
		this.customerStockID = customerStockID;
	}
	
}
