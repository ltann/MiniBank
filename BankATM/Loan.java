public class Loan {

    private int LoanID;
    private int type; // 0: 3 month, 1: 2 month, 2: 1 month
    private double interest;
    private double amount;
    private int boughtAt;

    public Loan(int LoanID, double interest, double amount, int type, int BoughtAt){
        this.LoanID = LoanID;
        this.type = type;
    	this.amount = amount;
    	this.interest = interest;
        this.boughtAt = BoughtAt;
    }

	public int getLoanID() {
		return LoanID;
	}

	public void setLoanID(int loanID) {
		LoanID = loanID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getBoughtAt() {
		return boughtAt;
	}

	public void setBoughtAt(int boughtAt) {
		this.boughtAt = boughtAt;
	}
    




}
