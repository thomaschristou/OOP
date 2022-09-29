import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainclass {

	public static void main(String[] args) throws FileNotFoundException {
		// useraccountfile();
		List<Product> listofstock = new ArrayList<Product>();
		listofstock = stockfile();
		for (int i = 0; i < listofstock.size(); i++) {
			//System.out.println(listofstock.get(i).toString());
		}
		Keyboard mouse = new Keyboard(238800, "keyboard", "flexible", "Microsoft", "black", "wired", "10", 26.50, 30.0, "US");
		String newst = mouse.toString();
		System.out.println(newst);
		System.out.println("238800, keyboard, flexible, Microsoft, black, wired, 10, 26.50, 30.0, US");
	}

	public static void useraccountfile() throws FileNotFoundException {
		List<Users> listofusers = new ArrayList<Users>();
		File inputFile = new File("src/Useraccounts");
		Scanner fileScanner = new Scanner(inputFile);
		Customer thecustomer = null;
		Admin theadmin = null;
		Address ad = null;
		while (fileScanner.hasNextLine()) {
			String[] details = fileScanner.nextLine().split(",");
			ad = new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim());
			if (details[6].equals("customer")) {
				thecustomer = new Customer(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
						ad, details[6].trim());
				listofusers.add(thecustomer);
			} else {
				theadmin = new Admin(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(), ad,
						details[6].trim());
				listofusers.add(theadmin);
			}
		}
		for (int i = 0; i < listofusers.size(); i++) {
			System.out.println(listofusers.get(i).toString() + ", " + listofusers.get(i).getad().toString());
		}
		fileScanner.close();
	}

	public static List<Product> stockfile() throws FileNotFoundException {
		List<Product> listofstock = new ArrayList<Product>();
		File inputFile = new File("src/Stock");
		Scanner fileScanner = new Scanner(inputFile);
		Keyboard thekeyboard = null;
		Mouse themouse = null;
		while (fileScanner.hasNextLine()) {
			String[] thedetails = fileScanner.nextLine().split(",");

			if (thedetails[1].trim().equals("keyboard")) {
				thekeyboard = new Keyboard(Long.parseLong(thedetails[0].trim()), thedetails[1].trim(),
						thedetails[2].trim(), thedetails[3].trim(), thedetails[4].trim(), thedetails[5].trim(),
						thedetails[6].trim(), Double.parseDouble(thedetails[7].trim()),
						Double.parseDouble(thedetails[8].trim()), thedetails[9].trim());
				listofstock.add(thekeyboard);
			} else {
				themouse = new Mouse(Long.parseLong(thedetails[0].trim()), thedetails[1].trim(), thedetails[2].trim(),
						thedetails[3].trim(), thedetails[4].trim(), thedetails[5].trim(), thedetails[6].trim(),
						Double.parseDouble(thedetails[7].trim()), Double.parseDouble(thedetails[8].trim()),
						Integer.parseInt(thedetails[9].trim()));
				listofstock.add(themouse);
			}
		}

		fileScanner.close();
		return listofstock;
	}

	
	/*public static void mouselist(List<Product> stocklist ){

		for ( Product accessory : stocklist )
		{
		    if ( accessory instanceof Mouse )
		    {
		        Mouse mouse = ( Mouse ) accessory;
		        System.out.print(mouse.getNumofbuttons());
		    }
		    if ( accessory instanceof Keyboard )
		    {
		        Keyboard keyboard = ( Keyboard ) accessory;
		        System.out.print(keyboard.getLayout());
		    }
		}
		
		

		
	}*/
	
}
