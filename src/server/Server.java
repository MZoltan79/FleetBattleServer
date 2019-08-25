package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	static ServerSocket ss;
	public static boolean connected = false;

	public static void main(String[] args) {
		
		try {
			ss = new ServerSocket(11111);
			System.out.println("Server started");
//			while(true) {
			Socket clientSocket1 = new Socket();
			Socket clientSocket2 = new Socket();
			System.out.println("clientsocket1 connected: " + clientSocket1.isConnected());
				clientSocket1 = ss.accept();
				System.out.println("clientsocket1 connected: " + clientSocket1.isConnected());
				System.out.println("clientsocket2 connected: " + clientSocket2.isConnected());
				clientSocket2 = ss.accept();
				System.out.println("clientsocket2 connected: " + clientSocket2.isConnected());
				if(clientSocket1.isConnected()) System.out.println("1 csatlakozva");
				if(clientSocket2.isConnected()) System.out.println("2 csatlakozva");
				
				Thread th = new Thread(new GameThread(clientSocket1, clientSocket2));
				th.start();
				Thread th2 = new Thread(new GameThread(clientSocket2, clientSocket1));
				th2.start();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
