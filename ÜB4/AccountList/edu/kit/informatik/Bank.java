package edu.kit.informatik;

/**
 * class Bank
 * 
 * @author Michael Finger, Alma Schmitt, Merlin Michel
 * @version v1.0.1
 */
public class Bank {
    private int bankCode;
    // private Account[] accounts;
    private AccountList accounts;
    private int currentAccountNumber;

    /**
     * constructor of Bank
     * 
     * @param bankCode the code for the bank that shall be created
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
        this.accounts = new AccountList();
        this.currentAccountNumber = 0;
    }

    /**
     * adds an other account to the bank with the next possible account-number
     * 
     * @return the accountnumber that was used to create the new account
     */
    public int createAccount() {
        this.accounts.add(new Account(currentAccountNumber, bankCode));

        return this.currentAccountNumber++;
    }

    /**
     * removes the account that is linked to the given account-number
     * 
     * @param accountNumber the account-number of the Account that shall be removed
     * @return true, if remove was successful; false, if not
     */
    public boolean removeAccount(int accountNumber) {
        int size = accounts.size();   
        for (int i = 0; i < size; i++) {
            Account account = accounts.get(i);

            if (account.getAccountNumber() == accountNumber) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * checks whether or not there is an Account which has the account-number that
     * is looked for
     * 
     * @param accountNumber the accountnumber of the Account that shall be found
     * @return true, if the corresponding Account is part of the Bank; false, if not
     */
    public boolean containsAccount(int accountNumber) {
        return accounts.contains(new Account(accountNumber, bankCode));
    }

    /**
     * tries to transfer money between two Accounts given by their account-numbers
     * 
     * @param fromAccountNumber the account-number that shall be transfered from
     * @param toAccountNumber   the account-number that shall be transfered to
     * @param amount            the number that shall be transfered
     * @return true, if the transfer worked; false, if not
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        Account from = accounts.getByAccountNumber(fromAccountNumber);
        Account to = accounts.getByAccountNumber(toAccountNumber);

        return from.transfer(to, amount);
    }

    /**
     * @return the length of the array 'this.accounts'
     */
    public int length() {
        return accounts.size();
    }

    /**
     * @return the number of Accounts in 'this.accounts'
     */
    public int size() {
        return accounts.size();
    }

    /**
     * tries to get an Account by its index in 'this.accounts'
     * 
     * @param index the index that shall be looked at
     * @return the Account at the given index; null if there is no Account there
     */
    public Account getAccount(int index) {
        try {
            return accounts.get(index);
        } catch (ArrayIndexOutOfBoundsException err) {
            return null;
        }
    }

    /**
     * @return the corresponding code to this Bank
     */
    public int getBankCode() {
        return this.bankCode;
    }
}
