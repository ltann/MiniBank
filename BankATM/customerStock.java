import java.lang.reflect.Array;
import java.util.ArrayList;

public class customerStock extends Stocks{
    private double priceBoughtAt;
    private int numShares;

    public customerStock(String ticker, String stockName, double pPS, int nS, ArrayList<Double> stockPriceHistory){
        super(ticker, stockName, pPS, stockPriceHistory);
        this.priceBoughtAt = pPS;
        this.numShares = numShares;
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
