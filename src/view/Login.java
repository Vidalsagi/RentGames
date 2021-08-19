package view;

import controller.LoginController;
import model.Customer;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Login {
	public JRadioButton rdbtnManager;
	JRadioButton rdbtnCustomer;
	public JFrame frame;
	public JTextField userField;
	public JPasswordField passField;
	private LoginController loginController;
	public JButton loginBottom;
	
	/**
	 * Create the application.
	 */
	
	public Login() {
		loginController = new LoginController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {

		frame = new JFrame("Rent Game");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		frame.setLocation(new Point(50, 50));
		frame.setBounds(100, 100, 685, 505);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel user = new JLabel("Username");
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		user.setBounds(324, 224, 155, 28);
		frame.getContentPane().add(user);
		
		JLabel user_1 = new JLabel("Password");
		user_1.setForeground(Color.WHITE);
		user_1.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		user_1.setBounds(324, 268, 165, 28);
		frame.getContentPane().add(user_1);
		
		userField = new JTextField();
		userField.setForeground(UIManager.getColor("TextPane.caretForeground"));
		userField.setBounds(455, 215, 200, 40);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		loginBottom = new JButton("Login");
		loginBottom.setForeground(Color.WHITE);
		loginBottom.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		loginBottom.setBackground(Color.BLACK);
		loginBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("deprecation")
					int result = loginController.login(userField.getText(), passField.getText(), rdbtnManager.isSelected());
					JOptionPane.showMessageDialog(null, "UserName & Password is Correct");
					frame.dispose();
					switch (result)
					{
					case 1:
						System.out.println("Logged in as Manager");
						new ManagerMenu().setVisible(true);
						break;
					 default:
						System.out.println("Logged in as Customer");
						new CustomerMenu(new Customer(result)).setVisible(true);
					}
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					}
			});
		loginBottom.setBounds(387, 349, 174, 46);
		loginBottom.setOpaque(false);
		loginBottom.setContentAreaFilled(false);
		loginBottom.setBorderPainted(false);
		frame.getContentPane().add(loginBottom);
		
		passField = new JPasswordField();
		passField.setBounds(455, 271, 200, 40);
		frame.getContentPane().add(passField);
		
		
		JButton ExitBottom = new JButton("Exit");
		ExitBottom.setBackground(Color.BLACK);
		ExitBottom.setForeground(Color.WHITE);
		ExitBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		ExitBottom.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		ExitBottom.setBounds(387, 412, 174, 46);
		ExitBottom.setOpaque(false);
		ExitBottom.setContentAreaFilled(false);
		ExitBottom.setBorderPainted(false);
		frame.getContentPane().add(ExitBottom);
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBackground(Color.BLACK);
		rdbtnManager.setForeground(SystemColor.window);
		rdbtnManager.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		rdbtnManager.setBounds(438, 89, 110, 40);
		frame.getContentPane().add(rdbtnManager);
		
		rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setForeground(SystemColor.window);
		rdbtnCustomer.setBorder(new EmptyBorder(0, 0, 0, 0));
		rdbtnCustomer.setBackground(Color.BLACK);
		rdbtnCustomer.setSelected(true);
		rdbtnCustomer.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		rdbtnCustomer.setBounds(294, 89, 110, 40);
		frame.getContentPane().add(rdbtnCustomer);
		
		ButtonGroup bg1 = new ButtonGroup( ); //Group radio buttons
		bg1.add(rdbtnCustomer);
		bg1.add(rdbtnManager);
		
		JLabel imgLogin = new JLabel("");
		imgLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		imgLogin.setLocation(new Point(50, 50));
		imgLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		imgLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		imgLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		imgLogin.setIcon(new ImageIcon(Login.class.getResource("/RentGameLogin.png")));
		imgLogin.setBounds(-275, 0, 1472, 549);
		frame.getContentPane().add(imgLogin, BorderLayout.CENTER);


	}
}
