package model;

//import java.util.UUID;

/**
 * The User class represents a user in the system.
 */
public class User {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String userType;

    /**
     * Creates a new User instance
     * 
     * @param username  | username of the user
     * @param firstName | first name of the user
     * @param lastName  | last name of the user
     * @param userType  | user type of the user
     */
    public User(String username, String firstName, String lastName, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
    }

    /**
     * Creates a new User instance (OVERLOADED)
     * 
     * @param id        | the user's UUID
     * @param username
     * @param firstName
     * @param lastName
     * @param userType
     */
    public User(String id, String username, String firstName, String lastName, String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
    }

    // Getters and Setters
    public String getUUID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
