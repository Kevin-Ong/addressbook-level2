package seedu.addressbook.data.person;

public class Street {

    private String streetName;
    
    public Street(String streetName) {
        this.streetName = streetName;
    }
    
    @Override
    public String toString() {
        return this.streetName;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceof handles nulls
                && this.streetName.equals(((Street) other).streetName)); // state check
    }

    @Override
    public int hashCode() {
        return streetName.hashCode();
    }
}
