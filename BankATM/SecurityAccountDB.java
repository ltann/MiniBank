import java.util.*;

public class SecurityAccountDB {
	private String userName; 
	private String accountNumber; 

//	private LinkedList<customerStocks> stock; 
	private ArrayList<Map<String, String>> stockInfo;
	private ArrayList<Map<String, Double>> stockPrice;
	private ArrayList<ArrayList<Double>> stockPriceHistory;
	private ArrayList<Integer> stockNumShares;

//	private LinkedList<customerBond> bond;
	private ArrayList<Map<String, String>> bondInfo;
	private ArrayList<Map<String, Integer>> bondValue;
	private ArrayList<Double> bondInterest;
	
	private double avaliableFunds;
	private double valueOfSA;

	public SecurityAccountDB(String userName, String accountNumber, ArrayList<customerStock> stock,
			ArrayList<customerBond> bond, double avaliableFunds, double valueOfSA) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		
		// this.stock = stock;
		setStock(stock);
		
		// this.bond = bond;
		setBond(bond);
			
		this.avaliableFunds = avaliableFunds;
		this.valueOfSA = valueOfSA;
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
						stockPrice.get(i).get("priceBoughtAt")));
			}
		}
		return stock;
	}

	public void setStock(ArrayList<customerStock> stock) {
		stockInfo = new ArrayList<Map<String, String>>();
		stockPrice = new ArrayList<Map<String, Double>>();
		stockPriceHistory = new ArrayList<ArrayList<Double>>();
		stockNumShares = new ArrayList<Integer>();	
		for(customerStock s : stock) {
			Map<String, String> sInfo = new HashMap<String, String>();
			sInfo.put("ticker", s.getTicker());
			sInfo.put("stockName", s.getStockName());
			stockInfo.add(sInfo);
			
			Map<String, Double> sPrice = new HashMap<String, Double>();
			sPrice.put("pricePerShare", s.getPricePerShare());
//			sPrice.put("feePerTrade", s.getFeePerTrade());
			sPrice.put("priceBoughtAt", s.getPriceBoughtAt());
			stockPrice.add(sPrice);
			
			stockPriceHistory.add(s.getSph());
			stockNumShares.add(s.getNumShares());
		}
	}

	public ArrayList<customerBond> getBond() {
		ArrayList<customerBond> bond = null;
		if(!bondInfo.isEmpty()) {
			bond = new ArrayList<customerBond>();
			for(int i = 0; i < bondInfo.size(); i ++) {
				bond.add(new customerBond(bondInfo.get(i).get("bondID"),
						bondInfo.get(i).get("bondType"),
						bondValue.get(i).get("maturity"), 
						bondValue.get(i).get("amount"), 
						bondInterest.get(i),
						bondValue.get(i).get("daysMatured"))
						);
			}
		}	
		return bond;
	}

	public void setBond(ArrayList<customerBond> bond) {
		bondInfo = new ArrayList<Map<String, String>>();
		bondValue = new ArrayList<Map<String, Integer>>();
		bondInterest = new ArrayList<Double>();
		for(customerBond b : bond) {
			Map<String, String> bInfo = new HashMap<String, String>();
			bInfo.put("bondID", b.getBondID());
			bInfo.put("bondType", b.getBondType());
			bondInfo.add(bInfo);
			Map<String, Integer> bValue = new HashMap<String, Integer>();
			bValue.put("maturity", b.getMaturity());
			bValue.put("amount", b.getAmount());
			bValue.put("daysMatured", b.getDaysMatured());
			bondValue.add(bValue);
			bondInterest.add(b.getInterest());
		}
	}

	public double getAvaliableFunds() {
		return avaliableFunds;
	}

	public void setAvaliableFunds(double avaliableFunds) {
		this.avaliableFunds = avaliableFunds;
	}

	public double getValueOfSA() {
		return valueOfSA;
	}

	public void setValueOfSA(double valueOfSA) {
		this.valueOfSA = valueOfSA;
	}

}
