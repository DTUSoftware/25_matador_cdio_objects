package dk.dtu.matador_objects;

public class Player {
    private final String name;
    private final int playerID;
    private final int accountID;

    public Player(String name, int playerID) {
        this.name = name;
        this.playerID = playerID;
        this.accountID = Main.getAccountManager().createAccount().getAccountID();
    }

    public Player(String name, int playerID, int startingBalance) {
        this.name = name;
        this.playerID = playerID;
        this.accountID = Main.getAccountManager().createAccount(startingBalance).getAccountID();
    }

    public static boolean guiInitialized() {
        return Main.getGUIManager() != null;
    }

    public String getName() {
        return name;
    }

    public boolean withdrawMoney(double m) {
        boolean success = Main.getAccountManager().getAccount(this.accountID).withdraw(m);
        // update the GUI
        if (guiInitialized()) {
            Main.getGUIManager().setPlayerBalance(playerID, getMoney());
        }
        return success;
    }

    public void setMoney(double newMoney) {
        Main.getAccountManager().getAccount(this.accountID).setBalance(newMoney);
        // update the GUI
        if (guiInitialized()) {
            Main.getGUIManager().setPlayerBalance(playerID, getMoney());
        }
    }

    public double getMoney() {
        return Main.getAccountManager().getAccount(this.accountID).getMoney();
    }

    public int getID() {
        return this.playerID;
    }
}
