public class LoanDB extends Loan {
	
	private String userName;

	public LoanDB(int loanID, double interest, double amount, int type, int boughtAt, String userName) {
		super(loanID, interest, amount, type, boughtAt);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
