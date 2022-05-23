
package prosthetidist.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import prosthetidist.pojos.*;
import prosthetidist.ifaces.UserManager;


//PREGUNTAR SI TENEMOS QUE INICIALIZAR JPA Y JDBC EN CADA DISPLAY
//GENERATE CONSTRUCTORS EN SWING?? PARA JPA


public class JPAUserManager implements UserManager {

	private EntityManager em;
	
	
	public JPAUserManager() {
		this.connect();
	}


	private void connect() {
		
		//we can only use persist in classes that are annotated
		
		em = Persistence.createEntityManagerFactory("prosthetidist-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		
		if (this.getRoles().isEmpty()) {
			Role company = new Role("Company");
			Role patient = new Role("Patient");
			this.newRole(company);
			this.newRole(patient);
		}
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	

	private void newRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM Role WHERE name = ?", Role.class);
		q.setParameter(1, name);
		return (Role) q.getSingleResult();
	}
	
	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM Role", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}

	@Override
	public User checkPassword(String email, String passwd) {
		
		//PASSWORDS ARE NERVER STORED INTO THE DATA BASE
		//TODO investigar
		// null user if match not found
		
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM User WHERE email = ? AND password = ?", User.class); //user no debe ser con mayuscula?
		q.setParameter(1, email);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); //MD5 most common algorithim 
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			q.setParameter(2, digest);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			u = (User) q.getSingleResult();
			
		} catch (NoResultException e) {}
		
		return u;
	}



	
}
