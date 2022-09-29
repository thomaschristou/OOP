public class Address {
	private int housenumber;
	private String postcode;
	private String city;

	public Address(int housenumber, String postcode, String city) {
		this.housenumber = housenumber;
		this.postcode = postcode;
		this.city = city;
	}

	@Override
	public String toString() {
		return ("Housenumber: "+this.housenumber + ", Postcode: " + this.postcode+ ", City: " + this.city);
	}
}