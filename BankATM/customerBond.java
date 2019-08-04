public class customerBond extends Bonds {
    private int daysMatured = 0;

    public customerBond(String bondID, String bondType, int maturity, int value, double i) {
        super(bondID, bondType, maturity, value, i);
    }

    public void updateDaysMatured(){
        daysMatured += 1;
    }

    public boolean isMatured(){
        boolean isMatured = true;
        if(getDaysMatured() == getMaturity()){
            System.out.println(getBondID() + " have matured.");
            System.out.println(getBondID() + " has matured for " + (daysMatured - getMaturity()) + " day(s).");
        }
        else{
            isMatured = false;

        }
        return isMatured;
    }

    public void changeInterest(double newInterest) {
        System.out.println("Customer's bond's interest should stay the same and will not be changed");
    }

    public int getDaysMatured() {
        return daysMatured;
    }
}
