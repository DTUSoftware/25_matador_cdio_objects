package dk.dtu.matador_objects;

import dk.dtu.matador_objects.managers.PlayerManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerManager {
    PlayerManager pm = new PlayerManager();

    @Test
    public void testPlayerManager() {
        Player player = pm.createPlayer("Test Tester");

        assertEquals(1, player.getID());
        assertEquals("Test Tester", player.getName());
        assertEquals(0.0, player.getMoney());

        assertFalse(player.withdrawMoney(-100));
        assertEquals(0, player.getMoney());

        assertTrue(player.withdrawMoney(100));
        assertEquals(100, player.getMoney());

        assertTrue(player.withdrawMoney(-100));
        assertEquals(0, player.getMoney());

        double bal = 1000000000000.0;
        player.setMoney(bal);
        assertEquals(bal, player.getMoney());;

        player.setMoney(100);
        assertEquals(100, player.getMoney());;
    }

    @Test
    public void testStartingBalance() {
        Player player = pm.createPlayer("Test Tester", 3000);
        assertEquals(3000, player.getMoney());
    }

}
