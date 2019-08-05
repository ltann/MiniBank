public class Person {
    private String loginName;
    private String password;
    private AccessRight ar;

    public Person(){
        this.loginName = "admin";
        this.password = "admin";
        this.ar = AccessRight.BANKER;
    }

    public Person(String loginName, String password, AccessRight ar){
        this.loginName = loginName;
        this.password = password;
        this.ar = ar;
    }

    public AccessRight getAr() {
        return ar;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
		return password;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
        this.password = password;
    }
}
