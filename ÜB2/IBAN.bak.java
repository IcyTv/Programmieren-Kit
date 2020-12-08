import java.math.BigInteger;

public class IBAN {
    public static void main(String[] args) {
        IBAN iban = new IBAN(args[0], args[1], args[2]);
        System.out.println(iban);
    }

    private String isoCountryCode;
    private String bankCode;
    private String accountNo;

    public IBAN(String country, String blz, String kontoNr) {
        this.isoCountryCode = country;
        this.bankCode = blz;
        this.accountNo = kontoNr;
    }

    @Override
    public String toString() {
        String kN = String.format("%1$10s", this.accountNo.toString()).replace(" ", "0");

        String countryCode = "";

        for (char c : isoCountryCode.toString().toCharArray()) {
            countryCode += ((int) c - 55);
        }

        countryCode += "00";

        String checksumInitialValue = bankCode + kN + countryCode;

        BigInteger checksum = new BigInteger(checksumInitialValue);

        checksum = new BigInteger("98").subtract(checksum.mod(new BigInteger("97")));

        String out = this.isoCountryCode + String.format("%1$2s", checksum.toString()).replace(" ", "0") + this.bankCode
                + kN;

        return out.replaceAll("(.{4})", "$1 ").trim();
    }

}
