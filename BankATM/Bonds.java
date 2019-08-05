public class Bonds {
    private String bondID;
    private String bondType;
    private int maturity;
    private double interest;

    public Bonds(String bondID, String bondType, int maturity, double i) {
        this.bondType = bondType;
        this.bondID = bondID;
        this.maturity = maturity;
        this.interest = i;
    }

    public double getInterest() { //returns bond's interest rate
      return interest;
    }

    public void changeInterest(double newInterest) {
      interest = newInterest;
    }

    public String getBondType() { //return whether a month or week bond
      return bondType;
    }

    public String getBondID() {
        return bondID;
    }

    public int getMaturity() {
        return maturity;
    }
}
