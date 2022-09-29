import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Customer extends Users {
	static List<Product> basket = new ArrayList<Product>();

	public Customer(int Userid, String username, String name, Address add, String ut) {
		super(Userid, username, name, add, ut);
	}

	public double calculatebaskettotal() {
		double total = 0;
		
		for(int j = 0; j<basket.size();j++) {
			total += basket.get(j).getRetailprice();
		}
		total = total * 100;
		total = Math.round(total);
		total = total / 100;
		
		return total;	
	}
	
	public List<Product> getBasket() {
		return basket;
	}

	public void pay(String paymentmethod) {
		JOptionPane.showMessageDialog(null,  "£"+calculatebaskettotal()+" paid using "+paymentmethod+", and the delivery address is "
				+this.getad().toString()  );
	}
	
	public static boolean changequantities(List<Product> listofstock) {
		boolean quantitiesalign = true;
		List<Product> finalbasket = new ArrayList<>();
		List<Product> basketupdate = basket;
		for (int i = 0; i < basketupdate.size(); i++) {
			boolean inbasket = false;
			for (int x = 0; x < finalbasket.size(); x++) {
				if (finalbasket.get(x).getBarcode() == basketupdate.get(i).getBarcode()) {
					inbasket = true;
					basketupdate.get(i).setQuantityinbasket(basketupdate.get(i).getQuantityinbasket() + 1);
				}
			}
			if (inbasket == false) {
				Product accessory = basketupdate.get(i);
				String features = "";
				if (accessory instanceof Mouse) {
					Mouse mouse = (Mouse) accessory;
					features = Integer.toString(mouse.getNumofbuttons());
				}
				if (accessory instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) accessory;
					features = keyboard.getLayout();
				}
				basketupdate.get(i).setQuantityinbasket(1);
				finalbasket.add(basketupdate.get(i));
			}
		}
		
		for(int i=0;i<listofstock.size();i++) {
			for(int j=0;j<finalbasket.size();j++) {
				if(finalbasket.get(j).getBarcode()==listofstock.get(i).getBarcode()) {
					if(finalbasket.get(j).getQuantityinbasket()>listofstock.get(i).getQinstock()) {
						//System.out.println(basket.get(j).getQuantityinbasket());
						//System.out.println(listofstock.get(j).getQinstock());
						quantitiesalign =false;
					}
				}
			}
		}
		
		if (quantitiesalign==true) {
			for(int i=0;i<listofstock.size();i++) {
				for(int j=0;j<finalbasket.size();j++) {
					if(finalbasket.get(j).getBarcode()==listofstock.get(i).getBarcode()) {
						listofstock.get(i).setQinstock(listofstock.get(j).getQinstock() - finalbasket.get(i).getQuantityinbasket());
					}
				}
			}
		}
		return quantitiesalign;
	}
	
	public void setBasket(List<Product> basket) {
		this.basket = basket;
	}


	@Override
	public String toString() {
		return ("UserID:"+ getUserid() +", Username:"+getUsername() + ", name:"+getName());
	}
}


