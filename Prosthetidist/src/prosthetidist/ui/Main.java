package prosthetidist.ui;

import javax.swing.JFrame;

import SwingWindows.AppDisplay;
import prosthetidist.jdbc.JDBCInvoiceManager;
import prosthetidist.jdbc.JDBCManager;

public class Main {
	
	
	public static void main(String args[]) {
		
		JDBCManager manager = new JDBCManager();
		JFrame appDisplay = new AppDisplay(manager);
		
	}
	
}

