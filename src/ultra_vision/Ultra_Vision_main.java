package ultra_vision;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Connection.ConnectionManager;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Ultra_Vision_main extends JFrame {
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ConnectionManager connMan;
	private JButton btManCustomers;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Ultra_Vision_main frame= new Ultra_Vision_main();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Ultra_Vision_main() {
		setTitle("UltraVision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuBar);
		
		JMenu menuArchive = new JMenu("File");
		menuBar.add(menuArchive);
		
		JMenuItem menuRegRent = new JMenuItem("Manage titles");
		menuRegRent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ManageTitles mt = new ManageTitles(Ultra_Vision_main.this);
				mt.setVisible(true);
			}

		});
		menuArchive.add(menuRegRent);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manage rents");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageRents mt = new ManageRents(Ultra_Vision_main.this);
				mt.setVisible(true);
			}

		
		});
		
		menuArchive.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		//contentPane.setBorder(new EmptyBorder(5 ,5,5,5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnManageTitles = new JButton("Manage titles");
		btnManageTitles.setBackground(new Color(255, 228, 196));
		btnManageTitles.setBorder(new LineBorder(new Color(255, 99, 71),2,true));
		btnManageTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageTitles mt = new ManageTitles(Ultra_Vision_main.this);
				mt.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnManageTitles = new GridBagConstraints();
		gbc_btnManageTitles.weightx = 1.0;
		gbc_btnManageTitles.fill = GridBagConstraints.BOTH;
		gbc_btnManageTitles.insets = new Insets(4, 4, 5, 5);
		gbc_btnManageTitles.weightx = 0.3;
		gbc_btnManageTitles.gridx = 0;
		gbc_btnManageTitles.gridy = 0;
		contentPane.add(btnManageTitles, gbc_btnManageTitles);
		
		btManCustomers = new JButton("Manage customer");
		btManCustomers.setBorder(new LineBorder(new Color(255, 99, 71), 2, true));
		btManCustomers.setBackground(new Color(255, 228, 196));
		btManCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCustomers mt = new ManageCustomers(Ultra_Vision_main.this);
				mt.setVisible(true);
			}

			
		});
		
		//btNewCustomer.setMargin(new Insets(0 , 0, 0, 2));
		GridBagConstraints gbc_btManCustomers = new GridBagConstraints();
		gbc_btManCustomers.fill = GridBagConstraints.BOTH;
		gbc_btManCustomers.insets = new Insets(4, 2, 5, 5);
		gbc_btManCustomers.weighty = 1.0;
		gbc_btManCustomers.weightx = 0.3;
		gbc_btManCustomers.gridx = 1;
		gbc_btManCustomers.gridy = 0;
		contentPane.add(btManCustomers, gbc_btManCustomers);
		
		
		JButton btnManageRents = new JButton ("Manage rents");
		btnManageRents.setBorder(new LineBorder(new Color (255, 99, 71), 2, true));
		btnManageRents.setBackground(new Color(255, 228, 196));
		btnManageRents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageRents mt = new ManageRents(Ultra_Vision_main.this);
			}

		
		});
		
		GridBagConstraints gbc_btnManageRents = new GridBagConstraints();
		gbc_btnManageRents.weightx = 0.3;
		gbc_btnManageRents.weighty = 1.0;
		gbc_btnManageRents.fill = GridBagConstraints.BOTH;
		gbc_btnManageRents.insets = new Insets(2, 2, 5, 4);
		gbc_btnManageRents.gridy =0;
		contentPane.add(btnManageRents, gbc_btnManageRents);
		
		setConnMan(new ConnectionManager());
		
	}
	//setting up the connection  with the database
	
	public ConnectionManager getConnMan() {
		return connMan;
	}
	public void setConnMan(ConnectionManager connMan) {
		this.connMan = connMan;
	}
}
