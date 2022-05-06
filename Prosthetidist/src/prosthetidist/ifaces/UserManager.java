package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Role;
import prosthetidist.pojos.User;

public interface UserManager {
	
	public void connect();
	public void disconnect();
	public void newUser(User u);
	public void newRole (Role r);
	public Role getRole(String name);
	public List<Role> getRoles();
	/**
	 * 
	 * @param email
	 * @param passwd
	 * @return A User if there is a match, null if there isn't
	 */
	public User checkPassword(String email, String passwd);
	
}

