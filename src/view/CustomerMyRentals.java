package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.CustomerMyRentalsController;
import model.Customer;
import model.PanelService;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;

public class CustomerMyRentals extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public static JTable table;
	public Customer cust=null;
	private JLabel imgLogin;
	private CustomerMyRentalsController customerMyRentalsController;


	/**
	 * Launch the application (My Renting menu).
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMyRentals frame = new CustomerMyRentals(new Customer(2));
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
	
	public CustomerMyRentals(Customer cust) {
		super("RentGame");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		customerMyRentalsController =  new CustomerMyRentalsController(cust);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 452);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		scrollPane.setBounds(27, 27, 568, 277);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.controlHighlight);
		table.addMouseListener(new MouseAdapter() { //Selected mouse clicked 
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		customerMyRentalsController.selectedOnTableC();
	    }});
		scrollPane.setViewportView(table);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.BLACK);
		btnReturn.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		btnReturn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				customerMyRentalsController.returnFuncC();
				JOptionPane.showMessageDialog(null, "succesfully returned");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		});
		contentPane.add(btnReturn);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.BLACK);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		btnExit.setBounds(326, 373, 115, 29);
		contentPane.add(btnExit);
		
		imgLogin = new JLabel("");
		imgLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		imgLogin.setLocation(new Point(50, 50));
		imgLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		imgLogin.setAlignmentX(0.5f);
		imgLogin.setBounds(-145, -81, 900, 724);
		contentPane.add(imgLogin);
		btnReturn.setBounds(153, 373, 161, 29);
		imgLogin.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		PanelService.updatePanel(1, table, cust.customer.getID());

	}
}
