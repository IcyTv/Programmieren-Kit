package edu.kit.informatik;

public class Bank {
    private int bankCode;
    private Account[] accounts;
    int currentAccountNumber;

    public Bank(int bankCode) {
        this.bankCode = bankCode;
        this.accounts = new Account[8];
        this.currentAccountNumber = 0;
    }

    public int createAccount() {
        if (currentAccountNumber + 1 == accounts.length) {
            Account[] tmp = new Account[accounts.length * 2];
            for (int i = 0; i < accounts.length; i++) {
                tmp[i] = accounts[i];
            }
        }

        accounts[currentAccountNumber] = new Account(currentAccountNumber, bankCode, 0);

        return currentAccountNumber++;
    }

    public boolean removeAccount(int accountNumber) {
        boolean removed = false;
        int filled = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == accountNumber) {
                accounts[i] = null;
                removed = true;
            }
            if (accounts[i] != null) {
                filled++;
            }
        }

        if (filled == accounts.length / 4 && accounts.length > 8) {
            Account[] tmp = new Account[accounts.length / 4];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = accounts[i];
            }
        }

        return removed;
    }

    public boolean containsAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean internationalBankTransfer(int fromAccountNumber, int toAccountNumber, int amount) {
        Account from = null;
        Account to = null;

        for (Account account : accounts) {
            if (account != null && account.getAccountNumber() == fromAccountNumber) {
                from = account;
            } else if (account != null && account.getAccountNumber() == toAccountNumber) {
                to = account;
            }
        }

        if (from == null || to == null) {
            return false;
        }

        return from.transer(to, amount);
    }

    public int length() {
        return accounts.length;
    }

    public int size() {
        int size = 0;
        for (Account account : accounts) {
            if (account != null) {
                size++;
            }
        }
        return size;
    }

    public Account getAccount(int index) {
        try {
            return accounts[index];
        } catch (ArrayIndexOutOfBoundsException err) {
            return null;
        }
    }
}
