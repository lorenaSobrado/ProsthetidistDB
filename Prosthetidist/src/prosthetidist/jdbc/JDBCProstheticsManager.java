package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//no deberia
import prosthetidist.ifaces.ProstheticsManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurements;
import prosthetidist.pojos.Prosthetics;


public class JDBCProstheticsManager implements ProstheticsManager {

	private JDBCManager manager;
	private JDBCCompanyManager cm;
	private JDBCMeasurementsManager mm;

	public JDBCProstheticsManager(JDBCManager m) {
		this.manager = m;
	}
	
	
	// @TODO comprobar si funciona
	public List<Prosthetics> listAllProsthetics () {
	List <Prosthetics> allProsthetics = new ArrayList<Prosthetics>();
	Company c= null;
	Measurements m= null;
	try {
			Statement stat = manager.getConnection().createStatement();
			String sql= "SELECT * FROM Prosthetics";
			ResultSet rs = stat.executeQuery(sql);
			//rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("Code");
				Float price = rs.getFloat("Price");
				String functionalities = rs.getString("Functionalities");
				String type = rs.getString("Type");
				String model = rs.getString("Model");
				Integer company_id = rs.getInt("Company_id");
				Integer measurement_id= rs.getInt("Measurement_id");
				
				//FALTAN LOS MATERIALES
			
				
				c=cm.getCompanyById(company_id);
				m=mm.getMeasurementById(measurement_id);
				Prosthetics p = new Prosthetics (code, price, functionalities, type, model, c);
				allProsthetics.add(p);
			}
			rs.close();
			stat.close();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	return allProsthetics;
	}
	
}
