package lokova.chat;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import ilya.util.Msgbox;

public class Chatroom {
	
	private JFrame frame;
	private JPanel panel;
	JTextArea msgArea;
	private JTextField inputField;
	private String serverIP;
	private Client client;
	
	public Chatroom(String ip, Client cl) {
		serverIP = ip;
		client = cl;
	}
	
	public void go() {
		frame = new JFrame("Chatroom on " + serverIP);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		msgArea = new JTextArea(30, 100);
		msgArea.setLineWrap(true);
		msgArea.setWrapStyleWord(true);
		msgArea.setEditable(false);

		JScrollPane scroller = new JScrollPane(msgArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		inputField = new JTextField(25);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				send();
			}
		});

		JButton exportButton = new JButton("Export chat");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				export();
			}
		});

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
//				quit();
				frame.dispose();
				Start.main(null);
			}
		});

		JPanel sendPanel = new JPanel();
		sendPanel.add(inputField);
		sendPanel.add(sendButton);
		sendPanel.add(exportButton);
		sendPanel.add(exitButton);

		panel.add(scroller);
		panel.add(sendPanel);
	}
	
	private void export() {
		JFileChooser chooser = new JFileChooser();
		File file;
		chooser.showSaveDialog(frame);
		if ((file = chooser.getSelectedFile()) != null) {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(msgArea.getText());
				writer.close();
			} catch (IOException e) {
				Msgbox.error("Failed exporting chat", e);
			}
		}
	}
	
	private void send() {
		client.send(inputField.getText());
	}
	
	private void quit() {
		client.quit();
	}
	
}
