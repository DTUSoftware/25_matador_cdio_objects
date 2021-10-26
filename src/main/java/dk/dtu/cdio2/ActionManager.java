package dk.dtu.cdio2;

import dk.dtu.cdio2.actions.*;

public class ActionManager {
    private Action[] actions;

    public ActionManager(DiceManager dm, GUIManager gm, PlayerManager pm) {
        actions = new Action[]{ new RollAction(dm.createDiceCup(), gm, pm, this ) };
    }

    public Action getAction(int actionID) {
        return actions[actionID-1];
    }
}
