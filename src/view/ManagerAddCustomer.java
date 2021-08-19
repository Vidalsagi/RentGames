package view;


import controller.ManagerAddCustomerController;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.awt.SystemColor;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.EventQueue;

public class ManagerAddCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField UserNameField;
	public JTextField PasswordField;
	public JTextField CityField;
	public JTextField LastNameField;
	public JTextField AgeField;
	public JTextField FirstNameField;
	public JTextField AddressField;
	private ManagerAddCustomerController managerAddCustomerController;
	public JButton btnAdd;
	/**
	 * Launch the application (Manager Add Customer)
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerAddCustomer frame = new ManagerAddCustomer();
					frame.setVisible(true);
					 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JLabel imgLogin;
	
	/**
	 * Create the frame.
	 */
	
	public ManagerAddCustomer() {
		managerAddCustomerController = new ManagerAddCustomerController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 454, 461);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(72, 15, 105, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(Color.BLACK);
		lblPassword.setOpaque(true);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblPassword.setBounds(72, 54, 105, 28);
		contentPane.add(lblPassword);
		
		JLabel lblAshdod = new JLabel("City");
		lblAshdod.setBackground(Color.BLACK);
		lblAshdod.setOpaque(true);
		lblAshdod.setForeground(Color.WHITE);
		lblAshdod.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblAshdod.setBounds(72, 92, 43, 28);
		contentPane.add(lblAshdod);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBackground(Color.BLACK);
		lblLastName.setOpaque(true);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblLastName.setBounds(72, 210, 96, 28);
		contentPane.add(lblLastName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setOpaque(true);
		lblAge.setBackground(Color.BLACK);
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblAge.setBounds(72, 249, 43, 28);
		contentPane.add(lblAge);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBackground(Color.BLACK);
		lblFirstName.setOpaque(true);
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblFirstName.setBounds(72, 171, 105, 28);
		contentPane.add(lblFirstName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBackground(Color.BLACK);
		lblAddress.setOpaque(true);
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Cooper Black", Font.PLAIN, 17));
		lblAddress.setBounds(72, 132, 81, 28);
		contentPane.add(lblAddress);
		
		UserNameField = new JTextField();
		UserNameField.setBounds(195, 18, 176, 26);
		contentPane.add(UserNameField);
		UserNameField.setColumns(10);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(195, 57, 176, 26);
		contentPane.add(PasswordField);
		
		CityField = new JTextField();
		CityField.setColumns(10);
		CityField.setBounds(195, 95, 176, 26);
		contentPane.add(CityField);
		
		LastNameField = new JTextField();
		LastNameField.setColumns(10);
		LastNameField.setBounds(195, 213, 176, 26);
		contentPane.add(LastNameField);
		
		AgeField = new JTextField();
		AgeField.setColumns(10);
		AgeField.setBounds(195, 252, 176, 26);
		contentPane.add(AgeField);
		
		FirstNameField = new JTextField();
		FirstNameField.setColumns(10);
		FirstNameField.setBounds(195, 174, 176, 26);
		contentPane.add(FirstNameField);
		
		AddressField = new JTextField();
		AddressField.setColumns(10);
		AddressField.setBounds(195, 135, 176, 26);
		contentPane.add(AddressField);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Cooper Black", Font.PLAIN, 36));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				managerAddCustomerController.AddCustomer(UserNameField.getText(), PasswordField.getText(), 
				CityField.getText(), LastNameField.getText(), AgeField.getText(), FirstNameField.getText(), AddressField.getText());
				JOptionPane.showMessageDialog(null, "Add Succesfully");
				System.out.print("Add Succesfully");				
				}catch(SQLException e) {
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnAdd.setBounds(205, 334, 135, 60);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		contentPane.add(btnAdd);
		
		imgLogin = new JLabel("");
		imgLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		imgLogin.setLocation(new Point(50, 50));
		imgLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLogin.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		imgLogin.setAlignmentX(0.5f);
		imgLogin.setBounds(-232, -20, 1472, 549);
		imgLogin.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		contentPane.add(imgLogin);
		

	}
}
