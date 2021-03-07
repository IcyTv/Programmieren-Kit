package edu.kit.informatik;

/**
 * class Bank
 * 
 * @author Michael Finger, Alma Schmitt, Merlin Michel
 * @version v1.0.0
 */
public class Bank {
    private int bankCode;           
    private Account[] accounts;
    private int currentAccountNumber;

    /**
     * constructor of Bank
     * 
     * @param bankCode the code for the bank that shall be created
     */
    public Bank(int bankCode) {
        this.bankCode = bankCode;
        this.accounts = new Account[8];
        this.currentAccountNumber = 0;
    }

    /**
     * adds an other account to the bank with the next possible account-number
     * if - however - the new Account wouldn't fit in the 'this.accounts' array
     * the size of 'this.accounts' is doubled and the new accountnumber will then be stored
     * 
     * @return the accountnumber that was used to create the new account
     */
    public int createAccount() {
        if (this.currentAccountNumber + 1 == this.accounts.length) {  //if theres no mor space in the array
            Account[] tmp = new Account[this.accounts.length * 2];    //create a temporary array with douple the size
            for (int i = 0; i < this.accounts.length; i++) {          //copy all Accounts from 'accounts' to 'tmp'
                tmp[i] = this.accounts[i];                            
            }
            this.accounts = tmp;                                      //set 'accounts' to 'tmp'
        }
        //add the new Account
        this.accounts[this.currentAccountNumber] = new Account(this.currentAccountNumber, this.bankCode);

        return this.currentAccountNumber++;
    }

    /**
     * removes the account that is linked to the given account-number
     * if only 1/4 of 'accounts' is filled, its size is devided by 4 
     * 
     * @param accountNumber the account-number of the Account that shall be removed
     * @return true, if remove was successful; false, if not
     */
    public boolean removeAccount(int accountNumber) {
        boolean removed = false;
        int filled = 0;
        for (int i = 0; i < accounts.length; i++) {
            //if the Account exists and contains the demanded account-number
            if (this.accounts[i] != null && this.accounts[i].getAccountNumber() == accountNumber) {
                this.accounts[i] = null;        //account will be overwritten by 'null'
                removed = true;                 //since removing was successful, 'removed' is set to true
            }
            if (this.accounts[i] != null) {     //count the number of existing accounts after removing
                filled++;
            }
        }

        //if only 1/4 of the array is filled and the array length is greater than 8
        if (filled == this.accounts.length / 4 && this.accounts.length > 8) {
            //create a temporary array with 1/4 of the previous length
            Account[] tmp = new Account[this.accounts.length / 4];
            int index = 0;
            //copy elements from 'accounts' to 'tmp' eliminating spaces between elements
            for (int i = 0; i < this.accounts.length; i++) {
                if (this.accounts[i] != null) {
                    tmp[index++] = this.accounts[i];
                }
            }
            this.accounts = tmp;
        }

        return removed;
    }

    /**
     * checks whether or not there is an Account which has the account-number that is looked for
     * 
     * @param accountNumber the accountnumber of the Account that shall be found
     * @return true, if the corresponding Account is part of the Bank; false, if not
     */
    public boolean containsAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * tries to transfer money between two Accounts given by their account-numbers
     * 
     * @param fromAccountNumber the account-number that shall be transfered from
     * @param toAccountNumber the account-number that shall be transfered to
     * @param amount the number that shall be transfered
     * @return true, if the transfer worked; false, if not
     */
    public boolean internalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        Account from = null;
        Account to = null;

        for (Account account : this.accounts) {
            if (account != null && account.getAccountNumber() == fromAccountNumber) {
                from = account;
            } else if (account != null && account.getAccountNumber() == toAccountNumber) {
                to = account;
            }
        }

        if (from == null || to == null) {
            return false;
        }

        return from.transfer(to, amount);
    }

    /**
     * @return the length of the array 'this.accounts'
     */
    public int length() {
        return this.accounts.length;
    }

    /**
     * @return the number of Accounts in 'this.accounts'
     */
    public int size() {
        int size = 0;
        for (Account account : this.accounts) {
            if (account != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * tries to get an Account by its index in 'this.accounts'
     * 
     * @param index the index that shall be looked at
     * @return the Account at the given index; null if there is no Account there
     */
    public Account getAccount(int index) {
        try {
            return this.accounts[index];
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
