public class customerStock extends Stocks{
    private double priceBoughtAt;
    private int numShares;
    private double unrealizedProfitOrLoss; //positive if a gain, negative if a loss but 0 if neither.

    public customerStock(String ticker, String stockName, double pricePerShare, int numShares){
        super(ticker, stockName, pricePerShare);
        this.priceBoughtAt = pricePerShare;
        this.numShares = numShares;
        this.unrealizedProfitOrLoss = 0;
    }

    public int getNumShares() {
        return numShares;
    }

    public double getPriceBoughtAt() {
        return priceBoughtAt;
    }

    public double getUnrealizedProfitOrLoss(Stocks stocks) {
        double unrealized = stocks.getPricePerShare() - this.getPriceBoughtAt();
        if(unrealized < 0){
            System.out.println("There will be a loss in profit of " + unrealized + " USD.");
        }
        else if(unrealized > 0){
            System.out.println("There will be a gain in profit of " + unrealized + " USD.");
        }
        else{
            System.out.println("There will be no loss or profit from selling at this price");
        }
        return unrealized;
    }
}
