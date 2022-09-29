import java.io.FileNotFoundException;

public class Mouse extends Product{
	private int numofbuttons;

	public Mouse(long barcode, String devicename, String devicetype, String brand, String colour,String connectivity, String qinstock, double originalcost, double retailprice, int nob) {
		super(barcode, devicename, devicetype, brand, colour, connectivity, qinstock, originalcost, retailprice);
		this.numofbuttons = nob;
	}

	public int getNumofbuttons() {
		return numofbuttons;
	}

	public void setNumofbuttons(int numofbuttons) {
		this.numofbuttons = numofbuttons;
	}
//237799, mouse, standard, Logitech, grey, wireless, 13, 7.0, 8.0, 2

	@Override
	public String toString() {
		return this.getBarcode()+", "+this.getDevicename() +", "+this.getDevicetype()+", "+this.getBrand()+", "+this.getColour()
		+", "+this.getConnectivity() +", "+this.getQinstock()+", "+this.getOriginalcost()+", "+this.getRetailprice()+", "+numofbuttons;
	}


	
}
