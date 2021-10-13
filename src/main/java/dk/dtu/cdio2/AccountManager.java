package dk.dtu.cdio2;

import java.util.HashMap;

/**
 * The Account Controller.
 */
public class AccountManager {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    /**
     * The Account class, typically a Player has an instance of this class.
     */
    public class Account {
        private Integer accountID;
        private double balance = 0.0;

        private Account(Integer accountID) {
            this.accountID = accountID;
        }

        private Account(Integer accountID, double startingBalance) {
            this.accountID = accountID;
            balance = startingBalance;
        }

        /**
         * Returns the balance as a rounded string.
         *
         * @return  The balance as a String, rounded.
         */
        public String getBalanceString() {
            return Float.toString(Math.round(balance));
        }

        /**
         * Gets money.
         *
         * @return      A double with the given account balance.
         */
        public double getMoney() {
            return balance;
        }

        /**
         * Withdraws or deposits a certain amount of cash onto the account.
         *
         * @param amount    The amount to deposit/withdraw.
         * @return          <code>true</code> if the transaction succeeded,
         *                  <code>false</code> if the amount didn't have a sufficient balance.
         */
        public boolean withdraw(double amount) {
            if (balance+amount < 0) {
                balance = 0;
                return false;
            }
            balance = balance+amount;
            return true;
        }

        public Integer getAccountID() {
            return accountID;
        }
    }

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
