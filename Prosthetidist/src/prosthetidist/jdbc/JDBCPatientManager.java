package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import prosthetidist.ifaces.PatientManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

public class JDBCPatientManager implements PatientManager {
	
	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	//@TODO materials
	
	@Override
	
	public void addPatient(Patient p) {
		
		try {
			String sql = "INSERT INTO Patient (name, email, phone,address, notes,dob) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getEmail());
			prep.setInt(3, p.getPhone());
			prep.setString(4, p.getAddress());
			prep.setString(5, p.getNotes());
			prep.setDate(0, null); // COMO SE HACE ESTO (6, p.getDob())
			
			prep.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void designProsthetic (/*String functionalities, String type,*/ Measurement measurement) {
		
		int measurement_id = measurement.getId();
		Prosthetic p= null;
		try {
			String sql= "INSERT INTO Prosthetic (functionalities, type, measurement_id) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, p.getFunctionalities()/*functionalities*/);
			prep.setString(2, p.getType()/*type*/);
			prep.setInt(3, measurement_id);
			//@HELP funciona?
			p.getCompany().setId(null);// why?? 

			prep.executeUpdate();
			//luego esta prosthetic se mete en la db, lo de null se ha metido???????
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	
	
}
