public class Creditcard extends Payment {
	private int cardnumber;
	private int securitycode;
	
	public int getCardnumber() {
		return cardnumber;
	}
	
	public void setCardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	public int getSecuritycode() {
		return securitycode;
	}
	
	public void setSecuritycode(int securitycode) {
		this.securitycode = securitycode;
	}
	
	public Creditcard(int cardnumber, int securitycode) {
		super();
		this.cardnumber = cardnumber;
		this.securitycode = securitycode;
	}
	
}
