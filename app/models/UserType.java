package models;

/**
 * Model of AppUser type. Defines permissions for user.
 */

public class UserType {
    /*
     * AppUser type attributes
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
     * @param name        - AppUser's type.
     */

    public UserType(String name) {
        this.name = name;
    }
}

