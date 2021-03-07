package edu.kit.informatik;

/**
 * Object store for a specific Mail. Could be used for storing additional Data
 * for Mail's, such as Sender, Contents, Special Pricing, etc.
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public final class Mail implements Comparable<Mail> {
    private MailType type;

    /**
     * Constructor for MailType
     * Private, because the use of <code>Mail.fromString(mailType)</code> is assumed
     * as to not unnecessarily export the MailType enum
     * @param type
     */
    private Mail(MailType type) {
        this.type = type;
    }

    /**
     * Gets the price of this mail
     * @return double Price of the Mail
     */
    public double getPrice() {
        return Mail.getPrice(this.type);
    }

    /**
     * Gets this Mails MailType, primarily used in HashMaps
     * @return MailType type of this mail
     */
    public MailType getType() {
        return this.type;
    }

    /**
     * Gets the name of this mail
     * @return String Name of this Mail
     */
    public String getName() {
        return this.type.name();
    }

    /**
     * Converts String to Mail.
     * ATTENTION this is a soft fail Method
     * @param mailType String (Choice of MailType names)
     * @return new MailType | null
     */
    public static Mail fromString(String mailType) {
        for (MailType type: MailType.values()) {
            if (type.name().equals(mailType)) {
                return new Mail(type);
            }
        }
        return null;
    }

    /**
     * Used to compare Mails
     */
    @Override
    public int compareTo(Mail o) {
        return this.type.name().compareTo(o.getType().name());
    }

    /**
     * Used for its application in HashMaps, Just uses the MailTypes hash
     */
    @Override
    public int hashCode() {
        return this.type.hashCode();
    }

    //Static Methods

    /**
     * Gets the price for a specified Mail Type
     * @param mailType
     * @return double: Price for mail
     */
    public static double getPrice(MailType mailType) {
        switch(mailType) {
            case Brief:
                return .7;
            case Einwurf:
                return 1.2;
            case Einschreiben:
                return 2.0;
            case PaketS:
                return 5.0;
            case PaketM:
                return 6.0;
            case PaketL:
                return 7.0;
            default:
                return 0.0;
        } 
    }
}
