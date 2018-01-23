package grust.fbla;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class <code>StandardReportDialog</code> represents a JDialog for viewing a standard report
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class StandardReportDialog extends JDialog {
	
	/**
	 * Private field <code>contentPane</code>
	 * represents the Content Pane of the JDialog
	 */
	private final JPanel contentPane = new JPanel();
	
	/**
	 * Creates a new StandardReportDialog and blocks input to other windows while active
	 * @param members The List of members for which the report will be displayed
	 */
	public StandardReportDialog(final FBLAMemberList members) {
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		final JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setText(members.generateHTMLStandardReport());
		scrollPane.setViewportView(textPane);
		
		JPanel lowerPanel = new JPanel();
		contentPane.add(lowerPanel, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("C:/"));
				int approved = fileChooser.showSaveDialog(null);
				if (approved == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					try {
						FileOutputStream fos = new FileOutputStream(f);
						byte[] data = members.generatePrintableStandardReport().getBytes();
						fos.write(data);
						fos.close();
					}
					catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error", "Could not write to file.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		lowerPanel.add(saveButton);
		
		JButton printButton = new JButton("PRINT");
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Util.print(members.generatePrintableStandardReport());
			}
		});
		lowerPanel.add(printButton);
		
		pack();
		if (getHeight() > 600) {
			setSize(getWidth(), 600);
		}
		setLocationRelativeTo(null);
	}

}
