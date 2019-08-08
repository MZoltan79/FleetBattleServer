package data;

import java.util.Map;

import fleetbattle.model.Player;

public class Tester {

	public static void main(String[] args) {
		PlayersData pd = PlayersData.getInstance();
		Player zoli = new Player();
		zoli.setNickName("Zoli");
		zoli.setGamesPlayed(5);
		zoli.setGamesWon(4);
		System.out.println(zoli);
		pd.addPlayer(zoli);
		pd.addAccount("Zoli", "1234");
		pd.addAccount("Feri", "4321");
		for(Map.Entry<String, String> e: pd.getAuthentication().entrySet()) {
			System.out.println(e.getKey()+";"+e.getValue());
		}
		pd.saveMap();
		pd.saveData();
		pd.loadData();
		pd.loadMap();
		pd.getPlayers().forEach(e -> System.out.println(e));
		for(Map.Entry<String, String> e: pd.getAuthentication().entrySet()) {
			System.out.println(e.getKey()+";"+e.getValue());
		}
		
		
	}

}
