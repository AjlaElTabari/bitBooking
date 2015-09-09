package models;

import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Model of App_User. App_User is a person who sign up into database on bitBooking.com web page
 * and has permissions depending on type of user
 */
@Entity
public class App_User extends Model{

    private static Finder<String, App_User> finder = new Finder<>(String.class, App_User.class);
   
    /*
     * App_User attributes
     */
    @Id
    public Integer id;
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String phoneNumber;


    /**
     * Default constructor
     */
    public App_User() {
    }

    /**
     * Constructor for creating new App_User object.
     *
     * @param firstName   - App_User's first name.
     * @param lastName    - App_User's last name.
     * @param email       - App_User's email address.
     * @param password    - App_User's password.
     * @param phoneNumber - App_User's phone number.
     */
    public App_User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        System.out.println(this.password);
        this.phoneNumber = phoneNumber;
    }




    public static App_User authenticate(String email, String password) {
        App_User user = finder.where().eq("email", email.toString()).findUnique();

        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }

    public void hashPass() {
       this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }
}
