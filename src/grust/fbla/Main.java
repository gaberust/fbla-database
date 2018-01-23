package grust.fbla;

import java.awt.EventQueue;

/**
 * Class <code>Main</code> starts the program
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class Main {
	
	/**
	 * Starting point of the program
	 * @param args A list of program arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
	
}