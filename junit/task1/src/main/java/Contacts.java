import java.util.HashMap;
import java.util.Map;

public class Contacts {
    private Map<String, Contact> contacts = new HashMap<>();

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public void addContact(String name, String surname, String phone, Group group) {
        contacts.put(phone, new Contact(name, surname, phone, group));
    }

    public boolean removeContact(String name, String surname) {
        Contact contact;
        boolean result = false;
        for (Map.Entry<String, Contact> contactEntry : contacts.entrySet()) {
            contact = contactEntry.getValue();
            if (contact.getName().equals(name) && contact.getSurname().equals(surname)) {
                contacts.remove(contactEntry.getKey());
                result = true;
                break;
            }
        }
        return result;
    }

    public Contact findContact(String phone) {
        return contacts.getOrDefault(phone, null);
    }

    public Contact findContact(String name, String surname) {
        Contact contact = null;
        for (Map.Entry<String, Contact> contactEntry : contacts.entrySet()) {
            contact = contactEntry.getValue();
            if (contact.getName().equals(name) && contact.getSurname().equals(surname))
                return contact;
        }
        return contact;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            result.append(contacts.toString()).append("\n");
        }
        return result.toString();
    }
}