package models;

/**
 * Model of App_User type. Defines permissions for user.
 */

public class UserType {
    /*
     * App_User type attributes
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
     * @param name        - App_User's type.
     */

    public UserType(String name) {
        this.name = name;
    }
}

