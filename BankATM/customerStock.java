public class customerStock extends Stocks{
    private double priceBoughtAt;
    private int numShares;

    public customerStock(String ticker, String stockName, double pricePerShare, int numShares){
        super()
        this.priceBoughtAt = pricePerShare;
        this.numShares = numShares;
    }

    public double getPriceBoughtAt() {
        return priceBoughtAt;
    }

    public String getStockName() {
        return stockName;
    }

    public String getTicker() {
        return ticker;
    }

    public int getNumShares() {
        return numShares;
    }
}
