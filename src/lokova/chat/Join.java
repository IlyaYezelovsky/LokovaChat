package lokova.chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Join {
	
	private JFrame frame;
	private JPanel panel;
	
	public void go() {
		frame = new JFrame("Join chatroom");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel usernameLabel = new JLabel("Username");
		JLabel ipLabel = new JLabel("Server IP");
		JLabel passwordLabel = new JLabel("Password");
		
		JTextField usernameField = new JTextField(10);
		JTextField ipField = new JTextField(10);
		JPasswordField passwordField = new JPasswordField(10);
		
		JButton joinButton = new JButton("Join");
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Start.main(null);
			}
		});
		
		JPanel usernamePanel = new JPanel();
		JPanel ipPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		ipPanel.add(ipLabel);
		ipPanel.add(ipField);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		buttonPanel.add(joinButton);
		buttonPanel.add(cancelButton);
		
		panel.add(usernamePanel);
		panel.add(ipPanel);
		panel.add(passwordPanel);
		panel.add(buttonPanel);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350, 200);
		frame.setVisible(true);
	}
	
	private void join() {
		
	}
	
}
