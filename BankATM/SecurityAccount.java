import java.util.*;

public class SecurityAccount extends Account {
    private LinkedList<customerStock> stocks;
    private LinkedList<customerBond> bonds;
    private double availableFunds;
    private double valueOfSA;
    private LinkedList<String> Transactions;
    private double profitMade;

    public SecurityAccount(int type, int accountNumber, int funds) {
        super(type, accountNumber);
        super.c = this.getC();
        this.stocks = new LinkedList<customerStock>();
        this.bonds = new LinkedList<customerBond>();
        availableFunds = funds;
        valueOfSA = funds;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public double getValueOfSA() {
        return valueOfSA;
    }

    public boolean purchaseStock(Stocks stock, int numOfShares) {
        boolean purchasable = true;
        double shareValue = stock.getPricePerShare() * numOfShares;
        if (shareValue > availableFunds) {
            System.out.println("You're available funds are: " + availableFunds + " you cannot purchase this stock share value at: " + shareValue);
            purchasable = false;
        } else {
            availableFunds = availableFunds - shareValue;
            valueOfSA = availableFunds;
            stocks.add(new customerStock(stock.getTicker(), stock.getStockName(), stock.getPricePerShare(), numOfShares));
            //UPDATE DAILY? TRANSACTIONS
            Transactions.add("Purchased " + numOfShares + " of " + stock.getStockName() + " Share(s) at " + stock.getPricePerShare());
            profitMade -= shareValue;
        }
        return purchasable;
    }

    public boolean sellStock(customerStock customerStock, double currentStockPrice, int numOfShares) {
        //change available Balance and valueOfSA
        boolean sellable = true;
        if(customerStock.getNumShares() < numOfShares){
            sellable = false;
            System.out.println("You only have " + customerStock.getNumShares() + " shares, so you can't sell " + numOfShares + " of those...");
        }
        else{
            double shareValue = currentStockPrice * numOfShares;
            double profits = shareValue - (customerStock.getNumShares() * customerStock.getPriceBoughtAt());
            availableFunds += shareValue;
            valueOfSA += profits;
            stocks.remove(customerStock);
            Transactions.add("Sold " + numOfShares + " of " + customerStock.getStockName() + " Share(s) at " + currentStockPrice);
            profitMade += shareValue;
        }
        return sellable;
    }

//    public void addBonds(Bonds b){
//        bonds.put(b);
//    }
//    public Bonds getBonds() {
//    }

    public LinkedList<customerStock> getStocks(){//get all Stocks
        return this.stocks;
    }

    public LinkedList<customerBond> getBonds() {
        return bonds;
    }

    public double getBalance() {
        return super.c[0].getBalance();
    }

    public LinkedList<String> getTransactions() {
        return Transactions;
    }

    public double getProfitMade() {
        return profitMade;
    }
}
