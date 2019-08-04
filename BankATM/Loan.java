public class Loan {
    private double interest;
    private Currency currency;
    private String type;
    private int boughtAt;

    public Loan(double interest, int currencyType){
        this.interest = interest;
        switch (currencyType){
            case 0:
                this.currency = new Currency("USD",'$');
            case 1:
                this.currency = new Currency("RMB", '¥');
            case 2:
                this.currency = new Currency("EUR", '€');
        }
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
