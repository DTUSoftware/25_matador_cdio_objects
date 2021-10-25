package dk.dtu.cdio2;

import java.util.HashMap;

/**
 * The Account Controller.
 */
public class AccountManager {
    private static HashMap<Integer, Account> accounts = new HashMap<>();

    /**
     * The Account class, typically a Player has an instance of this class.
     */
    private static class Account {
        private Integer amAccountID;
        private double balance = 0.0;

        public Account(Integer amAccountID) {
            this.amAccountID = amAccountID;
        }

        public Account(Integer amAccountID, double startingBalance) {
            this.amAccountID = amAccountID;
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
                balance = 0;  //hvis jeg købet noget for 100kr og kun har 10kr får jeg ikke lov til at købe og mister 10kr?
                return false;
            }
            balance = balance+amount;
            return true;
        }

        public Integer getamAccountID() {
            return amAccountID;
        }
    }

    public void createAccount(int amAccountID, double startingBalance) {
        Account account = new Account(amAccountID, startingBalance);
        accounts.put(amAccountID, account);
    }

    public Account getAccount(Integer amAccountID) {
        return accounts.get(amAccountID);
    }
}
