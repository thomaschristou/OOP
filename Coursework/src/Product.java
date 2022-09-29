public abstract class Product {
	private long barcode;
	private String devicename;
	private String devicetype;
	private String brand;
	private String colour;
	private String connectivity; 
	private int qinstock;
	private double originalcost; 
	private double retailprice;
	private int quantityinbasket;


	public Product(long barcode, String devicename, String devicetype, String brand, String colour, String connectivity, String qinstock, double originalcost, double retailprice) {
		this.barcode = barcode;
		this.devicename = devicename;
		this.devicetype = devicetype;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.qinstock = Integer.parseInt(qinstock);
		this.originalcost = originalcost;
		this.retailprice = retailprice;
		this.quantityinbasket = 1;
	}



	@Override
	public String toString() {
		return "barcode=" + barcode + ", devicename=" + devicename + ", devicetype=" + devicetype + ", brand="
				+ brand + ", colour=" + colour + ", connectivity=" + connectivity + ", qinstock=" + qinstock
				+ ", originalcost=" + originalcost + ", retailprice=" + retailprice;
	}



	public long getBarcode() {
		return barcode;
	}



	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}



	public String getDevicename() {
		return devicename;
	}



	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}



	public String getDevicetype() {
		return devicetype;
	}



	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getColour() {
		return colour;
	}



	public void setColour(String colour) {
		this.colour = colour;
	}



	public String getConnectivity() {
		return connectivity;
	}



	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}



	public int getQinstock() {
		return qinstock;
	}



	public void setQinstock(int qinstock) {
		this.qinstock = qinstock;
	}



	public double getOriginalcost() {
		return originalcost;
	}



	public void setOriginalcost(double originalcost) {
		this.originalcost = originalcost;
	}



	public double getRetailprice() {
		return retailprice;
	}



	public void setRetailprice(double retailprice) {
		this.retailprice = retailprice;
	}
	
	public int getQuantityinbasket() {
		return quantityinbasket;
	}



	public void setQuantityinbasket(int quantityinbasket) {
		this.quantityinbasket = quantityinbasket;
	}

}
