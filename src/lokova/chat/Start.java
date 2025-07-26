package lokova.chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start {
	
	private JFrame frame;
	private JPanel panel;
	
	public static void main(String[] args) {
		Start start = new Start();
		start.go();
	}
	
	private void go() {
		frame = new JFrame("LokovaChat v0.1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("LokovaChat v0.1.0 by IlyaYezelovsky");
		JButton joinButton = new JButton("Join a chatroom");
		JButton hostButton = new JButton("Host a chatroom");
		JButton aboutButton = new JButton("About");
		JButton exitButton = new JButton("Exit");
		
		joinButton.addActionListener(new JoinListener());
		hostButton.addActionListener(new HostListener());
		aboutButton.addActionListener(new AboutListener());
		exitButton.addActionListener(new ExitListener());
		
		JPanel titlePanel = new JPanel();
		JPanel joinPanel = new JPanel();
		JPanel hostPanel = new JPanel();
		JPanel aboutPanel = new JPanel();
		JPanel exitPanel = new JPanel();
		
		titlePanel.add(titleLabel);
		joinPanel.add(joinButton);
		hostPanel.add(hostButton);
		aboutPanel.add(aboutButton);
		exitPanel.add(exitButton);
		
		panel.add(titleLabel);
		panel.add(joinPanel);
		panel.add(hostPanel);
		panel.add(aboutPanel);
		panel.add(exitPanel);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350, 200);
		frame.setVisible(true);
	}
	
	public class JoinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			Join join = new Join();
			join.go();
		}
	}
	
	public class HostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			Host host = new Host();
			host.go();
		}
	}
	
	public class AboutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			About about = new About();
			about.go();
		}
	}
	
	public class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
