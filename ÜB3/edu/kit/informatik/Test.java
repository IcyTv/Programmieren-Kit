package edu.kit.informatik;

public final class Test {
    public static void main(String[] args) {
        Bank bank1 = new Bank(1);
        Bank bank2 = new Bank(2);

        int accountNumber1Bank1 = bank1.createAccount();
        int accountNumber2Bank1 = bank1.createAccount();
        int accountNumber1Bank2 = bank2.createAccount();
        int accountNumber2Bank2 = bank2.createAccount();

        assert (bank1.containsAccount(accountNumber1Bank1));
        assert (bank1.containsAccount(accountNumber2Bank1));
        assert (bank2.containsAccount(accountNumber1Bank2));
        assert (bank2.containsAccount(accountNumber2Bank2));

        assert (!bank1.containsAccount(12));

        assert (!bank1.internationalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 10));

        Account account1Bank1 = bank1.getAccount(0);
        Account account2Bank1 = bank1.getAccount(1);

        account1Bank1.deposit(100);
        assert (bank1.internationalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 10));

        assert (account1Bank1.getBalance() == 90);
        assert (account2Bank1.getBalance() == 10);
        assert (!bank1.internationalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 1000));

        assert (account1Bank1.withdraw(10));
        assert (account1Bank1.getBalance() == 80);

        assert (!account1Bank1.withdraw(100));
        assert (account1Bank1.getBalance() == 80);

        account1Bank1.deposit(100);
        assert (account1Bank1.getBalance() == 180);

        assert (account1Bank1.transer(account2Bank1, 10));
        assert (account1Bank1.getBalance() == 170);
        assert (account2Bank1.getBalance() == 20);

        assert (!account2Bank1.transer(account1Bank1, 100));
        assert (account1Bank1.getBalance() == 170);
        assert (account2Bank1.getBalance() == 20);

        assert (bank1.length() == 8);
        assert (!bank1.removeAccount(12));
        assert (bank1.length() == 8);
        assert (bank1.size() == 2);
        assert (bank1.removeAccount(accountNumber2Bank1));
        assert (bank1.size() == 1);

        assert (bank1.getAccount(15) == null);
    }
}
