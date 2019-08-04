import java.util.Map;
import java.util.HashMap;

public class LoanDB {
	private int loanID;
	private String type;
	private double interestRate;
	private Map<String, Double> currency;
	private int broughtTime;
	
	public LoanDB(int loanID, String type, double interestRate, Map<String, Double> currency, int broughtTime) {
		this.loanID = loanID;
		this.type = type;
		this.interestRate = interestRate;
		this.currency = currency;
		this.broughtTime = broughtTime;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Map<String, Double> getCurrency() {
		return currency;
	}

	public void setCurrency(Map<String, Double> currency) {
		this.currency = currency;
	}

	public int getBroughtTime() {
		return broughtTime;
	}

	public void setBroughtTime(int broughtTime) {
		this.broughtTime = broughtTime;
	}
	
}
