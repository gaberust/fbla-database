package grust.fbla;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

/**
 * Class <code>FBLAMemberList</code> represents an <code>ArrayList</code> of FBLA Members
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class FBLAMemberList extends ArrayList<FBLAMember> {

	/**
	 * Private field <code>serialVersionUID</code>
	 * is to be used by <code>java.io.Serializable</code>
	 */
	private static final long serialVersionUID = -3812445056479542898L;

	/**
	 * Inserts a new <code>FBLAMember</code> into the <code>FBLAMemberList</code>
	 * @param m The new FBLAMember to be inserted
	 */
	public void insert(FBLAMember m) {
		if (isEmpty()) {
			add(m);
			return;
		}
		for (int i = 0; i < size(); i ++) {
			int tempNum = get(i).getMembershipNumber();
			int mNum = m.getMembershipNumber();
			if (mNum < tempNum) {
				add(i, m);
				break;
			}
			if (mNum == tempNum) {
				int selection = JOptionPane.showConfirmDialog(null, "A member with this ID already exists.\nAre you sure you want to replace it?", "Continue?", JOptionPane.OK_CANCEL_OPTION);
				if (selection == JOptionPane.OK_OPTION) {
					set(i, m);
				}
				break;
			}
			if (i == size() - 1) {
				add(m);
				break;
			}
		}
	}
	
	/**
	 * Searches each <code>FBLAMember</code> for multiple queries
	 * @param  queries A list of queries
	 * @param  strict  Whether a strict or casual search should be conducted
	 * 		   Details can be found in FBLAMember.java
	 * @return A list of members found
	 */
	public SearchInfo search(String[] queries, boolean strict) {
		SearchInfo info = new SearchInfo();
		for (int i = 0; i < size(); i ++) {
			FBLAMember temp = get(i);
			if (temp.search(queries, strict)) {
				info.results.add(temp);
				info.indeces.add(i);
			}
		}
		return info;
	}
	
	/**
	 * Creates an XLSX document representing a report of FBLA Senior Members
	 * @return A new XLSX document
	 */
	public void createXLSXSeniorReport(File f) {
		FBLAMemberList seniors = getSeniors();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("FBLA SENIORS");
		XSSFRow title = sheet.createRow(0);
		title.createCell(0).setCellValue("FBLA SENIOR MEMBER REPORT");
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
		XSSFRow header = sheet.createRow(1);
		header.createCell(0).setCellValue("MEMBERSHIP NUMBER");
		header.createCell(1).setCellValue("NAME");
		header.createCell(3).setCellValue("EMAIL");
		
		for (int i = 0; i < seniors.size(); i ++) {
			FBLAMember temp = seniors.get(i);
			XSSFRow record = sheet.createRow(i + 2);
			record.createCell(0).setCellValue(temp.getMembershipNumber());
			record.createCell(1).setCellValue(temp.getLastName() + ", " + temp.getFirstName());
			record.createCell(2).setCellValue(temp.getEmail());
		}
		
		sheet.autoSizeColumn(0, true);
		sheet.autoSizeColumn(1, true);
		sheet.autoSizeColumn(2, true);
		
		try {
			FileOutputStream out = new FileOutputStream(f);
			workbook.write(out);
			workbook.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Due to an IO error,\nthe file could not be saved.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Generates and formats a String representing a report that can be printed
	 * @return A String containing the report
	 */
	public String generatePrintableStandardReport() {
		String report = "FBLA MEMBER REPORT\r\n";
		
		int activeCount = 0, inactiveCount = 0;
		double amountOwed = 0;
		
		for (int i = 0; i < size(); i ++) {
			FBLAMember temp = get(i);
			if (temp.getAmountOwed() > 0) {
				if (temp.isActive()) {
					activeCount ++;
				}
				else {
					inactiveCount ++;
				}
				amountOwed += temp.getAmountOwed();
				report += temp.generatePrintableStandardRecord();
			}
		}
		
		report += "==================================================\r\n";
		report += "Total Number Of Active Owing Members:   " + Integer.toString(activeCount) + "\r\n";
		report += "Total Number Of Inactive Owing Members: " + Integer.toString(inactiveCount) + "\r\n";
		report += "Total Number Of Owing Members:          " + Integer.toString(activeCount + inactiveCount) + "\r\n";
		report += "Total Amount Owed:                      $" + new DecimalFormat("##.00").format(amountOwed) + "\r\n";
		report += "==================================================";
		
		return report;
	}
	
	/**
	 * Generates an HTML table representing a report
	 * @return A new String containing the HTML data
	 */
	public String generateHTMLStandardReport() {
		String report = "<html><body><table><tr><th colspan = '6'>FBLA MEMBER REPORT</th></tr>";
		
		report += "<tr><th>NUMBER</th><th>NAME</th><th>GRADE</th><th>STATE</th><th>YEAR</th><th>OWED</th></tr>";
		
		int activeCount = 0, inactiveCount = 0;
		double amountOwed = 0;
		
		for (int i = 0; i < size(); i ++) {
			FBLAMember temp = get(i);
			if (temp.getAmountOwed() > 0) {
				if (temp.isActive()) {
					activeCount ++;
				}
				else {
					inactiveCount ++;
				}
				amountOwed += temp.getAmountOwed();
				report += temp.generateHTMLStandardRow();
			}
		}
		
		report += "<tr><td colspan = '5'>Total Number Of Active Owing Members:</td><td>" + Integer.toString(activeCount) + "</td></tr>";
		report += "<tr><td colspan = '5'>Total Number Of Inactive Members:</td><td>" + Integer.toString(inactiveCount) + "</td></tr>";
		report += "<tr><td colspan = '5'>Total Number Of Owing Members:</td><td>" + Integer.toString(activeCount + inactiveCount) + "</td></tr>";
		report += "<tr><td colspan = '5'>Total Amount Owed:</td><td>$" + new DecimalFormat("##.00").format(amountOwed) + "</td></tr>";
		
		report += "</table></body></html>";
		
		return report;
	}
	
	/**
	 * Generates and formats a String representing a senior report that can be printed
	 * @return A String containing the report
	 */
	public String generatePrintableSeniorReport() {
		String report = "FBLA SENIOR MEMBER REPORT\r\n";
		
		FBLAMemberList seniors = getSeniors();
		
		for (int i = 0; i < seniors.size(); i ++) {
			report += seniors.get(i).generatePrintableSeniorRecord();
		}
		
		return report;
	}
	
	/**
	 * Generates an HTML table representing a senior report
	 * @return A new String containing the HTML data
	 */
	public String generateHTMLSeniorReport() {
		String report = "<html><body><table><tr><th colspan = '4'>FBLA SENIOR MEMBER REPORT</th></tr>";
		
		report += "<tr><th>NUMBER</th><th>NAME</th><th>STATE</th><th>EMAIL</th></tr>";
		
		FBLAMemberList seniors = getSeniors();
		
		for (int i = 0; i < seniors.size(); i ++) {
			report += seniors.get(i).generateHTMLSeniorRow();
		}
		
		report += "</table></body></html>";
		
		return report;
	}
	
	/**
	 * Returns table data representing this list
	 * @return new Object[][]
	 */
	public Object[][] getTableData() {
		Object[][] data = new Object[size()][];
		for (int i = 0; i < size(); i ++) {
			data[i] = get(i).getTableData();
		}
		return data;
	}
	
	/**
	 * @return An FBLAMemberList of all seniors
	 */
	private FBLAMemberList getSeniors() {
		FBLAMemberList seniors = new FBLAMemberList();
		for (int i = 0; i < size(); i ++) {
			FBLAMember temp = get(i);
			if (temp.getGrade() == 12) {
				seniors.add(temp);
			}
		}
		return seniors;
	}
	
	/**
	 * Reads a file into this <code>FBLAMemberList</code>
	 * @param path File path to the file to be written
	 * @param name Name of the file to be written
	 */
	public void save(String path, String name) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
		Util.writeObjectToFile(this, path + "/" + name);
	}
	
}