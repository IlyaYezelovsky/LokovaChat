package ilya.util;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Msgbox {

	private Msgbox() {
		throw new AssertionError("No Msgbox instances for you!");
	}

	public static void info(String msg, String title) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		JTextArea message = new JTextArea(20, 40);
		message.setEditable(false);

		JScrollPane scroller = new JScrollPane(message);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JButton button = new JButton("OK");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				frame.dispose();
			}
		});

		message.setText(msg);
		panel.add(scroller);
		panel.add(button);
		frame.getContentPane().add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
	}

	public static void info(String msg) {
		info(msg, "");
	}

	public static void error(String msg) {
		info(msg, "Error");
	}

	public static void error(String msg, Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter writer = new PrintWriter(sw);
		e.printStackTrace(writer);
		info(msg + "\n" + sw.toString(), "Error");
	}

	public static void error(Exception e) {
		error("An error occured", e);
	}

//	For test
//	public static void main(String[] args) {
//		int[] test = new int[2];
//		try {
//			test[2] = 0;
//		} catch (Exception e) {
//			errorWithStackTrace("An error occured", e);
//		}
//	}

}
