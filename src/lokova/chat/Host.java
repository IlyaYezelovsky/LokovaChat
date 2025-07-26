package lokova.chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Host {
	
	private JFrame frame;
	private JPanel panel;
	private JTextField portField;
	private JPasswordField passwordField;
	
	public void go() {
		frame = new JFrame("Host chatroom");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel portLabel = new JLabel("Port");
		JLabel passwordLabel = new JLabel("Password");
		
		portField = new JTextField(20);
		passwordField = new JPasswordField(20);
		
		JButton hostButton = new JButton("Host");
		JButton cancelButton = new JButton("Cancel");
		hostButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				host();
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Start.main(null);
			}
		});
		
		JPanel portPanel = new JPanel();
		JPanel passwordPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		portPanel.add(portLabel);
		portPanel.add(portField);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		buttonPanel.add(hostButton);
		buttonPanel.add(cancelButton);
		
		panel.add(portPanel);
		panel.add(passwordPanel);
		panel.add(buttonPanel);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350, 200);
		frame.setVisible(true);
	}
	
	private void host() {
		Server server = new Server(Integer.parseInt(portField.getText()), passwordField.getPassword());
		server.go();
	}
	
}
