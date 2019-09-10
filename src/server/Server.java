package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;




public class Server {

	static ServerSocket ss;
	public static boolean connected = false;
	private static int port = 11111;
	
	@SuppressWarnings("resource")
	public static void start() {
		String str = " ";
		Scanner sc = new Scanner(System.in);
		System.out.println("\nTo continue, type \"start\", than hit ENTER. To change port, type \"port\", than ENTER.");
		str = sc.next();
		if(str.equals("port")) {
			System.out.println("What should be the port number?");
			port = sc.nextInt();
			System.out.println("The new port is: " + port);
			try {
				ss = new ServerSocket(port);
				System.out.println("Server started");
//				while(true) {
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
//				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(str.equals("start") || str == null) {
		try {
			ss = new ServerSocket(port);
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
		
	} else {
		
		System.out.println("You misstyped probably... Try again!");
		start();
	}
		sc.close();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the FleetBattle game server!\nThe port is 11111 by default.");
		start();
	}

}
