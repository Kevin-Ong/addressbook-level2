package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name extends Contact{
    
    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        this.EXAMPLE = "John Doe";
        this.MESSAGE_CONSTRAINTS = "Person names should be spaces or alphabetic characters";
        this.VALIDATION_REGEX = "[\\p{Alpha} ]+";
        
        name = name.trim();
        if (!this.isValid(name)) {
            throw new IllegalValueException(MESSAGE_CONSTRAINTS);
        }
        this.value = name;
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInName() {
        return Arrays.asList(value.split("\\s+"));
    }

}
