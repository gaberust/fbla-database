package grust.fbla;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.*;
import javax.swing.*;

/**
 * Class <code>EditDialog</code> represents a JDialog for editing records
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class EditDialog extends JDialog {

	/**
	 * Private field <code>member</code>
	 * represents the new data for the record being edited
	 */
	private FBLAMember member = null;
	
	/**
	 * Represents different UI components used in the JDialog
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField schoolTextField;
	private JTextField emailTextField;
	private JTextField yearJoinedTextField;
	private JTextField amountOwedTextField;
	
	/**
	 * Creates a new EditDialog and blocks input to other windows while active
	 * @param member The record being edited
	 */
	public EditDialog(final FBLAMember member) {		
		setTitle("Edit Record");
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		contentPanel.add(upperPanel, BorderLayout.CENTER);
		upperPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		final JLabel lblMembershipNumber = new JLabel("Membership Number:");
		upperPanel.add(lblMembershipNumber, "2, 2, right, default");
		
		JLabel lblNewLabel = new JLabel(Integer.toString(member.getMembershipNumber()));
		upperPanel.add(lblNewLabel, "4, 2");
		
		final JLabel lblFirstName = new JLabel("First Name:");
		upperPanel.add(lblFirstName, "2, 4, right, default");
		
		firstNameTextField = new JTextField();
		firstNameTextField.setText(member.getFirstName());
		upperPanel.add(firstNameTextField, "4, 4, fill, default");
		firstNameTextField.setColumns(10);
		
		final JLabel lblLastName = new JLabel("Last Name:");
		upperPanel.add(lblLastName, "2, 6, right, default");
		
		lastNameTextField = new JTextField();
		lastNameTextField.setText(member.getLastName());
		upperPanel.add(lastNameTextField, "4, 6, fill, default");
		lastNameTextField.setColumns(10);
		
		final JLabel lblGrade = new JLabel("Grade:");
		upperPanel.add(lblGrade, "2, 8, right, default");
		
		final JComboBox<String> gradeComboBox = new JComboBox(Util.GRADES);
		gradeComboBox.setSelectedIndex(member.getGrade() - 4);
		upperPanel.add(gradeComboBox, "4, 8, fill, default");
		
		final JLabel lblSchool = new JLabel("School:");
		upperPanel.add(lblSchool, "2, 10, right, default");
		
		schoolTextField = new JTextField();
		schoolTextField.setText(member.getSchool());
		upperPanel.add(schoolTextField, "4, 10, fill, default");
		schoolTextField.setColumns(10);
		
		final JLabel lblState = new JLabel("State/Territory:");
		upperPanel.add(lblState, "2, 12, right, default");
		
		final JComboBox<String> stateComboBox = new JComboBox(Util.STATES);
		stateComboBox.setSelectedItem(member.getState());
		upperPanel.add(stateComboBox, "4, 12, fill, default");
		
		final JLabel lblEmail = new JLabel("Email:");
		upperPanel.add(lblEmail, "2, 14, right, default");
		
		emailTextField = new JTextField();
		emailTextField.setText(member.getEmail());
		upperPanel.add(emailTextField, "4, 14, fill, default");
		emailTextField.setColumns(10);
		
		final JLabel lblYearJoined = new JLabel("Year Joined:");
		upperPanel.add(lblYearJoined, "2, 16, right, default");
		
		yearJoinedTextField = new JTextField();
		yearJoinedTextField.setText(Integer.toString(member.getYearJoined()));
		upperPanel.add(yearJoinedTextField, "4, 16, fill, default");
		yearJoinedTextField.setColumns(10);
		
		final JLabel lblAmountOwed = new JLabel("Amount Owed:");
		upperPanel.add(lblAmountOwed, "2, 18, right, default");
		
		amountOwedTextField = new JTextField();
		amountOwedTextField.setText(Double.toString(member.getAmountOwed()));
		upperPanel.add(amountOwedTextField, "4, 18, fill, default");
		amountOwedTextField.setColumns(10);
		
		final JCheckBox activeCheckBox = new JCheckBox("Active");
		activeCheckBox.setSelected(member.isActive());
		upperPanel.add(activeCheckBox, "4, 20");
		
		JPanel lowerPanel = new JPanel();
		contentPanel.add(lowerPanel, BorderLayout.SOUTH);
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean formValid = true;
				
				if (!Validate.validateName(firstNameTextField.getText())) {
					formValid = false;
					lblFirstName.setForeground(Color.RED);
				}
				else {
					lblFirstName.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateName(lastNameTextField.getText())) {
					formValid = false;
					lblFirstName.setForeground(Color.RED);
				}
				else {
					lblFirstName.setForeground(Color.BLACK);
				}
				
				if (gradeComboBox.getSelectedIndex() == 0) {
					formValid = false;
					lblGrade.setForeground(Color.RED);
				}
				else {
					lblGrade.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateName(schoolTextField.getText())) {
					formValid = false;
					lblSchool.setForeground(Color.RED);
				}
				else {
					lblSchool.setForeground(Color.BLACK);
				}
				
				if (stateComboBox.getSelectedIndex() == 0) {
					formValid = false;
					lblState.setForeground(Color.RED);
				}
				else {
					lblState.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateEmail(emailTextField.getText())) {
					formValid = false;
					lblEmail.setForeground(Color.RED);
				}
				else {
					lblEmail.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateYearJoined(yearJoinedTextField.getText())) {
					formValid = false;
					lblYearJoined.setForeground(Color.RED);
				}
				else {
					lblYearJoined.setForeground(Color.BLACK);
				}
				
				if (!Validate.validateAmountOwed(amountOwedTextField.getText())) {
					formValid = false;
					lblAmountOwed.setForeground(Color.RED);
				}
				else {
					lblAmountOwed.setForeground(Color.BLACK);
				}
				
				if (formValid) {
					member.setGrade(gradeComboBox.getSelectedIndex() + 4);
					member.setYearJoined(Integer.parseInt(yearJoinedTextField.getText()));
					member.setAmountOwed(Double.parseDouble(amountOwedTextField.getText()));
					member.setFirstName(firstNameTextField.getText());
					member.setLastName(lastNameTextField.getText());
					member.setSchool(schoolTextField.getText());
					member.setState((String) stateComboBox.getSelectedItem());
					member.setEmail(emailTextField.getText());
					member.setActive(activeCheckBox.isSelected());
					
					EditDialog.this.member = member;
					
					dispose();
				}
				
			}
		});
		lowerPanel.add(updateButton);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		lowerPanel.add(deleteButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditDialog.this.member = member;
				dispose();
			}
		});
		lowerPanel.add(cancelButton);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * @return The new data for the record being edited
	 */
	public FBLAMember getRecord() {
		return member;
	}

}
