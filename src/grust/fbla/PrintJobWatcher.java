package grust.fbla;

import javax.print.DocPrintJob;
import javax.print.event.*;

/**
 * Class <code>PrintJobWatcher</code> watches a print job and pauses the program until the print job is done
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class PrintJobWatcher {
	
	/**
	 * Private field <code>done</code>
	 * represents whether the print job is done
	 */
	private boolean done = false;
	
	/**
	 * creates a new PrintJobWatcher
	 * @param job The print job to be watched
	 */
	public PrintJobWatcher(DocPrintJob job) {
		job.addPrintJobListener(new PrintJobAdapter() {
			
			@Override
			public void printJobCanceled(PrintJobEvent e) {
				done();
			}
			
			@Override
			public void printJobCompleted(PrintJobEvent e) {
				done();
			}
			
			@Override
			public void printJobFailed(PrintJobEvent e) {
				done();
			}
			
			@Override
			public void printJobNoMoreEvents(PrintJobEvent e) {
				done();
			}
			
		});
	}
	
	/**
	 * Notifies that the print job is done
	 */
	private synchronized void done() {
		done = true;
		notify();
	}
	
	/**
	 * Pauses program until print job is done
	 * Waits until it is notified to resume program
	 */
	public synchronized void waitForDone() {
		try {
			while (!this.done) {
				wait();
			}
		}
		catch (InterruptedException e) {
			//DO NOTHING
		}
	}
	
}
