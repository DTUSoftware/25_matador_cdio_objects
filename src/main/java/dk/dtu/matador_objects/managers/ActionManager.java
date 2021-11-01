package dk.dtu.matador_objects.managers;

import dk.dtu.matador_objects.actions.*;

public class ActionManager {
    private Action[] actions;

    public ActionManager() {
        actions = new Action[]{ new RollAction() };
    }

    public Action getAction(int actionID) {
        return actions[actionID-1];
    }
}
