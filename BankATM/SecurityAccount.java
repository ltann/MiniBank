import java.util.*;

public class SecurityAccount extends Account {
    private Currency usd; //USD
    private LinkedList<customerStock> stocks;
    private LinkedList<customerBond> bonds;
    private double availableFunds ;
    private double valueOfSA;

    public SecurityAccount(int type, int accountNumber, int funds){
        super(type, accountNumber);
        this.usd = this.getC()[0];
        this.stocks = new LinkedList<>();
        this.bonds = new LinkedList<>();
        availableFunds = funds;
        valueOfSA = funds;
    }
    public double getAvailableFunds() {
      return availableFunds;
    }

    public void updateValueOfSA(){
        double value = usd.getBalance();
        ListIterator<customerStock> stockListIterator = stocks.listIterator();
        while(stockListIterator.hasNext()){
            customerStock st = stockListIterator.next();
            value += st.getPriceBoughtAt() * st.getNumShares();
        }

        ListIterator<customerBond> bondsListIterator = bonds.listIterator();
        while(bondsListIterator.hasNext()){
            customerBond bd = bondsListIterator.next();

        }

    }

    public double getValueOfSA() {
      return valueOfSA;
    }

    public void purchaseStock(String ticker, String StockName, double priceOfShare, double numOfShares) {
      //change available Balance
      double Sharevalue = priceOfShare*numOfShares;
      double canPurchase = availableFunds -  Sharevalue;
      if(canPurchase < 0) { //not enough funds --> should print error
        
      }else {
        availableFunds  = availableFunds -  Sharevalue;
      //Change hash of Stock
//       Stocks stock = stocks.get(ticker);
      }
    }
    public void sellStock(String ticker, String StockName, double priceOfShare, double numOfShares) {
      //change available Balance and valueOfSA
      double Sharevalue = priceOfShare*numOfShares;
      availableFunds  = availableFunds -  Sharevalue;
    }

    private void containsStocks(){
        ListIterator<customerStock> i = stocks.listIterator();

        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
    

//    public void addBonds(Bonds b){
//        bonds.put(b);
//    }
//
//    public Bonds getBonds() {
//
//    }
//

//    public Stocks getStocks(){//get stocks
//
//        return stock;
//    }

    public Currency getUsd() {
        return usd;
    }

    public double getBalance(){
        return this.usd.getBalance();
    }

    public String portfolio(){
        StringBuilder str = new StringBuilder();
        return str.toString();
    }

    public static void main(String[] args){
        SecurityAccount i = new SecurityAccount(3, 33, 3);
        i.stocks.add(new customerStock("Apple", "a", 5.0,3));
        i.stocks.add(new customerStock("asdf", "a", 5.0,2));
        i.containsStocks();

    }
}
