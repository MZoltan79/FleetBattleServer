package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import data.Player;
import data.PlayersData;


public class GameThread implements Runnable {

	Socket sender;
	Socket receiver;
	PlayersData pd;
	int id;

	public GameThread(Socket sender, Socket receiver, int id) {
		this.sender = sender;
		this.receiver = receiver;
		this.id = id;
		pd = PlayersData.getInstance();
	}
	
	

	@Override
	public void run() {

		try {
			PrintWriter send = new PrintWriter(sender.getOutputStream(), true);
			PrintWriter own = new PrintWriter(receiver.getOutputStream(), true);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(receiver.getInputStream()));
			send.println("client" + id);
			while (true) {
				String msg = br.readLine();
				if(msg == null) {
					System.out.println("Opponent has disconnected!");
					break;
				}
				if(msg.equals("login")) {
					String nickName = br.readLine();
					String password = br.readLine();
					if(checkNickName(nickName)) {
						
					if(checkLoginData(nickName, password)) {
						own.println("login successed");
						for(Player p: pd.getPlayers()) {
							if(p.getNickName().equals(nickName)) {
								own.println(p);
								break;
							}
						}
					}
					}
				} else if(msg.equals("newplayer")) {
					String nickName = br.readLine();
					String password = br.readLine();
					if(checkNickName(nickName)) {
						own.println("Nickname is already in use, choose another!");
					} else {
						pd.getAuthentication().put(nickName, password);
						pd.getPlayers().add(new Player(nickName,0,0));
						pd.saveData();
						pd.saveMap();
						own.println("new player successed");
					}
				} else if(msg.equals("gameover")) {
					String str = br.readLine();
					String[] tmp = str.split(";");
					for(Player p: pd.getPlayers()) {
						if(p.getNickName().equals(tmp[0])) {
							p.setGamesPlayed(Integer.parseInt(tmp[1]));
							p.setGamesWon(Integer.parseInt(tmp[2]));
						}
						if(p.getNickName().equals(tmp[3])) {
							p.setGamesPlayed(Integer.parseInt(tmp[4]));
							p.setGamesWon(Integer.parseInt(tmp[5]));
						}
					}
					pd.saveData();
			
				} else {
					send.println(msg);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean checkNickName(String nickName) {
		for(Map.Entry<String, String> e: pd.getAuthentication().entrySet()) {
			if(e.getKey().equals(nickName)) return true;
		}
		return false;
		
	}

	private boolean checkLoginData(String nickName, String passWord) {
		if(pd.getAuthentication().get(nickName).equals(passWord)) return true;
		return false;
	}
}
