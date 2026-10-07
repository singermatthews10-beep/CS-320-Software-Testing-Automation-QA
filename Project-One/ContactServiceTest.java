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
