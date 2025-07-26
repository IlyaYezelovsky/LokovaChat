package lokova.chat;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.*;
import ilya.util.*;

public class Client {
	
	private String serverIP;
	private int serverPort;
	private Socket sock;
	private BufferedReader reader;
	private PrintWriter writer;
	private InputStreamReader streamReader;
	boolean using = true;
	private Chatroom room;
	private Thread readThread;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (EEE) HH:mm:ss");
	private String username;
	private char[] password;
	
	public Client(String ip, int port, String name, char[] pass) {
		username = name;
		serverIP = ip;
		serverPort = port;
		password = pass;
	}
	
	public void join() {
		try {
			sock = new Socket(serverIP, serverPort);
			streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
//			readThread = new Thread(new IncomingReader());
//			readThread.start();
			room = new Chatroom(serverIP + ":" + serverPort, this);
			room.go();
		} catch (UnknownHostException e) {
			Msgbox.error("Unknown host");
		} catch (IOException e) {
			Msgbox.error("Failed establishing connection", e);
		}
	}
	
	void quit() {
		try {
			reader.close();
			writer.close();
			streamReader.close();
			sock.close();
		} catch (IOException e) {
			Msgbox.error(e);
		}
	}
	
	void send(String msg) {
		String time = LocalDateTime.now().format(formatter);
		writer.write("[" + time + "]<" + username + "> " + msg);
		writer.flush();
	}
	
	private class IncomingReader implements Runnable {
		@Override
		public void run() {
			String msg;
			while (using) {
				try {
					while ((msg = reader.readLine()) != null) {
						room.msgArea.append(msg + "\n");
					}
				} catch (IOException e) {
//					TODO This is a bug that client cannot exit gracefully
					Msgbox.info("This is a known bug that an IOException is always thrown when the client quits. We are working on resolving it.");
				}
			}
		}
	}

}
