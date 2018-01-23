package grust.fbla;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.*;

import com.jgoodies.forms.layout.*;

/**
 * Class <code>MainFraim</code> creates the main window for the application
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class MainFrame extends JFrame {
	
	/**
	 * Private constant <code>TITLE</code>
	 * contains the title of this JFrame
	 */
	private static final String TITLE = "FBLA MEMBER DATABASE";
	
	/**
	 * Private constant <code>SAVE_PATH</code>
	 * represents the file path to the save file
	 */
	private static final String SAVE_PATH = "./save";
	
	/**
	 * Private constant <code>SAVE_FILE_NAME</code>
	 * represents the name of the save file
	 */
	private static final String SAVE_FILE_NAME = "members.dat";
	
	/**
	 * Private constant <code>COLUMN_HEADINGS</code>
	 * represents a list of column headings for the JTable
	 */
	private static final Object[] COLUMN_HEADINGS = {
		"Membership Number",
		"First Name",
		"Last Name",
		"Grade",
		"School",
		"State",
		"Email",
		"Year Joined",
		"Amount Owed",
		"Active"
	};
	
	/**
	 * Private field <code>members</code>
	 * represents the FBLAMemberList to be used in GUI
	 */
	private FBLAMemberList members;
	
	/**
	 * Private field <code>searchInfo</code>
	 * represent an FBLAMemberList of search results
	 */
	private SearchInfo searchInfo;
	
	/**
	 * Private field <code>searchActive</code>
	 * represents whether a search is being displayed or not
	 */
	private boolean searchActive = false;
	
	/**
	 * Represents different UI JComponents in the JFrame
	 */
	private JPanel contentPane;
	private JTable table;
	private JTextField mNumberTextField;
	private JTextField fNameTextField;
	private JTextField lNameTextField;
	private JTextField schoolTextField;
	private JTextField emailTextField;
	private JTextField amountOwedTextField;
	private JTextField searchQueryTextField;
	private JTextField yearJoinedTextField;
	private JComboBox<String> gradeComboBox;
	private JComboBox<String> stateComboBox;
	private JCheckBox activeCheckBox;
	private ButtonGroup active;
	private ButtonGroup strict;
	private JScrollPane scrollPane;
	private JLabel selectedRecordLabel;
	
	/**
	 * Creates a new MainFrame and loads the save file into the program
	 */
	public MainFrame() {
		load(SAVE_PATH, SAVE_FILE_NAME);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
				members.save(SAVE_PATH, SAVE_FILE_NAME);
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
			
		});
		
		setTitle(TITLE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				members.save(SAVE_PATH, SAVE_FILE_NAME);
			}
		});
		fileMenu.add(saveMenuItem);
		
		JMenu reportMenu = new JMenu("Report");
		fileMenu.add(reportMenu);
		
		JMenuItem standardMenuItem = new JMenuItem("Standard");
		standardMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StandardReportDialog(members).setVisible(true);
			}
		});
		reportMenu.add(standardMenuItem);
		
		JMenuItem seniorMenuItem = new JMenuItem("Senior");
		seniorMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SeniorReportDialog(members).setVisible(true);
			}
		});
		reportMenu.add(seniorMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel upperPanel = new JPanel();
		contentPane.add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel newRecordPanel = new JPanel();
		newRecordPanel.setBorder(new TitledBorder(UIManager.getBorder("InternalFrame.optionDialogBorder"), "New Record:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		upperPanel.add(newRecordPanel, BorderLayout.WEST);
		newRecordPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		final JLabel mNumberLabel = new JLabel("Membership Number:");
		newRecordPanel.add(mNumberLabel, "2, 2, right, default");
		
		mNumberTextField = new JTextField();
		newRecordPanel.add(mNumberTextField, "4, 2, fill, default");
		mNumberTextField.setColumns(10);
		
		final JLabel fNameLabel = new JLabel("First Name:");
		newRecordPanel.add(fNameLabel, "2, 4, right, default");
		
		fNameTextField = new JTextField();
		newRecordPanel.add(fNameTextField, "4, 4, fill, default");
		fNameTextField.setColumns(10);
		
		final JLabel lNameLabel = new JLabel("Last Name:");
		newRecordPanel.add(lNameLabel, "2, 6, right, default");
		
		lNameTextField = new JTextField();
		newRecordPanel.add(lNameTextField, "4, 6, fill, default");
		lNameTextField.setColumns(10);
		
		final JLabel gradeLabel = new JLabel("Grade:");
		newRecordPanel.add(gradeLabel, "2, 8, right, default");
		
		gradeComboBox = new JComboBox(Util.GRADES);
		newRecordPanel.add(gradeComboBox, "4, 8, fill, default");
		
		final JLabel schoolLabel = new JLabel("School:");
		newRecordPanel.add(schoolLabel, "2, 10, right, default");
		
		schoolTextField = new JTextField();
		newRecordPanel.add(schoolTextField, "4, 10, fill, default");
		schoolTextField.setColumns(10);
		
		final JLabel stateLabel = new JLabel("State/Territory:");
		newRecordPanel.add(stateLabel, "2, 12, right, default");
		
		stateComboBox = new JComboBox(Util.STATES);
		newRecordPanel.add(stateComboBox, "4, 12, fill, default");
		
		final JLabel emailLabel = new JLabel("Email:");
		newRecordPanel.add(emailLabel, "2, 14, right, default");
		
		emailTextField = new JTextField();
		newRecordPanel.add(emailTextField, "4, 14, fill, default");
		emailTextField.setColumns(10);
		
		final JLabel yearJoinedLabel = new JLabel("Year Joined:");
		newRecordPanel.add(yearJoinedLabel, "2, 16, right, default");
		
		yearJoinedTextField = new JTextField();
		newRecordPanel.add(yearJoinedTextField, "4, 16, fill, default");
		yearJoinedTextField.setColumns(10);
		
		final JLabel amountOwedLabel = new JLabel("Amount Owed:");
		newRecordPanel.add(amountOwedLabel, "2, 18, right, default");
		
		amountOwedTextField = new JTextField();
		newRecordPanel.add(amountOwedTextField, "4, 18, fill, default");
		amountOwedTextField.setColumns(10);
		
		activeCheckBox = new JCheckBox("Active");
		activeCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		newRecordPanel.add(activeCheckBox, "2, 20");
		
		JButton addButton = new JButton("ADD");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean formValid = true;
				
				if (!Validate.validateMembershipNumber(mNumberTextField.getText())) {
					formValid = false;
					mNumberLabel.setForeground(Color.RED);
				}
				else {
					mNumberLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateName(fNameTextField.getText())) {
					formValid = false;
					fNameLabel.setForeground(Color.RED);
				}
				else {
					fNameLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateName(lNameTextField.getText())) {
					formValid = false;
					lNameLabel.setForeground(Color.RED);
				}
				else {
					lNameLabel.setForeground(Color.BLACK);
				}
				
				if (gradeComboBox.getSelectedIndex() == 0) {
					formValid = false;
					gradeLabel.setForeground(Color.RED);
				}
				else {
					gradeLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateName(schoolTextField.getText())) {
					formValid = false;
					schoolLabel.setForeground(Color.RED);
				}
				else {
					schoolLabel.setForeground(Color.BLACK);
				}
				
				if (stateComboBox.getSelectedIndex() == 0) {
					formValid = false;
					stateLabel.setForeground(Color.RED);
				}
				else {
					stateLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateEmail(emailTextField.getText())) {
					formValid = false;
					emailLabel.setForeground(Color.RED);
				}
				else {
					emailLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateYearJoined(yearJoinedTextField.getText())) {
					formValid = false;
					yearJoinedLabel.setForeground(Color.RED);
				}
				else {
					yearJoinedLabel.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateAmountOwed(amountOwedTextField.getText())) {
					formValid = false;
					amountOwedLabel.setForeground(Color.RED);
				}
				else {
					amountOwedLabel.setForeground(Color.BLACK);
				}
				
				if (formValid) {
					members.insert(
						new FBLAMember(
							Integer.parseInt(mNumberTextField.getText()), 
							gradeComboBox.getSelectedIndex() + 4, 
							Integer.parseInt(yearJoinedTextField.getText()), 
							Double.parseDouble(amountOwedTextField.getText()), 
							fNameTextField.getText(), 
							lNameTextField.getText(), 
							schoolTextField.getText(), 
							(String) stateComboBox.getSelectedItem(),
							emailTextField.getText(), 
							activeCheckBox.isSelected()
						)
					);
					searchActive = false;
					searchInfo = null;
					updateTable();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please review the highlighted fields.", "Invalid Form", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		newRecordPanel.add(addButton, "2, 22");
		
		JButton newRecordResetButton = new JButton("RESET FORM");
		newRecordResetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFocusableWindowState(false);
				
				mNumberTextField.setText("");
				fNameTextField.setText("");
				lNameTextField.setText("");
				gradeComboBox.setSelectedIndex(0);
				schoolTextField.setText("");
				stateComboBox.setSelectedIndex(0);
				emailTextField.setText("");
				yearJoinedTextField.setText("");
				amountOwedTextField.setText("");
				activeCheckBox.setSelected(false);
				
				mNumberLabel.setForeground(Color.BLACK);
				fNameLabel.setForeground(Color.BLACK);
				lNameLabel.setForeground(Color.BLACK);
				gradeLabel.setForeground(Color.BLACK);
				schoolLabel.setForeground(Color.BLACK);
				stateLabel.setForeground(Color.BLACK);
				emailLabel.setForeground(Color.BLACK);
				yearJoinedLabel.setForeground(Color.BLACK);
				amountOwedLabel.setForeground(Color.BLACK);
				
				setFocusableWindowState(true);
			}
		});
		newRecordPanel.add(newRecordResetButton, "4, 22");
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(UIManager.getBorder("InternalFrame.optionDialogBorder"), "Search Records:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		upperPanel.add(searchPanel, BorderLayout.CENTER);
		searchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel searchQueryLabel = new JLabel("Search Query:");
		searchPanel.add(searchQueryLabel, "2, 2, right, default");
		
		searchQueryTextField = new JTextField();
		searchPanel.add(searchQueryTextField, "4, 2, fill, default");
		searchQueryTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		searchPanel.add(separator, "2, 4, 3, 1");
		
		JLabel searchFromLabel = new JLabel("Search From:");
		searchPanel.add(searchFromLabel, "2, 6, 1, 5");
		
		final JRadioButton allMembersRadioButton = new JRadioButton("All Members");
		allMembersRadioButton.setSelected(true);
		searchPanel.add(allMembersRadioButton, "4, 6");
		
		final JRadioButton activeMembersRadioButton = new JRadioButton("Active Members");
		searchPanel.add(activeMembersRadioButton, "4, 8");
		
		final JRadioButton inactiveMembersRadioButton = new JRadioButton("Inactive Members");
		searchPanel.add(inactiveMembersRadioButton, "4, 10");
		
		active = new ButtonGroup();
		active.add(allMembersRadioButton);
		active.add(activeMembersRadioButton);
		active.add(inactiveMembersRadioButton);
		
		JSeparator separator_1 = new JSeparator();
		searchPanel.add(separator_1, "2, 12, 3, 1");
		
		JLabel typeOfSearchLabel = new JLabel("Type of Search:");
		searchPanel.add(typeOfSearchLabel, "2, 14, 1, 3");
		
		final JRadioButton casualRadioButton = new JRadioButton("Casual");
		casualRadioButton.setSelected(true);
		searchPanel.add(casualRadioButton, "4, 14");
		
		final JRadioButton strictRadioButton = new JRadioButton("Strict");
		searchPanel.add(strictRadioButton, "4, 16");
		
		strict = new ButtonGroup();
		strict.add(casualRadioButton);
		strict.add(strictRadioButton);
		
		JSeparator separator_2 = new JSeparator();
		searchPanel.add(separator_2, "2, 18, 3, 1");
		
		JButton searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchActive = true;
				Boolean activeMemberSearch;
				if (allMembersRadioButton.isSelected()) {
					activeMemberSearch = null;
				}
				else if (activeMembersRadioButton.isSelected()) {
					activeMemberSearch = true;
				}
				else {
					activeMemberSearch = false;
				}
				String[] query = searchQueryTextField.getText().split(" ");
				if (query.length != 0) {
					searchInfo = members.search(query, strictRadioButton.isSelected());
				}
				else {
					searchInfo = new SearchInfo();
					for (int i = 0; i < members.size(); i ++) {
						FBLAMember temp = members.get(i);
						if (activeMemberSearch != null) {
							for (int j = 0; j < members.size(); j ++) {
								if (temp.isActive() == activeMemberSearch) {
									searchInfo.results.add(temp);
									searchInfo.indeces.add(j);
								}
							}
						}
						else {
							searchInfo.results = members;
							for (int j = 0; j < members.size(); j ++) {
								searchInfo.indeces.add(j);
							}
						}
					}
				}
				updateTable();
			}
		});
		searchPanel.add(searchButton, "2, 20");
		
		JButton searchRecordsResetButton = new JButton("RESET FORM");
		searchRecordsResetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchActive = false;
				searchInfo = null;
				searchQueryTextField.setText("");
			}
		});
		searchPanel.add(searchRecordsResetButton, "4, 20");
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new TitledBorder(UIManager.getBorder("InternalFrame.optionDialogBorder"), "Selected Record:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		displayPanel.setPreferredSize(new Dimension(300, 0));
		upperPanel.add(displayPanel, BorderLayout.EAST);
		displayPanel.setLayout(new BorderLayout(0, 0));
		
		selectedRecordLabel = new JLabel();
		selectedRecordLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		displayPanel.add(selectedRecordLabel);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.setBorder(new TitledBorder(UIManager.getBorder("InternalFrame.optionDialogBorder"), "Members:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		contentPane.add(lowerPanel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		lowerPanel.add(scrollPane, BorderLayout.CENTER);
		updateTable();
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setLocationRelativeTo(null);
	}
	
	/**
	 * Updates the JTable to a new set of members
	 */
	private void updateTable() {
		table = new JTable(searchActive ? searchInfo.results.getTableData() : members.getTableData(), COLUMN_HEADINGS) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (table.getSelectedRow() != -1) {
						if (searchActive) {
							EditDialog edit = new EditDialog(searchInfo.results.get(table.getSelectedRow()));
							edit.setVisible(true);
							FBLAMember temp = edit.getRecord();
							if (temp != null) {
								searchActive = false;
								members.remove(searchInfo.indeces.get(table.getSelectedRow()));
								searchInfo = null;
								members.insert(temp);
							}
							else {
								searchActive = false;
								members.remove(searchInfo.indeces.get(table.getSelectedRow()));
								searchInfo = null;
							}
						}
						else {
							EditDialog edit = new EditDialog(members.get(table.getSelectedRow()));
							edit.setVisible(true);
							FBLAMember temp = edit.getRecord();
							if (temp != null) {
								members.remove(table.getSelectedRow());
								members.insert(temp);
							}
							else {
								members.remove(table.getSelectedRow());
							}
						}
						updateTable();
					}
				}
				updateSelectedRecord();
			}
		});
		scrollPane.setViewportView(table);
	}
	
	/**
	 * Displays the selected table row in <code>selectedRecordLabel</code>
	 * when the user clicks on the table and the selected record does not equal -1
	 */
	private void updateSelectedRecord() {
		if (table.getSelectedRow() != -1) {
			FBLAMember temp = searchActive ? searchInfo.results.get(table.getSelectedRow()) : members.get(table.getSelectedRow());
			selectedRecordLabel.setText(
					"<html><body><pre>Membership Number: " + temp.getMembershipNumber() +
					"\n             Name: " + temp.getLastName() + ", " + temp.getFirstName() +
					"\n            Grade: " + temp.getGrade() +
					"\n           School: " + temp.getSchool() +
					"\n            State: " + temp.getState() +
					"\n            Email: " + temp.getEmail() +
					"\n      Year Joined: " + temp.getYearJoined() +
					"\n      Amount Owed: $" + new DecimalFormat("##.00").format(temp.getAmountOwed()) +
					"\n           Active: " + (temp.isActive() ? "Yes" : "No") + "</pre></body></html>"
				);
		}
		else {
			selectedRecordLabel.setText("");
		}
	}
	
	/**
	 * Reads the save file into the program
	 * @param path The file path to the save file
	 * @param name The name of the save file
	 */
	private void load(String path, String name) {
		File f = new File(path);
		if (!f.exists()) {
			members = new FBLAMemberList();
			f.mkdir();
			Util.writeObjectToFile(new FBLAMemberList(), path + "/" + name);
			return;
		}
		members = (FBLAMemberList) Util.readObjectFromFile(path + "/" + name);
	}
}
