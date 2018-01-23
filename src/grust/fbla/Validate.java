package grust.fbla;

import java.util.regex.*;

/**
 * Class <code>Validate</code> validates user input
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class Validate {
	
	/**
	 * Private constant <code>FIRST_YEAR</code>
	 * represents the year the first FBLA high school chapter was founded
	 */
	private static final int FIRST_YEAR = 1942;
	
	/**
	 * Private constant <code>EMAIL_PATTERN</code>
	 * represents patterns to be checked for when validating email addresses
	 */
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	/**
	 * Validates a string as a membership number
	 * @param  str The string to be validated
	 * @return Whether or not the string is valid
	 */
	public static boolean validateMembershipNumber(String str) {
		try {
			if (Integer.parseInt(str) > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Validates a string as a name
	 * @param  str The string to be validated
	 * @return Whether or not the string is valid
	 */
	public static boolean validateName(String str) {
		for (int i = 0; i < str.length(); i ++) {
			if (str.charAt(i) != ' ') {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validates a string as an email
	 * @param  str The string to be validated
	 * @return Whether or not the string is valid
	 */
	public static boolean validateEmail(String str) {
		Matcher matcher = EMAIL_PATTERN.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * Validates a string as a year a member joined
	 * @param  str The string to be validated
	 * @return Whether or not the string is valid
	 */
	public static boolean validateYearJoined(String str) {
		try {
			if (Integer.parseInt(str) >= FIRST_YEAR) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Validates a string as a positive decimal
	 * @param  str The string to be validated
	 * @return Whether or not the string is valid
	 */
	public static boolean validateAmountOwed(String str) {
		try {
			if (Double.parseDouble(str) >= 0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
}
