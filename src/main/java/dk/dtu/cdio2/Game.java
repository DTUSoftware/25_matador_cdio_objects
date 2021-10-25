package dk.dtu.cdio2;

/**
 * Main class for the program
 */
public class Game {
    private final static GUIManager gm = new GUIManager();
    private final static PlayerManager pm = new PlayerManager(gm);

    private final static int startingBalance = 1000;
    private final Action[] actions = new Action[]{ new RollAction(1) };
    private static boolean isPlaying = true;
    private final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

    public static void main(String[] args) {
        // Create the players
        for (int i = 1; i <= 2; i++) {
            String playerName = gm.getUserString("Enter name for Player " + i);
            PlayerManager.Player player = pm.createPlayer(playerName, startingBalance);
            gm.createGUIPlayer(player.getID(), playerName, player.getMoney());
        }

        //***************************************************************//
        // Play the game until they want to stop playing.
        //***************************************************************//
        while (isPlaying) {
            play();
        }

        //***************************************************************//
        // After they have stopped playing, close the GUI and exit.
        //***************************************************************//
        gm.closeGUI();
        System.exit(0);
    }

    private class Action {
        private Integer actionID;

        Action(Integer actionID) {
            this.actionID = actionID;
        }

        public void doAction(Integer playerID) {}
    }

    private class RollAction extends Action {
        RollAction(Integer actionID) {
            super(actionID);
        }

        @Override
        public void doAction(Integer playerID) {

        }
    }

    public static void play() {
        int playerWon = 0;
        while (playerWon == 0) {
            // Loop through players
            for (int playerID : pm.getPlayerIDs()) {
                // TODO: implement game rolling and moving here
                pm.getPlayer(playerID).withdrawMoney(1000);

                // Check wincondition
                if (pm.getPlayer(playerID).getMoney() >= 3000) {
                    playerWon = playerID;
                }
            }
        }

        if (gm.askPrompt("Do you want to play again?")) {
            for (int playerID : pm.getPlayerIDs()) {
                pm.getPlayer(playerID).setMoney(startingBalance);
            }
        }
        else {
            isPlaying = false;
        }
    }
}
