package edu.kit.informatik;

/**
 * class Test
 * 
 * @author Michael Finger, Alma Schmitt, Merlin Michel
 * @version v1.0.0
 */
public final class Test {
    
    /**
     * mein method
     * 
     * @param args an unnecessary option of an input
     */
    public static void main(String[] args) {
        Bank bank1 = new Bank(1);   //create a Bank with the bankCode '1'
        Bank bank2 = new Bank(2);   //create a Bank with the bankCode '2'

        int accountNumber1Bank1 = bank1.createAccount();    //creates a new Account at the first Bank
        int accountNumber2Bank1 = bank1.createAccount();    //creates a new Account at the first Bank
        int accountNumber1Bank2 = bank2.createAccount();    //creates a new Account at the second Bank
        int accountNumber2Bank2 = bank2.createAccount();    //creates a new Account at the second Bank

        assert (bank1.containsAccount(accountNumber1Bank1)); //is Account 'accountNumber1Bank1' in the first Bank
        assert (bank1.containsAccount(accountNumber2Bank1)); //is Account 'accountNumber2Bank1' in the first Bank
        assert (bank2.containsAccount(accountNumber1Bank2)); //is Account 'accountNumber1Bank2' in the second Bank
        assert (bank2.containsAccount(accountNumber2Bank2)); //is Account 'accountNumber2Bank2' in the second Bank

        assert (!bank1.containsAccount(12));        //is '12' not an Account in the first Bank?

        //is a transfer between 'accountNumber1Bank1' and 'accountNumber2Bank1' not possible?
        assert (!bank1.internalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 10));

        Account account1Bank1 = bank1.getAccount(0); //is it possible to get Accounts by their account-number?
        Account account2Bank1 = bank1.getAccount(1); //is it possible to get Accounts by their account-number?

        //after depositing some money:
        account1Bank1.deposit(100);
        //is a transfer between 'accountNumber1Bank1' and 'accountNumber2Bank1' possible?
        assert (bank1.internalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 10));

        //did the transfer work out correctly?
        assert (account1Bank1.getBalance() == 90); //on Account 'account1Bank1' is exactly 90 left
        assert (account2Bank1.getBalance() == 10); //on Account 'account2Bank1' is axactly 10
        //is a transfer between 'accountNumber1Bank1' and 'accountNumber2Bank1' with higher amounts than available still
        //not possible?
        assert (!bank1.internalBankTransfer(accountNumber1Bank1, accountNumber2Bank1, 1000));

        assert (account1Bank1.withdraw(10));           //can '10' be withdrawn from 'account1Bank1'
        assert (account1Bank1.getBalance() == 80);     //and are there now exactly '80' left on 'account1Bank1'

        assert (!account1Bank1.withdraw(100));         //can '100' not be withdrawn from 'account1Bank1'
        assert (account1Bank1.getBalance() == 80);     //and are there now still '80' left on 'account1Bank1'

        account1Bank1.deposit(100);                    //after there are '100' more deposited on 'account1Bank1'
        assert (account1Bank1.getBalance() == 180);    //are there now exactly '180'?

        //is the transfer of '10' from 'account1Bank1' to 'account2Bank1' possible?
        assert (account1Bank1.transfer(account2Bank1, 10));
        assert (account1Bank1.getBalance() == 170);  //and are there now exactly '170' left on 'account1Bank1'
        assert (account2Bank1.getBalance() == 20);   //and are there now exactly '20' on 'account2Bank1'

        //is the transfer of '100' from 'account2Bank1' to 'account1Bank1' not possible?
        assert (!account2Bank1.transfer(account1Bank1, 100));
        assert (account1Bank1.getBalance() == 170);  //and are there still exactly '170' left on 'account1Bank1'
        assert (account2Bank1.getBalance() == 20);   //and are there still exactly '20' on 'account2Bank1'

        assert (bank1.length() == 8);               //is 'bank.length()' still the initial value
        assert (!bank1.removeAccount(12));          //is it not possible to remove an Account that doesn't exist
        assert (bank1.length() == 8);               //is 'bank.length()' still the initial value
        assert (bank1.size() == 2);                 //are there still only the two Accounts that have been created
        assert (bank1.removeAccount(accountNumber2Bank1));  //can an existing Account be removed?
        assert (bank1.size() == 1);                 //is there now only '1' Account left in 'bank1'

        assert (bank1.getAccount(15) == null);      //a non-existing Account is given as 'null'
    }
}
