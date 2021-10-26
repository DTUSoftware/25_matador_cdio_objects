package dk.dtu.cdio2;

import java.util.HashMap;

public class PlayerManager {
    private final static AccountManager am = new AccountManager(); //<- tror ikke på at det er nødvendigt at også havde den her
    private GUIManager gm;

    PlayerManager() {}
    PlayerManager(GUIManager gm) {
        this.gm = gm;
    }

    /**
     * We create a HashMap to keep the players and their numbers.
     */
    private HashMap<Integer, Player> players = new HashMap<>();

    private boolean guiInitialized() {
        return gm != null;
    }

    public class Player {

        private final String name;
        private int playerID;
        private int accountID;

        Player(String name, int playerID) {
            this.name = name;
            this.playerID = playerID;
            this.accountID = am.createAccount().getAccountID();
        }

        Player(String name, int playerID, int startingBalance) {
            this.name = name;
            this.playerID = playerID;
            this.accountID = am.createAccount(startingBalance).getAccountID();
        }

        public String getName() {
            return name;
        }

        public boolean withdrawMoney(double m) {
            boolean success = am.getAccount(this.accountID).withdraw(m);
            // update the GUI
            if (guiInitialized()) {
                gm.setPlayerBalance(playerID, getMoney());
            }
            return success;
        }

        public void setMoney(double newMoney) {
            am.getAccount(this.accountID).setBalance(newMoney);
            // update the GUI
            if (guiInitialized()) {
                gm.setPlayerBalance(playerID, getMoney());
            }
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
