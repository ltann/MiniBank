public class BankerBasicDB {
	
	private String bankerName;
	private String psw;
	
	public BankerBasicDB(String bankerName, String psw) {
		this.bankerName = bankerName;
		this.psw = psw;
	}
	public String getBankerName() {
		return bankerName;
	}
	public void setBankerName(String bankerName) {
		this.bankerName = bankerName;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}

}
