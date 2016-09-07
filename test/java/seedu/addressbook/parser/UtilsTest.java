package seedu.addressbook.parser;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

public class UtilsTest {
    
    @Before
    public void setup() {}
    
    @Test
    public void nullObject_returnsTrue() {
        Object o = null;
        assertTrue(Utils.isAnyNull(o));
    }
    
    @Test
    public void nullObjects_returnsTrue() {
        Person p = null;
        Name n = null;
        Email e = null;
        Address a = null;
        assertTrue(Utils.isAnyNull(p, n, e, a));
    }
    
    @Test
    public void oneNullObject_returnsTrue() {
        try {
            Person p = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), null, new UniqueTagList(new Tag("tag1")));
            assertTrue(Utils.isAnyNull(p.getName(), p.getPhone(), p.getEmail(), p.getAddress()));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void noNullObjects_returnsFalse() {
        try {
            Person p = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            assertFalse(Utils.isAnyNull(p.getName(), p.getPhone(), p.getEmail(), p.getAddress()));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void duplicatePersonReferences_returnsFalse() {
        try {
            Person p1 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            Person p2 = p1;
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(p1);
            persons.add(p2);
            
            assertFalse(Utils.elementsAreUnique(persons));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void duplicatePersonAttributes_returnsFalse() {
        try {
            Person p1 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            Person p2 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(p1);
            persons.add(p2);
            
            //this fails, because the persons are compared by reference and not by attributes
            assertFalse(Utils.elementsAreUnique(persons));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void differentName_returnsTrue() {
        try {
            Person p1 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            Person p2 = new Person(new Name("Jane Doe"), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(p1);
            persons.add(p2);
            
            assertTrue(Utils.elementsAreUnique(persons));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void extraTag_returnsTrue() {
        try {
            Person p1 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1")));
            Person p2 = new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, true), new Email(Email.EXAMPLE, false), new Address(Address.EXAMPLE, true), new UniqueTagList(new Tag("tag1"), new Tag("tag2")));
            ArrayList<Person> persons = new ArrayList<>();
            persons.add(p1);
            persons.add(p2);
            
            assertTrue(Utils.elementsAreUnique(persons));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
