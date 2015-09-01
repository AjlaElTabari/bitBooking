package models;

import java.util.Calendar;
import java.util.Date;

/**
 * Model of User type. Defines permissions for user.
 */

public class UserType {
    /*
     * User type attributes
     */
    public Integer id;
    public String name;


    /**
     * Default constructor
     */
    public UserType() {
    }

    /**
     * Constructor for creating new UserType object.
     *
     * @param name        - User's type.
     */
    public UserType(String name) {
        this.name = name;
    }
}
