package lokova.chat;

import java.net.*;
import java.io.*;
import java.util.*;
import ilya.util.*;

public class Server {

	int port;
	char[] password;
	ArrayList<PrintWriter> clientOutputStreams;
    TreeSet<String> onlinePlayers = new TreeSet<String>();
	
	public Server(int p, char[] pass) {
		port = p;
		password = pass;
	}

	class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;
		String clientIP;
		String clientUsername;
		char[] clientPassword;

		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				clientIP = sock.getInetAddress().getHostAddress();
				ObjectInputStream accountStream = new ObjectInputStream(sock.getInputStream());
				clientUsername = (String) accountStream.readObject();
				clientPassword = (char[]) accountStream.readObject();
				if (!onlinePlayers.add(clientUsername) || clientPassword != password) {
					sock.close();
				}
				accountStream.close();
				accountStream = null;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			} catch (Exception e) {
				Msgbox.error(e);
			}
		}

		public void run() {
			String msg;
			try {
				while ((msg = reader.readLine()) != null) {
					tell(msg);
				}
			} catch (IOException e) {
//				System.out.println(clientIP + " disconnected.");
			} finally {
				try {
					if (reader != null)
						reader.close();
					if (sock != null)
						sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				synchronized (clientOutputStreams) {
					Iterator<PrintWriter> it = clientOutputStreams.iterator();
					while (it.hasNext()) {
						try {
							PrintWriter writer = it.next();
							if (writer.checkError()) {
								it.remove();
							}
						} catch (Exception e) {
							it.remove();
						}
					}
				}
				tell("[System] Client " + clientIP + " left the chat.");
			}
		}
	}

	public void tell(String msg) {
		synchronized (clientOutputStreams) {
			Iterator<PrintWriter> it = clientOutputStreams.iterator();
			while (it.hasNext()) {
				try {
					PrintWriter writer = it.next();
					writer.println(msg);
					writer.flush();
					if (writer.checkError()) {
						it.remove();
					}
				} catch (Exception e) {
					it.remove();
				}
			}
		}
	}

	public void go() {
		try {
			clientOutputStreams = new ArrayList<PrintWriter>();
			ServerSocket serverSock = new ServerSocket(port);
//			System.out.println("Successfully started server on port " + port);
			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				synchronized (clientOutputStreams) {
					clientOutputStreams.add(writer);
				}
				new Thread(new ClientHandler(clientSocket)).start();
				String clientIP = clientSocket.getInetAddress().getHostAddress();
				tell("[System] Client " + clientIP + " joined the chat.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		Server s = new Server();
//		s.go();
//	}

}

