package view;

import java.sql.*;

import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import controller.ManagerMenuController;
import controller.ManagerRentController;
import model.PanelService;
import model.SqliteConnection;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;

public class ManagerMenu extends JFrame {

	public JTextField GameIDField;
	public JTextField CompanyField;
	public JTextField ReleaseYearField;
	public JTextField CategoryField;
	public JTextField NameField;
	public JTextField PlatformField;
	public JTextField MinAgeReqField;
	public JTextField CostField;
	public JPanel contentPane;
	public JTextField txtSearch;
	public static JTable table;
	public JComboBox<?> Search_comboBox;
	
	/**
	 * panel 2
	 */
	
	public JTextField GameIDP2Field;
	public int GameID_val;
	public JComboBox<String> Search_comboBox2;
	public JComboBox<String> Search_comboBox2_1;
	public static JTable table2;
	public String GamesStockID_;
	private ManagerRentController managerRentController;
	private ManagerMenuController managerMenuController;
	String test;

	/**
	 * panel 3
	 */
	
	public static JTable table3;
	public JTextField txtSearchbox;
	public int gameCopy_table3=0;
	
	public void fillComboBox(String str) { //Reload Games from DB to search box
	try { 
		Search_comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		GameIDP2Field.setText(null);
		String query = "select * from Games where Platform=?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1,str);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{ 
			Search_comboBox2.addItem(rs.getString("Name"));
		}
		pst.close();
		rs.close();
	}
	catch (Exception e) 
	 { 
		 e.printStackTrace(); 
	 }
	}
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerMenu frame = new ManagerMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static Connection connection=null;	
	
	public ManagerMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/IconRentGames.jpg")));
		managerMenuController = new ManagerMenuController();
		managerRentController = new ManagerRentController();
		SqliteConnection singelton= SqliteConnection.getInstance();
		connection  = singelton.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1140, 534);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Menu");
		mnFile.setBackground(Color.WHITE);
		mnFile.setForeground(Color.BLACK);
		menuBar.add(mnFile);
		
		JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
		mntmAddCustomer.setBackground(SystemColor.desktop);
		mntmAddCustomer.setForeground(Color.BLACK);
		mntmAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ManagerAddCustomer().setVisible(true);
			}});
		mnFile.add(mntmAddCustomer);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(SystemColor.desktop);
		mntmExit.setForeground(Color.BLACK);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setBackground(SystemColor.desktop);
		mntmLogout.setForeground(Color.BLACK);
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login().frame.setVisible(true);
				}
		});
		mnFile.add(mntmLogout);
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1128, 459);
		contentPane.add(tabbedPane);
	    
	    Panel panel = new Panel();
	    panel.setBackground(SystemColor.activeCaption);
	    tabbedPane.addTab("Games List", null, panel, null);
	    tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 16));
	    panel.setLayout(null);
	    
	    JScrollPane GameExistPane = new JScrollPane();
	    GameExistPane.setBounds(0, 0, 709, 424);
	    panel.add(GameExistPane);
	    
	    table = new JTable();
	    table.addMouseListener(new MouseAdapter() { //Selected mouse clicked and fill all in panel 1
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		managerMenuController.mouseClicked1(GameIDField, NameField, CategoryField, ReleaseYearField, PlatformField, CompanyField, MinAgeReqField, CostField);
	    }});
	    GameExistPane.setViewportView(table);
	    
	    JButton SaveButton = new JButton("Save");
	    SaveButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    SaveButton.setForeground(new Color(255, 255, 255));
	    SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.savePanel1(NameField.getText(), CategoryField.getText(),
							ReleaseYearField.getText(), PlatformField.getText(), CompanyField.getText(),
							MinAgeReqField.getText(), CostField.getText());
					JOptionPane.showMessageDialog(null, "Data Saved");
					} catch (Exception e) {
					System.err.println(e);
					}
				}
			});
	    SaveButton.setBounds(990, 386, 115, 29);
	    SaveButton.setOpaque(false);
	    SaveButton.setContentAreaFilled(false);
	    panel.add(SaveButton);
	    
	    JLabel lblGameID = new JLabel("GameID");
	    lblGameID.setOpaque(true);
	    lblGameID.setBackground(new Color(0, 0, 0));
	    lblGameID.setForeground(Color.WHITE);
	    lblGameID.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblGameID.setBounds(780, 67, 72, 20);
	    panel.add(lblGameID);
	    
	    GameIDField = new JTextField();
	    GameIDField.setEditable(false);
	    GameIDField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    GameIDField.setBounds(958, 65, 146, 26);
	    panel.add(GameIDField);
	    GameIDField.setColumns(10);
	    
	    JLabel lblCompany = new JLabel("Comapny");
	    lblCompany.setOpaque(true);
	    lblCompany.setBackground(new Color(0, 0, 0));
	    lblCompany.setForeground(Color.WHITE);
	    lblCompany.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblCompany.setBounds(780, 242, 95, 20);
	    panel.add(lblCompany);
	    
	    CompanyField = new JTextField();
	    CompanyField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    CompanyField.setColumns(10);
	    CompanyField.setBounds(958, 238, 146, 26);
	    panel.add(CompanyField);
	    
	    JLabel lblReleaseYear = new JLabel("Release Year");
	    lblReleaseYear.setOpaque(true);
	    lblReleaseYear.setBackground(new Color(0, 0, 0));
	    lblReleaseYear.setForeground(Color.WHITE);
	    lblReleaseYear.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblReleaseYear.setBounds(780, 170, 127, 20);
	    panel.add(lblReleaseYear);
	    
	    ReleaseYearField = new JTextField();
	    ReleaseYearField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    ReleaseYearField.setColumns(10);
	    ReleaseYearField.setBounds(959, 168, 146, 26);
	    panel.add(ReleaseYearField);
	    
	    CategoryField = new JTextField();
	    CategoryField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    CategoryField.setColumns(10);
	    CategoryField.setBounds(959, 134, 146, 26);
	    panel.add(CategoryField);
	    
	    NameField = new JTextField();
	    NameField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    NameField.setColumns(10);
	    NameField.setBounds(959, 99, 146, 26);
	    panel.add(NameField);
	    
	    PlatformField = new JTextField();
	    PlatformField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    PlatformField.setColumns(10);
	    PlatformField.setBounds(960, 202, 146, 26);
	    panel.add(PlatformField);
	    
	    MinAgeReqField = new JTextField();
	    MinAgeReqField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    MinAgeReqField.setColumns(10);
	    MinAgeReqField.setBounds(958, 273, 146, 26);
	    panel.add(MinAgeReqField);
	    
	    JLabel lblMinAgeReq = new JLabel("Min Age Required");
	    lblMinAgeReq.setOpaque(true);
	    lblMinAgeReq.setBackground(new Color(0, 0, 0));
	    lblMinAgeReq.setForeground(Color.WHITE);
	    lblMinAgeReq.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblMinAgeReq.setBounds(780, 276, 173, 20);
	    panel.add(lblMinAgeReq);
	    
	    JLabel lblPlatform = new JLabel("Platform");
	    lblPlatform.setOpaque(true);
	    lblPlatform.setBackground(new Color(0, 0, 0));
	    lblPlatform.setForeground(Color.WHITE);
	    lblPlatform.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblPlatform.setBounds(780, 205, 95, 20);
	    panel.add(lblPlatform);
	    
	    JLabel lblCategory = new JLabel("Category");
	    lblCategory.setOpaque(true);
	    lblCategory.setBackground(new Color(0, 0, 0));
	    lblCategory.setForeground(Color.WHITE);
	    lblCategory.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblCategory.setBounds(780, 137, 83, 20);
	    panel.add(lblCategory);
	    
	    JLabel lblName = new JLabel("Name");
	    lblName.setOpaque(true);
	    lblName.setBackground(new Color(0, 0, 0));
	    lblName.setForeground(Color.WHITE);
	    lblName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblName.setBounds(780, 102, 61, 20);
	    panel.add(lblName);
	    
	    CostField = new JTextField();
	    CostField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    CostField.setColumns(10);
	    CostField.setBounds(958, 310, 146, 26);
	    panel.add(CostField);
	    
	    JLabel lblCost = new JLabel("Cost");
	    lblCost.setOpaque(true);
	    lblCost.setBackground(new Color(0, 0, 0));
	    lblCost.setForeground(Color.WHITE);
	    lblCost.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblCost.setBounds(780, 313, 47, 20);
	    panel.add(lblCost);
	    
	    JButton UpdateButton = new JButton("Update");
	    UpdateButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    UpdateButton.setForeground(new Color(255, 255, 255));
	    UpdateButton.addActionListener(new ActionListener() { //Update Game
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.updateDate(GameIDField, NameField, CategoryField, ReleaseYearField, PlatformField, CompanyField, MinAgeReqField, CostField);
					JOptionPane.showMessageDialog(null, "Data Update");
					} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Eror Please select Game");
					}
				}
			});
	    UpdateButton.setBounds(865, 386, 115, 29);
	    UpdateButton.setOpaque(false);
	    UpdateButton.setContentAreaFilled(false);
	    panel.add(UpdateButton);
	    
	    JButton DeleteButton = new JButton("Delete");
	    DeleteButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    DeleteButton.setForeground(new Color(255, 255, 255));
	    DeleteButton.addActionListener(new ActionListener() { //Delete Game
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.deletePanel1(GameIDField.getText());
					JOptionPane.showMessageDialog(null, "Data Deleted");
					} catch (Exception e) {
					System.err.println(e);
					}
				}
			});
	    DeleteButton.setBounds(735, 386, 115, 29);
	    DeleteButton.setOpaque(false);
	    DeleteButton.setContentAreaFilled(false);

	    panel.add(DeleteButton);
	    
	    Search_comboBox = new JComboBox();
	    Search_comboBox.setFont(new Font("Cooper Black", Font.PLAIN, 16));
	    Search_comboBox.setBounds(771, 16, 141, 26);
	    panel.add(Search_comboBox);
	    Search_comboBox.setOpaque(false);
	    Search_comboBox.setModel(new DefaultComboBoxModel(new String[] {"GameID", "Name", "Category", "ReleaseYear", "Platform", "Company", "MinAgeReq", "Cost"}));
	    
	    txtSearch = new JTextField();
	    txtSearch.setFont(new Font("Cooper Black", Font.PLAIN, 16));
	    txtSearch.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		txtSearch.setText("");
	    	}
	    });
	    
	    txtSearch.setText("Search");
	    txtSearch.setBounds(958, 16, 146, 26);
	    panel.add(txtSearch);
	    txtSearch.addKeyListener(new KeyAdapter() { //Search on panel 1
			@Override
			public void keyReleased(KeyEvent d)
			{	
				managerMenuController.searchP1(txtSearch.getText(),(String) Search_comboBox.getSelectedItem());
			}
	 });
	    txtSearch.setColumns(10);
		 
	  	Panel panel2 = new Panel();
	  	panel2.setBackground(SystemColor.activeCaption);
	  	tabbedPane.addTab("In Store", null, panel2, null);
	  	panel2.setLayout(null);
	  		
	  			  		
	  	Search_comboBox2 = new JComboBox<String>();
	  	Search_comboBox2.setBackground(SystemColor.text);
	  	Search_comboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {" "}));
	  	Search_comboBox2.setActionCommand("Combobox pane 2");
	  	Search_comboBox2.setBounds(958, 167, 146, 26);
	  	Search_comboBox2.addActionListener(new ActionListener() { //Set GameID to add store in panel 2 
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.getGameIdByomboboxpane2((String)Search_comboBox2.getSelectedItem(),(String)Search_comboBox2_1.getSelectedItem(),GameIDP2Field);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					}
			});
	  	panel2.add(Search_comboBox2);
	  		
		Search_comboBox2_1 = new JComboBox<String>();
		Search_comboBox2_1.setBackground(SystemColor.text);
		Search_comboBox2_1.setToolTipText("");
		Search_comboBox2_1.setActionCommand("Combobox pane 2_1");
		Search_comboBox2_1.setBounds(958, 130, 146, 26);
		Search_comboBox2_1.setModel(new DefaultComboBoxModel<String>(new String[] {"","PC", "PS4", "XBOX"}));
		Search_comboBox2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					fillComboBox(test = (String) Search_comboBox2_1.getSelectedItem());
			}});
	    panel2.add(Search_comboBox2_1);
	  		
	  	JButton AddButton = new JButton("Add");
	  	AddButton.setForeground(new Color(255, 255, 255));
	  	AddButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
	  	AddButton.setActionCommand("AddButton2");
	    AddButton.setBounds(754, 265, 146, 29);
		AddButton.addActionListener(new ActionListener() { //Actually add game to store
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.addButtonP2(GameIDP2Field.getText());
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					}
			});
		AddButton.setOpaque(false);
		AddButton.setContentAreaFilled(false);
		AddButton.setBorderPainted(false);
 		panel2.add(AddButton);
	  		 		
	    JLabel lblNameP2 = new JLabel("Name");
	    lblNameP2.setBackground(Color.BLACK);
	    lblNameP2.setOpaque(true);
	    lblNameP2.setForeground(new Color(255, 255, 255));
	    lblNameP2.setBounds(806, 169, 58, 20);
	  	lblNameP2.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    panel2.add(lblNameP2);
	  		 		
        JButton RemoveButton = new JButton("Remove");
        RemoveButton.setForeground(new Color(255, 255, 255));
        RemoveButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
	  	RemoveButton.setActionCommand("RemoveButton2");
	  	RemoveButton.setBounds(916, 265, 165, 29);
        RemoveButton.addActionListener(new ActionListener() { //Remove game from store
			public void actionPerformed(ActionEvent arg0) {
				try {
					managerMenuController.removeP2(GamesStockID_, GameIDP2Field.getText());
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					}
			});
        RemoveButton.setOpaque(false);
        RemoveButton.setContentAreaFilled(false);
        RemoveButton.setBorderPainted(false);
        panel2.add(RemoveButton);
	  		 		  
        JLabel lblGameIDP2 = new JLabel("GameID");
        lblGameIDP2.setBackground(Color.BLACK);
        lblGameIDP2.setOpaque(true);
        lblGameIDP2.setForeground(new Color(255, 255, 255));
        lblGameIDP2.setBounds(806, 93, 87, 20);
        lblGameIDP2.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        panel2.add(lblGameIDP2);
	  		 		
        GameIDP2Field = new JTextField();
        GameIDP2Field.setBackground(SystemColor.text);
        GameIDP2Field.setBounds(958, 89, 146, 26);
        GameIDP2Field.setFont(new Font("Cooper Black", Font.PLAIN, 18));
        GameIDP2Field.setEditable(false);
        GameIDP2Field.setColumns(10);
        panel2.add(GameIDP2Field);
	  		 		
        JScrollPane GamesStock = new JScrollPane();
        GamesStock.setBounds(0, 0, 709, 424);
        panel2.add(GamesStock);
	  		 		
        table2 = new JTable();
        table2.addMouseListener(new MouseAdapter() { //Selected mouse clicked and fill GameID in panel 2
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		GamesStockID_ = managerMenuController.mouseClicked2(GameIDP2Field);
	    }});
        GamesStock.setViewportView(table2);	
	  		 		
		 JButton RefreshButtonP2 = new JButton("Refresh");
		 RefreshButtonP2.setForeground(new Color(255, 255, 255));
		 RefreshButtonP2.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		 RefreshButtonP2.setActionCommand("RefreshButton2");
		 RefreshButtonP2.addActionListener(new ActionListener() { //Return clicked
				public void actionPerformed(ActionEvent arg0) {
					managerMenuController.refreshP2();
			}});
		 RefreshButtonP2.setBounds(831, 324, 197, 35);
		 panel2.add(RefreshButtonP2);
		 RefreshButtonP2.setOpaque(false);
		 RefreshButtonP2.setContentAreaFilled(false);
		 RefreshButtonP2.setBorderPainted(false);
		 PanelService.updatePanel(2, table2);
		 
		 JLabel lblPlatformP2 = new JLabel("Platform");
		 lblPlatformP2.setBackground(Color.BLACK);
		 lblPlatformP2.setOpaque(true);
		 lblPlatformP2.setForeground(new Color(255, 255, 255));
		 lblPlatformP2.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblPlatformP2.setBounds(806, 132, 99, 20);
		 panel2.add(lblPlatformP2);
		 
		 JLabel imgLogin_2 = new JLabel("");
		 imgLogin_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgLogin_2.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgLogin_2.setLocation(new Point(50, 50));
		 imgLogin_2.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgLogin_2.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgLogin_2.setAlignmentX(0.5f);
		 imgLogin_2.setBounds(372, -23, 1472, 549);
		 imgLogin_2.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		 panel2.add(imgLogin_2);
		 
		 JLabel imgLogin_2_1 = new JLabel("");
		 imgLogin_2_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgLogin_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgLogin_2_1.setLocation(new Point(50, 50));
		 imgLogin_2_1.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgLogin_2_1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgLogin_2_1.setAlignmentX(0.5f);
		 imgLogin_2_1.setBounds(-95, 0, 1531, 549);
		 imgLogin_2_1.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		 panel2.add(imgLogin_2_1);
		 
		 /*
		  * panel 3
		  */

		 JPanel panel3 = new JPanel();
		 panel3.setBackground(SystemColor.activeCaption);
		 tabbedPane.addTab("Games Rented list", null, panel3, null);
		 panel3.setLayout(null);
		 PanelService.updatePanel(1, table);
		 
		 JLabel imgLogin = new JLabel("");
		 imgLogin.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgLogin.setLocation(new Point(50, 50));
		 imgLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgLogin.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgLogin.setAlignmentX(0.5f);
		 imgLogin.setBounds(382, -29, 1472, 549);
		 imgLogin.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		 panel.add(imgLogin);
		 
		 JLabel imgLogin_1 = new JLabel("");
		 imgLogin_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgLogin_1.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgLogin_1.setLocation(new Point(50, 50));
		 imgLogin_1.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgLogin_1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgLogin_1.setAlignmentX(0.5f);
		 imgLogin_1.setBounds(-148, 23, 1472, 549);
		 panel.add(imgLogin_1);
		 imgLogin_1.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		 
		 JButton btnReturn = new JButton("Return");
		 btnReturn.setDefaultCapable(false);
		 btnReturn.setContentAreaFilled(false);
		 btnReturn.setBackground(Color.BLACK);
		 btnReturn.setForeground(new Color(255, 255, 255));
		 btnReturn.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		 btnReturn.addActionListener(new ActionListener() { //Return clicked
				public void actionPerformed(ActionEvent arg0) {
						managerRentController.returnF(gameCopy_table3);	
				}});
		 btnReturn.setBounds(852, 141, 153, 29);
		 btnReturn.setBorderPainted(false);
		 panel3.add(btnReturn);
		 
		 txtSearchbox = new JTextField();
		 txtSearchbox.setFont(new Font("Cooper Black", txtSearchbox.getFont().getStyle(), txtSearchbox.getFont().getSize() + 4));
		 txtSearchbox.addMouseListener(new MouseAdapter() { //Delete old text in panel 3
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		txtSearchbox.setText("");
		 	}
		 });
		 txtSearchbox.addKeyListener(new KeyAdapter() { //Search by Customer
				@Override
				public void keyReleased(KeyEvent e)
				{	
					managerRentController.search(txtSearchbox.getText());
				}
		 });
		 txtSearchbox.setText("Search");
		 txtSearchbox.setBounds(851, 95, 154, 35);
		 panel3.add(txtSearchbox);
		 txtSearchbox.setColumns(10);
		 
		 JLabel lblSearchByCustomer = new JLabel("Search By Customer");
		 lblSearchByCustomer.setBackground(Color.BLACK);
		 lblSearchByCustomer.setOpaque(true);
		 lblSearchByCustomer.setForeground(new Color(255, 255, 255));
		 lblSearchByCustomer.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblSearchByCustomer.setBounds(815, 71, 216, 20);
		 panel3.add(lblSearchByCustomer);
		 
		 JButton RefreshButtonP3 = new JButton("Refresh");
		 RefreshButtonP3.setDefaultCapable(false);
		 RefreshButtonP3.setContentAreaFilled(false);
		 RefreshButtonP3.setBackground(Color.BLACK);
		 RefreshButtonP3.setForeground(new Color(255, 255, 255));
		 RefreshButtonP3.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		 RefreshButtonP3.setActionCommand("hidenRefreshButton3");
		 RefreshButtonP3.addActionListener(new ActionListener() { //return clicked
				public void actionPerformed(ActionEvent arg0) {
					managerMenuController.refreshP3();
			}});
		 RefreshButtonP3.setBounds(845, 343, 157, 29);
		 RefreshButtonP3.setBorderPainted(false);
		 panel3.add(RefreshButtonP3); 
		 
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBounds(12, 0, 709, 424);
		 panel3.add(scrollPane);
		 
		 table3 = new JTable();
		 table3.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		int row = table3.getSelectedRow();
		 		gameCopy_table3=(int) table3.getModel().getValueAt(row, 0);
		 	}
		 });
		 scrollPane.setViewportView(table3);
		 PanelService.updatePanel(3, table3);
		 
		 JLabel imgLogin_2_2 = new JLabel("");
		 imgLogin_2_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgLogin_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgLogin_2_2.setLocation(new Point(50, 50));
		 imgLogin_2_2.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgLogin_2_2.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgLogin_2_2.setAlignmentX(0.5f);
		 imgLogin_2_2.setBounds(391, -31, 1472, 549);
		 imgLogin_2_2.setIcon(new ImageIcon(Login.class.getResource("/RentGames2.png")));
		 panel3.add(imgLogin_2_2);
	}
}
