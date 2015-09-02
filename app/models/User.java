package models;

import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Model of User. User is a person who sign up into database on bitBooking.com web page
 * and has permissions depending on type of user
 */
@Entity
public class User extends Model{

    private static Finder<String,User> finder = new Finder<>(String.class, User.class);
   
    /*
     * User attributes
     */
    @Id
    public Integer id;
    @Column
    public String firstName;
    @Column
    public String lastName;
    @Column
    public String email;
    @Column
    public String password;


    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor for creating new User object.
     *
     * @param firstName   - User's first name.
     * @param lastName    - User's last name.
     * @param email       - User's email address.
     * @param password    - User's password.
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }




    public static User authenticate(String email, String password) {
        User user = finder.where().eq("email", email.toString()).findUnique();

        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }
}
