package dk.dtu.cdio2;

import java.util.HashMap;
import dk.dtu.cdio2.AccountManager;

public class PlayerManager {

    /**
     * We create a HashMap to keep the players and their numbers.
     */

    private HashMap<Integer, Player> players = new HashMap<>();

    private static class Player {

        private final String name;
        private int money = 0;

        Player (String name) {
            this.name = name;
        }

        public void withdrawMoney(int m) {
            money += m;
        }

        public void setMoney(int newMoney) {
            money = newMoney;
        }

        public int getMoney() {
            return money;
        }


    }

    public Player createPlayer() {
        Player player = new Player();
        players.put(players.size(), player);
        AccountManager.createAccount(0);
        return player;
    }
}
