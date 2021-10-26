package dk.dtu.cdio2.fields;

import dk.dtu.cdio2.Game;

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
    public WerewallField(Integer fieldNumber, int reward) {
        super(fieldNumber, reward);
    }

    @Override
    public void doLandingAction(int playerID) {
        super.doLandingAction(playerID);

        Game.getActionManager().getAction(1).doAction(playerID);
    }
}
