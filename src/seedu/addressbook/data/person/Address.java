package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must consist of all of the following: "
            + "block number, street name, unit number, and postal code, separated by a comma and a whitespace.";
    public static final String ADDRESS_VALIDATION_REGEX = "[\\d]+, .+, #[\\d]{2}-[\\d]{2}, [\\d]{6}"; //https://regex101.com/ for good online regex tester

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        
        String[] addressItems = address.split(", ");
        this.block = new Block(addressItems[0]);
        this.street = new Street(addressItems[1]);
        this.unit = new Unit(addressItems[2]);
        this.postalCode = new PostalCode(addressItems[3]);
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return block.toString() + ", "
                + street.toString() + ", "
                + unit.toString() + ", "
                + postalCode.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && (this.block.equals(((Address) other).block))
                && this.street.equals(((Address) other).street)
                && this.unit.equals(((Address) other).unit)
                && this.postalCode.equals(((Address) other).postalCode)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(block.hashCode() + street.hashCode() + unit.hashCode() + postalCode.hashCode());
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}