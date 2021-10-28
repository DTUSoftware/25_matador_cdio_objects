package dk.dtu.cdio2;

public class Player {
    private final String name;
    private final int playerID;
    private final int accountID;

    public Player(String name, int playerID) {
        this.name = name;
        this.playerID = playerID;
        this.accountID = Game.getAccountManager().createAccount().getAccountID();
    }

    public Player(String name, int playerID, int startingBalance) {
        this.name = name;
        this.playerID = playerID;
        this.accountID = Game.getAccountManager().createAccount(startingBalance).getAccountID();
    }

    public static boolean guiInitialized() {
        return Game.getGUIManager() != null;
    }

    public String getName() {
        return name;
    }

    public boolean withdrawMoney(double m) {
        boolean success = Game.getAccountManager().getAccount(this.accountID).withdraw(m);
        // update the GUI
        if (guiInitialized()) {
            Game.getGUIManager().setPlayerBalance(playerID, getMoney());
        }
        return success;
    }

    public void setMoney(double newMoney) {
        Game.getAccountManager().getAccount(this.accountID).setBalance(newMoney);
        // update the GUI
        if (guiInitialized()) {
            Game.getGUIManager().setPlayerBalance(playerID, getMoney());
        }
    }

    public double getMoney() {
        return Game.getAccountManager().getAccount(this.accountID).getMoney();
    }

    public int getID() {
        return this.playerID;
    }
}
