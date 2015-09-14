package models;

import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Model of App_User. App_User is a person who sign up into database on bitBooking.com web page
 * and has permissions depending on type of user
 */
@Entity
public class App_User extends Model {

    public static final Integer USR_TYPE_BUYER = 4;
    public static Finder<String, App_User> finder = new Finder<>(String.class, App_User.class);

    /*
     * App_User attributes
     */
    @Id
    public Integer id;

    @Constraints.Required(message = "Please input your first name")
    @Constraints.MinLength(value = 2, message = "First name must be at least 2 letters long!")
    @Constraints.Pattern("^[a-zA-Z]+$")
    public String firstname;

    @Constraints.Required(message = "Please input your last name")
    @Constraints.MinLength(value = 2, message = "Last name must be at leastt 2 letters long!")
    @Constraints.Pattern("^[a-zA-Z]+$")
    public String lastname;

    @Constraints.Required(message = "Please input email!")
    @Column(unique = true)
    public String email;

    @Constraints.Required(message = "Please input password!")
    @Constraints.MinLength(6)
    public String password;

    @Constraints.Required
    @Constraints.MaxLength(15)
    @Constraints.Pattern(value = "\\d+", message = "Phone number can contain digits only!")
    public String phoneNumber;

    @ManyToOne
    public Integer userTypeId = USR_TYPE_BUYER;


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

    /**
     * Tries to authenticate user who is trying to log in.
     * Hashes entered password for entered email, and checks
     * if provided combination exists in the database.
     *
     * @param email
     * @param password
     * @return
     */
    public static App_User authenticate(String email, String password) {
        App_User user = finder.where().eq("email", email.toString()).findUnique();

        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Checks if user with provided email exists in database.
     * Should be used when using an email from the session,
     * to be sure that email from session exists in the database.
     *
     * @param email
     * @returns App_User object if user exists, and NULL if doesn't.
     */
    public static App_User existsInDB(String email) {
        App_User user = finder.where().eq("email", email.toString()).findUnique();

        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    /**
     * Hashes user password.
     */
    public void hashPass() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }


    /**
     * Retrieves user from the database by provided email.
     * @param email
     * @return
     */
    public static App_User getUserByEmail(String email) {
        App_User user = finder.where().eq("email", email).findUnique();
        return user;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", firstname, lastname);
    }

}