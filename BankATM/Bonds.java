public class Bonds {
    private int amount;
    private double interest;
    private int maturity; //how long the bond is until receive interest.
    private String typeOfBond; //week or Month 

    public Bonds(int value, double i, int length, String type, int id) {
      amount = value;
      interest = i;
      maturity = length;
      typeOfBond = type; 
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
    public int getLength() {// return bond's total length
      return maturity;
    }
    public String getTypeofBond() { //return whether a month or week bond
      return typeOfBond;
    }
    public double getMaturityValue(){// returns bond's value at maturity
      double x = interest + 1;
      return amount*x ;
    }
}
