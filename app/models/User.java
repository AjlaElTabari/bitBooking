package models;

import java.util.Calendar;
import java.util.Date;

/**
 * Model of User. User is a person who sign up into database on bitBooking.com web page
 * and has permissions depending on type of user
 */
@Entity
public class User {
   
    /*
     * User attributes
     */
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String cityZipCode;
    public UserType userType;
    public String password;
    public Boolean gender;
    public Date date;

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
     * @param cityZipCode - User's postal code.
     * @param userType    - Type of buyer.
     * @param password    - User's password.
     * @param gender      - Male or female
     */
    public User(String firstName, String lastName, String email, String cityZipCode, UserType userType, String password, Boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cityZipCode = cityZipCode;
        this.userType = userType;
        this.password = password;
        this.gender = gender;
        this.date = Calendar.getInstance().getTime();
    }
}
