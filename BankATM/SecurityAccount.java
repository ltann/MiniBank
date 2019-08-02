import java.util.Date;
import java.util.HashMap;

public class SecurityAccount extends Account {
    private Currency usd; //in USD, this is the available balance
    private double valueofSA;



    public SecurityAccount(int type, int accountNumber){
        super(type, accountNumber);
        this.usd = this.getC()[0];
        this.valueofSA = usd.getBalance();
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
