import java.math.BigInteger;

/**
 * Representation of an IBAN
 */
public class IBAN {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String countryCode;
    private String bankCode;
    private String accountNumber;

    /**
     * Constructor for the IBAN
     * 
     * @param countryCode   ISO Country code of the Account
     * @param bankCode      Code for the issuing bank
     * @param accountNumber Number of the account at the issuing bank
     */
    public IBAN(String countryCode, String bankCode, String accountNumber) {
        this.countryCode = countryCode;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the String reperesentation of the IBAN
     * 
     * @return The string representation of the IBAN
     */
    @Override
    public String toString() {
        String bankCodeAndAccountNumber = this.bankCode + padAccountNumber(); // concatinates bankCode and accountNumber
        String checknumber = checkNumberCreatedFrom(bankCodeAndAccountNumber);
        String iban = this.countryCode + checknumber + bankCodeAndAccountNumber;
        char[] charIban = iban.toCharArray();
        String readableIban = "";
        for (int i = 0; i < charIban.length; i++) {
            readableIban += Character.toString(charIban[i]);
            if ((i + 1) % 4 == 0) {
                readableIban += " ";
            }
        }
        return readableIban;
    }

    /**
     * padAccountnumber extends the accountNumber by leading zeros, to make it 10
     * digits long
     *
     * @param accountNumber The account-number without leading zeros with length <
     *                      10
     * 
     * @return the extended account-number as String
     */
    private String padAccountNumber() { // adds the amount of missing zeros to accountNumber
        String n = "";
        int missingZeroAmount = 10 - this.accountNumber.toCharArray().length;
        for (int i = 0; i < missingZeroAmount; i++) {
            n += "0";
        }
        return n + this.accountNumber;
    }

    /**
     * checkNumberCreatedFrom creates a checknumber out of the bankCode concatinated
     * with accountnumber concatinated with the countryCode translated to its
     * corresponding number by subtracting the rest of the diversion of the number
     * from the concatinatin by 97 from 98.
     *
     * @param bcaan       the concatination of bankCode and accountNumber
     *                    (BankCodeAndAccountNumber: bcaan)
     * 
     * @param countryCode the corresponding number to the countryCode
     * 
     * @return checknumber the number with a value between 1 and 98
     */
    private String checkNumberCreatedFrom(String bcaan) {
        BigInteger numerator = new BigInteger(bcaan + countryCodeToNumber());
        BigInteger denominator = new BigInteger("97");
        BigInteger modulo = numerator.mod(denominator);
        BigInteger sub = new BigInteger("98").subtract(modulo);
        return (sub.intValue() < 10) ? "0" + sub.toString() : sub.toString();
    }

    /**
     * converts the countryCode to corresponding numbers by taking their position in
     * the latin alphabat and adding the value 9.
     *
     * @param countryCode the String that contains the country-code (f.e. DE =
     *                    Deutschland)
     * 
     * @return the corresponding number to the countrycode concatinated with two
     *         zeros "00"
     */
    private String countryCodeToNumber() {
        int firstLetterIndex = ALPHABET.indexOf(this.countryCode.toCharArray()[0]);
        int secondLetterIndex = ALPHABET.indexOf(this.countryCode.toCharArray()[1]);
        return Integer.toString(firstLetterIndex + 10) + Integer.toString(secondLetterIndex + 10) + "00";
    }

    /**
     * Usage: java IBAN "ISO country code" "bank number" "account number".
     * 
     * @param args [ISO country code, bank number, account number]
     */
    public static void main(String[] args) {
        IBAN myIBAN = new IBAN(args[0], args[1], args[2]);
        System.out.println(myIBAN);
    }
}