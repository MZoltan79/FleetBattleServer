package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameThread implements Runnable {

	Socket sender;
	Socket receiver;

	public GameThread(Socket sender, Socket receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	@Override
	public void run() {
		if(sender.isConnected()) System.out.println("GameThread sender is connected");
		if(receiver.isConnected()) System.out.println("GameThread receiver is connected");

		try {
			PrintWriter send = new PrintWriter(sender.getOutputStream(), true);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(receiver.getInputStream()));
			
			while (true) {
				String msg = br.readLine();
				System.out.println("server data: " + msg);
				send.println(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
