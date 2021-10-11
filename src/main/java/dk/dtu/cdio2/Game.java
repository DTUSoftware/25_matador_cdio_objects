package dk.dtu.cdio2;

import dk.dtu.cdio2.PlayerManager.Player;

//***************************************************************//
// Main class for the program
//***************************************************************//
public class Game {
    private final static GUIManager gm = new GUIManager();
    private final static PlayerManager pm = new PlayerManager();

    private static Player player1;
    private static Player player2;

    private static boolean isPlaying = true;
    private final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

    public static void main(String[] args) {
        player1 = pm.createPlayer(gm);
        player2 = pm.createPlayer(gm);

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

    public static void play() {

    }
}
