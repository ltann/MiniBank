import java.util.*;

public class SecurityAccount extends Account {
    private Currency usd; //USD
    private LinkedList<Stocks> stocks;
    private HashMap<, Bonds> bonds;
    private double availableFunds ;
    private double valueOfSA;
    private double valueofSA;

    public SecurityAccount(int type, int accountNumber, int funds){
        super(type, accountNumber);
        this.usd = this.getC()[0];
        this.stocks = new LinkedList<>();
        this.bonds = new HashMap<>();
        availableFunds = funds;
        valueOfSA = funds;
        this.valueofSA = usd.getBalance();
    }
    public double getAvailableFunds() {
      return availableFunds;
    }
    public double getSaValuve() {
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
        ListIterator<Stocks> i = stocks.listIterator();
        stocks.add(new Stocks("Apple", "a", 5.0));
        stocks.add(new Stocks("asdf", "a", 5.0));
        while(i.hasNext()){
            i.next();
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

    public Stocks getStocks(){//get stocks

//        return stock;
    }

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
        i.containsStocks();
    }
}
