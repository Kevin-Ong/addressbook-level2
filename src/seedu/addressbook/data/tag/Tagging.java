package seedu.addressbook.data.tag;

import java.util.ArrayList;

import seedu.addressbook.data.person.Person;

public class Tagging {
    
    private class TaggingAction {
        public Tagging tagging;
        public boolean isAdded;
        
        public TaggingAction(Tagging tagging, boolean isAdded) {
            this.tagging = tagging;
            this.isAdded = isAdded;
        }
    }
    
    public static final boolean ADD_TAG_BOOLEAN = true;
    public static final boolean REMOVE_TAG_BOOLEAN = false;
    
    public Person person;
    public Tag personTag;
    
    public static ArrayList<Tagging> taggings = new ArrayList<>();
    public static ArrayList<TaggingAction> taggingActions = new ArrayList<>();
    
    public Tagging(Person person, Tag tag) {
        this.person = person;
        this.personTag = tag;
    }
    
    //Here I assumed the program already adds the tag to the person's UniqueTaglist
    public static void addTagging(Person person, Tag tag) { 
        Tagging newTag = new Tagging(person, tag);
        taggings.add(newTag);
        taggingActions.add(newTag.new TaggingAction(newTag, ADD_TAG_BOOLEAN));
    }
    
    //Here I assumed the program already removes the tag from the person's UniqueTaglist
    public static void removeTagging(Person person, Tag tag) {
        Tagging checkTag = new Tagging(person, tag);
        taggings.remove(checkTag); //don't know if this works as it is by reference
        taggingActions.add(checkTag.new TaggingAction(checkTag, REMOVE_TAG_BOOLEAN));
    }
    
    public static String returnTaggingsInSession() {
        String s = "";
        for (TaggingAction taggingAction : taggingActions) {
            if (taggingAction.isAdded == ADD_TAG_BOOLEAN) {
                s += "+ ";
            } else {
                s += "- ";
            }
            
            s += taggingAction.tagging.person.getName().toString() + " " 
               + taggingAction.tagging.personTag.toString() + "\n";
        }
        
        return s;
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tagging // instanceof handles nulls
                && this.person.equals(((Tagging) other).person)
                && this.personTag.equals(((Tagging) other).personTag)); // state check
    }
}
