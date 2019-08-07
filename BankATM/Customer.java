import java.util.ArrayList;
import java.util.ListIterator;

public class Customer extends Person {
    private ArrayList<Account> acc;
    private SecurityAccount sacc;
    private ArrayList<Loan> ln;
    private PersonInfo perInfomation;
    private boolean collateral;
    public Customer(){
        super("-------","-------",AccessRight.CUSTOMER);
        acc=new ArrayList<Account>();
        ln=new ArrayList<Loan>();
        this.collateral = false;
    }
    public Customer(String username, String password, String name, String cell, String address, boolean collateral){
        super(username,password,AccessRight.CUSTOMER);
        perInfomation=new PersonInfo(name,cell,address);
        acc=new ArrayList<Account>();
        sacc = null;
        ln=new ArrayList<Loan>();
        this.collateral = collateral;
    }
    
    public void addAccount(int type,int number){
        //type is type => security=3 saving=2 checking=1 default=0
        //number is account number
        acc.add(new Account(type,number));
    }

    public boolean delAccount(int number) {
        for(int i = 0; i < acc.size(); i++) {
            if(acc.get(i).getAccountNumber() == number) {
                acc.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addLoan(Loan loan){
        ln.add(loan);
    }

    public void removeLoan(Loan loan){
        ln.remove(loan);
    }

    public int getAccountNumber() {
        return acc.size();
    }

    public int getLoanNumber() {
        return ln.size();
    }

    public ArrayList<Account> getAcc() {
        return acc;
    }

    public ArrayList<Loan> getLn() {
        return ln;
    }

    public PersonInfo getPerInfomation() {
        return perInfomation;
    }

    public boolean isCollateral() {
        return collateral;
    }

    public void setCollateral(boolean collateral) {
        this.collateral = collateral;
    }
    
    
    
    /*public String getInfo(){
        String answer;
        answer+=perInfomation.getName();
        answer+=" ";
        answer+=perInfomation.getCell();
        answer+=" ";
        answer+=perInfomation.getAddress();
        answer+="\n";
        for(int i=0;i<=accountNumber-1;i++){
            answer.get(i).getAccountInfo();
            answer+=" ";
        }
        answer+="\n";
        return answer;
    }*/

    public void setAcc(ArrayList<Account> acc) {
		this.acc = acc;
	}
	public void setLn(ArrayList<Loan> ln) {
		this.ln = ln;
	}
	public void setPerInfomation(PersonInfo perInfomation) {
		this.perInfomation = perInfomation;
	}

//	public SecurityAccount getSecurityAccount(){
//        //ListIterator<Account> i = acc.listIterator();
//        while(i.hasNext()){
//            Account acc = i.next();
//            if(acc.getType() == 3){
//                return (SecurityAccount) acc;
//            }
//        }
//        System.out.println("There is no Security Account");
//        return null;
//    }

    public SecurityAccount getSacc() {
		return sacc;
	}
	public void setSacc(SecurityAccount sacc) {
		this.sacc = sacc;
	}
	public boolean hasEnoughMoney(double requiredFunds, int currencyType){
        boolean payable = true;
        if(getTotalBalance(currencyType) < requiredFunds){
            payable = false;
        }
        return payable;
    }

    public double getTotalBalance(int currencyType){//total balance across all account of one type of currency
        ListIterator<Account> i = acc.listIterator();
        double totalFunds = 0.0;
        char currType;
        if(currencyType == 1){
            currType = '$';
        }
        else if(currencyType == 2){
            currType = '¥';
        }
        else{
            currType = '€';
        }
        while(i.hasNext()){
            for(Currency currency: i.next().c){
                if(currency.getSymbol() == currType){
                    totalFunds += currency.getBalance();
                }
            }
        }
        return totalFunds;
    }


}