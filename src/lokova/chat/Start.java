package lokova.chat;

import javax.swing.*;
import java.awt.*;

public class Start {
	
	private JFrame frame;
	private JPanel panel;
	
	public static void main(String[] args) {
		Start start = new Start();
		start.go();
	}
	
	private void go() {
		frame = new JFrame("LokovaChat v0.1.0");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton joinButton = new JButton("Join");
		JButton hostButton = new JButton("Host");
		JButton aboutButton = new JButton("About");
		panel.add(joinButton);
		panel.add(hostButton);
		panel.add(aboutButton);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}

}
