package dk.dtu.matador_objects.managers;

import dk.dtu.matador_objects.DiceCup;

/**
 * The DiceManager is the controller used to create new Die and
 * DiceCup objects.
 * Note: This code is copied from the Dice manager from the CDIO-1 assignment
 * with a few edits so it fits the CDIO-2 assignment better
 */
public class DiceManager {
    /**
     * DiceCup a constructer function that returns a
     * new instance of the Dicecup class
     *
     * @return a ne instance of the dicecup class
     */
    public DiceCup createDiceCup() {
        return new DiceCup();
    }
}

