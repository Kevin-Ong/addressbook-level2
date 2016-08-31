package seedu.addressbook.data.person;

public class Unit {
    
    private int floor;
    private int unitNumber;
    
    public Unit(String unitString) {
        String[] unitItems = unitString.replace("#", "").split("-");
        this.floor = Integer.parseInt(unitItems[0]);
        this.unitNumber = Integer.parseInt(unitItems[1]);
    }
    
    @Override
    public String toString() {
        return "#" + String.format("%02d", floor) + "-" + String.format("%02d", unitNumber);
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && (this.floor == ((Unit) other).floor) && (this.unitNumber == ((Unit) other).unitNumber)); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(floor + unitNumber);
    }
}
