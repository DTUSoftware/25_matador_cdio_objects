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
        private int playerID;
        private int accountID;

        Player (String name, int playerID) {
            this.name = name;
            this.playerID = playerID;
            this.accountID = am.createAccount().getAccountID();
        }

        public void withdrawMoney(double m) {
            am.getAccount(this.accountID).withdraw(m); //Mangler at connecte am med mp her
        }

        public void setMoney(int newMoney) {
            am.getAccount(this.accountID).setBalance(newMoney); //Mangler at connecte am med mp her
        }

        public double getMoney() {
            return am.getAccount(this.accountID).getMoney(); //Mangler at connecte am med mp her
        }

        public int getID() {
            return this.playerID;
        }
    }


    public Player createPlayer(String name) {
        int ID = players.size()+1;
        Player player = new Player(name, ID);
        players.put(player.getID(), player);
        return player;
    }
}
