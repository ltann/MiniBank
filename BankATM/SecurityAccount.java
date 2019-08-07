import java.util.*;

public class SecurityAccount extends Account {
    private ArrayList<customerStock> stocks;
    private ArrayList<customerBond> bonds;
    private double availableFunds;
    private double valueOfSA;
    private ArrayList<String> Transactions;
    private double profitMade; //from both bonds and stocks

    public SecurityAccount(int type, int accountNumber, int funds) {
        super(type, accountNumber);
        this.stocks = new ArrayList<>();
        this.bonds = new ArrayList<>();
        availableFunds = funds;
        valueOfSA = funds;
    }
    public SecurityAccount(int type, int accountNumber, ArrayList<customerStock> stocks, ArrayList<customerBond> bonds, double funds) {
        super(type, accountNumber);
        this.stocks = stocks;
        this.bonds = bonds;
        availableFunds = funds;
        valueOfSA = funds;
    }

//    public boolean purchaseStock(Stocks stock, int numOfShares) {
//        boolean purchasable = true;
//        double shareValue = stock.getPricePerShare() * numOfShares;
//        if (shareValue > availableFunds) {
//            System.out.println("You're available funds are: " + availableFunds + " you cannot purchase this stock share value at: " + shareValue);
//            purchasable = false;
//        } else {
//            availableFunds = availableFunds - shareValue;
//            stocks.add(new customerStock(stock.getTicker(), stock.getStockName(), stock.getPricePerShare(), numOfShares, stock.getSph()));
//            //UPDATE DAILY? TRANSACTIONS
//            Transactions.add("Purchased " + numOfShares + " of " + stock.getStockName() + " Share(s) at " + stock.getPricePerShare());
//            profitMade -= shareValue;
//        }
//        return purchasable;
//    }
//
//    public boolean sellStock(customerStock customerStock, double currentStockPrice, int numOfShares) {
//        //change available Balance and valueOfSA
//        boolean sellable = true;
//        if(customerStock.getNumShares() < numOfShares){
//            sellable = false;
//            System.out.println("You only have " + customerStock.getNumShares() + " shares, so you can't sell " + numOfShares + " of those...");
//        }
//        else{
//            double shareValue = currentStockPrice * numOfShares;
//            double profits = shareValue - (customerStock.getNumShares() * customerStock.getPriceBoughtAt());
//            availableFunds += shareValue;
//            valueOfSA += profits;
//            stocks.remove(customerStock);
//            Transactions.add("Sold " + numOfShares + " of " + customerStock.getStockName() + " Share(s) at " + currentStockPrice);
//            profitMade += shareValue;
//        }
//        return sellable;
//    }
//
//    public boolean purchaseBond(Bonds b, double amount){
//        boolean purchasable = true;
//        if(amount > availableFunds){
//            purchasable = false;
//            System.out.println("You can't buy this bond at price " + amount + ". You only have " + availableFunds + " USD left in security acount");
//        }
//        else{
//            availableFunds -= amount;
//            //valueOfSA += amount;
//            bonds.add(new customerBond(b.getBondID(),b.getBondType(),b.getMaturity(),amount,b.getInterest(),0));
//            //profitMade -= amount;
//        }
//        return purchasable;
//    }
//
//    public boolean sellBond(customerBond b){//fixx
//        boolean sellable = true;
//        if(b.isMatured()){
//            availableFunds += b.getAmount() + b.getInterest();
//            valueOfSA += b.getInterest();
//            profitMade += b.getInterest();
//        }else{
//            availableFunds += b.getAmount();
//            //profitMade += b.getAmount();
//        }
//        return sellable;
//    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public double getValueOfSA() {
        return valueOfSA;
    }

    public ArrayList<customerStock> getStocks(){//get all Stocks
        return this.stocks;
    }

    public ArrayList<customerBond> getBonds() {
        return bonds;
    }

    public ArrayList<String> getTransactions() {
        return Transactions;
    }

    public double getProfitMade() {
        return profitMade;
    }
}
