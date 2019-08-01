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