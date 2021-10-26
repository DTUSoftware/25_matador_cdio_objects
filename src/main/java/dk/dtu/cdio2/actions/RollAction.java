package dk.dtu.cdio2.actions;

import dk.dtu.cdio2.DiceCup;
import dk.dtu.cdio2.fields.Field;
import dk.dtu.cdio2.Player;
import dk.dtu.cdio2.Game;

public class RollAction extends Action {
    DiceCup dc;

    public RollAction() {
        super();
        this.dc = Game.getDiceManager().createDiceCup();
    }

    @Override
    public void doAction(Integer playerID) {
        Player player = Game.getPlayerManager().getPlayer(playerID);

        if (!Game.debug) {
            Game.getGUIManager().waitUserRoll(player.getName());
        }
        dc.raffleCup();

        int[] diceValues = dc.getDiceValues();
        Game.getGUIManager().updateDice(diceValues[0], diceValues[1]);

        Field field = Game.getGUIManager().movePlayerField(player.getID(), dc.getSum());
        field.doLandingAction(player.getID());
    }
}
