import java.util.Date;
import java.util.HashMap;

public class SecurityAccount extends Account {
    private Currency usd; //USD
    private HashMap<Date,Stocks> stocks;
    private HashMap<Date, Bonds> bonds;


    public SecurityAccount(int type, int accountNumber){
        super(type, accountNumber);
        this.usd = this.getC()[0];
        this.stocks = new HashMap<>();
        this.bonds = new HashMap<>();
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
