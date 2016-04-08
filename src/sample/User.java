package sample;

/**
 * Represents a user of the platform.
 * A user has a name, an unique code and username and a email.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class User{
	private String name, username, email;
	private static int cod;

	/**
	 * Creates a new User with the given name, username and email.
	 *
	 * @param username	Identifier used by the user to login
	 * @param name		Name of the user
	 * @param email		User's email
	 *
	 */
	public User(String username, String name, String email) throws java.rmi
			.RemoteException{
		this.username = username;
		this.name = name;
		this.email = email;
		this.cod++;
	}

	/**
	 * Creates a new User with the given name and username and sets Unknown
	 * email.
	 *
	 * @param name		Name of the user
	 * @param username	Identifier used by the user to login
	 */
	public User(String name, String username) throws java.rmi.RemoteException{
		this.name = name;
		this.username = username;
		this.email = "Unknown";
		this.cod++;
	}

	/**
	 * Gets the username of this User.
	 *
	 * @return Username of this user
	 */
	public String getUsername() throws java.rmi.RemoteException {
		return username;
	}

	/**
	 * Gets the Name of this User.
	 *
	 * @return Name of the user
	 */
	public String getName() throws java.rmi.RemoteException {
		return name;
	}

	/**
	 * Gets the email of this User.
	 *
	 * @return Email of the user
	 */
	public String getEmail() throws java.rmi.RemoteException {
		return email;
	}

	/**
	 * Gets the identifier of this User.
	 *
	 * @return Code of this user
	 */
	public int getCod() throws java.rmi.RemoteException {
		return cod;
	}
}
