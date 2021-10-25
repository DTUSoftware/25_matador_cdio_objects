package dk.dtu.cdio2;

import java.util.HashMap;

public class PlayerManager {

    private final static AccountManager am = new AccountManager(); //<- tror ikke på at det er nødvendigt at også havde den her

    /**
     * We create a HashMap to keep the players and their numbers.
     */

    private HashMap<Integer, Player> players = new HashMap<>();

    private static class Player {

        private final String name;
        private int pmAccountID = 0;

        Player (String name) {
            this.name = name;
        }

        public void withdrawMoney(int m) {
            am.withdraw(); //Mangler at connecte am med mp her
        }

        public void setMoney(int newMoney) {
            am.balance = newMoney; //Mangler at connecte am med mp her
        }

        public int getMoney() {
            return am.getMoney(); //Mangler at connecte am med mp her
        }

        public int getID() {
            return pmAccountID;
        }
    }


    public Player createPlayer(String name) {
        Player player = new Player(name);
        Integer ID = players.size()+1;
        players.put(ID, player);
        am.createAccount(ID, 0);
        return player;
    }
}
