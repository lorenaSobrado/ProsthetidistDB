package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//no deberia
import prosthetidist.ifaces.ProstheticsManager;
import prosthetidist.pojos.*;


public class JDBCProstheticsManager implements ProstheticsManager {

	private JDBCManager manager;
	private JDBCCompanyManager cm;
	private JDBCMeasurementsManager mm;

	public JDBCProstheticsManager(JDBCManager m) {
		this.manager = m;
	}
	
	// @TODO comprobar si funciona
	
	public ArrayList<Prosthetic> listAllProstheticsWithCompanyId () {
	ArrayList <Prosthetic> allProsthetics = new ArrayList<Prosthetic>();
	List <Material> materials = new ArrayList<Material>();

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
				String sql2="SELECT * FROM Material  AS m JOIN Prosthetic AS p WHERE m.prosthetic_code=p.code";
				ResultSet rs2 = stat.executeQuery(sql2);
							while(rs2.next()) {
								String namemat = rs2.getString("name");
								String strength = rs2.getString("strength");
								Float pricemat = rs2.getFloat("price");
								String temperatureresistance= rs2.getString("temperatureResistance");
								String flexibility=rs2.getString("flexibility");				
								Material mat= new Material (namemat,strength, pricemat, temperatureresistance, flexibility);
								materials.add(mat);

				
				c=cm.getCompanyById(company_id);
				m=mm.getMeasurementById(measurement_id);
				Prosthetic p = new Prosthetic (code, price, functionalities, type, model, c, m, materials);
				allProsthetics.add(p);
			}
			}
			rs.close();
			stat.close();
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	return allProsthetics;
	}
	
	//crear listprosthetic by code
	public Prosthetic getProstheticByCode (int code) {
		Prosthetic pros= null;
		Company company= null;
		Measurement mes=null;
		
		try {
			String sql = "SELECT * FROM Prosthetic WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, code);
			ResultSet rs = prep.executeQuery();
			
			Float price = rs.getFloat("price");
			String functionalities = rs.getString("functionalities");
			String type = rs.getString("type");
			String model = rs.getString("model");
			int company_id = rs.getInt("company_id");
			company=cm.getCompanyById(company_id);
			int measurement_id = rs.getInt("measurement_id");
			mes=mm.getMeasurementById(measurement_id);
			//materials
			//invoice
			
			pros = new Prosthetic (code, price,functionalities,type,model,company, mes);
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		
	}
		return pros;
	
}
}
