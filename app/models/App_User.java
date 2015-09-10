package models;

import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
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

    @Constraints.Required(message = "Please input your first name")
    @Constraints.MinLength(value=2, message = "First name must be at least 2 letters long!")
    @Constraints.Pattern("^[a-zA-Z]+$")
    public String firstname;

    @Constraints.Required(message = "Please input your last name")
    @Constraints.MinLength(value=2, message="Last name must be at leastt 2 letters long!")
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
    @Constraints.Pattern(value="\\d+", message = "Phone number can contain digits only!")
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


    public App_User getUserByEmail(String email){
        App_User user = finder.where().eq("email",email).findUnique();
        return user;
    }

}