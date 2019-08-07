import java.lang.reflect.Array;
import java.util.ArrayList;

public class customerStock extends Stocks{
    private double priceBoughtAt;
    private int numShares;
    private int customerStockID;

    public customerStock(String ticker, String stockName, double pPS, int nS, ArrayList<Double> stockPriceHistory, int customerStockID){
        super(ticker, stockName, pPS, stockPriceHistory);
        this.priceBoughtAt = stockPriceHistory.get(stockPriceHistory.size()-1);
        this.numShares = nS;
        this.customerStockID = customerStockID;
    }

    public int getNumShares() {
        return numShares;
    }

    public double getPriceBoughtAt() {
        return priceBoughtAt;
    }
    
    public int getCustomerStockID() {
		return customerStockID;
	}

	public void setCustomerStockID(int customerStockID) {
		this.customerStockID = customerStockID;
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
