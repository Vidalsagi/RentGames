import java.awt.EventQueue;

import javax.swing.*;

import view.Login;

public class Main {

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
					Login window = new Login();
					window.frame.setVisible(true);
				}
			});
		}
	}