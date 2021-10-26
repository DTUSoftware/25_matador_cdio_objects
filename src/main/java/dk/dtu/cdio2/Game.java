package dk.dtu.cdio2;

import dk.dtu.cdio2.actions.*;
import dk.dtu.cdio2.managers.*;

/**
 * Main class for the program
 */
public class Game {
    private final static LanguageManager lm = new LanguageManager();
    private final static GUIManager gm = new GUIManager();
    private final static PlayerManager pm = new PlayerManager();
    private final static DiceManager dm = new DiceManager();
    private final static ActionManager actm = new ActionManager();
    private final static AccountManager accm = new AccountManager();

    private final static int startingBalance = 1000;
    private final static int finalBalance = 3000;
    private static boolean isPlaying = true;
    public final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

    // Getter functions for the managers
    public static LanguageManager getLanguageManager() {
        return lm;
    }
    public static GUIManager getGUIManager() {
        return gm;
    }
    public static PlayerManager getPlayerManager() {
        return pm;
    }
    public static DiceManager getDiceManager() {
        return dm;
    }
    public static ActionManager getActionManager() {
        return actm;
    }
    public static AccountManager getAccountManager() {
        return accm;
    }

    public static void main(String[] args) {
        // Choose a language
        if (gm.askLanguage()) {
            lm.setLocale("en");
        }
        else {
            lm.setLocale("da");
        }
        gm.reloadLanguage();

        // Create the players
        for (int i = 1; i <= 2; i++) {
            String playerName = gm.getUserString(lm.getString("enter_player_name").replace("{player_number}", Integer.toString(i)));
            Player player = pm.createPlayer(playerName, startingBalance);
            gm.createGUIPlayer(player.getID(), playerName, player.getMoney());
        }

        // Play the game until they want to stop playing
        while (isPlaying) {
            play();
        }

        // After they have stopped playing, close the GUI and exit
        gm.closeGUI();
        System.exit(0);
    }

    private static void play() {
        Action rollAction = Game.getActionManager().getAction(1);

        int playerWon = 0;
        while (playerWon == 0) {
            // Loop through players
            for (int playerID : pm.getPlayerIDs()) {
                Player player = pm.getPlayer(playerID);

                rollAction.doAction(playerID);

                // Check wincondition
                if (player.getMoney() >= finalBalance) {
                    playerWon = playerID;
                    gm.showMessage(lm.getString("game_win").replace("{player_name}", player.getName()));
                    break;
                }
            }
        }

        if (gm.askPrompt(lm.getString("play_again"))) {
            for (int playerID : pm.getPlayerIDs()) {
                pm.getPlayer(playerID).setMoney(startingBalance);
            }
        }
        else {
            isPlaying = false;
        }
    }
}
