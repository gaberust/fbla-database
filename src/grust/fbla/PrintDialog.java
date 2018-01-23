package grust.fbla;

import java.awt.BorderLayout;

import javax.print.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

/**
 * Class <code>PrintDialog</code> represents a JDialog for selecting a printer
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class PrintDialog extends JDialog {

	/**
	 * Private field <code>contentPane</code>
	 * represents the Content Pane of the JDialog
	 */
	private final JPanel contentPane = new JPanel();
	
	/**
	 * Private field <code>selectedService</code>
	 * represents the printer that the user selected
	 */
	private PrintService selectedService = null;
	
	/**
	 * Creates a new PrintDialog and blocks input to other windows while active
	 */
	public PrintDialog() {
		setModal(true);
		setTitle("Print");
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Select A Print Service:");
		contentPane.add(label, BorderLayout.NORTH);
		
		final JComboBox<PrintService> comboBox = new JComboBox<PrintService>(PrintServiceLookup.lookupPrintServices(null, null));
		contentPane.add(comboBox, BorderLayout.CENTER);
		
		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JButton printButton = new JButton("PRINT");
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedService = (PrintService) comboBox.getSelectedItem();
				dispose();
			}
		});
		lowerPanel.add(printButton, BorderLayout.WEST);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		lowerPanel.add(cancelButton, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	/**
	 * @return The printer service that the user selected
	 */
	public PrintService getSelectedService() {
		return selectedService;
	}

}
