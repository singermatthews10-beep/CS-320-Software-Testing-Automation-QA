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
