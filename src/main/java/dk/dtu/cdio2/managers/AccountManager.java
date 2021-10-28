package dk.dtu.cdio2.managers;

import dk.dtu.cdio2.Account;
import java.util.HashMap;

/**
 * The Account Controller.
 */
public class AccountManager {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    private Integer generateNewAccountID() {
        int accountID = accounts.size()+1;
        // check if account ID is already taken
        while (true) {
            if (accounts.get(accountID) != null) {
                accountID += 1;
            }
            else {
                break;
            }
        }
        return accountID;
    }

    public Account createAccount() {
        int accountID = generateNewAccountID();
        Account account = new Account(accountID);
        accounts.put(accountID, account);

        return account;
    }

    public Account createAccount(double startingBalance) {
        int accountID = generateNewAccountID();
        Account account = new Account(accountID, startingBalance);
        accounts.put(accountID, account);

        return account;
    }

    public Account getAccount(Integer accountID) {
        return accounts.get(accountID);
    }
}
