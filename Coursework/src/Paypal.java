
public class Paypal extends Payment{
	private String emailaddress;

	public Paypal(String emailaddress) {
		super();
		this.emailaddress = emailaddress;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
}
