package prosthetidist.xml;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.pojos.Company;

public class XmlManager {

	private static EntityManager em;
	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	

	public void marshall(Company comp) throws Exception { //JAVA TO XML
		
		em = Persistence.createEntityManagerFactory("prosthetidist-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		//marshal the Java object to a file
		
		File file = new File("./xmls/Company.xml");
		marshaller.marshal(comp, file);

	}

	public void unmarshall(JDBCCompanyManager cm) throws JAXBException, SQLException { //XML TO JAVA
		
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


		File file = new File("./xmls/External-Company.xml");
		Company comp = (Company)unmarshaller.unmarshal(file);
		
		cm.addCompany(comp);

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction et = em.getTransaction();

		// Start transaction
		et.begin();
		em.persist(comp);

		// End transaction
		et.commit();
	}
	
	public static void main(String args[]) {
		System.out.println("Choose a company");
		Company c = new Company (15,"exo","eleledj",123456789);
		
		
	}
}
















