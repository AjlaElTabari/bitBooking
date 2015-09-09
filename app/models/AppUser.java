package models;

import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Model of AppUser. AppUser is a person who sign up into database on bitBooking.com web page
 * and has permissions depending on type of user
 */
@Entity
public class AppUser extends Model{

    private static Finder<String, AppUser> finder = new Finder<>(String.class, AppUser.class);
   
    /*
     * AppUser attributes
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
    public AppUser() {
    }

    /**
     * Constructor for creating new AppUser object.
     *
     * @param firstName   - AppUser's first name.
     * @param lastName    - AppUser's last name.
     * @param email       - AppUser's email address.
     * @param password    - AppUser's password.
     * @param phoneNumber - AppUser's phone number.
     */
    public AppUser(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
        System.out.println(this.password);
        this.phoneNumber = phoneNumber;
    }




    public static AppUser authenticate(String email, String password) {
        AppUser user = finder.where().eq("email", email.toString()).findUnique();

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
