package edu.kit.informatik;

public class Account {
    private int accountNumber;
    private int bankCode;
    private int balance;

    public Account(int accountNumber, int bankCode, int balance) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.balance = balance;
    }

    public boolean withdraw(int amount) {
        if (balance - amount < 0) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean transer(Account account, int amount) {
        if (this.balance - amount < 0) {
            return false;
        } else {
            this.withdraw(amount);
            account.deposit(amount);
            return true;
        }
    }

    // Getters and Setters

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public int getBankCode() {
        return this.bankCode;
    }

    public int getBalance() {
        return this.balance;
    }

}
