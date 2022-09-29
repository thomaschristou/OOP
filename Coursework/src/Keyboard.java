
public class Keyboard extends Product{
	private String layout;

	public Keyboard(long barcode, String devicename, String devicetype, String brand, String colour,String connectivity, String qinstock, double originalcost, double retailprice, String thelayout) {
		super(barcode, devicename, devicetype, brand, colour, connectivity, qinstock, originalcost, retailprice);
		this.layout = thelayout;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	@Override
	public String toString() {
		return this.getBarcode()+", "+this.getDevicename() +", "+this.getDevicetype()+", "+this.getBrand()+", "+this.getColour()
		+", "+this.getConnectivity() +", "+this.getQinstock()+", "+this.getOriginalcost()+", "+this.getRetailprice()+", "+layout;
	}
	
}
