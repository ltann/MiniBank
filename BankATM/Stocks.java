import java.util.List;

public class Stocks {
    private stockPriceHistory priceHistory;
    private String ticker;
    private String stockName;
    private double pricePerShare; //randomized between the setMax and setMin
    private double setMax; //Always 50% higher than the current stock value
    private double setMin; //Always 50% lower than the current stock value

    public Stocks(String ticker, String stockName, double pricePerShare){
        this.ticker = ticker;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public String getStockName() {
        return stockName;
    }

    public String getTicker() {
        return ticker;
    }
}
