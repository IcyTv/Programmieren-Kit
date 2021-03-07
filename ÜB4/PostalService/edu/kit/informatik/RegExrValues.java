package edu.kit.informatik;

/**
 * Types of regexes to build
 * @author Michael Finger
 * @version 1.0.0
 */
public enum RegExrValues {
    /** .* */
    STRING,
    /** [A-Za-z]* */
    NAME,
    /** \d* */
    NUMBER,
    /**
     * See MailTypes
     */
    SERVICE
}