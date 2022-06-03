package prosthetidist.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import prosthetidist.pojos.Company;

public class Java2Xml {

	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static void printCompanies() {
	
		Query q1 = em.createNativeQuery("SELECT * FROM Company", Company.class);
		List<Company> companies = (List<Company>) q1.getResultList();
		for (Company comp : companies) {
			System.out.println(comp);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Get the entity manager
		// Note that we are using the class' entity manager
		em = Persistence.createEntityManagerFactory("prosthetidist-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		// Choose the report to turn into an XML
		// Choose his new department
	    System.out.print("Choose a company to turn into an XML file:");
		int comp_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM Company WHERE id = ?", Company.class);
		q2.setParameter(1, comp_id);
		Company comp = (Company) q2.getSingleResult();
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./xmls/Company.xml");
		marshaller.marshal(comp, file);
		// Printout
		marshaller.marshal(comp, System.out);

	}
}
