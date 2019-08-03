package bank;

import java.util.ArrayList;

public class StocksDB {
	private String ticker;
	private ArrayList<StockPriceHistory> priceHistory;
	
	public StocksDB(String ticker, ArrayList<StockPriceHistory> priceHistory) {
		this.ticker = ticker;
		this.priceHistory = priceHistory;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public ArrayList<StockPriceHistory> getPriceHistory() {
		return priceHistory;
	}

	public void setPriceHistory(ArrayList<StockPriceHistory> priceHistory) {
		this.priceHistory = priceHistory;
	}

	
}
