package dk.dtu.cdio2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAccountManager {
    AccountManager am = new AccountManager();

    @Test
    public void testAccountManager() {
        AccountManager.Account account = am.createAccount();
        assertEquals(1, account.getAccountID());

        assertEquals(0, account.getMoney());

        assertFalse(account.withdraw(-100));
        assertEquals(0, account.getMoney());

        assertTrue(account.withdraw(100));
        assertEquals(100, account.getMoney());
        assertEquals("100.0", account.getBalanceString());

        assertTrue(account.withdraw(-100));
        assertEquals(0, account.getMoney());

        double bal = 1000000000000.0;
        account.setBalance(bal);
        assertEquals(bal, account.getMoney());
        assertEquals("1.0E12", account.getBalanceString());
    }

}
