package edu.kit.informatik;

import java.util.ArrayList;


/**
 * Regular Expression Builder, to simplify ease of Parameter Checking
 * 
 * @author Michael Finger
 * @version 1.0.0
 */
public class RegExr {
    private ArrayList<RegExrValues> regExrValues;
    private final String separator;

    /**
     * Constructor for the Regular Expression
     * @param separator Character or String that seperates the different Expressions
     */
    public RegExr(String separator) {
        this.separator = separator;
        this.regExrValues = new ArrayList<RegExrValues>();
    }

    /**
     * Adds a new Check to the regular expression, seperated by the Separator
     * @param value Check to add
     * @return this for chaining
     */
    public RegExr add(RegExrValues value) {
        this.regExrValues.add(value);
        return this;
    }

    /**
     * Same as add(RegExrValues) but with Strings for ease of use.
     * ATTENTION: This is a Hard fail method if the String is not a valid Check
     * @param value Choice of RegExrValues as String
     * @return this for chaining
     */
    public RegExr add(String value) {
        add(RegExrValues.valueOf(value));
        return this;
    }

    /**
     * Builds and returns the String representation of this Regular Expression
     * @return String representation of the regular Expression
     */
    public String toString() {
        String ret = "";
        for (RegExrValues val: this.regExrValues) {
            switch(val) {
                case STRING: //Can be anything of any length
                    ret += ".*";
                    break;
                case NAME: //Can be anything a-z of any length
                    ret += "[A-Za-z]*";
                    break;
                case NUMBER: //Can be any Whole Number
                    ret += "\\d*";
                    break;
                case SERVICE: //Can be any of the MailType names
                    ret += "(" + String.join("|", MailType.getNames()) + ")";
                    break;
                default:
                    break;
            }
            ret += separator;
        }
        return ret.substring(0, ret.length() - 1);
    }
}

