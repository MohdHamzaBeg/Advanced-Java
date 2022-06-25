// Adding a panel to my window using Jpanel
// ***Warning- Whenever we add an attribute of the GridBagLayout, it gets applied till the end of the code. That is why we need to be careful while adding an attribute
// and reset that attribute to default where we do not want its effect.
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private UserFormListener formListener; // Creating an Object of the custom form listener

	public MainPanel() {

		// Adding componenets to our Application using a Layout Manager named as
		// GridBagLayout
		// Setting components
		var formLabel = new JLabel("Add User");
		formLabel.setFont(new Font("TimesNewRoman", Font.ITALIC, 40));

		// Adding components by giving the plane dimensions to our grid
		setLayout(new GridBagLayout());
		var gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weighty = 1; // Difference here is that we do not need to repeat this again and again for
						// different rows until we want to change. weightx is not in effect here because
						// anchor attribute is more effective as it is applied after this attribute
		add(formLabel, gc);
		gc.weighty = 1.5;
		gc.gridy++;
		gc.anchor = GridBagConstraints.NORTH;
		add(createpanel(), gc);
	}

	// This method will accept the formlistener which I created in UserformListener
	// class
	public void setformListener(UserFormListener formlistener) {
		this.formListener = formlistener;
	}

	// Creating a subpanel which will be added to the main panel which only contains
	// the form label. We are doing this so that our code do not get
	// clusterred up and we can differentiate between a number of panels easily
	private JPanel createpanel() {
		JPanel panel = new JPanel();

		// Setting two borders out which one will take specified space from the second
		// from every direction
		var padding = 20;
		var etchedborder = BorderFactory.createEtchedBorder();
		var emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
		panel.setBorder(BorderFactory.createCompoundBorder(etchedborder, emptyBorder));

		var nameLabel = new JLabel("Name: ");
		var passLabel = new JLabel("Password: ");

		var nameField = new JTextField(15);
		var passField = new JTextField(15);

		var addButton = new JButton("Save");
		addButton.addActionListener(e -> {
			String username = nameField.getText();
			String password = passField.getText();
			if (formListener != null) {
				formListener.formSubmitted(username, password); // observer pattern
			}
		});

		var gc = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		var rightPad = new Insets(0, 0, 0, 10);
		var zeroPad = new Insets(0, 0, 0, 0);

		gc.gridwidth = 1;

		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END; // Anchor is not effective here as inset is now applied
		gc.insets = rightPad;
		panel.add(nameLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START; // This line is added to overcome the effect of the line no. 46 on
													// the rest of the code
		gc.insets = zeroPad; // 53
		panel.add(nameField, gc);

		gc.gridy++;
		gc.gridx = 0;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPad;
		panel.add(passLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = zeroPad; // 66
		panel.add(passField, gc);

		gc.gridy++;
		gc.weighty = 30;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		panel.add(addButton, gc);

		return panel;
	}
}
