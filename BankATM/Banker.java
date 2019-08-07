import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Banker extends Person {
    public Boolean showAllCustomer;
    public Boolean getReport;
    private double SandBprofits; //profits from Stocks and Bonds

    public Banker(String loginName, String password) {
        super(loginName, password, AccessRight.BANKER);
        this.showAllCustomer = false;
        this.getReport = false;
        SandBprofits = 0;
        
    }

    public Banker() {
        this.showAllCustomer = false;
        this.getReport = false;
    }

    // public String showAllCustomer(customer[] c) {
    //     // 1. name, 2. cellphone 3. address 4. account type 5. balance 
    //    String CustomerInfo;
    //     for (int i = 0; i < c.size(); i++) {
    //         String info = c.getInfo();
    //     }
    // }
    
    
  //adds 10 inital stocks when the program is first started
    public void createInitialStocks() { // we need to add company name to the database
      if(SystemApp.database.dataFindAllStocks() == null) {
        //Apple Stock 1
        ArrayList<Double> ApplePriceHistoryList =  new ArrayList<Double>();
        Double ApplePrice = 204.50;
        ApplePriceHistoryList.add(ApplePrice);
        StocksDB AppleStock = new StocksDB("AAPL", "Apple", ApplePriceHistoryList);
        SystemApp.database.dataAddStocks(AppleStock);
        
        //Microsoft Stock 2
        ArrayList<Double> MicroPriceHistoryList =  new ArrayList<Double> ();
        Double MicroPrice = 136.95;
        MicroPriceHistoryList.add(MicroPrice);
        StocksDB MicroStock = new StocksDB("MSFT", "MicroSoft", MicroPriceHistoryList);
        SystemApp.database.dataAddStocks(MicroStock);
        
        //Tesla Stock 3
        ArrayList<Double> TeslaPriceHistoryList =  new ArrayList<Double> ();
        Double TeslaPrice = 235.69;
        TeslaPriceHistoryList.add(TeslaPrice);
        StocksDB TeslaStock = new StocksDB("TSLA","Tesla", TeslaPriceHistoryList);
        SystemApp.database.dataAddStocks(TeslaStock);
        
        //General Electric 4
        ArrayList<Double> GEPriceHistoryList =  new ArrayList<Double> ();
        Double GEPrice = 9.99;
        GEPriceHistoryList.add(GEPrice);
        StocksDB GEStock = new StocksDB("GE","GE", GEPriceHistoryList);
        SystemApp.database.dataAddStocks(GEStock);
        
       //Bank Of America 5
        ArrayList<Double> BACPriceHistoryList =  new ArrayList<Double> ();
        Double BACPrice = 29.43;
        BACPriceHistoryList.add(BACPrice);
        StocksDB BACStock = new StocksDB("BAC","Bank of America", BACPriceHistoryList);
        SystemApp.database.dataAddStocks(BACStock);
        
        //Disney 6
        ArrayList<Double> DISPriceHistoryList =  new ArrayList<Double> ();
        Double DISPrice = 141.82;
        DISPriceHistoryList.add(DISPrice);
        StocksDB DISStock = new StocksDB("DIS","Disney", DISPriceHistoryList);
        SystemApp.database.dataAddStocks(DISStock);
        
      //Amazon 7
        ArrayList<Double> AMZNPriceHistoryList =  new ArrayList<Double> ();
        Double AMZNPrice = 1824.29;
        AMZNPriceHistoryList.add(AMZNPrice);
        StocksDB AMZNStock = new StocksDB("AMZN", "Amazon", AMZNPriceHistoryList);
        SystemApp.database.dataAddStocks(AMZNStock);
        
      //Netflix 8
        ArrayList<Double> NFLXPriceHistoryList =  new ArrayList<Double> ();
        Double NFLXPrice = 319.03;
        NFLXPriceHistoryList.add(NFLXPrice);
        StocksDB NFLXStock = new StocksDB("NFLX", "Netflix", NFLXPriceHistoryList);
        SystemApp.database.dataAddStocks(NFLXStock);
        
      //Uber 9
        ArrayList<Double> UBERPriceHistoryList =  new ArrayList<Double> ();
        Double UBERPrice = 141.82;
        UBERPriceHistoryList.add(UBERPrice);
        StocksDB UBERStock = new StocksDB("UBER", "UBER", UBERPriceHistoryList);
        SystemApp.database.dataAddStocks(UBERStock);
        
      //Alibaba 10
        ArrayList<Double> BABAPriceHistoryList =  new ArrayList<Double> ();
        Double BABAPrice = 141.82;
        BABAPriceHistoryList.add(BABAPrice);
        StocksDB BABAStock = new StocksDB("BABA", "Alibaba", BABAPriceHistoryList);
        SystemApp.database.dataAddStocks(BABAStock);
      }    
    }
    
    //**** need to look at ****
    public static double getRandomIntegerBetweenRange(double min, double max){
      double x = (int)(Math.random()*((max-min)+1))+min;
      return x;
  }
    
    //Updates all existing stocks 
    //Untested needs to be checked
    public void updateExisitngStocks() {
      List<StocksDB> dbStocks =  SystemApp.database.dataFindAllStocks();
      Iterator iter = dbStocks.iterator();
      Random r = new Random();
      double price = 0.0;
      while(iter.hasNext()) {
        StocksDB s = (StocksDB) iter.next();
        ArrayList<Double> stockPrices = s.getPriceHistory();
        if(stockPrices.size() != 0) {
        	price = stockPrices.get(stockPrices.size()-1);
        }
        double lower_range =  price/2; 
        double upper_range =  price*1.5;
        double newPrice = getRandomIntegerBetweenRange(lower_range, upper_range);
        int i = stockPrices.size();
        //Double priceChangeAdd = new Double(newPrice, i+1);
        
        stockPrices.add(newPrice);
        s.setPriceHistory(stockPrices);
      }
      SystemApp.database.dataUpdateAllStocks(dbStocks);
    }
   
    
    //TODO: add new Stock
    public void createNewStock(String ticker, String CompanyName, double newPrice ) {
      ArrayList<Double> newPriceHistory = new ArrayList<Double>();
      newPriceHistory.add(newPrice);
      StocksDB updateStock = new StocksDB(ticker, CompanyName, newPriceHistory);
      SystemApp.database.dataAddStocks(updateStock);
    }
    
    //add profits from Stocks and Bonds purchase 
    public void addProfits() {
      SandBprofits += 5.95;
    }
    
    //Initialize Bonds
    public void initBonds() {
      //1 week Bond
      String bondID = "a"; // Think more about bondID placeholder for now
      double interest = 0.01;
      int maturity = 7;
      String bondType = "Day";
      BondsDB newBond = new BondsDB(bondID, interest, maturity, bondType);
      SystemApp.database.dataAddBonds(newBond);
      
    //1 month Bond
      bondID = "b"; // Think more about bondID placeholder for now
      interest = 0.05;
      maturity = 30;
      bondType = "Day";
      newBond = new BondsDB(bondID, interest, maturity, bondType);
      SystemApp.database.dataAddBonds(newBond);
      
      //3 month Bond
      bondID = "c"; // Think more about bondID placeholder for now
      interest = 0.1;
      maturity = 90;
      bondType = "Day";
      newBond = new BondsDB(bondID, interest, maturity, bondType);
      SystemApp.database.dataAddBonds(newBond);
    }
    
    //Set new Bond Interest Rate
    public static boolean setNewInterestRate(String bondID, double newInterestRate) {
     BondsDB updateBond = SystemApp.database.dataFindBonds(bondID);
     System.out.println(newInterestRate);
     updateBond.setInterest(newInterestRate);
     SystemApp.database.dataUpdateBonds(bondID, updateBond); 
     return true;
    }

    public Boolean getShowAllCustomer() {
        return showAllCustomer;
    }

    public void setShowAllCustomer(Boolean showAllCustomer) {
        this.showAllCustomer = showAllCustomer;
    }

    public Boolean getGetReport() {
        return getReport;
    }

    public void setGetReport(Boolean getReport) {
        this.getReport = getReport;
    }
}