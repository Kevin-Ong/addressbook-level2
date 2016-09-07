package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address extends Contact {

    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.EXAMPLE = "123, some street";
        this.MESSAGE_CONSTRAINTS =
                "Person addresses can be in any format";
        this.VALIDATION_REGEX = ".+";
        
        this.isPrivate = isPrivate;
        if (!this.isValid(address)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        this.value = address;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}