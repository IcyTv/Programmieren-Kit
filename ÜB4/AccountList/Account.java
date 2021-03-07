package edu.kit.informatik;

/**
 * class Account
 * 
 * @author Michael Finger, Alma Schmitt, Merlin Michel
 * @version v1.0.0
 */
public class Account {
    private int accountNumber;
    private int bankCode;
    private int balance;

    /**
     * constructer of Account
     * 
     * @param accountNumber the corresponding number to this account
     * @param bankCode the bankCode of the Bank this Account belongs to
     */
    public Account(int accountNumber, int bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.balance = 0;               //initializing balance at 0, because theres no money on a new Account
    }

    /**
     * tries to decrease th balence by the given amount
     * 
     * @param amount the number that shall be withdrawn
     * @return true, if able to withdraw the amount; false, if not
     */
    public boolean withdraw(int amount) {
        if (this.balance - amount < 0) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }

    /**
     * increase the balance by the given amount
     * 
     * @param amount the number that shall be deposited
     */
    public void deposit(int amount) {
        this.balance += amount;
    }

    /**
     * transfers money from one Account to an other
     * 
     * @param account the Account that shall be transfered to
     * @param amount the number that shall be transfered
     * @return true, if transfer was successful; false, if not
     */
    public boolean transfer(Account account, int amount) {
        if (this.withdraw(amount)) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    // Getters and Setters

    /**
     * @return the account number of this Account
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * @return the bankCode of the bank this Account belongs to
     */
    public int getBankCode() {
        return this.bankCode;
    }

    /**
     * @return the balance of this Account
     */
    public int getBalance() {
        return this.balance;
    }

}
