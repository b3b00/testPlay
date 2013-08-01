package models;

/**
 * Created with IntelliJ IDEA.
 * User: beboo
 * Date: 13/02/13
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Contact extends Model {

    public static Finder<Integer,Contact> find = new Finder<Integer,Contact>(
            Integer.class, Contact.class
    );

    @Id
    public Integer id;

    public String lastName;

    public String firstName;

    public String phoneNumber;


}