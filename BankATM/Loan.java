public class Loan {
    private double interest;
    private Currency currency;
    private String type;
    private int boughtAt;

    public Loan(double interest, int currencyType, String type, int priceBoughtAt){
        this.interest = interest;
        this.type = type;
        this.boughtAt = priceBoughtAt;
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

    public Currency getCurrency() {
        return currency;
    }

    public String getLoanType() {
        return type;
    }

    public int getBoughtAt() {
        return boughtAt;
    }
}
