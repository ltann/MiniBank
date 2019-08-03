import java.util.ArrayList;

public class Banker extends Person {
    public Boolean showAllCustomer;
    public Boolean getReport;

    public Banker(String loginName, String password) {
        super(loginName, password, AccessRight.BANKER);
        this.showAllCustomer = false;
        this.getReport = false;
        
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
    public void createInitialStocks() {
      if(Welcome.database.dataFindAllStocks() == null) {
        //Apple Stock
        ArrayList<stockPriceHistory> ApplePriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory ApplePrice =  new stockPriceHistory(100);
        ApplePriceHistoryList.add(ApplePrice);
        StocksDB AppleStock = new StocksDB("AAPL", ApplePriceHistoryList);
        Welcome.database.dataAddStocks(AppleStock);
        
        //Microsoft Stock
        ArrayList<stockPriceHistory> MicrosoftPriceHistoryList =  new ArrayList<stockPriceHistory> ();
        stockPriceHistory MicroPrice =  new stockPriceHistory(100);
        ApplePriceHistoryList.add(ApplePrice);
        StocksDB AppleStock = new StocksDB("MSFT", ApplePriceHistoryList);
        Welcome.database.dataAddStocks(AppleStock);
        
        
      }    
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