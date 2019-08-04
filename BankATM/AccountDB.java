import java.util.HashMap;
import java.util.Map;

public class AccountDB {
	private String userName;
	private String accountNumber;
	private int type;
	private Map<String, String> currency;
	
	
	public AccountDB(String userName, String accountNumber, int type, Map<String, String> currency) {
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Map<String, String> getCurrency() {
		return currency;
	}
	public void setCurrency(Map<String, String> currency) {
		this.currency = currency;
	}
	
	
}
