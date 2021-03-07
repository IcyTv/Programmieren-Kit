package edu.kit.informatik;

import java.util.ArrayList;

/**
 * Types of Mail as per Exercise Specification
 * @author Michael Finger
 * @version 1.0.0
 */
public enum MailType {
    /**
     * See Exercise 4.B
     */
    Brief, 
    /**
     * See Exercise 4.B
     */
    Einwurf,
    /**
     * See Exercise 4.B
     */
    Einschreiben,
    /**
     * See Exercise 4.B
     */
    PaketS,
    /**
     * See Exercise 4.B
     */
    PaketM,
    /**
     * See Exercise 4.B
     */
    PaketL;

    /**
     * Gets the names (as string) of all MailTypes
     * 
     * @return String[] names of all mail types
     */
    public static String[] getNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (MailType m : MailType.values()) {
            names.add(m.name());
        }
        return names.toArray(new String[names.size()]);
    }
}