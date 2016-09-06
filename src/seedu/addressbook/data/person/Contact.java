package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Contact {
    
    public static final String EXAMPLE;
    public static final String MESSAGE_CONSTRAINTS;
    public static final String VALIDATION_REGEX;
    
    public final String value;
    private boolean isPrivate;
    
    public Contact(String value, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        value = value.trim();
        if (!isValid(value)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
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

    public boolean isPrivate() {
        return isPrivate;
    }
}
