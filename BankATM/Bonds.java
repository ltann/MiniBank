public class Bonds {
    private String bondID;
    private String bondType;
    private int maturity;
    private int amount;
    private double interest;

    public Bonds(String bondID, String bondType, int maturity, int value, double i) {
        this.bondType = bondType;
        this.bondID = bondID;
        this.maturity = maturity;
        this.amount = value;
        this.interest = i;

    }
    public int getAmount() { //return bond's value
      return amount;
    }

    public double getInterest() { //returns bond's interest rate
      return interest;
    }

    public void changeInterest(double newInterest) {
      interest = newInterest;
    }

    public String getTypeofBond() { //return whether a month or week bond
      return typeOfBond;
    }
}
