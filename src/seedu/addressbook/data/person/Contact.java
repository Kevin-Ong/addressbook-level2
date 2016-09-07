package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Contact {
    
    public static String EXAMPLE;
    public static String MESSAGE_CONSTRAINTS;
    public static String VALIDATION_REGEX;
    
    public String value;
    
    protected Contact() {
    }
    
    /**
     * Checks if a given string is a valid contact information.
     */
    public static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact // instanceof handles nulls
                && this.value.equals(((Contact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
