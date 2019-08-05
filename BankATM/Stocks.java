import java.util.ArrayList;

public class Stocks {
    private ArrayList<Double> sph;
    private String ticker;
    private String stockName;
    private double pricePerShare;
    private static final double feePerTrade = 5.95;

    public Stocks(String ticker, String stockName, double pricePerShare, ArrayList<Double> sph){
        this.ticker = ticker;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
        this.sph = sph;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public static double getFeePerTrade() {
        return feePerTrade;
    }

    public String getStockName() {
        return stockName;
    }

    public ArrayList<Double> getSph() {
        return sph;
    }

	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
    
    
}
