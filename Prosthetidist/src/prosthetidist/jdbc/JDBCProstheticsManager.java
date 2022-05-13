package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//no deberia
import prosthetidist.ifaces.ProstheticsManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;


public class JDBCProstheticsManager implements ProstheticsManager {

	private JDBCManager manager;
	private JDBCCompanyManager cm;
	private JDBCMeasurementsManager mm;

	public JDBCProstheticsManager(JDBCManager m) {
		this.manager = m;
	}
	
	// @TODO comprobar si funciona
	
	public List<Prosthetic> listAllProstheticsWithCompanyID () {
	List <Prosthetic> allProsthetics = new ArrayList<Prosthetic>();
	Company c= null;
	Measurement m= null;
	try {
			Statement stat = manager.getConnection().createStatement();
			String sql= "SELECT * FROM Prosthetic WHERE company_id IS NOT NULL";
			ResultSet rs = stat.executeQuery(sql);
			//rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("code");
				Float price = rs.getFloat("price");
				String functionalities = rs.getString("functionalities");
				String type = rs.getString("type");
				String model = rs.getString("model");
				Integer company_id = rs.getInt("company_id");
				Integer measurement_id= rs.getInt("measurement_id");
				
				//FALTAN LOS MATERIALES
			
				
				c=cm.getCompanyById(company_id);
				m=mm.getMeasurementById(measurement_id);
				Prosthetic p = new Prosthetic (code, price, functionalities, type, model, c, m);
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
