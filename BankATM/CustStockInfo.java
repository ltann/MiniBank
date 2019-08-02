public class CustStockInfo extends Stocks {
    private static final double feePerTrade = 5.95; //USD
    private int numberOfShares;
    private double prevValue;
    private double percentChange;

    public CustStockInfo(String ticker, String stockName, double pricePerShare) {
        super(ticker, stockName, pricePerShare);
    }
}
