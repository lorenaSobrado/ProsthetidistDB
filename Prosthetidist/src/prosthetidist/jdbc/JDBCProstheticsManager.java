package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//no deberia
import prosthetidist.ifaces.ProstheticsManager;
import prosthetidist.pojos.Prosthetics;


public class JDBCProstheticsManager implements ProstheticsManager {

	private JDBCManager manager;

	public JDBCProstheticsManager(JDBCManager m) {
		this.manager = m;
	}
	

	public List<Prosthetics> listAllProsthetics () {
	List <Prosthetics> allProsthetics = new ArrayList<Prosthetics>();
		
		try {
			Statement stat = manager.getConnection().createStatement();
			String sql= "SELECT * FROM Prosthetics";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Integer code = rs.getInt("Code");
				Float price = rs.getFloat("Price");
				String functionalities = rs.getString("Functionalities");
				String type = rs.getString("Type");
				String model = rs.getString("Model");
				Integer company_id = rs.getInt("Company_id");
				Integer measurement_id= rs.getInt("Measurement_id");
				
				// @TODO crear constructor, hacer metodo en compnymanagerjdbc que liste todas las companies
				//hacer search company by id
				// eso devuelve una company que le pasamos a este constructor
				Prosthetics p = new Prosthetics (code, price, functionalities, type, model, company_id, measurement_id);
				allProsthetics.add(p);
			}
			rs.close();
			stat.close();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
