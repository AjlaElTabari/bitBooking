package models;

/**
 * Model of User type. Defines permissions for user.
 */
@Entity
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
<<<<<<< Updated upstream
    public User(String name) {
=======
    public UserType(String name) {
        this.id = 1;
>>>>>>> Stashed changes
        this.name = name;
    }
}

