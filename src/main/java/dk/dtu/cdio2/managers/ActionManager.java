package dk.dtu.cdio2.managers;

import dk.dtu.cdio2.actions.*;

public class ActionManager {
    private Action[] actions;

    public ActionManager() {
        actions = new Action[]{ new RollAction() };
    }

    public Action getAction(int actionID) {
        return actions[actionID-1];
    }
}
