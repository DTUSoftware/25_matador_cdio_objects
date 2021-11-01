package dk.dtu.matador_objects.managers;

import dk.dtu.matador_objects.Player;
import java.util.HashMap;

public class PlayerManager {
    public PlayerManager() {}

    /**
     * We create a HashMap to keep the players and their numbers.
     */
    private HashMap<Integer, Player> players = new HashMap<>();

    public Player createPlayer(String name) {
        int ID = players.size()+1;
        Player player = new Player(name, ID);
        players.put(player.getID(), player);
        return player;
    }

    public Player createPlayer(String name, int startingBalance) {
        int ID = players.size()+1;
        Player player = new Player(name, ID, startingBalance);
        players.put(player.getID(), player);
        return player;
    }

    public Player getPlayer(int playerID) {
        return players.get(playerID);
    }

    public Integer[] getPlayerIDs() {
        return players.keySet().toArray(new Integer[0]);
    }
}
