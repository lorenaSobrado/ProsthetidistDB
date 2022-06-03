package prosthetidist.xml;

import java.io.File;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import prosthetidist.pojos.*;


public class Xml2Java {

	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

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
		ArrayList<Prosthetic> pros= comp.getProsthetics();
		
		
	
		
		for (int i=0; i<pros.size();i++) {
			System.out.println("Code: " + pros.get(i).getCode());
			System.out.println("Price: " + pros.get(i).getPrice());
			System.out.println("Functionalities: " + pros.get(i).getFunctionalities());
			System.out.println("Type: " + pros.get(i).getType());
			System.out.println("Model: " + pros.get(i).getModel());
			
			Measurement m= new Measurement ();
			
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


