package data;

import java.util.Map;

import fleetbattle.model.Player;

public class Tester {

	public static void main(String[] args) {
		PlayersData pd = PlayersData.getInstance();
		pd.addAccount("Player1", "12345");
		
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
