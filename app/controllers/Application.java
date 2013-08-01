package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.node.ObjectNode;

import play.*;
import play.mvc.*;

import static play.libs.Json.toJson;
import play.libs.Json;
import play.api.libs.json.JsArray;

import play.data.Form;
import static play.data.Form.*;

import views.html.newcontact;
import views.html.list;
import views.html.next;
import views.html.index;
import views.html.main;
import models.*;
import java.util.Date;
import java.util.ArrayList;

public class Application extends Controller {

    /**
     * Defines a form wrapping the Contact class.
     */
    final static Form<Contact> contactForm = form(Contact.class);

    public static Result index() {
        System.out.println("index()");
        return ok(index.render("Your new application is ready, really. Now I understand....","this is a message"));
    }

    public static Result next() {
        System.out.println("next()");
        return ok(next.render("deuxième page","2nd page"));
    }

    public static Result message() {
        ObjectNode result = Json.newObject();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        result.put("msg", sdf.format(new Date()));

        return ok(result);

    }

    public static Result list() {

       return ok(list.render(Contact.find.all()));
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


    public static Result add() {
        Form<Contact> filledForm = contactForm.bindFromRequest();
        Contact contact = filledForm.get();
        contact.save();
        return ok(index.render("Your new application is ready, really. Now I understand....","this is a message"));
    }

    public static Result newContact() {
        return ok(newcontact.render(contactForm));
    }
                      /*
    public static ObjectNode contactsToJSON(List<Contact> contacts) {
        ObjectNode result = Json.newObject();
        JsArray array = new JsArray();
        for (Contact c : contacts) {
           ObjectNode cjs = contactToJSON(c);
            array.add(cjs);
        }
    }


    public static ObjectNode contactToJSON(Contact contact) {
        ObjectNode result = Json.newObject();

        result.put("firstName", contact.firstName);
        result.put("lastName", contact.lastName);
        result.put("phoneNumber", contact.phoneNumber);

        return result;
    }             */
}
