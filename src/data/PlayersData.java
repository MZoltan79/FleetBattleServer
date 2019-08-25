package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fleetbattle.model.Player;

public class PlayersData {
	
	private static PlayersData instance;
	
	private HashMap<String, String> authentication;
	ArrayList<Player> players;
	
	private PlayersData() {
		authentication = new HashMap<>();
		players = new ArrayList<>();
	}
	
	public static PlayersData getInstance() {
		if(instance == null) {
			instance = new PlayersData();
		}
		return instance;
	}

	public HashMap<String, String> getAuthentication() {
		return authentication;
	}

	public void setAuthentication(HashMap<String, String> authentication) {
		this.authentication = authentication;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void addAccount(String nickName, String passwrd) {
		authentication.put(nickName, passwrd);
	}
	
	public void loadData() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("player_stats.txt"));
			while(br.ready()) {
				String tmp[] = br.readLine().split(";");
				Player player = new Player();
				player.setNickName(tmp[0]);
				player.setGamesPlayed(Integer.parseInt(tmp[1]));
				player.setGamesWon(Integer.parseInt(tmp[2]));
				players.add(player);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("accounts.txt"));
			while(br.ready()) {
				String line = br.readLine();
				String[] tmp = line.split(";");
				authentication.put(tmp[0], tmp[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter("player_stats.txt",true));
			players.forEach(e -> pw.println(e));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveMap() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter("accounts.txt",true));
			for(Map.Entry<String, String> e: authentication.entrySet()) {
			pw.println(e.getKey()+";"+e.getValue());
		}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
