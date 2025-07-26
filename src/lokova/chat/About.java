package lokova.chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About {
	
	private JFrame frame;
	private JPanel panel;
	
	public void go() {
		frame = new JFrame("About");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new JPanel();
		
		JTextArea text = new JTextArea(5, 5);
		text.setText("LokovaChat v0.1.0 by IlyaYezelovsky\nA simple uncentralized chat program\nGitHub: https://github.com/IlyaYezelovsky/LokovaChat");
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		panel.add(text);
		panel.add(ok);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350, 170);
		frame.setVisible(true);
	}

}
