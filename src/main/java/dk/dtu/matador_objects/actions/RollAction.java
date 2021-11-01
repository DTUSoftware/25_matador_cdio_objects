package dk.dtu.matador_objects.actions;

import dk.dtu.matador_objects.DiceCup;
import dk.dtu.matador_objects.fields.Field;
import dk.dtu.matador_objects.Player;
import dk.dtu.matador_objects.Main;

public class RollAction extends Action {
    DiceCup dc;

    public RollAction() {
        super();
        this.dc = Main.getDiceManager().createDiceCup();
    }

    @Override
    public void doAction(Integer playerID) {
        Player player = Main.getPlayerManager().getPlayer(playerID);

        if (!Main.debug) {
            Main.getGUIManager().waitUserRoll(player.getName());
        }
        dc.raffleCup();

        int[] diceValues = dc.getDiceValues();
        Main.getGUIManager().updateDice(diceValues[0], diceValues[1]);

        Field field = Main.getGUIManager().movePlayerField(player.getID(), dc.getSum());
        field.doLandingAction(player.getID());
    }
}
