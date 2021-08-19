package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Customer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Toolkit;

public class CustomerMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Customer cust=null;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField cityField;
	private JTextField addressField;
	private JTextField ageField;


	/**
	 * Launch the application (in Customer menu)
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenu frame = new CustomerMenu(new Customer(2)); //2 = Shay in DB
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
	public CustomerMenu(Customer customer) {
		super("RentGame");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		//initialize the controller
		cust=customer;
		System.out.println("welcome "+ cust.customer.getFirstName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 466);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JMenuItem mntmExit = new JMenuItem("Exit"); //exit
		mntmExit.setForeground(Color.BLACK);
		mntmExit.setBackground(Color.WHITE);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Menu");
		mnFile.setBackground(Color.WHITE);
		mnFile.setForeground(Color.BLACK);
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setForeground(Color.BLACK);
		mntmLogout.setBackground(Color.WHITE);
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login().frame.setVisible(true);
				}
		});
		mnFile.add(mntmLogout);
		mnFile.add(mntmExit);
		
		JButton btnRent = new JButton("Rent");
		btnRent.setBackground(Color.BLACK);
		btnRent.setForeground(Color.BLACK);
		btnRent.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		btnRent.setActionCommand("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CustomerRent(cust).setVisible(true);
				}
		});
		btnRent.setBounds(398, 89, 185, 95);
		contentPane.add(btnRent);

		JButton btnRented = new JButton("My Rentals");
		btnRented.setDefaultCapable(false);
		btnRented.setBackground(Color.BLACK);
		btnRented.setForeground(Color.BLACK);
		btnRented.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		btnRented.setActionCommand("My Renting");
		btnRented.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CustomerMyRentals(cust).setVisible(true);
				}
		});
		btnRented.setBounds(398, 267, 185, 95);
		contentPane.add(btnRented);
		
		// Information about the customer
		cust = new Customer(cust.customer.getID());
		JLabel lblConnectedAs = new JLabel("Connected as: " + cust.customer.getUsername());
		lblConnectedAs.setBackground(Color.BLACK);
		lblConnectedAs.setOpaque(true);
		lblConnectedAs.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblConnectedAs.setForeground(Color.WHITE);
		lblConnectedAs.setBounds(20, 9, 311, 71);
		contentPane.add(lblConnectedAs);
		
		JLabel lblFirstName = new JLabel("FirstName:");
		lblFirstName.setBackground(Color.BLACK);
		lblFirstName.setOpaque(true);
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstName.setBounds(20, 89, 113, 20);
		contentPane.add(lblFirstName);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		firstNameField.setHorizontalAlignment(SwingConstants.LEFT);
		firstNameField.setForeground(Color.WHITE);
		firstNameField.setText(cust.customer.getFirstName());
		firstNameField.setEditable(false);
		firstNameField.setColumns(10);
		firstNameField.setBackground(Color.BLACK);
		firstNameField.setBounds(138, 87, 146, 26);
		contentPane.add(firstNameField);
		
		JLabel lblLastName = new JLabel("LastName:");
		lblLastName.setBackground(Color.BLACK);
		lblLastName.setOpaque(true);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLastName.setBounds(20, 141, 113, 20);
		contentPane.add(lblLastName);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		lastNameField.setForeground(Color.WHITE);
		lastNameField.setText(cust.customer.getLastName());
		lastNameField.setEditable(false);
		lastNameField.setColumns(10);
		lastNameField.setBackground(Color.BLACK);
		lastNameField.setBounds(138, 141, 146, 26);
		contentPane.add(lastNameField);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBackground(Color.BLACK);
		lblCity.setOpaque(true);
		lblCity.setForeground(Color.WHITE);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCity.setBounds(20, 198, 53, 20);
		contentPane.add(lblCity);
		
		cityField = new JTextField();
		cityField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		cityField.setForeground(Color.WHITE);
		cityField.setText(cust.customer.getCity());
		cityField.setEditable(false);
		cityField.setColumns(10);
		cityField.setBackground(Color.BLACK);
		cityField.setBounds(138, 198, 146, 26);
		contentPane.add(cityField);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBackground(Color.BLACK);
		lblAddress.setOpaque(true);
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(20, 258, 94, 20);
		contentPane.add(lblAddress);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		addressField.setForeground(Color.WHITE);
		addressField.setText(cust.customer.getAddress());
		addressField.setEditable(false);
		addressField.setColumns(10);
		addressField.setBackground(Color.BLACK);
		addressField.setBounds(138, 256, 146, 26);
		contentPane.add(addressField);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBackground(Color.BLACK);
		lblAge.setOpaque(true);
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAge.setBounds(20, 315, 53, 20);
		contentPane.add(lblAge);
		
		ageField = new JTextField();
		ageField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		ageField.setForeground(Color.WHITE);
		ageField.setEditable(false);
		ageField.setText(String.valueOf(cust.customer.getAge()));
		ageField.setColumns(10);
		ageField.setBackground(Color.BLACK);
		ageField.setBounds(138, 313, 146, 26);
		contentPane.add(ageField);
		
		
		JLabel imgLogin_1 = new JLabel("");
		imgLogin_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgLogin_1.setVerticalAlignment(SwingConstants.BOTTOM);
		imgLogin_1.setLocation(new Point(50, 50));
		imgLogin_1.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		imgLogin_1.setAlignmentX(0.5f);
		imgLogin_1.setBounds(-15, -180, 900, 724);
		imgLogin_1.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		contentPane.add(imgLogin_1);
	}
}
