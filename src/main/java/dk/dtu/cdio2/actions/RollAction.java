package dk.dtu.cdio2.actions;

import dk.dtu.cdio2.ActionManager;
import dk.dtu.cdio2.DiceManager.DiceCup;
import dk.dtu.cdio2.GUIManager;
import dk.dtu.cdio2.Game;
import dk.dtu.cdio2.PlayerManager;

public class RollAction extends Action {
    DiceCup dc;
    GUIManager gm;
    PlayerManager pm;
    ActionManager am;

    public RollAction(DiceCup dc, GUIManager gm, PlayerManager pm, ActionManager am) {
        super();
        this.dc = dc;
        this.gm = gm;
        this.pm = pm;
        this.am = am;
    }

    @Override
    public void doAction(Integer playerID) {
        PlayerManager.Player player = pm.getPlayer(playerID);

        if (!Game.debug) {
            gm.waitUserRoll(player.getName());
        }
        dc.raffleCup();

        int[] diceValues = dc.getDiceValues();
        gm.updateDice(diceValues[0], diceValues[1]);

        GUIManager.Field field = gm.movePlayerField(player.getID(), dc.getSum());
        field.doLandingAction(pm, am, player.getID());
    }
}
