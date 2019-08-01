public class Account {
    protected int accountNumber;
    protected int type; // 0: Default, 1: Checking, 2:Saving
    protected Currency[] c = new Currency[3];


    public Account(int type, int accountNumber) {
        this.type = type;
        this.accountNumber = accountNumber;
        c[0] = new Currency("USD", '$');
        c[1] = new Currency("RMB", '¥');
        c[2] = new Currency("EUR", '€');
    }

    public Account(int type) {  
        this(type, -1);
    }

    public Account() {
        this(0, -1);
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
        info += "  Account\n";
        for(int i=0;i<=2;i++){
            if(c[i].getBalance()==0){
                continue;
            }
            info += c[i].getName();
            info += " ";
            info += c[i].getSymbol();
            info += c[i].getBalance();
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