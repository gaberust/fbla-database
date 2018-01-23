package grust.fbla;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Class <code>FBLAMember</code> represents an FBLA Member
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class FBLAMember implements Serializable {
	
	/**
	 * Private field <code>serialVersionUID</code>
	 * is to be used by <code>java.io.Serializable</code>
	 */
	private static final long serialVersionUID = -8395760327335412835L;

	/**
	 * Private field <code>membershipNumber</code> 
	 * represents the membership number of an FBLA Member
	 */
	private int membershipNumber;
	
	/**
	 * Private field <code>firstName</code>
	 * represents the first name of an FBLA Member
	 */
	private String firstName;
	
	/**
	 * Private field <code>lastName</code>
	 * represents the last name of an FBLA Member
	 */
	private String lastName;
	
	/**
	 * Private field <code>grade</code>
	 * represents the grade of an FBLA Member
	 */
	private int grade;
	
	/**
	 * Private field <code>school</code>
	 * represents the school an FBLA Member is from
	 */
	private String school;
	
	/**
	 * Private field <code>state</code>
	 * represents the state an FBLA Member is from
	 */
	private String state;
	
	/**
	 * Private field <code>email</code>
	 * represents the email of an FBLA Member
	 */
	private String email;
	
	/**
	 * Private field <code>yearJoined</code>
	 * represents the year a member joined FBLA
	 */
	private int yearJoined;
	
	/**
	 * Private field <code>active</code>
	 * represents whether an FBLA Member is active or not
	 */
	private boolean active;
	
	/**
	 * Private field <code>amountOwed</code>
	 * represents the amount a member owes FBLA
	 */
	private double amountOwed;
	
	/**
	 * Creates a new <code>FBLAMember</code> object
	 * @param membershipNumber  Membership number of the FBLA Member
	 * @param grade				Grade of the FBLA Member
	 * @param yearJoined        Year the member joined FBLA
	 * @param amountOwed        Amount owed to FBLA by the member
	 * @param firstName         First name of the FBLA Member
	 * @param lastName          Last name of the FBLA Member
	 * @param school            School the FBLA Member is from
	 * @param state             State the FBLA Member is from
	 * @param email             Email of the FBLA Member
	 * @param active            Boolean state of the member's activity
	 */
	public FBLAMember(int membershipNumber, int grade, int yearJoined, double amountOwed, String firstName, String lastName, String school, String state, String email, boolean active) {
		this.membershipNumber = membershipNumber;
		this.grade = grade;
		this.yearJoined = yearJoined;
		this.amountOwed = amountOwed;
		this.firstName = firstName;
		this.lastName = lastName;
		this.school = school;
		this.state = state;
		this.email = email;
		this.active = active;
	}

	/**
	 * @return The membership number of this FBLA Member
	 */
	public int getMembershipNumber() {
		return membershipNumber;
	}

	/**
	 * Assigns a new value to <code>membershipNumber</code> of the current object
	 * @param membershipNumber New value to be assigned to membership number
	 */
	public void setMembershipNumber(int membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	/**
	 * @return The first name of this FBLA Member
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Assigns a new value to <code>firstName</code> of the current object
	 * @param firstName New value to be assigned to first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return The last name of this FBLA Member
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Assigns a new value to <code>lastName</code> of the current object
	 * @param lastName New value to be assigned to last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return The grade of this FBLA Member
	 */
	public int getGrade() {
		return grade;
	}
	
	/**
	 * Assigns a new value to <code>grade</code> of the current object
	 * @param grade New value to be assigned to grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * @return The school that this FBLA Member is from
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * Assigns a new value to <code>school</code> of the current object
	 * @param school New value to be assigned to school
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * @return The state that this FBLA Member is from
	 */
	public String getState() {
		return state;
	}

	/**
	 * Assigns a new value to <code>state</code> of the current object
	 * @param state New value to be assigned to state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return The email of this FBLA Member
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Assigns a new value to <code>email</code> of the current object
	 * @param email New value to be assigned to email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return The year that this member joined FBLA
	 */
	public int getYearJoined() {
		return yearJoined;
	}

	/**
	 * Assigns a new value to <code>yearJoined</code> of the current object
	 * @param yearJoined New value to be assigned to year joined
	 */
	public void setYearJoined(int yearJoined) {
		this.yearJoined = yearJoined;
	}

	/**
	 * @return The state of activity of this FBLAMember
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Assigns a new value to <code>active</code> of the current object
	 * @param active New value to be assigned to active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return The amount that this member owes to FBLA
	 */
	public double getAmountOwed() {
		return amountOwed;
	}

	/**
	 * Assigns a new value to <code>amountOwed</code> of the current object
	 * @param amountOwed New value to be assigned to amount owed
	 */
	public void setAmountOwed(double amountOwed) {
		this.amountOwed = amountOwed;
	}
	
	/**
	 * Searches this FBLAMember for a list of queries
	 *     Casual Search:
	 *         Only one query needs to be found to return <code>true</code>
	 *         Loops through queries, and returns true when ONE is found
	 *         If NO queries are found, returns false
	 *     Strict Search:
	 *         All queries must be found to return <code>true</code>
	 *         Loops through queries, and returns false when ONE is NOT found
	 *         If ALL queries are found, returns true
	 * @param  query  Array of <code>String</code> queries that are to be searched for
	 * @param  strict Whether a strict or casual search is conducted: <code>true</code> for strict, <code>false</code> for casual
	 * @return <code>true</code> or <code>false</code>
	 *         depending on the type of search and result of search
	 */
	public boolean search(String[] queries, boolean strict) {
		if (strict) {
			for (String query : queries) {
				if (!checkQuery(query)) return false;
			}
			return true;
		}
		else {
			for (String query : queries) {
				if (checkQuery(query)) return true;
			}
			return false;
		}
	}
	
	/**
	 * Searches this FBLA Member for a single <code>String</code> query
	 * @param  query The <code>String</code> to be searched for
	 * @return <code>true</code> if the query is found, <code>false</code> if the query is not found
	 */
	private boolean checkQuery(String query) {
		String temp = query.toLowerCase();
		if (Integer.toString(membershipNumber).contains(temp)) return true;
		if (firstName.toLowerCase().contains(temp)) return true;
		if (lastName.toLowerCase().contains(temp)) return true;
		if (Integer.toString(grade).contains(temp)) return true;
		if (school.toLowerCase().contains(temp)) return true;
		if (state.toLowerCase().contains(temp)) return true;
		if (email.toLowerCase().contains(temp)) return true;
		if (Integer.toString(yearJoined).contains(temp)) return true;
		if (Double.toString(amountOwed).contains(temp)) return true;
		
		return false;
	}
	
	/**
	 * Generates a String[] that represents this member
	 * @return Data in <code>String[]</code>
	 */
	public String generateHTMLStandardRow() {
		return new String(
				"<tr>" +
				"<td>" + membershipNumber + "</td>" +
				"<td>" + lastName + ", " + firstName + "</td>" +
				"<td>" + grade + "</td>" +
				"<td>" + state + "</td>" +
				"<td>" + yearJoined + "</td>" +
				"<td>$" + new DecimalFormat("##.00").format(amountOwed) + "</td>" +
				"</tr>"
			);
	}
	
	/**
	 * Generates and formats data for this record in a report that can be printed
	 * @return A new String containing the record
	 */
	public String generatePrintableStandardRecord() {
		return new String(
				"==================================================\r\n" +
				"Membership Number: " + membershipNumber + "\r\n" +
				"Name:              " + lastName + ", " + firstName + "\r\n" +
				"Grade:             " + grade + "\r\n" +
				"State:             " + state + "\r\n" +
				"Year Joined:       " + yearJoined + "\r\n" +
				"Amount Owed:       " + new DecimalFormat("##.00").format(amountOwed) + "\r\n" +
				"==================================================\r\n"
			);
	}
	
	/**
	 * Generates an HTML table row containing this record for a report
	 * @return The HTML data containing this record
	 */
	public String generateHTMLSeniorRow() {
		return new String(
				"<tr>" +
				"<td>" + membershipNumber + "</td>" +
				"<td>" + lastName + ", " + firstName + "</td>" +
				"<td>" + state + "</td>" +
				"<td>" + email + "</td>" +
				"</tr>"
			);
	}
	
	/**
	 * Generates and formats data for this record in a senior report that can be printed
	 * @return A new String containing the record
	 */
	public String generatePrintableSeniorRecord() {
		return new String(
				"==================================================\r\n" +
				"Membership Number: " + membershipNumber + "\r\n" +
				"Name:              " + lastName + ", " + firstName + "\r\n" +
				"Email:             " + email + "\r\n" +
				"State:             " + state + "\r\n" +
				"==================================================\r\n"
			);
	}
	
	/**
	 * Returns array of table data for JTable
	 * @return new Object[]
	 */
	public Object[] getTableData() {
		return new Object[] {
			membershipNumber,
			firstName,
			lastName,
			grade,
			school,
			state,
			email,
			yearJoined,
			amountOwed,
			(active) ? "Yes" : "No"
		};
	}
	
}