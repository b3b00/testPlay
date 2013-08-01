import org.junit.Test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import static org.fest.assertions.Assertions.assertThat;

import models.Contact;

public class ContactTest {

    @Test
    public void create() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Contact contact = new Contact();
                contact.firstName = "toto";
                contact.lastName = "toto";
                contact.phoneNumber = "0660850330";
                contact.save();
                assertThat(contact.id).isNotNull();
            }
        });
    }

}