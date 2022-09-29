import java.util.List;

public class Admin extends Users {

	public Admin(int Userid, String username, String name, Address Ad, String ut) {
		super(Userid, username, name, Ad, ut);
	}

	public static void addkeyboard(List<Product> prodlist, long barcode, String devicename, String devicetype,
			String brand, String colour, String connectivity, String qinstock, double originalcost, double retailprice,
			String thelayout) {
		boolean inprodlist = false;
		
		Keyboard keyboard = new Keyboard(barcode, devicename, devicetype, brand, colour, connectivity, qinstock,
				originalcost, retailprice, thelayout);
		
		inprodlist = false;
		for(int i =0;i<prodlist.size();i++) {
			if(keyboard.getBarcode() == prodlist.get(i).getBarcode() ) {
				inprodlist = true;
				prodlist.get(i).setQinstock(prodlist.get(i).getQinstock()+Integer.parseInt(qinstock));
			}
		}
		
		if(inprodlist==false) {
			prodlist.add(keyboard);
		}
		
	}

	public static void addmouse(List<Product> prodlist, long barcode, String devicename, String devicetype,
			String brand, String colour, String connectivity, String qinstock, double originalcost, double retailprice,
			int nob) {
		boolean inprodlist = false;
		Mouse mouse = new Mouse(barcode, devicename, devicetype, brand, colour, connectivity, qinstock, originalcost,
				retailprice, nob);
		
		inprodlist = false;
		for(int i =0;i<prodlist.size();i++) {
			if(mouse.getBarcode() == prodlist.get(i).getBarcode() ) {
				inprodlist = true;
				prodlist.get(i).setQinstock(prodlist.get(i).getQinstock()+Integer.parseInt(qinstock));
			}
		}
		
		if(inprodlist==false) {
			prodlist.add(mouse);
		}

	}
}
