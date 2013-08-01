package controllers;

import models.Contact;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.index;
import views.html.list;
import views.html.newcontact;
import views.html.next;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static play.data.Form.*;
import static play.libs.Json.toJson;

public class Contacts extends Controller {

    /**
     * Defines a form wrapping the Contact class.
     */
    final static Form<Contact> contactForm = form(Contact.class);

    /*=================================================
     *
     * SYNCHRONOUSLY
     *
     *=================================================
     */

    public static Result add() {
        Form<Contact> filledForm = contactForm.bindFromRequest();
        Contact contact = filledForm.get();
        contact.save();
        return ok(index.render("Your new application is ready, really. Now I understand....","this is a message"));
    }


    public static Result newContact() {
        return ok(newcontact.render(contactForm));
    }


    /*=================================================
     *
     * ASYNCHRONOUSLY
     *
     *=================================================
     */

    public static Result list() {
       return ok(list.render(new ArrayList<Contact>()));
    }

    public static Result getAll() {
        return ok(toJson(Contact.find.all()));
    }


    public static Result addContact() {
        Form<Contact> filledForm = contactForm.bindFromRequest();
        Contact contact = filledForm.get();
        contact.save();
        contact.find.all();
        return ok(toJson(Contact.find.all()));
    }


    public static Result deleteContact(Integer id) {
        Contact.find.ref(id).delete();
        return ok(toJson(Contact.find.all()));
    }




}
