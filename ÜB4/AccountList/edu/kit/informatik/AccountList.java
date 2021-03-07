package edu.kit.informatik;

/**
 * AccountList
 * @author Michael Finger
 * @version 1.0.0
 */
public class AccountList {
    private AccountList next;
    private Account data;

    /**
     * empty constructor for account list
     */
    public AccountList() {
        this.next = new AccountList(null, null);
        this.data = null;
    }

    /**
     * Private Constructor for account list, useful for adding data
     * @param next next AccountList element
     * @param data Data to add
     */
    private AccountList(AccountList next, Account data) {
        this.next = next;
        this.data = data;
    }

    /**
     * Adds data to the account list
     * @param account Account to add
     */
    public void add(Account account) {
        AccountList head = this.next;

        while (head.next != null) {
            head = head.next;
        }

        head.data = account; //TODO setter?
        head.next = new AccountList(null, null); 
    }

    /**
     * Adds an account at the index
     * @param account Account to add
     * @param index index to add it at
     */
    public void add(Account account, int index) {
        int i = 0;
        AccountList head = this.next;

        while (head.next != null && i != index - 1) {
            head = head.next;
            i++;
        } 

        if (head.next != null) {
            head.next = new AccountList(head.next, account);
        } else {
            head.data = account;
            head.next = new AccountList(null, null);
        }
    } 

    /**
     * Removes the item at the specified index
     * @param index Index to remove
     * @return if removed
     */
    public boolean remove(int index) {
        AccountList previous = this;
        AccountList head = this.next;
        int i = 0;

        while (head.next != null && index != i) {
            previous = head;
            head = head.next;
            i++;
        }
        
        if (index == i) {
            previous.next = head.next;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets first element
     * @return First Element
     */
    public Account getFirst() {
        return this.next.data;
    }

    /**
     * Gets last element
     * @return Last Element
     */
    public Account getLast() {
        AccountList previous = this;
        AccountList head = this.next;

        while (head.next != null) {
            previous = head;
            head = head.next;
        }

        return previous.data;
    }

    /**
     * Gets element at index
     * @param index Index to get
     * @return Account at the specified index
     */
    public Account get(int index) {
        AccountList head = this.next;
        int i = 0;

        while (head.next != null && i != index) {
            head = head.next;
            i++;
        }

        return head.data;
    }

    /**
     * Contains supplied account
     * @param account Account to test for
     * @return if account is present
     */
    public boolean contains(Account account) {
        AccountList head = this.next;

        while (head.next != null) {
            if (account.compareTo(head.data) == 0) {
                return true;
            }
            head = head.next;
        }

        return head.data != null && account.compareTo(head.data) == 0;
    }

    /**
     * Size of the AccountList
     * @return size
     */
    public int size() {
        AccountList head = this.next;
        int size = 0; //Start at 0 because last element is always empty, and therfore does not count
        
        while (head.next != null) {
            head = head.next;
            size++;
        }

        return size;
    }

    /**
     * Gets Account by its id number
     * @param accountNumber AccountNumberToGet
     * @return Account | null
     */
    public Account getByAccountNumber(int accountNumber) {
        AccountList head = this.next;

        while (head.next != null) {
            if (head.data.getAccountNumber() == accountNumber) {
                return head.data;
            }

            head = head.next;
        }

        return null;
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        String ret = "";
        AccountList head = this.next;

        while (head.next != null) {
            ret += head.data.toString() + "\n";
            head = head.next;
        }

        return ret.trim();
        
    }
}