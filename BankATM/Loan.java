public class Loan {
    private double interest;
    private Currency currency;
    private int currencyType;
    private String type;
    private int boughtAt;
    private int dayBought;

    public Loan(double interest, int currencyType, String type, int priceBoughtAt, int dayBought){
        this.interest = interest;
        this.type = type;
        this.boughtAt = priceBoughtAt;
        this.dayBought = dayBought;
        this.currencyType = currencyType;
        switch (currencyType){
            case 0:
                this.currency = new Currency("USD",'$');
            case 1:
                this.currency = new Currency("RMB", '¥');
            case 2:
                this.currency = new Currency("EUR", '€');
        }
    }
    
    public Loan(double interest, int index){
        this.interest = interest;
        this.currencyType = index;
        switch (index){
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

    public int getDayBought() {
        return dayBought;
    }

    public int getCurrencyType() {
        return currencyType;
    }
}
