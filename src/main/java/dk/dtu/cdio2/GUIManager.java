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
    private LanguageManager lm;
    private Field[] fields;
    private HashMap<Integer, GUI_Player> players = new HashMap<>();

    /**
     * The GUIManager constructor initializes the GUI instance, and
     * create a new instance of the dtudiplom GUI.
     */
    public GUIManager(LanguageManager lm) {
        this.lm = lm;
        GUI_Field[] fields = initializeFields();

        gui = new GUI(fields, Color.decode("0x"+"3E6990"));

        gui.setDice(6, 6);
    }

    /**
     * Reloads the language on all fields.
     */
    public void reloadLanguage() {
        for (Field field : fields) {
            field.reloadLanguage();
        }
    }

    /**
     * A Field object, which can have a name and a reward, including
     * other things.
     *
     * In the future, there is also supposed to be things like Field actions,
     * to for example control custom stuff for special fields by extending the
     * class.
     */
    public class Field {
        private GUI_Street  guiStreet;
        private String      landingText;
        private int         reward;
        private int         fieldNumber;

        /**
         * The constructor for creating a new Field.
         *
         * @param fieldNumber   The number of the field.
         * @param reward        The reward - can be both negative and positive.
         */
        Field(int fieldNumber, int reward) {
            this.fieldNumber = fieldNumber;
            this.reward = reward;
            this.landingText = lm.getString("field"+fieldNumber+"_landing_text");

            this.guiStreet = new GUI_Street();
            this.guiStreet.setTitle(Integer.toString(fieldNumber));
            this.guiStreet.setSubText(lm.getString("field"+fieldNumber+"_name") + " | " + reward);
        }

        private void reloadLanguage() {
            this.landingText = lm.getString("field"+fieldNumber+"_landing_text");
            this.guiStreet.setTitle(Integer.toString(fieldNumber));
            this.guiStreet.setSubText(lm.getString("field"+fieldNumber+"_name") + " | " + reward);

        }

        public void doLandingAction(PlayerManager pm, ActionManager am, int playerID) {
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
    public class WerewallField extends Field {
        /**
         * The constructor for creating a new Field.
         *
         * @param fieldNumber   The number of the field.
         * @param reward        The reward - can be both negative and positive.
         */
        WerewallField(Integer fieldNumber, int reward) {
            super(fieldNumber, reward);
        }

        @Override
        public void doLandingAction(PlayerManager pm, ActionManager am, int playerID) {
            super.doLandingAction(pm, am, playerID);

            am.getAction(1).doAction(playerID);
        }
    }

    /**
     * Initializes the fields in accordance to the given assignment.
     *
     * @return      An array consisting of GUI_Field objects, to use
     *              with the GUI.
     */
    private GUI_Field[] initializeFields() {
        GUI_Field[] fields = new GUI_Field[12];
        Field[] customFields = new Field[12];

        for (int i = 1; i <= fields.length; i++) {
            Field field;
            switch (i) {
                case 1:
                case 7:
                    field = new Field(i, 0);
                    break;
                case 2:
                case 6:
                    field = new Field(i, 250);
                    break;
                case 3:
                    field = new Field(i, -100);
                    break;
                case 4:
                    field = new Field(i, 100);
                    break;
                case 5:
                    field = new Field(i, -20);
                    break;
                case 8:
                    field = new Field(i, -70);
                    break;
                case 9:
                    field = new Field(i, 60);
                    break;
                case 10:
                    field = new WerewallField(i,-80);
                    break;
                case 11:
                    field = new Field(i, -50);
                    break;
                case 12:
                    field = new Field(i,650);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            customFields[i-1] = field;
            fields[i-1] = field.getGUIStreet();
        }
        this.fields = customFields;
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
        return gui.getUserLeftButtonPressed(question, lm.getString("yes"), lm.getString("no"));
    }

    /**
     * Helper function to ask for Language.
     *
     * @return              <code>true</code> if the player(s) choose
     *                      English, for Danish <code>false</code>.
     */
    public boolean askLanguage() {
        return gui.getUserLeftButtonPressed("Choose a Language // Vælg et sprog", "English", "Danish");
    }

    /**
     * Helper function to ask a prompt to a player and return the
     * response.
     *
     * @param question      The question to ask the player(s).
     * @return              The string the player(s) wrote in response.
     */
    public String getUserString(String question) {
        return gui.getUserString(question);
    }

    /**
     * Function to wait for the user to roll their dice (clicking
     * a button). The loop won't continue before they click.
     *
     * @param playerName        The name of a player who has
     *                          to roll the dice now.
     */
    public void waitUserRoll(String playerName) {
        gui.showMessage(lm.getString("player_turn").replace("{player_name}", playerName));
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
    public GUI_Player createGUIPlayer(int playerID, String playerName, double startingBalance) {
        GUI_Car car = new GUI_Car();

        GUI_Player player = new GUI_Player(playerName, (int) startingBalance, car); // the GUI takes int, so typecast

        gui.addPlayer(player);
        this.players.put(playerID, player);

        return player;
    }

    /**
     * Moves a player to a designated field.
     *
     * @param playerID      The ID of the player.
     * @param fieldNumber   The number of the field to move the player to.
     * @return              The Field which the Player landed on.
     */
    public Field movePlayerField(int playerID, int fieldNumber) {
        GUI_Player player = this.players.get(playerID);
        assert(player != null);

        // The given fieldNumber may be 12, but the field element number is one less
        Field field = this.fields[fieldNumber-1];
        assert(field != null);

        player.getCar().setPosition(field.getGUIStreet());
        return field;
    }

    /**
     * Sets the balance of a player on the GUI.
     *
     * @param playerID  The ID of the player.
     * @param balance   The balance to set to the player.
     */
    public void setPlayerBalance(int playerID, double balance) {
        GUI_Player player = this.players.get(playerID);
        assert(player != null);

        player.setBalance((int) balance); // The GUI only accepts integers, so typecasting
    }
}
