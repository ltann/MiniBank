package bank;

import java.util.HashMap;

public class SecurityAccountDB {
	private String userName; 
	private String accountNumber; 
	private HashMap<String, Stocks> stock; 
	private HashMap<String, Bonds> bond;
	private Currency balance;
	
	public SecurityAccountDB(String userName, String accountNumber, HashMap<String, Stocks> stock,
			HashMap<String, Bonds> bond, Currency balance) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.stock = stock;
		this.bond = bond;
		this.balance = balance;
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

	public HashMap<String, Stocks> getStock() {
		return stock;
	}

	public void setStock(HashMap<String, Stocks> stock) {
		this.stock = stock;
	}

	public HashMap<String, Bonds> getBond() {
		return bond;
	}

	public void setBond(HashMap<String, Bonds> bond) {
		this.bond = bond;
	}

	public Currency getBalance() {
		return balance;
	}

	public void setBalance(Currency balance) {
		this.balance = balance;
	}

	
}
