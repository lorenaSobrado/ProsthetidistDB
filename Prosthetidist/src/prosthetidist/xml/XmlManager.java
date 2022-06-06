package prosthetidist.xml;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import prosthetidist.jdbc.JDBCCompanyManager;
import prosthetidist.jdbc.JDBCManager;
import prosthetidist.jdbc.JDBCProstheticManager;
import prosthetidist.pojos.*;

public class XmlManager {


	

	public void marshall(Company comp) throws Exception { //JAVA TO XML
		
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		File file = new File("./xmls/Company.xml");
		marshaller.marshal(comp, file);

	}
	

	public void unmarshall(JDBCCompanyManager cm, JDBCProstheticManager pm) throws JAXBException, SQLException { //XML TO JAVA
		
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File("./xmls/External-Company.xml");
		Company comp = (Company)unmarshaller.unmarshal(file);
		
		ArrayList<Company> comps=cm.getAllCompanies();
		int exist =0;
		for (Company c: comps) {
			if((c.getEmail().compareTo(comp.getEmail()))==0) {
				exist=1;
				break;
			}
			else {
				exist=0;
			}
		}
		if(exist==0) {
			cm.addCompany(comp);
			for(Prosthetic pro : comp.getProsthetics()) {
				pro.setCompany(comp);
				pm.addProstheticFromXML(pro);
			}
		}
		
		
	}
	//sourcePath--> Absolute path to source XML file
	//xsltPath --> Absolute path to xslt file
	//resultDir --> Directory where you want to put resulting files
	
	public static void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		XmlManager xm= new XmlManager();
		ArrayList <Prosthetic> pros= new ArrayList <Prosthetic>();
		//measurements y materials
		Measurement meas = new Measurement (3,24.7F,12.3F,9.7F);
		Material mat = new Material("aluminium", "HIGH", "LOW", "MEDIUM");
		Material mat2= new Material("Carbon Fiber", "LOW", "LOW", "MEDIUM");
		ArrayList <Material> mats= new ArrayList <Material>();
		mats.add(mat);
		mats.add(mat2);
		Prosthetic p = new Prosthetic (23,34.3F,"walk","left arm", "3453x", meas, mats);
		pros.add(p);
		Company c = new Company (16,"rob.arms","robotics@gmail.com",123456789,pros);
		JDBCManager m = new JDBCManager();
		JDBCCompanyManager cm= new JDBCCompanyManager(m);
		JDBCProstheticManager pm= new JDBCProstheticManager(m);

		ArrayList<Company> comps=cm.getAllCompanies();
		for(int i=0; i<comps.size(); i++) {
			
		System.out.println(comps.get(i));
		}
		try {
			//
			xm.unmarshall(cm,pm);
			//xm.marshall(c);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		
		
	}


}

















