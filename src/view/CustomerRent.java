package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.CustomerRentController;
import model.Customer;
import model.SqliteConnection;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class CustomerRent extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Customer cust=null;
	public JList<String> inStore;
	public JList<String> ToRent;
	public JPanel contentPane;
	public JDateChooser dateChooserFrom;
	public JDateChooser dateChooserUntill;
	public  DefaultListModel<String> model1;
	public  DefaultListModel<String> model2;
	private CustomerRentController customerRentController;


	
	/**
	 * Launch the application (Customer Rent Menu)
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRent frame = new CustomerRent(new Customer(2));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	SqliteConnection singelton= SqliteConnection.getInstance();
	Connection connection  = singelton.dbConnector();
	
	/**
	 * Create the frame.
	 */
	
	public CustomerRent(Customer cust) {
		super("RentGame");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		customerRentController =  new CustomerRentController();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 462);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(0, 43, 191, 347);
		 contentPane.add(scrollPane_1);
		
		 inStore = new JList<String>();
		 inStore.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		 inStore.setBorder(new LineBorder(SystemColor.textHighlight));
		 inStore.setBackground(SystemColor.text);
		 scrollPane_1.setViewportView(inStore);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(424, 43, 191, 347);
		 contentPane.add(scrollPane);
		
		 ToRent = new JList<String>();
		 ToRent.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		 ToRent.setBorder(new LineBorder(SystemColor.textHighlight));
		 ToRent.setBackground(SystemColor.text);
		 scrollPane.setViewportView(ToRent);
		
		 model1 = new DefaultListModel<String>();
		 addelements(model1);
		
		 model2 = new DefaultListModel<String>();
		
		
		JLabel lblInStore = new JLabel("In Store");
		lblInStore.setOpaque(true);
		lblInStore.setForeground(Color.WHITE);
		lblInStore.setBackground(Color.BLACK);
		lblInStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblInStore.setFont(new Font("Cooper Black", lblInStore.getFont().getStyle(), 23));
		lblInStore.setBounds(37, 16, 109, 20);
		contentPane.add(lblInStore);
		
		JLabel lblToRent = new JLabel("To Rent");
		lblToRent.setBackground(Color.BLACK);
		lblToRent.setOpaque(true);
		lblToRent.setForeground(Color.WHITE);
		lblToRent.setHorizontalAlignment(SwingConstants.CENTER);
		lblToRent.setFont(new Font("Cooper Black", lblToRent.getFont().getStyle(), 23));
		
		lblToRent.setBounds(465, 16, 122, 20);
		contentPane.add(lblToRent);
		
		JButton button = new JButton(">>");
		button.setBorder(new LineBorder(SystemColor.text));
		button.setBackground(Color.BLACK);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		button.setActionCommand(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerRentController.moveRightC(model1, model2, inStore, ToRent);
			}
		});
		button.setBounds(190, 43, 50, 39);
		contentPane.add(button);
		
		JButton button_1 = new JButton("<<");
		button_1.setBorder(new LineBorder(SystemColor.text));
		button_1.setBackground(Color.BLACK);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		button_1.setActionCommand("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerRentController.moveLeft(model1, model2, inStore, ToRent);
			}
		});
		button_1.setBounds(375, 43, 50, 39);
		contentPane.add(button_1);
		
		JButton btnRent = new JButton("Rent");
		btnRent.setBackground(Color.BLACK);
		btnRent.setForeground(Color.BLACK);
		btnRent.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					customerRentController.rentFuncC(inStore, ToRent, contentPane, cust, dateChooserFrom, dateChooserUntill);
					dispose();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnRent.setBounds(220, 319, 159, 29);
		contentPane.add(btnRent);

		
		JLabel lblTillWhen = new JLabel("Untill:");
		lblTillWhen.setBackground(Color.BLACK);
		lblTillWhen.setOpaque(true);
		lblTillWhen.setForeground(Color.WHITE);
		lblTillWhen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTillWhen.setFont(new Font("Cooper Black", lblTillWhen.getFont().getStyle(), 20));
		lblTillWhen.setBounds(249, 177, 115, 20);
		contentPane.add(lblTillWhen);
		
		dateChooserUntill = new JDateChooser();
		dateChooserUntill.setBackground(SystemColor.menu);
		dateChooserUntill.setDateFormatString("MMM,d,yyyy");
		dateChooserUntill.setBounds(249, 200, 115, 26);
		contentPane.add(dateChooserUntill);
		
		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBackground(SystemColor.menu);
		dateChooserFrom.setDateFormatString("MMM,d,yyyy");
		dateChooserFrom.setBounds(249, 118, 115, 26);
		contentPane.add(dateChooserFrom);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBackground(Color.BLACK);
		lblFrom.setOpaque(true);
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Cooper Black", lblFrom.getFont().getStyle(), 20));
		lblFrom.setBounds(249, 95, 109, 20);
		contentPane.add(lblFrom);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBounds(220, 361, 159, 29);
		btnExit.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		contentPane.add(btnExit);
		
		JLabel imgLogin = new JLabel("");
		imgLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		imgLogin.setLocation(new Point(50, 50));
		imgLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		imgLogin.setAlignmentX(0.5f);
		imgLogin.setBounds(-148, -150, 900, 724);
		imgLogin.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		contentPane.add(imgLogin);
		

	}
	
	private void addelements(DefaultListModel<String> model) {
		try { 
			String query = "select DISTINCT Name from Games join GamesStock using(GameID) where IsRented= 'false'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{ 
				model.addElement(rs.getString("Name"));
			}
			inStore.setModel(model);
			pst.close();
			rs.close();
		}
		catch (Exception e) 
		 { 
			 e.printStackTrace(); 
		 }	
	}
}
