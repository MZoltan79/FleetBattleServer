package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {
	
	static ServerSocket ss;
	public static boolean connected = false;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			ss = new ServerSocket(11111);
			System.out.println("Server started");
//			while(true) {
			Socket clientSocket1 = new Socket();
			Socket clientSocket2 = new Socket();
			
			clientSocket1 = ss.accept();
			System.out.println("client_1 connected: " + clientSocket1.isConnected());
			clientSocket2 = ss.accept();
			System.out.println("client_2 connected: " + clientSocket2.isConnected());
			
			Thread th = new Thread(new GameThread(clientSocket1, clientSocket2, 1));
			th.start();
			
				
			Thread th2 = new Thread(new GameThread(clientSocket2, clientSocket1, 2));
			th2.start();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
