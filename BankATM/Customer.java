import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<Account> acc;
    private ArrayList<Loan> ln;
    private int accountNumber;
    private int loanNumber;
    private PersonInfo perInfomation;
    private boolean collateral;
    public Customer(){
        super("-------","-------",AccessRight.CUSTOMER);
        this.accountNumber=0;
        this.loanNumber=0;
        acc=new ArrayList<Account>();
        ln=new ArrayList<Loan>();
        this.collateral = false;
    }
    public Customer(String username, String password, String name, String cell, String address, boolean collateral){
        super(username,password,AccessRight.CUSTOMER);
        this.accountNumber=0;
        this.loanNumber=0;
        perInfomation=new PersonInfo(name,cell,address);
        acc=new ArrayList<Account>();
        ln=new ArrayList<Loan>();
        this.collateral = collateral;
    }
    
    public void addAccount(int type,int number){
        //type is type => saving=2 checking=1 default=0
        //number is account number
        acc.add(new Account(type,number));
        accountNumber++;
    }

    public boolean delAccount(int number) {
        for(int i = 0; i < acc.size(); i++) {
            if(acc.get(i).getAccountNumber() == number) {
                acc.remove(i);
                accountNumber--;
                return true;
            }
        }
        return false;
    }

    public void addLoan(Loan loan){
        //x the amount money
        ln.add(loan);
        loanNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getLoanNumber() {
        return loanNumber;
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
}