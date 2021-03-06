package prosthetidist.xml;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCMeasurementManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.*;

public class XmlManager {

	public void marshall(Company comp) throws Exception { // JAVA TO XML

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Marshaller marshaller = jaxbContext.createMarshaller();

		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		File file = new File("./xmls/Company.xml");
		marshaller.marshal(comp, file);

	}

	public void unmarshall(JDBCCompanyManager cm, JDBCProstheticManager pm, JDBCMeasurementManager mm) throws JAXBException, SQLException { // XML TO JAVA

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		File file = new File("./xmls/External-Company.xml");
		Company comp = (Company) unmarshaller.unmarshal(file);
		ArrayList<Company> comps = cm.getAllCompanies();
		int exist = 0;
		for (Company c : comps) {
			if (c.getEmail().compareTo(comp.getEmail()) == 0) {
				exist = 1;
				break;
			}
		}
		if (exist == 0) {
			cm.addCompany(comp);
// 			WE HAD PROBLEMS CATCHING THE PROSTHETICS AND MEASUREMENTS (THEY RETURNED AS NULL) SO AREN'T ADDED TO THE DB 			
//			Integer companyId = cm.getLastCompanyId();
//			comp = cm.getCompanyById(companyId);
//			for (Prosthetic pro : comp.getProsthetics()) {
//				if (mm.getMeasurementById(pro.getMeasurement().getId()) == null) {
//					mm.addMeasurement(pro.getMeasurement());
//					System.out.println(pro.getMeasurement());
//				}
//				pm.addProstheticFromXML(pro, comp);
//			}
		}
	}
	
	// sourcePath--> Absolute path to source XML file
	// xsltPath --> Absolute path to xslt file
	// resultDir --> Directory where you want to put resulting files

	public static void simpleTransform(String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)), new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
