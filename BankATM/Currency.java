public class Currency {
    private String name;
    private char symbol;
    private double balance;

    public Currency(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.balance = 0.0;
    }

    public Currency() {
        this("USD", '$');
    }
    
    public Currency(String name, double balance) {
		this.name = name;
		switch(name) {
			case "USD":
				this.symbol = '$';
				break;
			case "RMB":
				this.symbol = '¥';
				break;
			case "EUR":
				this.symbol = '€';
				break;	
		}
		this.balance = balance;
	}

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double money) {
        this.balance += money;
    }

    public void withdraw(double money) {
        this.balance -= money;
    }
}