package dk.dtu.cdio2;

import java.util.HashMap;

public class PlayerManager {

    //***************************************************************//
    // We create a HashMap to keep the players and their numbers.
    //***************************************************************//
    private HashMap<Integer, Player> players = new HashMap<>();

    class Player {

        private final String name;

        Player (String name) {
            this.name = name;
        }

        public void withdrawMoney(int m) {
            Money += m;
        }

        public void setMoney(int newMoney) {
            Money = newMoney;
        }

        public int getMoney() {
            return Money;
        }


    }

    public Player createPlayer() {
        Player player = new Player();
        players.put(players.size(), player);
        return player;
    }
}
