
public class CustomerBasicDB {
	
	private String userName;
	private String name;
	private String addr;
	private String cell;
	private String psw;
	private boolean colletral;
	
	//username, password, name, cell, address, collateral
	public CustomerBasicDB(String userName, String psw, String name, String cell,
			String addr, boolean colletral) {
		this.userName = userName;
		this.name = name;
		this.addr = addr;
		this.cell = cell;
		this.psw = psw;
		this.colletral = colletral;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public boolean isColletral() {
		return colletral;
	}
	public void setColletral(boolean colletral) {
		this.colletral = colletral;
	}	
	
}
