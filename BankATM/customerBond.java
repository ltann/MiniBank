public class customerBond extends Bonds {
    private int daysMatured;
    private int customerBondID;
    private double amount;

    public int getCustomerBondID() {
		return customerBondID;
	}

	public void setCustomerBondID(int customerBondID) {
		this.customerBondID = customerBondID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDaysMatured(int daysMatured) {
		this.daysMatured = daysMatured;
	}

	public customerBond(String bondID, String bondType, int maturity, double value, double i, int daysMatured, int customerBondID) {
        super(bondID, bondType, maturity, i);
        this.daysMatured = daysMatured;
        this.amount = value;
        this.customerBondID = customerBondID;
    }

    public void updateDaysMatured(){
        daysMatured -= 1;
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
