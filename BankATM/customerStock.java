public class customerStock extends Stocks{
    private double priceBoughtAt;
    private int numShares;

    public customerStock(String ticker, String stockName, double pricePerShare, int numShares){
        super(ticker, stockName, pricePerShare);
        this.priceBoughtAt = pricePerShare;
        this.numShares = numShares;
    }

    public int getNumShares() {
        return numShares;
    }

    public double getPriceBoughtAt() {
        return priceBoughtAt;
    }
}
