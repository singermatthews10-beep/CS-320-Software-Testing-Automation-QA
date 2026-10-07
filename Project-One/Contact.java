package contact;

public class Contact {

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {

        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID");
        }

        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }

        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }

        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name");
        }

        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        this.phone = phone;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }

        this.address = address;
    }
}
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

package contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactServiceTest {

    @Test
    void testAddContact() {
        ContactService service = new ContactService();

        Contact contact = new Contact(
                "12345",
                "Chris",
                "Walker",
                "5551234567",
                "123 Main Street");

        service.addContact(contact);

        assertNotNull(service.findContact("12345"));
        assertEquals("Chris",
                service.findContact("12345").getFirstName());
    }

    @Test
    void testDuplicateContactId() {
        ContactService service = new ContactService();

        Contact contact1 = new Contact(
                "12345",
                "Chris",
                "Walker",
                "5551234567",
                "123 Main Street");

        Contact contact2 = new Contact(
                "12345",
                "Alex",
                "George",
                "5559876543",
                "456 Main Street");

        service.addContact(contact1);

        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();

        Contact contact = new Contact(
                "12345",
                "Chris",
                "Walker",
                "5551234567",
                "123 Main Street");

        service.addContact(contact);
        service.deleteContact("12345");

        assertNull(service.findContact("12345"));
    }

    @Test
    void testDeleteContactNotFound() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("99999");
        });
    }

    @Test
    void testUpdateFirstName() {
        ContactService service = createService();

        service.updateFirstName("12345", "James");

        assertEquals(
                "James",
                service.findContact("12345").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        ContactService service = createService();

        service.updateLastName("12345", "Smith");

        assertEquals(
                "Smith",
                service.findContact("12345").getLastName());
    }

    @Test
    void testUpdatePhone() {
        ContactService service = createService();

        service.updatePhone("12345", "5559876543");

        assertEquals(
                "5559876543",
                service.findContact("12345").getPhone());
    }

    @Test
    void testUpdateAddress() {
        ContactService service = createService();

        service.updateAddress(
                "12345",
                "456 Oak Street");

        assertEquals(
                "456 Oak Street",
                service.findContact("12345").getAddress());
    }

    @Test
    void testUpdateContactNotFound() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("99999", "James");
        });
    }

    private ContactService createService() {
        ContactService service = new ContactService();

        Contact contact = new Contact(
                "12345",
                "Chris",
                "Walker",
                "5551234567",
                "123 Main Street");

        service.addContact(contact);

        return service;
    }
}
package contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void testContact() {
        Contact contact = new Contact(
                "12345",
                "Chris",
                "Walker",
                "5551234567",
                "123 Main Street");

        assertEquals("12345", contact.getContactId());
        assertEquals("Chris", contact.getFirstName());
        assertEquals("Walker", contact.getLastName());
        assertEquals("5551234567", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345678901",
                    "Chris",
                    "Walker",
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    null,
                    "Chris",
                    "Walker",
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Christopher",
                    "Walker",
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    null,
                    "Walker",
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "WalkerSmith",
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    null,
                    "5551234567",
                    "123 Main Street");
        });
    }

    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    "555123456",
                    "123 Main Street");
        });
    }

    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    "55512345678",
                    "123 Main Street");
        });
    }

    @Test
    void testPhoneContainsLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    "555ABC4567",
                    "123 Main Street");
        });
    }

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    null,
                    "123 Main Street");
        });
    }

    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    "5551234567",
                    "1234567890123456789012345678901");
        });
    }

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(
                    "12345",
                    "Chris",
                    "Walker",
                    "5551234567",
                    null);
        });
    }
}
