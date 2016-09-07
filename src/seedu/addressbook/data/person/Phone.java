package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends Contact {

    private boolean isPrivate;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws IllegalValueException {
        this.EXAMPLE = "123456789";
        this.MESSAGE_CONSTRAINTS =
                "Person phone numbers should only contain numbers";
        this.VALIDATION_REGEX = "\\d+";
        
        this.isPrivate = isPrivate;
        phone = phone.trim();
        if (!this.isValid(phone)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        this.value = phone;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
