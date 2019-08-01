public class Stocks {
    private String ticker;
    private String name;
    private double pricePerShare; //randomized between the setMax and setMin
    private static final double feePerTrade = 5.95; //USD
    private double setMax; //Always 50% higher than the current stock value
    private double setMin; //Always 50% lower than the current stock value

}
