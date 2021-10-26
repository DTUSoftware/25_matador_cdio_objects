package dk.dtu.cdio2;

import dk.dtu.cdio2.actions.*;

/**
 * Main class for the program
 */
public class Game {
    private final static LanguageManager lm = new LanguageManager();
    private final static GUIManager gm = new GUIManager(lm);
    private final static PlayerManager pm = new PlayerManager(gm);
    private final static DiceManager dm = new DiceManager();
    private final static ActionManager am = new ActionManager(dm, gm, pm);

    private final static int startingBalance = 1000;
    private final static int finalBalance = 3000;
    private static boolean isPlaying = true;
    public final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

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
            PlayerManager.Player player = pm.createPlayer(playerName, startingBalance);
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
        Action rollAction = am.getAction(1);

        int playerWon = 0;
        while (playerWon == 0) {
            // Loop through players
            for (int playerID : pm.getPlayerIDs()) {
                PlayerManager.Player player = pm.getPlayer(playerID);

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
