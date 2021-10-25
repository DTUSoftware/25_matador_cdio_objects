package dk.dtu.cdio2;

import java.util.Random;

/**
 * The DiceManager is the controller used to create new Die and
 * DiceCup objects.
 * Note: This code is copied from the Dice manager from the CDIO-1 assignment
 * with a few edits so it fits the CDIO-2 assignment better
 */
public class DiceManager {
    private final Random rand = new Random();

    /**
     * The Die class is a single dice that can be raffled and give
     * a face value of the die
     */
    private class Die {
        private int minDieValue = 1;
        private int maxDieValue = 6;

        private int faceValue = 6;

        /**
         * Constructor for the die class where you can set the max dice value
         * fx if you want a D8 instead of a D6. Default is D6
         * @param maxDieValue is the amount of sides you want on the die
         */
        public Die(int maxDieValue){
            this.maxDieValue = maxDieValue;
        }
        public Die() {}

        /**
         * Raffle function to set the facevalue of a die randomly
         * We use Random to generate a random Integer between the min
         * and max die values.
         * We use Random.nextInt() instead of Math.random(), since it is
         * both faster and more efficient
         * (see https://stackoverflow.com/a/738651/12418245).
         */
        public void raffle() {
            faceValue = rand.nextInt(maxDieValue) + minDieValue;
        }

        /**
         * Function getValue that returns the face value of the dice
         */
        public int getValue() {
            return faceValue;
        }


    }

    /**
     * The DiceCup class is a package-private class, such that it
     * should get created through an instance of the class (ex.
     * an instance of DiceManager).
     * An instance of DiceCup holds two Die instances, which are
     * created on initialization.
     */
    class DiceCup {
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

