package dk.dtu.cdio2;

import dk.dtu.cdio2.managers.DiceManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

/**
 * Class for performing unit tests on the DiceManager class.
 *
 * Note: Inspired by old code @ https://github.com/DTUSoftware/25_del1/blob/master/src/test/java/dk/dtu/spil/TestDiceManager.java
 */
public class TestDiceManager {
    private final static DiceManager dm = new DiceManager();

    /**
     * We test Die and DiceCup creation.
     */
    @Test
    public void testDiceCupAndDieCreation() {
        DiceCup testCup = dm.createDiceCup();
        assertArrayEquals(new int[] {6, 6}, testCup.getDiceValues());
    }

    /**
     *  We test 1000 Die rolls, to ensure that the values are only
     *  between 1 and 6.
     */
    @Test
    public void testDiceCupRaffle() {
        DiceCup testCup = dm.createDiceCup();

        // We test the dice cup for 1000 throws
        for (int i = 0; i < 1000; i++) {
            testCup.raffleCup();
            int[] diceValues = testCup.getDiceValues();

            // Ensure that the value is between 1 and 6
            assertTrue((1 <= diceValues[0] && diceValues[0] <= 6) &&
                    (1 <= diceValues[1] && diceValues[1] <= 6));
        }
    }

    /**
     * We test the DiceCup for 10 million rolls, and ensuring that
     * the probability of the rolls are theoretically correct.
     */
    @Test
    public void testRaffleProbability() {
        // We create a HashMap to keep how many instances of the
        // different die values that we have rolled
        HashMap<Integer, Integer> allDiceValues = new HashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
        }};
        // We also keep an integer to keep track of how many equal throws are made
        int equalThrows = 0;

        DiceCup testCup = dm.createDiceCup();

        // We test the dice cup for x amount of throws
        int throwAmount = 10000000;
        for (int i = 0; i < throwAmount; i++) {
            testCup.raffleCup();
            int[] diceValues = testCup.getDiceValues();

            // If the values are equal, add one to equalThrows
            if (diceValues[0] == diceValues[1]) {
                equalThrows++;
            }

            // Add the values of the two dice to the HashMap
            allDiceValues.put(diceValues[0], allDiceValues.get(diceValues[0])+1);
            allDiceValues.put(diceValues[1], allDiceValues.get(diceValues[1])+1);
        }

        // We calculate the ratio between the equal throws and the amount of throws
        double ratio = (((double) equalThrows)/((double) throwAmount));

        // We ensure that the calculated ratio is within a respectable limit
        assertEquals(0.1666, ratio, 0.005);

        // We loop through the different die values and ensure that the
        // calculated ratio is within a respectable limit.
        for (int value : allDiceValues.values()) {
            ratio = (((double) value)/((double) (throwAmount*2)));
            assertEquals(0.1666, ratio, 0.005);
        }
    }

}
