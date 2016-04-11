package example;

import java.io.Serializable;

/**
 * Represents a user of the platform.
 * A user has a name, an unique code and username and a email.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class User implements Serializable{
    private String username, email,password;
    private static int cod;

    static final public String USER_FILENAME = "users.dat";

    /**
     * Creates a new User with the given name, username and email.
     *
     * @param username	Identifier used by the user to login
     * @param password	Password of the user
     * @param email		User's email
     *
     */
    public User(String username, String password, String email){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.cod++;
    }

    /**
     * Creates a new User with the given name and username and sets Unknown
     * email.
     *
     * @param username	Identifier used by the user to login
     * @param password	Password of the user
     */
    public User(String username, String password) {
        super();
        this.password = password;
        this.username = username;
        this.email = "";
        this.cod++;
    }

    /**
     * Gets the username of this User.
     *
     * @return Username of this user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the Name of this User.
     *
     * @return Name of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of this User.
     *
     * @return Email of the user
     */
    public String getEmail(){
        return email;
    }

    /**
     * Gets the identifier of this User.
     *
     * @return Code of this user
     */
    public int getCod() {
        return cod;
    }
}