import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactsTests {
    Contacts contacts;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        contacts = new Contacts();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testAddContact() {
        //arrange
        int expected = 1;

        //act
        contacts.addContact("Name", "Surname", "87765554433", Group.FAMILY);
        contacts.addContact("Name1", "Surname1", "87765554433", Group.WORK);
        contacts.addContact("Name2", "Surname2", "87765554433", Group.FRIEND);
        contacts.addContact("Name3", "Surname3", "87765554433", Group.FAMILY);
        contacts.addContact("Name4", "Surname4", "87765554433", Group.WORK);
        int result = contacts.getContacts().size();

        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testRemove(String name, String surname, boolean expected, String buf) {
        //arrange
        contacts.addContact(name, surname, "123456789", Group.FAMILY);

        //act
        boolean result = contacts.removeContact(name + buf, surname);

        //assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("name1", "surname1", true, ""),
                Arguments.of("name1", "surname1", false, "1"));
    }

    @Test
    public void testFindContact() {
        //arrange
        String name = "Ivan";
        String surname = "Ivanov";
        String expected = name + surname;

        //act
        contacts.addContact(name, surname, "1234567890", Group.FAMILY);
        Contact contact = contacts.findContact(name, surname);
        String result = contact.getName() + contact.getSurname();

        //asserts
        assertEquals(expected, result);

    }


}
