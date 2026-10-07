package contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        for (Contact existingContact : contacts) {
            if (existingContact.getContactId().equals(contact.getContactId())) {
                throw new IllegalArgumentException("Contact ID already exists");
            }
        }

        contacts.add(contact);
    }

    public void deleteContact(String contactId) {
        Contact contactToDelete = findContact(contactId);

        if (contactToDelete == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contacts.remove(contactToDelete);
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contact.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contact.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contact.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact contact = findContact(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contact.setAddress(address);
    }

    public Contact findContact(String contactId) {
        for (Contact contact : contacts) {
            if (contact.getContactId().equals(contactId)) {
                return contact;
            }
        }

        return null;
    }
}
