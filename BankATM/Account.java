public class Account {
    protected int accountNumber;
    protected int type; // 0: Default, 1: Checking, 2:Saving, 3: Security
    protected Currency[] c;

    
    public Account(int type, int accountNumber) {
        this.type = type;
        this.accountNumber = accountNumber;
        if(type < 3){
            c = new Currency[3];
            c[0] = new Currency("USD", '$');
            c[1] = new Currency("RMB", '¥');
            c[2] = new Currency("EUR", '€');
        }else if(type == 3){
            this.c = new Currency[1];
            c[0] = new Currency("USD", '$');
        }

    }

    public Account(int type) {
        this(type, -1);
    }

    public Account() {
        this(0, -1);
    }

    public Account(int accountNumber, int type, Currency[] c) {
		this.accountNumber = accountNumber;
		this.type = type;
		this.c = c;
	}
    
    public int getType() {
        return type;
    }

    public String getAccountInfo() {
        String info = "";
        info += this.getAccountNumber();
        info += " ";
        if(this.getType()==0){
            info += "Default";
        }
        else if(this.getType()==1){
            info += "Checking";
        }
        else if(this.getType()==2){
            info += "Saving";
        }
        else if(this.getType() == 3){
            info += "Security";
        }
        info += "  Account\n";
        if(this.getType() <3) {
            for (int i = 0; i <= 2; i++) {
                if (c[i].getBalance() == 0) {
                    continue;
                }
                info += c[i].getName();
                info += " ";
                info += c[i].getSymbol();
                info += c[i].getBalance();
                info += " ";
            }
        }
        else if(this.getType() == 3){
            info += c[0].getName();
            info += " ";
            info += c[0].getSymbol();
            info += c[0].getBalance();
            info += " ";
        }
        info += "\n";
        return info;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Currency[] getC() {
        return c;
    }
}