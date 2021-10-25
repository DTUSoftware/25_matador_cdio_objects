package dk.dtu.cdio2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import java.awt.*;
import java.util.HashMap;

/**
 * The GUIManager class, for managing the GUI. The
 * GUIManager is a constructor which initializes a
 * single instance of the dtudiplom GUI interface.
 *
 * Note: Based on https://github.com/DTUSoftware/25_del1/blob/master/src/main/java/dk/dtu/spil/GUIManager.java
 *
 * @author      DTUSoftware, Gruppe 25
 * @version     %I%, %G%
 * @since       v0.0.2
 */
public class GUIManager {
    private GUI gui;
    private HashMap<Integer, GUI_Player> players = new HashMap<>();

    /**
     * The GUIManager constructor initializes the GUI instance, and
     * create a new instance of the dtudiplom GUI.
     */
    public GUIManager() {
        GUI_Field[] fields = initializeFields();

        gui = new GUI(fields, Color.decode("0x"+"3E6990"));

        gui.setDice(6, 6);
    }

    /**
     * A Field object, which can have a name and a reward, including
     * other things.
     *
     * In the future, there is also supposed to be things like Field actions,
     * to for example control custom stuff for special fields by extending the
     * class.
     */
    private class Field {
        private GUI_Street  guiStreet;
        private String      landingText;
        private Integer     reward;

        /**
         * The constructor for creating a new Field.
         *
         * @param fieldNumber   The number of the field.
         * @param name          The name of the field.
         * @param landingText   The text to show when the player lands on the field.
         * @param reward        The reward - can be both negative and positive.
         */
        Field(Integer fieldNumber, String name, String landingText, Integer reward) {
            this.reward = reward;
            this.landingText = landingText;

            this.guiStreet = new GUI_Street();
            this.guiStreet.setTitle(fieldNumber.toString());
            this.guiStreet.setSubText(name + " | " + reward.toString());
        }

        public void doLandingAction(PlayerManager pm, int playerID, int faceValue1, int faceValue2) {
            showMessage(this.landingText);
            pm.getPlayer(playerID).withdrawMoney(this.reward);
        }

        public GUI_Street getGUIStreet() {
            return this.guiStreet;
        }
    }

    /**
     * Special class for the Werewall field, which extends the Field class.
     *
     * The Werewall field should have the following rule:
     * - Get an extra turn.
     */
    private class WerewallField extends Field {
        /**
         * The constructor for creating a new Field.
         *
         * @param fieldNumber   The number of the field.
         * @param name          The name of the field.
         * @param landingText   The text to show when the player lands on the field.
         * @param reward        The reward - can be both negative and positive.
         */
        WerewallField(Integer fieldNumber, String name, String landingText, Integer reward) {
            super(fieldNumber, name, landingText, reward);
        }

//        @Override
//        public void doLandingAction(PlayerManager.Player player, int faceValue1, int faceValue2) {
//            showMessage(super.landingText);
//
//        }
    }

