public abstract class Users {
	private int Userid;
	private String username;
	private String name;
	private Address ad;
	private String usertype;
	
	
	public Users(int Userid, String username, String name, Address add, String usertype) {
		this.Userid = Userid;
		this.username=username;
		this.name=name;
		this.ad = add;
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return ("UserID:"+ getUserid() +", Username:"+getUsername() + ", name:"+getName());
	}

	public void setUserid(int userid) {
		Userid = userid;
	}

	public String getUsername() {
		return username;
	}
	
	public int getUserid() {
		return Userid;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Address getAd() {
		return ad;
	}
	public void setAd(Address ad) {
		this.ad = ad;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Address getad() {
		return ad;
	}
}
