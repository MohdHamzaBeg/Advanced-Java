package controller;

import model.SwingDatabase;
import model.User;
import model.UserDao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import gui.MainPanel;
import gui.Mainframe;

public class Controller {
	private Mainframe mainframe;
	private MainPanel mainpanel;

	public Controller() throws SQLException {
		SwingDatabase.ins().getconnection();
		System.out.println("Database is connected now");
		UserDao userdao = new UserDao();

		mainpanel = new MainPanel();
		mainpanel.setformListener((username, password) -> { // observer pattern
			System.out.println(username + " : " + password);
			userdao.save(new User(username, password));
		});

		mainframe = new Mainframe(); // Making an object of the mainframe class to connect it with the app
		mainframe.setContentPane(mainpanel); // Setting the mainframe to add our content
		mainframe.addWindowListener(new WindowAdapter() { // This anonymous class and the windowclosing method is used
															// to make the database close at the time of closing the
															// window
			@Override
			public void windowClosing(WindowEvent e) {

				try {
					SwingDatabase.ins().disconnect();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Cannot close the database");
				}
				System.out.println("Database is disconnected now");
			}

		});

	}
}