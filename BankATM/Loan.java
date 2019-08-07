public class Loan {
	private int loanID; //add new field
    private double interest;
    // as in Alex's design, we only allow to loan with USD, so change the type from Currency class to double
    private double amount;
//  private Currency currency;
//  private int currencyType; //haha, currency type has already included in Currency class
    private int type;
    private int boughtAt; 

    public Loan(int loanID, double interest, double amount, int type, int boughtAt) {
		this.loanID = loanID;
		this.interest = interest;
		this.amount = amount;
		this.type = type;
		this.boughtAt = boughtAt;
    }

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBoughtAt() {
		return boughtAt;
	}

	public void setBoughtAt(int boughtAt) {
		this.boughtAt = boughtAt;
	}

}
