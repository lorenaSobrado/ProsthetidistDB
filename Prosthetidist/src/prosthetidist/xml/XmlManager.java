package prosthetidist.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.internal.oxm.Unmarshaller;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;

public class XmlManager {

	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	private static void printCompanies() {

		Query q1 = em.createNativeQuery("SELECT * FROM Company", Company.class);
		List<Company> companies = (List<Company>) q1.getResultList();
		for (Company comp : companies) {
			System.out.println(comp);
		}
	}

	public void marshall() throws Exception {
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
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

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

	public void unmarshall() throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Company.xml");
		Company comp = (Company) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Company:");
		System.out.println("Name: " + comp.getName());
		System.out.println("Email: " + comp.getEmail());
		System.out.println("Phone: " + comp.getPhone());
		ArrayList<Prosthetic> pros = comp.getProsthetics();

		for (int i = 0; i < pros.size(); i++) {
			System.out.println("Code: " + pros.get(i).getCode());
			System.out.println("Price: " + pros.get(i).getPrice());
			System.out.println("Functionalities: " + pros.get(i).getFunctionalities());
			System.out.println("Type: " + pros.get(i).getType());
			System.out.println("Model: " + pros.get(i).getModel());

			Measurement m = new Measurement();

			m = pros.get(i).getMeasurement();
			System.out.println("Length: " + m.getLengthiness());
			System.out.println("Width: " + m.getWidth());
			System.out.println("Weight: " + m.getWeight());

		}

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		for (Prosthetic p : pros) {
			em.persist(p);
		}
		em.persist(comp);

		// End transaction
		tx1.commit();
	}
}
