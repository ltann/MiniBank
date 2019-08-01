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