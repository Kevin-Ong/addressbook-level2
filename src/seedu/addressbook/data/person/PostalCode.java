package seedu.addressbook.data.person;

public class PostalCode {
    private int postalCode;
        
        public PostalCode(String postalCodeString) {
            this.postalCode = Integer.parseInt(postalCodeString);
        }
        
        @Override
        public String toString() {
            return Integer.toString(postalCode);
        }
        
        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof PostalCode // instanceof handles nulls
                    && this.postalCode == (((PostalCode) other).postalCode)); // state check
        }
    
        @Override
        public int hashCode() {
            return Integer.hashCode(postalCode);
        }
}
