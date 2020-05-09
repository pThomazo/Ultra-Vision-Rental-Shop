package ultra_vision;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class RegisterTitleDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField tfGenre;

	private JComboBox<String> typeField;
	private JComboBox<String> comboMedia;
	private JSpinner spinnerYear;

	
	 // Create the dialog.
	 
	public RegisterTitleDialog(Ultra_Vision_main mf) {
	
		setTitle("Register title");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.insets = new Insets(0, 0, 5, 0);
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.gridx = 3;
			gbc_nameField.gridy = 0;
			contentPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		{
			JLabel lblType = new JLabel("Type:");
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.insets = new Insets(0, 0, 5, 5);
			gbc_lblType.gridx = 0;
			gbc_lblType.gridy = 1;
			contentPanel.add(lblType, gbc_lblType);
		}
		{
			typeField = new JComboBox<String>();
			typeField.setModel(
					new DefaultComboBoxModel<String>(new String[] { "Music", "Live concert", "Movie", "Box set" }));
			GridBagConstraints gbc_typeField = new GridBagConstraints();
			gbc_typeField.insets = new Insets(0, 0, 5, 0);
			gbc_typeField.fill = GridBagConstraints.HORIZONTAL;
			gbc_typeField.gridx = 3;
			gbc_typeField.gridy = 1;
			contentPanel.add(typeField, gbc_typeField);
		}
		{
			JLabel lblMedia = new JLabel("Media:");
			GridBagConstraints gbc_lblMedia = new GridBagConstraints();
			gbc_lblMedia.insets = new Insets(0, 0, 5, 5);
			gbc_lblMedia.gridx = 0;
			gbc_lblMedia.gridy = 2;
			contentPanel.add(lblMedia, gbc_lblMedia);
		}
		{
			comboMedia = new JComboBox<String>();
			comboMedia.setModel(new DefaultComboBoxModel<String>(new String[] { "CD", "DVD", "Blu-ray" }));
			GridBagConstraints gbc_comboMedia = new GridBagConstraints();
			gbc_comboMedia.insets = new Insets(0, 0, 5, 0);
			gbc_comboMedia.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboMedia.gridx = 3;
			gbc_comboMedia.gridy = 2;
			contentPanel.add(comboMedia, gbc_comboMedia);
		}
		{
			spinnerYear = new JSpinner();
			spinnerYear.setModel(new SpinnerNumberModel(1800, 1800, null,1));
			GridBagConstraints gbc_spinnerYear = new GridBagConstraints();
			gbc_spinnerYear.insets = new Insets(0, 0, 5, 0);
			gbc_spinnerYear.gridx = 3;
			gbc_spinnerYear.gridy = 3;
			contentPanel.add(spinnerYear, gbc_spinnerYear);
		}
		{
			JLabel lblYear = new JLabel("Year:");
			GridBagConstraints gbc_lblYear = new GridBagConstraints();
			gbc_lblYear.insets = new Insets(0, 0, 5, 5);
			gbc_lblYear.gridx = 0;
			gbc_lblYear.gridy = 3;
			contentPanel.add(lblYear, gbc_lblYear);
		}
		{
			JLabel lblGener = new JLabel("Genre:");
			GridBagConstraints gbc_lblGener = new GridBagConstraints();
			gbc_lblGener.insets = new Insets(0, 0, 5, 5);
			gbc_lblGener.gridx = 0;
			gbc_lblGener.gridy = 4;
			contentPanel.add(lblGener, gbc_lblGener);
		}
		{
			tfGenre = new JTextField();
			GridBagConstraints gbc_tfGenre = new GridBagConstraints();
			gbc_tfGenre.insets = new Insets(0, 0, 5, 0);
			gbc_tfGenre.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfGenre.gridx = 3;
			gbc_tfGenre.gridy = 4;
			contentPanel.add(tfGenre, gbc_tfGenre);
			tfGenre.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Connection cn = mf.getConnMan().getConnection();

							String titleName = nameField.getText();
							int type = typeField.getSelectedIndex();
							int mediaIdx = comboMedia.getSelectedIndex();
							int year = (Integer) spinnerYear.getValue();
							String titleGenre = tfGenre.getText();

							String sqlStoreTitle = "INSERT INTO titles (name,type,mediaType,year,genre) VALUES(?,?,?,?,?)";
							PreparedStatement ps = cn.prepareStatement(sqlStoreTitle);
							ps.setString(1, titleName);
							System.out.println("T "+type);
							ps.setString(2, String.valueOf(type));
							ps.setString(3, String.valueOf(mediaIdx));
							ps.setInt(4, year);
							ps.setString(5, titleGenre);

							int numRows = ps.executeUpdate();

							if (numRows > 0) {
								// Success
								JOptionPane.showMessageDialog(RegisterTitleDialog.this, "Title registered");
							}

						} catch (SQLException e1) {
							//  Bloco catch gerado automaticamente
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegisterTitleDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
