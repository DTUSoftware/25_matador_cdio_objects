package dk.dtu.cdio2;

import dk.dtu.cdio2.PlayerManager.Player;

/**
 * Main class for the program
 */
public class Game {
    private final static GUIManager gm = new GUIManager();
    private final static PlayerManager pm = new PlayerManager();
    private Action[] actions = new Action[1];

    private static boolean isPlaying = true;
    private final static boolean debug = ((System.getenv("debug") != null) || (System.getProperty("debug") != null));

    public static void main(String[] args) {

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

    }
}
