package dk.dtu.cdio2.fields;

import dk.dtu.cdio2.Game;
import gui_fields.GUI_Street;

/**
 * A Field object, which can have a name and a reward, including
 * other things.
 *
 * In the future, there is also supposed to be things like Field actions,
 * to for example control custom stuff for special fields by extending the
 * class.
 */
public class Field {
    private final GUI_Street    guiStreet;
    private String              landingText;
    private final int           reward;
    private final int           fieldNumber;

    /**
     * The constructor for creating a new Field.
     *
     * @param fieldNumber   The number of the field.
     * @param reward        The reward - can be both negative and positive.
     */
    public Field(int fieldNumber, int reward) {
        this.fieldNumber = fieldNumber;
        this.reward = reward;
        this.landingText = Game.getLanguageManager().getString("field"+fieldNumber+"_landing_text");

        this.guiStreet = new GUI_Street();
        this.guiStreet.setTitle(Integer.toString(fieldNumber));
        this.guiStreet.setSubText(Game.getLanguageManager().getString("field"+fieldNumber+"_name") + " | " + reward);
    }

    public void reloadLanguage() {
        this.landingText = Game.getLanguageManager().getString("field"+fieldNumber+"_landing_text");
        this.guiStreet.setTitle(Integer.toString(fieldNumber));
        this.guiStreet.setSubText(Game.getLanguageManager().getString("field"+fieldNumber+"_name") + " | " + reward);

    }

    public void doLandingAction(int playerID) {
        Game.getGUIManager().showMessage(this.landingText);
        Game.getPlayerManager().getPlayer(playerID).withdrawMoney(this.reward);
    }

    public GUI_Street getGUIStreet() {
        return this.guiStreet;
    }
}
