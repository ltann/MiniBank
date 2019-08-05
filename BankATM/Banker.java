import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import bank.BondsDB;

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
        ArrayList<stockPriceHistory> ApplePriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory ApplePrice =  new stockPriceHistory(204.50, 0);
        ApplePriceHistoryList.add(ApplePrice);
        StocksDB AppleStock = new StocksDB("AAPL", "Apple", ApplePriceHistoryList);
        GUI.database.dataAddStocks(AppleStock);
        
        //Microsoft Stock 2
        ArrayList<stockPriceHistory> MicroPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory MicroPrice =  new stockPriceHistory(136.95, 0);
        MicroPriceHistoryList.add(MicroPrice);
        StocksDB MicroStock = new StocksDB("MSFT", "MicroSoft", MicroPriceHistoryList);
        GUI.database.dataAddStocks(MicroStock);
        
        //Tesla Stock 3
        ArrayList<stockPriceHistory> TeslaPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory TeslaPrice =  new stockPriceHistory(235.69, 0);
        TeslaPriceHistoryList.add(TeslaPrice);
        StocksDB TeslaStock = new StocksDB("TSLA","Tesla", TeslaPriceHistoryList);
        GUI.database.dataAddStocks(TeslaStock);
        
        //General Electric 4
        ArrayList<stockPriceHistory> GEPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory GEPrice =  new stockPriceHistory(9.99, 0);
        GEPriceHistoryList.add(GEPrice);
        StocksDB GEStock = new StocksDB("GE","GE", GEPriceHistoryList);
        GUI.database.dataAddStocks(GEStock);
        
       //Bank Of America 5
        ArrayList<stockPriceHistory> BACPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory BACPrice =  new stockPriceHistory(29.43, 0);
        BACPriceHistoryList.add(BACPrice);
        StocksDB BACStock = new StocksDB("BAC","Bank of America", BACPriceHistoryList);
        GUI.database.dataAddStocks(BACStock);
        
        //Disney 6
        ArrayList<stockPriceHistory> DISPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory DISPrice =  new stockPriceHistory(141.82, 0);
        DISPriceHistoryList.add(DISPrice);
        StocksDB DISStock = new StocksDB("DIS","Disney", DISPriceHistoryList);
        GUI.database.dataAddStocks(DISStock);
        
      //Amazon 7
        ArrayList<stockPriceHistory> AMZNPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory AMZNPrice =  new stockPriceHistory(1824.29, 0);
        DISPriceHistoryList.add(AMZNPrice);
        StocksDB DISStock = new StocksDB("AMZN", "Amazon", AMZNPriceHistoryList);
        GUI.database.dataAddStocks(AMZNStock);
        
      //Netflix 8
        ArrayList<stockPriceHistory> NFLXPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory NFLXPrice =  new stockPriceHistory(319.03, 0);
        NFLXPriceHistoryList.add(NFLXPrice);
        StocksDB NFLXStock = new StocksDB("NFLX", "Netflix", NFLXPriceHistoryList);
        GUI.database.dataAddStocks(NFLXStock);
        
      //Uber 9
        ArrayList<stockPriceHistory> UBERPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory UBERPrice =  new stockPriceHistory(141.82, 0);
        UBERPriceHistoryList.add(UBERPrice);
        StocksDB UBERStock = new StocksDB("UBER", "UBER", UBERPriceHistoryList);
        GUI.database.dataAddStocks(UBERStock);
        
      //Alibaba 10
        ArrayList<stockPriceHistory> BABAPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory BABAPrice =  new stockPriceHistory(141.82, 0);
        BABAPriceHistoryList.add(BABAPrice);
        StocksDB BABAStock = new StocksDB("BABA", "Alibaba", BABAPriceHistoryList);
        GUI.database.dataAddStocks(BABAStock);
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
      List<StocksDB> dbStocks =  GUI.database.dataFindAllStocks();
      Iterator<E> iter = new dbStocks.iterator();
      while(iter.hasNext()) {
        Random r = new Random();
        StocksDB s = (StocksDB) iter.next();
        ArrayList <stockPriceHistory> stockPrices = s.priceHistory.getPriceHistory();
        double price = stockPrices.get(0).getPrice();
        double lower_range =  price/2; 
        double upper_range =  price*2;
        double newPrice = getRandomIntegerBetweenRange(lower_range, upper_range);
        int i = stockPrices.get(0).getDay();
        stockPriceHistory priceChangeAdd = new stockPriceHistory(newPrice, i+1);
        stockPrices.add(priceChangeAdd);
      }
      GUI.database.dataUpdateAllStocks(dbStocks);
    }
   
    
    //TODO: add new Stock
    public void createNewStock(String ticker, String CompanyName, double newPrice ) {
      StockDB updateStock = new StocksDB(ticker, CompanyName, newPrice);
      dataAddStocks(updateStock);
    }
    
    //add profits from Stocks and Bonds purchase 
    public void addProfits() {
      SandBprofits += 5.95;
    }
    
    //Initialize Bonds
    public void initBonds() {
      //1 week Bond
      String bondID = "a"; // Think more about bondID placeholder for now
      int amount = 10000;
      double interest = 0.01;
      int maturity = 1;
      String bondType = "week";
      BondsDB newBond = BondsDB(bondID, amount, interest, maturity, bondType);
      GUI.database.dataAddBonds(newBond);
      
    //1 month Bond
      bondID = "b"; // Think more about bondID placeholder for now
      amount = 10000;
      interest = 0.05;
      maturity = 1;
      bondType = "month";
      newBond = BondsDB(bondID, amount, interest, maturity, bondType);
      GUI.database.dataAddBonds(newBond);
      
      //3 month Bond
      bondID = "c"; // Think more about bondID placeholder for now
      amount = 10000;
      interest = 0.1;
      maturity = 3;
      bondType = "month";
      newBond = BondsDB(bondID, amount, interest, maturity, bondType);
      GUI.database.dataAddBonds(newBond);
    }
    
    //Set new Bond Interest Rate
    public void setNewInterestRate(String bondID, double newInterestRate) {
     BondsDB updateBond = GUI.database.dataFindBonds(String bondID);
     updateBond.setInterest(newInterestRate);
     GUI.database.dataUpdateBonds(bondID, updateBond); 
      
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