public class stockPriceHistory {
    private int day = 0;
    private double price;
    
    public stockPriceHistory(double initPrice, int i) {
      day = i;
      price = initPrice;
    }
    public double getPrice() {
      return price;
    }
    public int getDay() {
      return day;
    }
    
}
