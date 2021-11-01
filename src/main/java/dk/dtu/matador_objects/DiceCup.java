package dk.dtu.matador_objects;

/**
 * The DiceCup class is a package-private class, such that it
 * should get created through an instance of the class (ex.
 * an instance of DiceManager).
 * An instance of DiceCup holds two Die instances, which are
 * created on initialization.
 */
public class DiceCup {
    private Die die1;
    private Die die2;

    /**
     * Constructer function DiceCup
     * When a new DiceCup instance is created, two Die instances are
     * created with it.
     */
    public DiceCup() {
        die1 = new Die();
        die2 = new Die();
    }

    /**
     * Rafflecup function that raffles the dice like a
     * real life raffle.
     */
    public void raffleCup() {
        die1.raffle();
        die2.raffle();
    }

    /**
     * getDiceValues function that gives out the values of
     * the individues dice
     *
     * @return The facevalue of the individual dice
     */
    public int[] getDiceValues() {
        return new int[]{die1.getValue(), die2.getValue()};
    }

    /**
     * getSum function that gives the sum of both dice
     *
     * @return the sum of both dice
     */
    public int getSum() {
        return (die1.getValue() + die2.getValue());
    }
}
