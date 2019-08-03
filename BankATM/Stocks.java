public class Stocks {
    private stockPriceHistory sph;
    private String ticker;
    private String stockName;
    private double pricePerShare;
    private static final double feePerTrade = 5.95;

    public Stocks(String ticker, String stockName, double pricePerShare){ // used for when called by customerStocks!
        this.ticker = ticker;
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
    }

    public Stocks(String ticker, String stockName, double pricePerShare, stockPriceHistory sph){
        this(ticker, stockName, pricePerShare);
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

    public stockPriceHistory getSph() {
        return sph;
    }
}