    /**
     * Initializes the fields in accordance to the given assignment.
     *
     * @return      An array consisting of GUI_Field objects, to use
     *              with the GUI.
     */
    private GUI_Field[] initializeFields() {
        GUI_Field[] fields = new GUI_Field[12];

        for (int i = 1; i <= fields.length; i++) {
            Field field;
            switch (i) {
                case 1:
                    field = new Field(i, "Not possible to roll 1",
                            "How did you even get here..?",
                            0);
                    break;
                case 2:
                    field = new Field(i, "Tower",
                            "You have reached the tower and get 250!",
                            250);
                    break;
                case 3:
                    field = new Field(i, "Crater",
                            "You fell into the crater and lost 100!",
                            -100);
                    break;
                case 4:
                    field = new Field(i, "Palace gates",
                            "You have reached the palace gates and get 100!",
                            100);
                    break;
                case 5:
                    field = new Field(i, "Cold Desert",
                            "You have reached the cold desert, you bought a blanket and lost 20!",
                            -20);
                    break;
                case 6:
                    field = new Field(i, "Walled city",
                            "You have reached the safety of the walled city, and get 180!",
                            250);
                    break;
                case 7:
                    field = new Field(i, "Monastery",
                            "You have reached the monastery and is provided with a safe place to rest - nothing gained!",
                            0);
                    break;
                case 8:
                    field = new Field(i, "Black cave",
                            "You have reached the black cave and bought a torch, lost 70!",
                            -70);
                    break;
                case 9:
                    field = new Field(i, "Huts in the mountain",
                            "You have reached the huts in the mountain and found a coin purse, got 60!",
                            60);
                    break;
                case 10:
                    field = new WerewallField(i, "The Werewall",
                            "You have reached the Werewall and paid the werewolves for another turn, lost 80 but got another turn!",
                            -80);
                    break;
                case 11:
                    field = new Field(i, "The pit",
                            "You fell into the deep dark pit and lost 50!",
                            -50);
                    break;
                case 12:
                    field = new Field(i, "Goldmine",
                            "You have found gold in the mountains and sold it for 650, you are rich!",
                            650);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            fields[i-1] = field.getGUIStreet();
        }

        return fields;
    }

    /**
     * Closes the GUI after usage.
     */
    public void closeGUI() {
        gui.close();
    }

    /**
     * Function to update the two dice on the GUI.
     *
     * @param faceValue1    The value of the first die
     * @param faceValue2    The value of the second die
     */
    public void updateDice(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, faceValue2);
    }

    /**
     * Shows a message to the player(s), which they will have
     * to accept to continue playing (stops the current thread).
     *
     * @param message       The message to be shown to the player(s)
     */
    public void showMessage(String message) {
        gui.showMessage(message);
    }

    /**
     * Helper function to ask a prompt to a player and return the
     * result.
     *
     * @param question      The question to ask the player(s).
     * @return              <code>true</code> if the player(s) agree to
     *                      the question, else <code>false</code>.
     */
    public boolean askPrompt(String question) {
        return gui.getUserLeftButtonPressed(question, "Yes", "No");
    }

    /**
     * Function to wait for the user to roll their dice (clicking
     * a button). The loop won't continue before they click.
     *
     * @param playerName        The name of a player who has
     *                          to roll the dice now.
     */
    public void waitUserRoll(String playerName) {
        gui.showMessage(playerName + "'s turn. Click to roll the dice!");
    }

    /**
     * Function to create a new GUIPlayer. This function is
     * for example used in the PlayerManager, where it is passed
     * an instance of the GUIManager by the main function.
     *
     * @param playerID          The ID of the player.
     * @param startingBalance   The starting balance of a player.
     * @return                  A GUI_Player object, linked to the
     *                          player in question.
     */
    public GUI_Player createGUIPlayer(int playerID, int startingBalance) {
        String player_name = gui.getUserString("Enter name for player");

        GUI_Car car = new GUI_Car();

        GUI_Player player = new GUI_Player(player_name, startingBalance, car);

        gui.addPlayer(player);
        this.players.put(playerID, player);

        return player;
    }

    /**
     * Moves a player to a designated field.
     *
     * @param playerID      The ID of the player.
     * @param fieldNumber   The number of the field to move the player to.
     */
    public void movePlayerField(int playerID, int fieldNumber) {
        GUI_Player player = this.players.get(playerID);
        assert(player != null);

        // The given fieldNumber may be 12, but the field element number is one less
        GUI_Field field = gui.getFields()[fieldNumber-1];
        assert(field != null);

        player.getCar().setPosition(field);
    }

    /**
     * Sets the balance of a player on the GUI.
     *
     * @param playerID  The ID of the player.
     * @param balance   The balance to set to the player.
     */
    public void setPlayerBalance(int playerID, int balance) {
        GUI_Player player = this.players.get(playerID);
        assert(player != null);

        player.setBalance(balance);
    }
}
