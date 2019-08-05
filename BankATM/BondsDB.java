public class BondsDB {
	private String bondID;
	private int amount;
	private double interest;
	private int maturity;
	private String bondType;
	
	public BondsDB(String bondID, double interest, int maturity, String bondType) {
		this.bondID = bondID;
		this.interest = interest;
		this.maturity = maturity;
		this.bondType = bondType;
	}

	public String getBondID() {
		return bondID;
	}

	public void setBondID(String bondID) {
		this.bondID = bondID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getMaturity() {
		return maturity;
	}

	public void setMaturity(int maturity) {
		this.maturity = maturity;
	}

	public String getBondType() {
		return bondType;
	}

	public void setBondType(String bondType) {
		this.bondType = bondType;
	}
	
	
}
