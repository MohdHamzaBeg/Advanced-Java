// This class is to make up my swing window using Jframe class

package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Mainframe extends JFrame {

	private static final long serialVersionUID = 1L; // Important to implement Jframe class

	public Mainframe() {
		super("Swing Demo");

		setJMenuBar(createMenu());
		setSize(1000, 800); // Sets the size of the frame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // To close the whole program together
		setVisible(true); // To run the mainframe
	}

	private JMenuBar createMenu() {
		var menuBar = new JMenuBar(); // JMenuBar class adds menu to our frame
		var fileMenu = new JMenu("File");// Jmenu class is a heading which will be visible in the menu
		var exititem = new JMenuItem("Exit"); // JmenuItem class adds the items to our Jmenu headings
		fileMenu.add(exititem);
		menuBar.add(fileMenu);
		exititem.addActionListener(e->System.exit(0));
		return menuBar;
}
}