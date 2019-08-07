import java.util.ArrayList;

public class BankDB {

	private ArrayList<String> report;
	private ArrayList<Double> profit;
	private int accountNum;
	
	
	public BankDB(ArrayList<String> report, ArrayList<Double> profit, int accountNum) {
		this.report = report;
		this.profit = profit;
		this.accountNum = accountNum;
	}
	
	public ArrayList<String> getReport() {
		return report;
	}
	
	public void setReport(ArrayList<String> report) {
		this.report = report;
	}
	
	public ArrayList<Double> getProfit() {
		return profit;
	}
	
	public void setProfit(ArrayList<Double> profit) {
		this.profit = profit;
	}
	
	public int getAccountNum() {
		return accountNum;
	}
	
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
}
