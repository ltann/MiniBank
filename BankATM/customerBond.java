public class customerBond extends Bonds {
    private int daysMatured;

    public customerBond(String bondID, String bondType, int maturity, int value, double i, int daysMatured) {
        super(bondID, bondType, maturity, value, i);
        this.daysMatured = daysMatured;
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
