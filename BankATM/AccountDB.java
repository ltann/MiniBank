import java.util.HashMap;
import java.util.Map;

public class AccountDB {
	private String userName;
	private int accountNumber;
	private int type;
	private Map<String, Double> currency;
	
	
	public AccountDB(String userName, int accountNumber, int type, Map<String, Double> currency) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.type = type;
		this.currency = currency;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Map<String, Double> getCurrency() {
		return currency;
	}
	public void setCurrency(Map<String, Double> currency) {
		this.currency = currency;
	}
	
	
}
