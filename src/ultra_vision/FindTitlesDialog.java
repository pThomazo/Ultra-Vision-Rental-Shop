package ultra_vision;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Media;
import model.Title;
import model.Titletype;
import model.Titletype;

import javax.swing.JScrollPane;

public class FindTitlesDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fieldNameToSearch;
	private TitlesTableModel titlesTModel;
	private Ultra_Vision_main mF;
	private JTable titlesTable;

	
	 // Create the dialog.
	 
	public FindTitlesDialog(Ultra_Vision_main mFrame) {
		this.mF = mFrame;

		//Setting window and parameter (Find titles)
		setTitle("Find titles");
		setBounds(100, 100, 561, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		contentPanel.setLayout(gbl_contentPanel);
		{
			//creating label name and adding it to the panel.
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			//creating the button search setting actionlistener to it
			JButton btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String searchTerm = fieldNameToSearch.getText();

					try {
						List<Title> titlesFound = executeTitlesFind(searchTerm);
						// Update the table with the titles found
						titlesTModel.setTitles(titlesFound);
					} catch (Exception e1) {
						//  Block catch generated automatically 
						e1.printStackTrace();
					}
				}
			});
			getRootPane().setDefaultButton(btnSearch);
			{
				//new JTextField 
				fieldNameToSearch = new JTextField();
				GridBagConstraints gbc_fieldNameToSearch = new GridBagConstraints();
				gbc_fieldNameToSearch.gridwidth = 2;
				gbc_fieldNameToSearch.fill = GridBagConstraints.HORIZONTAL;
				gbc_fieldNameToSearch.insets = new Insets(0, 0, 5, 5);
				gbc_fieldNameToSearch.gridx = 1;
				gbc_fieldNameToSearch.gridy = 0;
				contentPanel.add(fieldNameToSearch, gbc_fieldNameToSearch);
				fieldNameToSearch.setColumns(10);
			}
			GridBagConstraints gbc_btnSearch = new GridBagConstraints();
			gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
			gbc_btnSearch.gridx = 0;
			gbc_btnSearch.gridy = 1;
			contentPanel.add(btnSearch, gbc_btnSearch);
		}
		{
			//creating a new TitlesTableModel
			titlesTModel = new TitlesTableModel();
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 6;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 2;
				contentPanel.add(scrollPane, gbc_scrollPane);

				titlesTable = new JTable(titlesTModel);
				scrollPane.setViewportView(titlesTable);
			}
		}

	}

	
	 // This method must be called just after this window be turned visible to the
	 // table be filled with titles
	 
	public void fillTable() {

		try {
			List<Title> allTit = executeTitlesFind(null);

			titlesTModel.setTitles(allTit);
		} catch (Exception e) {
			//  Bloco catch gerado automaticamente
			e.printStackTrace();
		}

	}

	
	 
	 //@param searchTerm null to list all titles
	 // @return
	 // @throws Exception
	 
	private List<Title> executeTitlesFind(String searchTerm) throws Exception {
		try {

			Connection cn = mF.getConnMan().getConnection();
			String sqlFindTitles = "SELECT * FROM titles";

			boolean hasTerm = false;
			if (searchTerm != null && !searchTerm.isEmpty()) {
				sqlFindTitles += " WHERE name LIKE ?";
				hasTerm = true;
			}

			PreparedStatement psFT = cn.prepareStatement(sqlFindTitles);
			if (hasTerm) {
				psFT.setString(1, '%' + searchTerm + '%');
			}
			ResultSet rsTitl = psFT.executeQuery();

			List<Title> titles = new ArrayList<>();
			Title titl;
			while (rsTitl.next()) {
				System.out.println("Found title");
//setting bounder to set titles and get it as return 
				titl = new Title();

				titl.setId(rsTitl.getInt(1));
				titl.setName(rsTitl.getString(2));
				titl.setType(Titletype.fromCode(rsTitl.getInt(3)));
				titl.setMidia(Media.fromCode(rsTitl.getInt(4)));
				titl.setYearReleased(rsTitl.getInt(5));
				titl.setGenre(rsTitl.getString(6));

				titles.add(titl);
			}

			return titles;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;

		}

	}

}
