package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email extends Contact {

    private boolean isPrivate;

    /**
     * Validates given email.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public Email(String email, boolean isPrivate) throws IllegalValueException {
        this.EXAMPLE = "valid@e.mail";
        this.MESSAGE_CONSTRAINTS =
                "Person emails should be 2 alphanumeric/period strings separated by '@'";
        this.VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";
        
        this.isPrivate = isPrivate;
        email = email.trim();
        if (!this.isValid(email)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        this.value = email;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}