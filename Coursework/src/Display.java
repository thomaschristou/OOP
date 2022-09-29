import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.RoundingMode;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.table.*;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Display extends JFrame {
	private JPanel contentPane;
	private JTextField brandsearch;
	private JTextField perksearch;
	private JTextField connectivitysearch;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private String username;
	private String paymentmethod;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	static List<Product> listofstock = new ArrayList<Product>();
	static List<Users> listofusers = new ArrayList<Users>();
	private JPanel panel_2;
	private Long[] basket;
	private JTextField brandbox;
	private JTextField barcodebox;
	private JTextField colourbox;
	private JTextField connectivitybox;
	private JLabel totalamount = new JLabel("Total Amount:");
	private JPanel panel_5;
	private JTextField QinSbox;
	private JTextField originalcostbox;
	private JTextField retailpricebox;
	private JTextField typebox;
	private JTextField Layoutbox;
	private String productype;
	private String productcombostring;

	private JTextField firstpaymentbox;
	private JTextField secondpaymentbox;
	private JTextField Peripheralsearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */

	public void emptybasket(String username) {
		DefaultTableModel tblModel = (DefaultTableModel) table_1.getModel();
		tblModel.setRowCount(0);
		for (int j = 0; j < listofusers.size(); j++) {
			if (username == listofusers.get(j).getUsername()) {
				if (listofusers.get(j) instanceof Customer) {
					Customer customer = (Customer) listofusers.get(j);
					List<Product> basketupdate = customer.getBasket();
					basketupdate.clear();
					customer.setBasket(basketupdate);
					listofusers.remove(j);
					listofusers.add(j, customer);
				}
			}
		}
		changebasketview(username);
		totalamount.setText("Total Amount: £");
	}

	public void searchforproduct(String brand, String perk, String connec,String prodsearch, String peripheralchosen) {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setRowCount(0);
		for (int i = 0; i < listofstock.size(); i++) {
			Product accessory = listofstock.get(i);
			boolean addtolist = true;
			if (accessory.getBrand() == brand) {
				addtolist = false;
			}
			if (accessory.getConnectivity() == connec) {
				addtolist = false;
			}
			if (accessory.getDevicetype() == prodsearch) {
				addtolist = false;
			}
			if (accessory.getDevicename() == peripheralchosen) {
				addtolist = false;
			}
			String features = "";
			if (accessory instanceof Mouse) {
				Mouse mouse = (Mouse) accessory;
				features = Integer.toString(mouse.getNumofbuttons());
			}
			if (accessory instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) accessory;
				features = keyboard.getLayout();
			}

			String[] obj = { listofstock.get(i).getDevicename(), listofstock.get(i).getDevicetype(), features,
					listofstock.get(i).getBrand(), listofstock.get(i).getColour(), listofstock.get(i).getConnectivity(),
					Integer.toString(listofstock.get(i).getQinstock()),
					Double.toString(listofstock.get(i).getRetailprice()),
					Long.toString(listofstock.get(i).getBarcode()) };
			if(addtolist==true) {
				tblModel.addRow(obj);
			}
		}

	}

	public void changebasketview(String username) {

		DefaultTableModel tblModel = (DefaultTableModel) table_1.getModel();
		tblModel.setRowCount(0);
		double thetotalamount = 0;
		for (int j = 0; j < listofusers.size(); j++) {

			if (username.trim() == listofusers.get(j).getUsername().trim()) {
				Users theuser = listofusers.get(j);
				if (theuser instanceof Customer) {
					Customer customer = (Customer) theuser;
					List<Product> basketupdate = customer.getBasket();
					List<Product> finalbasket = new ArrayList<>();
					for (int i = 0; i < basketupdate.size(); i++) {
						boolean inbasket = false;
						for (int x = 0; x < finalbasket.size(); x++) {
							if (finalbasket.get(x).getBarcode() == basketupdate.get(i).getBarcode()) {
								inbasket = true;
								basketupdate.get(i).setQuantityinbasket(basketupdate.get(i).getQuantityinbasket() + 1);
								;
								for (int y = 0; y < tblModel.getRowCount(); y++) {
									String tablecellinfo = (String) tblModel.getValueAt(y, 8);
									if (basketupdate.get(i).getBarcode() == Long.parseLong(tablecellinfo)) {
										tblModel.setValueAt(basketupdate.get(i).getQuantityinbasket(), y, 9);
									}
								}
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
							String[] obj = { basketupdate.get(i).getDevicename(), basketupdate.get(i).getDevicetype(),
									features, basketupdate.get(i).getBrand(), basketupdate.get(i).getColour(),
									basketupdate.get(i).getConnectivity(),
									Integer.toString(basketupdate.get(i).getQinstock()),
									Double.toString(basketupdate.get(i).getRetailprice()),
									Long.toString(basketupdate.get(i).getBarcode()),
									Integer.toString(basketupdate.get(i).getQuantityinbasket()) };
							finalbasket.add(basketupdate.get(i));
							tblModel.addRow(obj);
						}
						thetotalamount += basketupdate.get(i).getRetailprice();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Admin Selected.Customer account required to see or add to  basket");
				}
			}
		}

		thetotalamount = thetotalamount * 100;
		thetotalamount = Math.round(thetotalamount);
		thetotalamount = thetotalamount / 100;
		totalamount.setText("Total Amount: £" + thetotalamount);

	}

	public void changeusertable(List<Product> listofstock) throws FileNotFoundException {
		writetofile();
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setRowCount(0);
		for (int i = 0; i < listofstock.size(); i++) {

			Product accessory = listofstock.get(i);
			String features = "";
			if (accessory instanceof Mouse) {
				Mouse mouse = (Mouse) accessory;
				features = Integer.toString(mouse.getNumofbuttons());
			}
			if (accessory instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) accessory;
				features = keyboard.getLayout();
			}

			String[] obj = { listofstock.get(i).getDevicename(), listofstock.get(i).getDevicetype(), features,
					listofstock.get(i).getBrand(), listofstock.get(i).getColour(), listofstock.get(i).getConnectivity(),
					Integer.toString(listofstock.get(i).getQinstock()),
					Double.toString(listofstock.get(i).getRetailprice()),
					Long.toString(listofstock.get(i).getBarcode()) };

			tblModel.addRow(obj);
		}
	}

	public void changeadmintable(List<Product> listofstock) {
		DefaultTableModel tblModel = (DefaultTableModel) table_2.getModel();
		tblModel.setRowCount(0);
		for (int i = 0; i < listofstock.size(); i++) {

			Product accessory = listofstock.get(i);
			String features = "";
			if (accessory instanceof Mouse) {
				Mouse mouse = (Mouse) accessory;
				features = Integer.toString(mouse.getNumofbuttons());
			}
			if (accessory instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) accessory;
				features = keyboard.getLayout();
			}

			String[] obj = { Long.toString(listofstock.get(i).getBarcode()), listofstock.get(i).getBrand(),
					listofstock.get(i).getColour(), listofstock.get(i).getConnectivity(),
					Integer.toString(listofstock.get(i).getQinstock()),
					Double.toString(listofstock.get(i).getOriginalcost()),
					Double.toString(listofstock.get(i).getRetailprice()), features, listofstock.get(i).getDevicetype(),
					listofstock.get(i).getDevicename(), listofstock.get(i).getDevicetype(), features, };

			DefaultTableModel tblModel2 = (DefaultTableModel) table_2.getModel();
			tblModel2.addRow(obj);
		}
	}

	public Display() throws FileNotFoundException {

		listofusers = useraccountfile();
		listofstock = sortbyretailprice(stockfile());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 872, 512);
		contentPane.add(tabbedPane);

		panel_2 = new JPanel();
		tabbedPane.addTab("Login", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		panel_3.setBounds(20, 11, 819, 458);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = (String) comboBox.getSelectedItem();
				// if choice
			}
		});
		for (int i = 0; i < listofusers.size(); i++) {
			comboBox.addItem(listofusers.get(i).getUsername());
		}

		comboBox.setBounds(264, 180, 168, 22);
		panel_3.add(comboBox);

		JLabel lblNewLabel_4 = new JLabel("Choose User");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(274, 132, 158, 37);
		panel_3.add(lblNewLabel_4);

		username = (String) comboBox.getSelectedItem();

		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = (String) comboBox.getSelectedItem();
				changebasketview(username.trim());
			}

		});
		btnNewButton_1.setBounds(452, 180, 89, 23);
		panel_3.add(btnNewButton_1);

		Panel panel_1 = new Panel();
		tabbedPane.addTab("User", null, panel_1, null);
		panel_1.setLayout(null);

		brandsearch = new JTextField();
		brandsearch.setBounds(10, 40, 213, 20);
		panel_1.add(brandsearch);
		brandsearch.setColumns(10);

		perksearch = new JTextField();
		perksearch.setColumns(10);
		perksearch.setBounds(10, 85, 213, 20);
		panel_1.add(perksearch);

		connectivitysearch = new JTextField();
		connectivitysearch.setColumns(10);
		connectivitysearch.setBounds(10, 129, 213, 20);
		panel_1.add(connectivitysearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(233, 16, 616, 392);
		panel_1.add(scrollPane);
		String[] colnames = { "Devicename", "Devicetype", "Buttons/layout", "brand", "colour", "connectivity",
				"Leftinstock", "Price", "Barcode" };
		Object[][] emptydata = {};
		table = new JTable(0, 9);
		table.setRowSelectionAllowed(false);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" }));
		for (int i = 0; i < colnames.length; i++) {
			table.getColumnModel().getColumn(i).setHeaderValue(colnames[i]);
		}
		changeusertable(listofstock);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Brand");
		lblNewLabel.setBounds(10, 23, 190, 14);
		panel_1.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("Number of Buttons/Layout");
		lblNewLabel_2.setBounds(10, 71, 213, 14);
		panel_1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Connectivity");
		lblNewLabel_3.setBounds(10, 115, 213, 14);
		panel_1.add(lblNewLabel_3);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		for (int i = 0; i < listofstock.size(); i++) {
			comboBox_1.addItem(listofstock.get(i).getBarcode() + " , " + listofstock.get(i).getDevicename() + " , "
					+ listofstock.get(i).getBrand() + " , " + listofstock.get(i).getColour());
		}
		comboBox_1.setBounds(122, 441, 338, 22);
		panel_1.add(comboBox_1);

		JButton btnNewButton = new JButton("Add to Basket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = (String) comboBox_1.getSelectedItem();
				String[] barcodechose = choice.split(",");
				for (int i = 0; i < listofstock.size(); i++) {
					if (Long.parseLong(barcodechose[0].trim()) == listofstock.get(i).getBarcode()) {

						for (int j = 0; j < listofusers.size(); j++) {

							if (username == listofusers.get(j).getUsername()) {
								if (listofusers.get(j) instanceof Customer) {
									Customer customer = (Customer) listofusers.get(j);
									List<Product> basketupdate = customer.getBasket();
									basketupdate.add(listofstock.get(i));
									customer.setBasket(basketupdate);
									listofusers.remove(j);
									listofusers.add(j, customer);
								}
							}
						}

					}
				}
				changebasketview(username);
			}
		});
		btnNewButton.setBounds(470, 441, 123, 23);
		panel_1.add(btnNewButton);

		JComboBox productcombo = new JComboBox();
		productcombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productcombostring = (String) productcombo.getSelectedItem();
			}
		});
		productcombo.setBounds(10, 166, 213, 22);
		panel_1.add(productcombo);
		productcombo.addItem("");
		productcombo.addItem("keyboard");
		productcombo.addItem("mouse");
		productcombostring = (String) productcombo.getSelectedItem();

		JLabel lblProducttype_1 = new JLabel("Product Type");
		lblProducttype_1.setBounds(10, 140, 213, 32);
		panel_1.add(lblProducttype_1);

		JLabel lblNewLabel_5_3_1_2_1_2 = new JLabel("Type of Peripheral");
		lblNewLabel_5_3_1_2_1_2.setBounds(10, 199, 118, 14);
		panel_1.add(lblNewLabel_5_3_1_2_1_2);

		Peripheralsearch = new JTextField();
		Peripheralsearch.setColumns(10);
		Peripheralsearch.setBounds(10, 214, 213, 20);
		panel_1.add(Peripheralsearch);

		JButton Searchbutton = new JButton("Search");
		Searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = brandsearch.getText();
				String perk = perksearch.getText();
				String connec = connectivitysearch.getText();
				String prodsearch = productcombostring;
				String peripheralchose = Peripheralsearch.getText();
				searchforproduct(brand.trim(), perk.trim(), connec.trim(), prodsearch.trim(),
						peripheralchose.trim());

			}
		});
		Searchbutton.setBounds(10, 245, 213, 23);
		panel_1.add(Searchbutton);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		tabbedPane.addTab("Basket", null, panel, null);
		panel.setLayout(null);

		totalamount.setBounds(549, 290, 213, 32);
		panel.add(totalamount);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(65, 27, 697, 252);
		panel.add(scrollPane_1);
		String[] basknames = { "Devicename", "Devicetype", "Buttons/layout", "brand", "colour", "connectivity",
				"Leftinstock", "Price", "Barcode", "Quantity" };
		Object[][] emptydata2 = {};
		table_1 = new JTable(0, 10);
		table_1.setRowSelectionAllowed(false);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }));
		for (int i = 0; i < basknames.length; i++) {
			table_1.getColumnModel().getColumn(i).setHeaderValue(basknames[i]);
		}
		scrollPane_1.setViewportView(table_1);

		panel_5 = new JPanel();
		panel_5.setBounds(549, 290, 213, 32);
		panel.add(panel_5);

		JButton btnNewButton_2 = new JButton("Clear Basket");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptybasket(username);
			}
		});
		btnNewButton_2.setBounds(549, 333, 112, 55);
		panel.add(btnNewButton_2);

		JLabel lblNewLabel_5_1_1 = new JLabel("Security Code");
		lblNewLabel_5_1_1.setVisible(false);
		lblNewLabel_5_1_1.setBounds(35, 391, 112, 14);
		panel.add(lblNewLabel_5_1_1);

		JLabel Email = new JLabel("Email");
		Email.setBounds(34, 349, 156, 14);
		panel.add(Email);

		secondpaymentbox = new JTextField();
		secondpaymentbox.setVisible(false);
		secondpaymentbox.setColumns(10);
		secondpaymentbox.setBounds(34, 408, 213, 20);
		panel.add(secondpaymentbox);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentmethod = (String) comboBox.getSelectedItem();

				if ((String) comboBox_1_1.getSelectedItem() == "Paypal") {
					secondpaymentbox.setVisible(false);
					lblNewLabel_5_1_1.setVisible(false);
					Email.setText("Email");
				} else {
					secondpaymentbox.setVisible(true);
					lblNewLabel_5_1_1.setVisible(true);
					Email.setText("Card Number");
				}
			}
		});
		comboBox_1_1.setBounds(34, 316, 280, 22);
		panel.add(comboBox_1_1);
		comboBox_1_1.addItem("Paypal");
		comboBox_1_1.addItem("Credit Card");

		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(40, 290, 213, 32);
		panel.add(lblPaymentMethod);

		firstpaymentbox = new JTextField();
		firstpaymentbox.setColumns(10);
		firstpaymentbox.setBounds(34, 366, 213, 20);
		panel.add(firstpaymentbox);

		JButton Payment = new JButton("Pay");
		Payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((String) comboBox_1_1.getSelectedItem() == "Paypal") {
					String emailinput = firstpaymentbox.getText().trim();
					if (emailinput.contains("@")) {
						for (int j = 0; j < listofusers.size(); j++) {
							if (listofusers.get(j).getUsername() == username.trim()
									&& listofusers.get(j) instanceof Customer) {
								Customer customer = (Customer) listofusers.get(j);
								String st = "Paypal";
								boolean instock = customer.changequantities(listofstock);
								if (instock == true) {
									customer.pay(st);
								} else {
									JOptionPane.showMessageDialog(null,
											"There is insufficient stock for basket purchase");
								}
								emptybasket(username);
								changeadmintable(listofstock);
								try {
									changeusertable(listofstock);
								} catch (FileNotFoundException e1) {

								}
							} else if (listofusers.get(j).getUsername() == username.trim()) {
								emptybasket(username);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Paypal Email must contain an \"@\"");
					}
				} else {
					String numberinput = firstpaymentbox.getText().trim();
					String numberinput2 = secondpaymentbox.getText().trim();

					boolean cardnisnumber = number(numberinput);
					boolean securitycode = number(numberinput2);

					if (cardnisnumber == false || numberinput.length() != 6) {
						JOptionPane.showMessageDialog(null, "Card Number must be 6 numbers long");
					} else if (securitycode == false || numberinput2.length() != 3) {
						JOptionPane.showMessageDialog(null, "Security code must be 3 numbers long");
					} else {
						for (int j = 0; j < listofusers.size(); j++) {
							if (listofusers.get(j).getUsername() == username.trim()
									&& listofusers.get(j) instanceof Customer) {
								Customer customer = (Customer) listofusers.get(j);
								String st = "Credit Card";
								boolean instock = customer.changequantities(listofstock);
								if (instock == true) {
									customer.pay(st);
								} else {
									JOptionPane.showMessageDialog(null,
											"There is insufficient stock for basket purchase");
								}
								emptybasket(username);
								changeadmintable(listofstock);
								try {
									changeusertable(listofstock);
								} catch (FileNotFoundException e1) {
								}
							} else if (listofusers.get(j).getUsername() == username.trim()) {
								emptybasket(username);
							}
						}
					}
				}
			}
		});
		Payment.setBounds(671, 333, 111, 55);
		panel.add(Payment);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Admin", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Brand");
		lblNewLabel_5.setBounds(10, 18, 46, 14);
		panel_4.add(lblNewLabel_5);

		brandbox = new JTextField();
		brandbox.setColumns(10);
		brandbox.setBounds(10, 35, 213, 20);
		panel_4.add(brandbox);

		barcodebox = new JTextField();
		barcodebox.setColumns(10);
		barcodebox.setBounds(10, 77, 213, 20);
		panel_4.add(barcodebox);

		colourbox = new JTextField();
		colourbox.setColumns(10);
		colourbox.setBounds(10, 122, 213, 20);
		panel_4.add(colourbox);

		connectivitybox = new JTextField();
		connectivitybox.setColumns(10);
		connectivitybox.setBounds(10, 166, 213, 20);
		panel_4.add(connectivitybox);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		scrollPane_2.setBounds(261, 16, 550, 392);
		panel_4.add(scrollPane_2);
		String[] admin = { "barcode", "brand", "colour", "connectivity", "QinS", "Originalcost", "retail price",
				"Numberofbuttons/Layout", "Type" };
		Object[][] emptydata3 = {};
		table_2 = new JTable(0, 9);
		table_2.setRowSelectionAllowed(false);
		table_2.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I" }));

		for (int i = 0; i < admin.length; i++) {
			table_2.getColumnModel().getColumn(i).setHeaderValue(admin[i]);
		}

		changeadmintable(listofstock);
		scrollPane_2.setViewportView(table_2);

		JLabel lblNewLabel_5_1 = new JLabel("Barcode");
		lblNewLabel_5_1.setBounds(11, 60, 46, 14);
		panel_4.add(lblNewLabel_5_1);

		JLabel lblNewLabel_5_2 = new JLabel("Colour");
		lblNewLabel_5_2.setBounds(10, 107, 46, 14);
		panel_4.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_3 = new JLabel("Connectivity");
		lblNewLabel_5_3.setBounds(10, 153, 118, 14);
		panel_4.add(lblNewLabel_5_3);

		JLabel lblNewLabel_5_3_1 = new JLabel("Quantity in Stock");
		lblNewLabel_5_3_1.setBounds(10, 197, 118, 14);
		panel_4.add(lblNewLabel_5_3_1);

		QinSbox = new JTextField();
		QinSbox.setColumns(10);
		QinSbox.setBounds(10, 210, 213, 20);
		panel_4.add(QinSbox);

		originalcostbox = new JTextField();
		originalcostbox.setColumns(10);
		originalcostbox.setBounds(10, 260, 213, 20);
		panel_4.add(originalcostbox);

		retailpricebox = new JTextField();
		retailpricebox.setColumns(10);
		retailpricebox.setBounds(10, 302, 213, 20);
		panel_4.add(retailpricebox);

		JLabel lblNewLabel_5_3_1_1 = new JLabel("Originalcost");
		lblNewLabel_5_3_1_1.setBounds(10, 241, 118, 14);
		panel_4.add(lblNewLabel_5_3_1_1);

		JLabel lblNewLabel_5_3_1_2 = new JLabel("Retailprice");
		lblNewLabel_5_3_1_2.setBounds(10, 287, 118, 14);
		panel_4.add(lblNewLabel_5_3_1_2);

		JComboBox Producttypecombo = new JComboBox();
		Producttypecombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productype = (String) Producttypecombo.getSelectedItem();
			}
		});
		Producttypecombo.setBounds(10, 359, 213, 22);
		panel_4.add(Producttypecombo);

		Producttypecombo.addItem("keyboard");
		Producttypecombo.addItem("mouse");

		productype = (String) Producttypecombo.getSelectedItem();

		JLabel lblProducttype = new JLabel("Product Type");
		lblProducttype.setBounds(10, 333, 213, 32);
		panel_4.add(lblProducttype);

		JLabel lblNewLabel_5_3_1_2_1 = new JLabel("Type of Peripheral");
		lblNewLabel_5_3_1_2_1.setBounds(10, 392, 118, 14);
		panel_4.add(lblNewLabel_5_3_1_2_1);

		typebox = new JTextField();
		typebox.setColumns(10);
		typebox.setBounds(10, 407, 213, 20);
		panel_4.add(typebox);

		Layoutbox = new JTextField();
		Layoutbox.setColumns(10);
		Layoutbox.setBounds(10, 453, 213, 20);
		panel_4.add(Layoutbox);

		JLabel lblNewLabel_5_3_1_2_1_1 = new JLabel("Layout/Number of Buttons");
		lblNewLabel_5_3_1_2_1_1.setBounds(10, 438, 213, 14);
		panel_4.add(lblNewLabel_5_3_1_2_1_1);

		JButton Addproduct = new JButton("Add Product");
		Addproduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < listofusers.size(); j++) {
					username = (String) comboBox.getSelectedItem();
					if (username.trim() == listofusers.get(j).getUsername().trim()) {
						Users theuser = listofusers.get(j);
						if (theuser instanceof Admin) {
							Admin tempadmin = (Admin) theuser;
							Long barcode = Long.parseLong(barcodebox.getText());
							String devicename = productype;// = testField.getText();
							String devicetype = typebox.getText();// = testField.getText();
							String brand = brandbox.getText();
							String colour = colourbox.getText();
							String connectivity = connectivitybox.getText();
							String qinstock = QinSbox.getText();
							double originalcost = Double.parseDouble(originalcostbox.getText());
							double retailprice = Double.parseDouble(retailpricebox.getText());
							String thelayout = Layoutbox.getText();// ;
							if (productype.trim() == "keyboard") {
								tempadmin.addkeyboard(listofstock, barcode, devicename, devicetype, brand, colour,
										connectivity, qinstock, originalcost, retailprice, thelayout);
							} else {
								tempadmin.addmouse(listofstock, barcode, devicename, devicetype, brand, colour,
										connectivity, qinstock, originalcost, retailprice, Integer.parseInt(thelayout));
							}
							int i = listofstock.size() - 1;
							comboBox_1.addItem(
									listofstock.get(i).getBarcode() + " , " + listofstock.get(i).getDevicename() + " , "
											+ listofstock.get(i).getBrand() + " , " + listofstock.get(i).getColour());
							changeadmintable(listofstock);
							try {
								changeusertable(listofstock);
							} catch (FileNotFoundException e1) {
							}
						}
					}
				}
			}
		});
		Addproduct.setBounds(237, 419, 132, 54);
		panel_4.add(Addproduct);
	}

	public static List<Users> useraccountfile() throws FileNotFoundException {
		List<Users> listofusers = new ArrayList<Users>();
		File inputFile = new File("src/Useraccounts");
		Scanner fileScanner = new Scanner(inputFile);
		Customer thecustomer = null;
		Admin theadmin = null;
		Address ad = null;
		while (fileScanner.hasNextLine()) {
			String[] details = fileScanner.nextLine().split(",");
			ad = new Address(Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim());
			if (details[6].trim().equals("customer")) {
				thecustomer = new Customer(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
						ad, details[6].trim());
				listofusers.add(thecustomer);
			} else {
				theadmin = new Admin(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(), ad,
						details[6].trim());
				listofusers.add(theadmin);
			}
		}

		fileScanner.close();
		return listofusers;
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

	public static List<Product> sortbyretailprice(List<Product> prodlist) {
		Product temp;
		for (int i = 0; i < prodlist.size(); i++) {
			for (int j = 0; j < prodlist.size() - i - 1; j++) {
				if (prodlist.get(j).getRetailprice() > prodlist.get(j + 1).getRetailprice()) {
					temp = prodlist.get(j);
					prodlist.set(j, prodlist.get(j + 1));
					prodlist.set(j + 1, temp);
				}
			}

		}
		return prodlist;
	}

	public static boolean number(String theinput) {
		int theint;
		try {
			theint = Integer.parseInt(theinput);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public static void writetofile() throws FileNotFoundException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("src/Stock", false));
			// false here means that we don't want to append data to the content of the
			// file.
			// If we need to append, then we should use true.
			// When we don't pass the second parameter (false/true), by default it would be
			// false.
			for (Product accessory : listofstock) {
				if (accessory instanceof Mouse) {
					Mouse mouse = (Mouse) accessory;
					bw.write(mouse.toString() + "\n");
				} else if (accessory instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) accessory;
					bw.write(keyboard.toString() + "\n");
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}