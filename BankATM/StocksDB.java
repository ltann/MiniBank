import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StocksDB {
	private String ticker;
	private String companyName;
	private ArrayList<Double> priceHistory;
	
	public StocksDB(String ticker, String companyName, ArrayList<Double> priceHistory) {
		this.ticker = ticker;
		this.companyName = companyName;
		this.priceHistory = priceHistory;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public ArrayList<Double> getPriceHistory() {
		return priceHistory;
	}

	public void setPriceHistory(ArrayList<Double> priceHistory) {
		this.priceHistory = priceHistory;
	}
}
