package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new GUI("SuperMart Management"));
	}

}
