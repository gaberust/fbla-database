package grust.fbla;

import java.io.*;
import java.util.Scanner;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.swing.JOptionPane;

/**
 * Class <code>Util</code> contains a set of utility methods and constants
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class Util {
	
	/**
	 * Public constant <code>GRADES</code>
	 * contains a list of FBLA Member grades
	 */
	public static final String[] GRADES = loadResourceAsString("txt/grades");
	
	/**
	 * Public constant <code>STATES</code>
	 * contains a list of states
	 */
	public static final String[] STATES = loadResourceAsString("txt/states");
	
	/**
	 * Reads resources into a <code>String</code> array
	 * @param  path File path of the resource to read
	 * @return A new <code>String</code> array split by newlines in the resource file
	 */
	private static String[] loadResourceAsString(String path) {
		try {
			InputStream resource = ClassLoader.getSystemResource(path).openStream();
			Scanner in = new Scanner(resource, "UTF-8").useDelimiter("\\A");
			String[] out = in.next().split("\n");
			return out;
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "MISSING RESOURCE!\nEXITING PROGRAM!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Prints HTML data
	 * @param htmlContent Data to be printed
	 * @param service     Printer to be used
	 */
	public static void print(String content) {
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		attrs.add(new Copies(1));
		PrintDialog printDialog = new PrintDialog();
		printDialog.setVisible(true);
		PrintService selectedService = printDialog.getSelectedService();
		if (selectedService != null) {
			InputStream inputStream;
			try {
				inputStream = new ByteArrayInputStream(content.getBytes("UTF8"));
			}
			catch (UnsupportedEncodingException e) {
				JOptionPane.showMessageDialog(null, "Unable to print content", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Doc data = new SimpleDoc(inputStream, flavor, null);
			DocPrintJob printJob = selectedService.createPrintJob();
			try {
				PrintJobWatcher pjw = new PrintJobWatcher(printJob);
				printJob.print(data, attrs);
				pjw.waitForDone();
			}
			catch (PrintException e) {
				JOptionPane.showMessageDialog(null, "Unable to print content", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Print job canceled", "Message", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
	/**
	 * Writes an object to a file
	 * @param o    The object to be written
	 * @param path The path to the file to be written
	 */
	public static void writeObjectToFile(Object o, String path) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(o);
			out.close();
			fos.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error writing to file!\nCould not be saved!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Reads an object from a file 
	 * @param  path File path of object to be read
	 * @return The object read from the file
	 */
	public static Object readObjectFromFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			ObjectInputStream in = new ObjectInputStream(fis);
			Object o = in.readObject();
			in.close();
			fis.close();
			return o;
		}
		catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error!\nFile could not be read!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
}