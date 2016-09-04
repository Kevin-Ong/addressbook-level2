package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.*;

public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";
    
    public static String addDivider() {
        return DIVIDER;
    }
    
    public static String addLinePrefix(String message) {
        return LINE_PREFIX + message;
    }
    
    public static String formatMessage(String message) {
        return addLinePrefix(message.replace("\n", LS + LINE_PREFIX));
    }
}
