package seedu.addressbook.data.person;

public class Block {

    private int blockNumber;
    
    public Block(String blockNumberString) {
        this.blockNumber = Integer.parseInt(blockNumberString);
    }
    
    @Override
    public String toString() {
        return Integer.toString(blockNumber);
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.blockNumber == (((Block) other).blockNumber)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(blockNumber);
    }
}
