public class Loan {
    private double interest;
    private Currency currency;

    public Loan(){
        this.interest = 0.01;
        this.currency = new Currency("USD",'$');
    }

    public Loan(double interest, int index){
        this.interest = interest;
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
