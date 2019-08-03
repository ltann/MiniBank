import java.util.Date;
import java.util.HashMap;

public class SecurityAccount extends Account {
<<<<<<< HEAD
    private Currency usd; //USD
    private HashMap<Date,Stocks> stocks;
    private HashMap<Date, Bonds> bonds;
    private double availableFunds ;
    private double valueOfSA;
=======
    private Currency usd; //in USD, this is the available balance
    private double valueofSA;

>>>>>>> caaf710948d095f9b1420b1b7298a6ef57cf8a4f


    public SecurityAccount(int type, int accountNumber, int funds){
        super(type, accountNumber);
        this.usd = this.getC()[0];
<<<<<<< HEAD
        this.stocks = new HashMap<>();
        this.bonds = new HashMap<>();
        availableFunds = funds;
        valueOfSA = funds;
=======
        this.valueofSA = usd.getBalance();
>>>>>>> caaf710948d095f9b1420b1b7298a6ef57cf8a4f
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
       Stocks stock = stocks.get(ticker);
      }
    }
    public void sellStock(String ticker, String StockName, double priceOfShare, double numOfShares) {
      //change available Balance and valueOfSA
      double Sharevalue = priceOfShare*numOfShares;
      availableFunds  = availableFunds -  Sharevalue;
      
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
}
